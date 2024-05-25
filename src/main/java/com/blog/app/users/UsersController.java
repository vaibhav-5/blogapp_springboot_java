package com.blog.app.users;

import java.net.URI;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.common.dto.ErrorResponse;
import com.blog.app.users.UsersService.UserNotFoundException;
import com.blog.app.users.dto.CreateUserRequest;
import com.blog.app.users.dto.UserResponse;
import com.blog.app.users.dto.LoginUserRequest;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ModelMapper modelMapper;

	@PostMapping("")
	ResponseEntity<UserResponse> signupUser(@RequestBody CreateUserRequest userDto) {
		UserEntity savedUser = usersService.createUser(userDto);
		URI savedUserUri = URI.create("/users/" +savedUser.getId());
		return ResponseEntity.created(savedUserUri).body(modelMapper.map(savedUser, UserResponse.class));
	}
	
	@PostMapping("/login")
	ResponseEntity<UserResponse> loginUser(@RequestBody LoginUserRequest request) {
		UserEntity savedUser = usersService.loginUser(request.getUserName(), request.getPassword());
		return ResponseEntity.ok(modelMapper.map(savedUser, UserResponse.class));
	}
	
	@ExceptionHandler({UserNotFoundException.class})
	ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex){
		String message;
		HttpStatus status;
		
		if(ex instanceof UserNotFoundException) {
			message = ex.getMessage();
			status = HttpStatus.NOT_FOUND;
		} else {
			message = "Something went wrong";
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		ErrorResponse response = ErrorResponse.builder()
				.message(message)
				.build();
		return ResponseEntity
				.status(status)
				.body(response);
	}
}
