package br.com.api.trabalhoIndividual.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "pessoa")
public class Pessoa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // informa q é pk
	private Integer id;
	@NotNull(message = "Nome não pode ser nulo.")
	@NotBlank(message = "Nome não pode ser vazio.")
	@Size(max = 50)
	private String nome;
	@NotNull(message = "EMAIL não pode ser nulo.")
	@NotBlank(message = "EMAIL não pode ser vazio.")
	@Email
	@Column(unique = true)
	private String email;
	@NotNull(message = "CPF não pode ser nulo.")
	@NotBlank(message = "CPF não pode ser vazio.")
	@Size(max = 11)
	private String cpf;
	private Boolean ativo;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	@OneToMany
	@JoinColumn(name = "pessoa_id")
	private List<Carro> carros;

	public Pessoa() {
		super();
	}

	public Pessoa(Integer id,
			@NotNull(message = "Nome não pode ser nulo.") @NotBlank(message = "Nome não pode ser vazio.") @Size(max = 50) String nome,
			@NotNull(message = "EMAIL não pode ser nulo.") @NotBlank(message = "EMAIL não pode ser vazio.") @Email String email,
			@NotNull(message = "CPF não pode ser nulo.") @NotBlank(message = "CPF não pode ser vazio.") @Size(max = 11) String cpf,
			Boolean ativo, String password, List<Carro> carros) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.ativo = ativo;
		this.password = password;
		this.carros = carros;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
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

	
	@Override
	public String toString() {
		return "Pessoa [id=" + id + ", nome=" + nome + ", email=" + email + ", cpf=" + cpf + ", ativo=" + ativo
				+ ", password=" + password + ", carros=" + carros + "]";
	}
}