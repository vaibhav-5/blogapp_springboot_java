package com.blog.app.users;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.users.dto.CreateUserRequest;

@Service
public class UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public UserEntity createUser(CreateUserRequest userDto) {
		UserEntity newUser = modelMapper.map(userDto, UserEntity.class);
		/*var newUser = UserEntity.builder()
				.userName(userDto.getUserName())
				.email(userDto.getEmail())
				.build();*/
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
	
}
