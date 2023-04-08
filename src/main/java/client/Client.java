package client;

import java.io.*;
import java.net.Socket;

public class Client {


    String host = "localhost";
    int port = 80;
    public Client() throws IOException {
        try (Socket clientSocket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))){

            String word = reader.readLine();
            out.write(word + "\n");
            out.flush();
            String serverAnswer = in.readLine();
            System.out.println(serverAnswer);
        }

    }
}
