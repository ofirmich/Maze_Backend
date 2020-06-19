package algorithms.search;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

abstract class AStateComparator implements Comparator<AState> {
    public int compareTo(AState s1, AState s2) {
        if (s1.getCost() < s2.getCost())
            return 1;
        else if (s1.getCost() > s2.getCost())
            return -1;
        return 0;
    }
}

public class BestFirstSearch extends ASearchingAlgorithm{
    public Solution solve(ISearchable s) {
        HashMap<Integer, AState> closed = new HashMap<Integer, AState>();
        HashMap<Integer, AState> openHash = new HashMap<Integer, AState>();
        PriorityQueue<AState> open = new PriorityQueue<AState>(new AStateComparator() {
            @Override
            public int compare(AState o1, AState o2) {
                return o1.compareTo(o2);
            }
        });
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
            ArrayList<AState> successors = s.getAllPossibleStates(currSt, 15, 10);
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
                        openHash.put(currNeighb.hashCode(), currNeighb);
                    }
                    openHash.get(currNeighb.hashCode()).setCost(currNeighb.getCost());
                }
            }
        }
        return new Solution(new ArrayList<AState>());
    }
}
