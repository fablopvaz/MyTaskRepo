package com.fabio.nttdata.controller;

import org.springframework.stereotype.Component;

@Component
public class Greeting {
	
	private long id;
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
}
