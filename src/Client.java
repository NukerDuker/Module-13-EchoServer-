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
            //�������� ������ ����� � ������
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            //������� ������� �������� ����� � ������
            in = new Scanner(is);
            out = new PrintStream(os);

            //������ �� ���� � ����� � ����
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
