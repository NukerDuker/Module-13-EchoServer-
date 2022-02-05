import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    Scanner in;
    PrintStream out;
    Socket socket;
    ChatServer server;

    public Client (Socket socket, ChatServer server) {
        this.socket = socket;
        new Thread(this).start();
        this.server = server;
    }

    public void receive(String message) {
        out.println(message);
    }
    public void run() {
        try{
            //получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //Создаем удобные средства ввода и вывода
            in = new Scanner(is);
            out = new PrintStream(os);

            //читаем из сети и пишем в сеть
            out.println("Welcome, friend!");
            String input = in.nextLine();
            while (!(input.equals("bye"))) {
                server.sendAll(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
