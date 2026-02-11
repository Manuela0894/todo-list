package com.backend.todolist.entity;

import java.time.LocalDate;

import com.backend.todolist.dto.request.TaskRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
//compound constraint for titles to be allowed more than once in the db but only once for each user
@Table(name = "tb_task", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"title", "user_id"})
})
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (nullable = false)
	private String title;
	private String description;
	@Column (name = "is_done", nullable = false)
	private boolean isDone;
	@Column (name = "start_date")
	//default date
	private LocalDate startDate;
	@Column (name = "end_date")
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Task(String title, String description, boolean isDone,
		LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.description = description;
		this.isDone = isDone;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Task(TaskRequest taskRequest) {
		this.title = taskRequest.getTitle();
		this.description = taskRequest.getDescription();
		this.isDone = taskRequest.isDone();
		this.startDate = taskRequest.getStartDate();
		this.endDate = taskRequest.getEndDate();
	}

}
