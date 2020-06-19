package algorithms.mazeGenerators;

import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator {
    public long measureAlgorithmTimeMillis(int row, int col) {
        long firstTime = System.currentTimeMillis(); //time before generate maze
        generate(row, col);
        long secondTime = System.currentTimeMillis(); //time after generate maze
        return (secondTime - firstTime); // time for generate maze - diff between first and sec
    }

    public Position pickStartPosition(int row, int col) {
        Random rand = new Random();
        int r = rand.nextInt(row);
        int c = rand.nextInt(col);
        return new Position(r, c);
    }

    public Position pickGoalPosition(int row, int col, Position start , int[][] mazeArr , boolean isSimple) {
        boolean validGoal = false;
        Random rand = new Random();
        while (validGoal == false) {
            int r = rand.nextInt(row);
            int c = rand.nextInt(col);
            if(isSimple == false) {
                if (mazeArr[r][c] == 0 && (!(start.getRowIndex() == r && start.getColumnIndex() == c))) {
                    validGoal = true;
                    return new Position(r, c);
                }
            }
            else if ((!(start.getRowIndex() == r && start.getColumnIndex() == c))) {
                validGoal = true;
                return new Position(r, c);
            }
        }
        return null;
    }
}
