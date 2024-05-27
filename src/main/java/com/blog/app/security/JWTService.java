package com.blog.app.security;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;

@Service
public class JWTService {

	private static final String JWT_KEY ="fhd8e72hdkfdskfj2300dkjfsdhak393fhdfhf021j";
	private Algorithm algorithm = Algorithm.HMAC256(JWT_KEY);
	
	public String createJWT(Long userId) {
		return JWT.create()
				.withSubject(userId.toString())
				.withIssuedAt(new Date())
				.sign(algorithm);
	}
	
	public Long retrieveUserId(String jwtString) {
		var decodedJWT = JWT.decode(jwtString);
		var userId = Long.valueOf(decodedJWT.getSubject());
		return userId;	
	}
	
}
