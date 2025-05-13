package br.edu.infnet.testes;

import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {

		if(args.length != 1) {
			System.err.println("Entrada inválida: não conseguimos identificar nenhum valor!");
			return;//TODO criar uma exception
		}
		
		final int QTDE_MAX_VENDAS = 2;	
		final float VALOR_DESCONTO = 0.8f;		
		final int CADASTRAR = 1;
		final int SAIR = 9;
		
		Scanner leitura = new Scanner(System.in);
		
		//TODO Transformar os vetores atuais numa única estrutura
		//Venda[] vendas = new Venda[QTDE_MAX_VENDAS];		
		//Venda venda = new Venda("feijão", 12, 1);
		//Venda vendaArroz = new Venda("arroz", 9, 1);		
		//vendas[0] = venda;
		//vendas[1] = vendaArroz;
		

		String[] descricoes = new String[QTDE_MAX_VENDAS];//obrigatório
		int[] quantidades = new int[QTDE_MAX_VENDAS];//1
		float[] precos = new float[QTDE_MAX_VENDAS];//obrigatório
		boolean[] descontos = new boolean[QTDE_MAX_VENDAS];//false
		int[] categorias = new int[QTDE_MAX_VENDAS];//obrigatório
		float[] valoresVenda = new float[QTDE_MAX_VENDAS];//calculado
		
		float valorTotalVendas = 0;		
		int opcao = 1;
		int i = 0;

		do {
			
			System.out.println("Menu de opções:");
			System.out.println("1 - Lançar venda");
			System.out.println("9 - Sair");
			System.out.printf("Escolha a opção desejada: ");
			opcao = leitura.nextInt();
			
			switch (opcao) {
			case CADASTRAR:
				if(i >= QTDE_MAX_VENDAS) {
					System.err.println("Limite alcançado! Impossível realizar novas vendas!");
					break;
				}
				
				System.out.println("Venda #" + (i+1));
				
				System.out.print("Entre com a descrição do produto: ");
				descricoes[i] = leitura.next();

				System.out.print("Entre com a quantidade do produto: ");
				quantidades[i] = leitura.nextInt();

				System.out.print("Entre com o preço do produto: ");
				precos[i] = leitura.nextFloat();

				System.out.print("Indique se o produto tem desconto (true/false): ");
				descontos[i] = leitura.nextBoolean();
				
				System.out.println("1 - Alimentação");
				System.out.println("2 - Limpeza");
				System.out.println("3 - Vestuário");
				System.out.println("4 - Papelaria");
				System.out.println("5 - Brinquedo");
				System.out.print("Escolha a categoria do produto: ");
				categorias[i] = leitura.nextInt();
				
				//validação da categoria
				if(categorias[i] < 1 || categorias[i] > 5) {
					System.err.println("Categoria inválida: "+categorias[i]+"!!");
				} else {
					//definição do novo preço
					switch (categorias[i]) {
					case 1:
						precos[i] = precos[i] * 1.05f;
						break;
					case 2:
						precos[i] = precos[i] * 1.08f;
						break;
					case 3:
						precos[i] = precos[i] * 1.10f;
						break;
					case 4:
					case 5:
						precos[i] = precos[i] * 1.06f;
						break;

					default:
						break;
					}

					//definição do valor de venda
					valoresVenda[i] = quantidades[i] * precos[i];
					if(descontos[i]) {
						valoresVenda[i] = valoresVenda[i] * VALOR_DESCONTO;
					}
					
					//definição do valor total das vendas
					valorTotalVendas = valorTotalVendas + valoresVenda[i];
				}			
				
				i++;
				break;

			case SAIR:
				System.out.println("Preparação para saída!");
				break;

			default:
				System.out.println("Opção inválida! Tente novamente!");
				break;
			}
			
		} while (opcao != 9);
		
		int qtdeVenda = i;
		
		opcao = 10;
		
		for(int j = 0; j < qtdeVenda; j++) {
			System.out.printf("Produto: %s; qtde: %d; R$ %.2f; desconto: %s\nTotal da venda: %.2f\n\n",
					descricoes[j], quantidades[j], precos[j], descontos[j], valoresVenda[j]
				);
		}
		
		//imprimir o total de todas as vendas realizadas
		System.out.printf("O valor total de vendas realizadas é R$%.2f.", valorTotalVendas);
			
		leitura.close();
	}

}