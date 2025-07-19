package org.server;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Frontend {
    private final String path = "/home/shashikant-yadav/Desktop/Java_Projects/Custom_Java_Server/src/main/java/org/public/";
    private final String page;
    private final Socket clientSocket;

    Frontend(String page,Socket clientSocket){
        this.page = page;
        this.clientSocket = clientSocket;
    }
    public byte[] serveResolver(){
        System.out.println("here");
        byte[] fileContent=null;
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))){
            File file = new File(path + page);
            if (file.exists() && !file.isDirectory()) {
                fileContent = Files.readAllBytes(Paths.get(path + page));
                out.write("HTTP/1.1 200 OK\r\n");
                out.write("Content-Length: " + fileContent.length + "\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("\r\n");
                out.flush();
                clientSocket.getOutputStream().write(fileContent);
                clientSocket.getOutputStream().flush();
            } else {
                out.write("HTTP/1.1 404 Not Found\r\n");
                out.write("Content-Type: text/plain\r\n");
                out.write("\r\n");
                out.write("404 - File Not Found");
                out.flush();
            }
        }catch (IOException  e){
            e.printStackTrace();
        }
        return fileContent;
    }

}
