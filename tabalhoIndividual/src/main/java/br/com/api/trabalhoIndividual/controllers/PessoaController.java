package br.com.api.trabalhoIndividual.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.trabalhoIndividual.config.JWTUtil;
import br.com.api.trabalhoIndividual.dto.LoginDTO;
import br.com.api.trabalhoIndividual.dto.PessoaRequisicaoDTO;
import br.com.api.trabalhoIndividual.dto.PessoaRespostaDTO;
import br.com.api.trabalhoIndividual.entities.Endereco;
import br.com.api.trabalhoIndividual.entities.Pessoa;
import br.com.api.trabalhoIndividual.entities.Role;
import br.com.api.trabalhoIndividual.enums.TipoRoleEnum;
import br.com.api.trabalhoIndividual.repositories.CarroRepository;
import br.com.api.trabalhoIndividual.repositories.EnderecoRepository;
import br.com.api.trabalhoIndividual.repositories.PessoaRepository;
import br.com.api.trabalhoIndividual.repositories.RoleRepository;
import br.com.api.trabalhoIndividual.services.EmailService;
import br.com.api.trabalhoIndividual.services.EnderecoService;
import br.com.api.trabalhoIndividual.services.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	EnderecoService enderecoService;
	@Autowired
	PessoaRepository pessoaRepository;
	@Autowired
	EnderecoRepository enderecoRepository;
	@Autowired
	CarroRepository carroRepository;

	private EmailService emailService;

	@Autowired
	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}

	@GetMapping("/count")
	public Integer getCount() {
		return pessoaService.getCount();
	}

	@PostMapping("/mensagem")
	public String mensagem(@RequestParam String nome, @RequestParam String email, @RequestParam String mensagem) {
		emailService.mensagem(nome, email, mensagem);
		return "Email enviado";
	}

	@GetMapping("/{id}")
	public PessoaRespostaDTO acharId(@PathVariable Integer id) {
		return pessoaService.acharId(id);
	}

	@GetMapping("/listar")
	public List<PessoaRespostaDTO> listar() {
		return pessoaService.listar();
	}

	@DeleteMapping("/deletarLogico")
	public void deletarLogico(@RequestParam String email, @RequestParam String password) {

		pessoaService.deletarLogico(email, password);
		emailService.envioEmailDelete(email);
	}

	@PutMapping("/atualizar/{id}")
	public Pessoa atualizar(@PathVariable Integer id, @RequestBody PessoaRequisicaoDTO objetousuario) {
		return pessoaService.atualizar(id, objetousuario);
	}

	@PutMapping("/trocaSenha/{id}")
	public void trocarSenha(@PathVariable Integer id, @RequestParam String senha) {
		pessoaService.recuperarSenha(id, senha);
	}

	@PutMapping("/reativar/{id}")
	public void reativarConta(@PathVariable Integer id) {
		pessoaService.recuperarConta(id);
		emailService.envioEmailRecuperacaoConta(pessoaRepository.findById(id).get());
	}

	// Registro de usuario
	@SuppressWarnings("unused")
	@PostMapping("/registro")
	public Pessoa cadastro(@RequestBody PessoaRequisicaoDTO pessoa) {

		// usuario = usuarioService.save(usuario);

		// Gerando o token JWT a partir do e-mail do Usuario
		// String token = jwtUtil.generateToken(pessoa.getEmail());
		Set<String> strRoles = new HashSet<>();
		
		Set<String> usuarioMaiusculo = new HashSet<>();
		if (pessoa.getRoles() != null) {
			for (String str : pessoa.getRoles()) {
				usuarioMaiusculo.add(str.toUpperCase());
				strRoles = usuarioMaiusculo;
			}
		} else {
			 strRoles = pessoa.getRoles();
		}

		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			// Se não houver roles especificadas, atribui o papel de USUÁRIO
			Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_LOCATARIO)
					.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
			roles.add(usuarioRole);
		} else {
			// Mapeando roles especificadas para objetos Role
			strRoles.forEach(role -> {
				switch (role) {
				case "FUNCIONARIO":
					Role adminRole = roleRepository.findByName(TipoRoleEnum.ROLE_FUNCIONARIO)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(adminRole);
					break;
				case "LOCATARIO":
					Role usuarioRole = roleRepository.findByName(TipoRoleEnum.ROLE_LOCATARIO)
							.orElseThrow(() -> new RuntimeException("Erro: Role não encontrada."));
					roles.add(usuarioRole);
				}
			});
		}

		Endereco viaCep = enderecoService.pesquisarEndereco(pessoa.getCep());
		Endereco endereco = new Endereco();
		endereco.setCep(pessoa.getCep());
		endereco.setComplemento(pessoa.getComplemento());
		endereco.setNumero(pessoa.getNumero());
		endereco.setBairro(viaCep.getBairro());
		endereco.setLocalidade(viaCep.getLocalidade());
		endereco.setLogradouro(viaCep.getLogradouro());
		endereco.setUf(viaCep.getUf());
		endereco.setAtivo(true);

//		Carro carro = new Carro();
//		carro.setAtivo(true);
//		carro.setDataFabricacao(pessoa.getCarros().get(0).getDataFabricacao());
//		carro.setNome(pessoa.getCarros().get(0).getNome());

		Pessoa pessoaResumido = new Pessoa();
		pessoaResumido.setAtivo(true);
//		pessoaResumido.setNomeUsuario(pessoa.getNomeUsuario());
		pessoaResumido.setEmail(pessoa.getEmail());
		pessoaResumido.setRoles(roles);
		pessoaResumido.setCpf(pessoa.getCpf());
//		pessoaResumido.setDataNascimento(pessoa.getDataNascimento());
		pessoaResumido.setNome(pessoa.getNome());

		// Encriptando a senha usando o Bcrypt
		String encodedPass = passwordEncoder.encode(pessoa.getPassword());
		pessoaResumido.setPassword(encodedPass);
		pessoaRepository.save(pessoaResumido);

//		List<Carro> carros = new ArrayList<>();
//		carros.add(carro);
//		pessoaResumido.setCarros(carros);
//		carroRepository.save(carro);

		List<Endereco> enderecos = new ArrayList<>();
		enderecos.add(endereco);
		pessoaResumido.setEndereco(enderecos);
//	        String token = jwtUtil.generateTokenWithUsuarioData(usuarioResumido);
//	        Collections.singletonMap("jwt-token", token);

		enderecoRepository.save(endereco);

		emailService.envioEmailCadastro(pessoa);
		return pessoaRepository.save(pessoaResumido);
	}

	// Login de usuario
	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginDTO body) {

		System.out.println("o email é " + body.getEmail());

		try {
			// Criando o token que sera usado no processo de autenticacao
			UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
					body.getEmail(), body.getPassword());
			// Autenticando as credenciais de login
			authManager.authenticate(authInputToken);

			// Se o processo de autenticacao foi concluido com sucesso - etapa anterior,
			// eh gerado o JWT
//	            String token = jwtUtil.generateToken(body.getEmail());

			Pessoa pessoa = pessoaService.findByEmail(body.getEmail());
			Pessoa usuarioResumido = new Pessoa();
			usuarioResumido.setNome(pessoa.getNome());
			usuarioResumido.setEmail(pessoa.getEmail());
			usuarioResumido.setId(pessoa.getId());
			usuarioResumido.setRoles(pessoa.getRoles());
			// Gerando o token JWT a partir dos dados do Usuario
			String token = jwtUtil.generateTokenWithUsuarioData(usuarioResumido);
			// Responde com o JWT
			return Collections.singletonMap("jwt-token", token);
		} catch (AuthenticationException authExc) {
			throw new RuntimeException("Credenciais Invalidas");
		}
	}
}
