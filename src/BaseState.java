import java.util.*;

public class BaseState {

    public BaseState() {
        this.createWinWinStates();
        this.addAllRotations();
    }

    private void createWinWinStates() {
        this.allWinWinStates.put("???######", true);
        this.allWinWinStates.put("?##?##?##", true);
        this.allWinWinStates.put("?###?###?", true);
        this.allWinWinStates.put("###???###", true);
        this.allWinWinStates.put("######???", true);
        this.allWinWinStates.put("##?##?##?", true);
        this.allWinWinStates.put("##?#?#?##", true);
        this.allWinWinStates.put("#?##?##?#", true);
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
        List<String> temp = new ArrayList<String>();

        for (int i = 0; i < state.length(); i++) {
            String current = String.valueOf(state.charAt(i));
            if (!current.equals("0")) {
                temp.add(current);
            }
        }

        for (int j = 0; j < temp.size(); j++) {
            String question = temp.get(j);
            String strToCompare = "";
            for (int i = 0; i < state.length(); i++) {
                String current = String.valueOf(state.charAt(i));
                if (current.equals("0")) {
                    strToCompare += "#";
                    continue;
                }

                if (question.equals(current)) {
                    strToCompare += "?";
                } else {
                    strToCompare += "#";
                }
            }
            if (this.allWinWinStates.containsKey(strToCompare)) return true;
        }

        return false;
    }


    public void generateMoves() {
        for (int i = 0; i < this.allMoves.length; i++) {
            System.out.println(this.allMoves[i]);
        }
    }

    public Map<String, Object> verify(ArrayList<Integer> state) {
        int xs = 0;
        int os = 0;
        int i = 0;
        List<Integer> temp = new ArrayList<Integer>();

        while (i < state.size()) {
            Integer curr = state.get(i);
            if (curr.equals(1)) xs++;
            if (curr.equals(2)) os++;
            if (curr.equals(0)) temp.add(i);
            i++;
        }

        // result is an object with valid and data(available spots)
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("data", temp);

        //if no one played
        if (xs == 0 && os == 0) {
            result.put("val", false);
            return result;
        }

        // xs = os // considering menace plays first
        if ((xs + os) % 2 == 0 && xs == os) {
            result.put("val", true);
            return result;
        }

        result.put("val", false);
        return result;
    }


    public ArrayList<Integer> getCustomSizeValue(ArrayList<Integer> data) {
        if (data.size() == (this.initialSizeAvailabilityArray)) return data;
        int i = 0;
        int j = 0;
        int until = this.initialSizeAvailabilityArray - data.size();
        while (i < until) {
            data.add(data.get(j));
            i++;
            j++;
        }
        return data;
    }

    public void compute(List<Integer> track) {
        if (track.size() == this.validLettersTTT) {
            Map<String, Object> data = this.verify((ArrayList<Integer>) track);
            if (data.get("val").equals(true)) {
                String encoded = this.concatenateMe(track);
                if (!isValidWinWinMove(encoded) && isValidUniqueRotation(encoded))
                    this.allCombinations.put(encoded, getCustomSizeValue((ArrayList<Integer>) data.get("data")));
            }
            return;
        }

        for (int i = 0; i < this.allMoves.length; i++) {
            track.add(this.allMoves[i]);
            this.compute(track);
            track.remove(track.size() - 1);
        }
    }

    public String concatenateMe(List<Integer> arr) {
        String str = new String();
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i);
        }
        return str;
    }

    public Map<String, ArrayList<Integer>> getAllCombinations() {
        Map<String, Object> temp = new HashMap<String, Object>();

        List<String> combinations = new ArrayList<String>();
        List<Object> available = new ArrayList<Object>();

        for (Map.Entry<String, ArrayList<Integer>> entry : this.allCombinations.entrySet()) {
            combinations.add(entry.getKey());
            available.add(entry.getValue());
        }

        temp.put("combination", combinations);
        temp.put("available", available);

        this.printCSV("combinations", temp);

        return this.allCombinations;
    }

    public Map<String, Boolean> getWinWinStates() {
        return this.allWinWinStates;
    }

    public void printCSV(String fileName, Map<String, Object> data) {
        CSV csvInstance = new CSV();
        csvInstance.generateCSV(fileName, data);
    }

    public ArrayList<Integer> getAllAvailablePositions(String state) {
        if (!this.allCombinations.containsKey(state)) return new ArrayList<Integer>();
        return (ArrayList<Integer>) this.allCombinations.get(state);
    }

    public Integer getRandomAvailableColor(ArrayList<Integer> options) {
        int random = new Random().nextInt(options.size());
        return options.get(random);
    }

    public String getSerialized2Dto1D(int[][] state) {
        String str = "";
        for (int i = 0; i < state.length; ++i) {
            for (int j = 0; j < state[i].length; ++j) {
                str += state[i][j];
            }
        }
        return str;
    }

    public int[][] resetBoard() {
        int[][] board = new int[3][3];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = 0;
            }
        }
        return board;
    }

    public ArrayList<Integer> getAvailableSpotsOnBoard(String board) {
        List available = new ArrayList<Integer>();
        for (int i = 0; i < board.length(); i++) {
            if (board.charAt(i) == '0') {
                available.add(i);
            }
        }
        return (ArrayList<Integer>) available;
    }

    public ArrayList<Integer> convertStringToArray(String state) {
        List<Integer> board = new ArrayList<Integer>();
        for (int i = 0; i < state.length(); i++) {
            if (state.charAt(i) == '0') board.add(Integer.valueOf(state.charAt(i)));
        }
        return (ArrayList<Integer>) board;
    }


    public ArrayList<Integer> getAvailableMove(String state) {
        ArrayList<Integer> available = this.allCombinations.get(state);
        if (available != null) {
            return available;
        } else {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(getRandomAvailableColor(getAvailableSpotsOnBoard(state)));
            return temp;
        }
    }

    private Map<String, Boolean> allWinWinStates = new HashMap<String, Boolean>();
    private Integer[] allMoves = new Integer[]{0, 1, 2};
    private Integer validLettersTTT = 9;
    private Map<String, ArrayList<Integer>> allCombinations = new HashMap<String, ArrayList<Integer>>();
    private int[][] allRotations = new int[8][9];
    private int initialSizeAvailabilityArray = 9; //alpha
    private int addWhenWin = 3; //beta
    private int removeWhenLose = 1; //gamma
    private int addWhenDraw = 1; //delta
}
