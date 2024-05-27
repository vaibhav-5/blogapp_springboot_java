package com.blog.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.blog.app.users.UsersService;

public class JWTAuthenticationManager implements AuthenticationManager {
	
	private JWTService jwtService;
	private UsersService userService;

	public JWTAuthenticationManager(JWTService jwtService, UsersService userService) {
		super();
		this.jwtService = jwtService;
		this.userService = userService;
	}



	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if(authentication instanceof JWTAuthentication) {
			var jwtAuthentication = (JWTAuthentication) authentication;
			var jwt = jwtAuthentication.getCredentials();
			var userId = jwtService.retrieveUserId(jwt);
			var userEntity = userService.getUser(userId);
			jwtAuthentication.userEntity = userEntity;
			jwtAuthentication.setAuthenticated(true);
			return jwtAuthentication;
		}
		
		throw new IllegalAccessError("Cannot authenticate with non-JWT authentication");
	}

}
