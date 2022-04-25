import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenaceBegin {

    public static void main(String[] args) {
        BaseState baseInstance = new BaseState();

        Logger logger = LoggerFactory.getLogger(MenaceBegin.class);

        System.out.println("total combinations: " + baseInstance.getAllCombinations().size());
        int menace = 0;
        int humanC = 0;
        for (int i = 0; i < 10000; ++i) {
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
                    // MENACE turn

                    //If there is no serial/state in HashMap - find the appropriate state and replace it!
                    if (!baseInstance.getAllCombinations().containsKey(serial) && chance < 7) {
                        // validCombination - is the appropriate state
                        String validCombination = baseInstance.getValidCombination(serial);
                        serial = validCombination;
                        // replace the board based on appropriate state
                        baseInstance.getNewConfigBoard(board, serial);
                    }
                    // positionsToPlay - return an array with all valid positions allowed to play
                    List<Integer> positionsToPlay = baseInstance.getAllAvailablePositions(serial);
                    // randomly pick from the available spots
                    int rand = baseInstance.pickRandomIndex(positionsToPlay, serial);
                    System.out.println("rand" + serial + "|" + rand);
                    // moves - to keep a track on the game played
                    moves.put(serial, rand);
                    // mark the menace move on the board
                    board[(int) Math.floor(rand / 3)][rand % 3] = 1;
                } else {
                    human.play();
                }
                toCheckWin = baseInstance.getSerialized2Dto1D((board));
                if (baseInstance.isValidWinWinMove(toCheckWin)) {
                    if (turn) {
                        //subtract beads
                        baseInstance.rewardSystem(moves, -1 * baseInstance.gammaRewardWhenLose());
                    } else {
                        //add beads
                        baseInstance.rewardSystem(moves, baseInstance.betaRewardWhenWin());
                    }
                    logger.info((turn ? "Human:" : "Menace:") + "Win!!" + toCheckWin);

                    System.out.println((turn ? "Human:" : "Menace:") + "Win!!" + toCheckWin);
                    if (turn) humanC++;
                    else menace++;
                    break;
                }
                turn = !turn;
                chance++;
            }

            if (chance == 9) {
                // Draw
                baseInstance.rewardSystem(moves, baseInstance.deltaRewardWhenDraw());
                System.out.println("Draw!!" + toCheckWin);
                logger.info("Draw: " + String.valueOf(board));

            }
        }


        System.out.println(menace + "v/s" + humanC);
        baseInstance.getAllCombinations();
    }
}

