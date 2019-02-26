package tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {

        serverSocket = new ServerSocket(port);
        Executor executor = Executors.newFixedThreadPool(8);

        while(!serverSocket.isClosed()) {

            clientSocket = serverSocket.accept();

            Handler handler = new Handler(clientSocket);
            executor.execute(handler);
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        serverSocket.close();
    }
}
