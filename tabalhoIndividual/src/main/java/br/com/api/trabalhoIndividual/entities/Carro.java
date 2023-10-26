package br.com.api.trabalhoIndividual.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "caro")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	@NotNull(message = "Nome não pode ser nulo.")
	@NotBlank(message = "Nome não pode ser vazio.")
	@Size(max = 50)
	private String nome;
	private LocalDate dataFabricacao;
	private Boolean ativo;

	public Carro() {
		super();
	}

	public Carro(Integer id,
			@NotNull(message = "Nome não pode ser nulo.") @NotBlank(message = "Nome não pode ser vazio.") @Size(max = 50) String nome,
			LocalDate dataFabricacao, Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataFabricacao = dataFabricacao;
		this.ativo = ativo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataFabricacao() {
		return dataFabricacao;
	}

	public void setDataFabricacao(LocalDate dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", nome=" + nome + ", dataFabricacao=" + dataFabricacao + ", ativo=" + ativo + "]";
	}
}
