package com.lima.dto;

public class ForwarderReference {

	
	String vessel;
	String originPort;
	String destinationPort;
	String eta;
	String container;
	String vendor;
	String houseBill;
	String shipQty;
	String shipCtn;
	String status;
	String refValue;
	
		

	public ForwarderReference(String vessel, String originPort,
			String destinationPort, String eta, String container,
			String vendor, String houseBill, String shipQty, String shipCtn,
			String status,String refValue) {
		super();
		this.vessel = vessel;
		this.originPort = originPort;
		this.destinationPort = destinationPort;
		this.eta = eta;
		this.container = container;
		this.vendor = vendor;
		this.houseBill = houseBill;
		this.shipQty = shipQty;
		this.shipCtn = shipCtn;
		this.status = status;
		this.refValue = refValue;
		
	}
	
	
	public String getVessel() {
		return vessel;
	}
	public String getOriginPort() {
		return originPort;
	}
	public String getDestinationPort() {
		return destinationPort;
	}
	public String getEta() {
		return eta;
	}
	public String getContainer() {
		return container;
	}
	public String getVendor() {
		return vendor;
	}
	public String getHouseBill() {
		return houseBill;
	}
	public String getShipQty() {
		return shipQty;
	}
	public String getShipCtn() {
		return shipCtn;
	}
	public String getStatus() {
		return status;
	}

	public String getRefValue() {
		return refValue;
	}

	
	
	
	
}
