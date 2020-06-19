package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IServerStrategy {
    public void serverStrategy(InputStream inputStream , OutputStream outputStream) throws IOException, ClassNotFoundException;//the input and output of the client-the servers way to communicate with the client

}
