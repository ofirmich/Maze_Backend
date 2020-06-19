package Client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private InetAddress serverIP;
    private int port;
    private IClientStrategy clientStrategy;

    public Client(InetAddress serverIp, int port, IClientStrategy clientStrategy) {
        this.serverIP = serverIp;
        this.port = port;
        this.clientStrategy = clientStrategy;
    }

    public void communicateWithServer(){
        try{
            Socket socket = new Socket(this.serverIP , this.port);//restart the socket
            //System.out.println("Client is connected to server!");
            //connect to the server, run your strategy in the server and close the socket
            clientStrategy.clientStrategy(socket.getInputStream() , socket.getOutputStream());
            socket.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}


