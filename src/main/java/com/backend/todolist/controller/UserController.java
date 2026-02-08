package com.backend.todolist.controller;

import com.backend.todolist.dto.request.UserRequest;
import com.backend.todolist.dto.response.UserResponse;
import com.backend.todolist.service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping (value = "/user")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping (value = "/all")
	public ResponseEntity<?> listAllUsers() {
		return ResponseEntity.ok(userService.listAllUsers());
	}

	@GetMapping (value = "/find/{id}")
	public ResponseEntity<?> listUserById(@PathVariable Long id) {
		List<UserResponse> users = userService.listUserById(id);
		return ResponseEntity.ok(users);
	}

	@PostMapping (value = "/create")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest createUser) {
		return ResponseEntity.ok(userService.createUser(createUser));
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest updatedUser) {
		return ResponseEntity.ok(userService.updateUser(id, updatedUser));
	}

	@DeleteMapping (value = "/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok(userService.deleteUser(id));
	}
}
