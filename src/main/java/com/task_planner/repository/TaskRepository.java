package com.task_planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.task_planner.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{

}
