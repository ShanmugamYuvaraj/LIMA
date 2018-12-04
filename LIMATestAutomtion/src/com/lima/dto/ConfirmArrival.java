package com.lima.dto;

public class ConfirmArrival {
	
	String Vessel;
	String DestinationPort;
	String Arrived;
	String Container;
	String OriginPort;
	String ForwardReference;
	String Status;
	
	public ConfirmArrival(String vessel, String destinationPort,
			String arrived, String container, String originPort,
			String forwardReference, String status) {
		super();
		this.Vessel = vessel;
		this.DestinationPort = destinationPort;
		this.Arrived = arrived;
		this.Container = container;
		this.OriginPort = originPort;
		this.ForwardReference = forwardReference;
		this.Status = status;
	}

	public String getVessel() {
		return Vessel;
	}

	public String getDestinationPort() {
		return DestinationPort;
	}

	public String getArrived() {
		return Arrived;
	}

	public String getContainer() {
		return Container;
	}

	public String getOriginPort() {
		return OriginPort;
	}

	public String getForwardReference() {
		return ForwardReference;
	}

	public String getStatus() {
		return Status;
	}
	
	

}
