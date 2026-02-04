package com.backend.todolist.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class TaskRequest {

	// Attributes
	@NotBlank(message = "Informe o título da tarefa")
	private String title;
	@NotBlank(message = "Informe a descrição da tarefa")
	private String description;
	private boolean isDone;
	private LocalDate startDate;
	private LocalDate endDate;

	public TaskRequest(String title, String description, boolean isDone,
		LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.description = description;
		this.isDone = isDone;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
