import java.util.*;

public class MenaceBegin {

    public static void main(String[] args) {
        BaseState baseInstance = new BaseState();

        Boolean isValid = baseInstance.isValidWinWinMove("120102100");

        System.out.println("isValid:- " + isValid);
        baseInstance.generateMoves();
        baseInstance.compute(new ArrayList<String>());
//        for (Map.Entry<String, Boolean> entry : baseInstance.getWinWinStates().entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }

//        for (Map.Entry<String, Object> entry : baseInstance.getAllCombinations().entrySet()) {
//            System.out.println(entry.getKey() + " = " + entry.getValue());
//        }

        System.out.println("total combinations: " + baseInstance.getAllCombinations().size());
    }
}
