package Client;

import java.io.InputStream;
import java.io.OutputStream;

public interface IClientStrategy {
    void clientStrategy(InputStream inputStream , OutputStream outputStream);//input and output stream of the server because we want to communicate with the server
}
