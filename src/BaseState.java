import java.util.HashMap;
import java.util.ArrayList;

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
        ArrayList<String> temp = new ArrayList<String>();

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

    public HashMap<String, Object> verify(ArrayList<String> state) {
        int xs = 0;
        int os = 0;
        int i = 0;
        ArrayList<Integer> temp = new ArrayList<Integer>();

        while (i < state.size()) {
            String curr = state.get(i);
            if (curr.equals("1")) xs++;
            if (curr.equals("2")) os++;
            if (curr.equals("0")) temp.add(i);
            i++;
        }

        // result is an object with valid and data(available spots)
        HashMap<String, Object> result = new HashMap<String, Object>();
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


    public void compute(ArrayList<String> track) {
        if (track.size() == this.validLettersTTT) {
            HashMap<String, Object> data = this.verify(track);
            if (data.get("val").equals(true)) {
                String encoded = this.concatenateMe(track);
//                System.out.println("!!!encoded" + encoded);
                if (!isValidWinWinMove(encoded) && isValidUniqueRotation(encoded))
                    this.allCombinations.put(encoded, data.get("data"));
            }
            return;
        }

        for (int i = 0; i < this.allMoves.length; i++) {
            track.add(this.allMoves[i]);
            this.compute(track);
            track.remove(track.size() - 1);
        }
    }

    public String concatenateMe(ArrayList<String> arr) {
        String str = new String();
        for (int i = 0; i < arr.size(); i++) {
            str += arr.get(i);
        }
        return str;
    }

    public HashMap<String, Object> getAllCombinations() {
        HashMap<String, Object> temp = new HashMap<String, Object>();

        ArrayList<String> combinations = new ArrayList<String>();
        ArrayList<Object> available = new ArrayList<Object>();

        for (HashMap.Entry<String, Object> entry : this.allCombinations.entrySet()) {
            combinations.add(entry.getKey());
            available.add(entry.getValue());
        }

        temp.put("combination", combinations);
        temp.put("available", available);

        this.printCSV("combinations", temp);

        return this.allCombinations;
    }

    public HashMap<String, Boolean> getWinWinStates() {
        return this.allWinWinStates;
    }

    public void printCSV(String fileName, HashMap<String, Object> data) {
        CSV csvInstance = new CSV();
        csvInstance.generateCSV(fileName, data);
    }

    private HashMap<String, Boolean> allWinWinStates = new HashMap<String, Boolean>();
    private String[] allMoves = new String[]{"0", "1", "2"};
    private Integer validLettersTTT = 9;
    private HashMap<String, Object> allCombinations = new HashMap<String, Object>();
    private int[][] allRotations = new int[8][9];
}
