package br.edu.infnet.elberthapi.model.domain.exceptions;

public class VendedorInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendedorInvalidoException(String mensagem) {
		super(mensagem);
	}
}
