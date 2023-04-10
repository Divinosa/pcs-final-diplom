package server;
import service.BooleanSearchEngine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    BooleanSearchEngine engine = new BooleanSearchEngine(new File("pdfs\\SoftSkills.pdf"));

    public Server() throws IOException {
        try (ServerSocket server = new ServerSocket(80);) {
            System.out.println("Сервер запущен " + server.getLocalPort());
            while (true) try (
                    Socket clientSocket = server.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ) {
                String word = in.readLine();
                out.writeObject(engine.search(word).toString());
                out.flush();

            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }
}
