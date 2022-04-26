import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BaseState extends Utils {
    public BaseState() {
        this.createWinWinStates();
        this.addAllRotations();
        this.readAndSaveCSV();
    }

    private void createWinWinStates() {
        this.allWinWinStates[0] = new int[]{0, 1, 2};
        this.allWinWinStates[1] = new int[]{3, 4, 5};
        this.allWinWinStates[2] = new int[]{6, 7, 8};
        this.allWinWinStates[3] = new int[]{0, 3, 6};
        this.allWinWinStates[4] = new int[]{1, 4, 7};
        this.allWinWinStates[5] = new int[]{2, 5, 8};
        this.allWinWinStates[6] = new int[]{0, 4, 8};
        this.allWinWinStates[7] = new int[]{6, 4, 2};
    }

    private void addAllRotations() {
        this.allRotations[0] = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        this.allRotations[1] = new int[]{0, 3, 6, 1, 4, 7, 2, 5, 8};
        this.allRotations[2] = new int[]{6, 3, 0, 7, 4, 1, 8, 5, 2};
        this.allRotations[3] = new int[]{6, 7, 8, 3, 4, 5, 0, 1, 2};
        this.allRotations[4] = new int[]{8, 7, 6, 5, 4, 3, 2, 1, 0};
        this.allRotations[5] = new int[]{8, 5, 2, 7, 4, 1, 6, 3, 0};
        this.allRotations[6] = new int[]{2, 5, 8, 1, 4, 7, 0, 3, 6};
        this.allRotations[7] = new int[]{2, 1, 0, 5, 4, 3, 8, 7, 6};
    }

    private Boolean isAlreadyPresent(int[] combination, String toCompare) {
        String str = "";
        for (int i = 0; i < combination.length; i++) {
            str += toCompare.charAt(combination[i]);
        }
        return this.allCombinations.containsKey(str);
    }

    public Boolean isValidUniqueRotation(String encoded) {
        for (int i = 0; i < this.allRotations.length; i++) {
            Boolean isValid = isAlreadyPresent(this.allRotations[i], encoded);
            if (isValid) return false;
        }
        return true;
    }

    public Boolean isValidWinWinMove(String state) {

        for (int i = 0; i < this.allWinWinStates.length; i++) {
            Integer first = this.allWinWinStates[i][0];
            Integer second = this.allWinWinStates[i][1];
            Integer third = this.allWinWinStates[i][2];
            if (state.charAt(first) != '0' && (state.charAt(first) == state.charAt(second)) && (state.charAt(second) == state.charAt(third))) {
                return true;
            }
        }
        return false;
    }

    public Map<String, Object> verify(List<Integer> state) {
        int xs = 0;
        int os = 0;
        int i = 0;
        List<Integer> temp = new ArrayList<>();

        while (i < state.size()) {
            Integer curr = state.get(i);
            if (curr.equals(1)) xs++;
            if (curr.equals(2)) os++;
            if (curr.equals(0)) temp.add(i);
            i++;
        }

        // result is an object with valid and data(available spots)
        Map<String, Object> result = new HashMap<>();
        result.put("data", temp);

        /**
         * Two conditions:
         * Considering Menace plays first then - {xs == os}, as always it's going to be even no.
         *
         * The winning condition can only be defined within first 6 steps.
         * 7 step can be winning or else DRAW!!
         */
        if ((xs + os) < 7 && xs == os) {
            result.put("val", true);
            return result;
        }

        result.put("val", false);
        return result;
    }


    public List<Integer> getCustomSizeValue(List<Integer> data) {
        //if (data.size() == (this.initialSizeAvailabilityArray)) return data;

        List<Integer> value = new ArrayList<Integer>();

        for (int i = 0; i < this.validLettersTTT; i++) {
            value.add(0);
        }

        int i = 0;
        while (i < data.size()) {
            value.set(data.get(i), this.initialSizeAvailabilityArray);
            i++;
        }

        return (ArrayList<Integer>) value;
    }

    /**
     * MENACE
     * Generate all the combinations based on 3 states 0,1,2
     * and then validate
     * State -
     * 0 - Empty
     * 1 - Menace
     * 2 - Human
     *
     * @param track - a state of recursion
     */
    public void compute(List<Integer> track) {
        if (track.size() == this.validLettersTTT) {
            Map<String, Object> data = this.verify(track);
            if (data.get("val").equals(true)) {
                String encoded = this.concatenateMe(track);
                if (!this.isValidWinWinMove(encoded) && this.isValidUniqueRotation(encoded))
                    this.allCombinations.put(encoded, getCustomSizeValue((List<Integer>) data.get("data")));
            }
            return;
        }

        for (int i = 0; i < this.allMoves.length; i++) {
            track.add(this.allMoves[i]);
            this.compute(track);
            track.remove(track.size() - 1);
        }
    }


    public Map<String, List<Integer>> getAllCombinations() {
        Map<String, Object> temp = new HashMap<>();
        List<String> combinations = new ArrayList<>();
        List<Object> available = new ArrayList<>();

        for (Map.Entry<String, List<Integer>> entry : this.allCombinations.entrySet()) {
            combinations.add(entry.getKey());
            available.add(entry.getValue());
        }

        temp.put("combination", combinations);
        temp.put("available", available);

        this.printCSV("combinations", temp);
        return this.allCombinations;
    }

    public void printCSV(String fileName, Map<String, Object> data) {
        CSV csvInstance = new CSV();
        csvInstance.generateCSV(fileName, data);
    }


    public List<Integer> checkMirrorImagesValidPositions(String encoded) {
        for (int i = 0; i < this.allRotations.length; i++) {
            String toCompare = "";
            for (int j = 0; j < this.allRotations[i].length; j++) {
                toCompare += encoded.charAt(this.allRotations[i][j]);
            }
            if (this.allCombinations.containsKey(toCompare)) {
                return (List<Integer>) this.allCombinations.get(toCompare);
            }
        }
        return getRandomPositionForDraw(encoded);
    }


    /**
     * MENACE
     * Get available from HashMap State
     *
     * @param state
     * @return
     */
    //TODO - if not present then what ?
    public List<Integer> getAllAvailablePositions(String state) {
        if (!this.allCombinations.containsKey(state)) return checkMirrorImagesValidPositions(state);
        return (List<Integer>) this.allCombinations.get(state);
    }


    /**
     * MENACE
     * getValidCombination()
     * Get a valid combination if the played state is not found
     * Check for mirror availability
     *
     * @param encoded
     * @return
     */
    public String getValidCombination(String encoded) {
        for (int i = 0; i < this.allRotations.length; i++) {
            String toCompare = "";
            for (int j = 0; j < this.allRotations[i].length; j++) {
                toCompare += encoded.charAt(this.allRotations[i][j]);
            }

            if (this.allCombinations.containsKey(toCompare)) {
                return toCompare;
            }
        }
        return null;
    }

    /**
     * MENACE
     * getNewConfigBoard()
     * Get new config of the board - if the state is not found in the store/HashMap
     *
     * @param board   - old board
     * @param encoded - new state to replace old board
     */
    public void getNewConfigBoard(int[][] board, String encoded) {
        for (int i = 0; i < encoded.length(); i++) {
            board[(int) Math.floor(i / 3)][i % 3] = Character.getNumericValue(encoded.charAt(i));
        }
    }

    /**
     * MENACE
     * rewardSystem()
     * To train - maintain reward system
     * if win - add beads
     * if lose - subtract
     *
     * @param path
     * @param reward
     */
    public void rewardSystem(Map<String, Integer> path, int reward) {
        for (String s : path.keySet()) {
            List<Integer> beads = this.getAllAvailablePositions(s);
            int step = path.get(s);
            beads.set(step, beads.get(step) + reward);
        }
    }


    public List<Integer> getRandomPositionForDraw(String encoded) {
        List<Integer> availablePositions = new ArrayList<>();
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '0') availablePositions.add(this.initialSizeAvailabilityArray);
            else availablePositions.add(0);
        }
        return availablePositions;
    }


    public int pickRandomIndex(List<Integer> options, String encoded) {
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

    public void readAndSaveCSV() {
        CSV csvInstance = new CSV();
        this.allCombinations = csvInstance.readCSV();
        if (this.allCombinations.size() == 0) {
            this.compute(new ArrayList<Integer>());
        }
    }

    public int betaRewardWhenWin() {
        return this.addWhenWin;
    }

    public int gammaRewardWhenLose() {
        return this.removeWhenLose;
    }

    public int deltaRewardWhenDraw() {
        return this.addWhenDraw;
    }

    private int[][] allWinWinStates = new int[8][3];
    private Integer[] allMoves = new Integer[]{0, 1, 2};
    private Integer validLettersTTT = 9;
    private Map<String, List<Integer>> allCombinations = new HashMap<>();
    private int[][] allRotations = new int[8][9];


    private int initialSizeAvailabilityArray = 100; //alpha
    private int addWhenWin = 8; //beta
    private int removeWhenLose = 1; //gamma
    private int addWhenDraw = 5; //delta
}
