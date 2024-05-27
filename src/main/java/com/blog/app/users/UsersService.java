package com.blog.app.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.app.users.dto.CreateUserRequest;

@Service
public class UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserEntity createUser(CreateUserRequest userDto) {
		UserEntity newUser = modelMapper.map(userDto, UserEntity.class);
		newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
		
		return userRepository.save(newUser);
	}
	
	public UserEntity getUser(String userName) {
		return userRepository.findByUserName(userName).orElseThrow(()-> new UserNotFoundException(userName)); 
	}
	
	public UserEntity getUser(Long userId) {
		return userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId)); 
	}
	
	public UserEntity loginUser(String userName, String password) {
		var user = userRepository.findByUserName(userName).orElseThrow(()-> new UserNotFoundException(userName));
		var passMatch = passwordEncoder.matches(password, user.getPassword());
		if(!passMatch) {
			throw new InvalidCredentialsException();
		}
		return user;
	}
	
	public static class UserNotFoundException extends IllegalArgumentException{
		
		public UserNotFoundException(String userName) {
			super("User with userName " + userName + " not found.");
		}
		
		public UserNotFoundException(Long id) {
			super("User with id " + id + " not found.");
		}
	}
	
public static class InvalidCredentialsException extends IllegalArgumentException{
		
		public InvalidCredentialsException() {
			super("Invalid username or password");
		}
		
	}
	
}
