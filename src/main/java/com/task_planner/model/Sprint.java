package com.task_planner.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Sprint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer sprintId;
	
	private String sprintDescription;
	
	private LocalDateTime creationDateTime = LocalDateTime.now();
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tasks",fetch=FetchType.EAGER)
	private List<Task> tasks = new ArrayList<>();
}
