package br.edu.infnet.testes;

import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {

		if(args.length != 1) {
			System.err.println("Entrada inv�lida: n�o conseguimos identificar nenhum valor!");
			return;//TODO criar uma exception
		}
		
		final int QTDE_MAX_VENDAS = 2;	
		final float VALOR_DESCONTO = 0.8f;		
		final int CADASTRAR = 1;
		final int SAIR = 9;
		
		Scanner leitura = new Scanner(System.in);
		
		//TODO Transformar os vetores atuais numa �nica estrutura
		//Venda[] vendas = new Venda[QTDE_MAX_VENDAS];		
		//Venda venda = new Venda("feij�o", 12, 1);
		//Venda vendaArroz = new Venda("arroz", 9, 1);		
		//vendas[0] = venda;
		//vendas[1] = vendaArroz;
		

		String[] descricoes = new String[QTDE_MAX_VENDAS];//obrigat�rio
		int[] quantidades = new int[QTDE_MAX_VENDAS];//1
		float[] precos = new float[QTDE_MAX_VENDAS];//obrigat�rio
		boolean[] descontos = new boolean[QTDE_MAX_VENDAS];//false
		int[] categorias = new int[QTDE_MAX_VENDAS];//obrigat�rio
		float[] valoresVenda = new float[QTDE_MAX_VENDAS];//calculado
		
		float valorTotalVendas = 0;		
		int opcao = 1;
		int i = 0;

		do {
			
			System.out.println("Menu de op��es:");
			System.out.println("1 - Lan�ar venda");
			System.out.println("9 - Sair");
			System.out.printf("Escolha a op��o desejada: ");
			opcao = leitura.nextInt();
			
			switch (opcao) {
			case CADASTRAR:
				if(i >= QTDE_MAX_VENDAS) {
					System.err.println("Limite alcan�ado! Imposs�vel realizar novas vendas!");
					break;
				}
				
				System.out.println("Venda #" + (i+1));
				
				System.out.print("Entre com a descri��o do produto: ");
				descricoes[i] = leitura.next();

				System.out.print("Entre com a quantidade do produto: ");
				quantidades[i] = leitura.nextInt();

				System.out.print("Entre com o pre�o do produto: ");
				precos[i] = leitura.nextFloat();

				System.out.print("Indique se o produto tem desconto (true/false): ");
				descontos[i] = leitura.nextBoolean();
				
				System.out.println("1 - Alimenta��o");
				System.out.println("2 - Limpeza");
				System.out.println("3 - Vestu�rio");
				System.out.println("4 - Papelaria");
				System.out.println("5 - Brinquedo");
				System.out.print("Escolha a categoria do produto: ");
				categorias[i] = leitura.nextInt();
				
				//valida��o da categoria
				if(categorias[i] < 1 || categorias[i] > 5) {
					System.err.println("Categoria inv�lida: "+categorias[i]+"!!");
				} else {
					//defini��o do novo pre�o
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

					//defini��o do valor de venda
					valoresVenda[i] = quantidades[i] * precos[i];
					if(descontos[i]) {
						valoresVenda[i] = valoresVenda[i] * VALOR_DESCONTO;
					}
					
					//defini��o do valor total das vendas
					valorTotalVendas = valorTotalVendas + valoresVenda[i];
				}			
				
				i++;
				break;

			case SAIR:
				System.out.println("Prepara��o para sa�da!");
				break;

			default:
				System.out.println("Op��o inv�lida! Tente novamente!");
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
		System.out.printf("O valor total de vendas realizadas � R$%.2f.", valorTotalVendas);
			
		leitura.close();
	}

}