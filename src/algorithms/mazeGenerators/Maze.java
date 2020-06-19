package algorithms.mazeGenerators;

import java.io.Serializable;

public class Maze implements Serializable {
    private int[][] maze;
    private int rows;
    private int cols;
    private Position start;
    private Position goal;

    public Maze(int[][] maze , Position start , Position goal) {
        this.maze = maze;
        this.start = start;
        this.goal = goal;
        this.rows = maze.length;
        this.cols = maze[0].length;
    }

    public Maze(byte [] byteMaze) {
        //this.rows = (int) byteMaze[0] + (int) byteMaze[1];
        this.rows = Byte.toUnsignedInt(byteMaze[0]) + Byte.toUnsignedInt(byteMaze[1]);
        //this.cols = (int) byteMaze[2] + (int) byteMaze[3];
        this.cols = Byte.toUnsignedInt(byteMaze[2]) + Byte.toUnsignedInt(byteMaze[3]);
        this.start = new Position(Byte.toUnsignedInt(byteMaze[4]) + Byte.toUnsignedInt(byteMaze[5]) , Byte.toUnsignedInt(byteMaze[6]) + Byte.toUnsignedInt(byteMaze[7]));
        this.goal = new Position(Byte.toUnsignedInt(byteMaze[8]) + Byte.toUnsignedInt(byteMaze[9]) ,Byte.toUnsignedInt(byteMaze[10]) + Byte.toUnsignedInt(byteMaze[11]));
        int[][]maze = new int[rows][cols];
        int i=12;
        for(int j = 0 ; j< this.rows ; j++){
            for(int k = 0 ; k< this.cols; k++){
                maze[j][k] = Byte.toUnsignedInt(byteMaze[i]);
                i++;
            }
        }
        this.maze = maze;
    }

    public void setMaze(int[][] maze) {
        //this.maze = maze;
        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                this.maze[i][j] = maze[i][j];//not visited
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public Position getStartPosition() {
        return start;
    }

    public Position getGoalPosition() {
        return goal;
    }

    public void setStart(Position start) {
        this.start = start;
    }

    public void setGoal(Position goal) {
        this.goal = goal;
    }

    public int getMazeCell(int i, int j) {
        return this.maze[i][j];
    }

    public void setMazeCell(int i, int j, int num) {
        maze[i][j] = num;
    }

    public void print() {
        for (int i = 0; i < this.getRows(); i++) {
            for (int j = 0; j < this.getCols(); j++) {
                if (i == start.getRowIndex() && j == start.getColumnIndex())
                    System.out.print(" S ");
                else if (i == goal.getRowIndex() && j == goal.getColumnIndex())
                    System.out.print(" E ");
                else
                    System.out.print(" " + this.maze[i][j] + " ");

            }
            System.out.print("\n");
        }
    }

    public byte[] toByteArray(){
        byte [] byteMaze = new byte[(rows*cols)+12];
        insert(byteMaze , rows , 0 , 1);
        insert(byteMaze , cols , 2 , 3);
        insert(byteMaze , start.getRowIndex() , 4 , 5);
        insert(byteMaze , start.getColumnIndex() , 6 , 7);
        insert(byteMaze , goal.getRowIndex() , 8 , 9);
        insert(byteMaze , goal.getColumnIndex() , 10 , 11);
        int i=12;
        for(int j = 0 ; j< this.rows ; j++){
            for(int k = 0 ; k< this.cols; k++){
                byteMaze[i] = (byte) maze[j][k];
                i++;
             }
        }
        return byteMaze;
    }

    public byte[] insert(byte[] byteMaze,int par , int ind1 , int ind2){
        if(par > 255){
            byteMaze[ind1] = (byte)255;
            byteMaze[ind2] = (byte)(par-255);
        }
        else {
            byteMaze[ind1] = (byte)par;
            byteMaze[ind2] = (byte)0;
        }
        return byteMaze;
    }
}
