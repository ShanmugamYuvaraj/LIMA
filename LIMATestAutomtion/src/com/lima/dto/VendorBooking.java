package com.lima.dto;

public class VendorBooking 
{
	String strPONumber;
	String strProduct;
	String strVendorRef;
	String strPOShipDate;
	String strCustomerRef;
	String strPOQty;
	String strPOCtns;
	String strOpenQty;
	String strBkdQty;
	String strBkdCtns;
	String strBkdCBM;
	String strStatus;
	
	public VendorBooking(String strPONumber, String strProduct,
			String strVendorRef, String strPOShipDate, String strCustomerRef,
			String strPOQty, String strOpenQty,
			String strBkdQty, String strBkdCtns, String strBkdCBM) 
	{
		super();
		this.strPONumber = strPONumber;
		this.strProduct = strProduct;
		this.strVendorRef = strVendorRef;
		this.strPOShipDate = strPOShipDate;
		this.strCustomerRef = strCustomerRef;
		this.strPOQty = strPOQty;
		this.strOpenQty = strOpenQty;
		this.strBkdQty = strBkdQty;
		this.strBkdCtns = strBkdCtns;
		this.strBkdCBM = strBkdCBM;
	}
	

	public VendorBooking(String strPONumber, String strStatus) {
		super();
		this.strPONumber = strPONumber;
		this.strStatus = strStatus;
	}


	public String getStrPONumber() {
		return strPONumber;
	}

	public String getStrProduct() {
		return strProduct;
	}

	public String getStrVendorRef() {
		return strVendorRef;
	}

	public String getStrPOShipDate() {
		return strPOShipDate;
	}

	public String getStrCustomerRef() {
		return strCustomerRef;
	}

	public String getStrPOQty() {
		return strPOQty;
	}

	public String getStrPOCtns() {
		return strPOCtns;
	}

	public String getStrOpenQty() {
		return strOpenQty;
	}

	public String getStrBkdQty() {
		return strBkdQty;
	}

	public String getStrBkdCtns() {
		return strBkdCtns;
	}

	public String getStrBkdCBM() {
		return strBkdCBM;
	}


	public String getStrStatus() {
		return strStatus;
	}
	
	
	
	
}
