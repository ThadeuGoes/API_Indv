package br.com.api.trabalhoIndividual.dto;

import java.time.LocalDate;

public class CarroRespostaDTO {

	private String nome;
	private LocalDate dataFabricacao;
	private String descricao;
	private Double valor;
	private String img;
	private Boolean ativo;

	public CarroRespostaDTO() {
		super();
	}

	public CarroRespostaDTO(String nome, LocalDate dataFabricacao, String descricao, Double valor, String img,
			Boolean ativo) {
		super();
		this.nome = nome;
		this.dataFabricacao = dataFabricacao;
		this.descricao = descricao;
		this.valor = valor;
		this.img = img;
		this.ativo = ativo;
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

}