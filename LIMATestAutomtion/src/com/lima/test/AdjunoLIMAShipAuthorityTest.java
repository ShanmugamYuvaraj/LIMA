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
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.ShipAuthority;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAShipAuthorityPOM;

public class AdjunoLIMAShipAuthorityTest {
	
	WebDriver driver;
	
	long nPage_Timeout = 0;
    String strTestURL;
    
    String strLIMAUserName;
    String strLIMAPassword;
    String strPageTitleShipAuthority;
    String strSearchFormNameShipAuthority;
    String strAuthoriseFormName;
    String strPOStatusFormName;
    String strDailogFormName;
    String strCompleteFormName;
    String strDailogPopUpFormName;
    String strSearchFormNameVendorBooking;
    String strSearchFormNameShipmentBooking;
    boolean bSearchResultsProductsFound = true;
    ArrayList<ShipAuthority> lstSearchResults;
    
    String strInvalidPONumber = "";
    String strPONumber = "";
    String strStatusAwaiting = "";
    String strAuthorisedQuery = "";
    String strComments = "";
    String strStatusQuery = "";
    String strStatusDeclined = "";
    String strStatusAny = "";
    String strStatusAuthorised = "";
    String strDeclinedPONumber = "";
    String strDeclinedComments = "";
    String strAuthoriseComments = "";
    String strAuthorisedNO = "";
    String strAuthorisedYes = "";
    String strEmailID = "";
    String strDailogMsg = "";
    String strShipmentBookingStatus = "";
    String strVendorBookingStatus = "";
    String strProduct = "";
    
    String strTrackNO = "";
       
    NodeList nlSearchParamsShipAuthority;
    NodeList nlAuthoriseGrid;
    NodeList nlDetailsGrid;
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAShipAuthorityPOM objAdjunoLIMAShipAuthorityPOM;
    
    ShipAuthority product;
    ShipAuthority ShipmentBooking;
    
    @BeforeTest
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

            Node testURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testURL.getTextContent();
            
            Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
            strLIMAUserName = limaUserName.getTextContent();

            Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
            strLIMAPassword = limaPassword.getTextContent();
            
            Node pageTitleShipAuthority = (Node) xPath.evaluate("/config/LIMA/Page_Title_Ship_Authority", dDoc, XPathConstants.NODE);
            strPageTitleShipAuthority = pageTitleShipAuthority.getTextContent();
            
            Node SearchFormNameShipAuthority = (Node) xPath.evaluate("config/LIMA/Search_Form_Name_Ship_Authority", dDoc, XPathConstants.NODE);
            strSearchFormNameShipAuthority = SearchFormNameShipAuthority.getTextContent();
            
            Node AuthoriseFormName = (Node) xPath.evaluate("config/LIMA/Authorise_Form_Name", dDoc, XPathConstants.NODE);
            strAuthoriseFormName = AuthoriseFormName.getTextContent();
            
            Node POStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
            strPOStatusFormName = POStatusFormName.getTextContent();
            
            Node DailogFormName = (Node) xPath.evaluate("/config/LIMA/Dailog_Form_Name", dDoc, XPathConstants.NODE);
            strDailogFormName = DailogFormName.getTextContent();
            
            Node CompleteFormName = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
            strCompleteFormName = CompleteFormName.getTextContent();
            
            Node DailogPopUpFormName = (Node) xPath.evaluate("/config/LIMA/Dailog_Popup_Form_Name", dDoc, XPathConstants.NODE);
            strDailogPopUpFormName = DailogPopUpFormName.getTextContent();
            
            Node VendorBookingFormName = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_Vendor_BookingForm", dDoc, XPathConstants.NODE);
            strSearchFormNameVendorBooking = VendorBookingFormName.getTextContent();
            
            Node ShipmentBookingFormName = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Shipment_BookingForm", dDoc, XPathConstants.NODE);
            strSearchFormNameShipmentBooking = ShipmentBookingFormName.getTextContent();
            
            driver = new FirefoxDriver();
            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

            driver.get(strTestURL);
            
            objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
            
            objAdjunoLIMALoginPOM.setUserName(strLIMAUserName);
            objAdjunoLIMALoginPOM.setPassword(strLIMAPassword);
            objAdjunoLIMALoginPOM.clickLogon();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
        
        DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
        try{
        	
        	DocumentBuilder builder1 = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getShipAuthorityXMLDateFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            nlAuthoriseGrid = (NodeList)xPath1.evaluate("/ShipAuthority/Authorise_Grid", dDoc1, XPathConstants.NODESET);
            nlDetailsGrid = (NodeList)xPath1.evaluate("/ShipAuthority/Details_Grid", dDoc1, XPathConstants.NODESET);
            nlSearchParamsShipAuthority = (NodeList)xPath1.evaluate("/ShipAuthority/SearchParams", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsShipAuthority.getLength() -1; i++)
             {
                 if (nlSearchParamsShipAuthority.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		 el = (Element) nlSearchParamsShipAuthority.item(i); 
            		 
            		 strInvalidPONumber = el.getElementsByTagName("Invalid_PONumber").item(0).getTextContent();
            		 
            		// strPONumber = el.getElementsByTagName("PONumber").item(0).getTextContent();
            		 
            		 strDeclinedPONumber = el.getElementsByTagName("Declined_PONumber").item(0).getTextContent();
            		 
            		 strStatusAwaiting = el.getElementsByTagName("Status_Awaiting").item(0).getTextContent();
            		 
            		 strStatusQuery = el.getElementsByTagName("Status_Query").item(0).getTextContent();
            		 
            		 strStatusDeclined = el.getElementsByTagName("Status_Declined").item(0).getTextContent();
            		 
            		 strStatusAny = el.getElementsByTagName("Status_Any").item(0).getTextContent();
            		 
            		 strStatusAuthorised = el.getElementsByTagName("Status_Authorised").item(0).getTextContent();
            		 
            		 strAuthorisedQuery = el.getElementsByTagName("Authorised_Query").item(0).getTextContent();
            		 
            		 strAuthorisedNO = el.getElementsByTagName("Authorised_NO").item(0).getTextContent();
            		 
            		 strAuthorisedYes = el.getElementsByTagName("Authorised_Yes").item(0).getTextContent();
            		 
            		 strComments = el.getElementsByTagName("Comments").item(0).getTextContent();
            		 
            		 strDeclinedComments = el.getElementsByTagName("Declined_Comments").item(0).getTextContent();
            		 
            		 strAuthoriseComments = el.getElementsByTagName("Authorised_Comments").item(0).getTextContent();
            		 
            		 strEmailID = el.getElementsByTagName("Email").item(0).getTextContent();
            		 
            		 strDailogMsg = el.getElementsByTagName("Message").item(0).getTextContent();
            		 
            		 strShipmentBookingStatus = el.getElementsByTagName("ShipmentBooking_Status").item(0).getTextContent();
            		 
            		 strVendorBookingStatus = el.getElementsByTagName("Vendor_Booking_Status").item(0).getTextContent();
            	}
             }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
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
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
    
    @Test(priority = 1)
    public void Test_1_accessShipAuthority()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        
        String strTitle = objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority,objAdjunoLIMAShipAuthorityPOM.getLnkTools(),objAdjunoLIMAShipAuthorityPOM.getLnkShipAuthority());
                
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleShipAuthority))
                bFlag = true;
            else
                bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true, "****test ID:_1_Title of the page is wrong for Ship Authority *****");
        
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getChevSearch()), true, "****test ID:1_Search chevron is not displayed in Ship Authority*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:1_Select chevron is not displayed in Ship Authority*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getChevAuthorise()), true, "****test ID:1_Authorise chevron is not displayed in Ship Authority*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getChevComplete()), true, "****test ID:1_Complete chevron is not displayed in Ship Authority*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 5)
    public void Test_2_checkSearchParamsFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Origin"), true, "****test ID:2_Origin field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Port"), true, "****test ID:2_Origin Port field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Mode"), true, "****test ID:2_Mode field do not exist in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Carrier"), true, "****test ID:2_Carrier field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Vessel"), true, "****test ID:2_Vessel field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_LoadType"), true, "****test ID:2_LoadType field do not exist in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_ETDFrom"), true, "****test ID:2_ETDFrom field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_ETDTo"), true, "****test ID:2_ETDTo field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:2_Status field do not exist in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Group"), true, "****test ID:2_Group field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Department"), true, "****test ID:2_Department field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_CustomerReference"), true, "****test ID:2_Vendor Ref1 field do not exist in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Vendor"), true, "****test ID:2_Vendor field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_SupplierReference"), true, "****test ID:2_Vendor Ref2 field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:2_PO Number field do not exist in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strSearchFormNameShipAuthority, "PARAM_Product"), true, "****test ID:2_Product field do not exist in Ship Authority Search Chevron*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 10)
    public void Test_5_verifyInvalidPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Origin"), true, "****test ID:5_Origin field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Port"), true, "****test ID:5_Origin Port field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Mode"), true, "****test ID:5_Mode field is not cleared in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Carrier"), true, "****test ID:5_Carrier field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Vessel"), true, "****test ID:5_Vessel field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_LoadType"), true, "****test ID:5_LoadType field is not cleared in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_ETDFrom"), true, "****test ID:5_ETDFrom field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_ETDTo"), true, "****test ID:5_ETDTo field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:5_Status field is not cleared in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Group"), true, "****test ID:5_Group field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Department"), true, "****test ID:5_Department field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_CustomerReference"), true, "****test ID:5_Vendor Ref1 field is not cleared in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Vendor"), true, "****test ID:5_Vendor field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_SupplierReference"), true, "****test ID:5_Vendor Ref2 field is not cleared in Ship Authority Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:5_PO Number field is not cleared in Ship Authority Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Product"), true, "****test ID:5_Product field is not cleared in Ship Authority Search Chevron*****");
    	
    	wait(2000);
    	if (!isNullOrBlank(strInvalidPONumber)){
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strInvalidPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:5_Invalid PO Number is not set in PO Number field under Ship Authority Search Chevron*****");
    	}
    	else
    	{
    		strMessage = strMessage + " PO number value is empty";
    	}
    	if (!isNullOrBlank(strStatusAwaiting)){
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAwaiting, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:5_Status is not set to Awaiting in Status field under Ship Authority Search page *****");
    	}
    	else
    	{
    		strMessage = strMessage + " Status value is empty ";
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:5_Select chevron is not clicked under Ship Authority Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtErrMsg()), true, "****test ID:5_\"No items were found.\" message is not displayed when Invalid PO Number is set in PO Number field under Search page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 15)
    public void Test_6_verifyAwaitingStausValidPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:6_PO Number field is not cleared in Ship Authority Select Chevron*****");
    	if (!isNullOrBlank(strStatusAwaiting)){
        	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAwaiting, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:6_Status is not set to Awaiting in Status field in Select chevron*****");
        	}
        	else
        	{
        		strMessage = strMessage + " Status is empty ";
        	}
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButton(strSearchFormNameShipAuthority, "FORK_ShipAuthority_RefineSearch"), true, "****test ID:6_Refine Search button is not clicked in Select Chevron");
    	strPONumber = objAdjunoLIMAShipAuthorityPOM.getTrPONumber().getText();
    	if(!isNullOrBlank(strPONumber)){
    		objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:6_Valid PO Number value is not set in PO Number field in Select Chevron");
    		}
    		else
    		{
    			strMessage = strMessage + " PO Number is empty ";
    		}
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButton(strSearchFormNameShipAuthority, "FORK_ShipAuthority_RefineSearch"), true, "****test ID:6_Refine Search button is not clicked in Select Chevron");
    	
    	List<ShipAuthority> list = new ArrayList<ShipAuthority>();  
    	list = objAdjunoLIMAShipAuthorityPOM.getStaus();
    	
    	strMessage = objAdjunoLIMAShipAuthorityPOM.verifyStaus(strStatusAwaiting,list);
    	//strPONumber = list.get(0).getPoNumber();
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "****test ID:6_"+strMessage+"*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 20)
    public void Test_7_verifyGridHeader()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThMode()), true, "****test ID:7_Mode is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThOriginPort()), true, "****test ID:7_Origin Port is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThDestinationPort()), true, "****test ID:7_Destination is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThVessel()), true, "****test ID:7_Vessel is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThVoyage()), true, "****test ID:7_Voyage is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThCarrier()), true, "****test ID:7_Carrier is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThETDDate()), true, "****test ID:7_ETD Date is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThETADate()), true, "****test ID:7_ETA Date is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThVendor()), true, "****test ID:7_Vedor is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThFreightTerms()), true, "****test ID:7_FreightTerms is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThLoadType()), true, "****test ID:7_Load Type is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThCFSClosingDate()), true, "****test ID:7_CFSClosingDate is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThVendorReference()), true, "****test ID:7_VendorReference is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThVendorRef()), true, "****test ID:7_VendorRef is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThPONumber()), true, "****test ID:7_PO Number is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThProduct()), true, "****test ID:7_Product is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThSKU()), true, "****test ID:7_SKU is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThEventCode()), true, "****test ID:7_Event code is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThDescription()), true, "****test ID:7_Description is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThPOShipDate()), true, "****test ID:7_PO Ship Date is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThDCDate()), true, "****test ID:7_DC date is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThPOQty()), true, "****test ID:7_PO Qty is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThOpenQty()), true, "****test ID:7_Open Qty is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThBkdQty()), true, "****test ID:7_Bkd Qty is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThBkdCtns()), true, "****test ID:7_BkdCtns is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThBkdCBM()), true, "****test ID:7_BkdCBM is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThPackType()), true, "****test ID:7_Pack Type is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThDC()), true, "****test ID:7_DC is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThDueIntoCFS()), true, "****test ID:7_DueIntoCFS is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThQC()), true, "****test ID:7_QC is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThBookingKPI()), true, "****test ID:7_Booking KPI is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThAuthorityStatus()), true, "****test ID:7_Authority Status is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThComments()), true, "****test ID:7_Comments is not displayed in the resultant grid under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThLastAuthosiedBy()), true, "****test ID:7_LastAuthosiedBy is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThStatus()), true, "****test ID:7_Status is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThDailog()), true, "****test ID:7_Dailog is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getThLandedCostVarience()), true, "****test ID:7_LandedCostVarience is not displayed in the resultant grid under Select page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 21)
    public void Test_8_verifyPONumberDetails()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	product = objAdjunoLIMAShipAuthorityPOM.getShipAuthorityData();
    	
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipmentBooking, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipmentBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipmentBooking, "PARAM_Mode"), true, "****test ID:8_Mode is not cleared in Shipment Booking Search page");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipmentBooking, "PARAM_OrderNumber"), true, "****test ID:8_PO number is not set in Po Number field in Shipment Booking Search page");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strShipmentBookingStatus, strSearchFormNameShipmentBooking, "PARAM_Status"), true, " ****test ID:8_status is not set in Status field in Shipment Booking Search page");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()),true, "****test ID:8_Select chevron is not clickled in Shipment Booking Search page");
    	strProduct = objAdjunoLIMAShipAuthorityPOM.getTrProduct().getText();
    	ShipmentBooking = objAdjunoLIMAShipAuthorityPOM.getShipmentBookingData();
    	
    	objSoftAssert.assertEquals(product.getOriginPort().equalsIgnoreCase(ShipmentBooking.getSBOriginPort()), true, "****test ID:8_Origin port values in Shipment Booking Search page do not match with Origin Port value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getDestinationPort().equalsIgnoreCase(ShipmentBooking.getSBDestinationPort()), true, "****test ID:8_Destination port value in Shipment Booking Search page do not match with Destination port value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getPoNumber().equalsIgnoreCase(ShipmentBooking.getSBPONumber()), true, "****test ID:8_PO Number value in Shipment Booking Search page do not match with Po Number value in Ship Authority for the same PO Number*****");
        
        objSoftAssert.assertEquals(product.getProduct().equalsIgnoreCase(ShipmentBooking.getSBProduct()), true, "****test ID:8_Product value in Shipment Booking Search page do not match with Product value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getPoQty().equalsIgnoreCase(ShipmentBooking.getSBPOQty()), true, "****test ID:8_PO Qty value in Shipment Booking Search page do not match with PO Qty value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getBkdQty().equalsIgnoreCase(ShipmentBooking.getSBBkdQty()), true, "****test ID:8_Bkd Qty value in Shipment Booking Search page do not match with Bkd Qty value in Ship Authority for the same PO Number*****");
        
        objSoftAssert.assertEquals(product.getETD().equalsIgnoreCase(ShipmentBooking.getSBETDDate()), true, "****test ID:8_ETD value in Shipment Booking Search page do not match with ETD value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getETA().equalsIgnoreCase(ShipmentBooking.getSBETADate()), true, "****test ID:8_ETA  value in Shipment Booking Search page do not match with ETA value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getETD().equalsIgnoreCase(ShipmentBooking.getSBETDDate()), true, "****test ID:8_ETD value mismatch*****");
        
        objSoftAssert.assertEquals(product.getVessel().equalsIgnoreCase(ShipmentBooking.getSBVessel()), true, "****test ID:8_Vessel  value in Shipment Booking Search page do not match with Vessel value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getVoyage().equalsIgnoreCase(ShipmentBooking.getSBVoyage()), true, "****test ID:8_Voyage value in Shipment Booking Search page do not match with Voyage value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getCarrier().equalsIgnoreCase(ShipmentBooking.getSBCarrier()), true, "****test ID:8_Carrier  value in Shipment Booking Search page do not match with Carrier value in Ship Authority for the same PO Number*****");
        
        objSoftAssert.assertEquals(product.getFrieghtTerms().equalsIgnoreCase(ShipmentBooking.getSBFrieghtTerms()), true,"****test ID:8_FrieghtTerms value in Shipment Booking Search page do not match with Freight Terms value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getPOShipDate().equalsIgnoreCase(ShipmentBooking.getSBPOShipDate()), true, "****test ID:8_POShipDate  value in Shipment Booking Search page do not match with PO Ship Date value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getOpenQty().equalsIgnoreCase(ShipmentBooking.getSBOpenQty()), true, "****test ID:8_Open Qty  value in Shipment Booking Search page do not match with Open Qty value in Ship Authority for the same PO Number*****");
        
        objSoftAssert.assertEquals(product.getCtns().equalsIgnoreCase(ShipmentBooking.getSBCtns()), true, "****test ID:8_Ctns  value in Shipment Booking Search page do not match with Ctns value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getCbm().equalsIgnoreCase(ShipmentBooking.getSBCbm()), true, "****test ID:8_Cbm  value in Shipment Booking Search page do not match with Cbm value in Ship Authority for the same PO Number*****");
        objSoftAssert.assertEquals(product.getPackType().equalsIgnoreCase(ShipmentBooking.getSBPackType()), true, "****test ID:8_PackType  value in Shipment Booking Search page do not match with Pack type value in Ship Authority for the same PO Number*****");
        
    	objSoftAssert.assertAll();
    }
    
     @Test(priority = 25)
    public void Test_9_verifyErrMsgAtAuthorise()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipAuthority());
    	if (!isNullOrBlank(strStatusAwaiting)){
        	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAwaiting, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:9_Status is not set to Awaiting in Status field under Search page*****");
        	}
        	else
        	{
        		strMessage = strMessage + " ****test ID:9_Status is null***** ";
        	}
    	if(!isNullOrBlank(strPONumber)){
    		objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:9_Valid PO Number value is not set in Po Number field under Search page*****");
    		}
    		else
    		{
    			strMessage = strMessage + " ****test ID:9_PO Number is null ";
    		}
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:9_Select chevron is not clicked in Search page*****");

    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevAuthorise1()), true, "****test ID:9_Authorise chevron is not clicked in Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtMandatoryErrMsg()), true, "****test ID:9_You must make selection(s) before progressing msg is not displayed when No Po's are Selected in the resultant grid under Search page*****");
    	
    	objSoftAssert.assertAll();
    		
    }
    
     @Test(priority = 30)
    public void Test_10_verifyAuthorisePage()
    
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickCheckBox(objAdjunoLIMAShipAuthorityPOM.getThCheckBox()),true, "****test ID:10_CheckBox is not clicked in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevAuthorise1()), true, "****test ID:10_Authorise chevron is not clicked in Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strAuthoriseFormName), true, "****test ID:10_Authorise page is not loaded after clicking Authorise chevron under Select page*****");
//    	/*if(bSearchResultsProductsFound)
//		  {
//				String strReturnMessage = objAdjunoLIMAShipAuthorityPOM.verifyProductsDataOnGrid(strAuthoriseFormName,"GridAuthorise",lstSearchResults);
//				
//			  	objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, strReturnMessage);
//			  
//		  }
//		  else
//			  objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "No Products in Search Results.");//
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 35)
    public void Test_11_verifyAuthoriseGridColumns()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage="";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesFieldsExist(strAuthoriseFormName, "GridAuthorise"), true, "****test ID:11_Authorise grid is not displayed under Authorise Chevron");   
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMAShipAuthorityPOM.getCaptionsList(strAuthoriseFormName,"GridAuthorise");
         
         strMessage = objAdjunoLIMAShipAuthorityPOM.verifyCaptionsONGrid(list,nlAuthoriseGrid,14);
                
         objSoftAssert.assertEquals(strMessage.equals(""), true," ****test ID:11_Authorise grid Caption: "+strMessage+"*****");
                     
         objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 40)
    public void Test_13_verifyDetailsInHyperLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = objAdjunoLIMAShipAuthorityPOM.isHyperlinkPresent(strAuthoriseFormName, "GridAuthorise", 0, "DetailLink");
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "****test ID:13_details is not a hyperlink in Authorise page*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
    	
    	list = objAdjunoLIMAShipAuthorityPOM.getCaptionsList(strAuthoriseFormName,"DetailGrid");
    	strMessage = objAdjunoLIMAShipAuthorityPOM.verifyCaptionsONGrid(list,nlDetailsGrid,19);
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"****test ID:13_Details grid Caption: "+strMessage+"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getCloseBtn()), true, "****test ID:13_Close Button is not clicked in \"Details pop up \" under Authorise page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 45)
    public void Test_15_verifyValidationErrorMsg()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:15_Complete chevron is not clicked in Authorise page*****");
    	String strMessage = objAdjunoLIMAShipAuthorityPOM.getTxtValidationErrMsg().getText();
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("You must correct all validation failures before progressing."), true, "****test ID:15_You must correct all validation failures before progressing. message is not displayed when all the Mandatory fields are filled in Authorise page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 50)
    public void Test_15_verifyMandatoryFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	String strAuthorisedMandatoryErrMsg = objAdjunoLIMAShipAuthorityPOM.getValidationMessageGridElement(strAuthoriseFormName, "GridAuthorise", 0, "Authorised");
    	String strCommentsMandatoryErrMsg = objAdjunoLIMAShipAuthorityPOM.getValidationMessageGridElement(strAuthoriseFormName, "GridAuthorise", 0, "Comments");
    	
    	if(!strAuthorisedMandatoryErrMsg.equals("") || !strCommentsMandatoryErrMsg.equals("") )
    	{
    		if(strAuthorisedMandatoryErrMsg.contains("&#39;"))
    		{
    		strAuthorisedMandatoryErrMsg = strAuthorisedMandatoryErrMsg.replace("&#39;", "'");
    		objSoftAssert.assertEquals(strAuthorisedMandatoryErrMsg.equalsIgnoreCase("> 'Authorised' is a required field"), true, "****test ID:15_'Authorised' is the required field msg is not displayed as \"Authorised\" field is the mandatory field in Authorise page*****");
    		}
    		if(strCommentsMandatoryErrMsg.contains("&#39;"))
    		{
    		strCommentsMandatoryErrMsg = strCommentsMandatoryErrMsg.replace("&#39;", "'");
    		objSoftAssert.assertEquals(strCommentsMandatoryErrMsg.equalsIgnoreCase("> 'Comments' is a required field"), true, "****test ID:15_'Comments is the required field' msg is not displayed as \"Comments\" field is mandatory field in Authorise page*****");
    		}
    	}
    	else
    	{
    		strMessage = "****test ID:15_"+strMessage + "validation message is empty in Authorise page*****";
    	}
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 52)
    public void Test_16_17_18_verifySelectedAuthorisedValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage;
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Authorised", strAuthorisedQuery), true, "****test ID:17_Authorised field is not set to Query in Authorise page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Comments", strComments), true, "****test ID:16_Comments field is not set in Authorise page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "Complete chevron is not clicked under Authorise page*****");
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strCompleteFormName), true, "****test ID:18_Complete page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtProcessedMsg()), true, "****test ID:18_'100% processed' message is not displayed in Authorise page*****");

    	strMessage = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getTxtProcessedMsg());
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("Processed 100%"), true, "****test ID:18_Processed 100% msg is not displayed is wrong in Complete page*****");
    	wait(7000);
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 55)
    public void Test_19_verifyViewDetail()
    {
    	
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
   
    	objAdjunoLIMAShipAuthorityPOM.getBtnViewDetail().click();
    	
    	wait(3000);
    	String strDespatchDetails = objAdjunoLIMAShipAuthorityPOM.getTxtViewDetail().getText();   	
    //	String strTrackReferenceNo = strDespatchDetails.substring(strDespatchDetails.indexOf("LIMAUAT"),strDespatchDetails.indexOf("].", strDespatchDetails.indexOf("LIMAUAT")));
    	String[] vals = strDespatchDetails.split("Track Reference ");
    
    	vals[1] = vals[1].replace("[", " ").replace("]", " ");
    	String valss[] = vals[1].split(" ",12);
    	
    	strTrackNO = valss[1];
    	System.out.println("track ref num: - " + strTrackNO);
    //	System.out.println("track ref num: - " + strTrackReferenceNo);
    	wait(2000);
    		
    
    }
    
    @Test(priority = 60)
    public void Test_19_verifyTrackPage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority,objAdjunoLIMAShipAuthorityPOM.getLnkTrack(),objAdjunoLIMAShipAuthorityPOM.getLnkEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getFldRefNo()), true,"****test ID:19_Reference field is not found in \"Edit or Create track page\"*****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForWebElement(objAdjunoLIMAShipAuthorityPOM.getFldRefNo(),strTrackNO), true,"****test ID:19_track No is not set in Reference field under \"Edit or Create track page\"*****");
        }else{
        	strMessage =strMessage + " Track number is empty ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getBtnTrackApply()), true,"****test ID:19_Apply button is not clicked in \"Edit or Create track page\"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 65)
    public void Test_20_checkShipmentPageDisplay() throws ParseException{        
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        String strMessage ="";
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtShipmentTrack()), true, "****test ID:20_Shipment track page is not loaded*****");
                        
       long min = objAdjunoLIMAShipAuthorityPOM.getTrackValue(strLIMAUserName);
       
       if(min<=350){
                    
       }else{
           strMessage = "Ship Authority is not updated to today's date";
       }
                
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
                       
       objSoftAssert.assertAll();
    }
    
    @Test(priority = 70)
    public void Test_22_verifyPOStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        String strMessage = "";
        objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkFind(), objAdjunoLIMAShipAuthorityPOM.getLnkPOStatus());
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strPOStatusFormName), true, "****test ID:22_PO Status Page is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "****test ID:22_PO Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strProduct, strPOStatusFormName, "Param_17Item"), true, "****test ID:22_Product Number is not set in PO Status page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getBtnRun()), true, "****test ID:22_Run button is not clicked in PO Status page*****");
    	try{
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getPoStatusReport()), true, "****test ID:22_PO Status report page is not loaded*****");
    	
    	String strStatus = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getPoStatus_Status());
    	
    	objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Authority Query"), true, "****test ID:22_Status mismatch*****");	
    	}
    	catch(NoSuchElementException e)
    	{
    		strMessage = "PO Status report is not displayed";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "****test ID:22_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 75)
    public void Test_23_verifyPONumberInAwaitingStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipAuthority());
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strSearchFormNameShipAuthority), true, "****test ID:23_Search page is not loaded*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAwaiting, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:23_Status is not set to Awaiting*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:23_Valid PO Number value is not set*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:23_Select Chevron is no clicked under Search page*****");
      
        wait(4000);
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtErrMsg()), true, "****test ID:23_No items were found. message is not displayed*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 80)
    public void Test_24_verifyPONumberInQueryStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        String strMessage="";
        wait(3000);
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusQuery, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:24_Status is not set to Awaiting*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:24_Valid PO Number value is not set*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButton(strSearchFormNameShipAuthority, "FORK_ShipAuthority_RefineSearch"), true, "****test ID:24_Refine Search button is not clicked*****");
        
        List<ShipAuthority> list = new ArrayList<ShipAuthority>();  
    	list = objAdjunoLIMAShipAuthorityPOM.getStaus();
    	
    	strMessage = objAdjunoLIMAShipAuthorityPOM.verifyStaus(strStatusQuery,list);
    	strPONumber = list.get(0).getPoNumber();
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "****test ID:24_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 85)
    public void Test_25_verifyQueryStatusInAuthorisedPage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickCheckBox(objAdjunoLIMAShipAuthorityPOM.getThCheckBox()),true, "****test ID:25_CheckBox is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevAuthorise1()), true, "****test ID:25_Authorise chevron is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strAuthoriseFormName), true, "****test ID:25_Authorise page is not loaded*****");
    	String strAuthoriseStatus = objAdjunoLIMAShipAuthorityPOM.getGridCellValue(strAuthoriseFormName, "GridAuthorise", 0, "Authorised");
    	if(!isNullOrBlank(strAuthoriseStatus))
    	{
    		objSoftAssert.assertEquals(strAuthoriseStatus.equalsIgnoreCase("Query"), true, "*****Status is not Query");
    	}
    	else
    	{
    		strAuthoriseStatus = strAuthoriseStatus + "Status is null";
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Authorised",strAuthorisedNO ), true, "****test ID:25_Authorised field is not set to N*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Comments", strDeclinedComments), true, "****test ID:25_Comments field is not set*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:25_Complete chevron is not clicked under Authorise page*****");
    	objSoftAssert.assertAll();
    }
      
    @Test(priority = 90)
    public void Test_28_verifyDeclinedPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        
        objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipAuthority());
        String strMessage = "";
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusDeclined, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:28_Status is not set to declined");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:28_PO number is not set for declined status");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:28_Select Chevron is not clicked");
        List<ShipAuthority> list = new ArrayList<ShipAuthority>();  
    	list = objAdjunoLIMAShipAuthorityPOM.getStaus();
    	
    	strMessage = objAdjunoLIMAShipAuthorityPOM.verifyStaus(strStatusDeclined,list);
    	
    	objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickCheckBox(objAdjunoLIMAShipAuthorityPOM.getThCheckBox()),true, "****test ID:28_CheckBox is not clicked");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevAuthorise1()), true, "****test ID:28_Authorise chevron is not clicked");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strAuthoriseFormName), true, "****test ID:28_Authorise page is not loaded");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Comments",""), true,"****test ID:28_Comments field is not cleared");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:28_Complete chevron is not clicked under Authorise page");
    	
    	String strCommentsMandatoryErrMsg = objAdjunoLIMAShipAuthorityPOM.getValidationMessageGridElement(strAuthoriseFormName, "GridAuthorise", 0, "Comments");
    	if(!strCommentsMandatoryErrMsg.equals(""))
    	{
    		if(strCommentsMandatoryErrMsg.contains("&#39;"))
    		{
    		strCommentsMandatoryErrMsg = strCommentsMandatoryErrMsg.replace("&#39;", "'");
    		objSoftAssert.assertEquals(strCommentsMandatoryErrMsg.equalsIgnoreCase("> 'Comments' is a required field"), true, "****test ID:28_'Comments is the required field' msg is not displayed");
    		}	
    	}
    	else
    	{
    		strMessage = "****test ID:28_"+strMessage + "validation message is null*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"****test ID:28_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 95)
    public void Test_30_31_32_33_verifyDeclinedCompleteProcess()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Comments", strDeclinedComments), true, "****test ID:30_Comment is not set*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:31_Complete chevron is not clicked under Authorise page*****");
    	strMessage = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getTxtProcessedMsg());
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("Processed 100%"), true, "****test ID:31_Processed 100% msg is not displayed*****");
    	
    	objAdjunoLIMAShipAuthorityPOM.getBtnViewDetail().click();
    	
    	wait(3000);
    	String strDespatchDetails = objAdjunoLIMAShipAuthorityPOM.getTxtViewDetail().getText();   	
    	//String strTrackReferenceNo = strDespatchDetails.substring(strDespatchDetails.indexOf("LIMAUAT"),strDespatchDetails.indexOf("].", strDespatchDetails.indexOf("LIMAUAT")));
    	String[] vals = strDespatchDetails.split("- Reference ");
    
    	vals[1] = vals[1].replace("[", " ").replace("]", " ");
    	String valss[] = vals[1].split(" ",12);
    	
    	strTrackNO = valss[1];
    	System.out.println("track ref num: - " + strTrackNO);
    	//System.out.println("track ref num1: - " + strTrackReferenceNo);
    	wait(2000);
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority,objAdjunoLIMAShipAuthorityPOM.getLnkTrack(),objAdjunoLIMAShipAuthorityPOM.getLnkEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getFldRefNo()), true,"****test ID:33_Reference field is not exist*****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForWebElement(objAdjunoLIMAShipAuthorityPOM.getFldRefNo(),strTrackNO), true,"****test ID:33_Reference field value is not set*****");
        }else{
        	strMessage =strMessage + " Track number is null ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getBtnTrackApply()), true,"****test ID:33_Apply button is not clicked on track edit page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 100)
    public void Test_34_checkShipmentPageDisplayforDeclinedStatus() throws ParseException
    {        
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        String strMessage ="";
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtShipmentTrack()), true, "****test ID:34_Shipment track page is not loaded*****");
                        
       long min = objAdjunoLIMAShipAuthorityPOM.getTrackValue(strLIMAUserName);
       if(min<=350){
                    
       }else
       {
           strMessage = "****test ID:34_Not a todays date*****";
       }
                
       objSoftAssert.assertEquals(strMessage.equals(""), true,"****test ID:34_"+strMessage+"*****"); 
       
       objSoftAssert.assertAll();
    }
    
    @Test(priority = 105)
    public void Test_36_verifyDeclinedPONumberPOStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        String strMessage = "";
        objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkFind(), objAdjunoLIMAShipAuthorityPOM.getLnkPOStatus());
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strPOStatusFormName), true, "****test ID:36_PO Status Page is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "****test ID:36_PO Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strProduct, strPOStatusFormName, "Param_17Item"), true, "****test ID:36_Product Number is not set in PO Status page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getBtnRun()), true, "****test ID:36_Run button is not clicked*****");
    	try{
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getPoStatusReport()), true, "****test ID:36_PO Status report page is not loaded*****");
    	wait(3000);
    	String strStatus = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getPoStatus_Status());
    	
    	objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Declined to Ship"), true, "****test ID:36_\"Declined to Ship\" Status is not displayed when the PO Number is declined in Ship Authority*****");	
    	}
    	catch(NoSuchElementException e)
    	{
    		strMessage = "PO Status report is not displayed";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "****test ID:36_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 110)
    public void Test_37_verifyDeclinedPONumberWithAwaitingStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        
        objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipAuthority());
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:37_Declined PO Number is not set in Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAwaiting, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:37_Status is not set to Awaiting in Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:37_Select Chevron is not clicked in Search page*****");
        wait(4000);
        String strMessage = objAdjunoLIMAShipAuthorityPOM.getTxtErrMsg().getText();
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("No items were found."), true, "****test ID:37_No items were found message is not displayed when Declined PO number is searched in Awaiting Status under Search page*****");
        objSoftAssert.assertAll(); 
    }
    
    @Test(priority = 115)
    public void Test_38_verifyDeclinedPONumberWithAnyStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
        String strMessage = "";
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipAuthority, "PARAM_Product"), true, "****test ID:38_Product field is not cleared in Select page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:38_Declined PO Number is not setin PO Number field under Select page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAny, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:38_Status is not set to Any in Status field in Select page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButton(strSearchFormNameShipAuthority, "FORK_ShipAuthority_RefineSearch"), true, "****test ID:38_Refine search button is not clicked in Select page*****");
        List<ShipAuthority> list = new ArrayList<ShipAuthority>(); 
        wait(2000);
    	list = objAdjunoLIMAShipAuthorityPOM.getStaus();
    	
    	strMessage = objAdjunoLIMAShipAuthorityPOM.verifyStaus(strStatusDeclined,list);
    	
    	objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
    	
    	//setting PO Number as Authorise
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickCheckBox(objAdjunoLIMAShipAuthorityPOM.getThCheckBox()),true, "****test ID:38_CheckBox is not clicked in the resultant grid under search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevAuthorise1()), true, "****test ID:38_Authorise chevron is not clicked under Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strAuthoriseFormName), true, "****test ID:38_Authorise page is not loaded after clicking Authorise chevron under Select page");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Authorised",strAuthorisedYes ), true, "****test ID:38_Authorised field is not set to N in Authorise page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValueForGridCell(strAuthoriseFormName, "GridAuthorise", 0, "Comments", strAuthoriseComments), true, "****test ID:38_Comments field is not set in Authorise page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:38_Complete chevron is clicked in Authorise page*****");
    	
        objSoftAssert.assertAll();
    }
     
    @Test(priority = 130)
    public void Test_50_51_52_verifyAuthorisedPONumberTrackDetails() throws ParseException
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	Test_19_verifyTrackPage();
    	Test_20_checkShipmentPageDisplay();
    	
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkFind(), objAdjunoLIMAShipAuthorityPOM.getLnkPOStatus());
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strPOStatusFormName), true, "****test ID:52_PO Status Page is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "****test ID:52_PO Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strProduct, strPOStatusFormName, "Param_17Item"), true, "****test ID:52_Product Number is not set in PO Status page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getBtnRun()), true, "****test ID:52_Run button is not clicked in PO Status page*****");
     	try
     	{
     	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getPoStatusReport()), true, "****test ID:52_PO Status report page is not loaded*****");
     	
     	String strStatus = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getPoStatus_Status());
     	
     	objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Authorised to Ship"), true, "****test ID:52_Status is not found as \"Authorised to Ship\" in  PO Status report for the PO Number which is Authorised in Ship Authority*****");
     	}
     	catch(NoSuchElementException e)
     	{
     		strMessage = "****test ID:52_No Records are displayed in PO Status page*****";
     	}
     	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
     	objSoftAssert.assertAll();
    }
    
    @Test(priority = 135)
    public void Test_40_verifyPONumberInAuthoriseStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage ="";
    	
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipAuthority, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipAuthority());
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipAuthority, "PARAM_PONumber"), true, "****test ID:40_PO Number is not set in Po Number field under Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strStatusAuthorised, strSearchFormNameShipAuthority, "PARAM_Status"), true, "****test ID:40_Status is not set to Awaiting in Status field under Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:40_Select Chevron is not clicked in Search page*****");
        
    	List<ShipAuthority> list = new ArrayList<ShipAuthority>();  
    	list = objAdjunoLIMAShipAuthorityPOM.getStaus();
    	
    	strMessage = objAdjunoLIMAShipAuthorityPOM.verifyStaus(strStatusAuthorised,list);
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "****test ID:40_"+strMessage+"*****");
    	
    	objSoftAssert.assertAll();    	
    }
    
    @Test(priority = 140)
    public void Test_58_59_60_clickAddDailog()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickCheckBox(objAdjunoLIMAShipAuthorityPOM.getThCheckBox()), true,"****test ID:58_Check box is not clicked in the resultant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButton(strSearchFormNameShipAuthority, "FORK_ShipAuthorityDialog"), true, "****test ID:58_Add Dailog button is not clicked in Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strDailogFormName), true, "****test ID:59_Dailog page is not loaded*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesElementExist(strDailogFormName, "DialogSubject"), true, "****test ID:60_Subject field is not displayed in dailog page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesElementExist(strDailogFormName, "DialogMessage"), true, "****test ID:60_Message field is not displayed in dailog page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.checkDoesElementExist(strDailogFormName, "CopyEmail"), true, "****test ID:60_'Copy by email to' is not displayed in dailog page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 145)
    public void Test_61_verifyDailogPageMandatoryField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strDailogFormName, "DialogSubject"), true, "****test ID:61_Subject field is not set in dailog page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strEmailID, strDailogFormName, "CopyEmail"), true, "****test ID:61_CopyEmail field is not set in dailog page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:61_Complete Chevron is not clicked under Dailog page*****");
    	
    	String strMandatoryMsg = objAdjunoLIMAShipAuthorityPOM.getValidationMessageElement(strDailogFormName, "DialogMessage");
    	if(!strMandatoryMsg.equals(""))
    	{
    		if(strMandatoryMsg.contains("&#39;"))
    		{
    			strMandatoryMsg = strMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strMandatoryMsg.equalsIgnoreCase("> 'Message' is a required field"), true, "****test ID:61_> 'Message' is a required field msg is not displayed as Message is the mandatory field in dailog page*****");
    		}	
    	}
    	
    	else
    	{
    		strMessage = "****test ID:61_"+strMessage + "Message field validation message is empty"+"*****";	
    	}
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 150)
    public void Test_62_verifyDailogUpdateMsg()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	//String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strDailogMsg, strDailogFormName, "DialogMessage"), true, "****test ID:62_Massage field is not set in dailog page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevComplete1()), true, "****test ID:62_Complete chevron is not clicked under dailog page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtUpdateMsg()), true, "****test ID:62_updated message field is not displayed in dailog page*****");
    	
//    	strMessage = objAdjunoLIMAShipAuthorityPOM.getElement(strCompleteFormName, "UpdateMessage").getText();
//    			
//    	System.out.println("1:"+strMessage);
//    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("1 Dialog has been updated."), true, "****test ID:62_'1 Dialog has been updated.' message is not displayed");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 155)
    public void Test_63_verifyDailogViewDetail()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getBtnViewDetail()), true, "****test ID:63_View Deatil button is not clicked in Dailog Complete page*****");
    	//String str = objAdjunoLIMAShipAuthorityPOM.getTxtViewDetail().getText(); 
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSearch1()), true, "****test ID:63_Search Chevron is not clicked in Dailog Complete page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:63_Select Chevron is not clicked in Search page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 160)
    public void Test_65_verifyDailogColumn()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	wait(5000);
    	//objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.isElementPresent(objAdjunoLIMAShipAuthorityPOM.getTxtNewDailog()), true, "1 New Dailog message is not displayed");
    	String strhref = objAdjunoLIMAShipAuthorityPOM.getLnkDailog().getAttribute("onclick");
        if(!isNullOrBlank(strhref))
        {
        
        }
        else
        {
            strMessage = strMessage + "It is not a Link";
        }
        objSoftAssert.assertEquals(strMessage.equals(""), true,"****test ID:65_"+strMessage+"*****");
    	//objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.getLnkDailog().isDisplayed(), true, "New Dailog Message is not displayed");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 165)
    public void Test_66_verifyDailogPopupPanel()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	//clicking on dailog hyprelink
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getLnkDailog()), true, "Dailog hyperLink is not clicked for the dailog added PO Number under Select page");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strDailogPopUpFormName), true, "****test ID:65_Dailog pop up is not displayed*****");
    	wait(3000);
    	
//    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.getCCmsg().getText().contains(strEmailID),true,"****test ID:65_CC does not match in Dailog pop up*****");
//        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.getNewSubject().getText().equalsIgnoreCase(strPONumber),true,"****test ID:65_Subject does not match in Dailog pop up*****");
//        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.getTxtmsg().getText().equalsIgnoreCase(strDailogMsg),true,"****test ID:65_Message does not match in Dailog pop up*****");
        objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickButtonUsingWebElement(objAdjunoLIMAShipAuthorityPOM.getCloseBtn()), true, "****test ID:65_Dailog popup is not closed in Dailog pop up*****");
        objSoftAssert.assertAll();
        
    }
    
    @Test(priority = 170)
    public void Test_68_69_verifyPONumberStatusInVendorBooking()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	String strMessage = "";
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "****test ID:68_Vendor booking Search page is not displayed*****");
    	wait(2000);
    	Actions action = new Actions(driver);
        action.moveToElement(objAdjunoLIMAShipAuthorityPOM.getLnkLogOff()).perform();
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strVendorBookingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "****test ID:68_vendor booking status is not set to booking made in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "****test ID:68_vendor booking po number is not set to Ship authorised Po Number*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:68_Selet Chevron is not clicked under vendor booking search page*****");
    	try
    	{
    	if(objAdjunoLIMAShipAuthorityPOM.getVendorBookingPONumber().isDisplayed())
    	{
    	String strVBPONumber =objAdjunoLIMAShipAuthorityPOM.getVendorBookingPONumber().getText();
    	objSoftAssert.assertEquals(strVBPONumber.equalsIgnoreCase(strPONumber), true, "****test ID:68_PO Number is not displayed under Vendor Booking*****");
    	
    	String strVendorBookingStatus = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getVendorBookingAuthoriseStatus());
    	objSoftAssert.assertEquals(strVendorBookingStatus.equalsIgnoreCase("Authorised"), true, "****test ID:69_Vendor booking status is not dispalyed as Authorised*****");
    	}
    	}
    	catch(NoSuchElementException e)
    	{
    		 strMessage = "****test ID:68_Authorised PO Number is not displayed under Booked status in Vendor Booking*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "****test ID:68_"+strMessage+"*****");
    	
    	objSoftAssert.assertAll();
    	
    }
        
    @Test(priority = 180)
    public void Test_70_71_verifyPONumberStatusInShipmentBooking()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAShipAuthorityPOM = new AdjunoLIMAShipAuthorityPOM(driver);
    	
    	objAdjunoLIMAShipAuthorityPOM.callMouseHover(strSearchFormNameShipmentBooking, objAdjunoLIMAShipAuthorityPOM.getLnkTools(), objAdjunoLIMAShipAuthorityPOM.getLnkShipmentBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.verifyPageIsLoaded(strSearchFormNameShipmentBooking), true, "****test ID:70_Shipment booking Search page is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clearInputField(strSearchFormNameShipmentBooking, "PARAM_Mode"), true, "****test ID:70_Mode field is not cleared in Shipment Bookinf Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strShipmentBookingStatus, strSearchFormNameShipmentBooking, "PARAM_Status"), true, "****test ID:70_shipment booking status is not set to Booking made in Shipment Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.setFieldValue(strPONumber, strSearchFormNameShipmentBooking, "PARAM_OrderNumber"), true, "****test ID:70_shipment booking po number is not set to Authorised PO Number*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAShipAuthorityPOM.clickChevron(objAdjunoLIMAShipAuthorityPOM.getChevSelect()), true, "****test ID:70_Selet Chevron is not clicked under shipment booking search page*****");
    	
    	String strSBPONumber =objAdjunoLIMAShipAuthorityPOM.getShipmentBookingPONumber().getText();
    	objSoftAssert.assertEquals(strSBPONumber.equalsIgnoreCase(strPONumber), true, "****test ID:70_PO Number is not displayed under Shipment booking*****");
    	
    	String strVendorBookingStatus = objAdjunoLIMAShipAuthorityPOM.getWebElementCellValue(objAdjunoLIMAShipAuthorityPOM.getShipmentBookingAuthoriseStatus());
    	objSoftAssert.assertEquals(strVendorBookingStatus.equalsIgnoreCase("Authorised"), true, "****test ID:71_ PO Number status in shipment booking is not Authorised*****");
    	objSoftAssert.assertAll();
    }
   
    @AfterTest()
    public void closeBrowser()
    {
    	driver.quit();
    }
    	
    
    
}
