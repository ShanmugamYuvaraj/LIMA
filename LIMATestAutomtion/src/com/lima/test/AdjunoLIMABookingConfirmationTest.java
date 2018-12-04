package com.lima.test;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.BookingConfirmation;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMABookingConfirmationPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMABookingConfirmationTest {
	
	WebDriver driver;

	long nPage_Timeout = 0;
    String strTestURL;
    String strLIMAUserName;
    String strLIMAPassword;
	
	AdjunoLIMABookingConfirmationPOM objAdjunoLIMABookingConfirmationPOM;
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	NodeList nlSearchParamsBookingConfirmation;
	NodeList nlBookingConfirmationGrid;
	NodeList nlBookingConfirmationPOGrid;
	
	String strSearchFormNameBookingConfirmation;
	String strPageTitleBookingConfirmation;
	String strBookingConfirmationFormName;
	String strPurchaseOrderFormName;
	String strPOStatusFormName;
	String strBookingDairyFormName;
	String strCompleteFormName;
	String strBookingConfirmationTrackPage;
	
	String strInvalidPONumber = "";
	String strStatusPending = "";
	String strStatusDeclined = "";
	String strStatusAny = "";
	String strPONumber = "";
	String strConfirmedStatusNo = "";
	String strConfirmedStatusYes = "";
	String strReason = "";
	String strMailID = "";
	String strMessage = "";
	String strTrackNo = "";
	String strOrderType = "";
	String strMode = "";
	String strStatusBooked = "";
	String strBookingDate = "";
	String strBookingTime = "";
	String strTrackNO = "";
	String strStatusConfirmed = "";
	String strBookingDairyStatus = "";
	String strProductNo = "";
	String strSKU = "";
	String strContainerNo = "";
	
	boolean bSearchResultsProductsFound = true;
	BookingConfirmation SearchResults;

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
            
            Node SearchFormNameBookingConfirmation = (Node) xPath.evaluate("config/LIMA/Search_Form_Name_Booking_Confirmation", dDoc, XPathConstants.NODE);
            strSearchFormNameBookingConfirmation = SearchFormNameBookingConfirmation.getTextContent();
            
            Node pageTitleBookingConfirmation = (Node) xPath.evaluate("config/LIMA/Page_Title_Booking_Confirmation", dDoc, XPathConstants.NODE);
            strPageTitleBookingConfirmation = pageTitleBookingConfirmation.getTextContent();
            
            Node BookingConfirmationFormName = (Node) xPath.evaluate("config/LIMA/Booking_Confirmation_Form_Name", dDoc, XPathConstants.NODE);
            strBookingConfirmationFormName = BookingConfirmationFormName.getTextContent();
            
            Node PurchaseOrderFormName = (Node) xPath.evaluate("config/LIMA/View_Form_Name_PO", dDoc, XPathConstants.NODE);
            strPurchaseOrderFormName = PurchaseOrderFormName.getTextContent();
            
            Node POStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
            strPOStatusFormName = POStatusFormName.getTextContent();
            
            Node BookingDairyFormName = (Node) xPath.evaluate("/config/LIMA/Form_Name_BookingDiary", dDoc, XPathConstants.NODE);
            strBookingDairyFormName = BookingDairyFormName.getTextContent();
            
            Node CompleteFormName = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
            strCompleteFormName = CompleteFormName.getTextContent();
            
            Node BookingConfirmationTrackPage = (Node) xPath.evaluate("/config/LIMA/Booking_Confirmation_Track_Page", dDoc, XPathConstants.NODE);
            strBookingConfirmationTrackPage = BookingConfirmationTrackPage.getTextContent();
             
            
            		
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getBookingConfirmationXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
           
            nlSearchParamsBookingConfirmation = (NodeList)xPath1.evaluate("/Booking_Confirmation/Search_Params", dDoc1, XPathConstants.NODESET);
            nlBookingConfirmationGrid = (NodeList) xPath1.evaluate("/Booking_Confirmation/BookingConfirmation_Grid", dDoc1, XPathConstants.NODESET);
            nlBookingConfirmationPOGrid = (NodeList) xPath1.evaluate("/Booking_Confirmation/BookingConfirmationPO_Grid", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsBookingConfirmation.getLength() -1; i++)
             {
                 if (nlSearchParamsBookingConfirmation.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		 el = (Element) nlSearchParamsBookingConfirmation.item(i); 
            		 
            		 strInvalidPONumber = el.getElementsByTagName("Invalid_PONumber").item(0).getTextContent();
            		 
            		 strStatusPending = el.getElementsByTagName("Status_Pending").item(0).getTextContent();
            		 
            		 strStatusDeclined = el.getElementsByTagName("Status_Declined").item(0).getTextContent();
            		 
            		 strStatusConfirmed = el.getElementsByTagName("Status_Confirmed").item(0).getTextContent();
            		 
            		 strStatusAny = el.getElementsByTagName("Status_Any").item(0).getTextContent();
            		 
            		 strStatusBooked = el.getElementsByTagName("Status_Booked").item(0).getTextContent();
            		 
            		 strConfirmedStatusNo = el.getElementsByTagName("Confirmed_Status_No").item(0).getTextContent();
            		 
            		 strConfirmedStatusYes = el.getElementsByTagName("Confirmed_Status_Yes").item(0).getTextContent();
            		 
            		 strReason = el.getElementsByTagName("Reason").item(0).getTextContent();
            		 
            		 strMailID = el.getElementsByTagName("Mail_ID").item(0).getTextContent();
            		 
            		 strMessage = el.getElementsByTagName("Message").item(0).getTextContent();
            		
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
    
    public boolean compareTwoStringValuesToSame(String strValue1,String strValue2) {
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
    
    @Test(priority = 1)
    public void test_1_accessBookingConfirmation()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        
        String strTitle = objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation,objAdjunoLIMABookingConfirmationPOM.getLnkTools(),objAdjunoLIMABookingConfirmationPOM.getLnkBookingConfirmation());
                
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleBookingConfirmation))
                bFlag = true;
            else
                bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true, "*****_1_Title of the page is wrong in Booking Confirmation tool*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 10)
    public void test_2_VerifyChevrons()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getChevSearch()), true, "*****_2_Search Chevron is not displayed in Booking Confirmation tool*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getChevSelect()), true, "*****_2_Select Chevron is not displayed in Booking Confirmation tool*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getChevUpdate()), true, "*****_2_Update Chevron is not displayed in Booking Confirmation tool*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getChevComplete()), true, "*****_2_Complete Chevron is not displayed in Booking Confirmation tool*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 15)
    public void test_3_VerifySearchFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_Destination"), true, "*****_3_Destination field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_BookingDateFrom"), true, "*****_3_Delivery Date From field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_BookingDateTo"), true, "*****_3_Delivery Date To field is not displayed in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_BookingReference"), true, "*****_3_Booking Reference field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_ClientReference"), true, "*****_3_Client Reference field is not displayed***** in Booking Confirmation Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_DeliveryMethod"), true, "*****_3_Delivery Method field is not displayed in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_Haulier"), true, "*****_3_Haulier field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_OrderType"), true, "*****_3_OrderType field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_Mode"), true, "*****_3_Mode field is not displayed in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_Supplier"), true, "*****_3_Supplier field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_3_PO Number field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_Priority"), true, "*****_3_Priority field is not displayed in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_3_Status field is not displayed in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strSearchFormNameBookingConfirmation, "Param_WFStatus"), true, "*****_3_WorkFlow Status field is not displayed in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 20)
    public void test_4_SetAwaitingAndInvalidPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_Destination"), true, "*****_3_Destination field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_BookingDateFrom"), true, "*****_3_Delivery Date From field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_BookingDateTo"), true, "*****_3_Delivery Date To field is not cleared in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_BookingReference"), true, "*****_3_Booking Reference field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_ClientReference"), true, "*****_3_Client Reference field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_DeliveryMethod"), true, "*****_3_Delivery Method field is not cleared in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_Haulier"), true, "*****_3_Haulier field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_OrderType"), true, "*****_3_OrderType field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_Mode"), true, "*****_3_Mode field is not cleared in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_Supplier"), true, "*****_3_Supplier field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_3_PO Number field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_Priority"), true, "*****_3_Priority field is not cleared in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_3_Status field is not cleared in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_WFStatus"), true, "*****_3_WorkFlow Status field is not cleared in Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusPending, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_4_Status field is not set to Awaiting in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strInvalidPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_4_Invalid PO Number is not set in PO Number field under Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevSelect()), true, "*****_4_Select Chevron is not clicked in Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtErrMsg()), true, "*****_4_\"No Records found\" Error Message is not displayed when invalid PO Number is set PO Number field under Booking Confirmation Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.getTxtErrMsg().getText().equals("No items were found."), true, "*****_4_Error Message as \"No Records found\" is not displayed properly when invalid PO Number is set PO Number field under Booking Confirmation Search Chevron*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 25)
    public void test_5_SetAwaitingAndValidPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_5_PO Number field is not cleared in Booking Confirmation Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusPending, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_5_Status field is not set to Awaiting in Booking Confirmation Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strSearchFormNameBookingConfirmation, "FORK_DeliveryBookingConfirm_SUT_RefineSearch"), true, "*****_5_Refine Search Button is not clicked in Booking Confirmation Select Chevron*****");
    	
    	strPONumber = objAdjunoLIMABookingConfirmationPOM.getTrPONumber().getText();
    	if(strPONumber.equals("Various"))
    	{
    		objAdjunoLIMABookingConfirmationPOM.getTrPONumber().click();
    		strPONumber = objAdjunoLIMABookingConfirmationPOM.getPopUpPoNumber().getText();
    	}
    	else{}
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_5_Valid PO Number is not set in PO number field under Booking Confirmation Select chevron*****");
    	wait(10000);
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strSearchFormNameBookingConfirmation, "FORK_DeliveryBookingConfirm_SUT_RefineSearch"), true, "*****_5_Refine Search Button is not clicked in Booking Confirmation Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.getTrPONumber().getText().equals(strPONumber), true, "*****_5_Searched PO number details are not displayed in resultant grid under Booking Confirmation Select Chevron *****");
    	
    	objSoftAssert.assertAll();
    }

    @Test(priority = 30)
    public void test_6_VerifyGridHeader()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThMultiDrop()), true, "*****_6_Multi Drop Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThDestination()), true, "*****_6_Destination Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThBookingRef()), true, "*****_6_BookingRef Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThBookingDate()), true, "*****_6_Booking Date Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThBookingTime()), true, "*****_6_Booking Time Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThDuration()), true, "*****_6_Duration Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThDeliveryMethod()), true, "*****_6_Delivery Method Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThHaulier()), true, "*****_6_Haulier Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThOrderType()), true, "*****_6_Order Type Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThClentRef()), true, "*****_6_Client Ref Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThMode()), true, "*****_6_Mode Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThConatiner()), true, "*****_6_Container Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThPoNumber()), true, "*****_6_Po Number Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThPriority()), true, "*****_6_Priority Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThPackType()), true, "*****_6_Pack Type Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThConfirmationStatus()), true, "*****_6_Confirmation Status Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getThDailogs()), true, "*****_6_Dailog(s) Column is not displayed in resultant grid in Booking Confirmation Select chevron*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 35)
    public void test_7_VerifyErrorMessageAtUpdateChev()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevUpdate1()), true, "*****_7_Update Chevron is not clicked in Bookig confirmation Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtNoSelectionsErrMsg()), true, "*****_7_\"You must make selection(s) before progressing\" message is not displayed when no PO's are selected in Booking Confirmation Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.getTxtNoSelectionsErrMsg().getText().equals("You must make selection(s) before progressing"), true, "*****_7_\"You must make selection(s) before progressing\" message is not displayed properly when no PO's are selected in Booking Confirmation Select page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 40)
    public void test_8_VerifyUpdatePageLoaded()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
//    	SearchResults = objAdjunoLIMABookingConfirmationPOM.getBookingConfirmation();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickCheckBox(objAdjunoLIMABookingConfirmationPOM.getTrCheckBox()), true, "*****_8_CheckBox is clicked in resultant grid under Booking Confirmation Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevUpdate1()), true, "*****_8_Update Chevron is not clicked under Booking Confirmation Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strBookingConfirmationFormName), true, "*****_8_Booking Confirmation Update page is not loaded when Update chevron is not clicked under Booking Confirmation Select page*****");
    	
//    	if(bSearchResultsProductsFound)
//		  {
//				String strReturnMessage = objAdjunoLIMABookingConfirmationPOM.verifyProductsDataOnGrid(strBookingConfirmationFormName,"GridBookingConfirmation",SearchResults);
//				
//			  	objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "*****_8_"+strReturnMessage+"*****");
//			  
//		  }
//		  else
//			  objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****_8_No Products in Search Results.*****");

    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 45)
    public void test_9_VerifyGridsInUpdatePage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strBookingConfirmationFormName, "GridBookingConfirmation"), true, "*****_9_Booking Confirmation Grid is not displayed in Booking Confirmation update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strBookingConfirmationFormName, "GridBookingConfirmationDetails"), true, "*****_9_Booking Confirmation PO Number Grid is not displayed in Booking Confirmation update chevron*****");
    	
    	objSoftAssert.assertAll();
    }
     
    @Test(priority = 50)
    public void test_10_VerifyCopyDownsDisplayed()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strBookingConfirmationFormName, "BookingConfirmed"), true, "*****_10_Confirmed Copy down is not displayed in Booking Confirmation update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strBookingConfirmationFormName, "Haulier1"), true, "*****_10_Haulier Copy down is not displayed Booking Confirmation update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strBookingConfirmationFormName, "DeliveryBookingConfirmationReason"), true, "*****_10_Reason Copy down is not displayed Booking Confirmation update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.checkDoesFieldsExist(strBookingConfirmationFormName, "DeliveryBookingConfirmationComments"), true, "*****_10_Comments Copy down is not displayed Booking Confirmation update chevron*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 55)
    public void test_11_VerifyBookingConfirmationGridCaptions()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	boolean bFlag;
        String strMessage="";
        
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMABookingConfirmationPOM.getCaptionsList(strBookingConfirmationFormName,"GridBookingConfirmation");
                
        strMessage = objAdjunoLIMABookingConfirmationPOM.verifyCaptionsONGrid(list,nlBookingConfirmationGrid,14);
        
        if(strMessage.equals(""))  
        {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true," *****_11_Booking Confirmation grid Caption: "+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 60)
    public void test_12_VerifyBookingConfirmationPOGridCaptions()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	boolean bFlag;
        String strMessage="";
        
        objSoftAssert.assertEquals(strPONumber.equals(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingConfirmationFormName, "GridBookingConfirmationDetails", 0, "PONumber")), true, "The Selected PO Number in select chevron is not displayed under Booking Confirmation PO grid in update chevron");
              
        ArrayList<String> list = new ArrayList<String>();  
        list = objAdjunoLIMABookingConfirmationPOM.getCaptionsList(strBookingConfirmationFormName,"GridBookingConfirmationDetails");
        strMessage = objAdjunoLIMABookingConfirmationPOM.verifyCaptionsONGrid(list,nlBookingConfirmationPOGrid,13);
        
        if(strMessage.equals(""))  
        {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true,"*****_12_Booking Confirmation PO grid Caption: "+strMessage+"*****");
        strProductNo = objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingConfirmationFormName, "GridBookingConfirmationDetails", 0, "PProduct");
    	objSoftAssert.assertAll();         	
    }
    
    @Test(priority = 65)
    public void test_13_VerifyMandatoryFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strBookingConfirmationFormName, "BookingConfirmed"), true, "*****_13_Confirmed field is not Cleared in Booking Confirmation update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevComplete1()), true, "*****_13_Complete Chevron is not clicked under Booking Confirmation Update page*****");
    	
    	String strMandatoryErrMsg = objAdjunoLIMABookingConfirmationPOM.getValidationMessageGridElement(strBookingConfirmationFormName, "GridBookingConfirmation", 0, "Confirmed");
    	
    	if(!strMandatoryErrMsg.equals(""))
    	{
    		if(strMandatoryErrMsg.contains("&#39;"))
    		{
    			strMandatoryErrMsg = strMandatoryErrMsg.replace("&#39;", "'");
    		}
    	}
    	else
    	{
    		strMandatoryErrMsg = "*****_13_"+strMandatoryErrMsg + "validation message is not displayed for mandatory field*****";
    	} 
    	objSoftAssert.assertEquals(strMandatoryErrMsg.equals("> 'Confirmed' is a required field"), true, "*****_13_\"> 'Confirmed' is a required field\" error Message is wrong for Confirmed field in Booking Confirmation update chevron*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 70)
    public void test_14_SetConfirmedStatusNo()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickAllCheckBox(strBookingConfirmationFormName,"GridBookingConfirmation", "Select"), true,"*****_14_Checkbox is not clicked Booking Confirmation grid under Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strConfirmedStatusNo, strBookingConfirmationFormName, "BookingConfirmed"), true, "*****_14_Confirmed field is not set as \"NO\" in Booking Comfirmation grid under Update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strBookingConfirmationFormName, "Button1"), true, "*****_14_Update button is not clicked in Booking Comfirmation grid under Update chevron*****");
    	
    	objSoftAssert.assertEquals(strConfirmedStatusNo.equals(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingConfirmationFormName, "GridBookingConfirmation", 0, "Confirmed")), true, "*****_14_Value Entered in the Confirmed field copy down is not reflected under Confirmed field in the Grid under Update Chevron*****");
    	
    	String strMandatoryErrMsg = objAdjunoLIMABookingConfirmationPOM.getValidationMessageGridElement(strBookingConfirmationFormName, "GridBookingConfirmation", 0, "Reason");
    	
    	if(!strMandatoryErrMsg.equals(""))
    	{
    		if(strMandatoryErrMsg.contains("&#39;"))
    		{
    			strMandatoryErrMsg = strMandatoryErrMsg.replace("&#39;", "'");
    		}
    	}
    	else
    	{
    		strMandatoryErrMsg = "*****_14_"+strMandatoryErrMsg + "validation message is null*****";
    	}
    	objSoftAssert.assertEquals(strMandatoryErrMsg.equals("> 'Reason' is a required field"), true, "*****_14_\"> 'Reason' is a required field\" Error Message is wrong for Reason Mandatory field*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 75)
    public void test_15_SetReasonValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strReason,strBookingConfirmationFormName, "DeliveryBookingConfirmationReason"), true, "*****_15_Reason field value is not set in Booking Confirmation grid under Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strBookingConfirmationFormName, "Button1"), true, "*****_15_Update button is not clicked in Booking Confirmation grid under Update Chevron*****");
    	objSoftAssert.assertEquals(strReason.equals(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingConfirmationFormName, "GridBookingConfirmation", 0, "Reason")), true, "*****_15_Value Entered in the Reason field copy down is not reflected under Reason field in Booking Confirmation Grid under Update chevron*****");
    	
    	objSoftAssert.assertAll();	
    }
    
    @Test(priority = 80)
    public void test_16_VerifyDiaologsHyperLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	String strMessage = "";
    	
    	String strhref = objAdjunoLIMABookingConfirmationPOM.isHyperlinkPresent(strBookingConfirmationFormName, "GridBookingConfirmation", 0, "Dialog1", "onclick");
    	
    	if(!isNullOrBlank(strhref))
        {
        
        }
        else
        {
            strMessage = strMessage + "Dailog it is not a hyperlink in Booking confirmation grid under update chevron";
        }
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****_16_"+strMessage+"*****");
        
        wait(5000);
        try{
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtSubject()), true, "*****_16_Subject field is not displayed in Dailog pop up under Booking Confirmation tool*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getWatch()), true, "*****_16_Message field is not displayed in Dailog pop up under Booking Confirmation tool*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtCopyByEmailTo()), true, "*****_16_Copy By Email To field is not displayed in Dailog pop up under Booking Confirmation tool*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getRestrictTo()), true, "*****_16_Restrict To field is not displayed in Dailog pop up under Booking Confirmation tool*****");
        }
        catch(NoSuchElementException e)
        {
        	strMessage = strMessage + "Dailog pop up is not displayed";
        }
        objSoftAssert.assertEquals(strMessage.equals(""), true, "**********_16_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    

    @Test(priority = 85)
    public void test_17_VerifyMandatoryFieldInDailogPopUp()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMABookingConfirmationPOM.getTxtSubject(), strPONumber), true, "*****_17_Subject field value is not set in Dailog popup*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMABookingConfirmationPOM.getTxtCopyByEmailTo(), strMailID), true, "*****_17_Copy By Email To field value is not set in Dailog popup*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnAddDialog()), true, "*****_17_Add Dialog button is clicked in Dailog popup*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getErrPopUp()), true, "*****_17_'Error adding dialog' Error message is not displayed in Dailog popup*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 90)
    public void test_18_SetMessageField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnOK()), true, "*****_18_OK Button is not clicked in Error adding dailog pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMABookingConfirmationPOM.getTxtMessage(), strMessage), true, "*****_18_Message is not set in Message field in Dailog popup*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnAddDialog()), true, "*****_18_Add Dialog button is not clicked in Dailog popup*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtDailogNew()), true, "****_18_New Dialog is not updated in Dailog popup******");
//    	objSoftAssert.assertEquals((objAdjunoLIMABookingConfirmationPOM.getDialogSubject().getText()).contains(strPONumber), true, "*****_18_Subject updated is wrong*****");
//    	objSoftAssert.assertEquals(strMessage.equals(objAdjunoLIMABookingConfirmationPOM.getDialogMessage().getText()), true, "*****_18_Message updated is wrong*****");
//    	objSoftAssert.assertEquals((objAdjunoLIMABookingConfirmationPOM.getDialogMailID().getText().contains(strMailID)), true, "*****_18_Mail ID updated is wrong*****");
//    	
    	wait(3000);
    	//objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM, expected);
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 100)
    public void test_19_CloseDailogPopUp()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getCloseBtn()), true, "*****_19_Close button is not clicked in dailog pop up*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strBookingConfirmationFormName), true, "*****_19_Update page is not displayed in Booking Confirmation tool after closing dailog pop*****");
//    	objSoftAssert.assertEquals("1 Dialog(s)".equals(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingConfirmationFormName, "GridBookingConfirmation", 0, "Dialog1")), true, "*****_19_Dailog is not updated*****");
    	
    	objSoftAssert.assertAll();  	   	
    }
    
    @Test(priority = 105)
    public void test_20_VerifyPONumberHyperLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	String strMessage = "";
    	
    	wait(3000);
    	String strhref = objAdjunoLIMABookingConfirmationPOM.isHyperlinkPresent(strBookingConfirmationFormName, "GridBookingConfirmationDetails", 0, "PONumber", "href");
    	if(!isNullOrBlank(strhref))
        {
        
        }
        else
        {
            strMessage = strMessage + "PO Number is not a hyperlink in PO Grid under Booking Confirmation update page";
        }
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****_20_"+strMessage+"*****");
        wait(6000);
        
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnApply()), true,"*****_20_Apply button is not clicked in Purchase Order read only page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strPurchaseOrderFormName), true, "*****_20_Purchase Order edit page is not displayed*****");
        strSKU = objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strPurchaseOrderFormName, "GRID_ContractSummaryDetails", 0, "SKU");
        if(!objAdjunoLIMABookingConfirmationPOM.getFieldValue(strPurchaseOrderFormName,"OrderNumber").equals(""))
        {
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strPONumber, objAdjunoLIMABookingConfirmationPOM.getFieldValue(strPurchaseOrderFormName,"OrderNumber")),true,"*****_20_PO number  found in PO grid under Booking confirmation update page do not match with the PO number found in Purchase Order edit page*****");
		}
        else
        {
			strMessage = strMessage +"OrderNumber field value is empty in Purchase Order page";	    		
		}
        objSoftAssert.assertEquals(strMessage.equals(""), true, "*****_20_"+strMessage+"*****");
        objSoftAssert.assertAll();   	
    }
    
    @Test(priority = 120)
    public void test_21_VerifyCompletePage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	
    	driver.navigate().back();
    	driver.navigate().back();
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickAllCheckBox(strBookingConfirmationFormName,"GridBookingConfirmation", "Select"), true,"*****_21_All the Checkboxs are not clicked in PO grid under Booking Confirmation Update chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strReason,strBookingConfirmationFormName, "DeliveryBookingConfirmationReason"), true, "*****_21_Reason field is not set in Booking Confirmation grid under Update Chevron *****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strConfirmedStatusNo, strBookingConfirmationFormName, "BookingConfirmed"), true, "*****_21_Confirmed field is not set as NO in Booking Confirmation grid under Update Chevron *****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strBookingConfirmationFormName, "Button1"), true, "*****_21_Update button is not clicked in Booking Confirmation grid under Update Chevron *****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevComplete1()), true, "*****_21_Complete Chevron is not clicked Booking Confirmation Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtProcessedMsg()), true, "*****_21_'100% processed' message is not displayed in Booking Confirmation Complete Chevron*****");
    	strMessage = objAdjunoLIMABookingConfirmationPOM.getWebElementCellValue(objAdjunoLIMABookingConfirmationPOM.getTxtProcessedMsg());
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("Processed 100%"), true, "*****_18_Processed 100% msg is wrong in Booking Confirmation Complete page*****");
    	objSoftAssert.assertAll();
    }
    
////    @Test(priority = 125)
////    public void test_22_ClickOnEdit()
////    {
////    	SoftAssert objSoftAssert = new SoftAssert();
////    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
////    	
////    	objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation, objAdjunoLIMABookingConfirmationPOM.getLnkTrack(), objAdjunoLIMABookingConfirmationPOM.getLnkEdit());
////    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTitleEditPage()), true, "*****_22_Edit or create a track page is not displayed*****");
////    	
////        objSoftAssert.assertAll();
////    }
////    
////    @Test(priority = 130)
////    public void test_23_EnterTrackNo()
////    {
////    	SoftAssert objSoftAssert = new SoftAssert();
////    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
////    	
////    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getFldRefNo()), true,"*****_22_Reference field is not exist*****");
////
////        if (!isNullOrBlank(strTrackNo)) {
////            objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMABookingConfirmationPOM.getFldRefNo(),strTrackNo), true,"*****_22_Reference field value is not set*****");
////        }else{
////        	strMessage =strMessage + " Track number is null ";
////        }
////        
////        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnTrackApply()), true,"*****_22_Apply button is not clicked on track edit page*****");
////    	objSoftAssert.assertAll();
////    }
//   
    @Test(priority = 135)
    public void test_25_VerifyPOStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
    	String strMessage = "";
    	wait(5000);
    	objAdjunoLIMABookingConfirmationPOM.callMouseHover(strPOStatusFormName, objAdjunoLIMABookingConfirmationPOM.getLnkFind(), objAdjunoLIMABookingConfirmationPOM.getLnkPOStatus());
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strPOStatusFormName), true, "*****_25_PO Status Page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clearInputField(strPOStatusFormName, "Param_17Item"), true, "*****_25_Product Number field is not cleared in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "*****_25_PO Number is not set in PO Status page*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strProductNo, strPOStatusFormName, "Param_17Item"), true, "*****_25_Product Number is not set in PO Status page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnRun()), true, "*****_25_Run button is not clicked in PO Status page*****");
    	try
    	{
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getPoStatusReport()), true, "*****_25_PO Status report page is not loaded*****");
    	
    	String strStatus = objAdjunoLIMABookingConfirmationPOM.getWebElementCellValue(objAdjunoLIMABookingConfirmationPOM.getPoStatus_Status());
    	//objSoftAssert.assertEquals(strStatus.equals("DC Booking Declined"), true, "*****_25_Status mismatch*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verfyPOStatus("DC Booking Declined"), true, "*****_25_Status is not DC Booking Declined when the PO is made as \"Declined\" in Booking Confirmation*****");
    	}
    	catch(NoSuchElementException e)
    	{
    		strMessage = strMessage + "No Records are found in PO Status Report";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****_25_"+strMessage+"*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 140)
    public void Test_26_VerifyPONumberInAwaitingStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        
        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation, objAdjunoLIMABookingConfirmationPOM.getLnkTools(), objAdjunoLIMABookingConfirmationPOM.getLnkBookingConfirmation());
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strSearchFormNameBookingConfirmation), true, "*****_26_Search page is not loaded*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusPending, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_26_Status is not set to Awaiting in Booking Confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_26_Valid PO Number value is not set in Booking Confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevSelect()), true, "*****_26_Select Chevron is no clicked  in Booking Confirmation Search page*****");
        wait(5000);
        
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getTxtErrMsg()), true, "*****_26_No items were found. message is not displayed when Invalid PO Number is set in Container field in Booking Confirmation Search page*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 145)
    public void Test_27_VerifyPONumberInDeclinedStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        
        String strMessage="";
        wait(3000);
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusDeclined, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_27_Status is not set to Awaiting in Booking Confirmation Select Chevron*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_27_Valid PO Number value is not set in PO Number field in Booking Confirmation Select Chevron*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strSearchFormNameBookingConfirmation, "FORK_DeliveryBookingConfirm_SUT_RefineSearch"), true, "*****_27_Refine Search button is not clicked in Booking Confirmation Select Chevron*****");
        objSoftAssert.assertEquals(strPONumber.equals(objAdjunoLIMABookingConfirmationPOM.getWebElementCellValue(objAdjunoLIMABookingConfirmationPOM.getTrPONumber())), true, "****_27_PO Number details in the resultant grid do not match with the PO Number set in the PO Number*****");
        strOrderType = objAdjunoLIMABookingConfirmationPOM.getTrOrderType().getText();
        strMode = objAdjunoLIMABookingConfirmationPOM.getTrMode().getText();
        strBookingDate = objAdjunoLIMABookingConfirmationPOM.getTrBookingDate().getText();
        strBookingTime = objAdjunoLIMABookingConfirmationPOM.getTrBookingTime().getText();
        strContainerNo = objAdjunoLIMABookingConfirmationPOM.getTrContainer().getText();
        List<BookingConfirmation> list = new ArrayList<BookingConfirmation>();  
    	list = objAdjunoLIMABookingConfirmationPOM.getStaus();
    	
    	strMessage = objAdjunoLIMABookingConfirmationPOM.verifyStaus(strStatusDeclined,list);
    	strPONumber = list.get(0).getPoNumber();
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "*****_27_"+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 150)
    public void Test_28_VerifyDeclinedPOStatusInBookingDairy()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
       
        strBookingDate = objAdjunoLIMABookingConfirmationPOM.parseDate(strBookingDate, "dd MMM yyyy", "dd/MM/yyyy");
        strBookingDairyStatus = strBookingDate.concat(" "+strBookingTime);
     
        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strBookingDairyFormName, objAdjunoLIMABookingConfirmationPOM.getLnkTools(), objAdjunoLIMABookingConfirmationPOM.getLnkBookingDiary());
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strBookingDairyFormName), true, "*****_28_Booking dairy page is not loaded*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strOrderType, strBookingDairyFormName, "PARAM_OrderType"), true, "*****_28_Order type value is not set in Booking Dairy*****");
        if(!(strOrderType).equals("Domestic"))
        {
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strMode, strBookingDairyFormName, "PARAM_Mode"), true, "*****_28_Mode value is not set in Booking Dairy*****");
        }
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strBookingDairyFormName, "PARAM_PONumber"), true, "*****_28_PO Number is not set in Booking Dairy*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusBooked, strBookingDairyFormName, "PARAM_BookingStatus"), true, "*****_28_Status is not set to {Any} in Booking Dairy*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strBookingDairyFormName, "BookingsSearch"), true, "*****_28_Search button is not clicked  in Booking Dairy*****");
        wait(2000);
        int Rows = (int) objAdjunoLIMABookingConfirmationPOM.getNoOfGridRows(strBookingDairyFormName, "Grid_Bookings");
        for(int i = 0;i<=Rows-1;i++)
        {
        	if(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingDairyFormName, "Grid_Bookings", i, "ContainerLink").equals(strContainerNo))
        	{
        		objSoftAssert.assertEquals(strBookingDairyStatus.equals(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingDairyFormName, "Grid_Bookings", i, "StatusDateLink")), true, "*****_28_Booking Status is wrong in Booking dairy for the PO which is been Declined in Booking Confirmation*****");
        	}
        	
        }
         
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 155)
    public void Test_29_SetBookingConfirmedStatusYes()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        
        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation, objAdjunoLIMABookingConfirmationPOM.getLnkTools(), objAdjunoLIMABookingConfirmationPOM.getLnkBookingConfirmation());
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strSearchFormNameBookingConfirmation), true, "*****_29_Booking Confirmation page is not loaded*****");
      
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusDeclined, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_29_Status field is not set to Awaiting in Booking confirmation Search Chevron*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_29_PO Number is not set in PO Number field under Booking confirmation Search Chevron*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevSelect()), true, "*****_29_Select chevron is not clicked in Booking Confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickCheckBox(objAdjunoLIMABookingConfirmationPOM.getTrCheckBox()), true, "*****_29_Check box is not selected in the resultant grid under Select page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevUpdate1()), true, "*****_29_Update Chevron is not clicked in Booking Confirmation Select chevron*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickAllCheckBox(strBookingConfirmationFormName, "GridBookingConfirmation", "Select"), true, "*****_29_Checkbox is not clicked in Booking Confirmation grid under Update page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strConfirmedStatusYes, strBookingConfirmationFormName, "BookingConfirmed"), true, "*****_29_Confirmed field is not set as YES in Booking Confirmation Upadae page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strBookingConfirmationFormName, "Button1"), true, "*****_29_Update button is not clicked in Booking Confirmation Upadae page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevComplete1()), true, "*****_29_Complete Chevron is not clicked under Booking Confirmation Upadae page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strCompleteFormName), true, "*****_29_Complete page is not displayed after clicking Complete chevron in update page *****");
         
        objSoftAssert.assertAll();
    }
    
//    /*@Test(priority = 160)
//    public void Test_30_TrackDetails()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
//        
//        String strDespatchDetails = objAdjunoLIMABookingConfirmationPOM.getTxtTrackDetails().getText();   	
//        //	String strTrackReferenceNo = strDespatchDetails.substring(strDespatchDetails.indexOf("LIMAUAT"),strDespatchDetails.indexOf("].", strDespatchDetails.indexOf("LIMAUAT")));
//        String[] vals = strDespatchDetails.split("track");
//        
//        vals[1] = vals[1].replace("[", " ").replace("]", " ");
//        String valss[] = vals[1].split(" ",12);
//        	
//        strTrackNO = valss[1];
//        System.out.println("track ref num: - " + strTrackNO);
//    }
//*/    
//    @Test(priority = 165)//pending
//    public void Test_31_CopyTrackNo()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
//        wait(5000);
//        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation,objAdjunoLIMABookingConfirmationPOM.getLnkTrack(),objAdjunoLIMABookingConfirmationPOM.getLnkEdit());
//        wait(10000);
//
//        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getFldRefNo()), true,"*****_19_Reference field is not exist*****");
//
//        if (!isNullOrBlank(strTrackNO)) {
//            objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMABookingConfirmationPOM.getFldRefNo(),strTrackNO), true,"*****_19_Reference field value is not set*****");
//        }else{
//        	strMessage =strMessage + " Track number is null ";
//        }
//        
//        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnTrackApply()), true,"*****_19_Apply button is not clicked on track edit page*****");
//        objSoftAssert.assertAll();
//    }
//    
//    @Test(priority = 167)
//    public void Test_32_ClickDeliveryBookingTrackEvent()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
//        
//        wait(3000);
//        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickLinkUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getLnkTrackDeliveryConfirmation()), true, "*****_32_DeliveryConfirmation Link is not clicked*****");
//        
//        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.getWebElementCellValue(objAdjunoLIMABookingConfirmationPOM.getTrackBookingConfirmedStatus()).equals("Yes"), true, "*****_32_Booking Confirmed status is wrong*****");
//        
//        objSoftAssert.assertAll();
//    }
    
    @Test(priority = 170)
    public void Test_33_VerifyConfirmedPOStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        String strMessage = "";
        wait(3000);
        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation, objAdjunoLIMABookingConfirmationPOM.getLnkFind(), objAdjunoLIMABookingConfirmationPOM.getLnkPOStatus());
        wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strPOStatusFormName), true, "*****_33_PO Status Page is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "*****_33_PO Number is not set in PO Status page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMABookingConfirmationPOM.getBtnRun()), true, "*****_33_Run button is not clicked*****");
    	try
    	{
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.isElementPresent(objAdjunoLIMABookingConfirmationPOM.getPoStatusReport()), true, "*****_33_PO Status report page is not loaded*****");
    	
    	//objSoftAssert.assertEquals(strStatus.equals("DC Booking Confirmed"), true, "*****_33_Status mismatch*****");
    	objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verfyPOStatus("DC Booking Confirmed"), true, "*****_33_Status is not DC Booking Confirmed when the PO is made as \"Confirmed\" in Booking Confirmation*****");
    	//objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verfyPOStatus("Order Progressed"), true, "*****_24_Status is not Order Progressed*****");
    	}
    	catch(NoSuchElementException e)
    	{
    		strMessage = strMessage + "No Records are found in PO Status Report";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****_33_"+strMessage+"*****");
        objSoftAssert.assertAll();
        
    }
    
    @Test(priority = 175)
    public void Test_34_VerifyConfirmedPOInDeclinedStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        
        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strSearchFormNameBookingConfirmation, objAdjunoLIMABookingConfirmationPOM.getLnkTools(), objAdjunoLIMABookingConfirmationPOM.getLnkBookingConfirmation());
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strSearchFormNameBookingConfirmation), true, "*****_34_ Booking confirmation Search page is not loaded*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusDeclined, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****_34_Status is not set to Awaiting in Booking confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_34_Valid PO Number value is not set in Booking Confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevSelect()), true, "*****_34_Select Chevron is no clicked under Booking Confirmation Search page*****");
        wait(5000);

        objSoftAssert.assertAll();
    }
    
    @Test(priority = 180)
    public void Test_35_VerifyConfirmedPOInConfirmedStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusConfirmed, strSearchFormNameBookingConfirmation, "Param_Status"), true, "*****35 Status is not set to Awaiting in Booking confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strSearchFormNameBookingConfirmation, "Param_PONumber"), true, "*****_35_Valid PO Number value is not set in Booking Confirmation Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickChevron(objAdjunoLIMABookingConfirmationPOM.getChevSelect()), true, "*****_35_Select Chevron is no clicked under Booking Confirmation Search page*****");
        objSoftAssert.assertEquals(strPONumber.equals(objAdjunoLIMABookingConfirmationPOM.getWebElementCellValue(objAdjunoLIMABookingConfirmationPOM.getTrPONumber())), true, "****_35_PO Number details displayed in the resultant grid do not match with the PO set in the Po Number Search field under Booking Confirmation Select page*****");
        List<BookingConfirmation> list = new ArrayList<BookingConfirmation>();  
    	list = objAdjunoLIMABookingConfirmationPOM.getStaus();
    	
    	strMessage = objAdjunoLIMABookingConfirmationPOM.verifyStaus(strStatusConfirmed,list);
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "*****_35_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 185)
    public void Test_36_VerifyConfirmedPOInBookingDairy()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMABookingConfirmationPOM = new AdjunoLIMABookingConfirmationPOM(driver);  
        
        objAdjunoLIMABookingConfirmationPOM.callMouseHover(strBookingDairyFormName, objAdjunoLIMABookingConfirmationPOM.getLnkTools(), objAdjunoLIMABookingConfirmationPOM.getLnkBookingDiary());
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.verifyPageIsLoaded(strBookingDairyFormName), true, "*****_36_Booking dairy page is not loaded*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strOrderType, strBookingDairyFormName, "PARAM_OrderType"), true, "*****_36_Order type value is not set in Booking Dairy*****");
        
        if(!(strOrderType).equals("Domestic"))
        {
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strMode, strBookingDairyFormName, "PARAM_Mode"), true, "*****_36_Mode value is not set in Booking Dairy*****");
        }
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strPONumber, strBookingDairyFormName, "PARAM_PONumber"), true, "*****_36_PO Number is not set in Booking Dairy*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.setFieldValue(strStatusBooked, strBookingDairyFormName, "PARAM_BookingStatus"), true, "*****_36_Status is not set to {Any} in Booking Dairy*****");
        objSoftAssert.assertEquals(objAdjunoLIMABookingConfirmationPOM.clickButton(strBookingDairyFormName, "BookingsSearch"), true, "*****_36_Search button is not clicked in Booking Dairy*****");
        
        int Rows = (int) objAdjunoLIMABookingConfirmationPOM.getNoOfGridRows(strBookingDairyFormName, "Grid_Bookings");
        for(int i = 0;i<=Rows-1;i++)
        {
        	if(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingDairyFormName, "Grid_Bookings", i, "ContainerLink").equals(strContainerNo))
        	{
        		objSoftAssert.assertEquals(strBookingDairyStatus.equals(objAdjunoLIMABookingConfirmationPOM.getGridCellValue(strBookingDairyFormName, "Grid_Bookings", i, "StatusDateLink")), true, "*****_36_Booking Status is wrong in Booking dairy for the PO which is been Confirmed in Booking Confirmation*****");
        	}
        }
        	
        
        objSoftAssert.assertAll();
    }
    
    @AfterTest()
    public void closeBrowser()
    {
    	driver.close();
    }
    
    
    	
    
    
    
    
}
