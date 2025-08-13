package br.edu.infnet.app.model;

import java.util.List;

public class Dependente {

	private String nuNB;
	private int qtDepCadastro;
	private List<Integer> csCausaExtincao;

	public Dependente(String nuNB, int qtDepCadastro, List<Integer> csCausaExtincao) {
		this.nuNB = nuNB;
		this.qtDepCadastro = qtDepCadastro;
		this.csCausaExtincao = csCausaExtincao;
	}

	public String getNuNB() {
		return nuNB;
	}
	public int getQtDepCadastro() {
		return qtDepCadastro;
	}
	public List<Integer> getCsCausaExtincao() {
		return csCausaExtincao;
	}
}