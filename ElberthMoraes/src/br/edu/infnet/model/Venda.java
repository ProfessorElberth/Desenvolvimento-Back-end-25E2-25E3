package br.edu.infnet.model;

import br.edu.infnet.config.CategoriaProduto;
import br.edu.infnet.config.Constante;

public class Venda {

	private String descricao;
	private float preco;
	private CategoriaProduto categoriaProduto;
	private int quantidade;
	private boolean desconto;
	
	public Venda(String descricao, float preco, CategoriaProduto categoriaProduto) {
		
		this.preco = categoriaProduto.aplicarFator(preco);
				
		this.descricao = descricao;
		this.categoriaProduto = categoriaProduto;
	}
	
	public float calcularValorVenda(){

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
				categoriaProduto, descricao, quantidade, preco, desconto, calcularValorVenda()
			);
	}


	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getQuantidade(){
		return this.quantidade;
	}

	public void setDesconto(boolean desconto) {
		this.desconto = desconto;
	}
	
	public boolean getDesconto() {
		return this.desconto;
	}
}