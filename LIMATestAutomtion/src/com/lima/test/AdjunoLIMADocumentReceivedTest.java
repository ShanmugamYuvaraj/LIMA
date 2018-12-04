package com.lima.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.JavascriptExecutor;
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

import com.lima.dto.DocumentReceived;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMADocumentReceivedPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMADocumentReceivedTest {
	
	WebDriver driver;
    long nPage_Timeout = 0;
	String strTestURL;
	String strDriver;
	

	ArrayList<DocumentReceived> lstSearchResults;
	String strLIMAUserName;
	String strLIMAPassword;
	String strPageTitleDocumentsReceived;
	String strPageTitleUpdate;
	String strSearchDocumentsReceivedFormName;
	String strUpdateDocumentsReceivedFormName;
	String strDocumentsDashBoardUpdate;
	boolean bSearchResultsProductsFound = true;
	String strPageTitleTrackAWR;
	
	String strHBL;
	String strStatusPending;
	String strStatusReceived;
	String strStatus;
	String strCourier;
	String strCourierAWB;
	String strDateSent;
	String strRecepient;
	
	long nRow;
	
	
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary  objAdjunoLIMALibrary;
	AdjunoLIMADocumentReceivedPOM objAdjunoLIMADocumentReceivedPOM;
    
	String  strCurrDate;
	
	String strFileName;
	String strSize;
	
	DocumentReceived doc1;
	DocumentReceived doc2;
	DocumentReceived doc3;
	DocumentReceived doc4;
	
	
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

			Node PageTitleDocumentsReceived = (Node) xPath.evaluate("/config/LIMA/Page_Title_DocumentsReceived", dDoc,XPathConstants.NODE);
			strPageTitleDocumentsReceived = PageTitleDocumentsReceived.getTextContent();
			
			Node PageTitleTrackAWR = (Node) xPath.evaluate("/config/LIMA/Page_Title_TrackAWR", dDoc,XPathConstants.NODE);
			strPageTitleTrackAWR = PageTitleTrackAWR.getTextContent();

			Node SearchDocumentsReceivedFormName = (Node) xPath.evaluate("/config/LIMA/Search_Documents_Received_Form_Name",dDoc, XPathConstants.NODE);
			strSearchDocumentsReceivedFormName = SearchDocumentsReceivedFormName.getTextContent();
			
			Node UpdateDocumentsReceivedFormName = (Node) xPath.evaluate("/config/LIMA/Update_Documents_Received_Form_Name",dDoc, XPathConstants.NODE);
			strUpdateDocumentsReceivedFormName = UpdateDocumentsReceivedFormName.getTextContent();
			

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
			Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getDoucumentReceivedXMLDataFileName());

			XPath xPath1 = XPathFactory.newInstance().newXPath();
			
			Node statusReceived = (Node) xPath1.evaluate("/DocumentsReceived/SearchParams/Status_Received",dDoc1, XPathConstants.NODE);
			strStatusReceived = statusReceived.getTextContent();
			
			Node statusPending = (Node) xPath1.evaluate("/DocumentsReceived/SearchParams/Status_Pending",dDoc1, XPathConstants.NODE);
			strStatusPending = statusPending.getTextContent();
    	}
			
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
		
	private boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}
	public void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Test(priority = 1)
	public void test_16d1_AccessDocumentReceived() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);

		String strTitle = objAdjunoLIMADocumentReceivedPOM.callMouseHover(strSearchDocumentsReceivedFormName,objAdjunoLIMADocumentReceivedPOM.getLnkTools(),objAdjunoLIMADocumentReceivedPOM.getLnkDocumentReceived());

		System.out.println("Title : "+strTitle);
		boolean bFlag = true;

		if (isNullOrBlank(strTitle))
			bFlag = false;

		if (!(isNullOrBlank(strTitle))) {
			if (strTitle.equalsIgnoreCase(strPageTitleDocumentsReceived))
				bFlag = true;
			else
				bFlag = false;
		}

		objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 16.1 - Title of the page is wrong *****");
		try{
	        wait(3000);
	        objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnOK1());
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
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesChevronExist(objAdjunoLIMADocumentReceivedPOM.getChvSearch()), true, "Search Chevorn not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesChevronExist(objAdjunoLIMADocumentReceivedPOM.getChvSelect()), true, "Select Chevorn not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesChevronExist(objAdjunoLIMADocumentReceivedPOM.getChvUpdate()), true,"Confirmation Chevorn not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesChevronExist(objAdjunoLIMADocumentReceivedPOM.getChvComplete()), true, "Complete Chevorn not found");
		objSoftAssert.assertAll();
	}
	
	
	
	
	@Test(priority = 16)
	 public void verifyDocumentReceivedFields() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_Origin"), true,"Origin field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_Port"), true,"Origin Port field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_Mode"), true,"Mode field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_Vessel"), true,"Vessel field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_DocumentStatus"), true,"Document Status field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_Courier"),true, "Courier field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_HBL"),true, "HBL field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_Vendor"), true,"Vendor field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_PONumber"), true,"PO Number field is not found");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesFieldsExist(strSearchDocumentsReceivedFormName, "PARAM_CourierAWB"),true, "CourierAWB Status field is not found");
	
		objSoftAssert.assertAll();
   }


	@Test(priority = 17)
	 public void ClearDocumentReceivedFields() {

		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_Origin"), true,"Origin field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_Port"), true,"Origin Port field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_Mode"), true,"Mode field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_Vessel"), true,"Vessel field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_DocumentStatus"), true,"Document Status field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_Courier"),true, "Courier field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clearInputField(strSearchDocumentsReceivedFormName, "PARAM_HBL"),true, "HBL field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue("",strSearchDocumentsReceivedFormName, "PARAM_Vendor"), true,"Vendor field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clearInputField(strSearchDocumentsReceivedFormName, "PARAM_PONumber"), true,"PO Number field is not Cleared");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clearInputField(strSearchDocumentsReceivedFormName, "PARAM_CourierAWB"),true, "CourierAWB Status field is not Cleared");
	
		objSoftAssert.assertAll();
  }


	
	
	
	
	@Test(priority = 20)
	public void test_16d1_SetFeildDocumentReceivedfield() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";

		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		System.out.println("StatusPending:"+strStatusPending);
		if (!isNullOrBlank(strStatusPending)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue(strStatusPending, strSearchDocumentsReceivedFormName,"PARAM_DocumentStatus"), true,"***** Test ID : 16.1 - Not able to  set value in Document Status field *****");
		} else {
			//System.out.println("StatusPending null");
			strMessage = strMessage + " ***** Test ID : 16.1 - Status Pending null *****";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvSelect()),true, " ***** Test ID : 16.1 - Not able to click to select chevron *****");

		List<String> list = new ArrayList<String>();

		list = objAdjunoLIMADocumentReceivedPOM.getStaus();

		strMessage =strMessage +objAdjunoLIMADocumentReceivedPOM.compareGridData(list, strStatusPending);

		strHBL=objAdjunoLIMADocumentReceivedPOM.gethBL().getText();
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
		objSoftAssert.assertAll();
	}


	
	@Test(priority = 25)
	public void test_16_2_SetFieldMultipleValue() {
		SoftAssert objSoftAssert = new SoftAssert();
        
		String strMessage="";
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getDownArrow()),true, " ***** Test ID : 16.2 - Not able to click to Down Arrow *****");

		ClearDocumentReceivedFields();

		
		strHBL=objAdjunoLIMADocumentReceivedPOM.gethBL().getText();
		
		if (!isNullOrBlank(strHBL)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue(strHBL, strSearchDocumentsReceivedFormName,"PARAM_HBL"), true,"***** Test ID : 16.2 - Not able to  set value in HBL field *****");
		} else {
			strMessage = strMessage + strHBL + "***** Test ID : 16.2 - HBL is Empty *****";
		}
		if (!isNullOrBlank(strStatusPending)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue(strStatusPending, strSearchDocumentsReceivedFormName,"PARAM_DocumentStatus"), true,"***** Test ID : 16.2 - Not able to  set value in Document Status field *****");
		} else {
			strMessage = strMessage + strStatusPending + "***** Test ID : 16.2 - Status is Empty *****";
		}
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvSelect()),true, "***** Test ID : 16.2 - Not able to click to select chevron *****");
		
		
		List<DocumentReceived> list = new ArrayList<DocumentReceived>();

		list = objAdjunoLIMADocumentReceivedPOM.getStaus2();

		objAdjunoLIMADocumentReceivedPOM.compareGridData2(strHBL,strStatusPending,list);
        
		//objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.verifyNoelement(),true, "Element is Present");
		objSoftAssert.assertAll();

	}
	


	@Test(priority = 30)
	public void selectCheckBoxData() {
		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);

		lstSearchResults = new ArrayList<DocumentReceived>();
		if (objAdjunoLIMADocumentReceivedPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMADocumentReceivedPOM.selectMulitpleProducts(3);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);

			
		} else {
			bSearchResultsProductsFound = false;
		}

		doc1 = objAdjunoLIMADocumentReceivedPOM.updateTableValue();
		doc3 = objAdjunoLIMADocumentReceivedPOM.dataTableValue();
		objSoftAssert.assertAll();
		
		

	}
	
	
	
	@Test(priority=35)
	public void test_17d5_UpdateChevValidation(){
		SoftAssert objSoftAssert =new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		
		//Click on update chev 
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvUpdate1()), true," ***** Test ID : 17.5 - Not able to click to Update chevron *****");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.verifyPageIsLoaded(strUpdateDocumentsReceivedFormName),true,"***** Test ID : 17.5 - Update form is not loaded *****");		
		
		//Checking Grid Existence
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesElementExist(strUpdateDocumentsReceivedFormName," Grid_CourierDetails"), true," ***** Test ID : 17.5 - CourierDetails Grid not found *****");
 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesElementExist(strUpdateDocumentsReceivedFormName,"Grid_ShipmentDetails"), true," ***** Test ID : 17.5 - ShipmentDetails Grid not found *****");
 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesElementExist(strUpdateDocumentsReceivedFormName,"Grid_DocumentAttachments"), true,"***** Test ID : 17.5 - DocumentAttachments Grid not found *****");
 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkDoesElementExist(strUpdateDocumentsReceivedFormName,"Grid_DocumentDetails"), true," ***** Test ID : 17.5 - DocumentDetails Grid not found *****");
 		
 		WebElement we=objAdjunoLIMADocumentReceivedPOM.getUpdateCourier();
 		we.click();
 	   //objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkGridCellUserEditable(strUpdateDocumentsReceivedFormName,"Grid_CourierDetails",0,"Courier"),true,"Not Clickable");
 		
 		
 		//Courier DropDown Validation
 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.doesDropDownExist(objAdjunoLIMADocumentReceivedPOM.getLstUpdateCourier()),true," ***** Test ID : 17.6 - Courier is not a dropdown *****");
 		
 		//Courier AWB DropDown Validation
 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkGridCellUserEditable(strUpdateDocumentsReceivedFormName, "Grid_CourierDetails", 0, "Textbox_CourierAWB"),true," ***** Test ID : 17.6 - Grid Courier AWB is not TextBox *****");
 		
 		
 		WebElement we1=objAdjunoLIMADocumentReceivedPOM.getUpdateReceipted();
 		we1.click();
 	   	//Receiptent DropDown Validation
 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.doesDropDownExist(objAdjunoLIMADocumentReceivedPOM.getLstUpdateReceipted()),true," ***** Test ID : 17.6 - Receipted is not a dropdown *****");
 		 		
 		
 		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority=40)
	public void test_17d7_UpdateTrackAWB(){
		SoftAssert objSoftAssert =new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		
		// check and clicking on Hyper Link if Track AWB
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isHyperlinkPresent(objAdjunoLIMADocumentReceivedPOM.getTrackAWB()),true,"***** Test ID : 17.7 - Track AWB couldn't be clicked *****");
				
		//Checking for the URL
		

		 // String parentHandle = driver.getWindowHandle();
	     //   System.out.println(parentHandle);

	        // click

	        objAdjunoLIMADocumentReceivedPOM.getTrackAWB().click();
	        wait(3000);
	        Set<String> AllWindowHandles = driver.getWindowHandles();
	        String window1 = (String) AllWindowHandles.toArray()[0];
	        System.out.print("window1 handle code = "+AllWindowHandles.toArray()[0]);
	        String window2 = (String) AllWindowHandles.toArray()[1];
	        System.out.print("\nwindow2 handle code = "+AllWindowHandles.toArray()[1]);
	        driver.switchTo().window(window2);
	        
	        System.out.println("new page url is:"+driver.getCurrentUrl());
	        	    
	        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isURLMatched(),true," ***** Test ID : 17.7 - Page is not loaded *****");
	    	wait(3000);
	    			            
//	        }

	        driver.close();
	        driver.switchTo().window(window1);
		
		//objAdjunoLIMADocumentReceivedPOM.closeWindow();
		wait(2000);
	//	objAdjunoLIMADocumentReceivedPOM.switchWindow();
		
		objSoftAssert.assertAll();	
	}				

	

	/*@Test(priority=45)
	public void test_17d8_UpdateShipmentGrid(){
		SoftAssert objSoftAssert =new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		
		
		//Shipment Details 
		doc2 = objAdjunoLIMADocumentReceivedPOM.shipmentTableValue();
		doc4 = objAdjunoLIMADocumentReceivedPOM.ShipmentdataTableValue();
		
		int n2 = lstSearchResults.size();
		
		for (int j=0; j <= n2-1;j++)
        {
        
		objSoftAssert.assertEquals(doc1.getOriginPort().equalsIgnoreCase(doc2.getOriginPort()),true," ***** Test ID : 17.8 - OriginPort Didn't Match *****");
		System.out.println("Value1 :"+doc1.getOriginPort()+ " Value2 : "+doc2.getOriginPort());
		objSoftAssert.assertEquals(doc1.getDestinationPort().equalsIgnoreCase(doc2.getDestinationPort()),true," ***** Test ID : 17.8 - Destination Didn't Match *****");
		objSoftAssert.assertEquals(doc1.getMode().equalsIgnoreCase(doc2.getMode()),true," ***** Test ID : 17.8 - Mode Didn't Match *****");
		objSoftAssert.assertEquals(doc1.getVessel().equalsIgnoreCase(doc2.getVessel()),true," ***** Test ID : 17.8 - Vessel Didn't Match *****");
		objSoftAssert.assertEquals(doc1.getVoyage().equalsIgnoreCase(doc2.getVoyage()),true," ***** Test ID : 17.8 - Voyage Didn't Match *****");
		objSoftAssert.assertEquals(doc1.getCTD().equalsIgnoreCase(doc2.getCTD()),true," ***** Test ID : 17.8 - CTD Didn't Match *****");
		objSoftAssert.assertEquals(doc1.getETA().equalsIgnoreCase(doc2.getETA()),true," ***** Test ID : 17.8 - ETA Didn't Match *****");
		
        }
		
		//Document Attachments
		objSoftAssert.assertEquals(doc3.getVendor().equalsIgnoreCase(doc4.getVendor()),true," ***** Test ID : 17.8 - Vendor Didn't Match *****");
		objSoftAssert.assertEquals(doc3.getHBL().equalsIgnoreCase(doc4.getHBL()),true," ***** Test ID : 17.8 - HBL Didn't Match *****");
		
		objSoftAssert.assertAll();	
	
	}*/
	
	 public int getIndexValue(){
		 	
			objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
	    	//char index=objAdjunoLIMADocumentReceivedPOM.getLnkSDDailog().getText().charAt(0);
			
			String upToNCharacters = objAdjunoLIMADocumentReceivedPOM.getLnkSDDailog().getText().substring(0, Math.min(objAdjunoLIMADocumentReceivedPOM.getLnkSDDailog().getText().length(), 2));
	
			int index = Integer.parseInt(upToNCharacters);
			System.out.println("index :"+index);
			return index;
	    }
	 
	/*@Test(priority=47)
	public void test_17d9_DocumentAttachmentsGrid(){
		SoftAssert objSoftAssert =new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
		
		//Checking for hyper link in Document Attachments grid 
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isHyperlinkPresent(objAdjunoLIMADocumentReceivedPOM.getLnkSDHBL()),true,"***** Test ID : 17.9.7 - HBL is not Hyper link *****");
		driver.navigate().back();
		
	
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isHyperlinkPresent(objAdjunoLIMADocumentReceivedPOM.getLnkSDDailog()),true,"***** Test ID : 17.9.8 - Dialog is not Hyper link *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValueForWebElement(objAdjunoLIMADocumentReceivedPOM.getMessageBox(), "Message box is expecting the value"),true,"***** Test ID : 17.9.8 - Didn't enter the value in Message box *****");
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnAddDialog()),true," ***** Test ID : 17.9.8 - not Clicked on button add dialog ***** Test ID : 17.9.8 - ");
		wait(2000);
		
		
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
		//count of dialog box
		int indVal= getIndexValue();
		
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.getNoOrRowsInDialog(indVal),true," ***** Test ID : 17.9.9 - Dialog box count is not matching *****");
	
		objSoftAssert.assertAll();	
	}*/
	

	@Test(priority = 71)
    public void clickadddocumentbtn() {
        SoftAssert objSoftAssert = new SoftAssert();

        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        
        long nRow = objAdjunoLIMADocumentReceivedPOM.getNoOfGridRows(strUpdateDocumentsReceivedFormName, "Grid_DocumentDetails");

        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnAddDocument()), true,"Add Document button is not clicked");
       // wait(3000);
        long nAddRows = objAdjunoLIMADocumentReceivedPOM.getNoOfGridRows(strUpdateDocumentsReceivedFormName, "Grid_DocumentDetails");

        objSoftAssert.assertEquals(nRow < nAddRows, true,"Add Document button has issues");
        //wait(2000);
        objSoftAssert.assertAll();

    }


 
	@Test(priority = 75)
    	public void clickDeleteDocumentBtn() {
        SoftAssert objSoftAssert = new SoftAssert();

        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        long nRows;
        long nRows1;
        long nRows3;
        wait(2000);
        nRows = objAdjunoLIMADocumentReceivedPOM.getNoOfGridRows(strUpdateDocumentsReceivedFormName, "Grid_DocumentDetails");
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnDeleteDocument()), true,"Delete Document button is not clicked");

        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnNo()), true,"No button is not clicked in Delete Document confirmation pop up");
        nRows1 = objAdjunoLIMADocumentReceivedPOM.getNoOfGridRows(strUpdateDocumentsReceivedFormName, "Grid_DocumentDetails");

        // verifying No button function
        objSoftAssert.assertEquals(nRows == nRows1, true,"No button in delete leg confirmation pop up has issues");
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnDeleteDocument()), true,"Delete Document button is not clicked");

        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnYes()), true,"Yes button is not clicked in Delete Document confirmation pop up");
        nRows3 = objAdjunoLIMADocumentReceivedPOM.getNoOfGridRows(strUpdateDocumentsReceivedFormName, "Grid_DocumentDetails");

        // verifying Yes button function
        objSoftAssert.assertEquals(nRows > nRows3, true,"Yes button in delete leg confirmation pop up has issues");

        objSoftAssert.assertAll();

    }
	
	@Test(priority = 80)
    public void checkforValidationBox() {
        SoftAssert objSoftAssert = new SoftAssert();

        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);

	String strDocumentRefMandatoryErrMsg = objAdjunoLIMADocumentReceivedPOM.getValidationMessageGridElement(strUpdateDocumentsReceivedFormName, "Grid_DocumentDetails", 2, "Textbox_DocumentReference");
    if(!strDocumentRefMandatoryErrMsg.equals(""))
    {
        if(strDocumentRefMandatoryErrMsg.contains("&#39;"))
        {
            strDocumentRefMandatoryErrMsg = strDocumentRefMandatoryErrMsg.replace("&#39;", ",");
            objSoftAssert.assertEquals(strDocumentRefMandatoryErrMsg.equalsIgnoreCase("> 'Document Reference' is a required field" ), true, "'Document Reference is a required field' msg is not displayed");
        }
    }
    objSoftAssert.assertAll();

    }
	
	
	@Test(priority = 85)
    public void checkforValidationMsg() {
        SoftAssert objSoftAssert = new SoftAssert();

        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getErrormsg()), true, "error msg is not Visible");
        
        objSoftAssert.assertAll();

        }
	
	
	@Test(priority = 90)
    public void clickHyperlink() {
        SoftAssert objSoftAssert = new SoftAssert();
    
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);

        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getFileHyperlink()), true, "Hyperlink is not Present");
        objAdjunoLIMADocumentReceivedPOM.getFileHyperlink().click();
        wait(2000);
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnAdd()), true, "Add Button is not Present");
        
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnClear()), true, "Clear Button is not Present");
               
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnDelete()), true, "Delete Button is not Present");
        wait(2000);
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnAdd()), true,"Add button is not clicked");
          
        objSoftAssert.assertAll();

    }
    
    @Test(priority = 95)
    public void clickAddButton() throws IOException{
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        wait(2000);
        //objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnBrowser()), true, "Browser Button is not Present");
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnClearAll()), true, "Clear All Button is not Present");
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnRemove()), true, "Remove Button is not Present");
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnUpload()), true, "Upload Button is not Present");
       
        objSoftAssert.assertAll();

    }
    
    @Test(priority = 97)
    public void checkuploadfile() throws IOException{
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        wait(3000);
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnBrowser()), false,"Browser button is not clicked");
        wait(2000);
        Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\FileUpload.exe");
        
        wait(2000);
        strFileName = objAdjunoLIMADocumentReceivedPOM.getUploadFileName().getText();
        strSize = objAdjunoLIMADocumentReceivedPOM.getUploadSize().getText();
        
        System.out.println("file name:" + strFileName + "  size :" + strSize);
        wait(2000);

        doc3 = new DocumentReceived(strFileName, strSize);
      
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnUpload()), true,"Upload button is not clicked");
        wait(5000);
        
        if(objAdjunoLIMADocumentReceivedPOM.getBtnYes().isDisplayed())
        {
        	objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnYes()), true,"Yes button is not clicked in Upload page pop up");
        	wait(2000);
        }else{
           
        }
        objSoftAssert.assertAll();

    }
    
    @Test(priority = 98)
    public void verifyFileNameAndSize() {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        String strMessage = "";
        ArrayList<DocumentReceived> docList = new ArrayList<DocumentReceived>();
        for (int j = 0; j <= objAdjunoLIMADocumentReceivedPOM.getUploadFileName2().size() - 1; j++) {

        	DocumentReceived doc = new DocumentReceived(objAdjunoLIMADocumentReceivedPOM.getUploadFileName2().get(j).getText(),
                    objAdjunoLIMADocumentReceivedPOM.getUploadSize2().get(j).getText());
            docList.add(doc);

        }

        System.out.println(" docList size:" + docList.size());
        for (int i = 0; i <= docList.size() - 1; i++) {

            if (docList.get(i).getFileName().equalsIgnoreCase(doc3.getFileName())) {

                System.out.println("test list name:"+ docList.get(i).getFileName() + " doc name:"+ doc3.getFileName());
                System.out.println("test list size:" + docList.get(i).getSize()+ " doc size:" + doc3.getSize());
                objSoftAssert.assertEquals(docList.get(i).getFileName().equalsIgnoreCase(doc3.getFileName()), true,"file name does not match");
                objSoftAssert.assertEquals(docList.get(i).getSize().equalsIgnoreCase(doc3.getSize()), true,"file size does not match");
            } else {
                if (i == docList.size() - 1) {
                  //  strMessage = strMessage + "Filename and Size";
                }
            }
        }

        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
        
        objSoftAssert.assertAll();
    }
    
    
    
    
    
    
    @Test(priority=100)
	public void Test_close(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);

		/*JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-250)", "");*/
		wait(3000);
	/*	Actions action = new Actions(driver);
		action.sendKeys(Keys.ESCAPE);*/
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnCancel()), true,"close is not available");
		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnCancel()), true,"*****_13_5_close is not clicked in pop up*****");
		wait(2000);
		
		System.out.println("close popup window");
		objSoftAssert.assertAll();
	}
    
    
   @Test(priority = 105)
    public void uploadmultiplefile() throws IOException {
    	 SoftAssert objSoftAssert = new SoftAssert();
    	 objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
         JavascriptExecutor jse = (JavascriptExecutor) driver;
         jse.executeScript("window.scrollBy(0,-250)", "");
         wait(3000);
       
         for (int i = 0; i <= nRow - 1; i++) {

        	 objAdjunoLIMADocumentReceivedPOM.getAttachments().get(i).click();
             wait(5000);

             objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnAdd()), true, "Add button is not clicked");
             wait(2000);
             objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnBrowser()), true,"Browser button is not clicked");
             wait(2000);
             Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\FileUploadMulti.exe");

             wait(2000);
             objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnUpload()), true,"Upload button is not clicked");
             wait(4000);
             if (objAdjunoLIMADocumentReceivedPOM.isElementPresent(objAdjunoLIMADocumentReceivedPOM.getBtnYes())) {
             wait(2000);
             objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getBtnYes()), true,"Yes button is not clicked in Upload page pop up");
             }
             wait(8000);

            Test_close();
         }
         
//         objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getChvComplete1()), true,"Complete chevron is not clicked ");
         

         objSoftAssert.assertAll();
     }
    
   
    @Test (priority=110)
    public void attachmentDocStatus()
    {
    
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        String strMessage = "";
        //ArrayList<String> docStatus = new ArrayList<String>();
        
        for(int i=0;i<=objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus().size()-1;i++)
        {
        	wait(1000);
            objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus().get(i)), true,"DD is not clicked "); 	
            objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValueForListWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus(),strStatusReceived,i),true,"Didn't set status to received");
        
        }
        
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
        objSoftAssert.assertAll();
  
    
    }
    
    
  
    @Test (priority=115)
    public void test_19d1_CompleteChev()
    {
    
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        String strMessage = "";
       
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvComplete1()), true,"Complete chev is not clicked "); 	
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.validationMessageExist(objAdjunoLIMADocumentReceivedPOM.getUpdateValidationError()),false,"Complete page is not loaded");
        wait(5000);
  
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
        objSoftAssert.assertAll();
    }
  

 /*   @Test (priority=120)
    public void test_19d2_VarryingStatus()
    {
    
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADocumentReceivedPOM = new AdjunoLIMADocumentReceivedPOM(driver);
        String strMessage = "";
       
        ClearDocumentReceivedFields();
        
		if (!isNullOrBlank(strStatusPending)) {
			objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValue(strStatusPending, strSearchDocumentsReceivedFormName,"PARAM_DocumentStatus"), true,"***** Test ID : 19.2 - Not able to  set value in Document Status field *****");
		} else {
			//System.out.println("StatusPending null");
			strMessage = strMessage + " ***** Test ID : 19.2 - Status Pending null *****";
		}

		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvSelect()),true, " ***** Test ID : 19.2 - Not able to click to select chevron *****");

		lstSearchResults = new ArrayList<DocumentReceived>();
		if (objAdjunoLIMADocumentReceivedPOM.getNoOrRowsResulted() > 0) {
			lstSearchResults = objAdjunoLIMADocumentReceivedPOM.selectMulitpleProducts(1);

			objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
		    } else {
			bSearchResultsProductsFound = false;
		}

	//	test_17d5_UpdateChevValidation();
		
		
		//Click on update chev 
				objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvUpdate1()), true," ***** Test ID : 17.5 - Not able to click to Update chevron *****");
				objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.verifyPageIsLoaded(strUpdateDocumentsReceivedFormName),true,"***** Test ID : 17.5 - Update form is not loaded *****");		
				
				
		 		WebElement we=objAdjunoLIMADocumentReceivedPOM.getUpdateCourier();
		 		we.click();
		 	   //objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.checkGridCellUserEditable(strUpdateDocumentsReceivedFormName,"Grid_CourierDetails",0,"Courier"),true,"Not Clickable");
		 		
		 		
		 		
		 		
		 		WebElement we1=objAdjunoLIMADocumentReceivedPOM.getUpdateReceipted();
		 		we1.click();
		 	   	//Receiptent DropDown Validation
		 		objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.doesDropDownExist(objAdjunoLIMADocumentReceivedPOM.getLstUpdateReceipted()),true," ***** Test ID : 17.6 - Receipted is not a dropdown *****");
		 	

        for(int i=0;i<=objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus().size()-1;i++)
        {
        	wait(1000);
            objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus().get(i)), true,"DD is not clicked "); 	
            objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValueForListWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus(),strStatusReceived,i),true,"Didn't set status to received");
            
            if(i==objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus().size()-1){
            	
            	wait(1000);
            	objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValueForListWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus()," ",i),true,"Didn't set status to null");
                  objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickButtonUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus().get(i)), true,"DD is not clicked "); 
//                objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clearFieldUsingWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus()), true);
          
                objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.setFieldValueForListWebElement(objAdjunoLIMADocumentReceivedPOM.getAttachmentDocStatus(),strStatusPending,i),true,"Didn't set status to Pending");  
            }
        }
        
        //uploadmultiplefile();
		
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.clickChevron(objAdjunoLIMADocumentReceivedPOM.getChvComplete1()), true,"Complete chev is not clicked "); 	
        objSoftAssert.assertEquals(objAdjunoLIMADocumentReceivedPOM.validationMessageExist(objAdjunoLIMADocumentReceivedPOM.getUpdateValidationError()),false,"Complete page is not loaded");
        wait(5000);
        
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
        objSoftAssert.assertAll();
    }*/
    
    @AfterTest
    public void closeBrowser(){
    	driver.close();
    }

}