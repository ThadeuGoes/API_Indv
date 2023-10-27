package br.com.api.trabalhoIndividual.config;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.api.trabalhoIndividual.entities.Pessoa;
import br.com.api.trabalhoIndividual.repositories.PessoaRepository;
import br.com.api.trabalhoIndividual.repositories.RoleRepository;
import br.com.api.trabalhoIndividual.services.PessoaService;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	PessoaRepository userRepo;
	@Autowired
	PessoaService userService;
	@Autowired
	RoleRepository roleRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Pessoa> userRes = userRepo.findByEmail(email);
		if (userRes.isEmpty()) {
			throw new UsernameNotFoundException("Não foi possível encontrar usuário com o email = " + email);
		}
		return new org.springframework.security.core.userdetails.User(email, userRes.get().getPassword(),
				roleRepo.roles(email).stream().map(role -> new SimpleGrantedAuthority(role.getName().name()))
						.collect(Collectors.toList())); // Define, de forma estatica, o perfil do pessoa encontrado
	}
}