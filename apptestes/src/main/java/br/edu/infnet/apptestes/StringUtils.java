package br.edu.infnet.apptestes;

public class StringUtils {

	public String reverter(String texto) {
	
		if(texto == null) {
			throw new IllegalArgumentException("O texto não pode ser nulo!");
		}
		
		return new StringBuilder(texto).reverse().toString();
	}
	
	 public boolean ehPalindromo(String texto) {
			
		//TODO Trabalhar na exception 
		//if(texto == null) {
		//	throw new IllegalArgumentException("O texto não pode ser nulo!");
		//}
		
		String textoInvertido = new StringBuilder(texto).reverse().toString();
		
		return textoInvertido.equalsIgnoreCase(texto);
	 }
}