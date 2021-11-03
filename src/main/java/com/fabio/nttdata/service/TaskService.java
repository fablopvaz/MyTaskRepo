package com.fabio.nttdata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fabio.nttdata.model.Task;
import com.fabio.nttdata.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	TaskRepository taskRepository;
	
	public Task  addTask(Task task) {
		return taskRepository.save(task);
	}
	
	public List<Task> getAllTasks(){
		return taskRepository.findAll();
	}
	
	public void removeTask(String id) {
		taskRepository.deleteById(id);
	}
	
	public Task modifyTask(Task modified_task) {
		
		Task existingTask = taskRepository.getById(modified_task.getId());
		existingTask.setStatus(modified_task.getStatus());
		
		return taskRepository.save(existingTask);
	}
	
}
