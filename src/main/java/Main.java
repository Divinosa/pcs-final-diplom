import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main extends Thread {
    public static void main(String[] args) throws Exception {
        Path relPath = Paths.get("pdfs/SoftSkills.pdf");
        Path absPath = relPath.toAbsolutePath();
        BooleanSearchEngine engine = new BooleanSearchEngine(absPath.toFile());
        Thread serverStart = new Thread(() -> {
            try (ServerSocket server = new ServerSocket(80);) {
                System.out.println("Сервер запущен ");
                while (true) try (
                        Socket clientSocket = server.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ) {
                    String word = in.readLine();
                    out.writeObject(engine.search(word));
                    out.flush();

                }
            } catch (IOException e) {
                System.out.println("Не могу стартовать сервер");
                e.printStackTrace();
            }
        });
        serverStart.start();
        Client client = new Client();
    }
}

