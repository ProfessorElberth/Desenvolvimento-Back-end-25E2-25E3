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

    private static void cadastrarVenda() {
        if (qtdeVenda >= Constante.QTDE_MAX_VENDAS) {
            System.err.println("\n Limite alcançado! Impossível realizar novas vendas!\n");
            return;
        }

        String descricao = null;
        float preco = 0;
        int codigo = 0;
        boolean desconto = false;
        int quantidade = 0;
        String cpf = null;
        String nome = null;
        String email = null;
        double salario = 0;

        System.out.printf("\n=== Cadastro da Venda #%d ===\n", qtdeVenda + 1);

        System.out.print("Descrição do produto: ");
        descricao = leitura.next();

        System.out.print("Quantidade do produto: ");
        quantidade = leitura.nextInt();

        System.out.print("Preço unitário do produto: ");
        preco = leitura.nextFloat();

        System.out.print("O produto tem desconto? (true/false): ");
        desconto = leitura.nextBoolean();

        System.out.println("\nCategorias disponíveis:");
        for (CategoriaProduto categoria : CategoriaProduto.values()) {
            System.out.println("  " + categoria);
        }

        System.out.print("Informe o código da categoria: ");
        codigo = leitura.nextInt();

        CategoriaProduto categoria = CategoriaProduto.obterPorCodigo(codigo);
        
        if(categoria == null) {
        	System.err.printf("Categoria inválida: %d!\n", codigo);
        	return;
        }

        System.out.print("CPF do vendedor: ");
        cpf = leitura.next();
        
        System.out.print("Nome do vendedor: ");
        nome = leitura.next();

        System.out.print("E-mail do vendedor: ");
        email = leitura.next();

        System.out.print("Salário do vendedor: ");
        salario = leitura.nextDouble();
        
        Venda venda = new Venda(descricao, preco, categoria, cpf, nome, email, salario);
        venda.setDesconto(desconto);
        venda.setQuantidade(quantidade);

        vendas[qtdeVenda] = venda;
        valorTotalVendas += venda.calcularValorVenda();

        System.out.println("Venda registrada com sucesso!");


        qtdeVenda++;
        System.out.println();
    }

    private static void impressaoVendas() {
        System.out.println("\n=== RELATÓRIO DE VENDAS ===");

        for (int j = 0; j < qtdeVenda; j++) {
            System.out.println(vendas[j]);
        }

        System.out.printf("\nTotal acumulado de vendas: R$ %.2f\n", valorTotalVendas);
        System.out.println("===========================\n");
    }

    private static int obterMenu() {
        System.out.println("======= MENU DE OPÇÕES =======");
        System.out.println("1 - Lançar nova venda");
        System.out.println("2 - Imprimir vendas");
        System.out.println("9 - Sair do sistema");
        System.out.print("Escolha a opção desejada: ");
        return leitura.nextInt();
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Entrada inválida: argumento não identificado!");
            return;
        }

        leitura = new Scanner(System.in);
        vendas = new Venda[Constante.QTDE_MAX_VENDAS];

        int opcao = 1;

        do {
            System.out.println();
            opcao = obterMenu();
            System.out.println();

            switch (opcao) {
                case Constante.CADASTRAR:
                    cadastrarVenda();
                    break;

                case Constante.IMPRIMIR:
                    impressaoVendas();
                    break;

                case Constante.SAIR:
                    System.out.println("Saindo do sistema. Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente!");
                    break;
            }

        } while (opcao != Constante.SAIR);

        leitura.close();
    }
}
