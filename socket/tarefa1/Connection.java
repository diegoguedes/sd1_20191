package tarefa1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.net.Socket;

import javax.swing.SwingConstants;

class Connection extends Thread {
	ObjectInputStream in;
	ObjectOutputStream out;
	Socket clientSocket;

	public Connection(Socket aClientSocket) {
		try {
			clientSocket = aClientSocket;
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());

			this.start();
		} catch (IOException e) {
			System.out.println("Connection:" + e.getMessage());
		}
	}

	public void run() {
		try { // an echo server
			Mensagem mensagemChegada;
			Mensagem mensagemSaida = null;
			try {
				mensagemChegada = (Mensagem) in.readObject();
				mensagemSaida = new Mensagem();
				mensagemSaida.setOperacao(mensagemChegada.getOperacao());

				switch (mensagemChegada.getOperacao()) {
				case SOMA:
					mensagemSaida.adicionarArgumento(((BigDecimal) mensagemChegada.getArgumento(0))
							.add((BigDecimal) mensagemChegada.getArgumento(1)));
					break;
				case MULTIPLICACAO:
					mensagemSaida.adicionarArgumento(((BigDecimal) mensagemChegada.getArgumento(0))
							.multiply((BigDecimal) mensagemChegada.getArgumento(1)));
					break;

				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			out.writeObject(mensagemSaida);
		} catch (EOFException e) {
			System.out.println("EOF:" + e.getMessage());
		} catch (IOException e) {
			System.out.println("IO:" + e.getMessage());
		} finally {
			
		}
	}
}