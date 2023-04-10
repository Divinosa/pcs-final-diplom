import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class Client {


    String host = "localhost";
    int port = 80;

    public Client() throws IOException {
        try (Socket clientSocket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            String word = reader.readLine();
            out.write(word + "\n");
            out.flush();
            ArrayList pageEntryList;
            pageEntryList = ((ArrayList) in.readObject());
            PageEntry.toString(pageEntryList);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
