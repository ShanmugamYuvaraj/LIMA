package com.lima.dto;

public class BookingConfirmation {
	
	String destination;
	String bookingRef;
	String bookingDate;
	String bookingTime;
	String duration;
	String deliveryMethod;
	String container;
	String orderType;
	String poNumber;
	String status;
	
	public BookingConfirmation(String destination, String bookingRef,
			String bookingDate, String bookingTime, String duration,
			String deliveryMethod, String container, String orderType)
	{
		super();
		this.destination = destination;
		this.bookingRef = bookingRef;
		this.bookingDate = bookingDate;
		this.bookingTime = bookingTime;
		this.duration = duration;
		this.deliveryMethod = deliveryMethod;
		this.container = container;
		this.orderType = orderType;
		
	}
	public BookingConfirmation(String poNumber, String status)
	{
		super();
		this.poNumber = poNumber;
		this.status = status;
	}

	public String getDestination() {
		return destination;
	}

	public String getBookingRef() {
		return bookingRef;
	}

	public String getBookingDate() {
		return bookingDate;
	}

	public String getBookingTime() {
		return bookingTime;
	}

	public String getDuration() {
		return duration;
	}

	public String getDeliveryMethod() {
		return deliveryMethod;
	}

	public String getContainer() {
		return container;
	}

	public String getOrderType() {
		return orderType;
	}

	public String getPoNumber() {
		return poNumber;
	}
	public String getStatus() {
		return status;
	}
	
	
	
	
	
	
	

}
