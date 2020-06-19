package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{
    public Maze generate(int row, int col) {
        int[][] newMaze = new int[row][col];
        for (int i = 0; i<row ; i++){
            for (int j = 0; j<col ; j++){
                newMaze[i][j] = 0;
            }
        }

        Position start = pickStartPosition(row , col);
        Position goal = pickGoalPosition(row , col , start , newMaze , false);


        Maze maze = new Maze(newMaze , start , goal);


        return maze;

    }
}
