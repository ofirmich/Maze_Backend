package algorithms.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch extends ASearchingAlgorithm {
    public Solution solve(ISearchable s) {
        HashMap<Integer, AState> closed = new HashMap<Integer, AState>();
        HashMap<Integer, AState> openHash = new HashMap<Integer, AState>();
        Queue<AState> open = new LinkedList<AState>();
        open.add(s.getStartState());
        openHash.put(s.getStartState().hashCode(), s.getStartState());
        while (!(open.isEmpty())) {
            AState currSt = open.poll();
            this.numOfEnodes++;
            openHash.remove(currSt.hashCode());
            closed.put(currSt.hashCode(), currSt);
            if (currSt.equals(s.getGoalState())) {
                s.getGoalState().setCost(currSt.getCost());
                return restorePath(currSt, s.getStartState());
            }
            ArrayList<AState> successors = s.getAllPossibleStates(currSt, 1, 1);
            for (int i = 0; i < successors.size(); i++) {
                AState currNeighb = successors.get(i);
                if ((closed.containsKey(currNeighb.hashCode())))
                    continue;
                if (!(openHash.containsKey(currNeighb.hashCode()))) {
                    currNeighb.setPrev(currSt);
                    openHash.put(currNeighb.hashCode(), currNeighb);
                    open.add(currNeighb);
                } else if (currNeighb.getCost() < openHash.get(currNeighb.hashCode()).getCost()) {
                    if (!(openHash.containsKey(currNeighb.hashCode()))) {
                        open.add(currNeighb);
                    }
                    openHash.get(currNeighb.hashCode()).setCost(currNeighb.getCost());
                }
            }
        }

        return new Solution(new ArrayList<AState>());
    }
}
