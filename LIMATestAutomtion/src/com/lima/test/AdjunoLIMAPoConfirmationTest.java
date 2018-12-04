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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.PurchaseOrders;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAPoConfirmationPOM;

public class AdjunoLIMAPoConfirmationTest {

	WebDriver driver;

	long nPage_Timeout = 0;
	String strTestURL;
	String strDriver;
	String strTrackN0;
	String strTrackN01;

	ArrayList<PurchaseOrders> lstSearchResults;
	String strLIMAUserName;
	String strLIMAPassword;
	String strPageTitlePOConfirmation;
	String strSearchFormName;
	String strPOConfirmationForm;
	String strPOConfirmationPOStatusForm;
	String StrPONumberQuery;
	String StrPONumberConfirmed;

	String strInvalidPoNumber;
	String strPoNumber;
	String strConfirmationStatusAny;
	String strConfirmationStatusPending;
	String strConfirmationStatusUnderQuery;
	String strConfirmationStatusConfirmed;
	String strConfirmationStatus;
	String strReasonCode;
	String strConfirmationStatus1;
	String strConfirmationStatus2;
	String strComments;
	String strPONumberConfirmedStatus;
	String strPOQueryStatus;
	String strOrderLateStatus;

	AdjunoLIMALibrary objAdjunoLIMALibrary;
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMAPoConfirmationPOM objAdjunoLIMAPoConfirmationPOM;
	boolean bSearchResultsProductsFound = true;

	NodeList nPurchaseOrderDetails;
	NodeList nProductDetails;



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

			Node pageTitlePOConfirmation = (Node) xPath.evaluate("/config/LIMA/Page_Title_PO_Confirmation", dDoc,XPathConstants.NODE);
			strPageTitlePOConfirmation = pageTitlePOConfirmation.getTextContent();

			Node SearchFormNamePOConfirmationForm = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_PO_ConfirmationForm", dDoc,	XPathConstants.NODE);
			strSearchFormName = SearchFormNamePOConfirmationForm.getTextContent();

			Node POConfirmationForm = (Node) xPath.evaluate("/config/Generic/PO_ConfirmationForm", dDoc, XPathConstants.NODE);
			strPOConfirmationForm = POConfirmationForm.getTextContent();
			
			Node POConfirmationPOStatusForm = (Node) xPath.evaluate("/config/Generic/PO_Confirmation_POStatusForm", dDoc, XPathConstants.NODE);
			strPOConfirmationPOStatusForm = POConfirmationPOStatusForm.getTextContent();

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
			DocumentBuilder builder1 = domFactory1.newDocumentBuilder();
			Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getPOConfirmationDataFileName());

			XPath xPath1 = XPathFactory.newInstance().newXPath();

			Node InvalidPoNumber = (Node) xPath1.evaluate("/POConfirmation/SearchParams/InvalidPoNumber", dDoc1,XPathConstants.NODE);
			strInvalidPoNumber = InvalidPoNumber.getTextContent();
             
			Node ConfirmationStatusAny = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatusAny",dDoc1, XPathConstants.NODE);
			strConfirmationStatusAny = ConfirmationStatusAny.getTextContent();

			Node ConfirmationStatusPending = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatusPending",dDoc1, XPathConstants.NODE);
			strConfirmationStatusPending = ConfirmationStatusPending.getTextContent();

			Node ConfirmationStatusUnderQuery = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatusUnderQuery",dDoc1, XPathConstants.NODE);
			strConfirmationStatusUnderQuery = ConfirmationStatusUnderQuery.getTextContent();

			Node ConfirmationStatusConfirmed = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatusConfirmed",dDoc1, XPathConstants.NODE);
			strConfirmationStatusConfirmed = ConfirmationStatusConfirmed.getTextContent();

			Node ConfirmationStatus = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatus", dDoc1,XPathConstants.NODE);
			strConfirmationStatus = ConfirmationStatus.getTextContent();

			Node ReasonCode = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ReasonCode", dDoc1,XPathConstants.NODE);
			strReasonCode = ReasonCode.getTextContent();

			Node ConfirmationStatus1 = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatus1", dDoc1,XPathConstants.NODE);
			strConfirmationStatus1 = ConfirmationStatus1.getTextContent();

			Node ConfirmationStatus2 = (Node) xPath1.evaluate("/POConfirmation/SearchParams/ConfirmationStatus2", dDoc1,XPathConstants.NODE);
			strConfirmationStatus2 = ConfirmationStatus2.getTextContent();

			Node Comments = (Node) xPath1.evaluate("/POConfirmation/SearchParams/Comments", dDoc1,XPathConstants.NODE);
			strComments = Comments.getTextContent();
						
			Node POQueryStatus = (Node) xPath1.evaluate("/POConfirmation/SearchParams/VendorBooked_Status", dDoc1,XPathConstants.NODE);
			strPOQueryStatus = POQueryStatus.getTextContent();
			
			nPurchaseOrderDetails = (NodeList) xPath1.evaluate("/POConfirmation/PurchaseOrderDetails", dDoc1,XPathConstants.NODESET);

			nProductDetails = (NodeList) xPath1.evaluate("/POConfirmation/ProductDetails", dDoc1,XPathConstants.NODESET);

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

	}

	private boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}

	@Test(priority = 1)
	public void test_1_AccessPoConfirmation() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		String strTitle = objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkTools(),objAdjunoLIMAPoConfirmationPOM.getLnkPOConfirmation());
		boolean bFlag = true;

		if (isNullOrBlank(strTitle))
			bFlag = false;

		if (!(isNullOrBlank(strTitle))) {
			if (strTitle.equalsIgnoreCase(strPageTitlePOConfirmation))
				bFlag = true;
			else
				bFlag = false;
		}

		objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 1 - Page title of PO Confimation tool is wrong *****");
		try{
	        wait(3000);
	        objAdjunoLIMAPoConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMAPoConfirmationPOM.getBtnOK1());
	        wait(3000);
	        }
	        catch(NoSuchElementException e)
	        {
	        	
	        }
		objSoftAssert.assertAll();
	}

	@Test(priority = 5)
	public void test_1_checkForChevorons() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesChevronExist(objAdjunoLIMAPoConfirmationPOM.getChvSearch()), true, "***** Test ID : 1 - In PO Confirmation Tool \"Search Chevron\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesChevronExist(objAdjunoLIMAPoConfirmationPOM.getChvSelect()), true, "***** Test ID : 1 - In PO Confirmation Tool  \"Select Chevron\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesChevronExist(objAdjunoLIMAPoConfirmationPOM.getChvConfirmation()), true,"***** Test ID : 1 - In PO Confirmation Tool \"Confirmation Chevron\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesChevronExist(objAdjunoLIMAPoConfirmationPOM.getChvComplete()), true, "***** Test ID : 1 - In PO Confirmation Tool \"Complete Chevron\" is not found *****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 10)
	public void test_1_checkFortxtfields() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_Origin"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Origin field\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_Port"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Origin port field\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_Vendor"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Vendor field\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_VendorReference"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Vendor Reference field\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_BuyingAgent"),true, "***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"BuyingAgent field\" is not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_Mode"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Mode field\" is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_PONumber"),true, "***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"PO Number field\" is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_Product"),true, "***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Product field\" is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName,"PARAM_ConfirmationStatus"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Production status field\" is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.checkDoesFieldsExist(strSearchFormName, "PARAM_WorkflowStatus"), true,"***** Test ID : 1 - In PO Confirmation Tool Under Search chevron \"Workflow status field\" is not found*****");
		objSoftAssert.assertAll();
	}

	@Test(priority = 15)
	public void test_2_verifyConfirmationStatusMandatory() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 2 - In PO Confirmation Tool Under Search chevron \"Confirmation status field\" is not Cleared *****");
		wait(2000);
		
		String validationMsg =objAdjunoLIMAPoConfirmationPOM.getValidationMessageFieldElement(strSearchFormName, "PARAM_ConfirmationStatus");
		
		if(!validationMsg.equals(""))
        {
            if(validationMsg.contains("&#39;"))
            {
            	validationMsg = validationMsg.replace("&#39;", "'");
                objSoftAssert.assertEquals(validationMsg.equalsIgnoreCase("> 'Status' is a required field"), true, "***** Test ID : 2 - When Status field is empty,\"'Status' is the required field\" validation message is not displayed under PO Confirmation Search Chevron*****");
            }else{
                strMessage =  strMessage + "In PO Confirmation Tool Under Search chevron \"Confirmation status field\" is not mandatory ";
            }
        }
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2 - "+ strMessage +"*****");
		objSoftAssert.assertAll();

	}

	@Test(priority = 20)
	public void test_3_verifyIncorrectmsg() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true," ***** Test ID : 3 - In PO Confirmation Tool Under Search chevron \"PO Number field\" is not Cleared *****");
		wait(2000);
		if (!isNullOrBlank(strInvalidPoNumber)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strInvalidPoNumber, strSearchFormName,"PARAM_PONumber"), true,"***** Test ID : 3 - In PO Confirmation Tool Under Search chevron \"PO Number field\"  value is not set *****");
		}

		if (!isNullOrBlank(strConfirmationStatusAny)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusAny, strSearchFormName,"PARAM_ConfirmationStatus"), true,"***** Test ID : 3 - In PO Confirmation Tool Under Search chevron \"PO Number field\"  value is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true,"***** Test ID : 3 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		if (objAdjunoLIMAPoConfirmationPOM.getNoOfRowsResulted() == 0) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getContentMsg()), true, "***** Test ID : 3 - In PO Confirmation Tool Under Select chevron \"No items were found.\" validation message is not displayed *****");
		} else {
			bSearchResultsProductsFound = true;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, false,"***** Test ID : 3 - In PO Confirmation Tool Under Select chevron, for PO Number"+ strInvalidPoNumber +"product is displayed *****");
		}

		objSoftAssert.assertAll();
	}

	@Test(priority = 25)
	public void test_4_verifyStatusForConfirmationStatusAny() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true,"***** Test ID : 4 - In PO Confirmation Tool Under Select Page \"PO Number field\" is not Cleared *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 4 - In PO Confirmation Tool Under Select Page \"Confirmation status field\" is not Cleared *****");

		if (!isNullOrBlank(strConfirmationStatusAny)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusAny, strSearchFormName,"PARAM_ConfirmationStatus"), true,"***** Test ID : 4 - In PO Confirmation Tool Under Select chevron \"confirmation field\"  value is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButton(strSearchFormName, "FORK_POConfirmation_SUT_RefineSearch"),true, "***** Test ID : 4 - In PO Confirmation Tool Under Select chevron \"Refine Search \"Button is not Clicked *****");
		wait(2000);
		verifyProductGridHeader();
		objSoftAssert.assertAll();

	}

	@Test(priority = 30)
	public void test_5_clickSearchtab() {
		SoftAssert objSoftAssert = new SoftAssert();

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSearch1()),true, "***** Test ID : 5 - In PO Confirmation Tool \"Search chevron \" is not Clicked *****");
		wait(2000);
		objSoftAssert.assertAll();

	}

	@Test(priority = 35)
	public void test_4_verifyStatusForAny() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 4 - In PO Confirmation Tool Under Search Page \"Confirmation status field\" is not Cleared *****");

		if (!isNullOrBlank(strConfirmationStatusAny)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusAny, strSearchFormName,"PARAM_ConfirmationStatus"), true,"***** Test ID : 4 - In PO Confirmation Tool Under Search Page \"Confirmation status field\" is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 4 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		
		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyStatusForAllProduct(strConfirmationStatusAny);
		if (strMessage.equals("")) {

		} else {
			strMessage = strMessage+"In Po Confirmation Tool under Select chevron "+ strMessage;
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 4 - "+ strMessage +" *****");

		verifyProductGridHeader();
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 40)
	public void test_5_clickSearchtab1() {
		SoftAssert objSoftAssert = new SoftAssert();

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSearch1()),true, "***** Test ID : 5 - In PO Confirmation Tool \"Search chevron \" is not Clicked *****");
		wait(2000);
		objSoftAssert.assertAll();

	}

	@Test(priority = 45)
	public void test_6_verifyStatusForPending() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true,"***** Test ID : 6 - In PO Confirmation Tool Under Search Page \"PO Number field\" is not Cleared *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 6 - In PO Confirmation Tool Under Search Page \"Confirmation status field\" is not Cleared *****");

		if (!isNullOrBlank(strConfirmationStatusPending)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusPending,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 6 - In PO Confirmation Tool Under Search Page \"Confirmation status field\" is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()), true, "***** Test ID : 6 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyStatusForAllProduct(strConfirmationStatusPending);
		if (strMessage.equals("")) {
	
		} else {
			strMessage = strMessage+"In Po Confirmation Tool under Select chevron "+ strMessage;
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6 - "+ strMessage +" *****");
		verifyProductGridHeader();

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 50)
	public void test_7_clickSearchtab2() {
		SoftAssert objSoftAssert = new SoftAssert();

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSearch1()),true, "***** Test ID : 7 - In PO Confirmation Tool \"Search chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertAll();

	}

	@Test(priority = 55)
	public void test_8_verifyStatusForConfirmed() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true,"***** Test ID : 8 - In PO Confirmation Tool under Search chevron \"PO Number \"field is not cleared *****");

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 8 - In PO Confirmation Tool under Search chevron \"Confirmation status \"field is not cleared *****");

		if (!isNullOrBlank(strConfirmationStatusConfirmed)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusConfirmed,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 8 - In PO Confirmation Tool under Search chevron \"Confirmation status\"Field value is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 8 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyStatusForAllProduct(strConfirmationStatusConfirmed);
		if (strMessage.equals("")) {
			
		} else {
			strMessage = strMessage+"In Po Confirmation Tool under Select chevron "+ strMessage;
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8 - "+ strMessage +" *****");
		verifyProductGridHeader();

		objSoftAssert.assertAll();
	}

	@Test(priority = 60)
	public void test_9_verifyStatusForUnderQuery() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSearch1()),true, "***** Test ID : 9 - In PO Confirmation Tool \"Search chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 9 - In PO Confirmation Tool under Search chevron \"Confirmation status\"field is not cleared *****");

		if (!isNullOrBlank(strConfirmationStatusUnderQuery)) {
         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusUnderQuery,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 9 - In PO Confirmation Tool under Search chevron \"Confirmation status \"field value is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 9 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyStatusForAllProduct(strConfirmationStatusUnderQuery);
		if (strMessage.equals("")) {
			
		} else {
			strMessage = "In Po Confirmation Tool Under Select chevron "+ strMessage;
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9 - "+ strMessage +" *****");
		wait(1200);
		verifyProductGridHeader();

		objSoftAssert.assertAll();
	}

	public void verifyProductGridHeader() {
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThPONumber()), true, "***** In PO Confirmation Tool Under Select chevron \"PO Number\" Grid Field is not displayed *****");

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThVendor()), true, "***** In PO Confirmation Tool Under Select chevron \"Vendor\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThVendorref()), true, "***** In PO Confirmation Tool Under Select chevron \"VendorRef\" Grid Field is not displayed *****");

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThFactory()), true, "***** In PO Confirmation Tool Under Select chevron \"Factory\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThBuyingAgent()), true, "***** In PO Confirmation Tool Under Select chevron \"BuyingAgent\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThShipDate()), true, "***** In PO Confirmation Tool Under Select chevron \"Ship date\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThLatestShipDate()), true, "***** In PO Confirmation Tool Under Select chevron \"Latest Ship date\" Grid Field is not displayed *****");

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThDeliveryDate()), true, "***** In PO Confirmation Tool Under Select chevron \"Delivery Date\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThPOQuantity()), true, "***** In PO Confirmation Tool Under Select chevron \"PO Quantity\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThPriority()), true, "***** In PO Confirmation Tool Under Select chevron \"Priority\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThConfirmationstatus()), true,"***** In PO Confirmation Tool Under Select chevron \"Confirmation Status\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThOrderDetails()), true, "***** In PO Confirmation Tool Under Select chevron \"Order Details\" Grid Field is not displayed *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getThDialogs()), true, "***** In PO Confirmation Tool Under Select chevron \"Dialogs\" Grid Field is not displayed *****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 65)
	public void test_10_11_12_verifyPoConfirmationCheveron() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSearch1()),true, "***** Test ID : 10_11_12_In PO Confirmation Tool \"Search chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 10_11_12_In PO Confirmation Tool Under Search chevron\"Confirmation Status\"Field is not cleared *****");

		if (!isNullOrBlank(strConfirmationStatusPending)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusPending,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 10_11_12_In PO Confirmation Tool Under Search chevron\"Confirmation Status\"Field value is not set *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 10_11_12_In PO Confirmation Tool \"Select Chevron\" is not clicked *****");
		wait(2000);
		lstSearchResults = new ArrayList<PurchaseOrders>();
		if (objAdjunoLIMAPoConfirmationPOM.getNoOfRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMAPoConfirmationPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvConfirmation1()), true,"***** Test ID : 10_11_12_In PO Confirmation Tool \"Confirmation Chevron\" is not clicked *****");
			wait(2000);
		} else {
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 10_11_12 -In PO Confirmation Tool Under select chevron\"No items were found.\" Validation Message is displayed *****");
		}

		objSoftAssert.assertAll();

	}

	@Test(priority = 70)
	public void test_13_verifyCaptionsProductDetails() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
	
		String strMessage = "";

		ArrayList<String> list = new ArrayList<String>();

		list = objAdjunoLIMAPoConfirmationPOM.getCaptionsList(strPOConfirmationForm, "GridConfirmation");

		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyCaptionsONGrid(list,nProductDetails, 20);

		if (strMessage.equals("")) {

		} else {
			strMessage = "In Po Confirmation Tool under Confirmation chevron "+ strMessage;
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 13 - "+ strMessage +" *****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 75)
	public void test_14_ComparePurchaseOrderStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		
		String strReturnMessage = objAdjunoLIMAPoConfirmationPOM.verifyProductsDataOnGrid(strPOConfirmationForm,"GridOrder", lstSearchResults);
		if(strReturnMessage.equalsIgnoreCase("")){
			
		}else{
			strReturnMessage =strReturnMessage+"In PO COnfirmation tool the selected PO number under Select Chevron do not match with PO Number details displayed in Confirmation Chevron under Purchase Order Details Grid ";
		}
	    objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true,"***** Test Id :14 - "+strReturnMessage+" *****");
		objSoftAssert.assertAll();
	}

	@Test(priority = 80)
	public void verifyCaptionsPurchaseOrderDetails() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		boolean bFlab;
		String strMessage = "";

		ArrayList<String> list = new ArrayList<String>();

		list = objAdjunoLIMAPoConfirmationPOM.getCaptionsList(strPOConfirmationForm, "GridOrder");

		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyCaptionsONGrid(list,nPurchaseOrderDetails, 10);

		if (strMessage.equals("")) {
			bFlab = true;
		} else {
			bFlab = false;
		}

		objSoftAssert.assertEquals(bFlab, true, "***** In PO Confirmation Tool Under Confirmation chevron "+ strMessage +"*****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 85)
	public void test_15_verifyMandatoryFieldComment() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
	
		StrPONumberQuery = objAdjunoLIMAPoConfirmationPOM.getGridCellValue(strPOConfirmationForm, "GridOrder", 0, "PONumber");
		
		if (!isNullOrBlank(strConfirmationStatus1)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatus1,strPOConfirmationForm, "chkConfirmationStatus"),true, "***** Test ID : 15 -In PO Confirmation Tool Under Confirmation chevron \"Confirmation Status\" Field Value in Grid is not set *****");
		}

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.verifyProductConfirmation(strPOConfirmationForm,"GridConfirmation", 0, "SelectProduct"), true,"***** Test ID : 15 -In PO Confirmation Tool Under Confirmation chevron \"Checkbox\" is not clicked in Grid *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButton(strPOConfirmationForm, "btnCopyGridProduct"), true,"***** Test ID : 15 -In PO Confirmation Tool Under Confirmation chevron \"Update Button\" is not clicked in Grid*****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvComplete1()), true,"***** Test ID : 15 -In PO Confirmation Tool \"Complete Chevron\" is not clicked *****");
		wait(2200);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getMandatorymsg()), true, "***** Test ID : 15 -In PO Confirmation Tool Under Complete Chevron "+ objAdjunoLIMAPoConfirmationPOM.getMandatorymsg()+" Complete Message is not Displayed *****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 90)
	public void test_16_verifyCompleteprocess() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValueForGridCell(strPOConfirmationForm,"GridConfirmation", 0, "ReasonCode", strReasonCode),true, "***** Test ID : 16 -In PO Confirmation Tool Under Confirmation chevron \"Reason Code\" Field Value is not set in Grid*****");

        objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValueForGridCell(strPOConfirmationForm,"GridConfirmation", 0, "Comments", strComments), true,"***** Test ID : 16 -In PO Confirmation Tool Under Confirmation chevron \"Comments\" Field Value is not set in Grid *****");

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButton(strPOConfirmationForm, "btnCopyGridProduct"), true,"***** Test ID : 16 -In PO Confirmation Tool Under Confirmation chevron \"Update Button\" is not clicked in Grid*****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvComplete1()), true,"***** Test ID : 16 -In PO Confirmation Tool \"Complete Chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertAll();
	}

	@Test(priority = 95)
	public void test_17_verifyViewDetails() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoLIMAPoConfirmationPOM.getBtnViewDetail().click();
		wait(3000);
		String poDetails = objAdjunoLIMAPoConfirmationPOM.getTxtViewDetail().getText();
		String[] vals = poDetails.split("track");
		
		String trackValue[] = vals[1].split("'",14);

		strTrackN0 = trackValue[1];

		objSoftAssert.assertAll();

	}

	@Test(priority = 100)
	public void test_18_AccessEdit() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkTrack(),objAdjunoLIMAPoConfirmationPOM.getLnkEdit());

		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getFldRefNo()), true,"***** Test ID : 18 - In Edit or Create a Track Page\"Reference\" Field is not Displayed *****");

		if (!isNullOrBlank(strTrackN0)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMAPoConfirmationPOM.getFldRefNo(),strTrackN0), true,"***** Test ID : 18 - In Edit or Create a Track Page\"Reference\" Field value is not set *****");
		}
		
		objAdjunoLIMAPoConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMAPoConfirmationPOM.getBtnApply());
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getTitlePurchaseOrder()), true, "***** Test ID : 18 - In Edit or Create a Track Page \"Purchase Order\" Page title is not Displayed *****");
		wait(2000);
		long min = objAdjunoLIMAPoConfirmationPOM.getTrackValue(strLIMAUserName);
       
		if(min<=355){
            
        }else{
            strMessage = " In Purchase Order page the Order Confirmation Record is not found in todays date";
        }
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID : 18 - "+strMessage);
				
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 105)
	public void test_19_verifyPOStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkFind(),objAdjunoLIMAPoConfirmationPOM.getLnkPOStatusTool());
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_16PO"), true,"***** Test ID : 19 - In PO Status page \"PO Number\" Field is not cleared *****");
		
		if (!isNullOrBlank(StrPONumberQuery)) {
			
	         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(StrPONumberQuery,strPOConfirmationPOStatusForm, "Param_16PO"),true, "***** Test ID : 19 - In PO Status page \"PO Number\" Field value is not set *****");
		}
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_17Item"), true,"***** Test ID : 19 - In PO Status page \"Product Number\" Field is not cleared *****");
		wait(1200);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMAPoConfirmationPOM.getBtnRun()), true , "***** Test ID : 19 - In PO Status page \"Run\" button is not clicked *****");
		wait(2000);
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 110)
	public void test_20_verifyStatusColumnasPOQuery() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		
		List<WebElement> PONumber = objAdjunoLIMAPoConfirmationPOM.getLstPONumber();
	    List<WebElement> status = objAdjunoLIMAPoConfirmationPOM.getLstStatus();
	    for(int i=0;i<=PONumber.size()-1;i++){
	    	if(StrPONumberQuery.equalsIgnoreCase(PONumber.get(i).getText())){
	    		objSoftAssert.assertEquals(status.get(i).getText().equalsIgnoreCase("PO Query"),true,"***** Test ID : 20 - In PO Status page \"PO Number\" Status is not matched as \"PO Query\". *****");
	    		break;
	    	}
	    }
	    objSoftAssert.assertAll();
	}

	
	@Test(priority = 115)
	public void test_21_VerifyStatusforPoNumberasPOQuery() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
				
		objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkTools(),objAdjunoLIMAPoConfirmationPOM.getLnkPOConfirmation());
		
        objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true,"***** Test ID : 21 - In PO Confirmation Tool Under Search chevron \"PO Number\" Field is not cleared *****");
        objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 21 - In PO Confirmation Tool Under Search chevron \"Confirmation Status\" Field is not cleared *****");
		
		if (!isNullOrBlank(StrPONumberQuery)) {
	         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(StrPONumberQuery,strSearchFormName, "PARAM_PONumber"),true, "***** Test ID : 21 - In PO Confirmation Tool Under Search chevron \"PO Number\" Field value is not set *****");
		}


		if (!isNullOrBlank(strConfirmationStatusAny)) {
         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusAny,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 21 - In PO Confirmation Tool Under Search chevron \"Confirmation Status\" Field value is not set *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 21 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.getteConfirmationstatus().getText().equalsIgnoreCase("Under Query"),true ,"***** Test ID : 21 - In PO Confirmation Tool Under Select chevron \"PO Number\" status is not matching as \"Under Query\". *****");

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 120)
	public void test_22_23_verifyPoConfirmationCheveron() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSearch1()),true, "***** Test ID : 22-23 - In PO Confirmation Tool  \"Search Chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true,"***** Test ID : 22-23 - In PO Confirmation Tool Under Search chevron \"PO Number\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 22-23 - In PO Confirmation Tool Under Search chevron \"Confirmation Status\" field is not cleared *****");

		if (!isNullOrBlank(strConfirmationStatusPending)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusPending,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 22-23 - In PO Confirmation Tool Under Search chevron \"Confirmation Status\" field value is not set *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 22-23 - In PO Confirmation Tool \"Select Chevron\" is not clicked *****");
		wait(2000);	
		lstSearchResults = new ArrayList<PurchaseOrders>();
		if (objAdjunoLIMAPoConfirmationPOM.getNoOfRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMAPoConfirmationPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvConfirmation1()), true,"***** Test ID : 22-23 - In PO Confirmation Tool \"Confirmation Chevron\" is not clicked *****");
			wait(2000);
		} else {
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 22-23 - In PO Confirmation Tool Under Select chevron \"No items were found.\" Validation Message is Displayed *****");
		}

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 125)
	public void test_24_verifyCaptionsProductDetails() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		boolean bFlab;
		String strMessage = "";

		ArrayList<String> list = new ArrayList<String>();

		list = objAdjunoLIMAPoConfirmationPOM.getCaptionsList(strPOConfirmationForm, "GridConfirmation");

		strMessage = objAdjunoLIMAPoConfirmationPOM.verifyCaptionsONGrid(list,nProductDetails, 20);

		if (strMessage.equals("")) {
			bFlab = true;
		} else {
			bFlab = false;
		}

		objSoftAssert.assertEquals(bFlab, true, "***** Test ID : 24 - In PO Confirmation Tool Under Confirmation chevron " + strMessage +" *****");

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 130)
	public void test_25_ComparePurchaseOrderStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		if (bSearchResultsProductsFound) {
			String strReturnMessage = objAdjunoLIMAPoConfirmationPOM.verifyProductsDataOnGrid(strPOConfirmationForm,"GridOrder", lstSearchResults);

			objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true,strReturnMessage);

		} else
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 25 - In PO COnfirmation tool the selected PO number under Select Chevron do not match with PO Number details displayed in Confirmation Chevron under Purchase Order Details Grid *****");

		objSoftAssert.assertAll();
	}

	@Test(priority = 135)
	public void test_26_verifyCompleteprocessStatusConfirmed() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);


		StrPONumberConfirmed = objAdjunoLIMAPoConfirmationPOM.getGridCellValue(strPOConfirmationForm, "GridOrder", 0, "PONumber");
		
		if (!isNullOrBlank(strConfirmationStatus2)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatus2,strPOConfirmationForm, "chkConfirmationStatus"),true, "***** Test ID : 26 - In PO Confirmation Tool Under Confirmation chevron \"Confirmation Status\" Field Value is not set in Grid *****");
		}
		
		if (!isNullOrBlank(strReasonCode)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strReasonCode,strPOConfirmationForm, "txtReasonCode"),true, "***** Test ID : 26 - In PO Confirmation Tool Under Confirmation chevron \"Reason\" Field Value is not set in Grid *****");
		}
		
		if (!isNullOrBlank(strComments)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strComments,strPOConfirmationForm, "txtComments"),true, "***** Test ID : 26 - In PO Confirmation Tool Under Confirmation chevron \"Comment\" Field Value is not set in Grid *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMAPoConfirmationPOM.getClkMultipleCheckBox()), true , "***** Test ID : 26 -In PO Confirmation Tool Under Confirmation chevron \"Checkbox\" is not clicked in Grid *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButton(strPOConfirmationForm, "btnCopyGridProduct"), true,"***** Test ID : 26 -In PO Confirmation Tool Under Confirmation chevron \"Update\" Button is not Clicked in Grid *****");
		wait(2000);
	
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvComplete1()), true,"***** Test ID : 26 -In PO Confirmation Tool \"Complete Chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 140)
	public void test_27_verifyViewDetailsStatusConfirmed() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoLIMAPoConfirmationPOM.getBtnViewDetail().click();
		wait(3000);
		String poDetails = objAdjunoLIMAPoConfirmationPOM.getTxtViewDetail().getText();
		String[] vals = poDetails.split("track");

		String valss[] = vals[1].split("'", 14);
		strTrackN01 = valss[1];		
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 145)
	public void test_28_AccessEditStatusConfirmed() throws ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkTrack(),objAdjunoLIMAPoConfirmationPOM.getLnkEdit());

		wait(2000);

		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getFldRefNo()), true,"***** Test ID : 28 - In Edit or Create a Track Page\"Reference\" Field is not Displayed *****");

		if (!isNullOrBlank(strTrackN0)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValueForWebElement(objAdjunoLIMAPoConfirmationPOM.getFldRefNo(),strTrackN01), true,"***** Test ID : 28 - In Edit or Create a Track Page\"Reference\" Field value is not set *****");
		}
		
		objAdjunoLIMAPoConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMAPoConfirmationPOM.getBtnApply());
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.isElementPresent(objAdjunoLIMAPoConfirmationPOM.getTitlePurchaseOrder()), true, "***** Test ID : 28 - In Edit or Create a Track Page\"Purchase Order\" title is not Displayed *****");
		wait(2000);
		long min = objAdjunoLIMAPoConfirmationPOM.getTrackValue(strLIMAUserName);
       
		if(min<=355){
            
        }else{
            strMessage = " In Purchase Order page the Order Confirmation Record is not found in todays date";
        }
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID : 28 - "+ strMessage);
				
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 150)
	public void test_29_verifyPOStatusValue() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);

		objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkFind(),objAdjunoLIMAPoConfirmationPOM.getLnkPOStatusTool());
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_16PO"), true,"***** Test ID : 29 - In PO Status page \"PO Number\" Field is not cleared *****");
		
		if (!isNullOrBlank(StrPONumberConfirmed)) {
			
	         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(StrPONumberConfirmed,strPOConfirmationPOStatusForm, "Param_16PO"),true, "***** Test ID : 29 - In PO Status page \"PO Number\" Field value is not set *****");
		}
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strPOConfirmationPOStatusForm, "Param_17Item"), true,"***** Test ID : 29 - In PO Status page \"Product Number\" Field is not cleared *****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickButtonUsingWebElement(objAdjunoLIMAPoConfirmationPOM.getBtnRun()), true , "***** Test ID : 29 - In PO Status page \"Run\" button is not clicked *****");
		wait(2000);

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 155)
	public void test_30_verifyStatusColumnasPOConfirmed() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
		
		List<WebElement> PONumber = objAdjunoLIMAPoConfirmationPOM.getLstPONumber();
	    List<WebElement> status = objAdjunoLIMAPoConfirmationPOM.getLstStatus();
	    for(int i=0;i<=PONumber.size()-1;i++){
	    	if(StrPONumberConfirmed.equalsIgnoreCase(PONumber.get(i).getText())){
	    		objSoftAssert.assertEquals(status.get(i).getText().equalsIgnoreCase("PO Confirmed"),true,"***** Test ID : 30 - In PO Status page \"PO Number\" Status is not matched as \"PO Confirmed\".");
	            break;
	    	}
	    }
		
		objSoftAssert.assertAll();
	}

	@Test(priority = 160)
	public void test_31_VerifyStatusforPoNumberasPOConfirmed() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
				
		objAdjunoLIMAPoConfirmationPOM.callMouseHover(strSearchFormName,objAdjunoLIMAPoConfirmationPOM.getLnkTools(),objAdjunoLIMAPoConfirmationPOM.getLnkPOConfirmation());
		
        objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_PONumber"), true,"***** Test ID : 31 - In PO Confirmation Tool Under Search chevron \"PO Number\" Field is not cleared *****");
        objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clearInputField(strSearchFormName, "PARAM_ConfirmationStatus"), true,"***** Test ID : 31 - In PO Confirmation Tool Under Search chevron \"Confirmation Status\" Field is not cleared *****");
		
		if (!isNullOrBlank(StrPONumberConfirmed)) {
	         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(StrPONumberConfirmed,strSearchFormName, "PARAM_PONumber"),true, "***** Test ID : 31 - In PO Confirmation Tool Under Search chevron \"PO Number\" Field value is not set *****");
		}


		if (!isNullOrBlank(strConfirmationStatusAny)) {
         objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.setFieldValue(strConfirmationStatusAny,strSearchFormName, "PARAM_ConfirmationStatus"),true, "***** Test ID : 31 - In PO Confirmation Tool Under Search chevron \"Confirmation Status\" Field value is not set *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.clickChevron(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true, "***** Test ID : 31 - In PO Confirmation Tool \"Select chevron\" is not clicked *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAPoConfirmationPOM.getteConfirmationstatus().getText().equalsIgnoreCase("Confirmed"),true ,"***** Test ID : 31 - In PO Confirmation Tool Under Select chevron \"PO Number\" status is not matching as \"Confirmed\". *****");

		objSoftAssert.assertAll();
	}
		
	
	 @AfterTest 
	 public void terminateBrowser() 
	 { 
		 driver.quit(); 
	 }
	 
}