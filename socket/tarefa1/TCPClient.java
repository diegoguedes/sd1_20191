package tarefa1;

import java.net.*;
import java.io.*;
import java.math.BigDecimal;

public class TCPClient {
	public static void main(String args[]) throws ClassNotFoundException {
		// arguments supply message and hostname of destination
		Socket s = null;
		Mensagem mensagem;

		try {
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);
			
			mensagem = new Mensagem();
			mensagem.setOperacao(TipoOperacao.SOMA);
			mensagem.adicionarArgumento(new BigDecimal(2));
			mensagem.adicionarArgumento(new BigDecimal(3));

			
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());

			out.writeObject(mensagem);

			mensagem = (Mensagem) in.readObject();
			
			System.out.println("O resultado foi " + mensagem.getArgumento(0));
			
			mensagem = new Mensagem();
			mensagem.setOperacao(TipoOperacao.MULTIPLICACAO);
			mensagem.adicionarArgumento(new BigDecimal(2));
			mensagem.adicionarArgumento(new BigDecimal(3));			
			
			s = new Socket("localhost", serverPort);
			in = new ObjectInputStream(s.getInputStream());
			out = new ObjectOutputStream(s.getOutputStream());

			out.writeObject(mensagem);

			mensagem = (Mensagem) in.readObject();

			System.out.println("O resultado foi " + mensagem.getArgumento(0));

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