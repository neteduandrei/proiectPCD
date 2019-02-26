package udp;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Client {

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;

    public Client(String ip, int port) throws IOException {

        socket = new Socket(ip, port);
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    private void sendBytes(byte[] message) throws IOException {

        out.writeInt(message.length);
        out.write(message, 0, message.length);
    }

    private byte[] readBytes() throws IOException {

        int len = in.readInt();
        byte[] message = new byte[len];

        int readLen = in.read(message);
        return message;
    }

    public void run(List<String> message) throws IOException {

        for(String x : message) {

            sendBytes(x.getBytes());

            String readMessage = new String(readBytes());

            if(!readMessage.equals("Done")) {
                throw new IOException();
            }
        }

        socket.close();
    }
}
