package tcp;

import java.io.*;
import java.net.Socket;

public class Handler implements Runnable {

    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;

    Handler(Socket clientSocket) throws IOException {

        this.clientSocket = clientSocket;
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());
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

    public void run() {

        int messageCount = 0;
        int bytesCount = 0;

        while(true) {

            try {
                byte[] messageBytes = readBytes();
                String readMessage = new String(messageBytes);

                if(readMessage.equals("Done")) {
                    break;
                }

                bytesCount = bytesCount + messageBytes.length;
                messageCount = messageCount + 1;

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Protocol used: TCP");
        System.out.println("Number of sent messages: " + messageCount);
        System.out.println("Number of sent bytes: " + bytesCount);

        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
