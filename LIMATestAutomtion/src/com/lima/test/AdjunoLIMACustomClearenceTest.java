package com.lima.test;

import java.text.ParseException;
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

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMACustomClearencePOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMACustomClearenceTest {
	
	WebDriver driver;
	Long nPage_Timeout;
	String strTestURL;
	String strLIMAUserName;
	String strLIMAPassword;
	NodeList nlSearchParamsCustomerClearence;
	NodeList n1VesselGrid;
	NodeList n1HouseBillGrid;
	
	String strSearchFormNameCustomerClearence;
	String strPageTitleCustomerClearence;
	String strCustomClearenceFormName;
	String strCompletePageFormName;
	String strPageTitleDCBooking;
	
	String strInvalidContainerNo = "";
	String strContainerNo = "";
	String strAwaitingStatus = "";
	String strEPU = "";
	String strUCR = "";
	
	String strTrackNO = "";
	String strEntryNo = "";
	String strEntryDate = "";
	String strClearenceDate = "";
	
	AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMACustomClearencePOM objAdjunoLIMACustomClearencePOM;
    
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
            
            Node SearchFormNameCustomerClearence = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Customs_Clearence", dDoc, XPathConstants.NODE);
            strSearchFormNameCustomerClearence = SearchFormNameCustomerClearence.getTextContent();
            
            Node PageTitleCustomerClearence = (Node) xPath.evaluate("/config/LIMA/Page_Title_Customs_Clearence", dDoc, XPathConstants.NODE);
            strPageTitleCustomerClearence = PageTitleCustomerClearence.getTextContent();
            
            Node CustomClearenceFormName = (Node) xPath.evaluate("/config/LIMA/Custom_Clearence_Form_Name", dDoc, XPathConstants.NODE);
            strCustomClearenceFormName = CustomClearenceFormName.getTextContent();
            
            Node CompletePageFormName = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
            strCompletePageFormName = CompletePageFormName.getTextContent();
            
            Node PageTitleDCBooking = (Node) xPath.evaluate("/config/LIMA/Page_Title_DC_Booking", dDoc, XPathConstants.NODE);
            strPageTitleDCBooking = PageTitleDCBooking.getTextContent();
            
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getCustomClearenceXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
                    
            nlSearchParamsCustomerClearence = (NodeList)xPath1.evaluate("/Custom_Clearence/Search_Params", dDoc1, XPathConstants.NODESET);
            n1VesselGrid = (NodeList)xPath1.evaluate("/Custom_Clearence/Vessel_Grid", dDoc1, XPathConstants.NODESET);
            n1HouseBillGrid = (NodeList)xPath1.evaluate("/Custom_Clearence/HouseBill_Grid", dDoc1, XPathConstants.NODESET);
            Element el;
            for(int i=0; i <= nlSearchParamsCustomerClearence.getLength() -1; i++)
             {
                 if (nlSearchParamsCustomerClearence.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		 el = (Element) nlSearchParamsCustomerClearence.item(i); 
            		 
            		 strInvalidContainerNo = el.getElementsByTagName("Invalid_ContainerNumber").item(0).getTextContent();
            		 strAwaitingStatus = el.getElementsByTagName("Awaiting_Status").item(0).getTextContent();
            		 strEPU = el.getElementsByTagName("EPU").item(0).getTextContent();
            		 strUCR = el.getElementsByTagName("UCR").item(0).getTextContent();
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
		} 
    	catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
    
    @Test(priority = 1)
    public void Test_2_acessCustomClearence()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	String strTitle = objAdjunoLIMACustomClearencePOM.callMouseHover(strSearchFormNameCustomerClearence,objAdjunoLIMACustomClearencePOM.getLnkTools(),objAdjunoLIMACustomClearencePOM.getLnkCustomsClearance());
        
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleCustomerClearence))
                bFlag = true;
            else
                bFlag = false;
        }
        	       
    objSoftAssert.assertEquals(bFlag, true, "*****_2_Page Title is wrong in Custom Clerence tool*****");
    
    objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getChevSearch()), true, "*****_2_Search Chevron is not displayed in Custom Clearence tool*****");
    objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getChevSelect()), true, "*****_2_Select Chevron is not displayed in Custom Clearence tool*****");
    objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getChevClearence()), true, "*****_2_Clearence Chevron is not displayed in Custom Clearence tool*****");
    objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getChevComplete()), true, "*****_2_Complete Chevron is not displayed in Custom Clearence tool");
    
    objSoftAssert.assertAll();     
    }
    
    @Test(priority = 5)
    public void Test_3_verifySearchFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence,"PARAM_01Origin"), true, "*****_3_Origin field is not displayed in Custom Clearence Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_02OriginPort"), true, "*****_4_Origin Port field is not displayed in Custom Clearence Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_03Mode"), true, "*****_3_Mode field is not displayed in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_04Vessel"), true,"*****_3_Vessel field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_11ETAFrom"), true,"*****_3_ETAFrom field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_12ETATo"), true,"*****_3_ETATo field is not displayed in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_13Status"), true,"*****_3_Status field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_08Product"), true,"*****_3_Product field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_06Container"), true,"*****_3_Container field is not displayed in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_09HouseBill"), true,"*****_3_HouseBill field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_10ForwarderRef"), true,"*****_3_Forwarder Ref field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_05Vendor"), true,"*****_3_Vendor field is not displayed in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_14CustomerReference"), true,"*****_3_Customer Reference field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_07PO"), true,"*****_3_PO Number field is not displayed in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strSearchFormNameCustomerClearence, "PARAM_19URN"), true,"*****_3_URN field is not displayed in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 10)
    public void Test_4_setAwaitingAndInvalidPO()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_01Origin"), true, "*****_4_Origin field is not cleared in Custom Clearence Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_02OriginPort"), true, "*****_4_Origin Port field is not cleared in Custom Clearence Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_03Mode"), true, "*****_4_Mode field is not cleared in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_04Vessel"), true,"*****_4_Vessel field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_11ETAFrom"), true,"*****_4_ETAFrom field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_12ETATo"), true,"*****_4_ETATo field is not cleared in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_13Status"), true,"*****_4_Status field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_08Product"), true,"*****_4_Product field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_06Container"), true,"*****_4_Container field is not cleared in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_09HouseBill"), true,"*****_4_HouseBill field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_10ForwarderRef"), true,"*****_4_Forwarder Ref field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_05Vendor"), true,"*****_4_Vendor field is not cleared");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_14CustomerReference"), true,"*****_4_Customer Reference field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_07PO"), true,"*****_4_PO Number field is not cleared in Custom Clearence Search Chevron");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_19URN"), true,"*****_4_URN field is not cleared in Custom Clearence Search Chevron");
    	wait(3000);
    	  	
    	if(!isNullOrBlank(strAwaitingStatus))
    	{
    		
    		objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValue(strAwaitingStatus, strSearchFormNameCustomerClearence, "PARAM_13Status"), true, "*****_4_Awaiting is not set in status field in Custom Clearence Search Chevron*****");
    	}
    	else
    	{
    		strAwaitingStatus = "*****_4_Status is Null*****";
    	}
    	
    	if(!isNullOrBlank(strInvalidContainerNo))
    	{
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValue(strInvalidContainerNo, strSearchFormNameCustomerClearence, "PARAM_06Container"), true, "*****_4_Container Number is not set in conatiner field in Custom Clearence Search Chevron*****");
    	}
    	else
    	{
    		strContainerNo = "*****_4_Container Number is null*****";
    	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickChevron(objAdjunoLIMACustomClearencePOM.getChevSelect()), true, "*****_4_Select Chevron is not clicked in Custom Clearence Search Chevron*****");
    	wait(2000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getNoProductErrMsg()), true, "*****_4_Error Message as \"No items were found.\" is not displayed when Invalid Container Number is set in Container field in Custom Clearence Search Chevron*****");
    	String strMessage = objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getNoProductErrMsg());
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("No items were found."), true, "*****_4_Error Message as \"No items were found.\" is not displayed when Invalid Container Number is set in Container field in Custom Clearence Search Chevron");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clearInputField(strSearchFormNameCustomerClearence, "PARAM_06Container"), true, "*****_4_Container field is not cleared in Custom Clearence Select chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickButton(strSearchFormNameCustomerClearence, "FORK_Clearance_RefineSearch"), true, "*****_4_Refine Search button is not clicked in Custom Clearence Select chevron*****");
    	strContainerNo = objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrContainerNo());
    	
    	objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 15)
    public void Test_5_setAwaitingAndValidContainerNo()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	String strMessage="";
    	if(!isNullOrBlank(strContainerNo))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValue(strContainerNo, strSearchFormNameCustomerClearence, "PARAM_06Container"), true, "*****_5_Container Value is not set in Container Number field in Custom Clearence Select page*****");
    	}
    	else
    	{
    		strMessage = strMessage + "*****_5_Container Number is null*****";
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickButton(strSearchFormNameCustomerClearence, "FORK_Clearance_RefineSearch"), true, "*****_5_Refine Search button is not clicked in Custom Clearence Select page*****");
    	
    	objSoftAssert.assertEquals(strContainerNo.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrContainerNo())), true, "*****_5_Valid Container Number is results are not displayed in the resultant grid under Custom Clearence Select page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 20)
    public void Test_6_verifyResultantGridHeader()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThVessel()), true, "*****_6_Vessel Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThArrivalDate()), true, "*****_6_Arrival Date Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThContainer()), true, "*****_6_Container Column is not displayed in Custom Clearence Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThHouseBill()), true, "*****_6_House Bill Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThEntryDate()), true, "*****_6_Entry Date Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThEntryNo()), true, "*****_6_Entry No Column is not displayed in Custom Clearence Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThClearenceDate()), true, "*****_6_Clearence Date Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThEPU()), true, "*****_6_EPU Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThUCR()), true, "*****_6_UCR Column is not displayed in Custom Clearence Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThTaxPoint()), true, "*****_6_TaxPoint Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getThStatus()), true, "*****_6_Status Column is not displayed in Custom Clearence Select page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 25)
    public void Test_9_verifyContainerHyperLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	String strMessage = "";
    	
    	String strhref = objAdjunoLIMACustomClearencePOM.getLnkContainer().getAttribute("href");
    	if(!isNullOrBlank(strhref))
    	{
    	
    	}
    	else
    	{
    		strMessage = "*****_9_"+strMessage + "It is not a hyperLink*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickLink(objAdjunoLIMACustomClearencePOM.getLnkContainer()), true, "*****_9_Conatiner hyperlink is not clicked in the resultant grid under Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getPageShipMentStatus()), true, "*****_9_Shipment Status page is not dispalyed after clicking Conatiner hyperlink in the resultant grid under Custom Clearence Select page*****");
    	driver.navigate().back();
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 30)
    public void Test_10_verifyHouseBillLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	String strMessage = "";
    	
    	String strhref = objAdjunoLIMACustomClearencePOM.getLnkHouseBill().getAttribute("href");
    	if(!isNullOrBlank(strhref))
    	{
    	
    	}
    	else
    	{
    		strMessage = "*****_10_"+strMessage + "It is not a hyperLink in resultant grid under Custom Clearence Select page*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickLink(objAdjunoLIMACustomClearencePOM.getLnkHouseBill()), true, "*****_10_HouseBill Link is not clicked resultant grid under Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getPageShipMentStatus()), true, "*****_10_Shipment Status page is not dispalyed after clicking HouseBill hyperlink in the resultant grid under Custom Clearence Select page*****");
    	driver.navigate().back();
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 35)
    public void Test_11_verifyErrorMsg()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickChevron(objAdjunoLIMACustomClearencePOM.getChevClearence1()), true, "*****_11_Clearence Chevron is not clicked under Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getNoSelectErrMsg()), true, "*****_11_'You must make selection(s) before progressing' message is not displayed when no Container numbers are selected in the resultant grid under Custom Clearence Select page*****");
    	strMessage = objAdjunoLIMACustomClearencePOM.getNoSelectErrMsg().getText();
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase("You must make selection(s) before progressing"), true, "*****_11_Proper Error Message as \"You must make selection(s) before progressing\" is not displayed when no Container numbers are selected in the resultant grid under Custom Clearence Select page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 40)
    public void Test_12_verifyCustomsClearencePageDisplayed()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickCheckBox(objAdjunoLIMACustomClearencePOM.getTrCheckBox()), true, "*****_12_Check Box is not clicked in resultant grid under Custom Clearence Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickChevron(objAdjunoLIMACustomClearencePOM.getChevClearence1()), true, "*****_12_Clearence Chevron is not clicked Custom Clearence Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.verifyPageIsLoaded(strCustomClearenceFormName), true, "*****_12_Clearence page is not loaded after clicking Clearence Chevron Select page*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 45)
    public void Test_13_verifyVesselGridColumns()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	boolean bFlag;
        String strMessage="";
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strCustomClearenceFormName, "Grid_Vessel"), true, "*****_13_Vessel grid is not displayed in Custom Clearence Clearence page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.checkDoesFieldsExist(strCustomClearenceFormName, "GridHouseBill"), true, "*****_13_HouseBill grid is not displayed in Custom Clearence Clearence page*****");
    	
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMACustomClearencePOM.getCaptionsList(strCustomClearenceFormName,"Grid_Vessel");
                
        strMessage = objAdjunoLIMACustomClearencePOM.verifyCaptionsONGrid(list,n1VesselGrid,3);
        
        if(strMessage.equals(""))    {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true," *****_13_Vessel grid Caption: "+strMessage+"*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 50)
    public void Test_13_verifyHouseBillGridColumn()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	boolean bFlag;
        String strMessage="";
        
        ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMACustomClearencePOM.getCaptionsList(strCustomClearenceFormName,"GridHouseBill");
                
        strMessage = objAdjunoLIMACustomClearencePOM.verifyCaptionsONGrid(list,n1HouseBillGrid,8);
        
        if(strMessage.equals(""))   
        {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true," *****_13_HouseBill grid Caption: "+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 55)
    public void Test_14_verifyEntryDatefield()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	strEntryDate = objAdjunoLIMACustomClearencePOM.setCurrentDate("dd MMM YYYY");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForGridCell(strCustomClearenceFormName, "GridHouseBill", 0, "EntryDate", strEntryDate), true, "*****_14_Entry date field is not set in PO Number grid in Clerence page*****");
    	
    	objSoftAssert.assertEquals(strEntryDate.equals(objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "EntryDate")), true, "*****_14_Set Entry Date is "+strEntryDate+" and date found is "+objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "EntryDate")+"wrong"+"*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 60)
    public void Test_15_verifyEntryNumberField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	 strEntryNo = objAdjunoLIMACustomClearencePOM.getRandomString();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForGridCell(strCustomClearenceFormName, "GridHouseBill", 0, "EntryNo",strEntryNo), true, "*****_15_Entry Number value is not set in PO Number grid in Clerence page*****");
    	objSoftAssert.assertEquals(strEntryNo.equalsIgnoreCase(objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "EntryNo")), true, "*****_15_Set Entry Number is "+strEntryNo+" and the Entry No found is "+objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "EntryNo")+" wrong*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 65)
    public void Test_16_verifyClearenceDateField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	strClearenceDate = objAdjunoLIMACustomClearencePOM.setFutureDate("dd MMM YYYY");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForGridCell(strCustomClearenceFormName, "GridHouseBill", 0, "ClearanceDate", strClearenceDate), true, "*****_16_Clearance Date field is not set in PO Number grid in Clerence page*****");
    	objSoftAssert.assertEquals(strClearenceDate.equals(objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "ClearanceDate")), true, "*****_16_Set Clearence date is "+strClearenceDate+" and the Clearence date found is "+ objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "ClearanceDate")+ " wrong*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 70)
    public void Test_17_verifyEPUField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForGridCell(strCustomClearenceFormName, "GridHouseBill", 0, "EPU", strEPU), true, "*****_17_EPU field value is not set in PO Number grid in Clerence page*****");
    	objSoftAssert.assertEquals(strEPU.equals(objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "EPU")), true, "*****_17_ Set EPU field value is "+strEPU+" and the Clearence date found is "+ objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "EPU")+" wrong");
    	objSoftAssert.assertAll();
       	
    }
    
    @Test(priority = 75)
    public void Test_18_verifyUCRField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForGridCell(strCustomClearenceFormName, "GridHouseBill", 0, "UCR", strEPU), true, "*****_18_UCR field value is not set in PO Number grid in Clerence page*****");
    	objSoftAssert.assertEquals(strUCR.equals(objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "UCR")), true, "*****_18_UCR fiels value is "+strEPU+" and the Clearence date found is "+ objAdjunoLIMACustomClearencePOM.getGridCellValue(strCustomClearenceFormName, "GridHouseBill", 0, "UCR")+" wrong");
    	objSoftAssert.assertAll();

    }
    
    @Test(priority = 80)
    public void Test_19_verifyCompleteProcess()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickChevron(objAdjunoLIMACustomClearencePOM.getChevComplete1()), true, "*****_19_Complete Chevron is not clicked under Clearence page*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.verifyPageIsLoaded(strCompletePageFormName), true, "*****_19_Complete page is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getTxtUpdatedMsg()), true, "*****_19_'1 Housebill has been updated as Cleared.' message is not displayed in Complete page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getTxtProcessedMsg()), true, "*****_19_'100% processed message' is not displayed in Complete page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 85)
    public void Test_20_verifyViewDetail()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	
    	wait(2000);
    	objAdjunoLIMACustomClearencePOM.getBtnViewDetail().click();
    	
    	wait(3000);
    	String strDespatchDetails = objAdjunoLIMACustomClearencePOM.getTxtViewDetail().getText(); 
    	String[] vals = strDespatchDetails.split("Track Reference ");
        
    	vals[1] = vals[1].replace("[", " ").replace("]", " ");
    	String valss[] = vals[1].split(" ",12);
    	
    	strTrackNO = valss[1];
    	 
    	objAdjunoLIMACustomClearencePOM.callMouseHover(strSearchFormNameCustomerClearence,objAdjunoLIMACustomClearencePOM.getLnkTrack(),objAdjunoLIMACustomClearencePOM.getLnkEdit());
                
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 90)
    public void Test_21_EnterTrackRefNumber() throws ParseException
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getFldRefNo()), true,"*****_21_Reference field is not found in \"Edit or create a track\" page *****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForWebElement(objAdjunoLIMACustomClearencePOM.getFldRefNo(),strTrackNO), true,"*****_21_Track number is not set in \"Reference field\" under \"Edit or create a track\" page*****");
        }else{
        	strMessage ="*****_41_"+strMessage + " Track number is null***** ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickButtonUsingWebElement(objAdjunoLIMACustomClearencePOM.getBtnTrackApply()), true,"*****_21_Apply button is not clicked in \"Edit or create a track\" page*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.isElementPresent(objAdjunoLIMACustomClearencePOM.getShipmentTrack()), true, "*****_21_Shipment event track page is not displayed*****");
        
        long min = objAdjunoLIMACustomClearencePOM.getTrackValue(strLIMAUserName);
        if(min<=350){
                     
        }else{
            strMessage = "Not a todays date";
        }
                 
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****_21_"+strMessage+"*****");
        objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 95)
    public void Test_22_clickOnCustomClearenceLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickLink(objAdjunoLIMACustomClearencePOM.getLnkTrackCustomClearance()), true, "*****_22_Custom Clearence link is not displayed under Shpment Track page*****");
    	
    	//verify the details
    	objSoftAssert.assertEquals(strContainerNo.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrkContainerNo())), true, "*****_22_Container Number do not match in Custom Clereance track page*****");
    	objSoftAssert.assertEquals(strEntryNo.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrkEntryNo())), true, "*****_22_Entry Number do not match in Custom Clereance track page*****");
    	objSoftAssert.assertEquals(strEntryDate.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrkEntryDate())), true, "*****_23_Entry date do not match in Custom Clereance track page*****");
    	objSoftAssert.assertEquals(strClearenceDate.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrkClearenceDate())), true, "*****_23_Clearence date do not match in Custom Clereance track page*****");
    	objSoftAssert.assertEquals(strEPU.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrkEPU())), true, "*****_23_EPU value do not match in Custom Clereance track page*****");
    	objSoftAssert.assertEquals(strUCR.equals(objAdjunoLIMACustomClearencePOM.getWebElementCellValue(objAdjunoLIMACustomClearencePOM.getTrkUCR())), true, "*****_23_UCR value do not match in Custom Clereance track page*****");
    	objSoftAssert.assertAll();
    }
    
//    @Test(priority = 100)
//    public void Test_23_CustomClearedContainerStausInDCBooking()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//    	objAdjunoLIMACustomClearencePOM = new AdjunoLIMACustomClearencePOM(driver);
//    	String strMessage = "";
//    	try
//    	{
//    	objAdjunoLIMACustomClearencePOM.callMouseOverUsingWebElement(strPageTitleDCBooking,objAdjunoLIMACustomClearencePOM.getLnkTools(), objAdjunoLIMACustomClearencePOM.getLnkDCBooking());
//    	wait(2000);
//    	
//    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.setFieldValueForWebElement(objAdjunoLIMACustomClearencePOM.getDCBookContainerNo(), strContainerNo), true, "*****_23_Container Number is not set under DC Booking page*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMACustomClearencePOM.clickButtonUsingWebElement(objAdjunoLIMACustomClearencePOM.getBtnTrackApply()), true, "*****_23_Apply button is not clicked under DC Booking page*****");
//    	wait(3000);
//    	
//    	objSoftAssert.assertEquals(strContainerNo.equals(objAdjunoLIMACustomClearencePOM.getDCBookingConatinerNo().getText()), true, "*****_23_Container Number found in the grid and Container number set in the field do not match in DC booking tool*****");
//    	objSoftAssert.assertEquals((objAdjunoLIMACustomClearencePOM.getDCBookingStatus().getText()).equals("Pending"), true, "*****_23_Container Number Status is not Pending in the resultant grid in DC booking tool*****");
//    	}
//    	catch(NoSuchElementException e)
//    	{
//    		strMessage = "*****_23_DC Booking Tool is not displayed in the Tool list*****";
//    	}
//    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
//    	objSoftAssert.assertAll();
//    }
    
    @AfterTest()
    public void closeBrowser()
    {
    	driver.quit();
    }
 
    
    

    
    
    
    
    
    

}
