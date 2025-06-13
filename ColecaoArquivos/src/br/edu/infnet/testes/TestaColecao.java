package br.edu.infnet.testes;

import java.util.ArrayList;
import java.util.List;

public class TestaColecao {

	public static void main(String[] args) {
		
		float[] salarios = new float[4];
		salarios[0] = 100;
		salarios[1] = 100;
		salarios[2] = 100;
		salarios[3] = 100;
		
		String[] nomes = {"elberth", "lins", "costa", "moraes"};
		int[] idades = {10, 20, 30, 40};
				
		System.out.println(nomes[2]);

		nomes[2] = "maria";

		System.out.println(nomes[2]);
		
		for(String nome : nomes) {
			System.out.println("- " + nome);
		}
		
		List<Integer> listaIdades = new ArrayList<Integer>();		
		listaIdades.add(10);
		listaIdades.add(20);
		listaIdades.add(30);
		listaIdades.add(40);
		
		System.out.println(listaIdades.get(1));
		
		listaIdades.add(40);
		
		for(Integer idade : listaIdades) {
			System.out.println(". " + idade);
		}

		List<String> listaNomes = new ArrayList<String>();
		
		listaNomes.add("joao");
		listaNomes.add("maria");
		
		for(String nome : listaNomes) {
			System.out.println(". " + nome);
		}

	}
}
