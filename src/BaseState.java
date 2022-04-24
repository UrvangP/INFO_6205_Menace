import java.util.*;

public class BaseState extends Utils {
    public BaseState() {
        this.createWinWinStates();
        this.addAllRotations();
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

    private Boolean isAlreadyPresent(int[] combination, String toCompare) {
        String str = "";
        for (int i = 0; i < combination.length; i++) {
            str += toCompare.charAt(combination[i]);
        }
        return this.allCombinations.containsKey(str);
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

    public void generateMoves() {
        for (int i = 0; i < this.allMoves.length; i++) {
            System.out.println(this.allMoves[i]);
        }
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

    public int[][] getWinWinStates() {
        return this.allWinWinStates;
    }

    public void printCSV(String fileName, Map<String, Object> data) {
        CSV csvInstance = new CSV();
        csvInstance.generateCSV(fileName, data);
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
        if (!this.allCombinations.containsKey(state)) return new ArrayList<Integer>();
        return (List<Integer>) this.allCombinations.get(state);
    }

    /**
     * For MENACE
     * Check if the value is present in the HashMap
     * if yes, return it
     * else, check for available spot -> choose a random value
     *
     * @param state - in the format of 0,1,2 Eg.010120102
     * @return
     */
    public List<Integer> getAvailableMove(String state) {
        List<Integer> available = this.allCombinations.get(state);
        if (available != null) {
            return available;
        } else {
            List<Integer> temp = new ArrayList<>();
            temp.add(getRandomAvailableColor(getAvailableSpotsOnBoard(state)));
            return temp;
        }
    }


    /**
     * Used by Human
     * To get the available spots on board - Replace this!!!
     *
     * @param board - in the format of 0,1,2 Eg.010120102
     * @return
     */
    //TODO - Check for human validations !
    public List<Integer> getAvailableSpotsOnBoard(String board) {
        List<Integer> available = new ArrayList<>();
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == '0') {
                available.add(i);
            }
        }
        return (List<Integer>) available;
    }

    public void rewardSystem( Map<String,Integer> path, int reward ){
        for( String s : path.keySet() ){
            List<Integer> beads = this.allCombinations.get(s);
            int step = path.get(s);
            beads.set(step, beads.get(step)+reward);
        }
    }

    private int[][] allWinWinStates = new int[8][3];
    private Integer[] allMoves = new Integer[]{0, 1, 2};
    private Integer validLettersTTT = 9;
    private Map<String, List<Integer>> allCombinations = new HashMap<>();
    private int[][] allRotations = new int[8][9];

    private int initialSizeAvailabilityArray = 9; //alpha
    private int addWhenWin = 3; //beta
    private int removeWhenLose = 1; //gamma
    private int addWhenDraw = 1; //delta
}
