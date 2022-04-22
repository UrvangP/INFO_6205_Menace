import java.util.*;

public class MenaceBegin extends BaseState {

    public static void main(String[] args) {
        BaseState baseInstance = new BaseState();

        Boolean isValid = baseInstance.isValidWinWinMove("111007000");
        
        System.out.println("isValid:- " + isValid);
        for (Map.Entry<String, Boolean> entry : baseInstance.getWinWinStates().entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }
}
