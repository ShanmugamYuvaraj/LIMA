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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.VendorBooking;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAVendorBookingPOM;

public class AdjunoLIMAVendorBookingTest {

	WebDriver driver;
	Long nPage_Timeout;
	String strTestURL;
	String strLIMAUserName;
	String strLIMAPassword;
	String strSearchFormNameVendorBooking;
	String strPageTitleVendorBooking;
	String strVendorBookingFormName;
	String strCompletePageFromName;
	String strPOStatusFormName;
	String strShipmentRequestFormName;
	String strCatalogFormName;
	String strCatalogConfigurationFormName;
	String strPOManagerSearchFormName;
	String strPOManagerEditFormName;
	String strCatalogViewFormName;
	String strDashBoardFormName;
	
	
	ArrayList<VendorBooking> lstSearchResults;
	ArrayList<String> listNotifyPartyDetails;
    boolean bSearchResultsProductsFound = true;

	String strInvalidPONumber = "";
	String strPendingStatus = "";
	String strPONumber = "";
	String strFactoryDetails = "";
	String strNotifyPartyDetails = "";
	String strContactDetails = "";
	String strOpenQty = "";
	String strBkdCtns = "";
	String strBkdNetKgs = "";
	String strBkdCBM = "";
	String strPackType = "";
	String strReasonCode = "";
	String strHSCode = "";
	String strBkdKg = "";
	String strMode = "";
	String strPreviousModeValue = "";
	String strOriginPort = "";
	String strLoding = "";
	String strBuyingTerms = "";
	String strFreightTerms = "";
	String strExFactoryDate = "";
	String strLoadingCY_CY = "";
	String strTrackNO = "";
	String strProduct = "";
	String strStatusBooked = "";
	String strStatusShipmentBooked = "";
	String strStatusVendorBooked = "";
	String strVendorBookingCancelPageFormName = "";
	String strCancelPONumber = "";
	String strStatusPartBooked = "";
	String VendorBookingID = "";
	String strTypeConfiguration = "";
	String strCatalogSeaMarksAndNumbersMsg = "";
	String strCatalogAirMarksAndNumbersMsg = "";
	String strCatalogRoadMarksAndNumbersMsg = "";
	String strCatalogSea_AirMarksAndNumbersMsg = "";
	String strPOOriginDesc = "";
	String strCatalogTypeLocode = "";
	String strCatalogTypeFactoryDetails = "";
	String strCatalogTypeNotifyPartyDetails = "";
	String strCatalogTypeNotifyParty = "";
	
	int nNotifyPartyDetailsDropDownList;
	
	NodeList nlSearchParamsVendorBooking;
	NodeList nlProductDetailsGrid;
	NodeList nlCYConatinerRequirementGrid;
	NodeList nlEquipmentSelectorGrid;
	NodeList nlCatalogData;
	
	
	AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAVendorBookingPOM objAdjunoLIMAVendorBookingPOM;
    
	
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
            
            Node SearchFormNameVendorBooking = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Vendor_Booking", dDoc, XPathConstants.NODE);
            strSearchFormNameVendorBooking = SearchFormNameVendorBooking.getTextContent();
            
            Node pageTitleVendorBooking = (Node) xPath.evaluate("/config/LIMA/Page_Title_Vendor_Booking", dDoc, XPathConstants.NODE);
            strPageTitleVendorBooking = pageTitleVendorBooking.getTextContent();
           
            Node vendorBookingFormName = (Node) xPath.evaluate("/config/LIMA/Vendor_Booking_Form_Name", dDoc, XPathConstants.NODE);
            strVendorBookingFormName = vendorBookingFormName.getTextContent();
            
            Node completePageFromName = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
            strCompletePageFromName = completePageFromName.getTextContent();
            		
            Node POStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
            strPOStatusFormName = POStatusFormName.getTextContent();
            
            Node ShipmentRequestFormName = (Node) xPath.evaluate("/config/LIMA/Shipment_Request_FormName", dDoc, XPathConstants.NODE);
            strShipmentRequestFormName = ShipmentRequestFormName.getTextContent();
            
            Node VendorBookingCancelPageFormName = (Node) xPath.evaluate("/config/LIMA/Vendor_Booking_Cancel_Page", dDoc, XPathConstants.NODE);
            strVendorBookingCancelPageFormName = VendorBookingCancelPageFormName.getTextContent();
            
            Node CatalogFromName = (Node) xPath.evaluate("/config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
            strCatalogFormName = CatalogFromName.getTextContent();
            
            Node CatalogConfigurationFormName = (Node)xPath.evaluate("/config/LIMA/Catalog_Configuration_Form_Name", dDoc, XPathConstants.NODE);
            strCatalogConfigurationFormName = CatalogConfigurationFormName.getTextContent();
            
            Node POManagerFormName = (Node)xPath.evaluate("/config/Generic/Search_Form_Name_PO_Manager", dDoc, XPathConstants.NODE);
            strPOManagerSearchFormName = POManagerFormName.getTextContent();
            
            Node POManagerEditFormName = (Node)xPath.evaluate("/config/Generic/Edit_Form_Name_PO_Manager", dDoc, XPathConstants.NODE);
            strPOManagerEditFormName = POManagerEditFormName.getTextContent();
            
            Node CatalogViewFormName = (Node)xPath.evaluate("/config/LIMA/Catalog_View_FormName", dDoc, XPathConstants.NODE);
            strCatalogViewFormName = CatalogViewFormName.getTextContent();
            
            Node DashBoardFormName = (Node)xPath.evaluate("/config/LIMA/VendorBooking_DashBoard_Form_Name", dDoc,XPathConstants.NODE);
            strDashBoardFormName = DashBoardFormName.getTextContent();
            
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getStrLIMAVendorBooking());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            nlSearchParamsVendorBooking = (NodeList)xPath1.evaluate("/Vendor_Booking/Search_Params", dDoc1, XPathConstants.NODESET);
            nlProductDetailsGrid = (NodeList)xPath1.evaluate("/Vendor_Booking/Product_Details_Grid", dDoc1, XPathConstants.NODESET);
            nlCYConatinerRequirementGrid = (NodeList)xPath1.evaluate("/Vendor_Booking/CY_Container_Requirements_Grid", dDoc1, XPathConstants.NODESET);
            nlEquipmentSelectorGrid = (NodeList)xPath1.evaluate("/Vendor_Booking/Equipment_Selector_Grid", dDoc1, XPathConstants.NODESET);
            nlCatalogData = (NodeList)xPath1.evaluate("/Vendor_Booking/Catalog_Data", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsVendorBooking.getLength() -1; i++)
             {
                 if (nlSearchParamsVendorBooking.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
                	 el = (Element) nlSearchParamsVendorBooking.item(i); 
                	 
                	 strInvalidPONumber = el.getElementsByTagName("Invalid_PoNumber").item(0).getTextContent();
                	 
                	 strPendingStatus = el.getElementsByTagName("Status_Pending").item(0).getTextContent();
                	 
                	 strFactoryDetails = el.getElementsByTagName("Factory_Details").item(0).getTextContent();
                	 
                	 strNotifyPartyDetails = el.getElementsByTagName("Notify_Party_Details").item(0).getTextContent();
                	 
                	 strContactDetails = el.getElementsByTagName("Contact_Details").item(0).getTextContent();
                	 
                	 strBkdCtns = el.getElementsByTagName("Bkd_Ctns").item(0).getTextContent();
                	 
                	 strBkdNetKgs = el.getElementsByTagName("Bkd_Net_Kgs").item(0).getTextContent();
                	 
                	 strBkdCBM = el.getElementsByTagName("Bkd_CBM").item(0).getTextContent();
                	 
                	 strPackType = el.getElementsByTagName("Pack_Type").item(0).getTextContent();
                	 
                	 strReasonCode = el.getElementsByTagName("Reason_Code").item(0).getTextContent();
                	 
                	 strMode = el.getElementsByTagName("Mode").item(0).getTextContent();
                	 
                	 strOriginPort = el.getElementsByTagName("Origin_Port").item(0).getTextContent();
                		
                	 strLoadingCY_CY = el.getElementsByTagName("Loading").item(0).getTextContent();
                	 
                	 strStatusBooked = el.getElementsByTagName("Status_Booked").item(0).getTextContent();
                	 
                	 strStatusShipmentBooked = el.getElementsByTagName("Status_Shipment_Booked").item(0).getTextContent();
                	 
                	 strStatusVendorBooked = el.getElementsByTagName("Status_VendorBooked").item(0).getTextContent();
            	
                	 strStatusPartBooked = el.getElementsByTagName("Status_PartBooked").item(0).getTextContent();
                	
            	}
             }
            
            Element el1;
            for(int i=0; i <= nlCatalogData.getLength() -1; i++)
            {
                if (nlCatalogData.item(i).getNodeType() == Node.ELEMENT_NODE)
           	{
               	 el1 = (Element) nlCatalogData.item(i); 
               	 
               	 strTypeConfiguration = el1.getElementsByTagName("Type_Configuration").item(0).getTextContent();
               	 strCatalogTypeLocode = el1.getElementsByTagName("Type_LOCODE").item(0).getTextContent();
               	 strCatalogTypeFactoryDetails = el1.getElementsByTagName("Type_Factory").item(0).getTextContent();
               	 strCatalogTypeNotifyPartyDetails = el1.getElementsByTagName("Type_Notify_Party").item(0).getTextContent();
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
	public void test_1_verifyAccessConfirmArrival()  
	{
	    SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
	        
        String strTitle = objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking,objAdjunoLIMAVendorBookingPOM.getLnkTools(),objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());       
        boolean bFlag = true;
	        
        if (isNullOrBlank(strTitle))
            bFlag = false;
	        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleVendorBooking))
            	bFlag = true;
            else
                bFlag = false;
        }
	        	       
	    objSoftAssert.assertEquals(bFlag, true, "*****test ID:_1_Page Title is wrong in Vendor Booking Search Chevron*****");
	       
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 5)
	public void test_2_VerifyChevronsInVendorBookingSearchPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getChevSearch()), true, "*****test ID: 'Search Chevron is not displayed in Vendor Booking tool*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID: 'Select Chevron is not displayed in Vendor Booking tool*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getChevBooking()), true, "*****test ID: 'Booking Chevron is not displayed in Vendor Booking tool*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getChevComplete()), true, "*****test ID: 'Complete Chevron is not displayed in Vendor Booking tool*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 10)
	public void test_3_VerifySearchFeildsUnderSearchChevron()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_Mode"), true, "*****test ID: Mode search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_OriginPort"), true, "*****test ID: Origin Port search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_DestinationPort"), true, "*****test ID: Destination Port search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_VendorReference"), true, "*****test ID: Vendor Reference search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_VendorBookingId"), true, "*****test ID: Vendor Booking ID search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_Vessel"), true, "*****test ID: Vessel search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_ShipmentDue"), true, "*****test ID: Shipment Due search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_ShipDateFrom"), true, "*****test ID: ETD Date From search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_ShipDateTo"), true, "*****test ID:  ETD Date To search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_Category"), true, "*****test ID: Group search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_Department"), true, "*****test ID: Department search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "*****test ID: OrderNumber search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_Product"), true, "*****test ID: Product search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID: Booking Status search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strSearchFormNameVendorBooking, "PARAM_WorkflowStatus"), true, "*****test ID: Workflow Status search field is not displayed in Vendor Booking Search page*****");
    	objSoftAssert.assertAll();
	}
	
	public void clearSearchFields()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_Mode"), true, "*****test ID: Mode search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_OriginPort"), true, "*****test ID: Origin Port search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_DestinationPort"), true, "*****test ID: Destination Port search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_VendorReference"), true, "*****test ID: Vendor Reference search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_VendorBookingId"), true, "*****test ID: Vecleareding ID search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_Vessel"), true, "*****test ID: Vessel search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_ShipmentDue"), true, "*****test ID: Shipment Due search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_ShipDateFrom"), true, "*****test ID: ETD Date From search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_ShipDateTo"), true, "*****test ID:  ETD Date To search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_Category"), true, "*****test ID: Group search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_Department"), true, "*****test ID: Department search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "*****test ID: OrderNumber search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_Product"), true, "*****test ID: Product search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID: Booking Status search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strSearchFormNameVendorBooking, "PARAM_WorkflowStatus"), true, "*****test ID: Workflow Status search field is not cleared in Vendor Booking Search page*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 15)
	public void test_4_CheckMandatoryFieldsInSearchChevron()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	clearSearchFields();
    	String strShipmentDueErrMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strSearchFormNameVendorBooking, "PARAM_ShipmentDue");
    	String strPONumberErrMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strSearchFormNameVendorBooking, "PARAM_OrderNumber");
    	String strBookingStatusErrMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strSearchFormNameVendorBooking, "PARAM_BookingStatus");
    	
    	if(!strShipmentDueErrMsg.equals("")  )
    	{
    		if(strShipmentDueErrMsg.contains("&#39;"))
    		{
    			strShipmentDueErrMsg = strShipmentDueErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strShipmentDueErrMsg.equalsIgnoreCase("> 'Shipment Due' is a required field"), true, "****test ID:4_'Shipment Due' is the required field msg is not displayed as \"ShipmentDue\" field is the mandatory field in VendorBooking Search page*****");
    		}
    	}
    	else
    	{
    		strMessage = "*****test ID:4_Validation Message is not displayed for Shipment Due Search field*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	
    	if(!strPONumberErrMsg.equals(""))
    	{
    		if(strPONumberErrMsg.contains("&#39;"))
    		{
    			strPONumberErrMsg = strPONumberErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strPONumberErrMsg.equalsIgnoreCase("> 'PO Number' is a required field"), true, "****test ID:4_'PO Number' is the required field message is not not displayed as \"PO Number\" field is the mandatory in VendorBooking Search page*****");
    		}
    	}
    	else
    	{
    		strMessage = "*****test ID:4_Validation Message is not displayed for PO Number Search field*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	
    	if(!strBookingStatusErrMsg.equals(""))
    	{
    		if(strBookingStatusErrMsg.equals(""))
    		{
    			strBookingStatusErrMsg = strBookingStatusErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strBookingStatusErrMsg.equalsIgnoreCase("> 'Booking Status' is a required field"), true, "****test ID:4_'Booking Status' is the required field message is not not displayed as \"Booking Status\" field is the mandatory in VendorBooking Search page*****");
    		}
    	}
    	else
    	{
    		strMessage = "*****test ID:4_Validation Message is not displayed for Booking Status Search field*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 17)
	public void test_5_SetShipmentDueFieldAndCheckMandtoryFields()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	clearSearchFields();
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0), strSearchFormNameVendorBooking, "PARAM_ShipmentDue"), true, "*****test ID:5_Shipment Due field is not set in Search page*****");
    	wait(2000);
		
    	String strBookingStatusErrMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strSearchFormNameVendorBooking, "PARAM_BookingStatus");
    	
    	if(!strBookingStatusErrMsg.equals(""))
    	{
    		if(strBookingStatusErrMsg.equals("&#39;"))
    		{
    			strBookingStatusErrMsg = strBookingStatusErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strBookingStatusErrMsg.equalsIgnoreCase("> 'Booking Status' is a required field"), true, "****test ID:5_'Booking Status' is the required field message is not not displayed as \"Booking Status\" field is the mandatory in VendorBooking Search page*****");
    		}
    	}
    	else
    	{
    		strMessage = "*****test ID:5_Validation Message is not displayed for Booking Status Search field*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objSoftAssert.assertAll();
		
	}
	
	@Test(priority = 20)
	public void test_5_CheckForPendingStatusAndInvalidContainerNumberInSearchPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	clearSearchFields();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:5_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strInvalidPONumber, strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "*****test ID:5_Invalid PO Number is not set in \"PO Number field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:5_Select Chevron is not clicked in Vendor Booking Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getErrMsgNoRecords()), true, "*****test ID:5_\"No items were found.\" message is not displayed when Invalid Container number is set in Vendor Booking Search page*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 25)
	public void test_6_CheckForPendingStatusAndValidContainerNumberInSearchPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	clearSearchFields();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:6_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:6_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButton(strSearchFormNameVendorBooking, "Button_RefineSearch"), true, "*****test ID:6_Refine Search button is not clicked under Select Chevron in Vendor Booking*****");
    	wait(3000);
//    	if(!objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strSearchFormNameVendorBooking, "Button_RefineSearch"))
//    	{
    	wait(3000);
    	objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnSearchPanelArrowBtn());
//    	}
    	wait(3000);
    	strPONumber = objAdjunoLIMAVendorBookingPOM.getTrPONumber().getText();
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPONumber, strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "*****test ID:6_PO Number is not set in \"PO Number field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButton(strSearchFormNameVendorBooking, "Button_RefineSearch"), true, "*****test ID:6_Refine Search button is not clicked under Select Chevron in Vendor Booking*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getTrPONumber().getText().equals(strPONumber), true, "*****test ID:6_Selected PO Number is not displayed under Select page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 30)
	public void test_7_CheckForResultantGridHeadersInSelectPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThVendor()), true, "*****test ID:7_\"Vendor Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThFactory()), true, "*****test ID:7_\"Factory Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThMode()), true, "*****test ID:7_\"Mode Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThOriginPort()), true, "*****test ID:7_\"OriginPort Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThDestinationPort()), true, "*****test ID:7_\"DestinationPort Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThVessel()), true, "*****test ID:7_\"Vessel Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThVoyage()), true, "*****test ID:7_\"Voyage Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThETD()), true, "*****test ID:7_\"ETD Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThETA()), true, "*****test ID:7_\"ETA Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThLoading()), true, "*****test ID:7_\"Loading Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThVendorBookingID()), true, "*****test ID:7_\"VendorBookingID Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThPONumber()), true, "*****test ID:7_\"PONumber Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThProduct()), true, "*****test ID:7_\"Product Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThIdentifier()), true, "*****test ID:7_\"Identifier Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThPriority()), true, "*****test ID:7_\"Priority Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThPromotion()), true, "*****test ID:7_\"Promotion Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThVendorRef()), true, "*****test ID:7_\"VendorRef Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThCustomerRef()), true, "*****test ID:7_\"CustomerRef Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThPOShipDate()), true, "*****test ID:7_\"POShipDate Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThPOQty()), true, "*****test ID:7_\"POQty Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThOpenQty()), true, "*****test ID:7_\"OpenQty Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThBkdQty()), true, "*****test ID:7_\"BkdQty Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThBkdCtns()), true, "*****test ID:7_\"BkdCtns Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThBkdCbm()), true, "*****test ID:7_\"BkdCbm Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThDestination()), true, "*****test ID:7_\"Destination Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThQC()), true, "*****test ID:7_\"QC Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThBookingStatus()), true, "*****test ID:7_\"BookingStatus Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThAuthorityStatus()), true, "*****test ID:7_\"AuthorityStatus Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getThDailog()), true, "*****test ID:7_\"Dailog(s) Column\" is not displayed in resultant grid under Vendor Booking Select Chevron*****");
    
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 35)
	public void test_8_VerifyValidationMessageWithoutSelectingAnyRecordInSelectChevron()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()), true, "*****test ID:8_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getTxtProgressingErrMsg()), true, "*****test ID:8_\"You must make selection(s) before progressing\" validation message is not displayed when no records are selected \"Select Chevron\"*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 40)
	public void test_9_SelectMultipleRecordsAndVerifyInBookingPage()
	{
		
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	wait(2000);
    	boolean bFlag = false;
    	objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnSearchPanelArrowBtn());
    	clearSearchFields();
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:9_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:9_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()), true, "*****test ID:9_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
        wait(5000);
        lstSearchResults = new ArrayList<VendorBooking>();
         
        if ( objAdjunoLIMAVendorBookingPOM.getNoOfRowsResulted() > 0)
        {
            lstSearchResults =  objAdjunoLIMAVendorBookingPOM.selectMulitpleProducts(2);
            
         objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()),true,"*****test ID:9_Booking Chevron is not clicked in Vendor Booking Select page*****");
            
        }
        else
        {
            bSearchResultsProductsFound = false;
              objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****test ID:9_No Products are displayed in Search Results under Vendor Booking Select Chevron*****");
        }
        if (objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strVendorBookingFormName)) //create a function in POM and that fucntion should call the UI function
        {
       	 bFlag = true;
            objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName,"Grid_ProductDetails"),true,"*****test ID:9_ProductDetails Grid is not dispalyed in \"Booking\" page under Booking Chevron*****");
        }
        else
        {
       	 bFlag = false;
       	 
        }
        objSoftAssert.assertEquals(bFlag, true, "*****test ID:9_Booking Page is not loaded*****");
        if(bSearchResultsProductsFound)
		  {
				String strReturnMessage = objAdjunoLIMAVendorBookingPOM.verifyProductsDataOnGrid(strVendorBookingFormName,"Grid_ProductDetails",lstSearchResults);
				
			  	objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, strReturnMessage);
			  
		  }
		  else
			  objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****test ID:9_No Products in Search Results.*****");
       
	  	objSoftAssert.assertAll();
	}
	
	@Test(priority = 45)
	public void test_10_SelectOneRecordAndVerifyInBookingPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	wait(2000);
    	boolean bFlag;
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:10_Vendor Booking Search page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:10_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:10_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:10_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
        wait(5000);
        lstSearchResults = new ArrayList<VendorBooking>();
         
        if ( objAdjunoLIMAVendorBookingPOM.getNoOfRowsResulted() > 0)
        {
            lstSearchResults =  objAdjunoLIMAVendorBookingPOM.selectMulitpleProducts(1);
            
         objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()),true,"*****test ID:10_Booking Chevron is not clicked in Vendor Booking Select page*****");
            
        }
        else
        {
            bSearchResultsProductsFound = false;
              objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****test ID:10_No Products are displayed in Search Results under Vendor Booking Select Chevron*****");
        }
        if (objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strVendorBookingFormName)) //create a function in POM and that fucntion should call the UI function
        {
       	 bFlag = true;
            objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName,"Grid_ProductDetails"),true,"*****test ID:10_ProductDetails Grid is not dispalyed in \"Booking\" page under Booking Chevron*****");
        }
        else
        {
       	 bFlag = false;
       	 
        }
        objSoftAssert.assertEquals(bFlag, true, "*****test ID:10_Booking Page is not loaded*****");
        if(bSearchResultsProductsFound)
		  {
				String strReturnMessage = objAdjunoLIMAVendorBookingPOM.verifyProductsDataOnGrid(strVendorBookingFormName,"Grid_ProductDetails",lstSearchResults);
				
			  	objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, strReturnMessage);
			  
		  }
		  else
			  objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****test ID:10_No Products in Search Results.*****");
       
	  	objSoftAssert.assertAll();
	}
	
	@Test(priority = 50)
	public void test_11_VerifyGridsInBookingPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:10_Vendor Booking Search page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:10_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:10_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:10_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
        
    	wait(3000);
    	for(int i = 0;i<=objAdjunoLIMAVendorBookingPOM.getLstPONumber().size()-1;i++)
    	{
    		if(objAdjunoLIMAVendorBookingPOM.getLstPONumber().get(i).getText().contains("LIMATEST"))
    		{
    			objAdjunoLIMAVendorBookingPOM.getLstCheckBox().get(i+2).click();
    			
    			break;
    		}
    		
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()),true,"*****test ID:10_Booking Chevron is not clicked in Vendor Booking Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Panel1"), true, "*****test ID:11_\"Container Details\" panel is not displayed in Vender Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Grid_ProductDetails"), true, "*****test ID:11_\"Product Details\" grid is not displayed in Vender Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Panel8"), true, "*****test ID:11_\"Shipment Details\" panel is not displayed in Vender Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Panel5"), true, "*****test ID:11_\"Container Details\" panel is not displayed in Vender Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Panel4"), true, "*****test ID:11_\"CY Container Requirement\" panel is not displayed in Vendor Booking \"Booking\" page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 55)
	public void test_12_VerifyFieldsInConatinerDetailsPanel()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Link_VendorDetails"), true, "*****test ID:12_\"Vendor Details hyperlink\" is not displayed in Container Details panel under Vendor Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Link_ConsigneeDetails"), true, "*****test ID:12_\"Consignee Detials hyperlink\" is not displayed in Container Details panel under Vendor Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "FactoryName"), true, "*****test ID:12_\"Factory Details hyperlink\" is not displayed in Container Details panel under Vendor Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "NotifyPartyName"), true, "*****test ID:12_\"NotifyParty Details hyperlink\" is not displayed in Container Details panel under Vendor Booking \"Booking\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "ContactDetails"), true, "*****test ID:12_\"Contact Details hyperlink\" is not displayed in Container Details panel under Vendor Booking \"Booking\" page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 56)
	public void test_14_InspectFactoryDetailsDropDown()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	ArrayList<String> listFactoryDetails = new ArrayList<String>();
    	listFactoryDetails = objAdjunoLIMAVendorBookingPOM.getDropDownList(strVendorBookingFormName, "FactoryName");
    	
    	nNotifyPartyDetailsDropDownList = objAdjunoLIMAVendorBookingPOM.getDropDownCount(strVendorBookingFormName, "NotifyPartyName");
    	listNotifyPartyDetails = new ArrayList<String>();
    	listNotifyPartyDetails = objAdjunoLIMAVendorBookingPOM.getDropDownList(strVendorBookingFormName, "NotifyPartyName");
    	
    	System.out.println("listNotifyPartyDetails:"+listNotifyPartyDetails);
    	ArrayList<String> CatalogFactorylist = new ArrayList<String>();
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strCatalogFormName, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkCatalog());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strCatalogFormName), true, "*****test ID:14_Welcome to Catalog page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkLightHouse()), true, "*****test ID:14_\"Lighthouse\" Hyper Link is not clicked under Welcome to Catalog page*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Name"), true, "*****test ID:14_\"Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Type"), true, "*****test ID:14_\"Type field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Description"), true, "*****test ID:14_\"Description field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"), true, "*****test ID:14_\"Associated Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"), true, "*****test ID:14_\"Associated Type field\" is not cleared under Catalog Item Page******");
    	
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strCatalogTypeFactoryDetails, strCatalogFormName, "Param_Type"), true, "*****test ID:14_Type fie;d is not set as \"Configuration\" in Catalog Item page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnApply()), true, "*****test ID:14_Apply button is not clicked in Catalog Item page*****");
    	wait(5000);
    	for(int i=0;i<=objAdjunoLIMAVendorBookingPOM.getLstCatalogItem().size()-1;i++)
    	{
    		String Itemlist = objAdjunoLIMAVendorBookingPOM.getLstCatalogItem().get(i).getText();
    		CatalogFactorylist.add(Itemlist);
    		objSoftAssert.assertEquals(listFactoryDetails.get(i).equals(CatalogFactorylist.get(i)), true,"*****test ID:14_Factory Details Drop Down values are not matching with Catalog Factory Details List*****");
    	}
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 57)
	public void test_15_InspectNotifyPartyDetailsDropDown()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	ArrayList<String> listCatalogNotifyPartDetails = new ArrayList<String>();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Name"), true, "*****test ID:15_\"Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Type"), true, "*****test ID:15_\"Type field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Description"), true, "*****test ID:15_\"Description field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"), true, "*****test ID:15_\"Associated Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"), true, "*****test ID:15_\"Associated Type field\" is not cleared under Catalog Item Page******");
    	
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strCatalogTypeNotifyPartyDetails, strCatalogFormName, "Param_Type"), true, "*****test ID:15_Type fie;d is not set as \"Configuration\" in Catalog Item page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnApply()), true, "*****test ID:23_Apply button is not clicked in Catalog Item page*****");
    	wait(2000);
    	
    	for(int i=0;i<=objAdjunoLIMAVendorBookingPOM.getLstCatalogItem().size()-1;i++)
    	{
    		String Itemlist = objAdjunoLIMAVendorBookingPOM.getLstCatalogItem().get(i).getText();
    		listCatalogNotifyPartDetails.add(Itemlist);
    		wait(3000);
    		objSoftAssert.assertEquals(listNotifyPartyDetails.get(i).trim().equalsIgnoreCase(listCatalogNotifyPartDetails.get(i).trim()), true,"*****test ID:14_NotifyParty Details Drop Down values are not matching with Catalog NotifyParty Details List*****");
    		
    	}
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 60)
	public void test_13_CheckForPODetailsGridInBookingPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:10_Vendor Booking Search page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:10_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:10_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:10_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
        
    	wait(3000);
    	for(int i = 0;i<=objAdjunoLIMAVendorBookingPOM.getLstPONumber().size()-1;i++)
    	{
    		if(objAdjunoLIMAVendorBookingPOM.getLstPONumber().get(i).getText().contains("LIMATEST"))
    		{
    			objAdjunoLIMAVendorBookingPOM.getLstCheckBox().get(i+2).click();
    			
    			break;
    		}
    		
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()),true,"*****test ID:10_Booking Chevron is not clicked in Vendor Booking Select page*****");
    	wait(2000);
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMAVendorBookingPOM.getCaptionsList(strVendorBookingFormName,"Grid_ProductDetails");
        strProduct = objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "LinkProduct");
        strPONumber = objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_PONumber");
        strMessage = objAdjunoLIMAVendorBookingPOM.verifyCaptionsONGrid(list,nlProductDetailsGrid,19);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:13_ProductDetailsGridCaption: "+strMessage+"*****");
        
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 65)
	public void test_14_CheckForShipmentDetailsPanel()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "ModeS"), true, "*****test ID:14_\"Mode Field\" is not displayed in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "OriginPortS"), true, "*****test ID:14_\"Origin Port Field\" is not displayed in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "DestinationPortS "), true, "*****test ID:14_\"Destination Port Field\" is not displayed in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "LoadingS"), true, "*****test ID:14_\"Loading Field\" is not displayed in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "ExFactoryS"), true, "*****test ID:14_\"ExFactory Field\" is not displayed in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "BuyingTermsS"), true, "*****test ID:14_\"Buying Terms Field\" is not displayed in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "FreightTermsS"), true, "*****test ID:14_\"Freight Terms Field\" is not displayed in Shipment Details panel under Booking page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 67)
	public void test_15_InspectOriginPortDropDownInShipmentDetailsPanel()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	int nDropDownList = objAdjunoLIMAVendorBookingPOM.getDropDownCount(strVendorBookingFormName, "OriginPortS");
    	ArrayList<String> list = new ArrayList<String>();
    	list = objAdjunoLIMAVendorBookingPOM.getDropDownList(strVendorBookingFormName, "OriginPortS");
    	System.out.println("oroiginPort:"+list);
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strPOManagerSearchFormName, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkPoManager());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strPOManagerSearchFormName), true, "*****test ID:15_PO Manager Search page is not loaded*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPONumber, strPOManagerSearchFormName, "PARAM_OrderNumber"), true, "*****test ID:15_Same PO Number used in Vendor Booking is not set in PO Number field under Search page in PO Manager*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChvEdit()), true, "*****test ID:15_Edit Chevron is not clicked under PO Manager Search page*****");
    	wait(3000);
    	strPOOriginDesc = objAdjunoLIMAVendorBookingPOM.getFieldValue(strPOManagerEditFormName, "OriginCountryDesc");
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strCatalogFormName, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkCatalog());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strCatalogFormName), true, "*****test ID:15_Welcome to Catalog page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkLightHouse()), true, "*****test ID:15_\"Lighthouse\" Hyper Link is not clicked under Welcome to Catalog page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Name"), true, "*****test ID:15_\"Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Type"), true, "*****test ID:15_\"Type field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Description"), true, "*****test ID:15_\"Description field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"), true, "*****test ID:15_\"Associated Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"), true, "*****test ID:15_\"Associated Type field\" is not cleared under Catalog Item Page******");
    	
    	for(int i = 0;i<=nDropDownList-1;i++)
    	{
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strCatalogTypeLocode, strCatalogFormName, "Param_Type"), true, "*****test ID:15_Type fie;d is not set as \"Configuration\" in Catalog Item page*****");
    	wait(3000);
    	System.out.println("---"+list.get(i));
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(list.get(i), strCatalogFormName, "Param_Name"), true, "*****test ID:15_Origin Port value is not set in Item field under Catalog Item page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnApply()), true, "*****test ID:15_Apply button is not clicked in Catalog Item page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getHyperLinkItemCatalog()), true, "*****test ID:15_Item HyperLink is not clicked in Catalog Item page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(strPOOriginDesc.equals(objAdjunoLIMAVendorBookingPOM.getWebElementCellValue(objAdjunoLIMAVendorBookingPOM.getCatalogAssociatedDescription())), true, "dvvnakjcn");
    	driver.navigate().back();
    	
    	}
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 70)
	public void test_15_16_VerifyColumnHeaderInCYConatinerRequirementsGrid()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	ArrayList<String> list = new ArrayList<String>();
    	wait(3000);
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:10_Vendor Booking Search page is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:10_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:10_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:10_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
        
    	wait(3000);
    	for(int i = 0;i<=objAdjunoLIMAVendorBookingPOM.getLstPONumber().size()-1;i++)
    	{
    		if(objAdjunoLIMAVendorBookingPOM.getLstPONumber().get(i).getText().contains("LIMATEST"))
    		{
    			objAdjunoLIMAVendorBookingPOM.getLstCheckBox().get(i+2).click();
    			
    			break;
    		}
    		
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()),true,"*****test ID:10_Booking Chevron is not clicked in Vendor Booking Select page*****");
    	wait(2000);
    	
        list = objAdjunoLIMAVendorBookingPOM.getCaptionsList(strVendorBookingFormName,"Containers");
        System.out.println(list);
        strMessage = objAdjunoLIMAVendorBookingPOM.verifyCaptionsONGrid(list,nlCYConatinerRequirementGrid,6);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:15_ProductDetailsGridCaption: "+strMessage+"*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getBtnDeleteRowCYContainerRequirement()), true, "*****test ID:16_\"Delete Row button\" is not displayed in CY Container Requirement panel under Booking page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "TotalContainers"), true, "*****test ID:16_\"Total COntainers field\" is not displayed in CY Container Requirement panel under Booking page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "PercentageFill"), true, "*****test ID:16_\"Percentage Fill field\" is not displayed in CY Container Requirement panel under Booking page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "EquipmentSelector"), true, "*****test ID:16_\"Equipment Selector link\" is not displayed in CY Container Requirement panel under Booking page*****");
        objSoftAssert.assertAll();
	}
	
	@Test(priority = 75)
	public void test_17_ContainerDetailsGridMandatoryFieldCheckInBookingPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	String strConatinerDetailsMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strVendorBookingFormName, "ContactDetails");
    	if(!strConatinerDetailsMandatoryMsg.equals(""))
    	{
    		if(strConatinerDetailsMandatoryMsg.contains("&#39;"))
    		{
    			strConatinerDetailsMandatoryMsg = strConatinerDetailsMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strConatinerDetailsMandatoryMsg.equalsIgnoreCase("> 'ContactDetails' is a required field"), true, "*****test ID:17_> 'ContactDetails' is a required field message is not displayed for Contact Details field  in VendorBooking \"Booking\" page*****");
    			System.out.println(strConatinerDetailsMandatoryMsg);
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:17_Contact Details field validation message is empty*****";	
    	}
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 80)
	public void test_18_ProductDetailsGridMandatoryFieldCheckInBookingPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdQty", ""), true, "*****test ID:18_Bkd Qty field is not cleared in Product Details Grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdCtns", ""), true, "*****test ID:18_Bkd Ctns field is not cleared in Product Details Grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdKgs", ""), true, "*****test ID:18_BkdKgs field is not cleared in Product Details Grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_KPI", ""), true, "*****test ID:18_PackType field is not cleared in Product Details Grid under Booking page*****");
    	
    	String strBkdQtyMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageGridElement(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdQty");
    	System.out.println("strBkdQtyMandatoryMsg:"+strBkdQtyMandatoryMsg);
    	String strBkdCtnsMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageGridElement(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdCtns");
    	String strBkdKgsMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageGridElement(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdKgs");
    	String strReasonCodeMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageGridElement(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_KPI");
    	String strPackTypeMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageGridElement(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_PackType");
    	
    	if(!strBkdQtyMandatoryMsg.equals(""))
    	{
    		if(strBkdQtyMandatoryMsg.contains("&#39;"))
    		{
    			strBkdQtyMandatoryMsg = strBkdQtyMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strBkdQtyMandatoryMsg.equalsIgnoreCase("> Product Min Tolerance has been exceeded, > 'Bkd Qty' is a required field"), true, "*****test ID:18_\"> Product Min Tolerance has been exceeded, > 'Bkd Qty' is a required field\" is not displayed for BkdQty field under Product Details Grid in VendorBooking \"Booking\" page*****");
    			System.out.println(strPackTypeMandatoryMsg);
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:18_BkdQty field validation message is not displayed in Product Details Grid*****";	
    	}
    	
    	if(!strBkdCtnsMandatoryMsg.equals(""))
    	{
    		if(strBkdCtnsMandatoryMsg.contains("&#39;"))
    		{
    			strBkdCtnsMandatoryMsg = strBkdCtnsMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strBkdCtnsMandatoryMsg.equalsIgnoreCase("> 'Bkd Ctns' is a required field"), true, "*****test ID:18_\"Bkd Ctns is a required field\" message is not displayed for BkdCtns field under Product Details Grid in VendorBooking \"Booking\" page*****");
    			System.out.println(strBkdCtnsMandatoryMsg);
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:18_BkdCtns field validation message is not displayed in Product Details Grid*****";	
    	}
    	
    	if(!strBkdKgsMandatoryMsg.equals(""))
    	{
    		if(strBkdKgsMandatoryMsg.contains("&#39;"))
    		{
    			strBkdKgsMandatoryMsg = strBkdKgsMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strBkdKgsMandatoryMsg.equalsIgnoreCase("> The value must be greater than zero, > 'Bkd Kgs' is a required field"), true, "*****test ID:18_\"> The value must be greater than zero, > 'Bkd Kgs' is a required field\" is not displayed for BkdKgs field under Product Details Grid in VendorBooking \"Booking\" page*****");
    			System.out.println(strBkdKgsMandatoryMsg);
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:18_BkdKgs field validation message is not displayed in Product Details Grid*****";	
    	}
    	
    	if(!strPackTypeMandatoryMsg.equals(""))
    	{
    		if(strPackTypeMandatoryMsg.contains("&#39;"))
    		{
    			strPackTypeMandatoryMsg = strPackTypeMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strPackTypeMandatoryMsg.equalsIgnoreCase("> 'Pack Type' is a required field"), true, "*****test ID:18_> 'PackType' is a required field message is not displayed for Pack Type field under Product Details Grid in VendorBooking \"Booking\" page*****");
    			System.out.println(strPackTypeMandatoryMsg);
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:17_PackType field validation message is not displayed in Product Details Grid*****";	
    	}
    	
    	if(!strReasonCodeMandatoryMsg.equals(""))
    	{
    		if(strReasonCodeMandatoryMsg.contains("&#39;"))
    		{
    			strReasonCodeMandatoryMsg = strReasonCodeMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strReasonCodeMandatoryMsg.equalsIgnoreCase("> 'Reason Code' is a required field"), true, "*****test ID:18_> 'Reason Code' is a required field message is not displayed for Reason Code field under Product Details Grid in VendorBooking \"Booking\" page*****");
    			System.out.println(strReasonCodeMandatoryMsg);
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:18_Reason Code field validation message is not displayed in Product Details Grid*****";	
    	}
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 85)
	public void test_19_ShipmentDetailsGridMandatoryFieldCheckInBookingPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strVendorBookingFormName, "OriginPortS"), true, "******test ID:19_Origin Port field is not cleared in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strVendorBookingFormName, "DestinationPortS"), true, "******test ID:19_Destination Port field is not cleared in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strVendorBookingFormName, "LoadingS"), true, "******test ID:19_Loading  field is not cleared in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strVendorBookingFormName, "ExFactoryS"), true, "******test ID:19_ExFactory  field is not cleared in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strVendorBookingFormName, "BuyingTermsS"), true, "******test ID:19_Buying Terms  field is not cleared in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strVendorBookingFormName, "FreightTermsS"), true, "******test ID:19_Freight Terms  field is not cleared in Shipment Details panel under Booking page*****");
    	
    	String strOriginPortMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strVendorBookingFormName,"OriginPortS");
    	String strExFactoryMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strVendorBookingFormName, "ExFactoryS");
    	String strBuyingTermsMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strVendorBookingFormName, "BuyingTermsS");
    	String strFreightTermsMandatoryMsg = objAdjunoLIMAVendorBookingPOM.getValidationMessageElement(strVendorBookingFormName, "FreightTermsS");
    	
    	if(!strOriginPortMandatoryMsg.equals(""))
    	{
    		if(strOriginPortMandatoryMsg.contains("&#39;"))
    		{
    			strOriginPortMandatoryMsg = strOriginPortMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strOriginPortMandatoryMsg.equalsIgnoreCase("> 'OriginPortS' is a required field"), true, "*****test ID:19_> 'OriginPortS' is a required field message is not displayed for OriginPort field under Shipment Details panel in VendorBooking \"Booking\" page*****");
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:17_OriginPortS field validation message is not displayed in Shipment Details panel*****";	
    	}
    	if(!strExFactoryMandatoryMsg.equals(""))
    	{
    		if(strExFactoryMandatoryMsg.contains("&#39;"))
    		{
    			strExFactoryMandatoryMsg = strExFactoryMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strExFactoryMandatoryMsg.equalsIgnoreCase("> 'ExFactoryS' is a required field"), true, "*****test ID:19_> 'ExFactoryS' is a required field message is not displayed for ExFactory Date field under Shipment Details panel in VendorBooking \"Booking\" page*****");
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:17_ExFactoryS field validation message is not displayed in Shipment Details panel*****";	
    	}
    	
    	if(!strBuyingTermsMandatoryMsg.equals(""))
    	{
    		if(strBuyingTermsMandatoryMsg.contains("&#39;"))
    		{
    			strBuyingTermsMandatoryMsg = strBuyingTermsMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strBuyingTermsMandatoryMsg.equalsIgnoreCase("> 'BuyingTermsS' is a required field"), true, "*****test ID:19_> 'BuyingTermsS' is a required field message is not displayed for BuyingTerms field under Shipment Details panel in VendorBooking \"Booking\" page*****");
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:17_BuyingTermsS field validation message is not displayed in Shipment Details panel*****";	
    	}
    	
    	if(!strFreightTermsMandatoryMsg.equals(""))
    	{
    		if(strFreightTermsMandatoryMsg.contains("&#39;"))
    		{
    			strFreightTermsMandatoryMsg = strFreightTermsMandatoryMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strFreightTermsMandatoryMsg.equalsIgnoreCase("> 'FreightTermsS' is a required field"), true, "*****test ID:19_> 'FreightTermsS' is a required field message is not displayed for FreightTerms field under Shipment Details panel in VendorBooking \"Booking\" page*****");
    		}	
    	}
    	
    	else
    	{
    		strMessage = strMessage + "*****test ID:17_FreightTermsS field validation message is not displayed in Shipment Details panel*****";	
    	}
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 90)
	public void test_21_SetFieldValuesInContainerDetailsPanel()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strFactoryDetails, strVendorBookingFormName, "FactoryName"), true, "*****test ID:21_Value is not in Factory Name field under Contact Details Panel in Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strNotifyPartyDetails, strVendorBookingFormName, "NotifyPartyName"), true, "*****test ID:21_Value is not set in NotifyPartyDetails field under Contact Details Panel in Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strContactDetails, strVendorBookingFormName, "ContactDetails"), true, "*****test ID:21_Value is not set in Contact Details field under Contact Details panel in Booking page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 95)
	public void test_22_VerifyVendorDetailsHyperLink()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	String strMessage="";
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getHyperLinkVentorDetails()), true, "*****test ID:22_Vendor Details hyperLink do not exist in Contact Details panel under Booking page******");
    	String strhref = objAdjunoLIMAVendorBookingPOM.getHyperLinkVentorDetails().getAttribute("onclick");
    	if(!isNullOrBlank(strhref))
    	{
    	
    	}
    	else
    	{
    		strMessage = "*****test ID:22_"+strMessage + " Vendor Details is not a hyperLink in Contact Detals Panel under Booking page******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objAdjunoLIMAVendorBookingPOM.getHyperLinkVentorDetails().click();
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getVendorDetailsPopup()), true, "*****test ID:22_Vendor Details pop up is not displayed in Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_AddressLine1"), true, "*****test ID:_22_Address Line 1 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_AddressLine2"), true, "*****test ID:_22_Address Line 2 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_AddressLine3"), true, "*****test ID:_22_Address Line 3 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_AddressLine4"), true, "*****test ID:_22_Address Line 4 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_AddressLine5"), true, "*****test ID:_22_Address Line 5 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Country"), true, "*****test ID:_22_Country field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_PostCode"), true, "*****test ID:_22_PostCode field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_ContactName"), true, "*****test ID:_22_Contact Name field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Phone1"), true, "*****test ID:_22_Telephone  1 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Phone2"), true, "*****test ID:_22_Telephone  2 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Fax1"), true, "*****test ID:_22_Fax Number 1 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Fax2"), true, "*****test ID:_22_Fax Number 2 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Email1"), true, "*****test ID:_22_Email Address 1 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Supplier_Email2"), true, "*****test ID:_22_Email Address 2 field is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "VendorUpdateButton"), true, "*****test ID:_22_OK Button is not displayed in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "VendorCancelButton"), true, "*****test ID:_22_Cancel Button is not displayed in Vendor Details pop up under Booking page*****");
    	
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnClose()), true, "*****test ID:22_Close button is not clicked in Vendor Details pop up under Booking page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_AddressLine1"), true, "****test ID:22_Address Line 1 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_AddressLine2"), true, "****test ID:22_Address Line 2 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_AddressLine3"), true, "****test ID:22_Address Line 3 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_AddressLine4"), true, "****test ID:22_Address Line 4 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_AddressLine5"), true, "****test ID:22_Address Line 5 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Country"), true, "****test ID:22_Country field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_PostCode"), true, "****test ID:22_PostCode field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_ContactName"), true, "****test ID:22_Contact Name 1 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Phone1"), true, "****test ID:22_Telephone 1 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Phone2"), true, "****test ID:22_Telephone 2 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Fax1"), true, "****test ID:22_Fax Number 1 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Fax2"), true, "****test ID:22_Fax Number 2 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Email1"), true, "****test ID:22_Email Address 1 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInEditableMode(strVendorBookingFormName, "Supplier_Email2"), true, "****test ID:22_Email Address 2 field is not in Editable Mode in Vendor Details pop up under Booking page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 97)
	public void test_23_VerifyVendorAddressIsEditableInCatalog()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strCatalogFormName, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkCatalog());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strCatalogFormName), true, "*****test ID:23_Welcome to Catalog page is not displayed*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkLightHouse()), true, "*****test ID:23_\"Lighthouse\" Hyper Link is not clicked under Welcome to Catalog page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Name"), true, "*****test ID:23_\"Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Type"), true, "*****test ID:23_\"Type field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_Description"), true, "*****test ID:23_\"Description field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"), true, "*****test ID:23_\"Associated Item field\" is not cleared under Catalog Item Page******");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"), true, "*****test ID:23_\"Associated Type field\" is not cleared under Catalog Item Page******");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strTypeConfiguration, strCatalogFormName, "Param_Type"), true, "*****test ID:23_Type fie;d is not set as \"Configuration\" in Catalog Item page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnApply()), true, "*****test ID:23_Apply button is not clicked in Catalog Item page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkConfiguration()), true, "*****test ID:23_Configuration HyperLink is not clicked in Catalog Item page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnApply()), true, "*****test ID:23_Apply button is not clicked in View Configuration Item 'Configuration' page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strCatalogConfigurationFormName), true, "*****test ID:23_\"Edit An Item\" page is not loaded after clicking Configuration link in View Configuration Item 'Configuration' page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strCatalogConfigurationFormName, "TabPanel_Configuration", "TabItem_OrderConfiguration"), true, "*****test ID:23_Order tab is not clicked under Edit an Item page under Catalog*****");
    	wait(3000);
//    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strCatalogConfigurationFormName, "TabPanel_Order_Settings"), true, "*****test ID:23_Module Settings Panel is not displayed when Orders Tab is clicked in Edit an Item page under Catalog*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strCatalogConfigurationFormName, "TabPanel_Order_Settings", "TabPanel_Order_VendorBooking"), true, "*****test ID:23_VendorBooking tab is not clicked under Orders Tab in Edit an Item page under Catalog*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getElement(strCatalogConfigurationFormName, "VendorBooking_IsEditableSupplierName").isSelected(), true, "*****test ID:23_Vendor Address is not selected under Editable Fields panel in Vendor Booking Panel under Edit an Item page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strCatalogConfigurationFormName, "TabPanelMarkNumbers", "TabPanelMarkNumbers_Sea"), true, "*****test ID:23_Sea tab is not clicked under Default Marks & Numbers panel in Edit an Item page under Catalog*****");
    	strCatalogSeaMarksAndNumbersMsg = objAdjunoLIMAVendorBookingPOM.getFieldValue(strCatalogConfigurationFormName, "MarkersNumbers_Sea");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strCatalogConfigurationFormName, "TabPanelMarkNumbers", "TabPanelMarkNumbers_Air"), true, "*****test ID:23_Air tab is not clicked under Default Marks & Numbers panel in Edit an Item page under Catalog*****");
    	strCatalogAirMarksAndNumbersMsg = objAdjunoLIMAVendorBookingPOM.getFieldValue(strCatalogConfigurationFormName, "MarkersNumbers_Air");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strCatalogConfigurationFormName, "TabPanelMarkNumbers", "TabPanelMarkNumbers_Road"), true, "*****test ID:23_Air tab is not clicked under Default Marks & Numbers panel in Edit an Item page under Catalog*****");
    	strCatalogRoadMarksAndNumbersMsg = objAdjunoLIMAVendorBookingPOM.getFieldValue(strCatalogConfigurationFormName, "MarkersNumbers_Road");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strCatalogConfigurationFormName, "TabPanelMarkNumbers", "TabPanelMarkNumbers_AirSea"), true, "*****test ID:23_Air tab is not clicked under Default Marks & Numbers panel in Edit an Item page under Catalog*****");
    	strCatalogSea_AirMarksAndNumbersMsg = objAdjunoLIMAVendorBookingPOM.getFieldValue(strCatalogConfigurationFormName, "MarkersNumbers_AirSea");
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:23_Vendor Booking Search page is not displayed*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:23_Status is not set as Pending in \"Booking Status field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0),strSearchFormNameVendorBooking,"PARAM_ShipmentDue"), true, "*****test ID:23_Shipment Due value is not set in \"Shipment Due field\" in Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:23_\"Booking Chevron\" is not clicked under Vendor Booking Select Chevron*****");
        
    	wait(3000);
    	for(int i = 0;i<=objAdjunoLIMAVendorBookingPOM.getLstPONumber().size()-1;i++)
    	{
    		if(objAdjunoLIMAVendorBookingPOM.getLstPONumber().get(i).getText().contains("LIMATEST"))
    		{
    			objAdjunoLIMAVendorBookingPOM.getLstCheckBox().get(i+2).click();
    			
    			break;
    		}
    		
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevBooking1()),true,"*****test ID:23_Booking Chevron is not clicked in Vendor Booking Select page*****");
    	
    	objSoftAssert.assertAll();
    	
	}
	
	@Test(priority = 100)
	public void test_23_VerifyConsigneeDetailsHyperLink()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	String strMessage="";
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getHypeLinkConsigneeDetails()), true, "*****test ID:23_Consignee Details hyperLink do not exist in Contact Details panel under Booking page******");
    	String strhref = objAdjunoLIMAVendorBookingPOM.getHypeLinkConsigneeDetails().getAttribute("onclick");
    	if(!isNullOrBlank(strhref))
    	{
    	
    	}
    	else
    	{
    		strMessage = "*****test ID:23_"+strMessage + " Consignee Details is not a hyperLink in Contact Detals Panel under Booking page******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objAdjunoLIMAVendorBookingPOM.getHypeLinkConsigneeDetails().click();
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getConsigneeDetailsPopUp()), true, "*****test ID:23_Conginee Details pop up is not displayed in Booking page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_AddressLine1"), true, "*****test ID:_23_Address Line 1 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_AddressLine2"), true, "*****test ID:_23_Address Line 2 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_AddressLine3"), true, "*****test ID:_23_Address Line 3 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_AddressLine4"), true, "*****test ID:_23_Address Line 4 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_AddressLine5"), true, "*****test ID:_23_Address Line 5 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Country"), true, "*****test ID:_23_Country field is not displayed in Vendor Consignee pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_PostCode"), true, "*****test ID:_23_PostCode field is not displayed in Vendor Consignee pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_ContactName"), true, "*****test ID:_23_Contact Name field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Phone1"), true, "*****test ID:_23_Telephone  1 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Phone2"), true, "*****test ID:_23_Telephone  2 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Fax1"), true, "*****test ID:_23_Fax Number 1 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Fax2"), true, "*****test ID:_23_Fax Number 2 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Email1"), true, "*****test ID:_23_Email Address 1 field is not displayed in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesFieldsExist(strVendorBookingFormName, "Consignee_Email2"), true, "*****test ID:_23_Email Address 1 field is not displayed in Consignee Details pop up under Booking page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_AddressLine1"), true, "*****test ID:_23_Address Line 1 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_AddressLine2"), true, "*****test ID:_23_Address Line 2 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_AddressLine3"), true, "*****test ID:_23_Address Line 3 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_AddressLine4"), true, "*****test ID:_23_Address Line 4 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_AddressLine5"), true, "*****test ID:_23_Address Line 5 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Country"), true, "*****test ID:_23_Country field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_PostCode"), true, "*****test ID:_23_PostCode field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Phone1"), true, "*****test ID:_23_Telephone  1 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Phone2"), true, "*****test ID:_23_Telephone  2 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Fax1"), true, "*****test ID:_23_Fax Number 1 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Fax2"), true, "*****test ID:_23_Fax Number 2 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Email1"), true, "*****test ID:_23_Email Address 1 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_Email2"), true, "*****test ID:_23_Email Address 2 field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyElementIsInReadOnlyMode(strVendorBookingFormName, "Consignee_ContactName"), true, "*****test ID:_23_Contact Name field is not in ReadOnly mode in Consignee Details pop up under Booking page*****");
    	wait(6000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnConsigneeDetailsCloseButton()), true, "*****test ID:23_Close button is not clicked in Consignee Details pop up under Booking page*****");
    	objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 105)
	public void test_24_VerifyProductDetailsGridFunctionality()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	strOpenQty = objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_OpenQty");
    	 
    	
    	strBkdNetKgs = strOpenQty+".00";
    	strBkdKg = strOpenQty+".00";
    	
    	strHSCode = objAdjunoLIMAVendorBookingPOM.getRandomString();
    	System.out.println("OpenQty:"+strOpenQty);
    	wait(3000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strFactoryDetails, strVendorBookingFormName, "FactoryName"), true, "*****test ID:24_Value is not in Factory Name field under Contact Details Panel in Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strNotifyPartyDetails, strVendorBookingFormName, "NotifyPartyName"), true, "*****test ID:24_Value is not set in NotifyPartyDetails field under Contact Details Panel in Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strContactDetails, strVendorBookingFormName, "ContactDetails"), true, "*****test ID:24_Value is not set in Contact Details field under Contact Details panel in Booking page*****");
    	
     	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdQty", strOpenQty), true, "*****test ID:24_Value is not set in Bkd Qty field in Product Details grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdCtns", strBkdCtns), true, "*****test ID:24_Value is not set in Bkd Ctns field in Product Details grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdKgs", strBkdKg), true, "*****test ID:24_Value is not set in Bkd Kgs field in Product Details grid under Booking page*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdNetKgs", strBkdNetKgs), true, "*****test ID:24_Value is not set in BkdNetKgs  field in Product Details grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdCBM" , strBkdCBM), true, "*****test ID:24_Value is not set in Bkd CBM field in Product Details grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_PackType", strPackType), true, "*****test ID:24_Value is not set in PackType  field in Product Details grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_KPI", strReasonCode), true, "*****test ID:24_Value is not set in  Reason Code field in Product Details grid under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForGridCell(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_HSCode", strHSCode), true, "*****test ID:24_Value is not set in HSCode field in Product Details grid under Booking page*****");
    
    	wait(2000);
    	objSoftAssert.assertEquals(strOpenQty.equals(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdQty")), true, "*****test ID:24_Value set and the value returned from BkdQty field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strBkdCtns.equals(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdCtns")), true, "*****test ID:24_Value set and the value returned from BkdCtns field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strBkdKg.contains(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdKgs")), true, "*****test ID:24_Value set and the value returned from BkdKgs field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strBkdNetKgs.contains(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdNetKgs")), true, "*****test ID:24_Value set and the value returned from BkdNetKgs field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strBkdCBM.contains(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_BkdCBM")), true, "*****test ID:24_Value set and the value returned from BkdCBM field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strPackType.equals(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_PackType")), true, "*****test ID:24_Value set and the value returned from PackType field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strReasonCode.equals(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_KPI")), true, "*****test ID:24_Value set and the value returned from ReasonCode field in Product Details grid is wrong under booking page*****");
    	objSoftAssert.assertEquals(strHSCode.equals(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingFormName, "Grid_ProductDetails", 0, "Product_HSCode")), true, "*****test ID:24_Value set and the value returned from HSCode field in Product Details grid is wrong under booking page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 110)
	public void test_25_ChangeModeFieldInShipmentDetailsPanel()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	String strMessage1 = "";
    	String strMessage2 = "";
    	
    	wait(2000);
    	strPreviousModeValue = objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strMode, strVendorBookingFormName, "ModeS"), true, "*****test ID:25_Mode value is not chnaged under \"Shipment Details\" panel under Booking page*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getChangingModePopup()), true, "*****test ID:25_Changing Mode pop up is not displayed in Booking page*****");
    	strMessage1 = objAdjunoLIMAVendorBookingPOM.getChangeModeValidationMsg1().getText();
    	strMessage2 = objAdjunoLIMAVendorBookingPOM.getChangeModeValidationMsg2().getText();
    	strMessage = strMessage1.concat(strMessage2);
    	
    	objSoftAssert.assertEquals(strMessage.equals("Are you sure you want to change the mode?You may lose existing data from Marks and Numbers field."), true, "*****test ID:25_Validation Message in Changing Mode pop up is wrong*****");
    	
    	objSoftAssert.assertAll();
    	
	}
	
	@Test(priority = 115)
	public void test_26_ClickNoButtonInChangeModePopUp()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strPreviousModeValue1 = "";
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButton(strVendorBookingFormName, "Button1"), true, "*****test ID:26_No button is not clicked in Changing mode pop up under Booking page******");
    	wait(3000);
    	strPreviousModeValue1 = objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS");
    	System.out.println("strPreviousModeValue:"+strPreviousModeValue);
    	System.out.println("strPreviousModeValue1:"+strPreviousModeValue1);
    	objSoftAssert.assertEquals(strPreviousModeValue.equals(strPreviousModeValue1), true, "*****test ID:26_Mode value is changed even after clicking No in Changing Mode popup  in SHipment Details Panel under Booking page*****");
    	if(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS").equals("Air"))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "MarksAndNumbersS").equals(strCatalogAirMarksAndNumbersMsg), true, "*****test ID:26_Marks and Numbers message is wrong when Mode is Air*****" );
    	}
    	else if(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS").equals("Sea"))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "MarksAndNumbersS").equals(strCatalogSeaMarksAndNumbersMsg), true, "*****test ID:26_Marks and Numbers message is wrong when Mode is Sea*****" );
    	}
    	else if(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS").equals("Road"))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "MarksAndNumbersS").equals(strCatalogRoadMarksAndNumbersMsg), true, "*****test ID:26_Marks and Numbers message is wrong when Mode is Road*****" );
    	}
    	else if(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS").equals("Sea/Air"))
    	{
    		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "MarksAndNumbersS").equals(strCatalogSea_AirMarksAndNumbersMsg), true, "*****test ID:26_Marks and Numbers message is wrong when Mode is Sea/Air*****" );
    	}
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 120)
	public void test_27_ClickYesButtonInChangeModePopUp()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strPresentModeValue = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strMode, strVendorBookingFormName, "ModeS"), true, "*****test ID:27_Mode value is not changed under \"Shipment Details\" panel under Booking page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getChangingModePopup()), true, "*****test ID:27_Changing Mode pop up is not displayed in Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButton(strVendorBookingFormName, "Button2"), true, "*****test ID:27_Yes button is not clicked in Changing mode pop up under Booking page******");
    	strPresentModeValue = objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ModeS");
    	wait(3000);
    	System.out.println("Mode"+strMode);
    	System.out.println("strPresentModeValue:"+strPresentModeValue);
    	objSoftAssert.assertEquals(strMode.equals(strPresentModeValue), true, "*****test ID:27_Mode Value is not changed in Shipment Details panel under Bookig page*****");
    	
    	objSoftAssert.assertAll();
    	
	}
	
	@Test(priority = 123)
	public void test_28_InspectOriginPortDropDown()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	ArrayList<String> list = new ArrayList<String>();
    	list = objAdjunoLIMAVendorBookingPOM.getDropDownList(strVendorBookingFormName, "OriginPortS");
    	System.out.println("oroiginPort:"+list);
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 125)
	public void test_28_SetDataInAllFieldsUnderShipmentDetailsPanel()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	
    	System.out.println("strOriginPort:"+strOriginPort);
    	strLoding = objAdjunoLIMAVendorBookingPOM.getDropDownValue(strVendorBookingFormName, "LoadingS", 0);
    	strBuyingTerms = objAdjunoLIMAVendorBookingPOM.getDropDownValue(strVendorBookingFormName, "BuyingTermsS", 0);
    	strFreightTerms = objAdjunoLIMAVendorBookingPOM.getDropDownValue(strVendorBookingFormName, "FreightTermsS", 0);
    	strExFactoryDate = objAdjunoLIMAVendorBookingPOM.setPresentDate("dd MMM YYYY");
    	
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strLoding, strVendorBookingFormName, "LoadingS"), true, "*****test ID:28_Value is not set in Loading field in Shipment Details Panel under Booking Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strExFactoryDate, strVendorBookingFormName, "ExFactoryS"), true, "*****test ID:28_Value is not set in Ex Factory field in Shipment Details Panel under Booking Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strBuyingTerms, strVendorBookingFormName, "BuyingTermsS"), true, "*****test ID:28_Value is not set in BuyingTerms field in Shipment Details Panel under Booking Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strFreightTerms, strVendorBookingFormName, "FreightTermsS"), true, "*****test ID:28_Value is not set in FreightTerms field in Shipment Details Panel under Booking Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strOriginPort, strVendorBookingFormName, "OriginPortS"), true, "*****test ID:28_Value is not set in Origin Port field in Shipment Details Panel under Booking Page*****");
    	
    	objSoftAssert.assertEquals(strLoding.equals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "LoadingS")), true, "*****test ID:28_Value set and the value returned from Loading field in Shipment Details panel is wrong under booking page*****");
    	System.out.println("strExFactoryDate: "+objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ExFactoryS"));
    	System.out.println("OriginPort: "+objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "OriginPortS"));
    	
//    	objSoftAssert.assertEquals(strExFactoryDate.equals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "ExFactoryS")), true, "*****test ID:28_Value set and the value returned from Ex Factory date field in Shipment Details panel is wrong under booking page*****");
    	objSoftAssert.assertEquals(strBuyingTerms.equals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "BuyingTermsS")), true, "*****test ID:28_Value set and the value returned from Buying Trems field in Shipment Details panel is wrong under booking page*****");
    	objSoftAssert.assertEquals(strFreightTerms.equals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "FreightTermsS")), true, "*****test ID:28_Value set and the value returned from FreightTerms field in Shipment Details panel is wrong under booking page*****");
//    	objSoftAssert.assertEquals(strOriginPort.equals(objAdjunoLIMAVendorBookingPOM.getFieldValue(strVendorBookingFormName, "OriginPortS")), true, "*****test ID:28_Value set and the value returned from Origin Port field in Shipment Details panel is wrong under booking page*****");
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 130)
	public void test_29_VerifyAddtionalRequirementHyperLinkInProductsGrid()
	{
		
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	String strMessage="";
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getAdditionaliRequirementHyperLink()), true, "*****test ID:29_Additional Requirement hyperLink do not exist in Product Details grid under Booking page******");
    	String strhref = objAdjunoLIMAVendorBookingPOM.getAdditionaliRequirementHyperLink().getAttribute("onclick");
    	if(!isNullOrBlank(strhref))
    	{
    	 objAdjunoLIMAVendorBookingPOM.getAdditionaliRequirementHyperLink().click();
    	}
    	else
    	{
    		strMessage = "*****test ID:29_"+strMessage + " Additional Requirements is not a hyperLink in Product Detals Grid under Booking page******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	wait(5000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getAddtionalRequirementPopUp()), true, "*****test ID:29_Addtional Requirements pop up is not displayed under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "AvailableAdditionalRequirements"), true, "*****test ID:29_\"Available column\" is not displayed in in Addtional Requirement pop up under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "SelectedAdditionalRequirements"), true, "*****test ID:29_\"Selected column\" is not displayed in in Addtional Requirement pop up under Booking page*****");
    	objSoftAssert.assertAll();
    	
	}

	
	@Test(priority = 135)
	public void test_34_CloseAdditionalRequirementPopUp()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	wait(3000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnAdditionalRequirementClose()), true, "*****test ID:34_Additional Requirement pop up is not closed in Booking page*****");
    	
    	
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 140)
	public void test_35_36_37_38_39_40_VerifyCYContainerRequirementGridFunctionality()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strLoadingCY_CY, strVendorBookingFormName, "LoadingS"), true, "*****test ID:35_Loading field is not as \"CY/CY\" in Shipment Details panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Grid_EquipmentSelection"), true, "*****test ID:35:Equipment Seletor pop up is not displayed when Loding field is set as CY/CY in Shipment panel under Booking page*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMAVendorBookingPOM.getCaptionsList(strVendorBookingFormName,"Grid_EquipmentSelection");
        System.out.println(list);
        strMessage = objAdjunoLIMAVendorBookingPOM.verifyCaptionsONGrid(list,nlEquipmentSelectorGrid,3);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:35_EquipmentSelectorGridCaption: "+strMessage+"*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Button_Submit"), true, "*****test ID:35_Use Suggestions button is not dispalyed in Equipment Selector popup under Booking page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnEquipmentSelectorCloseButton()), true, "*****test ID:36_Equipment Selector pop up is not closed under Booking page*****");
        wait(3000);
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getBtnAddRowCYContainerRequirementPanel()), true, "*****test ID:36_Add Row button is not displayed in CY Container Requirement panel under Booking page*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnAddRowCYContainerRequirementPanel()), true, "*****test ID:37_Add Row button is not clicked in CY Conatiner Requirement  Grid under Boking page*****");
        int nRows = objAdjunoLIMAVendorBookingPOM.getNoOfRowsGrid(strVendorBookingFormName, "Containers");
        
        String strhref = objAdjunoLIMAVendorBookingPOM.getHyperLinkEquipmentSelector().getAttribute("onclick");
    	if(!isNullOrBlank(strhref))
    	{
    	 objAdjunoLIMAVendorBookingPOM.getHyperLinkEquipmentSelector().click();
    	}
    	else
    	{
    		strMessage = "*****test ID:36_"+strMessage + " Equipment Selector is not a hyperLink in CY Container Requirement panel under Booking page******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.checkDoesElementExist(strVendorBookingFormName, "Grid_EquipmentSelection"), true, "*****test ID:38:Equipment Seletor pop up is not displayed when Loding field is set as CY/CY in Shipment panel under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnEquipmentSelectorCloseButton()), true, "*****test ID:38_Equipment Selector pop up is not closed under Booking page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnDeleteRowCYContainerRequirement()), true, "*****test ID:39_Delete Row button is not clicked in CY Conatiner Requirement panel under Booking page*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getDeleteRowPopUp()), true, "*****test ID:39_Delete Row pop up is not displayed under Booking page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnYes()), true, "*****test ID:40_Yes Button is not clicked in Delete Row pop up under Booking page*****");
    	wait(3000);
    	
    	int nRows1 = objAdjunoLIMAVendorBookingPOM.getNoOfRowsGrid(strVendorBookingFormName, "Containers");
    	objSoftAssert.assertEquals(nRows1 == nRows-1, true, "*****test ID:40_Row is not deleted in CY Container Requirement panel under Booking page*****");
    	
    	objSoftAssert.assertAll(); 
	}
	
	@Test(priority = 145)
	public void test_41_42_43_VerifyCompleteFunctionality()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strLoding, strVendorBookingFormName, "LoadingS"), true, "*****test ID:41_Loading field is not as \"CFS/CY\" in Shipment Details panel under Booking page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevComplete1()), true, "*****test ID:41_Complete Chevron is not clicked in Booking page after entering all the fields*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strCompletePageFromName), true, "*****test ID:41_Complete page is not loaded after Complete chevron is not clicked in Booking page*****");
    	wait(6000);
    	strMessage = objAdjunoLIMAVendorBookingPOM.getValueFromReadOnlyMode(objAdjunoLIMAVendorBookingPOM.getStrCompleteMsgID());
    	System.out.println("strMessage: "+strMessage);
    	wait(5000);
    	objSoftAssert.assertEquals(strMessage.contains("1 Product has been booked."), true, "*****test ID:42_\"1 Product has been booked\" message is not displayed in Complete Chevron.");
    	
    	String[] temp = strMessage.split("Vendor Booking ID: ");
    	VendorBookingID =  temp[1];
    	System.out.println(VendorBookingID);
    	objAdjunoLIMAVendorBookingPOM.getBtnViewDetail().click();
    	
    	wait(5000);
    	String strVendorBookedDetails = objAdjunoLIMAVendorBookingPOM.getTxtViewDetail().getText(); 
    	
    //	String strTrackReferenceNo = strDespatchDetails.substring(strDespatchDetails.indexOf("LIMAUAT"),strDespatchDetails.indexOf("].", strDespatchDetails.indexOf("LIMAUAT")));
    	String[] vals = strVendorBookedDetails.split("achieved on track ");
    
    	vals[1] = vals[1].replace("'", " ");
    	String valss[] = vals[1].split(" ",22);
    	
    	strTrackNO = valss[1];
    	System.out.println("track ref num: - " + strTrackNO);
    //	System.out.println("track ref num: - " + strTrackReferenceNo);
    	wait(5000);
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking,objAdjunoLIMAVendorBookingPOM.getLnkTrack(),objAdjunoLIMAVendorBookingPOM.getLnkEdit());
        wait(2000);

        
    	objSoftAssert.assertAll();
	}

	@Test(priority = 150)
	public void test_44_VerifyPurchaseOrderPage()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getFldRefNo()), true,"****test ID:43_Reference field is not found in \"Edit or Create track page\"*****");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValueForWebElement(objAdjunoLIMAVendorBookingPOM.getFldRefNo(),strTrackNO), true,"****test ID:43_track No is not set in Reference field under \"Edit or Create track page\"*****");
        }else{
        	strMessage =strMessage + " Track number is empty ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnTrackApply()), true,"****test ID:43_Apply button is not clicked in \"Edit or Create track page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getPurchaseOrderPage()), true, "*****test ID:43_Purchase Order Track page is not displayed*****");
        
        objSoftAssert.assertAll();   
	}
	
	@Test(priority = 155)
	public void test_45_VerifyLatestShipmentRelatedLinkInPurchaseOrderPage() throws ParseException
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	long min = objAdjunoLIMAVendorBookingPOM.getTrackValue(strLIMAUserName);
        if(min<=350){
                     
        }else
        {
            strMessage = "****test ID:34_Not a todays date*****";
        }
                 
        objSoftAssert.assertEquals(strMessage.equals(""), true,"****test ID:45_"+strMessage+"*****"); 
        wait(2000);
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkTrackShipmentRelated()), true, "*****test ID:45_Latest Updated \"Shipment Related\" link is not clicked under Purchase Order Track page******");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getShipmentRelatedPage()), true, "*****test ID:45_Shipment Related page is not displayed after clicking on lated updated link in Puchase Order Page*****");
        
        
        //pending
        objSoftAssert.assertAll();   
	}
	
	@Test(priority = 160)
	public void test_46_CheckForBookedPOStatusFunctionality()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkFind(), objAdjunoLIMAVendorBookingPOM.getLnkPOStatus());
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strPOStatusFormName), true, "****test ID:46_PO Status Page is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "****test ID:46_PO Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strProduct, strPOStatusFormName, "Param_17Item"), true, "****test ID:46_Product Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnRun()), true, "****test ID:46_Run button is not clicked in PO Status page*****");
        
        try{
        	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getPoStatusReport()), true, "****test ID:22_PO Status report page is not loaded*****");
        	
        	String strStatus = objAdjunoLIMAVendorBookingPOM.getWebElementCellValue(objAdjunoLIMAVendorBookingPOM.getPoStatus_Status());
        	
        	objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Vendor Booked"), true, "****test ID:46_Status mismatch*****");	
        	}
        	catch(NoSuchElementException e)
        	{
        		strMessage = "PO Status report is not displayed";
        	}
        	objSoftAssert.assertEquals(strMessage.equals(""), true, "****test ID:46_"+strMessage+"*****");
        	
        objSoftAssert.assertAll();
	}
	
	@Test(priority = 165)
	public void test_45_VerifyBookedPOInAwaitingStatus()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:45_Vendor Booking Search page is not displayed*****");	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPONumber, strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "*****test ID:_45_Booked PO Number is not set in PO Number field under Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPendingStatus, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:45_Status is not set as Pending in Vendor Booking Serach page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:45_Select Chevron is not clicked under Vendor Booking Search page*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getErrMsgNoRecords()), true, "*****test ID:45_Vendor Booked PO Number is displayed uder Pending Status*****");
    	objSoftAssert.assertAll();
	}

	@Test(priority = 170)
	public void test_46_47_VerifyBookedPOInBookedStatus()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	wait(5000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPONumber, strSearchFormNameVendorBooking, "PARAM_OrderNumber"), true, "*****test ID:_46_Booked PO Number is not set in PO Number field under Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strStatusBooked, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:46_Status is not set as Pending in Vendor Booking Serach page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButton(strSearchFormNameVendorBooking, "Button_RefineSearch"), true, "*****test ID:46_Refine Search button is not clicked under Select Chevron in Vendor Booking*****");
    	wait(3000);
    	objSoftAssert.assertEquals(strPONumber.equals(objAdjunoLIMAVendorBookingPOM.getTrPONumber().getText()), true, "*****test ID:46_Booked PO Number is not displayed under Booked Status in Vendor Booking Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnSearchPanelArrowBtn()), true, "*****test ID_47_Arrow buuton is not clicked in VendorBooking Select page*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getElement(strSearchFormNameVendorBooking, "Button_RefineSearch").isEnabled(), true, "*****test ID_47_Refine Search button is not enabled when Status is set as Booked in Vendor Booking Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getElement(strSearchFormNameVendorBooking, "Button_CancelBooking").isEnabled(), true, "*****test ID_47_Cancel Booking button is not enabled when Status is set as Booked in Vendor Booking Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.getElement(strSearchFormNameVendorBooking, "Button_ChangeRequest").isEnabled(), true, "*****test ID_47_Change Request button is not enabled when Status is set as Booked in Vendor Booking Select page*****");
    	objSoftAssert.assertAll();
	}
	
	@Test(priority = 175)
	public void test_48_49_50_VerifyCancelBookingFunctionality()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkTools(), objAdjunoLIMAVendorBookingPOM.getLnkVendorBooking());
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:48_Vendor Booking Search page is not displayed*****");	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(objAdjunoLIMAVendorBookingPOM.getDropDownValue(strSearchFormNameVendorBooking, "PARAM_ShipmentDue", 0), strSearchFormNameVendorBooking, "PARAM_ShipmentDue"), true, "*****test ID:_48_Shipment due value is not set under Vendor Booking Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strStatusBooked, strSearchFormNameVendorBooking, "PARAM_BookingStatus"), true, "*****test ID:48_Status is not set as Pending in Vendor Booking Serach page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevSelect()), true, "*****test ID:48_Select Chevron is not clicked under Vendor Booking Search page*****");
    	wait(5000);
    	
    	List<VendorBooking> list = new ArrayList<VendorBooking>();  
    	list = objAdjunoLIMAVendorBookingPOM.getStaus();
    	
    	strMessage = objAdjunoLIMAVendorBookingPOM.verifyStaus(strStatusVendorBooked,strStatusShipmentBooked,strStatusPartBooked,list);
    	strCancelPONumber = list.get(0).getStrPONumber();
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "****test ID:48_"+strMessage+"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickCheckBox(objAdjunoLIMAVendorBookingPOM.getLstCheckBox().get(0)), true, "*****test ID:49_CheckBox is not clicked in Select page under Vendor Booking*****");
    	objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnSearchPanelArrowBtn());
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButton(strSearchFormNameVendorBooking, "Button_CancelBooking"), true, "*****test ID:49_Cancel Booking button is not clicked under Vendor Booking Select page*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strVendorBookingCancelPageFormName), true, "*****test ID:49_Vendor Booking Review page is not displayed*****");
    	objSoftAssert.assertEquals(strCancelPONumber.equals(objAdjunoLIMAVendorBookingPOM.getGridCellValue(strVendorBookingCancelPageFormName, "Grid1", 0, "OrderNumber")), true, "*****test ID:49_Selected PO NUmber is not displayed under Review page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickChevron(objAdjunoLIMAVendorBookingPOM.getChevComplete1()), true, "*****test ID:50_Complete Chevron is not clicked under Vendor Booking Review page*****");
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strCompletePageFromName), true, "*****test ID:50_Complete page is not loaded after Complete chevron is clicked in Booking page*****");
    	strMessage = objAdjunoLIMAVendorBookingPOM.getValueFromReadOnlyMode(objAdjunoLIMAVendorBookingPOM.getStrCompleteMsgID());
    	
    	wait(5000);
    	objSoftAssert.assertEquals(strMessage.contains("1 Product has been cancelled."), true, "*****test ID:50_\"1 Product has been cancelled\" message is not displayed in Complete Chevron.");

    	objSoftAssert.assertAll();
	}

	@Test(priority = 175)
	public void test_51_CheckForPOStatusFunctionalityForCancelBooking()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	String strMessage = "";
    	
    	objAdjunoLIMAVendorBookingPOM.callMouseHover(strSearchFormNameVendorBooking, objAdjunoLIMAVendorBookingPOM.getLnkFind(), objAdjunoLIMAVendorBookingPOM.getLnkPOStatus());
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strPOStatusFormName), true, "****test ID:51_PO Status Page is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strPONumber, strPOStatusFormName, "Param_16PO"), true, "****test ID:51_PO Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.setFieldValue(strProduct, strPOStatusFormName, "Param_17Item"), true, "****test ID:51_Product Number is not set in PO Status page*****");
        objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickButtonUsingWebElement(objAdjunoLIMAVendorBookingPOM.getBtnRun()), true, "****test ID:51_Run button is not clicked in PO Status page*****");
        
        try{
        	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.isElementPresent(objAdjunoLIMAVendorBookingPOM.getPoStatusReport()), true, "****test ID:51_PO Status report page is not loaded*****");
        	
        	String strStatus = objAdjunoLIMAVendorBookingPOM.getWebElementCellValue(objAdjunoLIMAVendorBookingPOM.getPoStatus_Status());
        	
        	objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Vendor Booked"), true, "****test ID:51_Status mismatch*****");	
        	}
        	catch(NoSuchElementException e)
        	{
        		strMessage = "PO Status report is not displayed";
        	}
        	objSoftAssert.assertEquals(strMessage.equals(""), true, "****test ID:51_"+strMessage+"*****");
        	
        objSoftAssert.assertAll();	
	} 
	
	 /*@Test(priority = 180)
	 public void test_60_VerifyVendorBookingIsAccessableFromDashBoard()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	
		objAdjunoLIMAVendorBookingPOM.getLnkHome().click();
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strDashBoardFormName, "DashboardPanel", "Tab4"), true, "*****test ID:60_Vendor Tab is not clicked in Dashboard page under WorkFlow panel*****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkDashBoardVendorBooking()), true, "******test ID:60_Vendor Booking MileStone is not clicked under Workflow panel in Dashboard*****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:60_Vendor Booking Search page is not displayed when Vendor Booking milestone is clicked from Dashboard*****");
		driver.navigate().back();
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority = 185)
	public void test_61_VerifyExpectedNumberLinkForVendorBookingAtDashBoard()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objAdjunoLIMAVendorBookingPOM.getLnkHome().click();
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkVBExpected()), true, "*****test ID:61_Expected Number link is not clicked under WorkFlow panel in Dashboard*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:61_Vendor Booking Search page is not displayed when Expected Number link is not clicked for Vendor Booking milestone in from Dashboard*****");
		wait(3000);
		driver.navigate().back();
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 190)
	public void test_62_VerifyDueNumberLinkForVendorBookingAtDashBoard()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objAdjunoLIMAVendorBookingPOM.getLnkHome().click();
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkVBDue()), true, "*****test ID:62_Due Number link is not clicked under WorkFlow panel in Dashboard*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:62_Vendor Booking Search page is not displayed when Expected Number link is not clicked for Vendor Booking milestone in from Dashboard*****");
		wait(3000);
		driver.navigate().back();
		objSoftAssert.assertAll();
	}

	@Test(priority = 195)
	public void test_63_VerifyCriticalNumberLinkForVendorBookingAtDashBoard()
	{
		SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAVendorBookingPOM = new AdjunoLIMAVendorBookingPOM(driver);
    	
    	objAdjunoLIMAVendorBookingPOM.getLnkHome().click();
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickLink(objAdjunoLIMAVendorBookingPOM.getLnkVBCritical()), true, "*****test ID:63_Critical Number link is not clicked under WorkFlow panel in Dashboard*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.verifyPageIsLoaded(strSearchFormNameVendorBooking), true, "*****test ID:63_Vendor Booking Search page is not displayed when Expected Number link is not clicked for Vendor Booking milestone in from Dashboard*****");
		wait(3000);
		driver.navigate().back();
		objSoftAssert.assertEquals(objAdjunoLIMAVendorBookingPOM.clickTab(strDashBoardFormName, "DashboardPanel", "Tab5"), true, "*****test ID:60_Vendor Tab is not clicked in Dashboard page under WorkFlow panel*****");
		wait(2000);
		objSoftAssert.assertAll();
	}*/
	
		@AfterTest
	public void closeBrowser()
	{
		driver.quit();
	}

	
	
	
   
    
}
