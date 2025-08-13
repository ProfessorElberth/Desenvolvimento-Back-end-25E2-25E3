package br.edu.infnet.elberthapi.model.exceptions;

public class BonusInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BonusInvalidoException(String mensagem) {
		super(mensagem);
	}
}
