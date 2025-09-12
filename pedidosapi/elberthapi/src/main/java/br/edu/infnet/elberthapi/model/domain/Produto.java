package br.edu.infnet.elberthapi.model.domain;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tprodutos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
	
	private String codigo;
	
	private String nome;
	
	@NotNull(message = "O tipo do produto é obrigatório.")
	@Enumerated(EnumType.STRING)
	private TipoProduto tipo;
	
	@NotNull(message = "O valor  do produto é obrigatório.")
	@DecimalMin(value = "0.01", message = "O valor do produto deve ser maior que zero.")
	@Digits(integer = 10, fraction = 2, message = "O valor do produto deve ter no máximo 10 digitos inteiros e 2 decimais.")
	private BigDecimal valor;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "vendedor_id", nullable = false)
	private Vendedor vendedor;
	
	@Override
	public String toString() {
	    return String.format(
	        "Produto {id=%d, codigo='%s', nome='%s', tipo='%s', valor=%s, vendedor=%s}",
	        id,
	        codigo,
	        nome,
	        tipo != null ? tipo.name() : "N/A",
	        valor != null ? valor.toPlainString() : "N/A"
	        , vendedor != null ? String.format("%d - %s", vendedor.getId(), vendedor.getNome()) : "N/A"
	    );
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoProduto getTipo() {
		return tipo;
	}

	public void setTipo(TipoProduto tipo) {
		this.tipo = tipo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
}
