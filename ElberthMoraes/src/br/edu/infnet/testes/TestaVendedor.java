package br.edu.infnet.testes;

import br.edu.infnet.model.Vendedor;

public class TestaVendedor {

	public static void main(String[] args) {
		
		Vendedor vendedor = new Vendedor();
		
		vendedor.registrarVenda(100);
		vendedor.registrarVenda(300);
		vendedor.registrarVenda(500);
		vendedor.registrarVenda(700);
		vendedor.registrarVenda(900);
		
		System.out.println("Valor total de venda: " + vendedor.getTotalVenda());
		
		System.out.println(vendedor);
		
		vendedor.imprimir();
	}
}