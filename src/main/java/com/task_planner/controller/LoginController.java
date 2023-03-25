package com.task_planner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_planner.model.Person;
import com.task_planner.repository.PersonRepository;

@RestController
public class LoginController {

	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping("/signIn")
	public ResponseEntity<Person> getLoggedInUserDetailsHandler(Authentication auth){
		
		Person person = personRepository.findByEmail(auth.getName()).orElseThrow(() -> new BadCredentialsException("Invalid Username of password"));
		
		return new ResponseEntity<>(person, HttpStatus.ACCEPTED);
	}
}
