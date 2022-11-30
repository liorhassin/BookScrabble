package test;

import java.util.Objects;
import java.util.Random;
public class Tile {
    public final char letter;
    public final int score;

    // default Ctor that will reset all the elements.
    Tile(char letter, int score) {
        this.letter = letter;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return letter == tile.letter && score == tile.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter, score);
    }

    // Class BAG
    public static class Bag {
        public int[] letters;
        public Tile[] tiles;
        public int[] score;
        public int countLetter;
        public final int[] defaultLetters;
        private static Bag checkBag = null;

        private Bag() {
            this.score = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
            this.letters = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            this.defaultLetters = new int[]{9, 2, 2, 4, 12, 2, 3, 2, 9, 1, 1, 4, 2, 6, 8, 2, 1, 6, 4, 6, 4, 2, 2, 1, 2, 1};
            this.countLetter = 98;
            this.tiles = new Tile[26];
            int i = 0;
            for (char c = 'A'; c <= 'Z'; c++, i++) {
                this.tiles[i] = new Tile(c, this.score[i]);
            }
        }

        public Tile getRand() {
            Random random = new Random();
            int randomNumber;
            if (countLetter != 0) {
                while (true) {
                    randomNumber = random.nextInt(25); // generate random number
                    if (letters[randomNumber] != 0) {
                        letters[randomNumber] -= 1;
                        countLetter--;
                        return tiles[randomNumber];
                    }
                }
            }
            return null;
        }

        public Tile getTile(char t) {
            if (countLetter != 0) {
                if(t>='A' && t<='Z') {
                    if (letters[t - 'A'] != 0) {
                        letters[t - 'A']--;
                        countLetter--;
                        return tiles[t - 'A'];
                    }
                }
            }
            return null;
        }

        public void put(Tile t) {
            if(countLetter < 98) {
                if (letters[t.letter - 'A'] < defaultLetters[t.letter - 'A']) {
                    letters[t.letter - 'A']++;
                    countLetter++;
                }
            }
        }

        public int size() {
            return countLetter;
        }
        public int[] getQuantities(){
            int[] newArray = new int[26];
            System.arraycopy(this.letters, 0, newArray, 0, 26);
            return newArray;
        }

        public static Bag getBag(){
            if (checkBag == null){
                checkBag = new Bag();
            }
            return checkBag;
        }
    }
}
