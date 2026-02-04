package com.backend.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	// Attributes
	@NotBlank (message = "O nome não pode ser nulo")
	private String name;
	@NotBlank (message = "O email não pode ser nulo")
	private String email;
	@Size (min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres")
	private String password;

	public UserRequest(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

}
