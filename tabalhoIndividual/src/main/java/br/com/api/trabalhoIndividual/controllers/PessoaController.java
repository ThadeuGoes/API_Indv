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

	@PostMapping("/salvar")
	public void salvar(@RequestBody PessoaRequisicaoDTO objetoProduto, @RequestParam String nomeDaCategoria,
			@RequestParam String email) {
		pessoaService.salvar(objetoProduto, nomeDaCategoria, email);
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
	/*
	 * // Registro de usuario
	 * 
	 * @SuppressWarnings("unused")
	 * 
	 * @PostMapping("/registro") public Pessoa cadastro(@RequestParam String
	 * email, @RequestBody PessoaRequisicaoDTO usuario) {
	 * 
	 * // usuario = usuarioService.save(usuario);
	 * 
	 * // Gerando o token JWT a partir do e-mail do Usuario // String token =
	 * jwtUtil.generateToken(usuario.getEmail()); Set<String> usuarioMaiusculo = new
	 * HashSet<>();
	 * 
	 * for (String str : usuario.getRoles()) {
	 * usuarioMaiusculo.add(str.toUpperCase()); }
	 * 
	 * Set<String> strRoles = usuarioMaiusculo; Set<Role> roles = new HashSet<>();
	 * 
	 * if (strRoles == null) { // Se não houver roles especificadas, atribui o papel
	 * de USUÁRIO Role usuarioRole =
	 * roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR) .orElseThrow(() -> new
	 * RuntimeException("Erro: Role não encontrada.")); roles.add(usuarioRole); }
	 * else { // Mapeando roles especificadas para objetos Role
	 * strRoles.forEach(role -> { switch (role) { case "VENDEDOR": Role adminRole =
	 * roleRepository.findByName(TipoRoleEnum.ROLE_VENDEDOR) .orElseThrow(() -> new
	 * RuntimeException("Erro: Role não encontrada.")); roles.add(adminRole); break;
	 * case "COMPRADOR": Role usuarioRole =
	 * roleRepository.findByName(TipoRoleEnum.ROLE_COMPRADOR) .orElseThrow(() -> new
	 * RuntimeException("Erro: Role não encontrada.")); roles.add(usuarioRole); }
	 * }); }
	 * 
	 * Endereco viaCep = enderecoService.pesquisarEndereco(usuario.getCep());
	 * Endereco endereco = new Endereco(); endereco.setCep(usuario.getCep());
	 * endereco.setComplemento(usuario.getComplemento());
	 * endereco.setNumero(usuario.getNumero());
	 * endereco.setBairro(viaCep.getBairro());
	 * endereco.setLocalidade(viaCep.getLocalidade());
	 * endereco.setLogradouro(viaCep.getLogradouro());
	 * endereco.setUf(viaCep.getUf()); endereco.setAtivo(true);
	 * 
	 * Usuario usuarioResumido = new Usuario(); usuarioResumido.setAtivo(true);
	 * usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
	 * usuarioResumido.setEmail(usuario.getEmail());
	 * usuarioResumido.setRoles(roles); usuarioResumido.setCpf(usuario.getCpf());
	 * usuarioResumido.setDataNascimento(usuario.getDataNascimento());
	 * usuarioResumido.setNome(usuario.getNome()); // Encriptando a senha usando o
	 * Bcrypt String encodedPass = passwordEncoder.encode(usuario.getPassword());
	 * usuarioResumido.setPassword(encodedPass);
	 * usuarioRepository.save(usuarioResumido);
	 * 
	 * List<Endereco> enderecos = new ArrayList<>(); enderecos.add(endereco);
	 * usuarioResumido.setEndereco(enderecos); // String token =
	 * jwtUtil.generateTokenWithUsuarioData(usuarioResumido); //
	 * Collections.singletonMap("jwt-token", token);
	 * 
	 * enderecoRepository.save(endereco); emailService.envioEmailCadastro(usuario);
	 * return usuarioRepository.save(usuarioResumido); }
	 * 
	 * // Login de usuario
	 * 
	 * @PostMapping("/login") public Map<String, Object> login(@RequestBody LoginDTO
	 * body) { try { // Criando o token que sera usado no processo de autenticacao
	 * UsernamePasswordAuthenticationToken authInputToken = new
	 * UsernamePasswordAuthenticationToken( body.getEmail(), body.getPassword());
	 * 
	 * // Autenticando as credenciais de login
	 * authManager.authenticate(authInputToken);
	 * 
	 * // Se o processo de autenticacao foi concluido com sucesso - etapa anterior,
	 * // eh gerado o JWT // String token = jwtUtil.generateToken(body.getEmail());
	 * 
	 * Usuario usuario = usuarioService.findByEmail(body.getEmail()); Usuario
	 * usuarioResumido = new Usuario();
	 * usuarioResumido.setNomeUsuario(usuario.getNomeUsuario());
	 * usuarioResumido.setEmail(usuario.getEmail());
	 * usuarioResumido.setId(usuario.getId());
	 * usuarioResumido.setRoles(usuario.getRoles()); // Gerando o token JWT a partir
	 * dos dados do Usuario String token =
	 * jwtUtil.generateTokenWithUsuarioData(usuarioResumido);
	 * 
	 * // Responde com o JWT return Collections.singletonMap("jwt-token", token); }
	 * catch (AuthenticationException authExc) { throw new
	 * RuntimeException("Credenciais Invalidas"); } }
	 */
}
