package com.neoris.reto.application.configuration.security.jwt;

import com.neoris.reto.application.service.impl.UsuarioService;
import com.neoris.reto.application.service.impl.UsuarioServiceImpl;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthTokenFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UsuarioServiceImpl userDetailsService;

	private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String jwt = parseJwt(request);
		if (jwt != null && jwtUtils.validateJwtToken(jwt).blockingGet()) {
			String email = jwtUtils.getEmailFromJwtToken(jwt).blockingGet();

			Completable.fromCallable(() -> {
				UserDetails userDetails = userDetailsService.loadUserByEmail(email);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authentication);
				 return Completable.complete();
			}).subscribe(
					() -> {
					},
					error -> logger.error("Cannot set user authentication: {}", error)
			);
		}

		filterChain.doFilter(request, response);
	}

	private String parseJwt(HttpServletRequest request) {
		String headerAuth = request.getHeader("Authorization");

		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
			return headerAuth.substring(7, headerAuth.length());
		}

		return null;
	}
}
