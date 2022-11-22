package test;
import java.util.Arrays;

public class Word {
 private final Tile[] tiles;
 private final int row,col;
 private final boolean vertical;

    public Word(Tile[] tilesArr, int row, int col, boolean vertical) {
        tiles = new Tile[tilesArr.length];
        System.arraycopy(tilesArr, 0, tiles, 0, tilesArr.length);
        this.row = row;
        this.col = col;
        this.vertical = vertical;
    }

    //Getters
    public Tile[] getTilesArr() {
        return tiles;
    }

    public void setTiles(Tile[] tiles) {
        System.arraycopy(tiles, 0, this.tiles, 0, tiles.length);
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
        return row == word.row && col == word.col && vertical == word.vertical && Arrays.equals(tiles, word.tiles);
    }
}
