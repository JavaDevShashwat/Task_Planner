package com.task_planner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task_planner.model.Person;
import com.task_planner.model.Sprint;
import com.task_planner.model.Task;
import com.task_planner.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/user")
	public ResponseEntity<Person> registerUserHandler(@RequestBody Person person){
		
		person.setRole("ROLE_" + person.getRole().toUpperCase());
		
		person.setPassword(passwordEncoder.encode(person.getPassword()));
		
		Person registeredUser = userService.registerUser(person);
		
		return new ResponseEntity<>(registeredUser,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/sprint/{email}")
	public ResponseEntity<String> addNewSprint(@PathVariable("email") String email, @RequestBody Sprint sprint){
		
		String response = userService.addNewSprint(email, sprint);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/task/{email}")
	public ResponseEntity<String> addNewTask(@PathVariable("email") String email, @RequestBody Task task){
		
		String response = userService.addNewTask(email, task);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/sprint/{email}")
	public ResponseEntity<List<Sprint>> getAllSprintofTheUser(@PathVariable("email") String email){
		
		List<Sprint> response = userService.getAllSprint(email);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/task/{email}/ {id}")
	public ResponseEntity<List<Task>> getAllTaskAccordingToSprintOfTheUser(@PathVariable("email") String email, @PathVariable("id") Integer sprintId){
		
		List<Task> response = userService.getAllTaskAccordingToSprint(email, sprintId);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/task/{email}")
	public ResponseEntity<List<Task>> getAllTaskofTheUser(@PathVariable("email") String email){
		
		List<Task> response = userService.getAllTask(email);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/sprint")
	public ResponseEntity<Sprint> updateSprintOfAUser(@RequestBody Sprint sprint){
		
		Sprint response = userService.updateSprint(sprint);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/task")
	public ResponseEntity<Task> updateTaskOfAUser(@RequestBody Task task){
		
		Task response = userService.updateTask(task);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/assignee/{taskId}")
	public ResponseEntity<String> addNewAssigneeToTheTask(@PathVariable("taskId") Integer taskId, @RequestBody Person person){
		
		String response = userService.addNewAssigneeToTheTask(taskId, person);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user/{email}")
	public ResponseEntity<Person> getUserDetailsByEmail(@PathVariable("email") String email){
		Person response = userService.getUserDetailsByEmail(email);
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/user")
	public ResponseEntity<List<Person>> getUserDetailsByEmail(){
		List<Person> response = userService.getAllUserDetails();
		
		return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
	}
	
	
}
