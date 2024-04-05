package Minesweeper;

public class Tile {
    int xCoord;
    int yCoord;
    boolean isBomb = false;
    boolean isRevealed = false;

    boolean isFlagged = false;
    int adjacentBombs = 0;

    void setCoords(int x, int y) {
        xCoord = x;
        yCoord = y;

    }
}

