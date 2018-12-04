package com.lima.dto;

public class FreightPayments {
	
	String strStatus;
	String strContainerNo;
	String strCarrier;
	String strCarrierType;
	String strEquipment;
	String strLoading;
	String strHBL;
	String strOriginPort;
	String strDestinationPort;
	String strETD;
	String strETA;
	
	public FreightPayments(String strStatus, String strContainerNo,
			String strCarrier, String strCarrierType) {
		super();
		this.strStatus = strStatus;
		this.strContainerNo = strContainerNo;
		this.strCarrier = strCarrier;
		this.strCarrierType = strCarrierType;
	}
	


	public FreightPayments(String strContainerNo, String strEquipment,
			String strLoading, String strHBL, String strOriginPort,
			String strDestinationPort, String strETD, String strETA) {
		super();
		this.strContainerNo = strContainerNo;
		this.strEquipment = strEquipment;
		this.strLoading = strLoading;
		this.strHBL = strHBL;
		this.strOriginPort = strOriginPort;
		this.strDestinationPort = strDestinationPort;
		this.strETD = strETD;
		this.strETA = strETA;
	}



	public String getStrStatus() {
		return strStatus;
	}

	public String getStrContainerNo() {
		return strContainerNo;
	}
	
	public String getStrCarrier() {
		return strCarrier;
	}


	public String getStrCarrierType() {
		return strCarrierType;
	}



	public String getStrEquipment() {
		return strEquipment;
	}



	public String getStrLoading() {
		return strLoading;
	}



	public String getStrHBL() {
		return strHBL;
	}



	public String getStrOriginPort() {
		return strOriginPort;
	}



	public String getStrDestinationPort() {
		return strDestinationPort;
	}



	public String getStrETD() {
		return strETD;
	}



	public String getStrETA() {
		return strETA;
	}

	
	
	
	

}
