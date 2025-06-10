package br.edu.infnet.politica.model;

import br.edu.infnet.politica.model.superclass.Politico;

public class Governador extends Politico {

	
	@Override
	public String toString() {

		return "Governador: " + super.toString();
	}

	@Override
	public void fazerDiscurso() {
		System.out.println("(Governador) anuncia plano de segurança!");
	}
}
