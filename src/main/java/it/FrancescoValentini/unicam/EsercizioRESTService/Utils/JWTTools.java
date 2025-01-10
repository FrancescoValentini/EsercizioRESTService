package it.FrancescoValentini.unicam.EsercizioRESTService.Utils;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTTools {
	
	@Value("${spring.jwt.secret}")
	String hmacKeyString;
	
	public String signToken(String uid) {
		SecretKey hmacKey = Keys.hmacShaKeyFor(hmacKeyString.getBytes());
		return Jwts.builder()
				.setSubject(uid)
				.setExpiration(new Date(System.currentTimeMillis() + (15 * 60 * 1000)))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.signWith(hmacKey)
				.compact();
	}
}
