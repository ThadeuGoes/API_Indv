package br.com.api.trabalhoIndividual.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro")
public class Carro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	private String nome;
	private LocalDate dataFabricacao;
	@Column(columnDefinition = "TEXT")
	private String descricao;
	private Double valor;
	private String img;
	private Boolean ativo;

	public Carro() {
		super();
	}

	public Carro(Integer id, String nome, LocalDate dataFabricacao, String descricao, Double valor, String img,
			Boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.dataFabricacao = dataFabricacao;
		this.descricao = descricao;
		this.valor = valor;
		this.img = img;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Carro [id=" + id + ", nome=" + nome + ", dataFabricacao=" + dataFabricacao + ", descricao=" + descricao
				+ ", valor=" + valor + ", img=" + img + ", ativo=" + ativo + "]";
	}

}