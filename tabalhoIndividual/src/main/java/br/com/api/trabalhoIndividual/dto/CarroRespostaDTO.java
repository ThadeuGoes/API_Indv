package br.com.api.trabalhoIndividual.dto;

import java.time.LocalDate;

public class CarroRespostaDTO {

	private String nome;
	private LocalDate dataFabricacao;
	private Boolean ativo;

	public CarroRespostaDTO() {
		super();
	}

	public CarroRespostaDTO(String nome, LocalDate dataFabricacao, Boolean ativo) {
		super();
		this.nome = nome;
		this.dataFabricacao = dataFabricacao;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

}
