package com.blog.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import com.blog.app.users.UsersService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {
	
	private JWTAuthenticationFilter jwtAuthenticationFilter;
	private JWTService jwtService;
	private UsersService userService;

	public AppSecurityConfig(JWTService jwtService, UsersService userService) {
		super();
		this.jwtService = jwtService;
		this.userService = userService;
		this.jwtAuthenticationFilter = new JWTAuthenticationFilter(new JWTAuthenticationManager(jwtService, userService));
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors(cors -> cors.disable()).csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(authorizationManagerRequestRegistryMatcher ->
					authorizationManagerRequestRegistryMatcher
						.requestMatchers(HttpMethod.POST, "/users","/users/login").permitAll()
						.requestMatchers(HttpMethod.POST, "/articles","/articles/*").permitAll()
						.anyRequest().authenticated()
						);
		
		http.addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter.class);
		
		return http.build();
	}
}
