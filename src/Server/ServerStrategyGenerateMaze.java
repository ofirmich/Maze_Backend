package Server;

import IO.MyCompressorOutputStream;
import IO.MyDecompressorInputStream;
import algorithms.mazeGenerators.*;

import java.io.*;
import java.util.Properties;

public class ServerStrategyGenerateMaze implements IServerStrategy {
    @Override
    public void serverStrategy(InputStream inputStream, OutputStream outputStream) throws IOException {
        FileInputStream in = new FileInputStream("C:\\Users\\ofirm\\Desktop\\ATP-Project-PartB-master (1)\\ATP-Project-PartB\\resources\\config.properties");
        Properties prop = new Properties();
        prop.load(in);

        int [] mazeSize;
        try {
            ObjectInputStream fromClient = new ObjectInputStream(inputStream);
            ObjectOutputStream toClient = new ObjectOutputStream(outputStream);
            //  String reversedPhrase;
            mazeSize = (int[]) fromClient.readObject();
            String output = prop.getProperty("generateAlgo");//////
            byte[] bMaze;
            if (output == null) {
                MyMazeGenerator mg = new MyMazeGenerator();
                Maze maze = mg.generate(mazeSize[0], mazeSize[1]);
                bMaze = maze.toByteArray();
            }
            else {
                if (output.compareTo("empty") == 0) {
                    EmptyMazeGenerator mg = new EmptyMazeGenerator();
                    Maze maze = mg.generate(mazeSize[0], mazeSize[1]);
                    bMaze = maze.toByteArray();
                } else if (output.compareTo("simple") == 0) {
                    SimpleMazeGenerator mg = new SimpleMazeGenerator();
                    Maze maze = mg.generate(mazeSize[0], mazeSize[1]);
                    bMaze = maze.toByteArray();
                } else {
                    MyMazeGenerator mg = new MyMazeGenerator();
                    Maze maze = mg.generate(mazeSize[0], mazeSize[1]);
                    bMaze = maze.toByteArray();
                }
            }

            //Maze maze = mg.generate(mazeSize[0], mazeSize[1]);
            //byte[] bMaze = maze.toByteArray();
            /*if(((mazeSize[0] * mazeSize[1])-12) % 8 != 0 ){
                leftover =1;
            }*/
            ByteArrayOutputStream b = new ByteArrayOutputStream();//(mazeSize[0]* mazeSize[1])+12
            MyCompressorOutputStream out = new MyCompressorOutputStream(b);
            out.write(bMaze);
            // byte[] arrayToSend = b.toByteArray();
            toClient.writeObject(b.toByteArray());
           // String ss = "asd";
            //byte[] bb = ss.getBytes();
//            toClient.writeObject(bb);
            fromClient.close();
            toClient.flush();
            toClient.close();



//

//            toClient.flush();
//            toClient.close();
//            out.flush();//dont wait until you have alot of data- send now!
//            out.close();
//            fromClient.close();
        }catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

    }
    }

