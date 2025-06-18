package br.edu.infnet.politica.model;

import br.edu.infnet.politica.model.superclass.Politico;

public class Vereador extends Politico {

	@Override
	public void fazerDiscurso() {
		System.out.println("(Vereador) defente melhorias no transporte público!");
	}

	@Override
	public double calcularSalario() {

		return 8000;
	}
}