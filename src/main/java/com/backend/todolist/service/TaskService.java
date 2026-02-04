package com.backend.todolist.service;

import com.backend.todolist.dto.response.TaskResponse;
import com.backend.todolist.entity.Task;
import com.backend.todolist.dto.request.TaskRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.backend.todolist.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

	// Attribute
	private final TaskRepository taskRepository;

	// Constructor
	public TaskService(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	// Get all tasks
	public List<TaskResponse> listAllTasks() {
		List<Task> tasksList = taskRepository.findAll();
		List<TaskResponse> tasksResponseList = new ArrayList<>();

		for (Task task : tasksList) {
			tasksResponseList.add(new TaskResponse(task));
		}

		return tasksResponseList;
	}

	// Get task by id
	public Optional<TaskResponse> listTaskById(Long id) {
		Optional<Task> taskOptional = taskRepository.findById(id);
		TaskResponse taskResponse = new TaskResponse();

		if (taskOptional != null) {
			Task task = taskOptional.get();
			taskResponse = new TaskResponse(task);
		}
		return Optional.of(taskResponse);
	}

	// Add new task
	public String addTask(TaskRequest addedTask) {
		Task task = new Task(addedTask.getTitle(), addedTask.getDescription(),
			addedTask.getIsDone(), addedTask.getStartDate(),
			addedTask.getEndDate());
		taskRepository.save(task);
		return "Tarefa adicionada com sucesso!";
	}

	// Update task
	public String updateTask(Long id, TaskRequest updatedTask) {
		Optional<Task> existingTask = taskRepository.findById(id);

		if (existingTask.isPresent()) {
			Task task = existingTask.get();
			task.setTitle(updatedTask.getTitle());
			task.setDescription(updatedTask.getDescription());
			task.setIsDone(updatedTask.getIsDone());
			task.setStartDate(updatedTask.getStartDate());
			task.setEndDate(updatedTask.getEndDate());
			taskRepository.save(task);

			TaskResponse taskResponse = new TaskResponse(task);
			return "Tarefa atualizada com sucesso!";
		} else {
			return "Tarefa não encontrada!";
		}
	}

	// Delete task
	public String deleteTask(Long id) {
		if (taskRepository.existsById(id)) {
			taskRepository.deleteById(id);
			return "Tarefa excluída com sucesso!";
		} else {
			return "Tarefa não encontrada!";
		}
	}

}
