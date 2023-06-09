package com.neoris.reto.application.configuration.security.jwt;

import com.neoris.reto.application.service.impl.UsuarioService;
import io.jsonwebtoken.*;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwt.prueba.secret}")
	private String jwtSecret;

	@Value("${jwt.prueba.jwtExpirationMs}")
	private int jwtExpirationMs;

	public Single<String> generateJwtToken(Authentication authentication) {
		return Single.fromCallable(() -> {
			UsuarioService userPrincipal = (UsuarioService) authentication.getPrincipal();
			return Jwts.builder()
					.setSubject(userPrincipal.getEmail())
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512, jwtSecret)
					.compact();
		});
	}


	public Single<String> getEmailFromJwtToken(String token) {
		return Single.fromCallable(() -> {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
		});
	}

	public Single<Boolean> validateJwtToken(String authToken) {
		return Single.fromCallable(() -> {
			try {
				Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
				return true;
			} catch (SignatureException e) {
				logger.error("Invalid JWT signature: {}", e.getMessage());
			} catch (MalformedJwtException e) {
				logger.error("Invalid JWT token: {}", e.getMessage());
			} catch (ExpiredJwtException e) {
				logger.error("JWT token is expired: {}", e.getMessage());
			} catch (UnsupportedJwtException e) {
				logger.error("JWT token is unsupported: {}", e.getMessage());
			} catch (IllegalArgumentException e) {
				logger.error("JWT claims string is empty: {}", e.getMessage());
			}
			return false;
		});
	}
}
