package algorithms.search;

import algorithms.mazeGenerators.Position;

public class MazeState extends AState  {
    Position pos;

    public MazeState(String state, int cost, AState prev, Position pos) {
        super(state, cost, prev);
        this.pos = pos;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        MazeState mazeState = (MazeState) o;
        return pos.equals(mazeState.pos);
    }

    public Position getPos() {
        return pos;
    }

    @Override
    public int hashCode() {
        return pos.toString().hashCode();
    }

    @Override
    public String toString() {
        return "" + pos + "";
    }
}
