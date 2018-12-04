package com.lima.test;

import java.io.File;
import java.io.IOException;
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

import org.apache.http.ParseException;
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
import com.lima.pagefactory.AdjunoLIMAContainerRestitutionPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;


public class AdjunoLIMAContainerRestitutionTest {
	WebDriver driver;
	long nPage_Timeout = 0;
    String strTestURL;
    
    String strUserName;
    String strPassword;
    String strSearchFormNameContainerRestitution;
    String strSearchFormNameContainerRestitutionUpload;
    String strSearchFormNameContainerRestitutionTrack;
    String strPageTitleContainerRestitution;
    String strRestitutionPageFormName;
    String strAwaitingStatus = "";
    String strInvalidContainerNo = "";
	String strContainerNo = "";
	String strRestitutionPointValue = "";
	String strReasonValue = "";
	String strForwarderRef = "";
	String strResultListvalue = "";
	String strDestinationValue = "";
	String strBookingDate;
	String strLocode;
	String strCatalogFormName;
	String strCatalogPageTitle;
	String strRestitutionPoint;
	String strRestitutionValue;
	String commentvalue;
	String strCompleteFromName;
	String strTrackNO;
	String strTrackN0;
	String strUploadPageFormName;
	String strNextDay;
	String strNextDay2;
	String strRestitutionPointVal;
	String strRestitutionPointVal2;
	
	String strRestitutionReason;
	String strStatusRestituted;
	String strETAFrom;
	String strETATo;
	
	String strDestination;
	boolean searchResult;
		
	ArrayList<String> catalogDestinationList;
	ArrayList<String> catalogRestitutionPointList;
	ArrayList<String> containerList;
	String strValidContainer;
	int position;
	
	boolean bSearchResultsProductsFound = true; 
	
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAContainerRestitutionPOM objAdjunoLIMAContainerRestitutionPOM;
    
    NodeList comments;
    NodeList status;
    NodeList nlRestitutionGrid;
    NodeList nlSearchParamsContainerRestitution;
    public boolean isNullOrBlank(String s)
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
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName_Anand", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
	        
  	        Node SearchFormNameContainerRestitution = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Container_Restitution", dDoc, XPathConstants.NODE);
  	        strSearchFormNameContainerRestitution = SearchFormNameContainerRestitution.getTextContent();
          
  	        Node SearchFormNameContainerRestitutionUpload = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Container_Restitution_Upload", dDoc, XPathConstants.NODE);
	        strSearchFormNameContainerRestitutionUpload = SearchFormNameContainerRestitutionUpload.getTextContent();
         
	        Node pageTitleContainerRestitution = (Node) xPath.evaluate("/config/LIMA/Page_Title_Container_Restitution", dDoc, XPathConstants.NODE);
  	        strPageTitleContainerRestitution = pageTitleContainerRestitution.getTextContent();
          
  	        Node RestitutionFormName = (Node) xPath.evaluate("/config/LIMA/Restitution_Form_Name", dDoc, XPathConstants.NODE);
            strRestitutionPageFormName = RestitutionFormName.getTextContent();
               
            Node catalogPageTitle = (Node) xPath.evaluate("/config/LIMA/Catalog_Page_Title", dDoc, XPathConstants.NODE);
	        strCatalogPageTitle = catalogPageTitle.getTextContent();
	        
            Node catalogFormName = (Node) xPath.evaluate("/config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	        strCatalogFormName = catalogFormName.getTextContent();
	        
	        Node completeFromName = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
  	        strCompleteFromName = completeFromName.getTextContent();
	        
  	        Node uploadPageFormName = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Container_Restitution_Upload", dDoc, XPathConstants.NODE);
	        strUploadPageFormName = uploadPageFormName.getTextContent();
	        
	        
	        
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
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getContainerRestitutionXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            Node  locode = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/Catalog_Locode", dDoc1, XPathConstants.NODE);
            strLocode = locode.getTextContent();
            
            Node desti = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/Destination_port", dDoc1, XPathConstants.NODE);
            strDestination = desti.getTextContent();
            
            Node forwarderRef = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/ForwarderRef", dDoc1, XPathConstants.NODE);
            strForwarderRef = forwarderRef.getTextContent();
            
            Node statusRestituted = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/Status_Restituted", dDoc1, XPathConstants.NODE);
            strStatusRestituted = statusRestituted.getTextContent();
            
            nlRestitutionGrid = (NodeList)xPath1.evaluate("/Container_Restitution/Restitution_Grid_Caption", dDoc1, XPathConstants.NODESET);
            
            nlSearchParamsContainerRestitution = (NodeList)xPath1.evaluate("/Container_Restitution/SearchParams", dDoc1, XPathConstants.NODESET);
            
            status = (NodeList) xPath1.evaluate("/Container_Restitution/Status", dDoc1, XPathConstants.NODESET);
            
            Node  restitutionpoint = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/Catalog_RestitutionPoint", dDoc1, XPathConstants.NODE);
            strRestitutionPoint = restitutionpoint.getTextContent();
            
            Node  comments = (Node) xPath1.evaluate("/Container_Restitution/InputParams/Comment", dDoc1, XPathConstants.NODE);
            commentvalue = comments.getTextContent();
            
            Node  restitutionpointval = (Node) xPath1.evaluate("/Container_Restitution/InputParams/restitutionpointval", dDoc1, XPathConstants.NODE);
            strRestitutionPointVal = restitutionpointval.getTextContent();
            
            Node  restitutionreason = (Node) xPath1.evaluate("/Container_Restitution/InputParams/restitutionreason", dDoc1, XPathConstants.NODE);
            strRestitutionReason = restitutionreason.getTextContent();
           
            Node RestitutionPointVal2 = (Node) xPath1.evaluate("/Container_Restitution/InputParams/restitutionpointval2", dDoc1, XPathConstants.NODE);
            strRestitutionPointVal2 = RestitutionPointVal2.getTextContent();
            
            Node invalidcontainer = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/Invalid_Container", dDoc1, XPathConstants.NODE);
            strInvalidContainerNo = invalidcontainer.getTextContent();
            
            Node awaitintstatus = (Node) xPath1.evaluate("/Container_Restitution/SearchParams/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strAwaitingStatus = awaitintstatus.getTextContent();
                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
  /*  
   @Test(priority=1)
    public void test_accessCatalog()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	 String strTitle = objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAContainerRestitutionPOM.getLnkTools(), objAdjunoLIMAContainerRestitutionPOM.getLnkCatalog());    
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
        
         objSoftAssert.assertEquals(bFlag, true, "***** Title of the Catalog page is wrong *****");
         objSoftAssert.assertAll();
    }
      */
    
    /*@Test(priority=2)
    public void getCatalogData(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	String strMessage = "";
    	catalogDestinationList = new ArrayList<String>();
    	catalogRestitutionPointList = new ArrayList<String>();
    	    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getLnkLightHouse()), true,"***** Lighthouse Link is not clicked in Catalog page *****");
    	wait(3000);
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strLocode)){
			 objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strLocode, strCatalogFormName, "Param_Type"),true,"***** Test ID : 8.002 - Type Field value is not entered as Customer*****");
		 
			 objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getCatalogbtnApply()),true,"*****Test ID : 8.002 -  Apply Button is not Clicked *****");
			 wait(5000);
			 
			 int nCount = objAdjunoLIMAContainerRestitutionPOM.valCount();
			 catalogDestinationList = objAdjunoLIMAContainerRestitutionPOM.getCatalogTableData(nCount);
			 System.out.println("catalogDestinationList size"+catalogDestinationList.size());
			 wait(5000);
    	
    	}else{
			 strMessage=strMessage+"Locode Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strRestitutionPoint)){
    		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strRestitutionPoint, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.006 - Type Field value is not entered as Restitution Point*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getCatalogbtnApply()),true,"***** Test ID : 8.006 - Apply button is not clicked *****");
			 wait(5000);
			             
			 int nCount2 = objAdjunoLIMAContainerRestitutionPOM.valCount();
			 catalogRestitutionPointList = objAdjunoLIMAContainerRestitutionPOM.getCatalogTableData1(nCount2);  
			 System.out.println("restitution size"+catalogRestitutionPointList.size());
    	}else{
			 strMessage=strMessage+"Restitution Point Value is Empty in Xml";
		}
    	   	
    	objSoftAssert.assertAll();
    }*/
    
    @Test(priority = 5)
    public void test_1_AccessContainerRestitution()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strTitle = objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strSearchFormNameContainerRestitution, objAdjunoLIMAContainerRestitutionPOM.getLnkTools(), objAdjunoLIMAContainerRestitutionPOM.getLnkContainerRestitution());
    	
    	boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleContainerRestitution))
                bFlag = true;
            else
                bFlag = false;
        }
        	       
        objSoftAssert.assertEquals(bFlag, true, "*****test ID:_1_Page Title is wrong in Container Restitution Search Chevron*****");
       
    	objSoftAssert.assertAll();
    }
    
   @Test(priority = 8)
	public void test_2_verifyChevrons()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true, "*****test ID:_2_Select chevron is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getChevSearch()), true, "*****test ID:_2_Search chevron is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getChevRestitution()), true, "*****test ID:_2_Restitution chevron is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getChevComplete()), true, "*****test ID:_2_Complete chevron is not displayed in Container Restitution Search Chevron*****");
    	
    	objSoftAssert.assertAll();
	}
    
    @Test(priority = 10)
	public void test_3_verifySearchFields()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_Destination"), true, "*****test ID:_3_Destination field is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_DeliveryDateFrom"), true, "*****test ID:_3_Delivery Booking From field is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_DeliveryDateTo"), true, "*****test ID:_3_To From field is not displayed in Container Restitution Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_Status"), true, "*****test ID:_3_Status To field is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_WFStatus"), true, "*****test ID:_3_Work Flow Status field is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_Haulier"), true, "*****test ID:_3_Haulier field is not displayed in Container Restitution Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_Container"), true, "*****test ID:_3_Container field is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_ForwarderReference"), true, "*****test ID:_3_Forwarder Ref field is not displayed in Container Restitution Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "PARAM_RestitutionPoint"), true, "*****test ID:_3_Restitution Point field is not displayed in Container Restitution Search Chevron*****");
    	
    	objSoftAssert.assertAll();
	}
    
    @Test(priority=15)
    public void test_4_VerifyStatusDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		ArrayList<String> statusList = new ArrayList<String>();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		statusList = objAdjunoLIMAContainerRestitutionPOM.getDropDownList(strSearchFormNameContainerRestitution, "PARAM_Status");
		
		if(statusList.size()>0){
			strMessage= strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyStatusDropDown(status,statusList,3);
				
		}else{
			strMessage=strMessage+"status Drop Down Won't have any Value";
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 4"+ strMessage +"*****");
		objSoftAssert.assertAll();

	}
      
   /* @Test(priority=16)
    public void test_5_VerifyDestinationDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> destinationList = new ArrayList<String>();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		destinationList = objAdjunoLIMAContainerRestitutionPOM.getDropDownList(strSearchFormNameContainerRestitution, "PARAM_Destination");
		
		if(destinationList.size()>0 && catalogDestinationList.size()>0){
			strMessage= strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyCatalogData(catalogDestinationList, destinationList);
		}else{
			strMessage=strMessage+"Destination Drop Down Won't have any Value";
		}
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 5"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
  @Test(priority=17)
    public void test_6_VerifyRestitutionPointDropDownValues()
    {
    	
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> destinationList = new ArrayList<String>();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		destinationList = objAdjunoLIMAContainerRestitutionPOM.getDropDownList(strSearchFormNameContainerRestitution, "PARAM_RestitutionPoint");
		
		if(destinationList.size()>0 && catalogRestitutionPointList.size()>0){
			strMessage= strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyCatalogData(catalogRestitutionPointList, destinationList);
		}else{
			strMessage=strMessage+"Restitution Point Drop Down don't have any Value";
		}
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
  */
  @Test(priority=18)
  public void getValidContainerList()
  {
  	SoftAssert objSoftAssert = new SoftAssert();
  	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
      String strMessage = "";
  	clearFields();
  	containerList =  new ArrayList<String>();	
  	if(!isNullOrBlank(strAwaitingStatus)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strAwaitingStatus, strSearchFormNameContainerRestitution, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
		}
  	
  	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID : 6 - can't click on Select chev in Search page of Container Restitution tool *****");

  	wait(3000);
  	
  	searchResult = objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr());
		
  	if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMAContainerRestitutionPOM.getLstContainer().size()-1; i++) {
				
				containerList.add(objAdjunoLIMAContainerRestitutionPOM.getLstContainer().get(i).getText());
			}
				
			System.out.println("container size:"+containerList.size());    
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6-"+ strMessage +"*****");
		
      objSoftAssert.assertAll();
      
  }
  

    @Test(priority = 20)
	public void test_7_setAwaitingAndInvalidContainerNo()
	{	
    	
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage = "";
    	clearFields();
    	System.out.println("Status:"+ strAwaitingStatus);
    	if(!isNullOrBlank(strAwaitingStatus))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strAwaitingStatus, strSearchFormNameContainerRestitution, "PARAM_Status"), true, "*****test ID:_7_Status is not set as \"Awaiting\" in Container Restitution Search Chevron*****");
    		
    	}
    	else
    	{
    		strMessage = strMessage + "Status is empty in XML";
    	}
    	if(!isNullOrBlank(strInvalidContainerNo))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strInvalidContainerNo, strSearchFormNameContainerRestitution, "PARAM_Container"), true, "*****test ID:_7_Invalid container number is not set in Conatiner field in Container Restitution Search Chevron*****");
    	}
    	else
    	{
    		strMessage = strMessage + "Container number is empty in XML";
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true, "*****test ID:_7_Select chevron is not clicked in Container Restitution Search Chevron*****");
    	    	
    	String strErrMessage = objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr().getText();
    	
    	objSoftAssert.assertEquals(strErrMessage.equalsIgnoreCase("No items were found."), true, "*****test ID:_7_Error message is not displayed as \"No items were found.\" when Invalid Container Number is set in Container Number field under Container Restitution Search Chevron*****");
    	objSoftAssert.assertAll();
    	   	
	}
    @Test(priority = 25)
	public void test_8_setAwaitingAndValidContainerNo()
	{
    	clearFields();
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
		
		clearFields();
		wait(3000);
		if(!isNullOrBlank(strAwaitingStatus)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strAwaitingStatus, strSearchFormNameContainerRestitution, "PARAM_Status"),true,"***** Test id:8- Could not set status value *****");
	 	
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
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strValidContainer, strSearchFormNameContainerRestitution, "PARAM_Container"),true,"*****Test id:8- Could not set valid container value *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty in List";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true,"*****Test id:8- Select Chevron is not clicked in Container Restitution  tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr());
	
		if(searchResult==false){
		
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
	
		
		strMessage = strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyContainerAndStaus(strValidContainer,strAwaitingStatus);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:8- "+ strMessage +"*****");
		objSoftAssert.assertAll();	
	}
    
   @Test(priority = 30)
	public void test_9_verifySelectFields()
	{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getThDestination()), true, "*****test ID:_9_\"Destination Column\" is not displayed in resultant grid under Container Restitution Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getThDeliveryDate()), true, "*****test ID:_9_\"Delivery Column\" is not displayed in resultant grid under Container Restitution Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getThContainer()), true, "*****test ID:_9_\"Container Column\" is not displayed in resultant grid under Container Restitution Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getThForwarderReference()), true, "*****test ID:_9_\"ForwarderReference Column\" is not displayed in resultant grid under Container Restitution Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getThStatus()), true, "*****test ID:_9_\"Status Column\" is not displayed in resultant grid under Container Restitution Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "FORK_ContainerRestitution_RefineSearch"), true, "*****test ID:9_\"Refine Search button\" is not displayed in resultant grid under Conatainer Rstitution Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "UploadContainerRestitutionTemplate"), true, "*****test ID:9_\"Upload Button\" is not displayed in resultant grid under Conatainer Rstitution Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strSearchFormNameContainerRestitution, "OpenReport"), true, "*****test ID:9_\"Download Container Restitution Template link\" is not displayed in resultant grid under Conatainer Rstitution Select page*****");
    	
    	objSoftAssert.assertAll();
	
	}
    
    @Test(priority = 35)
	public void test_10_verifyErrMsgAtRestitutionTab()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevronRestitution()), true, "*****_10_\"Restitution Chevron\" is not clicked in Container Restitution Select page*****");
    	String strErrMsg = objAdjunoLIMAContainerRestitutionPOM.getTxtProgressingErrMsg().getText();
    	objSoftAssert.assertEquals(strErrMsg.equalsIgnoreCase("You must make selection(s) before progressing"), true, "*****test ID:_10_In Container Restitution Select page \"You must make selection(s) before progressing\" is not displayed when no records are Selected and clicked on Restitution Chevron*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority =40)
	public void test_11_verifyDestinationdropdown()
	{
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strDestination))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strDestination,strSearchFormNameContainerRestitution,"PARAM_Destination"),true," *****  Test ID : 11 - Destination is not set Value in Select page of Container Restitution ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 11 - Destination Port is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID : 11 - can't click on Select chev in Search page of Container Restitution tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Container Restitution tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAContainerRestitutionPOM.getDestionationPortM();
	    strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyDestinationPort(strDestination,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:11- " + strMessage +" ***** ");
	    objSoftAssert.assertAll(); 
        
	}
	@Test(priority = 41)
	public void test_12_verifyDeliveryBookinFrom() throws java.text.ParseException
	{
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage ="";
    	
    	strETAFrom = objAdjunoLIMAContainerRestitutionPOM.getDate(-60,"dd MMM yyyy");
	    Date etaFromDate = new SimpleDateFormat("dd MMM yyyy").parse(strETAFrom);
	    System.out.println("strETAFrom:"+strETAFrom);
	    System.out.println("etaFromDate:"+etaFromDate);
        clearFields();
		
    	//Setting the field value Invalid Container:setFieldValue
	    if(!isNullOrBlank(strETAFrom))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strETAFrom,strSearchFormNameContainerRestitution,"PARAM_DeliveryDateFrom"),true," *****  Test ID : 12 -  Delivery Booking from date in Select page of Container Restitution tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 12 - Delivery Booking From Value is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID : 12 - can't click on Select chev in Search page of Container Restitution tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Container Restitution tool ***** ");
	    wait(5000);
	     
	    List<Date> list = new ArrayList<Date>();  
	    list = objAdjunoLIMAContainerRestitutionPOM.getArrivedDate();
	    System.out.println("list:"+list);
	    strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyArrivedDate(etaFromDate,list);
	    System.out.println("strMessage:"+strMessage);
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:12- " + strMessage +" ***** ");
	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 42)
	public void test_13_verifyDeliveryBookingTo() throws java.text.ParseException
	{
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage ="";
    	
    	strETATo= objAdjunoLIMAContainerRestitutionPOM.getDate(0,"dd MMM yyyy");
	    Date etaToDate = new SimpleDateFormat("dd MMM yyyy").parse(strETATo);
	       
        clearFields();
		
    	//Setting the field value Invalid Container:setFieldValue
	    if(!isNullOrBlank(strETATo))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strETATo,strSearchFormNameContainerRestitution,"PARAM_DeliveryDateTo"),true," *****  Test ID : 10 -  Delivery Booking To date in Select page of Container Restitution tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 13 - Delivery Booking To Value is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID :13 - can't click on Select chev in Search page of Container Restitution tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Container Restitution tool ***** ");
	    wait(5000);
	     
	    List<Date> list = new ArrayList<Date>();  
	    list = objAdjunoLIMAContainerRestitutionPOM.getArrivedDate();
	    strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyArrivedDate2(etaToDate,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:13- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
	}
    @Test(priority = 46)
    public void test_14_VerifyStatusdropdown()
    
	{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strAwaitingStatus))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strAwaitingStatus,strSearchFormNameContainerRestitution,"PARAM_Status"),true," *****  Test ID : 14 - Status is not set Value in Select page of Container Restitution tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 14 - Status is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID : 14 - can't click on Select chev in Search page of Container Restitution tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Container Restitution tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAContainerRestitutionPOM.getStatusAwait();
	    strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyStatusAwaiting(strAwaitingStatus,list);
    	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:14- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    	
	}
    
//   /* @Test(priority = 50)
//    public void test_15_VerifyHaulierdropdown()
//    
//	{
//    	SoftAssert objSoftAssert = new SoftAssert();
//		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
//		clearFields();
//		
//		ArrayList<String> destinationList = new ArrayList<String>();
//		ArrayList<String> DestinationResultList = new ArrayList<String>();
//		destinationList = objAdjunoLIMAContainerRestitutionPOM.getDropdownValues(strSearchFormNameContainerRestitution, "PARAM_Haulier");
//		strDestinationValue = destinationList.get(0);
//		System.out.println("strDestinationValue:"+strDestinationValue);
//		
//		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strDestinationValue, strSearchFormNameContainerRestitution, "PARAM_Haulier"), true, "*****test ID:_15_Haulier dropdown value is not set in Haulier field under Container Restitution Select Chevron*****");
//		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true, "*****test ID:_15_Select chevron is not clicked in Container Restitution Search Chevron*****");
//		DestinationResultList = objAdjunoLIMAContainerRestitutionPOM.getHaulierValues();
//		System.out.println("DestinationResultList:"+DestinationResultList);
//		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.verifyResults(strDestinationValue, DestinationResultList), true, "*****test ID:_15_Selected Haulier value does not match with the results in Container Restitution Search Chevron*****");
//	
//		objSoftAssert.assertAll();
//	}
//	*/
    @Test(priority = 55)
	public void test_16_verifycontainerfield()
	{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();
        System.out.println("contianer no: "+strValidContainer);
    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strValidContainer))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strValidContainer,strSearchFormNameContainerRestitution,"PARAM_Container"),true," *****  Test ID : 16 - Container Value is not set in Select page of Container Restitution tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 16 - Container is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID : 16 - can't click on Select chev in Search page of Container Restitution tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Container Restitution tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAContainerRestitutionPOM.getContainerValues();
	    System.out.println("list size: " + list.size());
	    strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyContainervalue(strValidContainer,list);
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:16- " + strMessage +" ***** ");
    	
    	objSoftAssert.assertAll();
    	
	}
    
    @Test(priority = 60)
   	public void test_17_verifyforwarderreffield()
   	{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strForwarderRef))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strForwarderRef,strSearchFormNameContainerRestitution,"PARAM_ForwarderReference"),true," *****  Test ID : 17 - ForwarderReference Value is not set in Select page of Container Restitution tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 16 - ForwarderRef is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()),true,"***** Test ID : 17 - can't click on Select chev in Search page of Container Restitution tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Container Restitution tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMAContainerRestitutionPOM.getForwardRefValues();
	    strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyForwardRefvalue(strForwarderRef,list);
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:17- " + strMessage +" ***** ");
    	
    	objSoftAssert.assertAll();
       
   	}
  
   @Test(priority=65)
    public void test_18_downloadContainerRestitutiontemplate() throws IOException{
 	   SoftAssert objSoftAssert = new SoftAssert();
 	  objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
 	   
 	   File downloadedFile = new File("C:\\Users\\Anand\\Downloads\\ContainerRestitutionTemplate.xlsx");
 	   if(downloadedFile.exists()){
 		   downloadedFile.delete();
 		   System.out.println("deleted");
 	   }
 		
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strSearchFormNameContainerRestitution, "OpenReport"), true,"***** Test ID:21- Download Container Restitution Template Link is not cliked in select chevron in Container Restitution Tool *****");
 	   wait(5000);
 		
 	   Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\DownloadContainerRestitutionTemplate.exe");
 	   wait(6000);
 		
 	   boolean exists = downloadedFile.exists();
 	   objSoftAssert.assertEquals(exists, true,"***** Test ID:21- Container Restitution Template File is not downloaded *****");
 		
 	   objSoftAssert.assertAll();
    }
   
    @Test(priority = 70)
   	public void test_19_verifyUpdateChevronFields()
   	{
   		SoftAssert objSoftAssert = new SoftAssert();
       	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strSearchFormNameContainerRestitution, "UploadContainerRestitutionTemplate"),true,"");
 	   wait(4000);

    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.verifyPageIsLoaded(strSearchFormNameContainerRestitutionUpload), true, "*****test ID:_10_Update page is not loaded in Container Restitution*****");
    	    	       	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strUploadPageFormName, "Field_CollectionDate"), true, "*****test ID:_3_Collection Date field is not displayed in Container Restitution Restitution Chevron*****");
       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strUploadPageFormName, "Field_RestitutionDate"), true, "*****test ID:_3_Restitution Date field is not displayed in Container Restitution Restitution Chevron*****");
       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strUploadPageFormName, "Field_RestitutionPoint"), true, "*****test ID:_3_Restituion Point field is not displayed in Container Restitution Restitution Chevron*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strUploadPageFormName, "Field_ReasonCode"), true, "*****test ID:_3_Reason Code To field is not displayed in Container Restitution Restitution Chevron*****");
       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strUploadPageFormName, "Field_Comments"), true, "*****test ID:_3_Comments field is not displayed in Container Restitution Search Restitution*****");
       	
       	boolean bFlag;
        String strMessage="";
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strUploadPageFormName, "Grid_Containers"), true, "*****test ID:_11_Restitution grid do not exist in Container Restitution \"Restitution page\"*****");
        
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMAContainerRestitutionPOM.getCaptionsList(strUploadPageFormName,"Grid_Containers");
                
        strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyCaptionsONGrid(list,nlRestitutionGrid,10);
        
        if(strMessage.equals(""))    {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true," *****test ID:_22_Restitution grid Caption: "+strMessage+"*****");
    	
    
       	objSoftAssert.assertAll();
   	}
    
    @Test(priority=75)
    public void test_20_uploadContainerRestitutionTemplate() throws IOException{
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   String strMessage = "";
 	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
 	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strUploadPageFormName, "ContainerRestitutionUpload"), true,"***** Test ID:23- Browse button is not clicked in \"Update Chevorn\" in Container Restitution Tool *****");
 	   wait(3000);
 	   long nRows = objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strUploadPageFormName,"Grid_Containers");
 	   
 	   Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\UploadContainerRestitution.exe");
 	   wait(5000);
 	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getBtnUpload()), true,"***** Test ID:23- upload button is not clicked in \"Update Chevorn\" in Container Restitution Tool *****");
 	   wait(4000);
 	   
 	    long nRow2 = objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strUploadPageFormName,"Grid_Containers");
	   
	   if(nRow2>nRows){
		   
	   }else{
		   strMessage = strMessage + " Upload Container Restitution function in working";
	   }
 	  
 	   objSoftAssert.assertAll();
    }

    
    @Test(priority = 80,dependsOnMethods="test_20_uploadContainerRestitutionTemplate")
    public void test_21_VerifyUpdateChevronMandatoryFields(){
  	SoftAssert objSoftAssert = new SoftAssert();
  	String strMessage = "";
  	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
	  	   
	    String strErrorRestitutionDate = objAdjunoLIMAContainerRestitutionPOM.getValidationMessageGridElement(strUploadPageFormName, "Grid_Containers", 0, "RestitutionDate");
	   	if(!strErrorRestitutionDate.equals("")){
	   		if(strErrorRestitutionDate.contains("&#39;")){
	   		strErrorRestitutionDate = strErrorRestitutionDate.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorRestitutionDate.equalsIgnoreCase("> 'Restitution Date' is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:23 - In Container Restitution Tool Under Restitution Chevron \"Restitution Date \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage +"In Container Restitution Tool Under Restitution Chevron \"Restitution Date \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:23 -"+strMessage+" *****");
	    
	   String strErrorRestitutionPoint = objAdjunoLIMAContainerRestitutionPOM.getValidationMessageGridElement(strUploadPageFormName, "Grid_Containers", 0, "RestitutionPoint");
	  	
	   if(!strErrorRestitutionPoint.equals("")){
	   		if(strErrorRestitutionPoint.contains("&#39;")){
	   			strErrorRestitutionPoint = strErrorRestitutionPoint.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorRestitutionPoint.equalsIgnoreCase("> 'Restitution Point' is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:23 - In Container Restitution Tool Under Restitution Chevron \"Restitution Point \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage +"In Container Restitution Tool Under Restitution Chevron \"Restitution Point \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:23 -"+strMessage+" *****");
	   
	   	String strErrorCollectionDate = objAdjunoLIMAContainerRestitutionPOM.getValidationMessageGridElement(strUploadPageFormName, "Grid_Containers", 0, "CollectionDate");
	   	System.out.println("Error Colllection Date:" +strErrorCollectionDate);
	   	if(!strErrorCollectionDate.equals("")){
	   		if(strErrorCollectionDate.contains("&#39;")){
	   			strErrorCollectionDate = strErrorCollectionDate.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorCollectionDate.equalsIgnoreCase("> Collection Date must be equal to or greater than the Arrival Date");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:23 - In Container Restitution Tool Under Restitution Chevron \"Collection Date \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage +"In Container Restitution Tool Under Restitution Chevron \"Collection Date \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:23 -"+strMessage+" *****");
	   	objSoftAssert.assertAll();
  
    }
    @Test(priority=90)
    public void test_22_VerifyGridValue() throws ParseException, java.text.ParseException{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
   	   String strMessage="";
   	   
   	   int nRows = (int) objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strUploadPageFormName, "Grid_Containers");
   	   
   	//   strArrivalDate = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName, "Grid_Containers", 0, "ArrivedDate");
   	   
   	   strNextDay = objAdjunoLIMAContainerRestitutionPOM.getDate(0, "dd MMM yyyy");
   	   
   	   strNextDay2 = objAdjunoLIMAContainerRestitutionPOM.getDate(1, "dd MMM yyyy");
   	   
   	   if(!isNullOrBlank(strNextDay)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay, strUploadPageFormName, "Field_CollectionDate"),true,"***** *****");
   			
   	   }else{
   	   		strMessage=strMessage+" Collection Value is Empty";
   	   }
   	   
   	  if(!isNullOrBlank(strNextDay2)){
  			
  			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay2, strUploadPageFormName, "Field_RestitutionDate"),true,"***** *****");
  	   }else{
  	   		strMessage=strMessage+" Restitution Value is Empty";
  	   }
   	   
   	   if(!isNullOrBlank(strRestitutionPointVal)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strRestitutionPointVal, strUploadPageFormName, "Field_RestitutionPoint"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Destination Value is Empty";
   	   }
   	   
   	   if(!isNullOrBlank(strRestitutionReason)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strRestitutionReason, strUploadPageFormName, "Field_ReasonCode"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Reason Value is Empty  in XML";
   	   }
   	   
   	   if(!isNullOrBlank(commentvalue)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(commentvalue, strUploadPageFormName, "Field_Comments"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Comments Value is Empty in XML";
   	   }
   	   
   	   wait(4000);
   	  
   	  for (int i = 0; i <= nRows-1; i++) {
   		  	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getGridCellElement(strUploadPageFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:29- Select CheckBox is not clicked in the Restitution Chevron *****");
   		  	
   	  }
   	  wait(2000);
   	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strUploadPageFormName, "BUTTON_Update"),true,"***** Test ID:29- Update buttoin not clicked in Restitution  Chevron *****");
   	  wait(3000);
   	  
   	  //verify entered data
   	  for (int i = 0; i <= nRows-1; i++) {
  	
   		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strUploadPageFormName,"Grid_Containers",i,"CollectionDate")), true,"***** Test ID:29-  Collection Date is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strUploadPageFormName,"Grid_Containers",i,"RestitutionDate")), true,"***** Test ID:29-  Restitution Date is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strRestitutionPointVal.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strUploadPageFormName,"Grid_Containers",i,"RestitutionPoint")), true,"***** Test ID:29-  Restitution Point is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strRestitutionReason.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strUploadPageFormName,"Grid_Containers",i,"ReasonCode")), true,"***** Test ID:29- Reason is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(commentvalue.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strUploadPageFormName,"Grid_Containers",i,"Comments")), true,"***** Test ID:29- Comments is not matching With Entered value *****");
   	 	 
   	  }
   	  
   	  //strTrackN0 = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",0,"TrackReference");
   	  
   	  wait(4000);
   	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 29"+ strMessage +"*****");
   	  objSoftAssert.assertAll();
   	  }
     
    
    @Test(priority=98)
    public void test_23_deleteRow(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	  objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
 	   String strMessage="";
 	   long nRow1 = objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strUploadPageFormName,"Grid_Containers");
 	   if(nRow1>0){
 	   		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getBtnDelete()), true,"***** Test ID;23- Delete Rows Button is not cliked in Update Chevron *****");
 	   		wait(5000);
 	   		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getBtnYes()), true,"***** Test ID;23- Yes Button is not cliked in Update Chevron *****");
 	   		wait(2000);
 	   }else{
 	   
 	   		strMessage = strMessage + " No rows are uploaded to deleted";
 	   }		
 	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 26"+ strMessage +"*****");
 	   objSoftAssert.assertAll();
 	  
    }
   
    @Test(priority=99,dependsOnMethods="test_23_deleteRow")
    public void test_upload() throws IOException, ParseException, java.text.ParseException {
 	   SoftAssert objSoftAssert = new SoftAssert();
 	  test_20_uploadContainerRestitutionTemplate();
 	   wait(4000);
 	  test_22_VerifyGridValue();
 	   wait(4000);
 	   objSoftAssert.assertAll();
    }
    
    
    @Test(priority=101)
    public void test_26_complete(){
	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevronComplete()), true,"***** Test ID:34- Complete Chevron is not clicked  *****");
 	   wait(3000);
 	   /*String poDetails = objAdjunoLIMAContainerRestitutionPOM.getTrackMessage().getText();
 	  String[] a= poDetails.split("achieved on track '", 2);
	   System.out.println(a[1]);
	   a = a[1].split("'");
	   strTrackN0 = a[0];
	   System.out.println(strTrackN0);*/
	   wait(5000);
	  
 	   objSoftAssert.assertAll();
    }
    
    @Test(priority=105)
    public void test_28_ContainerRestitutionLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getTracklnkContainerRestitution()), true,"***** Test ID:38- Container Restitution Location link is not clicked *****");
		wait(4000);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isPageLoaded(strSearchFormNameContainerRestitution),true,"***** Test ID:38- Container Restitution Location Page is not loaded *****");
		
		        
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:38-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
    	
    }

  
    @Test(priority=111)
    public void test_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		//containerList.remove(position);
 
 		String strMessage ="";
 	
 		wait(4000);
 		clearFields();
 		
 		if(!isNullOrBlank(strAwaitingStatus)){
 			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strAwaitingStatus, strSearchFormNameContainerRestitution, "PARAM_Status"),true,"***** *****");
 	 	}else{
 	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
 		}
 		
 		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true,"*****  Select Chevron is not clicked in Container Restitution Tool *****");
 		wait(4000);
 		 
 		searchResult = objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr());
		
		if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMAContainerRestitutionPOM.getLstContainers().size()-1; i++) {
				
				if(!objAdjunoLIMAContainerRestitutionPOM.getLstContainers().get(i).getText().isEmpty()){
					objAdjunoLIMAContainerRestitutionPOM.getLstSelect().get(i).click();
					strContainerNo = objAdjunoLIMAContainerRestitutionPOM.getLstContainers().get(i).getText();
					break;
				}
			}
				
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickChevron(objAdjunoLIMAContainerRestitutionPOM.getChevronRestitution()), true, "*****__\"Restitution Chevron\" is not clicked in Container Restitution Select page*****");
 		wait(4000);
 		
 		 if(objAdjunoLIMAContainerRestitutionPOM.isPageLoaded(strRestitutionPageFormName)){
 			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_CollectionDate"), true, "*****test ID:_3_Collection Date field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_RestitutionDate"), true, "*****test ID:_3_Restitution Date field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_RestitutionPoint"), true, "*****test ID:_3_Restituion Point field is not displayed in Container Restitution Restitution Chevron*****");
 	       	
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_ReasonCode"), true, "*****test ID:_3_Reason Code To field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_Comments"), true, "*****test ID:_3_Comments field is not displayed in Container Restitution Search Restitution*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "BUTTON_Update"), true,"***** Test ID:3- Update Button is not found in \"Container Restitution Chevorn\" in Container Restitution Tool *****");
 			   
 			   
 		   }else{
 			 strMessage = strMessage +" Restitution Chevron Page is not loaded";  
 		   }
 		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test "+ strMessage +" *****");
		objSoftAssert.assertAll();
        }
       
    @Test(priority=115)
    public void test_30_verifyField(){
    	SoftAssert objSoftAssert = new SoftAssert();
   	  	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	boolean bFlag;
        String strMessage="";
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Grid_Containers"), true, "*****test ID:_30_Restitution grid do not exist in Container Restitution \"Restitution page\"*****");
        
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMAContainerRestitutionPOM.getCaptionsList(strRestitutionPageFormName,"Grid_Containers");
                
        strMessage = objAdjunoLIMAContainerRestitutionPOM.verifyCaptionsONGrid(list,nlRestitutionGrid,10);
        
        if(strMessage.equals(""))    {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
       objSoftAssert.assertEquals(bFlag, true," *****test ID:_30_Restitution grid Caption: "+strMessage+"*****");   
 	   objSoftAssert.assertAll();
    }
    
    @Test(priority = 121)
    public void test_31_VerifyRestitutionChevronMandatoryFields(){
  	SoftAssert objSoftAssert = new SoftAssert();
  	String strMessage = "";
  	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
	  	   
	    String strErrorRestitutionDate = objAdjunoLIMAContainerRestitutionPOM.getValidationMessageGridElement(strRestitutionPageFormName, "Grid_Containers", 0, "RestitutionDate");
	   	if(!strErrorRestitutionDate.equals("")){
	   		if(strErrorRestitutionDate.contains("&#39;")){
	   		strErrorRestitutionDate = strErrorRestitutionDate.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorRestitutionDate.equalsIgnoreCase("> 'Restitution Date' is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:23 - In Container Restitution Tool Under Restitution Chevron \"Restitution Date \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage +"In Container Restitution Tool Under Restitution Chevron \"Restitution Date \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:31 -"+strMessage+" *****");
	    
	   String strErrorRestitutionPoint = objAdjunoLIMAContainerRestitutionPOM.getValidationMessageGridElement(strRestitutionPageFormName, "Grid_Containers", 0, "RestitutionPoint");
	  	
	   if(!strErrorRestitutionPoint.equals("")){
	   		if(strErrorRestitutionPoint.contains("&#39;")){
	   			strErrorRestitutionPoint = strErrorRestitutionPoint.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorRestitutionPoint.equalsIgnoreCase("> 'Restitution Point' is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:31 - In Container Restitution Tool Under Restitution Chevron \"Restitution Point \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage +"In Container Restitution Tool Under Restitution Chevron \"Restitution Point \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:31 -"+strMessage+" *****");
	   
	   	String strErrorCollectionDate = objAdjunoLIMAContainerRestitutionPOM.getValidationMessageGridElement(strRestitutionPageFormName, "Grid_Containers", 0, "CollectionDate");
	   	System.out.println("Error Colllection Date:" +strErrorCollectionDate);
	   	if(!strErrorCollectionDate.equals("")){
	   		if(strErrorCollectionDate.contains("&#39;")){
	   			strErrorCollectionDate = strErrorCollectionDate.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorCollectionDate.equalsIgnoreCase("> Collection Date must be equal to or greater than the Arrival Date");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:31 - In Container Restitution Tool Under Restitution Chevron \"Collection Date \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage +"In Container Restitution Tool Under Restitution Chevron \"Collection Date \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:31 -"+strMessage+" *****");
	   	objSoftAssert.assertAll();
  
    }
 	 		      
    @Test(priority=125)
    public void test_32_VerifyGridValue() throws ParseException, java.text.ParseException{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
   	   String strMessage="";
   	   
   	   int nRows = (int) objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strRestitutionPageFormName, "Grid_Containers");
   	   
   	//   strArrivalDate = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName, "Grid_Containers", 0, "ArrivedDate");
   	   
   	   strNextDay = objAdjunoLIMAContainerRestitutionPOM.getDate(0, "dd MMM yyyy");
   	   
   	   strNextDay2 = objAdjunoLIMAContainerRestitutionPOM.getDate(1, "dd MMM yyyy");
   	   
   	   if(!isNullOrBlank(strNextDay)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay, strRestitutionPageFormName, "Field_CollectionDate"),true,"***** *****");
   			
   	   }else{
   	   		strMessage=strMessage+" Collection Value is Empty";
   	   }
   	   
   	  if(!isNullOrBlank(strNextDay2)){
  			
  			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay2, strRestitutionPageFormName, "Field_RestitutionDate"),true,"***** *****");
  	   }else{
  	   		strMessage=strMessage+" Restitution Value is Empty";
  	   }
   	   
   	   if(!isNullOrBlank(strRestitutionPointVal)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strRestitutionPointVal, strRestitutionPageFormName, "Field_RestitutionPoint"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Restitution point Value is Empty in XML";
   	   }
   	   
   	   if(!isNullOrBlank(strRestitutionReason)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strRestitutionReason, strRestitutionPageFormName, "Field_ReasonCode"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Reason Value is Empty  in XML";
   	   }
   	   
   	   if(!isNullOrBlank(commentvalue)){
   			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(commentvalue, strRestitutionPageFormName, "Field_Comments"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Comments Value is Empty in XML";
   	   }
   	   
   	   wait(4000);
   	  
   	  for (int i = 0; i <= nRows-1; i++) {
   		  	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getGridCellElement(strRestitutionPageFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:32- Select CheckBox is not clicked in the Restitution Chevron *****");
   		  	
   	  }
   	  wait(2000);
   	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strRestitutionPageFormName, "BUTTON_Update"),true,"***** Test ID:32- Update buttoin not clicked in Restitution Chevron *****");
   	  wait(3000);
   	  
   	  //verify entered data
   	  for (int i = 0; i <= nRows-1; i++) {
  	
   		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"CollectionDate")), true,"***** Test ID:32-  Collection Date is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"RestitutionDate")), true,"***** Test ID:32-  Restitution Date is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strRestitutionPointVal.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"RestitutionPoint")), true,"***** Test ID:32-  Restitution Point is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strRestitutionReason.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"ReasonCode")), true,"***** Test ID:32- Reason is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(commentvalue.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"Comments")), true,"***** Test ID:32- Comments is not matching With Entered value *****");
   	 	 
   	  }
   	  
   	  //strTrackN0 = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",0,"TrackReference");
   	  
   	  wait(4000);
   	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 32"+ strMessage +"*****");
   	  objSoftAssert.assertAll();    }

    
    @Test(priority=130)
    public void test_33_complete(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevronComplete()), true,"***** Test ID:33- Complete Chevron is not clicked  *****");
 	   wait(1000);
 	   String poDetails = objAdjunoLIMAContainerRestitutionPOM.getTrackMessage().getText();
 	   String[] a= poDetails.split("achieved on track '", 2);
	 //  System.out.println(a[1]);
	   a = a[1].split("'");
	   strTrackN0 = a[0];
	   System.out.println(strTrackN0);
	   wait(5000);
	  
 	   objSoftAssert.assertAll();
    }
    
    @Test(priority=135)
    public void test_34_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   wait(5000);
 	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
 	    	
 	   objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAContainerRestitutionPOM.getLnkTrack(),objAdjunoLIMAContainerRestitutionPOM.getLnkEdit());
 	   wait(1000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:34- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=140)
	public void test_35_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getFldRefNo()),true," ***** Test ID:35-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValueForWebElement(objAdjunoLIMAContainerRestitutionPOM.getFldRefNo(),strTrackN0),true,"***** Test ID: 35-Reference field value is not set");   
			    	  
			 objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:35-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=145)
    public void test_36_clickRestitutionLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getTracklnkContainerRestitution()), true,"***** Test ID:36- Container Restitution link is not clicked *****");
		wait(4000);
		
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTitleContainer()), true,"***** Test ID : 36 - Container Restitution page title is not found *****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdContainer()), true,"***** Test ID : 36 - In Container Restitution page \"Container\"  is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdSeal()), true,"***** Test ID : 36 - In Container Restitution page \"Seal\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdCollectionDate()), true,"***** Test ID :36 - In Container Restitution page \"Collection Date\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdRestitutionDate()), true,"***** Test ID : 36 - In Container Restitution page \"Restitution Date\" is not found *****");
        

		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdRestitutionPoint()), true,"***** Test ID : 36 - In Container Restitution page \"Restitution Point\"  is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdReasonCode()), true,"***** Test ID :36 - In Container Restitution page \"ReasonCode\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdComments()), true,"***** Test ID : 36 - In Container Restitution page \"Comments\" is not found *****");
		
        
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColContainer().getText().equalsIgnoreCase(strContainerNo), true," Test ID : 36 - In Container Restitution page Container value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColCollectionDate().getText().contains(strNextDay), true," Test ID : 36 - In Container Restitution page Collection Date value is not matching *****");
        System.out.println("sdatee"+objAdjunoLIMAContainerRestitutionPOM.getColCollectionDate().getText());
        
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColRestitutionDate().getText().contains(strNextDay2), true," Test ID : 36 - In Container Restitution page Restitution Date value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColRestitutionPoint().getText().equalsIgnoreCase(strRestitutionPointVal), true," Test ID : 36 - In Container Restitution page  Restitution Point value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColReasonCode().getText().equalsIgnoreCase(strRestitutionReason), true," Test ID : 36 - In Container Restitution page Reason value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColComments().getText().equalsIgnoreCase(commentvalue), true," Test ID : 36 - In Container Restitution page Comments value is not matching *****");
        
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:36-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test(priority=150)
    public void test_37_accessContainerRestitution()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	 String strTitle = objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strSearchFormNameContainerRestitution,objAdjunoLIMAContainerRestitutionPOM.getLnkTools(), objAdjunoLIMAContainerRestitutionPOM.getLnkContainerRestitution());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strPageTitleContainerRestitution))
                 bFlag = true;
             else
                 bFlag = false;
         }          
         
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:37-Title of the Container Restitution page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    @Test(priority=155)
    public void test_38_SearchContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
		wait(5000);
		clearFields();
		wait(5000);
		//strStatusRestituted
		
		if(!isNullOrBlank(strStatusRestituted)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strStatusRestituted, strSearchFormNameContainerRestitution, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Restituted status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strContainerNo)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strContainerNo, strSearchFormNameContainerRestitution, "PARAM_Container"),true,"*****Test ID:38- Container value is not in Search Chevron In Container Restitution tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true,"***** Test ID:38- Select Chevron is not clicked in Container Restitution Tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr());
	
		if(searchResult==false){
			objAdjunoLIMAContainerRestitutionPOM.getLstSelect().get(0).click();
			
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
	//	strMessage = strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyContainerAndStaus(strContainerValue,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:38- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    }   
    
    @Test(priority=160)
    public void test_39_navigateToRestitutionChevron()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevronRestitution()), true,"***** Test ID:39- Restitution Chevron is not clicked in Container Restitution Tool *****");
		wait(4000);
		
		
		if(objAdjunoLIMAContainerRestitutionPOM.isPageLoaded(strRestitutionPageFormName)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_CollectionDate"), true, "*****test ID:_39_Collection Date field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_RestitutionDate"), true, "*****test ID:_39_Restitution Date field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_RestitutionPoint"), true, "*****test ID:_39_Restituion Point field is not displayed in Container Restitution Restitution Chevron*****");
 	       	
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_ReasonCode"), true, "*****test ID:_39_Reason Code To field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_Comments"), true, "*****test ID:_39_Comments field is not displayed in Container Restitution Search Restitution*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "BUTTON_Update"), true,"***** Test ID:39- Update Button is not found in \"Container Restitution Chevorn\" in Container Restitution Tool *****");
 			    
			   
		}else{
			   strMessage = strMessage +" Container Restitution Chevron Page is not loaded";  
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:39- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    
    }
    
    @Test(priority=165)
    public void test_40_VerifyGridValue() throws ParseException, java.text.ParseException{
    	 SoftAssert objSoftAssert = new SoftAssert();
    	 wait(5000);
    	 objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
     	   String strMessage="";
     	   
     	   int nRows = (int) objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strRestitutionPageFormName, "Grid_Containers");
     	   
     	  //   strArrivalDate = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName, "Grid_Containers", 0, "ArrivedDate");
     	   
     	   strNextDay = objAdjunoLIMAContainerRestitutionPOM.getDate(-1, "dd MMM yyyy");
     	   
     	  //  strNextDay2 = objAdjunoLIMAContainerRestitutionPOM.getDate(1, "dd MMM yyyy");
     	   
     	   if(!isNullOrBlank(strNextDay)){
     			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay, strRestitutionPageFormName, "Field_CollectionDate"),true,"***** *****");
     			
     	   }else{
     	   		strMessage=strMessage+" Collection Date Value is Empty";
     	   }
     	   
     	   
     	   if(!isNullOrBlank(strRestitutionPointVal2)){
     			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strRestitutionPointVal2, strRestitutionPageFormName, "Field_RestitutionPoint"),true,"***** *****");	
     	   }else{
     	   		strMessage=strMessage+"Restitution Point Value is Empty";
     	   }
     	   
     	   
     	   wait(4000);
     	  
     	  for (int i = 0; i <= nRows-1; i++) {
     		  	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getGridCellElement(strRestitutionPageFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:40- Select CheckBox is not clicked in the Restitution Chevron *****");   		  	
     	  }
     	  
     	  wait(2000);
     	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strRestitutionPageFormName, "BUTTON_Update"),true,"***** Test ID:40- Update buttoin not clicked in Restitution Chevron *****");
     	  wait(3000);
     	  
     	  //verify entered data
     	  for (int i = 0; i <= nRows-1; i++) {
    	
     		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"CollectionDate")), true,"***** Test ID:37-  Collection Date is not matching With Entered value *****");
     	 	  objSoftAssert.assertEquals(strRestitutionPointVal2.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"RestitutionPoint")), true,"***** Test ID:37-  Restitution Point is not matching With Entered value *****");
     	 	 
     	  }
     	  
     	   
     	  //strTrackN0 = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",0,"TrackReference");
     	  wait(4000);
     	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 40"+ strMessage +"*****");
     	  objSoftAssert.assertAll();
     	  }

    
    @Test(priority=168)
    public void test_41_complete(){
    	SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
  	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevronComplete()), true,"***** Test ID:41- Complete Chevron is not clicked  *****");
  	   wait(1000);
  	   String poDetails = objAdjunoLIMAContainerRestitutionPOM.getTrackMessage().getText();
  	   String[] a= poDetails.split("achieved on track '", 2);
 	   System.out.println(a[1]);
 	   a = a[1].split("'");
 	   strTrackN0 = a[0];
 	   System.out.println(strTrackN0);
 	   wait(5000);
 	  
  	   objSoftAssert.assertAll();
     }
    
    
    
    @Test(priority=170)
    public void test_42_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	  
 	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
 	  wait(5000);
 	   objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAContainerRestitutionPOM.getLnkTrack(),objAdjunoLIMAContainerRestitutionPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:42- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=175)
	public void test_43_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getFldRefNo()),true," ***** Test ID:43-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValueForWebElement(objAdjunoLIMAContainerRestitutionPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:43-Reference field value is not set");   
			    	  
			 objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:43-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=180)
    public void test_44_clickRestitutionLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getTracklnkContainerRestitution()), true,"***** Test ID:44- Container Restitution link is not clicked *****");
		wait(4000);
		
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdContainer()), true,"***** Test ID : 44 - In Container Restitution page \"Container\"  is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdSeal()), true,"***** Test ID : 44 - In Container Restitution page \"Seal\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdCollectionDate()), true,"***** Test ID :44 - In Container Restitution page \"Collection Date\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdRestitutionDate()), true,"***** Test ID : 44 - In Container Restitution page \"Restitution Date\" is not found *****");
        

		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdRestitutionPoint()), true,"***** Test ID : 44 - In Container Restitution page \"Restitution Point\"  is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdReasonCode()), true,"***** Test ID :44 - In Container Restitution page \"ReasonCode\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdComments()), true,"***** Test ID : 44 - In Container Restitution page \"Comments\" is not found *****");
		
        
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColContainer().getText().equalsIgnoreCase(strContainerNo), true," Test ID : 44 - In Container Restitution page Container value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColCollectionDate().getText().contains(strNextDay), true," Test ID : 44 - In Container Restitution page Collection Date value is not matching *****");
        System.out.println("sdatee"+objAdjunoLIMAContainerRestitutionPOM.getColCollectionDate().getText());
        
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColRestitutionDate().getText().contains(strNextDay2), true," Test ID : 44 - In Container Restitution page Restitution Date value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColRestitutionPoint().getText().equalsIgnoreCase(strRestitutionPointVal2), true," Test ID : 44 - In Container Restitution page  Restitution Point value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColReasonCode().getText().equalsIgnoreCase(strRestitutionReason), true," Test ID : 44 - In Container Restitution page Reason value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColComments().getText().equalsIgnoreCase(commentvalue), true," Test ID : 44 - In Container Restitution page Comments value is not matching *****");
        
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:44-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test(priority=185)
    public void test_45_accessContainerRestitution()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	 String strTitle = objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strSearchFormNameContainerRestitution,objAdjunoLIMAContainerRestitutionPOM.getLnkTools(), objAdjunoLIMAContainerRestitutionPOM.getLnkContainerRestitution());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strPageTitleContainerRestitution))
                 bFlag = true;
             else
                 bFlag = false;
         }          
         
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:45-Title of the Container Restitution page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    @Test(priority=190)
    public void test_46_SearchContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
		wait(5000);
		clearFields();
		wait(5000);
		//strStatusRestituted
		
		if(!isNullOrBlank(strStatusRestituted)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strStatusRestituted, strSearchFormNameContainerRestitution, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Restituted status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strContainerNo)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strContainerNo, strSearchFormNameContainerRestitution, "PARAM_Container"),true,"*****Test ID:46- Container value is not in Search Chevron In Container Restitution tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true,"***** Test ID:46- Select Chevron is not clicked in Container Restitution Tool *****");
		wait(5000);
		
		searchResult = objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr());
	
		if(searchResult==false){
			objAdjunoLIMAContainerRestitutionPOM.getLstSelect().get(0).click();
			
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
	//	strMessage = strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyContainerAndStaus(strContainerValue,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:46- "+ strMessage +"*****");
		objSoftAssert.assertAll();			   
    }   
    
    @Test(priority=195)
    public void test_47_navigateToRestitutionChevron()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevronRestitution()), true,"***** Test ID:47- Restitution Chevron is not clicked in Container Restitution Tool *****");
		wait(4000);
		
		
		if(objAdjunoLIMAContainerRestitutionPOM.isPageLoaded(strRestitutionPageFormName)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_CollectionDate"), true, "*****test ID:_47_Collection Date field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_RestitutionDate"), true, "*****test ID:_47_Restitution Date field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_RestitutionPoint"), true, "*****test ID:_47_Restituion Point field is not displayed in Container Restitution Restitution Chevron*****");
 	       	
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_ReasonCode"), true, "*****test ID:_47_Reason Code To field is not displayed in Container Restitution Restitution Chevron*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "Field_Comments"), true, "*****test ID:_47_Comments field is not displayed in Container Restitution Search Restitution*****");
 	       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.checkDoesFieldsExist(strRestitutionPageFormName, "BUTTON_Update"), true,"***** Test ID:_47_ Update Button is not found in \"Container Restitution Chevorn\" in Container Restitution Tool *****");
 			    
			   
		}else{
			   strMessage = strMessage +" Container Restitution Chevron Page is not loaded";  
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:47- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    
    }

    
    @Test(priority=198)
    public void test_48_VerifyGridValue() throws ParseException, java.text.ParseException{
    	SoftAssert objSoftAssert = new SoftAssert();
    	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
    	   String strMessage="";
    	   
    	   int nRows = (int) objAdjunoLIMAContainerRestitutionPOM.getNoOfRowsGrid(strRestitutionPageFormName, "Grid_Containers");
    	   
    	  //   strArrivalDate = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName, "Grid_Containers", 0, "ArrivedDate");
    	   
    	//   strNextDay = objAdjunoLIMAContainerRestitutionPOM.getDate(0, "dd MMM yyyy");
    	   
    	   strNextDay2 = objAdjunoLIMAContainerRestitutionPOM.getDate(2, "dd MMM yyyy");
    	   
    	  /* if(!isNullOrBlank(strNextDay)){
    			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay, strRestitutionPageFormName, "YardArrivalDate1"),true,"***** *****");
    			
    	   }else{
    	   		strMessage=strMessage+" Collection Date Value is Empty";
    	   }*/
 	   
 	   if(!isNullOrBlank(strNextDay2)){
    			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strNextDay2, strRestitutionPageFormName, "Field_RestitutionDate"),true,"***** *****");
    			
    	   }else{
    	   		strMessage=strMessage+" Restitution Date Value is Empty";
    	   }
  	   	 
    	  
    	   wait(4000);
    	  
    	  for (int i = 0; i <= nRows-1; i++) {
    		  	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getGridCellElement(strRestitutionPageFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:45- Select CheckBox is not clicked in the Restitution Chevron *****");
    		  	
    	  }
    	  wait(2000);
    	  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButton(strRestitutionPageFormName, "BUTTON_Update"),true,"***** Test ID:48- Update buttoin not clicked in Restitution Chevron *****");
    	  wait(3000);
    	  
    	  //verify entered data
    	  for (int i = 0; i <= nRows-1; i++) {
   	
    		 // objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"YardArrivalDate")), true,"***** Test ID:45-  Yard Arrival Date is not matching With Entered value *****");
    	 	  
    	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",i,"RestitutionDate")), true,"***** Test ID:48-  Restitution Date is not matching With Entered value *****");
    	 	  
    	 	  
    	 	 
    	  }
    	  
    	   
    	  //strTrackN0 = objAdjunoLIMAContainerRestitutionPOM.getGridCellElementValue(strRestitutionPageFormName,"Grid_Containers",0,"TrackReference");
    	  wait(4000);
    	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :48"+ strMessage +"*****");
    	  objSoftAssert.assertAll();    }

    @Test(priority=201)
    public void test_49_complete(){
    	SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
  	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevronComplete()), true,"***** Test ID:49- Complete Chevron is not clicked  *****");
  	   wait(1000);
  	   String poDetails = objAdjunoLIMAContainerRestitutionPOM.getTrackMessage().getText();
  	   String[] a= poDetails.split("achieved on track '", 2);
 	   System.out.println(a[1]);
 	   a = a[1].split("'");
 	   strTrackN0 = a[0];
 	   System.out.println(strTrackN0);
 	   wait(5000);
 	  
  	   objSoftAssert.assertAll();
     }
    
    
    
    @Test(priority=202)
    public void test_50_ClickOnTrackLink(){	    
    	 SoftAssert objSoftAssert = new SoftAssert();
    	 wait(5000);
   	   objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
   	    	
   	   objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAContainerRestitutionPOM.getLnkTrack(),objAdjunoLIMAContainerRestitutionPOM.getLnkEdit());
   	   wait(4000);
   	  
   	   objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:50- Header Title is not matching *****");
   	   
   	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=203)
	public void test_51_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getFldRefNo()),true," ***** Test ID:51-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValueForWebElement(objAdjunoLIMAContainerRestitutionPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:51-Reference field value is not set");   
			    	  
			 objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:51-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=204)
    public void test_52_clickRestitutionLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getTracklnkContainerRestitution()), true,"***** Test ID:52- Container Restitution link is not clicked *****");
		wait(4000);
						
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdContainer()), true,"***** Test ID : 52 - In Container Restitution page \"Container\"  is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdSeal()), true,"***** Test ID : 52 - In Container Restitution page \"Seal\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdCollectionDate()), true,"***** Test ID :52 - In Container Restitution page \"Collection Date\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdRestitutionDate()), true,"***** Test ID : 52 - In Container Restitution page \"Restitution Date\" is not found *****");
        

		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdRestitutionPoint()), true,"***** Test ID : 52 - In Container Restitution page \"Restitution Point\"  is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdReasonCode()), true,"***** Test ID :52 - In Container Restitution page \"ReasonCode\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getHdComments()), true,"***** Test ID : 52 - In Container Restitution page \"Comments\" is not found *****");
		
        
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColContainer().getText().equalsIgnoreCase(strContainerNo), true," Test ID : 52 - In Container Restitution page Container value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColCollectionDate().getText().contains(strNextDay), true," Test ID : 52 - In Container Restitution page Collection Date value is not matching *****");
        System.out.println("sdatee"+objAdjunoLIMAContainerRestitutionPOM.getColCollectionDate().getText());
        
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColRestitutionDate().getText().contains(strNextDay2), true," Test ID : 52 - In Container Restitution page Restitution Date value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColRestitutionPoint().getText().equalsIgnoreCase(strRestitutionPointVal2), true," Test ID : 52 - In Container Restitution page  Restitution Point value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColReasonCode().getText().equalsIgnoreCase(strRestitutionReason), true," Test ID : 52 - In Container Restitution page Reason value is not matching *****");
        objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getColComments().getText().equalsIgnoreCase(commentvalue), true," Test ID : 52 - In Container Restitution page Comments value is not matching *****");
        
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:52-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test(priority=205)
    public void test_53_accessContainerRestitution()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);
    	 String strTitle = objAdjunoLIMAContainerRestitutionPOM.callMouseHover(strSearchFormNameContainerRestitution,objAdjunoLIMAContainerRestitutionPOM.getLnkTools(), objAdjunoLIMAContainerRestitutionPOM.getLnkContainerRestitution());    
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strPageTitleContainerRestitution))
                 bFlag = true;
             else
                 bFlag = false;
         }          
         
        
         objSoftAssert.assertEquals(bFlag, true, "***** Test ID:53-Title of the Container Restitution page is wrong*****");
         objSoftAssert.assertAll();
    }
    
    @Test(priority=206)
    public void test_54_SearchContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAContainerRestitutionPOM = new AdjunoLIMAContainerRestitutionPOM(driver);	
		wait(5000);
		clearFields();
		wait(5000);
		//strStatusRestituted
		
		if(!isNullOrBlank(strStatusRestituted)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strStatusRestituted, strSearchFormNameContainerRestitution, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Restituted status Value is Empty in Xml";
		}
		
		if(!isNullOrBlank(strContainerNo)){
			objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.setFieldValue(strContainerNo, strSearchFormNameContainerRestitution, "PARAM_Container"),true,"*****Test ID:54- Container value is not in Search Chevron In Container Restitution tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clickButtonUsingWebElement(objAdjunoLIMAContainerRestitutionPOM.getChevSelect()), true,"***** Test ID:54- Select Chevron is not clicked in Container Restitution Tool *****");
		wait(5000);
		
		searchResult = objAdjunoLIMAContainerRestitutionPOM.isElementPresent(objAdjunoLIMAContainerRestitutionPOM.getTxtNoProductErr());
	
		if(searchResult==false){
			objAdjunoLIMAContainerRestitutionPOM.getLstSelect().get(0).click();
			
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
	//	strMessage = strMessage + objAdjunoLIMAContainerRestitutionPOM.verifyContainerAndStaus(strContainerValue,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:39- "+ strMessage +"*****");
		objSoftAssert.assertAll();			   
    }
       
    public void clearCatalogFields(){
    	SoftAssert objSoftAssert = new  SoftAssert();
       	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strCatalogFormName, "Param_Name"),true,"");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strCatalogFormName, "Param_Description"),true,"");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"),true,"");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"),true,"");
    	objSoftAssert.assertAll();
    }	
    
    public void clearFields(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	//clear field
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_Destination"), true,"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_DeliveryDateFrom"), true,"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_DeliveryDateTo"), true,"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_Status"), true,"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_WFStatus"), true,"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_Haulier"), true,"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_Container"), true,"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_ForwarderReference"), true,"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAContainerRestitutionPOM.clearInputField(strSearchFormNameContainerRestitution, "PARAM_RestitutionPoint"), true,"*****");
    	objSoftAssert.assertAll();
    }
    
    
    @AfterClass
   public void terminate(){
   	driver.quit();
   }
    
}
