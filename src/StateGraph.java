import java.util.*;

public class StateGraph {

    Map<String, Map<String,Integer>> states;
    Map<String, State> serialStateMap;
    Set<String> winStates;

    StateGraph(){
        states = new HashMap<>();
        serialStateMap = new HashMap<>();
        winStates = new HashSet<>();
        assignWinStates();
        generateStates( );
    }

    public void generateStates( ){

        State firstState = new State( new int[9], 1 );

        Queue<String> graphQ = new LinkedList<>();
        graphQ.offer( firstState.getSerial() );
        serialStateMap.put( firstState.getSerial(), firstState );
        while( !graphQ.isEmpty() ){
            String currentSerial = graphQ.poll();

            State currentState = serialStateMap.get(currentSerial);
            int[] board = currentState.getBoard();
            int player = currentState.getNextPlayer();

            if( !states.containsKey( currentSerial ) ){
                states.put( currentSerial, new HashMap<>() );
            }
            else{
                continue;
            }

            Map<String, Integer> paths = states.get(currentSerial);

            //if current state serial is win condition we break
            if( isThisWinState(currentSerial)){
                continue;
            }

            Set<Integer> steps = currentState.getEmptyBlocks();
            for( int next : steps ){
                board[next] = player;

                int nextP = player == 1 ? 2 : 1;

                State nextState = new State( board, nextP );

                if( !serialStateMap.containsKey(nextState.getSerial()) ){
                    serialStateMap.put(nextState.getSerial(), nextState);
                    graphQ.offer(nextState.getSerial());
                }
                else{
                    nextState = serialStateMap.get(nextState.getSerial());
                }

                paths.put(nextState.getSerial(), 0);
                board[next]=0;
            }
        }
    }

    public void assignWinStates(){
        winStates.add("1#1#1#?#?#?#?#?#?#");
        winStates.add("?#?#?#1#1#1#?#?#?#");
        winStates.add("?#?#?#?#?#?#1#1#1#");
        winStates.add("1#?#?#1#?#?#1#?#?#");
        winStates.add("?#1#?#?#1#?#?#1#?#");
        winStates.add("?#?#1#?#?#1#?#?#1#");
        winStates.add("1#?#?#?#1#?#?#?#1#");
        winStates.add("?#?#1#?#1#?#1#?#?#");

        winStates.add("2#2#2#?#?#?#?#?#?#");
        winStates.add("?#?#?#2#2#2#?#?#?#");
        winStates.add("?#?#?#?#?#?#2#2#2#");
        winStates.add("2#?#?#2#?#?#2#?#?#");
        winStates.add("?#2#?#?#2#?#?#2#?#");
        winStates.add("?#?#2#?#?#2#?#?#2#");
        winStates.add("2#?#?#?#2#?#?#?#2#");
        winStates.add("?#?#2#?#2#?#2#?#?#");
    }

    public boolean isThisWinState( String inputState ){

        boolean result = false;
        for( String st : winStates ){
            boolean found = true;
            for( int i=0; i<inputState.length(); ++i){
                if( st.charAt(i) != '?' && st.charAt(i) != inputState.charAt(i) ){
                    found = false;
                    break;
                }
            }
            result = result || found;
        }

        if(result) System.out.println(inputState);
        return result;
    }
}
