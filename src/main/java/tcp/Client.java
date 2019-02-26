package tcp;

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

        int bytesCount = 0;
        long startTime = System.nanoTime();

        for(String x : message) {

            byte[] bytes = x.getBytes();
            bytesCount = bytesCount + bytes.length;
            sendBytes(bytes);

            String readMessage = new String(readBytes());

            if(!readMessage.equals("Done")) {
                throw new IOException();
            }
        }

        sendBytes("Done".getBytes());
        socket.close();

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;

        System.out.println("Transmission time: " + totalTime);
        System.out.println("Number of sent messages: " + message.size());
        System.out.println("Number of sent bytes: " + bytesCount);
    }
}
