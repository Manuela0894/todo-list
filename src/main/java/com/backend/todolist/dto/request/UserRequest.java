package com.backend.todolist.dto.request;

import com.backend.todolist.entity.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	@NotBlank (message = "The name can't be null")
	@Column(nullable = false)
	private String name;
	@NotBlank (message = "The email can't be null")
	@Column(unique = true, nullable = false)
	private String email;
	@Size (min = 6, max = 20, message = "The password must be between 6 and 20 characters long")
	private String password;

	public UserRequest(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
	}

}
