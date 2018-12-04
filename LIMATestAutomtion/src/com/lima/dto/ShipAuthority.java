package com.lima.dto;

public class ShipAuthority {

	String poNumber;
	String status;
	
	String vendor;
	String product;
	String SKU;
	String mode;
	String poQty;
	String bkdQty;
	String loadType;
	String ETD;
	String ETA;
	String QC;
	String OriginPort;
	

	String DestinationPort;
	String Vessel;
	String Voyage;
	String Carrier;
	String FrieghtTerms;
	String POShipDate;
	String OpenQty;
	String Ctns;
	String Cbm;
	String PackType;
	
	
	String SBOriginPort;
	String SBDestinationPort;
	String SBVessel;
	String SBVoyage;
	String SBCarrier;
	String SBETDDate;
	String SBETADate;
	String SBVendor;
	String SBFrieghtTerms;
	String SBPONumber;
	String SBProduct;
	String SBPOShipDate;
	String SBPOQty;
	String SBBkdQty;
	String SBOpenQty;
	String SBCtns;
	String SBCbm;
	String SBPackType;
	
	
	public ShipAuthority(String poNumber, String vendor, String product,
			String poQty, String bkdQty, String eTD, String eTA,
			String originPort, String destinationPort, String vessel,
			String voyage, String carrier, String frieghtTerms,
			String pOShipDate, String openQty, String ctns, String cbm,
			String packType,String QC) {
		super();
		this.poNumber = poNumber;
		this.vendor = vendor;
		this.product = product;
		this.poQty = poQty;
		this.bkdQty = bkdQty;
		this.ETD = eTD;
		this.ETA = eTA;
		this.OriginPort = originPort;
		this.DestinationPort = destinationPort;
		this.Vessel = vessel;
		this.Voyage = voyage;
		this.Carrier = carrier;
		this.FrieghtTerms = frieghtTerms;
		this.POShipDate = pOShipDate;
		this.OpenQty = openQty;
		this.Ctns = ctns;
		this.Cbm = cbm;
		this.PackType = packType;
		this.QC = QC;
	}

	
	
	
	
	public ShipAuthority(String sBOriginPort, String sBDestinationPort,
			String sBVessel, String sBVoyage, String sBCarrier,
			String sBETDDate, String sBETADate, String sBVendor,
			String sBFrieghtTerms, String sBPONumber, String sBProduct,
			String sBPOShipDate, String sBPOQty, String sBBkdQty,
			String sBOpenQty, String sBCtns, String sBCbm, String sBPackType) {
		super();
		SBOriginPort = sBOriginPort;
		SBDestinationPort = sBDestinationPort;
		SBVessel = sBVessel;
		SBVoyage = sBVoyage;
		SBCarrier = sBCarrier;
		SBETDDate = sBETDDate;
		SBETADate = sBETADate;
		SBVendor = sBVendor;
		SBFrieghtTerms = sBFrieghtTerms;
		SBPONumber = sBPONumber;
		SBProduct = sBProduct;
		SBPOShipDate = sBPOShipDate;
		SBPOQty = sBPOQty;
		SBBkdQty = sBBkdQty;
		SBOpenQty = sBOpenQty;
		SBCtns = sBCtns;
		SBCbm = sBCbm;
		SBPackType = sBPackType;
	}

	public ShipAuthority(String poNumber, String status)
	{
		super();
		this.poNumber = poNumber;
		this.status = status;
	}

	public ShipAuthority(String poNumber, String vendor, String product,
			String sKU, String mode, String poQty, String bkdQty,
			String loadType, String eTD, String eTA) {
		super();
		this.poNumber = poNumber;
		this.vendor = vendor;
		this.product = product;
		this.SKU = sKU;
		this.mode = mode;
		this.poQty = poQty;
		this.bkdQty = bkdQty;
		this.loadType = loadType;
		this.ETD = eTD;
		this.ETA = eTA;
	}

	public String getVendor() {
		return vendor;
	}

	public String getProduct() {
		return product;
	}

	public String getSKU() {
		return SKU;
	}

	public String getMode() {
		return mode;
	}

	public String getPoQty() {
		return poQty;
	}

	public String getBkdQty() {
		return bkdQty;
	}

	public String getLoadType() {
		return loadType;
	}

	public String getETD() {
		return ETD;
	}

	public String getETA() {
		return ETA;
	}

	public String getQC() {
		return QC;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public String getStatus() {
		return status;
	}

	public String getSBOriginPort() {
		return SBOriginPort;
	}

	public String getSBDestinationPort() {
		return SBDestinationPort;
	}

	public String getSBVessel() {
		return SBVessel;
	}

	public String getSBVoyage() {
		return SBVoyage;
	}

	public String getSBCarrier() {
		return SBCarrier;
	}

	public String getSBETDDate() {
		return SBETDDate;
	}

	public String getSBETADate() {
		return SBETADate;
	}

	public String getSBVendor() {
		return SBVendor;
	}

	public String getSBFrieghtTerms() {
		return SBFrieghtTerms;
	}

	public String getSBPONumber() {
		return SBPONumber;
	}

	public String getSBProduct() {
		return SBProduct;
	}

	public String getSBPOShipDate() {
		return SBPOShipDate;
	}

	public String getSBPOQty() {
		return SBPOQty;
	}

	public String getSBBkdQty() {
		return SBBkdQty;
	}

	public String getSBOpenQty() {
		return SBOpenQty;
	}

	public String getSBCtns() {
		return SBCtns;
	}

	public String getSBCbm() {
		return SBCbm;
	}

	public String getSBPackType() {
		return SBPackType;
	}
	
	public String getOriginPort() {
		return OriginPort;
	}

	public String getDestinationPort() {
		return DestinationPort;
	}

	public String getVessel() {
		return Vessel;
	}

	public String getVoyage() {
		return Voyage;
	}

	public String getCarrier() {
		return Carrier;
	}

	public String getFrieghtTerms() {
		return FrieghtTerms;
	}

	public String getPOShipDate() {
		return POShipDate;
	}

	public String getOpenQty() {
		return OpenQty;
	}

	public String getCtns() {
		return Ctns;
	}

	public String getCbm() {
		return Cbm;
	}

	public String getPackType() {
		return PackType;
	}
	
}
