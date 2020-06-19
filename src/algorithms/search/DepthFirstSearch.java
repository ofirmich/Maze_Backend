package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class DepthFirstSearch extends ASearchingAlgorithm {
    public Solution solve(ISearchable s) {
        Stack<AState> states = new Stack<AState>();
        states.push(s.getStartState());
        HashMap<Integer, AState> visited = new HashMap<Integer, AState>();
        while (!(states.peek().equals(s.getGoalState()))) {
            AState currSt = states.pop();
            if (!(visited.containsKey(currSt.hashCode()))) {
                visited.put(currSt.hashCode(), currSt);
                this.numOfEnodes++;
                for (AState stNighb : s.getAllPossibleStates(currSt, 1, 1)) {
                    if (!(visited.containsKey(stNighb.hashCode()))) {
                        stNighb.setPrev(currSt);
                        states.push(stNighb);
                    }
                }
                if (states.empty()) {
                    return new Solution(new ArrayList<AState>());
                }
            }
        }
        AState currSt = states.pop();
        s.getGoalState().setCost(currSt.getCost());
        return restorePath(currSt, s.getStartState());
    }
}
