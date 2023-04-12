package Client;
import java.io.*;
import java.net.Socket;
public class Client {
    private String host = "localhost";
    private int port = 8989;
    public Client() throws IOException {
        try (Socket clientSocket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             ) {

            System.out.println("Введите слово для поиска:");
            String word = reader.readLine();
            out.write(word + "\n");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        Client client = new Client();
    }
}
