package br.edu.infnet.testes;

import java.util.Scanner;

import br.edu.infnet.config.CategoriaProduto;
import br.edu.infnet.config.Constante;
import br.edu.infnet.model.Venda;

public class GestaoVenda {

	private static Venda[] vendas;
	private static float valorTotalVendas;		
	private static Scanner leitura;
	private static int qtdeVenda;
	
	private static boolean categoriaValida(int categoria){
		return categoria < 1 || categoria > 5;
	}

	private static void cadastrarVenda(){
		if(qtdeVenda >= Constante.QTDE_MAX_VENDAS) {
			System.err.println("Limite alcançado! Impossível realizar novas vendas!");
			return;
		}

		String descricao = null;
		float preco = 0;	
		int codigo = 0;
		boolean desconto = false;
		int quantidade = 0;
						
		System.out.println("Venda #" + (qtdeVenda+1));
		
		System.out.print("Entre com a descrição do produto: ");
		descricao = leitura.next();

		System.out.print("Entre com a quantidade do produto: ");
		quantidade = leitura.nextInt();

		System.out.print("Entre com o preço do produto: ");
		preco = leitura.nextFloat();

		System.out.print("Indique se o produto tem desconto (true/false): ");
		desconto = leitura.nextBoolean();
		
		for(CategoriaProduto categoria : CategoriaProduto.values()) {
			System.out.println(categoria);
		}
		System.out.print("Escolha a categoria do produto: ");
		codigo = leitura.nextInt();
		
		if(categoriaValida(codigo)) {
			System.err.println("Categoria inválida: "+codigo+"!!");
		} else {
			
			CategoriaProduto categoria = CategoriaProduto.obterPorCodigo(codigo); 

			Venda venda = new Venda(descricao, preco, categoria);
			venda.setDesconto(desconto);
			venda.setQuantidade(quantidade);
			
			vendas[qtdeVenda] = venda;
		
			valorTotalVendas = valorTotalVendas + venda.calcularValorVenda();
		}			
		
		qtdeVenda++;		
	}
	
	private static void impressaoVendas(){

		for(int j = 0; j < qtdeVenda; j++) {
			System.out.println(vendas[j]);
		}
		
		System.out.printf("O valor total de vendas realizadas é R$%.2f.", valorTotalVendas);		
	}

	private static int obterMenu(){
		System.out.println("Menu de opções:");
		System.out.println("1 - Lançar venda");
		System.out.println("2 - Imprimir vendas");
		System.out.println("9 - Sair");
		System.out.printf("Escolha a opção desejada: ");

		return leitura.nextInt();
	}
	
	public static void main(String[] args) {

		if(args.length != 1) {
			System.err.println("Entrada inválida: não conseguimos identificar nenhum valor!");
			return;
		}
		
		leitura = new Scanner(System.in);
		
		vendas = new Venda[Constante.QTDE_MAX_VENDAS];		

		int opcao = 1;
		
		do {
			
			opcao = obterMenu();
			
			switch (opcao) {
			case Constante.CADASTRAR:				
				cadastrarVenda();
				break;

			case Constante.IMPRIMIR:
				impressaoVendas();
				break;
				
			case Constante.SAIR:
				System.out.println("Preparação para saída!");
				break;

			default:
				System.out.println("Opção inválida! Tente novamente!");
				break;
			}
			
		} while (opcao != 9);

		leitura.close();
	}

}