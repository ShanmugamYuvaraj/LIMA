package com.lima.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoWFLDeliveryBookingPOM;

public class AdjunoWFLDeliveryBookingTest {
	
	WebDriver driver;

    long nPage_Timeout = 0;
    String strTestURL;
    String strTrackN0;
    boolean searchResult;
    
    String strUserName;
    String strPassword;
    String strPageTitleHome;
    String strPageTitleDeliveryBooking;
    String strFormNameDeliveryBooking;
       
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoUILibrary objAdjunoUILibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoWFLDeliveryBookingPOM objAdjunoWFLDeliveryBookingPOM;

	String strSupplier;
	String strCustomer;
	String strModeSea;
	String strCatalogPageTitle;
	String strCatalogFormName;
	String strLoCode;
	String strMode;
	String strVessel;
	String strDestination;
	String strDepartment;
	
	ArrayList<String> catalogSupplierList;
	ArrayList<String> catalogCustomerList;
	ArrayList<String> catalogLoCodeList;
	ArrayList<String> catalogModeList;
	ArrayList<String> catalogVesselList;
	ArrayList<String> catalogDestinationList;
	ArrayList<String> catalogDepartmentList;
	
	NodeList nlSearchParamsCatalog;
	NodeList nlSearchParamsDeliveryBooking;
	
	ArrayList<String> customerList;
	ArrayList<String> supplierList;
	ArrayList<String> destinationList;
	ArrayList<String> vesselList;
	ArrayList<String> departmentList;
	ArrayList<String> modeList;
	ArrayList<String> statusList;
	ArrayList<String> catalogLevelList;
	ArrayList<String> levelOfDetailsList;
	
	String strCustomerUserName;
	String strCustomerPassword;
	String strConfiguration;
	String strAnyStatusValue;
	String strDesinationPortValue;
	//String strLevelProductValue;
	String strLevelProduct;
	String strLevelOrderNumber;
	String strLevelContainer;
	String strLevelSKU;
	String strDCValue;
	String strStausDeliveryBooked;
	String strWFLDeliveryBookingDialogFormName;
	String strStausPending;
	String strFormNameDeliveryBookingChevron;
	String strFormNameBookingCancel;
	String strStockOrder;
	String strConsignee;
	String strWarehouse;
	String strSplitDeliveryY;
	String strSplitDeliveryN;
	
	String strCrossDockOrder;
	String strReasonCode;
	String strVendorValue;
	String strDepartmentValue;
	String strSeaModeValue;
	String strBookingRefValue;
	String strCustomerValue;
	String strContainerValue;
	String strWFLRefValue;
	String strProductValue;
	String strSKUValue;
	String strLineType;
	String strFormNameDeliveryBookingWFLRef;

	ArrayList<String> catalogReasonCodeList;
	ArrayList<String> lineTypeList;
	ArrayList<String> catalogLineTypeList;
	ArrayList<String> consigneeList;
	ArrayList<String> catalogconsigneeList;

	String strConsigneeType;
	String strLineTypeStock;
	String strBookingDate;

	private ArrayList<String> catalogWarehouseList;

	private ArrayList<String> warehouseList;

	private String strDirectOrder;

	private ArrayList<String> deliveryMethodList;

	private ArrayList<String> catalogDeliveryMethodList;

	private String strDeliveryMethod;

	private String strHaulier;

	private ArrayList<String> catalogHaulierList;

	private ArrayList<String> haulierList;

	private String strBookingRef;

	private String strBookingTime;

	private String strFormNameCompletePage;

	private ArrayList<String> splitDeliveryList;

	   
    public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
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
            
            Node catalogPageTitle = (Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
            strCatalogPageTitle = catalogPageTitle.getTextContent();
            
            Node catalogFormName = (Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
            strCatalogFormName = catalogFormName.getTextContent();
            
            Node customerUserName = (Node) xPath.evaluate("/config/WFL/WFL_Customer_UserName", dDoc, XPathConstants.NODE);
	    	strCustomerUserName = customerUserName.getTextContent();
	            
	    	Node customerPassword = (Node) xPath.evaluate("/config/WFL/WFL_Customer_Password", dDoc, XPathConstants.NODE);
	    	strCustomerPassword = customerPassword.getTextContent();
            
            Node testLIMAURL = (Node) xPath.evaluate("/config/WFL/WFL_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/WFL/WFL_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/WFL/WFL_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	            
  	        Node pageTitleHome = (Node) xPath.evaluate("/config/WFL/WFL_Title_HomePage", dDoc, XPathConstants.NODE);
  	        strPageTitleHome = pageTitleHome.getTextContent();
  	            
  	        Node pageTitleDeliveryBooking = (Node) xPath.evaluate("/config/WFL/WFL_Title_Delivery_Booking", dDoc, XPathConstants.NODE);
	        strPageTitleDeliveryBooking = pageTitleDeliveryBooking.getTextContent();
  	        
	        Node formNameDeliveryBooking = (Node) xPath.evaluate("/config/WFL/WFL_Form_Name_Delivery_Booking", dDoc, XPathConstants.NODE);
	        strFormNameDeliveryBooking = formNameDeliveryBooking.getTextContent();
	        
	        Node DeliveryBookingDialogFormName = (Node) xPath.evaluate("/config/WFL/WFL_DeliveryBooking_Dialog", dDoc, XPathConstants.NODE);
	        strWFLDeliveryBookingDialogFormName = DeliveryBookingDialogFormName.getTextContent();
	        
	        Node formNameDeliveryBookingChevron = (Node) xPath.evaluate("/config/WFL/WFL_Delivery_Booking_Chevorn", dDoc, XPathConstants.NODE);
	        strFormNameDeliveryBookingChevron = formNameDeliveryBookingChevron.getTextContent();
	        
	        Node formNameBookingCancel = (Node) xPath.evaluate("/config/WFL/WFL_Booking_Cancel", dDoc, XPathConstants.NODE);
	        strFormNameBookingCancel = formNameBookingCancel.getTextContent();
	        
	        Node formNameDeliveryBookingWFLRef = (Node) xPath.evaluate("/config/WFL/WFL_DeliveryBooking_WFLRefFormName", dDoc, XPathConstants.NODE);
	        strFormNameDeliveryBookingWFLRef = formNameDeliveryBookingWFLRef.getTextContent();
	        
	        
	        Node formNameCompletePage = (Node) xPath.evaluate("/config/WFL/Form_Name_Complete_Page", dDoc, XPathConstants.NODE);
	        strFormNameCompletePage = formNameCompletePage.getTextContent();
	        
	        
	        
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
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getstrWFLDeliveryBooking());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            nlSearchParamsCatalog = (NodeList) xPath1.evaluate("/DeliveryBooking/Catalog_SearchParams", dDoc1, XPathConstants.NODESET);
            
            nlSearchParamsDeliveryBooking = (NodeList) xPath1.evaluate("/DeliveryBooking/SearchParams", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsCatalog.getLength() -1; i++)
             {
                 if (nlSearchParamsCatalog.item(i).getNodeType() == Node.ELEMENT_NODE)
                 {
                     el = (Element) nlSearchParamsCatalog.item(i);
                     strSupplier = el.getElementsByTagName("Type_Supplier").item(0).getTextContent(); 
                     
                     strCustomer = el.getElementsByTagName("Type_Customer").item(0).getTextContent(); 
                     
                     strLoCode = el.getElementsByTagName("Type_LoCode").item(0).getTextContent(); 
                     
                     strDestination = el.getElementsByTagName("Type_Destination").item(0).getTextContent();
                     
                     strDepartment = el.getElementsByTagName("Type_Department").item(0).getTextContent();
                     
                     strMode = el.getElementsByTagName("Type_Mode").item(0).getTextContent(); 
                     
                     strVessel = el.getElementsByTagName("Type_Vessel").item(0).getTextContent(); 
                     
                     strConfiguration = el.getElementsByTagName("Type_Configuration").item(0).getTextContent();
                     
                     strReasonCode = el.getElementsByTagName("Type_ReasonCode").item(0).getTextContent();
                     
                     strLineType = el.getElementsByTagName("Type_LineType").item(0).getTextContent();
                     
                     strConsigneeType = el.getElementsByTagName("Type_Consignee").item(0).getTextContent();
                     
                     strDeliveryMethod = el.getElementsByTagName("Type_DeliveryMethod").item(0).getTextContent();
                     
                     strHaulier = el.getElementsByTagName("Type_Haulier").item(0).getTextContent();
            
                 }
             }

            
            Element e2;
            for(int i=0; i <= nlSearchParamsDeliveryBooking.getLength() -1; i++)
             {
                 if (nlSearchParamsDeliveryBooking.item(i).getNodeType() == Node.ELEMENT_NODE)
                 {
                     e2 = (Element) nlSearchParamsDeliveryBooking.item(i);
                     strDesinationPortValue = e2.getElementsByTagName("Destination_Value").item(0).getTextContent(); 
                     
                     strAnyStatusValue = e2.getElementsByTagName("Status_Any").item(0).getTextContent(); 
                     
                     strLevelProduct = e2.getElementsByTagName("Level_Product").item(0).getTextContent(); 
                     
                     strLevelOrderNumber = e2.getElementsByTagName("Level_Order_Number").item(0).getTextContent();
                     
                     strLevelContainer = e2.getElementsByTagName("Level_Container").item(0).getTextContent();
                     
                     strLevelSKU = e2.getElementsByTagName("Level_SKU").item(0).getTextContent(); 
                     
                     strDCValue = e2.getElementsByTagName("DC_Value").item(0).getTextContent(); 
                     
                     strStausPending = e2.getElementsByTagName("Status_Pending").item(0).getTextContent(); 
                     
                     strStausDeliveryBooked = e2.getElementsByTagName("Status_Delivery_Booked").item(0).getTextContent();
                     
                     strConsignee = e2.getElementsByTagName("Consignee").item(0).getTextContent(); 
                     
                     strWarehouse = e2.getElementsByTagName("Warehouse").item(0).getTextContent(); 
                     
                     strStockOrder = e2.getElementsByTagName("LineType_Stock").item(0).getTextContent();
                     
                     strCrossDockOrder = e2.getElementsByTagName("Line_Type_Cross").item(0).getTextContent();
                     
                     strDirectOrder = e2.getElementsByTagName("LineType_Direct_Order").item(0).getTextContent();
                     
                     strSplitDeliveryY = e2.getElementsByTagName("Split_Delivery_Y").item(0).getTextContent();
                     
                     strSplitDeliveryN = e2.getElementsByTagName("Split_Delivery_N").item(0).getTextContent();
            
                     strVendorValue = e2.getElementsByTagName("Vendor_Value").item(0).getTextContent();

                     strDepartmentValue = e2.getElementsByTagName("Department_Value").item(0).getTextContent();
                     
                     strCustomerValue = e2.getElementsByTagName("Customer_Value").item(0).getTextContent();
                     
                     strSeaModeValue = e2.getElementsByTagName("Mode_Value").item(0).getTextContent();
                     
                     strBookingRefValue = e2.getElementsByTagName("BookingRef_Value").item(0).getTextContent();
                     
                     strContainerValue = e2.getElementsByTagName("Container_Value").item(0).getTextContent();
                     
                     strWFLRefValue = e2.getElementsByTagName("WFLRef_Value").item(0).getTextContent();

                     strProductValue = e2.getElementsByTagName("Product_Value").item(0).getTextContent();
                     
                     strSKUValue = e2.getElementsByTagName("SKU_Value").item(0).getTextContent();

                     strBookingRef = e2.getElementsByTagName("Booking_Ref").item(0).getTextContent();

                     strBookingTime = e2.getElementsByTagName("Booking_Time").item(0).getTextContent();
                     
                     strLineTypeStock = e2.getElementsByTagName("LineType_Stock").item(0).getTextContent();
                 }
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
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
    public void accessCatalog()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
        
        String strTitle = objAdjunoWFLDeliveryBookingPOM.callMouseHover(strCatalogFormName,objAdjunoWFLDeliveryBookingPOM.getLnkTools(), objAdjunoWFLDeliveryBookingPOM.getLnkCatalog());
        
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
        objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");
        objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getLnkLightHouse()),true,"Light house link is not clicked");
        wait(5000);
     
        objSoftAssert.assertAll();

    }
    

	public void clearCatalogField(){
		wait(2000);
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** Name Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Type Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** Description Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** Associated item Field element not cleared *****");
		
		objSoftAssert.assertAll();
	}
	
	 // get the catalog values on different field 
	 @Test(priority=10)
	 public void getCatalogValues()
	 {
		// set field value as Customer
		String strMessage = "";
		SoftAssert objSoftAssert = new SoftAssert();
		clearCatalogField();
		if (!isNullOrBlank(strCustomer)) {
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strCustomer, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.002 -Type Field value is not entered as Customer*****");
		} else {
			strMessage = strMessage + "Customer Value is Empty in Xml";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.002 - Apply Button is not Clicked *****");
		catalogCustomerList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();

		if (catalogCustomerList.size() > 0) {

		} else {
			strMessage = strMessage + " Customer Type won't have data";
		}

		// Setting the field value LODOCE setFieldValue
		if (!isNullOrBlank(strLoCode)) {
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.003 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 6.003 - Previous Button didn't click *****");
			wait(1000);
			clearCatalogField();
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLoCode, strCatalogFormName, "Param_Type"), true,"***** Test ID : 9.003 - Type Field value is not entered as LoCODE*****");

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 6.003 - Apply button is not clicked *****");
			wait(5000);

			int nCount4 = objAdjunoWFLDeliveryBookingPOM.valCount();
			catalogLoCodeList = new ArrayList<String>();
			catalogLoCodeList = objAdjunoWFLDeliveryBookingPOM.getCatalogTableData1(nCount4);
			wait(2000);

		} else {

			strMessage = strMessage + " LOCODE value is Empty in XML";
		}
		if (catalogLoCodeList.size() > 0) {

		} else {
			
			strMessage = strMessage + " LOCODE type won't have data";
		}

		// set the DC value in textbox
		if (!isNullOrBlank(strDestination)) {
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.004 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.004 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strDestination, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.004 - Type Field value is not entered as Destination*****");
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.004 - Apply button is not clicked *****");
			wait(5000);
			catalogDestinationList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();
			
			catalogWarehouseList = new ArrayList<String>();
			for (int i = 0; i <=objAdjunoWFLDeliveryBookingPOM.getLstItemDescription().size()-1; i++) {
				objAdjunoWFLDeliveryBookingPOM.getLstItemDescription().get(i).click();
				wait(3000);
				boolean bFalg = objAdjunoWFLDeliveryBookingPOM.getIsCheckBoxClcked("CatalogDestination_StockDC");
				driver.navigate().back();
				if(bFalg==true){
					catalogWarehouseList.add(objAdjunoWFLDeliveryBookingPOM.getLstDescription().get(i).getText());
				}
				
			}
			wait(2000);

		} else {
			strMessage = strMessage + " Destination Value is Empty in XML";
		}
		if (catalogDestinationList.size() > 0) {

		} else {
			strMessage = strMessage + " Destination Type won't have data";
		}

		// set the mode value in textbox
		if (!isNullOrBlank(strMode)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.009 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.009 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strMode, strCatalogFormName, "Param_Type"), true,"***** Test ID : 9.009 - Type Field value is not entered as Mode*****");
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.009 - Apply button is not clicked *****");
			wait(5000);
			catalogModeList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();

		} else {
			strMessage = strMessage + " Mode Value is Empty in XML";
		}
		if (catalogModeList.size() > 0) {

		} else {
			strMessage = strMessage + " Mode type won't have data";
		}
		
		//Line type
		if (!isNullOrBlank(strLineType)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.009 - Type Field element not cleared *****");
			wait(3000);
			clearCatalogField();
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLineType, strCatalogFormName, "Param_Type"), true,"***** Test ID : 9.009 - Type Field value is not entered as LineType*****");
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.009 - Apply button is not clicked *****");
			wait(5000);
			catalogLineTypeList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();

		} else {
			strMessage = strMessage + " LineType Value is Empty in XML";
		}
		if (catalogLineTypeList.size() > 0) {

		} else {
			strMessage = strMessage + " LineType type won't have data";
		}

		// set the Vessel value in textbox
		if (!isNullOrBlank(strVessel)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.006 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.006 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strVessel, strCatalogFormName, "Param_Type"), true,"***** Test ID : 9.006 - Type Field value is not entered as Vessel*****");

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.006 - Apply button is not clicked *****");
			wait(5000);

			int nCount5 = objAdjunoWFLDeliveryBookingPOM.valCount();
			catalogVesselList = new ArrayList<String>();
			catalogVesselList = objAdjunoWFLDeliveryBookingPOM.getCatalogTableData(nCount5);
			wait(2000);
		} else {
			strMessage = strMessage + "Vessel Value is Empty in XML";
		}
		if (catalogVesselList.size() > 0) {

		} else {
			strMessage = strMessage + " Vessel type won't have data";
		}
		
		//Delivery Method
		if (!isNullOrBlank(strDeliveryMethod)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.007 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.007 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strDeliveryMethod, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.007 - Type Field value is not entered as Delivery Method*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.007 - Apply button is not clicked *****");

			catalogDeliveryMethodList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();
		} else {

			strMessage = strMessage + "DeliveryMethod Value is Empty in XML";
		}
		if (catalogDeliveryMethodList.size() > 0) {

		} else {
			strMessage = strMessage + " DeliveryMethod type won't have data";
		}
		
		
		
		//Haulier
		if (!isNullOrBlank(strHaulier)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.007 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.007 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strHaulier, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.007 - Type Field value is not entered as Haulier*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.007 - Apply button is not clicked *****");

			catalogHaulierList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();
		} else {

			strMessage = strMessage + "Haulier Value is Empty in XML";
		}
		if (catalogHaulierList.size() > 0) {

		} else {
			strMessage = strMessage + " Haulier type won't have data";
		}
		
		
		

		// Suppliers
		// set the Suppliers value in textbox
		if (!isNullOrBlank(strSupplier)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.007 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.007 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strSupplier, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.007 - Type Field value is not entered as Supliers*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.007 - Apply button is not clicked *****");

			catalogSupplierList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();
		} else {

			strMessage = strMessage + "Supplier Value is Empty in XML";
		}
		if (catalogSupplierList.size() > 0) {

		} else {
			strMessage = strMessage + " Supplier type won't have data";
		}
		
		
		// department
		// set the department value in textbox
		if (!isNullOrBlank(strDepartment)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.008 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.008 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strDepartment, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.008 - Type Field value is not entered as Department*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.008 - Apply button is not clicked *****");

			catalogDepartmentList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();
		} else {

			strMessage = strMessage + "Department Value is Empty in XML";
		}
		if (catalogDepartmentList.size() > 0) {

		} else {
			strMessage = strMessage + " department type won't have data";
		}
		
		
		//configuration
		if (!isNullOrBlank(strConfiguration)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.0012 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.0012 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strConfiguration, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.0012 - Type Field value is not entered as Department*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.0012 - Apply button is not clicked *****");

			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getLnkConfiguration()), true,"***** Test ID : 9.0012 - configuration link is not clicked *****");
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getDeliveryTab()), true,"***** Test ID : 9.0012 - Delivery tab is not clicked *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getDeliveryBookingTab()), true,"***** Test ID : 9.0012 - Delivery Booking link is not clicked *****");
			
			catalogLevelList = objAdjunoWFLDeliveryBookingPOM.getDeliveryBookingLevels();
			
		} else {

			strMessage = strMessage + "Configuration Value is Empty in XML";
		}
		if (catalogLevelList.size() > 0) {

			
		} else {
			strMessage = strMessage + " Level Of details  won't have data";
		}
		
		//consignee
	if (!isNullOrBlank(strConsigneeType)) {
			
			driver.navigate().back();
			wait(4000);
					
			
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strConsigneeType, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.0012 - Type Field value is not entered as Consignee*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.0012 - Apply button is not clicked *****");

			wait(3000);
			
			
			catalogconsigneeList = objAdjunoWFLDeliveryBookingPOM.getCatalogData();
			
		} else {

			strMessage = strMessage + "Consignee Value is Empty in XML";
		}
		if (catalogconsigneeList.size() > 0) {

			
		} else {
			strMessage = strMessage + " Consignee won't have data";
		}
		
		//ReasonCode
		if (!isNullOrBlank(strReasonCode)) {

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.0012 - Type Field element not cleared *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnPagePrev()), true,"***** Test ID : 9.0012 - Previous Button didn't click *****");
			wait(3000);
			clearCatalogField();
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strReasonCode, strCatalogFormName, "Param_Type"),true,"***** Test ID : 9.0012 - Type Field value is not entered as ReasonCode*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true,"***** Test ID : 9.0012 - Apply button is not clicked *****");

			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getLnkReasonCode()), true,"***** Test ID : 9.0012 - Reasoncode link is not clicked *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getDeliveryTab2()), true,"***** Test ID : 9.0012 - Delivery tab is not clicked *****");
			wait(3000);
			
			
			catalogReasonCodeList = objAdjunoWFLDeliveryBookingPOM.getReasonCode();
			
		} else {

			strMessage = strMessage + "ReasonCode Value is Empty in XML";
		}
		if (catalogReasonCodeList.size() > 0) {

			
		} else {
			strMessage = strMessage + " ReasonCode won't have data";
		}
		
		

		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
		objSoftAssert.assertAll();

	}
	 
	 
	@Test(priority = 12)
	public void test_9d003_AccessDelivery_Booking() {
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		//wait(2000);
		String strTitle = objAdjunoWFLDeliveryBookingPOM.callMouseHover(strFormNameDeliveryBooking,objAdjunoWFLDeliveryBookingPOM.getLnkTools(),objAdjunoWFLDeliveryBookingPOM.getLnkDeliveryBooking());

		boolean bFlag = true;

		if (!(isNullOrBlank(strTitle))) {
			if (strTitle.equalsIgnoreCase(strPageTitleDeliveryBooking))
				bFlag = true;
			else
				bFlag = false;
		} else {
			bFlag = false;
		}
		objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.003 - Title of the page is wrong *****");
		objSoftAssert.assertAll();

	}
	
	
	 //verify customer dropdown values 
	 @Test(priority=15)
	 public void test_9d002_VerifyCustomerValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 customerList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_Customer");
	       
		 if(customerList.size()>0){
				
		 }else{
			 strMessage = strMessage +" customer Drop drown won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the customer dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogCustomerList, customerList);
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.002-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	}
	 
	 @Test(priority=20)
	 public void test_9d003_VerifyDestinationPortValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 destinationList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_DestinationPort");
	       
		 if(destinationList.size()>0){
				
		 }else{
			 strMessage = strMessage +" destination won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the destination dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogDestinationList, destinationList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.003-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	}
	 
	 @Test(priority=40)
	 public void test_9d004_VerifyDCValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 destinationList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_DC");
	       
		 if(destinationList.size()>0){
				
		 }else{
			 strMessage = strMessage +" Mode type won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the dc dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogDestinationList, destinationList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.004-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	}   
	 
	 @Test(priority=25)
	 public void test_9d006_VerifyVesselValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 vesselList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_Vessel");
	       
		 if(destinationList.size()>0){
				
		 }else{
			 strMessage = strMessage +" vessel won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the destination dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogVesselList, vesselList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.006-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	} 
	 
	 
	 @Test(priority=30)
	 public void test_9d007_VerifyVendorValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 supplierList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_Supplier");
	       
		 if(supplierList.size()>0){
				
		 }else{
			 strMessage = strMessage +" vendor won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the destination dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogSupplierList, supplierList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.007-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	}  
	 
	 
	 @Test(priority=35)
	 public void test_9d008_VerifyDepartmentValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 departmentList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_Department");
	       
		 if(departmentList.size()>0){
				
		 }else{
			 strMessage = strMessage +" Department type won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the departmnet dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogDepartmentList, departmentList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.008-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	}  
	 
	 
	 @Test(priority=40)
	 public void test_9d009_VerifyModeValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		 String strMessage = "";
		 
		 modeList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking, "Param_Mode");
	       
		 if(modeList.size()>0){
				
		 }else{
			 strMessage = strMessage +" Mode type won't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
			
		 //verify the departmnet dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogModeList, modeList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.009-"+strMessage +" *****");
	
		 objSoftAssert.assertAll();
	}  
	 
	public void LoginCustomer() {
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getLnkLogout()), true,"***** Logout button not Clicked *****");
		objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
		objAdjunoLIMALoginPOM.setUserName(strCustomerUserName);
		objAdjunoLIMALoginPOM.setPassword(strCustomerPassword);
		objAdjunoLIMALoginPOM.clickLogon();

	}
		
	// logged as a customer and verify the customer dropdown
	@Test(priority = 45)
	public void test_9d0010_VerifyCustomerDropDown() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		LoginCustomer();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		String strTitle = objAdjunoWFLDeliveryBookingPOM.callMouseHover(strFormNameDeliveryBooking,objAdjunoWFLDeliveryBookingPOM.getLnkTools(),objAdjunoWFLDeliveryBookingPOM.getLnkDeliveryBooking());
		boolean bFlag = true;

		if (isNullOrBlank(strTitle))
			bFlag = false;

		if (!(isNullOrBlank(strTitle))) {
			if (strTitle.equalsIgnoreCase(strPageTitleDeliveryBooking))
				bFlag = true;
			else
				bFlag = false;
		}

		objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");

		customerList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking,"Param_Customer");

		if (customerList.size() == 1) {

		} else {
			strMessage = strMessage + " customer Drop drown won't have value";
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.010" + strMessage + "*****");
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 50)
	public void test_9d0011_VerifyStatusDropDown() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);

		statusList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking,"Param_Status");

		if (statusList.size() > 0 ) {

		} else {
			strMessage = strMessage + " status Drop drown won't have value";
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.011" + strMessage + "*****");
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority = 55)
	public void test_9d0012_VerifyLevelOfDetailsDropDown() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);

		levelOfDetailsList = objAdjunoWFLDeliveryBookingPOM.getDropdownValues(strFormNameDeliveryBooking,"Param_LevelOfDetail");

		if (levelOfDetailsList.size() > 0 ) {

		} else {
			strMessage = strMessage + " Level Of Detail won't have value";
		}
		
		strMessage= strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogLevelList, levelOfDetailsList);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 9.012" + strMessage + "*****");
		objSoftAssert.assertAll();
	}
	
	
	public void clearFields(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_LevelOfDetail"), true,"***** Level Of Detail field not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_DestinationPort"), true,"***** Destination Port Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Mode"), true,"***** Mode Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Vessel"), true,"***** Vessel Field  not cleared *****");		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_ETDFrom"), true,"***** ETA From Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_ETDTo"), true,"***** ETA To Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Status"), true,"***** Status Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_BookingRef"), true,"***** Booking Ref Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Department"), true,"***** department Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Supplier"), true,"***** Vendor Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Customer"), true,"***** Customer  Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Container"), true,"***** Container Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Housebill"), true,"***** housebill Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_DC"), true,"***** DC Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Customer"), true,"***** Customer Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_BookedFrom"), true,"***** booked From Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_BookedTo"), true,"***** booked To Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_SKU"), true,"***** SKU Field  not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_Product"), true,"***** Product Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_OrderNumber"), true,"***** WFL Reference From Field  not cleared *****");
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 60)
	public void test_9d013_VerifyDestinationPortSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		wait(3000);
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.013 - Status Field value is not entered as Any *****");
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strDesinationPortValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strDesinationPortValue, strFormNameDeliveryBooking,"Param_DestinationPort"), true,"***** Test ID : 9.013 - OrginPortValue Field value is not entered *****");
		}else{
			strMessage=strMessage+"Destination port Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelProduct)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelProduct, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.013 - OrginPortValue Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.013 - Select Chevron not clicked *****");
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue(strDesinationPortValue,objAdjunoWFLDeliveryBookingPOM.getLstDestnationPort());
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.013 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority = 65)
	public void test_9d0014_VerifyDCSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.014 - Status Field value is not entered as Any *****");
		
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strDCValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strDCValue, strFormNameDeliveryBooking,"Param_DC"), true,"***** Test ID : 9.014 - OrginPortValue Field value is not entered *****");
		}else{
			strMessage=strMessage+"Destination port Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelProduct)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelProduct, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.014 - OrginPortValue Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.014- Refine Search is not clicked *****");
		
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue(strDCValue,objAdjunoWFLDeliveryBookingPOM.getLstDC());
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.014 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority = 70)
	public void test_9d018_VerifyVendorSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()), true,"***** Test ID : 9.018 - Select Chevron is Not Clicked *****");
		wait(2000);
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.018 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.018 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strVendorValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strVendorValue, strFormNameDeliveryBooking,"Param_Supplier"), true,"***** Test ID : 9.018 - Vendor Field value is not entered *****");
		}else{
			strMessage=strMessage+"Vendor Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.018- Refine Search is not clicked *****");
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getClkWflRef()), true,"***** Test Id :9.018- WFLRef Link is not Clicked *****");
		String window= objAdjunoWFLDeliveryBookingPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true , "***** Test ID : 9.018 - Apply button not clicked *****");
	//	wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.compareTwoStringValuesToSame(strVendorValue, objAdjunoWFLDeliveryBookingPOM.getFieldValue(strFormNameDeliveryBookingWFLRef, "Supplier")), true , " Test ID : 9.018 - Vendor Field Value is not Matched ***** ");	
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.018 - "+strMessage+" *****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority = 75)
	public void test_9d019_VerifyDepartmentSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.019 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.019 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strDepartmentValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strDepartmentValue, strFormNameDeliveryBooking,"Param_Department"), true,"***** Test ID : 9.019 - Department Field value is not entered *****");
		}else{
			strMessage=strMessage+"Department Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.019- Refine Search is not clicked *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getClkWflRef()), true,"***** Test Id :9.019- WFLRef Link is not Clicked *****");
		String window= objAdjunoWFLDeliveryBookingPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true , "***** Test ID : 9.019 - Apply button not clicked *****");
	//	wait(2000);
		System.out.println(objAdjunoWFLDeliveryBookingPOM.getGridCellValue(strFormNameDeliveryBookingWFLRef, "GRID_ContractSummaryDetails", 0, "ProductDepartment"));
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.compareTwoStringValuesToSame(strDepartmentValue, objAdjunoWFLDeliveryBookingPOM.getGridCellValue(strFormNameDeliveryBookingWFLRef, "GRID_ContractSummaryDetails", 0, "ProductDepartment")), true , " Test ID : 9.019 - Department Field Value is not Matched ***** ");	
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.019 - "+strMessage+" *****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority = 80)
	public void test_9d021_VerifyCustomerSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.021 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}					
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.021 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strCustomerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strCustomerValue, strFormNameDeliveryBooking,"Param_Customer"), true,"***** Test ID : 9.021 - Customer Field value is not entered *****");
		}else{
			strMessage=strMessage+"Customer Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.021- Refine Search is not clicked *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getClkWflRef()), true,"***** Test Id :9.021- WFLRef Link is not Clicked *****");
		String window= objAdjunoWFLDeliveryBookingPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getcatalogbtnApply()), true , "***** Test ID : 9.021 - Apply button not clicked *****");
	//	wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.compareTwoStringValuesToSame(strCustomerValue, objAdjunoWFLDeliveryBookingPOM.getFieldValue(strFormNameDeliveryBookingWFLRef, "Customer")), true , " Test ID : 9.021 - Customer Field Value is not Matched ***** ");	
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.021 - "+strMessage+" *****");
		objSoftAssert.assertAll();
			
	}
	
	@Test(priority = 85)
	public void test_9d020_VerifyModeSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strSeaModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strSeaModeValue, strFormNameDeliveryBooking,"Param_Mode"), true,"***** Test ID : 9.020 - Mode Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Mode Value is Empty in XML";
		}	
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.020 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.020 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.020 - Refine Search is not clicked *****");

		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strSeaModeValue,objAdjunoWFLDeliveryBookingPOM.getLstModeValue());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.020"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	@Test(priority = 90)
	public void test_9d022_VerifyStatusSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.022 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}		
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.022 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.022 - Refine Search is not clicked *****");

		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strStausPending,objAdjunoWFLDeliveryBookingPOM.getLstStatus());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.022"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	@Test(priority = 95)
	public void test_9d023_VerifyBookingRefSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.023 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}		
		
		if(!isNullOrBlank(strBookingRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strBookingRefValue, strFormNameDeliveryBooking,"Param_BookingRef"), true,"***** Test ID : 9.023 - BookingRef Field value is not entered *****");
		}else{
			strMessage=strMessage+"BookingRef Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.023 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.023 - Refine Search is not clicked *****");

		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strBookingRefValue,objAdjunoWFLDeliveryBookingPOM.getLstBookingRef());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.023"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	@Test(priority = 100)
	public void test_9d026_VerifyContainerSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.026 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}		
		
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strContainerValue, strFormNameDeliveryBooking,"Param_Container"), true,"***** Test ID : 9.026 - Container Field value is not entered *****");
		}else{
			strMessage=strMessage+"Container Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.026 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.026 - Refine Search is not clicked *****");

		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strContainerValue,objAdjunoWFLDeliveryBookingPOM.getLstContainer());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.026"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	@Test(priority = 105)
	public void test_9d028_VerifyWFLRefSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.028 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}		
		
		if(!isNullOrBlank(strWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strWFLRefValue, strFormNameDeliveryBooking,"Param_OrderNumber"), true,"***** Test ID : 9.028 - WFLRef Field value is not entered *****");
		}else{
			strMessage=strMessage+"WFLRef Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.028 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.028 - Refine Search is not clicked *****");

		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strWFLRefValue,objAdjunoWFLDeliveryBookingPOM.getLstWflRef());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.028"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	@Test(priority = 110)
	public void test_9d029_VerifyProductSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.029 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}		
		
		if(!isNullOrBlank(strProductValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strProductValue, strFormNameDeliveryBooking,"Param_Product"), true,"***** Test ID : 9.029 - Product Field value is not entered *****");
		}else{
			strMessage=strMessage+"Product Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.029 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.029 - Refine Search is not clicked *****");
		wait(4000);
		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strProductValue,objAdjunoWFLDeliveryBookingPOM.getLstProduct());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.029"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	@Test(priority = 115)
	public void test_9d030_VerifySKUSearchData() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.030 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}		
		
		if(!isNullOrBlank(strSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strSKUValue, strFormNameDeliveryBooking,"Param_SKU"), true,"***** Test ID : 9.030 - SKU Field value is not entered *****");
		}else{
			strMessage=strMessage+"SKU Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.030 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.030 - Refine Search is not clicked *****");
		wait(4000);
		strMessage= strMessage +objAdjunoWFLDeliveryBookingPOM.verifyValue(strSKUValue,objAdjunoWFLDeliveryBookingPOM.getLstSku());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 9.030"+ strMessage +"*****");
		objSoftAssert.assertAll();	
		
	}
	
	
	@Test(priority=118)
	public void test_9d033_SearchOrderNumberLevel() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.033 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.033 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.033- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.013 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	for (int i = 0; i <=objAdjunoWFLDeliveryBookingPOM.getLstContainer().size()-1; i++) {
	    		strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstContainer().get(i).getText(),"container",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstWflRef().get(i).getText(),"wflref",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstIdentifier().get(i).getText(),"identifier",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstProduct().get(i).getText(),"product",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSku().get(i).getText(),"SKU",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSplitId().get(i).getText(),"splitid",i,false);
		    	
			}
	    	
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.033 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=120)
	public void test_9d034_ClickDailog() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.034 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Delivery Booked Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.034 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.034- Refine Search is not clicked *****");
		
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingDialog"), true,"***** Test Id :9.034- Add dialog is not clicked *****");
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strWFLDeliveryBookingDialogFormName),true,"***** Test Id :9.034- Dialog Page is not loaded *****");
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.034 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority=125)
	public void test_9d035_VerifyStatus() {
		SoftAssert objSoftAssert = new SoftAssert();
		driver.navigate().back();
		wait(3000);
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.035 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Delivery Booked Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.035 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.035- Refine Search is not clicked *****");
		
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue(strStausDeliveryBooked,objAdjunoWFLDeliveryBookingPOM.getLstStatus());
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.035 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=130)
	public void test_9d036_VerifyWareHouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		wait(3000);
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.036 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.036 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.036- Refine Search is not clicked *****");
		
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyWareHouse(objAdjunoWFLDeliveryBookingPOM.getLstWarehouse());
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.036 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=133)
	public void test_9d037_VerifyButton() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		wait(3000);
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.037 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBooking, "FORK_DeliveryBookingRefineSearch"),true,"***** Test ID : 9.037 - Refine Research button not found");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBooking, "FORK_DeliveryBooking"),true,"***** Test ID : 9.037 - Delivery Booking button not found");
		
		objSoftAssert.assertAll();
	}	
	
	
	@Test(priority=135)
	public void test_9d038_VerifyButton() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		wait(3000);
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.038 - Status Field value is not entered as Any Status *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBooking, "FORK_DeliveryBookingRefineSearch"),true,"***** Test ID : 9.038 - Refine Research button not found");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBooking, "FORK_DeliveryBooking"),true,"***** Test ID : 9.038 - Delivery Booking button not found");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBooking, "FORK_DeliveryBookingCancel"),true,"***** Test ID : 9.038 - Booking Calncel button not found");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBooking, "FORK_DeliveryBookingDialog"),true,"***** Test ID : 9.038 - Add Dialog button not found");
		
		objSoftAssert.assertAll();
	}	
	
	
	@Test(priority=138)
	public void test_9d040_VerifyDeliveryBookingPage() {
		SoftAssert objSoftAssert = new SoftAssert();
		
		wait(3000);
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.040 - Status Field value is not entered as Any Status *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.040 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.040- Refine Search is not clicked *****");
		
	    wait(5000);
	    
	    
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.040- Delivery Booking is not clicked *****");
			
		    wait(5000);
		    objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.034- Delivery Booking page is not loaded *****");
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID: 9.040 -"+strMessage+ " *****");
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=140)
	public void test_9d041_VerifyCancelBookingPage() {
		SoftAssert objSoftAssert = new SoftAssert();
		driver.navigate().back();
		wait(3000);
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.041 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Delivery booked Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.041 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.041- Refine Search is not clicked *****");
		
	    wait(5000);
	    
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingCancel"),true,"***** Test Id :9.041- Booking Calcel is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameBookingCancel),true,"***** Test Id :9.041- Dialog Page is not loaded *****");
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID: 9.041 -"+strMessage+ " *****");
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=143)
	public void test_9d042_SearchContainerLevel() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(1200);
		clearFields();
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearInputField(strFormNameDeliveryBooking, "Param_DestinationPort"), true);
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.042 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.042 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.042- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	for (int i = 0; i <=objAdjunoWFLDeliveryBookingPOM.getLstContainer().size()-1; i++) {
	    		strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstContainer().get(i).getText(),"container",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstWflRef2().get(i).getText(),"wflref",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstIdentifier().get(i).getText(),"identifier",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstProduct().get(i).getText(),"product",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSku().get(i).getText(),"SKU",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSplitId().get(i).getText(),"splitid",i,false);
		    	
			}
	    	
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.042 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=145)
	public void test_9d043_navigateToDeliverybooking() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.043 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.043 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.043- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.043- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.043- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strStockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strCrossDockOrder), true,"***** Test ID : 9.043 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName"),false,"***** Test ID : 9.043 - Consignee Field value is not read only *****");
	    	//clear ware house
	    	wait(3000);
	 //   	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clearGridInputField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "WarehouseName"), true,"***** Test ID : 9.043 - Value is not set *****");
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"WarehouseName");
	    	
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.043 - The Mandatory field error message is not dispalyed for Line Consignee *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.043 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=147)
	public void test_9d044_MandatoryFieldCheck() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.044 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.044 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.044- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.044- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.044- Delivery Booking page is not loaded *****");
	    	wait(5000);
	    	if(!isNullOrBlank(strCrossDockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName",strCrossDockOrder), true,"***** Test ID : 9.044 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName"),false,"***** Test ID : 9.044 - Consignee Field value is not read only *****");
	    	wait(2000);
	    	
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"OrderWarehouseName");
	    	
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.044 - The Mandatory field error message is not dispalyed for Line Consignee *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
					    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.044 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=149)
	public void test_9d045_CheckForSplitDelivery() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.045 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.045 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.045- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.045- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.045- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.045 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	wait(2000);
	    	if(!isNullOrBlank(strCrossDockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitLineTypeName",strCrossDockOrder), true,"***** Test ID : 9.045 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	

	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitConsigneeName"),false,"***** Test ID : 9.045 - Consignee Field value is not read only *****");
	    	wait(2000);
	       	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitWarehouseName");
	    	
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.045 - The Mandatory field error message is not dispalyed for Line Consignee *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.045 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=150)
	public void test_9d046_Set_Split_Delivery_TO_N() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.046 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.046 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.046- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.046- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.046- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.046 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridSplit"), true,"***** Test ID : 9.046 - Split Delivery Grid is not present *****"); 	
	    	wait(3000);
	    	if(!isNullOrBlank(strSplitDeliveryN)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryN), true,"***** Test ID : 9.046 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridSplit"), false,"***** Test ID : 9.046 - Split Delivery Grid is present *****");
					    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.046 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	

	
	@Test(priority=152)
	public void test_9d047_Check_Write_Enble_Fiellds() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.047 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.047 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.047- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.047- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.047- Delivery Booking page is not loaded *****");
		    		
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridContainer"), true,"***** Test ID : 9.047 - Container Grid is not present *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridSKU"), true,"***** Test ID : 9.047 - SKU Grid is not present *****");
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingRef"),true,"***** Test ID : 9.047 - Booking ref Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderClientRef"),true,"***** Test ID : 9.047 - Client ref Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName"),true,"***** Test ID : 9.047 - Line type Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName"),true,"***** Test ID : 9.047 - Consignee Field value is not read only *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBay"),true,"***** Test ID : 9.047 - Bay Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingDate"),true,"***** Test ID : 9.047 - Booking Time Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingTime"),true,"***** Test ID : 9.047 - Booking Time Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderDeliveryMethod"),true,"***** Test ID : 9.047 - Delivery Method Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingReason"),true,"***** Test ID : 9.047 - Booking Reason Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderHaulier"),true,"***** Test ID : 9.047 - Haulier Field value is not read only *****");
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.045 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	
	
	@Test(priority=155)
	public void test_9d051_Check_Blank_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
	//	objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect());
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.051 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.051 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.051- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.051- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.051- Delivery Booking page is not loaded *****");
		    		
	    	if(!isNullOrBlank(strCrossDockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strCrossDockOrder), true,"***** Test ID : 9.051 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(7000);
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName").equals("/S"), true, "***** Test Id :9.051- Consignee field has some value  *****");
	    	System.out.println("str "+objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName"));
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridContainer",0,"WarehouseName"), "/S", "***** Test Id :9.051- Warehouse field has some value  *****");
	    	
	    	
	    		
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.051 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=157)
	public void test_9d052_Check_Blank_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.052 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.052 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.052- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.052- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.052- Delivery Booking page is not loaded *****");
		    		
	    	if(!isNullOrBlank(strCrossDockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName",strCrossDockOrder), true,"***** Test ID : 9.052 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(6000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName").equals("\\s"), true,"***** Test Id :9.052- Consignee field has some value  *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderWarehouseName").equals("\\s"), true,"***** Test Id :9.052- Warehouse field has some value  *****");
	    	
	    	
	    		
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.052 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=159)
	public void test_9d053_Check_Blank_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.053 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.053 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.053- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.053- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.053- Delivery Booking page is not loaded *****");
		    		
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.053 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	wait(3000);
	    	if(!isNullOrBlank(strCrossDockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitLineTypeName",strCrossDockOrder), true,"***** Test ID : 9.053 - Line Type value is not entered *****");
			}else{
		 		strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(6000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitConsigneeName").equals("\\s"), true,"***** Test Id :9.053- Consignee field has some value  *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitWarehouseName").equals("\\s"), true,"***** Test Id :9.053- Warehouse field has some value  *****");
	    	
    		
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.053 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}


	
	@Test(priority=160)
	public void test_9d057_Verify_Booking_Reason() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.057 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.057 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.057- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.057- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.057- Delivery Booking page is not loaded *****");
		    		
	    	//get reasoncode from dropdown
	    	
	    	ArrayList<String>  lstBookingReason = objAdjunoWFLDeliveryBookingPOM.getReasonCodeFromDropDown();
	    	
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogReasonCodeList, lstBookingReason);
    		
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.057 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	@Test(priority=162)
	public void test_9d058_Verify_Booking_Reason() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.058 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.058 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.058- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.058- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.058- Delivery Booking page is not loaded *****");
	    	//get reasoncode from dropdown
	    	
	    	ArrayList<String>  lstBookingReason = objAdjunoWFLDeliveryBookingPOM.getReasonCodeFromDropDown();
	    	
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogReasonCodeList, lstBookingReason);
    		
	    	
	    		
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.058 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=165)
	public void test_9d059_Verify_Booing_Reason() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.059 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.059 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.059- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.059- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.059- Delivery Booking page is not loaded *****");
		    		
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.059 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	//get reasoncode from dropdown
	    	
	    	ArrayList<String>  lstBookingReason = objAdjunoWFLDeliveryBookingPOM.getReasonCodeFromDropDown();
	    	
	    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogReasonCodeList, lstBookingReason);
    		
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.059 - "+strMessage+" *****");
		
		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority=170)
	public void test_9d060_Verify_Booing_Qty() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.060 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.060 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.060 - Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.060 - Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.060- Delivery Booking page is not loaded *****");
		    		
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.060 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OpenProgressQuantity"), "0","***** Test Id :9.060 -Balance Ctns value not Zero *****");
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.060 - "+strMessage+" *****");
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=190)
	public void test_9d061_Verify_Booing_Qty() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausDeliveryBooked)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausDeliveryBooked, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.061 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.061 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.061- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.061- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.061- Delivery Booking page is not loaded *****");
		    		
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.061 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	wait(2000);
	    	
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitBookedQty","10000"), true,"***** Test ID : 9.061 - Booked qty value is not entered *****");
			
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSKU",0,"OpenProgressQuantity"), "> Open Balance must not be less than zero","***** Test ID : 9.061 - Balance qty Valaidation message mismatch  *****");
			
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.059 - "+strMessage+" *****");
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=195)
	public void test_9d062_Check_Grid_Fiellds() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.062 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.062 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.062- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.062- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.062- Delivery Booking page is not loaded *****");
		    		
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridContainer"), true,"***** Test ID : 9.062 - Container Grid is not present *****");
	    //	wait(3000);
	    //	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridSKU"), true,"***** Test ID : 9.062 - SKU Grid is not present *****");
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"BookingRef"),true,"***** Test ID : 9.062 - Booking ref Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"ClientRef"),true,"***** Test ID : 9.062 - Client ref Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName"),true,"***** Test ID : 9.062 - Line type Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName"),true,"***** Test ID : 9.062 - Conginee Field value is not read only *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"WarehouseName"),true,"***** Test ID : 9.062 - Warehouse Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"Bay"),true,"***** Test ID : 9.062 - Bay Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"BookingDate"),true,"***** Test ID : 9.062 - Booking date Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"BookingTime"),true,"***** Test ID : 9.062 - Booking Time Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"DeliveryMethod"),true,"***** Test ID : 9.062 - delivery Method Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"Haulier"),true,"***** Test ID : 9.062 - Haulier Field value is not read only *****");
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.062 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=200)
	public void test_9d063_SearchOrderNumberLevel() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.063 - Select Chevron not clicked *****");

		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.063 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelProduct)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelProduct, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.063 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.063- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.063 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	for (int i = 0; i <=objAdjunoWFLDeliveryBookingPOM.getLstContainer().size()-1; i++) {
	    		strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstContainer().get(i).getText(),"container",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstWflRef().get(i).getText(),"wflref",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstIdentifier().get(i).getText(),"identifier",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstProduct().get(i).getText(),"product",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSku().get(i).getText(),"SKU",i,false);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSplitId().get(i).getText(),"splitid",i,false);
		    	
			}
	    	
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.063 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=205)
	public void test_9d064_Check_Grid_Fiellds() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.064 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelProduct)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelProduct, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.064 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.064- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.064- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.064- Delivery Booking page is not loaded *****");
		    		
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridContainer"), true,"***** Test ID : 9.064- Container Grid is not present *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron,"GridSKU"), true,"***** Test ID : 9.064 - SKU Grid is not present *****");
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingRef"),true,"***** Test ID : 9.064 - Booking ref Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderClientRef"),true,"***** Test ID : 9.064 - Client ref Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName"),true,"***** Test ID : 9.064 - Line type Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName"),true,"***** Test ID : 9.064 - Conginee Field value is not read only *****");
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBay"),true,"***** Test ID : 9.064 - Bay Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingDate"),true,"***** Test ID : 9.064 - Booking Time Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingTime"),true,"***** Test ID : 9.064 - Booking Time Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderDeliveryMethod"),true,"***** Test ID : 9.064 - Delivery Method Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingReason"),true,"***** Test ID : 9.064 - Booking Reason Field value is not read only *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderHaulier"),true,"***** Test ID : 9.064 - Haulier Field value is not read only *****");
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.064 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=210)
	public void test_9d065_SearchOrderNumberLevel() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		wait(2000);
		driver.navigate().back();
		wait(2000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strAnyStatusValue, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.065 - Status Field value is not entered as Any *****");
			
		}else{
			strMessage=strMessage+"Status Any Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.065 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.065- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	for (int i = 0; i <=objAdjunoWFLDeliveryBookingPOM.getLstContainer().size()-1; i++) {
	    		strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstContainer().get(i).getText(),"container",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstWflRef().get(i).getText(),"wflref",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstIdentifier().get(i).getText(),"identifier",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstProduct().get(i).getText(),"product",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSku().get(i).getText(),"SKU",i,true);
		    	strMessage = strMessage + objAdjunoWFLDeliveryBookingPOM.verifyValue2(objAdjunoWFLDeliveryBookingPOM.getLstSplitId().get(i).getText(),"splitid",i,true);
		    	
			}
	    	
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.065 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=215)
	public void test_9d068_Verify_BookingRef() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(2000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.068 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.068 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.068- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.068- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.068- Delivery Booking page is not loaded *****");
		    		
	    	
	    	String strBookingRefErrorMessage = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron, "GridContainer", 0, "BookingRef");
	    	if(!isNullOrBlank(strBookingRefErrorMessage)){
	    		if(strBookingRefErrorMessage.equalsIgnoreCase("> &#39;Booking Ref&#39; is a required field")){
	    			
	    		}else{
	    			strMessage = strMessage + " Booking Ref error message mismatch ";
	    		}
	    	}else{
	    		strMessage = strMessage +" Booking ref Error message is blank";
	    	}
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.068 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=220)
	public void test_9d069_Verify_LineType_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		wait(2000);
		driver.navigate().back();
		wait(4000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.069 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.069 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.069- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.069- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.069- Delivery Booking page is not loaded *****");
		    		
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "LineType"),true,"***** Test Id :9.069- Line type Field is not clicked *****");
	    	
	    	lineTypeList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogLineTypeList, lineTypeList);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.069 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=225)
	public void test_9d070_Verify_Consignee_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
	//	objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect());

		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.070 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.070 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.070- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.070- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.070- Delivery Booking page is not loaded *****");
		    
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridContainer", 0, "LineTypeName", strCrossDockOrder), true, "***** Test Id :9.070- LineType value is not set *****");
	    	wait(2000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ConsigneeName"),true,"***** Test Id :9.070- Consignee Field is not clicked *****");
	    	wait(2000);
	    	consigneeList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstConsignee());
	    	System.out.println("consigneeList "+consigneeList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogconsigneeList, consigneeList);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.070 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=225)
	public void test_9d071_Verify_ConsigneeField() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.071 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.071 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.071- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.071- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.071- Delivery Booking page is not loaded *****");
		  
	    	if(!isNullOrBlank(strLineTypeStock)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridContainer", 0, "LineTypeName", strLineTypeStock), true,"***** Test ID : 9.071 - Line type Field value is not entered *****");
			}else{
				strMessage=strMessage+"Line type Value is Empty in XML";
			}
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ConsigneeName"), false,"***** Test ID : 9.071 - Consignee field is not read only *****");
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.071 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=230)
	public void test_9d072_Verify_WareHouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.072 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.072 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.072- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.072- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.072- Delivery Booking page is not loaded *****");
		  
	    	if(!isNullOrBlank(strCrossDockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridContainer", 0, "LineTypeName", strCrossDockOrder), true,"***** Test ID : 9.072 - Line type Field value is not entered *****");
			}else{
				strMessage=strMessage+"Line type Value is Empty in XML";
			}
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "WarehouseName"),true,"***** Test Id :9.072- Warehouse Field is not clicked *****");
	    	
	    	warehouseList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("72.warehouseList "+warehouseList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogWarehouseList, warehouseList);
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.072 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=240)
	public void test_9d073_verify_Warehouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.073 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.073- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.073- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.073- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strDirectOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strDirectOrder), true,"***** Test ID : 9.073 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(3000);
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName"),true,"***** Test ID : 9.073 - Consignee Field value is not read only *****");
	    	
	    	String strErrorMessageConsignee = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName");
	    	System.out.println("73.strErrorMessageConsignee "+strErrorMessageConsignee);

	    	if(!strErrorMessageConsignee.equals("")){
			     boolean bFlag = strErrorMessageConsignee.equalsIgnoreCase("> &#39;Consignee#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.073 - The Mandatory field error message is not dispalyed for Consignee *****");
			 }else{
				 strMessage =strMessage + " Consignee field error message is empty ";
			 }
	    	wait(5000);
	    	
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"WarehouseName");
	    	System.out.println("73.strErrorMessageWarehouse "+strErrorMessageWarehouse);
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse&#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.073 - The Mandatory field error message is not dispalyed for WareHouse *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.073 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority=245)
	public void test_9d074_verify_Warehouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(4000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.074 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.074- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.074- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.074- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strStockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strStockOrder), true,"***** Test ID : 9.074 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(2000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridContainer",0,"ConsigneeName"),false,"***** Test ID : 9.074 - Consignee Field value is not read only *****");
	    	
	    	wait(5000);
	    	
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"WarehouseName");
	    	System.out.println("74.strErrorMessageWarehouse "+strErrorMessageWarehouse);
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse&#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.074 - The Mandatory field error message is not dispalyed for Line Consignee *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.074 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=250)
	public void test_9d075_verify_BookingDate() throws java.text.ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(4000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.075 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.075- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.075- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.075- Delivery Booking page is not loaded *****");
		    
	    	String strETA = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ETA");
	    	
	    	 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	    	 Date date = null;

	         try {
	        	 date = formatter.parse(strETA);
	             System.out.println(date);
	         } catch (ParseException e) {
	             e.printStackTrace();
	         }	     	
	        
	         wait(5000);
	    	ArrayList<String> listError = objAdjunoWFLDeliveryBookingPOM.getListOfValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"BookingDate");
	    	 
	    	for (int i = 0; i <= listError.size()-1; i++) {
				System.out.println("75.listError.get(i) "+listError.get(i));
	    		 if(listError.get(i).equalsIgnoreCase("> &#39;Booking Date&#39; is a required field")){
				    		       
				     objSoftAssert.assertEquals(listError.get(i).equalsIgnoreCase("> &#39;Booking Date&#39; is a required field"), true,"***** Test ID :9.075 - The Mandatory field error message is not dispalyed for Booking Date *****");
				 }else{
					 if(i== listError.size()-1){
					 strMessage =strMessage + " Booking Date error message is empty ";
					 }
				 }  
			}
	    	
 			strBookingDate =  objAdjunoWFLDeliveryBookingPOM.compareDate(date);

	    	 if(!isNullOrBlank(strBookingDate)){
	        	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridContainer", 0, "BookingDate", strBookingDate), true,"***** Test ID : 9.075 - BookingDate date field value is not entered *****");
	         }else{
	        	 strMessage = strMessage+"\"strBookingDate\" is empty";
	 	    		
	         }
	         wait(2000);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.075 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	

	@Test(priority=255)
	public void test_9d076_verify_Delivery_Methods() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.076 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.076- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.076- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.076- Delivery Booking page is not loaded *****");
		    
	    	
	//    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridContainer", 0, "DeliveryMethod"),"Single Drop","***** Test Id :9.076- Warehouse Field is not clicked *****");
	    	wait(2000);
	//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "DeliveryMethod"),true,"***** Test Id :9.076- Delivery Method Field is not clicked *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getDeliveryMethod()), true , "***** Test Id :9.076- Delivery method is not clicked *****");
	    	wait(2000);
	    	deliveryMethodList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("76.deliveryMethodList "+deliveryMethodList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogDeliveryMethodList, deliveryMethodList);
	    	
	    	
	    	
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.076 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=260)
	public void test_9d077_verify_Delivery_Methods() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.077 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.077- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.077- Delivery Booking is not clicked *****");
	    	wait(4000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.077- Delivery Booking page is not loaded *****");
		   	wait(4000);
	//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "Haulier"),true,"***** Test Id :9.077- Haulier Field is not clicked *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getHaulier()),true,"***** Test Id :9.077- Haulier Field is not clicked *****");

			haulierList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("77.haulierList "+haulierList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogHaulierList, haulierList);
	    	
	     	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.077 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=265)
	public void test_9d079_Verify_BookingRef() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.079 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.079 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.079- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.079- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.079- Delivery Booking page is not loaded *****");
		    		
	    	wait(5000);
	    	String strBookingRefErrorMessage = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderBookingRef");
	    	System.out.println("79.strBookingRefErrorMessage "+strBookingRefErrorMessage);

	    	if(!isNullOrBlank(strBookingRefErrorMessage)){
	    		if(strBookingRefErrorMessage.equalsIgnoreCase("> &#39;Booking Ref&#39; is a required field")){
	    			
	    		}else{
	    			strMessage = strMessage + " Booking Ref error message mismatch ";
	    		}
	    	}else{
	    		strMessage = strMessage +" Booking ref Error message is blank";
	    	}
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.079 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=270)
	public void test_9d080_Verify_LineType_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.080 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.080 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.080- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.080- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.080- Delivery Booking page is not loaded *****");
	    	wait(3000);	
	//    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderLineTypeName"),true,"***** Test Id :9.080- Line type Field is not clicked *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getLinetype()),true,"***** Test Id :9.080- Line type Field is not clicked *****");

	    	wait(3000);
	    	lineTypeList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("80.lineTypeList "+lineTypeList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogLineTypeList, lineTypeList);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.080 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=275)
	public void test_9d081_Verify_Consignee_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.081 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.081 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.081- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.081- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.081- Delivery Booking page is not loaded *****");
	//    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderConsigneeName"),true,"***** Test Id :9.081- Consignee Field is not clicked *****");
		
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getConsignee()),true,"***** Test Id :9.081- Consignee Field is not clicked *****");
	    	wait(3000);
	    	consigneeList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstConsignee());
	    	System.out.println("81.consigneeList "+consigneeList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogconsigneeList, consigneeList);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.081 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	@Test(priority=280)
	public void test_9d082_Verify_ConsigneeField() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.082 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.082 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.082- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.082- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.082- Delivery Booking page is not loaded *****");
		  
	    	if(!isNullOrBlank(strLineTypeStock)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderLineTypeName", strLineTypeStock), true,"***** Test ID : 9.082 - Line type Field value is not entered *****");
			}else{
				strMessage=strMessage+"Line type Value is Empty in XML";
			}
	    	wait(4000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderConsigneeName"), false,"***** Test ID : 9.082 - Consignee field is not read only *****");
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.082 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=285)
	public void test_9d083_Verify_WareHouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.083 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.083 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.083- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.083- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.083- Delivery Booking page is not loaded *****");
		  
	    	if(!isNullOrBlank(strLineTypeStock)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridContainer", 0, "LineTypeName", strLineTypeStock), true,"***** Test ID : 9.083 - Line type Field value is not entered *****");
			}else{
				strMessage=strMessage+"Line type Value is Empty in XML";
			}
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridContainer", 0, "Warehouse"),true,"***** Test Id :9.083- Warehouse Field is not clicked *****");
	    	
	    	warehouseList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("83.warehouseList "+warehouseList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogWarehouseList, warehouseList);
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.083 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=290)
	public void test_9d084_verify_Warehouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
	//	objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect());
		wait(4000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.084 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.084 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.084- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.084- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.084- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strDirectOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName",strDirectOrder), true,"***** Test ID : 9.084 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	
//	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName"),false,"***** Test ID : 9.084 - Consignee Field value is not read only *****");
	    	
	    	String strErrorMessageConsignee = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName");
	    	System.out.println("84.strErrorMessageConsignee "+strErrorMessageConsignee);
	    	if(!strErrorMessageConsignee.equals("")){
			     boolean bFlag = strErrorMessageConsignee.equalsIgnoreCase("> &#39;Consignee#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.084 - The Mandatory field error message is not dispalyed for Consignee *****");
			 }else{
				 strMessage =strMessage + " Consignee field error message is empty ";
			 }
	    	
	    	
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderWarehouseName");
	    	System.out.println("84.strErrorMessageWarehouse "+strErrorMessageWarehouse);

	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse&#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.084 - The Mandatory field error message is not dispalyed for WareHouse *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.084 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=295)
	public void test_9d085_verify_Warehouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.085 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.085- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.085- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.085- Delivery Booking page is not loaded *****");
		    
	    	if(!isNullOrBlank(strStockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName",strStockOrder), true,"***** Test ID : 9.085 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderConsigneeName"),false,"***** Test ID : 9.085 - Consignee Field value is not read only *****");
	    	
	    	
	    	
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderWarehouseName");
	    	System.out.println("85.strErrorMessageWarehouse "+strErrorMessageWarehouse);
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse&#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.085 - The Mandatory field error message is not dispalyed for Line Consignee *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.085 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=300)
	public void test_9d086_verify_BookingDate() throws java.text.ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.086 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.086- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.086- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.086- Delivery Booking page is not loaded *****");
		    
	    	String strETA = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ETA");
	    	
	    	 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	    	 Date date = null;

	         try {
	        	 date = formatter.parse(strETA);
	             System.out.println(date);
	         } catch (ParseException e) {
	             e.printStackTrace();
	         }
	    	
	    	ArrayList<String> listError = objAdjunoWFLDeliveryBookingPOM.getListOfValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridContainer",0,"OrderBookingDate");
	    	 
	    	for (int i = 0; i <= listError.size()-1; i++) {
				System.out.println("86.listError.get(i) "+listError.get(i));
	    		 if(listError.get(i).equalsIgnoreCase("&gt; &#39;Booking Date&#39; is a required field")){
				    		       
				     objSoftAssert.assertEquals(listError.get(i).equalsIgnoreCase("&gt; &#39;Booking Date&#39; is a required field"), true,"***** Test ID :9.086 - The Mandatory field error message is not dispalyed for Booking Date *****");
				 }else{
					 if(i== listError.size()-1){
					 strMessage =strMessage + " Booking Date error message is empty ";
					 }
				 }  
			}
	    	
	         strBookingDate =  objAdjunoWFLDeliveryBookingPOM.compareDate(date);

	    	
	    	if(!isNullOrBlank(strBookingDate)){
	        	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderBookingDate", strBookingDate), true,"***** Test ID : 9.075 - BookingDate date field value is not entered *****");
	         }else{
	        	 strMessage = strMessage+"\"strBookingDate\" is empty";
	 	    		
	         }
	         wait(2000);
	    	
	    	
	   
	    	 
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.086 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	

	@Test(priority=305)
	public void test_9d087_verify_Delivery_Methods() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.087 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.087- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.087- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.087- Delivery Booking page is not loaded *****");
		    
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderDeliveryMethod"),"Single Drop","***** Test Id :9.087- Warehouse Field is not clicked *****");
	    	wait(5000);
	//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSKU", 0, "DeliveryMethod"),true,"***** Test Id :9.087- Delivery Method Field is not clicked *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getDeliveryMethod()),true,"***** Test Id :9.087- Delivery Method Field is not clicked *****");
			wait(4000);
	    	deliveryMethodList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("87.deliveryMethodList "+deliveryMethodList.size());
	    	wait(3000);
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogDeliveryMethodList, deliveryMethodList);
	    	
	    	
	    	
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.087 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=310)
	public void test_9d088_verify_Delivery_Methods() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.088 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.088- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.088- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.088- Delivery Booking page is not loaded *****");
	    	wait(5000);
	//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSKU", 0, "Haulier"),true,"***** Test Id :9.088- Haulier Field is not clicked *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getHaulier()),true,"***** Test Id :9.088- Haulier Field is not clicked *****");

			haulierList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("88.haulierList "+haulierList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogHaulierList, haulierList);
	    	
	     	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.088 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	
	
	@Test(priority=265)
	public void test_9d078_Complete() throws java.text.ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.078 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.078- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.078- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.078- Delivery Booking page is not loaded *****");
		   	
	    	
	    	String strETA = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ETA");
	    	
	    	 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	    	 Date date = null;

	         try {
	        	 date = formatter.parse(strETA);
	             System.out.println(date);
	         } catch (ParseException e) {
	             e.printStackTrace();
	         }
	    	
	    	if(date.before(new Date())){
	    		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(objAdjunoWFLDeliveryBookingPOM.getDate(1, "dd MMM yyyy"), strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.078 - Level of detail Field value is not entered *****");
	    	}else{
	    		
	    	}
	    	
	    	if(!isNullOrBlank(strStockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strStockOrder), true,"***** Test ID : 9.078 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	
	    	if(!isNullOrBlank(strBookingRef)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strBookingRef), true,"***** Test ID : 9.078 - BookingRef value is not entered *****");
			}else{
				strMessage=strMessage+"Booking ref Value is Empty in XML";
			}
	    	
	    	if(!isNullOrBlank(strWarehouse)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strWarehouse), true,"***** Test ID : 9.078 - WareHouse value is not entered *****");
			}else{
				strMessage=strMessage+"Warehouse Value is Empty in XML";
			}
	    	
	    	if(!isNullOrBlank(strBookingTime)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridContainer",0,"LineTypeName",strWarehouse), true,"***** Test ID : 9.078 - Booking Time value is not entered *****");
			}else{
				strMessage=strMessage+"Booking Time Value is Empty in XML";
			}
	    	
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvComplete2()),true,"***** Test Id :9.078 - Complete Chevron Not clicked");
	     	wait(5000); 
	    	
	     	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameCompletePage),true,"***** Test Id :9.078- Complete page is not loaded *****");
		   	
	     	String strCompleteMessage = objAdjunoWFLDeliveryBookingPOM.getWebElementValue(strFormNameCompletePage, "UpdateMessage");
	     	
	     	if(strCompleteMessage.contains("Delivery Booking has been made. ")){
	     		
	     	}else{
	     		strMessage = strMessage + " Complete message mismatch ";
	     	}
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.078 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=315)
	public void test_9d089_Complete() throws java.text.ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.089 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelOrderNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelOrderNumber, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.089 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.089- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.089- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.089- Delivery Booking page is not loaded *****");
		   	
	    	
	    	String strETA = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ETA");
	    	
	    	 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	    	 Date date = null;

	         try {
	        	 date = formatter.parse(strETA);
	             System.out.println(date);
	         } catch (ParseException e) {
	             e.printStackTrace();
	         }
	    	   	
	    	
	    	if(!isNullOrBlank(strStockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderLineTypeName",strStockOrder), true,"***** Test ID : 9.089 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(2000);
	    	if(!isNullOrBlank(strBookingRef)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingRef",strBookingRef), true,"***** Test ID : 9.089 - BookingRef value is not entered *****");
			}else{
				strMessage=strMessage+"Booking ref Value is Empty in XML";
			}
	    	wait(2000);
	    	if(!isNullOrBlank(strWarehouse)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderWarehouseName",strWarehouse), true,"***** Test ID : 9.089 - WareHouse value is not entered *****");
			}else{
				strMessage=strMessage+"Warehouse Value is Empty in XML";
			}
	    	wait(2000);
	    	if(!isNullOrBlank(strBookingTime)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderBookingTime",strBookingTime), true,"***** Test ID : 9.089 - Booking Time value is not entered *****");
			}else{
				strMessage=strMessage+"Booking Time Value is Empty in XML";
			}
	    	wait(2000);
	    	strBookingDate =  objAdjunoWFLDeliveryBookingPOM.compareDate(date);

	    	if(!isNullOrBlank(strBookingDate)){
	    		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderBookingDate", strBookingDate), true,"***** Test ID : 9.075 - BookingDate date field value is not entered *****");
	    	}else{
	    		strMessage = strMessage+"\"strBookingDate\" is empty";
			 	    		
	    	}
	    	wait(2000);
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvComplete2()),true,"***** Test Id :9.089 - Complete Chevron Not clicked");
	     	wait(5000); 
	    	
	     	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameCompletePage),true,"***** Test Id :9.089- Complete page is not loaded *****");
		   	
	     	String strCompleteMessage = objAdjunoWFLDeliveryBookingPOM.getWebElementValue(strFormNameCompletePage, "UpdateMessage");
	     	System.out.println("89.strCompleteMessage "+strCompleteMessage);
	     	if(strCompleteMessage.contains("Delivery Booking has been made. ")){
	     		
	     	}else{
	     		strMessage = strMessage + " Complete message mismatch ";
	     	}
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.089 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=320)
	public void test_9d090_verify_Split_Delivery() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.073 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.090 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.090- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.090- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.090- Delivery Booking page is not loaded *****");
		   	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridSKU", 0, "SplitDelivery"),"N","***** Test Id :9.090- Split Delivery mandatory Value is not N *****");
	    	
//	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSKU", 0, "SplitDelivery"), true,"***** Test Id :9.090- Split Delivery Field is not cleared *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitDelivery()), true,"***** Test Id :9.090- Split Delivery Field is not cleared *****");

	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSKU", 0, "SplitDelivery", ""), true,"***** Test Id :9.090- Split Delivery Field is not cleared *****");

	    	wait(4000);
	    	String strErrorSplitDelivery = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron, "GridSKU", 0, "SplitDelivery");
	    	System.out.println("90.strErrorSplitDelivery "+strErrorSplitDelivery);
	    	if(!isNullOrBlank(strErrorSplitDelivery)){
	    		boolean bFlag = strErrorSplitDelivery.equalsIgnoreCase("> &#39;Split Delivery&#39; is a required field");
 		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.090 - The Mandatory field error message is not dispalyed for SplitDelivery *****");
			 }else{
				 strMessage =strMessage + " Slipt delivery error message is empty ";
			 }
	    	
//	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSKU", 0, "SplitDelivery"),true,"***** Test Id :9.090- Split Delivery Field is not clicked *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitDelivery()),true,"***** Test Id :9.090- Split Delivery Field is not clicked *****");
    	
	    	wait(2000);
	    	splitDeliveryList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("90.splitDeliveryList "+splitDeliveryList.size());
	    	for (int i = 0; i <=splitDeliveryList.size()-1; i++) {
				if(splitDeliveryList.get(i).equalsIgnoreCase("")||splitDeliveryList.get(i).equalsIgnoreCase("N") || splitDeliveryList.get(i).equalsIgnoreCase("Y")){
					
				}else{
					strMessage = strMessage + " Split Delivery Value Mismatch ";
				}
			}
			
	     	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.090 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	
	@Test(priority=325)
	public void test_9d091_Verify_Split_Delivery_Grid() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.091 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.091 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.091 - Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.091 - Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.091- Delivery Booking page is not loaded *****");
		    		
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.091 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkDoesElementExist(strFormNameDeliveryBookingChevron, "GridSplit"), true,"***** Test Id :9.091- split Delivery grid is not found *****");	
	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.091 - "+strMessage+" *****");
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=330)
	public void test_9d092_Verify_BookingQty() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.092 - Status Field value is not entered as Delivery Booked *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.092 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.092 - Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.092 - Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.092- Delivery Booking page is not loaded *****");
		    		
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.092 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	String strShipQty = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridSKU",0,"OrderShipQty");
	    	int shipQty = Integer.parseInt(strShipQty);
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitBookedQty", String.valueOf(shipQty-1)), true,"***** Test ID : 9.092 - balance Qty value is not entered *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBookingChevron, "AddSplitRow"), true,"***** Test ID : 9.092 - Add row button is not clicked *****");
	    	
	    	String strBalance = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron,"GridSplit",1,"SplitBookedQty");
	    	int balanceQty = Integer.parseInt(strBalance);
	    	
	    	if(balanceQty==1){
	    		
	    	}else{
	    		strMessage = strMessage + " Balance Booked qty value is not correct";
	    	}
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.092 - "+strMessage+" *****");
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=335)
	public void test_9d093_Verify_BookingRef() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.093 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.093 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.093- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.093- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.093- Delivery Booking page is not loaded *****");
		    		
	    	wait(5000);
	    	if(!isNullOrBlank(strSplitDeliveryN)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryN), true,"***** Test ID : 9.092 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	wait(5000);
	    	
	    	String strBookingRefErrorMessage = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron, "GridSKU", 0, "OrderBookingRef");
	    	System.out.println("93.strBookingRefErrorMessage "+strBookingRefErrorMessage);
	    	if(!isNullOrBlank(strBookingRefErrorMessage)){
	    		if(strBookingRefErrorMessage.equalsIgnoreCase("> &#39;Booking Ref&#39; is a required field")){
	    			
	    		}else{
	    			strMessage = strMessage + " Booking Ref error message mismatch ";
	    		}
	    	}else{
	    		strMessage = strMessage +" Booking ref Error message is blank";
	    	}
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.093 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	@Test(priority=340)
	public void test_9d094_Verify_LineType_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.094 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.094 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.094- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.094 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.094- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.094- Delivery Booking page is not loaded *****");
		    		
	    	

	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.094 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	//    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSplit", 0, "LineType"),true,"***** Test Id :9.094- Line type Field is not clicked *****");
	    	wait(3000);
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitLineType()),true,"***** Test Id :9.094- Line type Field is not clicked *****");
	    	wait(2000);
	    	lineTypeList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("94.lineTypeList "+lineTypeList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogLineTypeList, lineTypeList);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.094 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=345)
	public void test_9d095_Verify_Consignee_Value() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.095 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.095 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.095- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.095- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.095- Delivery Booking page is not loaded *****");
		    	
	      	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.095 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	//    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSplit", 0, "ConsigneeName"),true,"***** Test Id :9.095- Consignee Field is not clicked *****");
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitConsignee()),true,"***** Test Id :9.095- Consignee Field is not clicked *****");
	    	
	    	consigneeList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstConsignee());
	    	System.out.println("95.consigneeList "+consigneeList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogconsigneeList, consigneeList);
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.095 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=350)
	public void test_9d096_Verify_ConsigneeField() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.096 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.096 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.096- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.096- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.096- Delivery Booking page is not loaded *****");
		  

	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.096 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	wait(3000);
//	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSplit", 0, "LineType"),true,"***** Test Id :9.094- Line type Field is not clicked *****");
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitLineType()),true,"***** Test Id :9.094- Line type Field is not clicked *****");
    		  wait(3000);  	
	    	if(!isNullOrBlank(strLineTypeStock)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSplit", 0, "LineTypeName", strLineTypeStock), true,"***** Test ID : 9.096 - Line type Field value is not entered *****");
			}else{
				strMessage=strMessage+"Line type Value is Empty in XML";
			}
	    	
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron, "GridSplit", 0, "ConsigneeName"), false,"***** Test ID : 9.096 - Consignee field is not read only *****");
	    	
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.096 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	@Test(priority=355)
	public void test_9d097_Verify_WareHouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.097 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		}
								
				
		if(!isNullOrBlank(strLevelContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelContainer, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.097 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.097- Refine Search is not clicked *****");
		//objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickChevorn(objAdjunoWFLDeliveryBookingPOM.getChvSelect()),true,"***** Test Id :9.065 - Select Chevron not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.097- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.097- Delivery Booking page is not loaded *****");
		  
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.097 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	wait(3000);
	    	if(!isNullOrBlank(strLineTypeStock)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSplit", 0, "LineTypeName", strLineTypeStock), true,"***** Test ID : 9.097 - Line type Field value is not entered *****");
			}else{
				strMessage=strMessage+"Line type Value is Empty in XML";
			}
	    	wait(2000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSplit", 0, "Warehouse"),true,"***** Test Id :9.097- Warehouse Field is not clicked *****");
//	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.gets),true,"***** Test Id :9.097- Warehouse Field is not clicked *****");
	    	
	    	warehouseList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("97.warehouseList "+warehouseList);
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogWarehouseList, warehouseList);
	    	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.097 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=360)
	public void test_9d098_verify_Warehouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.098 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.098 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.098- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.098- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.098- Delivery Booking page is not loaded *****");
		    wait(5000);
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.098 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	 wait(5000);	    	
	    	if(!isNullOrBlank(strDirectOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitLineTypeName",strDirectOrder), true,"***** Test ID : 9.098 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	
	    	wait(3000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitConsigneeName"),false,"***** Test ID : 9.098 - Consignee Field value is not read only *****");
	    	wait(3000);
	    	String strErrorMessageConsignee = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitConsigneeName");
	    	System.out.println("98.strErrorMessageConsignee "+strErrorMessageConsignee);
	    	if(!strErrorMessageConsignee.equals("")){
			     boolean bFlag = strErrorMessageConsignee.equalsIgnoreCase("> &#39;Consignee#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.098 - The Mandatory field error message is not dispalyed for Consignee *****");
			 }else{
				 strMessage =strMessage + " Consignee field error message is empty ";
			 }
	    	wait(4000);

	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitWarehouseName");
	    	System.out.println("98.strErrorMessageWarehouse "+strErrorMessageWarehouse);

	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> Please select a warehouse");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.098 - The Mandatory field error message is not dispalyed for WareHouse *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.098 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=365)
	public void test_9d099_verify_Warehouse() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.099 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.099 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.099- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.099- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.098- Delivery Booking page is not loaded *****");
		    
	    	
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.099 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	
	    	wait(3000);
	    	if(!isNullOrBlank(strStockOrder)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitLineTypeName",strStockOrder), true,"***** Test ID : 9.099 - Line Type value is not entered *****");
			}else{
				strMessage=strMessage+"Line Type Value is Empty in XML";
			}
	    	wait(4000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkFieldIsReadOnlyInGrid(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitConsigneeName"),false,"***** Test ID : 9.099 - Consignee Field value is not read only *****");
	    	
	    	
	    	
	    	String strErrorMessageWarehouse = objAdjunoWFLDeliveryBookingPOM.getValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitWarehouseName");
	    	System.out.println("99.strErrorMessageWarehouse "+strErrorMessageWarehouse);
	    	if(!strErrorMessageWarehouse.equals("")){
			     boolean bFlag = strErrorMessageWarehouse.equalsIgnoreCase("> &#39;Warehouse#39; is a required field");
			        		       
			     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :9.098 - The Mandatory field error message is not dispalyed for Line Consignee *****");
			 }else{
				 strMessage =strMessage + " WareHouse error message is empty ";
			 }
	    	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.099 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	
	@Test(priority=365)
	public void test_9d0100_verify_BookingDate() throws java.text.ParseException {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.0100- Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 
								
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.100 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.100- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.100- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.100- Delivery Booking page is not loaded *****");
		    
	    	
	    	String strETA = objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridContainer", 0, "ETA");
	    	
	    	 SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
	    	 Date date = null;

	         try {
	        	 date = formatter.parse(strETA);
	             System.out.println(date);
	         } catch (ParseException e) {
	             e.printStackTrace();
	         }
	         
	         
	         if(!isNullOrBlank(strSplitDeliveryY)){
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.100 - Split Delivery value is not entered *****");
				}else{
					strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
		    	
	    	wait(3000);
	    	ArrayList<String> listError = objAdjunoWFLDeliveryBookingPOM.getListOfValidationMessageGridElement(strFormNameDeliveryBookingChevron,"GridSplit",0,"SplitBookingDate");
	    	 
	    	for (int i = 0; i <= listError.size()-1; i++) {
				System.out.println("100.listError.get(i) "+listError.get(i));
	    		 if(listError.get(i).equalsIgnoreCase("> &#39;Booking Date&#39; is a required field")){
				    		       
				     objSoftAssert.assertEquals(listError.get(i).equalsIgnoreCase("> &#39;Booking Date&#39; is a required field"), true,"***** Test ID :9.100 - The Mandatory field error message is not dispalyed for Booking Date *****");
				 }else{
					 if(i== listError.size()-1){
					 strMessage =strMessage + " Booking Date error message is empty ";
					 }
				 }  
			}
	    	wait(3000);
	         strBookingDate =  objAdjunoWFLDeliveryBookingPOM.compareDate(date);
	    	System.out.println("100.strBookingDate "+strBookingDate);
	    	 if(!isNullOrBlank(strBookingDate)){
	        	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron, "GridSplit", 0, "SplitBookingDate", strBookingDate), true,"***** Test ID : 9.075 - BookingDate date field value is not entered *****");
	         }else{
	        	 strMessage = strMessage+"\"strBookingDate\" is empty";
	 	    		
	         }
	         wait(2000);
	
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.100 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	
	

	@Test(priority=375)
	public void test_9d101_verify_Delivery_Methods() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.101 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.101 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.101- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.101- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.101- Delivery Booking page is not loaded *****");
		   wait(4000);
		   
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.101 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	 wait(4000);
	    	 
	    	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitDeliveryMethod()),true,"***** Test Id :9.101- Delivery Method Field is not clicked *****");
	    	 wait(2000);
	    	 
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.getGridCellElementValue(strFormNameDeliveryBookingChevron, "GridSplit", 0, "SplitDeliveryMethod"),"Single Drop","***** Test Id :9.101- Warehouse Field is not clicked *****");
	    		    	
	    	deliveryMethodList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("101.deliveryMethodList "+deliveryMethodList.size());
	    	wait(2000);
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogDeliveryMethodList, deliveryMethodList);
	  
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.101 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}

	
	@Test(priority=380)
	public void test_9d102_verify_Delivery_Methods() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		driver.navigate().back();
		wait(3000);
		objAdjunoWFLDeliveryBookingPOM = new AdjunoWFLDeliveryBookingPOM(driver);
		clearFields();
		
		if(!isNullOrBlank(strStausPending)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strStausPending, strFormNameDeliveryBooking,"Param_Status"), true,"***** Test ID : 9.102 - Status Field value is not entered as Pending *****");
			
		}else{
			strMessage=strMessage+"Status Pending Value is Empty in XML";
		} 				
				
		if(!isNullOrBlank(strLevelSKU)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValue(strLevelSKU, strFormNameDeliveryBooking,"Param_LevelOfDetail"), true,"***** Test ID : 9.102 - Level of detail Field value is not entered *****");
		}else{
			strMessage=strMessage+"Level of Detail Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBookingRefineSearch"),true,"***** Test Id :9.102- Refine Search is not clicked *****");
		   
	    wait(5000);
	    searchResult = objAdjunoWFLDeliveryBookingPOM.isElementPresent(objAdjunoWFLDeliveryBookingPOM.getNoItem());
		
	    if(searchResult==false){
	    	
	    	objAdjunoWFLDeliveryBookingPOM.selectProduct(objAdjunoWFLDeliveryBookingPOM.getLstSelect());
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButton(strFormNameDeliveryBooking,"FORK_DeliveryBooking"),true,"***** Test Id :9.102- Delivery Booking is not clicked *****");
	    	wait(5000);
	    	objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.checkForIsPageLoaded(strFormNameDeliveryBookingChevron),true,"***** Test Id :9.102- Delivery Booking page is not loaded *****");
		   	wait(2000);
	    	if(!isNullOrBlank(strSplitDeliveryY)){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.setFieldValueForGridCell(strFormNameDeliveryBookingChevron,"GridSKU",0,"SplitDelivery",strSplitDeliveryY), true,"***** Test ID : 9.102 - Split Delivery value is not entered *****");
			}else{
				strMessage=strMessage+"Split Delivery Value is Empty in XML";
			}
	    	wait(3000);
	//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickGridField(strFormNameDeliveryBookingChevron, "GridSplit", 0, "SplitHaulier"),true,"***** Test Id :9.102- Haulier Field is not clicked *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryBookingPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryBookingPOM.getSplitHaulier()),true,"***** Test Id :9.102- Haulier Field is not clicked *****");
			wait(3000);
			haulierList = objAdjunoWFLDeliveryBookingPOM.getListValue(objAdjunoWFLDeliveryBookingPOM.getLstLineType());
	    	System.out.println("102.haulierList "+haulierList.size());
	    	strMessage = objAdjunoWFLDeliveryBookingPOM.verifyCatalogData(catalogHaulierList, haulierList);
	    	
	     	    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :9.102 - "+strMessage+" *****");
	   	
		
		objSoftAssert.assertAll();
	}
	

}
