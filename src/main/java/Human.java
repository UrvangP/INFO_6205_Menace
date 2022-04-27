import java.util.*;

public class Human {

    int[][] board;
    private final MenaceLogger logger = new MenaceLogger();
    int prob = 9;

    public Human(int[][] data) {
        board = data;
    }
    public Human(){}

    public int getProbability(){
        return this.prob;
    }

    public void play() {

        int p = 9;
        List<Integer> weights = new ArrayList<>();

        weights.add(p);
        weights.add(10-p);

        String dummy = "00";

        int index = Utils.pickRandomIndex(weights, dummy);

//        System.out.println("P Index:" + Utils.pickRandomIndex(weights, dummy));

        if(index == 0) humanStrat();
        else playRandom();
    }

    public void playRandom(){

        List<int[]> availableSpots = new ArrayList<>();

        for( int i=0; i<board.length; ++i){
            for( int j=0; j<board[0].length; ++j){
                if(board[i][j] == 0){
                    availableSpots.add( new int[]{i, j});
                }
            }
        }

        Random rand = new Random();
        int ind = rand.nextInt(availableSpots.size());

        int[] playSpot = availableSpots.get(ind);

        board[playSpot[0]][playSpot[1]] = 2;
    }

    public boolean humanStrat() {

        if (horizontalWinCase()) return true;
        if (verticalWinCase()) return true;
        if (diagonalWinCase()) return true;
        if (blockWins()) return true;
        if (fork()) return true;
        if (forkBlock()) return true;
        if (isCenterEmpty()) return true;
        if (oppositeCorner()) return true;
        if (isCornerEmpty()) return true;
        if (isSideEmpty()) return true;

        return false;
    }

    public boolean horizontalWinCase() {

        int[] pos = horizontalWin(2);

        if (pos[0] != -1 && pos[1] != -1) {
            //logger.logMe("Human played " + pos[0] + ", " + pos[1]);
            board[pos[0]][pos[1]] = 2;
            return true;
        }

        return false;
    }

    public boolean verticalWinCase() {

        int[] pos = verticalWin(2);

        if (pos[0] != -1 && pos[1] != -1) {
            //logger.logMe("Human played " + pos[0] + ", " + pos[1]);
            board[pos[0]][pos[1]] = 2;
            return true;
        }

        return false;
    }

    public boolean diagonalWinCase() {

        int[] pos = diagonalWin(2);

        if (pos[0] != -1 && pos[1] != -1) {
            //logger.logMe("Human played " + pos[0] + ", " + pos[1]);
            board[pos[0]][pos[1]] = 2;
            return true;
        }

        return false;
    }

    public boolean blockWins() {

        int[] pos = new int[2];
        pos = horizontalWin(1);

        if (pos[0] != -1 && pos[1] != -1) {
            //logger.logMe("Human played " + pos[0] + ", " + pos[1]);

            board[pos[0]][pos[1]] = 2;
            return true;
        }

        pos = verticalWin(1);

        if (pos[0] != -1 && pos[1] != -1) {
            //logger.logMe("Human played " + pos[0] + ", " + pos[1]);

            board[pos[0]][pos[1]] = 2;
            return true;
        }

        pos = diagonalWin(1);

        if (pos[0] != -1 && pos[1] != -1) {
            //logger.logMe("Human played " + pos[0] + ", " + pos[1]);

            board[pos[0]][pos[1]] = 2;
            return true;
        }

        return false;
    }

    public boolean fork() {

        if (board[0][0] == 2 && board[2][2] == 2 && board[1][1] == 1) {
            if (board[0][2] == 1 && board[2][0] == 0) {
                //logger.logMe("Human played " + 2 + ", " + 0);

                board[2][0] = 2;
                return true;
            }
            if (board[0][2] == 0 && board[2][0] == 1) {
                //logger.logMe("Human played " + 0 + ", " + 2);

                board[0][2] = 2;
                return true;
            }
        } else if (board[0][2] == 2 && board[2][0] == 2 && board[1][1] == 1) {
            if (board[0][0] == 1 && board[2][2] == 0) {
                //logger.logMe("Human played " + 2 + ", " + 2);

                board[2][2] = 2;
                return true;
            }
            if (board[0][0] == 0 && board[2][2] == 1) {
                //logger.logMe("Human played " + 0 + ", " + 0);

                board[0][0] = 2;
                return true;
            }
        }

        return false;
    }

    public boolean forkBlock() {

        if (board[0][0] == 1 && board[2][2] == 1 && board[1][1] == 2) {
            if (isCornerEmpty()) return true;
        }

        if (board[0][2] == 1 && board[2][0] == 1 && board[1][1] == 2) {
            if (isCornerEmpty()) return true;
        }

        return false;
    }

    public boolean isCenterEmpty() {

        if (board[1][1] == 0) {
            //logger.logMe("Human played " + 1 + ", " + 1);

            board[1][1] = 2;
            return true;
        }

        return false;
    }

    public boolean isCornerEmpty() {

        List<int[]> empty = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (Math.abs(i - j) == 2 && board[i][j] == 0) {
                    empty.add(new int[]{i, j});
                }
                if (Math.abs(i - j) == 0 && i != 1 && board[i][j] == 0) {
                    empty.add(new int[]{i, j});
                }
            }
        }

        if (empty.isEmpty()) {
            return false;
        } else {
            Random rand = new Random();
            int val = rand.nextInt(empty.size());
            int[] index = empty.get(val);
            //logger.logMe("Human played " + index[0] + ", " + index[1]);

            board[index[0]][index[1]] = 2;
            return true;
        }
    }

    public boolean isSideEmpty() {

        List<int[]> empty = new ArrayList<>();

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (Math.abs(i - j) == 1 && board[i][j] == 0) {
                    empty.add(new int[]{i, j});
                }
            }
        }

        if (empty.isEmpty()) {
            return false;
        } else {
            Random rand = new Random();
            int val = rand.nextInt(empty.size());
            int[] index = empty.get(val);
            //logger.logMe("Human played " + index[0] + ", " + index[1]);

            board[index[0]][index[1]] = 2;
            return true;
        }
    }

    public int[] horizontalWin(int player) {
        for (int i = 0; i < board.length; ++i) {
            int[] counts = new int[2];
            int zeroRow = -1;
            int zeroCol = -1;
            for (int j = 0; j < board[0].length; ++j) {
                if (board[i][j] == 0) {
                    counts[0]++;
                    zeroRow = i;
                    zeroCol = j;
                }
                if (board[i][j] == player) counts[1]++;
            }
            if (counts[0] == 1 && counts[1] == 2) {
                return new int[]{zeroRow, zeroCol};
            }
        }
        return new int[]{-1, -1};
    }

    public int[] verticalWin(int player) {
        for (int i = 0; i < board.length; ++i) {
            int[] counts = new int[2];
            int zeroRow = -1;
            int zeroCol = -1;
            for (int j = 0; j < board[0].length; ++j) {
                if (board[j][i] == 0) {
                    counts[0]++;
                    zeroRow = j;
                    zeroCol = i;
                }
                if (board[j][i] == player) counts[1]++;
            }
            if (counts[0] == 1 && counts[1] == 2) {
                return new int[]{zeroRow, zeroCol};
            }
        }
        return new int[]{-1, -1};
    }

    public int[] diagonalWin(int player) {
        int[] counts = new int[2];

        int zero = -1;
        for (int i = 0; i < board.length; ++i) {
            if (board[i][i] == 0) {
                counts[0]++;
                zero = i;
            }
            if (board[i][i] == player) counts[1]++;

            if (counts[0] == 1 && counts[1] == 2) {
                return new int[]{zero, zero};
            }

        }

        int zeroRow = -1;
        int zeroCol = -1;
        counts = new int[3];

        for (int i = 0, j = board[0].length - 1; i < board.length && j >= 0; ++i, --j) {

            if (board[i][j] == 0) {
                counts[0]++;
                zeroRow = i;
                zeroCol = j;
            }
            if (board[i][j] == player) counts[1]++;

            if (counts[0] == 1 && counts[1] == 2) {
                //board[zeroRow][zeroCol] = 2;
                return new int[]{zeroRow, zeroCol};
            }
        }

        return new int[]{-1, -1};
    }

    public boolean oppositeCorner(){
        int[] counts = new int[2];
        int pos = -1;
        for( int i=0; i<3; ++i){
            if(board[i][i] == 0 && i!=1){
                counts[0]++;
                pos = i;
            }
            if(board[i][i]==1 && i!=1){
                counts[1]++;
            }
        }

        if(pos!=-1 && counts[0]==1 && counts[1]==1){
            board[pos][pos] = 2;
            return true;
        }

        int posR = -1;
        int posC = -1;
        counts = new int[2];
        for( int i=0, j=2; i<3 && j>=0; ++i,--j){
            if(Math.abs(i-j)==2 && board[i][j] == 0 && i!=1){
                counts[0]++;
                posR = i;
                posC = j;
            }
            if(Math.abs(i-j)==2 && board[i][j]==1 && i!=1){
                counts[1]++;
            }
        }

        if(posR!=-1 && posC!=-1 && counts[0]==1 && counts[1]==1){
            board[posR][posC] = 2;
            return true;
        }

        return false;
    }
}
