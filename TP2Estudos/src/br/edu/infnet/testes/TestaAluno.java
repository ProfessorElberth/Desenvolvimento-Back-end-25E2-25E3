package br.edu.infnet.testes;

import br.edu.infnet.model.Aluno;

public class TestaAluno {

	public static void main(String[] args) {
		
		Aluno a1 = new Aluno("maria", 123);
		Aluno a2 = new Aluno("joao", 234);
		Aluno a3 = new Aluno("ana", 345);
		
		Aluno[] alunos = new Aluno[3];
		alunos[0] = a1;
		alunos[1] = a2;
		alunos[2] = a3;
		
		for(Aluno a : alunos) {
			System.out.println(a);			
		}
	}
}