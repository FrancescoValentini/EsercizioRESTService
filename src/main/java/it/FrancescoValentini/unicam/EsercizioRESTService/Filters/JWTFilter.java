package it.FrancescoValentini.unicam.EsercizioRESTService.Filters;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
		
        if (request.getRequestURI().startsWith("/login")) { 
            filterChain.doFilter(request, response); 
            return;
        }
		
		String token = request.getHeader("Authorization");
		
		// Verifica il JWT
		if(token != null && token.startsWith("Bearer ")){
			String userID = jwtTools.verifyToken(token.substring(7));
			Produttore p = usersRepository.findById(userID).get();
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
					p,
					null,
					p.getAuthorities()
					);
			SecurityContextHolder.getContext().setAuthentication(auth);
			
			filterChain.doFilter(request, response);
		}else {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token di autenticazione non presente");
			return;
		}
		
	}
}
