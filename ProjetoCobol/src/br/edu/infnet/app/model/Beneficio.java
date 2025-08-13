package br.edu.infnet.app.model;

public class Beneficio {

	private String nuNB;
	private int csEspecie;
	private int cdSituacaoBenef;
	private String d2DIB;
	private String d2DCB;
	
	public Beneficio(String nuNB, int csEspecie, int cdSituacaoBenef, String d2dib, String d2dcb) {
		this.nuNB = nuNB;
		this.csEspecie = csEspecie;
		this.cdSituacaoBenef = cdSituacaoBenef;
		d2DIB = d2dib;
		d2DCB = d2dcb;
	}

	public String getNuNB() {
		return nuNB;
	}

	public int getCsEspecie() {
		return csEspecie;
	}

	public int getCdSituacaoBenef() {
		return cdSituacaoBenef;
	}

	public String getD2DIB() {
		return d2DIB;
	}

	public String getD2DCB() {
		return d2DCB;
	}
}