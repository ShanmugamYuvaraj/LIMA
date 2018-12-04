package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.lima.dto.Shipmentdata;
import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAShipmentBookingPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;

	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(linkText="Logoff") WebElement lnklogoff;
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	@FindBy(linkText="Complete List") WebElement lnkCompleteList;
	@FindBy(linkText="Shipment Booking") WebElement lnkShipmentBooking;
	@FindBy(linkText="Vendor Booking") WebElement lnkVendorBooking;
	@FindBy(linkText="Ship Authority") WebElement lnkShipAuthority;
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chvSearch1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[text()='Select']") WebElement chvSelect1;
	@FindBy(xpath=".//*[text()='Booking']") WebElement chvBooking;
	@FindBy(xpath="//*[@id='SUT_StageBar_Booking']") WebElement chvBooking1;
	@FindBy(xpath=".//*[text()='Routing']") WebElement chvRouting;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Routing']") WebElement chvRouting1;
	@FindBy(xpath=".//*[text()='KPI']") WebElement chvKPI;
	@FindBy(xpath=".//*[@id='SUT_StageBar_KPI']") WebElement chvKPI1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete2;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div/div[5]/span") WebElement chvReview;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[2]/div[5]/span") WebElement chvDialog;
    @FindBy(xpath=".//*[@id='InnerContent']/p") WebElement contentmsg;
	@FindBy(xpath=".//*[@id='ext-gen24']/div[1]/table/tbody/tr/td[5]/div/a") WebElement clickhyperlink;
	@FindBy(xpath=".//*[@id='ext-gen24']/div[1]/table/tbody/tr/td[7]/div/a") WebElement clickProducthyperlink;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement ghShipment;
	@FindBy(xpath=".//*[@id='ext-gen58']") WebElement ghVoyageLegs;
	@FindBy(xpath=".//*[@id='ext-gen61']") WebElement btnAddLeg;
	@FindBy(xpath=".//*[@id='ext-gen63']") WebElement btnDeleteLeg;
	@FindBy(xpath=".//*[text()='Yes']") WebElement grdyes;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[3]/p") WebElement Contenterrormsg;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(xpath=".//*[@id='LH_Shipment_SUTShipmentBookingTool_Booking_SKUPanelUpdateButton']") WebElement btnUpdateSKU;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement TitleShipment;
	//@FindBy(xpath=".//*[@id='InnerContent']/p/a") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnRun;
	@FindBy(xpath=".//*[@id='extId_ProgressForm_PanelDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[23]/a/b/font") WebElement ShipmentPostatus;
	@FindBy(xpath=".//*[text()='Subject']") WebElement ghSubject;
	@FindBy(xpath=".//*[text()='Message']") WebElement ghMessage;
	@FindBy(xpath=".//*[text()='Copy by E-Mail to']") WebElement ghCopybyEMailto;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[3]/p") WebElement DialogContenterrormsg;
    @FindBy(xpath=".//*[@id='DialogSummary_DialogControl2']/span/span") WebElement NewDialogComments;
    @FindBy(xpath=".//*[@id='extId_ProgressForm_ProgressControl']") WebElement txtProcessedMsg;
    @FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[3]") WebElement ProcessedcheckboxMsg;
   
   
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]")  WebElement  thOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]")  WebElement  thMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]")  WebElement  thFeederVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]")  WebElement  thFeederVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]")  WebElement  thFeederETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]")  WebElement  thFeederETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]")  WebElement  thDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[8]")  WebElement  thCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[9]")  WebElement  thMotherVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[10]") WebElement thMotherVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[11]") WebElement thMotherETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[12]") WebElement thMotherETA;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]")  WebElement  teOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]")  WebElement  teMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]")  WebElement  teFeederVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]")  WebElement  teFeederVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]")  WebElement  teFeederETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[6]")  WebElement  teFeederETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]")  WebElement  teDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[8]")  WebElement  teCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]")  WebElement  teMotherVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[10]") WebElement teMotherVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[11]") WebElement teMotherETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[12]") WebElement teMotherETA;
	@FindBy(xpath=".//*[text()='Are you sure you want to delete the selected row?']")WebElement txtDeleteLeg;
	@FindBy(xpath=".//*[text()='No']") WebElement btnNO;
    @FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
    @FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement Updatemsg;
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[25]/span/a") WebElement lnkDailog;
    @FindBy(xpath=".//*[@class='x-tool x-tool-close']") WebElement btnClose;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[1]")  WebElement thVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]")  WebElement thLoading;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]")  WebElement teVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]")  WebElement teLoading;
	
	
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[2]/input")  WebElement  thPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[3]/input")  WebElement  thProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[4]/input")  WebElement  thIdentifier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[5]/input")  WebElement  thPriority;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[6]/input")  WebElement  thVendorRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[7]/input")  WebElement  thSupplierRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[8]/input")  WebElement  thCategory;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[9]/input")  WebElement  thPOQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[10]/input")  WebElement thBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[11]/input") WebElement  thOpenQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[12]/input") WebElement  thCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[13]/input") WebElement  thCbm;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[14]/input")  WebElement thPackType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[15]/input")  WebElement thRange;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[16]/input")  WebElement thDC;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[17]/input")  WebElement thShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[18]/input")  WebElement thLatestShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[19]/input")  WebElement thFreightTerms;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[20]/input")  WebElement thBookingStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[21]/input")  WebElement thAuthorityStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[22]/input")  WebElement thVendorBooked;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[23]/input") WebElement  thDialog;
	
	
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[1]/input")  List <WebElement> teSelect_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[2]/a")  List <WebElement> tePONumber_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[3]")  List <WebElement> teProduct_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[4]")  List <WebElement> teIdentifier_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[5]")  List <WebElement> tePriority_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[6]")  List <WebElement> teVendorRef_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[7]")  List <WebElement> teSupplierRef_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[8]")  List <WebElement> teCategory_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[9]")  List <WebElement> tePOQty_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[10]")  List <WebElement> teBkdQty_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[11]")  List <WebElement> teOpenQty_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[12]")  List <WebElement> teCtns_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[13]")  List <WebElement> teCbm_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[14]")  List <WebElement> tePackType_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[15]")  List <WebElement> teRange_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[16]")  List <WebElement> teDC_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[17]")  List <WebElement> teShipDate_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[18]")  List <WebElement> teLatestShipDate_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[19]")  List <WebElement> teFreightTerms_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[20]")  List <WebElement> teBookingStatus_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[21]")  List <WebElement> teAuthorityStatus_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[22]")  List <WebElement> teVendorBooked_Multi;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[23]")  List <WebElement> teDialog_Multi;

    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[20]") List <WebElement> lstBookingstatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[22]") List <WebElement>  lstVendorBooked;
	
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[3]/div")  WebElement  thbookingVendor;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[5]/div")  WebElement  thbookingPONumber;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[7]/div")  WebElement  thbookingProduct;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[9]/div")  WebElement  thbookingVendorRef;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[10]/div")  WebElement  thbookingIdentifier;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[13]/div")  WebElement  thbookingDC;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[14]/div")  WebElement  thbookingQC;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[15]/div")  WebElement  thbookingPOQty;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[17]/div")  WebElement thbookingOpenQty;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[18]/div") WebElement  thbookingBkdQty;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[19]/div") WebElement  thbookingBkdCtns;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[20]/div") WebElement  thbookingBkdCbm;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[21]/div")  WebElement thbookingGrKgs;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[22]/div")  WebElement thbookingChKgs;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[23]/div")  WebElement thbookingPackTypes;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[24]/div")  WebElement thbookingFreightTerms;
	 @FindBy(xpath=".//*[@id='ext-gen28']/div/table/thead/tr/td[25]/div")  WebElement thbookingDueInCFS;
	 
     @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[1]/div")  WebElement  ghMode;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[2]/div")  WebElement  ghLoadType;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[3]/div")  WebElement  ghLoadRefrence;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[4]/div")  WebElement  ghCarrier;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[5]/div")  WebElement ghClosingDate;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[6]/div")  WebElement  ghCarrierType;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[7]/div")  WebElement  ghContactNo;
	 @FindBy(xpath=".//*[@id='ext-gen35']/div/table/thead/tr/td[8]/div")  WebElement  ghColoader;
	 
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[3]/div")  WebElement  ghleg;
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[5]/div")  WebElement  ghVessel;
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[6]/div")  WebElement  ghVoyage;
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[7]/div")  WebElement  ghOriginPort;
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[8]/div")  WebElement  ghDestinationPort;
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[9]/div")  WebElement  ghETD;
	 @FindBy(xpath=".//*[@id='ext-gen57']/div/table/thead/tr/td[10]/div")  WebElement  ghETA;
	 
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[1]/div")  WebElement  ghKPIMode;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[2]/div")  WebElement  ghKPILoadType;;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[3]/div")  WebElement  ghKPILoadRefrence;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[4]/div")  WebElement  ghKPICarrier;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[5]/div")  WebElement ghKPIClosingDate;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[6]/div")  WebElement ghKPICarrierType;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[7]/div")  WebElement ghKPIContactNo;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[8]/div")  WebElement ghKPIColoader;
	 @FindBy(xpath=".//*[@id='ext-gen27']/div/table/thead/tr/td[9]/div")  WebElement ghKPIFreightTerms;
	 
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[2]/div")  WebElement  ghKPIleg;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[4]/div")  WebElement  ghKPIVessel;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[5]/div")  WebElement  ghKPIVoyage;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[6]/div")  WebElement  ghKPIOriginPort;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[7]/div")  WebElement  ghKPIDestinationPort;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[8]/div")  WebElement  ghKPIETD;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[9]/div")  WebElement ghKPIETA;
	 @FindBy(xpath=".//*[@id='ext-gen42']/div/table/thead/tr/td[10]/div")  WebElement ghKPIPrimaryLeg;
	 
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[2]/div")  WebElement  ghKPIVendor;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[4]/div")  WebElement  ghKPIPONumber;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[5]/div")  WebElement  ghKPIProduct;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[8]/div")  WebElement ghKPIbookingPOQty;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[9]/div")  WebElement ghKPIOpenQty;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[10]/div") WebElement  ghKPIBkdQty;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[11]/div") WebElement  ghKPIQuantityVariance;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[12]/div") WebElement  ghKPIPercVariance;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[13]/div") WebElement  ghKPIBkdCtns;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[14]/div") WebElement  ghKPIBkdCbm;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[15]/div")  WebElement ghKPIGrKgs;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[16]/div")  WebElement ghKPIChKgs;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[17]/div")  WebElement ghKPIPackTypes;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[18]/div")  WebElement ghKPIDC;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[19]/div")  WebElement ghKPIQC;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[20]/div")  WebElement ghKPIPOShipDate;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[21]/div")  WebElement ghKPIDaysVariance;
	 @FindBy(xpath=".//*[@id='ext-gen55']/div/table/thead/tr/td[22]/div")  WebElement ghKPIReason;

	 
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a")  List <WebElement> trEvent;;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]")  List <WebElement> trAcheived;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]")  List <WebElement> trUserName;
	

	
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[2]/div")  WebElement  ghReviewVendor;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[4]/div")  WebElement  ghReviewPONumber;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[5]/div")  WebElement  ghReviewProduct;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[8]/div")  WebElement ghReviewPOQty;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[9]/div")  WebElement ghReviewOpenQty;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[10]/div") WebElement  ghReviewBkdQty;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[11]/div") WebElement  ghReviewBkdCtns;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[12]/div") WebElement  ghReviewBkdCbm;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[14]/div")  WebElement ghReviewChKgs;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[15]/div")  WebElement ghReviewPackTypes;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[16]/div")  WebElement ghReviewDC;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[17]/div")  WebElement ghReviewQC;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[18]/div")  WebElement ghReviewBookingStatus;
	 @FindBy(xpath=".//*[@id='ext-gen24']/div/table/thead/tr/td[19]/div")  WebElement ghReviewReason;
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[1]/a")  List <WebElement> tePONumber_StatusMulti;;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[23]/a/b/font")  List <WebElement> testatus_StatusMulti;
	 
	 @FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr/td/div/div[1]/h2/span[7]") WebElement CCmsg ;
	 @FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr/td/div/div[2]") WebElement txtmsg ;
	 @FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr/td/div/div[1]/h2/span[5]") WebElement NewSubject ;
	 

	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]")  WebElement  ghoriginVendor;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]")  WebElement  ghOriginFactory;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]")  WebElement  ghOriginMode;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]")  WebElement  ghOriginOriginPort;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]")  WebElement  ghOriginDestinationPort;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[6]")  WebElement  ghOriginVessel;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]")  WebElement  ghOriginVoyage;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[8]")  WebElement ghOriginETD;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]")  WebElement  ghOriginETA;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[10]")  WebElement  ghOriginLoading;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[11]")  WebElement ghOriginVendorBookingID;
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[2]/a")  List <WebElement> teVendorPONumber_PONumberMulti;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[17]")  List <WebElement> teVendorstatus_StatusMulti;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[4]/a")  List <WebElement> teAuthorityPONumber_PONumberMulti;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[21]/font")  List <WebElement> teAuthoritystatus_StatusMulti;
		
	 @FindBy(xpath = ".//*[@id='InnerContent']/p") WebElement noResult;
	
	 @FindBy(xpath=".//*[text()='No items were found.']") WebElement noItem;
	 @FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
	
	 public WebElement getBtnOK1() {
			return btnOK1;
		}
	 
	public WebElement getNoItem() {
		return noItem;
	}

	public WebElement getNoResult() {
		return noResult;
	}
	
	public WebElement getProcessedcheckboxMsg() {
		return ProcessedcheckboxMsg;
	}

	public WebElement getBtnClose() {
		return btnClose;
	}

	public WebElement getLnkDailog() {
		return lnkDailog;
	}

	public WebElement getTxtProcessedMsg() {
		return txtProcessedMsg;
	}

	public WebElement getLnklogoff() {
		return lnklogoff;
	}

	public List<WebElement> getTeAuthorityPONumber_PONumberMulti() {
		return teAuthorityPONumber_PONumberMulti;
	}

	public List<WebElement> getTeVendorstatus_StatusMulti() {
		return teVendorstatus_StatusMulti;
	}

	public List<WebElement> getTeVendorPONumber_PONumberMulti() {
		return teVendorPONumber_PONumberMulti;
	}

	public List<WebElement> getTeAuthoritystatus_StatusMulti() {
		return teAuthoritystatus_StatusMulti;
	}

	public WebElement getLnkShipAuthority() {
		return lnkShipAuthority;
	}

	public WebElement getLnkVendorBooking() {
		return lnkVendorBooking;
	}
	
	public WebElement getGhoriginVendor() {
		return ghoriginVendor;
	}
	public WebElement getOriginFactory() {
		return ghOriginFactory;
	}
	public WebElement getGhOriginMode() {
		return ghOriginMode;
	}
	public WebElement getGhOriginOriginPort() {
		return ghOriginOriginPort;
	}
	public WebElement getGhOriginDestinationPort() {
		return ghOriginDestinationPort;
	}
	public WebElement getGhOriginVessel() {
		return ghOriginVessel;
	}
	public WebElement getGhOriginVoyage() {
		return ghOriginVoyage;
	}
	public WebElement getGhOriginETD() {
		return ghOriginETD;
	}
	public WebElement getGhOriginETA() {
		return ghOriginETA;
	}
	public WebElement getGhOriginVendorBookingID() {
		return ghOriginVendorBookingID;
	}
	public WebElement getGhOriginLoading() {
		return ghOriginLoading;
		}
	
	public void setGhOriginPort(WebElement ghOriginPort) {
		this.ghOriginPort = ghOriginPort;
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
	public WebElement getBtnYes() {
		return btnYes;
	}
	public WebElement getTxtDeleteLeg() {
		return txtDeleteLeg;
	}
	public WebElement getBtnNO() {
		return btnNO;
	}
	public WebElement getNewDialogComments() {
		return NewDialogComments;
	}
	public WebElement getTeVendor() {
		return teVendor;
	}
	public WebElement getTeLoading() {
		return teLoading;
	}
	public WebElement getTeOriginPort() {
		return teOriginPort;
	}
	public WebElement getTeMode() {
		return teMode;
	}
	public WebElement getTeFeederVessel() {
		return teFeederVessel;
	}
	public WebElement getTeFeederVoyage() {
		return teFeederVoyage;
	}
	public WebElement getTeFeederETD() {
		return teFeederETD;
	}
	public WebElement getTeFeederETA() {
		return teFeederETA;
	}
	public WebElement getTeDestinationPort() {
		return teDestinationPort;
	}
	public WebElement getTeCarrier() {
		return teCarrier;
	}
	public WebElement getTeMotherVessel() {
		return teMotherVessel;
	}
	public WebElement getTeMotherVoyage() {
		return teMotherVoyage;
	}
	public WebElement getTeMotherETD() {
		return teMotherETD;
	}
	public WebElement getTeMotherETA() {
		return teMotherETA;
	}
	
	public WebElement getDialogContenterrormsg() {
		return DialogContenterrormsg;
	}
	public WebElement getGhSubject() {
		return ghSubject;
	}
	public WebElement getGhMessage() {
		return ghMessage;
	}
	public WebElement getGhCopybyEMailto() {
		return ghCopybyEMailto;
	}
	public WebElement getChvDialog() {
		return chvDialog;
	}
	public List<WebElement> getTePONumber_StatusMulti() {
		return tePONumber_StatusMulti;
	}
	public List<WebElement> getTestatus_StatusMulti() {
		return testatus_StatusMulti;
	}
	public WebElement getChvReview() {
		return chvReview;
	}
	public WebElement getGhReviewVendor() {
		return ghReviewVendor;
	}
	public WebElement getGhReviewPONumber() {
		return ghReviewPONumber;
	}
	public WebElement getGhReviewProduct() {
		return ghReviewProduct;
	}
	public WebElement getGhReviewPOQty() {
		return ghReviewPOQty;
	}
	public WebElement getGhReviewOpenQty() {
		return ghReviewOpenQty;
	}
	public WebElement getGhReviewBkdQty() {
		return ghReviewBkdQty;
	}
	public WebElement getGhReviewBkdCtns() {
		return ghReviewBkdCtns;
	}
	public WebElement getGhReviewBkdCbm() {
		return ghReviewBkdCbm;
	}
	public WebElement getGhReviewChKgs() {
		return ghReviewChKgs;
	}
	public WebElement getGhReviewPackTypes() {
		return ghReviewPackTypes;
	}
	public WebElement getGhReviewDC() {
		return ghReviewDC;
	}
	public WebElement getGhReviewQC() {
		return ghReviewQC;
	}
	public WebElement getGhReviewBookingStatus() {
		return ghReviewBookingStatus;
	}
	public WebElement getGhReviewReason() {
		return ghReviewReason;
	}
	public List<WebElement> getTeSelect_Multi() {
		return teSelect_Multi;
	}
	public List<WebElement> getTePONumber_Multi() {
		return tePONumber_Multi;
	}
	public List<WebElement> getTeProduct_Multi() {
		return teProduct_Multi;
	}
	public List<WebElement> getTeIdentifier_Multi() {
		return teIdentifier_Multi;
	}
	public List<WebElement> getTePriority_Multi() {
		return tePriority_Multi;
	}
	public List<WebElement> getTeVendorRef_Multi() {
		return teVendorRef_Multi;
	}
	public List<WebElement> getTeSupplierRef_Multi() {
		return teSupplierRef_Multi;
	}
	public List<WebElement> getTeCategory_Multi() {
		return teCategory_Multi;
	}
	public List<WebElement> getTePOQty_Multi() {
		return tePOQty_Multi;
	}
	public List<WebElement> getTeBkdQty_Multi() {
		return teBkdQty_Multi;
	}
	public List<WebElement> getTeOpenQty_Multi() {
		return teOpenQty_Multi;
	}
	public List<WebElement> getTeCtns_Multi() {
		return teCtns_Multi;
	}
	public List<WebElement> getTeCbm_Multi() {
		return teCbm_Multi;
	}
	public List<WebElement> getTePackType_Multi() {
		return tePackType_Multi;
	}
	public List<WebElement> getTeRange_Multi() {
		return teRange_Multi;
	}
	public List<WebElement> getTeDC_Multi() {
		return teDC_Multi;
	}
	public List<WebElement> getTeShipDate_Multi() {
		return teShipDate_Multi;
	}
	public List<WebElement> getTeLatestShipDate_Multi() {
		return teLatestShipDate_Multi;
	}
	public List<WebElement> getTeFreightTerms_Multi() {
		return teFreightTerms_Multi;
	}
	public List<WebElement> getTeBookingStatus_Multi() {
		return teBookingStatus_Multi;
	}
	public List<WebElement> getTeAuthorityStatus_Multi() {
		return teAuthorityStatus_Multi;
	}
	public List<WebElement> getTeVendorBooked_Multi() {
		return teVendorBooked_Multi;
	}
	public List<WebElement> getTeDialog_Multi() {
		return teDialog_Multi;
	}
	public WebElement getShipmentPostatus() {
		return ShipmentPostatus;
	}
	public List<WebElement> getTrEvent() {
		return trEvent;
	}
	public List<WebElement> getTrAcheived() {
		return trAcheived;
	}
	public List<WebElement> getTrUserName() {
		return trUserName;
	}
	public WebElement getLnkFind() {
		return lnkFind;
	}
	public WebElement getLnkCompleteList() {
		return lnkCompleteList;
	}
	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}
	public WebElement getBtnRun() {
		return btnRun;
	}
	public WebElement getTitleShipment() {
		return TitleShipment;
	}
	public WebElement getBtnUpdateSKU() {
		return btnUpdateSKU;
	}
	public WebElement getClickProducthyperlink() {
		return clickProducthyperlink;
	}
	public WebElement getFldRefNo() {
		return fldRefNo;
	}
	public WebElement getBtnApply() {
		return btnApply;
	}
	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	public WebElement getLnkEdit() {
		return lnkEdit;
	}
	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}
	public WebElement getGrdyes() {
		return grdyes;
	}
	public WebElement getContenterrormsg() {
		return Contenterrormsg;
	}
	public WebElement getGhKPIMode() {
		return ghKPIMode;
	}
	public WebElement getGhKPILoadType() {
		return ghKPILoadType;
	}
	public WebElement getGhKPILoadRefrence() {
		return ghKPILoadRefrence;
	}
	public WebElement getGhKPICarrier() {
		return ghKPICarrier;
	}
	public WebElement getGhKPIClosingDate() {
		return ghKPIClosingDate;
	}
	public WebElement getGhKPICarrierType() {
		return ghKPICarrierType;
	}
	public WebElement getGhKPIContactNo() {
		return ghKPIContactNo;
	}
	public WebElement getGhKPIColoader() {
		return ghKPIColoader;
	}
	public WebElement getGhKPIFreightTerms() {
		return ghKPIFreightTerms;
	}
	public WebElement getGhKPIleg() {
		return ghKPIleg;
	}
	public WebElement getGhKPIVessel() {
		return ghKPIVessel;
	}
	public WebElement getGhKPIVoyage() {
		return ghKPIVoyage;
	}
	public WebElement getGhKPIOriginPort() {
		return ghKPIOriginPort;
	}
	public WebElement getGhKPIDestinationPort() {
		return ghKPIDestinationPort;
	}
	public WebElement getGhKPIETD() {
		return ghKPIETD;
	}
	public WebElement getGhKPIETA() {
		return ghKPIETA;
	}
	public WebElement getGhKPIPrimaryLeg() {
		return ghKPIPrimaryLeg;
	}
	public WebElement getGhKPIVendor() {
		return ghKPIVendor;
	}
	public WebElement getGhKPIPONumber() {
		return ghKPIPONumber;
	}
	public WebElement getGhKPIProduct() {
		return ghKPIProduct;
	}
	public WebElement getGhKPIbookingPOQty() {
		return ghKPIbookingPOQty;
	}
	public WebElement getGhKPIOpenQty() {
		return ghKPIOpenQty;
	}
	public WebElement getGhKPIBkdQty() {
		return ghKPIBkdQty;
	}
	public WebElement getGhKPIQuantityVariance() {
		return ghKPIQuantityVariance;
	}
	public WebElement getGhKPIPercVariance() {
		return ghKPIPercVariance;
	}
	public WebElement getGhKPIBkdCtns() {
		return ghKPIBkdCtns;
	}
	public WebElement getGhKPIBkdCbm() {
		return ghKPIBkdCbm;
	}
	public WebElement getGhKPIGrKgs() {
		return ghKPIGrKgs;
	}
	public WebElement getGhKPIChKgs() {
		return ghKPIChKgs;
	}
	public WebElement getGhKPIPackTypes() {
		return ghKPIPackTypes;
	}
	public WebElement getGhKPIDC() {
		return ghKPIDC;
	}
	public WebElement getGhKPIQC() {
		return ghKPIQC;
	}
	public WebElement getGhKPIPOShipDate() {
		return ghKPIPOShipDate;
	}
	public WebElement getGhKPIDaysVariance() {
		return ghKPIDaysVariance;
	}
	public WebElement getGhKPIReason() {
		return ghKPIReason;
	}
	public WebElement getBtnAddLeg() {
		return btnAddLeg;
	}
	public WebElement getBtnDeleteLeg() {
		return btnDeleteLeg;
	}
	public WebElement getGhleg() {
		return ghleg;
	}
	public WebElement getGhVessel() {
		return ghVessel;
	}
	public WebElement getGhVoyage() {
		return ghVoyage;
	}
	public WebElement getGhOriginPort() {
		return ghOriginPort;
	}
	public WebElement getGhDestinationPort() {
		return ghDestinationPort;
	}
	public WebElement getGhETD() {
		return ghETD;
	}
	public WebElement getGhETA() {
		return ghETA;
	}
	public WebElement getGhMode() {
		return ghMode;
	}
	public WebElement getGhLoadType() {
		return ghLoadType;
	}
	public WebElement getGhLoadRefrence() {
		return ghLoadRefrence;
	}
	public WebElement getGhCarrier() {
		return ghCarrier;
	}
	public WebElement getGhClosingDate() {
		return ghClosingDate;
	}
	public WebElement getGhCarrierType() {
		return ghCarrierType;
	}
	public WebElement getGhContactNo() {
		return ghContactNo;
	}
	public WebElement getGhColoader() {
		return ghColoader;
	}
	public WebElement getLnkTools() {
		return lnkTools;
	}
    public WebElement getLnkShipmentBooking() {
		return lnkShipmentBooking;
	}
    
    public WebElement getChvSearch() {
		return chvSearch;
	}
    
    public WebElement getChvSearch1() {
		return chvSearch1;
	}
	public WebElement getChvSelect() {
		return chvSelect;
	}
	
	public WebElement getChvSelect1() {
		return chvSelect1;
	}
	public WebElement getChvBooking() {
		return chvBooking;
	}
	
	public WebElement getChvBooking1() {
		return chvBooking1;
	}
	public WebElement getChvRouting() {
		return chvRouting;
	}
	
	public WebElement getChvRouting1() {
		return chvRouting1;
	}
	public WebElement getChvKPI() {
		return chvKPI;
	}
	public WebElement getChvKPI1() {
		return chvKPI1;
	}
	public WebElement getChvComplete() {
		return chvComplete;
	}
	
	public WebElement getChvComplete1() {
		return chvComplete1;
	}
	public WebElement getChvComplete2() {
		return chvComplete2;
	}
	
	public WebElement getContentmsg() {
		return contentmsg;
	}
	
	public WebElement getThOriginPort() {
		return thOriginPort;
	}
	public WebElement getThMode() {
		return thMode;
	}
	public WebElement getThFeederVessel() {
		return thFeederVessel;
	}
	public WebElement getThFeederVoyage() {
		return thFeederVoyage;
	}
	public WebElement getThFeederETD() {
		return thFeederETD;
	}
	public WebElement getThFeederETA() {
		return thFeederETA;
	}
	public WebElement getThDestinationPort() {
		return thDestinationPort;
	}
	public WebElement getThCarrier() {
		return thCarrier;
	}
	public WebElement getThMotherVessel() {
		return thMotherVessel;
	}
	public WebElement getThMotherVoyage() {
		return thMotherVoyage;
	}
	public WebElement getThMotherETD() {
		return thMotherETD;
	}
	public WebElement getThMotherETA() {
		return thMotherETA;
	}
	
	public WebElement getThPONumber() {
		return thPONumber;
	}
	public WebElement getThProduct() {
		return thProduct;
	}
	public WebElement getThIdentifier() {
		return thIdentifier;
	}
	public WebElement getThPriority() {
		return thPriority;
	}
	public WebElement getThVendorRef() {
		return thVendorRef;
	}
	public WebElement getThSupplierRef() {
		return thSupplierRef;
	}
	public WebElement getThCategory() {
		return thCategory;
	}
	public WebElement getThPOQty() {
		return thPOQty;
	}
	public WebElement getThBkdQty() {
		return thBkdQty;
	}
	public WebElement getThOpenQty() {
		return thOpenQty;
	}
	public WebElement getThCtns() {
		return thCtns;
	}
	public WebElement getThCbm() {
		return thCbm;
	}
	public WebElement getThPackType() {
		return thPackType;
	}
	public WebElement getThRange() {
		return thRange;
	}
	public WebElement getThDC() {
		return thDC;
	}
	public WebElement getThShipDate() {
		return thShipDate;
	}
	public WebElement getThLatestShipDate() {
		return thLatestShipDate;
	}
	public WebElement getThFreightTerms() {
		return thFreightTerms;
	}
	public WebElement getThBookingStatus() {
		return thBookingStatus;
	}
	public WebElement getThAuthorityStatus() {
		return thAuthorityStatus;
	}
	public WebElement getThVendorBooked() {
		return thVendorBooked;
	}
	public WebElement getThDialog() {
		return thDialog;
	}
	
	public WebElement getThVendor() {
		return thVendor;
	}
	
	public WebElement getThLoading() {
		return thLoading;
	}
	
	public List<WebElement> getLstBookingstatus() {
		return lstBookingstatus;
	}
	public List<WebElement> getLstVendorBooked() {
		return lstVendorBooked;
	}
	public WebElement getThbookingVendor() {
		return thbookingVendor;
	}
	public WebElement getThbookingPONumber() {
		return thbookingPONumber;
	}
	public WebElement getThbookingProduct() {
		return thbookingProduct;
	}
	public WebElement getThbookingVendorRef() {
		return thbookingVendorRef;
	}
	public WebElement getThbookingIdentifier() {
		return thbookingIdentifier;
	}
	public WebElement getThbookingDC() {
		return thbookingDC;
	}
	public WebElement getThbookingQC() {
		return thbookingQC;
	}
	public WebElement getThbookingPOQty() {
		return thbookingPOQty;
	}
	public WebElement getThbookingOpenQty() {
		return thbookingOpenQty;
	}
	public WebElement getThbookingBkdQty() {
		return thbookingBkdQty;
	}
	public WebElement getThbookingBkdCtns() {
		return thbookingBkdCtns;
	}
	public WebElement getThbookingBkdCbm() {
		return thbookingBkdCbm;
	}
	public WebElement getThbookingGrKgs() {
		return thbookingGrKgs;
	}
	public WebElement getThbookingChKgs() {
		return thbookingChKgs;
	}
	public WebElement getThbookingPackTypes() {
		return thbookingPackTypes;
	}
	
	public WebElement getThbookingFreightTerms() {
		return thbookingFreightTerms;
	}
	public WebElement getThbookingDueInCFS() {
		return thbookingDueInCFS;
	}
	
	public WebElement getClickhyperlink() {
		return clickhyperlink;
	}
	
	public WebElement getGhShipment() {
		return ghShipment;
	}
	public WebElement getGhVoyageLegs() {
		return ghVoyageLegs;
	}
	
	public AdjunoLIMAShipmentBookingPOM(WebDriver driver)
	{
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }

    private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
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
	
	public String getPageTitle()
	{
    	objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	String strTitle = "";
		 if (objAdjunoUILibrary.isPageLoaded("LIMA_Order_POConfirmation_SUT"))
		 {
			 strTitle = driver.getTitle();
		 }
		 return strTitle;
	}
	
	public boolean checkDoesChevronExist(WebElement chevron) {
        Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        bFlag = objAdjunoUILibrary.isElementPresent(chevron);
        
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
	
	public boolean clearInputField(String formName, String controlName) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
		try {
			bFlag = we.isDisplayed();
			we.clear();
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}
	
	public boolean setFieldValue(String strFieldValue, String formName,String controlName) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);

		try {
			bFlag = we.isDisplayed();
			we.sendKeys(strFieldValue);
			wait(2000);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}

	public boolean clickChevron(WebElement we) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = objAdjunoUILibrary.isElementPresent(we);
			System.out.println("^^^^^^^^^^^"+bFlag);
			we.click();
			wait(4000);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	
	public boolean setFieldValueForGridCell(String strFormName,String strGridControlName, int nRow, String strColumnName,String strValue) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			objAdjunoUILibrary.setGridCellValue(strFormName,strGridControlName, nRow, strColumnName, strValue);
			WebElement we;
			
		we = objAdjunoUILibrary.getGridCellElementEditor(strFormName,strGridControlName, nRow, strColumnName);
			
			we.sendKeys(Keys.TAB);
			bFlag = true;
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	


	public boolean isElementPresent(WebElement we) {
        Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        bFlag = objAdjunoUILibrary.isElementPresent(we);
        
        return bFlag;
    }
	
	public boolean compareTwoStringValuesToSame(String strValue1,
			String strValue2) {
		boolean bFlag = true;
		if ((!(isNullOrBlank(strValue1))) && (!(isNullOrBlank(strValue2)))) {
			if (strValue1.equalsIgnoreCase(strValue2)) {
			} else {
				bFlag = false;
			}
		} else {
			bFlag = false;
		}

		return bFlag;
	}
	
	public ArrayList<Shipmentdata> selectMulitpleProducts(int nMaxProductsToSelect) {
		int nCounterLen = 0;
		ArrayList<Shipmentdata> lstSearchResults = new ArrayList<Shipmentdata>();

		// TODO:
		// ArrayList<String> lstStatus = new ArrayList<String>();

		if (tePONumber_Multi.size() < nMaxProductsToSelect)
			nCounterLen = tePONumber_Multi.size();
		else
			nCounterLen = nMaxProductsToSelect;

		for (int i = 0; i <= nCounterLen - 1; i++) {

			Shipmentdata order = new Shipmentdata(( tePONumber_Multi).get(i).getText(), teProduct_Multi.get(i).getText(),teIdentifier_Multi.get(i).getText(), tePriority_Multi.get(i).getText(), teVendorRef_Multi.get(i).getText(),teSupplierRef_Multi.get(i).getText(), teCategory_Multi.get(i).getText(), tePOQty_Multi.get(i).getText(), teBkdQty_Multi.get(i).getText(),teOpenQty_Multi.get(i).getText(),teCtns_Multi.get(i).getText(), tePackType_Multi.get(i).getText(),teRange_Multi.get(i).getText(),teDC_Multi.get(i).getText(),teShipDate_Multi.get(i).getText(),teLatestShipDate_Multi.get(i).getText(),teFreightTerms_Multi.get(i).getText(),teBookingStatus_Multi.get(i).getText(),teAuthorityStatus_Multi.get(i).getText(),teVendorBooked_Multi.get(i).getText(),teDialog_Multi.get(i).getText());
			teSelect_Multi.get(i).click();
			lstSearchResults.add(order);

			if (i == (nMaxProductsToSelect - 1))
				break;
		}

		return lstSearchResults;
	}

	public int getNoOrRowsResulted() {
		// TODO Auto-generated method stub
		return tePONumber_Multi.size();
	}

	public boolean clickButton(String formName, String controlName) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);

		try {
			bFlag = we.isDisplayed();
			we.click();
			wait(4000);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}

	public boolean clickButtonUsingWebElement(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = we.isDisplayed();
			we.click();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}

	public boolean setFieldValueForWebElement(WebElement we,String strFieldValue) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = we.isDisplayed();
			we.sendKeys(strFieldValue);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	
	public long getDifference(Date today, Date date) {

		if (date == null || today == null)
			return 0;

		return ((today.getTime() / 60000) - (date.getTime() / 60000));

	}
	
	public long getTrackValue(String userName) throws ParseException {
		int length = getTrEvent().size();
		long min = 0;
		List<Tracksdata> list = new ArrayList<Tracksdata>();
		for (int i = 0; i < length - 1; i++) {
			Tracksdata track = new Tracksdata(getTrEvent().get(i).getText(),getTrAcheived().get(i).getText(), getTrUserName().get(i).getText());
			list.add(track);
		}

		for (int j = 0; j < list.size() - 1; j++) {
			if (list.get(j).getEvent().equalsIgnoreCase("Purchase Order")&& list.get(j).getUsername().equalsIgnoreCase(userName)) {
				String strAchievedDate = list.get(j).getAcheived();

				SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
				Date today = new Date();

				Date achievedDate = formatter.parse(strAchievedDate);

				min = getDifference(today, achievedDate);
				System.out.println("difference is:" + min);
			}
		}
		return min;
	}
	
	public List<Shipmentdata> getStaus() {
		List<Shipmentdata> list = new ArrayList<Shipmentdata>();
		System.out.println("Size of PO numbers"+ getTeBookingStatus_Multi().size());
		for (int i = 0; i <= getTeBookingStatus_Multi().size() -2; i++) {
			Shipmentdata po = new Shipmentdata(getTePONumber_Multi().get(i).getText(), getTeBookingStatus_Multi().get(i).getText(),getTeVendorBooked_Multi().get(i).getText());
			// scroll down
			// JavascriptExecutor jse = (JavascriptExecutor)driver;

			list.add(po);
			System.out.println("For" + i + " po number:" + po.getPONumber()+ "Bookingstatus:" + po.getBookingStatus()+ "VendorBooked:" + po.getVendorBooked());
			// jse.executeScript("window.scrollBy(0,100)", "");
		}

		return list;
	}
//	
//	public String verifyStaus(String strBookingstatus, String strVendorBooked,
//			List<Shipmentdata> list) {
//		String strMessage = "";
//		for (int i = 0; i <= list.size() - 1; i++) {
//			System.out.println("order number :" + i);
//			if (list.get(i).getBookingStatus().equalsIgnoreCase(strBookingstatus)) {
//				System.out.println("test--" + strBookingstatus);
//			} else {
//
//				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "Bookingstatus is not matching";
//			}
//
//			if (list.get(i).getBookingStatus().equalsIgnoreCase(strVendorBooked)) {
//				System.out.println("test--" + strVendorBooked);
//			} else {
//
//				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "VendorBooked is not matching";
//			}
//		}
//		System.out.println("error message:" + strMessage);
//		return strMessage;
//	}
	public String compareshipmentgriddata(String strPoNumber1,String strBookingstatus, String strVendorBooked,List<Shipmentdata> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size() - 1; i++) {

			if (list.get(i).getBookingStatus().equalsIgnoreCase(strBookingstatus)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "Bookingstatus is not matching";
			}

			if (list.get(i).getPONumber().equalsIgnoreCase(strPoNumber1)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "PONumber is not matching";
			}

			if (list.get(i).getVendorBooked().equalsIgnoreCase(strVendorBooked)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "VendorBooked is not matching";
			}
		}
		System.out.println("error message:" + strMessage);
		return strMessage;
	}
	
	public boolean clickcheckbox(String strFormName,String strGridControlName,int nRow, String strColumnName) {
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
        
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
	

	public List<Shipmentdata> getStaus1() {
        List<Shipmentdata> list = new ArrayList<Shipmentdata>();
        System.out.println("Size of PO numbers"+getTePONumber_StatusMulti().size());
        for (int i = 0; i <= getTePONumber_StatusMulti().size()-2; i++) {
        	Shipmentdata po = new Shipmentdata(getTePONumber_StatusMulti().get(i).getText(),getTestatus_StatusMulti().get(i).getText()); 
            //scroll down 
            //JavascriptExecutor jse = (JavascriptExecutor)driver;
            
            list.add(po);
            System.out.println("For"+i + " po number:"+po.getPONumber() +"status"+po.getStatus());
            //jse.executeScript("window.scrollBy(0,100)", "");
        }
        
        return list;
    }
	
	
	public String compareshipmentgriddata1(String strPoNumber1,String strStatus, List<Shipmentdata> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size() - 1; i++) {

			if (list.get(i).getStatus().equalsIgnoreCase(strStatus)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber() + "Status is not matching";
			}

			if (list.get(i).getPONumber().equalsIgnoreCase(strPoNumber1)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "PONumber is not matching";
			}

		}
		System.out.println("error message:" + strMessage);
		return strMessage;
	}
	
	
	public List<Shipmentdata> getStaus2() {
        List<Shipmentdata> list = new ArrayList<Shipmentdata>();
        System.out.println("Size of PO numbers"+getTeVendorPONumber_PONumberMulti().size());
        for (int i = 0; i <= getTeVendorPONumber_PONumberMulti().size()-1; i++) {
        	Shipmentdata po = new Shipmentdata(getTeVendorPONumber_PONumberMulti().get(i).getText(),getTeVendorstatus_StatusMulti().get(i).getText()); 
            //scroll down 
            //JavascriptExecutor jse = (JavascriptExecutor)driver;
            
            list.add(po);
            System.out.println("For"+i + " po number:"+po.getPONumber() +"status"+po.getStatus());
            //jse.executeScript("window.scrollBy(0,100)", "");
        }
        
        return list;
    }
	
	public String compareshipmentgriddata2(String strPoNumber4,String strStatus, List<Shipmentdata> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size() - 1; i++) {

			if (list.get(i).getStatus().equalsIgnoreCase(strStatus)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber() + "Status is not matching";
			}

			if (list.get(i).getPONumber().equalsIgnoreCase(strPoNumber4)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "PONumber is not matching";
			}

		}
		System.out.println("error message:" + strMessage);
		return strMessage;
	}
	
	
	public List<Shipmentdata> getStaus3() {
        List<Shipmentdata> list = new ArrayList<Shipmentdata>();
        System.out.println("Size of PO numbers"+getTeAuthorityPONumber_PONumberMulti().size());
        for (int i = 0; i <= getTeAuthorityPONumber_PONumberMulti().size()-1; i++) {
        	Shipmentdata po = new Shipmentdata(getTeAuthorityPONumber_PONumberMulti().get(i).getText(),getTeAuthoritystatus_StatusMulti().get(i).getText()); 
            //scroll down 
            //JavascriptExecutor jse = (JavascriptExecutor)driver;
            
            list.add(po);
            System.out.println("For"+i + " po number:"+po.getPONumber() +"status"+po.getStatus());
            //jse.executeScript("window.scrollBy(0,100)", "");
        }
        
        return list;
    }
	
	public String compareshipmentgriddata3(String strPoNumber4,String strStatus, List<Shipmentdata> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size() - 1; i++) {

			if (list.get(i).getStatus().equalsIgnoreCase(strStatus)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber() + "Status is not matching";
			}

			if (list.get(i).getPONumber().equalsIgnoreCase(strPoNumber4)) {

			} else {

				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPONumber()+ "PONumber is not matching";
			}

		}
		System.out.println("error message:" + strMessage);
		return strMessage;
	}
	
	
	

	public String getDate(int days,String datePattern){
	       
	       Calendar cal = Calendar.getInstance();
	       cal.add(Calendar.DATE, days);
	       
	        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	        dateFormat.format( cal.getTime());
	       
	       
	       return dateFormat.format(cal.getTime());            
	   }
	
	
	
	public long getNoOfGridRows(String strFormName, String strControlName)
    {
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        long GridRows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strControlName);
        
        if(GridRows>0){
            
        }
        else{
            GridRows = 0;
        }
        
        return GridRows;
    }
	
	public Shipmentdata getShipmentData() {
	    
	    
 
	Shipmentdata po = new Shipmentdata(getTeOriginPort().getText(),"",getTeMode().getText(),getTeFeederVessel().getText(),getTeFeederVoyage().getText(),getTeFeederETD().getText(),getTeFeederETA().getText(),getTeDestinationPort().getText(),getTeCarrier().getText(),getTeMotherVessel().getText(),getTeMotherVoyage().getText(),getTeMotherETD().getText(),getTeMotherETA().getText(),getTeVendor().getText()); 
        
    return po;
	}
    
    
    public Shipmentdata getVendorBokkingData() {
	    
	    
    Shipmentdata po = new Shipmentdata(getGhoriginVendor().getText(),"",getOriginFactory().getText(),getGhOriginMode().getText(),getGhOriginOriginPort().getText(),getGhOriginDestinationPort().getText(),getGhOriginVessel().getText(),getGhOriginVoyage().getText(),getGhOriginETD().getText(),getGhOriginETA().getText(),getGhOriginLoading().getText(),getGhOriginVendorBookingID().getText(),"",""); 
            
      return po;
    
}
    
	public String verifyStatusForAllProduct(String strBookingstatus) {
		String strMessage = "";

		int statusLength = teBookingStatus_Multi.size();

		for (int i = 0; i < statusLength - 1; i++) {
			String status = teBookingStatus_Multi.get(i).getText();
			if (strBookingstatus.equals(" NOT overbooked")) {

				if (status.equalsIgnoreCase("Complete(R)")) {

				} else {
					strMessage = strMessage+ teBookingStatus_Multi.get(i).getText()+ "in this line" + i + "Not found";
				}

			} else if (strBookingstatus.equals("Overbooked")) {

				if (status.equalsIgnoreCase("Overbooked(R)")) {
				} else {
					strMessage = strMessage+ teBookingStatus_Multi.get(i).getText()+ "in this line" + i + "Not found";
				}

			} else if (strBookingstatus.equals("Partial Booked")) {

				if (status.equalsIgnoreCase("Partial Booked")) {
				} else {
					strMessage = strMessage+ teBookingStatus_Multi.get(i).getText()+ "in this line" + i + "Not found";
				}
			}

			else {
				System.out.println("Booking status is not known");

			}

		}
		return strMessage;
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
    public String getWebElementCellValue(WebElement we)
    {
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        String strWeCellValue="";
        strWeCellValue = we.getText();
        if(!isNullOrBlank("WeCellValue:"+strWeCellValue))
        {
            
        }
        else
        {
            strWeCellValue = "The text of the WebElement is not available";
        }
        return strWeCellValue;
    }
}
	
	

		
		
