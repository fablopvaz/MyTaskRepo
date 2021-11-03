package com.fabio.nttdata.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ControllerAdvisor {

	@ExceptionHandler(InvalidFormatException.class)
	public ResponseEntity<String> handleStatusTaskFormatException(){
		return new ResponseEntity<String>("Status format is not valid", HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<String> handleTaskNotFoundException(){
		return new ResponseEntity<String>("This task does not exist", HttpStatus.NOT_FOUND);
	}
	
}
