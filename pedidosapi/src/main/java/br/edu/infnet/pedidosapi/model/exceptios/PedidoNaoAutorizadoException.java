package br.edu.infnet.pedidosapi.model.exceptios;

public class PedidoNaoAutorizadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PedidoNaoAutorizadoException(String mensagem) {
		super(mensagem);
	}
}