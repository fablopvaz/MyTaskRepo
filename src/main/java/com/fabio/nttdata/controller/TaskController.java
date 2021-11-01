package com.fabio.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabio.nttdata.repository.TaskRepository;
import com.fabio.nttdata.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	TaskRepository repository;
	
	
//	@GetMapping("/{id}")
//	public Object getTaskById(@PathVariable(name="id") String id ) {
//		return repository.findById(id);
//	}
	
	@GetMapping
	public List<Task> getAllTasks() {
		
		return taskService.getAllTasks();
	}
	
	@PostMapping
	public ResponseEntity<String> createTask(@RequestBody Task task) {
		ResponseEntity<String> response = null;
		try {
			taskService.addTask(task);
			response = new ResponseEntity<String>("Task created successfully",HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("Repository is unavailable",HttpStatus.SERVICE_UNAVAILABLE);
		}
		return response;
	}
	
	@DeleteMapping
	public void deleteTask(@RequestBody() Task task) {
		taskService.removeTask(task.getId());
	}
	
	@PutMapping
	public ResponseEntity<Task> updateTaskStatus(@RequestBody Task modified_task) {
		
		ResponseEntity<Task> response = null;
		
		if(taskService.modifyTask(modified_task).getId().equals(modified_task.getId()))
			response = new ResponseEntity<>(modified_task,HttpStatus.CREATED);
		else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return response;
		
	}
	
	
}
