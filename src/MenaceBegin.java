import java.util.*;

public class MenaceBegin {

    public static void main(String[] args) {
        BaseState baseInstance = new BaseState();
        Boolean isValid = baseInstance.isValidWinWinMove("120102100");
        System.out.println("120102100 isValid:- " + isValid);
//        baseInstance.generateMoves();
        baseInstance.compute(new ArrayList<Integer>());
        ArrayList<Integer> availablePositions = baseInstance.getAllAvailablePositions("001002210");
        for (int i = 0; i < availablePositions.size(); i++) {
            System.out.println(availablePositions.get(i));
        }
        int randomPosition = baseInstance.getRandomAvailableColor(availablePositions);
        System.out.println("Random No. " + randomPosition);

        System.out.println("total combinations: " + baseInstance.getAllCombinations().size());


        for (int i = 0; i < 10; i++) {
            Boolean turn = false; //MENACE
            /**
             * MENACE - 1
             * HUMAN - 2
             * EMPTY - 0
             */
            int[][] board = baseInstance.resetBoard();
            int chance = 0;

            String toCheckWin = "";
            while (chance < 9) {
                String serial = baseInstance.getSerialized2Dto1D(board);
                ArrayList<Integer> positionsToPlay = turn ? baseInstance.getAvailableSpotsOnBoard(serial) : baseInstance.getAvailableMove(serial);
                int rand = baseInstance.getRandomAvailableColor(positionsToPlay);
                board[(int) Math.floor(rand / 3)][rand % 3] = !turn ? 1 : 2;

                toCheckWin = baseInstance.getSerialized2Dto1D((board));
                if (baseInstance.isValidWinWinMove(toCheckWin)) {
                    System.out.println(turn ? "Human:" : "Menace:" + "Win!!" + toCheckWin);
                    break;
                }
                turn = !turn;
                chance++;
            }

            if (chance == 9) {
                //Draw
                System.out.println("Draw!!" + toCheckWin);
            }
        }
    }
}

