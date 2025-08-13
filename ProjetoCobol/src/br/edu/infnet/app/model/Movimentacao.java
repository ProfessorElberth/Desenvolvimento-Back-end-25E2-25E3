package br.edu.infnet.app.model;

public class Movimentacao {

	private String nuNB;
	private int cdTipoDigitacao;
	private int csCausaExtincao;
	private String d2CessacaoDep;

	public Movimentacao(String nuNB, int cdTipoDigitacao, int csCausaExtincao, String d2CessacaoDep) {
		this.nuNB = nuNB;
		this.cdTipoDigitacao = cdTipoDigitacao;
		this.csCausaExtincao = csCausaExtincao;
		this.d2CessacaoDep = d2CessacaoDep;
	}

	public String getNuNB() {
		return nuNB;
	}
	public int getCdTipoDigitacao() {
		return cdTipoDigitacao;
	}
	public int getCsCausaExtincao() {
		return csCausaExtincao;
	}
	public String getD2CessacaoDep() {
		return d2CessacaoDep;
	}
}