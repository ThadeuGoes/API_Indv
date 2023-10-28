package br.com.api.trabalhoIndividual.dto;

import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.api.trabalhoIndividual.entities.Carro;

public class PessoaRequisicaoDTO {

	private String nome;
	private String email;
	private String cpf;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private List<Carro> carros;
	private Set<String> roles;
	private String cep;
	private String complemento;
	private String numero;

	public PessoaRequisicaoDTO() {
		super();
	}

	public PessoaRequisicaoDTO(String nome, String email, String cpf, String password, List<Carro> carros,
			Set<String> roles, String cep, String complemento, String numero) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.password = password;
		this.carros = carros;
		this.roles = roles;
		this.cep = cep;
		this.complemento = complemento;
		this.numero = numero;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Carro> getCarros() {
		return carros;
	}

	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

}