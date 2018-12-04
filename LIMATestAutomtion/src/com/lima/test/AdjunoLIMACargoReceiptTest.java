package com.lima.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.ProductOrderData;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMACargoReceiptPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMACargoReceiptTest {

	WebDriver driver;

    long nPage_Timeout = 0;
    String strTestURL;
    
    String strLIMAUserName;
    String strLIMAPassword;
    String strOriginPort;
    String strBookedVessel;
	String strMode;
	String strLoading;
	String strAwaiting;
	String strTestData;
	String strPackType;
	String strVendorRef;
	String strProduct;
    String strStatusAwaiting;
    String strStatusReceipted;
    String strPoNumber;
    String strInvalidPoNumber;
    String strStatusBookingMade;
    
    String strCargoReceiptTitle;
    String strCargoReceiptFormName;
    
    
    String strShipmentBooking;
    String strShipmentBookingTitle;
    String strStatus_SB;
    String strPO_Number_SB;
    
    String strEditFormNamePOManager;
    String strReceiptFormName;
    String strRecCtns;
    String strRecCBM;

    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoUILibrary objAdjunoUILibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    
    NodeList searchParams;
    NodeList search;
    
    ProductOrderData product;

	AdjunoLIMACargoReceiptPOM objAdjunoLIMACargoParamPOM;

	ProductOrderData shipmentData;

	NodeList receiptCaption;

	NodeList receiptDetailsCaption;

	NodeList receiptDetails;

	String strTrackNO = "";

	String strPOStatusFormName;
    
    @BeforeTest
    public void Setup()
    {
    	objAdjunoLIMALibrary = new AdjunoLIMALibrary();
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());

            XPath xPath = XPathFactory.newInstance().newXPath();

            Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout", dDoc, XPathConstants.NODE);
            nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());

            Node testURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testURL.getTextContent();
            
            Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
            strLIMAUserName = limaUserName.getTextContent();

            Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
            strLIMAPassword = limaPassword.getTextContent(); 
            
            Node pageTitleCargoReceipt = (Node) xPath.evaluate("/config/LIMA/Page_Title_Cargo_Receipt", dDoc, XPathConstants.NODE);
            strCargoReceiptTitle = pageTitleCargoReceipt.getTextContent();
            
            Node formNameCargoReceipt = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Cargo_Receipt", dDoc, XPathConstants.NODE);
            strCargoReceiptFormName = formNameCargoReceipt.getTextContent();
            
            Node formNameShipmentBooking = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Shipment_BookingForm", dDoc, XPathConstants.NODE);
            strShipmentBooking = formNameShipmentBooking.getTextContent();
            
            Node pageTitleShipmentBooking = (Node) xPath.evaluate("/config/LIMA/Page_Title_ShipmentBooking", dDoc, XPathConstants.NODE);
            strShipmentBookingTitle = pageTitleShipmentBooking.getTextContent();
            
            Node editFormNamePOManager = (Node) xPath.evaluate("/config/LIMA/View_Form_Name_PO", dDoc, XPathConstants.NODE);
            strEditFormNamePOManager = editFormNamePOManager.getTextContent();


            Node receiptFormName = (Node) xPath.evaluate("/config/LIMA/Receipt_Form_Name", dDoc, XPathConstants.NODE);
            strReceiptFormName = receiptFormName.getTextContent();
            
            Node POStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
            strPOStatusFormName = POStatusFormName.getTextContent();
            
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
        DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrLIMACargoReceiptXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            searchParams = (NodeList) xPath1.evaluate("/Cargo_Receipt/SearchParams", dDoc1, XPathConstants.NODESET);
            
            search = (NodeList) xPath1.evaluate("/Cargo_Receipt/Search", dDoc1, XPathConstants.NODESET);
            
            receiptCaption = (NodeList) xPath1.evaluate("/Cargo_Receipt/Receipt_Caption", dDoc1, XPathConstants.NODESET);
             
            receiptDetailsCaption = (NodeList) xPath1.evaluate("/Cargo_Receipt/Receipt_Details_Caption", dDoc1, XPathConstants.NODESET);
            
            receiptDetails = (NodeList) xPath1.evaluate("/Cargo_Receipt/Receipt_Details", dDoc1, XPathConstants.NODESET);
            
             
        }catch(Exception ex){
        	ex.printStackTrace();
        }
    
    }
       
    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
    
     
    //Title  
    @Test(priority=1)
    public void test_1_AccessCargoReceipt()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        
    	String strTitle = objAdjunoLIMACargoParamPOM.callMouseHover(strCargoReceiptFormName,objAdjunoLIMACargoParamPOM.getLnkTools(), objAdjunoLIMACargoParamPOM.getLnkCargoReceipt());
    	
    	boolean bFlag = true;
    	
    	if (isNullOrBlank(strTitle))
    		bFlag = false;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strCargoReceiptTitle))
    			bFlag = true;
    		else
    			bFlag = false;
    	}	       
    	objSoftAssert.assertEquals(bFlag, true, "*****  Test ID : 1 - Title of the page is Wrong *****");
    	try
    	{
    	objAdjunoLIMACargoParamPOM.clickButtonUsingWebElement(objAdjunoLIMACargoParamPOM.getBtnOK());
    	}
    	catch(NoSuchElementException e)
    	{
    		
    	}
    	
    	objSoftAssert.assertAll();

    }

    
	private boolean isNullOrBlank(String s) {
		// TODO Auto-generated method stub
		return (s==null || s.trim().equals(""));
	}
	
	   
	//Chev check
    @Test(priority=2)
    public void checkForChevorons(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
    
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesChevronExist(objAdjunoLIMACargoParamPOM.getChvSearch()), true,"search Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesChevronExist(objAdjunoLIMACargoParamPOM.getChvSelect()), true,"select Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesChevronExist(objAdjunoLIMACargoParamPOM.getChvReceipt()), true,"receipt Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesChevronExist(objAdjunoLIMACargoParamPOM.getChvComplete()), true,"complete Chevorn not found");
        
    	objSoftAssert.assertAll();
    }
    
    
    //Fields Check
    @Test(priority=3)
    public void test_2_CheckForExistanceOfFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
     	        
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_OriginPort"), true," *****  Test ID : 2 - Origin port element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_Vessel"), true," *****  Test ID : 2 - Vessel element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_Mode"), true," *****  Test ID : 2 - Mode element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_Loading"), true," *****  Test ID : 2 - Loading element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_Status"), true," *****  Test ID : 2 - Status element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_Vendor"), true," *****  Test ID : 2 - Vendor element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_PackType"), true," *****  Test ID : 2 - PackType element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_CustomerReference"), true," *****  Test ID : 2 - Vender ref element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_PO"), true," *****  Test ID : 2 - Po number element not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strCargoReceiptFormName,"PARAM_Product"), true," *****  Test ID : 2 - Product element not found ***** ");
        
        objSoftAssert.assertAll();
    }
   
    
    
    @Test(priority=4)
    public void clearFeilds()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_OriginPort"), true," *****  Test ID : 2 - Origin port element not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_Vendor"), true,"*****  Test ID : 2 - Vendor element not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_Vessel"), true,"*****  Test ID : 2 - Vessel element not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_PackType"),true,"*****  Test ID : 2 - Pack Type element not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_Mode"), true,"*****  Test ID : 2 - Mode element not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_CustomerReference"),true," *****  Test ID : 2 -can't clear Vendor ref Field");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_Loading"), true,"*****  Test ID : 2 - Loading element not cleared *****");     
	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_Status"),true," *****  Test ID : 2 - Status  element not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_PO"),true," *****  Test ID : 2 - PO element not cleared ***** ");
	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strCargoReceiptFormName,"PARAM_Product"),true," *****  Test ID : 2 - Product element not cleared ***** ");
       
	    objSoftAssert.assertAll();
    }

    
    //Setting status awaiting
    @Test(priority=5)
    public void test_8_SearchProducts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        String strMessage ="";
	    
        clearFeilds();
	    
	    //get data from cargoreceipt.xml
	    Element el;	         
	    for(int i=0; i <= searchParams.getLength()-1; i++)
        {
         	if (searchParams.item(i).getNodeType() == Node.ELEMENT_NODE)
         	{
         		el = (Element) searchParams.item(i);
 				strStatusAwaiting = el.getElementsByTagName("Status_Awaiting").item(0).getTextContent();
 				
 				strStatusReceipted = el.getElementsByTagName("Status_Receipted").item(0).getTextContent();
 				 
 				
 				strInvalidPoNumber = el.getElementsByTagName("Invalid_PONumber").item(0).getTextContent();
 			
 			    //strVendorRef = el.getElementsByTagName("Vendor_Ref").item(0).getTextContent();
 			
 				strPackType = el.getElementsByTagName("Pack_Type").item(0).getTextContent(); 	 				
         	}	
        }

    	//Setting the field value Status Awaiting:setFieldValue
	    if(!isNullOrBlank(strStatusAwaiting))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusAwaiting,strCargoReceiptFormName,"PARAM_Status"),true," *****  Test ID : 8 - Status is not set as Awaiting ***** ");    	   
	    }else{
	    	strMessage = strMessage + "status is Empty";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getChvSelect()),true," ***** Not able to click Select Chevron ***** ");
	    wait(5000);
	        
	    objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll(); 
    }
    
    
    //Verify Awaiting status
    @Test(priority=7)
    public void test_12_StatusAwaiting()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        String strMessage ="";
	       
        clearFeilds();

    	//Setting the field value Status Awaiting:setFieldValue
	    if(!isNullOrBlank(strStatusAwaiting))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusAwaiting,strCargoReceiptFormName,"PARAM_Status"),true," *****  Test ID : 12 - Status is not set as Awaiting ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 8 - Status is Empty ***** ";
	    }
	   
	    //status awaiting
	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButton(strCargoReceiptFormName, "FORK_CargoReceipt_RefineSearch"),true," ***** Test ID : 12 - Not able to click Refine Search button ***** ");
	    wait(5000);
	     
	    List<ProductOrderData> list = new ArrayList<ProductOrderData>();  
	    list = objAdjunoLIMACargoParamPOM.getStaus();
	    strMessage = objAdjunoLIMACargoParamPOM.verifyStaus(strStatusAwaiting,list);
	    
	    strPoNumber = list.get(0).getPoNumber();
	    System.out.println("PO number:"+strPoNumber);
	    objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
	    
      objSoftAssert.assertAll();  
    }
    
    
    //setting PO number
    @Test(priority=10)
    public void test_9_SelectChevron() throws InterruptedException{
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	String strMessage="";
    	
    	objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
    	objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	
    	//Setting the field value PO Number:setFieldValue
    	if(!isNullOrBlank(strPoNumber))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPoNumber,strCargoReceiptFormName,"PARAM_PO"),true," ***** Test ID : 9 - PO number is not set ***** ");    	   
	    }else{
	    	strMessage = strMessage + "***** Test ID : 9 - PO number is Empty *****";
	    }
    	
    	//Setting the field value PackType:setFieldValue
    	if(!isNullOrBlank(strPackType))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPackType,strCargoReceiptFormName,"PARAM_PackType"),true,"***** Test ID : 9 - Pack Type is not set *****");    	   
	    }else{
	    	strMessage = strMessage + "***** Test ID : 9 - Pack Type is Empty *****";
	    }
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButton(strCargoReceiptFormName, "FORK_CargoReceipt_RefineSearch"),true," ***** Test ID : 9 - Not able to click Refine Search button ***** ");
	  
    	objSoftAssert.assertEquals(strPoNumber.equals(objAdjunoLIMACargoParamPOM.getLstOrderNumber().getText()), true," ***** Test ID : 9 - PO number Does not match ***** ");
	   
    	product = objAdjunoLIMACargoParamPOM.getProductOrder();
    	
    	objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
    	objSoftAssert.assertAll();
    }
      
   
    //Check for invalid po number
    @Test(priority=12)
    public void test_11_SelectSingleInvalid() throws InterruptedException{
    	SoftAssert objSoftAssert = new SoftAssert();
	
    	String strMessage="";
	
    	objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
    	objAdjunoUILibrary = new AdjunoUILibrary(driver);
	
    	clearFeilds();
	    
    	//Setting the field value PO Number:setFieldValue
    	if(!isNullOrBlank(strInvalidPoNumber))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strInvalidPoNumber,strCargoReceiptFormName,"PARAM_PO"),true," ***** Test ID : 10 - Invalid PO number is not set ***** ");    	   
    	}else{
    		strMessage = strMessage + "***** Test ID : 10 - Invalid PO number field is Empty *****";
    	}
	
    	 if(!isNullOrBlank(strStatusAwaiting))
 	    {
 	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusAwaiting,strCargoReceiptFormName,"PARAM_Status"),true," ***** Test ID : 10 - Status is not set for Awaiting ***** ");    	   
 	    }else{
 	    	strMessage = strMessage + " ***** Test ID : 10 - Status is Empty ***** ";
 	    }
	
    	objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButton(strCargoReceiptFormName, "FORK_CargoReceipt_RefineSearch"),true," ***** Test ID : 10 - Not able to click Refine Search button ***** ");
    	wait(5000);
    
    	objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getNoItemsFound()), true," ***** Test ID : 10 - No 'item found message' not displayed ***** ");
    	
    	objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
    	objSoftAssert.assertAll();
    }
      
    
    
      //Setting the Received status
      @Test(priority=15)
      public void test_13_StatusReceipted()
      {
      	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        String strMessage ="";
          
        clearFeilds();
  	    
      	//Setting the field value Status Receipted:setFieldValue
  	    if(!isNullOrBlank(strStatusReceipted))
  	    {
  	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusReceipted,strCargoReceiptFormName,"PARAM_Status"),true," *****  Test ID : 13 - Status is not set as Receipted ***** ");    	   
  	    }else{
  	    	strMessage = strMessage + " *****  Test ID : 13 - Status is Empty ***** ";
  	    }
  	   
  	    //status Receipted
  	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButton(strCargoReceiptFormName, "FORK_CargoReceipt_RefineSearch"),true," ***** Test ID : 13 - Not able to click Refine Search button ***** ");
  	    wait(5000);
  	     
  	    List<ProductOrderData> list = new ArrayList<ProductOrderData>();  
  	    list = objAdjunoLIMACargoParamPOM.getStaus();
  	    
  	    strMessage = objAdjunoLIMACargoParamPOM.verifyStaus(strStatusReceipted,list);
  	    
  	    objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
      }
      
    

    //Getting the value from Shipment booking
    @Test(priority=18)
    public void test_15_ShippmentDetails(){
    	  
    	SoftAssert objSoftAssert = new SoftAssert();
      	
      	String strMessage="";
      	
      	objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
      	objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	  
    	String strTitle = objAdjunoLIMACargoParamPOM.callMouseHover(strShipmentBooking,objAdjunoLIMACargoParamPOM.getLnkTools(), objAdjunoLIMACargoParamPOM.getLnkShipmentBooking());
      	
    	objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strShipmentBooking,"PARAM_Mode"),true," ***** Test ID : 15 - can't clear Mode in shipment booking page ***** ");
  	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strShipmentBooking,"PARAM_Status"),true," ***** Test ID : 15 - can't clear status in shipment booking page ****** ");
  	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strShipmentBooking,"PARAM_OrderNumber"),true," ***** Test ID : 15 - can't clear PO number Field in shipment booking page ***** ");
  	 
  	    Element el1;
 	    for(int i=0; i <= search.getLength()-1; i++)
  	    {
  	     if (search.item(i).getNodeType() == Node.ELEMENT_NODE)
   	     {
   		    el1 = (Element) search.item(i);
			strStatus_SB = el1.getElementsByTagName("Status").item(0).getTextContent();
			
			strPO_Number_SB = strPoNumber;
   	     }
  	    }
      	boolean bFlag = true;
      	
      	if (isNullOrBlank(strTitle))
      		bFlag = false;
      	
      	if (!(isNullOrBlank(strTitle)))
      	{
      		if (strTitle.equalsIgnoreCase(strShipmentBookingTitle))
      			bFlag = true;
      		else
      			bFlag = false;
      	}	       
      	
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strShipmentBooking,"PARAM_Status"),true,"***** Test ID : 15 - can't clear status in shipment booking page ******");
       
        //Setting the field value Status:setFieldValue
  	    if(!isNullOrBlank(strStatus_SB))
  	    {
  	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatus_SB,strShipmentBooking,"PARAM_Status"),true,"***** Test ID : 15 - Status has not been set in shipment booking page ******");    	   
  	    }else{
  	    	strMessage = strMessage + " ***** Test ID : 15 - Status is Empty in shipment booking page ***** ";
  	    }
  	   
  	    //status Made Booking
  	    if(!isNullOrBlank(strPO_Number_SB))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPO_Number_SB,strShipmentBooking,"PARAM_OrderNumber"),true,"***** Test ID : 15 - PO Number is not set in shipment booking page ******");    	   
	    }else{
	    	strMessage = strMessage + "***** Test ID : 15 - PO Number is Empty in shipment booking page ******";
	    }
  	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strShipmentBooking,"PARAM_Mode"),true,"***** Test ID : 15 - can't clear Mode in shipment booking page *****");
  	    //objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strShipmentBooking,"PARAM_Mode"),true,"can't set null in Mode");
  	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getChvSelect()),true,"***** Test ID : 15 - can't click on Select chev in shipment booking page *****");
	    wait(5000);
	  
	    objSoftAssert.assertEquals(bFlag, true,"***** Test ID : 15 - page title is wrong for shipment booking *****");
	    objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
  	    
        objSoftAssert.assertAll();   
      }
      
      
      //Compare the Shipment and Cargo receipt value
      @Test(priority=20)
      public void test_16_VerifyShipmentDetailsData(){
    	  
    	SoftAssert objSoftAssert = new SoftAssert();
         	
      	objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
      	shipmentData= objAdjunoLIMACargoParamPOM.getShipmentData();
      	
      	wait(1000);
        
      	objSoftAssert.assertEquals(product.getPoNumber().equalsIgnoreCase(shipmentData.getPoNumber()),true,"***** Test ID : 16 - PO number data does not match with shipment data *****");
      	System.out.println("Product PO value:"+product.getPoNumber() +"---"+"shipment po number:"+shipmentData.getPoNumber() );
        objSoftAssert.assertEquals(product.getOriginPort().equalsIgnoreCase(shipmentData.getOriginPort()),true,"***** Test ID : 16 - origin port data does not match with shipment data *****");
    	objSoftAssert.assertEquals(product.getVessel().equalsIgnoreCase(shipmentData.getVessel()),true,"***** Test ID : 16 - Vessel data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getETD().equalsIgnoreCase(shipmentData.getETD()),true,"***** Test ID : 16 - ETD data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getETA().equalsIgnoreCase(shipmentData.getETA()),true,"***** Test ID : 16 - ETA data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getLoading().equalsIgnoreCase(shipmentData.getLoading()),true,"***** Test ID : 16 - loading data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getProduct().equalsIgnoreCase(shipmentData.getProduct()),true,"***** Test ID : 16 - product data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getPackType().equalsIgnoreCase(shipmentData.getPackType()),true,"***** Test ID : 16 - pack type data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getBkdPcs().equalsIgnoreCase(shipmentData.getBkdPcs()),true,"***** Test ID : 16 - Bkd pcs data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getBkdCartons().equalsIgnoreCase(shipmentData.getBkdCartons()),true,"***** Test ID : 16 - BKD Cartons data does not match with shipment data *****");
     	objSoftAssert.assertEquals(product.getBkdCdm().equalsIgnoreCase(shipmentData.getBkdCdm()),true,"***** Test ID : 16 - BKD cbm data does not match with shipment data *****");
        
     	objSoftAssert.assertAll();
      }
      
      
      //Selecting value again
      @Test(priority=24)
      public void verifyingPoDetails()
      {
          SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        
          String strMessage="";
          
      	  String strTitle = objAdjunoLIMACargoParamPOM.callMouseHover(strCargoReceiptFormName,objAdjunoLIMACargoParamPOM.getLnkTools(), objAdjunoLIMACargoParamPOM.getLnkCargoReceipt());
      	
          boolean bFlag = true;
      	
          if(isNullOrBlank(strTitle))
      		bFlag = false;
      	
      	  if(!(isNullOrBlank(strTitle)))
      	  {
      		  if(strTitle.equalsIgnoreCase(strCargoReceiptTitle)){
      			bFlag = true;
      		  }else{
      			bFlag = false;
      		  }
      	  }	      
      	
      	objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");
      	
      	clearFeilds();
	    
    	//Setting the field value Status Awaiting:setFieldValue
  	    if(!isNullOrBlank(strStatusAwaiting))
  	    {
  	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusAwaiting,strCargoReceiptFormName,"PARAM_Status"),true,"Status is not set");    	   
  	    }else{
  	    	strMessage = strMessage + "status is Empty";
  	    }
  	    
    	//Setting the field value PO Number:setFieldValue
    	if(!isNullOrBlank(strPoNumber))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPoNumber,strCargoReceiptFormName,"PARAM_PO"),true," PO number is not set");    	   
    	}else{
    		strMessage = strMessage + "PO number is Empty";
    	}
		
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getChvSelect()),true,"Not able to click Select Chevron");
 	    wait(5000);
 	 
 	    objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
      	objSoftAssert.assertAll();
      }
     
      
      //Po number is hyper link or not
      @Test(priority=26)
      public void test_18_CheckForHyperLink(){
    	  SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
         
    	  String strMessage ="";
          objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getLstOrderNumber()), true,"***** Test ID : 18 - Hyperlink is not working *****");
      		
          String strhref = objAdjunoLIMACargoParamPOM.getLstOrderNumber().getAttribute("href");
          if(!isNullOrBlank(strhref))
          {
     
          }
          else
          {
              strMessage = strMessage + " ***** Test ID : 18 - PO number is not a hyperLink *****";
          }
          objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
        		  
      	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getLstOrderNumber()), true,"***** Test ID : 19 - Hyperlink is not clicked *****");
          
      	  objSoftAssert.assertAll();    	
      }
      
      
      
      //Checking for Identifier
      @Test(priority=28)
      public void test_20_VerifyingIdentifiersDetails()
      {
          SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        
          String strMessage="";
          
          objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getBtnApply());
          //objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.getGridCellElementValue(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Identifier"),true,"Identifier is not matching");    
            
          String strPoIdentifier = objAdjunoLIMACargoParamPOM.getGridCellElementValue(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Identifier");
		    
          if(strPoIdentifier.equals(product.getIdentifier())){
        	  
          }else{
        	  strMessage = strMessage +" ***** Test ID : 20 - Identifier don't match ***** ";
          }

          objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
          objSoftAssert.assertAll();
      }

      
    //BackSpace button  
 	@Test(priority=30)
 	public void test_20_ClickBack()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
    
 		driver.navigate().back();
 		wait(5000);

 		driver.navigate().back();
    
 		objSoftAssert.assertAll();
    }
 	
 	
 	//Selecting Check box
 	@Test(priority=35)
 	public void test_21_ReceiptChevron()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);

 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickCheckBox(objAdjunoLIMACargoParamPOM.getcheckBox()),true," ***** Test ID : 21 - Didn't Select the check box ***** ");
 		 
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getclickReceipt()),true," ***** Test ID : 21 - Receipt Chevron didn't click ***** ");
     	wait(5000);
 		 
  		objSoftAssert.assertAll(); 
 	}
 	
 	
 	//Checking for the grid
 	@Test(priority=40) 
 	public void test_24_VerifyReceiptGrids()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);

 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strReceiptFormName,"GridReceipt"), true,"***** Test ID : 24 - Receipts Grid not found *****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkDoesElementExist(strReceiptFormName,"GridSKUs"), true,"***** Test ID : 25 - Receipt Details Grid not found *****");
 		
 		objSoftAssert.assertAll();
 	}
 	
 	
 	//Verifing Caption
 	@Test(priority=43) 
 	public void verifyReceiptGridsCaption()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 		
        String strMessage="";
            
        ArrayList<String> list = new ArrayList<String>();
            
        list = objAdjunoLIMACargoParamPOM.getCaptionsList(strReceiptFormName,"GridReceipt");
            
        strMessage = objAdjunoLIMACargoParamPOM.verifyCaptionsONGrid(list,receiptCaption,7);
                   
        objSoftAssert.assertEquals(strMessage.equals(""), true,"Receipt Grid Caption:"+strMessage);
                         		
 		objSoftAssert.assertAll();
 	}
 	
 	
 	//Comparing the Grid Values
 	@Test(priority=46) 
 	public void test_25_VerifyReceiptGridValues()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 		String strMessage= "";
 		String originport = product.getOriginPort();
 		String etd = product.getETD();
 		
 		//Origin Port comparison 
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ReceivingLocation").equals("")){
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(originport, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ReceivingLocation")),true,"***** Test ID : 25 - Receiving Location Does not match *****");
        }else{
            strMessage = strMessage + originport +" ***** Test ID : 25 - Receiving Location is Empty *****";
        }
 		
 		//ETD comparison
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ETD").equals("")){
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(etd, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ETD")),true,"***** Test ID : 25 - ETD Does not match *****");
        }else{
            strMessage = strMessage + etd +"***** Test ID : 25 - ETD is Empty *****";
        }
 		
 		objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
 		objSoftAssert.assertAll();
 	}
 	
 	
 	//Caption Check
 	@Test(priority=50) 
 	public void verifyReceiptDetailGridsCaption()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 		
        String strMessage="";
            
        ArrayList<String> list = new ArrayList<String>();
            
        list = objAdjunoLIMACargoParamPOM.getCaptionsList(strReceiptFormName,"GridSKUs");
            
        strMessage = objAdjunoLIMACargoParamPOM.verifyCaptionsONGrid(list,receiptDetailsCaption,9);
      
            
        objSoftAssert.assertEquals(strMessage.equals(""), true,"Receipt Details Grid Caption:"+strMessage);
                         		
 		objSoftAssert.assertAll();
 	}
 	

 	
 	//Mandatory field
 	@Test(priority=55)	 
 	public void test_26_VerifyMandatoryfieldReceivedDate()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 		String strMessage ="";
 		String strErrorMessageReceivedDate = objAdjunoLIMACargoParamPOM.getValidationMessageGridElement(strReceiptFormName,"GridReceipt",0,"ReceivedDate");
        if(!strErrorMessageReceivedDate.equals("")){
        	if(strErrorMessageReceivedDate.contains("&#39;"))
        	{
        		strErrorMessageReceivedDate = strErrorMessageReceivedDate.replaceAll("&#39;" , "'");
        	
        	}
        	boolean bFlag = strErrorMessageReceivedDate.equalsIgnoreCase("> 'Received Date' is a required field");
            
            
            objSoftAssert.assertEquals(bFlag, true,"***** Test ID : 26 - The Mandatory field error message is not dispalyed for received date *****");
           
        }else{
        	strMessage =strMessage + " ***** Test ID : 26 - ReceivedDate error message is Empty *****";
        }
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
 		objSoftAssert.assertAll();	
 	}
 	
 	
 	//Checking for User Enterable
 	@Test(priority=60) 
 	public void test_26_VerifyGridUserEnterable()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 		
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridReceipt",0,"ReceivingLocation",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ReceivingLocation")),true,"***** Test ID : 26 - Origin Port is User Enterable*****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridReceipt",0,"ReceivedDate",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ReceivedDate")),true,"***** Test ID : 26 - Received Date is User Enterable*****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridReceipt",0,"ETD",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ETD")),false,"***** Test ID : 26 - ETD is User Enterable*****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridReceipt",0,"EthosStatus",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"EthosStatus")),false,"***** Test ID : 26 - Ethos Status is User Enterable*****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridReceipt",0,"ReceiptReference",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ReceiptReference")),true,"***** Test ID : 26 - Receipt Reference is User Enterable*****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridReceipt",0,"OnTimeKPI",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"OnTimeKPI")),true,"***** Test ID : 26 - OnTimeKPI is User Enterable*****");
 		
 		objSoftAssert.assertAll();
 	}
 	
 	
 	//verifyig the details
 	@Test(priority=65)
 	public void test_29_VerifyReceiptDetailsGridValue()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 	
 		String strMessage= "";
 		String vendor = product.getVendor();
 		String ponumber = product.getPoNumber();
 		String productCode = product.getProduct();
 		String sku= product.getSKU();
 		String packtype=product.getPackType();
 		
 		//Vendor comparison 
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"Vendor").equals("")){
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(vendor, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"Vendor")),true,"***** Test ID : 29 - Vendor Does not match");
        }else{
            strMessage = strMessage + vendor +" ***** Test ID : 29 - Vendor is Empty on this field ";
        }
 		
 		//ponumber comparison
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"PONumber").equals("")){
 			System.out.println("product val:"+ponumber+" "+"grid val:"+objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"PONumber"));
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(ponumber, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"PONumber")),true," ***** Test ID : 29 - PONumber Does not match");
        }else{
            strMessage = strMessage + ponumber +" ***** Test ID : 29 - PO Number is Empty on this field ";
        }
 		
 		//product comparison
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"Product").equals("")){
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(productCode, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"Product")),true," ***** Test ID : 29 - Product Does not match");
        }else{
            strMessage = strMessage + productCode +" ***** Test ID : 29 - Product Code is Empty on this field  *****";
        }
 		
 		//sku comparison
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"SKU").equals("")){
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(sku, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"SKU")),true," ***** Test ID : 29 - sku Does not match *****");
        }else{
            strMessage = strMessage + sku +" ***** Test ID : 29 - SKU is Empty on this field *****";
        }
 		
 		//PackType comparison
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"PackType").equals("")){
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.compareTwoStringValuesToSame(packtype, objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"PackType")),true," ***** Test ID : 29 - packtype Does not match *****");
        }else{
            strMessage = strMessage + packtype +" ***** Test ID : 29 - Pack Type is Empty on this field *****";
        }
 		
 		//objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkGridCellUserEditable(strReceiptFormName,"GridSKUs",0,"RecPcs"),true,"Rec Pcs is User Enterable");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridSKUs",0,"RecCtns",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"RecCtns")),true,"***** Test ID : 30 - Rec Ctns is User Enterable *****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName,"GridSKUs",0,"RecCube",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridSKUs",0,"RecCube")),true,"***** Test ID : 30 - Rec Cube is User Enterable *****");
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkFieldIsReadOnlyInGrid(strReceiptFormName, "GridSKUs", 0, "RecPcs",objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName, "GridSKUs", 0, "RecPcs")),true,"***** Test ID : 30 - Rec Pcs is not User Enterable *****");
 		
 		objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
 		objSoftAssert.assertAll();
 	}
 	

 	//adding the date verification
 	@Test(priority=70)
 	public void test_31_VerifyReceiptDetailsdropdown()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 	     
 	    String strMessage="";
 	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.checkGridCellUserEditable(strReceiptFormName,"GridReceipt",0,"ReceivingLocation"),true," ***** Grid value is Not Clickable *****");
 	    wait(2000);
 	    List<WebElement> dropdown = objAdjunoLIMACargoParamPOM.getOriginportdropdown();
 	    System.out.println("dropDown size:"+dropdown.size());
 	    if(dropdown.size()>1)
 	    {
 	    	
 	    }else{
            strMessage = strMessage + " ***** Test ID : 31 - Dropdown field does not exist ***** ";
        }
 		
 	    objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
 	    objSoftAssert.assertAll();
 	}
 	
 	
 	 public int getDateDiffrence(Date d1, Date d2){
         return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
 	 }
 	 
 	
 	//Entering valid and invalid dates 
 	@Test(priority=75)
 	public void test_32_VerifyReceiptInvalidReceivedDate() throws ParseException
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);

 		String strEtdDate = null;
 		String strMessage="";
 		if(!objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ETD").equals("")){
 			strEtdDate   = objAdjunoLIMACargoParamPOM.getGridCellElementValue(strReceiptFormName,"GridReceipt",0,"ETD");
        }else{
            strMessage = strMessage + " date value is Empty";
        }
 		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
 		Date etdDate =simpleDateFormat.parse(strEtdDate);
 		
 		//difference between todays date and etd data in days
 		int daydiff = getDateDiffrence(etdDate,new Date());
 		int dayBefore = daydiff-1;
 		int dayAfter =daydiff+2;
 		
 		int sevenWeekBefore = daydiff+49;
 		
 		int sixWeekBefore = daydiff +42;
 		System.out.println("DAY DIF:"+daydiff);
 		
 		strEtdDate = objAdjunoLIMACargoParamPOM.getDate(dayBefore,"dd MMM yyyy");
 		
 		objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValueForGridCell(strReceiptFormName,"GridReceipt",0,"ReceivedDate",strEtdDate),true," ***** Test ID : 32 - Received Date is Mandatory ***** ");
 		String strErrorMessageReceivedDate = objAdjunoLIMACargoParamPOM.getValidationMessageGridElement(strReceiptFormName,"GridReceipt",0,"ReceivedDate");
        if(!strErrorMessageReceivedDate.equals("")){
       
        	boolean bFlag = strErrorMessageReceivedDate.equalsIgnoreCase("> Received Date must be prior to ETD");
        	objSoftAssert.assertEquals(bFlag, true,"***** Test ID : 33 - Received Date must be prior to ETD *****");
             
        }else{
        	strMessage =strMessage + "***** Test ID : 33 - Received Date error message is Empty ***** ";
        }
 		
        wait(3000);
        
        strEtdDate = objAdjunoLIMACargoParamPOM.getDate(dayAfter,"dd MMM yyyy");
        
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValueForGridCell(strReceiptFormName,"GridReceipt",0,"ReceivedDate",strEtdDate),true,"***** Test ID : 33 - Received Date is Mandatory *****");
 		String strErrorMessageReceivedDate2 = objAdjunoLIMACargoParamPOM.getValidationMessageGridElement(strReceiptFormName,"GridReceipt",0,"ReceivedDate");
 		if(!strErrorMessageReceivedDate2.equals("")){
 	       
 			boolean bFlag2 = strErrorMessageReceivedDate2.equalsIgnoreCase("> Received Date must be prior to ETD");
 			objSoftAssert.assertEquals(bFlag2, true,"***** Test ID : 34 - Received Date must be prior to ETD.Received Date cannot be later than todays date. *****");
             
        }else{
        	strMessage =strMessage + "***** Test ID : 34 - Received Date error message is Empty *****";
        }
 		      
        strEtdDate = objAdjunoLIMACargoParamPOM.getDate(-sevenWeekBefore,"dd MMM yyyy");
        
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValueForGridCell(strReceiptFormName,"GridReceipt",0,"ReceivedDate",strEtdDate),true,"***** Test ID : 35 - Received Date is Mandatory *****");
 		String strErrorMessageReceivedDate3 = objAdjunoLIMACargoParamPOM.getValidationMessageGridElement(strReceiptFormName,"GridReceipt",0,"ReceivedDate");
 		if(!strErrorMessageReceivedDate3.equals("")){
  	       
 			boolean bFlag3 = strErrorMessageReceivedDate3.equalsIgnoreCase("> Received Date must be within 6 weeks of ETD");
 			objSoftAssert.assertEquals(bFlag3, true,"***** Test ID : 35 - Received Date must be within 6 weeks of ETD *****");
             
        }else{
        	strMessage =strMessage + "***** Test ID : 35 - Received Date error message is Empty *****";
        }
 		
        
        strEtdDate = objAdjunoLIMACargoParamPOM.getDate(-sixWeekBefore,"dd MMM yyyy");
        
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValueForGridCell(strReceiptFormName,"GridReceipt",0,"ReceivedDate",strEtdDate),true,"***** Test ID : 36 - Received Date is Mandatory *****");
 		String strErrorMessageReceivedDate4 = objAdjunoLIMACargoParamPOM.getValidationMessageGridElement(strReceiptFormName,"GridReceipt",0,"ReceivedDate");
 		if(strErrorMessageReceivedDate4.equals("")){
   	       
 		//boolean bFlag4 = strErrorMessageReceivedDate4.equalsIgnoreCase("x-grid3-cell-inner x-grid3-col-ReceivedDate");
 		//objSoftAssert.assertEquals(bFlag4, true,"The Mandatory field error message is not dispalyed for received date");
             
        }else{
        	strMessage =strMessage + "***** Test ID : 35 - Received Date error message is displayed ***** : "+strErrorMessageReceivedDate4;
        }
 		
 		objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
 	    objSoftAssert.assertAll();
 	}
 	
 	
 	//Click on complete chev
 	@Test(priority=80)
 	public void test_37_CompeleteChev()
 	{
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);

 	   String strMessage="";
 		
 	   for(int i=0; i <= receiptDetails.getLength()-1; i++)
       {
       	if (receiptDetails.item(i).getNodeType() == Node.ELEMENT_NODE)
       		{
       			Element el = (Element) receiptDetails.item(i);
				strRecCtns = el.getElementsByTagName("Rec_Ctns").item(0).getTextContent();
				
				strRecCBM = el.getElementsByTagName("Rec_CBM").item(0).getTextContent();
			}	
       }

 	   if(!isNullOrBlank(strRecCtns))
	   {
    	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strRecCtns,strReceiptFormName,"GridSKUs"),true,"Rec Ctns number is not set");	    
    	}else{
    	    strMessage = strMessage + "Rec Ctns is Empty";
	   }
 		
 		
 	   if(!isNullOrBlank(strRecCBM))
	   {
 		 	objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strRecCBM,strReceiptFormName,"GridSKUs"),true,"Rec CBM number is not set");	    
 	   }else{
 	    	strMessage = strMessage + "Rec CBM is Empty";
	   }
 	 
 	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getClickComplete()),true,"***** Test ID : 37 - Complete Chevron didn't click *****");
      wait(5000);
	  
  	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getupdateMessage()), true,"***** Test ID : 37 - Update Message not found *****");

      String updatedMessage= objAdjunoLIMACargoParamPOM.getupdateMessage().getText();
      System.out.println("updatedMessage:"+updatedMessage);
      //objSoftAssert.assertEquals(updatedMessage.equalsIgnoreCase("1 Product has been updated."),true,"Update PO is failed");
      
      objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
 	  objSoftAssert.assertAll();
 	}

 	
 	//Getting the track reference
 	@Test (priority=85)
 	public void test_38_GetTrackRefNo(){
 		
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        objAdjunoLIMACargoParamPOM.getBtnViewDetail().click();
        wait(3000);
        String strTrackDetails = objAdjunoLIMACargoParamPOM.gettrackReference().getText();
        
        String[] vals = strTrackDetails.split("Track Reference ");
        
        vals[1] = vals[1].replace("[", " ").replace("]", " ");
        String valss[] = vals[1].split(" ",12);
        
        strTrackNO = valss[1];
        System.out.println("tRACK NUMBER:"+strTrackNO);
        wait(2000);
    }
 	
 	
 	//Track --> Edit
 	@Test(priority=90)
	public void test_39_Tracktab()
 	{
 		SoftAssert objSoftAssert = new SoftAssert();
 		objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
 	
 		objAdjunoLIMACargoParamPOM.callMouseHover(strReceiptFormName,objAdjunoLIMACargoParamPOM.getLnkTrack(),objAdjunoLIMACargoParamPOM.getLnkTrackEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getFldRefNo()), true,"***** Test ID : 39 - Reference field is not exist *****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValueForWebElement(objAdjunoLIMACargoParamPOM.getFldRefNo(),strTrackNO), true,"***** Test ID : 39 - Reference field value is not set *****");
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButtonUsingWebElement(objAdjunoLIMACargoParamPOM.getBtnApply()), true,"***** Test ID : 39 - Apply button is not clicked on track edit page *****");
            
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getTxtShipmentTrack()), true, "***** Test ID : 39 - Shipment track page is not loaded *****");
        
        objSoftAssert.assertAll();
 	}
 	
 	
 	//Shipment track should be less then 275 
 	@Test(priority = 97)
    public void test_40_CheckForShipmentOrderDisplay() throws ParseException{        
        SoftAssert objSoftAssert = new SoftAssert();
        String strMessage ="";
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getTxtShipmentTrack()), true, "***** Test ID : 40 - Shipment track page is not loaded *****");
                        
        long min = objAdjunoLIMACargoParamPOM.getTrackValue(strLIMAUserName);
        if(min<=355){
                    
        }else{
           strMessage = "Not a todays date";
        }
                
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
        objSoftAssert.assertAll();
    }

 	
 	//Find --> PO status
 	@Test(priority = 100)
    public void test_42_CargoReceiptEvent(){        
       SoftAssert objSoftAssert = new SoftAssert();
        
       objAdjunoLIMACargoParamPOM.callMouseHover(strReceiptFormName, objAdjunoLIMACargoParamPOM.getLnkFind(), objAdjunoLIMACargoParamPOM.getLnkPOStatus());
          
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.verifyPageIsLoaded(strPOStatusFormName), true, "***** Test ID : 40 - PO Status Page is not displayed *****");
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clearInputField(strPOStatusFormName, "Param_16PO"),  true, "***** Test ID : 40 - PO Number is not cleared");
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPoNumber, strPOStatusFormName, "Param_16PO"), true, "***** Test ID : 40 - PO Number is not set in PO Status page *****");
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButtonUsingWebElement(objAdjunoLIMACargoParamPOM.getBtnRun()), true, "***** Test ID : 40 - Run button is not clicked *****");
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getPoStatusReport()), true, "***** Test ID : 40 - PO Status report page is not loaded *****");
       
       objSoftAssert.assertAll();
    }
 
 	
 	//Status Awaiting Authorisation
 	@Test(priority = 105)
    public void test_43_CheckStatusTrackRef() {        
       SoftAssert objSoftAssert = new SoftAssert();
       String strMessage ="";
        
       String strStatus = objAdjunoLIMACargoParamPOM.getWebElementValue(objAdjunoLIMACargoParamPOM.getPoStatus_Status());
       System.out.println("status awaitng aut :"+objAdjunoLIMACargoParamPOM.getPoStatus_Status().getText());
       objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Awaiting Authorisation"), true, " ***** Test ID : 43 - Product Status is  not  'Awaiting Authorisation'  *****");    
       
       
   		if(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getAwaitingAutho())){
   			
   		 String strhref = objAdjunoLIMACargoParamPOM.getAwaitingAutho().getAttribute("href");
	         if(!isNullOrBlank(strhref))
	         {
	         
	         }
	         else
	         {
	             strMessage = strMessage + "***** Test ID : 43 - Awaiting Authorisation is not  Hyperlink  *****";
	         }
         
   		}else{
   			strMessage = strMessage  + "***** Test ID : 43 - Awaiting Authorisation Hyperlink is not Present  *****";
   		}
   		
      
      
     		  
   	   objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getAwaitingAutho()), true,"***** Test ID : 43 - Awaiting Authorisation Hyperlink is not clicked  *****");
     
   	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
   	   objSoftAssert.assertAll();    	
 	}
 	
 	
 	//Check the data in Awaiting Authorisation link
 /*	@Test(priority = 108)
    public void test_44_CheckPoStatus(){        
        SoftAssert objSoftAssert = new SoftAssert();
        
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        
        ProductOrderData poStatus = objAdjunoLIMACargoParamPOM.getPoStatus();
        
        objSoftAssert.assertEquals(product.getBkdCartons().equalsIgnoreCase(poStatus.getBkdCartons()),true,"***** Test ID : 44 - Carton data does not match  *****");
    	objSoftAssert.assertEquals(product.getBkdCdm().equalsIgnoreCase(poStatus.getBkdCdm()),true,"***** Test ID : 44 - BKDCDM data does not match  *****");
     	objSoftAssert.assertEquals(product.getPackType().equalsIgnoreCase(poStatus.getPackType()),true,"***** Test ID : 44 - Pack type data does not match  *****");
     	objSoftAssert.assertEquals(product.getBkdPcs().equalsIgnoreCase(poStatus.getBkdPcs()),true,"***** Test ID : 44 - BkdPcs data does not match  *****");
     	
     	objSoftAssert.assertAll();    	
 	}
*/
 	
 	
 	//BKD History
 	@Test(priority = 109, dependsOnMethods="test_43_CheckStatusTrackRef")
    public void test_46_CheckBkdHistory() {        
       
 		SoftAssert objSoftAssert = new SoftAssert();
 		String strMessage ="";
        
   //    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getBkdHistoryPo()), true,"  ***** Test ID : 46 - Bkd History Hyperlink is not working *****");
   		
       String strhref = objAdjunoLIMACargoParamPOM.getBkdHistoryPo().getAttribute("href");
       if(!isNullOrBlank(strhref))
       {
       
       }
       else
       {
           strMessage = strMessage + "***** Test ID : 46 - BKD History is not a hyperLink *****";
       }
       
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
     		  
   	   objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getBkdHistoryPo()), true," ***** Test ID : 46 - Bkd History Hyperlink is not Clicked *****");
       wait(4000);
   	  
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getStatusPoBooked()),true, "***** Test ID : 46 - status booked not found *****");
        
       objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.getWebElementValue(objAdjunoLIMACargoParamPOM.getStatusPoBooked()).equalsIgnoreCase("Booked"),true,"***** Test ID : 46 - Staus is not booked *****");
       
   	  objSoftAssert.assertAll();    
  	}

 
 	//Adding the same PO number and checking with Awaiting status
 	@Test(priority = 110)
    public void test_47_VerifyCompletedPOStatus()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
        
        String strMessage="";
        objAdjunoLIMACargoParamPOM.callMouseHover(strCargoReceiptFormName,objAdjunoLIMACargoParamPOM.getLnkTools(), objAdjunoLIMACargoParamPOM.getLnkCargoReceipt());        
      
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.verifyPageIsLoaded(strCargoReceiptFormName), true, "***** Test ID : 47 - Cargo Receipt page is not displayed *****");
        
        clearFeilds();            

    	//Setting the field value Status Awaiting:setFieldValue
	    if(!isNullOrBlank(strStatusAwaiting))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusAwaiting,strCargoReceiptFormName,"PARAM_Status"),true,"***** Test ID : 47 - Awaiting Status is not set *****");    	   
	    }else{
	    	strMessage = strMessage + "***** Test ID : 47 - Awaiting Status is  Empty *****";
	    }

        if(!isNullOrBlank(strPoNumber))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPoNumber,strCargoReceiptFormName,"PARAM_PO"),true,"***** Test ID : 47 - PO number is not set *****");    	   
	    }else{
	    	strMessage = strMessage + " ***** Test ID : 47 - PO number is Empty *****";
	    }
    	 
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickChevorn(objAdjunoLIMACargoParamPOM.getChvSelect()), true,"***** Test ID : 47 - Select Chevron is not clicked *****");
        objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.isElementPresent(objAdjunoLIMACargoParamPOM.getNoItemsFound()), true,"***** Test ID : 47 - No item found is not working *****");
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);  
        objSoftAssert.assertAll();
    } 
    
    
 	
 	//Receipted status check
    @Test(priority = 115)
    public void test_48_VerifyCargoReceiptPOStatus()
    {
       SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMACargoParamPOM = new AdjunoLIMACargoReceiptPOM(driver);
             
       String strMessage ="";
	       
       clearFeilds();	    

     	//Setting the field value Status Receipted:setFieldValue
 	    if(!isNullOrBlank(strStatusReceipted))
 	    {
 	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strStatusReceipted,strCargoReceiptFormName,"PARAM_Status"),true,"***** Test ID : 48 - Receipt status is not set *****");    	   
 	    }else{
 	    	strMessage = strMessage + " ***** Test ID : 48 - Receiptedstatus is Empty *****";
 	    }
 	   
 	   if(!isNullOrBlank(strPoNumber))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.setFieldValue(strPoNumber,strCargoReceiptFormName,"PARAM_PO"),true,"***** Test ID : 48 - PO number is not set *****");    	   
	    }else{
	    	strMessage = strMessage + "***** Test ID : 47 - PO number is Empty *****";
	    }
   	
 	    //status Receipted
 	    objSoftAssert.assertEquals(objAdjunoLIMACargoParamPOM.clickButton(strCargoReceiptFormName, "FORK_CargoReceipt_RefineSearch"),true,"***** Test ID : 48 - Not able to click Refine Search button *****");
 	    wait(5000);
 	    

  	    List<ProductOrderData> list = new ArrayList<ProductOrderData>();  
  	    list = objAdjunoLIMACargoParamPOM.getStaus();
  	    
  	    strMessage = objAdjunoLIMACargoParamPOM.verifyStaus(strStatusReceipted,list);
  	           
  	    objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
        objSoftAssert.assertAll();
   } 
    
    
   @AfterTest
   public void closeBrowser()
   {
        driver.close();
   }

}

