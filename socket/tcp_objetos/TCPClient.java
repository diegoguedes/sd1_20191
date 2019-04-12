package tcp;

import java.net.*;
import java.io.*;

public class TCPClient {
	public static void main(String args[]) {
		// arguments supply message and hostname of destination
		Socket s = null;
		Pessoa daniel;
		try {
			int serverPort = 7896;
			daniel = new Pessoa("Daniel",21);
			
			s = new Socket(args[0], serverPort);
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			//ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			out.writeObject(daniel);
			//String data = in.readUTF();
			//System.out.println("Received: " + data);
		} catch (UnknownHostException e) {
			System.out.println("Sock:" + e.getMessage());
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			if (s != null)
				try {
					s.close();
				} catch (IOException e) {
					System.out.println("close:" + e.getMessage());
				}
		}
	}
}