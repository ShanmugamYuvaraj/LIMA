package com.lima.dto;

public class Manifest {
	
	String trOriginPort;
	String vessel;
	String ETD;
	String ETA;
	String carrier;
	String loading;
	String loadRef;
	String conatiner;
	String equipment;
	
	String vendor;
	String HBL;
	String poNumber;
	String product;
	String identifier;
	String custRef;
	String bkdQty;
	String qty;
	String ctns;
	String cbm;
	String packType;
	String frieghtTerms;
	String dc;
	String manifestStaus;
	String dialog;
	
	
	public Manifest(String trOriginPort, String vessel, String eTD, String eTA,
			String carrier, String loading, String loadRef, String conatiner,
			String equipment, String vendor, String hBL, String poNumber,
			String product, String identifier, String custRef, String bkdQty,
			String qty, String ctns, String cbm, String packType,
			String frieghtTerms, String dc, String manifestStaus, String dialog) {
		super();
		this.trOriginPort = trOriginPort;
		this.vessel = vessel;
		this.ETD = eTD;
		this.ETA = eTA;
		this.carrier = carrier;
		this.loading = loading;
		this.loadRef = loadRef;
		this.conatiner = conatiner;
		this.equipment = equipment;
		this.vendor = vendor;
		this.HBL = hBL;
		this.poNumber = poNumber;
		this.product = product;
		this.identifier = identifier;
		this.custRef = custRef;
		this.bkdQty = bkdQty;
		this.qty = qty;
		this.ctns = ctns;
		this.cbm = cbm;
		this.packType = packType;
		this.frieghtTerms = frieghtTerms;
		this.dc = dc;
		this.manifestStaus = manifestStaus;
		this.dialog = dialog;
	}
	

	public Manifest(String vendor, String poNumber, String product,
			String identifier, String bkdQty, String qty, String ctns,
			String cbm, String packType) {
		super();
		this.vendor = vendor;
		this.poNumber = poNumber;
		this.product = product;
		this.identifier = identifier;
		this.bkdQty = bkdQty;
		this.qty = qty;
		this.ctns = ctns;
		this.cbm = cbm;
		this.packType = packType;
	}


	public String getTrOriginPort() {
		return trOriginPort;
	}


	public String getVessel() {
		return vessel;
	}


	public String getETD() {
		return ETD;
	}


	public String getETA() {
		return ETA;
	}


	public String getCarrier() {
		return carrier;
	}


	public String getLoading() {
		return loading;
	}


	public String getLoadRef() {
		return loadRef;
	}


	public String getConatiner() {
		return conatiner;
	}


	public String getEquipment() {
		return equipment;
	}


	public String getVendor() {
		return vendor;
	}


	public String getHBL() {
		return HBL;
	}


	public String getPoNumber() {
		return poNumber;
	}


	public String getProduct() {
		return product;
	}


	public String getIdentifier() {
		return identifier;
	}


	public String getCustRef() {
		return custRef;
	}


	public String getBkdQty() {
		return bkdQty;
	}


	public String getQty() {
		return qty;
	}


	public String getCtns() {
		return ctns;
	}


	public String getCbm() {
		return cbm;
	}


	public String getPackType() {
		return packType;
	}


	public String getFrieghtTerms() {
		return frieghtTerms;
	}


	public String getDc() {
		return dc;
	}


	public String getManifestStaus() {
		return manifestStaus;
	}


	public String getDialog() {
		return dialog;
	}

}
