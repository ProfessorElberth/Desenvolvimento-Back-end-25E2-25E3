package br.edu.infnet.testes;

import br.edu.infnet.model.Isaac;

public class TesteDoIsaac {

	public static void main(String[] args) {
		
		Isaac.disse();
		
		Isaac i = new Isaac("Elberth");
		i.falou(500);

		Isaac.disse();

		Isaac m = new Isaac("Maria");
		m.falou(1000);
		
		Isaac.disse();
	}
}