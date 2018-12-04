package com.lima.library;

import java.io.InputStream;


public class AdjunoLIMALibrary {
	
    String strBaseLocationFolderName = "C:/LIMATestAutomationProject/AdjunoLIMAXMLFile/";
	
	String strConfigXMLDataFileName = strBaseLocationFolderName + "LimaConfigData.xml";
	
	String strPoManagerXMLDataFileName = strBaseLocationFolderName + "PoManager.xml";
	
	String strPoManagerWFLXMLDataFileName = strBaseLocationFolderName + "PoManagerWFL.xml";
	
	String strLIMAProductionCheckXMLDataFileName = strBaseLocationFolderName + "ProductionCheck.xml";
	
	String strLIMAPOConfirmationXMLDataFileName = strBaseLocationFolderName + "POConfirmation.xml";
	
	String strLIMADespatchFileName = strBaseLocationFolderName + "Despatch.xml";
	
	String strLIMACargoReceiptFileName = strBaseLocationFolderName + "CargoReceipt.xml";
	
	String strLIMAManifestFileName = strBaseLocationFolderName + "Manifest.xml";
	
	String strWFLCertificateFileName = strBaseLocationFolderName + "CertificateConfirmation.xml";
	
	String strLIMAShipAuthorityFileName = strBaseLocationFolderName + "ShipAuthority.xml";
	
	String strLIMAShipmentBookingXMLDataFileName = strBaseLocationFolderName + "ShipmentBooking.xml";
		
	String strLIMADocumentSentXMLDataFileName = strBaseLocationFolderName + "DocumentSent.xml";
	
	String strLIMADocumentReceivedXMLDataFileName = strBaseLocationFolderName + "DocumentRecevied.xml";
	
	String strLIMAConfirmBookingFileName = strBaseLocationFolderName + "ConfirmArrival.xml";
	
	String strLIMACustomClearenceFileName = strBaseLocationFolderName + "CustomClearence.xml";
	
	String strLIMABookingConfirmationFileName = strBaseLocationFolderName + "BookingConfirmation.xml";
	
	String strLIMAFreightPayments = strBaseLocationFolderName + "FreightPayments.xml";
	
	String strLIMAForwarderReference = strBaseLocationFolderName + "ForwarderReference.xml";
	
	String strLIMATariff = strBaseLocationFolderName + "Tariff.xml";
	
	String strWFLDeliveryPlanning = strBaseLocationFolderName + "DeliveryPlanning.xml";
	
	String strWFLDestinationQCPreInspection = strBaseLocationFolderName + "DestinationQCPreInspection.xml";
	
	String strWFLDestinationQCPostInspection = strBaseLocationFolderName + "DestinationQCPostInspection.xml";
	
	String strWFLDestinationQCPostDisposal = strBaseLocationFolderName + "DestinationQCPostDisposal.xml";

	String strWFLDeliveryBooking = strBaseLocationFolderName + "DeliveryBooking.xml";
	
	String strOffDockLocationXMLDataFileName = strBaseLocationFolderName + "OffDockLocation.xml";

    	String strLIMADeliveredYard = strBaseLocationFolderName + "DeliveredYard.xml";

    	String strLIMAontainerRestitution = strBaseLocationFolderName + "ContainerRestitution.xml";

	String strLIMAVendorBooking = strBaseLocationFolderName + "VendorBooking.xml";

	String strLIMAEstimatedLandedCost = strBaseLocationFolderName + "EstimatedLandedCost.xml";

	String strLIMADeliveryBooking = strBaseLocationFolderName +"DeliveryBooking.xml";
	
	public String getDoucumentReceivedXMLDataFileName()
	{
		return strLIMADocumentReceivedXMLDataFileName;
	}
	
	public String getConfigXMLDataFileName()
	{
		return strConfigXMLDataFileName;
	}
	
	public String getPOConfirmationDataFileName()
	{
		return strLIMAPOConfirmationXMLDataFileName;
	}
	
	public String getPoManagerXMLDataFileName()
	{
		return strPoManagerXMLDataFileName;
	} 
	
	public String getAdjunoLIMAProductionCheckXMLDataFileName()
    {
        return strLIMAProductionCheckXMLDataFileName;
    }

	public String getStrLIMACargoReceiptXMLDataFileName() {
		return strLIMACargoReceiptFileName;
	}

	public String getAdjunoLIMADespatchXMLDataFileName() {
		return strLIMADespatchFileName;
	}
	
	public String getStrLIMAForwarderReference() {
		return strLIMAForwarderReference;
	}

	public String getManifestXMLDataFileName() {		
		return strLIMAManifestFileName;
	}

	public String getWFLCertificateFileName() {
		return strWFLCertificateFileName;
	}
	
	public String getShipAuthorityXMLDateFileName(){
		return strLIMAShipAuthorityFileName;
	}


	public String getStrLIMAShipmentBookingXMLDataFileName() {
		return strLIMAShipmentBookingXMLDataFileName;
	}


	public String getStrLIMADocumentSentXMLDataFileName() {
		return strLIMADocumentSentXMLDataFileName;
	}
	
	public String getConfirmBookingXMLDateFileName() {
		return strLIMAConfirmBookingFileName;
	}
	
	public String getCustomClearenceXMLDataFileName(){
		return strLIMACustomClearenceFileName;
	}
	
	public String getBookingConfirmationXMLDataFileName(){
		return strLIMABookingConfirmationFileName;
	}
	
	public String getFreightPaymentsXMLDataFileName(){
		return strLIMAFreightPayments;
	}
	
	public String getTariffXMLDataFileName(){
		return strLIMATariff;
	}

	public String getStrWFLDeliveryPlanning() {
		return strWFLDeliveryPlanning;
	}

	public String getstrWFLDestinationQCPreInspection() {
		return strWFLDestinationQCPreInspection;
	}

	public String getstrWFLDestinationQCPostInspection() {
		return strWFLDestinationQCPostInspection;
	}

	public String getstrWFLDestinationQCPostDisposal() {
		return strWFLDestinationQCPostDisposal;
	}

	public String getstrWFLDeliveryBooking() {
		return strWFLDeliveryBooking;
	}
	
	public String getStrPoManagerWFLXMLDataFileName() {
		return strPoManagerWFLXMLDataFileName;
	}
	
	public String getOffDockLocationXMLDataFileName() {
        return strOffDockLocationXMLDataFileName;
    }

    public String getStrLIMADeliveredYard() {
        return strLIMADeliveredYard;
    }

    public String getContainerRestitutionXMLDataFileName() {
        return strLIMAontainerRestitution;
    }
	
	public String getStrLIMAVendorBooking() {
		return strLIMAVendorBooking;
	}
	
	public String getStrLIMAEstimatedLandedCost() {
		return strLIMAEstimatedLandedCost;
	}

	public String getDeliveryBookingXMLDataFileName() {
		return strLIMADeliveryBooking;
	}
	
	

}
