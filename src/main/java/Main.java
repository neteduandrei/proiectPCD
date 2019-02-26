import tcp.Client;
import tcp.Server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void startTcpServer() throws IOException {

        Server tcpServer = new Server();
        tcpServer.start(6666);
    }

    public static void startTcpClient() throws IOException {

        List<String> messages = new ArrayList<String>();
        messages.add("DA");
        messages.add("Sunt");

        Client tcpClient = new Client("127.0.0.1", 6666);
        tcpClient.run(messages);
    }

    public static void main(String[] args) throws IOException {

        startTcpServer();
    }
}
