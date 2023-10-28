package br.com.api.trabalhoIndividual.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.dto.CarroRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.CarroRespostaDTO;
import br.com.api.trabalhoIndividual.entities.Carro;
import br.com.api.trabalhoIndividual.services.CarroService;

@RestController
@RequestMapping("/carro")
public class CarroController {

	@Autowired
	CarroService carroService;

	@GetMapping("/count")
	public Integer getCount() {
		return carroService.getCount();
	}

	@PostMapping("/salvar")
	public void salvar(@RequestBody CarroRequisicaoDTO objetoProduto, @RequestParam String email) {
		carroService.salvar(objetoProduto, email);
	}

	@GetMapping("/{id}")
	public CarroRespostaDTO acharId(@PathVariable Integer id) {
		return carroService.acharId(id);
	}

	@GetMapping("/listar")
	public List<CarroRespostaDTO> listar() {
		return carroService.listar();
	}

	@DeleteMapping("/deletar/{id}")
	public void deletar(@PathVariable Integer id) {
		carroService.deletar(id);
	}

	@PutMapping("/atualizar/{id}")
	public Carro atualizar(@PathVariable Integer id, @RequestBody CarroRequisicaoDTO objetoProduto) {
		return carroService.atualizar(id, objetoProduto);
	}

}
