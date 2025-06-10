package br.edu.infnet.politica.testes;

import br.edu.infnet.politica.model.Deputado;
import br.edu.infnet.politica.model.Governador;
import br.edu.infnet.politica.model.Vereador;
import br.edu.infnet.politica.model.superclass.Politico;

public class TestaPolitico {

	public static void main(String[] args) {
		
		Politico[] politicos = new Politico[3];
		
		Vereador ze = new Vereador();
		ze.setNome("Ze");
		ze.setPartido("PZ");		
		politicos[0] = ze;

		Deputado maria = new Deputado("Maria", "PM");		
		politicos[1] = maria;
		
		Governador joao = new Governador();
		joao.setNome("Joao");
		joao.setPartido("PJ");		
		politicos[2] = joao;
		
		for(Politico politico : politicos) {
			System.out.println(politico);
			politico.imprimir();
			politico.fazerDiscurso();
			System.out.println();
		}
	}
}