package com.lima.dto;

public class EstimatedLandedCost {
	String ranking;
	String margin;
	String productLandedCost;
	String productShippingCost;
	String productCost;
	String transitTime;
	String estDeliveryDate;
	String originPort;
	String mode;
	String destinationPort;
	String equipment;
	
	public EstimatedLandedCost(String ranking,String margin,String productLandedCost,String productShippingCost,String productCost,String transitTime,String estDeliveryDate,String originPort,String mode,String destinationPort,String equipment){
		super();
		this.ranking=ranking;
		this.margin=margin;
		this.productLandedCost=productLandedCost;
		this.productShippingCost=productShippingCost;
		this.productCost=productCost;
		this.transitTime=transitTime;
		this.estDeliveryDate=estDeliveryDate;
		this.originPort=originPort;
		this.mode=mode;
		this.destinationPort=destinationPort;
		this.equipment=equipment;

		
	}

	public String getRanking() {
		return ranking;
	}

	public String getMargin() {
		return margin;
	}

	public String getProductLandedCost() {
		return productLandedCost;
	}

	public String getProductShippingCost() {
		return productShippingCost;
	}

	public String getProductCost() {
		return productCost;
	}

	public String getTransitTime() {
		return transitTime;
	}

	public String getEstDeliveryDate() {
		return estDeliveryDate;
	}

	public String getOriginPort() {
		return originPort;
	}

	public String getMode() {
		return mode;
	}

	public String getDestinationPort() {
		return destinationPort;
	}

	public String getEquipment() {
		return equipment;
	}
	
	
	

}
