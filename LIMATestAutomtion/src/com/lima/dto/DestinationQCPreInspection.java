package com.lima.dto;

public class DestinationQCPreInspection {
	
	String hbl;
	String certificateType;
	String containerNo;
	
	
	public DestinationQCPreInspection(String hbl,String certificateType, String containerNo) {
		super();
		this.hbl = hbl;
	
		this.certificateType = certificateType;
		this.containerNo = containerNo;
	}


	public String getHbl() {
		return hbl;
	}


	public String getCertificateType() {
		return certificateType;
	}


	public String getContainerNo() {
		return containerNo;
	}
	
	
	
	
	
	
	

}
