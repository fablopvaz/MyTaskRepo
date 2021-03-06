package com.fabio.nttdata.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fabio.nttdata.model.Task;
import com.fabio.nttdata.service.TaskService;

import io.swagger.v3.oas.annotations.Hidden;


@CrossOrigin(origins= "*")
@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@GetMapping
	public List<Task> getAllTasks() {
		
		return taskService.getAllTasks();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Task createTask(@RequestBody Task task) {
		return taskService.addTask(task);
			
	}
	
	@DeleteMapping("/{id}")
	public void deleteTask(@PathVariable("id") String task_id) {
		taskService.removeTask(task_id);
	}
	
	@Hidden
	@PutMapping
	public Task updateTaskStatus(@RequestBody Task modified_task) {		
		return taskService.modifyTask(modified_task);
		
		
	}
	
	
}
