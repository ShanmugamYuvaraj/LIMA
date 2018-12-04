package com.lima.dto;

public class Certificate {

	
	String Certified;
	String WFLReference;
	String Supplier;
	String Product;

	
	public Certificate(String certified, String wFLReference, String supplier,
			String product) {
		super();
		this.Certified = certified;
		this.WFLReference = wFLReference;
		this.Supplier = supplier;
		this.Product = product;
	}
	

	public String getCertified() {
		return Certified;
	}


	public void setCertified(String certified) {
		Certified = certified;
	}


	public String getWFLReference() {
		return WFLReference;
	}


	public void setWFLReference(String wFLReference) {
		WFLReference = wFLReference;
	}


	public String getSupplier() {
		return Supplier;
	}


	public void setSupplier(String supplier) {
		Supplier = supplier;
	}


	public String getProduct() {
		return Product;
	}


	public void setProduct(String product) {
		Product = product;
	}




	
	
}
