package com.lima.dto;


public class DocumentReceived {
	
	String OriginPort;
	String DestinationPort;
	String Mode;
	String Vessel;
	String Voyage;
	String Carrier;
	String CTD;
	String ETA;
	
	String HBL;
	String Vendor;
	
	String Courier;
	String CourierAWB;
	
	String Status;
	String Dialog;
	
	String FileName;
	String Size;
	
	
	
	public DocumentReceived(String HBL,String Status) {
        super();
        this.HBL = HBL;
        this.Status = Status;
        this.FileName= HBL;
        this.Size= Status;
        
	}
	
	public DocumentReceived(String Status) {
        super();
       this.Status = Status;
        
	}
	
	
	
	public DocumentReceived(String HBL,String Vendor,String Nulll) {
        super();
        this.HBL = HBL;
        this.Vendor = Vendor;
        
	}
	

	public DocumentReceived(String hBL, String vendor, String courier,
			String courierAWB, String status, String dialog) {
		super();
		this.HBL = hBL;
		this.Vendor = vendor;
		this.Courier = courier;
		this.CourierAWB = courierAWB;
		this.Status = status;
		this.Dialog = dialog;
	}




	public String getFileName() {
		return FileName;
	}

	public void setFileName(String fileName) {
		FileName = fileName;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}

	public String getOriginPort() {
		return OriginPort;
	}
	
	
	public DocumentReceived(String originPort, String destinationPort,
			String mode, String vessel, String voyage,
			String cTD, String eTA) {
		super();
		this.OriginPort = originPort;
		this.DestinationPort = destinationPort;
		this.Mode = mode;
		this.Vessel = vessel;
		this.Voyage = voyage;
		
		this.CTD = cTD;
		this.ETA = eTA;
	}

	public void setOriginPort(String originPort) {
		OriginPort = originPort;
	}
	public String getDestinationPort() {
		return DestinationPort;
	}
	public void setDestinationPort(String destinationPort) {
		DestinationPort = destinationPort;
	}
	public String getMode() {
		return Mode;
	}
	public void setMode(String mode) {
		Mode = mode;
	}
	public String getVessel() {
		return Vessel;
	}
	public void setVessel(String vessel) {
		Vessel = vessel;
	}
	public String getVoyage() {
		return Voyage;
	}
	public void setVoyage(String voyage) {
		Voyage = voyage;
	}
	public String getCarrier() {
		return Carrier;
	}
	public void setCarrier(String carrier) {
		Carrier = carrier;
	}
	public String getCTD() {
		return CTD;
	}
	public void setCTD(String cTD) {
		CTD = cTD;
	}
	public String getETA() {
		return ETA;
	}
	public void setETA(String eTA) {
		ETA = eTA;
	}
	public String getHBL() {
		return HBL;
	}
	public void setHBL(String hBL) {
		HBL = hBL;
	}
	public String getVendor() {
		return Vendor;
	}
	public void setVendor(String vendor) {
		Vendor = vendor;
	}
	public String getCourier() {
		return Courier;
	}
	public void setCourier(String courier) {
		Courier = courier;
	}
	public String getCourierAWB() {
		return CourierAWB;
	}
	public void setCourierAWB(String courierAWB) {
		CourierAWB = courierAWB;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDialog() {
		return Dialog;
	}
	public void setDialog(String dialog) {
		Dialog = dialog;
	}

}
