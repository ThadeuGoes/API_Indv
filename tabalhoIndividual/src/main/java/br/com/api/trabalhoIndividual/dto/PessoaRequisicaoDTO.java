package br.com.api.trabalhoIndividual.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.api.trabalhoIndividual.entities.Carro;

public class PessoaRequisicaoDTO {

	private String nome;
	private String email;
	private String cpf;
	private String password;
	private List<Carro> carros;

	public PessoaRequisicaoDTO() {
		super();
	}

	public PessoaRequisicaoDTO(
			@NotNull(message = "Nome não pode ser nulo.") @NotBlank(message = "Nome não pode ser vazio.") @Size(max = 50) String nome,
			@Email String email,
			@NotNull(message = "CPF não pode ser nulo.") @NotBlank(message = "CPF não pode ser vazio.") @Size(max = 11) String cpf,
			String password, List<Carro> carros) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.password = password;
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

}
