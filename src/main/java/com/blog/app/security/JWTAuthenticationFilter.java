package com.blog.app.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;

public class JWTAuthenticationFilter extends AuthenticationFilter {

	public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager) {
		super(jwtAuthenticationManager, new JWTAuthenticationConverter());
		
		this.setSuccessHandler((request, response, authentication) -> {
			SecurityContextHolder.getContext().setAuthentication(authentication);
		});
	}

}
