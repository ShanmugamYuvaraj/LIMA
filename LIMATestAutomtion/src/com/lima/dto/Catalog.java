package com.lima.dto;

public class Catalog {

	String description;
	String type;
	
	
	public Catalog(String description, String type) {
		super();
		this.description = description;
		this.type = type;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
