package br.edu.infnet.testes;

public class CalculoIdadeEmDias {

	public static void main(String[] args) {
		
		final int DIA_ATUAL = 19;
		final int MES_ATUAL = 5;
		final int ANO_ATUAL = 2025;
		
		int diaNascimento = Integer.valueOf(args[0]);
		int mesNascimento = Integer.valueOf(args[1]);
		int anoNascimento = Integer.valueOf(args[2]);

		int diasDeNascimento = calcularDias(diaNascimento, mesNascimento, anoNascimento);
		int diasDeHoje = calcularDias(DIA_ATUAL, MES_ATUAL, ANO_ATUAL);

		int idadeEmDias = diasDeHoje - diasDeNascimento;
		
		System.out.printf("Eu tenho %d dias de idade", idadeEmDias);
	}
		
	private static int calcularDias(int dia, int mes, int ano) {//24/08/1978
		
		final int DIAS_POR_ANO_BISSEXTO = 366;
		final int DIAS_POR_ANO_NORMAL = 365;

		int qtdeDias = 0;
		
		//tratamento dos anos
		for(int i = 0; i < ano; i++) {
			qtdeDias = qtdeDias + (ehBissexto(i) ? DIAS_POR_ANO_BISSEXTO : DIAS_POR_ANO_NORMAL);
		}		
		
		
		//tratamento dos meses
		int[] diasPorMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};	
		
		if(ehBissexto(ano)) {
			diasPorMes[1] = 29;
		}
		
		for(int i = 0;i < mes; i++) {
			qtdeDias = qtdeDias + diasPorMes[i]; 
		}
		
		//tratamento dos dias
		qtdeDias = dia;
		

		return qtdeDias;
	}
	
	private static boolean ehBissexto(int ano) {
		return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
	}
}