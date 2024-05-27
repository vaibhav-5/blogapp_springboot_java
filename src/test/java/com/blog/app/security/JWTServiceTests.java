package com.blog.app.security;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class JWTServiceTests {

	JWTService jwtService = new JWTService();
	
	@Test
	void canCreateJwtFromUserId() {
		var jwt = jwtService.createJWT(1001L);
		assertNotNull(jwt);
	}
}
