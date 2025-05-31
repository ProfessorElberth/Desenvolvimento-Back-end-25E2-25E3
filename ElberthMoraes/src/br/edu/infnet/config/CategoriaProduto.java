package br.edu.infnet.config;

public enum CategoriaProduto {

	ALIMENTACAO(1, 1.05f), 
	LIMPEZA(2, 1.08f), 
	VESTUARIO(3, 1.10f), 
	PAPELARIA(4, 1.06f), 
	BRINQUEDO(5, 1.06f);
	
	private final int codigo;
	private final float fator;
	
	CategoriaProduto(int codigo, float fator){
		this.codigo = codigo;
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
		
	public int getCodigo(){
		return codigo;
	}
	
	public float getFator() {
		return fator;
	}
}