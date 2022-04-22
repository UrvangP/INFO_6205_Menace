import java.util.HashMap;

public class BaseState {
    public BaseState() {
        this.createWinWinStates();
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

    public Boolean isValidWinWinMove(String state) {
        String question = "";
        String strToCompare = "";
        for (int i = 0; i < state.length(); i++) {
            String current = String.valueOf(state.charAt(i));
            if (current.equals("0")) {
                strToCompare += "#";
                continue;
            }

            if (question.equals("")) {
                question = current;
            }

            if (question.equals(current)) {
                strToCompare += "?";
            }
        }

        System.out.println("strToCompare" + strToCompare);
        return this.allWinWinStates.containsKey(strToCompare);
    }

    public HashMap<String, Boolean> getWinWinStates() {
        return this.allWinWinStates;
    }

    private HashMap<String, Boolean> allWinWinStates = new HashMap<String, Boolean>();
}