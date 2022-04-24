import java.util.*;

public class MenaceBegin {

    public static void main(String[] args) {
        BaseState baseInstance = new BaseState();
        Boolean isValid = baseInstance.isValidWinWinMove("000002100");
        System.out.println("120102100 isValid:- " + isValid);
//        baseInstance.generateMoves();
        baseInstance.compute(new ArrayList<Integer>());
        List<Integer> availablePositions = baseInstance.getAllAvailablePositions("000002100");
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
            Map<String, Integer> moves = new HashMap<>();
            Human human = new Human(board);
            int chance = 0;

            String toCheckWin = "";
            while (chance < 9) {
                String serial = baseInstance.getSerialized2Dto1D(board);

                if (!turn) {
                    List<Integer> positionsToPlay = baseInstance.getAvailableMove(serial);
                    List<Integer> playableSpots = new ArrayList<>();
                    for (int pos = 0; pos < positionsToPlay.size(); ++pos) {
                        int val = positionsToPlay.get(pos);
                        if (val > 0) {
                            playableSpots.add(pos);
                        }
                    }
                    int rand = baseInstance.getRandomAvailableColor(playableSpots);
                    moves.put(serial, rand);
                    board[(int) Math.floor(rand / 3)][rand % 3] = !turn ? 1 : 2;
                } else {
                    human.play();
                }
                toCheckWin = baseInstance.getSerialized2Dto1D((board));
                if (baseInstance.isValidWinWinMove(toCheckWin)) {
                    if (turn) {
                        //subtract beads
                        baseInstance.rewardSystem(moves, -1);
                    } else {
                        //add beads
                        baseInstance.rewardSystem(moves, 3);
                    }

                    System.out.println(turn ? "Human:" : "Menace:" + "Win!!" + toCheckWin);
                    break;
                }
                turn = !turn;
                chance++;
            }

            if (chance == 9) {
                //Draw
                baseInstance.rewardSystem(moves, 1);
                System.out.println("Draw!!" + toCheckWin);
            }
        }
    }
}

