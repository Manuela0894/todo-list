package com.backend.todolist.entity;

import com.backend.todolist.dto.request.UserRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name = "tb_users")
public class User {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column (unique = true)
	private String email;
	private String password;

	@OneToMany(mappedBy = "user")
	private List<Task> tasks = new ArrayList<>();

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(UserRequest userRequest ){
		this.name = userRequest.getName();
		this.email = userRequest.getEmail();
		this.password = userRequest.getPassword();

	}
}
