package br.com.api.trabalhoIndividual.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.api.trabalhoIndividual.dto.PessoaRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.PessoaRespostaDTO;
import br.com.api.trabalhoIndividual.entities.Pessoa;
import br.com.api.trabalhoIndividual.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	public Pessoa parseDePessoaRequisicao(PessoaRequisicaoDTO obj) {
		Pessoa pessoaNova = new Pessoa();

		return pessoaNova;
	}

	public PessoaRespostaDTO parseDePessoaResposta(Pessoa obj) {
		PessoaRespostaDTO pessoaNova = new PessoaRespostaDTO();

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

	public void deletarLogico(Integer id) {
		if (pessoaRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse usuario não existe");
		} else {
			Pessoa obgUsuario = pessoaRepository.findById(id).get();
			if (obgUsuario != null) {
				obgUsuario.setAtivo(false);
				pessoaRepository.save(obgUsuario);
			}
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

	public void salvar(PessoaRequisicaoDTO objeto, String email) {
		pessoaRepository.save(parseDePessoaRequisicao(objeto));

	}
}