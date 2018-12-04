package com.lima.dto;

public class Shipmentdata {
	
	String OriginPort;
	String Mode;
	String FeederVessel;
	String FeederVoyage;
	String FeederETD;
	String FeederETA;
	String DestinationPort;
	String Carrier;
	String MotherVessel;
	String MotherVoyage;
	String MotherETD;
	String MotherETA;
	
	String Vendor;
	String Loading;
	
	String PONumber;
	String Product;
	
	String Identifier;
	String Priority;
	String VendorRef;
	String SupplierRef;
	
	String Category;
	String POQty;
	String BkdQty;
	String OpenQty;
	String Ctns;
	String Cbm;
	String PackType;
	String Range;
	String DC;
	String ShipDate;
	String LatestShipDate;
	String FreightTerms;
	String BookingStatus;
	String AuthorityStatus;
	String VendorBooked;
	String Dialogs;
	String Status;
	
	String Factory;
	String Vessel;
	String Voyage;
	String ETD;
	String ETA;
	String VendorBookingID;
	
	
	
	
	public Shipmentdata(String poNumber, String BookingStatus,String VendorBooked) {
        super();
        this.PONumber = poNumber;
        this.BookingStatus = BookingStatus;
        this.VendorBooked=VendorBooked;
    }
	
	public Shipmentdata(String poNumber, String status) {
        super();
        this.PONumber = poNumber;
        this.Status = status;
        
        
        
    }
	
	public Shipmentdata(String vendor, String Factory,String mode,String OriginPort,String destinationPort,String Vessel,String Voyage,String ETD,String ETA,String VendorBookingID ) {
        super();
        this.PONumber = vendor;
        this.Status = Factory;
        this.Status = mode;
        this.PONumber = OriginPort;
        this.Status = destinationPort;
        this.Status = Vessel;
        this.Status = Voyage;
        this.PONumber = ETD;
        this.Status = ETA;
        this.Status = VendorBookingID;
        
        
    }
	
	
	
	public Shipmentdata(String originPort, String mode, String feederVessel,
			String feederVoyage, String feederETD, String feederETA,
			String destinationPort, String carrier, String motherVessel,
			String motherVoyage, String motherETD, String motherETA,
			String vendor, String loading) {
		super();
		OriginPort = originPort;
		Mode = mode;
		FeederVessel = feederVessel;
		FeederVoyage = feederVoyage;
		FeederETD = feederETD;
		FeederETA = feederETA;
		DestinationPort = destinationPort;
		Carrier = carrier;
		MotherVessel = motherVessel;
		MotherVoyage = motherVoyage;
		MotherETD = motherETD;
		MotherETA = motherETA;
		Vendor = vendor;
		Loading = loading;
	}

	public Shipmentdata(String pONumber, String product, String identifier,
			String priority, String vendorRef, String supplierRef,
			String category, String pOQty, String bkdQty, String openQty,
			String ctns, String cbm, String packType, String range, String dC,
			String shipDate, String latestShipDate, String freightTerms,
			String bookingStatus, String authorityStatus, String vendorBooked) {
		super();
		this.PONumber = pONumber;
		this.Product = product;
		this.Identifier = identifier;
		this.Priority = priority;
		this.VendorRef = vendorRef;
		this.SupplierRef = supplierRef;
		this.Category = category;
		this.POQty = pOQty;
		this.BkdQty = bkdQty;
		this.OpenQty = openQty;
		this.Ctns = ctns;
		this.Cbm = cbm;
		this.PackType = packType;
		this.Range = range;
		this.DC = dC;
		this.ShipDate = shipDate;
		this.LatestShipDate = latestShipDate;
		this.FreightTerms = freightTerms;
		this.BookingStatus = bookingStatus;
		this.AuthorityStatus = authorityStatus;
		this.VendorBooked = vendorBooked;
		
	}
   
	

	public String getOriginPort() {
		return OriginPort;
	}

	public String getMode() {
		return Mode;
	}

	public String getFeederVessel() {
		return FeederVessel;
	}

	public String getFeederVoyage() {
		return FeederVoyage;
	}

	public String getFeederETD() {
		return FeederETD;
	}

	public String getFeederETA() {
		return FeederETA;
	}

	public String getDestinationPort() {
		return DestinationPort;
	}

	public String getCarrier() {
		return Carrier;
	}

	public String getMotherVessel() {
		return MotherVessel;
	}

	public String getMotherVoyage() {
		return MotherVoyage;
	}

	public String getMotherETD() {
		return MotherETD;
	}

	public String getMotherETA() {
		return MotherETA;
	}

	public String getVendor() {
		return Vendor;
	}

	public String getLoading() {
		return Loading;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getPONumber() {
		return PONumber;
	}


	public void setPONumber(String pONumber) {
		PONumber = pONumber;
	}


	public String getProduct() {
		return Product;
	}


	public void setProduct(String product) {
		Product = product;
	}


	public String getIdentifier() {
		return Identifier;
	}


	public void setIdentifier(String identifier) {
		Identifier = identifier;
	}


	public String getPriority() {
		return Priority;
	}


	public void setPriority(String priority) {
		Priority = priority;
	}


	public String getVendorRef() {
		return VendorRef;
	}


	public void setVendorRef(String vendorRef) {
		VendorRef = vendorRef;
	}


	public String getSupplierRef() {
		return SupplierRef;
	}


	public void setSupplierRef(String supplierRef) {
		SupplierRef = supplierRef;
	}


	public String getCategory() {
		return Category;
	}


	public void setCategory(String category) {
		Category = category;
	}


	public String getPOQty() {
		return POQty;
	}


	public void setPOQty(String pOQty) {
		POQty = pOQty;
	}


	public String getBkdQty() {
		return BkdQty;
	}


	public void setBkdQty(String bkdQty) {
		BkdQty = bkdQty;
	}


	public String getOpenQty() {
		return OpenQty;
	}


	public void setOpenQty(String openQty) {
		OpenQty = openQty;
	}


	public String getCtns() {
		return Ctns;
	}


	public void setCtns(String ctns) {
		Ctns = ctns;
	}


	public String getCbm() {
		return Cbm;
	}


	public void setCbm(String cbm) {
		Cbm = cbm;
	}


	public String getPackType() {
		return PackType;
	}


	public void setPackType(String packType) {
		PackType = packType;
	}


	public String getRange() {
		return Range;
	}


	public void setRange(String range) {
		Range = range;
	}


	public String getDC() {
		return DC;
	}


	public void setDC(String dC) {
		DC = dC;
	}


	public String getShipDate() {
		return ShipDate;
	}


	public void setShipDate(String shipDate) {
		ShipDate = shipDate;
	}


	public String getLatestShipDate() {
		return LatestShipDate;
	}


	public void setLatestShipDate(String latestShipDate) {
		LatestShipDate = latestShipDate;
	}


	public String getFreightTerms() {
		return FreightTerms;
	}


	public void setFreightTerms(String freightTerms) {
		FreightTerms = freightTerms;
	}


	public String getBookingStatus() {
		return BookingStatus;
	}


	public void setBookingStatus(String bookingStatus) {
		BookingStatus = bookingStatus;
	}


	public String getAuthorityStatus() {
		return AuthorityStatus;
	}


	public void setAuthorityStatus(String authorityStatus) {
		AuthorityStatus = authorityStatus;
	}


	public String getVendorBooked() {
		return VendorBooked;
	}


	public void setVendorBooked(String vendorBooked) {
		VendorBooked = vendorBooked;
	}


	public String getDialogs() {
		return Dialogs;
	}


	public void setDialogs(String dialogs) {
		Dialogs = dialogs;
	}
	
	public String getStatus() {
		return Status;
	}


	public void setstatus(String status) {
		Status = status;
	}

	
	
	
}
