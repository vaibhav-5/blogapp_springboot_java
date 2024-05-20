package com.blog.app.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.blog.app.users.dto.CreateUserRequest;

@SpringBootTest
@ActiveProfiles("test")
public class UsersServiceTests {

	@Autowired
	private UsersService usersService;
	
	@Test
	void can_create_users() {
		var user = usersService.createUser(new CreateUserRequest("thor", "pass123", "thor@blog.com"));
		
		assertNotNull(user);
		assertEquals("thor", user.getUserName());
	}
}
