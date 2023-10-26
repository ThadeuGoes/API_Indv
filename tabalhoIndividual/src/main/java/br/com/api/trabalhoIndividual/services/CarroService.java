package br.com.api.trabalhoIndividual.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.api.trabalhoIndividual.dto.CarroRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.CarroRespostaDTO;
import br.com.api.trabalhoIndividual.entities.Carro;
import br.com.api.trabalhoIndividual.entities.Pessoa;
import br.com.api.trabalhoIndividual.repositories.CarroRepository;
import br.com.api.trabalhoIndividual.repositories.PessoaRepository;

public class CarroService {

	@Autowired
	CarroRepository carroRepository;
	@Autowired
	PessoaRepository pessoaRepository;

	public Carro parseDeCarroRequisicao(CarroRequisicaoDTO objeto) {
		Carro carroNovo = new Carro();

		return carroNovo;
	}

	public CarroRespostaDTO parseDeProdutoResposta(Carro objeto) {
		CarroRespostaDTO carroNovo = new CarroRespostaDTO();

		return carroNovo;
	}

	public Integer getCount() {
		return carroRepository.contar();
	}

	public void salvar(CarroRequisicaoDTO objeto, String email) {
		Carro carroNovo = parseDeCarroRequisicao(objeto);

		carroNovo.setAtivo(true);

		Optional<Pessoa> usuario = pessoaRepository.findByEmail(email);
		usuario.get().getCarros().add(carroNovo);

		carroRepository.save(carroNovo);
		pessoaRepository.save(usuario.get());
	}

	public CarroRespostaDTO acharId(Integer id) {
		if (carroRepository.findById(id).get() == null) {

			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			CarroRespostaDTO produtoResposta = parseDeProdutoResposta(carroRepository.findById(id).get());
			return produtoResposta;
		}
	}

	public List<CarroRespostaDTO> listar() {
		List<CarroRespostaDTO> carrosResposta = new ArrayList<>();
		List<Carro> carros = carroRepository.findAll();
		for (Carro carro : carros) {
			carrosResposta.add(parseDeProdutoResposta(carro));
		}
		return carrosResposta;
	}

	public void deletar(Integer id) {
		if (carroRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			carroRepository.deleteById(id);
		}
	}

	public Carro atualizar(Integer id, CarroRequisicaoDTO objeto) {
		if (carroRepository.findById(id).get() == null) {
			throw new EntityNotFoundException("Esse produto não existe");
		} else {
			Optional<Carro> registroAntigo = carroRepository.findById(id);
			Carro produto = parseDeCarroRequisicao(objeto);

			if (produto.getAtivo() != null) {
				registroAntigo.get().setAtivo(produto.getAtivo());
			}
			if (produto.getNome() != null) {
				registroAntigo.get().setNome(produto.getNome());
			}

			if (produto.getDataFabricacao() != null) {
				registroAntigo.get().setDataFabricacao(produto.getDataFabricacao());
			}

			registroAntigo.get().setId(id);
			return carroRepository.save(registroAntigo.get());
		}
	}
}
