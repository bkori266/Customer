package com.cg.customer.serviceImpl;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class CustomerJwtService {
	
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	private Key getKey() {
		byte[] bytes=Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(bytes);
		
	}

	public String extractUsername(String token) {
		
		return extractClaim(token,Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
		final Claims claims=extractAllClaims(token);
		return claimResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts.parserBuilder()
				.setSigningKey(getKey())
				.build().parseClaimsJws(token)
				//.parseClaimsJws(token)
				.getBody();
	}

	public boolean validateToken(String token) {
		final String name=extractUsername(token);
		return (!isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		
		return extractClaim(token, Claims::getExpiration);
	}

	
	
	
}
