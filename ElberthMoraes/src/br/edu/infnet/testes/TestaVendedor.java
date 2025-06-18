package br.edu.infnet.testes;

import br.edu.infnet.exceptions.CpfInvalidoException;
import br.edu.infnet.exceptions.ValorNegativoException;
import br.edu.infnet.model.Vendedor;

public class TestaVendedor {

	public static void main(String[] args) {
		
		try {
			try {
				Vendedor meuVendedor = new Vendedor("12312312312", "Elberth", "elberth@moraes.com", 10);
				meuVendedor.imprimir();
				
				Vendedor outroVendedor = new Vendedor("1231231231", "Elberth", "elberth@moraes.com", 10);
				outroVendedor.imprimir();
				
			} catch (ValorNegativoException e1) {
				System.out.println("Deu ruim");
				Vendedor meuVendedor = new Vendedor("12312312312", "Elberth", "elberth@moraes.com", 0);
				meuVendedor.imprimir();
			} catch (CpfInvalidoException e2) {
				System.out.println(e2.getMessage());
			}
			

			Vendedor vendedor = null;
			vendedor = new Vendedor();
			
			vendedor.registrarVenda(100);
			vendedor.registrarVenda(300);
			vendedor.registrarVenda(500);
			vendedor.registrarVenda(700);
			vendedor.registrarVenda(900);
			
			System.out.println("Valor total de venda: " + vendedor.getTotalVenda());
			
			System.out.println(vendedor);
			
			vendedor.imprimir();
			
		} finally {
			System.out.println("Cheguei aqui!!!");
		}
		
	}
}