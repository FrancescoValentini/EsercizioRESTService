package it.FrancescoValentini.unicam.EsercizioRESTService.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(c -> c.disable()); // consente csrf
		http.formLogin(c -> c.disable()); // disabilita form login di default
		http.sessionManagement(
				session -> session.sessionCreationPolicy(SessionCreationPolicy.NEVER)
				); // Spegne le policy di creazione delle sessioni
		
		// Autorizza tutte le richieste senza autenticazione (solo per TEST) 
		http.authorizeHttpRequests(req -> req.requestMatchers("/**").permitAll());
		
		return http.build();
	}
}
