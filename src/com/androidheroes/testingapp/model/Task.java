package com.androidheroes.testingapp.model;

public class Task {

	private int id = -1;
	private String title 		= null;
	private String description = null;	

	public Task(int new_id, String new_title, String new_description){
		id = new_id;
		title = new_title;
		description = new_description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}