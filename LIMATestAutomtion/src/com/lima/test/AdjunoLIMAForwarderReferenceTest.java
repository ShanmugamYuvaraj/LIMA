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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.ForwarderReference;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMAForwarderReferencePOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMAForwarderReferenceTest {
	
	WebDriver driver;
	boolean searchResult;
    long nPage_Timeout = 0;
    String strTestURL;
    String strTrackN0;
    
    String strUserName;
    String strPassword;
    String strForwarderReferenceTrackPage;
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoUILibrary objAdjunoUILibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAForwarderReferencePOM objAdjunoLIMAForwarderReferencePOM;

    String strFormNameSearchForwarderRef;
    String strForwarderRefTitle;

	ArrayList<String> vendorList;

	ArrayList<String> modeList;

	ArrayList<String> originList;

	ArrayList<String> originPortList;

	ArrayList<String> statusList;

	String strStatus;
	String strInvForwardRef;
	String strInvConatainer;
	String strStatusReferenced;
	String strFormNameForwarderRef;
	ForwarderReference ref;
	ForwarderReference ref2;
	ForwarderReference referencedRef;
	NodeList poCaptions;
	String strReference;
	String strShipQty;
	String strShipCtn;
	String strCompleteFromName;
	String strTrackNO;
	String strPONumber;
	String strPOStatusFormName;
    String strViewFormNamePO;
    String strProduct;
    String streditForwardRef;
    String strContainerValue;
    String strVesselValue;
    String strHouseBillValue;
	private String strEditForwardRef;
   
    
    
    
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
            
            Node formNameSearchForwarderRef = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Forwarder_Reference", dDoc, XPathConstants.NODE);
            strFormNameSearchForwarderRef = formNameSearchForwarderRef.getTextContent();
            
            Node formNameForwarderRef = (Node) xPath.evaluate("/config/LIMA/Form_Name_Forwarder_Reference", dDoc, XPathConstants.NODE);
            strFormNameForwarderRef = formNameForwarderRef.getTextContent();
            
            Node forwarderRefTitle = (Node) xPath.evaluate("/config/LIMA/Forwarder_Reference_Title", dDoc, XPathConstants.NODE);
            strForwarderRefTitle= forwarderRefTitle.getTextContent();
            
            Node testLIMAURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	        
  	        Node completeFromName = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
  	        strCompleteFromName = completeFromName.getTextContent();
  	        
  	        Node POStatusFormName = (Node) xPath.evaluate("config/LIMA/PO_Status_Form_Name",dDoc,XPathConstants.NODE);
  	        strPOStatusFormName=POStatusFormName.getTextContent();
  	        
  	        Node ForwarderReferenceTrackPage = (Node)xPath.evaluate("/config/LIMA/Forwarder_Reference_Track_Page",dDoc,XPathConstants.NODE);
  	        strForwarderReferenceTrackPage=ForwarderReferenceTrackPage.getTextContent();
  	       
  	       	Node ViewFormNamePO = (Node)xPath.evaluate("/config/LIMA/View_Form_Name_PO",dDoc,XPathConstants.NODE);
  	       	strViewFormNamePO=ViewFormNamePO.getTextContent();
  	       
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
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrLIMAForwarderReference());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            Node status = (Node) xPath1.evaluate("/Forwarder_Reference/Search_Param/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strStatus = status.getTextContent();
            
            Node invForwardRef = (Node) xPath1.evaluate("/Forwarder_Reference/Search_Param/Invalid_Forward_Ref", dDoc1, XPathConstants.NODE);
            strInvForwardRef = invForwardRef.getTextContent();
            
            Node statusReferenced = (Node) xPath1.evaluate("/Forwarder_Reference/Search_Param/Status_Referenced", dDoc1, XPathConstants.NODE);
            strStatusReferenced = statusReferenced.getTextContent();
            
            Node invContainer = (Node) xPath1.evaluate("/Forwarder_Reference/Search_Param/Invalid_Container", dDoc1, XPathConstants.NODE);
            strInvConatainer = invContainer.getTextContent();
                    
      
          
            poCaptions = (NodeList) xPath1.evaluate("/Forwarder_Reference/Po_captions", dDoc1, XPathConstants.NODESET);
            
                          
            
        }catch(Exception ex){
           ex.printStackTrace();
        }
   }
    
    public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
    
    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
    
    @Test(priority=1)
    public void test_1_AccessForwardReference(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	        wait(2000);
	    String strTitle = objAdjunoLIMAForwarderReferencePOM.callMouseHover(strFormNameSearchForwarderRef,objAdjunoLIMAForwarderReferencePOM.getLnkTools(),objAdjunoLIMAForwarderReferencePOM.getLnkForwarderReference());
	    boolean bFlag = true;
	    	
	    if (!(isNullOrBlank(strTitle))){
	    	if (strTitle.equalsIgnoreCase(strForwarderRefTitle))
	    		bFlag = true;
	    	else
	    		bFlag = false;
	    }else{
	    	bFlag = false;
	    }	       
	   	objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 1 - Page title of Forwarder Reference tool is wrong  *****");
	   	try{
	        wait(3000);
	        objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getBtnOK1());
	        wait(3000);
	        }
	        catch(NoSuchElementException e)
	        {
	        	
	        }
	   	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=5)
    public void test_2_verifyDropDownExistance(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage ="";
	    vendorList = new ArrayList<String>();
        vendorList = objAdjunoLIMAForwarderReferencePOM.getDropDownList(strFormNameSearchForwarderRef,"PARAM_Vendor");
        
        modeList = new ArrayList<String>();
        modeList = objAdjunoLIMAForwarderReferencePOM.getDropDownList(strFormNameSearchForwarderRef,"PARAM_Mode");
        
        originPortList = new ArrayList<String>();
        originPortList = objAdjunoLIMAForwarderReferencePOM.getDropDownList(strFormNameSearchForwarderRef,"PARAM_Port");
        
        originList = new ArrayList<String>();
        originList = objAdjunoLIMAForwarderReferencePOM.getDropDownList(strFormNameSearchForwarderRef,"PARAM_Origin");
        
        statusList = new ArrayList<String>();
        statusList = objAdjunoLIMAForwarderReferencePOM.getDropDownList(strFormNameSearchForwarderRef,"PARAM_Status");
        
        if(vendorList.size()>=2){
        	
        }else{
        	strMessage = strMessage +"In Forwarder Reference Tool Under Search Page \"Vendor\" field is not a drop down ";
        }
        
        if(originList.size()>=2){
        	
        }else{
        	strMessage = strMessage +"In Forwarder Reference Tool Under Search Page \"Origin\" field is not a drop down ";
        }
        
        
        if(modeList.size()>=2){
        	
        }else{
        	strMessage = strMessage +"In Forwarder Reference Tool Under Search Page \"Mode\" field is not a drop down ";
        }
        
        if(originPortList.size()>=2){
        	
        }else{
        	strMessage = strMessage +"In Forwarder Reference Tool Under Search Page \"OriginPort\" field is not a drop down ";
        }
        
        if(statusList.size()>=2){
        	
        }else{
        	strMessage = strMessage +"In Forwarder Reference Tool Under Search Page \"Status\" field is not a drop down ";
        }
        
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id: 2 - "+strMessage+" *****");
	    objSoftAssert.assertAll();
	    
    }
    
    public void clearField() {
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Container"), true,"***** In Forwarder Reference Tool \"Container\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_HouseBL"), true,"***** In Forwarder Reference Tool \"HBL/HAWB\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_ForwarderRef"), true,"***** In Forwarder Reference Tool \"Forwarder Reference\"field is not cleared *****");

	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Origin"), true,"***** In Forwarder Reference Tool \"Origin\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Port"), true,"***** In Forwarder Reference Tool \"Origin Port\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Mode"), true,"***** In Forwarder Reference Tool \"Mode\"field is not cleared *****");
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Vessel"), true,"***** In Forwarder Reference Tool \"Vessel\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_ETAFrom"), true,"***** In Forwarder Reference Tool \"ETA From\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_ETATo"), true,"***** In Forwarder Reference Tool \"ETA To\"field is not cleared *****");

	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Status"), true,"***** In Forwarder Reference Tool \"Status\"field is not cleared *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strFormNameSearchForwarderRef,"PARAM_Vendor"), true,"***** In Forwarder Reference Tool \"Vendor\"field is not cleared *****");
	    
	    objSoftAssert.assertAll();
		
	}
    @Test(priority=10)
    public void test_7_VerifyExistanceOfFields(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnly(strFormNameSearchForwarderRef,"PARAM_Container","test"), true,"***** Test Id:7 - In Forwarder Reference Tool Under Search chevron \"Container\"field is Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnly(strFormNameSearchForwarderRef,"PARAM_HouseBL","test"), true,"***** Test Id:7 - In Forwarder Reference Tool Under Search chevron \"HBL/HAWB\"field is Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnly(strFormNameSearchForwarderRef,"PARAM_ForwarderRef","test"), true,"***** Test Id:7 - In Forwarder Reference Tool Under Search chevron \"Forwarder Reference\"field is Read Only *****");	    
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=15)
    public void test_8_VerifyExistanceOfFields(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnly(strFormNameSearchForwarderRef,"PARAM_ETAFrom",objAdjunoLIMAForwarderReferencePOM.getDate(0, "dd MMM yyyy")), true,"***** Test Id:8 - In Forwarder Reference Tool Under Search chevron \"ETA From\" field is Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnly(strFormNameSearchForwarderRef,"PARAM_ETATo",objAdjunoLIMAForwarderReferencePOM.getDate(10, "dd MMM yyyy")), true,"***** Test Id:8 - In Forwarder Reference Tool Under Search chevron \"ETA To\" field is Read Only *****");
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=18)
   	public void verifyExixtanceOFChevrons()
    {    	
       	SoftAssert objSoftAssert = new SoftAssert();
       	objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
           
       	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesChevronExist(objAdjunoLIMAForwarderReferencePOM.getChvSearch()), true,"***** In Forwarder Reference Tool \"Search chevron\"field is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesChevronExist(objAdjunoLIMAForwarderReferencePOM.getChvSelect()), true,"***** In Forwarder Reference Tool \"Edit chevron\"field is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesChevronExist(objAdjunoLIMAForwarderReferencePOM.getChvComplete()), true,"***** In Forwarder Reference Tool \"Complete chevron\"field is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesChevronExist(objAdjunoLIMAForwarderReferencePOM.getChvReference()), true,"***** In Forwarder Reference Tool \"Reference chevron\"field is not found *****");
      
        objSoftAssert.assertAll();
   }

    
    @Test(priority=20)
    public void test_9_VerifySingleCriteriaSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    clearField();
	    
	    if(!isNullOrBlank(strStatus)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef,"PARAM_Status"), true,"***** Test Id :9 - ***** In Forwarder Reference Tool Under Search Chevron \"Status\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " status value is empty in Xml ";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvSelect()),true,"***** Test Id :9 - ***** In Forwarder Reference Tool \"Select Chevron\" is not Clicked *****");
	    wait(5000);
	    strContainerValue = objAdjunoLIMAForwarderReferencePOM.getContainerValue().getText();
	    strVesselValue = objAdjunoLIMAForwarderReferencePOM.getStrVessel().getText();
	    strHouseBillValue = objAdjunoLIMAForwarderReferencePOM.getStrHouseBillValue().getText();
	    wait(1000);
	    searchResult = objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoLIMAForwarderReferencePOM.verifyValue(strStatus,objAdjunoLIMAForwarderReferencePOM.getTrStatus());
		    
	    }else{
	    	strMessage = strMessage + "In Forwarder Reference Tool under Select chevron \" No items were found\" Message is displayed ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9 - "+strMessage+" *****");
	   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=25)
    public void test_10_VerifyMultipleCriteriaSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    clearField();
	   
	    if(!isNullOrBlank(strStatus)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef,"PARAM_Status"), true,"***** Test Id :10 - ***** In Forwarder Reference Tool Under Select Chevron \"Status\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " status value is empty in Xml ";
	    }
	    
	    if(!isNullOrBlank(strVesselValue)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strVesselValue, strFormNameSearchForwarderRef,"PARAM_Vessel"), true,"***** Test Id :10 - ***** In Forwarder Reference Tool Under Select Chevron \"Vessel\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " Vessel value is empty in Xml ";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButton(strFormNameSearchForwarderRef,"FORK_ForwarderRef_RefineSearch"),true,"***** Test Id :10 - ***** In Forwarder Reference Tool Under Select Chevron \"Refine Search Button\" is not Clicked *****");
    	wait(2000);
	    searchResult = objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoLIMAForwarderReferencePOM.verifyStatusAndVessel(strStatus,strVesselValue);
		    
	    }else{
	    	strMessage = strMessage + "In Forwarder Reference Tool under Select chevron \" No items were found\" Message is displayed ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :10 - "+strMessage+" *****");
	   
	    objSoftAssert.assertAll();
    }
	
    
    @Test(priority=30)
    public void test_11_VerifySingleInvalidCriteriaSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    clearField();
	    
	    if(!isNullOrBlank(strStatus)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef,"PARAM_Status"), true,"***** Test Id :11 - ***** In Forwarder Reference Tool Under Select Chevron \"Status\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " status value is empty in Xml ";
	    }
	    
	    if(!isNullOrBlank(strInvForwardRef)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strInvForwardRef, strFormNameSearchForwarderRef,"PARAM_ForwarderRef"), true,"***** Test Id :11 - ***** In Forwarder Reference Tool Under Select Chevron \"Forwarder Reference\" Field InValid value is not set *****");
	    }else{
	    	strMessage = strMessage + " Forward Ref value is empty in Xml ";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButton(strFormNameSearchForwarderRef,"FORK_ForwarderRef_RefineSearch"),true,"***** Test Id :11 - ***** In Forwarder Reference Tool Under Select Chevron \"Refine Search Button\" is not Clicked *****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem()),true," ***** Test Id :11- In Forwarder Reference Tool under Select chevron \" No items were found\" validation message not found *****");
	   
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :11 - "+strMessage+" *****");
		   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=35)
    public void test_12_VerifyMultipleInvalidCriteriaSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    clearField();
	    
	    if(!isNullOrBlank(strStatus)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef,"PARAM_Status"), true,"***** Test Id :12 - ***** In Forwarder Reference Tool Under Select Chevron \"Status\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " status value is empty in Xml ";
	    }
	    
	    if(!isNullOrBlank(strInvForwardRef)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strInvForwardRef, strFormNameSearchForwarderRef,"PARAM_ForwarderRef"), true,"***** Test Id :12 - ***** In Forwarder Reference Tool Under Select Chevron \"Forwarder reference \" Field Invalid value is not set *****");
	    }else{
	    	strMessage = strMessage + " Forward Ref value is empty in Xml ";
	    }
	    
	    if(!isNullOrBlank(strInvConatainer)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strInvConatainer, strFormNameSearchForwarderRef,"PARAM_Container"), true,"***** Test Id :12 - ***** In Forwarder Reference Tool Under Select Chevron \"Container\" Field Invalid value is not set *****");
	    }else{
	    	strMessage = strMessage + " Forward Ref value is empty in Xml ";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButton(strFormNameSearchForwarderRef,"FORK_ForwarderRef_RefineSearch"),true,"***** Test Id :12 - ***** In Forwarder Reference Tool Under Select Chevron \"Refine Search Button\" is not Clicked *****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem()),true," ***** Test Id :12 - In Forwarder Reference Tool under Select chevron \" No items were found\" validation message not found *****");
	   
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :12 - "+strMessage+" *****");
		   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=40)
    public void test_14_VerifyStatusReferencedSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    clearField();
	    
	    if(!isNullOrBlank(strStatusReferenced)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatusReferenced, strFormNameSearchForwarderRef,"PARAM_Status"), true," ***** Test Id :14 - In Forwarder Reference Tool under Select chevron \"Status\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " status value is empty in Xml ";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButton(strFormNameSearchForwarderRef,"FORK_ForwarderRef_RefineSearch"),true," ***** Test Id :14 - In Forwarder Reference Tool under Select chevron \" Refine Search Button\" is not Clicked *****");
    	wait(2000);
    	
	    searchResult = objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoLIMAForwarderReferencePOM.verifyValue(strStatusReferenced,objAdjunoLIMAForwarderReferencePOM.getTrStatus());
		    
	    }else{
	    	strMessage = strMessage + " In Forwarder Reference Tool under Select chevron \" No items were found\" validation message found ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :14 - "+strMessage+" *****");
	   
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=45)
    public void test_15_VerifyConatinerSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    clearField();
	    
	    
	    if(!isNullOrBlank(strStatus)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef,"PARAM_Status"), true," ***** Test Id :15 - In Forwarder Reference Tool under Select chevron \"Status\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + " status value is empty in Xml ";
	    }
	    
	    if(!isNullOrBlank(strContainerValue)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strContainerValue, strFormNameSearchForwarderRef,"PARAM_Container"), true," ***** Test Id :15 - In Forwarder Reference Tool under Select chevron \"Container\" Field value is not set *****");
	    }else{
	    	strMessage = strMessage + "container value is empty in Xml ";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButton(strFormNameSearchForwarderRef,"FORK_ForwarderRef_RefineSearch"),true," ***** Test Id :15 - In Forwarder Reference Tool under Select chevron \"Refine Search Button\" is not Clicked *****");
    	wait(2000);
	    searchResult = objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoLIMAForwarderReferencePOM.verifyValue(strStatus,objAdjunoLIMAForwarderReferencePOM.getTrStatus());
		    
	    }else{
	    	strMessage = strMessage + " In Forwarder Reference Tool under Select chevron \" No items were found\" validation message found";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :15 - "+strMessage+" *****");
	   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=48)
    public void test_18_VerifyConatinerSearchData(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    
	    
	    searchResult = objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoLIMAForwarderReferencePOM.verifyValue(strContainerValue, objAdjunoLIMAForwarderReferencePOM.getTrContainer());
		    
	    }else{
	    	strMessage = strMessage +"In Forwarder Reference Tool under Select chevron \" No items were found\" validation message found ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :18 - "+strMessage+" *****");
	   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=50)
    public void test_20_VerifyConatinerAwaitingStaus(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    String strMessage = "";
	    
	    searchResult = objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoLIMAForwarderReferencePOM.verifyValue(strStatus,objAdjunoLIMAForwarderReferencePOM.getTrStatus());
		    
	    }else{
	    	strMessage = strMessage +"In Forwarder Reference Tool under Select chevron \" No items were found\" validation message found ";
	    }
	    System.out.println("test");
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :20 - "+strMessage+" *****");
	   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=60)
    public void test_21_Click_Reference(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    objAdjunoLIMAForwarderReferencePOM.getSelectBox().get(0).click();
	    wait(2000);
	    
	    ref = new ForwarderReference(objAdjunoLIMAForwarderReferencePOM.getTrVessel().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrOriginPort().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrDestinationPort().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrETA().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrContainer().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrVendor().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrHouseBill().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrShipQty().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrShipCtns().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrStatus().get(0).getText(),"");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvReference2()), true," ***** Test Id:21 - In Forwarder Reference Tool\" Reference Chevron\" is not clicked *****");
	    wait(2000);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getReferenceTitle().getText(), "Reference"," ***** Test Id:21 - In Forwarder Reference Tool\" Reference Chevron\" is not Loaded *****");
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=65)
    public void test_22_verifyGridsInReferenceChevron(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesElementExist(strFormNameForwarderRef, "GridForwarder"), true," ***** Test Id:22 - In Forwarder Reference Tool under Reference Chevron\" GridForwarder\" is not found *****");
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesElementExist(strFormNameForwarderRef, "GridProducts"), true," ***** Test Id:22 - In Forwarder Reference Tool under Reference Chevron\" GridProducts\" is not found *****");
	   
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=70)
    public void test_23_verifyGridForwarder(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    ref2 = new ForwarderReference(objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "Vessel"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "OriginPort"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "DestinationPort"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "ETA"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "Container"), "", objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "HouseBill"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "Quantity"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "Cartons"), "","");
	    
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "Vessel"),false,"***** Test Id:23 - In Forwarder Reference Tool Under Reference chevron \"Vessel\" field is not Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "OriginPort"),false,"***** Test Id:23 - In Forwarder Reference Tool Under Reference chevron \"Vessel\" field is not Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "DestinationPort"),false,"***** Test Id:23 - In Forwarder Reference Tool Under Reference chevron \"DestinationPort\" field is not Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "ETA"),false,"***** Test Id:23 - In Forwarder Reference Tool Under Reference chevron \"ETA\" field is not Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "Container"),false,"***** Test Id:23 - In Forwarder Reference Tool Under Reference chevron \"Container\" field is not Read Only *****");
	    
	    
	    objSoftAssert.assertEquals(ref.getContainer().equalsIgnoreCase(ref2.getContainer()),true,"***** Test ID : 23 - In Forwarder Reference Tool the selected Container value under Select Chevron does not match with Container value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    
	    System.out.println("one:"+ref.getEta()+"--"+ "two:"+ref2.getEta());
	    objSoftAssert.assertEquals(ref.getDestinationPort().equalsIgnoreCase(ref2.getDestinationPort()),true,"***** Test ID : 23 - In Forwarder Reference Tool the selected DestinationPort value under Select Chevron does not match with Destination Port value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    objSoftAssert.assertEquals(ref.getOriginPort().equalsIgnoreCase(ref2.getOriginPort()),true,"***** Test ID : 23 - In Forwarder Reference Tool the selected OriginPort value under Select Chevron does not match with OriginPort value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    objSoftAssert.assertEquals(ref.getVessel().equalsIgnoreCase(ref2.getVessel()),true,"***** Test ID : 23 - In Forwarder Reference Tool the selected vessel value under Select Chevron does not match with vessel value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    objSoftAssert.assertEquals(ref.getEta().equalsIgnoreCase(ref2.getEta()),true,"***** Test ID : 23 - In Forwarder Reference Tool the selected ETA value under Select Chevron does not match with ETA value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=75)
    public void test_24_verifyGridForwarder(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridProducts", 0, "HouseBill"),false,"***** Test Id:24 - In Forwarder Reference Tool Under Reference chevron \"House Bill\" field is not Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridProducts", 0, "Quantity"),false,"***** Test Id:24 - In Forwarder Reference Tool Under Reference chevron \"Qualtity\" field is not Read Only *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridProducts", 0, "Cartons"),false,"***** Test Id:24 - In Forwarder Reference Tool Under Reference chevron \"Cartons\" field is not Read Only *****");
	   
	    objSoftAssert.assertEquals(ref.getHouseBill().equalsIgnoreCase(ref2.getHouseBill()),true,"***** Test ID : 24 - In Forwarder Reference Tool the selected HouseBill value under Select Chevron does not match with HouseBill value displayed in Reference Chevron under GridProducts Details Grid *****");
	    objSoftAssert.assertEquals(ref.getShipQty().equalsIgnoreCase(ref2.getShipQty()),true,"***** Test ID : 24 - In Forwarder Reference Tool the selected Ship Qty value under Select Chevron does not match with Ship Qty value displayed in Reference Chevron under GridProducts Details Grid *****");
	    objSoftAssert.assertEquals(ref.getShipCtn().equalsIgnoreCase(ref2.getShipCtn()),true,"***** Test ID : 24 - In Forwarder Reference Tool the selected Ship Ctns value under Select Chevron does not match with Ship Ctns value displayed in Reference Chevron under GridProducts Details Grid *****");
	    
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=80)
    public void test_25_VerifyForwardReferenceMandatory(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference"),true,"***** Test Id:25 - In Forwarder Reference Tool Under Reference chevron \"ForwarderReference\" field is Read Only *****");
	   
	    String strErrorForwarderReference = objAdjunoLIMAForwarderReferencePOM.getValidationMessageGridElement(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference");
 	   	if(!strErrorForwarderReference.equals("")){
 	   		if(strErrorForwarderReference.contains("&#39;")){
 	   		strErrorForwarderReference = strErrorForwarderReference.replaceAll("&#39;", "'");
 		   	}
 	   		boolean bFlag = strErrorForwarderReference.equalsIgnoreCase("> 'Forwarder Reference' is a required field");
 	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:25 - In Forwarder Reference Tool Under Reference Chevron \"Forwarder Reference \" Field is not Mandatory *****");	   			
 	   	}else{
 	   		strMessage = strMessage +"In Forwarder Reference Tool Under Reference Chevron \"Forwarder Reference \" Field Mandatory message is not displayed*****";
 	   	}
 	   	
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:25 -"+strMessage+" *****");
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=85)
    public void test_26_VerifyPoHyperLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    strShipQty = objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "Quantity");
	    strShipCtn = objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "Cartons");	
	    strMessage= strMessage + objAdjunoLIMAForwarderReferencePOM.isHyperlinkPresent(strFormNameForwarderRef, "GridProducts", 0, "PODetail");
	   
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id:26 - In Forwarder Reference tool Under Reference Chevron PO Details"+strMessage+" *****");
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=90)
    public void test_27_ClickHyperLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
		objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickHyperLink(strFormNameForwarderRef, "GridProducts", 0, "PODetail"),true,"***** Test Id:27 - In Forwarder Reference tool Under Reference Chevron \"PO Details\" Hyperlink is not clicked *****");
	    
		strPONumber = objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "POGrid", 0, "OrderNumber");
		System.out.println("po number:"+strPONumber);
		strProduct= objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "POGrid", 0, "Product");
		System.out.println("product number :"+strProduct);
		ArrayList<String> list = new ArrayList<String>();
		
		list = objAdjunoLIMAForwarderReferencePOM.getCaptionsList(strFormNameForwarderRef,"POGrid");
		    
		
		strMessage = objAdjunoLIMAForwarderReferencePOM.verifyCaptionsONGrid(list,poCaptions,7);
			
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:27 - In Forwarder Reference tool Under Reference Chevron PO Details Page"+strMessage+" *****");
	    
 	   	objSoftAssert.assertAll();
    }
    
    //take total shipqty and total cartons
    @Test(priority=95)
    public void test_28_VerifyShipQtyAndCtnInPOLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    String strQty = objAdjunoLIMAForwarderReferencePOM.getSplitValueOfString(objAdjunoLIMAForwarderReferencePOM.getSumValueOfShipQty());
	    String strCtn = objAdjunoLIMAForwarderReferencePOM.getSplitValueOfString(objAdjunoLIMAForwarderReferencePOM.getSumValueOfShipCtns());

	    System.out.println(strShipQty +"is matching "+strQty);
	    System.out.println(strShipCtn +"is matching "+strCtn);

	    objSoftAssert.assertEquals(strShipQty.equalsIgnoreCase(strQty), true,"***** Test Id :28 - In Forwarder Reference Tool Under reference Chevron the selected Ship Qty value under Reference Chevron does not match with Ship Qty details displayed in PO details page *****");
	    
	    objSoftAssert.assertEquals(strShipCtn.equalsIgnoreCase(strCtn), true,"***** Test Id :28 - In Forwarder Reference Tool Under reference Chevron the selected Ship Ctns value under Reference Chevron does not match with Ship Ctns details displayed in PO details page *****");
 	   	
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getClosePODetails()), true, "***** Test Id :28 - In Forwarder Reference Tool Under reference Chevron \"PO details\"page is not closed *****");
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=98)
    public void test_29_VerifyMandatory(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvComplete2()),true,"***** Test Id :29 - In Forwarder Reference Tool \"Complete Chevron\"is not clicked *****");
	    wait(5000);
 	   	
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getValidationMsg().getText(), "You must correct all validation failures before progressing.", "***** Test Id :29 -  Expected validation message :\"You must correct all validation failures before progressing.\" But is showing: "+objAdjunoLIMAForwarderReferencePOM.getValidationMsg().getText()+ "*****");
 	   	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=100)
    public void test_30_SetForwardRef(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	   // strReference= get
	    strReference = objAdjunoLIMAForwarderReferencePOM.generateRandomString();
	    if(!isNullOrBlank(strReference)){
	    	objAdjunoLIMAForwarderReferencePOM.setFieldValueForGridCell(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference",strReference);
	    }else{
	    	strMessage = strMessage + " Forwarder reference value is empty in xml";
	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvComplete2()),true,"***** Test Id :30 - In Forwarder Reference Tool \"Complete Chevron\"is not clicked *****");
	    wait(5000);
	    
	    String strCompleteMessage = objAdjunoLIMAForwarderReferencePOM.verifyCompleteMessage(strCompleteFromName, "UpdateMessage");
	    
	    objSoftAssert.assertEquals(strCompleteMessage.contains("1 shipment has been referenced. "), true,"***** Test Id:30 - String value"+strCompleteMessage+" is not contain this string \"1 shipment has been referenced. \"value " +"*****");
	    
	    System.out.println("complete 1 done");
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(strMessage), true,"***** Test Id:30 "+strMessage+ " *****");
	   	objSoftAssert.assertAll();
    }
 
    
    //verify 
    @Test(priority=105)
    public void test_31_GetReference_Num(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    wait(5000);
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.expandViewDetails(strCompleteFromName, "ViewDetail"),true,"***** Test Id: 31 -In Forwarder Reference Tool Under Complete chevron \"ViewDetails\" field is not clicked  *****");
		wait(3000);
		String poDetails = objAdjunoLIMAForwarderReferencePOM.getTxtViewDetail().getText();
		String[] vals = poDetails.split("Track Reference ");
	    
    	vals[1] = vals[1].replace("[", " ").replace("]", " ");
    	String valss[] = vals[1].split(" ",12);
    	
    	strTrackNO = valss[1];
    	System.out.println("track ref num: - " + strTrackNO);    
	    objSoftAssert.assertEquals(strTrackNO.equalsIgnoreCase(""), false,"***** Test Id:31- track Number is empty  *****");
	   	objSoftAssert.assertAll();
    }
    
    @Test(priority=110)
    public void test_32_AccessEdit() 
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
        String strMessage = "";
        objAdjunoLIMAForwarderReferencePOM.callMouseHover("",objAdjunoLIMAForwarderReferencePOM.getLnkTrack(),objAdjunoLIMAForwarderReferencePOM.getLnkEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getFldRefNo()), true,"***** Test ID : 32 - In Edit or Create a Track Page\"Reference\" Field is not Displayed *****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValueForWebElement(objAdjunoLIMAForwarderReferencePOM.getFldRefNo(),strTrackNO), true,"***** Test ID : 32 - In Edit or Create a Track Page\"Reference\" Field value is not set *****");
        }else{
        	strMessage ="***** "+strMessage + " Track number is empty ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getBtnTrackApply()), true,"***** Test ID : 32 - In Edit or Create a Track Page\"Apply button\" is not clicked *****");
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****Test id 32-"+strMessage+"*****");    
        objSoftAssert.assertAll();
    }
    
    @Test(priority=115)
    public void test_33_clickForwardReference() throws ParseException 
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
        String strMessage = "";
        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getTxtShipmentTrack()), true, "***** Test ID : 33 - In Edit or Create a Track Tool \"Shipment\" page is not loaded *****");
        long min = objAdjunoLIMAForwarderReferencePOM.getTrackValue(strUserName);
        if(min<=338)
        {
        	objAdjunoLIMAForwarderReferencePOM.getTracklnkForwarderReference().click();
            wait(3000);
            
            for (int i = 0; i <=objAdjunoLIMAForwarderReferencePOM.getTrackForwardReference().size()-1; i++) {
    			
            	if(objAdjunoLIMAForwarderReferencePOM.getTrackHouseBill().get(i).getText().equalsIgnoreCase(ref.getHouseBill()) && objAdjunoLIMAForwarderReferencePOM.getTrackForwardReference().get(i).getText().equalsIgnoreCase(strReference)){
            		break;
            	}else{
            		if(i==objAdjunoLIMAForwarderReferencePOM.getTrackForwardReference().size()-1){
            			strMessage = strMessage + " In Shipment page the Forwarder Reference record is not found ";
            		}
            	}
    		}         
        }
        else
        {
            strMessage = " In Shipment page the Forwarder Reference Record is not found in todays date";
        }
                 
       
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****Test id :33-"+strMessage+"*****");
        objSoftAssert.assertAll();
    	
    }
    
   @Test(priority=125)
   public void test_35_verifyPONumber()
   {    
	   SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      
       
       wait(3000);  
       objAdjunoLIMAForwarderReferencePOM.callMouseHover(strPOStatusFormName, objAdjunoLIMAForwarderReferencePOM.getLnkFind() ,objAdjunoLIMAForwarderReferencePOM.getLnkPOStatus());

       wait(2000); 
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strPOStatusFormName, "Param_16PO"),true,"***** Test ID : 35 - In PO Status Page\"PO Number\" Field is not cleared *****");
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strPOStatusFormName, "Param_17Item"), true,"***** Test ID : 35 - In PO Status Page\"Product Number\" Field is not cleared *****");
       wait(2000); 
       System.out.println(" strPoNumber"+ strPONumber);
       System.out.println(" product number "+strProduct);
      
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true,"***** Test ID : 35 - In PO Status Page\"PO Number\" Field value is not set *****");
       wait(2000);
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strProduct, strPOStatusFormName,"Param_17Item"),true,"***** Test ID : 35 - In PO Status Page\"Product Number\" Field value is not set *****");
       wait(2000);
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getRunButton()), true,"***** Test ID : 35 - In PO Status Page\"Run Button\" is not clicked *****");
       wait(2000); 
       
       objSoftAssert.assertAll();
}
   @Test(priority=130)
   public void test_36_checkStatus()
   {
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = "";
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
       wait(2000);
       List<WebElement> PONumber = objAdjunoLIMAForwarderReferencePOM.getLstPONumber();
       List<WebElement> status = objAdjunoLIMAForwarderReferencePOM.getLstStatus();
		for (int i = 0; i <= PONumber.size() -1;i++){
			if (strPONumber.equalsIgnoreCase(PONumber.get(i).getText())){
               
              //
               if(status.get(i).getText().equalsIgnoreCase("Shipped")){
            	   status.get(i).click();
            	   break;
               }else{
            	   if(i==PONumber.size()-1){
            		   strMessage = strMessage + " In PO Status Page\"PO Number\" Status is not matched as \"Shipped\"";
            	   }
            	   
               }
              
           }
       }
      
       objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :36 - "+strMessage+" *****");
       objSoftAssert.assertAll();
       
   }
   
   @Test(priority=135)
   public void test_37_verifyHyperLink()
   {
	   SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
       String strMessage="";
      
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink()), true, "***** Test ID : 37 - In Shipped Page\"PO Number Hyperlink\" Field is not found *****");
       if(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink())==true){
    	   String strhref = objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink().getAttribute("href");
           if(!isNullOrBlank(strhref))
           {
           
           }
           else
           {
            strMessage = "*****_37_"+strMessage + "In Shipped page \"Po Number\"field is not a hyperLink*****";
           }
            
       }else{
    	   strMessage = "*****_37_"+strMessage + "In Shipped page \"Po Number\"field is not a hyperLink*****";
       }
      
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink()), true, "***** Test ID : 37 - In Shipped Page\"Container Hyperlink\" Field is not found *****");
       
       if(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink())==true){
       String strhref1 = objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink().getAttribute("href");

       		if(!isNullOrBlank(strhref1))
       		{
       
       		}
       		else{
       			strMessage = "*****_37_"+strMessage + "In Shipped page \"Container\"field is not a hyperLink*****";
       		}
       }else{
  			strMessage = "*****_37_"+strMessage + "In Shipped page \"Container\"field is not a hyperLink*****";
  		}
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
       objSoftAssert.assertAll();  
   }
   
   @Test(priority=140)
   public void test_38_clickPONumberHyperLink(){
	   
	   SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink()),true, "***** Test ID : 38 - In Shipped Page\"PO Number Hyperlink\" Field is not found *****");
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickHyperLinkUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink()),true,"***** Test ID : 38 - In Shipped Page\"PO Number Hyperlink\" is not clicked *****");
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getClickApplyButton()),true,"***** Test ID : 38 - In Purchase Order Page\"Apply button\" is not Clicked *****");
      
       objSoftAssert.assertEquals(strPONumber.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getFieldValue(strViewFormNamePO, "OrderNumber")),true,"***** Test ID : 38 - In Purchase Order Page\""+ strPONumber +"\" Field value is not matching with \""+objAdjunoLIMAForwarderReferencePOM.getFieldValue(strViewFormNamePO, "OrderNumber")+"\" value *****");
       objSoftAssert.assertEquals(strProduct.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strViewFormNamePO, "GRID_ContractSummaryDetails", 0, "Product")),true,"***** Test ID : 38 - In Purchase Order Page\""+ strProduct +"\" Field value is not matching with \""+objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strViewFormNamePO, "GRID_ContractSummaryDetails", 0, "Product") +"\" value *****");
       objSoftAssert.assertAll();
   }
   
   @Test(priority=145)
   public void test_39_clickContainerNoHyperLink()
   {
	   SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
       
       driver.navigate().back();
       wait(2000);
       driver.navigate().back();
       wait(2000);
       List<WebElement> poValue = objAdjunoLIMAForwarderReferencePOM.getPONumberValue();
       List<WebElement> containerValue = objAdjunoLIMAForwarderReferencePOM.getPoContainerValue();
       for(int i=0;i<=poValue.size()-1;i++){
           if(poValue.get(i).getText().equalsIgnoreCase(strPONumber)){
        	   for(int j=0; j<=containerValue.size()-1;j++){
        		   if(containerValue.get(j).getText().equalsIgnoreCase(ref.getContainer())){
        			//   objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickHyperLinkUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink()),true,"*******test id-39: Container HyperLink is not clicked******");
        			   containerValue.get(j).click();
        		       objSoftAssert.assertEquals(strPONumber.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getShipmentStatusPONumber().getText()),true,"***** Test ID : 39 - In Shipment Status Page\""+ strPONumber +"\" PO Number Field value is not matching with PO Number\""+objAdjunoLIMAForwarderReferencePOM.getShipmentStatusPONumber().getText()+"\" value *****");
        		      
        		       System.out.println("2:"+objAdjunoLIMAForwarderReferencePOM.getShipmentStatusContainerValue().getText());
        		      //container number
        		       System.out.println("2:"+ref.getContainer());
        		       objSoftAssert.assertEquals(ref.getContainer().equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getShipmentStatusContainerValue().getText()),true,"***** Test ID : 39 - In Purchase Order Page\""+ ref.getContainer() +"\" Container Field value is not matching with Container\""+objAdjunoLIMAForwarderReferencePOM.getShipmentStatusContainerValue().getText() +"\" value *****");

        		       System.out.println("3:"+objAdjunoLIMAForwarderReferencePOM.getShipmentStatus_Status().getText());
        		       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getShipmentStatus_Status().getText().equals("Shipped"), true, "***** Test ID : 39 - In Purchase Order Page\""+ objAdjunoLIMAForwarderReferencePOM.getShipmentStatus_Status().getText() +"\" Status Field value is not matched as Status \"Shipped\"value *****");
        		      
        		   }
        	   }
           }

       }
       
       objSoftAssert.assertAll();
       
   }
   
  @Test(priority=150)
   public void test_40_verifyContainerNoWithAwaitingStatus()
   {
	   String strMessage ="";
	   SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
       wait(5000);    
       objAdjunoLIMAForwarderReferencePOM.callMouseHover(strFormNameSearchForwarderRef, objAdjunoLIMAForwarderReferencePOM.getLnkTools(),objAdjunoLIMAForwarderReferencePOM.getLnkForwarderReference());
       wait(5000);
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.verifyPageIsLoaded(strFormNameSearchForwarderRef), true, "***** Test ID : 40 - page of Forwarder Reference Tool is not loaded*****");
       clearField();
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef, "PARAM_Status"),true,"***** Test ID : 40 - In Forwarder Reference Tool under Search chevron \"Status\" Field value is not set *****");
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strContainerValue, strFormNameSearchForwarderRef, "PARAM_Container"),true,"***** Test ID : 40 - In Forwarder Reference Tool under Search chevron \"Container\" Field value is not set *****");
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strHouseBillValue, strFormNameSearchForwarderRef, "PARAM_HouseBL"), true, "***** Test ID : 40 - In Forwarder Reference Tool under Search chevron \"HouseBill\" Field value is not set *****");
       
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvSelect()), true,"***** Test ID : 40 - In Forwarder Reference Tool\"Select Chevron\" is not clicked *****");
       wait(3000);
       if(objAdjunoLIMAForwarderReferencePOM.getNoItem().isDisplayed()){
           objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getNoItem().getText().equalsIgnoreCase("No items were found."),true,"***** Test_id 40: Expected validation Message:\"No items were found.\",But it is Showing "+objAdjunoLIMAForwarderReferencePOM.getNoItem().getText() +"*****");

       }else{
    	   strMessage = strMessage +"***** Test ID : 40 - In Forwarder Reference Tool under select chevron search criteria Products are Found *****";
       }
       objSoftAssert.assertAll();
       
   }
  
  @Test(priority=155)
  public void test_41_verifyContainerNoWithReferenceStatus()
  {
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
     
      
      objAdjunoLIMAForwarderReferencePOM.callMouseHover(strFormNameSearchForwarderRef, objAdjunoLIMAForwarderReferencePOM.getLnkTools(),objAdjunoLIMAForwarderReferencePOM.getLnkForwarderReference());
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.verifyPageIsLoaded(strFormNameSearchForwarderRef), true, "***** Test ID : 41 - page of Forwarder Reference Tool is not loaded*****");
      wait(2000);
      clearField();      
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatusReferenced, strFormNameSearchForwarderRef, "PARAM_Status"),true,"***** Test ID : 41 - In Forwarder Reference Tool under Search chevron \"status\" Field value is not set *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strContainerValue, strFormNameSearchForwarderRef, "PARAM_Container"),true,"***** Test ID : 41 - In Forwarder Reference Tool under Search chevron \"Container\" Field value is not set *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvSelect()), true,"***** Test ID : 40 - In Forwarder Reference Tool \"Select chevron\" is not clicked *****");
      
      objSoftAssert.assertEquals(strContainerValue.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getTrContainer().get(0).getText()),true, "***** Test ID : 41 - In Forwarder Reference Tool Container number which was entered in under Search chevron is not same as the Container number found in Select chevron *******");
      objSoftAssert.assertEquals(strStatusReferenced.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getTrStatus().get(0).getText()),true,"***** Test ID : 41 - In Forwarder Reference Tool Status value which was entered in  under Search chevron  is not same as the status value found in Select chevron *******");
    
      objSoftAssert.assertAll();
     
  }
  
  @Test(priority=160)
  public void test_42_Click_ReferenceTab()
  {
	  SoftAssert objSoftAssert = new SoftAssert();
	  objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	  wait(1000);
	  referencedRef = new ForwarderReference(objAdjunoLIMAForwarderReferencePOM.getTrVessel().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrOriginPort().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrDestinationPort().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrETA().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrContainer().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrVendor().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrHouseBill().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrShipQty().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrShipCtns().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrStatus().get(0).getText(),objAdjunoLIMAForwarderReferencePOM.getForwarderRefValue().get(0).getText());

	  objAdjunoLIMAForwarderReferencePOM.getSelectBox().get(0).click();
	  wait(2000);
	   
	  ref = new ForwarderReference(objAdjunoLIMAForwarderReferencePOM.getTrVessel().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrOriginPort().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrDestinationPort().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrETA().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrContainer().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrVendor().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrHouseBill().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrShipQty().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrShipCtns().get(0).getText(), objAdjunoLIMAForwarderReferencePOM.getTrStatus().get(0).getText(),"");
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvReference2()), true,"***** Test Id : 42 - In Forwarder reference tool \"Reference chevron\" is not Clicked *****");
	  wait(2000);
	   
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getReferenceTitle().getText(), "Reference","***** Test Id : 42 -In forwarder Reference Tool \"Reference chevron\" page is not Loaded  *****");
	  objSoftAssert.assertAll();
  }
  
  @Test(priority=165)
  public void test_43_verifyGridsInReferenceChevron()
  {  
	  SoftAssert objSoftAssert = new SoftAssert();
	  objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesElementExist(strFormNameForwarderRef, "GridForwarder"), true," ***** Test Id : 43 - In Forwarder Reference Tool under Reference Chevron\" GridForwarder\" is not found *****");
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkDoesElementExist(strFormNameForwarderRef, "GridProducts"), true," ***** Test Id : 43 - In Forwarder Reference Tool under Reference Chevron\" GridProducts\" is not found *****");
	   
	  objSoftAssert.assertAll();
	 
	 
  } 
  @Test(priority=170)
  public void test_44_verifyGridForwarder()
  {
	  SoftAssert objSoftAssert = new SoftAssert();
	  objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	  ref2 = new ForwarderReference(objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "Vessel"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "OriginPort"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "DestinationPort"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "ETA"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridForwarder", 0, "Container"), "", objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "HouseBill"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "Quantity"), objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "Cartons"), "","");
	    
	    
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "Vessel"),false,"***** Test Id : 44 - In Forwarder Reference Tool Under Reference chevron \"Vessel\" field is not Read Only *****");
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "OriginPort"),false,"***** Test Id : 44 - In Forwarder Reference Tool Under Reference chevron \"Vessel\" field is not Read Only *****");
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "DestinationPort"),false,"***** Test Id : 44 - In Forwarder Reference Tool Under Reference chevron \"DestinationPort\" field is not Read Only *****");
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "ETA"),false,"***** Test Id : 44 - In Forwarder Reference Tool Under Reference chevron \"ETA\" field is not Read Only *****");
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridForwarder", 0, "Container"),false,"***** Test Id : 44 - In Forwarder Reference Tool Under Reference chevron \"Container\" field is not Read Only *****");
	    
	  objSoftAssert.assertEquals(ref.getContainer().equalsIgnoreCase(ref2.getContainer()),true,"***** Test ID : 44 - In Forwarder Reference Tool the selected Container value under Select Chevron does not match with Container Port value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    
	  System.out.println("one:"+ref.getEta()+"--"+ "two:"+ref2.getEta());
	  objSoftAssert.assertEquals(ref.getDestinationPort().equalsIgnoreCase(ref2.getDestinationPort()),true,"***** Test ID : 44 - In Forwarder Reference Tool the selected DestinationPort value under Select Chevron does not match with Destination Port value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	  objSoftAssert.assertEquals(ref.getOriginPort().equalsIgnoreCase(ref2.getOriginPort()),true,"***** Test ID : 44 - In Forwarder Reference Tool the selected OriginPort value under Select Chevron does not match with OriginPort value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	  objSoftAssert.assertEquals(ref.getVessel().equalsIgnoreCase(ref2.getVessel()),true,"***** Test ID : 44 - In Forwarder Reference Tool the selected vessel value under Select Chevron does not match with vessel value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	  objSoftAssert.assertEquals(ref.getEta().equalsIgnoreCase(ref2.getEta()),true,"***** Test ID : 44 - In Forwarder Reference Tool the selected ETA value under Select Chevron does not match with ETA value displayed in Reference Chevron under GirdForwarder Details Grid *****");
	    
	  objSoftAssert.assertAll();
  }  
  
  
  @Test(priority=175)
  public void test_50_checkForward()
  {
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      System.out.println("forwareder reference value : "+objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference"));
      wait(2000);
      System.out.println(referencedRef.getRefValue());
      objSoftAssert.assertEquals(referencedRef.getRefValue().equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference")),true,"***** Test ID : 50 - Expected value is :"+referencedRef.getRefValue() +"But Actival value is : "+ objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference")+ "*****");
      wait(2000);
      objSoftAssert.assertAll(); 
  }
  
  
  
  @Test(priority=180)
  public void test_45_VerifyForwardReferenceMandatory() {
	  SoftAssert objSoftAssert = new SoftAssert();
	  String strMessage = "";
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.checkFieldIsReadOnlyInGrid(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference"),true,"***** Test Id:45 - In Forwarder Reference Tool Under Reference chevron \"ForwarderReference\" field is Read Only *****");
	  
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearGridInputField(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference"),true,"***** Test Id:45 - In Forwarder Reference Tool Under Reference chevron \"ForwarderReference\" field is not cleared *****");
	  wait(1000);
	  String strErrorForwarderReference = objAdjunoLIMAForwarderReferencePOM.getValidationMessageGridElement(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference");
	   	if(!strErrorForwarderReference.equals("")){
	   		if(strErrorForwarderReference.contains("&#39;")){
	   		strErrorForwarderReference = strErrorForwarderReference.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorForwarderReference.equalsIgnoreCase("> 'Forwarder Reference' is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:45 - In Forwarder Reference Tool Under Reference Chevron \"Forwarder Reference \" Field is not Mandatory *****");	   			
	   	}else{
	   		strMessage = strMessage + "In Forwarder Reference Tool Under Reference Chevron \"Forwarder Reference \" Field Mandatory message is not displayed*****";
	   	}
	   	
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id:45 -"+strMessage+" *****");
	    objSoftAssert.assertAll();
  }
  
 
  @Test(priority=185)
  public void test_48_ClickPOHyperLink()
  {
	  SoftAssert objSoftAssert = new SoftAssert();
	  String strMessage = "";
	  objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
	    
	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickHyperLink(strFormNameForwarderRef, "GridProducts", 0, "PODetail"),true,"***** Test Id :48 - In Forwarder Reference tool Under Reference Chevron \"PO Details\" Hyperlink is not clicked *****");
	    
	  strPONumber = objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "POGrid", 0, "OrderNumber");
	  System.out.println("po number:"+strPONumber);
	  strProduct= objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strFormNameForwarderRef, "POGrid", 0, "Product");
	  System.out.println("product number :"+strProduct);
	  ArrayList<String> list = new ArrayList<String>();
		
	  list = objAdjunoLIMAForwarderReferencePOM.getCaptionsList(strFormNameForwarderRef,"POGrid");
		    
	  strMessage = objAdjunoLIMAForwarderReferencePOM.verifyCaptionsONGrid(list,poCaptions,7);
			
	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"**** Test Id :48 - In Forwarder Reference tool Under Reference Chevron PO Details Page"+strMessage+" *****");
	  objSoftAssert.assertAll();
  }

 
  
  
  @Test(priority=190)
  public void test_51_EditForwardReferenceValue()
  {
	  String strMessage="";
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
       
      	
      	strEditForwardRef = objAdjunoLIMAForwarderReferencePOM.generateRandomString();
	    objAdjunoLIMAForwarderReferencePOM.setFieldValueForGridCell(strFormNameForwarderRef, "GridProducts", 0, "ForwarderReference",strEditForwardRef);
	  
	    
	    objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvComplete2()),true,"***** Test ID : 51 - In Forwarder Reference Tool\"Complete Chevron\" is not clicked *****");
	    wait(5000);
	    
	    String strCompleteMessage = objAdjunoLIMAForwarderReferencePOM.verifyCompleteMessage(strCompleteFromName, "UpdateMessage");
	    objSoftAssert.assertEquals(strCompleteMessage.equalsIgnoreCase("1 shipment has been referenced. "), true," ***** Test ID : 51 - Expected value is : \"1 shipment has been referenced. \",But actual value is :"+strCompleteMessage+" *****");
	    
	    System.out.println("complete 2 done");
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(strMessage), true,"***** Test Id:51"+strMessage+ " *****");
	   	objSoftAssert.assertAll();
                 
  }
  
  @Test(priority=195)
  public void test_52_GetReference_Num()
  {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.expandViewDetails(strCompleteFromName, "ViewDetail"),true,"***** Test Id : 52 - In Forwarder Reference Tool under Complete chevron \"view Details\" button is not clicked *****");
		wait(3000);
		String poDetails = objAdjunoLIMAForwarderReferencePOM.getTxtViewDetail().getText();
		String[] vals = poDetails.split("Track Reference ");

		vals[1] = vals[1].replace("[", " ").replace("]", " ");
		String valss[] = vals[1].split(" ", 12);

		strTrackNO = valss[1];
		System.out.println("track ref num2: - " + strTrackNO);
		objSoftAssert.assertEquals(strTrackNO.equalsIgnoreCase(""), false,"***** Test Id : 52 - In Forwarder Reference Tool Under complete chevron \"Track No \" is not found *****");
		objSoftAssert.assertAll();
  }
  
  
  @Test(priority=200)
  public void test_53_AccessEdit() 
 {
		wait(1000);
		SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
        String strMessage = "";
        objAdjunoLIMAForwarderReferencePOM.callMouseHover("",objAdjunoLIMAForwarderReferencePOM.getLnkTrack(),objAdjunoLIMAForwarderReferencePOM.getLnkEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getFldRefNo()), true,"***** Test ID : 53 - In Edit or Create a Track Page\"Reference\" Field is not Displayed *****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValueForWebElement(objAdjunoLIMAForwarderReferencePOM.getFldRefNo(),strTrackNO), true,"***** Test ID : 53 - In Edit or Create a Track Page\"Reference\" Field value is not set *****");
        }else{
        	strMessage ="***** "+strMessage + " Track number is empty ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getBtnTrackApply()), true,"***** Test ID : 53 - In Edit or Create a Track Page\"Apply button\" is not clicked *****");
        
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****Test id 53-"+strMessage+"*****");    
        objSoftAssert.assertAll();
  }
  
  @Test(priority=205)
  public void test_54_clickForwardReference() throws ParseException 
  {
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      String strMessage = "";
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getTxtShipmentTrack()), true, "***** Test ID : 54 - In Edit or Create a Track Tool \"Shipment\" page is not loaded *****");
      long min = objAdjunoLIMAForwarderReferencePOM.getTrackValue(strUserName);
      if(min<=338)
      {
    	  objAdjunoLIMAForwarderReferencePOM.getTracklnkForwarderReference().click();
          wait(3000);
          
          for (int i = 0; i <=objAdjunoLIMAForwarderReferencePOM.getTrackForwardReference().size()-1; i++) {
    			
          	if(objAdjunoLIMAForwarderReferencePOM.getTrackHouseBill().get(i).getText().equalsIgnoreCase(referencedRef.getHouseBill()) && objAdjunoLIMAForwarderReferencePOM.getTrackForwardReference().get(i).getText().equalsIgnoreCase(strEditForwardRef)){
          		break;
          	}else{
          		if(i==objAdjunoLIMAForwarderReferencePOM.getTrackForwardReference().size()-1){
          			strMessage = strMessage + "In Shipment page the Forwarder Reference Record is not found ";
          		}
          	}
          }        
      }
      else
      {
          strMessage = "***** In Shipment page the Forwarder Reference Recorde is not found in todays date*****";
      }
               
     
      
      objSoftAssert.assertEquals(strMessage.equals(""), true,"*****Test id : 54 - "+strMessage+"*****");
      objSoftAssert.assertAll();
  }
  
  @Test(priority=210)
  public void test_57_verifyPONumber()
  {
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
     
      
      wait(3000);  
      objAdjunoLIMAForwarderReferencePOM.callMouseHover(strPOStatusFormName, objAdjunoLIMAForwarderReferencePOM.getLnkFind() ,objAdjunoLIMAForwarderReferencePOM.getLnkPOStatus());

      wait(2000); 
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strPOStatusFormName, "Param_16PO"),true,"***** Test ID : 57 - In PO Status Page\"PO Number\" Field is not cleared *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clearInputField(strPOStatusFormName, "Param_17Item"), true,"***** Test ID : 57 - In PO Status Page\"Product Number\" Field is not cleared *****");
      wait(2000); 
      System.out.println(" strPoNumber"+ strPONumber);
      System.out.println(" product number "+strProduct);
     
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true,"***** Test ID : 57 - In PO Status Page\"PO Number\" Field value is not set *****");
      wait(2000);
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strProduct, strPOStatusFormName,"Param_17Item"),true,"***** Test ID : 57 - In PO Status Page\"Product Number\" Field value is not set *****");
      wait(2000);
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getRunButton()), true,"***** Test ID : 57 - In PO Status Page\"Run Button\" is not clicked *****");
      wait(2000); 
      
      objSoftAssert.assertAll();
  }
  
  @Test(priority=215)
  public void test_58_checkStatus(){
	  SoftAssert objSoftAssert = new SoftAssert();
	  String strMessage = "";
	  objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      wait(2000);
      List<WebElement> PONumber = objAdjunoLIMAForwarderReferencePOM.getLstPONumber();
      List<WebElement> status = objAdjunoLIMAForwarderReferencePOM.getLstStatus();
      for (int i = 0; i <= PONumber.size() -1;i++){
    	  if (strPONumber.equalsIgnoreCase(PONumber.get(i).getText())){
    		  if(status.get(i).getText().equalsIgnoreCase("Shipped")){
    			  status.get(i).click();
    			  break;
    		  }else{
    			  if(i==PONumber.size()-1){
    				  strMessage = strMessage + " In PO Status Page\"PO Number\" Status is not matched as \"Shipped\"";
    			  }
           	   
    		  }
    	  }
      }
     
      objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :58 - "+strMessage+" *****");
      objSoftAssert.assertAll();
  }
  
  @Test(priority=220)
  public void test_59_verifyHyperLink()
  {
	   SoftAssert objSoftAssert = new SoftAssert();
       objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
       String strMessage="";
      
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink()), true, "***** Test ID : 37 - In Shipped Page\"PO Number Hyperlink\" Field is not found *****");
       if(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink())==true){
    	   String strhref = objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink().getAttribute("href");
           if(!isNullOrBlank(strhref))
           {
           
           }
           else
           {
            strMessage = "*****_37_"+strMessage + "In Shipped page \"Po Number\"field is not a hyperLink*****";
           }
            
       }else{
    	   strMessage = "*****_37_"+strMessage + "In Shipped page \"Po Number\"field is not a hyperLink*****";
       }
      
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
       objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink()), true, "***** Test ID : 37 - In Shipped Page\"Container Hyperlink\" Field is not found *****");
       
       if(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink())==true){
       String strhref1 = objAdjunoLIMAForwarderReferencePOM.getContainerHyperlink().getAttribute("href");

       		if(!isNullOrBlank(strhref1))
       		{
       
       		}
       		else{
       			strMessage = "*****_37_"+strMessage + "In Shipped page \"Container\"field is not a hyperLink*****";
       		}
       }else{
  			strMessage = "*****_37_"+strMessage + "In Shipped page \"Container\"field is not a hyperLink*****";
  		}
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
       objSoftAssert.assertAll(); 
  }
  
  @Test(priority=225)
  public void test_60_clickPONumberHyperLink(){
	 
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
     
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.isElementPresent(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink()),true, "***** Test ID : 60 - In Shipped Page\"PO Number Hyperlink\" Field is not found *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickHyperLinkUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getPONumberHyperLink()),true,"***** Test ID : 60 - In Shipped Page\"PO Number Hyperlink\" is not clicked *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickButtonUsingWebElement(objAdjunoLIMAForwarderReferencePOM.getClickApplyButton()),true,"***** Test ID : 60 - In Purchase Order Page\"Apply button\" is not Clicked *****");
     
      objSoftAssert.assertEquals(strPONumber.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getFieldValue(strViewFormNamePO, "OrderNumber")),true,"***** Test ID : 60 - In Purchase Order Page\""+ strPONumber +"\" Field value is not matching with \""+objAdjunoLIMAForwarderReferencePOM.getFieldValue(strViewFormNamePO, "OrderNumber")+"\" value *****");
      objSoftAssert.assertEquals(strProduct.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strViewFormNamePO, "GRID_ContractSummaryDetails", 0, "Product")),true,"***** Test ID : 60 - In Purchase Order Page\""+ strProduct +"\" Field value is not matching with \""+objAdjunoLIMAForwarderReferencePOM.getGridCellElementValue(strViewFormNamePO, "GRID_ContractSummaryDetails", 0, "Product") +"\" value *****");
      objSoftAssert.assertAll();
  }
  
  @Test(priority=230)
  public void test_61_clickContainerNoHyperLink(){
	  
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      
      driver.navigate().back();
      wait(2000);
      driver.navigate().back();
      wait(2000);
      List<WebElement> poValue = objAdjunoLIMAForwarderReferencePOM.getPONumberValue();
      List<WebElement> containerValue = objAdjunoLIMAForwarderReferencePOM.getPoContainerValue();
      for(int i=0;i<=poValue.size()-1;i++){
          if(poValue.get(i).getText().equalsIgnoreCase(strPONumber)){
        	  for(int j=0; j<=containerValue.size()-1;j++){
        		  if(containerValue.get(j).getText().equalsIgnoreCase(ref.getContainer())){
        			  containerValue.get(j).click();
        			  objSoftAssert.assertEquals(strPONumber.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getShipmentStatusPONumber().getText()),true,"***** Test ID : 61 - In Shipment Status Page\""+ strPONumber +"\" PO Number Field value is not matching with PO Number\""+objAdjunoLIMAForwarderReferencePOM.getShipmentStatusPONumber().getText()+"\" value *****");
       		      
        			  System.out.println("2:"+objAdjunoLIMAForwarderReferencePOM.getShipmentStatusContainerValue().getText());
       		       	  System.out.println("2:"+ref.getContainer());
       		       	  objSoftAssert.assertEquals(ref.getContainer().equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getShipmentStatusContainerValue().getText()),true,"***** Test ID : 61 - In Purchase Order Page\""+ ref.getContainer() +"\" Container Field value is not matching with Container\""+objAdjunoLIMAForwarderReferencePOM.getShipmentStatusContainerValue().getText() +"\" value *****");

       		       	  System.out.println("3:"+objAdjunoLIMAForwarderReferencePOM.getShipmentStatus_Status().getText());
       		       	  objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getShipmentStatus_Status().getText().equals("Shipped"), true, "***** Test ID : 61 - In Purchase Order Page\""+ objAdjunoLIMAForwarderReferencePOM.getShipmentStatus_Status().getText() +"\" Status Field value is not matched as Status \"Shipped\"value *****");
        		  }
        	  }
          }

      }
      
      objSoftAssert.assertAll();
  }
  
  @Test(priority=235)
  public void test_62_verifyContainerNoWithAwaitingStatus(){
	 
	  String strMessage ="";
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
      wait(2000);    
      objAdjunoLIMAForwarderReferencePOM.callMouseHover(strFormNameSearchForwarderRef, objAdjunoLIMAForwarderReferencePOM.getLnkTools(),objAdjunoLIMAForwarderReferencePOM.getLnkForwarderReference());
      wait(2000);
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.verifyPageIsLoaded(strFormNameSearchForwarderRef), true, "***** Test ID : 62 - page of Forwarder Reference Tool is not loaded*****");
      wait(2000);
      clearField();
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatus, strFormNameSearchForwarderRef, "PARAM_Status"),true,"***** Test ID : 62 - In Forwarder Reference Tool under Search chevron \"Status\" Field value is not set *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strContainerValue, strFormNameSearchForwarderRef, "PARAM_Container"),true,"***** Test ID : 62 - In Forwarder Reference Tool under Search chevron \"Container\" Field value is not set *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strHouseBillValue, strFormNameSearchForwarderRef, "PARAM_HouseBL"), true, "***** Test ID : 62 - In Forwarder Reference Tool under Search chevron \"HouseBill\" Field value is not set *****");
      
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvSelect()), true,"***** Test ID : 62 - In Forwarder Reference Tool\"Select Chevron\" is not clicked *****");
      wait(3000);
      if(objAdjunoLIMAForwarderReferencePOM.getNoItem().isDisplayed()){
          objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.getNoItem().getText().equalsIgnoreCase("No items were found."),true,"***** Test_id : 62 - Expected validation Message:\"No items were found.\",But it is Showing "+objAdjunoLIMAForwarderReferencePOM.getNoItem().getText() +"*****");

      }else{
   	   strMessage = strMessage +"***** Test ID : 62 - In Forwarder Reference Tool under select chevron search criteria Products are Found *****";
      }
      objSoftAssert.assertAll();
  }
  
  @Test(priority=240)
  public void test_63_verifyContainerNoWithReferenceStatus(){
	
	  SoftAssert objSoftAssert = new SoftAssert();
      objAdjunoLIMAForwarderReferencePOM = new AdjunoLIMAForwarderReferencePOM(driver);
     
      
      objAdjunoLIMAForwarderReferencePOM.callMouseHover(strFormNameSearchForwarderRef, objAdjunoLIMAForwarderReferencePOM.getLnkTools(),objAdjunoLIMAForwarderReferencePOM.getLnkForwarderReference());
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.verifyPageIsLoaded(strFormNameSearchForwarderRef), true, "***** Test ID : 63 - page of Forwarder Reference Tool is not loaded*****");
      wait(2000);
      clearField();      
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strStatusReferenced, strFormNameSearchForwarderRef, "PARAM_Status"),true,"***** Test ID : 63 - In Forwarder Reference Tool under Search chevron \"status\" Field value is not set *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.setFieldValue(strContainerValue, strFormNameSearchForwarderRef, "PARAM_Container"),true,"***** Test ID : 63 - In Forwarder Reference Tool under Search chevron \"Container\" Field value is not set *****");
      objSoftAssert.assertEquals(objAdjunoLIMAForwarderReferencePOM.clickChevorn(objAdjunoLIMAForwarderReferencePOM.getChvSelect()), true,"***** Test ID : 63 - In Forwarder Reference Tool \"Select chevron\" is not clicked *****");
      wait(5000);
      objSoftAssert.assertEquals(strContainerValue.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getTrContainer().get(0).getText()),true, "***** Test ID : 63 - In Forwarder Reference Tool Container number which was entered in under Search chevron is not same as the Container number found in Select chevron *******");
      objSoftAssert.assertEquals(strStatusReferenced.equalsIgnoreCase(objAdjunoLIMAForwarderReferencePOM.getTrStatus().get(0).getText()),true,"***** Test ID : 63 - In Forwarder Reference Tool Status value which was entered in  under Search chevron  is not same as the status value found in Select chevron *******");
    
      objSoftAssert.assertAll();
  }
  
  @AfterClass
  public void test_CloseBrowser(){
	  driver.close();
  }
  
}
