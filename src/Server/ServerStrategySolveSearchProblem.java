package Server;


import algorithms.mazeGenerators.EmptyMazeGenerator;
import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.MyMazeGenerator;
import algorithms.mazeGenerators.SimpleMazeGenerator;
import algorithms.search.*;
import java.io.*;

import java.util.HashMap;
import java.util.Properties;


public class ServerStrategySolveSearchProblem implements IServerStrategy {

    private HashMap<Integer, String> dict = new HashMap<>();
    public void serverStrategy(InputStream inputStream, OutputStream outputStream) throws IOException
    {

        FileInputStream in = new FileInputStream("C:\\Users\\ofirm\\Desktop\\ATP-Project-PartB-master (1)\\ATP-Project-PartB\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(in);

        Maze maze;
        Solution sol=null;
        File file;
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            String tempDirectoryPath = System.getProperty("java.io.tmpdir");
            maze = (Maze) fromClient.readObject();
            String mazeArr = maze.toByteArray().toString();

            if(dict.containsKey(maze.hashCode())){
                file = new File(tempDirectoryPath+"/Maze" + dict.get(maze.hashCode()) + ".txt");
                FileInputStream solved = new FileInputStream(file);
                ObjectInputStream obj = new ObjectInputStream(solved);
                sol = (Solution) obj.readObject();
                obj.close();
                solved.close();
            }
            else {
                file = new File(tempDirectoryPath+"/Maze" + mazeArr + ".txt");
                String output = prop.getProperty("solveAlgo");//////
                SearchableMaze searchableMaze = new SearchableMaze(maze);
                if (output == null) {
                    ISearchingAlgorithm searcher = new DepthFirstSearch();
                    sol = searcher.solve(searchableMaze);
                }

                else {
                    if (output.compareTo("DFS") == 0) {

                        ISearchingAlgorithm searcher = new DepthFirstSearch();
                        sol = searcher.solve(searchableMaze);
                    } else if (output.compareTo("BFS") == 0) {

                        ISearchingAlgorithm searcher = new BreadthFirstSearch();
                        sol = searcher.solve(searchableMaze);
                    } else {
                        ISearchingAlgorithm searcher = new BestFirstSearch();
                        sol = searcher.solve(searchableMaze);
                    }
                }
                dict.put(maze.hashCode() , mazeArr);
                FileOutputStream solved = new FileOutputStream(file);
                ObjectOutputStream obj = new ObjectOutputStream(solved);
                obj.writeObject(sol);
                obj.close();
                solved.close();
            }

            toClient.writeObject(sol);
            fromClient.close();
            toClient.flush();
            toClient.close();
            }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}

