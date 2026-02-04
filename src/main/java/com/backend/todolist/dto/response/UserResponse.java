package com.backend.todolist.dto.response;

import com.backend.todolist.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

	// Attributes
	private long id;
	private String name;
	private String email;

	public UserResponse(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}


}
