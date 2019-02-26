import org.junit.Test;

import java.io.IOException;

public class MainTest {

    @Test
    public void tcpClient() throws IOException {

        Main.startTcpClient();
    }

    @Test
    public void tcpServer() throws IOException {

        Main.startTcpServer();
    }
}
