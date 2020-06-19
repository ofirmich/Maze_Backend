package test;
import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.AMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import java.io.*;
import java.util.Arrays;

public class RunCompressDecompressMaze {
    public static void main(String[] args) {
        String mazeFileName = "savedMaze.maze";
        AMazeGenerator mazeGenerator = new MyMazeGenerator();
        Maze maze = mazeGenerator.generate(500, 500); //Generate new maze
//        maze.setMazeCell(0,0,0);
//        maze.setMazeCell(0,1,0);
//        maze.setMazeCell(0,2,0);
//        maze.setMazeCell(1,0,0);
//        maze.setMazeCell(1,1,1);
//        maze.setMazeCell(1,2,0);
//        maze.setMazeCell(2,0,1);
//        maze.setMazeCell(2,1,0);
//        maze.setMazeCell(2,2,1);
//        maze.setMazeCell(3,0,0);
//        maze.setMazeCell(3,1,0);
//        maze.setMazeCell(3,2,0);
        try {
            //maze.print();
            // save maze to a file
            OutputStream out = new MyCompressorOutputStream(new
                    FileOutputStream(mazeFileName));
            out.write(maze.toByteArray());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte savedMazeBytes[] = new byte[0];
        try {
            //read maze from file
            InputStream in = new MyDecompressorInputStream(new
                    FileInputStream(mazeFileName));
            savedMazeBytes = new byte[maze.toByteArray().length];
            in.read(savedMazeBytes);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Maze loadedMaze = new Maze(savedMazeBytes);
        //System.out.println("\n");
        //loadedMaze.print();
        boolean areMazesEquals =
                Arrays.equals(loadedMaze.toByteArray(), maze.toByteArray());
        System.out.println(String.format("Mazes equal: %s", areMazesEquals));
//maze should be equal to loadedMaze
    }
}
