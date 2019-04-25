package tarefa1;

import java.net.*;
import java.io.*;
public class TCPServer {
    public static void main (String args[]) {
	try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
		listenSocket.setSoTimeout(0);
		while(true) {
			Socket clientSocket = listenSocket.accept();
			Connection c = new Connection(clientSocket);
		}
	} catch(IOException e) {System.out.println("Listen :"+e.getMessage());}
    }
}

// this figure continues on the next slide