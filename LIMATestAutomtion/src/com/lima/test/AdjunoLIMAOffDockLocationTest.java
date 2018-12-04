package com.lima.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAOffDockLocationPOM;

public class AdjunoLIMAOffDockLocationTest {
	WebDriver driver;
	long nPage_Timeout = 0;
    String strTestURL;
    boolean searchResult;
    
    String strUserName;
    String strPassword;
    
    int position;
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAOffDockLocationPOM objAdjunoLIMAOffDockLocationPOM;
	String strStatusAwaiting;
	String strOffDockLocationSearchFormName;
	String strOffDockLocationSearchFormName2;
	String strOffDockLocationPageTitle;
	String strCatalogFormName;
	String strCatalogPageTitle;
	String strLocode;
	String strVessel;
	String strOffDockLocation;
	String strOffDockLocationVal2;
	String strInvalidContainer;
	String strValidContainer = "";
	String strUploadPageFormName;
	String strOffDockLocationVal;
	String strOffDockReason;
	String strOffDockComments;
	String strTrackN0;
	String strOffDockChevronPageFormName;
	String strNextDay;
	String strNextDay2;
	String strContainerValue;
	
	
	ArrayList<String> containerList;
	ArrayList<String> catalogDestinationList;
	ArrayList<String> catalogVesselList;
	ArrayList<String> catalogOffDockLocationList;
	NodeList status;
	NodeList bookingUploadedCaptions;
	NodeList bookingUploadedCaptions2;
	String strArrivalDate;
	String strStatusDepated;
	String strDestinationPort;
	String strETAFrom;
	String strETATo;
	String strVesselValue;
	String strForwarderRef;
	
	
	
	public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
    public void wait(int ms) {
 	   try 
 	   {
 			Thread.sleep(ms);
 			
 	   } 
 	   catch (InterruptedException e) 
 	   {
 			e.printStackTrace();
 	   }
 	}
    
    @BeforeClass
    public void setup()
    {	    	
    	objAdjunoLIMALibrary = new AdjunoLIMALibrary();
    	
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());

            XPath xPath = XPathFactory.newInstance().newXPath();
           
            Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout", dDoc, XPathConstants.NODE);
            nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());
              
            Node testLIMAURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	          
  	        Node offDockLocationPageTitle = (Node) xPath.evaluate("/config/LIMA/Off_Dock_Location_PageTitle", dDoc, XPathConstants.NODE);
  	        strOffDockLocationPageTitle = offDockLocationPageTitle.getTextContent();
	        
	        Node offDockLocationSearchFormName = (Node) xPath.evaluate("/config/LIMA/Off_Dock_Location_FormName", dDoc, XPathConstants.NODE);
	        strOffDockLocationSearchFormName = offDockLocationSearchFormName.getTextContent();
	        
	        Node offDockLocationSearchFormName2 = (Node) xPath.evaluate("/config/LIMA/Off_Dock_Location_FormName2", dDoc, XPathConstants.NODE);
	        strOffDockLocationSearchFormName2 = offDockLocationSearchFormName2.getTextContent();

	        Node uploadPageFormName = (Node) xPath.evaluate("/config/LIMA/Off_Dock_Upload_Page_FormName", dDoc, XPathConstants.NODE);
	        strUploadPageFormName = uploadPageFormName.getTextContent();
	        
	        Node offDockChevronPageFormName = (Node) xPath.evaluate("/config/LIMA/Off_Dock_Location_Chevron_FormName", dDoc, XPathConstants.NODE);
	        strOffDockChevronPageFormName = offDockChevronPageFormName.getTextContent();
	        
	        Node catalogPageTitle = (Node) xPath.evaluate("/config/LIMA/Catalog_Page_Title", dDoc, XPathConstants.NODE);
	        strCatalogPageTitle = catalogPageTitle.getTextContent();
	        
	        Node catalogFormName = (Node) xPath.evaluate("/config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	        strCatalogFormName = catalogFormName.getTextContent();
	        
	               
            driver = new FirefoxDriver();
            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

            driver.get(strTestURL);

            objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
        	
            objAdjunoLIMALoginPOM.setUserName(strUserName);
            objAdjunoLIMALoginPOM.setPassword(strPassword);
        	objAdjunoLIMALoginPOM.clickLogon();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getOffDockLocationXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            Node invalidContainer = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Invalid_Container", dDoc1, XPathConstants.NODE);
            strInvalidContainer = invalidContainer.getTextContent();
            
            Node statusAwiting = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strStatusAwaiting = statusAwiting.getTextContent();
            
            Node statusDeparted = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Off_Dock_Departed", dDoc1, XPathConstants.NODE);
            strStatusDepated = statusDeparted.getTextContent();
                     
            Node locode = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Catalog_Locode", dDoc1, XPathConstants.NODE);
            strLocode = locode.getTextContent();
            
            Node vessel = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Catalog_Vessel", dDoc1, XPathConstants.NODE);
            strVessel = vessel.getTextContent();
            
            Node vesselValu = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Vessel_value", dDoc1, XPathConstants.NODE);
            strVesselValue = vesselValu.getTextContent();
            
            Node forwarderRef = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/ForwarderRef", dDoc1, XPathConstants.NODE);
            strForwarderRef = forwarderRef.getTextContent();
            
            
            Node destiPort = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Destination_port", dDoc1, XPathConstants.NODE);
            strDestinationPort = destiPort.getTextContent();
            
            Node offDockLocation = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Catalog_OffDockLocation", dDoc1, XPathConstants.NODE);
            strOffDockLocation = offDockLocation.getTextContent();
            
            Node offDockLocationVal = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/OffDockLocation", dDoc1, XPathConstants.NODE);
            strOffDockLocationVal = offDockLocationVal.getTextContent();
            
            Node offDockLocationVal2 = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/OffDockLocation2", dDoc1, XPathConstants.NODE);
            strOffDockLocationVal2 = offDockLocationVal2.getTextContent();
            
            Node OffDockReason = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Reason", dDoc1, XPathConstants.NODE);
            strOffDockReason = OffDockReason.getTextContent();
            
            Node OffDockComments = (Node) xPath1.evaluate("/Off_Dock_Location/Search_Param/Comments", dDoc1, XPathConstants.NODE);
            strOffDockComments = OffDockComments.getTextContent();
            
            status = (NodeList) xPath1.evaluate("/Off_Dock_Location/Status", dDoc1, XPathConstants.NODESET);
            bookingUploadedCaptions = (NodeList) xPath1.evaluate("/Off_Dock_Location/Grid_Caption", dDoc1, XPathConstants.NODESET);
            bookingUploadedCaptions2 = (NodeList) xPath1.evaluate("/Off_Dock_Location/Grid_Caption2", dDoc1, XPathConstants.NODESET);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
 /*   @Test(priority=1)
    public void test_accessCatalog()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	 String strTitle = objAdjunoLIMAOffDockLocationPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTools(), objAdjunoLIMAOffDockLocationPOM.getLnkCatalog());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strCatalogPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Title of the Catelog page is wrong *****");
         objSoftAssert.assertAll();
    }
    
 */   
   
    /*@Test(priority=2)
    public void getCatalogData(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	String strMessage = "";
    	catalogDestinationList = new ArrayList<String>();
    	catalogVesselList = new ArrayList<String>();
    	catalogOffDockLocationList = new ArrayList<String>();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getLnkLightHouse()), true,"***** Lighthouse Link is not clicked in Catalog page *****");
    	wait(3000);
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strLocode)){
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strLocode, strCatalogFormName, "Param_Type"),true,"***** Type Field value is not entered as LOCODE*****");
		 
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getCatalogbtnApply()),true,"***** Apply Button is not Clicked *****");
			 wait(5000);
			 
			 int nCount = objAdjunoLIMAOffDockLocationPOM.valCount();
			 catalogDestinationList = objAdjunoLIMAOffDockLocationPOM.getCatalogTableData(nCount);
			 
			 wait(5000);
    	
    	}else{
			 strMessage=strMessage+"Locode Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strVessel)){
    		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strVessel, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Vessel*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getCatalogbtnApply()),true,"*****  Apply button is not clicked *****");
			 wait(5000);
			             
			 int nCount2 = objAdjunoLIMAOffDockLocationPOM.valCount();
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnFirst()),true,"*****  Apply button is not clicked *****");
			 wait(3000);
			 catalogVesselList = objAdjunoLIMAOffDockLocationPOM.getCatalogTableData1(nCount2);  
			 
    	}else{
			 strMessage=strMessage+"vessel Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strOffDockLocation)){
    		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockLocation, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Off Dock Location*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getCatalogbtnApply()),true,"***** Apply button is not clicked *****");
			 wait(5000);
			             
		
			 catalogOffDockLocationList = objAdjunoLIMAOffDockLocationPOM.getCatalogData();  
			 
    	}else{
			 strMessage=strMessage+"off dock Location Value is Empty in Xml";
		}
		
    	
    	objSoftAssert.assertAll();
    }
 
 */   
    public void clearCatalogFields(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strCatalogFormName, "Param_Name"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strCatalogFormName, "Param_Description"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertAll();
    }	
        
    public void clearFields(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_DestinationPort"),true,"***** Could not clear Destination Port field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_ETAFrom"),true,"***** Could not clear ETA From field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_ETATo"),true,"***** Could not clear ETA TO field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** Could not clear Status field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_WFStatus"),true,"***** Could not clear Work Flow Status field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_Vessel"),true,"***** Could not clear Vessel field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_Container"),true,"***** Could not clear Container field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_ForwarderReference"),true,"***** Could not clear Forwarder Reference field value in Search Chevron in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clearInputField(strOffDockLocationSearchFormName, "PARAM_OffDockLocation"),true,"");
    	
    	objSoftAssert.assertAll();
    	
    	
    }
    
    
    @Test(priority=5)
    public void test_1_accessOffDockLocation()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	 String strTitle = objAdjunoLIMAOffDockLocationPOM.callMouseHover(strOffDockLocationSearchFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTools(), objAdjunoLIMAOffDockLocationPOM.getLnkOffDockLocation());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strOffDockLocationPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:1-Title of the Off Dock Location page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    
    @Test(priority=7)
	public void test_2_VerifyExistanceOfChevorns()
   	{    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getChvSearch()), true,"***** Test ID : 2 - In Off Dock Location Tool \"Search Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"***** Test ID : 2 - In Off Dock Location Tool \"Select Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getChvOffDock()), true,"***** Test ID : 2 - In Off Dock Location Tool \"Off Dock Location Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getChvComplete1()), true,"***** Test ID : 2 - In Off Dock Location Tool \"Complete Chevorn\" is not found *****");
     
        objSoftAssert.assertAll();
   	}
    
    
    @Test(priority=8)
	public void test_3_VerifyExistanceOfFields()
	{
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_DestinationPort"),true,"***** Test ID:3- Destination Port field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_ETAFrom"),true,"***** Test ID:3- ETA From field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_ETATo"),true,"***** Test ID:3- ETA TO field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** Test ID:3- Status field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_WFStatus"),true,"***** Test ID:3- Work flow status field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_Vessel"),true,"***** Test ID:3- Vessel field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_Container"),true,"***** Test ID:3- Container field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_ForwarderReference"),true,"***** Test ID:3- Forwarder Reference field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "PARAM_OffDockLocation"),true,"***** Test ID:3- Off Dock Location field is not found in \"Search Chevorn\" in Off Dock Location Tool *****");
    	
	 	objSoftAssert.assertAll();
	}
	   
    
    @Test(priority=9)
    public void test_4_VerifyStatusDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> statusList = new ArrayList<String>();
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		statusList = objAdjunoLIMAOffDockLocationPOM.getDropdownValues(strOffDockLocationSearchFormName, "PARAM_Status");
		
		if(statusList.size()>0){
			strMessage= strMessage + objAdjunoLIMAOffDockLocationPOM.verifyStatusDropDown(status, statusList,3);
				
		}else{
			strMessage=strMessage+"status Drop Down Won't have any Value";
		}
		
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 5"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
/*    @Test(priority=10)
    public void test_5_VerifyDestinationDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> destinationList = new ArrayList<String>();
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		destinationList = objAdjunoLIMAOffDockLocationPOM.getDropdownValues(strOffDockLocationSearchFormName, "PARAM_DestinationPort");
		
		if(destinationList.size()>0 && catalogDestinationList.size()>0){
			strMessage= strMessage + objAdjunoLIMAOffDockLocationPOM.verifyCatalogData(catalogDestinationList, destinationList);
		}else{
			strMessage=strMessage+"Destination Drop Down Won't have any Value";
		}
		
		
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 5"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
    @Test(priority=16)
    public void test_7_VerifyVesselDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> vesselList = new ArrayList<String>();
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		vesselList = objAdjunoLIMAOffDockLocationPOM.getDropdownValues(strOffDockLocationSearchFormName, "PARAM_Vessel");
		
		if(vesselList.size()>0 && catalogVesselList.size()>0){
			strMessage= strMessage + objAdjunoLIMAOffDockLocationPOM.verifyCatalogData(catalogVesselList, vesselList);
		}else{
			strMessage=strMessage+"Vessel Drop Down Won't have any Value";
		}
		
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
    @Test(priority=20)
    public void test_6_VerifyOffDockLocationDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> offDockLocationList = new ArrayList<String>();
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		offDockLocationList = objAdjunoLIMAOffDockLocationPOM.getDropdownValues(strOffDockLocationSearchFormName, "PARAM_OffDockLocation");
		
		if(offDockLocationList.size()>0){
				
		}else{
			strMessage=strMessage+"Off dock Location Drop Down Won't have any Value";
		}
		
		strMessage= strMessage + objAdjunoLIMAOffDockLocationPOM.verifyCatalogData(catalogOffDockLocationList, offDockLocationList);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
*/    
    @Test(priority=25)
    public void test_8_VerifyInvalidContainer()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		clearFields();
		
		if(!isNullOrBlank(strInvalidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strInvalidContainer, strOffDockLocationSearchFormName, "PARAM_Container"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Invalid container Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"***** Test id:8 - Select Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
		
		if(searchResult==true){
			
		} else {
			strMessage = strMessage + "No items were found. text is not shown for invalid container value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8-"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
    
    @Test(priority=30)
    public void getValidContainerList()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		containerList =  new ArrayList<String>();	
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStatusAwaiting)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusAwaiting, strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"*****  Select Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMAOffDockLocationPOM.getLstContainers().size()-1; i++) {
				
				containerList.add(objAdjunoLIMAOffDockLocationPOM.getLstContainers().get(i).getText());
			}
				
			 
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
	//  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** "+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
    

   
   @Test(priority=46)
   public void test_12_verifyDestinationPort()
   {
   		SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
   	
   		String strMessage ="";
   		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000); 
   		clearFields();

   	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strDestinationPort))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strDestinationPort,strOffDockLocationSearchFormName,"PARAM_DestinationPort"),true," *****  Test ID : 12 - Destination Port is not set Value in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 12 - Destination Port is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()),true,"***** Test ID : 12 - can't click on Select chev in Search page of off Dock Location *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 12 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAOffDockLocationPOM.getDestionationPortM();
	    strMessage = objAdjunoLIMAOffDockLocationPOM.verifyDestinationPort(strDestinationPort,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:12- " + strMessage +" ***** ");
	    objSoftAssert.assertAll();
       
   }
   
   

   @Test(priority=47) 
   public void test_13_verifyEtaFrom() throws ParseException
   {
   		SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
   	
   		String strMessage ="";
   	
   		strETAFrom = objAdjunoLIMAOffDockLocationPOM.getDate(-35,"dd MMM yyyy");
   		Date etaFromDate = new SimpleDateFormat("dd MMM yyyy").parse(strETAFrom);
   		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);   
   		clearFields();
		
   	//Setting the field value Invalid Container:setFieldValue
	    if(!isNullOrBlank(strETAFrom))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strETAFrom,strOffDockLocationSearchFormName,"PARAM_ETAFrom"),true," *****  Test ID : 13 -  ETA from date value is not set in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 13 - ETA From Value is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()),true,"***** Test ID : 13 - can't click on Select chev in Search page of off Dock Location *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 12 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<Date> list = new ArrayList<Date>();  
	    list = objAdjunoLIMAOffDockLocationPOM.getArrivedDate();
	    strMessage = objAdjunoLIMAOffDockLocationPOM.verifyArrivedDate(etaFromDate,list);
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:13- " + strMessage +" ***** ");
	
   	objSoftAssert.assertAll();
   }
   
   
   @Test(priority=48) 
   public void test_14_verifyEtaTo() throws ParseException
   {
	   	SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
   	
   		String strMessage ="";
   	
   		strETATo= objAdjunoLIMAOffDockLocationPOM.getDate(0,"dd MMM yyyy");
   		Date etaToDate = new SimpleDateFormat("dd MMM yyyy").parse(strETATo);
   		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);   
   		clearFields();
		
   	//Setting the field value Invalid Container:setFieldValue
	    if(!isNullOrBlank(strETATo))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strETATo,strOffDockLocationSearchFormName,"PARAM_ETATo"),true," *****  Test ID : 14 -  ETA From date value is not set in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 14 - ETA From Value is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()),true,"***** Test ID :14 - can't click on Select chev in Search page of off Dock Location *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 12 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<Date> list = new ArrayList<Date>();  
	    list = objAdjunoLIMAOffDockLocationPOM.getArrivedDate();
	    strMessage = objAdjunoLIMAOffDockLocationPOM.verifyArrivedDate2(etaToDate,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:14- " + strMessage +" ***** ");
   	objSoftAssert.assertAll();
   }
   
   
   @Test(priority=49)
   public void test_15_verifyStatus()
   {
   		SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
   	
   		String strMessage ="";
   		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);   
   		clearFields();

   	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strStatusAwaiting))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusAwaiting,strOffDockLocationSearchFormName,"PARAM_Status"),true," *****  Test ID : 15 - Status is not set Value in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 15 - Status is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()),true,"***** Test ID : 15 - can't click on Select chev in Search page of off Dock Location *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 12 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAOffDockLocationPOM.getStatusAwait();
	    strMessage = objAdjunoLIMAOffDockLocationPOM.verifyStatusAwaiting(strStatusAwaiting,list);
   	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:15- " + strMessage +" ***** ");
   	objSoftAssert.assertAll();
   }
   
   @Test(priority=50)
   public void test_16_verifyVessel()
   {
   		SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
   	
   		String strMessage ="";
   		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);   
   		clearFields();

   	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strVesselValue))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strVesselValue,strOffDockLocationSearchFormName,"PARAM_Vessel"),true," *****  Test ID : 16 - Vessel Value is not set in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 16 - vessel is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()),true,"***** Test ID : 16 - can't click on Select chev in Search page of off Dock Location *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAOffDockLocationPOM.getVesselValues();
	    strMessage = objAdjunoLIMAOffDockLocationPOM.verifyVesselvalue(strVesselValue,list);
   	
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:16- " + strMessage +" ***** ");
   	objSoftAssert.assertAll();
   }

   @Test(priority=51)
   public void test_19_verifyForwardRef()
   {
   		SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
   	
   		String strMessage ="";
   		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);   
   		clearFields();
   		
   	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strForwarderRef))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strForwarderRef,strOffDockLocationSearchFormName,"PARAM_ForwarderReference"),true," *****  Test ID : 19 - ForwarderReference Value is not set in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 19 - ForwarderRef is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()),true,"***** Test ID : 19 - can't click on Select chev in Search page of off Dock Location *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAOffDockLocationPOM.getForwardRefValues();
	    strMessage = objAdjunoLIMAOffDockLocationPOM.verifyForwardRefvalue(strForwarderRef,list);
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:19- " + strMessage +" ***** ");
   	
   	objSoftAssert.assertAll();
   }
   
   
   @Test(priority=52)
   public void test_9_VerifyValidContainerSearch()
   {
   	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSearch1()), true,"***** Search Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStatusAwaiting)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusAwaiting, strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
		}
	
		
		for (int i = 0; i <=containerList.size()-1; i++) {
			if(!containerList.get(i).isEmpty()){
				strValidContainer = containerList.get(i);
				position = i;
				break;
			}
		}
		
		if(!isNullOrBlank(strValidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strValidContainer, strOffDockLocationSearchFormName, "PARAM_Container"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty in List";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"*****  Select Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
	
		if(searchResult==false){
		
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		strMessage = strMessage + objAdjunoLIMAOffDockLocationPOM.verifyContainerAndStaus(strValidContainer,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:9- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
   }    
  
  
  @Test(priority=53)
   public void test_10_verifyFieldsInSelectChevron(){
	   SoftAssert objSoftAssert = new SoftAssert();
		
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "FORK_Off_Dock_Storage_RefineSearch"), true,"***** TestId:10- Refine Search button is not shown in Select chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "UploadDeliveryBookingTemplate"), true,"***** TestId:10- Upload button is not shown in Select chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockLocationSearchFormName, "OpenReport"), true,"***** TestId:10- Download Off Dock Location Template Link is not shown in Select chevron in Off Dock Location tool *****");
		
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThVessel()), true,"***** Test ID:10- Vessel Colunm in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThDestinationPOrt()), true,"***** Test ID:10- Destination Port Colunm in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThArrivalDate()), true,"***** Test ID:10- Arrival Date Colunm in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThContainer()), true,"***** Test ID:10- Container Colunm in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThOffDockArrivalDate()), true,"***** Test ID:10-  Off Dock Arrival Date in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThOffDockLocation()), true,"***** Test ID:10- Off Dock Location Colunm in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThForwarderRef()), true,"***** Test ID:10- Forwarder Ref Colunm in not shown in Select Chevron in Off Dock Location tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getThStatus()), true,"***** Test ID:10- Status Colunm in not shown in Select Chevron in Off Dock Location tool *****");
		
	   objSoftAssert.assertAll();	
		
  }
  
  
  @Test(priority=54)
  public void test_11_verifyValidationMessage(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage ="";
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvOffDock2()), true,"***** Test id:11 - Off Dock Location Chevron is not clicked in Off Dock Location Tool *****");
	   wait(4000);

	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getErrorMessage().getText(), "You must make selection(s) before progressing","***** Test ID:11- Error meaasge for without selecting an container data and click on Off Dock Location error mesasge mismatch Expected:'You must make selection(s) before progressing'"+ " but  Found:"+objAdjunoLIMAOffDockLocationPOM.getErrorMessage().getText());
		
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "*****Test ID:11 "+ strMessage +"*****");
	   objSoftAssert.assertAll();	
		
  }
   
   @Test(priority=55)
   public void test_21_downloadOffDocktemplate() throws IOException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
	   
	   File downloadedFile = new File("C:\\Users\\Anand\\Downloads\\OffDockStorageTemplate.xlsx");
	   if(downloadedFile.exists()){
		   downloadedFile.delete();
		   System.out.println("deleted");
	   }
		
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strOffDockLocationSearchFormName, "OpenReport"), true,"***** Test ID:21- Download Off Dock Location Template Link is not cliked in select chevron in Off Dock Location Tool *****");
	   wait(5000);
		
	   Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\DownloadOffDockLocationTemplate.exe");
	   wait(6000);
		
	   boolean exists = downloadedFile.exists();
	   objSoftAssert.assertEquals(exists, true,"***** Test ID:21- Off Dock Location Template File is not downloaded *****");
		
	   objSoftAssert.assertAll();
   }
   
   @Test(priority=56)
   public void test_22_verifyUpdateChevron(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = "";
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
	   
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strOffDockLocationSearchFormName, "UploadDeliveryBookingTemplate"),true,"");
	   wait(4000);
	   if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strUploadPageFormName)){
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "ParamOffDockArrivedDate"), true,"***** Test ID:22- Off D  Arrived Date field is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "ParamOffDockDepartedDate"), true,"***** Test ID:22- Off Dock Departed Date field is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "ParamOffDockLocation"), true,"***** Test ID:22- Off Dock Location field is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "ParamReason"), true,"***** Test ID:22- Reason field is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "ParamComments"), true,"***** Test ID:22- Comments field is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "DeliveryBookingUpload"), true,"***** Test ID:22- Browse button is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "UpdateGrid"), true,"***** Test ID:22- Update Button is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strUploadPageFormName, "UploadDeliveryBookingTemplate"), true,"***** Test ID:22- Upload Button is not found in \"Update Chevorn\" in Off Dock Location Tool *****");
		   
	   }else{
		 strMessage = strMessage +" Update Chevron Page is not loaded";  
	   } 
	      
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 22"+ strMessage +"*****");
	   
	   objSoftAssert.assertAll();
	   
   }
   
   @Test(priority=60)
   public void test_23_uploadOffDockTemplate() throws IOException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = "";
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
	   
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strUploadPageFormName, "DeliveryBookingUpload"), true,"***** Test ID:23- Browse button is not clicked in \"Update Chevorn\" in Off Dock Location Tool *****");
	   wait(3000);
	   long nRows = objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strUploadPageFormName,"DeliveryBooking");
	   
	   Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\UploadOffDockLocation.exe");
	   wait(5000);
	   
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnUpload()), true,"***** Test ID:23- upload button is not clicked in \"Update Chevorn\" in Off Dock Location Tool *****");
	   wait(4000);
	  
	   long nRow2 = objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strUploadPageFormName,"DeliveryBooking");
	   
	   if(nRow2>nRows){
		   
	   }else{
		   strMessage = strMessage + " Upload Off Dock Location funtion in working";
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 23-"+ strMessage +"*****");
	   objSoftAssert.assertAll();
   }
   
   
   @Test(priority=65)
   public void test_23_verifyField(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
	   String strMessage="";
	   	
	   ArrayList<String> list = new ArrayList<String>();
	    	
	   list = objAdjunoLIMAOffDockLocationPOM.getCaptionsList(strUploadPageFormName,"DeliveryBooking");
	    
	 //   long count = objAdjunoLIMAOffDockLocationPOM.getNoOfColumnsGrid(strUploadPageFormName,"DeliveryBooking");
	   strMessage = objAdjunoLIMAOffDockLocationPOM.verifyCaptionsONGrid(list,bookingUploadedCaptions,8);
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 23"+ strMessage +"*****");
	   objSoftAssert.assertAll();
   }
   
   @Test(priority=70, dependsOnMethods="test_23_uploadOffDockTemplate")
   public void test_25_VerifyMandatory(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
	   String strMessage="";
	   //OffDockLocation
	   ArrayList<String> listError = objAdjunoLIMAOffDockLocationPOM.getListOfValidationMessageGridElement(strUploadPageFormName,"DeliveryBooking",0,"OffDockArrivedDate");
	   
	   if(listError.size()<=0){
		   strMessage = strMessage	+ " off Dock Arrived Date Mandatory message is not displayed ";
	   }
	   for (int i = 0; i <= listError.size() - 1; i++) {
		  
		   if (listError.get(i).equalsIgnoreCase("> The Off Dock Arrived Date should be greater than or equal to Arrived Date!")) {
			
		   } else {
			   if (i == listError.size() - 1) {
					strMessage = strMessage	+ " off Dock Arrived Date Mandatory message is not displayed ";
				}
		   }
	   }
		
	  String strErrorOffDockLocation = objAdjunoLIMAOffDockLocationPOM.getValidationMessageGridElement(strUploadPageFormName,"DeliveryBooking",0,"OffDockLocation");
	   
	  
	  if(!strErrorOffDockLocation.equals("")){
	   		boolean bFlag = strErrorOffDockLocation.equalsIgnoreCase("> &#39;Off Dock Location&#39; is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:25 - In Off Dock Location Tool Under Update Chevron \" off Dock Location\" Field is not Mandatory *****");	   			
	   }else{
	   		strMessage = strMessage +"In Off Dock Location Tool Under Update Chevron \" off Dock Location \" Field Mandatory message is not displayed";
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 25-"+ strMessage +"*****");
	   objSoftAssert.assertAll();
   }
   
   
   @Test(priority=75,dependsOnMethods="test_23_uploadOffDockTemplate")
   public void test_24_VerifyGridValue() throws ParseException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
	   String strMessage="";
	   
	   long nRow2 = objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strUploadPageFormName,"DeliveryBooking");
	   
	  
 	   
	 	//   strArrivalDate = objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName, "Grid_Containers", 0, "ArrivedDate");
	 	   
	   strNextDay = objAdjunoLIMAOffDockLocationPOM.getDate(-1, "dd MMM yyyy");
		 	   
	   strNextDay2 = objAdjunoLIMAOffDockLocationPOM.getDate(0, "dd MMM yyyy");
	   
	   
	   
	   if(!isNullOrBlank(strNextDay)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay, strUploadPageFormName, "ParamOffDockArrivedDate"),true,"***** *****");
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay2, strUploadPageFormName, "ParamOffDockDepartedDate"),true,"***** *****");
	   }else{
	   		strMessage=strMessage+"Off Dock Arried Date/Off Dock Departure Date Value is Empty";
	   }
	   
	   if(!isNullOrBlank(strOffDockLocationVal)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockLocationVal, strUploadPageFormName, "ParamOffDockLocation"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Off Dock Location Value is Empty";
	   }
	   
	   if(!isNullOrBlank(strOffDockReason)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockReason, strUploadPageFormName, "ParamReason"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Reason Value is Empty  in XML";
	   }
	   
	   if(!isNullOrBlank(strOffDockComments)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockComments, strUploadPageFormName, "ParamComments"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Comments Value is Empty in XML";
	   }
	   
	   wait(4000);
	   
	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getSelectBox()), true,"***** Test ID:24- Select CheckBox is not clicked in the Update Chevron *****");
	  wait(2000);
	  
	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strUploadPageFormName, "UpdateGrid"),true,"***** Test ID:24- Update buttoin not clicked in Update Chevron *****");
	  wait(3000);
	  
	  //verify entered data
	  for (int i = 0; i <=nRow2-1; i++) {
		  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strUploadPageFormName,"DeliveryBooking",i,"OffDockArrivedDate").contains(strNextDay), true,"***** Test ID:24-  Off Dock Arrival Date is not matching With Entered value *****");
		  
		  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strUploadPageFormName,"DeliveryBooking",i,"DepartedDate").contains(strNextDay2), true,"***** Test ID:24-  Off Dock Departure Date is not matching With Entered value *****");
		  
		  objSoftAssert.assertEquals(strOffDockLocationVal.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strUploadPageFormName,"DeliveryBooking",i,"OffDockLocation")), true,"***** Test ID:24-  Off Dock Location is not matching With Entered value *****");
		  
		  objSoftAssert.assertEquals(strOffDockReason.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strUploadPageFormName,"DeliveryBooking",i,"ReasonCode")), true,"***** Test ID:24- Reason is not matching With Entered value *****");
		  
		  objSoftAssert.assertEquals(strOffDockComments.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strUploadPageFormName,"DeliveryBooking",i,"Comments")), true,"***** Test ID:24- Comments is not matching With Entered value *****");
		  
	  }
	   
	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 25"+ strMessage +"*****");
	  objSoftAssert.assertAll();
   }
   
   
   
   @Test(priority=80)
   public void test_26_deleteRow(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
	   String strMessage="";
	   long nRow1 = objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strUploadPageFormName,"DeliveryBooking");
	   
	   if(nRow1>0){
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnDelete()), true,"***** Test ID;26- Delete Rows Button is not cliked in Update Chevron *****");
		   wait(5000);
		   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnYes()), true,"***** Test ID;26- Yes Button is not cliked in Update Chevron *****");
		   wait(2000);
		   
	   }else{
		   strMessage = strMessage + " No rows are uploaded to deleted";
	   }
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 26"+ strMessage +"*****");
	   objSoftAssert.assertAll();
	  
   }
 
   
   @Test(priority=85, dependsOnMethods="test_26_deleteRow")
   public void test_upload() throws IOException, ParseException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   test_23_uploadOffDockTemplate();
	   wait(4000);
	   test_24_VerifyGridValue();
	   wait(4000);
	   objSoftAssert.assertAll();
   }
   

   @Test(priority=90)
   public void test_27_complete(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvComplete2()), true,"***** Test ID:27- Complete Chevron is not clicked  *****");

	  /* String poDetails = objAdjunoLIMAOffDockLocationPOM.getTrackMessage().getText();
	 //  String[] vals = poDetails.split("Off dock location with reference");
	   System.out.println("message is "+poDetails);
	   String valss[] = vals[1].split(" ",23);
	   strTrackN0 = valss[1];
	   System.out.println("value"+valss[0]);
	   System.out.println("strTrackN0:--"+strTrackN0);*/
	   wait(5000);
	   objSoftAssert.assertAll();
   }
   
    
    
    @Test(priority=95)
    public void test_29_accessOffDockLocation()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	 wait(5000);
    	 String strTitle = objAdjunoLIMAOffDockLocationPOM.callMouseHover(strOffDockLocationSearchFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTools(), objAdjunoLIMAOffDockLocationPOM.getLnkOffDockLocation());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strOffDockLocationPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:29-Title of the Off Dock Location page is wrong*****");
         objSoftAssert.assertAll();
    }

    
    @Test(priority=100)
    public void test_30_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		//containerList.remove(position);
 
 		String strMessage ="";
 		
 	
 		wait(4000);
 		clearFields();
 		
 		if(!isNullOrBlank(strStatusAwaiting)){
 			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusAwaiting, strOffDockLocationSearchFormName, "PARAM_Status"),true,"*****Test ID:30- Awaiting status value is not set in Search chevron*****");
 	 	}else{
 	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
 		}
 		
 		
 		
 		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"*****  Select Chevron is not clicked in Off Dock Location Tool *****");
 		wait(4000);
 		 
 		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMAOffDockLocationPOM.getLstContainers().size()-1; i++) {
				
				if(!objAdjunoLIMAOffDockLocationPOM.getLstContainers().get(i).getText().isEmpty()){
					objAdjunoLIMAOffDockLocationPOM.getLstSelect().get(i).click();
					strContainerValue = objAdjunoLIMAOffDockLocationPOM.getLstContainers().get(i).getText();
					break;
				}
			}
				
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvOffDock2()), true,"***** Test ID:30- Off Dock Location Chevron is not clicked in Off Dock Location Tool *****");
 		wait(4000);
 		
 		 if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strOffDockChevronPageFormName)){
 			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "ArrivedDate1"), true,"***** Test ID:30- Off D  Arrived Date field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "DepartedDate1"), true,"***** Test ID:30- Off Dock Departed Date field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "OffDockLocation1"), true,"***** Test ID:30- Off Dock Location field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "ReasonCode1"), true,"***** Test ID:30- Reason field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "Comments1"), true,"***** Test ID:30- Comments field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "BUTTON_Update"), true,"***** Test ID:30- Update Button is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
 			   
 			   
 		   }else{
 			 strMessage = strMessage +" Off Dock Location Chevron Page is not loaded";  
 		   }
 		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:30 "+ strMessage +" *****");
		objSoftAssert.assertAll();
    	
    }
   
    
    @Test(priority=105)
    public void test_30_verifyField(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   String strMessage="";
 	   	
 	   ArrayList<String> list = new ArrayList<String>();
 	    	
 	   list = objAdjunoLIMAOffDockLocationPOM.getCaptionsList(strOffDockChevronPageFormName,"Grid_Containers");
 	  
 	  // long count = objAdjunoLIMAOffDockLocationPOM.getNoOfColumnsGrid(strOffDockChevronPageFormName, "Grid_Containers");
 	   strMessage = objAdjunoLIMAOffDockLocationPOM.verifyCaptionsONGrid(list,bookingUploadedCaptions2,9);
 	   
 	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 30"+ strMessage +"*****");
 	   objSoftAssert.assertAll();
    }
    
    @Test(priority=110)
    public void test_32_VerifyMandatory(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   String strMessage="";
 	   //OffDockLocation
 	   String listError = objAdjunoLIMAOffDockLocationPOM.getValidationMessageGridElement(strOffDockChevronPageFormName,"Grid_Containers",0,"OffDockArrivedDate");
 	   System.out.println(listError);
 	   if(!listError.equals("")){
	   		objSoftAssert.assertEquals(listError.equalsIgnoreCase("> The Off Dock Arrived Date should be greater than or equal to Arrived Date!"), true,"***** Test Id:32 - In Off Dock Location Tool Under Off Dock Chevron \"Arrived Date\" Field is not Mandatory *****");	   			
	  }else{
	   		strMessage = strMessage +"off Dock Arrived Date Mandatory message is not displayed";
	  }
 		
 	  String strErrorOffDockLocation = objAdjunoLIMAOffDockLocationPOM.getValidationMessageGridElement(strOffDockChevronPageFormName,"Grid_Containers",0,"OffDockLocation");
 	
 	  if(!strErrorOffDockLocation.equals("")){
 	   		boolean bFlag = strErrorOffDockLocation.equalsIgnoreCase("> &#39;Off Dock Location&#39; is a required field");
 	   		objSoftAssert.assertEquals(bFlag, true,"***** Test Id:32 - In Off Dock Location Tool Under Off Dock Chevron \" off Dock Location\" Field is not Mandatory *****");	   			
 	  }else{
 	   		strMessage = strMessage +"In Off Dock Location Tool Under Off Dock Chevron \" off Dock Location\" Field Mandatory message is not displayed";
 	  }
 	   
 	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:32-"+ strMessage +"*****");
 	  objSoftAssert.assertAll();
    }
    
    @Test(priority=115)
    public void test_31_VerifyGridValue() throws ParseException{
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   String strMessage="";
 	   
 	   int nRows = (int) objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strOffDockChevronPageFormName, "Grid_Containers");
 	   
 	//   strArrivalDate = objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName, "Grid_Containers", 0, "ArrivedDate");
 	   
 	   strNextDay = objAdjunoLIMAOffDockLocationPOM.getDate(-1, "dd MMM yyyy");
 	   
 	   strNextDay2 = objAdjunoLIMAOffDockLocationPOM.getDate(0, "dd MMM yyyy");
 	   
 	   if(!isNullOrBlank(strNextDay)){
 			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay, strOffDockChevronPageFormName, "ArrivedDate1"),true,"***** *****");
 			
 	   }else{
 	   		strMessage=strMessage+" Off Dock Arried Date Value is Empty";
 	   }
 	   
 	  if(!isNullOrBlank(strNextDay2)){
			
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay2, strOffDockChevronPageFormName, "DepartedDate1"),true,"***** *****");
	   }else{
	   		strMessage=strMessage+" Off Dock Departure Date Value is Empty";
	   }
 	   
 	   if(!isNullOrBlank(strOffDockLocationVal)){
 			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockLocationVal, strOffDockChevronPageFormName, "OffDockLocation1"),true,"***** *****");	
 	   }else{
 	   		strMessage=strMessage+"Off Dock Location Value is Empty";
 	   }
 	   
 	   if(!isNullOrBlank(strOffDockReason)){
 			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockReason, strOffDockChevronPageFormName, "ReasonCode1"),true,"***** *****");	
 	   }else{
 	   		strMessage=strMessage+"Reason Value is Empty  in XML";
 	   }
 	   
 	   if(!isNullOrBlank(strOffDockComments)){
 			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockComments, strOffDockChevronPageFormName, "Comments1"),true,"***** *****");	
 	   }else{
 	   		strMessage=strMessage+"Comments Value is Empty in XML";
 	   }
 	   
 	   wait(4000);
 	  
 	  for (int i = 0; i <= nRows-1; i++) {
 		  	objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getGridCellElement(strOffDockChevronPageFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:31- Select CheckBox is not clicked in the Off Dock Chevron *****");
 		  	
 	  }
 	  wait(2000);
 	  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strOffDockChevronPageFormName, "BUTTON_Update"),true,"***** Test ID:31- Update buttoin not clicked in Off Dock Chevron *****");
 	  wait(3000);
 	  
 	  //verify entered data
 	  for (int i = 0; i <= nRows-1; i++) {
	
 		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"OffDockArrivedDate")), true,"***** Test ID:31-  Off Dock Arrival Date is not matching With Entered value *****");
 	 	  
 	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"DepartedDate")), true,"***** Test ID:31-  Off Dock Departure Date is not matching With Entered value *****");
 	 	  
 	 	  objSoftAssert.assertEquals(strOffDockLocationVal.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"OffDockLocation")), true,"***** Test ID:31-  Off Dock Location is not matching With Entered value *****");
 	 	  
 	 	  objSoftAssert.assertEquals(strOffDockReason.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"ReasonCode")), true,"***** Test ID:31- Reason is not matching With Entered value *****");
 	 	  
 	 	  objSoftAssert.assertEquals(strOffDockComments.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"Comments")), true,"***** Test ID:31- Comments is not matching With Entered value *****");
 	 	 
 	  }
 	  
 	   
 //	  strTrackN0 = objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",0,"TrackReference");
 	  wait(4000);
 	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 31"+ strMessage +"*****");
 	  objSoftAssert.assertAll();
    }

    
    @Test(priority=120)
    public void test_34_complete(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvComplete2()), true,"***** Test ID:34- Complete Chevron is not clicked  *****");
 	  
 	   String poDetails = objAdjunoLIMAOffDockLocationPOM.getTrackMessage().getText();
 	   
 	   String[] vals = poDetails.split("achieved on track '");
 	   System.out.println("message is :"+poDetails);
 	   String valss[] = vals[1].split("'",22);
 	   strTrackN0 = valss[0];
 	   System.out.println("strTrackN0--$$"+valss[0]);
 	   
 	   wait(5000);
 	   objSoftAssert.assertAll();
    }
    
    @Test(priority=125)
    public void test_36_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	    	
 	   objAdjunoLIMAOffDockLocationPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTrack(),objAdjunoLIMAOffDockLocationPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:36- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=130)
	public void test_37_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getFldRefNo()),true," ***** Test ID:37-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValueForWebElement(objAdjunoLIMAOffDockLocationPOM.getFldRefNo(),strTrackN0),true,"***** Test IDReference field value is not set");   
			    	  
			 objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:37-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=135)
    public void test_38_clickOffLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getLnkOffDock()), true,"***** Test ID:38- Off Dock Location link is not clicked *****");
		wait(5000);
		
		//if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strOffDockLocationSearchFormName2)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdContainer()), true,"***** Test ID : 38 - In Off Dock Location page \"Container\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdSeal()), true,"***** Test ID : 38 - In Off Dock Location page \"Seal\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdArrivedDate()), true,"***** Test ID :38 - In Off Dock Location page \"Arrived Date\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdDepartedDate()), true,"***** Test ID : 38 - In Off Dock Location page \"Departure Date\" is not found *****");
	        

			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdOffDockLocation()), true,"***** Test ID : 38 - In Off Dock Location page \"OffDockLocation\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdOperator()), true,"***** Test ID : 38 - In Off Dock Location page \"Operator\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdReasonCode()), true,"***** Test ID :38 - In Off Dock Location page \"ReasonCode\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdComments()), true,"***** Test ID : 38 - In Off Dock Location page \"Comments\" is not found *****");
			
	        
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColContainer().getText().equalsIgnoreCase(strContainerValue), true," Test ID : 38 - In Off Dock Location page Container value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColArrivedDate().getText().contains(strNextDay), true," Test ID : 38 - In Off Dock Location page Arrived Date value is not matching *****");
	        
	        
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColDepartedDate().getText().contains(strNextDay2), true," Test ID : 38 - In Track ->Off Dock Location page Departure Date value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColOffDockLocation().getText().equalsIgnoreCase(strOffDockLocationVal), true," Test ID : 38 - In Track ->Off Dock Location page Off Dock Location  value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColReasonCode().getText().equalsIgnoreCase(strOffDockReason), true," Test ID : 38 - In Track ->Off Dock Location page Reason value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColComments().getText().equalsIgnoreCase(strOffDockComments), true," Test ID : 38 - In Track ->Off Dock Location page Comments value is not matching *****");
	        

	/*	}else{
			strMessage = strMessage + "  Off Dock Location Page is not loaded ";
		}*/
				
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:38-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test(priority=140)
    public void test_39_accessOffDockLocation()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	 String strTitle = objAdjunoLIMAOffDockLocationPOM.callMouseHover(strOffDockLocationSearchFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTools(), objAdjunoLIMAOffDockLocationPOM.getLnkOffDockLocation());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strOffDockLocationPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:39-Title of the Off Dock Location page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    @Test(priority=150)
    public void test_39_SearchContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		wait(5000);
		clearFields();
		wait(5000);
		//strStatusDepated
		
		if(!isNullOrBlank(strStatusDepated)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusDepated, strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Depated status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strContainerValue, strOffDockLocationSearchFormName, "PARAM_Container"),true,"*****Test ID:39- Container value is not in Search Chevron In off Dock Location tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"***** Test ID:39- Select Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
	
		if(searchResult==false){
			objAdjunoLIMAOffDockLocationPOM.getLstSelect().get(0).click();
			
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
	//	strMessage = strMessage + objAdjunoLIMAOffDockLocationPOM.verifyContainerAndStaus(strContainerValue,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:39- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    }   
    
    @Test(priority=155)
    public void test_40_navigateToOffDockChevron()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvOffDock2()), true,"***** Test ID:40- Off Dock Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		
		
		if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strOffDockChevronPageFormName)){
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "ArrivedDate1"), true,"***** Test ID:40- Off D  Arrived Date field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "DepartedDate1"), true,"***** Test ID:40- Off Dock Departed Date field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "OffDockLocation1"), true,"***** Test ID:40- Off Dock Location field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "ReasonCode1"), true,"***** Test ID:40- Reason field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "Comments1"), true,"***** Test ID:40- Comments field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "BUTTON_Update"), true,"***** Test ID:40- Update Button is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			    
			   
		}else{
			   strMessage = strMessage +" Off Dock Location Chevron Page is not loaded";  
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:40- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    
    }
    
    @Test(priority=160)
    public void test_41_VerifyGridValue() throws ParseException{
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   String strMessage="";
 	   wait(5000);
 	 
 	   int nRows = (int) objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strOffDockChevronPageFormName, "Grid_Containers");
 	   //dd MMM yyyy HH:mm
 	   //Date departureDate = new SimpleDateFormat("dd MMM yyyy").parse(strDepartureDate);
 	  
 	   strNextDay = objAdjunoLIMAOffDockLocationPOM.getDate(-2, "dd MMM yyyy");
 	   
 	   wait(2000);
 	   if(!isNullOrBlank(strNextDay)){
			
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay, strOffDockChevronPageFormName, "ArrivedDate1"),true,"***** *****");
	   }else{
	   		strMessage=strMessage+" Off Dock Arrived Date Value is Empty";
	   }
 	   
 	   if(!isNullOrBlank(strOffDockLocationVal2)){
 			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockLocationVal2, strOffDockChevronPageFormName, "OffDockLocation1"),true,"***** *****");	
 	   }else{
 	   		strMessage=strMessage+"Off Dock Location Value is Empty";
 	   }
 	  
 	   wait(4000);
 	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getSelect()), true,"***** Test ID:41- Select CheckBox is not clicked in the Off Dock Chevron *****");
 	   wait(2000);
	  
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strOffDockChevronPageFormName, "BUTTON_Update"),true,"***** Test ID:41- Update buttoin not clicked in Off Dock Chevron *****");
 	   wait(3000);
 	  
 	   //verify entered data
 	   for (int i = 0; i <= nRows-1; i++) {
 		 objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"OffDockArrivedDate")), true,"***** Test ID:41-  Off Dock Arrived Date is not matching With Entered value *****");
 	  
 		 objSoftAssert.assertEquals(strOffDockLocationVal2.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"OffDockLocation")), true,"***** Test ID:41-  Off Dock Location is not matching With Entered value *****");
 	   }
 	  
 	 //  strTrackN0 = objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",0,"TrackReference");
 	  
 	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 41"+ strMessage +"*****");
 	   objSoftAssert.assertAll();
    }
    
    
    @Test(priority=165)
    public void test_42_complete(){
  	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
  	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvComplete2()), true,"***** Test ID:42- Complete Chevron is not clicked *****");
  	 
  	   String poDetails = objAdjunoLIMAOffDockLocationPOM.getTrackMessage().getText();
	   
	   String[] vals = poDetails.split("achieved on track '");
	   
	   String valss[] = vals[1].split("'",22);
	   strTrackN0 = valss[0];
	   System.out.println("strTrackN0--$$"+valss[0]);
 	   
 	   wait(5000);
 	  
  	   objSoftAssert.assertAll();
     }
    
    
    
    @Test(priority=170)
    public void test_44_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   wait(4000); 	
 	   objAdjunoLIMAOffDockLocationPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTrack(),objAdjunoLIMAOffDockLocationPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:44- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=175)
	public void test_45_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getFldRefNo()),true," ***** Test ID:45-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValueForWebElement(objAdjunoLIMAOffDockLocationPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:45-Reference field value is not set");   
			    	  
			 objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:45-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=180)
    public void test_46_clickOffLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getLnkOffDock()), true,"***** Test ID:46- Off Dock Location link is not clicked *****");
		wait(4000);
		
		//if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strOffDockLocationSearchFormName2)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdContainer()), true,"***** Test ID : 46 - In Off Dock Location page \"Container\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdSeal()), true,"***** Test ID : 46 - In Off Dock Location page \"Seal\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdArrivedDate()), true,"***** Test ID :46 - In Off Dock Location page \"Arrived Date\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdDepartedDate()), true,"***** Test ID : 46 - In Off Dock Location page \"Departure Date\" is not found *****");
	        

			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdOffDockLocation()), true,"***** Test ID : 46 - In Off Dock Location page \"OffDockLocation\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdOperator()), true,"***** Test ID : 46 - In Off Dock Location page \"Operator\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdReasonCode()), true,"***** Test ID :46 - In Off Dock Location page \"ReasonCode\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdComments()), true,"***** Test ID : 46 - In Off Dock Location page \"Comments\" is not found *****");
			
	        
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColContainer().getText().equalsIgnoreCase(strContainerValue), true," Test ID : 46 - In Track ->Off Dock Location page Container value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColArrivedDate().getText().contains(strNextDay), true," Test ID : 46 - In Track -> Off Dock Location page Arrived Date value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColDepartedDate().getText().contains(strNextDay2), true," Test ID : 46 - In Track ->Off Dock Location page Departure Date value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColOffDockLocation().getText().equalsIgnoreCase(strOffDockLocationVal2), true," Test ID : 46 - In Track ->Off Dock Location page Off Dock Location  value is not matching *****");
	       
	       

		/*}else{
			strMessage = strMessage +  " Off Dock Location Page is not loaded ";
		}*/
		
		        
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:46-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test(priority=185)
    public void test_47_accessOffDockLocation()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	 String strTitle = objAdjunoLIMAOffDockLocationPOM.callMouseHover(strOffDockLocationSearchFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTools(), objAdjunoLIMAOffDockLocationPOM.getLnkOffDockLocation());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strOffDockLocationPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:47-Title of the Off Dock Location page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    
    @Test(priority=190)
    public void test_47_SearchContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		clearFields();
		wait(4000);
		
		if(!isNullOrBlank(strStatusDepated)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusDepated, strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Depated status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strContainerValue, strOffDockLocationSearchFormName, "PARAM_Container"),true,"*****Test ID:47- Container value is not in Search Chevron In off Dock Location tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"***** Test ID:47- Select Chevron is not clicked in Off Dock Location Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
	
		if(searchResult==false){
			objAdjunoLIMAOffDockLocationPOM.getLstSelect().get(0).click();
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
	//	strMessage = strMessage + objAdjunoLIMAOffDockLocationPOM.verifyContainerAndStaus(strContainerValue,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:47- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    }
    
    
    @Test(priority=195)
    public void test_48_navigateToOffDockChevron()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvOffDock2()), true,"***** Test ID:48- Off Dock Chevron is not clicked in Off Dock Location Tool *****");
		wait(4800);
		
		if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strOffDockChevronPageFormName)){
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "ArrivedDate1"), true,"***** Test ID:48- Off D  Arrived Date field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "DepartedDate1"), true,"***** Test ID:48- Off Dock Departed Date field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "OffDockLocation1"), true,"***** Test ID:48- Off Dock Location field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "ReasonCode1"), true,"***** Test ID:48- Reason field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "Comments1"), true,"***** Test ID:48- Comments field is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.checkDoesElementExist(strOffDockChevronPageFormName, "BUTTON_Update"), true,"***** Test ID:48- Update Button is not found in \"Off Dock Location Chevorn\" in Off Dock Location Tool *****");
			   
			   
		}else{
			   strMessage = strMessage +" Off Dock Location Chevron Page is not loaded";  
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:48- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    
    }
    
    @Test(priority=200)
    public void test_49_VerifyGridValue() throws ParseException{
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	   String strMessage="";
 	   int nRows = (int) objAdjunoLIMAOffDockLocationPOM.getNoOfRowsGrid(strOffDockChevronPageFormName, "Grid_Containers");
	   // //dd MMM yyyy HH:mm
	  // Date arrivalDate = new SimpleDateFormat("dd MMM yyyy").parse(strArrivalDate);
 	   strNextDay = objAdjunoLIMAOffDockLocationPOM.getDate(-1, "dd MMM yyyy");
	   strNextDay2 = objAdjunoLIMAOffDockLocationPOM.getDate(0, "dd MMM yyyy");

	   if(!isNullOrBlank(strNextDay)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay, strOffDockChevronPageFormName, "ArrivedDate1"),true,"***** *****");
			
	   }else{
	   		strMessage=strMessage+" odd Dock Arrival Date Value is Empty";
	   }
	   
	   
	   if(!isNullOrBlank(strNextDay2)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strNextDay2, strOffDockChevronPageFormName, "DepartedDate1"),true,"***** *****");
			
	   }else{
	   		strMessage=strMessage+" Off Dock Departure Date Value is Empty";
	   }
	   
	   
	   if(!isNullOrBlank(strOffDockLocationVal2)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strOffDockLocationVal2, strOffDockChevronPageFormName, "OffDockLocation1"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Off Dock Location Value is Empty";
	   }
 	   wait(4000);
 	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getSelect()), true,"***** Test ID:41- Select CheckBox is not clicked in the Off Dock Chevron *****");
	   wait(2000);
	  
 	   wait(2000);
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButton(strOffDockChevronPageFormName, "BUTTON_Update"),true,"***** Test ID:49- Update button is not clicked in Off Dock Chevron *****");
 	   wait(4800);
 	  
 	   //verify entered data
 /*	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getSelect()), true,"***** Test ID:41- Select CheckBox is not clicked in the Off Dock Chevron *****");
	   wait(2000);*/
	   
	   for (int i = 0; i <= nRows-1; i++) {
			
	 		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"OffDockArrivedDate")), true,"***** Test ID:49-  Off Dock Arrival Date is not matching With Entered value *****");
	 	 	  
	 	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"DepartedDate")), true,"***** Test ID:49- Off Dock Departure Date is not matching With Entered value *****");
	 	 	  
	 	 	  objSoftAssert.assertEquals(strOffDockLocationVal2.equalsIgnoreCase(objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",i,"OffDockLocation")), true,"***** Test ID:49-  Off Dock Departure Date is not matching With Entered value *****");
	 	 	  
	 	 	 
	   }
	  
 	  
 	//   strTrackN0 = objAdjunoLIMAOffDockLocationPOM.getGridCellElementValue(strOffDockChevronPageFormName,"Grid_Containers",0,"TrackReference");
 	  
 	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 49"+ strMessage +"*****");
 	   objSoftAssert.assertAll();
    }

    @Test(priority=205)
    public void test_50_complete(){
  	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
  	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvComplete2()), true,"***** Test ID:205- Complete Chevron is not clicked *****");
  	 
  	   String poDetails = objAdjunoLIMAOffDockLocationPOM.getTrackMessage().getText();
	   
	   String[] vals = poDetails.split("achieved on track '");
	   
	   String valss[] = vals[1].split("'",22);
	   strTrackN0 = valss[0];
	   
	   wait(5000);
  	   
  	   objSoftAssert.assertAll();
     }
    
    
    @Test(priority=210)
    public void test_52_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   
 	   objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
 	    	
 	   objAdjunoLIMAOffDockLocationPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTrack(),objAdjunoLIMAOffDockLocationPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:52- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=215)
	public void test_53_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getFldRefNo()),true," ***** Test ID:53-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValueForWebElement(objAdjunoLIMAOffDockLocationPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:53-Reference field value is not set");   
			    	  
			 objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:53-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    

    @Test(priority=220)
    public void test_54_clickOffLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getLnkOffDock()), true,"***** Test ID:53- Off Dock Location link is not clicked *****");
		wait(4000);
		
	//	if(objAdjunoLIMAOffDockLocationPOM.isPageLoaded(strOffDockLocationSearchFormName2)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdContainer()), true,"***** Test ID : 53 - In Off Dock Location page \"Container\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdSeal()), true,"***** Test ID : 53 - In Off Dock Location page \"Seal\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdArrivedDate()), true,"***** Test ID :53 - In Off Dock Location page \"Arrived Date\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdDepartedDate()), true,"***** Test ID : 53 - In Off Dock Location page \"Departure Date\" is not found *****");
	        

			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdOffDockLocation()), true,"***** Test ID : 53 - In Off Dock Location page \"OffDockLocation\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdOperator()), true,"***** Test ID : 53 - In Off Dock Location page \"Operator\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdReasonCode()), true,"***** Test ID :53 - In Off Dock Location page \"ReasonCode\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getHdComments()), true,"***** Test ID : 53 - In Off Dock Location page \"Comments\" is not found *****");
			
	        
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColContainer().getText().equalsIgnoreCase(strContainerValue), true," Test ID : 53 - In Track ->Off Dock Location page Container value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColArrivedDate().getText().contains(strNextDay), true," Test ID : 53 - In Track ->Off Dock Location page Arrived Date value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getColDepartedDate().getText().contains(strNextDay2), true," Test ID : 53 - In Track ->Off Dock Location page Departure Date value is not matching *****");
	       

		/*}else{
			strMessage = strMessage + " Off Dock Location Page is not loaded ";
		}*/
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:53-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    

    @Test(priority=225)
    public void test_55_accessOffDockLocation()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);
    	 String strTitle = objAdjunoLIMAOffDockLocationPOM.callMouseHover(strOffDockLocationSearchFormName,objAdjunoLIMAOffDockLocationPOM.getLnkTools(), objAdjunoLIMAOffDockLocationPOM.getLnkOffDockLocation());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strOffDockLocationPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:55-Title of the Off Dock Location page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    @Test(priority=230)
    public void test_55_SearchContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAOffDockLocationPOM = new AdjunoLIMAOffDockLocationPOM(driver);	
		wait(5000);
		clearFields();
		
		if(!isNullOrBlank(strStatusDepated)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strStatusDepated, strOffDockLocationSearchFormName, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Depated status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.setFieldValue(strContainerValue, strOffDockLocationSearchFormName, "PARAM_Container"),true,"*****Test ID:55- Container value is not in Search Chevron In off Dock Location tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.clickButtonUsingWebElement(objAdjunoLIMAOffDockLocationPOM.getChvSelect()), true,"***** Test ID:55- Select Chevron is not clicked in Off Dock Location Tool *****");
		wait(5000);
		
		searchResult = objAdjunoLIMAOffDockLocationPOM.isElementPresent(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult());
	
		if(searchResult==false){
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
	//	strMessage = strMessage + objAdjunoLIMAOffDockLocationPOM.verifyContainerAndStaus(strContainerValue,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAOffDockLocationPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:55- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    }

   
    @AfterClass
    public void terminate(){
    	driver.quit();
    }
}
