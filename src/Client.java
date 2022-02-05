import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    Socket socket;

    public Client (Socket socket) {
        this.socket = socket;
        new Thread(this).start();
    }
    public void run() {
        try{
            //получаем потоки ввода и вывода
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //Создаем удобные средства ввода и вывода
            Scanner in = new Scanner(is);
            PrintStream out = new PrintStream(os);

            //читаем из сети и пишем в сеть
            out.println("Welcome, friend!");
            String input = in.nextLine();
            while (!(input.equals("bye"))) {
                out.println(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
