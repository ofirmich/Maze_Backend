package algorithms.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

import java.util.ArrayList;

public class SearchableMaze implements ISearchable {
    private Maze maze;
    private MazeState start;
    private MazeState goal;

    public SearchableMaze(Maze maze) {
        this.maze = maze;
        this.start = new MazeState("0" , 0, null, maze.getStartPosition());
        this.goal = new MazeState("0" , 0 , null, maze.getGoalPosition());
    }

    public AState getStartState(){return this.start;}
    public AState getGoalState(){return this.goal;}

    public ArrayList<AState> getAllPossibleStates(AState s , int diagonalCost , int straightCost){
        ArrayList<AState> possible = new ArrayList<AState>();
        MazeState st = (MazeState) s;
        int x = st.pos.getRowIndex();
        int y = st.pos.getColumnIndex();
        for(int r = x-1 ; r <= x + 1; r++) {
            for (int c = y - 1; c <= y + 1; c++) {
                if ((r != x || c != y )&&isValid(r, c) && maze.getMazeCell(r , c) == 0){
                    if(r != x && c != y){ //has to be diagonal
                        if(r==x+1 && c==y+1 && (maze.getMazeCell(x+1,y) == 0 || maze.getMazeCell(x,y+1) == 0))
                            possible.add(new MazeState("0" , st.getCost()+diagonalCost , st, new Position(r,c)));
                        else if(r==x+1 && c==y-1 && (maze.getMazeCell(x+1,y) == 0 || maze.getMazeCell(x,y-1) == 0))
                            possible.add(new MazeState("0" , st.getCost()+diagonalCost , st,new Position(r,c)));
                        else if(r==x-1 && c==y+1 && (maze.getMazeCell(x-1,y) == 0 || maze.getMazeCell(x,y+1) == 0))
                            possible.add(new MazeState("0" , st.getCost()+diagonalCost , st, new Position(r,c)));
                        else if(r==x-1 && c==y-1 && (maze.getMazeCell(x-1,y) == 0 || maze.getMazeCell(x,y-1) == 0))
                            possible.add(new MazeState("0" , st.getCost()+diagonalCost , st, new Position(r,c)));
                    }
                    else
                        possible.add(new MazeState("0" , st.getCost()+straightCost , st, new Position(r,c)));
                }
            }
        }
        return possible;
    }

    public boolean isValid(int x, int y) {
        if (x >= 0 && x < this.maze.getRows() && y >= 0 && y < this.maze.getCols()) {
            return true;
        }
        return false;
    }
}
