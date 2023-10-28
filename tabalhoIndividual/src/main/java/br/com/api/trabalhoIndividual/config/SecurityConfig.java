package br.com.api.trabalhoIndividual.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.api.trabalhoIndividual.repositories.PessoaRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	PessoaRepository userRepo;

	@Autowired
	JWTFilter filter;

	@Autowired
	UserDetailsServiceImpl uds;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// TODO voltar os paths de segurança certos
	@Override
	protected void configure(HttpSecurity http) throws Exception { // Metodo encarregado de configurar a seguranca da
																	// API
		http.cors().and().csrf().disable().httpBasic().disable().authorizeHttpRequests()
/*permitAll*/	.antMatchers("/roles","/pessoa/login","/pessoa/registro","/carro/count","/carro/listar").permitAll()
/*locatario*/	.antMatchers("/endereco/reativarEndereco/{id}","/endereco/atualizar/{id}","/endereco/deletarLogico/{id}","/endereco/salvar").hasRole("LOCATARIO")
/*funcionario*/	.antMatchers("/pessoa/deletarLogico/{id}","/pessoa/listar","/pessoa/{id}","/pessoa/count","/endereco/listar","/endereco/{id}","/endereco/count","/carro/deletar/{id}","/carro/atualizar/{id}","/carro/salvar").hasRole("FUNCIONARIO")
/*ambos*/		.antMatchers("/pessoa/reativar/{id}","/pessoa/trocaSenha/{id}","/pessoa/atualizar/{id}","/carro/{id}").hasAnyRole("LOCATARIO","FUNCIONARIO")
				
				.and().userDetailsService(uds).exceptionHandling()
				.authenticationEntryPoint((request, response, authException) -> response
						.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"))
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}

//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests().anyRequest().permitAll().and().csrf(csrf -> csrf.disable());
//		return http.build();
//	}

}