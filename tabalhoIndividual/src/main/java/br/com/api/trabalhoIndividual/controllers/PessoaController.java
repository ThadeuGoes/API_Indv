package br.com.api.trabalhoIndividual.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.dto.PessoaRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.PessoaRespostaDTO;
import br.com.api.trabalhoIndividual.entities.Pessoa;
import br.com.api.trabalhoIndividual.repositories.PessoaRepository;
import br.com.api.trabalhoIndividual.services.EmailService;
import br.com.api.trabalhoIndividual.services.PessoaService;

@RestController
@RequestMapping("/usuario")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	EmailService emailService;

	@GetMapping("/count")
	public Integer getCount() {

		return pessoaService.getCount();
	}

	@GetMapping("/{id}")
	public PessoaRespostaDTO acharId(@PathVariable Integer id) {
		return pessoaService.acharId(id);
	}

	@GetMapping("/listar")
	public List<PessoaRespostaDTO> listar() {
		return pessoaService.listar();
	}

	@DeleteMapping("/deletarLogico/{id}")
	public void deletarLogico(@PathVariable Integer id) {
		emailService.envioEmailDelete(pessoaRepository.findById(id).get());
		pessoaService.deletarLogico(id);
	}

	@PutMapping("/atualizar/{id}")
	public Pessoa atualizar(@PathVariable Integer id, @RequestBody PessoaRequisicaoDTO objetousuario) {
		return pessoaService.atualizar(id, objetousuario);
	}

	@PutMapping("/recuperarSenha/{id}")
	public void recuperarSenha(@PathVariable Integer id, @RequestParam String senha) {
		pessoaService.recuperarSenha(id, senha);
		emailService.envioEmailRecuperacaoSenha(pessoaRepository.findById(id).get());
	}

	@PutMapping("/recuperarConta/{id}")
	public void recuperarConta(@PathVariable Integer id) {
		pessoaService.recuperarConta(id);
		emailService.envioEmailRecuperacaoConta(pessoaRepository.findById(id).get());
	}
}
