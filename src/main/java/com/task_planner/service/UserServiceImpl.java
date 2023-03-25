package com.task_planner.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_planner.exception.SprintException;
import com.task_planner.exception.TaskException;
import com.task_planner.exception.UserException;
import com.task_planner.model.Person;
import com.task_planner.model.Sprint;
import com.task_planner.model.Task;
import com.task_planner.repository.PersonRepository;
import com.task_planner.repository.SprintRepository;
import com.task_planner.repository.TaskRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private PersonRepository pRepo;
	
	@Autowired
	private SprintRepository sRepo;
	
	@Autowired
	private TaskRepository tRepo;

	@Override
	public Person registerUser(Person user) throws UserException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(user.getEmail());
		
		if(person.isEmpty()) {
			Person registered = pRepo.save(user);
			return registered;
		}
		throw new UserException("User with this email Id "+ user.getEmail() +" already exist in the system");
	}

	@Override
	public String addNewSprint(String email, Sprint sprint) throws UserException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(email);
		
		if(person.isEmpty()) {
			
			throw new UserException("User with this email Id "+ email +" does not exist in the system");

		}
		person.get().getSprints().add(sprint);
		
		return "New sprint has been added successfully";
		
	}

	@Override
	public String addNewTask(String email, Task task) throws UserException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(email);
		
		if(person.isEmpty()) {
			
			throw new UserException("User with this email Id "+ email +" does not exist in the system");

		}
		person.get().getTasks().add(task);
		
		return "New task has been added successfully";
	}

	@Override
	public List<Sprint> getAllSprint(String email) throws UserException, SprintException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(email);
		
		if(person.isEmpty()) {
			
			throw new UserException("User with this email Id "+ email +" does not exist in the system");

		}
		
		
		List<Sprint> sprint = person.get().getSprints();
		
		if(sprint.isEmpty()) {
			throw new SprintException("There is no sprrint added by "+ person.get().getName() + " yet.");
		}
		
		return sprint;
	}

	@Override
	public List<Task> getAllTaskAccordingToSprint(String email, Integer sprintId) throws TaskException, UserException, SprintException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(email);
		
		if(person.isEmpty()) {
			
			throw new UserException("User with this email Id "+ email +" does not exist in the system");

		}
		Optional<Sprint> sprint = sRepo.findById(sprintId);
		
		if(sprint.isEmpty()) {
			throw new SprintException("No sprint is added with the given id by " + person.get().getName());
		}
		List<Task> task = sprint.get().getTasks();
		
		if(task.isEmpty()) {
			throw new TaskException("No task is present in the given sprint.");
		}
		
		return task;
	}

	@Override
	public List<Task> getAllTask(String email) throws TaskException, UserException, SprintException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(email);
		
		if(person.isEmpty()) {
			
			throw new UserException("User with this email Id "+ email +" does not exist in the system");

		}
		
		return person.get().getTasks();
	}

	@Override
	public Sprint updateSprint(Sprint sprint) throws UserException, SprintException {
		// TODO Auto-generated method stub

		Optional<Sprint> updatedSprint = sRepo.findById(sprint.getSprintId());
		if(updatedSprint.isEmpty()) {
			throw new SprintException("No sprint is present in the system with the given sprintId.");
		}
		
		return updatedSprint.get();
	}

	@Override
	public Task updateTask(Task task) throws UserException, TaskException {
		// TODO Auto-generated method stub
		Optional<Task> updatedTask = tRepo.findById(task.getTaskId());
		if(updatedTask.isEmpty()) {
			throw new TaskException("No Task is present in the system with the given taskId.");
		}
		
		return updatedTask.get();
	}

	@Override
	public String addNewAssigneeToTheTask(Integer taskId, Person person) throws TaskException, UserException {
		// TODO Auto-generated method stub
		Optional<Task> task = tRepo.findById(taskId);
		
		if(task.isEmpty()) {
			throw new TaskException("No Task is present in the system with the given taskId.");
		}
		
		Optional<Person> assignee = pRepo.findByEmail(person.getEmail());
		
		if(assignee.isEmpty()) {
			throw new UserException("User with this email Id "+ person.getEmail() +" does not exist in the system");
		}
		
		task.get().getAssignee().add(person);
		assignee.get().getTasks().add(task.get());
		
		return "New assignee has been added to the task with Id " + task.get().getTaskId() + " and with name " + task.get().getName();
	}

	@Override
	public Person getUserDetailsByEmail(String email) throws UserException {
		// TODO Auto-generated method stub
		Optional<Person> person = pRepo.findByEmail(email);
		
		if(person.isEmpty()) {
			throw new UserException("User with this email Id "+ person.get().getEmail() +" does not exist in the system");
		}
		return person.get();
	}

	@Override
	public List<Person> getAllUserDetails() throws UserException {
		// TODO Auto-generated method stub
		List<Person> persons = pRepo.findAll();
		
		if(persons.isEmpty()) {
			throw new UserException("No exist in the system");
		}
		
		return persons;
	}

	

}
