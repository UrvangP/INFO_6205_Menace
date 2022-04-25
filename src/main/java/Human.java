import java.util.*;

public class Human {

    int[][] board;

    public Human( int[][] data ){
        board = data;
    }

    public void play(){
        humanStrat();
    }

    public boolean humanStrat(){

        if( horizontalWinCase() ) return true;
        if( verticalWinCase() ) return true;
        if( diagonalWinCase() ) return true;
        if( blockWins() ) return true;
        if( fork() ) return true;
        if( forkBlock() ) return true;
        if( isCenterEmpty() ) return true;
        if( isCornerEmpty() ) return  true;
        if( isSideEmpty() ) return true;

        return false;
    }

    public boolean horizontalWinCase(){

        for( int i=0; i<board.length; ++i){
            int[] counts = new int[3];
            int zero = -1;
            for( int j=0; j<board[0].length; ++j){
                if(board[i][j]==0) {
                    counts[0]++;
                    zero = j;
                }
                if(board[i][j]==1) counts[1]++;
                if(board[i][j]==2) counts[2]++;
            }
            if(counts[0]==1 && counts[2]==2){
                board[i][zero] = 2;
                return true;
            }
        }
        return false;
    }

    public boolean verticalWinCase() {

        for (int i = 0; i < board.length; ++i) {
            int[] counts = new int[3];
            int zero = -1;
            for (int j = 0; j < board[0].length; ++j) {
                if (board[j][i] == 0) {
                    counts[0]++;
                    zero = j;
                }
                if (board[j][i] == 1) counts[1]++;
                if (board[j][i] == 2) counts[2]++;
            }
            if (counts[0] == 1 && counts[2] == 2) {
                board[zero][i] = 2;
                return true;
            }
        }
        return false;
    }

    public boolean diagonalWinCase(){
        int[] counts = new int[3];

        int zero = -1;
        for (int i=0; i < board.length; ++i) {
            if(board[i][i] == 0) {
                counts[0]++;
                zero=i;
            }
            if (board[i][i] == 1) counts[1]++;
            if (board[i][i] == 2) counts[2]++;

            if (counts[0] == 1 && counts[2] == 2) {
                board[zero][zero] = 2;
                return true;
            }

        }

        int zeroRow = -1;
        int zeroCol = -1;
        counts = new int[3];

        for (int i=0, j=board[0].length-1; i < board.length && j>=0; ++i,--j) {

            if (board[i][j] == 0) {
                counts[0]++;
                zeroRow = i;
                zeroCol = j;
            }
            if (board[i][j] == 1) counts[1]++;
            if (board[i][j] == 2) counts[2]++;

            if (counts[0] == 1 && counts[2] == 2) {
                board[zeroRow][zeroCol] = 2;
                return true;
            }
        }

        return false;
    }

    public boolean blockWins(){

        if(board[0][0]==1 && board[0][1]==1 && board[0][2]==0){
            board[0][2]=2;
            return true;
        }

        if(board[0][0]==0 && board[0][1]==1 && board[0][2]==1){
            board[0][0]=2;
            return true;
        }

        if(board[1][0]==1 && board[1][1]==1 && board[1][2]==0){
            board[1][2]=2;
            return true;
        }

        if(board[1][0]==0 && board[1][1]==1 && board[1][2]==1){
            board[1][0]=2;
            return true;
        }

        if(board[2][0]==1 && board[2][1]==1 && board[2][2]==0){
            board[2][2]=2;
            return true;
        }

        if(board[2][0]==0 && board[2][1]==1 && board[2][2]==1){
            board[2][0]=2;
            return true;
        }

        if(board[0][0]==1 && board[1][0]==1 && board[2][0]==0){
            board[2][0]=2;
            return true;
        }

        if(board[0][0]==0 && board[1][0]==1 && board[2][0]==1){
            board[0][0]=2;
            return true;
        }

        if(board[0][1]==1 && board[1][1]==1 && board[2][1]==0){
            board[2][1]=2;
            return true;
        }

        if(board[0][1]==0 && board[1][1]==1 && board[2][1]==1){
            board[0][1]=2;
            return true;
        }

        if(board[0][2]==1 && board[1][2]==1 && board[2][2]==0){
            board[2][2]=2;
            return true;
        }

        if(board[0][2]==0 && board[1][2]==1 && board[2][2]==1){
            board[0][2]=2;
            return true;
        }

        if(board[0][0]==1 && board[1][1]==1 && board[2][2]==0){
            board[2][2]=2;
            return true;
        }

        if(board[0][0]==0 && board[1][1]==1 && board[2][2]==1){
            board[0][0]=2;
            return true;
        }

        if(board[0][2]==1 && board[1][1]==1 && board[2][0]==0){
            board[2][0]=2;
            return true;
        }

        if(board[0][2]==0 && board[1][1]==1 && board[2][0]==1){
            board[0][2]=2;
            return true;
        }

        return false;
    }

    public boolean fork(){

        if( board[0][0]==2 && board[2][2]==2 && board[1][1]==1){
            if( board[0][2]==1 && board[2][0]==0){
                board[2][0]=2;
                return true;
            }
            if( board[0][2]==0 && board[2][0]==1){
                board[0][2]=2;
                return true;
            }
        }

        else if( board[0][2]==2 && board[2][0]==2 && board[1][1]==1){
            if( board[0][0]==1 && board[2][2]==0){
                board[2][2]=2;
                return true;
            }
            if( board[0][0]==0 && board[2][2]==1){
                board[0][0]=2;
                return true;
            }
        }

        return false;
    }

    public boolean forkBlock(){

        if( board[0][0]==1 && board[2][2]==1 && board[1][1]==2){
            if(isCornerEmpty()) return true;
        }

        if( board[0][2]==1 && board[2][0]==1 && board[1][1]==2){
            if(isCornerEmpty()) return true;
        }

        return false;
    }

    public boolean isCenterEmpty(){

        if( board[1][1]==0){
            board[1][1]=2;
            return true;
        }

        return false;
    }

    public boolean isCornerEmpty(){

        if(board[0][0]==0){
            board[0][0] = 2;
            return true;
        }

        if(board[0][2]==0){
            board[0][2] = 2;
            return true;
        }

        if(board[2][0]==0){
            board[2][0] = 2;
            return true;
        }

        if(board[2][2]==0){
            board[2][2] = 2;
            return true;
        }

        return false;
    }

    public boolean isSideEmpty(){

        if( board[0][1]==0 ){
            board[0][1]=2;
            return true;
        }

        if( board[1][0]==0 ){
            board[1][0]=2;
            return true;
        }

        if( board[1][2]==0 ){
            board[1][2]=2;
            return true;
        }

        if( board[2][1]==0 ){
            board[2][1]=2;
            return true;
        }

        return false;
    }
}
