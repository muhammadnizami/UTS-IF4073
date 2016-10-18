package nizami_13512501.UTS;

/**
 * Created by nim_13512501 on 05/10/16.
 */
public class DirectionAlphabetDFA {
    //states: 0..numStates-1
    //start state: 0

    int numStates;
    int [] finalStates;
    int[][] transition;
    //transisi dari state s, dengan alfabet x, menuju transition[s][x]
    //transition[s][x]=-1 artinya tidak ada transisi
    //transition[s][0] dikosongkan
    //dimensi harus numStates X 9

    public DirectionAlphabetDFA(int numStates, int[] finalStates, int[][] transition){
        this.numStates = numStates;
        this.finalStates = finalStates;
        this.transition = transition;
    }

    public boolean isAFinalState(int state){
        for (int i : finalStates){
            if (state==i)
                return true;
        }
        return false;
    }

    public boolean accepts(Integer[] input){
        int currentState = 0;
        for (int i=0;i<input.length;i++){
            int x = input[i];
            int transitionTo = transition[currentState][x];
            if (transitionTo < 0)
                return false;
            currentState = transitionTo;
        }

        return isAFinalState(currentState);
    }
}
