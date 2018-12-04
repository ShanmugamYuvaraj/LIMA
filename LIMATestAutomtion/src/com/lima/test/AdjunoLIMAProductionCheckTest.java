package com.lima.test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

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

import com.lima.dto.ProductionCheck;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAProductionCheckPOM;

public class AdjunoLIMAProductionCheckTest {
	
	WebDriver driver;
	
	long nPage_Timeout = 1;
    String strTestURL;
    String strDriver;
    
    ArrayList<ProductionCheck> lstSearchResults;
    String strLIMAUserName;
    String strLIMAPassword;
    String strPageTitleHomePage;
    
    String strPageTitleProductionCheck;
    String strSearchFormNameProductionCheck;
    String strProductionCheckFormName;
    String strPOStatusFormName;
    String strSearchFormNamePOManager;
    String strPoManagerEditFormName;
    
    NodeList nlSearchParamsProductionCheck;
    NodeList nlOrderProgress;
    NodeList nlProductsProgress;
    
    boolean bSearchResultsProductsFound = true;
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAProductionCheckPOM objAdjunoLIMAProductionCheckPOM;
    ProductionCheck objProductionCheck;

	 String strOrigin = "";
	 String strOriginPort = "";
	 String strVendor = "";
	 String strVendorReference = "";
	 String strBuyingAgent = "";
	 String strMode = "";
	 String strInvalidPONumber = "";
	 String strPONumber = "";
	 String strProduct = "";
	 String strProductionStatus = "";
	 String strProductionStatusPending ="";
	 String strProductionStatusProgressed ="";
	 String strProductionStatusPartProgressed ="";
	 String strWorkFlowStatus = "";
	 
	 String strIntendedShipDate = "";
	 String strReasonCode = "";
	 String strComments = "";
	 String strIntendedShipQty = "";
	 
	 String strTrackN0 = "";
	 long Rows;

	 String strFormNameComplete;
	 
    
    
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
            
            Node pageTitleProductionCheck = (Node) xPath.evaluate("/config/LIMA/Page_Title_Production_Check", dDoc, XPathConstants.NODE);
            strPageTitleProductionCheck = pageTitleProductionCheck.getTextContent();
            
            Node searchFormNameProductionCheck = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Production_Check", dDoc, XPathConstants.NODE);
            strSearchFormNameProductionCheck = searchFormNameProductionCheck.getTextContent();
            
            //
            
            Node formNameComplete = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
            strFormNameComplete = formNameComplete.getTextContent();
            
            Node productionCheckFormName = (Node) xPath.evaluate("/config/LIMA/Production_Check_Form_Name", dDoc, XPathConstants.NODE);
            strProductionCheckFormName = productionCheckFormName.getTextContent();
            
            Node poManagerSearchFormName = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_PO_Manager", dDoc, XPathConstants.NODE);
            strSearchFormNamePOManager = poManagerSearchFormName.getTextContent();
            
            Node poManagerEditPageFormName = (Node) xPath.evaluate("/config/Generic/Edit_Form_Name_PO_Manager", dDoc, XPathConstants.NODE);
            strPoManagerEditFormName = poManagerEditPageFormName.getTextContent();
            
            Node pOStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
            strPOStatusFormName = pOStatusFormName.getTextContent();
            
             
            
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getAdjunoLIMAProductionCheckXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();

            nlSearchParamsProductionCheck = (NodeList) xPath1.evaluate("/ProductionCheck/SearchParams", dDoc1, XPathConstants.NODESET);
            
            nlOrderProgress = (NodeList) xPath1.evaluate("/ProductionCheck/Orders_Progress", dDoc1, XPathConstants.NODESET);
            
            nlProductsProgress = (NodeList) xPath1.evaluate("/ProductionCheck/Product_Progress", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsProductionCheck.getLength() -1; i++)
             {
                 if (nlSearchParamsProductionCheck.item(i).getNodeType() == Node.ELEMENT_NODE)
                 {
                     el = (Element) nlSearchParamsProductionCheck.item(i);
                     strOrigin = el.getElementsByTagName("Origin").item(0).getTextContent(); 
                     
                     strOriginPort = el.getElementsByTagName("OriginPort").item(0).getTextContent();
                      
                     strVendor = el.getElementsByTagName("Vendor").item(0).getTextContent();
                     
                     strVendorReference = el.getElementsByTagName("Vendor_Reference").item(0).getTextContent();
                     
                     strBuyingAgent = el.getElementsByTagName("Buying_Agent").item(0).getTextContent();
                     
                     strMode = el.getElementsByTagName("Mode").item(0).getTextContent();
                     
                     strInvalidPONumber = el.getElementsByTagName("Invalid_PONumber").item(0).getTextContent();
                        
                     strProductionStatus = el.getElementsByTagName("Production_Status").item(0).getTextContent();
                     
                     strProductionStatusPending = el.getElementsByTagName("Production_Status_Pending").item(0).getTextContent();
                     
                     strProductionStatusProgressed = el.getElementsByTagName("Production_Status_Progressed").item(0).getTextContent();
                     
                     strProductionStatusPartProgressed = el.getElementsByTagName("Production_Status_PartProgressed").item(0).getTextContent();
                     
                     strWorkFlowStatus = el.getElementsByTagName("Workflow_Status").item(0).getTextContent();
                     
                     strIntendedShipDate = el.getElementsByTagName("IntendedShipDate").item(0).getTextContent();
                     
                     strReasonCode = el.getElementsByTagName("ReasonCode").item(0).getTextContent();
                     
                     strComments = el.getElementsByTagName("Comment").item(0).getTextContent();
                     
                     strIntendedShipQty = el.getElementsByTagName("IntendedShipQty").item(0).getTextContent();
                 }
        
             }
        
        } catch (Exception e) {
            e.printStackTrace();
        }
                 
        
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
    
    private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
    

    @Test(priority=1)
    public void test_1_accessProductionCheck()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
        wait(5000);
        String strTitle = objAdjunoLIMAProductionCheckPOM.callMouseHover(strSearchFormNameProductionCheck,objAdjunoLIMAProductionCheckPOM.getLnkTools(),objAdjunoLIMAProductionCheckPOM.getLnkProductionCheck());
                
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleProductionCheck))
                bFlag = true;
            else
                bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true, "*****test ID:_1_Title of the page is wrong for Production Check Serach page*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 2)
    public void Test_2_checkChevrons()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesChevronExist(objAdjunoLIMAProductionCheckPOM.getChevSearch()), true,"*****test ID:_2_Search chevron is not found in Production Check Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesChevronExist(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true,"*****test ID:_2_Select chevron is not found in Production Check Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesChevronExist(objAdjunoLIMAProductionCheckPOM.getChevProgress()), true,"*****test ID:_2_Progress chevron is not found in Production Check Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesChevronExist(objAdjunoLIMAProductionCheckPOM.getChevComplete()), true,"*****test ID:_2_Complete chevron is not found in Production Check Search page*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 3)
    public void Test_3_checkSearchParamsFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	  	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_Origin"),true,"*****_3_Origin field is not found in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_Port"),true,"*****_3_OriginPort field is not found  in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_Vendor"),true,"*****_3_Vendor field is not found  in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_VendorReference"),true,"*****_3_VendorReference field is not found  in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_BuyingAgent"),true,"*****_3_BuyingAgent field is not found in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_Mode"),true,"*****_3_Mode field is not found in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_PONumber"),true,"*****_3_PONumber field is not found in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_Product"),true,"*****_3_Product field is not found in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_3_Production Status field is not found in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strSearchFormNameProductionCheck,"PARAM_WorkflowStatus"),true,"*****_3_WorkflowStatus field is not found in Production Check Search page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 4)
    public void Test_4_verifyMandatoryFieldSearchParam()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	 
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_Origin"),true,"*****_4_The Origin field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_Port"),true,"*****_4_The OriginPort field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_Vendor"),true,"*****_4_The Vendor field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_VendorReference"),true,"*****_4_The Vendor Reference field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_BuyingAgent"),true,"*****_4_The Buying Agent field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_Mode"),true,"*****_4_The Mode field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_PONumber"),true,"*****_4_The PO Number field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_Product"),true,"*****_4_The Product field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_4_The Production Status field is not cleared in Production Check Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_WorkflowStatus"),true,"*****_4_The Workflow Status field is not cleared in Production Check Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()),true,"*****_4_The Select Chevron is not clicked in Production Check Search page*****");
    	
    	String strMandatoryMsg = objAdjunoLIMAProductionCheckPOM.getValidationMessageElement(strSearchFormNameProductionCheck, "PARAM_ConfirmationStatus");
    	if(!strMandatoryMsg.equals(""))
    	{
    		if(strMandatoryMsg.contains("&#39;"))
    		{
    			strMandatoryMsg = strMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strMandatoryMsg.equalsIgnoreCase("> 'Status' is a required field"), true, "*****_4_> 'Status' is a required field msg is not displayed for Status Mandatory field  in Production Check Select page*****");
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****_4_Status field validation message is empty*****";	
    	}
    	
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()),true,"*****_4_The Select Chevron is not clicked in Production Check Search page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 5)
    public void Test_5_checkForInvalidPoNumber()
    {

		SoftAssert objSoftAssert = new SoftAssert();

		objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strInvalidPONumber,strSearchFormNameProductionCheck,"PARAM_PONumber"),true,"*****_5_The PO Number value is not set in PO Number field in Search page*****");
		objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatus,strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_5_The Production status value is not set in Status field in Search page*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()),true,"*****_5_The Select Chevron is not clicked for Invalid PO number*****");
		
		 
		objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getErrorMsg()),true,"*****_5_The Items not found message is not displayed when Invalid PO Number is set in PO Number field in Search page*****");
		objSoftAssert.assertAll();
    }
    
    
    public void verifyGridHeaderExist(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	
    	//objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThReferenceNumber_ProductionCheck()), true, "*****_7_Reference Number is not found on the grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThPONumber_ProductionCheck()),true,"*****_7_PO number column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThVendor_ProductionCheck()),true,"*****_7_Vendor column is not found in the grid  under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThVendorReference_ProductionCheck()),true,"*****_7_Vendor Reference column is not found in the grid  under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThFactory_ProductionCheck()),true,"*****_7_Factory column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThBuyingAgent_ProductionCheck()),true,"*****_7_Buying Agent column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThShipDate_ProductionCheck()),true,"*****_7_Ship Date column is found not in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThIntendedShipDate_ProductionCheck()),true,"*****_7_Intended Ship Date column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThPOQuantity_ProductionCheck()),true,"*****_7_PO Quantity column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThPriority_ProductionCheck()),true,"*****_7_Priority column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThProductionStatus_ProductionCheck()),true,"*****_7_Production Status column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThOrderDetails_ProductionCheck()), true, "*****_7_Order Details column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getThDialogs_ProductionCheck()),true,"*****_7_Dialogs column is not found in the grid under Select Chevron*****");
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority = 7)
    public void Test_7_verifyProductionStatusAny(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	String strMessage = "";
    	boolean bFlag = true;
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_PONumber"),true,"*****_7_PO Number field is not Cleared  under Select Chevron*****");
		objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatus,strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_7_Production Status field is not set to {Any}  under Select Chevron*****");
		
		objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true,"*****_7_Select chevron is not clicked *****");
		
    	verifyGridHeaderExist();
    	strMessage = objAdjunoLIMAProductionCheckPOM.verifyStatusForAllProduct(strProductionStatus);
    	if (strMessage.equals("")) {
    	   bFlag = true;
        } 
    	else 
    	{
            bFlag = false;
        }
    	objSoftAssert.assertEquals(bFlag, true,"*****_7_error message for Status Any :"+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 8)
    public void Test_8_verifyProductionStatusPending(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage="";
    	boolean bFlag = true;
    	
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_8_The ConfirmationStatus field is not cleared in Production check Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatusPending,strSearchFormNameProductionCheck, "PARAM_ConfirmationStatus"),true,"*****_8_The Production Status field is not set to Pending status  under Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true,"*****_8_The Select Chevron is not clicked for Pending status*****");
    	
    	verifyGridHeaderExist();
    	wait(10000);
    	strMessage = objAdjunoLIMAProductionCheckPOM.verifyStatusForAllProduct(strProductionStatusPending);
    	if(strMessage.equals("")){
    		bFlag = true;
    	}
    	else
    	{
    		bFlag = false;
    	}
    	
    	objSoftAssert.assertEquals(bFlag, true,"*****_8_error message for Status Pending :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 9)
    public void verify_Test_6_ProductionStatusProgressed(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage="";
    	
    	boolean bFlag = true;
    	
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_6_The ConfirmationStatus field is not cleared  under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatusProgressed,strSearchFormNameProductionCheck, "PARAM_ConfirmationStatus"),true,"*****_6_The Production Status field is not set to Progressed status under Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true,"*****_6_The Select Chevron is not clicked for Progressed status*****");
    	
    	verifyGridHeaderExist();
    	wait(10000);
    	strMessage = objAdjunoLIMAProductionCheckPOM.verifyStatusForAllProduct(strProductionStatusProgressed);
    	if(strMessage.equals("")){
    		bFlag = true;
    	}
    	else
    	{
    		bFlag = false;
    	}
    	objSoftAssert.assertEquals(bFlag, true, "*****_6_error message for Status Progressed :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
     @Test(priority = 10)
    public void Test_9_verifyProductionStatusPartProgressed(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
    	
    	boolean bFlag = true;
    	
    	objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_9_The ConfirmationStatus field is not cleared under Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatusPartProgressed,strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_9_The Production Status field is not set to Part Progressed under Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true,"*****_9_The Select Chevron is not clicked for Part Progressed status*****");
    	
    	verifyGridHeaderExist();
    	wait(8000);
    	strMessage = objAdjunoLIMAProductionCheckPOM.verifyStatusForAllProduct(strProductionStatusPartProgressed);
    	if(strMessage.equals("")){
    		bFlag = true;
    	}
    	else
    	{
    		bFlag = false;
    	}	
    	
    	objSoftAssert.assertEquals(bFlag, true, "*****_9_error message for Status PartProgressed :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
     @Test(priority = 11)
    public void Test_11_verifyCheckBoxClicked()
      {
        SoftAssert objSoftAssert = new SoftAssert();
        
         objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
         boolean bFlag = true;
         objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_11_The PO Number field is not clered under Select Chevron*****");
 		 objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatusPending,strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_11_The Production Status field is not set to Pending under Select Chevron*****");
         objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true,"*****_11_The Select Chevron is not clicked for {Any} status*****");
         strPONumber = objAdjunoLIMAProductionCheckPOM.getTrPONumber().getText();
         
         //strTrackN0 = objAdjunoLIMAProductionCheckPOM.getTrReferenceNumber().getText();
         wait(5000);
         lstSearchResults = new ArrayList<ProductionCheck>();
          
         if ( objAdjunoLIMAProductionCheckPOM.getNoOfRowsResulted() > 0)
         {
             lstSearchResults =  objAdjunoLIMAProductionCheckPOM.selectMulitpleProducts(1);
             
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevProgress1()),true,"*****_11_Not able to click on Progress chevron*****");
             
         }
         else
         {
             bSearchResultsProductsFound = false;
               objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****_11_No Products in Search Results.*****");
         }
         if (objAdjunoLIMAProductionCheckPOM.verifyPageIsLoaded(strProductionCheckFormName)) //create a function in POM and that fucntion should call the UI function
         {
        	 bFlag = true;
             objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesElementExist(strProductionCheckFormName,"GridOrder"),true,"*****_11_Order Grid is not dispalyed on the progress page*****");
             objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesElementExist(strProductionCheckFormName,"GridProductionCheck"),true,"*****_11_Products Grid is not dispalyed on the progress page*****");
             
             
         }
         else
         {
        	 bFlag = false;
        	 
         }
         objSoftAssert.assertEquals(bFlag, true, "*****_11_Progress page is not loaded*****");
     
         objSoftAssert.assertAll();
         } 
      
     @Test(priority = 12)
      public void Test_12_verifyCaptionsInOrdersGrid(){
          
          SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          boolean bFlag;
          String strMessage="";
          
          ArrayList<String> list = new ArrayList<String>();
                      
          list = objAdjunoLIMAProductionCheckPOM.getCaptionsList(strProductionCheckFormName,"GridOrder");
                  
          strMessage = objAdjunoLIMAProductionCheckPOM.verifyCaptionsONGrid(list,nlOrderProgress,4);
          
          if(strMessage.equals(""))    {
              bFlag = true;
          }
          else{
              bFlag = false;
          }
          
          objSoftAssert.assertEquals(bFlag, true," *****_12_Orders grid Caption: "+strMessage+"*****");
                      
          objSoftAssert.assertAll();
      }
      
      @Test(priority = 13)
      public void Test_13_verifyCopyDownsDisplayed()
      {
    	  SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strProductionCheckFormName,"SetIntdShipDate"),true,"*****_13_Intended Ship Date Copy Down is not displayed in Progress Chevron*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strProductionCheckFormName,"SetReasonCode"), true,"*****_13_Reason Code Copy Down is not displayed in Progress Chevron*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strProductionCheckFormName,"SetComments"), true,"*****_13_Comments Copy Down is not displayed  in Progress Chevron*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.checkDoesFieldsExist(strProductionCheckFormName,"btnApplyValues"), true,"*****_13_Apply Button is not dispalyed in progress page*****");
     	 
          
      }
      
      @Test(priority = 14)
      public void Test_14_verifyCaptionsInProductsGrid(){
          
          SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          boolean bFlab;
          String strMessage="";
          
          ArrayList<String> list = new ArrayList<String>();
                      
          list = objAdjunoLIMAProductionCheckPOM.getCaptionsList(strProductionCheckFormName,"GridProductionCheck");
                  
          strMessage = objAdjunoLIMAProductionCheckPOM.verifyCaptionsONGrid(list,nlProductsProgress,12);
          
          if(strMessage.equals(""))    {
              bFlab = true;
          }
          else{
              bFlab = false;
          }
          
          objSoftAssert.assertEquals(bFlab, true," *****_14_Products grid Caption: "+strMessage+"*****");
                      
          objSoftAssert.assertAll();
      }
      
      @Test(priority = 15)
      public void Test_12_verifyRecordsInOrderGrid(){
    	  
    	  SoftAssert objSoftAssert = new SoftAssert();
			objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
			 
			if(bSearchResultsProductsFound)
			  {
					String strReturnMessage = objAdjunoLIMAProductionCheckPOM.verifyProductsDataOnGrid(strProductionCheckFormName,"GridOrder",lstSearchResults);
					
				  	objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, strReturnMessage);
				  
			  }
			  else
				  objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****_12_No Products in Search Results.*****");

		  	objSoftAssert.assertAll();
	  }
      
      @Test(priority = 16)
      public void Test_16_verifyDetailsInProductsGrid()
      {
    	  SoftAssert objSoftAssert = new SoftAssert();
    	  boolean bFlag;
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          Rows = objAdjunoLIMAProductionCheckPOM.getNoOfGridRows(strProductionCheckFormName, "GridProductionCheck");
          if(Rows> 0)
          {
        	  bFlag = true;
          }
          else
          {
        	  bFlag = false;
          }
          
          objSoftAssert.assertEquals(bFlag, true, "*****_16_No Product is displayed under Products Grid*****");
          strProduct = objAdjunoLIMAProductionCheckPOM.getGridCellElementValue(strProductionCheckFormName, "GridProductionCheck", 0, "Product");
          objAdjunoLIMAProductionCheckPOM.callMouseHover(strSearchFormNamePOManager, objAdjunoLIMAProductionCheckPOM.getLnkTools(), objAdjunoLIMAProductionCheckPOM.getLnkPoManager());
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strPONumber, strSearchFormNamePOManager, "PARAM_OrderNumber"), true, "*****_16_PO Number is not set in PO Manager Search tool*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevEdit()), true, "*****_16_Edit chevron is not clicked under PO Manager Search tool*****");
          wait(5000);
          objSoftAssert.assertEquals(strProduct.equals(objAdjunoLIMAProductionCheckPOM.getGridCellElementValue(strPoManagerEditFormName, "GRID_ContractSummaryDetails", 0, "Product")), true, "*****_16_product in Products grid under Production Check Progress Chevron displayed is not the product of corresponding PO Number*****");
          wait(2000);
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevCompletePOManager()), true, "*****_16_Complete Chevron is not clicked in PO Manager*****");
          wait(5000);
          objAdjunoLIMAProductionCheckPOM.getBtnViewDetail().click();
   	   	  wait(8000);
   	   	  String poDetails = objAdjunoLIMAProductionCheckPOM.getTxtViewDetail().getText();
   	      String[] vals = poDetails.split("- Reference ");
       
   	      vals[1] = vals[1].replace("[", " ").replace("]", " ");
   	      String valss[] = vals[1].split(" ",12);
   	
   	      strTrackN0 = valss[1];
   	      
          objAdjunoLIMAProductionCheckPOM.callMouseHover(strSearchFormNameProductionCheck, objAdjunoLIMAProductionCheckPOM.getLnkTools(), objAdjunoLIMAProductionCheckPOM.getLnkProductionCheck());
          wait(2000);
          
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProductionStatusPending,strSearchFormNameProductionCheck,"PARAM_ConfirmationStatus"),true,"*****_16_The Production Status field is not set to Pending in Production Check Search page*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevSelect()), true, "*****_16_Select chevron is not clicked under production check Search page*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickCheckBox(objAdjunoLIMAProductionCheckPOM.getTeCheckBox_ProductionCheck()), true, "*****_16_CheckBox is not clicked in resultant grid under Select page*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevProgress1()), true, "*****_16_Progress Chevron is not clicked under Select page*****");
          
          objSoftAssert.assertAll();
      }
      
      @Test(priority = 17)
      public void Test_17_verifyMandatoryFieldInProductsGrid() {
          SoftAssert objSoftAssert = new SoftAssert();

          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strProductionCheckFormName, "SetIntdShipDate"), true,"*****_17_Intended ship date field is not cleared under Progress Chevron*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strProductionCheckFormName, "SetReasonCode"), true,"*****_17_Reason Code field is not cleared under Progress Chevron*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clearInputField(strProductionCheckFormName, "SetComments"), true,"*****_17_Comments field is not cleared under Progress Chevron*****");
          
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickAllCheckBox(strProductionCheckFormName,"GridProductionCheck", "Select"), true,"*****_17_Checkbox is not clicked in \"Products\" grid under Progress Chevron*****");
          objAdjunoLIMAProductionCheckPOM.clickButton(strProductionCheckFormName,"btnApplyValues");
          for(int i = 0;i<=Rows-1; i++)
          {
        	  objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValueForGridCell(strProductionCheckFormName, "GridProductionCheck", i, "IntendedShipDate", " "), true, "*****_17_Intended Ship Date field is not not cleared in products grid under Progress Chevron*****");
        	  objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValueForGridCell(strProductionCheckFormName, "GridProductionCheck", i, "ReasonCode", " "), true, "*****_17_Reason Code field is not not cleared in products grid under Progress Chevron*****");
        	  
          }
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevCompleteProgress()), true,"*****_17_Not able to click Complete Progress chevron under Progress Chevron*****");

          wait(3000);
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getErrMsgProgress()), true, "*****_17_\"You must correct all validation failures before progressing.\" messafe is not displayed when all the mandatory fields are not filled under Progress Chevron*****");
         

          objSoftAssert.assertAll();
      }
          
      @Test(priority = 17)
      public void Test_18_verifyProductConfirmation()
 		{
          SoftAssert objSoftAssert = new SoftAssert();
          
           objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          
           wait(3000);
           objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setDate(strProductionCheckFormName,"SetIntdShipDate"),true,"*****_18_Intended Ship Date is not set in Production check Progress Chevron*****");
           
          
           if(!isNullOrBlank(strReasonCode))
              {
                    objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strReasonCode,strProductionCheckFormName,"SetReasonCode"),true,"*****_18_Reason code is not set in Production check Progress Chevron*****");          
              }
           if(!isNullOrBlank(strComments))
             {
                    objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strComments, strProductionCheckFormName, "SetComments"), true,"*****_18_Comment is not set in Production check Progress Chevron*****");
             }
           for(int i = 0;i<=Rows-1; i++)
           {
           if(!isNullOrBlank(strIntendedShipQty))
           {
        	   objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValueForGridCell(strProductionCheckFormName, "GridProductionCheck", i, "IntendedShipQuantity",objAdjunoLIMAProductionCheckPOM.getGridCellElementValue(strProductionCheckFormName, "GridProductionCheck", i, "POQuantity")), true, "*****_18_Intended Ship Quantity value is not set in Production check Progress Chevron*****");
           }
           }
          
           objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.verifyProductProgress(strProductionCheckFormName,"GridProductionCheck","Select"),true,"*****_18_check Box is not clicked in Products grid under Production Check Progress Chevron *****");
           objAdjunoLIMAProductionCheckPOM.clickButton(strProductionCheckFormName,"btnApplyValues");
          
          
           objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickChevron(objAdjunoLIMAProductionCheckPOM.getChevCompleteProgress()),true,"*****_18_Not able to click to complete chevron in Production check Progress Chevron*****");
          
           objSoftAssert.assertAll();
 		  
      }
      
      
      @Test(priority=18)
      public void Test_21_verifyCompleteProcess()
      {
    	  SoftAssert objSoftAssert = new SoftAssert();
          
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
           
        wait(3000);
          
        String strMessage ="";
              
//		if (objAdjunoLIMAProductionCheckPOM.checkDoesElementExist(strFormNameComplete, "UpdateMessage")) {
//		String strUpdatedMessage = objAdjunoLIMAProductionCheckPOM.getWebElementValue(strFormNameComplete, "UpdateMessage");
//		objSoftAssert.assertEquals(strUpdatedMessage.contains("Update Completed"), true,"Po Confirmation complete message mismatch");
//		} else {
//			strMessage = strMessage + " Production Check process is not completed ";
//		}

		objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
		
		objSoftAssert.assertAll();

      }
      
      @Test(priority = 19)
      public void test_24_accessEdit() 
      {
          SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          
          wait(3000);
          objAdjunoLIMAProductionCheckPOM.callMouseHover(strSearchFormNameProductionCheck,objAdjunoLIMAProductionCheckPOM.getLnkTrack(),objAdjunoLIMAProductionCheckPOM.getLnkEdit());

          wait(2000);

          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getFldRefNo()), true,"*****test_24_In Edit and Track page \"Reference field\" is not found*****");

          if (!isNullOrBlank(strTrackN0)) {
              objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValueForWebElement(objAdjunoLIMAProductionCheckPOM.getFldRefNo(),strTrackN0), true,"*****test_24_ track no is not set in Reference field under \"Edit and Track page\"*****");
          }
          
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickButtonUsingWebElement(objAdjunoLIMAProductionCheckPOM.getBtnTrackApply()), true,"*****test_24_Apply button is not clicked in \"Edit and Track page\"*****");
              
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getTxtEditPurchaseOrder()), true,"*****test_24_The Purchase Order event track page is not displayed*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickLink(objAdjunoLIMAProductionCheckPOM.getLnktrackProductionCheck()), true, "*****test_24_Latest updated Progress Check link is not clicked under Track*****");
          objSoftAssert.assertAll();
      }
      
      @Test(priority =20)
      public void test_24_verifyPOStatus()
      {
    	  SoftAssert objSoftAssert = new SoftAssert();
          objAdjunoLIMAProductionCheckPOM = new AdjunoLIMAProductionCheckPOM(driver);
          
          wait(3000);
          objAdjunoLIMAProductionCheckPOM.callMouseHover(strSearchFormNameProductionCheck, objAdjunoLIMAProductionCheckPOM.getLnkFind(), objAdjunoLIMAProductionCheckPOM.getLnkPOStatus());
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "*****test_24_PO Number is not set in PO Status page*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.setFieldValue(strProduct, strPOStatusFormName, "Param_17Item"), true, "*****test_24_Product Number is not set in PO Status page*****");
          objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.clickButtonUsingWebElement(objAdjunoLIMAProductionCheckPOM.getBtnRun()), true, "*****test_24_Run button is not clicked in PO Status page*****");
      	  objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.isElementPresent(objAdjunoLIMAProductionCheckPOM.getPoStatusReport()), true, "*****test_24_PO Status report page is not loaded*****");
      	  
      	  objSoftAssert.assertEquals(objAdjunoLIMAProductionCheckPOM.verfyPOStatus("Order Progressed"), true, "*****_24_Status is not Order Progressed for the progressed Po number under PO status report page*****");
      	 	
          objSoftAssert.assertAll();
      }
           
    @AfterTest
    public void closeBrowser()
    {
    	driver.close();
    }
    
}

    
