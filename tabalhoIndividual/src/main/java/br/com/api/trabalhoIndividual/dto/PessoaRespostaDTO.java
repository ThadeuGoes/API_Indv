package br.com.api.trabalhoIndividual.dto;

import java.util.List;

import br.com.api.trabalhoIndividual.entities.Carro;

public class PessoaRespostaDTO {

	private String nome;
	private String email;
	private Boolean ativo;
	private List<Carro> carros;

	public PessoaRespostaDTO() {
		super();
	}

	public PessoaRespostaDTO(String nome, String email, Boolean ativo, List<Carro> carros) {
		super();
		this.nome = nome;
		this.email = email;
		this.ativo = ativo;
		this.carros = carros;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

}
