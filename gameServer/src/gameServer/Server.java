package gameServer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private PrintWriter out;
    private BufferedReader in;
    private PrintWriter outt;
    private BufferedReader inn;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private Socket clientSockett;
    

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        clientSockett = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        outt = new PrintWriter(clientSockett.getOutputStream(), true);
        inn = new BufferedReader(new InputStreamReader(clientSockett.getInputStream()));
    }

    public void sendData(String d1, String d2) throws IOException {
        out.println(d2);
        outt.println(d1);

    }

    public void communicate() throws IOException {
 
        while(true){ // gets two strings and exchanges them to update location
            String location1 = in.readLine();
            String location2 = inn.readLine();
            sendData(location1, location2);
        }

    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        inn.close();
        outt.close();
        clientSockett.close();
        serverSocket.close();
    }
    public static void main(String[] args) throws IOException {
        Server server=new Server();
        server.start(8888);
        server.communicate();
        server.stop();
    }
}