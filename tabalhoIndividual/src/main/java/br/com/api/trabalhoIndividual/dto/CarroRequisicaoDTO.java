package br.com.api.trabalhoIndividual.dto;

import java.time.LocalDate;

public class CarroRequisicaoDTO {
	
	private String nome;
	private LocalDate dataFabricacao;

	public CarroRequisicaoDTO() {
		super();
	}

	public CarroRequisicaoDTO(String nome, LocalDate dataFabricacao) {
		super();
		this.nome = nome;
		this.dataFabricacao = dataFabricacao;
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

}
