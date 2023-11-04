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
		
/*permitAll*/	.antMatchers("/carro/count","/carro/listar","/pessoa/registro","/pessoa/login","/roles").permitAll()
/*locatario*/	.antMatchers("/carro/alugar/{id}","/carro/devolverCarro/{id}","/endereco/salvar","/endereco/deletarLogico/{id}","/endereco/atualizar/{id}","/endereco/reativarEndereco/{id}").hasRole("LOCATARIO")
/*funcionario*/	.antMatchers("/carro/salvar","/carro/deletar/{id}","/carro/atualizar/{id}","/endereco/count","/endereco/{id}","/endereco/listar","/pessoa/count","/pessoa/{id}","/pessoa/listar").hasRole("FUNCIONARIO")
/*ambos*/		.antMatchers("/carro/{id}","/pessoa/deletarLogico/{id}","/pessoa/atualizar/{id}","/pessoa/trocaSenha/{id}","/pessoa/reativar/{id}").hasAnyRole("LOCATARIO","FUNCIONARIO")
				
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