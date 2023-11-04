package br.com.api.trabalhoIndividual.dto;

import java.time.LocalDate;

public class CarroRequisicaoDTO {

	private String nome;
	private LocalDate dataFabricacao;
	private String descricao;
	private String img;
	private Double valor;

	public CarroRequisicaoDTO() {
		super();
	}

	public CarroRequisicaoDTO(String nome, LocalDate dataFabricacao, String descricao, String img, Double valor) {
		super();
		this.nome = nome;
		this.dataFabricacao = dataFabricacao;
		this.descricao = descricao;
		this.img = img;
		this.valor = valor;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}