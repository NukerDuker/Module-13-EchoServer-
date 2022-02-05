import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {

    public static void main(String[] args) throws IOException {
        //создаем серверный сокер на порту 1234
        ServerSocket server = new ServerSocket(1234);
        System.out.println("Waiting");

        //ждем клиента
        Socket s = server.accept();
        System.out.println("Client connected!");

        //получаем потоки ввода и вывода
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        //Создаем удобные средства ввода и вывода
        Scanner in = new Scanner(is);
        PrintStream out = new PrintStream(os);

        //читаем из сети и пишем в сеть
        out.println("Welcome, friend!");
        String input = in.nextLine();
        while (!(input.equals("bye"))) {
            out.println(input + "-" + input + "-" + input.substring(input.length()/2) + "...");
            input = in.nextLine();
        }
    }
}
