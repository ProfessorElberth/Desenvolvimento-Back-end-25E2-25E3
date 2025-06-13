package br.edu.infnet.testes;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Gestao {

	public static void main(String[] args) {
		
		try {
			FileReader arquivo = new FileReader("vendedor.txt");
			BufferedReader leitura = new BufferedReader(arquivo);

			String linha = leitura.readLine();
			
			String[] campos = null;
			
			List<Vendedor> vendedores = new ArrayList<Vendedor>();
			
			while(linha != null) {
				
				campos = linha.split(",");
				
				Vendedor vendedor = new Vendedor();				
				vendedor.setCpf(campos[0]);
				vendedor.setEmail(campos[1]);
				vendedor.setNome(campos[2]);
				vendedor.setSalario(Double.valueOf(campos[3]));

				vendedores.add(vendedor);

				linha = leitura.readLine();
			}
			
			for(Vendedor vendedor : vendedores) {
				System.out.println(vendedor);
			}

			leitura.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFound" + e.getMessage());

		} catch (IOException e) {
			System.out.println("IO" + e.getMessage());
		}		
		
		System.out.println("Fim");
	}
}
