package it.FrancescoValentini.unicam.EsercizioRESTService.RESTControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.FrancescoValentini.unicam.EsercizioRESTService.DTO.LoginDTO;
import it.FrancescoValentini.unicam.EsercizioRESTService.Models.Produttore;
import it.FrancescoValentini.unicam.EsercizioRESTService.Repositories.ProduttoriRepository;
import it.FrancescoValentini.unicam.EsercizioRESTService.Utils.JWTTools;

@RestController
public class RestLogin {
	
	@Autowired
	private ProduttoriRepository usersRepository;
	
	@Autowired
	JWTTools jwtTools;
	
	@GetMapping(value = "/whoami")
	
	public ResponseEntity<Object> whoami(@AuthenticationPrincipal Produttore utente) {
		if(utente != null) return new ResponseEntity<>(utente,HttpStatus.OK);
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<Object> login(@RequestBody LoginDTO utente ) {
		Produttore prod = usersRepository.findByUsername(utente.getUsername()).get();
		
		if(prod != null && prod.getPassword().equals(utente.getPassword())) {
			
			return new ResponseEntity<>(jwtTools.signToken(prod.getId()),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(prod,HttpStatus.UNAUTHORIZED);
		}
		
	}

}
