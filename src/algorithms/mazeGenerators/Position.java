package algorithms.mazeGenerators;

import java.io.Serializable;

public class Position implements Serializable {
    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public String toString() {
        return "{"+ row +
                "," + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row &&
                column == position.column;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return column;
    }
}
