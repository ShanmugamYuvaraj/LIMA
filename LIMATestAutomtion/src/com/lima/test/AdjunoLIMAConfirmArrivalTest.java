package com.lima.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.ArrayList;
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

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMAConfirmArrivalPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;


public class AdjunoLIMAConfirmArrivalTest {
	
	WebDriver driver;
	Long nPage_Timeout;
	String strTestURL;
	String strLIMAUserName;
	String strLIMAPassword;
	String strSearchFormNameConfirmArrival;
	String strPageTitleConfirmArrival;
	String strArrivalPageFormName;
	String strTrackConfirmArrival;
	String strSearchFormNameCustomClearence;
	
	String strInvalidContainerNo = "";
	String strAwaitingStatus = "";
	String strContainerNo = "";
	String strTrackNO ="";
	
	boolean bSearchResultsProductsFound = true; 
	
	NodeList nlSearchParamsConfirmArrival;
	NodeList nlArriveGrid;
	
	AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAConfirmArrivalPOM objAdjunoLIMAConfirmArrivalPOM;
    
	
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
            
            Node SearchFormNameConfirmArrival = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Confirm_Arrival", dDoc, XPathConstants.NODE);
            strSearchFormNameConfirmArrival = SearchFormNameConfirmArrival.getTextContent();
            
            Node pageTitleConfirmArrival = (Node) xPath.evaluate("/config/LIMA/Page_Title_Confirm_Arrival", dDoc, XPathConstants.NODE);
            strPageTitleConfirmArrival = pageTitleConfirmArrival.getTextContent();
            
            Node ArrivalFormName = (Node) xPath.evaluate("/config/LIMA/Arrival_Form_Name", dDoc, XPathConstants.NODE);
            strArrivalPageFormName = ArrivalFormName.getTextContent();
            
            Node TrackConfirmArrival = (Node) xPath.evaluate("/config/LIMA/Confirm_Arrival_Track_Page", dDoc, XPathConstants.NODE);
            strTrackConfirmArrival = TrackConfirmArrival.getTextContent();
            
            Node SearchFormNameCustomClearence = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Customs_Clearence", dDoc, XPathConstants.NODE);
            strSearchFormNameCustomClearence = SearchFormNameCustomClearence.getTextContent();
            
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getConfirmBookingXMLDateFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            nlSearchParamsConfirmArrival = (NodeList)xPath1.evaluate("/Confirm_Arrival/SearchParams", dDoc1, XPathConstants.NODESET);
            nlArriveGrid = (NodeList)xPath1.evaluate("/Confirm_Arrival/Arrive_Grid", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsConfirmArrival.getLength() -1; i++)
             {
                 if (nlSearchParamsConfirmArrival.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
                	 el = (Element) nlSearchParamsConfirmArrival.item(i); 
                	 
                	 strInvalidContainerNo = el.getElementsByTagName("Invalid_ContainerNumber").item(0).getTextContent();
                	 
                	 strAwaitingStatus = el.getElementsByTagName("Status_Awaiting").item(0).getTextContent();
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
	public void test_2_verifyAccessConfirmArrival() throws MalformedURLException, IOException
	{
	    SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
	        
        String strTitle = objAdjunoLIMAConfirmArrivalPOM.callMouseHover(strSearchFormNameConfirmArrival,objAdjunoLIMAConfirmArrivalPOM.getLnkTools(),objAdjunoLIMAConfirmArrivalPOM.getLnkConfirmArrival());       
        boolean bFlag = true;
	        
        if (isNullOrBlank(strTitle))
            bFlag = false;
	        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleConfirmArrival))
            	bFlag = true;
            else
                bFlag = false;
        }
	        	       
	    objSoftAssert.assertEquals(bFlag, true, "*****test ID:_2_Page Title is wrong in Confirm Arrival Search Chevron*****");
	       
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 5)
	public void test_2_verifyChevrons()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getChevSelect()), true, "*****test ID: chevron is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getChevSearch()), true, "*****test ID:_2_Search chevron is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getChevArrive()), true, "*****test ID:_2_Arrive chevron is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getChevComplete()), true, "*****test ID:_2_Complete chevron is not displayed in Confirm Arrival Search Chevron*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 10)
	public void test_3_verifySearchFields()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_DestinationPort"), true, "*****test ID:_3_Destination port field is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_Mode"), true, "*****test ID:_3_Mode field is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_ETAFrom"), true, "*****test ID:_3_ETA From field is not displayed in Confirm Arrival Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_ETATo"), true, "*****test ID:_3_ETA To field is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_Status"), true, "*****test ID:_3_Status field is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_Vessel"), true, "*****test ID:_3_Vessel field is not displayed in Confirm Arrival Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_Container"), true, "*****test ID:_3_Container field is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_HBL"), true, "*****test ID:_3_HBL field is not displayed in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strSearchFormNameConfirmArrival, "PARAM_ForwarderReference"), true, "*****test ID:_3_Forwarder ref field is not displayed in Confirm Arrival Search Chevron*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 15)
	public void test_4_setAwaitingAndInvalidContainerNo()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_DestinationPort"), true, "*****test ID:_4_Destination port field is not cleared in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_Mode"), true, "*****test ID:_4_Mode field is not cleared in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_ETAFrom"), true, "*****test ID:_4_ETA From field is not cleared in Confirm Arrival Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_ETATo"), true, "*****test ID:_4_ETA To field is not cleared in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_Status"), true, "*****test ID:_4_Status field is not cleared in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_Vessel"), true, "*****test ID:_4_Vessel field is not cleared in Confirm Arrival Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_Container"), true, "*****test ID:_4_Container field is not cleared in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_HBL"), true, "*****test ID:_4_HBL field is not cleared in Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_ForwarderReference"), true, "*****test ID:_4_Forwarder ref field is not cleared in Confirm Arrival Search Chevron*****");
    	
    	if(!isNullOrBlank(strAwaitingStatus))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValue(strAwaitingStatus, strSearchFormNameConfirmArrival, "PARAM_Status"), true, "*****test ID:_4_Status is not set as \"Awaiting\" in Confirm Arrival Search Chevron*****");
    		
    	}
    	else
    	{
    		strMessage = strMessage + "Status is null";
    	}
    	if(!isNullOrBlank(strInvalidContainerNo))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValue(strInvalidContainerNo, strSearchFormNameConfirmArrival, "PARAM_Container"), true, "*****test ID:_4_Invalid container number is not set in Conatiner field in Confirm Arrival Search Chevron*****");
    	}
    	else
    	{
    		strMessage = "*****"+strMessage + "Container number is null*****";
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickChevron(objAdjunoLIMAConfirmArrivalPOM.getChevSelect()), true, "*****test ID:_4_Select chevron is not clicked in Confirm Arrival Search Chevron*****");
    	
    	String strErrMessage = objAdjunoLIMAConfirmArrivalPOM.getWebElementCellValue(objAdjunoLIMAConfirmArrivalPOM.getTxtNoProductErr());
    	
    	objSoftAssert.assertEquals(strErrMessage.equalsIgnoreCase("No items were found."), true, "*****test ID:_4_Error message is not displayed as \"No items were found.\" when Invalid Container Number is set in Container Number field under Confirm Arrival Search Chevron*****");
    	objSoftAssert.assertAll();
    	   	
	}
	
	//Need to edit
	@Test(priority = 20)
	public void test_5_setAwaitingAndValidContainerNo()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValue(strAwaitingStatus, strSearchFormNameConfirmArrival, "PARAM_Status"), true, "*****test ID:_5_Status is not set as \"Awaiting\" in Status field under Confirm Arrival Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clearInputField(strSearchFormNameConfirmArrival, "PARAM_Container"), true, "*****test ID:_5_Container No is not cleared in Container Number field under Confirm Arrival Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickButton(strSearchFormNameConfirmArrival, "FORK_ConfirmArrival_RefineSearch"), true, "*****test ID:_5_Refine search button is not clicked in Confirm Arrival Select Chevron*****");
    	
    	strContainerNo = objAdjunoLIMAConfirmArrivalPOM.getTrContainerNo().getText();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValue(strContainerNo, strSearchFormNameConfirmArrival, "PARAM_Container"), true, "*****test ID:_5_ Valid Container Number is not set in Container Number field under Confirm Arrival Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickButton(strSearchFormNameConfirmArrival, "FORK_ConfirmArrival_RefineSearch"), true, "*****test ID:_5_Refine search button is not clicked in Confirm Arrival Select Chevron*****");
    	
    	if(objAdjunoLIMAConfirmArrivalPOM.getNoOfRowsResulted() > 0)
    	{
    		
    	}
    	else
    	{
    		bSearchResultsProductsFound = false;
    	}
    	
    	String strResult = objAdjunoLIMAConfirmArrivalPOM.getWebElementCellValue(objAdjunoLIMAConfirmArrivalPOM.getTrContainerNo());
    	objSoftAssert.assertEquals(strResult.equalsIgnoreCase(strContainerNo), true,"*****test ID:_5_Container number which is set in Container Number field do not match with the Container Number displayed in resultant grid under Confirm Arrival Select Chevron*****");
    	
    	objSoftAssert.assertAll();	
	}
	
	@Test(priority = 25)
	public void test_6_verifyGridHeaders()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThVessel()), true, "*****test ID:_6_\"Vessel Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThDestinationPort()), true, "*****test ID:_6_\"Destination Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThArrived()), true, "*****test ID:_6_\"Arrived/ETA Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThContainerNo()), true, "*****test ID:_6_\"ContainerNo Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThOriginPort()), true, "*****test ID:_6_\"Origin port Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThForwardRef()), true, "*****test ID:_6_\"Forward ref Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getThStatus()), true, "*****test ID:_6_\"Status Column\" is not displayed in resultant grid under Confirm Arrival Select page*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 30)
	public void test_9_verifyErrMsgAtArriveTab()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickChevron(objAdjunoLIMAConfirmArrivalPOM.getChevArrive1()), true, "*****_9_\"Arrive Chevron\" is not clicked in Confirm Arrival Select page*****");
    	String strErrMsg = objAdjunoLIMAConfirmArrivalPOM.getTxtProgressingErrMsg().getText();
    	objSoftAssert.assertEquals(strErrMsg.equalsIgnoreCase("You must make selection(s) before progressing"), true, "*****test ID:_9_In Confirm Arrival Select page \"You must make selection(s) before progressing\" is not displayed when no PO's are Selected and clicked on Arrive Chevron*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 35)
	public void test_10_verifyArrivePageDisplayed()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickCheckBox(objAdjunoLIMAConfirmArrivalPOM.getCheckBox()), true, "*****test ID:_10_CheckBox is not clicked in resultant grid under Confirm Arrival Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickChevron(objAdjunoLIMAConfirmArrivalPOM.getChevArrive1()), true, "*****test ID:_10_Arrive Chevron is not clicked in Confirm Arrival Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.verifyPageIsLoaded(strArrivalPageFormName), true, "*****test ID:_10_Arrive page is not loaded in Confirm Arrival*****");
    	
    	
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 40)
	public void test_11_verifyArriveGridDetails()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	boolean bFlag;
        String strMessage="";
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.checkDoesFieldsExist(strArrivalPageFormName, "GridArrive"), true, "*****test ID:_11_Arrive grid do not exist in Confirm Arrival \"Arrive page\"*****");
        
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMAConfirmArrivalPOM.getCaptionsList(strArrivalPageFormName,"GridArrive");
                
        strMessage = objAdjunoLIMAConfirmArrivalPOM.verifyCaptionsONGrid(list,nlArriveGrid,7);
        
        if(strMessage.equals(""))    {
            bFlag = true;
        }
        else{
            bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true," *****test ID:_11_Arrive grid Caption: "+strMessage+"*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 45)
	public void test_12_verifyCurrentETADateField()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValueForGridCell(strArrivalPageFormName, "GridArrive", 0, "Current", objAdjunoLIMAConfirmArrivalPOM.setDate("dd MMM YYYY")), true, "*****test ID:_12_Current ETA date is not set in Confirm Arrivl \"Arrive page\"*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 50)
	public void test_13_verifyArrivalDateField()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValueForGridCell(strArrivalPageFormName, "GridArrive", 0, "ArrivalDate", objAdjunoLIMAConfirmArrivalPOM.setDate("dd MMM YYYY")), true, "*****test ID:_13_Arrival Date is not set in Confirm Arrivl \"Arrive page\"*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 55)
	public void test_14_verifyCompletePage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickChevron(objAdjunoLIMAConfirmArrivalPOM.getChevComplete1()), true, "*****test ID:_14_Complete chevron is not clicked under Confirm Arrival Arrive page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getTxtProcessedMsg()), true,"*****test ID:_14_100% processed message is not displayed under Confirm Arrival \"Complete Chevron\"*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 60)
	public void test_15_verifyViewDetail()
	{
		
    	objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
    	       	       
    	objAdjunoLIMAConfirmArrivalPOM.getBtnViewDetail().click();
        	
        	wait(3000);
        	String strDespatchDetails = objAdjunoLIMAConfirmArrivalPOM.getTxtViewDetail().getText();   	
        //	String strTrackReferenceNo = strDespatchDetails.substring(strDespatchDetails.indexOf("LIMAUAT"),strDespatchDetails.indexOf("].", strDespatchDetails.indexOf("LIMAUAT")));
        	String[] vals = strDespatchDetails.split("Track Reference ");
        
        	vals[1] = vals[1].replace("[", " ").replace("]", " ");
        	String valss[] = vals[1].split(" ",12);
        	
        	strTrackNO = valss[1];
        	System.out.println("track ref num: - " + strTrackNO);
        //	System.out.println("track ref num: - " + strTrackReferenceNo);
        	wait(2000);      
	}
	
	@Test(priority = 65)
	public void test_15_16_verifyTrackPage()
	{
		SoftAssert objSoftAssert = new SoftAssert(); 
		objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
		
		String strMessage = "";
	    
		objAdjunoLIMAConfirmArrivalPOM.callMouseHover(strSearchFormNameConfirmArrival,objAdjunoLIMAConfirmArrivalPOM.getLnkTrack(),objAdjunoLIMAConfirmArrivalPOM.getLnkEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getFldRefNo()), true,"*****test ID:_15_Reference field  do not exist in \"Edit or create a track\" page*****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValueForWebElement(objAdjunoLIMAConfirmArrivalPOM.getFldRefNo(),strTrackNO), true,"*****test ID:_15_Track number is not set in \"Reference field\" under \"Edit or create a track\" page*****");
        }else{
        	strMessage ="*****"+strMessage + " Track number is null***** ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickButtonUsingWebElement(objAdjunoLIMAConfirmArrivalPOM.getBtnTrackApply()), true,"*****test ID:_16_Apply button is not clicked in \"Edit or create a track\" page*****");
        objSoftAssert.assertAll();
        
	}
	
	@Test(priority = 70)
    public void test_17_checkShipmentPageDisplay() throws ParseException
	{        
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
        String strMessage ="";
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.isElementPresent(objAdjunoLIMAConfirmArrivalPOM.getTxtShipmentTrack()), true, "*****test ID:_17_Shipment track page is not loaded*****");
                        
       long min = objAdjunoLIMAConfirmArrivalPOM.getTrackValue(strLIMAUserName);
       if(min<=350)
       {
                    
       }
       else
       {
           strMessage = "*****_17_Not a todays date*****";
       }
                
       objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
       objAdjunoLIMAConfirmArrivalPOM.getLnkTrackConfirmArrival().click();
       wait(3000);
       objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.verifyPageIsLoaded(strTrackConfirmArrival), true, "*****test ID:_17_Track Confirm Arrival page is not loaded after clicking the latest updated Confirm Arrival link in Shipment track page*****");
       
       objSoftAssert.assertAll();
    }
	
	@Test(priority = 75)
	public void test_18_verifyConfirmedContainerInCustomClearence()
	{
		SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAConfirmArrivalPOM = new AdjunoLIMAConfirmArrivalPOM(driver);
        objAdjunoLIMAConfirmArrivalPOM.callMouseHover(strSearchFormNameCustomClearence, objAdjunoLIMAConfirmArrivalPOM.getLnkTools(), objAdjunoLIMAConfirmArrivalPOM.getLnkCustomClearence());
        String strMessage = "";
        
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.verifyPageIsLoaded(strSearchFormNameCustomClearence), true, "*****test ID:_18_Custom Clearence Search page is not loaded*****");
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValue(strAwaitingStatus, strSearchFormNameCustomClearence, "PARAM_13Status"), true, "*****test ID:_18_Awaiting Status is not set in Custom Clearence page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.setFieldValue(strContainerNo, strSearchFormNameCustomClearence, "PARAM_06Container"), true, "*****test ID:_18_Container value is not set in Custom Clearence page*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMAConfirmArrivalPOM.clickChevron(objAdjunoLIMAConfirmArrivalPOM.getChevSelect()), true, "*****test ID:_18_Select Chevron is not clicked in Custom Clearence page***** ");
        try
        {
        String strCustomerClearenceStatus = objAdjunoLIMAConfirmArrivalPOM.getWebElementCellValue(objAdjunoLIMAConfirmArrivalPOM.getTrCustomClearenceStatus());
        
        objSoftAssert.assertEquals(strCustomerClearenceStatus.equalsIgnoreCase("Awaiting"), true, "*****test ID:_18_Confirm Arrival Container Number Status is not Awaiting in Customer Clearence*****");
        }
        catch(NoSuchElementException e)
        {
        	strMessage = "_18_Confirm Arrivaed Container Number is not found in Customer Clearence in Awaiting status*****";
        }
        objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
        objSoftAssert.assertAll();
	}
	
	@AfterTest
	public void closeBrowser()
	{
		driver.close();
	}
	
	
}
