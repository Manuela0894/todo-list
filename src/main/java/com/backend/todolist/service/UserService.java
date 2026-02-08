package com.backend.todolist.service;

import com.backend.todolist.dto.response.UserResponse;
import com.backend.todolist.entity.User;
import com.backend.todolist.dto.request.UserRequest;
import com.backend.todolist.repository.UserRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Get all users
	public List<UserResponse> listAllUsers() {
		return userRepository.findAll()
				.stream().map(UserResponse::new)
				.collect(Collectors.toList());
	}

	// Get user by id
	public List<UserResponse> listUserById(Long id) {
		return userRepository.findById(id).map(user -> List.of(new UserResponse(user)))
				.orElse(Collections.emptyList());
	}

	// Create user
	public String createUser(UserRequest request) {
		User user = new User(request);
		userRepository.save(user);
		return "User added successfully";
	}

	// Update user
	public String updateUser(Long id, UserRequest updatedUser) {
		Optional<User> existingUser = userRepository.findById(id);

		if (existingUser.isPresent()) {
			User user = existingUser.get();
			user.setName(updatedUser.getName());
			user.setEmail(updatedUser.getEmail());
			user.setPassword(updatedUser.getPassword());
			userRepository.save(user);

			UserResponse userResponse = new UserResponse(user);
			return "successfully updated";
		} else {
			return "User not found";
		}
	}

	// Delete user
	public String deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "User successfully deleted";
		} else {
			return "User not found";
		}
	}
}

