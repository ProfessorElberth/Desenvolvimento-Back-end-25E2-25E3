package br.edu.infnet.elberthapi.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotBlank(message = "O CEP é obrigatório.")
    @Pattern(regexp = "\\d{5}-\\d{3}", message = "CEP inválido. Use o formato XXXXX-XXX.")
    private String cep; 

    @NotBlank(message = "O logradouro é obrigatório.")
    @Size(min = 3, max = 100, message = "Logradouro deve ter entre 3 e 100 caracteres.")
    private String logradouro; 

    private String complemento;

    private String unidade;

    @NotBlank(message = "O bairro é obrigatório.")
    @Size(min = 3, max = 50, message = "Bairro deve ter entre 3 e 50 caracteres.")
    private String bairro; 

    @NotBlank(message = "A localidade é obrigatória.")
    @Size(min = 3, max = 50, message = "Localidade deve ter entre 3 e 50 caracteres.")
    private String localidade; 

    @NotBlank(message = "A UF é obrigatória.")
    @Size(min = 2, max = 2, message = "UF deve ter 2 caracteres.")
    private String uf; 

    @NotBlank(message = "O estado é obrigatório.")
    @Size(min = 3, max = 50, message = "Estado deve ter entre 3 e 50 caracteres.")
    private String estado;

    @Override
    public String toString() {
         return "Endereco{" +
                   "id=" + id +
                   ", cep='" + cep + '\'' +
                   ", logradouro='" + logradouro + '\'' +
                   ", complemento='" + complemento + '\'' +
                   ", unidade='" + unidade + '\'' +
                   ", bairro='" + bairro + '\'' +
                   ", localidade='" + localidade + '\'' +
                   ", uf='" + uf + '\'' +
                   ", estado='" + estado + '\'' +
                   '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}