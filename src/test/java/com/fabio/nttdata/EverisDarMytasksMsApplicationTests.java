package com.fabio.nttdata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import com.fabio.nttdata.controller.TaskController;
import com.fabio.nttdata.model.Task;
import com.fabio.nttdata.repository.TaskRepository;
import com.fabio.nttdata.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class EverisDarMytasksMsApplicationTests {

	@Autowired
	TaskController taskController;
	
	@Autowired
	TaskRepository taskRepository;
	
	@MockBean
	TaskService taskService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	public void addTaskTest() {
		
		Task task = buildTask("1");
		
		when(taskService.addTask(task)).thenReturn(task);
		
		ResponseEntity<String> response = taskController.createTask(task);
		assertEquals(response.getBody(), "Task created successfully");
	}
	@Test
	public void addTaskTest2() throws Exception {
		
		List<Task> allTasks = new ArrayList<>();
		allTasks.add(buildTask("1"));
		allTasks.add(buildTask("2"));
		
		when(taskService.getAllTasks()).thenReturn(allTasks);
		
		mockMvc.perform(get("/tasks"))
			.andExpect(status().isOk()).andExpect(jsonPath("$.length()",is(2)));
	}
	
	@Test
	public void addTaskControllerTest() throws Exception {
		Task task = buildTask("1");
		ObjectMapper map = new ObjectMapper();
		String jsonString = map.writeValueAsString(task);
		
		
		when(taskService.addTask(task)).thenReturn(task);
		
		mockMvc.perform(post("/tasks").contentType(MediaType.APPLICATION_JSON)
				.content(jsonString)).andExpect(status().isCreated());
		
		
	}
	@Test
	public void deleteTaskTest() throws Exception {
		Task task = buildTask("1");
		ObjectMapper map = new ObjectMapper();
		String jsonString = map.writeValueAsString(task);
		
		doNothing().when(taskService).removeTask(task.getId());
		mockMvc.perform(delete("/tasks").contentType(MediaType.APPLICATION_JSON).content(jsonString))
		.andExpect(status().isOk());
		
	}
	
	
	@Test
	public void updateTaskStatusTest() {
		
		Task modified_task = buildTask("1");
		Task modified_task2 = buildTask("2"); 
		
		/* Task not modified correctly*/
		when(taskService.modifyTask(modified_task)).thenReturn(modified_task2);
		
		ResponseEntity<Task> response = taskController.updateTaskStatus(modified_task);
		assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
		
		
	}
	
	public Task buildTask(String id) {
		Task task = new Task();
		task.setId(id);
		task.setStatus("Test");
		task.setContent("Tarea de prueba");
		return task;
	}

}
