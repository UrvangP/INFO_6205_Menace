import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {

    /**
     * Generic method
     * This method concatenates the array elements
     *
     * @param arr - array of object
     * @return
     */
    public String concatenateMe(List<Integer> arr) {
        String str = new String();
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i);
        }
        return str;
    }

    /**
     * Generic Method
     * Choose a random value from available spots(Array)
     *
     * @param options
     * @return
     */
    public Integer getRandomAvailableColor(List<Integer> options) {
        int random = new Random().nextInt(options.size());
        return options.get(random);
    }

    /**
     * Generic Method
     * Convert 2d int[][] into String, as we are dealing with string in HashMap
     *
     * @param state
     * @return
     */
    public String getSerialized2Dto1D(int[][] state) {
        String str = "";
        for (int i = 0; i < state.length; ++i) {
            for (int j = 0; j < state[i].length; ++j) {
                str += state[i][j];
            }
        }
        return str;
    }

    /**
     * Generic Method
     * To reset the board
     *
     * @return
     */
    public int[][] resetBoard() {
        int[][] board = new int[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        return board;
    }

    /**
     * Generic Util
     * To convert String to Array - Because JAVA sucks!
     *
     * @param state in the format of 0,1,2 Eg.010120102
     * @return
     */
    public ArrayList<Integer> convertStringToArray(String state) {
        List<Integer> board = new ArrayList<Integer>();
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '0') board.add(Integer.valueOf(state.charAt(i)));
        }
        return (ArrayList<Integer>) board;
    }

    public Integer getMenaceNextMove(){

        return 0;
    }

}
