package com.lima.dto;

public class Despatch {
	
	String strContainer;
    String strStatus;
    String strOriginPort;
    String strFeederVessel;
    String strFeederVoyage;
    String strFeederETD;
    String strFeederETA;
    String strDestinationPort;
    String strCarrier;
    String strMotherVessel;
    String strMotherVoyage;
    String strMotherETD; 
    String strMotherETA;
    String strSeal;
    String strEquipment;
    
    String strManifestOriginPort;
    String strManifestCarrier;
    String strManifestETD;
    String strManifestETA;
    String strManifestVessel;
    String strManifestContainer;
    String strManifestEquipment;
    
    
    /*public Despatch(String strContainer, String strOriginPort, String strCarrier, String strMotherVessel, String strMotherETD,
    		        String strMotherETA,String strEquipment)
    {
    	super();
    	this.strOriginPort = strOriginPort;
    	this.strCarrier = strCarrier;
    	this.strMotherVessel = strMotherVessel;
    	this.strMotherETD = strMotherETD;
    	this.strMotherETA = strMotherETA;
    	this.strContainer = strContainer;
    	this.strSeal = strSeal;
    	this.strEquipment = strEquipment;
    	
    }*/
    
    public Despatch(String strManifestOriginPort,String strManifestCarrier,String strManifestETD,String strManifestETA,
    		        String strManifestVessel,String strManifestContainer,String strManifestEquipment)
    {
    	super();
    	this.strManifestOriginPort = strManifestOriginPort;
    	this.strManifestCarrier = strManifestCarrier;
    	this.strManifestETD = strManifestETD;
    	this.strManifestETA = strManifestETA;
    	this.strManifestVessel = strManifestVessel;
    	this.strManifestContainer = strManifestContainer;
    	this.strManifestEquipment = strManifestEquipment;
    }
       
	public Despatch(String strContainer, String strOriginPort, String strCarrier, 
			String strMotherETD, String strMotherETA, String strEquipment, String strMotherVessel,String strMotherVoyage) {
		super();
		this.strContainer = strContainer;
		this.strOriginPort = strOriginPort;
		this.strCarrier = strCarrier;
		this.strMotherVoyage = strMotherVoyage;
		this.strMotherETD = strMotherETD;
		this.strMotherETA = strMotherETA;
		this.strEquipment = strEquipment;
	}





	public Despatch(String strContainer, String strStatus,
			String strOriginPort, String strFeederVessel,
			String strFeederVoyage, String strFeederETD, String strFeederETA,
			String strDestinationPort, String strCarrier,
			String strMotherVessel, String strMotherVoyage,
			String strMotherETD, String strMotherETA) {
		super();
		this.strContainer = strContainer;
		this.strStatus = strStatus;
		this.strOriginPort = strOriginPort;
		this.strFeederVessel = strFeederVessel;
		this.strFeederVoyage = strFeederVoyage;
		this.strFeederETD = strFeederETD;
		this.strFeederETA = strFeederETA;
		this.strDestinationPort = strDestinationPort;
		this.strCarrier = strCarrier;
		this.strMotherVessel = strMotherVessel;
		this.strMotherVoyage = strMotherVoyage;
		this.strMotherETD = strMotherETD;
		this.strMotherETA = strMotherETA;
	}
	
	public String getStrManifestOriginPort() {
		return strManifestOriginPort;
	}
	
	public String getStrManifestCarrier() {
		return strManifestCarrier;
	}

	public String getStrManifestETD() {
		return strManifestETD;
	}

	public String getStrManifestETA() {
		return strManifestETA;
	}
    
	public String getStrManifestContainer() {
		return strManifestContainer;
	}
	
	
	public String getStrManifestVessel() {
		return strManifestVessel;
	}

	public String getStrContainer() {
		return strContainer;
	}

	public String getStrStatus() {
		return strStatus;
	}

	public String getStrOriginPort() {
		return strOriginPort;
	}

	public String getStrFeederVessel() {
		return strFeederVessel;
	}

	public String getStrFeederVoyage() {
		return strFeederVoyage;
	}

	public String getStrFeederETD() {
		return strFeederETD;
	}

	public String getStrFeederETA() {
		return strFeederETA;
	}

	public String getStrDestinationPort() {
		return strDestinationPort;
	}

	public String getStrCarrier() {
		return strCarrier;
	}

	public String getStrMotherVessel() {
		return strMotherVessel;
	}

	public String getStrMotherVoyage() {
		return strMotherVoyage;
	}

	public String getStrMotherETD() {
		return strMotherETD;
	}

	public String getStrMotherETA() {
		return strMotherETA;
	}

	public String getStrEquipment() {
		return strEquipment;
	}

	public String getStrManifestEquipment() {
		return strManifestEquipment;
	}

	public String getStrSeal() {
		return strSeal;
	}

	
    
    
    
    
}

