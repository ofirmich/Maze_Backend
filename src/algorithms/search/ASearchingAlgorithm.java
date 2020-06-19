package algorithms.search;

import java.util.ArrayList;

public abstract class ASearchingAlgorithm implements ISearchingAlgorithm {
    protected int numOfEnodes=0;

    public String getName() {
        return this.getClass().getName();
    }
    public abstract Solution solve (ISearchable s);
    public int getNumberOfNodesEvaluated(){
        return numOfEnodes;
    }
    public Solution restorePath(AState goal, AState start) {
        ArrayList<AState> solArr = new ArrayList<AState>();
        AState curr = goal;
        while (!(curr.equals(start))) {
            solArr.add(curr);
            curr = curr.getPrev();
        }
        solArr.add(curr);
        return new Solution(solArr);
    }
}
