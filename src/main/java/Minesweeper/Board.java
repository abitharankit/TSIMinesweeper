package Minesweeper;

import java.util.ArrayList;
import java.util.LinkedList;

import static Minesweeper.Utility.padding;

public class Board {
    int xSize;
    int ySize;
    int numBombs;
    int pad = 5;
    int totalUnrevealedTiles;
    ArrayList<ArrayList<Tile>> tilesList = new ArrayList<>();
    void setBoard(int xSizeChoice , int ySizeChoice, int numBombsChoice) {
        xSize = xSizeChoice;
        ySize = ySizeChoice;

        totalUnrevealedTiles = xSize * ySize;
        numBombs = numBombsChoice;
        if (xSize>=ySize) {
            int stringLength = Integer.toString(xSize).length() + 2;
            if (pad < stringLength){
                pad = stringLength;
            }
        } else {
            int stringLength = Integer.toString(ySize).length() + 2;
            if (pad < stringLength){
                pad = stringLength;
            }
        }
        for (int x = 1; x <= xSize ; x++) {
            tilesList.add(new ArrayList<Tile>());
            for(int y = 1; y <= ySize; y++) {
                tilesList.get(x-1).add(new Tile());
                tilesList.get(x-1).get(y-1).setCoords(x,y);
            }
        }
    }
    void generateBombs(int x, int y) { //takes the coordinates of the first chosen tile
        LinkedList<String> bombLocations = new LinkedList<String>();
        bombLocations.add((x+1)+","+(y+1));
        for (int i=1; i<= numBombs; i++) {
            int bombX = 0;
            int bombY = 0;
            do {
                //Random generates bomb location
                bombX = (int) (Math.random() * xSize + 1);
                bombY = (int) (Math.random() * ySize + 1);
            } while (bombLocations.contains(bombX + "," + bombY));  //Checks if bomb already exists in that location
            tilesList.get(bombX - 1).get(bombY - 1).isBomb = true; //Adds bomb to location list
            bombLocations.add(bombX + "," + bombY); //Changes bomb attribute

            //Add one to all adjacent bomb counts for nearby tile
            if (bombX != 1) {
                if (bombY != 1) {
                    tilesList.get(bombX - 2).get(bombY - 2).adjacentBombs++; //top left
                }
                if (bombY != ySize) {
                    tilesList.get(bombX - 2).get(bombY).adjacentBombs++; //bottom left
                }
                tilesList.get(bombX - 2).get(bombY - 1).adjacentBombs++; //middle left
            }
            if (bombX != xSize) {
                if (bombY != 1) {
                    tilesList.get(bombX).get(bombY - 2).adjacentBombs++; //top right
                }
                if (bombY != ySize) {
                    tilesList.get(bombX).get(bombY).adjacentBombs++; //bottom right
                }
                tilesList.get(bombX).get(bombY - 1).adjacentBombs++; //middle right
            }
            if (bombY != 1) {
                tilesList.get(bombX - 1).get(bombY - 2).adjacentBombs++; //top middle
            }
            if (bombY != ySize) {
                tilesList.get(bombX - 1).get(bombY).adjacentBombs++; //bottom middle
            }
//            tilesList.get(bombX - 2).get(bombY - 2).adjacentBombs++; //top left
//            tilesList.get(bombX - 2).get(bombY - 1).adjacentBombs++; //middle left
//            tilesList.get(bombX - 2).get(bombY).adjacentBombs++; //bottom left
//            tilesList.get(bombX - 1).get(bombY - 2).adjacentBombs++; //top middle
//            tilesList.get(bombX - 1).get(bombY).adjacentBombs++; //bottom middle
//            tilesList.get(bombX).get(bombY - 2).adjacentBombs++; //top right
//            tilesList.get(bombX).get(bombY - 1).adjacentBombs++; //middle right
//            tilesList.get(bombX).get(bombY).adjacentBombs++; //bottom right

        }
    }

    void revealBombs() {
        for (int x = 0 ; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (tilesList.get(x).get(y).isBomb){
                    tilesList.get(x).get(y).isRevealed = true;
                }
            }
        }

    }

    void displayBoard() {
        //Go through each row first
        for (int y = 0; y <=ySize; y++) {
            String line = "";
            for (int x = 0; x <= xSize; x++) {
                //First create the locators
                if (y==0){
                    if (x==0) {
                        line += padding("   ", pad);
                    } else {
                        line += padding(String.valueOf(x) ,pad);
                    }
                }
                if (x==0 && y!=0) {
                    line += padding(String.valueOf(y), pad);
                } else if (x!=0 && y!=0){ // Handles all tiles
                    if (tilesList.get(x-1).get(y-1).isRevealed == false &&
                            tilesList.get(x-1).get(y-1).isFlagged == false){    // Non revealed tile not flagged
                        line += padding("██",pad);
                    } else if (tilesList.get(x-1).get(y-1).isRevealed == false &&
                            tilesList.get(x-1).get(y-1).isFlagged == true){ // Flagged tile
                        line += padding("¶",pad);
                    } else if (tilesList.get(x-1).get(y-1).isRevealed == true &&
                            tilesList.get(x-1).get(y-1).isBomb == true) { // Revealed bomb tile
                        line += padding("*", pad);
                    } else if (tilesList.get(x-1).get(y-1).isRevealed == true &&
                            tilesList.get(x-1).get(y-1).isBomb == false) { //Revealed tiles
                        int aBombs = tilesList.get(x-1).get(y-1).adjacentBombs; // If there are no adjacent bombs
                        if (aBombs == 0 ) {
                            line += padding("",5);

                        }else {
                            line += padding(String.valueOf(aBombs),5); // If there are adjacent bombs
                        }

                    }
                }
            }
            System.out.println(line);
        }
    }
}


