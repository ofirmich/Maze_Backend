package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

public class Solution implements Serializable {
    ArrayList<AState> sol;

    public Solution(ArrayList<AState> sol) {
        this.sol = sol;
    }

    public ArrayList<AState> getSolutionPath() {
        return this.sol;

    }
}
