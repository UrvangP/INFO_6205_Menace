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

    public static int pickRandomIndex(List<Integer> options, String encoded) {
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '0') {
                temp.add(i);
            }
        }
        List<Integer> temp1 = new ArrayList<>();
        for (int i = 0; i < temp.size(); i++) {
            temp1.add(options.get(temp.get(i)));
        }
        int[] w = new int[temp1.size()];
        for (int i = 0; i < temp1.size(); i++) {
            w[i] = temp1.get(i);
        }
        int[] prefixSums = new int[w.length];
        int prefixSum = 0;
        for (int i = 0; i < w.length; ++i) {
            prefixSum += w[i];
            prefixSums[i] = prefixSum;
        }
        int totalSum = prefixSum;
        double target = totalSum * Math.random();
        int i = 0;
        for (; i < prefixSums.length; ++i) {
            if (target < prefixSums[i]) return temp.get(i);
        }
        return temp.get(i - 1);
    }
}
