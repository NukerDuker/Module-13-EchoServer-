import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ChatServer implements Runnable{
    ArrayList<Client> clients = new ArrayList<>();
    ServerSocket serverSocket;

    public ChatServer() throws IOException {
        //создаем серверный сокет на порту 1234
        serverSocket = new ServerSocket( 1234);
    }

    @Override
    public void run() {
        while (true){
            System.out.println("Waiting");
            try {
                //ждем клиента из сети
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");
                //создаем клиента из сети
                clients.add(new Client(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}
