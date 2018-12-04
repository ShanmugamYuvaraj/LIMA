package com.lima.dto;

public class Tracksdata {
	
	String event;
	String acheived;
	String username;
	
	public Tracksdata(String event, String acheived, String username) {
		super();
		this.event = event;
		this.acheived = acheived;
		this.username = username;
	}

	public String getEvent() {
		return event;
	}
	
	public String getAcheived() {
		return acheived;
	}
	
	public String getUsername() {
		return username;
	}

}