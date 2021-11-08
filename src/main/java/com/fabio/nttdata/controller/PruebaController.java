package com.fabio.nttdata.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
@RestController
@RequestMapping("/test")
public class PruebaController {
	
		
		@GetMapping
		public String prueba() {
			return "test";
		}
}
