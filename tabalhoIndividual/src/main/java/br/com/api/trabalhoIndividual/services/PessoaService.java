package br.com.api.trabalhoIndividual.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.dto.CarroRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.CarroRespostaDTO;
import br.com.api.trabalhoIndividual.dto.PessoaRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.PessoaRespostaDTO;
import br.com.api.trabalhoIndividual.entities.Carro;
import br.com.api.trabalhoIndividual.entities.Pessoa;
import br.com.api.trabalhoIndividual.entities.Role;
import br.com.api.trabalhoIndividual.enums.TipoRoleEnum;
import br.com.api.trabalhoIndividual.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	CarroService carroService;

	public Pessoa parseDePessoaRequisicao(PessoaRequisicaoDTO obj) {
		Pessoa pessoaNova = new Pessoa();
		List<Carro> carros = new ArrayList<>();

		pessoaNova.setNome(obj.getNome());
		pessoaNova.setEmail(obj.getEmail());
		pessoaNova.setCpf(obj.getCpf());
		pessoaNova.setPassword(obj.getPassword());

		for (CarroRequisicaoDTO carro : obj.getCarros()) {
			carros.add(carroService.parseDeCarroRequisicao(carro));
		}
		pessoaNova.setCarros(carros);

		Set<Role> roles = new HashSet<>();
		for (String role : obj.getRoles()) {
			TipoRoleEnum rolem = TipoRoleEnum.valueOf(role);
			Role rolen = new Role(rolem);
			roles.add(rolen);
		}

		pessoaNova.setRoles(roles);

		return pessoaNova;
	}

	public PessoaRespostaDTO parseDePessoaResposta(Pessoa obj) {
		PessoaRespostaDTO pessoaNova = new PessoaRespostaDTO();
		List<CarroRespostaDTO> carros = new ArrayList<>();

		pessoaNova.setNome(obj.getNome());
		pessoaNova.setEmail(obj.getEmail());
		pessoaNova.setAtivo(obj.getAtivo());

		for (Carro carro : obj.getCarros()) {
			carros.add(carroService.parseDeProdutoResposta(carro));
		}
		pessoaNova.setCarros(carros);

		return pessoaNova;
	}

	public Pessoa findByEmail(String email) {
		return pessoaRepository.findByEmail(email).get();
	}

	public Integer getCount() {
		return pessoaRepository.contar();
	}

	public PessoaRespostaDTO acharId(Integer id) {
		PessoaRespostaDTO UsuarioResposta = parseDePessoaResposta(pessoaRepository.findById(id).get());
		return UsuarioResposta;
	}

	public List<PessoaRespostaDTO> listar() {
		List<PessoaRespostaDTO> pessoaResposta = new ArrayList<>();
		List<Pessoa> pessoas = pessoaRepository.findAll();

		for (Pessoa usuario : pessoas) {
			pessoaResposta.add(parseDePessoaResposta(usuario));
		}
		return pessoaResposta;
	}

	public void deletarLogico(String email, String senha) {
		Pessoa objUsuario = pessoaRepository.findByEmail(email).get();
		System.out.println(objUsuario.getEmail());

		if (objUsuario == null || !objUsuario.getPassword().equals(senha)) {
			throw new EntityNotFoundException("Email ou senha invalidos");
		} else {
			objUsuario.setAtivo(false);
			pessoaRepository.save(objUsuario);
		}
	}

	public Pessoa atualizar(Integer id, PessoaRequisicaoDTO obj) {
		if (pessoaRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Pessoa registroAntigo = pessoaRepository.findById(id).get();
			Pessoa pessoa = parseDePessoaRequisicao(obj);

			if (pessoa.getAtivo() != null) {
				registroAntigo.setAtivo(pessoa.getAtivo());
			}
			if (pessoa.getNome() != null) {
				registroAntigo.setNome(pessoa.getNome());
			}
			if (pessoa.getEmail() != null) {
				registroAntigo.setEmail(pessoa.getEmail());
			}
			if (pessoa.getCpf() != null) {
				registroAntigo.setCpf(pessoa.getCpf());
			}
			registroAntigo.setId(id);
			return pessoaRepository.save(registroAntigo);
		}
	}

	public void recuperarSenha(Integer id, String senha) {
		if (pessoaRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Pessoa objTeste = pessoaRepository.findById(id).get();
			if (objTeste != null) {
				objTeste.setPassword(senha);
				pessoaRepository.save(objTeste);
			}
		}
	}

	public void recuperarConta(Integer id) {
		if (pessoaRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Pessoa obgUsuario = pessoaRepository.findById(id).get();
			if (obgUsuario != null) {
				obgUsuario.setAtivo(true);
				pessoaRepository.save(obgUsuario);
			}
		}
	}

}