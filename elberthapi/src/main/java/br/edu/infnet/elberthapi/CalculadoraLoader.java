package br.edu.infnet.elberthapi;

import br.edu.infnet.elberthapi.model.domain.CalculadoraCientifica;

public class CalculadoraLoader {

	public static void main(String[] args) {
		
		
		CalculadoraCientifica cc = new CalculadoraCientifica();
		
		double resultado = cc.add(10, 4);
		
		System.out.println("= " + resultado);
	}
}
