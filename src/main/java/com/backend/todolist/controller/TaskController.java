package com.backend.todolist.controller;

import com.backend.todolist.dto.request.TaskRequest;
import com.backend.todolist.service.TaskService;

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
@RequestMapping (value = "/tasks")
public class TaskController {

	// Attribute
	private final TaskService taskService;

	// Constructor
	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	// Endpoints
	@GetMapping (value = "/all")
	public ResponseEntity<?> listAllTasks() {
		return ResponseEntity.ok(taskService.listAllTasks());
	}

	@GetMapping (value = "/find/{id}")
	public ResponseEntity<?> listTaskById(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.listTaskById(id));
	}

	@PostMapping (value = "/add")
	public ResponseEntity<?> addTask(@Valid @RequestBody TaskRequest addedTask) {
		return ResponseEntity.ok(taskService.addTask(addedTask));
	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest updatedTask) {
		return ResponseEntity.ok(taskService.updateTask(id, updatedTask));
	}

	@DeleteMapping (value = "/delete/{id}")
	public ResponseEntity<?> deleteTask(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.deleteTask(id));
	}

}
