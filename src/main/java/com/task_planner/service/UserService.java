package com.task_planner.service;

import java.util.List;

import com.task_planner.exception.SprintException;
import com.task_planner.exception.TaskException;
import com.task_planner.exception.UserException;
import com.task_planner.model.Person;
import com.task_planner.model.Sprint;
import com.task_planner.model.Task;

public interface UserService {
	
	public Person registerUser(Person user)throws UserException;
	
	public String addNewSprint(String email, Sprint sprint)throws UserException;
	
	public String addNewTask(String email, Task task)throws UserException;
	
	public List<Sprint> getAllSprint(String email)throws SprintException;
	
	public List<Task> getAllTaskAccordingToSprint(String email, Integer sprintId)throws TaskException, UserException, SprintException;
	
	public List<Task> getAllTask(String email)throws TaskException, UserException, SprintException;
	
	public Sprint updateSprint(Sprint sprint)throws UserException, SprintException;
	
	public Task updateTask(Task task)throws UserException, TaskException;
	
	public String addNewAssigneeToTheTask(Integer taskId, Person person)throws TaskException, UserException;
	
	public Person getUserDetailsByEmail(String email)throws UserException;
	
	public List<Person> getAllUserDetails()throws UserException;
	
	

}
