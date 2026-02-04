package com.backend.todolist.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table (name = "tb_users")
public class User {

	// Attributes
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name = "name", length = 100)
	private String name;
	@Column (name = "email", length = 50, unique = true)
	private String email;
	@Column (name = "password", length = 50)
	private String password;

	@OneToMany(mappedBy="todos")
	private List<Task> tasks = new ArrayList<>();

	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
