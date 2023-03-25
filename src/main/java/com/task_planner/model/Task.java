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
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer taskId;
	
	private String name;
	
	private String description;
	
	private LocalDateTime creationDateTime = LocalDateTime.now();
	
	private String bug;
	
	private String features;
	
	private String status;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Person> assignee = new ArrayList<>();
}
