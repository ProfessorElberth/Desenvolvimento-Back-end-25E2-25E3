package br.edu.infnet.elberthpedidoapi.model.domain;

import java.util.List;

public class LocalidadeQueryResult {

	private String cepConsultado;
	private String municipioOrigem;
	private String ufOrigem;
	private List<String> outrosMunicipiosNaUF;

	public String getCepConsultado() {
		return cepConsultado;
	}
	public void setCepConsultado(String cepConsultado) {
		this.cepConsultado = cepConsultado;
	}
	public String getMunicipioOrigem() {
		return municipioOrigem;
	}
	public void setMunicipioOrigem(String municipioOrigem) {
		this.municipioOrigem = municipioOrigem;
	}
	public String getUfOrigem() {
		return ufOrigem;
	}
	public void setUfOrigem(String ufOrigem) {
		this.ufOrigem = ufOrigem;
	}
	public List<String> getOutrosMunicipiosNaUF() {
		return outrosMunicipiosNaUF;
	}
	public void setOutrosMunicipiosNaUF(List<String> outrosMunicipiosNaUF) {
		this.outrosMunicipiosNaUF = outrosMunicipiosNaUF;
	}
}