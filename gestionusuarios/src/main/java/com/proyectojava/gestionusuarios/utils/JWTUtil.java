package com.proyectojava.gestionusuarios.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

import java.util.Base64;
import java.util.Date;


@Component
public class JWTUtil {
   
	private final SecretKey signingKey;
	private final String issuer;
	private final long expiration;

	public JWTUtil(
		@Value("${security.jwt.secret}") String secret,
		@Value("${security.jwt.issuer}") String issuer,
		@Value("${security.jwt.expiration}") long expiration
	){
		String secretKeyBase64 = Base64.getEncoder().encodeToString(secret.getBytes());
		byte[] keyBytes = Decoders.BASE64.decode(secretKeyBase64);
		SecretKey key = Keys.hmacShaKeyFor(keyBytes);

		this.signingKey = key;
		this.issuer = issuer;
		this.expiration = expiration;
	}

    public String create(String id, String subject) {

			Date now = new Date();
			Date expirationDate = new Date(now.getTime() + expiration);

			return Jwts.builder()
					.id(id)
					.subject(subject)
					.issuer(issuer)
					.issuedAt(now)
					.expiration(expirationDate)
					.signWith(signingKey)
					.compact();
     
    }

		public Claims validate(String token){
			
			return Jwts.parser()
				.verifyWith(signingKey)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		}

  
}