package com.blog.app.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
public class UsersRepositoryTests {
	
	@Autowired
	private UsersRepository userRepository;
	
	@Test
	@Order(1)
	void can_create_users() {
		UserEntity user = UserEntity.builder()
				.userName("brucew")
				.email("bruce@blog.com")
				.build();
		
		userRepository.save(user);
	}
	
	@Test
	@Order(2)
	void can_find_users() {
		UserEntity user = UserEntity.builder()
				.userName("brucew")
				.email("bruce@blog.com")
				.build();
		userRepository.save(user);
		var users = userRepository.findAll();
		assertEquals(1,users.size());
	}
}
