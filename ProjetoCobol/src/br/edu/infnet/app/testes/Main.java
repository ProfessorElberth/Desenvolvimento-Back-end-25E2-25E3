package br.edu.infnet.app.testes;

import br.edu.infnet.app.service.ProcessadorBeneficios;

public class Main {

	public static void main(String[] args) {
		
		String caminhoEntrada = "./data/";
		
		String caminhoSaida = caminhoEntrada+"ARQSAI.csv";

		new ProcessadorBeneficios().execute(caminhoEntrada, caminhoSaida);
		
		System.out.println("Processamento realizado com sucesso!!!");
	}
}