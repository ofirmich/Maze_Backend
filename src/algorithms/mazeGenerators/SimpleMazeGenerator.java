package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {
    public Maze generate(int row, int col) {
        Random rnd = new Random();
        int[][] newMaze = new int[row][col];
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                int num = Math.random()>0.5 ? 1 : 0;
                newMaze[i][j] = num;
            }
        }
        Position start = pickStartPosition(row, col);
        newMaze[start.getRowIndex()][start.getColumnIndex()] = 0;
        Position end = pickGoalPosition(row, col , start, newMaze,true);

        if(start.getRowIndex() - end.getRowIndex() < 0) {
            int i = start.getRowIndex();
            while (i <= end.getRowIndex()) {
                newMaze[i][start.getColumnIndex()]=0;
                i++;
            }
        }
        else if(start.getRowIndex() - end.getRowIndex() > 0) {
            int i = start.getRowIndex();
            while (i >= end.getRowIndex()) {
                newMaze[i][start.getColumnIndex()] = 0;
                i--;
            }
        }

        if(start.getColumnIndex() - end.getColumnIndex() > 0) {
            int i = start.getColumnIndex();
            while (i >= end.getColumnIndex()) {
                newMaze[end.getRowIndex()][i]= 0;
                i--;
            }
        }
        else if(start.getColumnIndex() - end.getColumnIndex() < 0) {
            int i = start.getColumnIndex();
            while (i <= end.getColumnIndex()) {
                newMaze[end.getRowIndex()][i]= 0;
                i++;
            }
        }
        Maze maze = new Maze(newMaze,start , end);

        return maze;
    }
}
