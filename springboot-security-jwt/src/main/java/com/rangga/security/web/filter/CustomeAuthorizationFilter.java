/**
 * 
 */
package com.rangga.security.web.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ryo rangga sumagusta
 * 
 */

@Slf4j
public class CustomeAuthorizationFilter extends OncePerRequestFilter {
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getServletPath().equals("/api/login") || request.getServletPath().equals("/api/token/refresh")) {
			filterChain.doFilter(request, response);
		} else {
			try {
				String authorizationHeader = "Bearer "+request.getHeader(AUTHORIZATION);
				if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
					try {
						String token = authorizationHeader.substring("Bearer ".length());
						Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
						JWTVerifier verifier = JWT.require(algorithm).build();
						DecodedJWT decodedJWT = verifier.verify(token);
						String username = decodedJWT.getSubject();
						String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
						Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
						stream(roles).forEach(role -> {
							authorities.add(new SimpleGrantedAuthority(role));
						});
						UsernamePasswordAuthenticationToken authenticationToken = 
									new UsernamePasswordAuthenticationToken(username, null, authorities);
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
						filterChain.doFilter(request, response);
						
					} catch (Exception e) {
						log.error("Error logging in: {}", e.getMessage());
						  response.setHeader("error", e.getMessage());
						  response.setStatus(FORBIDDEN.value());
						  //response.sendError(FORBIDDEN.value( ));
						  
						  Map<String, String> error = new HashMap<>();
							error.put("error_message", e.getMessage());
							response.setContentType(MediaType.APPLICATION_JSON_VALUE);
							new ObjectMapper().writeValue(response.getOutputStream(), error);
					}
					
				} else {
					filterChain.doFilter(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
