package com.cognizant.authenticationservice.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.cognizant.authenticationservice.security.service.UserDetailsImpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtUtils {

	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrincipal.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}

		catch (Exception e) {
			return false;
		}
//		catch (SignatureException e) {
//			log.error("Invalid JWT signature: {}", e.getMessage());
//			throw new TokenInvalidException("Invalid JWT signature: " + e.getMessage());
//		} catch (MalformedJwtException e) {
//			log.error("Invalid JWT token: {}", e.getMessage());
//			throw new TokenInvalidException("Invalid JWT signature: " + e.getMessage());
//		} catch (ExpiredJwtException e) {
//			log.error("JWT token is expired: {}", e.getMessage());
//			throw new TokenInvalidException("Invalid JWT signature: " + e.getMessage());
//		} catch (UnsupportedJwtException e) {
//			log.error("JWT token is unsupported: {}", e.getMessage());
//			throw new TokenInvalidException("Invalid JWT signature: " + e.getMessage());
//		} catch (IllegalArgumentException e) {
//			log.error("JWT claims string is empty: {}", e.getMessage());
//			throw new TokenInvalidException("Invalid JWT signature: " + e.getMessage());
//		}
	}
}
