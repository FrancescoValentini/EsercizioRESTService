package it.FrancescoValentini.unicam.EsercizioRESTService.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.OncePerRequestFilter;

import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Produttore;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProduttoriRepository;
import it.FrancescoValentini.unicam.EsercizioRESTService.Utils.JWTTools;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter {

	/**
	 * Middleware per la verifica del JWT ad ogni richiesta
	 */
	
	@Autowired
	JWTTools jwtTools;
	
	@Autowired
	ProduttoriRepository usersRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
									HttpServletResponse response, 
									FilterChain filterChain) throws ServletException, IOException {
		

		
		String token = request.getHeader("Authorization");
		
        if (token == null || !token.startsWith("Bearer ")) { // token non presente
            filterChain.doFilter(request, response); 
            return;
        }else { // token presente, lo verifica
        	String userID = jwtTools.verifyToken(token.substring(7));
        	Authentication authContext = SecurityContextHolder.getContext().getAuthentication();
        	if(userID != null && authContext == null) {
            	Produttore p = usersRepository.findById(userID).get();

    			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
    					p,
    					null,
    					p.getAuthorities()
    					);
    			SecurityContextHolder.getContext().setAuthentication(auth);
    			
        	}
        }
		
		filterChain.doFilter(request, response);
	}
	
	@Bean
	public FilterRegistrationBean registration(JWTFilter filter) {
	    FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>(filter);
	    registrationBean.setEnabled(false); // Disabilita la registrazione globale
	    return registrationBean;
	}
}
