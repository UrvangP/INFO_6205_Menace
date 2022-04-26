import java.util.*;

public class MenaceBegin {

    public static void main(String[] args) {
        System.out.println("args" + args);
        Boolean isUser = args.length > 0 ? Boolean.parseBoolean(args[0]) : false;
        Scanner humanTurnInput = new Scanner(System.in);
        // Initializes the Base Class
        BaseState baseInstance = new BaseState();
        // Initializes the MenaceLogger
        MenaceLogger logger = new MenaceLogger();
        // Total no. of states in HashMap and writes those into a file
        System.out.println("total combinations: " + baseInstance.getAllCombinations().size());

        int humanWins = 0;
        int menaceWins = 0;
        int draw = 0;

        Map<String, Object> results = new HashMap<>();
        Map<String, Object> beadGraph = new HashMap<>();

        for (int i = 0; i < (isUser ? 1 : totalGames); ++i) {
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

                    int countPositions = 0;
                    int countBeads = 0;
                    for (int val : positionsToPlay) {
                        if (val > 0) {
                            countPositions++;
                            countBeads += val;
                        }

                    }

                    if (countPositions == 9) {
                        //count
                        List<Integer> beads = new ArrayList<>();
                        beads.add(countBeads);
                        beadGraph.put(String.valueOf(i), beads);
                    }

                    // randomly pick from the available spots
                    int rand = Utils.pickRandomIndex(positionsToPlay, serial);
                    // moves - to keep a track on the game played
                    moves.put(serial, rand);
                    logger.logMe("Board state " + baseInstance.getSerialized2Dto1D(board));
                    logger.logMe("Menace played " + (int) Math.floor(rand / 3) + ", " + rand % 3);
                    // mark the menace move on the board
                    board[(int) Math.floor(rand / 3)][rand % 3] = 1;
                } else {
                    logger.logMe("Board state " + baseInstance.getSerialized2Dto1D(board));

                    if (isUser) {
                        for (int k = 0; k < 3; k++) {
                            String str = "";
                            for (int m = 0; m < 3; m++) {
                                str += Integer.toString(board[k][m]);
                                if (m != 2) {
                                    str += " | ";
                                }
                            }
                            System.out.println(str);
                        }
                        System.out.println("Enter in format x,y: ");
                        String step = humanTurnInput.nextLine();
                        String[] inputs = step.split(",");
                        board[Integer.parseInt(inputs[0])][Integer.parseInt(inputs[1])] = 2;
                        logger.logMe("User played " + inputs[0] + ", " + inputs[1]);
                    } else human.play();
                }
                toCheckWin = baseInstance.getSerialized2Dto1D((board));
                if (baseInstance.isValidWinWinMove(toCheckWin)) {
                    if (turn) {
                        //subtract beads
                        humanWins++;
                        baseInstance.rewardSystem(moves, -1 * baseInstance.gammaRewardWhenLose());
                    } else {
                        //add beads
                        menaceWins++;
                        baseInstance.rewardSystem(moves, baseInstance.betaRewardWhenWin());
                    }
                    logger.logMe((turn ? isUser ? "User" : "Human: " : "Menace: ") + toCheckWin);
                    break;
                }
                turn = !turn;
                chance++;
            }

            if (chance == 9) {
                baseInstance.rewardSystem(moves, baseInstance.deltaRewardWhenDraw());
                draw++;
                logger.logMe("Draw: " + baseInstance.getSerialized2Dto1D(board));
            }

            List<Object> winsLoss = new ArrayList<>();
            winsLoss.add(humanWins);
            winsLoss.add(menaceWins);
            winsLoss.add(draw);
            results.put(String.valueOf(i), winsLoss);
            logger.logMe("Result:" + i + "," + humanWins + "," + menaceWins + "," + draw);
        }

        CSV fileGenerator = new CSV();
        fileGenerator.generateCSV("winLoss", results);
        fileGenerator.generateCSV("beadGraph", beadGraph);

        baseInstance.getAllCombinations();
    }

    private final static int totalGames = 500;
}

