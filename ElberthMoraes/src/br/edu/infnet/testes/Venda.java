package br.edu.infnet.testes;

public class Venda {

	private String descricao;
	int quantidade;
	private float preco;
	boolean desconto;
	private int categoria;

	public Venda(String descricao, float preco, int categoria) {
		
		this.preco = atualizarPrecoPorCategoria(categoria, preco);
				
		this.descricao = descricao;
		this.categoria = categoria;
	}
	
	private float atualizarPrecoPorCategoria(int categoria, float preco){
		switch (categoria) {
		case 1:
			return preco * 1.05f;

		case 2:
			return preco * 1.08f;

		case 3:
			return preco * 1.10f;

		case 4:
		case 5:
			return preco * 1.06f;
		}

		return 0;
	}
	
	float calcularValorVenda(){

		float valorVenda = 0;	

		valorVenda = this.quantidade * this.preco;					
		if(desconto) {
			valorVenda = valorVenda * Constante.VALOR_DESCONTO;
		}

		return valorVenda;
	}
	
	@Override
	public String toString() {
		
		return String.format("Produto (%s): %s; qtde: %d; R$ %.2f; desconto: %s\nTotal da venda: %.2f\n\n",
				categoria, descricao, quantidade, preco, desconto, calcularValorVenda()
			);
	}
}
