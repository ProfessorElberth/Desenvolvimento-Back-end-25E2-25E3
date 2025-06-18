package br.edu.infnet.exceptions;

public class CpfInvalidoException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public CpfInvalidoException(String mensagem) {
		super(mensagem);
	}
}
