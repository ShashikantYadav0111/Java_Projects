package org.server;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        ) {
            // Read the request line (e.g., GET / HTTP/1.1)
            String requestLine = in.readLine();
            System.out.println("Request: " + requestLine);

            if (requestLine == null || requestLine.isEmpty()) return;

            // Parse the request line

            String[] tokens = requestLine.split(" ");
            String method = tokens[0];
            String path = tokens[1];
            System.out.println(path);

            if ("/".equals(path)) {
                path = "/index.html";

            }
            else if("/about".equals(path)) {
                path = "/about.html";
            }
            Frontend frontend = new Frontend(path,clientSocket);
            frontend.serveResolver();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException ignore) {}
        }
    }
}
