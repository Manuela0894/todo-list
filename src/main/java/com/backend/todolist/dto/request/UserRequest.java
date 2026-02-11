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

	@NotBlank
	@Column(nullable = false)
	private String name;
	@NotBlank
	@Column(unique = true, nullable = false)
	private String email;

	public UserRequest(User user) {
		this.name = user.getName();
		this.email = user.getEmail();
	}

}
