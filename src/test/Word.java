package test;


import java.util.Arrays;
import java.util.Objects;

public class Word {
 private Tile[] tilesArr;
 private final int row,col;
 private boolean vertical;

    public Word(Tile[] tilesArr, int row, int col, boolean vertical) {
        Tile[] newTiles = new Tile[tilesArr.length];
        System.arraycopy(tilesArr, 0, newTiles, 0, tilesArr.length);
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    //Getters
    public Tile[] getTilesArr() {
        return tilesArr;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return row == word.row && col == word.col && vertical == word.vertical && Arrays.equals(tilesArr, word.tilesArr);
    }


}
