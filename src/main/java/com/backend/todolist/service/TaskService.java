package com.backend.todolist.service;

import com.backend.todolist.dto.response.TaskResponse;
import com.backend.todolist.entity.Task;
import com.backend.todolist.dto.request.TaskRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import com.backend.todolist.repository.TaskRepository;
import com.backend.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	private final TaskRepository taskRepository;
	private final UserRepository userRepository;

	public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
		this.userRepository = userRepository;
		this.taskRepository = taskRepository;
	}

	// Create a new task
	public String createTask(Long userId, TaskRequest request) {
		return userRepository.findById(userId).map(user -> {
			Task task = new Task(request);
			task.setUser(user);
			taskRepository.save(task);
			return "Task Successfully created";
		}).orElse("User not found");
	}

	// Read all tasks of a single user
	public List<TaskResponse> listAllTasksByUser(Long userId) {
		return taskRepository.findByUserId(userId)
				.stream().map(TaskResponse::new)
				.collect(Collectors.toList());
	}

	// Update task
	public String updateTask(Long userId, Long taskId, TaskRequest updatedTask) {
		return taskRepository.findById(taskId).map(task -> {
			if(!Objects.equals(task.getUser().getId(), userId)) {
				return "This user is not allowed to update this task";
			}else{
				task.setTitle(updatedTask.getTitle());
				task.setDescription(updatedTask.getDescription());
				task.setDone((updatedTask.isDone()));
				taskRepository.save(task);
				return "Task successfully updated";}
		}).orElse("Task or user not found");
	}

	// Delete task
	public String deleteByUser (Long userId, Long taskId) {
	return taskRepository.findById(taskId).map( task -> {
		if (!Objects.equals(task.getUser().getId(), (userId))){
			return "User not allowed to delete this task";
		}else{
			taskRepository.delete(task);
			return "Successfully deleted task";
		}
	}).orElse("Task or user not found");
	}
}


