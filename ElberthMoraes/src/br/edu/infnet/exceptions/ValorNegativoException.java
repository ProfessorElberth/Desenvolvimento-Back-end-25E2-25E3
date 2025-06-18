package br.edu.infnet.exceptions;

public class ValorNegativoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public ValorNegativoException(String mensagem) {
		super(mensagem);
	}
}
