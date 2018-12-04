package com.lima.dto;


public class ProductOrderData {
	
	
	String poNumber;
	String status;
	
	String OriginPort;
	String Vessel;
	String ETD;
	String ETA;
	String Loading;
	String ReceivedDate;
	String Vendor;
	String VendorRef;
	String Product;
	String SKU;
	String identifier;
	String PackType;
	String BkdPcs;
	String BkdCartons;
	String BkdCdm;
	String DC;
	String QC;


	public ProductOrderData(String poNumber, String status) {
		super();
		this.poNumber = poNumber;
		this.status = status;
	}
	
	
	public ProductOrderData(String poNumber, String status, String originPort,
			String vessel, String eTD, String eTA, String loading,	String vendor,
			String product, String packType,
			String bkdPcs, String bkdCartons, String bkdCdm,String identifier,String SKU) {
		super();
		this.poNumber = poNumber;
		this.status = status;
		this.OriginPort = originPort;
		this.Vessel = vessel;
		this.ETD = eTD;
		this.ETA = eTA;
		this.Loading = loading;
		this.Vendor = vendor;
		this.Product = product;
		this.PackType = packType;
		this.BkdPcs = bkdPcs;
		this.BkdCartons = bkdCartons;
		this.BkdCdm = bkdCdm;
		this.identifier = identifier;
		this.SKU = SKU;
	}
	
	
	public ProductOrderData(String bkdPcs, String bkdCartons, String bkdCdm,String packType) {
		super();
		this.BkdPcs = bkdPcs;
		this.BkdCartons = bkdCartons;
		this.BkdCdm = bkdCdm;
		this.PackType = packType;
	}




	public String getOriginPort() {
		return OriginPort;
	}


	public void setOriginPort(String originPort) {
		OriginPort = originPort;
	}


	public String getVessel() {
		return Vessel;
	}


	public void setVessel(String vessel) {
		Vessel = vessel;
	}


	public String getETD() {
		return ETD;
	}


	public void setETD(String eTD) {
		ETD = eTD;
	}


	public String getETA() {
		return ETA;
	}


	public void setETA(String eTA) {
		ETA = eTA;
	}


	public String getLoading() {
		return Loading;
	}


	public void setLoading(String loading) {
		Loading = loading;
	}


	public String getReceivedDate() {
		return ReceivedDate;
	}


	public void setReceivedDate(String receivedDate) {
		ReceivedDate = receivedDate;
	}


	public String getVendor() {
		return Vendor;
	}


	public void setVendor(String vendor) {
		Vendor = vendor;
	}


	public String getVendorRef() {
		return VendorRef;
	}


	public void setVendorRef(String vendorRef) {
		VendorRef = vendorRef;
	}


	public String getProduct() {
		return Product;
	}


	public void setProduct(String product) {
		Product = product;
	}


	public String getSKU() {
		return SKU;
	}


	public void setSKU(String sKU) {
		SKU = sKU;
	}


	public String getIdentifier() {
		return identifier;
	}


	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	public String getPackType() {
		return PackType;
	}


	public void setPackType(String packType) {
		PackType = packType;
	}


	public String getBkdPcs() {
		return BkdPcs;
	}


	public void setBkdPcs(String bkdPcs) {
		BkdPcs = bkdPcs;
	}


	public String getBkdCartons() {
		return BkdCartons;
	}


	public void setBkdCartons(String bkdCartons) {
		BkdCartons = bkdCartons;
	}


	public String getBkdCdm() {
		return BkdCdm;
	}


	public void setBkdCdm(String bkdCdm) {
		BkdCdm = bkdCdm;
	}


	public String getDC() {
		return DC;
	}


	public void setDC(String dC) {
		DC = dC;
	}


	public String getQC() {
		return QC;
	}


	public void setQC(String qC) {
		QC = qC;
	}
	
	public String getPoNumber() {
		return poNumber;
	}
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
