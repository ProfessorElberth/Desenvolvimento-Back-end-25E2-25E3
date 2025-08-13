package br.edu.infnet.elberthapi;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.elberthapi.model.domain.Vendedor;

public class VendedorLoader {

	public static void main(String[] args) {
		
		List<Vendedor> lista = new ArrayList<Vendedor>();

		lista.add(new Vendedor("vend1", 123, 100, true));
		lista.add(new Vendedor("vend2", 123, 100, true));
		lista.add(new Vendedor("vend3", 123, 100, true));
		
		//TRADICIONAL
		for(Vendedor v : lista) {
			System.out.println(v);
		}
		
		//MODERNO
		lista.forEach(System.out::println);
	}
}
