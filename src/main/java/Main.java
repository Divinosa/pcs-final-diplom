import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {
        Path relPath = Paths.get("pdfs/SoftSkills.pdf");
        Path absPath = relPath.toAbsolutePath();
        BooleanSearchEngine engine = new BooleanSearchEngine(absPath.toFile());
        try (ServerSocket server = new ServerSocket(8989);) {
            System.out.println("Сервер запущен " + server.getLocalPort());
            while (true) {
                try (
                        Socket clientSocket = server.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                ) {
                    String word = in.readLine();
                    Gson gson = new GsonBuilder()
                            .setPrettyPrinting()
                            .create();
                    out.write(gson.toJson(engine.search(word)));
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}

