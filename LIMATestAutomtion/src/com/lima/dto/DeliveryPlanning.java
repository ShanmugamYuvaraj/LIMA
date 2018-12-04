package com.lima.dto;

public class DeliveryPlanning {
	String wflReference;
	String sku;
	String lineType;
	String consignee;
	String quantity;
	String deliveryDate;
	
	
	public DeliveryPlanning(String wflReference,String sku, String lineType ,String consignee ,String quantity,String deliveryDate) {
		super();
		this.wflReference = wflReference;
		this.sku = sku;
		this.lineType = lineType;
		this.consignee = consignee;
		this.quantity = quantity;
		this.deliveryDate = deliveryDate;
	}


	public String getWflReference() {
		return wflReference;
	}


	public String getSku() {
		return sku;
	}


	public String getLineType() {
		return lineType;
	}


	public String getConsignee() {
		return consignee;
	}


	public String getQuantity() {
		return quantity;
	}


	public String getDeliveryDate() {
		return deliveryDate;
	}


	

	

}
