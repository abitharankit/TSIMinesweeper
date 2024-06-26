package Minesweeper;

import java.util.LinkedList;

import static Minesweeper.Utility.*;


public class Main {


    public static void endGame(String winLose) {
        if (winLose.equalsIgnoreCase("WIN")) {
            System.out.println("WIN");
        } else if (winLose.equalsIgnoreCase("LOSE")) {
            System.out.println("LOSE");
        }
    }

    public static String revealTile(Board myBoard, int xCoord, int yCoord) {
        String gameState = "CONT";
        myBoard.tilesList.get(xCoord).get(yCoord).isRevealed =true;
        myBoard.totalUnrevealedTiles--;
        if (myBoard.totalUnrevealedTiles==((myBoard.xSize * myBoard.ySize)-1)) {
            myBoard.generateBombs(xCoord,yCoord);
        }
        if (myBoard.tilesList.get(xCoord).get(yCoord).isBomb){
            myBoard.revealBombs();
            gameState = "LOSE";
        } else if (myBoard.tilesList.get(xCoord).get(yCoord).adjacentBombs == 0){

            if (xCoord > 0) { //left
                if (yCoord > 0) {
                    if (!myBoard.tilesList.get(xCoord-1).get(yCoord-1).isRevealed) {
                        revealTile(myBoard, xCoord - 1, yCoord - 1); //up
                    }
                }
                if (yCoord < (myBoard.ySize-1)){ //down
                    if (!myBoard.tilesList.get(xCoord-1).get(yCoord+1).isRevealed) {
                        revealTile(myBoard, xCoord - 1, yCoord + 1);
                    }
                }
                if (!myBoard.tilesList.get(xCoord-1).get(yCoord).isRevealed) {
                    revealTile(myBoard, xCoord - 1, yCoord);
                }
            }
            if (xCoord < (myBoard.xSize - 1)) { //right
                if (yCoord > 0) { //up
                    if (!myBoard.tilesList.get(xCoord+1).get(yCoord-1).isRevealed) {
                        revealTile(myBoard, xCoord + 1, yCoord - 1);
                    }
                }
                if (yCoord < (myBoard.ySize-1)) { //down
                    if (!myBoard.tilesList.get(xCoord+1).get(yCoord+1).isRevealed) {
                        revealTile(myBoard, xCoord + 1, yCoord + 1);
                    }
                }
                if (!myBoard.tilesList.get(xCoord+1).get(yCoord).isRevealed) {
                    revealTile(myBoard, xCoord + 1, yCoord);
                }
            }
            if (yCoord > 0) { //up middle
                if (!myBoard.tilesList.get(xCoord).get(yCoord-1).isRevealed) {
                    revealTile(myBoard, xCoord, yCoord - 1);
                }
            }
            if (yCoord < (myBoard.ySize-1)) {
                if (!myBoard.tilesList.get(xCoord).get(yCoord+1).isRevealed) {
                    revealTile(myBoard, xCoord, yCoord + 1);
                }
            }

        }
        if (myBoard.totalUnrevealedTiles == myBoard.numBombs){
            gameState = "WIN";
        }
        return gameState;
    }
    public static String playTurn(Board myBoard, int boardXSize, int boardYSize){
        LinkedList<String> userOptions = new LinkedList<String>();
        userOptions.add("R"); //Option for revealing tile
        userOptions.add("F"); //Option for flagging tile
        LinkedList<String> confirmation = new LinkedList<String>();
        confirmation.add("Y");
        confirmation.add("N");
        String gameState = "CONT";
        String userInput = userInputChar("Would you like to reveal a tile (R) or flag a tile (F): ",
                userOptions);
        if (userInput.equalsIgnoreCase("R")) {
            System.out.println("What are the coordinates of the tile you want to reveal?");
        } else {
            System.out.println("What are the coordinates of the tile you want to flag?");
        }
        int userXCoord = userInputIntRange("X coordinate: ", 1, boardXSize) - 1;
        int userYCoord = userInputIntRange("Y coordinate: ",1, boardYSize) - 1;
        if (userInput.equalsIgnoreCase("R")) {
            if (myBoard.tilesList.get(userXCoord).get(userYCoord).isRevealed){
                System.out.println("This tile has already been revealed");
            } else if (myBoard.tilesList.get(userXCoord).get(userYCoord).isFlagged) {  // Checks if user is trying to reveal a flagged tile
                System.out.println("This tile is flagged.");
                String revealFlag = userInputChar("Would you like to remove the flag and reveal the tile? (Y/N)",
                        confirmation);
                if (revealFlag.equalsIgnoreCase("Y"))  {
                    myBoard.tilesList.get(userXCoord).get(userYCoord).isFlagged = false;
                    gameState = revealTile(myBoard,userXCoord,userYCoord);
//                    myBoard.tilesList.get(userXCoord).get(userYCoord).isRevealed = true;
//                    myBoard.totalUnrevealedTiles--;
//                    if (myBoard.totalUnrevealedTiles==((myBoard.xSize * myBoard.ySize)-1)) {
//                        myBoard.generateBombs(userXCoord,userYCoord);
//                    }
//                    if (myBoard.tilesList.get(userXCoord).get(userYCoord).isBomb){
//                        gameState = "LOSE";
//                    } else if (myBoard.totalUnrevealedTiles == myBoard.numBombs) {
//                        gameState = "WIN";
//                    }

                }
            } else {
                gameState = revealTile(myBoard,userXCoord,userYCoord);
//                myBoard.tilesList.get(userXCoord).get(userYCoord).isRevealed = true;
//                myBoard.totalUnrevealedTiles--;
//                if (myBoard.totalUnrevealedTiles==((myBoard.xSize * myBoard.ySize)-1)) {
//                    myBoard.generateBombs(userXCoord,userYCoord);
//                }
//                if (myBoard.tilesList.get(userXCoord).get(userYCoord).isBomb){
//                    gameState = "LOSE";
//                } else if (myBoard.totalUnrevealedTiles == myBoard.numBombs) {
//                    gameState = "WIN";
//                }
            }
        } else if (userInput.equalsIgnoreCase("F")) {
            if (myBoard.tilesList.get(userXCoord).get(userYCoord).isRevealed){
                System.out.println("This tile has already been revealed");
            } else if (myBoard.tilesList.get(userXCoord).get(userYCoord).isFlagged) {
                System.out.println("This tile is already flagged.");
                String revealFlag = userInputChar("Would you like to remove the flag? (Y/N)", confirmation);
                if (revealFlag.equalsIgnoreCase("Y"))  {
                    myBoard.tilesList.get(userXCoord).get(userYCoord).isFlagged = false;
                }
            } else {
                myBoard.tilesList.get(userXCoord).get(userYCoord).isFlagged = true;
            }

        }
        myBoard.displayBoard();
        return gameState;
    }


    public static void main(String[] args) {
        String gameState = "CONT";
        Board myBoard = new Board();
        System.out.println("How big do you want the board to be?");
        int boardXSize = userInputInt("X Size: ", 2);
        int boardYSize = userInputInt("Y Size: ", 2);
        int totalTileCount = boardXSize * boardYSize;
        int bombChoice = userInputIntRange("How many bombs would you like on the board? ", 1,
                (totalTileCount-1));
        myBoard.setBoard(boardXSize, boardYSize, bombChoice);
        myBoard.displayBoard();

        while (gameState.equalsIgnoreCase("CONT")) {
            gameState = playTurn(myBoard,boardXSize,boardYSize);
        }
        if (gameState.equalsIgnoreCase("WIN")) {
            System.out.println("You Win!!! Congrats!");
        } else if (gameState.equalsIgnoreCase("LOSE")) {
            System.out.println("***** KABOOM! *****");
            System.out.println("You Lose!!! Better Luck Next Time!");
        }

    }
}
