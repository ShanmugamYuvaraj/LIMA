package com.lima.dto;

public class ProductionCheck {
	
	String poNumber;
    String vendor;
    String venRef;
    
    String factory;
    String buyingAgent;
    String shipDate;
    
    String intendedShipDate;
    String poQty;
    String priority;
    
    String productionStaus;
    String dialogs;
    
    String productNo;
    String poShipDate;
    String mode;
    String description;
    String poQuantity;
    
    String productNoPoManager;
    String shipDatePoManager;
    String modePoManager;
    String descriptionPoManager;
    String poQuantityPoManager;
    
    public ProductionCheck(String poNumber, String vendor, String venRef,
            String factory, String buyingagent, String shipDate, String intendedShipDate,
            String poQty, String priority, String productionstaus,String dialogs){
    	
    	super();
    	this.poNumber = poNumber;
    	this.vendor = vendor;
    	this.venRef = venRef;
    	this.factory = factory;
    	this.buyingAgent = buyingagent;
    	this.shipDate = shipDate;
    	this.intendedShipDate = intendedShipDate;
    	this.poQty = poQty;
    	this.priority = priority;
    	this.productionStaus = productionstaus;
    	this.dialogs = dialogs;
    }
    
    
    
    public ProductionCheck(String productNo, String poShipDate, String mode,
			String description, String poQuantity) {
		super();
		this.productNo = productNo;
		this.poShipDate = poShipDate;
		this.mode = mode;
		this.description = description;
		this.poQuantity = poQuantity;
	}
    



	public ProductionCheck(String productNoPoManager, String shipDatePoManager,
			String modePoManager, String descriptionPoManager,
			String poQuantityPoManager,String poNumber) {
		super();
		this.productNoPoManager = productNoPoManager;
		this.shipDatePoManager = shipDatePoManager;
		this.modePoManager = modePoManager;
		this.descriptionPoManager = descriptionPoManager;
		this.poQuantityPoManager = poQuantityPoManager;
	}



	public String getPoNumber(){
    	
    	return poNumber;
    }
    
    public String getVendor(){
    	
    	return vendor;
    }
    
    public String getvenRef(){
    	
    	return venRef;
    }
    
    public String getfactory(){
    	
    	return factory;
    }

    public String getBuyingAgent(){
    	
    	return buyingAgent;
    }
    
    public String getShipDate(){
    	
    	return shipDate;
    }
   
    public String getIntentedShipDate(){
    	
    	return intendedShipDate;
    }
    
    public String getPoQty() {
    	
        return poQty;
    }
    
    public String getPriority() {
    	
        return priority;
    }

    public String getProductionStaus() {
    	
        return productionStaus;
    }
    
    public String getDialogs() {
    	
        return dialogs;
    }

	public String getVenRef() {
		return venRef;
	}

	public String getFactory() {
		return factory;
	}

	public String getIntendedShipDate() {
		return intendedShipDate;
	}

	public String getProductNo() {
		return productNo;
	}

	public String getPoShipDate() {
		return poShipDate;
	}

	public String getMode() {
		return mode;
	}

	public String getDescription() {
		return description;
	}

	public String getPoQuantity() {
		return poQuantity;
	}

	public String getProductNoPoManager() {
		return productNoPoManager;
	}

	public String getShipDatePoManager() {
		return shipDatePoManager;
	}

	public String getModePoManager() {
		return modePoManager;
	}

	public String getDescriptionPoManager() {
		return descriptionPoManager;
	}

	public String getPoQuantityPoManager() {
		return poQuantityPoManager;
	}
    
    		
    		


}
