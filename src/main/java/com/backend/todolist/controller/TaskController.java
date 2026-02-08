package com.backend.todolist.controller;

import com.backend.todolist.dto.request.TaskRequest;
import com.backend.todolist.dto.response.TaskResponse;
import com.backend.todolist.service.TaskService;
import org.springframework.http.HttpStatus;
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
@RequestMapping (value = "/task")
public class TaskController {

	private final TaskService taskService;

	public TaskController(TaskService taskService) {
		this.taskService = taskService;
	}

	// Get list of tasks by user id
	@GetMapping (value = "/list/{userId}")
	public ResponseEntity<?> listAllTasks(@Valid @PathVariable Long userId) {
		List<TaskResponse> tasks = taskService.listAllTasksByUser(userId);
		return ResponseEntity.ok(tasks);
	}

	// Create task by user id
	@PostMapping (value = "/create/{userId}")
	public ResponseEntity<String> addTask(@Valid @PathVariable Long userId, @RequestBody TaskRequest request) {
		String result = taskService.createTask(userId, request);
		if (result.equals("Task created successfully")) {
			return ResponseEntity.status(HttpStatus.CREATED).body(result);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}
	}

	//Update task by unique user
	@PutMapping(value = "/update/{userId}/{taskId}")
	public ResponseEntity<String> updateTask(@Valid @PathVariable Long userId, @PathVariable Long taskId, @RequestBody TaskRequest taskRequest){
		String result = taskService.updateTask(userId, taskId, taskRequest);
		if (result.equals("Task successfully updated")) {
			return ResponseEntity.ok(result);
		} else{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}
	}

	//Delete task by unique user
	@DeleteMapping(value = "/delete/{userId}/{taskId}")
	public ResponseEntity<String> deleteTask (@PathVariable Long userId, @PathVariable Long taskId){
		String result = taskService.deleteByUser(userId, taskId);
		if (result.equals("Task successfully deleted")) {
			return ResponseEntity.ok(result);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
		}

	}

}
