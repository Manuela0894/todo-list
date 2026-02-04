package com.backend.todolist.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_task")
public class Task {

	// Attributes
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column (name = "title", length = 100, nullable = false)
	private String title;
	@Column (name = "description")
	private String description;
	@Column (name = "isDone", nullable = false)
	private boolean isDone;
	@Column (name = "start_date")
	private LocalDate startDate;
	@Column (name = "end_date")
	private LocalDate endDate;

	@ManyToOne
	@JoinColumn(name="task_id")
	private Task todos;


	public Task(String title, String description, boolean isDone,
		LocalDate startDate, LocalDate endDate) {
		this.title = title;
		this.description = description;
		this.isDone = isDone;
		this.startDate = startDate;
		this.endDate = endDate;
	}



}
