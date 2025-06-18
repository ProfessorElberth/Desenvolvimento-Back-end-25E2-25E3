package br.edu.infnet.politica.testes;

import br.edu.infnet.politica.interfaces.Comissionado;
import br.edu.infnet.politica.model.Consultor;
import br.edu.infnet.politica.model.Deputado;
import br.edu.infnet.politica.model.Governador;
import br.edu.infnet.politica.model.Vereador;
import br.edu.infnet.politica.model.superclass.Politico;

public class TestaFuncionario {

	public static void main(String[] args) {
		
		Politico[] politicos = new Politico[4];
		
		Comissionado[] comissionados = new Comissionado[4];
		
		
		Vereador ze = new Vereador();
		ze.setNome("Ze");
		ze.setPartido("PZ");		
		politicos[0] = ze;
		comissionados[0] = ze;

		Deputado maria = new Deputado("Maria", "PM");		
		politicos[1] = maria;
		comissionados[1] = maria;
		
		Governador joao = new Governador();
		joao.setNome("Joao");
		joao.setPartido("PJ");		
		politicos[2] = joao;
		comissionados[2] = joao;
		
		Consultor well = new Consultor();
		//politicos[3] = well;
		comissionados[3] = well;
		
		for(Comissionado comissionado : comissionados) {
			System.out.println(comissionado.calcularSalario());
		}
		
		for(Politico politico : politicos) {
			System.out.println(politico);
			politico.imprimir();
			politico.fazerDiscurso();
			System.out.println("Salário: " +politico.calcularSalario());
			System.out.println();
		}
	}
}