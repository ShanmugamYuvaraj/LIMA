package com.lima.test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.lima.dto.Shipmentdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAShipmentBookingPOM;

public class AdjunoLIMAShipmentBookingTest {

	WebDriver driver;

	long nPage_Timeout = 0;
	String strTestURL;
	String strDriver;
	String strTrackN0;

	ArrayList<Shipmentdata> lstSearchResults;
	String strLIMAUserName;
	String strLIMAPassword;
	String strPageTitleShipmentBooking;
	String strSearchShipmentBookingForm;
	String strShipmentBookingForm;
	String strShipmentBookingFormRouting;
	String strShipmentBookingFormKPI;
	String strPOConfirmationPOStatusForm;
	String strShipmentBookingFormDialog;
	String strShipmentBookingFormReview;
	String strSearchVendorBookingForm;
	String strSearchShipAuthorityForm;
	boolean bSearchResultsProductsFound = true;
	
	String  strConfirmationStatusPendingShip;
	String  strConfirmationStatusPendingShip1;
	String  strConfirmationStatusPendingShip2;
	String  strInvalidPoNumber;
	String  strPoNumber1;
	String  strPoNumber2;
	String  strBookingstatus;
	String  strBookingstatus1;
	String  strBookingstatus2;
	String  strBookingstatus3;
	String  strVendorBooked;
	String  strStatus;
	String  strStatus1;
	String  strStatus2;
	String  strMode;
	String  strMode1;
	String  strBkdctns;
	String  strBkdcbm;
	String  strGrkgs;
	String  strChkgs;
	String  strPackType;
	String  strPackTypeedit;
	String  strFreightTerms;
	String  strFreightTermsedit;
	
	String  strCurrDate;
	String  strFuturDate;
	
	String  strCarrier;
	String  strCarrieredit;
    String  strCarrierType;
	String  strCarrierTypeedit;
	String  strContactNo;
	
	
	String  strVessel;
	String  strVoyage;
	String  strOriginPort;
	String  strDestinationPort;
	String  strLeg;
	String  strKPIReason;
	
	String  strSubject;
	String  strMessage1;
	String  strCopybyEmailto1;
	
	
	
	Shipmentdata product;
	Shipmentdata Shipmentdata;
	
	
	
	
	AdjunoLIMALibrary objAdjunoLIMALibrary; 
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMAShipmentBookingPOM objAdjunoLIMAShipmentBookingPOM;
	
	
	@BeforeTest
	public void setup() {
		objAdjunoLIMALibrary = new AdjunoLIMALibrary();

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());

			XPath xPath = XPathFactory.newInstance().newXPath();

			Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout",dDoc, XPathConstants.NODE);
			nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());
			
            Node testURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc,XPathConstants.NODE);
			strTestURL = testURL.getTextContent();
		
            Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName",	dDoc, XPathConstants.NODE);
			strLIMAUserName = limaUserName.getTextContent();
			
            Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password",dDoc, XPathConstants.NODE);
			strLIMAPassword = limaPassword.getTextContent();
			
            Node PageTitleShipmentBooking = (Node) xPath.evaluate("/config/LIMA/Page_Title_ShipmentBooking", dDoc, XPathConstants.NODE);
			strPageTitleShipmentBooking = PageTitleShipmentBooking.getTextContent();
			
            Node SearchShipmentBookingForm = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Shipment_BookingForm", dDoc,	XPathConstants.NODE);
			strSearchShipmentBookingForm = SearchShipmentBookingForm.getTextContent();
			
			Node ShipmentBookingForm = (Node) xPath.evaluate("/config/Generic/Shipment_BookingForm", dDoc,	XPathConstants.NODE);
     		strShipmentBookingForm = ShipmentBookingForm.getTextContent();
     		
     		Node ShipmentBookingFormRouting = (Node) xPath.evaluate("/config/Generic/Shipment_BookingForm_Routing", dDoc,	XPathConstants.NODE);
     		strShipmentBookingFormRouting = ShipmentBookingFormRouting.getTextContent();
     		

     		Node ShipmentBookingFormKPI = (Node) xPath.evaluate("/config/Generic/Shipment_BookingForm_KPI", dDoc,	XPathConstants.NODE);
     		strShipmentBookingFormKPI = ShipmentBookingFormKPI.getTextContent();
     		
     		Node POConfirmationPOStatusForm = (Node) xPath.evaluate("/config/Generic/PO_Confirmation_POStatusForm", dDoc,	XPathConstants.NODE);
     		strPOConfirmationPOStatusForm = POConfirmationPOStatusForm.getTextContent();
     		
     		Node ShipmentBookingFormDialog = (Node) xPath.evaluate("/config/Generic/Shipment_BookingForm_Dialog", dDoc,	XPathConstants.NODE);
     		strShipmentBookingFormDialog = ShipmentBookingFormDialog.getTextContent();
     		
     		Node ShipmentBookingFormReview = (Node) xPath.evaluate("/config/Generic/Shipment_BookingForm_Review", dDoc,	XPathConstants.NODE);
     		strShipmentBookingFormReview = ShipmentBookingFormReview.getTextContent();
     		
     		Node SearchVendorBookingForm = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_Vendor_BookingForm", dDoc,	XPathConstants.NODE);
     		strSearchVendorBookingForm = SearchVendorBookingForm.getTextContent();
     		
     		Node SearchShipAuthorityForm = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_Ship_AuthorityForm", dDoc,	XPathConstants.NODE);
     		strSearchShipAuthorityForm = SearchShipAuthorityForm.getTextContent();
			
			
			
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

			driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

			driver.get(strTestURL);

			objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);

			objAdjunoLIMALoginPOM.setUserName(strLIMAUserName);
			objAdjunoLIMALoginPOM.setPassword(strLIMAPassword);
			objAdjunoLIMALoginPOM.clickLogon();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		
		public void wait(int ms) {
			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = domFactory1.newDocumentBuilder();
				Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrLIMAShipmentBookingXMLDataFileName());

				XPath xPath1 = XPathFactory.newInstance().newXPath();

				Node InvalidPoNumber = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/InvalidPoNumber", dDoc1,XPathConstants.NODE);
				strInvalidPoNumber = InvalidPoNumber.getTextContent();
				
                Node ConfirmationStatusPendingShip = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/ConfirmationStatusPendingShip", dDoc1,XPathConstants.NODE);
				strConfirmationStatusPendingShip = ConfirmationStatusPendingShip.getTextContent();
				
				Node ConfirmationStatusPendingShip1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/ConfirmationStatusPendingShip1", dDoc1,XPathConstants.NODE);
				strConfirmationStatusPendingShip1 = ConfirmationStatusPendingShip1.getTextContent();
				
				Node ConfirmationStatusPendingShip2 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/ConfirmationStatusPendingShip2", dDoc1,XPathConstants.NODE);
				strConfirmationStatusPendingShip2 = ConfirmationStatusPendingShip2.getTextContent();
				
				Node PoNumber1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/PoNumber1", dDoc1,XPathConstants.NODE);
				strPoNumber1 = PoNumber1.getTextContent();
				
				Node PoNumber2 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/PoNumber2", dDoc1,XPathConstants.NODE);
				strPoNumber2 = PoNumber2.getTextContent();
				
				
				Node Mode = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Mode", dDoc1,XPathConstants.NODE);
				strMode = Mode.getTextContent();
				
				Node Mode1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Mode1", dDoc1,XPathConstants.NODE);
				strMode1 = Mode1.getTextContent();
				
				Node Bkdctns = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Bkdctns", dDoc1,XPathConstants.NODE);
				strBkdctns = Bkdctns.getTextContent();
				
				Node Bkdcbm = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Bkdcbm", dDoc1,XPathConstants.NODE);
				strBkdcbm = Bkdcbm.getTextContent();
				
				Node Grkgs = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Grkgs", dDoc1,XPathConstants.NODE);
				strGrkgs= Grkgs.getTextContent();
				
				Node Chkgs = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Chkgs", dDoc1,XPathConstants.NODE);
				strChkgs = Chkgs.getTextContent();
				
				Node PackType = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/PackType", dDoc1,XPathConstants.NODE);
				strPackType= PackType.getTextContent();
				
				Node PackTypeedit = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/PackTypeedit", dDoc1,XPathConstants.NODE);
				strPackTypeedit= PackTypeedit.getTextContent();
				
				Node FreightTerms = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/FreightTerms", dDoc1,XPathConstants.NODE);
				strFreightTerms= FreightTerms.getTextContent();
				
				Node FreightTermsedit = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/FreightTermsedit", dDoc1,XPathConstants.NODE);
				strFreightTermsedit= FreightTermsedit.getTextContent();
				
				Node Carrier = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Carrier", dDoc1,XPathConstants.NODE);
				strCarrier=  Carrier.getTextContent();
				
				Node Carrieredit = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Carrieredit", dDoc1,XPathConstants.NODE);
				strCarrieredit=  Carrieredit.getTextContent();
				
				Node CarrierType = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/CarrierType", dDoc1,XPathConstants.NODE);
				strCarrierType= CarrierType.getTextContent();
				
				Node CarrierTypeedit = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/CarrierTypeedit", dDoc1,XPathConstants.NODE);
				strCarrierTypeedit= CarrierTypeedit.getTextContent();
				
				Node Vessel = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Vessel", dDoc1,XPathConstants.NODE);
				strVessel= Vessel.getTextContent();
				
				Node  Voyage = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Voyage", dDoc1,XPathConstants.NODE);
				strVoyage= Voyage.getTextContent();
				
				Node DestinationPort = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/DestinationPort", dDoc1,XPathConstants.NODE);
				strDestinationPort= DestinationPort.getTextContent();
				
				Node OriginPort = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/OriginPort", dDoc1,XPathConstants.NODE);
				strOriginPort= OriginPort.getTextContent();
				
				Node  KPIReason = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/KPIReason", dDoc1,XPathConstants.NODE);
				strKPIReason= KPIReason.getTextContent();
				
				Node  Leg = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Leg", dDoc1,XPathConstants.NODE);
				strLeg= Leg.getTextContent();
				
				Node  ContactNo = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/ContactNo", dDoc1,XPathConstants.NODE);
				strContactNo=ContactNo.getTextContent();
				
				Node  Bookingstatus = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Bookingstatus", dDoc1,XPathConstants.NODE);
				strBookingstatus= Bookingstatus.getTextContent();
				

				Node  Bookingstatus1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Bookingstatus1", dDoc1,XPathConstants.NODE);
				strBookingstatus1= Bookingstatus1.getTextContent();
				

				Node  Bookingstatus2 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Bookingstatus2", dDoc1,XPathConstants.NODE);
				strBookingstatus2= Bookingstatus2.getTextContent();
				

				Node  Bookingstatus3 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Bookingstatus3", dDoc1,XPathConstants.NODE);
				strBookingstatus3= Bookingstatus3.getTextContent();
				
				Node  VendorBooked = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/VendorBooked", dDoc1,XPathConstants.NODE);
				strVendorBooked=VendorBooked.getTextContent();
				
				Node  Status = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Status", dDoc1,XPathConstants.NODE);
				strStatus=Status.getTextContent();
				
				Node  Status1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Status1", dDoc1,XPathConstants.NODE);
				strStatus1=Status1.getTextContent();
				
				Node  Status2 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Status2", dDoc1,XPathConstants.NODE);
				strStatus2=Status2.getTextContent();
				
				Node Subject = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Subject", dDoc1,XPathConstants.NODE);
				strSubject=Subject.getTextContent();
				
				Node Message1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/Message1", dDoc1,XPathConstants.NODE);
				strMessage1=Message1.getTextContent();
				
				Node CopybyEmailto1 = (Node) xPath1.evaluate("/ShipmentBooking/SearchParams/CopybyEmailto1", dDoc1,XPathConstants.NODE);
				strCopybyEmailto1=CopybyEmailto1.getTextContent();
				
				
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		

		private boolean isNullOrBlank(String s) {
			return (s == null || s.trim().equals(""));
		}

	@Test(priority = 1)
	public void Test_0_accessshipmentBooking() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		String strTitle = objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTools(),objAdjunoLIMAShipmentBookingPOM.getLnkShipmentBooking());

		boolean bFlag = true;

		if (isNullOrBlank(strTitle))
			bFlag = false;

		if (!(isNullOrBlank(strTitle))) {
			if (strTitle.equalsIgnoreCase(strPageTitleShipmentBooking))
				bFlag = true;
			else
				bFlag = false;
		}

		objSoftAssert.assertEquals(bFlag, true, "*****test ID:_0_Title of the page is wrong for Shipment Booking*****");
		try{
	        wait(3000);
	        objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnOK1());
	        wait(3000);
	        }
	        catch(NoSuchElementException e)
	        {
	        	
	        }
		objSoftAssert.assertAll();
	}
		@Test(priority = 10)
       public void Test_2_checkForChevorons() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvSearch()), true, "*****test ID:_2_Search Chevorn is not displayed in Shipment Booking*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvSelect()), true, "*****test ID:_2_Select Chevorn is not displayed in Shipment Booking*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvBooking()), true,"*****test ID:_2_Booking Chevorn is not displayed in Shipment Booking*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvRouting()), true, "*****test ID:_2_Routing Chevorn is not displayed in Shipment Booking*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvKPI()), true, "*****test ID:_2_KPI Chevorn is not displayed in Shipment Booking*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvComplete()), true, "*****test ID:_2_Complete Chevorn is not displayed in Shipment Booking*****");

		objSoftAssert.assertAll();
	}
		
		@Test(priority = 16)
		public void Test_3_checkForShipmentBookingfields() {

			SoftAssert objSoftAssert = new SoftAssert();
			objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
			veifyShipmentBookingtxtfields();
			objSoftAssert.assertAll();
		}
            
			public void veifyShipmentBookingtxtfields(){
			SoftAssert objSoftAssert = new SoftAssert();
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_OriginPort"), true,"*****test ID:_3_OriginPort field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_Mode"), true,"*****test ID:_3_Mode field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_Vessel"), true,"*****test ID:_3_Vessel field is not found for Shipment Booking*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_ETDFrom"), true,"*****test ID:_3_ETDForm field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_SupplierName"), true,"*****test ID:_3_Vendor field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_Status"),true, "*****test ID:_3_Status field is not found for Shipment Booking*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_Product"),true, "*****test ID:_3_Product field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_Category"), true,"*****test ID:_3_Category Status field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_CustomerReference"), true,"*****test ID:_3_VendorRef field is not found for Shipment Booking*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_Range"),true, "*****test ID:_3_Range field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_SupplierReference"),true, "*****test ID:_3_SupplierRefrence field is not found for Shipment Booking*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_VendorBooked"), true,"*****test ID:_3_VendorBooking Status field is not found for Shipment Booking*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesFieldsExist(strSearchShipmentBookingForm, "PARAM_OrderNumber"), true,"*****test ID:_3_PO Number field is not found for Shipment Booking*****");
			objSoftAssert.assertAll();
		}
		
	@Test(priority = 22)
	public void Test_9_verifyIncorrectmsg() {
		SoftAssert objSoftAssert = new SoftAssert();
         String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OriginPort"), true, " *****test ID:_9_OriginPort field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, " *****test ID:_9_Mpode field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Vessel"),true, " *****test ID:_9_Vessel field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_ETDFrom"), true, " *****test ID:_9_ETDForm field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierName"),true, " *****test ID:_9_Supplier Name field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_9_Status field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Product"), true, " *****test ID:_9_Product field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Category"),true, " *****test ID:_9_Category field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_CustomerReference"),true, " *****test ID:_9_Customer Reference field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Range"), true, " *****test ID:_9_Range field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierReference"),true, " *****test ID:_9_Supplier Reference field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_VendorBooked"),true, " *****test ID:_9_Vendor Booked field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, " *****test ID:_9_Order Number field is not cleared in Shipment Booking Search Chevron*****");
		wait(2000);
		if (!isNullOrBlank(strInvalidPoNumber)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strInvalidPoNumber,strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, "*****test ID:_9_Invalid PO Number is not set in PO Number field under Shipment Booking Search Chevron*****");
		}
		
		else{
			strMessage = strMessage + strInvalidPoNumber + "null";	    		
    	}
        if (!isNullOrBlank(strConfirmationStatusPendingShip)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip,strSearchShipmentBookingForm, "PARAM_Status"),true, "*****test ID:_9_Status is not set to Awaiting in Status field under Shipment Booking Search page *****");
		}
		
       else{
			strMessage = strMessage + strConfirmationStatusPendingShip + "null";	    		
    	}

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true, "*****test ID:_9_Select chevron is not clicked under Shipment Booking Search page*****");

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getContentmsg()), true,"*****test ID:_9_\"No items were found.\" message is not displayed when Invalid PO Number is set in PO Number field under Search page");
		objSoftAssert.assertAll();
	}
		
	@Test(priority = 25)
	public void Test_10_verifyPODetails() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSearch1()),true, "*****test ID:_10_Search chevron is not clicked under Shipment Booking*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OriginPort"), true, " *****test ID:_10_OriginPort field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, " *****test ID:_10_Mpode field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Vessel"),true, " *****test ID:_10_Vessel field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_ETDFrom"), true, " *****test ID:_10_ETDForm field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierName"),true, " *****test ID:_10_Supplier Name field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_10_Status field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Product"), true, " *****test ID:_10_Product field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Category"),true, " *****test ID:_10_Category field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_CustomerReference"),true, " *****test ID:_10_Customer Reference field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Range"), true, " *****test ID:_10_Range field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierReference"),true, " *****test ID:_10_Supplier Reference field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_VendorBooked"),true, " *****test ID:_10_Vendor Booked field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, " *****test ID:_10_Order Number field is not cleared in Shipment Booking Search Chevron*****");
		wait(2000);
		
		if (!isNullOrBlank(strMode1)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strMode1,strSearchShipmentBookingForm, "PARAM_Mode"),true, "*****test ID:_10_Mode is not set to Sea in Mode field under Shipment Booking Search page *****");
		
		}
		else{
			strMessage = strMessage + strMode1 + "null";	    		
    	}
		if (!isNullOrBlank(strPoNumber1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1, strSearchShipmentBookingForm,"PARAM_OrderNumber"), true,"*****test ID:_10_PO Number is not set in PO Number field under Shipment Booking Search Chevron*****");
		}
		 else{
				strMessage = strMessage + strPoNumber1 + "null";	    		
	    	}

		if (!isNullOrBlank(strConfirmationStatusPendingShip)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip,strSearchShipmentBookingForm, "PARAM_Status"),true, "*****test ID:_10_Status is not set to Awaiting in Status field under Shipment Booking Search page*****");
		}
		else{
			strMessage = strMessage + strConfirmationStatusPendingShip + "null";	    		
    	}

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true,"*****test ID:_10_Select chevron is not clicked under Shipment Booking Search page*****");
		verifyProductGridHeader();

		objSoftAssert.assertAll();
	}

	@Test(priority = 28)
	public void Test_11_compareGridData() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		String strMessage="";

		List<Shipmentdata> list = new ArrayList<Shipmentdata>();
        list = objAdjunoLIMAShipmentBookingPOM.getStaus();

		strMessage= objAdjunoLIMAShipmentBookingPOM.compareshipmentgriddata(strPoNumber1,strBookingstatus, strVendorBooked, list);
		objSoftAssert.assertEquals(strMessage.equals(""), true," ****test ID:_11_Compare grid Caption: "+strMessage+"*****");

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 34)
	public void Test_13_verifyGridHeader() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
        verifyProductGridHeader();

		objSoftAssert.assertAll();
	}


		public void verifyProductGridHeader() {
			SoftAssert objSoftAssert = new SoftAssert();
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThOriginPort()), true, "test ID:_13_Origin Port is not displayed in the resultant grid under Select page");
            objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThMode()), true, "test ID:_13_Mode is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThFeederVessel()), true, "test ID:_13_Feeder Vessel is not displayed in the resultant grid under Select page");

			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThFeederVoyage()), true, "test ID:_13_Feeder Voyage is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThFeederETD()), true, "test ID:_13_Feeder ETD is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThFeederETA()), true, "test ID:_13_Feeder ETA is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThDestinationPort()), true, "test ID:_13_Destination Port is not displayed in the resultant grid under Select page");
            objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThCarrier()), true, "test ID:_13_Carrier is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThMotherVessel()), true, "test ID:_13_Mother Vessel is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThMotherVoyage()), true, "test ID:_13_Mother Voyage is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThMotherETD()), true,"test ID:_13_Mother ETD is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThMotherETA()), true, "test ID:_13_Mother ETA is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThVendor()), true, "test ID:_13_Vendor is not displayed in the resultant grid under Select page");
			
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThLoading()), true,"test ID:_13_Loading is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThPONumber()), true, "test ID:_13_PO Number is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThProduct()), true, "test ID:_13_Product is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThIdentifier()), true, "test ID:_13_Identifier is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThPriority()), true, "test ID:_13_Priority is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThVendorRef()), true, "test ID:_13_Vendor Ref is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThSupplierRef() ), true,"test ID:_13_Supplier Ref is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThPOQty()), true, "test ID:_13_PO Qty is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThBkdQty()), true, "test ID:_13_Bkd Qty is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThOpenQty()), true, "test ID:_13_Open Qty is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThCtns()), true, "test ID:_13_Ctns is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThCbm()), true, "test ID:_13_Cbm is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThPackType()), true, "test ID:_13_Pack Type is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThRange() ), true,"test ID:_13_Range is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThDC()), true, "test ID:_13_DC is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThShipDate()), true, "test ID:_13_ShipDate is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThLatestShipDate()), true, "test ID:_13_Latest ShipDate is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThFreightTerms()), true, "test ID:_13_Freight Terms is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThBookingStatus()), true, "test ID:_13_Booking Status is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThAuthorityStatus()), true, "test ID:_13_Authority Status is not displayed in the resultant grid under Select page");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThVendorBooked()), true, "test ID:_13_Vendor Booked is not displayed in the resultant grid under Select page");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThDialog()), true,"test ID:_13_Dialog is not displayed in the resultant grid under Select page");
			
            objSoftAssert.assertAll();
		}
		
	@Test(priority = 36)
	public void Test_16_17_verifyShipmentDetailsData() {

		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		Shipmentdata = objAdjunoLIMAShipmentBookingPOM.getShipmentData();

		product = objAdjunoLIMAShipmentBookingPOM.getVendorBokkingData();

		objSoftAssert.assertEquals(product.getOriginPort().equalsIgnoreCase(Shipmentdata.getOriginPort()), true,"*****test ID:_16_17_Origin Port data does not match with the Vendor Booking Origin Port data*****");
		System.out.println("Product PO value:" + product.getOriginPort()+ "---" + "shipment po number:" + Shipmentdata.getOriginPort());
		objSoftAssert.assertEquals(product.getMode().equalsIgnoreCase(Shipmentdata.getMode()),true, "*****test ID:_16_17_Mode data does not match with the Vendor Booking Mode data*****");
		objSoftAssert.assertEquals(product.getFeederVessel().equalsIgnoreCase(Shipmentdata.getFeederVessel()), true," *****test ID:_16_17_Feeder Vessel data does not match with the Vendor Booking Feeder Vessel data*****");
		objSoftAssert.assertEquals(product.getFeederVoyage().equalsIgnoreCase(Shipmentdata.getFeederVoyage()), true,"*****test ID:_16_17_Feeder Voyage data does not match with the Vendor Booking Feeder Voyage data*****");
		objSoftAssert.assertEquals(product.getFeederETD().equalsIgnoreCase(Shipmentdata.getFeederETD()), true,"*****test ID:_16_17_Feeder ETD data does not match with the Vendor Booking Feeder ETD data*****");
		objSoftAssert.assertEquals(product.getFeederETA().equalsIgnoreCase(Shipmentdata.getFeederETA()), true,"*****test ID:_16_17_Feeder ETA data does not match with the Vendor Booking Feeder ETA data*****");
		objSoftAssert.assertEquals(product.getDestinationPort().equalsIgnoreCase(Shipmentdata.getDestinationPort()), true,"*****test ID:_16_17_Destination Port data does not match with the Vendor Booking DestinationPort data*****");
		objSoftAssert.assertEquals(product.getCarrier().equalsIgnoreCase(Shipmentdata.getCarrier()), true,"*****test ID:_16_17_Carrier data does not match with the Vendor Booking Carrier data*****");
		objSoftAssert.assertEquals(product.getMotherVessel().equalsIgnoreCase(Shipmentdata.getMotherVessel()), true,"*****test ID:_16_17_Mother Vessel data does not match with the Vendor Booking Mother Vessel data*****");
		objSoftAssert.assertEquals(product.getMotherVoyage().equalsIgnoreCase(Shipmentdata.getMotherVoyage()), true,"*****test ID:_16_17_Mother Voyage data does not match with the Vendor Booking Mother Voyage data*****");
		objSoftAssert.assertEquals(product.getMotherETD().equalsIgnoreCase(Shipmentdata.getMotherETD()), true,"*****test ID:_16_17_Mother ETD data does not match with the Vendor Booking Mother ETD data*****");
		objSoftAssert.assertEquals(product.getMotherETA().equalsIgnoreCase(Shipmentdata.getMotherETA()), true,"*****test ID:_16_17_Mother ETA data does not match with the Vendor Booking Mother ETA data*****");

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 39)
	public void Test_19_verifyinvalidcheckbox() {

		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSearch1()),true, "*****_19_Search chevron is not clicked under Shipment Booking*****");
		
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OriginPort"), true, " *****test ID:_19_OriginPort field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, " *****test ID:_19_Mpode field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Vessel"),true, " *****test ID:_19_Vessel field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_ETDFrom"), true, " *****test ID:_19_ETDForm field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierName"),true, " *****test ID:_19_Supplier Name field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_19_Status field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Product"), true, " *****test ID:_19_Product field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Category"),true, " *****test ID:_19_Category field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_CustomerReference"),true, " *****test ID:_19_Customer Reference field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Range"), true, " *****test ID:_19_Range field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierReference"),true, " *****test ID:_19_Supplier Reference field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_VendorBooked"),true, " *****test ID:_19_Vendor Booked field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, " *****test ID:_19_Order Number field is not cleared in Shipment Booking Search Chevron*****");
		if (!isNullOrBlank(strConfirmationStatusPendingShip)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip,strSearchShipmentBookingForm, "PARAM_Status"),true, "*****test ID:_19_Status is not set to Awaiting in Status field under Shipment Booking Search page*****");
		}
		else{
			strMessage = strMessage + strConfirmationStatusPendingShip + "null";	    		
    	}
		
		if (!isNullOrBlank(strPoNumber1)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1,strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, "*****test ID:_19_PO Number is not set in PO Number field under Shipment Booking Search Chevron*****");
		}
		else{
			strMessage = strMessage + strPoNumber1 + "null";	    		
    	}

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true, "*****test ID:_19_Select chevron is not clicked under Shipment Booking Search page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvBooking1()),true, "*****test ID:_19_Booking chevron is not clicked under Shipment Booking*****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getProcessedcheckboxMsg()),true, "*****test ID:_19_ message is not displayed when checkbox is not selected under Select page*****");
		objSoftAssert.assertAll();
		}


	    @Test(priority = 49)
		public void Test_20_verifyBookingCheveron() {
			SoftAssert objSoftAssert = new SoftAssert();
			String strMessage="";

			objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSearch1()),true, "*****_20_Search chevron is not clicked under Shipment Booking*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OriginPort"), true, " *****test ID:_20_OriginPort field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, " *****test ID:_20_Mpode field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Vessel"),true, " *****test ID:_20_Vessel field is not cleared in Shipment Booking Search Chevron*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_ETDFrom"), true, " *****test ID:_20_ETDForm field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierName"),true, " *****test ID:_20_Supplier Name field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_20_Status field is not cleared in Shipment Booking Search Chevron*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Product"), true, " *****test ID:_20_Product field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Category"),true, " *****test ID:_20_Category field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_CustomerReference"),true, " *****test ID:_20_Customer Reference field is not cleared in Shipment Booking Search Chevron*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_Range"), true, " *****test ID:_20_Range field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_SupplierReference"),true, " *****test ID:_20_Supplier Reference field is not cleared in Shipment Booking Search Chevron*****");
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_VendorBooked"),true, " *****test ID:_20_Vendor Booked field is not cleared in Shipment Booking Search Chevron*****");
			
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, " *****test ID:_20_Order Number field is not cleared in Shipment Booking Search Chevron*****");

			if (!isNullOrBlank(strConfirmationStatusPendingShip)) 
			{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip,strSearchShipmentBookingForm, "PARAM_Status"),true, "*****test ID:_20_Status is not set in Status field under Shipment Booking Search page*****");
			}
			else{
				strMessage = strMessage + strConfirmationStatusPendingShip + "null";	    		
	    	}
			
			if (!isNullOrBlank(strPoNumber1)) 
			{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1,strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, "*****test ID:_20_PO Number is not set in PO Number field under Shipment Booking Search Chevron*****");
			}
			else{
				strMessage = strMessage + strPoNumber1 + "null";	    		
	    	}

			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true, "*****test ID:_20_Select chevron is not clicked under Shipment Booking Search page*****");
			lstSearchResults = new ArrayList<Shipmentdata>();
			if (objAdjunoLIMAShipmentBookingPOM.getNoOrRowsResulted() > 0) {
				lstSearchResults = objAdjunoLIMAShipmentBookingPOM.selectMulitpleProducts(2);

				objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
				objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvBooking1()), true,"*****test ID:_20_Booking chevron is not clicked under Shipment Booking*****");

			} else {
				bSearchResultsProductsFound = false;
				objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"*****test ID:_20_No Products in Search Results.*****");
			}
             objSoftAssert.assertAll();
            }

		
	@Test(priority = 55)
	public void Test_21_verifyBookingDetails() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		verifyBookinggridDetails();
		objSoftAssert.assertAll();

	}
		
		public void verifyBookinggridDetails(){
		SoftAssert objSoftAssert = new SoftAssert();
        
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingVendor()), true, "*****test ID:_21_Vendor is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingPONumber()), true,"*****test ID:_21_Po Number is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingProduct()), true, "*****test ID:_21_Product is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingVendorRef()), true,"*****test ID:_21_Vendor Ref is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingIdentifier()), true,"*****test ID:_21_Identifier is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingDC()), true, "*****test ID:_21_DC is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingQC()), true, "*****test ID:_21_QC is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingPOQty()), true, "*****test ID:_21_PO Qty is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingOpenQty()), true, "*****test ID:_21_Open Qty is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingBkdQty()), true, "*****test ID:_21_Bkd Qty is not displayed in the resultant grid under Booking page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingBkdCtns()), true, "test ID:_21_Bkd Ctns is not displayed in the resultant grid under Booking page");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingBkdCbm()), true, "test ID:_21_Bkd Cbm is not displayed in the resultant grid under Booking page");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingGrKgs()), true, "test ID:_21_Gr Kgs is not displayed in the resultant grid under Booking page");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingChKgs()), true, "test ID:_21_Ch Kgs is not displayed in the resultant grid under Booking page");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getThbookingPackTypes()), true,"test ID:_21_Pack Types is not displayed in the resultant grid under Booking page");
        

		objSoftAssert.assertAll();

	}

	@Test(priority = 58)
	public void Test_22_clickPOHyperlink() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		String parentHandle = driver.getWindowHandle();
		System.out.println(parentHandle);
       objAdjunoLIMAShipmentBookingPOM.getClickhyperlink().click();

		for (String winHandle : driver.getWindowHandles()) {
			System.out.println(winHandle);
			driver.switchTo().window(winHandle);
		}

		driver.close();
		driver.switchTo().window(parentHandle);

		objSoftAssert.assertAll();

	}
	

	@Test(priority = 59)
	public void Test_23_26_clickBookingProduct() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		String strMessage ="";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objAdjunoLIMAShipmentBookingPOM.getClickProducthyperlink().click();

		wait(3000);

		if (!isNullOrBlank(strBkdctns))
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm,"SKUDetail", 0, "SKUBookedCartons", strBkdctns), true,"*****test ID:_23_26_Bkd ctns is not set in Bkd ctns field under Shipment Booking*****");
		
		}
		else{
			strMessage = strMessage + strBkdctns + "null";	    		
    	}
		
		
		if (!isNullOrBlank(strBkdcbm))
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm,"SKUDetail", 0, "SKUBookedCBM", strBkdcbm), true,"*****test ID:_23_26_Unable to set Value in Bkdcbm*****");
		}
		
		else{
			strMessage = strMessage + strBkdcbm + "null";	    		
    	}

		if (!isNullOrBlank(strGrkgs)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm,"SKUDetail", 0, "SKUGrossWeight", strGrkgs), true,"*****test ID:_23_26_Unable to set Value in Grkgs*****");
		}
		else{
			strMessage = strMessage + strGrkgs + "null";	    		
    	}

		if (!isNullOrBlank(strChkgs)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm,"SKUDetail", 0, "SKUChargeableWeight", strChkgs), true,"*****test ID:_23_26_Unable to set Value in Chkgs*****");
		}
		else{
			strMessage = strMessage + strChkgs + "null";	    		
    	}
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnUpdateSKU()), true, "*****test ID:_23_26_Update Button not clicked*****");

		driver.switchTo().alert().accept();
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 60)
	public void Test_27_29_verifyBookingValue() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		
		

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		
		strCurrDate=objAdjunoLIMAShipmentBookingPOM.getDate(0, "dd-MM-YYYY");

		wait(3000);
		if (!isNullOrBlank(strPackType)){
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm, "SKUs", 0,"PackType", strPackType), true, "*****test ID:_27_29_Not able to set value for PackType*****");
		}
		else{
			strMessage = strMessage + strPackType + "null";	    		
		}
		if (!isNullOrBlank(strFreightTerms)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm, "SKUs", 0,"FreightTerms", strFreightTerms), true,"*****test ID:_27_29_Not able to set value for Freight Terms*****");
		}
		else{
			strMessage = strMessage + strFreightTerms + "null";	    		
		}
		if (!isNullOrBlank(strCurrDate)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm, "SKUs", 0,"DueInCFS", strCurrDate), true, "*****test ID:_27_29_Not able to set value for Due in CFS*****");
		}
		else{
			strMessage = strMessage + strCurrDate + "null";	    		
		}
		objSoftAssert.assertAll();

	}

	@Test(priority = 63)
	public void Test_30_clickRoutingTab() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvRouting1()), true,"*****test ID:_30_Not able to click to Routing chevron*****");

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhShipment()), true, "*****test ID:_30_Grid Shipment is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhVoyageLegs()), true, "*****test ID:_30_Grid Voyage is not visible*****");

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 66)
	public void Test_31_checkForShipmentSectionField() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
	    verifyShipmentSectiongrdField();
		objSoftAssert.assertAll();
	}
        
		
		public void verifyShipmentSectiongrdField() {

		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhMode()),true, "*****test ID:_31_Grid Mode is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhLoadType()), true, "*****test ID:_31_Grid Load Type is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhLoadRefrence()), true, "*****test ID:_31_Grid Load Refrence is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhCarrier()), true, "*****test ID:_31_Grid Carrier is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhClosingDate()), true, "*****test ID:_31_Grid Closing Date is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhCarrierType()), true, "*****test ID:_31_Grid Carrier Type is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhContactNo()), true, "*****test ID:_31_Grid Contact No is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhColoader()), true, "*****test ID:_31_Grid Coloader is not visible*****");

		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority = 76)
	public void Test_33_checkForVoyageLegsSectionField() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		voyageLegsSectionField();

		objSoftAssert.assertAll();

	}
	
        public void voyageLegsSectionField(){
        	
        SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhleg()),true, "*****test ID:_33_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhVessel()), true, "*****test ID:_33_Grid Vessel is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhVoyage()), true, "*****test ID:_33_Grid Voyage is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhOriginPort()), true, "*****test ID:_33_Grid Origin Port is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhDestinationPort()), true, "*****test ID:_33_Grid Destination Port is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhETD()), true, "*****test ID:_33_Grid ETD is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhETA()), true, "*****test ID:_33_Grid ETA is not visible*****");
		

		objSoftAssert.assertAll();
	}
        
	@Test(priority = 78)
	public void setVoyageField() {

		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		strCurrDate=objAdjunoLIMAShipmentBookingPOM.getDate(0, "dd-MM-YYYY");
		strFuturDate=objAdjunoLIMAShipmentBookingPOM.getDate(7, "dd-MM-YYYY");
		
		
		if (!isNullOrBlank(strVessel)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Legs", 0, "Vessel", strVessel), true,"*****test ID:Not able to set value for Vessel*****");
		}
		else{
			strMessage = strMessage + strVessel + "null";	    		
		}
		wait(2000);
		if (!isNullOrBlank(strVoyage)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Legs", 0, "Voyage", strVoyage), true,"*****test ID:Not able to set value for Voyage*****");
		}
		else{
			strMessage = strMessage + strVoyage + "null";	    		
		}
		if (!isNullOrBlank(strOriginPort)){
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Legs", 0, "OriginPort", strOriginPort),true, "*****test ID:Not able to set value for Origin Port*****");
			}
			else{
				strMessage = strMessage + strOriginPort + "null";	    		
			}
		if (!isNullOrBlank(strDestinationPort)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Legs", 0, "DestinationPort", strDestinationPort),true, "*****test ID:Not able to set value for Destination Port*****");
		}
		else{
			strMessage = strMessage + strDestinationPort + "null";	    		
		}
		if (!isNullOrBlank(strFuturDate)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Legs", 0, "ETA", strFuturDate), true, "*****test ID:Not able to set value for Futur Date*****");
		}
		else{
			strMessage = strMessage + strFuturDate + "null";	    		
		}
		if (!isNullOrBlank(strCurrDate)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Legs", 0, "ETD", strCurrDate), true, "*****test ID:Not able to set value for Current Date*****");
		}
		else{
			strMessage = strMessage + strCurrDate + "null";	    		
		}

		objSoftAssert.assertAll();

	}

	@Test(priority = 79)
	public void Test_34_clickaddLegButton() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		long nRow = objAdjunoLIMAShipmentBookingPOM.getNoOfGridRows(strShipmentBookingFormRouting, "Legs");

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnAddLeg()), true,"*****test ID:34_Add Leg Button is not clicked in \"Voyage Legs Grid \" under Routing page*****");
		wait(3000);
		long nAddRows = objAdjunoLIMAShipmentBookingPOM.getNoOfGridRows(strShipmentBookingFormRouting, "Legs");

		objSoftAssert.assertEquals(nRow < nAddRows, true,"*****test ID:34_Add leg button has issues*****");
		objSoftAssert.assertAll();
 
    	}
   		
    		
    	
    	@Test(priority = 80)
    	public void Test_35_deleteLegButton() {

    		SoftAssert objSoftAssert = new SoftAssert();
    		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
    		   long nRows;
    	       long nRows1;
    	       long nRows3;
    	       nRows = objAdjunoLIMAShipmentBookingPOM.getNoOfGridRows(strShipmentBookingFormRouting, "Legs");
    	       objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnDeleteLeg()), true,"*****test ID:_35_Delete Leg Button is not clicked in \"Voyage Legs Grid \" under Routing page*****");
    	       objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getTxtDeleteLeg()), true,"*****test ID:_35_Delete leg confirmation pop up is not dispalyed*****");
    	       objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnNO()), true,"*****test ID:_35_No button is not clicked in Delete leg confirmation pop up*****");
    	       nRows1 = objAdjunoLIMAShipmentBookingPOM.getNoOfGridRows(strShipmentBookingFormRouting, "Legs");
    	   
    	       //verifying No button function
    	       objSoftAssert.assertEquals(nRows==nRows1, true,"*****test ID:35_No button in delete leg confirmation pop up has issues*****");
    	       
    	       objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnDeleteLeg()), true,"*****test ID:_35_Delete leg button is not clicked*****");
    	       objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getTxtDeleteLeg()), true,"*****test ID:_35_Delete leg confirmation pop up is not dispalyed*****");
    	       objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnYes()), true,"*****test ID:_35_Yes button is not clicked in Delete leg confirmation pop up*****");
    	       nRows3 = objAdjunoLIMAShipmentBookingPOM.getNoOfGridRows(strShipmentBookingFormRouting, "Legs");
    	       
    	       //verifying Yes button function
    	       objSoftAssert.assertEquals(nRows>nRows3, true,"*****test ID:_35_Yes button in delete leg confirmation pop up has issues*****");
    		   objSoftAssert.assertAll();
    		
    		
   	}

	@Test(priority = 82)
	public void Test_32_setValueForShipmentSectionField() {

		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		strCurrDate=objAdjunoLIMAShipmentBookingPOM.getDate(0, "dd-MM-YYYY");
        
		if (!isNullOrBlank(strCarrier)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Header", 0, "Carrier", strCarrier), true,"*****test ID:32_Not able to set value for Carrier*****");
		}
		else{
			strMessage = strMessage + strCarrier + "null";	    		
		}
		if (!isNullOrBlank(strCurrDate)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Header", 0, "ClosingDate", strCurrDate), true,"*****test ID:32_Not able to set value for closing date*****");
        
		}
		else{
			strMessage = strMessage + strCurrDate + "null";	    		
		}
		if (!isNullOrBlank(strCarrierType)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Header", 0, "NVOType", strCarrierType), true,"*****test ID:32_Not able to set value for Carrier Type*****");
		}
		else{
			strMessage = strMessage + strCarrierType + "null";	    		
		}
		
		objSoftAssert.assertAll();
	}
	
	
	

	@Test(priority = 85)
	public void clickKPICheveron() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvKPI1()),true, "*****test ID:Not able to click KPI chevron*****");

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 89)
	public void Test_36_verifyKPIdetails() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
	    verifyKPIgrddetails();
		objSoftAssert.assertAll();

	}
		
		
		public void verifyKPIgrddetails() {
        SoftAssert objSoftAssert = new SoftAssert();
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIMode()), true, "*****test ID:_36_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPILoadType()), true,"*****test ID:_36_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPILoadRefrence()), true, "*****test ID:_36_Grid leg is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPICarrier()), true,"*****test ID:_36_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIClosingDate()), true,"*****test ID:_36_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPICarrierType()), true, "*****test ID:_36_Grid leg is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIContactNo()), true, "*****test ID:_36_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIColoader()), true, "*****test ID:_36_Grid leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIFreightTerms()), true, "*****test ID:_36_Grid leg is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIleg()), true, "*****test ID:_36_Grid KPI leg is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIVessel()), true, "*****test ID:_36_Grid KPI Vessel is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIVoyage()), true, "*****test ID:_36_Grid KPI Voyage is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIOriginPort()), true, "*****test ID:_36_Grid KPI Origin Port is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIDestinationPort()), true, "*****test ID:_36_Grid KPI Destination Port is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIETD()), true,"*****test ID:_36_Grid KPI ETD is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIETA()), true, "*****test ID:_36_Grid KPI ETA is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIVendor()), true, "*****test ID:_36_Grid KPI Vendor is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIPONumber()), true, "*****test ID:_36_Grid KPI PO Number is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIProduct()), true, "*****test ID:_36_Grid KPI Product is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIbookingPOQty()), true, "*****test ID:_36_Grid KPI booking POQty is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIOpenQty()), true, "*****test ID:_36_Grid KPI Open Qty is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIBkdQty()), true,"*****test ID:_36_Grid KPI BkdQty is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIQuantityVariance()), true, "*****test ID:_36_Grid KPI Quantity Variance is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIPercVariance()), true,"*****test ID:_36_Grid KPI PercVariance is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIBkdCtns()), true, "*****test ID:_36_Grid KPI BkdCtns is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIBkdCbm()), true, "*****test ID:_36_Grid KPI BkdCbm is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIGrKgs()), true, "*****test ID:_36_Grid KPI GrKgs is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIChKgs()), true, "*****test ID:_36_Grid KPI ChKgs is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIPackTypes()), true, "*****test ID:_36_Grid KPI PackTypes is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIDC()), true, "*****test ID:_36_Grid KPI DC is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIQC()), true,"*****test ID:_36_Grid KPI QC is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIPOShipDate()), true, "*****test ID:_36_Grid KPI PO ShipDate is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIDaysVariance()), true, "*****test ID:_36_Grid KPI Days Variance is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhKPIReason()), true, "*****test ID:_36_Grid KPI Reason is not visible*****");
		
		
		objSoftAssert.assertAll();

		}
	
	@Test(priority = 91)
	public void Test_37_verifyMandatoryFieldComment() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		
		String strErrorMessage = objAdjunoLIMAShipmentBookingPOM.getValidationMessageGridElement(strShipmentBookingFormKPI,"SKUs", 0, "KPIReason");
		if (!strErrorMessage.equals("")) {
			objSoftAssert.assertEquals(strErrorMessage.equalsIgnoreCase("> KPI Reason is a required field"),true,"*****test ID:_37_'> KPI Reason is a required field' message is not displayed*****");
		} else {
			strMessage = strMessage + "validation message is null";
		}
		objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);

		objSoftAssert.assertAll();
	}

	@Test(priority = 93)
	public void Test_38_setKPIReasonField() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		if (!isNullOrBlank(strKPIReason)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormKPI,"SKUs", 0, "KPIReason", strKPIReason), true,"*****test ID:_38_Not able to set value for KPI Reason*****");
		} else {
			strMessage = strMessage + strKPIReason + "null";
		}

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvComplete1()), true,"*****test ID:_38_Not able to click Complete1 chevron*****");
		wait(5000);
		objSoftAssert.assertAll();
	}
	
	
    @Test(priority = 96)
	public void Test_39_verifyViewDetails() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
        wait(2000);
		objAdjunoLIMAShipmentBookingPOM.getBtnViewDetail().click();
		wait(3000);
		String poDetails = objAdjunoLIMAShipmentBookingPOM.getTxtViewDetail().getText();
		System.out.println("^^^^^^^^^^ 1 :" + poDetails);
		String[] vals = poDetails.split("Track");
		System.out.println("^^^^^^^^^^ 2 :" + vals[0]);
		System.out.println("^^^^^^^^^^ 2^^^^^^^^^ :" + vals[1]);
		String valss[] = vals[1].split("'", 13);
		System.out.println("^^^^^^^^^^ 3 :" + valss[0]);
		System.out.println("^^^^^^^^^^ 3 ^^^^^^^^^^^:" + valss[1]);
		strTrackN0 = valss[1];
		System.out.println(strTrackN0);
		wait(2000);
		

		objSoftAssert.assertAll();

	}
	

	@Test(priority = 98)
	public void Test_40_accessEdit() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage="";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTrack(),objAdjunoLIMAShipmentBookingPOM.getLnkEdit());

		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getFldRefNo()), true,"*****test ID:_40_Reference field is not found in \"Edit or Create track page\"*****");

		if (!isNullOrBlank(strTrackN0)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForWebElement(objAdjunoLIMAShipmentBookingPOM.getFldRefNo(),strTrackN0), true,"*****test ID:_40_Not able to set value for Refrence Field*****");
		}

		objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnApply());
		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getTitleShipment()), true, "*****test ID:_40_Shipment track page is not loaded*****");
		
		long min = objAdjunoLIMAShipmentBookingPOM.getTrackValue(strLIMAUserName);
	       
		if(min<=275){
            strMessage = "";
        }else{
            strMessage = "Not a todays date";
        }
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);

		objSoftAssert.assertAll();

	}
	
		
	
	@Test(priority = 100)
	public void Test_42_verifyPOStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		wait(2000);
		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkFind(),objAdjunoLIMAShipmentBookingPOM.getLnkPOStatus());

		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_16PO"), true,"*****test ID:_42_OriginPort field is not cleared in Shipment Booking Search Chevron*****");
		

		if (!isNullOrBlank(strPoNumber1)) {

			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1,strPOConfirmationPOStatusForm, "Param_16PO"),true, "*****test ID:_42_Not able to set value for PO Number1*****");
		}
		wait(1200);
		objAdjunoLIMAShipmentBookingPOM.getBtnRun().click();

		objSoftAssert.assertAll();
	}

	@Test(priority = 101)
	public void compareEDITShipmentGridData() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		List<Shipmentdata> list = new ArrayList<Shipmentdata>();

		list = objAdjunoLIMAShipmentBookingPOM.getStaus1();

		objAdjunoLIMAShipmentBookingPOM.compareshipmentgriddata1(strPoNumber1,strStatus,list);


		objSoftAssert.assertAll();
	}
	
	@Test(priority = 103)
	public void Test_45_clickSearchTab1() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		
		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTools(),objAdjunoLIMAShipmentBookingPOM.getLnkShipmentBooking());
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvSearch()), true, "*****test ID:_45_Search Chevorn is not displayed in Shipment Booking*****");

		veifyShipmentBookingtxtfields();

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 105)
	public void Test_46_amendedPOdetails() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, " *****test ID:_46_Mode field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Vessel"),true, " *****test ID:_46_Vessel field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OrderNumber"), true, " *****test ID:_46_Order Number field is not cleared in Shipment Booking Search Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_46_Status field is not cleared in Shipment Booking Search Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Product"),true, " *****test ID:_46_Product field is not cleared in Shipment Booking Search Chevron*****");
        wait(2000);
		if (!isNullOrBlank(strPoNumber1)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1,strSearchShipmentBookingForm, "PARAM_OrderNumber"),true, "*****test ID:_46_Not able to set value for PO Number1*****");
		}
		else{
			strMessage = strMessage + strPoNumber1 + "null";	    		
    	}
		
		wait(2000);
		if (!isNullOrBlank(strConfirmationStatusPendingShip1)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip1,strSearchShipmentBookingForm, "PARAM_Status"),true, "*****test ID:_46_Not able to set value for Confirmation Status*****");
		}
		else{
			strMessage = strMessage + strConfirmationStatusPendingShip1 + "null";	    		
    	}
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true, "*****test ID:_46_Not able to click to Select chevron*****");
		
		wait(2000);
		verifyProductGridHeader();
	    objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 106)
	public void Test_47_selectamenmendshipmentcheckbox() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		lstSearchResults = new ArrayList<Shipmentdata>();
		if (objAdjunoLIMAShipmentBookingPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMAShipmentBookingPOM.selectMulitpleProducts(2);
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButton(strSearchShipmentBookingForm,"FORK_SBT_AmendBooking"), true,"*****test ID:_47_Not able to click AmendBooking Button***** ");

		} else {
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"*****test ID:_47_No Products in Search Results.*****");
		}
		wait(2000);

		verifyBookinggridDetails();
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 108)
	public void Test_48_EditBookingfielddetils() {
		SoftAssert objSoftAssert = new SoftAssert();
		wait(2000);
        String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		wait(3000);
		if (!isNullOrBlank(strPackTypeedit)){
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm, "SKUs", 0,"PackType", strPackTypeedit), true, "*****test ID:_48_Not able to set value in Pack Type*****");
		}
		else{
			strMessage = strMessage + strPackTypeedit + "null";	    		
		}
		if (!isNullOrBlank(strFreightTermsedit)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingForm, "SKUs", 0,"FreightTerms", strFreightTermsedit), true,"*****test ID:_48_Not able to set value in Freight Terms*****");
		}
		else{
			strMessage = strMessage + strFreightTermsedit + "null";	    		
		}
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvRouting1()), true,"*****test ID:_48_Not able to click Routing chevron*****");
        wait(2000);
	    verifyShipmentSectiongrdField();
		wait(2000);
		voyageLegsSectionField();
		objSoftAssert.assertAll();
		}
	
	@Test(priority = 110)
	public void Test_49_veifyKPIfielddetils() {
		SoftAssert objSoftAssert = new SoftAssert();
        String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		wait(3000);
		 
		if (!isNullOrBlank(strCarrieredit)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Header", 0, "Carrier", strCarrieredit), true,"*****test ID:_49_Not able to set value for Carrier*****");
		}
		else{
			strMessage = strMessage + strCarrieredit + "null";	    		
		}
		
		if (!isNullOrBlank(strCarrierTypeedit)){
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormRouting,"Header", 0, "NVOType", strCarrierTypeedit), true,"*****test ID:_49_Not able to set value for Carrier Type*****");
		}
		else{
			strMessage = strMessage + strCarrierTypeedit + "null";	    		
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvKPI1()), true,"*****test ID:_49_Not able to click to Routing chevron*****");
     
        
        if (!isNullOrBlank(strKPIReason)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormKPI,"SKUs", 0, "KPIReason", strKPIReason), true,"*****test ID:_49_Not able to set value for KPI Reason***** ");
		} else {
			strMessage = strMessage + strKPIReason + "null";
		}
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvComplete1()), true,"*****test ID:_49_Not able to click Complete1 chevron*****");
        wait(5000);
		objSoftAssert.assertAll();
		}
	
	
	

    @Test(priority = 112)
	public void Test_51_verifyeditViewDetails() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objAdjunoLIMAShipmentBookingPOM.getBtnViewDetail().click();
		wait(3000);
		String poDetails = objAdjunoLIMAShipmentBookingPOM.getTxtViewDetail().getText();
		String[] vals = poDetails.split("Track Reference ");
	       
	       vals[1] = vals[1].replace("[", " ").replace("]", " ");
	       String valss[] = vals[1].split(" ",12);
	       
	       strTrackN0 = valss[1];
	       System.out.println("tRACK NUMBER:"+strTrackN0);
          wait(2000);
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 115)
	public void Test_52_accessEditDetails() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage="";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTrack(),objAdjunoLIMAShipmentBookingPOM.getLnkEdit());

		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getFldRefNo()), true,"*****test ID:_52_Reference field is not found in \"Edit or Create track page\"*****");

		if (!isNullOrBlank(strTrackN0)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForWebElement(objAdjunoLIMAShipmentBookingPOM.getFldRefNo(),strTrackN0), true,"*****test ID:_52_Not able to set value for Refrence Field*****");
		}

		objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnApply());
		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getTitleShipment()), true, "*****test ID:_52_Shipment track page is not loaded*****");
		
		long min = objAdjunoLIMAShipmentBookingPOM.getTrackValue(strLIMAUserName);
	       
		if(min<=275){
            strMessage = "";
        }else{
            strMessage = "Not a todays date";
        }
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 117)
	public void clkshipmentbooking() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objAdjunoLIMAShipmentBookingPOM.getLnkShipmentBooking().click();

		objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 119)
	public void Test_55_verifyeditPOStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkFind(),objAdjunoLIMAShipmentBookingPOM.getLnkPOStatus());

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_16PO"), true,"*****test ID:_55_PO Number field is not cleared in PO Status*****");
		

		if (!isNullOrBlank(strPoNumber1)) {

			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1,strPOConfirmationPOStatusForm, "Param_16PO"),true, "*****test ID:_55_Not able to set value for PO Number field*****");
		}
		objAdjunoLIMAShipmentBookingPOM.getBtnRun().click();

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 121)
	public void compareEditGridData() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		List<Shipmentdata> list = new ArrayList<Shipmentdata>();

		list = objAdjunoLIMAShipmentBookingPOM.getStaus1();

		objAdjunoLIMAShipmentBookingPOM.compareshipmentgriddata1(strPoNumber1,strStatus,list);


		objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 167)
	public void Test_61_verifySearchtabFields2() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTools(),objAdjunoLIMAShipmentBookingPOM.getLnkShipmentBooking());
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSearch()),true,"*****test ID:_61_Not able to click search chevron for BookingMade*****");
        veifyShipmentBookingtxtfields();
		objSoftAssert.assertAll();

	}

	@Test(priority = 169)
	public void Test_62_verifyBookingmadeStatus() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, "*****test ID:_62_Mode field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OrderNumber"), true, " *****test ID:_62_Order Number field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_62_Status field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Product"),true, " *****test ID:_62_Product field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Product"),true, " *****test ID:_62_Product field is not cleared in Search Page*****");
		if (!isNullOrBlank(strPoNumber2)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber2, strSearchShipmentBookingForm,"PARAM_OrderNumber"), true,"*****test ID:_62_Not able to set value in PO number field*****");
		}
	else{
		strMessage = strMessage + strPoNumber2 + "null";	
		
		
	}
		wait(2000);
		if (!isNullOrBlank(strConfirmationStatusPendingShip1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip1, strSearchShipmentBookingForm,"PARAM_Status"), true,"*****test ID:_62_Not able to  set value in Status field*****");
		}
	else{
		strMessage = strMessage + strConfirmationStatusPendingShip1 + "null";	    		
	}
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true,"*****test ID:_62_Not able to click select chevron*****");

		 verifyProductGridHeader();
		 objSoftAssert.assertAll();

	}
	
	@Test(priority = 172)
	public void Test_63_clickcancelbutton() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		lstSearchResults = new ArrayList<Shipmentdata>();
		if (objAdjunoLIMAShipmentBookingPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMAShipmentBookingPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButton(strSearchShipmentBookingForm,"FORK_SBT_CancelBooking"), true,"*****test ID:_63_Cancel booking  button is not clicked*****");

		} else {
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"*****test ID:_63_No Products in Search Results.*****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvReview()), true, "*****test ID:_63_Review Chevorn not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewVendor()), true, "*****test ID:_63_Grid Review Vendor is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewPONumber()), true, "*****test ID:_63_Grid Review PONumber is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewProduct()), true, "*****test ID:_63_Grid Review Product is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewPOQty()), true, "*****test ID:_63_Grid Review POQty is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewOpenQty()), true, "*****test ID:_63_Grid Review OpenQty is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewBkdQty()), true, "*****Grid Review BkdQty is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewBkdCtns()), true, "*****test ID:_63_Grid Review BkdCtns is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewBkdCbm()), true, "*****test ID:_63_Grid Review BkdCbm is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewChKgs()), true, "*****test ID:_63_Grid Review ChKgs is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewPackTypes()), true,"*****test ID:_63_Grid Review PackTypes is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewDC()), true, "*****test ID:_63_Grid Review DC is not visible*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewQC()), true, "*****test ID:_63_Grid Review QC is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhReviewBookingStatus()), true,"*****test ID:_63_Grid Review Booking Status is not visible*****");
		
        objSoftAssert.assertAll();

	}
	
	@Test(priority = 178)
	public void Test_64_verifyMandatoryFieldCommentKPI() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvComplete1()), true,"*****test ID:_64_Not able to click Complete1 chevron*****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getContenterrormsg()), true, "*****test ID:_64_Content msg is not Visible*****");

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 180)
	public void Test_65_setBookingKPIReasonField() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		if (!isNullOrBlank(strKPIReason)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForGridCell(strShipmentBookingFormReview,"SKUs", 0, "KPIReason", strKPIReason), true,"*****test ID:_65_Not able to set value for KPIReason Field*****");
		} else {
			strMessage = strMessage + strKPIReason + "null";
		}

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvComplete1()), true,"*****test ID:_65_Not able to click Complete1 chevron*****");
		wait(5000);
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 183)
	public void Test_66_verifyBookingeditViewDetails() {
		SoftAssert objSoftAssert = new SoftAssert();
        wait(3000);
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		wait(3000);
		if(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getBtnViewDetail())){
		objAdjunoLIMAShipmentBookingPOM.getBtnViewDetail().click();
		}
		else{
			System.out.println("Unable to Locate Element" + objAdjunoLIMAShipmentBookingPOM.getBtnViewDetail());
		}
		wait(3000);
		String poDetails = objAdjunoLIMAShipmentBookingPOM.getTxtViewDetail().getText();
		wait(5000);
		String[] vals = poDetails.split("Track Reference ");
		vals[1] = vals[1].replace("[", " ").replace("]", " ");
	    String valss[] = vals[1].split(" ",12);
	       
	     strTrackN0 = valss[1];
	    System.out.println("track ref num: - " + strTrackN0);
		

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 185)
	public void Test_67_accessBookingEditDetails() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage="";

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTrack(),objAdjunoLIMAShipmentBookingPOM.getLnkEdit());

		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getFldRefNo()), true,"*****test ID:_67_Reference field is not found in \"Edit or Create track page\"*****");

		if (!isNullOrBlank(strTrackN0)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValueForWebElement(objAdjunoLIMAShipmentBookingPOM.getFldRefNo(),strTrackN0), true,"*****test ID:_67_Not able to set value for Refrence field*****");
		}

		objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnApply());
		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getTitleShipment()), true, "*****test ID:_67_Shipment track page is not loaded*****");
		
		long min = objAdjunoLIMAShipmentBookingPOM.getTrackValue(strLIMAUserName);
	       
		if(min<=275){
            strMessage = "";
        }else{
            strMessage = "Not a todays date";
        }
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 190)
	public void Test_68_lnkshipmentbooking() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		
		objAdjunoLIMAShipmentBookingPOM.getLnkShipmentBooking().click();
		strMessage = strMessage + strPoNumber1 + "null";	
				
	    objSoftAssert.assertAll();
	}
	
	@Test(priority = 192)
	public void Test_69_verifyBookingPOStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkFind(),objAdjunoLIMAShipmentBookingPOM.getLnkPOStatus());

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_16PO"), true,"*****test ID:_69_Po Number field is not cleared in PO Status*****");
		

		if (!isNullOrBlank(strPoNumber1)) {

			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1,strPOConfirmationPOStatusForm, "Param_16PO"),true, "****test ID:_69_Not able to set Po Number field****");
		}
		objAdjunoLIMAShipmentBookingPOM.getBtnRun().click();

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 195)
	public void compareBookingGridData() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		List<Shipmentdata> list = new ArrayList<Shipmentdata>();

		list = objAdjunoLIMAShipmentBookingPOM.getStaus1();

		objAdjunoLIMAShipmentBookingPOM.compareshipmentgriddata1(strPoNumber1,strStatus1,list);


		objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 197)
	public void Test_72_verifySearchtabFields3() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipmentBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTools(),objAdjunoLIMAShipmentBookingPOM.getLnkShipmentBooking());
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSearch()),true,"*****test ID:_72_Not able to click to search chevron for BookingMade*****");
		wait(3000);
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 200)
	public void Test_73_verifyBookingmadeStatus3() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Mode"),true, " *****test ID:_73_Param Mode is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm,"PARAM_OrderNumber"), true, " *****test ID:_73_OrderNumber field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Status"),true, " *****test ID:_73_Po Number field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipmentBookingForm, "PARAM_Product"),true, " *****test ID:_73_Product field is not cleared in Search Page*****");
		
		if (!isNullOrBlank(strPoNumber1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1, strSearchShipmentBookingForm,"PARAM_OrderNumber"), true,"*****test ID:_73_Not able to  set value for PO number field*****");
		}
	else{
		strMessage = strMessage + strPoNumber1 + "null";	
		
		
	}
		wait(2000);
		if (!isNullOrBlank(strConfirmationStatusPendingShip1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip1, strSearchShipmentBookingForm,"PARAM_Status"), true,"*****test ID:_73_Not able to  set value for Status field*****");
		}
	else{
		strMessage = strMessage + strConfirmationStatusPendingShip1 + "null";	    		
	}
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true,"*****test ID:_73_Not able to click select chevron*****");
         wait(2000);
     	verifyProductGridHeader();
		objSoftAssert.assertAll();

	}
	
	
    @Test(priority = 205)
	public void Test_74_clickAddDialogbutton() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		lstSearchResults = new ArrayList<Shipmentdata>();
		if (objAdjunoLIMAShipmentBookingPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMAShipmentBookingPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButton(strSearchShipmentBookingForm,"FORK_SBT_AddDialog"), true,"*****test ID:_74_Add Dialog button is not clicked*****");

		} else {
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"*****test ID:_74_No Products in Search Results.*****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.checkDoesChevronExist(objAdjunoLIMAShipmentBookingPOM.getChvDialog()), true, "*****test ID:_74_Search Chevorn does not exist*****");
			
			objSoftAssert.assertAll();
      }
	}

	@Test(priority = 208)
	public void Test_75_checkDialogFields() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhSubject()), true, "*****test ID:_75_Grid Subject is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhMessage()), true, "*****test ID:_75_Grid Message is not visible*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getGhCopybyEMailto()), true, "*****test ID:_75_Grid CopybyEMailto is not visible*****");

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 210)
	public void Test_76_verifyMandatoryDialogMessage()  {
		SoftAssert objSoftAssert = new SoftAssert();
		
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		String strMessage="";
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strShipmentBookingFormDialog, "Subject"),true, " *****test ID:_76_Subject Field is not cleared in Dialog Page*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strShipmentBookingFormDialog, "CCEMail"),true, " *****test ID:_76_CC Email is not cleared in Dialog Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strShipmentBookingFormDialog, "Dialog"),true, " *****test ID:_76_ Dialog is not cleared Dialog Page*****");
		wait(2000);
		if (!isNullOrBlank(strSubject)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strSubject, strShipmentBookingFormDialog,"Subject"), true,"*****test ID:_76_Not able to  set value in Subject field*****");
		}
	else{
		strMessage = strMessage + strSubject + "null";	
		
		}
		
		if (!isNullOrBlank(strCopybyEmailto1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strCopybyEmailto1, strShipmentBookingFormDialog,"CCEMail"), true,"*****test ID:_76_Not able to  set value in CC EMail field*****");
		}
	else{
		strMessage = strMessage + strCopybyEmailto1 + "null";
		}
		
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvComplete1()),true, "*****test ID:_76_Not able to click complete cheveron*****");
		
		wait(8000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.isElementPresent(objAdjunoLIMAShipmentBookingPOM.getDialogContenterrormsg()),true, "*****test ID:_76_ Validation message  is not shown up*****");
		objSoftAssert.assertAll();
		

	}

		
	@Test(priority = 219)
	public void Test_77_verifyCompleteMessage(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		wait(3000);
		
		Actions action = new Actions(driver);

	   action.moveToElement(objAdjunoLIMAShipmentBookingPOM.getLnklogoff()).perform();
		
	//	objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strShipmentBookingFormDialog, "Subject"),true, " *****_76_Subject not found*****");
		
	//	objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strShipmentBookingFormDialog, "CCEMail"),true, " *****_77_CCEmail not found*****");
	//	objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strShipmentBookingFormDialog, "Dialog"),true, " *****_77_ Message  not found*****");
		wait(2000);
		
		
	/*	if (!isNullOrBlank(strSubject)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strSubject, strShipmentBookingFormDialog,"Subject"), true,"*****_77_not able to  set value in Subject field for Dialog*****");
		}
	else{
		strMessage = strMessage + strSubject + "null";	
		
	}*/
		
		if (!isNullOrBlank(strMessage1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strMessage1, strShipmentBookingFormDialog,"Dialog"), true,"*****test ID:_77_Not able to  set value in Message field for Dialog*****");
		}
	else{
		strMessage = strMessage + strMessage1 + "null";	
	}
		
	/*if (!isNullOrBlank(strCopybyEmailto1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strCopybyEmailto1, strShipmentBookingFormDialog,"CCEMail"), true,"*****_77_not able to  set value in cc field for Dialog*****");
		}
	else{
		strMessage = strMessage + strCopybyEmailto1 + "null";	
	}*/
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvComplete2()),true,"*****test ID:_77_Not able to click complete1 chevron*****");
	    wait(5000);
		objSoftAssert.assertAll();
	}
	
	
    @Test(priority = 225)
	public void Test_81_verifyDailogColumn() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);

		wait(5000);

		String strhref = objAdjunoLIMAShipmentBookingPOM.getLnkDailog().getAttribute("onclick");
		if (!isNullOrBlank(strhref)) {

		} else {
			strMessage = strMessage + "It is not a Link";
		}
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getLnkDailog()), true,"*****test ID:_81_Dailog hyperLink is not clicked*****");
        wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.getCCmsg().getText().equalsIgnoreCase(strCopybyEmailto1), true,"*****test ID:_81_CC does not match with the created Dialog*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.getNewSubject().getText().equalsIgnoreCase(strSubject), true,"*****test ID:_81_Subject does not match with the created Dialog*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.getTxtmsg().getText().equalsIgnoreCase(strMessage1), true,"*****test ID:_81_Message does not match with the created Dialog*****");

		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAShipmentBookingPOM.getBtnClose()), true, "*****test ID:_81_Dailog popup is not closed*****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 230)
	public void Test_83_verifyVendorBookingStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
		String strMessage="";
		objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchVendorBookingForm,objAdjunoLIMAShipmentBookingPOM.getLnkTools(),objAdjunoLIMAShipmentBookingPOM.getLnkVendorBooking());
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchVendorBookingForm,"PARAM_Mode"), true, " *****test ID:_83_Mode field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchVendorBookingForm,"PARAM_OrderNumber"), true, " *****test ID:_83_Po Number field is not cleared in Search Page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchVendorBookingForm, "PARAM_BookingStatus"),true, " *****test ID:_83_Booking Status field is not cleared in Search Page*****");
		
		if (!isNullOrBlank(strPoNumber1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1, strSearchVendorBookingForm,"PARAM_OrderNumber"), true,"*****test ID:_83_Not able to  set value in PO field*****");
		}
		else{
			strMessage = strMessage + strPoNumber1 + "null";
		}
		
	
		if (!isNullOrBlank(strConfirmationStatusPendingShip2)) {
			objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip2, strSearchVendorBookingForm,"PARAM_BookingStatus"), true,"*****test ID:_83_Not able to  set value in Status field*****");
		}
	else{
		strMessage = strMessage + strConfirmationStatusPendingShip2 + "null";
	}
		
		objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true,"*****test ID:_83_Not able to click to select chevron for Booking made*****");
		
		List<Shipmentdata> list = new ArrayList<Shipmentdata>();

		list = objAdjunoLIMAShipmentBookingPOM.getStaus2();

		objAdjunoLIMAShipmentBookingPOM.compareshipmentgriddata2(strPoNumber1,strStatus1,list);

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 235)
    public void Test_84_verifyShipAuthorityBookingStatus() {
        System.out.println();
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipmentBookingPOM = new AdjunoLIMAShipmentBookingPOM(driver);
        String strMessage="";
        objAdjunoLIMAShipmentBookingPOM.callMouseHover(strSearchShipAuthorityForm,objAdjunoLIMAShipmentBookingPOM.getLnkTools(),objAdjunoLIMAShipmentBookingPOM.getLnkShipAuthority());
        
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipAuthorityForm,"PARAM_Mode"), true, " *****test ID:_84_Mode field is not cleared in Search Page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipAuthorityForm,"PARAM_PONumber"), true, " *****test ID:_84_PO Number field is not cleared in Search Page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clearInputField(strSearchShipAuthorityForm, "PARAM_Status"),true, " *****test ID:_84_Status field is not cleared in Search Page*****");
        wait(2000);
        if (!isNullOrBlank(strPoNumber1)) {
            objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strPoNumber1, strSearchShipAuthorityForm,"PARAM_PONumber"), true,"*****test ID:_84_Not able to  set value in PO field*****");
        }
        else{
        	strMessage = strMessage + strPoNumber1 + "null";    
        }
        
        wait(2000);
        
        if (!isNullOrBlank(strMode1)) 
		{objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strMode1,strSearchShipAuthorityForm, "PARAM_Mode"),true, "*****test ID:_84_Not able to  set value in Mode field*****");
		}
		else{
			strMessage = strMessage + strMode1 + "null";	    		
    	}
        if (!isNullOrBlank(strConfirmationStatusPendingShip)) {
            objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.setFieldValue(strConfirmationStatusPendingShip, strSearchShipAuthorityForm,"PARAM_Status"), true,"*****test ID:_84_Not able to  set value in Status field*****");
        }
    else{
        strMessage = strMessage + strConfirmationStatusPendingShip + "null";
    }
        
        objSoftAssert.assertEquals(objAdjunoLIMAShipmentBookingPOM.clickChevron(objAdjunoLIMAShipmentBookingPOM.getChvSelect()),true,"*****test ID:_84_Not able to click select chevron for Booking made*****");
        
        
        boolean searchResult = objAdjunoLIMAShipmentBookingPOM.getNoResult().isDisplayed();
        if(searchResult==true){
            strMessage = strMessage + " No results are found ";
        }else{
            List<Shipmentdata> list = new ArrayList<Shipmentdata>();

            list = objAdjunoLIMAShipmentBookingPOM.getStaus3();

            strMessage = objAdjunoLIMAShipmentBookingPOM.compareshipmentgriddata3(strPoNumber1,strStatus2,list);
        }
        
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id:84 -"+strMessage  +" *****");
        objSoftAssert.assertAll();

    }
	
	 @AfterTest 
	 public void terminateBrowser() 
	 { 
		 driver.quit(); 
		 }
	

	}
