package com.backend.todolist.dto.response;

import com.backend.todolist.entity.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class TaskResponse {

	// Attributes
	private String title;
	private String description;
	private boolean isDone;
	private LocalDate startDate;
	private LocalDate endDate;

	public TaskResponse(Task task) {
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.isDone = task.isDone();
		this.startDate = task.getStartDate();
		this.endDate = task.getEndDate();
	}



}
