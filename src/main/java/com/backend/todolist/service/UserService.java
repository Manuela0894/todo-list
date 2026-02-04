package com.backend.todolist.service;

import com.backend.todolist.dto.response.UserResponse;
import com.backend.todolist.entity.User;
import com.backend.todolist.dto.request.UserRequest;
import com.backend.todolist.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	// Attribute
	private final UserRepository userRepository;

	// Constructor
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Get all users
	public List<UserResponse> listAllUsers() {
		List<User> usersList = userRepository.findAll();
		List<UserResponse> usersResponseList = new ArrayList<>();

		for (User user : usersList) {
			usersResponseList.add(new UserResponse(user));
		}

		return usersResponseList;
	}

	// Get user by id
	public Optional<UserResponse> listUserById(Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		UserResponse userResponse = new UserResponse();

		if (userOptional != null) {
			User user = userOptional.get();
			userResponse = new UserResponse(user);
		}
		return Optional.of(userResponse);
	}

	// Sign-Up
	public String signUp(UserRequest addedUser) {
		User user = new User(addedUser.getName(), addedUser.getEmail(),
				addedUser.getPassword());
		userRepository.save(user);
		return "Usuário adicionado com sucesso!";
	}

	// Sign-In
	public String signIn(UserRequest userRequest) {
		User findUser = userRepository.findByEmail(userRequest.getEmail());
		if (findUser == null) {
			return "Usuário não encontrado";
		} else {
			if (findUser.getPassword().equals(userRequest.getPassword())) {
				return "Logado com sucesso!";
			} else {
				return "Senha incorreta!";
			}
		}
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
			return "Usuário atualizado com sucesso!";
		} else {
			return "Usuário não encontrado!";
		}
	}

	// Delete user
	public String deleteUser(Long id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "Usuário excluído com sucesso!";
		} else {
			return "Usuário não encontrado!";
		}
	}
}
