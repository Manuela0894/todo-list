package com.backend.todolist.dto.request;

import com.backend.todolist.entity.Task;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class TaskRequest {

	@NotBlank
	private String title;
	@NotBlank
	private String description;
	private boolean isDone;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate startDate = LocalDate.now();
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate endDate;

	public TaskRequest(Task task) {
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.isDone = task.isDone();
		this.startDate = task.getStartDate();
		this.endDate = task.getEndDate();
	}

}
