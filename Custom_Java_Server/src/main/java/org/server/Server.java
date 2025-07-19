package org.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class    Server {
    public static void main(String[] args)  {
        int port = 8081;
        try(ServerSocket serverSocket= new ServerSocket(port)){
            System.out.println("Server Started on Port:"+serverSocket);

            while(true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client Connected:"+clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket)).start();
            }
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}