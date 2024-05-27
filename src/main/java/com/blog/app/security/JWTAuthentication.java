package com.blog.app.security;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.blog.app.users.UserEntity;

public class JWTAuthentication implements Authentication {

	String jwt;
	UserEntity userEntity;
	
	public JWTAuthentication(String jwt) {
		super();
		this.jwt = jwt;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getCredentials() {
		return jwt;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public UserEntity getPrincipal() {
		return userEntity;
	}

	@Override
	public boolean isAuthenticated() {
		return userEntity!=null;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		
	}

}
