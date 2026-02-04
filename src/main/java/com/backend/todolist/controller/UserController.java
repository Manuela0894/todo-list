package com.backend.todolist.controller;

import com.backend.todolist.dto.request.UserRequest;
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

@RestController
@RequestMapping (value = "/users")
public class UserController {

	// Attribute
	private final UserService userService;

	// Constructor
	public UserController(UserService userService) {
		this.userService = userService;
	}

	// Endpoints
	@GetMapping (value = "/all")
	public ResponseEntity<?> listAllUsers() {
		return ResponseEntity.ok(userService.listAllUsers());
	}

	@GetMapping (value = "/find/{id}")
	public ResponseEntity<?> listUserById(@PathVariable Long id) {
		return ResponseEntity.ok(userService.listUserById(id));
	}

	@PostMapping (value = "/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserRequest addedUser) {
		return ResponseEntity.ok(userService.signUp(addedUser));
	}

	@PostMapping (value = "/signin")
	public ResponseEntity<?> signIn(@Valid @RequestBody UserRequest userRequest) {
		return ResponseEntity.ok(userService.signIn(userRequest));
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
