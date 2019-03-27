package SocketUDP;

import java.net.*;
import java.io.*;

public class UDPServer {
	public static void main(String args[]) {
		DatagramSocket aSocket = null;
		try {
			aSocket = new DatagramSocket(6789);
			byte[] buffer = new byte[1000];
			String recebido;
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				aSocket.receive(request);
				recebido = new String(request.getData());
				System.out.println("Servidor recebeu " + recebido);

				recebido = "recebemos: " + recebido;
				DatagramPacket reply = new DatagramPacket(recebido.getBytes(), recebido.length(), request.getAddress(),
						request.getPort());
				aSocket.send(reply);
			}
		} catch (SocketException e) {
			System.out.println("Socket: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO: " + e.getMessage());
		} finally {
			if (aSocket != null)
				aSocket.close();
		}
	}
}