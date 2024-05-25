package com.blog.app.users.dto;

import org.springframework.lang.NonNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class LoginUserRequest {

	@NonNull
	private String userName;
	@NonNull
	private String password;

}
