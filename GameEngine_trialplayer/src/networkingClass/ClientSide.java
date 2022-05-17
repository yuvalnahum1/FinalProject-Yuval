package networkingClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientSide {
	
	
    private PrintWriter outside;
    private BufferedReader inside;
    private Socket Socket;

   
    public String sendMessage(String message) throws IOException {
        outside.println(message);
        String response = inside.readLine();
        return response;
    }
    
        public void stopConnection() throws IOException {
        inside.close();
        outside.close();
        Socket.close();
    }
 public ClientSide(String ip, int port) throws UnknownHostException, IOException {
        Socket = new Socket(ip, port);
        outside= new PrintWriter(Socket.getOutputStream(), true);
        inside = new BufferedReader(new InputStreamReader(Socket.getInputStream()));
    }



}