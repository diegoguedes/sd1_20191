package tarefa1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mensagem implements Serializable {
	private TipoOperacao operacao;
	private List<Object> argumentos;

	public Mensagem() {
		argumentos = new ArrayList<>();
	}

	public Mensagem(TipoOperacao operacao, List<Object> argumentos) {
		this.operacao = operacao;
		this.argumentos = argumentos;
	}

	public TipoOperacao getOperacao() {
		return operacao;
	}

	public void setOperacao(TipoOperacao operacao) {
		this.operacao = operacao;
	}

	public List<Object> getArgumentos() {
		return argumentos;
	}
	public Object getArgumento(int posicao) {
		return argumentos.get(posicao);
	}

	public void setArgumentos(List<Object> argumentos) {
		this.argumentos = argumentos;
	}
	
	public void adicionarArgumento(Object argumento){
		this.argumentos.add(argumento);
	}

}
