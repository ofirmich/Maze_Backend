package algorithms.mazeGenerators;
//new one
import java.util.ArrayList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {

    public Maze generate(int row, int col) {
        int[][] mazeArr = new int[row][col];
        // Initialize
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                mazeArr[i][j] = 1;//not visited

        Position start = pickStartPosition(row, col);
        int x = start.getRowIndex();
        int y = start.getColumnIndex();
        mazeArr[x][y] = 0;

        Random rnd = new Random();
        ArrayList<Position> neighbors = new ArrayList<Position>();
        neighbors = findNeighb(x,y , neighbors , mazeArr);
        while (!(neighbors.isEmpty())) {
            Position curNeighb = neighbors.get(rnd.nextInt(neighbors.size()));
            int neighbX = curNeighb.getRowIndex();
            int neighbY = curNeighb.getColumnIndex();


            if (count0Neighb(neighbX, neighbY, mazeArr) < 2) {
                mazeArr[neighbX][neighbY] = 0;
                neighbors = findNeighb(neighbX , neighbY , neighbors , mazeArr);
            }
            neighbors.remove(curNeighb);
        }
        Position goal = pickGoalPosition(row, col, start, mazeArr , false);
        Maze newMaze = new Maze(mazeArr, start, goal);
        return newMaze;
    }

    public boolean isValid(int x, int y, int[][] maze) {
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length) {
            return true;
        }
        return false;
    }

    public ArrayList<Position> checkNeighbors(int x, int y, ArrayList<Position> N, int[][] maze) {
        if (isValid(x,y,maze) && maze[x][y] == 1) {
            for (int i = 0; i < N.size(); i++) {
                if (N.get(i).getRowIndex() == x && N.get(i).getColumnIndex() == y) {
                    return N;
                }
            }
            Position pos = new Position(x, y);
            N.add(pos);
        }
        return N;
    }

    public ArrayList<Position> findNeighb (int x , int y , ArrayList<Position>  neighbors , int[][] mazeArr){
        checkNeighbors(x - 1, y , neighbors , mazeArr);
        checkNeighbors(x + 1, y , neighbors , mazeArr);
        checkNeighbors(x, y + 1, neighbors , mazeArr);
        checkNeighbors(x, y - 1, neighbors , mazeArr);
        return neighbors;
    }


    public int count0Neighb(int x, int y, int[][] maze) {
        int count = 0;
        if (isValid(x+1,y,maze) && maze[x + 1][y] == 0) {
            count++;
        }
        if (isValid(x-1 ,y,maze) && maze[x - 1][y] == 0) {
            count++;
        }
        if (isValid(x ,y+1,maze) && maze[x][y + 1] == 0) {
            count++;
        }
        if (isValid(x ,y-1,maze) && maze[x][y - 1] == 0) {
            count++;
        }
        return count;
    }
}
