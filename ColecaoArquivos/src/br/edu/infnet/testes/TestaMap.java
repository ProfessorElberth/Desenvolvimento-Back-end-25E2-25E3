package br.edu.infnet.testes;

import java.util.HashMap;
import java.util.Map;

public class TestaMap {

	public static void main(String[] args) {
		
		Map<Integer, Float> mapaValorPorAno = new HashMap<Integer, Float>();
		
		mapaValorPorAno.put(197801, 1000f);
		
		mapaValorPorAno.put(1980, 2000f);
		
		mapaValorPorAno.put(1990, 3000f);
		
		
		System.out.println(mapaValorPorAno.get(197801));
		
		
		Map<Integer, String> mapa;
	}
}