package com.lima.dto;

public class PurchaseOrders {

	String poNumber;
	String vendor;
	String venRef;

	String factory;
	String agent;
	String shipDate;

	String deliveyDate;
	String poQty;
	String priority;

	String staus;
	String orderDetails;
	String dialogs;


	public PurchaseOrders(String poNumber, String vendor, String venRef,
			String factory, String agent, String shipDate, String deliveyDate,
			String poQty, String priority, String staus, String orderDetails,
			String dialogs) {
		super();
		this.poNumber = poNumber;
		this.vendor = vendor;
		this.venRef = venRef;
		this.factory = factory;
		this.agent = agent;
		this.shipDate = shipDate;
		this.deliveyDate = deliveyDate;
		this.poQty = poQty;
		this.priority = priority;
		this.staus = staus;
		this.orderDetails = orderDetails;
		this.dialogs = dialogs;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getVender() {
		return vendor;
	}

	public void setVender(String vender) {
		this.vendor = vender;
	}

	public String getVenRef() {
		return venRef;
	}

	public void setVenRef(String venRef) {
		this.venRef = venRef;
	}

	public String getFactory() {
		return factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getDeliveyDate() {
		return deliveyDate;
	}

	public void setDeliveyDate(String deliveyDate) {
		this.deliveyDate = deliveyDate;
	}

	public String getPoQty() {
		return poQty;
	}

	public void setPoQty(String poQty) {
		this.poQty = poQty;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStaus() {
		return staus;
	}

	public void setStaus(String staus) {
		this.staus = staus;
	}

	public String getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(String orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getDialogs() {
		return dialogs;
	}

	public void setDialogs(String dialogs) {
		this.dialogs = dialogs;
	}

}
