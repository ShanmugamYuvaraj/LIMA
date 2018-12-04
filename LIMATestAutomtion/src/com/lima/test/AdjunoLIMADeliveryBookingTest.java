package com.lima.test;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMADeliveryBookingPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMADeliveryBookingTest {
	
	WebDriver driver;
	long nPage_Timeout = 0;
    String strTestURL;
    boolean searchResult;
    
    String strUserName;
    String strPassword;
    String strCatalogFormName;
    String strCatalogPageTitle;
	String strInvalidContainer;
	String strStatusPending;
	String strDeliveryBookingSearchFormName;
	String strLocode;
	String strVessel;
	String strCatalogDestination;
	String strLoading;
	String strLoadingValue;
	String strHaulier;
	String strHaulierValue;
	String strDeliveryBookingPageTitle;
	
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoLIMADeliveryBookingPOM objAdjunoLIMADeliveryBookingPOM;
	ArrayList<String> catalogDestinationPortList;
	ArrayList<String> catalogVesselList;
	ArrayList<String> catalogHaulierList;
	ArrayList<String> catalogDestinationList;
	ArrayList<String> catalogLoadingList;
	String strValidContainer;
	String strStatusAny;
	String strLevelOfDetail;
	String strDestinationPort;
	String strDestination;
	String strETATo;
	String strModeValue;
	 
	
	
	public boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}

	public void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@BeforeClass
	public void setup() {
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
			
			Node catalogPageTitle = (Node) xPath.evaluate("/config/LIMA/Catalog_Page_Title", dDoc, XPathConstants.NODE);
	        strCatalogPageTitle = catalogPageTitle.getTextContent();
	        
	        Node catalogFormName = (Node) xPath.evaluate("/config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	        strCatalogFormName = catalogFormName.getTextContent();
	        
	        Node deliveryBookingPageTitle = (Node) xPath.evaluate("/config/LIMA/Delivery_Booking_Title", dDoc, XPathConstants.NODE);
	        strDeliveryBookingPageTitle = deliveryBookingPageTitle.getTextContent();
	        
	        Node deliveryBookingSearchFormName = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Delivery_Booking", dDoc, XPathConstants.NODE);
	        strDeliveryBookingSearchFormName = deliveryBookingSearchFormName.getTextContent();
			
			
			driver = new FirefoxDriver();
            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

            driver.get(strTestURL);

            objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
        	
            objAdjunoLIMALoginPOM.setUserName(strUserName);
            objAdjunoLIMALoginPOM.setPassword(strPassword);
        	objAdjunoLIMALoginPOM.clickLogon();
        	wait(8000);
        	
		} catch (Exception e) {
			 e.printStackTrace();
		}
		
		DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getDeliveryBookingXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            Node invalidContainer = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Invalid_Container", dDoc1, XPathConstants.NODE);
            strInvalidContainer = invalidContainer.getTextContent();
            
            Node validContainer = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Valid_Container", dDoc1, XPathConstants.NODE);
            strValidContainer = validContainer.getTextContent();
            
            Node statusAwiting = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Status_Pending", dDoc1, XPathConstants.NODE);
            strStatusPending = statusAwiting.getTextContent();
            
            Node levelOfDetail = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Level_Of_Detail", dDoc1, XPathConstants.NODE);
            strLevelOfDetail = levelOfDetail.getTextContent();
            
            Node anyStatus = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Any_Status", dDoc1, XPathConstants.NODE);
            strStatusAny = anyStatus.getTextContent();
            
            Node locode = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Catalog_Locode", dDoc1, XPathConstants.NODE);
            strLocode = locode.getTextContent();
            
            Node vessel = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Catalog_Vessel", dDoc1, XPathConstants.NODE);
            strVessel = vessel.getTextContent();
            
            Node catologDestination = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Catalog_Destination", dDoc1, XPathConstants.NODE);
            strCatalogDestination = catologDestination.getTextContent();
            
            Node destinationPort = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Destination_Port", dDoc1, XPathConstants.NODE);
            strDestinationPort = destinationPort.getTextContent();
            
            Node destination = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Destination", dDoc1, XPathConstants.NODE);
            strDestination = destination.getTextContent();
            
            Node loading = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Catalog_Loading", dDoc1, XPathConstants.NODE);
            strLoading = loading.getTextContent();
            
            Node haulier = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Catalog_Haulier", dDoc1, XPathConstants.NODE);
            strHaulier = haulier.getTextContent();
            
            Node haulierValue = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Haulier_value", dDoc1, XPathConstants.NODE);
            strHaulierValue = haulierValue.getTextContent();
            
            Node loading2 = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Loading", dDoc1, XPathConstants.NODE);
            strLoadingValue = loading2.getTextContent();
            
            Node mode = (Node) xPath1.evaluate("/Delivery_Booking/Search_Param/Mode", dDoc1, XPathConstants.NODE);
            strModeValue = mode.getTextContent();
            
         
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	
	@Test(priority=1)
    public void test_accessCatalog()
    {
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getBtnOk());
    	wait(7000);
    	
    	String strTitle = objAdjunoLIMADeliveryBookingPOM.callMouseHover(strCatalogFormName,objAdjunoLIMADeliveryBookingPOM.getLnkTools(), objAdjunoLIMADeliveryBookingPOM.getLnkCatalog());    
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
	
	@Test(priority=2)
    public void getCatalogData(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	String strMessage = "";
    	catalogDestinationPortList = new ArrayList<String>();
    	catalogVesselList = new ArrayList<String>();
    	catalogHaulierList = new ArrayList<>();
    	catalogLoadingList = new ArrayList<>();
    	catalogDestinationList = new ArrayList<>();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getLnkLightHouse()), true,"***** Lighthouse Link is not clicked in Catalog page *****");
    	wait(3000);
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strLocode)){
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLocode, strCatalogFormName, "Param_Type"),true,"***** Type Field value is not entered as LOCODE*****");
		 
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getCatalogbtnApply()),true,"***** Apply Button is not Clicked *****");
			 wait(5000);
			 
			 int nCount = objAdjunoLIMADeliveryBookingPOM.valCount();
			 catalogDestinationPortList = objAdjunoLIMADeliveryBookingPOM.getCatalogTableData(nCount);
			 System.out.println("catalogDestinationList size"+catalogDestinationPortList.size());
			 wait(5000);
    	
    	}else{
			 strMessage=strMessage+"Locode Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strVessel)){
    		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strVessel, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Vessel*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getCatalogbtnApply()),true,"*****  Apply button is not clicked *****");
			 wait(5000);
			             
			 int nCount2 = objAdjunoLIMADeliveryBookingPOM.valCount();
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getBtnFirst()),true,"*****  Apply button is not clicked *****");
			 wait(3000);
			 catalogVesselList = objAdjunoLIMADeliveryBookingPOM.getCatalogTableData1(nCount2);  
			 System.out.println("vessel size"+catalogVesselList.size());
    	}else{
			 strMessage=strMessage+"vessel Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	if(!isNullOrBlank(strHaulier)){
    		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strHaulier, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Vessel*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getCatalogbtnApply()),true,"*****  Apply button is not clicked *****");
			 wait(5000);
			             
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getBtnFirst()),true,"*****  Apply button is not clicked *****");
			 wait(3000);
			 catalogHaulierList = objAdjunoLIMADeliveryBookingPOM.getCatalogData();  
			 
    	}else{
			 strMessage=strMessage+"Haulier Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	if(!isNullOrBlank(strCatalogDestination)){
    		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strCatalogDestination, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Vessel*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getCatalogbtnApply()),true,"*****  Apply button is not clicked *****");
			 wait(5000);
			             
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getBtnFirst()),true,"*****  Apply button is not clicked *****");
			 wait(3000);
			 catalogDestinationList = objAdjunoLIMADeliveryBookingPOM.getCatalogData();  
			 
    	}else{
			 strMessage=strMessage+"Destination Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	if(!isNullOrBlank(strLoading)){
    		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLoading, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Vessel*****");
			    
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getCatalogbtnApply()),true,"*****  Apply button is not clicked *****");
			 wait(5000);
			             
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getBtnFirst()),true,"*****  Apply button is not clicked *****");
			 wait(3000);
			 catalogLoadingList = objAdjunoLIMADeliveryBookingPOM.getCatalogData();  
			 
    	}else{
			 strMessage=strMessage+"Loading Value is Empty in Xml";
		}
    	
    	
    	objSoftAssert.assertAll();
    }
	
	
	
	public void clearCatalogFields(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Name"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Description"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertAll();
    }
	
	
	public void clearFields(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_OrderType"),true,"***** Test ID:3- OrderType field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Division"),true,"***** Test ID:3- Division field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Department"),true,"***** Test ID:3- Department field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Category"),true,"***** Test ID:3- Category field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Supplier"),true,"***** Test ID:3- Vendor field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_OrderNumber"),true,"***** Test ID:3- OrderNumber field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Product"),true,"***** Test ID:3- Product field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    //	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_SKU"),true,"***** Test ID:3- SKU field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Mode"),true,"***** Test ID:3- Mode field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_DestinationPort"),true,"***** Test ID:3- DestinationPort field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_DC"),true,"***** Test ID:3- Param_DC field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Vessel"),true,"***** Test ID:3- Param_Vessel field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_ETDTo"),true,"***** Test ID:3- ETD To field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_ETDFrom"),true,"***** Test ID:3- ETD From field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Container"),true,"***** Test ID:3- Container field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Housebill"),true,"***** Test ID:3- Housebill field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Catalog_Haulier"),true,"***** Test ID:3- Haulie field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_BookingRef"),true,"***** Test ID:3- BookingRef field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_ClientRef"),true,"***** Test ID:3- ClientRef field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_BookedFrom"),true,"***** Test ID:3- BookedFrom field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_BookedTo"),true,"***** Test ID:3- Booked To field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_AvailableFrom"),true,"***** Test ID:3- Param_AvailableFrom field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test ID:3- Param_Status field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "PARAM_WorkflowStatus"),true,"***** Test ID:3- Work flow Statu field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_LoadType"),true,"***** Test ID:3- LoadType field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clearInputField(strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test ID:3- Level Of Detail field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");

    	
    	objSoftAssert.assertAll();
    }
	
	
	
	@Test(priority=5)
    public void test_1_accessDeliveryBooking()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	
    	 String strTitle = objAdjunoLIMADeliveryBookingPOM.callMouseHover(strDeliveryBookingSearchFormName,objAdjunoLIMADeliveryBookingPOM.getLnkTools(), objAdjunoLIMADeliveryBookingPOM.getLnkDeliveryBooing());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if  (strTitle.equalsIgnoreCase(strDeliveryBookingPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:1-Title of the Delivery Booking page is wrong*****");
         objSoftAssert.assertAll();
    }
	
	
	@Test(priority=7)
	public void test_2_VerifyExistanceOfChevorns()
   	{    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getChvSearch()), true,"***** Test ID : 2 - In Delivery Booking Tool \"Search Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getChvSelect()), true,"***** Test ID : 2 - In Delivery Booking Tool \"Select Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getChvBooking()), true,"***** Test ID : 2 - In Delivery Booking Tool \"Delivery Booking Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getChvComplete1()), true,"***** Test ID : 2 - In Delivery Booking Tool \"Complete Chevorn\" is not found *****");
     
        objSoftAssert.assertAll();
   	}
	
	
	 @Test(priority=8)
		public void test_3_VerifyExistanceOfFields()
		{
		    SoftAssert objSoftAssert = new SoftAssert();
		    objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		    
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_OrderType"),true,"***** Test ID:3- OrderType field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Division"),true,"***** Test ID:3- Division field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Department"),true,"***** Test ID:3- Department field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Category"),true,"***** Test ID:3- Category field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Supplier"),true,"***** Test ID:3- Vendor field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_OrderNumber"),true,"***** Test ID:3- OrderNumber field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Product"),true,"***** Test ID:3- Product field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    //	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_SKU"),true,"***** Test ID:3- SKU field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Mode"),true,"***** Test ID:3- Mode field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_DestinationPort"),true,"***** Test ID:3- DestinationPort field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_DC"),true,"***** Test ID:3- Param_DC field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Vessel"),true,"***** Test ID:3- Param_Vessel field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_ETDTo"),true,"***** Test ID:3- ETD To field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_ETDFrom"),true,"***** Test ID:3- ETD From field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Container"),true,"***** Test ID:3- Container field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Housebill"),true,"***** Test ID:3- Housebill field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Catalog_Haulier"),true,"***** Test ID:3- Haulie field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_BookingRef"),true,"***** Test ID:3- BookingRef field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_ClientRef"),true,"***** Test ID:3- ClientRef field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_BookedFrom"),true,"***** Test ID:3- BookedFrom field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_BookedTo"),true,"***** Test ID:3- Booked To field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_AvailableFrom"),true,"***** Test ID:3- Param_AvailableFrom field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test ID:3- Param_Status field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "PARAM_WorkflowStatus"),true,"***** Test ID:3- Work flow Statu field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_LoadType"),true,"***** Test ID:3- LoadType field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
	    	objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test ID:3- Level Of Detail field is not found in \"Search Chevorn\" in Delivery Booking Tool *****");
		 	objSoftAssert.assertAll();
		}
	 
	 
	@Test(priority = 9)
	public void test_4_VerifyMandatoryFields() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		
		clearFields();
		wait(5000);
		
		
		String strErrorBookingStatus = objAdjunoLIMADeliveryBookingPOM.getValidationMessageElement(strDeliveryBookingSearchFormName,"Param_Status");

		System.out.println("sdjhh:" + strErrorBookingStatus);
		if (!strErrorBookingStatus.equals("")) {
			boolean bFlag = strErrorBookingStatus.equalsIgnoreCase("> &#39;Booking Status&#39; is a required field");
			objSoftAssert.assertEquals(bFlag,true,"**** Test Id:4 - In Delivery Booking Tool Under Select Chevron \" BookingStatus\" Field is not Mandatory *****");
		} else {
			strMessage = strMessage+ "In Delivery Booking Tool Under Select Chevron \"BookingStatus \" Field Mandatory message is not displayed";
		}
		
		String strErrorLevelOfDetail = objAdjunoLIMADeliveryBookingPOM.getValidationMessageElement(strDeliveryBookingSearchFormName,"Param_LevelOfDetail");

		System.out.println("dshdhs:" + strErrorLevelOfDetail);
		if (!strErrorLevelOfDetail.equals("")) {
			boolean bFlag = strErrorLevelOfDetail.equalsIgnoreCase("> &#39;Param_LevelOfDetail&#39; is a required field");
			objSoftAssert.assertEquals(bFlag,true,"**** Test Id:4 - In Delivery Booking Tool Under Select Chevron \" Level Of Detail\" Field is not Mandatory *****");
		} else {
			strMessage = strMessage+ "In Delivery Booking Tool Under Select Chevron \" Level Of Detail \" Field Mandatory message is not displayed";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName,"Param_LevelOfDetail"),true,"***** Test ID:4- Level Of Detail field is not found in \"Select Chevorn\" in Delivery Booking Tool *****");
		objSoftAssert.assertAll();
	}
	 
	
		
	@Test(priority=10)
    public void test_5_VerifyDestinationPortDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> destinationPortList = new ArrayList<String>();
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		destinationPortList = objAdjunoLIMADeliveryBookingPOM.getDropdownValues(strDeliveryBookingSearchFormName, "Param_DestinationPort");
		
		if(destinationPortList.size()>0 && catalogDestinationPortList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveryBookingPOM.verifyCatalogData(catalogDestinationPortList, destinationPortList);
		}else{
			strMessage=strMessage+"Destination Port Drop Down Won't have any Value";
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 5"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=12)
    public void test_6_VerifyDestinationDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> destinationList = new ArrayList<String>();
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		destinationList = objAdjunoLIMADeliveryBookingPOM.getDropdownValues(strDeliveryBookingSearchFormName, "Param_DC");
		
		if(destinationList.size()>0 && catalogDestinationList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveryBookingPOM.verifyCatalogData(catalogDestinationList, destinationList);
		}else{
			strMessage=strMessage+"Destination Drop Down Won't have any Value";
		}
		
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
	
	@Test(priority=14)
    public void test_7_VerifyHaulierDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> haulierList = new ArrayList<String>();
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		haulierList = objAdjunoLIMADeliveryBookingPOM.getDropdownValues(strDeliveryBookingSearchFormName, "Param_Catalog_Haulier");
		
		if(haulierList.size()>0 && catalogHaulierList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveryBookingPOM.verifyCatalogData(catalogHaulierList, haulierList);
		}else{
			strMessage=strMessage+"Haulier Drop Down Won't have any Value";
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	@Test(priority=16)
    public void test_8_VerifyLoadingDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> loadingList = new ArrayList<String>();
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		loadingList = objAdjunoLIMADeliveryBookingPOM.getDropdownValues(strDeliveryBookingSearchFormName, "Param_LoadType");
		
		if(loadingList.size()>0 && catalogLoadingList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveryBookingPOM.verifyCatalogData(catalogLoadingList, loadingList);
		}else{
			strMessage=strMessage+"Loading Drop Down Won't have any Value";
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    @Test(priority=18)
    public void test_9_VerifyVesselDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> vesselList = new ArrayList<String>();
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		vesselList = objAdjunoLIMADeliveryBookingPOM.getDropdownValues(strDeliveryBookingSearchFormName, "Param_Vessel");
		
		if(vesselList.size()>0 && catalogVesselList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveryBookingPOM.verifyCatalogData(catalogVesselList, vesselList);
		}else{
			strMessage=strMessage+"Vessel Drop Down Won't have any Value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	
    
    @Test(priority=20)
    public void test_10_VerifyInvalidContainer()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		
		clearFields();
		

		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:12- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:12- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		

		if(!isNullOrBlank(strInvalidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strInvalidContainer, strDeliveryBookingSearchFormName, "Param_Container"),true,"***** ***** Test id:10- Invalid Container Value is not set*****");
	 	
	   	}else{
	   		strMessage=strMessage+"Invalid container Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveryBookingPOM.getChvSelect()), true,"***** Test id:10 - Select Chevron is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==true){
			
		} else {
			strMessage = strMessage + "No items were found. text is not shown for invalid container value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 10-"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    

    
    
    @Test(priority=25)
    public void test_11_VerifyInvalidPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		
		clearFields();
		
		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:12- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:12- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strInvalidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strInvalidContainer, strDeliveryBookingSearchFormName, "Param_OrderNumber"),true,"***** Test id:11- Invalid PO Number Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Invalid Po Number Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:11 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==true){
			
		} else {
			strMessage = strMessage + "No items were found. text is not shown for invalid PO Number value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 11-"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    @Test(priority=30)
    public void test_12_VerifyValidPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		//containerList =  new ArrayList<String>();	
		
		clearFields();
		
		
		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:12- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:12- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
		
		if(!isNullOrBlank(strValidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strValidContainer, strDeliveryBookingSearchFormName, "Param_Container"),true,"***** Test id:12- Invalid Container Number Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"valid Po Number Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:12 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <=objAdjunoLIMADeliveryBookingPOM.getLstContainer().size()-1; i++) {
				if(objAdjunoLIMADeliveryBookingPOM.getLstContainer().get(i).getText().equalsIgnoreCase(strValidContainer)){
					
				}else{
					strMessage = strMessage + "  " +strValidContainer +" container value is not matching with "+objAdjunoLIMADeliveryBookingPOM.getLstContainer().get(i).getText();
				}
			}
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
	//  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:12- "+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
    @Test(priority=32)
    public void test_13_verifyFieldsInSelectChevron(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 		
 	   objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);	
 		
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "FORK_DeliveryBookingRefineSearch"), true,"***** TestId:13- Refine Search button is not shown in Select chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "FORK_DeliveryBookingCancel"), true,"***** TestId:13- Booking cancel button is not shown in Select chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "UploadDeliveryBookingTemplate"), true,"***** TestId:13- Upload button is not shown in Select chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "FORK_DeliveryBookingDialog"), true,"***** TestId:13- Add Dialog button is not shown in Select chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "OpenReport"), true,"***** TestId:13- Download Delivery Booking Template Link is not shown in Select chevron in Delivery Booking tool *****");
 	   
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThEquipment()), true,"***** Test ID:13- Equipment Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThLoading()), true,"***** Test ID:13- Loading Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThMode()), true,"***** Test ID:13- Mode Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThContainer()), true,"***** Test ID:13- Container Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThDestinationPort()), true,"***** Test ID:13- DestinationPort in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThETA()), true,"***** Test ID:13- ETA Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThDialog()), true,"***** Test ID:13- Dialog Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThBookingStatus()), true,"***** Test ID:13-  Booking Status Colunm in not shown in Select Chevron in Delivery Booking tool *****");
 		
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThBookingRef()), true,"***** Test ID:13- Booking Ref Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThBookingDate()), true,"***** Test ID:13- Booking Date Colunm in not shown in Select Chevron in Delivery Booking tool *****"); 
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThDestination()), true,"***** Test ID:13- Destination Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThHaulier()), true,"***** Test ID:13- haulier Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThQty()), true,"***** Test ID:13- Qty Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThCartons()), true,"***** Test ID:13- Carton Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThBkdQty()), true,"***** Test ID:13- Bkd Qty in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThBkdCartons()), true,"***** Test ID:13- Bkd Cartons Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThCBM()), true,"***** Test ID:13- CBM Colunm in not shown in Select Chevron in Delivery Booking tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getThBookingStatus()), true,"***** Test ID:13-  Booking Status Colunm in not shown in Select Chevron in Delivery Booking tool *****");
		
 	   objSoftAssert.assertAll();	
 		
   }
    
    
    @Test(priority=35)
    public void test_14_VerifyButtons()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		//containerList =  new ArrayList<String>();	
		
		clearFields();
		
		
		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:14- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:14- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		

	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "FORK_DeliveryBookingCancel"), true,"***** TestId:14- Booking cancel button is not shown in Select chevron in Delivery Booking tool *****");
	 	  
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.checkDoesElementExist(strDeliveryBookingSearchFormName, "FORK_DeliveryBookingDialog"), true,"***** TestId:14- Add Dialog button is not shown in Select chevron in Delivery Booking tool *****");
	 	
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:14- "+ strMessage +"*****");
	    objSoftAssert.assertAll();	
    }
    
    @Test(priority=38)
    public void test_16_verifyDestinationPort()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		//containerList =  new ArrayList<String>();	
		
		clearFields();
		
		
		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:16- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:16- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strDestinationPort))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strDestinationPort,strDeliveryBookingSearchFormName,"Param_DestinationPort"),true," *****  Test ID : 16 - Destination Port is not set Value in Select page of off Dock Location ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 16 - Destination Port is Empty ***** ";
	    }
	   
	 
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName, "FORK_DeliveryBookingRefineSearch"),true," ***** Test ID : 12 - Not able to click Refine Search button in Select page of off Dock Location ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMADeliveryBookingPOM.getDestionationPortM();
	    strMessage = objAdjunoLIMADeliveryBookingPOM.verifyDestinationPort(strDestinationPort,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:16- "+ strMessage +"*****");
	    objSoftAssert.assertAll();	
    }
    
    
    
    @Test(priority=40)
    public void test_17_verifyContainerValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		//containerList =  new ArrayList<String>();	
		
		clearFields();
		
		
		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:17- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:17- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
		
		if(!isNullOrBlank(strValidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strValidContainer, strDeliveryBookingSearchFormName, "Param_Container"),true,"***** Test id:17- Valid Container Number Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"valid Po Number Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:17 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <=objAdjunoLIMADeliveryBookingPOM.getLstContainer().size()-1; i++) {
				if(objAdjunoLIMADeliveryBookingPOM.getLstContainer().get(i).getText().equalsIgnoreCase(strValidContainer)){
					
				}else{
					strMessage = strMessage + "  " +strValidContainer +" container value is not matching with "+objAdjunoLIMADeliveryBookingPOM.getLstContainer().get(i).getText();
				}
			}
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
	//  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:17- "+ strMessage +"*****");
		objSoftAssert.assertAll();
    }

    
    @Test(priority=42)
    public void test_18_verifyDestnationValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
		//containerList =  new ArrayList<String>();	
		
		clearFields();
		
		
		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:18- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:18- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
		
		if(!isNullOrBlank(strDestination)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strDestination, strDeliveryBookingSearchFormName, "Param_DC"),true,"***** Test id:18- Destination Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"valid Destination Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:18 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <=objAdjunoLIMADeliveryBookingPOM.getLstDestination().size()-1; i++) {
				if(objAdjunoLIMADeliveryBookingPOM.getLstDestination().get(i).getText().equalsIgnoreCase(strDestination)){
					
				}else{
					strMessage = strMessage + "  " +strDestination +" Destination value is not matching with "+objAdjunoLIMADeliveryBookingPOM.getLstDestination().get(i).getText();
				}
			}
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
	//  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:18- "+ strMessage +"*****");
		objSoftAssert.assertAll();
    }
    
    @Test(priority=45)
    public void test_19_verifyEtaTo() throws ParseException
    {
 	   	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	
    	String strMessage ="";
    	
   		strETATo= objAdjunoLIMADeliveryBookingPOM.getDate(0,"dd MMM yyyy");
   		Date etaToDate = new SimpleDateFormat("dd MMM yyyy").parse(strETATo);
   		clearFields();
   		
   		
   		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:19- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:19- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
 	    if(!isNullOrBlank(strETATo))
 	    {
 	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strETATo,strDeliveryBookingSearchFormName,"Param_ETDFrom"),true," *****  Test ID : 19 -  ETA From date value is not set in Select page of Delivery Booking Tool ***** ");    	   
 	    }else{
 	    	  strMessage = strMessage + " *****  Test ID : 19 - ETA From Value is Empty ***** ";
 	    }
 	   
 	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:19 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			 List<Date> list = new ArrayList<Date>();  
		     list = objAdjunoLIMADeliveryBookingPOM.getArrivedDate();
		     strMessage = objAdjunoLIMADeliveryBookingPOM.verifyArrivedDate2(etaToDate,list);
		 	
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
 	     
 	  
 	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:19- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=48)
    public void test_20_verifyHaulierValue() throws ParseException
    {
 	   	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	
   		String strMessage ="";
    	
   		
 		wait(4000);   
   		clearFields();
   		
   		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:20- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:20- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
 		
 	    if(!isNullOrBlank(strHaulierValue))
 	    {
 	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strHaulierValue,strDeliveryBookingSearchFormName,"Param_Catalog_Haulier"),true," *****  Test ID : 20 -  Haulier value is not set in Select page of Delivery Booking Tool ***** ");    	   
 	    }else{
 	    	  strMessage = strMessage + " *****  Test ID : 20 - Haulier Value is Empty ***** ";
 	    }
 	   

 	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:20 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			for (int i = 0; i <=objAdjunoLIMADeliveryBookingPOM.getLstHaulier().size()-1; i++) {
				if(objAdjunoLIMADeliveryBookingPOM.getLstHaulier().get(i).getText().equalsIgnoreCase(strHaulierValue)){
					
				}else{
					strMessage = strMessage + "  " +strHaulierValue +" Haulier value is not matching with "+objAdjunoLIMADeliveryBookingPOM.getLstHaulier().get(i).getText();
				}
			}
		 	
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
 	    
 	   
 	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:20- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=50)
    public void test_21_verifyLoadingValue() throws ParseException
    {
 	   	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	
   		String strMessage ="";
    	
   		clearFields();
   		
   		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:21- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:21- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
 		
 	    if(!isNullOrBlank(strLoadingValue))
 	    {
 	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLoadingValue,strDeliveryBookingSearchFormName,"Param_LoadType"),true," *****  Test ID : 21 -  Loading value is not set in Select page of Delivery Booking Tool ***** ");    	   
 	    }else{
 	    	  strMessage = strMessage + " *****  Test ID : 21 -  Loading Value is Empty ***** ";
 	    }
 	   

 	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:21 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			for (int i = 0; i <=objAdjunoLIMADeliveryBookingPOM.getLstLoading().size()-1; i++) {
				if(objAdjunoLIMADeliveryBookingPOM.getLstLoading().get(i).getText().equalsIgnoreCase(strLoadingValue)){
					
				}else{
					strMessage = strMessage + "  " +strLoadingValue +" Loading value is not matching with "+objAdjunoLIMADeliveryBookingPOM.getLstLoading().get(i).getText();
				}
			}
		 	
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
 	    
 	   
 	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:21- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority=52)
    public void test_22_verifyModeValue() throws ParseException
    {
 	   	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveryBookingPOM = new AdjunoLIMADeliveryBookingPOM(driver);
    	
   		String strMessage ="";
    	
   		clearFields();
   		
   		if(!isNullOrBlank(strStatusAny)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strStatusAny, strDeliveryBookingSearchFormName, "Param_Status"),true,"***** Test id:22- stutus Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strLevelOfDetail)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strLevelOfDetail, strDeliveryBookingSearchFormName, "Param_LevelOfDetail"),true,"***** Test id:22- Level of detail Value is not set *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Level of detail Value is Empty in Xml";
		}
		
 		
 	    if(!isNullOrBlank(strModeValue))
 	    {
 	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.setFieldValue(strModeValue,strDeliveryBookingSearchFormName,"Param_Mode"),true," *****  Test ID : 22 -  MOde value is not set in Select page of Delivery Booking Tool ***** ");    	   
 	    }else{
 	    	  strMessage = strMessage + " *****  Test ID : 22 -  Mode Value is Empty ***** ";
 	    }
 	   

 	    objSoftAssert.assertEquals(objAdjunoLIMADeliveryBookingPOM.clickButton(strDeliveryBookingSearchFormName,"FORK_DeliveryBookingRefineSearch"), true,"***** Test id:22 - Refine Search is not clicked in Delivery Booking Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveryBookingPOM.isElementPresent(objAdjunoLIMADeliveryBookingPOM.getTxtNoResult());
		
		if(searchResult==false){
			for (int i = 0; i <=objAdjunoLIMADeliveryBookingPOM.getLstMode().size()-1; i++) {
				if(objAdjunoLIMADeliveryBookingPOM.getLstMode().get(i).getText().equalsIgnoreCase(strModeValue)){
					
				}else{
					strMessage = strMessage + "  " +strModeValue +" Mode value is not matching with "+objAdjunoLIMADeliveryBookingPOM.getLstMode().get(i).getText();
				}
			}
		 	
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
 	    
 	   
 	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:22- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
}
