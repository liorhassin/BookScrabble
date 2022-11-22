package test;


import javax.swing.table.TableCellEditor;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Board {
    private static final int N = 15;
    private static Board checkBoard = null;
    public Checker[][] gameBoard = new Checker[N][N];

    public ArrayList<Word> wordsArray = new ArrayList<Word>();

    private Board(){
        buildBoard();
    }
    public void buildBoard(){
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                gameBoard[i][j] = new Checker();
            }
        }
        //Setting Start Checker
        gameBoard[7][7].setColor(5);
        //Setting Red Checkers
        gameBoard[0][0].setColor(4);
        gameBoard[0][7].setColor(4);
        gameBoard[0][14].setColor(4);
        gameBoard[7][0].setColor(4);
        gameBoard[7][14].setColor(4);
        gameBoard[14][0].setColor(4);
        gameBoard[14][7].setColor(4);
        gameBoard[14][14].setColor(4);

        //Setting Yellow Checkers
        gameBoard[1][1].setColor(3);
        gameBoard[2][2].setColor(3);
        gameBoard[3][3].setColor(3);
        gameBoard[4][4].setColor(3);
        gameBoard[1][13].setColor(3);
        gameBoard[2][12].setColor(3);
        gameBoard[3][11].setColor(3);
        gameBoard[4][10].setColor(3);
        gameBoard[13][13].setColor(3);
        gameBoard[12][12].setColor(3);
        gameBoard[11][11].setColor(3);
        gameBoard[10][10].setColor(3);
        gameBoard[10][4].setColor(3);
        gameBoard[11][3].setColor(3);
        gameBoard[12][2].setColor(3);
        gameBoard[13][1].setColor(3);

        //Setting Blue Checkers
        gameBoard[1][5].setColor(2);
        gameBoard[1][9].setColor(2);
        gameBoard[5][1].setColor(2);
        gameBoard[5][5].setColor(2);
        gameBoard[5][9].setColor(2);
        gameBoard[5][13].setColor(2);
        gameBoard[9][1].setColor(2);
        gameBoard[9][5].setColor(2);
        gameBoard[9][9].setColor(2);
        gameBoard[9][13].setColor(2);
        gameBoard[13][5].setColor(2);
        gameBoard[13][9].setColor(2);

        //Setting Cyan Checkers
        gameBoard[0][3].setColor(1);
        gameBoard[0][11].setColor(1);
        gameBoard[2][6].setColor(1);
        gameBoard[2][8].setColor(1);
        gameBoard[3][0].setColor(1);
        gameBoard[3][7].setColor(1);
        gameBoard[3][14].setColor(1);
        gameBoard[6][2].setColor(1);
        gameBoard[6][6].setColor(1);
        gameBoard[6][8].setColor(1);
        gameBoard[6][12].setColor(1);
        gameBoard[7][3].setColor(1);
        gameBoard[7][11].setColor(1);
        gameBoard[8][2].setColor(1);
        gameBoard[8][6].setColor(1);
        gameBoard[8][8].setColor(1);
        gameBoard[8][12].setColor(1);
        gameBoard[11][0].setColor(1);
        gameBoard[11][7].setColor(1);
        gameBoard[11][14].setColor(1);
        gameBoard[12][6].setColor(1);
        gameBoard[12][8].setColor(1);
        gameBoard[14][3].setColor(1);
        gameBoard[14][11].setColor(1);

    }

    /***
     * Return board if he was already created, Else create a new board.
     * @return
     */
    public static Board getBoard(){
        if (checkBoard == null){
            checkBoard = new Board();
        }
        return checkBoard;
    }


    /***
     * Method for testing.
     * Prints the board when needed with all words already in it.
     */
    public void printBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (this.gameBoard[i][j].getTile() != null)
                    System.out.print(gameBoard[i][j].getTile().letter + " ");
                else
                    System.out.print("- ");
            }
            System.out.println("\n");
        }
    }


    /***
     * @return matrix of the tiles, if Tile is empty it will return null in that spot.
     */
    public Tile[][] getTiles(){
        Tile[][] tileMath = new Tile[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tileMath[i][j] = gameBoard[i][j].getTile();
            }
        }
        return tileMath;
    }


    /***
     *  Check if the word is within the board or not.
     * @param w - Given word to check
     * @return - True/False
     */
    public boolean withinBoard(Word w){
        if(w.getCol() >= 0 && w.getCol() <= 14 && w.getRow() >= 0 && w.getRow() <= 14){
            if(w.isVertical()){
                return w.getRow() + w.getTilesArr().length <= 14;
            }
            else{
                return w.getCol() + w.getTilesArr().length <= 14;
            }
        }
        return false;
    }

    /***
     * Check if the word tiles location is empty or same letter.
     * @param w - Given word to check
     * @return - True/False
     */
    public boolean wordPlaceIsLegal(Word w){
        if(w.isVertical()){
            for(int i = 0; i<w.getTilesArr().length; i++){
                if(gameBoard[w.getRow()+i][w.getCol()].getTile() != w.getTilesArr()[i] && gameBoard[w.getRow()+i][w.getCol()].getTile() != null){
                    return false;
                }
            }
        }
        else{
            for(int j = 0; j<w.getTilesArr().length; j++){
                if(gameBoard[w.getRow()][w.getCol()+j].getTile() != w.getTilesArr()[j] && gameBoard[w.getRow()][w.getCol()+j].getTile() != null){
                    return false;
                }
            }
        }
        return true;
    }

    /***
     * Check if there is at least one tile linked to the location of the new word, Unless this is the first word.
     * @param w
     * @return boolean if there is at least one adj tile.
     */
    public boolean oneTileAdj(Word w){
        if(w.isVertical()){
            for(int i = 0; i<w.getTilesArr().length; i++){
                if(w.getRow()+i+1 <= 14 && gameBoard[w.getRow()+i+1][w.getCol()].getTile() != null ||
                        w.getRow()+i-1 >= 0 && gameBoard[w.getRow()+i-1][w.getCol()].getTile() != null ||
                        w.getCol()+1 <= 14 && gameBoard[w.getRow()+i][w.getCol()+1].getTile() != null ||
                        w.getCol()-1 >= 0 && gameBoard[w.getRow()+i][w.getCol()-1].getTile() != null){
                    return true;
                }
            }
        }
        else{
            for(int j = 0; j<w.getTilesArr().length; j++){
                if(w.getRow()+1 <= 14 && gameBoard[w.getRow()+1][w.getCol()+j].getTile() != null ||
                        w.getRow()+j-1 >= 0 && gameBoard[w.getRow()-1][w.getCol()+j].getTile() != null ||
                        w.getCol()+1 <= 14 && gameBoard[w.getRow()][w.getCol()+j+1].getTile() != null ||
                        w.getCol()-1 >= 0 && gameBoard[w.getRow()][w.getCol()+j-1].getTile() != null){
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Check if the word being placed is the first one, If so it should be placed on the star location.
     * @param w - Given word to check
     * @return True/False if this is the first word trying
     */
    public boolean firstWord(Word w){
        if(gameBoard[7][7].getTile() == null){
            if(w.isVertical() && w.getCol() == 7 && w.getRow() <= 7){
                return w.getRow() + w.getTilesArr().length >= 7 && w.getRow() + w.getTilesArr().length <= 14;
            }
            else{
                if(!w.isVertical() && w.getRow() == 7 && w.getCol() <= 7){
                    return w.getCol() + w.getTilesArr().length >= 7 && w.getCol() + w.getTilesArr().length <= 14;
                }
            }
        }
        return false;
    }


    /***
     * Check if new word location is legal using helper methods.
     * @param w
     * @return
     */
    public boolean boardLegal(Word w) {
        if (w.getTilesArr() != null) {
            if (firstWord(w)) {
                return withinBoard(w) && wordPlaceIsLegal(w);
            }
            return withinBoard(w) && wordPlaceIsLegal(w) && oneTileAdj(w);
        }
        return false;
    }

    /***
     * For now always returns true until we use dictionary's.
     * @param w - Given word to check in dictionary.
     * @return boolean (Word is legal/or not)
     */
    public boolean dictionaryLegal(Word w){
        return true;
    }


    /***
     * Tile was found adj(horizontally) to one of the tiles.
     * @return New word that was created with one of the tiles from the original word.
     */
    public Word readWord(int row, int col, Tile t){
        int tempCol = col;
        int startCol;
        while(tempCol-1 >= 0 && gameBoard[row][tempCol-1].getTile()!=null){
            tempCol--;
        }
        startCol = tempCol;
        if(gameBoard[row][tempCol].getTile() == null){
            tempCol++;
        }
        ArrayList<Tile> tempTiles = new ArrayList<Tile>();
        while(tempCol <= 14 && tempCol<col && gameBoard[row][tempCol].getTile()!=null){
            tempTiles.add(gameBoard[row][tempCol].getTile());
            tempCol++;
        }

        //Add starting letter.
        tempTiles.add(t);

        //Second check if there are letter below the given starting letter.
        tempCol = col+1;
        while(tempCol <= 14 && gameBoard[row][tempCol].getTile()!=null){
            tempTiles.add(gameBoard[row][tempCol].getTile());
            tempCol++;
        }

        //Copy from ArrayList to Tile[];
        Tile[] newTiles = new Tile[tempTiles.size()];
        for(int j = 0; j<tempTiles.size(); j++){
            newTiles[j] = tempTiles.get(j);
        }
        return new Word(newTiles, row, startCol, false);
    }

    /***
     * Tile was found adj(vertically) to one of the tiles.
     * @return New word that was created with one of the tiles from the original word.
     */
    public Word readWordVertical(int row, int col, Tile t){
        //First check if there are letters above the given starting letter.
        int tempRow = row;
        int startRow;
        while(tempRow-1 >= 0 && gameBoard[tempRow-1][col].getTile()!=null){
            tempRow--;
        }
        startRow = tempRow;
        if(gameBoard[tempRow][col].getTile() == null){
            tempRow++;
        }
        ArrayList<Tile> tempTiles = new ArrayList<Tile>();
        while(tempRow <= 14 && tempRow<row && gameBoard[tempRow][col].getTile()!=null){
            tempTiles.add(gameBoard[tempRow][col].getTile());
            tempRow++;
        }

        //Add starting letter.
        tempTiles.add(t);

        //Second check if there are letter below the given starting letter.
        tempRow = row+1;
        while(tempRow <= 14 && gameBoard[tempRow][col].getTile()!=null){
            tempTiles.add(gameBoard[tempRow][col].getTile());
            tempRow++;
        }

        //Copy from ArrayList to Tile[];
        Tile[] newTiles = new Tile[tempTiles.size()];
        for(int j = 0; j<tempTiles.size(); j++){
            newTiles[j] = tempTiles.get(j);
        }
        return new Word(newTiles, startRow, col, true);
    }

    /***
     * Check if given word is part of a bigger word(Horizontally)
     * @return The new word created(left to right)
     */
    public Word edgesWord(int row, int col, Word w){
        Word newWord = readWord(row, col, w.getTilesArr()[0]);
        if(newWord.getTilesArr().length != w.getTilesArr().length){
            return newWord;
        }
        return w;
    }

    /***
     * Check if given word is part of a bigger word(Vertically)
     * @return The new word created(top to bottom)
     */
    public Word edgesWordVertical(int row, int col, Word w){
        Word newWord = readWordVertical(row, col, w.getTilesArr()[0]);
        if(newWord.getTilesArr().length != w.getTilesArr().length){
            return newWord;
        }
        return w;
    }

    /***
     * Helper method to check if word is missing any letters.(Example FA_M)
     * @param w - Word given for testing.
     * @return New word with all letters.
     */
    public Word checkComplete(Word w){
        Tile[] tempTiles = new Tile[w.getTilesArr().length];
        if(w.isVertical()){
            for(int i = 0; i<w.getTilesArr().length; i++){
                if(w.getTilesArr()[i] == null){
                    tempTiles[i] = gameBoard[w.getRow()+i][w.getCol()].getTile();
                }
                else{
                    tempTiles[i] = w.getTilesArr()[i];
                }
            }
        }
        else{
            for(int j = 0; j<w.getTilesArr().length; j++){
                if(w.getTilesArr()[j] == null){
                    tempTiles[j] = gameBoard[w.getRow()][w.getCol()+j].getTile();
                }
                else{
                    tempTiles[j] = w.getTilesArr()[j];
                }
            }
        }
        w.setTiles(tempTiles);
        return w;
    }

    /***
     * Method to get all new words created when w is added to the board.
     * @param w - Word being placed in board.
     * @return ArrayList of all new words created in board with the word w.
     */
    public ArrayList<Word> getWords(Word w){
        ArrayList<Word> words = new ArrayList<Word>();
        ArrayList<Word> finalWords = new ArrayList<Word>();
        if(firstWord(w) && withinBoard(w) && wordPlaceIsLegal(w)){
            words.add(w);
        }
        else{
            if(withinBoard(w) && wordPlaceIsLegal(w) && oneTileAdj(w)){
                words.add(w);
                if(w.isVertical()){
                    //Word is written vertically so check left and right tiles to see possible connected words.
                    for(int i = 0; i<w.getTilesArr().length; i++){
                        if(gameBoard[w.getRow()+i][w.getCol()-1].getTile() != null ||
                                gameBoard[w.getRow()+i][w.getCol()+1].getTile() != null){
                            words.add(readWord(w.getRow()+i, w.getCol(), w.getTilesArr()[i]));
                        }
                    }
                    //Check if w(Word) is part of a bigger word when placed.(Vertically)
                    //Example: Farm sits on the word s making Farms.
                    if(gameBoard[w.getRow() + w.getTilesArr().length][w.getCol()].getTile() != null ||
                            gameBoard[w.getRow()-1][w.getCol()].getTile() != null){
                        Word tempWord = edgesWordVertical(w.getRow(), w.getCol(), w);
                        if(tempWord != w){
                            words.add(tempWord);
                        }
                    }
                }
                else{
                    //Word is written horizontally so check top and bottom tiles to see possible connected words.
                    for(int j = 0; j<w.getTilesArr().length; j++){
                        if(gameBoard[w.getRow()+1][w.getCol()+j].getTile() != null ||
                                gameBoard[w.getRow()-1][w.getCol()+j].getTile() != null){
                            words.add(readWordVertical(w.getRow(), w.getCol()+j, w.getTilesArr()[j]));
                        }
                    }
                    //Check if w(Word) is part of a bigger word when placed.(Horizontally)
                    if(gameBoard[w.getRow()][w.getCol() + w.getTilesArr().length].getTile() != null ||
                            gameBoard[w.getRow()][w.getCol()-1].getTile() != null){
                        Word tempWord = edgesWord(w.getRow(), w.getCol(), w);
                        if(tempWord != w){
                            words.add(tempWord);
                        }
                    }
                }
            }
        }

        //Check if any of the words is already part of the board before w was added.
        for (Word word : words) {
            if (!wordsArray.contains(word)) {
                finalWords.add(word);
            }
        }
        //Save all new words to main array for later comparisons.
        wordsArray.addAll(finalWords);
        return finalWords;
    }

    /***
     * Method to calculate the score of given word, Calculated board special tiles too.
     * @param w - given word for calculation.
     * @return score number(int)
     */
    public int getScore(Word w){
        int score = 0;
        int redFlag = 0;
        int yellowFlag = 0;
        if (w.isVertical()) {
            for (int i = 0; i < w.getTilesArr().length; i++) {
                switch (gameBoard[w.getRow() + i][w.getCol()].getColor()) {
                    case 1:
                        score += w.getTilesArr()[i].score * 2;
                        break;
                    case 2:
                        score += w.getTilesArr()[i].score * 3;
                        break;
                    case 3:
                        yellowFlag++;
                        score += w.getTilesArr()[i].score;
                        break;
                    case 4:
                        redFlag++;
                        score += w.getTilesArr()[i].score;
                        break;
                    case 5:
                        gameBoard[7][7].setColor(0);
                        yellowFlag++;
                        score += w.getTilesArr()[i].score;
                        break;
                    default:
                        score += w.getTilesArr()[i].score;
                        break;
                }
            }
        }
        else{
            for(int i = 0; i < w.getTilesArr().length;i++){
                switch (gameBoard[w.getRow()][w.getCol() + i].getColor()) {
                    case 1:
                        score += w.getTilesArr()[i].score * 2;
                        break;
                    case 2:
                        score += w.getTilesArr()[i].score * 3;
                        break;
                    case 3:
                        yellowFlag++;
                        score += w.getTilesArr()[i].score;
                        break;
                    case 4:
                        redFlag++;
                        score += w.getTilesArr()[i].score;
                        break;
                    case 5:
                        gameBoard[7][7].setColor(0);
                        yellowFlag++;
                        score += w.getTilesArr()[i].score;
                        break;
                    default:
                        score += w.getTilesArr()[i].score;
                        break;
                }
            }
        }
        if(redFlag > 0){
            score = score * 3 * redFlag;
        }
        else if(yellowFlag > 0){
            score = score * 2 * yellowFlag;
        }
        return score;
    }

    /***
     * Method that placed the word on the board, will be called after all other checks have passed.
     * @param w - given word to place
     */
    public void placeWord(Word w){
        if(w.isVertical()){
            for(int i = 0; i<w.getTilesArr().length;i++){
                gameBoard[w.getRow()+i][w.getCol()].setTile(w.getTilesArr()[i]);
            }
        }
        else{
            for(int j = 0; j<w.getTilesArr().length;j++){
                gameBoard[w.getRow()][w.getCol()+j].setTile(w.getTilesArr()[j]);
            }
        }
    }

    /***
     * Main method that will be called if player is trying to place new word.
     * @param w - given word to place.
     * @return score the player has earned for placing that word.
     */
    public int tryPlaceWord(Word w){
        int score = 0;
        checkComplete(w);
        if(!boardLegal(w)){
            return 0;
        }
        ArrayList<Word> tempWords = new ArrayList<Word>();
        tempWords = getWords(w);
        for (Word tempWord : tempWords) {
            if (!dictionaryLegal(tempWord)) {
                return 0;
            }
        }
        placeWord(w);
        for (Word tempWord : tempWords) {
            score += getScore(tempWord);
        }
        return score;
    }

    /***
     * Checker inner class, to make each slot in the matrix hold more information.
     * Which tile it currently holds(null if empty).
     * Color = If special tile or not and what does it represent :
     * 0 = green (normal); 1 = doubleLetter (cyan); 2 = tripleLetter (blue);
     * 3 = doubleWord (yellow); 4 = tripleWord (red); 5 = Star (StartPoint);
     */
    public static class Checker {
        public Tile tile = null;
        public int color = 0;

        public Tile getTile() {
            return tile;
        }
        public void setTile(Tile tile) {
            this.tile = tile;
        }
        public int getColor() {
            return color;
        }
        public void setColor(int color) {
            this.color = color;
        }
    }

}