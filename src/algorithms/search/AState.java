package algorithms.search;

import java.io.Serializable;

public abstract class AState implements Serializable {
    private String state;
    private int cost;
    private AState prev;


    public AState(String state, int cost, AState prev) {
        this.state = state;
        this.cost = cost;
        this.prev = prev;
    }

    public int compareTo(AState s2) {
        if (this.getCost() > s2.getCost()) {
            return 1;
        }
        if (this.getCost() < s2.getCost()) {
            return -1;
        } else return 0;
    }

    public String getState() {
        return state;
    }

    public int getCost() {
        return cost;
    }

    public AState getPrev() {
        return prev;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setPrev(AState prev) {
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AState)) return false;
        AState aState = (AState) o;
        return cost == aState.cost;
    }
}
