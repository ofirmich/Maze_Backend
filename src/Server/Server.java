package Server;
//new
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Properties;

public class Server implements Runnable{
    private int port;
    private int listeningInterval;
    private IServerStrategy serverStrategy;//the strategy for handeling clients
    private volatile boolean stop;
    private ExecutorService threadPoolExecutor;//the thread pool

    public static class Configurations{

            public static void main(String[] args) {

                try (InputStream input = Configurations.class.getClassLoader().getResourceAsStream("config.properties")) {

                    Properties prop = new Properties();

                    if (input == null) {
                        System.out.println("Sorry, unable to find config.properties");
                        return;
                    }

                    //load a properties file from class path, inside static method
                    prop.load(input);

                    //get the property value and print it out
//                    System.out.println(prop.getProperty("db.url"));
//                    System.out.println(prop.getProperty("db.user"));
//                    System.out.println(prop.getProperty("db.password"));

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }

        }

    public Server(int port, int listeningInterval, IServerStrategy serverStrategy) {
        this.port = port;
        this.listeningInterval = listeningInterval;
        this.serverStrategy = serverStrategy;
        this.stop = false;
        int numOfThreads=5;
        String output = "";
        try {
            FileInputStream in = new FileInputStream("C:\\Users\\ofirm\\Desktop\\ATP-Project-PartB-master (1)\\ATP-Project-PartB\\resources\\config.properties");
            Properties prop = new Properties();
            prop.load(in);
            output = prop.getProperty("numOfThreads");//////

            //this.threadPoolExecutor = Executors.newFixedThreadPool(Integer.parseInt(output));
        }
        catch (IOException e){}
        if(output == null) {
            this.threadPoolExecutor = Executors.newFixedThreadPool(numOfThreads);
        }
        else{
          //  numOfThreads = Integer.parseInt(output);
            this.threadPoolExecutor = Executors.newFixedThreadPool(numOfThreads);
        }

    }
    public void start() {
        new Thread(this).start();
    }


    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(listeningInterval);
            //if 1000 milliseconds will pass and we wont get clients request it should also get to the catch and get out of the while so we need to put try and catch inside the while as well

            while (!stop){
                //while the server still running
                try {
                    Socket clientSocket = serverSocket.accept();//wait until the client sends request and then we start communicate with him. here we have the details of communication with this client
                    threadPoolExecutor.execute(() ->{
                        serverStrategy(clientSocket);
                    });
//                    Thread thread = new Thread(() -> {
//                        serverStrategy(clientSocket);
//                    });
//                    threadPoolExecutor.execute(thread);


                }
                catch (IOException e){
                    //there was no request from a client in the last 1000 milliseconds
                    //System.out.println("Where are the clients???");
                }
            }
            serverSocket.close();
            threadPoolExecutor.shutdown();
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private void serverStrategy(Socket clientSocket) {
        try {
            InputStream inFromClient = clientSocket.getInputStream();
            OutputStream outToClient = clientSocket.getOutputStream();
            this.serverStrategy.serverStrategy(inFromClient, outToClient);

            inFromClient.close();
            outToClient.close();
            clientSocket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void stop()
    {
        //System.out.println("The server has stopped!");
        this.stop = true;
    }
}
