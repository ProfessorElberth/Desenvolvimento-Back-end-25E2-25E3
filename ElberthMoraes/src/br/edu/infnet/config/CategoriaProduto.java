package br.edu.infnet.config;

public enum CategoriaProduto {

	ALIMENTACAO(1, "Alimentício", 1.05f), 
	LIMPEZA(2, "Limpeza", 1.08f), 
	VESTUARIO(3, "Vestuário", 1.10f), 
	PAPELARIA(4, "Papelaria", 1.06f), 
	BRINQUEDO(5, "Brinquedo", 1.06f);
	
	private final int codigo;
	private final String descricao;
	private final float fator;
	
	CategoriaProduto(int codigo, String descricao, float fator){
		this.codigo = codigo;
		this.descricao = descricao;
		this.fator = fator;
	}
	
	public float aplicarFator(float preco){
		return preco * fator;
	}
	
	public static CategoriaProduto obterPorCodigo(int codigo){
		for(CategoriaProduto categoria : values()) {
			if(categoria.codigo == codigo) {
				return categoria;
			}
		}
		
		return null;
	}
		
	@Override
	public String toString() {
		return String.format("%d - %s", codigo, descricao);
	}
	
	public int getCodigo(){
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public float getFator() {
		return fator;
	}
}