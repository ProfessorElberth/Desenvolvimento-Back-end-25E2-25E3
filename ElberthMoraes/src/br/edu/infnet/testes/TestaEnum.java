package br.edu.infnet.testes;

import java.util.Scanner;

import br.edu.infnet.config.CategoriaProduto;

public class TestaEnum {

	public static void main(String[] args) {

		Scanner leitura = new Scanner(System.in);
		
		for(CategoriaProduto categoria : CategoriaProduto.values()) {
			System.out.println(categoria);
		}
		System.out.print("Escolha a categoria do produto: ");
		int codigo = leitura.nextInt();

		CategoriaProduto categoria = CategoriaProduto.obterPorCodigo(codigo); 
		
		System.out.println(categoria.getCodigo());//c�digo
		System.out.println(categoria.getDescricao());//descri��o
		
		leitura.close();
		
		System.out.println("FIM");
	}
}
