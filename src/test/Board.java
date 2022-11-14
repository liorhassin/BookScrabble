package test;



public class Board {
    private static final int N = 15;
    private static Board checkBoard = null;
    public Checker[][] gameBoard = new Checker[N][N];

    public Board(){
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

    public static Board getBoard(){
        if (checkBoard == null){
            checkBoard = new Board();
        }
        return checkBoard;
    }

    public Tile[][] getTiles(){
        Tile[][] tileMath = new Tile[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                tileMath[i][j] = gameBoard[i][j].getTile();
            }
        }
        return tileMath;
    }
// vertical -true = up->down , false- left->right
    public boolean boardLegal(Word w){

        int flag = 0;
        if(gameBoard[7][7].getTile()==null){
            /*
            Need to fix this method.
             */
            return w.getCol() == 7 && w.getRow() == 7;
        }else{
            int wordLength = w.getTilesArr().length;
            if(w.isVertical()){
                // up to down
                for(int i=0;i<wordLength;i++){
                    if(gameBoard[w.getRow()+i][w.getCol()].getTile() != null ){
                       if(gameBoard[w.getRow()+i][w.getCol()].getTile().letter != w.getTilesArr()[i].letter){
                           return false;
                       }
                    }
                    if((w.getRow()+1 <15 && gameBoard[w.getRow()+1+i][w.getCol()].getTile() != null) ||
                            (w.getRow()-1 >= 0 && gameBoard[w.getRow()-1+i][w.getCol()].getTile() != null) ||
                            (w.getCol()+1 <15 && gameBoard[w.getRow()+i][w.getCol()+1].getTile() != null) ||
                            (w.getCol()-1 >= 0 && gameBoard[w.getRow()+i][w.getCol()-1].getTile() != null)){
                        flag++;
                    }
                }
            }
            else{
                // down to left
                for(int i=0;i<wordLength;i++){
                    if(gameBoard[w.getRow()][w.getCol()+i].getTile() != null ){
                        if(gameBoard[w.getRow()][w.getCol()+i].getTile().letter != w.getTilesArr()[i].letter){
                            return false;
                        }
                    }
                    if((w.getRow()+1 <15 && gameBoard[w.getRow()+1][w.getCol()+i].getTile() != null) ||
                            (w.getRow()-1 >= 0 && gameBoard[w.getRow()-1][w.getCol()+i].getTile() != null) ||
                            (w.getCol()+1 <15 && gameBoard[w.getRow()][w.getCol()+i+1].getTile() != null) ||
                            (w.getCol()-1 >= 0 && gameBoard[w.getRow()][w.getCol()+i-1].getTile() != null)){
                        flag++;
                    }
                }
            }
            return flag != 0;
        }
    }


    // just for now!
    public boolean dictionaryLegal(){
        return true;
    }



    public static class Checker {
        public Tile tile = null;
        public int color = 0;
        /*
        0 = green (normal)
        1 = doubleLetter (thelet)
        2= tripleLetter (blue);
        3= doubleWord (yellow);
        4= tripleWord (red);
        5= Star (StartPoint);
         */
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