package br.edu.infnet.elberthapi.model.domain.exceptions;

public class VendedorNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendedorNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}
