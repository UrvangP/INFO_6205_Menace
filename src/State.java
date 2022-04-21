import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;

public class State {

    int[] board;
    StringBuilder serial;
    Set<Integer> emptyBlocks;
    int nextPlayer;

    State(){
        board = new int[9];
        emptyBlocks = new HashSet<>();
        serial = new StringBuilder();
    }

    State( int[] data, int player ){
        board = Arrays.copyOf(data,9); //make a deep copy
        emptyBlocks = new HashSet<>();
        serial = new StringBuilder();
        nextPlayer = player;
        serializeBoard();
        generateNextSteps();
    }

    public void serializeBoard(){

        for( int val : board ){
            serial.append(val);
            serial.append('#');
        }

    }

    public void generateNextSteps(){

        for( int ind = 0; ind < board.length; ++ind ){
            if( board[ind] == 0 ){
                emptyBlocks.add(ind);
            }
        }
    }

    public String getSerial() {
        return serial.toString();
    }

    public Set<Integer> getEmptyBlocks() {
        return emptyBlocks;
    }

    public int[] getBoard() {
        return board;
    }

    public int getNextPlayer() {
        return nextPlayer;
    }
}
