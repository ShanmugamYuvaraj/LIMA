package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.ShipAuthority;
import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAShipAuthorityPOM {
	

	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Ship Authority") WebElement lnkShipAuthority;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chevSearch1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[@class='Stage' and text()='Authorise']") WebElement chevAuthorise;
	@FindBy(xpath=".//*[@value='Authorise']") WebElement chevAuthorise1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement txtErrMsg;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[4]/a") List <WebElement> lstPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div[2]/table/tbody/tr/td[22]") List<WebElement> lstStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]")WebElement Vendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[5]")WebElement Product;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[4]") WebElement PONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[7]") WebElement ETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[8]") WebElement ETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[11]") WebElement PoQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[13]")WebElement BKDQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") WebElement OriginPortData;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]") WebElement DestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[4]") WebElement Vessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[5]") WebElement Voyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[6]") WebElement Carrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[2]") WebElement FrieghtTerms;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[9]") WebElement POShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[12]")WebElement OpenQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[14]") WebElement Ctns;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[15]")WebElement Cbm;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[16]") WebElement PackType;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement thCheckBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]") WebElement thVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]") WebElement thCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]") WebElement thETDDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[8]") WebElement thETADate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[1]") WebElement thVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]") WebElement thFreightTerms;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement thLoadType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement thCFSClosingDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[2]") WebElement thVendorReference;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[3]") WebElement thCustomerReference;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[4]") WebElement thPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[5]") WebElement thProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[6]") WebElement thSKU;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[7]") WebElement thEventCode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[8]") WebElement thDescription;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[9]") WebElement thPOShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[10]") WebElement thDCDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[11]") WebElement thPOQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[12]") WebElement thOpenQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[13]") WebElement thBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[14]") WebElement thBkdCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[15]") WebElement thBkdCBM;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[16]") WebElement thPackType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[17]") WebElement thDC;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[18]") WebElement thDueIntoCFS;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[19]") WebElement thQC;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[20]") WebElement thLandedCostVarience;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[21]") WebElement thBookingKPI;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[22]") WebElement thAuthorityStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[23]") WebElement thComments;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[24]") WebElement thLastAuthorisedBy;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[25]") WebElement thStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[26]") WebElement thDailog;
	
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement SBOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]") WebElement SBDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]") WebElement SBVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[10]") WebElement SBVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[8]") WebElement SBCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[11]") WebElement SBETDDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[12]") WebElement SBETADate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]") WebElement SBVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[19]") WebElement SBFrieghtTerms;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[2]") WebElement SBPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[3]") WebElement SBProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[17]") WebElement SBPOShipdate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[9]") WebElement SBPOQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[9]") WebElement SBBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[11]") WebElement SBOpenQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[12]") WebElement SBCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[14]") WebElement SBPackType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[13]") WebElement SBCbm;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[4]") WebElement trPONumber;
	@FindBy(xpath=".//*[text()='You must make selection(s) before progressing']") WebElement txtMandatoryErrMsg;
	@FindBy(xpath=".//*[@class='x-tool x-tool-close']") WebElement closeBtn;
	@FindBy(xpath=".//*[text()='You must correct all validation failures before progressing.']") WebElement txtValidationErrMsg;
	
	@FindBy(xpath=".//*[text()='Processed 100%']") WebElement txtProcessedMsg;
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@class='x-tool x-tool-toggle']") WebElement btnViewDetail1;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement PoStatusReport;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]/a") WebElement poStatus_Status;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a]") WebElement txtShipAuthorisation;
	@FindBy(xpath="//*[contains(text(),'Shipment Authorisation')][last()]") WebElement txtShip;
	@FindBy(xpath=".//*[@class='Title']") WebElement txtDailogPage;
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement txtUpdateMsg;
	@FindBy(xpath=".//*[@id='DialogSummary_DialogControl2']/span/span") WebElement txtNewDailog;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[1]/h2/span[7]") WebElement CCmsg;
    @FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[2]") WebElement txtmsg ;
    @FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[1]/h2/span[6]") WebElement NewSubject;
    
    @FindBy(linkText="Vendor Booking") WebElement lnkVendorBooking;
    @FindBy(linkText="Shipment Booking") WebElement lnkShipmentBooking;
    @FindBy(xpath=".//*[@id='DialogSummary_DialogControl1']") WebElement lnkDailog;
    
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[18]/span") WebElement vendorBookingAuthoriseStatus;
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement vendorBookingPONumber;
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[21]/span") WebElement shipmentBookingAuthoriseStatus;
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[2]") WebElement shipmentBookingPONumber;
    @FindBy(linkText="Logoff") WebElement lnkLogOff;
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[5]") WebElement trProduct;
   
    
    
	public WebElement getTrPONumber() {
		return trPONumber;
	}

	public WebElement getTrProduct() {
		return trProduct;
	}

	public WebElement getVendorBookingPONumber() {
		return vendorBookingPONumber;
	}

	public WebElement getShipmentBookingPONumber() {
		return shipmentBookingPONumber;
	}

	
    public WebElement getLnkDailog() {
		return lnkDailog;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkShipAuthority() {
		return lnkShipAuthority;
	}

	public WebElement getChevSearch() {
		return chevSearch;
	}
	
	public WebElement getChevSearch1() {
		return chevSearch1;
	}

	public WebElement getChevSelect() {
		return chevSelect;
	}

	public WebElement getChevAuthorise() {
		return chevAuthorise;
	}
	
	public WebElement getChevAuthorise1() {
		return chevAuthorise1;
	}
	public WebElement getTxtErrMsg() {
		return txtErrMsg;
	}
	
	public WebElement getChevComplete() {
		return chevComplete;
	}
	
	public WebElement getChevComplete1() {
		return chevComplete1;
		
	}
	
	public List<WebElement> getLstPONumber() {
		return lstPONumber;
	}
	
	public List<WebElement> getLstStatus() {
		return lstStatus;
	}
	
	public WebElement getThCheckBox() {
		return thCheckBox;
	}

	public WebElement getThBookingKPI() {
		return thBookingKPI;
	}

	public WebElement getThMode() {
		return thMode;
	}

	public WebElement getThOriginPort() {
		return thOriginPort;
	}

	public WebElement getThDestinationPort() {
		return thDestinationPort;
	}

	public WebElement getThVessel() {
		return thVessel;
	}

	public WebElement getThVoyage() {
		return thVoyage;
	}

	public WebElement getThCarrier() {
		return thCarrier;
	}

	public WebElement getThETDDate() {
		return thETDDate;
	}

	public WebElement getThETADate() {
		return thETADate;
	}

	public WebElement getThVendor() {
		return thVendor;
	}

	public WebElement getThFreightTerms() {
		return thFreightTerms;
	}

	public WebElement getThLoadType() {
		return thLoadType;
	}

	public WebElement getThCFSClosingDate() {
		return thCFSClosingDate;
	}

	public WebElement getThVendorReference() {
		return thVendorReference;
	}

	public WebElement getThVendorRef() {
		return thCustomerReference;
	}

	public WebElement getThPONumber() {
		return thPONumber;
	}

	public WebElement getThProduct() {
		return thProduct;
	}

	public WebElement getThSKU() {
		return thSKU;
	}

	public WebElement getThEventCode() {
		return thEventCode;
	}

	public WebElement getThDescription() {
		return thDescription;
	}

	public WebElement getThPOShipDate() {
		return thPOShipDate;
	}

	public WebElement getThDCDate() {
		return thDCDate;
	}

	public WebElement getThPOQty() {
		return thPOQty;
	}

	public WebElement getThOpenQty() {
		return thOpenQty;
	}

	public WebElement getThBkdQty() {
		return thBkdQty;
	}

	public WebElement getThBkdCtns() {
		return thBkdCtns;
	}

	public WebElement getThBkdCBM() {
		return thBkdCBM;
	}

	public WebElement getThPackType() {
		return thPackType;
	}

	public WebElement getThDC() {
		return thDC;
	}

	public WebElement getThDueIntoCFS() {
		return thDueIntoCFS;
	}

	public WebElement getThQC() {
		return thQC;
	}

	public WebElement getThAuthorityStatus() {
		return thAuthorityStatus;
	}

	public WebElement getThComments() {
		return thComments;
	}

	public WebElement getThLastAuthosiedBy() {
		return thLastAuthorisedBy;
	}

	public WebElement getThStatus() {
		return thStatus;
	}

	public WebElement getThDailog() {
		return thDailog;
	}
	
	public WebElement getTxtMandatoryErrMsg() {
		return txtMandatoryErrMsg;
	}
	
	public WebElement getCloseBtn() {
		return closeBtn;
	}
	
	public WebElement getTxtValidationErrMsg() {
		return txtValidationErrMsg;
	}
	
	public WebElement getTxtProcessedMsg() {
		return txtProcessedMsg;
	}
	
	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	
	public WebElement getBtnViewDetail1() {
		return btnViewDetail1;
	}
	
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}
	
	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	
	public WebElement getLnkEdit() {
		return lnkEdit;
	}
	
	public WebElement getFldRefNo() {
		return fldRefNo;
	}
	
	public WebElement getBtnTrackApply() {
		return btnTrackApply;
	}
	
	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
	}
	
	public WebElement getThLandedCostVarience() {
		return thLandedCostVarience;
	}

	public List<WebElement> getLstTrackEvent() {
		return lstTrackEvent;
	}
	
	public List<WebElement> getLstTrAchivedDate() {
		return lstTrAchivedDate;
	}
	
	public List<WebElement> getLstTrUserName() {
		return lstTrUserName;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}
	
	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}
	
	public WebElement getBtnRun() {
		return btnRun;
	}
	
	public WebElement getPoStatusReport() {
		return PoStatusReport;
	}
	
	public WebElement getPoStatus_Status() {
		return poStatus_Status;
	}
		
	public WebElement getTxtShipAuthorisation() {
		return txtShipAuthorisation;
	}
	
	public WebElement getTxtDailogPage() {
		return txtDailogPage;
	}

	public WebElement getTxtUpdateMsg() {
		return txtUpdateMsg;
	}
	
	public WebElement getTxtNewDailog() {
		return txtNewDailog;
	}

	public WebElement getCCmsg() {
		return CCmsg;
	}

	public WebElement getTxtmsg() {
		return txtmsg;
	}
	
	public WebElement getNewSubject() {
		return NewSubject;
	}

	public WebElement getLnkVendorBooking() {
		return lnkVendorBooking;
	}

	public WebElement getLnkShipmentBooking() {
		return lnkShipmentBooking;
	}

	public WebElement getVendorBookingAuthoriseStatus() {
		return vendorBookingAuthoriseStatus;
	}

	public WebElement getShipmentBookingAuthoriseStatus() {
		return shipmentBookingAuthoriseStatus;
	}
	
	public WebElement getLnkLogOff() {
		return lnkLogOff;
	}
	
	public WebElement getSBOriginPort() {
		return SBOriginPort;
	}

	public WebElement getSBDestinationPort() {
		return SBDestinationPort;
	}

	public WebElement getSBVessel() {
		return SBVessel;
	}

	public WebElement getSBVoyage() {
		return SBVoyage;
	}

	public WebElement getSBCarrier() {
		return SBCarrier;
	}

	public WebElement getSBETDDate() {
		return SBETDDate;
	}

	public WebElement getSBETADate() {
		return SBETADate;
	}

	public WebElement getSBVendor() {
		return SBVendor;
	}

	public WebElement getSBFrieghtTerms() {
		return SBFrieghtTerms;
	}

	public WebElement getSBPONumber() {
		return SBPONumber;
	}

	public WebElement getSBProduct() {
		return SBProduct;
	}

	public WebElement getSBPOShipdate() {
		return SBPOShipdate;
	}

	public WebElement getSBPOQty() {
		return SBPOQty;
	}

	public WebElement getSBBkdQty() {
		return SBBkdQty;
	}

	public WebElement getSBOpenQty() {
		return SBOpenQty;
	}

	public WebElement getSBCtns() {
		return SBCtns;
	}

	public WebElement getSBPackType() {
		return SBPackType;
	}

	public WebElement getSBCbm() {
		return SBCbm;
	}
	
	public WebElement getVendor() {
		return Vendor;
	}

	public WebElement getProduct() {
		return Product;
	}

	public WebElement getPONumber() {
		return PONumber;
	}

	public WebElement getETD() {
		return ETD;
	}

	public WebElement getETA() {
		return ETA;
	}

	public WebElement getPoQty() {
		return PoQty;
	}

	public WebElement getBKDQty() {
		return BKDQty;
	}

	public WebElement getOriginPortData() {
		return OriginPortData;
	}

	public WebElement getDestinationPort() {
		return DestinationPort;
	}

	public WebElement getVessel() {
		return Vessel;
	}

	public WebElement getVoyage() {
		return Voyage;
	}

	public WebElement getCarrier() {
		return Carrier;
	}

	public WebElement getFrieghtTerms() {
		return FrieghtTerms;
	}

	public WebElement getPOShipDate() {
		return POShipDate;
	}

	public WebElement getOpenQty() {
		return OpenQty;
	}

	public WebElement getCtns() {
		return Ctns;
	}

	public WebElement getCbm() {
		return Cbm;
	}

	public WebElement getPackType() {
		return PackType;
	}

	public AdjunoLIMAShipAuthorityPOM(WebDriver driver)
	{
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
	
	public void wait(int ms)
    {
    	try 
    	{
			Thread.sleep(ms);
		} 
    	catch (InterruptedException e) 
    	{
			e.printStackTrace();
		}

    }
	
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink) {
	       Actions action = new Actions(driver);

	       action.moveToElement(mainLink).perform();

	       action.moveToElement(subLink);
	       
	       action.click();
	       
	       action.perform();
	       
	       objAdjunoUILibrary = new AdjunoUILibrary(driver);
	       String strTitle = "";
	         if (objAdjunoUILibrary.isPageLoaded(strFormName))
	         {
	             strTitle = driver.getTitle();
	         }
	         return strTitle;
	    }

	public boolean isElementPresent(WebElement we) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        try
        {
        bFlag = objAdjunoUILibrary.isElementPresent(we);
        }
        catch (NoSuchElementException e)
        {
            bFlag = false;
        }

		return bFlag;	
	}
	
	public boolean checkDoesFieldsExist(String strFormName, String strControlName) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
           bFlag = we.isDisplayed();
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;
	}
	
	public boolean clearInputField(String strFormName,String strControlName)
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try
		{
			WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
			we.clear();
			
			we.sendKeys(Keys.TAB);	
			bFlag = true;
		}
		catch (NoSuchElementException e)
	    {
	           bFlag = false;
	    }
		catch(NullPointerException e)
        {
	           bFlag = false;
        }
	        return bFlag;
	}
	
	public boolean setFieldValue(String strValue,String strFormName, String strControlName) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
           bFlag = we.isDisplayed();
           we.clear();
           we.sendKeys(strValue);
           we.sendKeys(Keys.TAB);
           we.sendKeys(Keys.TAB);
           
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
		
		
		return bFlag;
	}
	
	public boolean clickChevron(WebElement we)
	{
		Boolean bFlag;		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);	
		try {
			bFlag = objAdjunoUILibrary.isElementPresent(we);
			we.click();
			wait(2000);
		} catch (NoSuchElementException e) {
			
			bFlag = false;
		}		
		return bFlag;
	}
	
	public List<ShipAuthority> getStaus() 
	{
		List<ShipAuthority> list = new ArrayList<ShipAuthority>();
	    
		for (int i = 0; i <= getLstPONumber().size()-1; i++) {
			ShipAuthority po = new ShipAuthority(getLstPONumber().get(i).getText(),getLstStatus().get(i).getText()); 
			//scroll down 
			//JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			list.add(po);
			
			//jse.executeScript("window.scrollBy(0,100)", "");
		}
		
		return list;
	}
	
	public String verifyStaus(String strStatusAwaiting,List<ShipAuthority> list) 
	{
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			
			if(list.get(i).getStatus().equalsIgnoreCase(strStatusAwaiting))
			{
				
			}
			else{
				
				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPoNumber()+ "Status is not matching";
			}
		}
		
		return strMessage;
	}
	
	public boolean clickButton(String strFormName, String strControlName) 
	{
		Boolean bFlag;	
		objAdjunoUILibrary = new AdjunoUILibrary(driver);	
		try {
			WebElement button = objAdjunoUILibrary.getElement(strFormName, strControlName);
			button.click();
			wait(2000);
			bFlag = true;
		} catch (NoSuchElementException e) {
			
			bFlag = false;
		}		
		return bFlag;
		
	}
		
	public boolean clickCheckBox(WebElement checkbox) {
		boolean bFlag;
		try
        {
			bFlag = checkbox.isDisplayed();
			checkbox.click();
        }
        catch (NoSuchElementException e)
        {
        	bFlag = false;
        }
    	catch(NullPointerException e)
        {
    		bFlag = false;
        }
		return bFlag;
		
	}
	public boolean verifyPageIsLoaded(String strFormName)
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try
		{
		objAdjunoUILibrary.isPageLoaded(strFormName);
		bFlag = true;
		}
		catch(Exception e)
		{
			bFlag = false;
		}
		return bFlag;
	}
	
	public boolean compareTwoStringValuesToSame(String strValue1, String strValue2)
    {
        boolean bFlag = true;
         if ((!(isNullOrBlank(strValue1))) &&  (!(isNullOrBlank(strValue2))))
         {                
             if (strValue1.equalsIgnoreCase(strValue2)){}
             else
             {
                 bFlag = false;
             }
         }
         else
         {
             bFlag = false;
         }
         
         return bFlag;
    }
	
	

	public boolean checkDoesElementExist(String formName,String controlName) {        
    boolean bFlag;
    objAdjunoUILibrary = new AdjunoUILibrary(driver);
    WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
    try
    {
        
        bFlag = we.isDisplayed();
    }
    catch (NoSuchElementException e)
    {
       bFlag = false;
    }
    catch(NullPointerException e)
    {
       bFlag = false;
    }
    return bFlag;
	}
	
	public ArrayList<String> getCaptionsList(String strFormName, String strControlName) {
        objAdjunoUILibrary = new AdjunoUILibrary(driver); 
        long nGridColumns = objAdjunoUILibrary.getNoOfColumnsGrid(strFormName,strControlName);    
        String strColumnName = "";
        
        ArrayList<String> captionList = new ArrayList<String>();
        
        for (int i=0; i<= nGridColumns - 1; i++)
        {
            strColumnName = objAdjunoUILibrary.getColumnName(strFormName,strControlName, i);
            captionList.add(objAdjunoUILibrary.getGridColumnCaption(strFormName,strControlName,strColumnName,0));
        }
        
        return captionList;
    }
	
	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, int nCaptions) {
        String strMessage = "";
        Element e1;
        if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
        {
            e1 = (Element) nodeList.item(0);
            for (int j = 0; j <= nCaptions-1; j++) {
                String tempVal = e1.getElementsByTagName("Caption").item(j).getTextContent();
               for (int i = 0; i <= list.size()-1; i++) {
            	   String listVal = list.get(i);
            	   
                    if(tempVal.equalsIgnoreCase(listVal)){
                        break;
                    }else{
                        if(i==list.size()-1){
                            strMessage = strMessage+ tempVal+ "from Xml is not found in Grid";
                        }                                
                    }
               }
            
            }
        }    
        return strMessage;
    }
	
	public String isHyperlinkPresent(String strFormName,String strGridControlName,int nRow,String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strMessage = "";
		WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		String strhref=we.getAttribute("href");
		if(!isNullOrBlank(strhref))
		{
			we.click();
		}
		else
		{
			strMessage = strMessage + "It is not a hyperlink";
		}
		return strMessage;
	}
	
	public boolean clickButtonUsingWebElement(WebElement we) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
                
        try
       {
            bFlag = we.isDisplayed();
            we.click();
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;
        
    }
	
	public String getValidationMessageGridElement(String strFormName,String strGridControlName, int nRow, String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	
		String strValidationMsg = objAdjunoUILibrary.getValidationMessageGridElement(strFormName, strGridControlName, nRow, strColumnName);
		if(!isNullOrBlank(strValidationMsg))
		{
			
		}
		else
		{
			strValidationMsg ="";
		}
		return strValidationMsg;
	}
	
	public String getValidationMessageElement(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		String strValidationMsg = objAdjunoUILibrary.getValidationMessageElement(strFormName, strControlName);
		if(!isNullOrBlank(strValidationMsg))
		{
			
		}
		else
		{
			strValidationMsg ="";
		}
		return strValidationMsg;
	}
	
	public boolean setFieldValueForGridCell(String strFormName, String strGridControlName, int nRow, String strColumnName, String strValue) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           objAdjunoUILibrary.setGridCellValue(strFormName, strGridControlName, nRow, strColumnName, strValue);    
           WebElement we;
           we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strGridControlName, nRow, strColumnName);
           we.sendKeys(Keys.TAB);
           
           wait(2000);
 
           bFlag= true;
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;        
    }
	
	public String getWebElementCellValue(WebElement we)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strWeCellValue="";
		strWeCellValue = we.getText();
		if(!isNullOrBlank(strWeCellValue))
		{
			
		}
		else
		{
			strWeCellValue = "The text of the WebElement is not available";
		}
		return strWeCellValue;
	}
	
	public boolean setFieldValueForWebElement(WebElement we, String strFieldValue) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
    
        try
       {
            bFlag = we.isDisplayed();
            we.sendKeys(strFieldValue);    
            we.sendKeys(Keys.TAB);
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;        
    }
	
	public long getDifference(Date today, Date date) 
	{
        
        if( date == null || today == null ) return 0;
        
       
        return ((today.getTime()/60000) - (date.getTime()/60000));
        
    }
	
//	public boolean getDifference(Date today, Date date)
//	{
//		boolean bFlag = false;
//		//if( date == null || today == null ) return 0;
//		if(date == today)
//		{
//			bFlag = true;
//		}
//		else
//		{
//			bFlag = false;
//		}
//		return bFlag;
//	}
	
	public long getTrackValue(String userName) throws ParseException 
	{
        int length = getLstTrackEvent().size();
        long min = 0 ;
       List<Tracksdata> list= new ArrayList<Tracksdata>();
       for (int i = 0; i < length-1; i++) {
            Tracksdata track = new Tracksdata(getLstTrackEvent().get(i).getText(), getLstTrAchivedDate().get(i).getText(),getLstTrUserName().get(i).getText());
            list.add(track);
       }
       
       for (int j = 0; j < list.size()-1; j++) {
            if(list.get(j).getEvent().equalsIgnoreCase("Shipment Authorisation")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                String strAchievedDate = list.get(j).getAcheived();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                Date today = new Date();
                
                Date achievedDate = formatter.parse(strAchievedDate);
            
                min = getDifference(today,achievedDate);
               // System.out.println("difference is:"+min);
            }
        }
        return min;
    }
	
	public String getGridCellValue(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
		 objAdjunoUILibrary = new AdjunoUILibrary(driver);
		 String strGridCellValue="";
		 strGridCellValue = objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, nRow, strColumnName);
		 if(!isNullOrBlank(strGridCellValue))
		 {
			 
		 }
		 else
		 {
			 strGridCellValue="The text of the cell is not available";
		 }
		 return strGridCellValue;
		
	}
	
	public WebElement getElement(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		try{
			we.isDisplayed();
		}
		catch(NoSuchElementException e)
		{
			
		}
		return we;
	}
	
	public ShipAuthority getShipAuthorityData()
	{
		ShipAuthority sa = new ShipAuthority(getPONumber().getText(), getVendor().getText(), getProduct().getText(), getPoQty().getText(), getBKDQty().getText(), getETD().getText(), getETA().getText(), getOriginPortData().getText(), getDestinationPort().getText(), getVessel().getText(), getVoyage().getText(), getCarrier().getText(), getFrieghtTerms().getText(), getPOShipDate().getText(), getOpenQty().getText(), getCtns().getText(), getCbm().getText(), getPackType().getText(),"");
		
		return sa;
	}
	
	public ShipAuthority getShipmentBookingData()
	{
		
		ShipAuthority sa = new ShipAuthority(getSBOriginPort().getText(), getSBDestinationPort().getText(), getSBVessel().getText(), getSBVoyage().getText(), getSBCarrier().getText(), getSBETDDate().getText(), getSBETADate().getText(), getSBVendor().getText(), getSBFrieghtTerms().getText(), getSBPONumber().getText(), getSBProduct().getText(), getSBPOShipdate().getText(), getSBPOQty().getText(), getSBBkdQty().getText(), getSBOpenQty().getText(), getSBCtns().getText(), getSBCbm().getText(), getSBPackType().getText());
		
		return sa;
	}
	
	public String verifyMessageInReadOnlyMode(String strID)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strMessage = "";
		strMessage = objAdjunoUILibrary.getElementValueInReadMode(strID);
		if(!isNullOrBlank(strMessage))
		{
			
		}
		else
		{
			strMessage = strMessage + "Validation message is not diplayed";
		}
		return strMessage;
	}
	
	
	
	
	
	
	
	
	
	
	

}

