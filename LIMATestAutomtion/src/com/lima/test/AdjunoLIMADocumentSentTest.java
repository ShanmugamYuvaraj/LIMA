package com.lima.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.lima.dto.DocumentSent;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMADocumentSentPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMADocumentSentTest {
	
	WebDriver driver;
    long nPage_Timeout = 0;
	String strTestURL;
	String strDriver;

	ArrayList<DocumentSent> lstSearchResults;
	String strLIMAUserName;
	String strLIMAPassword;
	String strPageTitleDocumentsSent;
	String strSearchDocumentsSentForm;
	String strDocumentsSentFormUpdate;
	String strDocumentsDashBoardUpdate;
	boolean bSearchResultsProductsFound = true;
	
	
	String strDocumentStatus;
	String strDocumentStatus1;
	String strDocumentStatus2;
	String strStatus;
	String strCourier;
	String strCourierAWB;
	String strDateSent;
	String strRecepient;
	String strOriginalCopy;
	
	
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoUILibrary objAdjunoUILibrary;
	 AdjunoLIMALibrary  objAdjunoLIMALibrary;
	AdjunoLIMADocumentSentPOM objAdjunoLIMADocumentSentPOM;
    
	String  strCurrDate;
	String strFileName;
	String strSize;
	DocumentSent doc3;
	long nRow;
	ArrayList<String> hblList;
	
	
	@BeforeTest
	public void setup() {
		objAdjunoLIMALibrary = new AdjunoLIMALibrary();

		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());

			XPath xPath = XPathFactory.newInstance().newXPath();

			Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout", dDoc, XPathConstants.NODE);
			nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());

			Node testURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL",dDoc, XPathConstants.NODE);
			strTestURL = testURL.getTextContent();

			Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
			strLIMAUserName = limaUserName.getTextContent();

			Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
			strLIMAPassword = limaPassword.getTextContent();

			Node PageTitleDocumentsSent = (Node) xPath.evaluate("/config/LIMA/Page_Title_DocumentsSent", dDoc,XPathConstants.NODE);
			strPageTitleDocumentsSent = PageTitleDocumentsSent.getTextContent();

			Node SearchDocumentsSentForm = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_Documents_SentForm",dDoc, XPathConstants.NODE);
			strSearchDocumentsSentForm = SearchDocumentsSentForm.getTextContent();
			
			Node DocumentsSentFormUpdate = (Node) xPath.evaluate("/config/Generic/Documents_SentForm_Update",dDoc, XPathConstants.NODE);
			strDocumentsSentFormUpdate = DocumentsSentFormUpdate.getTextContent();
			
			Node DocumentsDashBoardUpdate = (Node) xPath.evaluate("/config/Generic/Documents_DashBoard_Update",dDoc, XPathConstants.NODE);
			strDocumentsDashBoardUpdate = DocumentsDashBoardUpdate.getTextContent();

			
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
			Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrLIMADocumentSentXMLDataFileName());

			XPath xPath1 = XPathFactory.newInstance().newXPath();
			
			Node DocumentStatus = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/DocumentStatus", dDoc1,XPathConstants.NODE);
			strDocumentStatus = DocumentStatus.getTextContent();
			
			Node DocumentStatus1 = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/DocumentStatus1", dDoc1,XPathConstants.NODE);
			strDocumentStatus1 = DocumentStatus1.getTextContent();
			
			Node DocumentStatus2 = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/DocumentStatus2", dDoc1,XPathConstants.NODE);
			strDocumentStatus2 = DocumentStatus2.getTextContent();
			
			Node Courier = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/Courier", dDoc1,XPathConstants.NODE);
			strCourier = Courier.getTextContent();
			
			Node CourierAWB = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/CourierAWB", dDoc1,XPathConstants.NODE);
			strCourierAWB = CourierAWB.getTextContent();
			
			Node Recepient = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/Recepient", dDoc1,XPathConstants.NODE);
			strRecepient = Recepient.getTextContent();
			
			Node OriginalCopy = (Node) xPath1.evaluate("/DocumentsSent/SearchParams/OriginalCopy", dDoc1,XPathConstants.NODE);
			strOriginalCopy = OriginalCopy.getTextContent();

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
	public void Test_10_1_accessDocumentSent() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		String strTitle = objAdjunoLIMADocumentSentPOM.callMouseHover(strSearchDocumentsSentForm,objAdjunoLIMADocumentSentPOM.getLnkTools(),objAdjunoLIMADocumentSentPOM.getLnkDocumentSent());

		boolean bFlag = true;

		if (isNullOrBlank(strTitle))
			bFlag = false;

		if (!(isNullOrBlank(strTitle))) {
			if (strTitle.equalsIgnoreCase(strPageTitleDocumentsSent))
				bFlag = true;
			else
				bFlag = false;
		}

		objSoftAssert.assertEquals(bFlag, true, "*****_10_1_Title of the page is wrong*****");
		try{
	        wait(3000);
	        objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnOK1());
	        wait(3000);
	        }
	        catch(NoSuchElementException e)
	        {
	        	
	        }
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 11)
	public void checkforchevrons() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		//objAdjunoLIMADocumentSentPOM.callMouseHover(strSearchDocumentsSentForm,objAdjunoLIMADocumentSentPOM.getLnkTools(),objAdjunoLIMADocumentSentPOM.getLnkDocumentSent());
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesChevronExist(objAdjunoLIMADocumentSentPOM.getChvSearch()), true, "*****Search Chevorn not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesChevronExist(objAdjunoLIMADocumentSentPOM.getChvSelect()), true, "*****Select Chevorn not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesChevronExist(objAdjunoLIMADocumentSentPOM.getChvUpdate()), true,"*****Confirmation Chevorn not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesChevronExist(objAdjunoLIMADocumentSentPOM.getChvComplete()), true,"*****Complete Chevorn not found*****");
		objSoftAssert.assertAll();
	}
	
	

	 @Test(priority = 16)
	public void checkForDocumentSentfields() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		veifyDocumentsSenttxtfields();
		objSoftAssert.assertAll();
    }
	
	
	public void veifyDocumentsSenttxtfields(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_Origin"), true,"Origin field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_Port"), true,"Origin Port field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_Mode"), true,"Mode field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_Vessel"), true,"Vessel field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_DocumentStatus"), true,"Document Status field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_Container"),true, "Container field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_HBL"),true, "HBL field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_Vendor"), true,"Vendor field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_PONumber"), true,"PO Number field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.checkDoesFieldsExist(strSearchDocumentsSentForm, "PARAM_WorkflowStatus"),true, "Work flow Status field is not found");
		
		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority = 25)
	public void setValueindocumentsentfield() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," Document Status is not clear ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");
		wait(2000);

		if (!isNullOrBlank(strDocumentStatus)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"Not able to  set value in Document Status field");
		} else {
			strMessage = strMessage + strDocumentStatus + "null";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "Not able to click to select chevron");
	    objSoftAssert.assertAll();
	}
	
	/*@Test(priority = 26)
	public void checkSelectPage() {
		SoftAssert objSoftAssert = new SoftAssert();
	//	String strMes
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		wait(2000);
		List<DocumentSent> list = new ArrayList<DocumentSent>();

		list = objAdjunoLIMADocumentSentPOM.getStaus();

		objAdjunoLIMADocumentSentPOM.compareshipmentgriddata(strHBL,strDocumentStatus,list);
        
		objSoftAssert.assertAll();
	}
	*/

	
	@Test(priority = 28)
	public void Test_11_1_compareGridDataforsingle() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		hblList = new ArrayList<String>();
		
		for (int i = 0; i < objAdjunoLIMADocumentSentPOM.getLstHBLS().size()-1; i++) {
			hblList.add(objAdjunoLIMADocumentSentPOM.getLstHBLS().get(i).getText());
		}
		System.out.println("hblList size"+hblList.size());
		strMessage = objAdjunoLIMADocumentSentPOM.compareshipmentgriddata1(strDocumentStatus);
        
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** test Id:11_1 "+ strMessage +"  ******");
		objSoftAssert.assertAll();

	}
	
	
	@Test(priority = 30)
	public void Test_11_2_compareGridDataformulti() {
		SoftAssert objSoftAssert = new SoftAssert();
        
		String strMessage="";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
        
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSearch1()),true, "*****_11_2_Not able to click select1 chevron*****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," *****_11_2_Document Status is not clear***** ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");

		if (!isNullOrBlank(hblList.get(0))) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(hblList.get(0), strSearchDocumentsSentForm,"PARAM_HBL"), true,"*****_11_2_Not able to  set value in HBL field*****");
		} else {
			strMessage = strMessage + hblList.get(0) + "null";
		}
		if (!isNullOrBlank(strDocumentStatus)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"*****_11_2_Not able to  set value in Document Status field*****");
		} else {
			strMessage = strMessage + strDocumentStatus + "null";
		}
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "*****_11_2_ot able to click to select chevron*****");
	

		objAdjunoLIMADocumentSentPOM.compareshipmentgriddata(hblList.get(0),strDocumentStatus);
        
		//objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.verifyNoelement(),true, "Element is Present");
		objSoftAssert.assertAll();

	}
	

	@Test(priority = 32)
	public void selectCheckBoxData() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		lstSearchResults = new ArrayList<DocumentSent>();
		if (objAdjunoLIMADocumentSentPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMADocumentSentPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvUpdate1()), true,"******Not able to click to Update chevron*****");

		} else {
			bSearchResultsProductsFound = false;
		}

		objSoftAssert.assertAll();

	}

	@Test(priority = 35)
	public void Test_12_5_checkforUpdateChevrons() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isPageLoaded(strDocumentsSentFormUpdate), true,"Not able to checkt Update page");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhCourier()),true, "*****_12_5_Courier grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhCourierAWB()),true,"*****_12_5_CourierAWB grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhDateSent()), true,"*****_12_5_Date Sent grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhRecepient()), true,"*****_12_5_Recepient grid header is not found*****");

		objSoftAssert.assertAll();

	}
	
	
	
	@Test(priority = 42)
	public void Test_12_7_checkforShipmentDetails() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		//objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isPageLoaded(strDocumentsSentFormUpdate), true,"Not able to checkt Update page");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhOriginPort()), true,"*****_12_7_Origin Port grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhDestination()), true,"*****_12_7_Destination Port grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhMode()), true,"*****_12_7_Mode grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhVessel()), true,"*****_12_7_Vessel grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhVoyage()), true,"*****_12_7_Voyage grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhCTD()), true,"*****_12_7_CTD grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhETA()), true,"*****_12_7_ETA grid header is not found*****");
		
		objSoftAssert.assertAll();

	}
	
	
	@Test(priority = 49)
	public void Test_12_8_6_checkforDocumentsAttachment() {
		SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhHBL_Doc()), true,"*****_12_8_6_HBL Doc grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhVendor_Doc()), true,"*****_12_8_6_Vendor Doc Port grid header is not found*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getGhDialog_Doc()), true,"*****_12_8_6_Dialog Doc grid header is not found*****");
		
		
		objSoftAssert.assertAll();

	}
	
	
	
	@Test(priority = 54)
	public void Test_12_8_7_checkforHBLlink() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		  objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isHyperlinkPresent(objAdjunoLIMADocumentSentPOM.getLnkHBLHyperlink()),true,"*****_12_8_7_Track AWB couldn't be clicked*****");
	 
		wait(2000);
		driver.navigate().back();
		

		wait(5000);
       objSoftAssert.assertAll();

	}
	
	
	
	
	@Test(priority = 55)
	public void setDetailsforCourierDetails() {
		SoftAssert objSoftAssert = new SoftAssert();
        String strMessage="";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		strCurrDate=objAdjunoLIMADocumentSentPOM.getDate(0, "dd-MM-YYYY");
		wait(2000);
		if (!isNullOrBlank(strCourier)){
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_Courier", strCourier), true,"No product found");
			}
			else{
				strMessage = strMessage + strCourier + "null";	    		
			}
		
		if (!isNullOrBlank(strCourierAWB)){
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_CourierAWB", strCourierAWB), true,"No product found");
			}
			else{
				strMessage = strMessage + strCourierAWB + "null";	    		
			}
		
		if (!isNullOrBlank(strCurrDate)){
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_DateSent", strCurrDate), true,"No product found");
			}
			else{
				strMessage = strMessage + strCurrDate + "null";	    		
			}
		
		if (!isNullOrBlank(strRecepient)){
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_Recipient", strRecepient), true,"No product found");
			}
			else{
				strMessage = strMessage + strRecepient + "null";	    		
			}
		
		objSoftAssert.assertAll();

		}
	
	
	@Test(priority = 69)
    public void checkforValidationMsg() {
        SoftAssert objSoftAssert = new SoftAssert();

       objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
        objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getErrormsg()), true, "error msg is not Visible");
        
       objSoftAssert.assertAll();

       }
	
	@Test(priority = 71)
	public void Test_12_9_3_clickadddocumentbtn() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		long nRow = objAdjunoLIMADocumentSentPOM.getNoOfGridRows(strDocumentsSentFormUpdate, "Grid_DocumentDetails");

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnAddDocument()), true,"*****_12_9_3_Add Document button is not clicked*****");
		wait(3000);
		long nAddRows = objAdjunoLIMADocumentSentPOM.getNoOfGridRows(strDocumentsSentFormUpdate, "Grid_DocumentDetails");

		objSoftAssert.assertEquals(nRow < nAddRows, true,"*****_12_9_3_Add Document button has issues*****");
		
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 75)
	public void Test_12_10_clickdeleteocumentbtn() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		long nRows;
		long nRows1;
		long nRows3;
		nRows = objAdjunoLIMADocumentSentPOM.getNoOfGridRows(strDocumentsSentFormUpdate, "Grid_DocumentDetails");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnDeleteDocument()), true,"*****_12_10_Delete Document button is not clicked*****");

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnNo()), true,"*****_12_10_No button is not clicked in Delete Document confirmation pop up*****");
		nRows1 = objAdjunoLIMADocumentSentPOM.getNoOfGridRows(strDocumentsSentFormUpdate, "Grid_DocumentDetails");

		// verifying No button function
		objSoftAssert.assertEquals(nRows == nRows1, true,"No button in delete leg confirmation pop up has issues");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnDeleteDocument()), true,"*****_12_10_Delete Document button is not clicked*****");

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnYes()), true,"*****_12_10_Yes button is not clicked in Delete Document confirmation pop up*****");
		nRows3 = objAdjunoLIMADocumentSentPOM.getNoOfGridRows(strDocumentsSentFormUpdate, "Grid_DocumentDetails");

		// verifying Yes button function
		objSoftAssert.assertEquals(nRows > nRows3, true,"*****_12_10_Yes button in delete leg confirmation pop up has issues*****");

		objSoftAssert.assertAll();

	}
	
	
	
	@Test(priority = 76)
	public void Test_12_9_1_setvalueforDocumentRefrence() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		nRow = objAdjunoLIMADocumentSentPOM.getNoOrRowsinGrid(strDocumentsSentFormUpdate, "Grid_DocumentDetails");
		System.out.println("Test11 :" + nRow);

		for (int i = 0; i <= nRow - 1; i++) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_DocumentDetails", i,"Textbox_DocumentReference",hblList.get(i) ), true,"*****_12_9_1_HBL Value is not set*****");
			wait(3000);
			
		}
		objSoftAssert.assertAll();

	}
	
	
	@Test(priority = 77)
	public void Test_13_1_2_clickHyperlink() {
		SoftAssert objSoftAssert = new SoftAssert();
	
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getFileHyperlink()), true, "*****_13_1_2_Hyperlink is not Present*****");
		objAdjunoLIMADocumentSentPOM.getFileHyperlink().click();
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnAdd()), true, "*****_13_1_2_Add Button is not Present*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnDelete()), true, "*****_13_1_2_Delete Button is not Present*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnAdd()), true,"*****_13_1_2_Add button is not clicked*****");
		wait(4000);
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 79)
	public void Test_13_2_4_clickAddButton(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		wait(2000);
		//objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnBrowser()), true, "*****_13_2_4_Browser Button is not Present*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnClearAll()), true, "*****_13_2_4_Clear All Button is not Present*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnRemove()), true, "*****_13_2_4_Remove Button is not Present*****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnUpload()), true, "*****_13_2_4_Upload Button is not Present*****");
		
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 83)
	public void Test_13_2_5_6_verifyfileupload() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		wait(2000);
		objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnBrowser());
		wait(2000);
		Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\FileUpload.exe");
		
		wait(4000);
		strFileName = objAdjunoLIMADocumentSentPOM.getUploadFileName().getText();
		strSize = objAdjunoLIMADocumentSentPOM.getUploadSize().getText();
		System.out.println("file name:" + strFileName + "  size :" + strSize);
		wait(6000);
        doc3 = new DocumentSent(strFileName, strSize);
        wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnUpload()), true,"*****_13_2_5_6_Upload button is not clicked****");
		wait(5000);
		try{
			if(objAdjunoLIMADocumentSentPOM.getBtnYes().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnYes()), true,"*****_13_2_5_6_Yes button is not clicked in Upload page pop up*****");
			}
			
		}catch(Exception ex){
			
		}
		
		wait(10000);
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 90)
	public void Test_13_3_1_2_3_verifyFileNameAndSize() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		String strMessage = "";
		wait(10000);
		ArrayList<DocumentSent> docList = new ArrayList<DocumentSent>();
		for (int j = 0; j <= objAdjunoLIMADocumentSentPOM.getUploadFileName2().size() - 1; j++) {

			DocumentSent doc = new DocumentSent(objAdjunoLIMADocumentSentPOM.getUploadFileName2().get(j).getText(),objAdjunoLIMADocumentSentPOM.getUploadSize2().get(j).getText());
			docList.add(doc);

		}
		for (int i = 0; i <= docList.size() - 1; i++) {
            System.out.println("check test :" + docList.get(i).getHBL());

			if (docList.get(i).getHBL().equalsIgnoreCase(doc3.getHBL())) {
			
				objSoftAssert.assertEquals(docList.get(i).getHBL().equalsIgnoreCase(doc3.getHBL()), true,"file name does not match");
				objSoftAssert.assertEquals(docList.get(i).getStatus().equalsIgnoreCase(doc3.getStatus()), true,"file size does not match");
			} else {
				if (i == docList.size() - 1) {
				//	strMessage = strMessage + "Filename and Size";
				}
			}
		}

		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
		wait(10000);
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=92)
	public void Test_close(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		/*JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");*/
		wait(3000);
	/*	Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);*/
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getCloseaddpage()), true,"close is not available");
		objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getCloseaddpage());
		wait(2000);
		
		System.out.println("close popup window");
		objSoftAssert.assertAll();
	}

	@Test(priority = 96)
	public void Test_13_5_uploadmultiplefile() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		wait(5000);

		for (int i = 0; i <= nRow - 1; i++) {

			objAdjunoLIMADocumentSentPOM.getAttachments().get(i).click();
			wait(5000);

			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnAdd()), true, "*****_13_5_Add button is not clicked*****");
			wait(2000);
			objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnBrowser());
			wait(2000);
			Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\FileUploadMulti.exe");

			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnUpload()), true,"*****_13_5_Upload button is not clicked*****");
			wait(4000);
			if (objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnYes())) {
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnYes()), true,"*****_13_5_Yes button is not clicked in Upload page pop up*****");
			}
			wait(10000);
			System.out.println("i value:"+i);
			Test_close();
            
		}
		
		//objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getCloseaddpage()), true,"*****_13_5_close is not clicked in pop up*****");
		
        objSoftAssert.assertAll();
	}
	
	
	@Test(priority=99)
	public void Test_clickComplete(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getChvComplete1()), true,"*****_13_5_Complete chevron is not clicked*****");
		
		wait(10000);
        objSoftAssert.assertAll();
	}
	

	@Test(priority = 101)
	public void Test_14_1_2_3_checkforstatus(){ 
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," *****_14_1_2_3_Document Status is not clear***** ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");
		
		wait(2000);
		if (!isNullOrBlank(hblList.get(1))) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(hblList.get(1), strSearchDocumentsSentForm,"PARAM_HBL"), true,"*****_14_1_2_3_Not able to  set value in HBL field*****");
		} else {
			strMessage = strMessage + hblList.get(1) + "null";
		}

		if (!isNullOrBlank(strDocumentStatus)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"*****_14_1_2_3_Not able to  set value in Document Status field*****");
		} else {
			strMessage = strMessage + strDocumentStatus + "null";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "*****_14_1_2_3_Not able to click to select chevron*****");
		wait(2000);
		
		lstSearchResults = new ArrayList<DocumentSent>();
		if (objAdjunoLIMADocumentSentPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMADocumentSentPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvUpdate1()), true,"*****_14_1_2_3_Not able to click to Update chevron*****");

		} else {
			bSearchResultsProductsFound = false;
		}

		wait(2000);
		objSoftAssert.assertAll();
	}
		
	@Test(priority = 102)
	public void Test_14_1_4_setallupdatevalues() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		strCurrDate = objAdjunoLIMADocumentSentPOM.getDate(0, "dd-MM-YYYY");
        wait(2000);
		if (!isNullOrBlank(strCourier)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_Courier",strCourier), true, "*****_14_1_4_No product found*****");
		} else {
			strMessage = strMessage + strCourier + "null";
		}
        
		wait(2000);
		if (!isNullOrBlank(strCourierAWB)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_CourierAWB",strCourierAWB), true, "*****_14_1_4_No product found*****");
		} else {
			strMessage = strMessage + strCourierAWB + "null";
		}
        
		if (!isNullOrBlank(strCurrDate)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_DateSent",strCurrDate), true, "*****_14_1_4_No product found*****");
		} else {
			strMessage = strMessage + strCurrDate + "null";
		}

		if (!isNullOrBlank(strRecepient)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_Recipient",strRecepient), true, "*****_14_1_4_No product found*****");
		} else {
			strMessage = strMessage + strRecepient + "null";
		}

		wait(2000);

		objSoftAssert.assertAll();
	}
	
	@Test(priority = 105)
	public void Test_14_1_6_setvalueforDocumentRefrencefield() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		nRow = objAdjunoLIMADocumentSentPOM.getNoOrRowsinGrid(strDocumentsSentFormUpdate, "Grid_DocumentDetails");
		System.out.println("Test11 :" + nRow);

		for (int i = 0; i <= nRow - 1; i++) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_DocumentDetails", i,"Textbox_DocumentReference", hblList.get(1)), true,"*****_14_1_6_HBL Value is not set*****");
			wait(3000);
			/*if (i == nRow - 1) {
				objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_DocumentDetails", i,"Textbox_OriginalCopy", strOriginalCopy), true,"*****_14_1_6_Status is not set*****");

			}*/
		}

		objSoftAssert.assertAll();

	}
	
	@Test(priority = 108)
	public void Test_14_1_5_7_uploadmultiplefileinupdate() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
		wait(3000);
		//objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getCloseaddpage()), true,"*****_14_1_5_7_close is not clicked in pop up*****");
		for (int i = 0; i <= nRow - 1; i++) {

			objAdjunoLIMADocumentSentPOM.getAttachments().get(i).click();
			wait(5000);

			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnAdd()), true, "*****_14_1_5_7_Add button is not clicked*****");
			wait(2000);
			objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnBrowser());
			wait(2000);
			Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\FileUpload.exe");

			wait(2000);
			objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnUpload());
			wait(4000);
			/*if (objAdjunoLIMADocumentSentPOM.getBtnYes().isDisplayed()) {
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnYes()), true,"*****_14_1_5_7_Yes button is not clicked in Upload page pop up*****");
			}*/
			wait(8000);

			//objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getCloseaddpage()), true,"*****_14_1_5_7_close is not clicked in pop up*****");
			Test_close();
			wait(2000);
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getChvComplete1()), true,"*****_14_1_5_7_Complete chevron is not clicked*****");
		wait(5000);
		System.out.println("completed");
		objSoftAssert.assertAll();
	}
	

	/*@Test(priority = 111)
	public void checkforUpdatedstatus() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		objAdjunoLIMADocumentSentPOM.callMouseHover(strSearchDocumentsSentForm,objAdjunoLIMADocumentSentPOM.getLnkTools(),objAdjunoLIMADocumentSentPOM.getLnkDocumentSent());
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," Document Status is not clear ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");
		wait(2000);
		if (!isNullOrBlank(hblList.get(2))) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(hblList.get(2), strSearchDocumentsSentForm,"PARAM_HBL"), true,"Not able to  set value in HBL field");
		} else {
			strMessage = strMessage + hblList.get(2) + "null";
		}

		if (!isNullOrBlank(strDocumentStatus1)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus1, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"Not able to  set value in Document Status field");
		} else {
			strMessage = strMessage + strDocumentStatus1 + "null";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "Not able to click to select chevron");
		wait(2000);
		

		objSoftAssert.assertAll();
	}
	@Test(priority = 114)
	public void compareGridDataforupdateddata() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		
		List<DocumentSent> list = new ArrayList<DocumentSent>();

		list = objAdjunoLIMADocumentSentPOM.getStaus3();

		objAdjunoLIMADocumentSentPOM.compareshipmentgriddata3(strDocumentStatus, strCourier, list);
        
		
		objSoftAssert.assertAll();

	}	
	
	@Test(priority = 119)
	public void Test_14_2_1_2_3_checkforstatus1() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage="";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		objAdjunoLIMADocumentSentPOM.callMouseHover(strSearchDocumentsSentForm,objAdjunoLIMADocumentSentPOM.getLnkTools(),objAdjunoLIMADocumentSentPOM.getLnkDocumentSent());
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," *****_14_2_1_2_3_Document Status is not clear***** ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");
		wait(2000);
		if (!isNullOrBlank(hblList.get(2))) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(hblList.get(2), strSearchDocumentsSentForm,"PARAM_HBL"), true,"*****_14_2_1_2_3_Not able to  set value in HBL field*****");
		} else {
			strMessage = strMessage + hblList.get(2) + "null";
		}

		if (!isNullOrBlank(strDocumentStatus)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"*****_14_2_1_2_3_Not able to  set value in Document Status field*****");
		} else {
			strMessage = strMessage + strDocumentStatus + "null";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "*****_14_2_1_2_3_Not able to click to select chevron*****");
		wait(2000);
		
		lstSearchResults = new ArrayList<DocumentSent>();
		if (objAdjunoLIMADocumentSentPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMADocumentSentPOM.selectMulitpleProducts(2);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvUpdate1()), true,"*****_14_2_1_2_3_Not able to click to Update chevron*****");

		} else {
			bSearchResultsProductsFound = false;
		}

		
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 122)
	public void Test_2_4_setallupdatevaluesforPending() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		strCurrDate = objAdjunoLIMADocumentSentPOM.getDate(0, "dd-MM-YYYY");
        wait(2000);
		if (!isNullOrBlank(strCourier)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_Courier",strCourier), true, "*****_2_4_No product found*****");
		} else {
			strMessage = strMessage + strCourier + "null";
		}
        
		wait(2000);
		if (!isNullOrBlank(strCourierAWB)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_CourierAWB",strCourierAWB), true, "*****_2_4_No product found*****");
		} else {
			strMessage = strMessage + strCourierAWB + "null";
		}
        
		if (!isNullOrBlank(strCurrDate)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_DateSent",strCurrDate), true, "*****_2_4_No product found*****");
		} else {
			strMessage = strMessage + strCurrDate + "null";
		}

		if (!isNullOrBlank(strRecepient)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_CourierDetails", 0, "Textbox_Recipient",strRecepient), true, "*****_2_4_No product found*****");
		} else {
			strMessage = strMessage + strRecepient + "null";
		}

	
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 125)
	public void Test_14_2_6_setvalueforDocumentRefrence2() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		nRow = objAdjunoLIMADocumentSentPOM.getNoOrRowsinGrid(strDocumentsSentFormUpdate, "Grid_DocumentDetails");
		System.out.println("Test11 :" + nRow);

		for (int i = 0; i <= nRow - 1; i++) {
					
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_DocumentDetails", i,"Textbox_DocumentReference", hblList.get(2)), true,"*****_14_2_6_HBL Value is not set*****");
			wait(3000);
			if (i == nRow-1) {
				objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_DocumentDetails", i,"Textbox_OriginalCopy", strOriginalCopy), true,"*****_14_2_6_Status is not set*****");
				wait(1000);
				objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValueForGridCell(strDocumentsSentFormUpdate,"Grid_DocumentDetails", i,"Textbox_DocumentStatus", strDocumentStatus), true,"*****_14_2_6_Status is not set*****");
			}
			
			
		}
        objSoftAssert.assertAll();

	}
	
	@Test(priority = 130)
	public void Test_14_2_5_7_uploadmultiplefileinupdate1() throws IOException {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getCloseaddpage()), true,"*****_14_2_5_7_close is not clicked in pop up*****");
		wait(2000);

		for (int i = 0; i <= nRow - 1; i++) {

			objAdjunoLIMADocumentSentPOM.getAttachments().get(i).click();
			wait(5000);

			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnAdd()), true, "*****_14_2_5_7_Add button is not clicked*****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnBrowser()), true,"*****_14_2_5_7_Browser button is not clicked*****");
			wait(2000);
			Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\FileUpload.exe");

			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnUpload()), true,"*****_14_2_5_7_Upload button is not clicked*****");
			wait(4000);
			if (objAdjunoLIMADocumentSentPOM.isElementPresent(objAdjunoLIMADocumentSentPOM.getBtnYes())) {
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getBtnYes()), true,"*****_14_2_5_7_Yes button is not clicked in Upload page pop up*****");
			}
			wait(3000);

			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getCloseaddpage()), true,"*****_14_2_5_7_close is not clicked in pop up*****");
			wait(2000);
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentSentPOM.getChvComplete1()), true,"*****_14_2_5_7_Complete chevron is not clicked***** ");

		objSoftAssert.assertAll();
	}
	
	@Test(priority =135)
	public void Test_10_2_accessDocumentSentthroughdashboard() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		objAdjunoLIMADocumentSentPOM.getLnkHome().click();
		wait(2000);
		objAdjunoLIMADocumentSentPOM.clickdocuments(strDocumentsDashBoardUpdate,"DashboardPanel");
		objAdjunoLIMADocumentSentPOM.getClkDocumentSent().click();
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.isPageLoaded(strSearchDocumentsSentForm), true,"Not able to check Search page");
	   
        objSoftAssert.assertAll();
	}
	
	@Test(priority = 137)
	public void Test_11_3_compareGridDataforsinlethroughdash() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);

		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," Document Status is not clear ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");
		wait(2000);
		if (!isNullOrBlank(strDocumentStatus)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"Not able to  set value in Document Status field");
		} else {
			strMessage = strMessage + strDocumentStatus + "null";
		}
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "Not able to click to select chevron");

		List<DocumentSent> list = new ArrayList<DocumentSent>();

		list = objAdjunoLIMADocumentSentPOM.getStaus1();

		objAdjunoLIMADocumentSentPOM.compareshipmentgriddata1(strDocumentStatus);
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 139)
	public void Test_11_4_compareGridDataforMultithroughdash() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADocumentSentPOM = new AdjunoLIMADocumentSentPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSearch1()),true, "Not able to click select1 chevron");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true," Document Status is not clear ");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clearInputField(strSearchDocumentsSentForm,"PARAM_PONumber"), true," po Number is not clear ");
		wait(2000);

		if (!isNullOrBlank(strHBL)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strHBL, strSearchDocumentsSentForm,"PARAM_HBL"), true,"Not able to  set value in HBL field");
		} else {
			strMessage = strMessage + strHBL + "null";
		}
		if (!isNullOrBlank(strDocumentStatus)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.setFieldValue(strDocumentStatus, strSearchDocumentsSentForm,"PARAM_DocumentStatus"), true,"Not able to  set value in Document Status field");
		} else {
			strMessage = strMessage + strDocumentStatus + "null";
		}
		objSoftAssert.assertEquals(objAdjunoLIMADocumentSentPOM.clickChevron(objAdjunoLIMADocumentSentPOM.getChvSelect()),true, "Not able to click to select chevron");
		List<DocumentSent> list = new ArrayList<DocumentSent>();

		list = objAdjunoLIMADocumentSentPOM.getStaus();

		objAdjunoLIMADocumentSentPOM.compareshipmentgriddata(strHBL,strDocumentStatus,list);
        
		
		objSoftAssert.assertAll();
		}
*/
	  @AfterTest
	  public void closeBrowser(){
	     driver.close();
	  }
		
}
