package com.task_planner.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_planner.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer>{

	public Optional<Person> findByEmail(String email);
}
