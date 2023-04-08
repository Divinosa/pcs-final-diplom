import java.io.*;
import java.net.Socket;

public class Client {


    String host = "localhost";
    int port = 8989;
    public Client() throws IOException {
        try (Socket socket = new Socket(host,port);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
            out.println("еще");
            String answer = in.readLine();
            System.out.println(answer);
        }

    }
}
