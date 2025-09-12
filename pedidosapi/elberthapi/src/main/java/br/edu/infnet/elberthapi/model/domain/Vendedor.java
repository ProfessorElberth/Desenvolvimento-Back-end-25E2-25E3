package br.edu.infnet.elberthapi.model.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
public class Vendedor extends Pessoa {

    @NotNull(message = "A matrícula é obrigatória.")
    @Min(value = 1, message = "A matrícula deve ser um número positivo.")
    private int matricula;
    
    @NotNull(message = "O salário é obrigatório.")
    @Min(value = 0, message = "O salário não pode ser negativo.")
    private double salario;
    
    private boolean ativo;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id")
    @Valid
    private Endereco endereco;
    
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Produto> produtos = new ArrayList<Produto>();

    @Override
    public String toString() {
        return String.format("Vendedor{%s, matricula=%d, salario=%.2f, ehAtivo=%s, %s}", 
                super.toString(), matricula, salario, ativo ? "ativo" : "inativo", endereco);
    }

    @Override
    public String obterTipo() {
        return "Vendedor";
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}