import java.util.*;

public class MenaceBegin {

    public static void main(String[] args) {
        // Initializes the Base Class
        BaseState baseInstance = new BaseState();
        // Initializes the MenaceLogger
        MenaceLogger logger = new MenaceLogger();
        // Total no. of states in HashMap and writes those into a file
        System.out.println("total combinations: " + baseInstance.getAllCombinations().size());

        for (int i = 0; i < totalGames; ++i) {
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
                        logger.logMe("Board mirror found " + serial + "->" + validCombination);
                        serial = validCombination;
                        // replace the board based on appropriate state
                        baseInstance.getNewConfigBoard(board, serial);
                    }
                    // positionsToPlay - return an array with all valid positions allowed to play
                    List<Integer> positionsToPlay = baseInstance.getAllAvailablePositions(serial);
                    // randomly pick from the available spots
                    int rand = baseInstance.pickRandomIndex(positionsToPlay, serial);
                    // moves - to keep a track on the game played
                    moves.put(serial, rand);
                    logger.logMe("Board state " + baseInstance.getSerialized2Dto1D(board));
                    logger.logMe("Menace played " + (int) Math.floor(rand / 3) + ", " + rand % 3);
                    // mark the menace move on the board
                    board[(int) Math.floor(rand / 3)][rand % 3] = 1;
                } else {
                    logger.logMe("Board state " + baseInstance.getSerialized2Dto1D(board));
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
                    logger.logMe((turn ? "Human: " : "Menace: ") + toCheckWin);
                    break;
                }
                turn = !turn;
                chance++;
            }

            if (chance == 9) {
                baseInstance.rewardSystem(moves, baseInstance.deltaRewardWhenDraw());
                logger.logMe("Draw: " + baseInstance.getSerialized2Dto1D(board));
            }
        }
        baseInstance.getAllCombinations();
    }

    private final static int totalGames = 100;
}

