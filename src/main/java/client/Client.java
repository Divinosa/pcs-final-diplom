package client;

import service.PageEntry;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {


    String host = "localhost";
    int port = 80;
    public Client() throws IOException {
        try (Socket clientSocket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())){

            String word = reader.readLine();
            out.write(word + "\n");
            out.flush();
            List<PageEntry> pageEntryList = new ArrayList<>();
            pageEntryList.add((PageEntry) in.readObject());
            System.out.println(pageEntryList);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
