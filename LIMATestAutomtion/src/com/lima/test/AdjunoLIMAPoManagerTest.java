package com.lima.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAPoConfirmationPOM;
import com.lima.pagefactory.AdjunoLIMAPoManagerPOM;

public class AdjunoLIMAPoManagerTest {
	
	 	WebDriver driver;

	    long nPage_Timeout = 0;
	    String strTestURL;
	    String strDriver;
	    String strTrackN0;
	    
	    String strUserName;
	    String strPassword;
	    String strPageTitlePoManager;
	    String strformNamePoManager;
	    String strViewFormNamePO;
	    String strPriorityFormName;
	    String strPageTitlePriority;
	    
	    String strEditFormNamePOManager;
	    String strNewProductCode;
	    String strNewIdentifie;
	    String strNewSKUCode;
	    String strDescription;
	    String strSupplier;
	    String strOriginDesc;
	    String strMode;
	    String strQuantity;
	    
	    String strConsignee;
	    String strOrderNumber;
	    String strOrderType;
	   
	    String strDestinationPortDesc;
	    String strCategory;
	    String strDestinationDesc;
	    String strDepartment;
	    
	    String strBuyer;
	    String strBuyingAgent;
	    String strBuyingTerms;
	    String strPurchaseCurrency;
	    String strRetailCurrency;
	    String strMerchandiser; 
	    String strQuantityTolerance;
	    //String strSeason;
	    String strLoadType;
	    String strRefrigeration;
	    String strColor;
	    String strPrimarySize;
	    String strSecondarySize;
	    String strCartons;
	    String strCube;
	    String strWeight;
	    String strWeightType;
	    String strPackType;
	    String strUnitPrice;
	    String strRetailPrice;
	    String strPriority;
	    String strProjectedLandedCost;
	    
	    String strPoConfirmationFormName;
	    String strConfirmationStatusAny="{Any}";
	    

	    AdjunoLIMALibrary objAdjunoLIMALibrary;
	    AdjunoUILibrary objAdjunoUILibrary;
	    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	    AdjunoLIMAPoConfirmationPOM objAdjunoLIMAPoConfirmationPOM;
	    AdjunoLIMAPoManagerPOM objAdjunoLIMAPoManagerPOM;
	    
	    
	    NodeList orderSummary;
	    NodeList newProduct;
	    NodeList orderHeader;
	    NodeList productSummary;
	    NodeList productSummaryDetailsCaptions;
	    NodeList productCardCaptions;
	    NodeList ratioPackCaptions;
	    NodeList ratioPackDetailsCaptions;
	    NodeList bwsCaptions;
	    NodeList productCardValue;
	    NodeList bwsValue;

	    String strVendorRef;
	   
	    String strDutyRate;
	    String strQARequest;
	    String strPiecesPerInner;
	    String strCartonWidth;
	    String strCartonLength;
	    String strCartonDepth;
	    String strOuterWeight;
	    String strOuterEAN;
	    String strInnerEAN;
	    String strNetWeight;
	    String strTI;
	    String strHI;
		String strCommodity;
		String strInnersPerOuter;

		String strTaxCode;
		String strABV;
		String strCL;
		String strWineType;
		String strVintage;
		String strLineDuty;

		String strPageTitleHome;

		String strShipDate;
		String strDeliveryDate;

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
	            
	            Node formNamePoManager = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_PO_Manager", dDoc, XPathConstants.NODE);
	            strformNamePoManager = formNamePoManager.getTextContent();
	                      
	            Node editFormNamePOManager = (Node) xPath.evaluate("/config/Generic/Edit_Form_Name_PO_Manager", dDoc, XPathConstants.NODE);
	            strEditFormNamePOManager = editFormNamePOManager.getTextContent();	            
	            
	            Node poConfirmationFormName = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_PO_ConfirmationForm", dDoc, XPathConstants.NODE);
	            strPoConfirmationFormName = poConfirmationFormName.getTextContent();
	            
	            Node priorityFormName = (Node) xPath.evaluate("/config/Generic/Priority_Form_Name", dDoc, XPathConstants.NODE);
	            strPriorityFormName = priorityFormName.getTextContent();
	            
	            	            
	            Node testLIMAURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
	            strTestURL = testLIMAURL.getTextContent();
	  	            
	  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
	  	        strUserName = limaUserName.getTextContent();

	  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
	  	        strPassword = limaPassword.getTextContent(); 
	  	            
	  	        Node pageTitleHome = (Node) xPath.evaluate("/config/LIMA/Page_Title_HomePage", dDoc, XPathConstants.NODE);
	  	        strPageTitleHome = pageTitleHome.getTextContent();
	  	            
	  	        Node pageTitlePoManager = (Node) xPath.evaluate("/config/LIMA/Page_Title_PO_Manager", dDoc, XPathConstants.NODE);
	  	        strPageTitlePoManager = pageTitlePoManager.getTextContent();
		            
	  	        Node viewFormNamePO = (Node) xPath.evaluate("/config/LIMA/View_Form_Name_PO", dDoc, XPathConstants.NODE);
	  	        strViewFormNamePO = viewFormNamePO.getTextContent();
		            
	  	        Node  pageTitlePriority= (Node) xPath.evaluate("/config/LIMA/Page_Title_Priority", dDoc, XPathConstants.NODE);
	  	        strPageTitlePriority = pageTitlePriority.getTextContent();
	  	        	           
	            
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
	            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getPoManagerXMLDataFileName());

	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            
	            orderSummary = (NodeList) xPath1.evaluate("/PO_Manager/Order_Summary", dDoc1, XPathConstants.NODESET);
	            newProduct = (NodeList) xPath1.evaluate("/PO_Manager/New_Product", dDoc1, XPathConstants.NODESET);
	            orderHeader = (NodeList) xPath1.evaluate("/PO_Manager/Order_Header", dDoc1, XPathConstants.NODESET);
	            productSummary = (NodeList) xPath1.evaluate("/PO_Manager/Product_Summary", dDoc1, XPathConstants.NODESET);
	            productSummaryDetailsCaptions = (NodeList) xPath1.evaluate("/PO_Manager/Lima_Product_Summary_Details_Captions", dDoc1, XPathConstants.NODESET); 
	            productCardCaptions = (NodeList) xPath1.evaluate("/PO_Manager/Lima_Product_Card_Captions", dDoc1,XPathConstants.NODESET);      
	            ratioPackCaptions = (NodeList) xPath1.evaluate("/PO_Manager/Ratio_Pack_Captions", dDoc1, XPathConstants.NODESET);
	            ratioPackDetailsCaptions = (NodeList) xPath1.evaluate("/PO_Manager/Ratio_Pack_Details_Captions", dDoc1, XPathConstants.NODESET);
	            bwsCaptions = (NodeList) xPath1.evaluate("/PO_Manager/BWS", dDoc1, XPathConstants.NODESET);
	            productCardValue = (NodeList) xPath1.evaluate("/PO_Manager/Product_Card_Value", dDoc1, XPathConstants.NODESET);
	            bwsValue = (NodeList) xPath1.evaluate("/PO_Manager/BWS_Value", dDoc1, XPathConstants.NODESET);
	            
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
	    
	    
    @Test(priority=5)
	public void accessPOManager()
	{
	     SoftAssert objSoftAssert = new SoftAssert();
	     objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	        
	     String strTitle = objAdjunoLIMAPoManagerPOM.callMouseHover(strformNamePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPoManager());
	    
	     boolean bFlag = true;
	    	
	    if (!(isNullOrBlank(strTitle))){
	    	if (strTitle.equalsIgnoreCase(strPageTitlePoManager))
	    		bFlag = true;
	    	else
	    		bFlag = false;
	    }else{
	    	bFlag = false;
	    }	       
	   	objSoftAssert.assertEquals(bFlag, true, "***** Page title of PO Manager tool is wrong  *****");
	   	try{
	        wait(3000);
	        objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getBtnOK1());
	        wait(3000);
	        }
	        catch(NoSuchElementException e)
	        {
	        	
	        }
	   	objSoftAssert.assertAll();
	   
	}
	    
	    
   	@Test(priority=10)
	public void test_1_VerifyExistanceOfChevorns()
   	{    	
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesChevronExist(objAdjunoLIMAPoManagerPOM.getChvSearch()), true,"***** Test ID : 1 - In PO Manager Tool \"search Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesChevronExist(objAdjunoLIMAPoManagerPOM.getChvEdit()), true,"***** Test ID : 1 - In PO Manager Tool \"Edit Chevorn\" is not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesChevronExist(objAdjunoLIMAPoManagerPOM.getChvComplete1()), true,"***** Test ID : 1 - In PO Manager Tool \"Complete Chevorn\" is not found *****");
     
        objSoftAssert.assertAll();
   	}

	    
   	@Test(priority=15)
	public void test_2_VerifyExistanceOfFields()
	{
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strformNamePoManager,"PARAM_OrderNumber"), true,"***** Test ID :2 - In PO Manager Tool Under Search chevron \"PO Number\" field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strformNamePoManager,"PARAM_CustomerReference"), true,"***** Test ID :2 -In PO Manager Tool Under Search chevron \"Vender ref\" field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strformNamePoManager,"PARAM_OrderCallOff"), true,"***** Test ID :2 -In PO Manager Tool Under Search chevron \"order call off\"  field is not found *****");
	 
	 	objSoftAssert.assertAll();
	}
	    
	    
   	@Test(priority=20)
	public void clearsearchFields()
	{
		SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
        Element el;	     
        strOrderNumber = objAdjunoLIMAPoManagerPOM.generateRandomPO();
		for (int i = 0; i <= orderHeader.getLength() - 1; i++) {
			if (orderHeader.item(i).getNodeType() == Node.ELEMENT_NODE) {
				el = (Element) orderHeader.item(i);
				// strOrderNumber =
				// el.getElementsByTagName("Order_Number").item(0).getTextContent();

				strOrderType = el.getElementsByTagName("Order_Type").item(0).getTextContent();

				strConsignee = el.getElementsByTagName("Consignee").item(0).getTextContent();

			}
		}
        
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderNumber"),true,"*****  In PO Manager Tool Under Select Chevron \"PO Order\" field is not Cleared *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_CustomerReference"),true,"***** In PO Manager Tool Under Select Chevron \"Vendor Ref\" field is not Cleared *****");
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderCallOff"),true,"***** In PO Manager Tool Under Select Chevron \"Order Call Off\" field is not Cleared *****");
	        
	   objSoftAssert.assertAll();           
	}
	    	      	
   	@Test(priority=23)
   	public void setFieldValue()
   	{
   		SoftAssert objSoftAssert = new SoftAssert();
   		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
   	   	String strMessage = "";
    	
  	   if(!isNullOrBlank(strOrderNumber)){
	    	  objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderNumber,strformNamePoManager,"PARAM_OrderNumber"),true,"***** In PO Manager Tool Under Select Chevron \"PO Order\" field value is not set ******");    	   
   	   	}else{
   	   		  strMessage = strMessage + "***** Po Number value is Blank *****";
  		}
   	  		
  	   objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);   
   	   		
   	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true,"Not able to click Edit Chevron");
   	   wait(5000);
	   		
   	   objSoftAssert.assertAll();
   }
   	   	  
   	    
   @Test(priority=25)
   public void test_3_AccessPOManagerEdit()
   {
   	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	        
	   objAdjunoUILibrary = new AdjunoUILibrary(driver);
	   String strTitle = "";
	   if (objAdjunoUILibrary.isPageLoaded(strEditFormNamePOManager)){
			strTitle = driver.getTitle();
	   }
	   
	   	boolean bFlag = true;
	    	
	   	if (!(isNullOrBlank(strTitle))){
	    	if (strTitle.equalsIgnoreCase(strPageTitlePoManager))
	    		bFlag = true;
	   		else
	   			bFlag = false;
	   	}else{
	   		bFlag = false;
    	}	       
	   	objSoftAssert.assertEquals(bFlag, true, "***** Test ID :3 - Page title of PO Manager tool is wrong  *****");
	   	objSoftAssert.assertAll();
    }
   	      
   	    
    @Test(priority=26)
   	public void verifyDataFromSearchInEdit()
   	{
   		SoftAssert objSoftAssert = new SoftAssert();
        String strMessage = "";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);	 
    	
    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"OrderNumber").equals("")){
	   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOrderNumber, objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"OrderNumber")),true," In Po Manager Tool Under Edit chevron  \"Order Number\" Value is not matching from Select chevron:"+"Expected value:"+strOrderNumber + " Actual Value:"+objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"OrderNumber"));
	   	}else{
	   		strMessage = strMessage  +"In Po Manager Tool Under Edit chevron  \"Order Number\" field value is Blank ";
	   	}
    	
    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate").equals("")){
    		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
    		String strOrderDate =objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate");
    		
    		objSoftAssert.assertEquals(strOrderDate.equals(simpleDateFormat.format(new Date())),true,"In Po Manager Tool Under Edit chevron \"Order Date\" Value is not matching from Select chevron:"+"Expected value:"+strOrderDate + " Actual Value:"+simpleDateFormat.format(new Date()));
    	}else{
    		strMessage = strMessage + "In Po Manager Tool Under Edit chevron \"Order Date\"  field value is blank ";
    	}
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** "+strMessage+ " *****");
    	objSoftAssert.assertAll();
   	}
           
       	 
    @Test(priority=32)
    public void newPOCreate(){
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	  
	    
	    Element el;	         
    	for(int i=0; i <= newProduct.getLength() -1; i++)
         {
         	if (newProduct.item(i).getNodeType() == Node.ELEMENT_NODE)
         	{
         		el = (Element) newProduct.item(i);
         		strNewProductCode = el.getElementsByTagName("New_Product_Code").item(0).getTextContent(); 
 				
         		strNewIdentifie = el.getElementsByTagName("New_Identifie").item(0).getTextContent(); 
 				
         		strNewSKUCode = el.getElementsByTagName("New_SKU_Code").item(0).getTextContent(); 
         		
         		strDescription = el.getElementsByTagName("Description").item(0).getTextContent(); 
         		
         		strQuantity = el.getElementsByTagName("Quantity").item(0).getTextContent(); 
         		
         		strColor = el.getElementsByTagName("Colour").item(0).getTextContent(); 
 				
         		strPrimarySize = el.getElementsByTagName("Primary_Size").item(0).getTextContent(); 
 				
         		strSecondarySize = el.getElementsByTagName("Secondary_Size").item(0).getTextContent(); 
         		
         		strCartons = el.getElementsByTagName("Cartons").item(0).getTextContent(); 
         		
         		strCube = el.getElementsByTagName("Cube").item(0).getTextContent(); 
         		
         		strWeight = el.getElementsByTagName("Weight").item(0).getTextContent(); 
         		
         		strWeightType = el.getElementsByTagName("Weight_Type").item(0).getTextContent(); 
         		
         		strPackType = el.getElementsByTagName("Pack_Type").item(0).getTextContent(); 
 				
         		strUnitPrice = el.getElementsByTagName("Unit_Price").item(0).getTextContent(); 
         		
         		strRetailPrice = el.getElementsByTagName("Retail_Price").item(0).getTextContent(); 
         		
         		strProjectedLandedCost = el.getElementsByTagName("Projected_Landed_Cost").item(0).getTextContent();
         		
         		strPriority = el.getElementsByTagName("Priority").item(0).getTextContent();
         	}	
         	
         }
    	wait(5000);
	    objSoftAssert.assertAll();
    }  
    
    
    @Test(priority=33)
   	public void test_4_VerifyElementsInEditChevron()
    {
   	    SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);	    
   	    
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getTxtEdit()), true, "***** Test ID :4 -In Po Manager Tool Under Edit chevron Edit title not found *****");
   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Panel_OrderNo"), true,"***** Test ID :4 -Order header section not found *****");
   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Panel_POH1"), true,"***** Test ID :4 -Order Summary section not found *****");
   		//  objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"TabPanel_Detail"), true,"c");
   		// tab header verify
   				
   		//objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"GRID_ContractSummaryDetails"), true,"Product summary Grid not found");
   		
   		objSoftAssert.assertAll();
   	 }
    
    
    @Test(priority=35)
    public void test_5_VerifyOrderHeaderFields() throws InterruptedException
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	    
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OrderNumber"), true,"***** Test ID :5 -Order header field not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OrderType"), true,"***** Test ID :5 -Order type field not found *****");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager, "Consignee"),true, "***** Test ID :5 -Consignee field not found *****");
		
		objSoftAssert.assertAll();
    }
    
    
    
	@Test(priority=37)
    public void test_6_setConsigneeFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage ="";
    
	    if (!isNullOrBlank(strConsignee)){
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strConsignee, strEditFormNamePOManager,"Consignee"), true,"***** Test ID :6 -Consignee field value is not set *****");
	    }else{
	   		strMessage = strMessage + " Consignee value is empty";
	   	}
    	
	   	objSoftAssert.assertEquals(strMessage.equals(""), true, "***** Test ID :6 -"+strMessage+" *****");
      	objSoftAssert.assertAll();
    }
	
	@Test(priority=38)
    public void test_7_setOrderTypeFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage ="";
    	if (!isNullOrBlank(strOrderType)){
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderType, strEditFormNamePOManager,"OrderType"), true,"***** Test ID :7 -Order Type field value is not set *****");
	    }else{
	   		strMessage = strMessage + " Order Type value is empty";
	   	}

	   
	   	objSoftAssert.assertEquals(strMessage.equals(""), true, "***** Test ID :7 -"+strMessage+ " *****");
      	objSoftAssert.assertAll();
    }
	
	
	
	@Test(priority=40)
	public void test_8_VerifyOrderSummarySectionFields() 
	{
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginCountryDesc"),true,"***** Test ID :8 -OriginCountryDesc field is not found *****");    	   
		    
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"SupplierCode"),true,"***** Test ID :8 -suppliercode field is not found *****");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Supplier"),true,"***** Test ID :8 -supplier field is not found *****");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Mode"),true," ***** Test ID :8 -Mode field is not found *****");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginPortDesc"),true," ***** Test ID :8 -origin Port desc field is not found *****");   			      			    
			
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DestinationPortDesc"), true,"***** Test ID :8 -Destination Port Desc field is not found *****");
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginCountry"), true,"***** Test ID :8 -Origin Country field is not found *****");
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginPort"), true,"***** Test ID :8 -OriginPort field is not found *****");
		    
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Category"),true,"***** Test ID :8 -Category field is not found *****");    	   
		    
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Department"),true,"***** Test ID :8 -Department field is not found *****");    	   	    	
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"ExFactoryDate"),true,"***** Test ID :8 -ExFactoryDate field is not found *****");    	   
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OrderCallOff"),true,"***** Test ID :8 -OrderCallOff field is not found *****");    	   
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"LatestShipDate"),true,"***** Test ID :8 -LatestShipDate field is not found *****");   			      			    
			
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DestinationPort"), true,"***** Test ID :8 -DestinationPort Port Desc field is not found *****");
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DeliveryDate"), true,"***** Test ID :8 -DeliveryDate field is not found ******");
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Channel"), true,"***** Test ID :8 -Channel field is not found *****");	    	
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"ShipDate"), true,"***** Test ID :8 -ShipDate field is not found *****");
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DestinationDesc"), true,"***** Test ID :8 -DestinationDesc field is not found *****");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Destination"), true,"***** Test ID :8 -DC field is not found *****");
	    	
	    objSoftAssert.assertAll();
	}
    
	  
	@Test(priority=45)
	public void checkProductSummaryTabFields(){
		SoftAssert objSoftAssert = new SoftAssert();
          
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    	
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OrderDate"),true,"Order Date field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Buyer"),true,"Buyer field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"BuyingAgent"),true,"Buying Agent field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"BuyingTerms"),true,"Buying Terms field is not found");   			      			    
			
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"PurchaseCurrency"), true,"Purchase Currency field is not found");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"LeadTime"), true,"LeadTime field is not found");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"PromotionCode"), true,"Promotion Code field is not found");
		    
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"RegionCode"),true,"Region Code Date field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Merchandiser"),true,"Merchandiser field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"BuyingCommission"),true,"Buying Commission field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"PaymentTerms"),true,"Payment Terms field is not found");   			      			    
			
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"RetailCurrency"), true,"Retail Currency field is not found");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Promotion"), true,"Promotion field is not found");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"QuantityTolerance"), true,"Quantity Tolerance field is not found");
	    	
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Season"),true,"Season Date field is not found");    	   
	    	    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"LoadType"),true,"Load Type field is not found");    	   
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"UsanceTerms"),true,"Usance Terms field is not found");   			      			    
			
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"ExchangeRate"), true,"Exchange Rate field is not found");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"PromotionStartDate"), true,"Promotion Start Date field is not found");
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Factory"),true,"Factory field is not found");
	    	
	    objSoftAssert.assertAll();
	}
	  
	  
	@Test(priority=50)
	public void test_22_verifyCaptionsInProductSummary(){
	    	
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	   String strMessage="";
	   	
	   ArrayList<String> list = new ArrayList<String>();
	    	
	   list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_ContractSummaryDetails");
	    	
	    strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list,productSummaryDetailsCaptions,29);
	  
	    objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :22 -"+strMessage+ " *****");
						
		objSoftAssert.assertAll();
	 }
	  
	  
	  @Test(priority=55)
		 public void addRow(){
		   SoftAssert objSoftAssert = new SoftAssert();
		   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		    	
		   String strMessage ="";
		    	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButton(strEditFormNamePOManager,"butAddRow"),true,"Add button Not clicked");
		    	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strEditFormNamePOManager,"tmpProduct"),true,"can't clear New Product Code Field");
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strEditFormNamePOManager,"tmpIdentifier"),true,"can't clear Vendor Ref Field");
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strEditFormNamePOManager,"tmpSKU"),true,"can't clear Order Call Off Field");
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strEditFormNamePOManager,"tmpDescription"),true,"can't clear Order Call Off Field");
		   
		   wait(3000);
		   if(!isNullOrBlank(strNewProductCode))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strNewProductCode,strEditFormNamePOManager,"tmpProduct"),true,"");    	   
		   }else{
			   strMessage = strMessage +"new Product code is null";
		   }
		   
		   if(!isNullOrBlank(strNewIdentifie))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strNewIdentifie,strEditFormNamePOManager,"tmpIdentifier"),true,"");    	   
		   }else{
			   strMessage = strMessage +" new identifie is null";
		   }
		   
		   if(!isNullOrBlank(strNewSKUCode))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strNewSKUCode,strEditFormNamePOManager,"tmpSKU"),true,"");    	   
		   }else{
			   strMessage = strMessage +" new Sku code is null";
		   }
		   
		   if(!isNullOrBlank(strDescription))
		   {
			  objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDescription,strEditFormNamePOManager,"tmpDescription"),true,"");    	   
		   }else{
			   strMessage = strMessage +" Description is Empty";
		   }
			    
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButton(strEditFormNamePOManager,"butAddNewProduct"),true,"Add Product Button not clicked");
		   
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getYesButton()),true,"Yes Button not clicked");
		   wait(2000);
		   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
		   objSoftAssert.assertAll();
		 }	 
	  
	
	  
	  @Test(priority=65)
	   public void test_9to21_SetOrderSummuryFields(){
		   SoftAssert objSoftAssert = new SoftAssert();
		   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		   String strMessage ="";
	    	
		   Element el;	         
		   for(int i=0; i <= orderSummary.getLength() -1; i++)
	       {
	         if (orderSummary.item(i).getNodeType() == Node.ELEMENT_NODE)
	         {
	         	el = (Element) orderSummary.item(i);
	         	strOriginDesc = el.getElementsByTagName("Origin_Desc").item(0).getTextContent(); 
	 				
	         	strSupplier = el.getElementsByTagName("Supplier").item(0).getTextContent(); 
	 				
	         	strMode = el.getElementsByTagName("Mode").item(0).getTextContent(); 
	         		
	         	strDestinationPortDesc = el.getElementsByTagName("Destination_Port_Desc").item(0).getTextContent(); 
	 				
	         	strCategory = el.getElementsByTagName("Category").item(0).getTextContent(); 
	 				
	         	strDepartment = el.getElementsByTagName("Department").item(0).getTextContent(); 
	         		
	         	strDestinationDesc = el.getElementsByTagName("Destination_Desc").item(0).getTextContent(); 
	         			         		
	         		
	         }	
	         	
	       }
	    	
	    	
		   if(!isNullOrBlank(strOriginDesc))
		   {
			    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOriginDesc,strEditFormNamePOManager,"OriginCountryDesc"),true,"Origin Country Desc field is not set");    	   
		   }else{
			   strMessage = strMessage + " origin Desc value is empty";
		   }
			    
		   if(!isNullOrBlank(strSupplier))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strSupplier,strEditFormNamePOManager,"Supplier"),true,"supplier field is not set");    	   
		   }else{
			   strMessage = strMessage + " supplier value is empty";
		   }
		   
		   if(!isNullOrBlank(strMode))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strMode,strEditFormNamePOManager,"Mode"),true,"Mode field is not set");    	   
		   }else{
			   strMessage = strMessage + " mode value is empty";
		   }
			    
		   if(!isNullOrBlank(strDestinationPortDesc))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDestinationPortDesc,strEditFormNamePOManager,"DestinationPortDesc"),true,"Destination Port Desc field is not set");    	   
		   }else{
			   strMessage = strMessage + " DestinationPort Desc value is empty";
		   }
			    
		   if(!isNullOrBlank(strCategory))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCategory,strEditFormNamePOManager,"Category"),true,"Category field is not set");    	   
		   }else{
			   strMessage = strMessage + " Category value is empty";
		   }
		   
			    
		   if(!isNullOrBlank(strDepartment))
		   {
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDepartment,strEditFormNamePOManager,"Department"),true,"Department field is not set");    	   
		   }else{
			   strMessage = strMessage + " Department value is empty";
		   }
		   
		   strShipDate = objAdjunoLIMAPoManagerPOM.getDate(15,"dd MMM yyyy");
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strShipDate,strEditFormNamePOManager,"ShipDate"),true,"Ship Date field is not set");    	   
		  
		   strDeliveryDate = objAdjunoLIMAPoManagerPOM.getDate(30,"dd MMM yyyy");
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDeliveryDate,strEditFormNamePOManager,"DeliveryDate"),true,"Delivery Date field is not set");    	   
		  
		  if(!isNullOrBlank(strDestinationDesc)){
			  objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDestinationDesc,strEditFormNamePOManager,"DestinationDesc"),true,"Destination Desc field is not set");    	   
		  }else{
			  strMessage = strMessage + " Destination Desc value is empty";
		  }
	  	   
			    
		  objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);    
		  objSoftAssert.assertAll();
	  }
	    
	  
	  
	 @Test(priority=70)
	 public void test_setValueForProductSummaryTabFields(){
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    String strMessage="";
	    	
	    Element el;	         
	    for(int i=0; i <= productSummary.getLength() -1; i++)
	    {
	        if (productSummary.item(i).getNodeType() == Node.ELEMENT_NODE)
	        {
	         	el = (Element) productSummary.item(i);
	         	strBuyer = el.getElementsByTagName("Buyer").item(0).getTextContent(); 
	 				
	         	strBuyingAgent = el.getElementsByTagName("Buying_Agent").item(0).getTextContent(); 
	 				
	        	strBuyingTerms = el.getElementsByTagName("Buying_Terms").item(0).getTextContent(); 
	         		
	         	strPurchaseCurrency = el.getElementsByTagName("Purchase_Currency").item(0).getTextContent(); 
	 				
	         	strRetailCurrency = el.getElementsByTagName("Retail_Currency").item(0).getTextContent(); 
	 				
	         	strMerchandiser = el.getElementsByTagName("Merchandiser").item(0).getTextContent(); 
	         		
	         	strQuantityTolerance = el.getElementsByTagName("Quantity_Tolerance").item(0).getTextContent(); 
	         		
	         //	strSeason = el.getElementsByTagName("Season").item(0).getTextContent(); 
	         		
	         	strLoadType = el.getElementsByTagName("Load_Type").item(0).getTextContent(); 
	         		
	         	strRefrigeration= el.getElementsByTagName("Refrigeration").item(0).getTextContent();
	         		
	         }	
	         	
	     }
		 
		if(!isNullOrBlank(strLoadType))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strLoadType,strEditFormNamePOManager,"LoadType"),true,"Load type field is not set");    	   
	    }else{
	    	strMessage = strMessage + " LoadType value is empty";
	    }
		 
		 
	    if(!isNullOrBlank(strBuyer))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strBuyer,strEditFormNamePOManager,"Buyer"),true,"Buyer field is not set");    	   
	    }else{
			strMessage = strMessage + " Buyer value is empty";
	    }
			    
			    
	    if(!isNullOrBlank(strBuyingAgent))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strBuyingAgent,strEditFormNamePOManager,"BuyingAgent"),true,"Buying Agent field is not set");    	   
	    }else{
	    	strMessage = strMessage + " buying Agent value is empty";
	    }
			    
	    if(!isNullOrBlank(strBuyingTerms))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strBuyingTerms,strEditFormNamePOManager,"BuyingTerms"),true,"Buying Terms field is not set");    	   
	    }else{
	    	strMessage = strMessage + " Buying Terms value is empty";
	    }
			    
	    if(!isNullOrBlank(strPurchaseCurrency))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strPurchaseCurrency,strEditFormNamePOManager,"PurchaseCurrency"),true,"Purchase Currency field is not set");    	   
	    }else{
	    	strMessage = strMessage + " Purchase Currency value is empty";
	    }
			    
	    if(!isNullOrBlank(strRetailCurrency))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strRetailCurrency,strEditFormNamePOManager,"RetailCurrency"),true,"Retail Currency field is not set");    	   
	    }else{
	    	strMessage = strMessage + " Retail Currency value is empty";
	    }
	    
	    if(!isNullOrBlank(strMerchandiser))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strMerchandiser,strEditFormNamePOManager,"Merchandiser"),true,"Merchandiser field is not set");    	   
	    }else{
	    	strMessage = strMessage + " Mechandiser value is empty";
	    }
	    
	    if(!isNullOrBlank(strQuantityTolerance))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strQuantityTolerance,strEditFormNamePOManager,"QuantityTolerance"),true,"QuantityTolerance field is not set");    	   
	    }else{
	    	strMessage = strMessage + " quantity Tolerence value is empty";
	    }
	    
	   /* if(!isNullOrBlank(strSeason))
	    {
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strSeason,strEditFormNamePOManager,"Season"),true,"Season field is not set");    	   
	    }else{
	    	strMessage = strMessage + " season value is empty";
	    }*/
	    
	   
	    
	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	   objSoftAssert.assertAll();
	 }
	  
  
		    	
	 @Test(priority=75)
	 public void test_23_SetProductSummaryGridValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		 String strMessage = "";
		 wait(5000);
		 
		 
		if (!isNullOrBlank(strWeight)) {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Weight",strWeight),true,"***** Test Id: 23 -Weight field value is not set *****");    	   
		}else{
			strMessage = strMessage +" Weight value is empty";
		}
		   
		if (!isNullOrBlank(strCube)) {
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Cube",strCube),true,"***** Test Id: 23 -Cube field value is not set *****");    	   
		}else{
			strMessage = strMessage +" cube value is empty";
		}
		   if(!isNullOrBlank(strWeightType))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"WeightType",strWeightType),true,"***** Test Id: 23 -Weight Type field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Weight Type value is empty";
		   }
		   
		   if(!isNullOrBlank(strPackType))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"PackType",strPackType),true,"***** Test Id: 23 - Pack Type field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Pack Type value is empty";
		   }
		   		     
		   if(!isNullOrBlank(strUnitPrice))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"UnitPrice",strUnitPrice),true,"***** Test Id: 23 -Unit Price field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Unit Price value is empty";
		   }
		   
		   if(!isNullOrBlank(strRetailPrice))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"RetailPrice",strRetailPrice),true,"***** Test Id: 23 -Retail Price field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Retail Price value is empty";
		   }
		   
		   if(!isNullOrBlank(strPriority))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Priority",strPriority),true," ***** Test Id: 23 -Priority field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Priority value is empty";
		   }
		   
		   if(!isNullOrBlank(strProjectedLandedCost))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LandedCost",strProjectedLandedCost),true,"***** Test Id: 23 -Project landed cost field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" project Landed cost value is empty";
		   }
		  
		 			    
		   if(!isNullOrBlank(strCartons)){	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Cartons",strCartons),true,"***** Test Id: 23 -Cartons field value is not set *****");    	   
			}else{
				strMessage = strMessage +" Cartons value is empty";
			}
			   
		   if(!isNullOrBlank(strColor))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Colour",strColor),true,"***** Test Id: 23 -Color field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Colour value is empty";
		   }
			   
		   if(!isNullOrBlank(strPrimarySize)){	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"PrimarySize",strPrimarySize),true,"***** Test Id: 23 -Primary Size field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Primary size value is empty";
		   }
			   
		   if(!isNullOrBlank(strSecondarySize))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"SecondarySize",strSecondarySize),true,"***** Test Id: 23 -Secondary Size field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Secondary size value is empty";
		   }
			   
		   if(!isNullOrBlank(strQuantity))
		   {	
			   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Quantity",strQuantity),true,"***** Test Id: 23 -Quentity field value is not set *****");    	   
		   }else{
			   strMessage = strMessage +" Quntity value is empty";
		   }
			
		   		   			   
		   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id: 23 -"+strMessage+ " *****");
		   objSoftAssert.assertAll();
	 }
	 
	  
	 @Test(priority=100)
	 public void test_24_verifyCaptionsInProductCard(){
	    	
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    String strMessage="";
	    	
	    ArrayList<String> list = new ArrayList<String>();
	    	
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickOnNextTab(strEditFormNamePOManager,"TabPanel_Detail"),true,"Product card tab not clicked");
	    	
	    list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_ProductCard");
	    	
	    strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list,productCardCaptions,21);
	    				
	    objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id:24 -"+strMessage+" *****");
						
		objSoftAssert.assertAll();
	 }
	    
	 @Test(priority=105)
	 public void test_25to41_SetValueInProductCardTab(){
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    String strMessage = "";
	    Element el;	         
		    for(int i=0; i <= productCardValue.getLength() -1; i++)
		    {
		         if (productCardValue.item(i).getNodeType() == Node.ELEMENT_NODE)
		         {
		         	el = (Element) productCardValue.item(i);
		         		
		         	strVendorRef = el.getElementsByTagName("Vendor_Ref").item(0).getTextContent(); 
		         		
		         	strCommodity = el.getElementsByTagName("Commodity_Code").item(0).getTextContent(); 
		 				
		         	strDutyRate = el.getElementsByTagName("Duty_Rate").item(0).getTextContent(); 
		         		
		         	strQARequest = el.getElementsByTagName("QA_Request").item(0).getTextContent(); 
		         		
		         	strPiecesPerInner = el.getElementsByTagName("Pieces_Per_Inner").item(0).getTextContent();
		         	
		         	strInnersPerOuter = el.getElementsByTagName("Inners_Per_Outer").item(0).getTextContent();
		         		 				
		         	strCartonLength = el.getElementsByTagName("Carton_Length").item(0).getTextContent(); 
		 				
		         	strCartonWidth = el.getElementsByTagName("Carton_Width").item(0).getTextContent(); 
		         		
		         	strCartonDepth = el.getElementsByTagName("Carton_Depth").item(0).getTextContent(); 
		         		
		         	strOuterWeight = el.getElementsByTagName("Outer_Weight").item(0).getTextContent(); 
		         		
		         	strOuterEAN = el.getElementsByTagName("Outer_EAN").item(0).getTextContent(); 
		         		
		         	strInnerEAN = el.getElementsByTagName("Inner_EAN").item(0).getTextContent(); 
		         		
		         	strTI = el.getElementsByTagName("TI").item(0).getTextContent(); 
		 				
		         	strHI = el.getElementsByTagName("HI").item(0).getTextContent(); 
		         		
		         	strNetWeight = el.getElementsByTagName("Net_Weight").item(0).getTextContent(); 
		         		
		         	}	
		    }
		    	 
		    if(!isNullOrBlank(strCommodity))
		    {	
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CommodityCode",strCommodity),true,"Commodity is not set");    	   
		    }else{
		    	strMessage = strMessage +" Commodity value is empty";
		    }
		    
		   
		    	
		    if(!isNullOrBlank(strDutyRate))
			{	
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"DutyRate",strDutyRate),true,"Duty Rate is not set");    	   
			}else{
				strMessage = strMessage +" DutyRate value is empty";
			}
				    
		    if(!isNullOrBlank(strQARequest))
		    {	
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"QARequest",strQARequest),true," QARequest is not set");    	   
		    }else{
		    	strMessage = strMessage +" QARequest value is empty";
		    }	
		    
		    if(!isNullOrBlank(strPiecesPerInner))
		    {	
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"PiecesPerInner",strPiecesPerInner),true,"PiecesPerInner is not set");    	   
		    }else{
		    	strMessage = strMessage +" PiecesPerInner value is empty";
		    }
		    	
			if(!isNullOrBlank(strNetWeight))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"NetWeight",strNetWeight),true,"NetWeight is not set");    	   
			}else{
				strMessage = strMessage +" NetWeight value is empty";
			}
				 
			if(!isNullOrBlank(strInnersPerOuter))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"InnersPerOuter",strInnersPerOuter),true,"InnersPerOuter is not set");    	   
			}else{
				strMessage = strMessage +" Inners Per Outer value is empty";
			}
				    
			if(!isNullOrBlank(strCartonLength))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CartonLength",strCartonLength),true,"CartonLength is not set");    	   
			}else{
				strMessage = strMessage +" CartonLength value is empty";
			}
				 
			if(!isNullOrBlank(strCartonWidth))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CartonWidth",strCartonWidth),true,"Carton Width is not set");    	   
			}else{
				strMessage = strMessage +" Carton Width value is empty";
			}
			
			if(	!isNullOrBlank(strCartonDepth))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CartonDepth",strCartonDepth),true,"Carton Depth is not set");    	   
			}else{
				strMessage = strMessage +" Carton Depth value is empty";
			}
			
			if(!isNullOrBlank(strOuterWeight))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"OuterWeight",strOuterWeight),true,"OuterWeight is not set");    	   
			}else{
				strMessage = strMessage +" OuterWeight value is empty";
			}
				
			if(!isNullOrBlank(strOuterEAN))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"OuterCartonEAN",strOuterEAN),true,"OuterEAN is not set");    	   
			}else{
				strMessage = strMessage +" OuterEAN value is empty";
			}
			
			if(!isNullOrBlank(strInnerEAN))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"InnerCartonEAN",strInnerEAN),true,"Inner EAN is not set");    	   
			}else{
				     strMessage = strMessage +" Inner EAN value is empty";
			}
			
			if(!isNullOrBlank(strTI))
			{	
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"TI",strTI),true,"TI is not set");    	   
			}else{
				strMessage = strMessage +" TI value is empty";
			}
			
			if(!isNullOrBlank(strHI))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"HI",strHI),true,"HI is not set");    	   
			}else{
				strMessage = strMessage +" HI value is empty";
			}	
				 
			if(!isNullOrBlank(strVendorRef))
			{	
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CustomerReference",strVendorRef),true,"Vendor Ref is not set");    	   
			}else{
				strMessage = strMessage +"VenderRef value is empty";
			}
				
			objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id: 25 to 41 -" +strMessage + " *****");
			objSoftAssert.assertAll();
	 }
	    
	    
	 @Test(priority=110)
	 public void test_42_VerifyCaptionsInRatioPackDetailCard(){
	    	
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    String strMessage="";
	    	
	 	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickOnNextTab(strEditFormNamePOManager,"TabPanel_Detail"),true,"Ratio pack tab not clicked");
		    	
		ArrayList<String> list = new ArrayList<String>();
		    	
		list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_RatioPackHeader");
		    		    	
		strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list,ratioPackCaptions,4);
		
		ArrayList<String> list2 = new ArrayList<String>();
    	
    	list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_RatioPackDetails");		    	    	
		
    	strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list2,ratioPackDetailsCaptions,11);
		    	
				
	   	objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id: 42 -"+strMessage+" *****");
		
	    objSoftAssert.assertAll();
	    		
    }
	
	    
	    
	@Test(priority=120)
	public void test_43_VerifyCaptionsBWSTab(){
	    	
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		String strMessage="";
	    	
    	ArrayList<String> list = new ArrayList<String>();
		    	
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickOnNextTab(strEditFormNamePOManager,"TabPanel_Detail"),true,"BWS tab not clicked");
		    	
    	list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_BWS");
		    	    	
    	strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list,bwsCaptions,9);
		    	
	  	objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id: 42 -"+strMessage+" *****");
	  	objSoftAssert.assertAll();
  }
	    
    @Test(priority=125)
    public void test_44_to_49_SetValueInBWSTab(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage = "";
	   	Element el;	         
	    for(int i=0; i <= bwsValue.getLength() -1; i++)
	    {
	    	if (bwsValue.item(i).getNodeType() == Node.ELEMENT_NODE)
		    {
	         	el = (Element) bwsValue.item(i);
		         		
	         	strTaxCode = el.getElementsByTagName("Tax_Code").item(0).getTextContent(); 
		         		
	         	strABV = el.getElementsByTagName("ABV").item(0).getTextContent(); 
		 				
		       	strCL = el.getElementsByTagName("CL").item(0).getTextContent(); 
		 				
		       	strWineType = el.getElementsByTagName("Wine_Type").item(0).getTextContent(); 
		         		
		       	strLineDuty = el.getElementsByTagName("Line_Duty").item(0).getTextContent(); 
		         		
		       	strVintage = el.getElementsByTagName("Vintage").item(0).getTextContent();		
		     }	
		 }
		    
	    if(!isNullOrBlank(strTaxCode))
		{	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_BWS",0,"TaxCode",strTaxCode),true,"Tax Code is not set");    	   				}else{
			strMessage = strMessage +" Tax Code value is empty";
		}
			
	    if(!isNullOrBlank(strABV))
	    {	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_BWS",0,"ABV",strABV),true,"ABV is not set");    	   
	    }else{
	    	strMessage = strMessage +" Abv value is empty";
	    }
			   
	    if(!isNullOrBlank(strCL))
	    {	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_BWS",0,"CL",strCL),true,"CL is not set");    	   
	    }else{
	    	strMessage = strMessage +" CL value is empty";
	    }	
		    
	    if(!isNullOrBlank(strWineType))
	    {	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_BWS",0,"WineType",strWineType),true,"Wine Type is not set");    	   
	    }else{
	    	strMessage = strMessage +" Wine Type value is empty";
	    }
			    
		if(!isNullOrBlank(strLineDuty))
	    {	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_BWS",0,"LineDuty",strLineDuty),true," LineDuty is not set");    	   
	    }else{
	    	strMessage = strMessage +" Line Duty value is empty";
	    }	
			    
	    if(!isNullOrBlank(strVintage))
	    {	
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_BWS",0,"Vintage",strVintage),true,"Vintage is not set");    	   
	    }else{
		    	strMessage = strMessage +" Vintage value is empty";
		}
		objSoftAssert.assertEquals(strMessage.equals(""), true, "***** Test Id: 44 to 49 -"+strMessage+ " *****");
		objSoftAssert.assertAll();
	    
    }	
	    	
	
    
   @Test(priority=130)
   public void test_50_clickComplite(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvComplete2()),true,"***** Test Id: 50 - Not able to click complete Chevron *****");
	   wait(10000);
	   
	   
	   objSoftAssert.assertAll();
 }
	    
   @Test(priority=135)
   public void test_51_GetTrackRefNo(){	    
	   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	   objAdjunoLIMAPoManagerPOM.getBtnViewDetail().click();
	   wait(3000);
	   String poDetails = objAdjunoLIMAPoManagerPOM.getTxtViewDetail().getText();
	   String[] vals = poDetails.split("Track");
	   String valss[] = vals[1].split("'",14);
	   strTrackN0 = valss[1];
	   System.out.println(""+strTrackN0);
	   
	   
   }
	    
	    
   @Test(priority=140)
   public void test_52_ClickOnTrackLink(){	    
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = "";
	   objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    	
	   objAdjunoLIMAPoManagerPOM.callMouseHover(strformNamePoManager,objAdjunoLIMAPoManagerPOM.getLnkTrack(),objAdjunoLIMAPoManagerPOM.getLnkEdit());
	   wait(2000);
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getFldRefNo()),true,"Reference field is not exist");
		    	
	   if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForWebElement(objAdjunoLIMAPoManagerPOM.getFldRefNo(),strTrackN0),true,"Reference field value is not set");   
			    	  
			 objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getBtnApply());
	   }else{
        	strMessage =  "Track number value is empty";		        	
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id :52-"+strMessage+ " *****");
	   objSoftAssert.assertAll();
	    	
	}	 
	    
	    
	@Test(priority=145)
	public void checkForPurchaseOrderDisplay() throws ParseException{	    
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getTxtPurchaseOrder()), true,"Title Purchase Order is not found");
		    	    	
    	long min = objAdjunoLIMAPoManagerPOM.getTrackValue(strUserName);
    	if(min<=355){
		    		
    	}else{
    		strMessage = "Not a today's date";
    	}
		    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
		    	
    	objSoftAssert.assertAll();
	}
	    
	    
	@Test(priority=150)
	public void checkDataInPurchaseOrder() {	    
	    SoftAssert objSoftAssert = new SoftAssert();
	    	
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getLnkPurchaseOrder()),true,"not able to click on Purchase Order");
	   	objSoftAssert.assertAll();
	    
	}
	    
	    
	@Test(priority=155)
	public void viewPoInEditMode(){	
	    SoftAssert objSoftAssert = new SoftAssert();
	    	
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getBtnApply()), true,"Apply button is not clicked");
		wait(3000);
		objSoftAssert.assertAll();
	}
	    
	    
	@Test(priority=160)
	public void checkPageLoaded(){
		SoftAssert objSoftAssert = new SoftAssert();
	    boolean bFlag;
	    objAdjunoUILibrary = new AdjunoUILibrary(driver);
		if (objAdjunoUILibrary.isPageLoaded(strViewFormNamePO))
		{
			bFlag = true; 
		}
		else{
			bFlag = false;		 
		}
				
		objSoftAssert.assertEquals(bFlag, true,"Po Page is not Loaded");	 
		objSoftAssert.assertAll();
	}
	    
	/*@Test(priority=170)
	public void test_53_CheckValuesInOrderHead(){	
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"OrderNumber").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOrderNumber, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"OrderNumber")),true,"order number Does not match");
		}else{
			strMessage = strMessage +"OrderNumber field value is empty ";	    		
		}
		    	
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OrderType").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOrderType, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OrderType")),true,"order Type does not match");		    	}else{
		   	strMessage = strMessage  + "OrderType field value is empty ";
	 	}
		
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Consignee").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strConsignee, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Consignee")),true,"Consignee does not match");
	  	}else{
	  		strMessage = strMessage +"Consignee field value is empty  ";
		}
			    	    	
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id :53 -"+strMessage+ " *****");
	    objSoftAssert.assertAll();
    }
	    
	    
	@Test(priority=175)
	public void test_verifyValuesInOrderSummary(){	
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Supplier").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strSupplier, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Supplier")),true,"Supplier Does not match");
		}else{
	 		strMessage = strMessage +" Supplier field value is empty ";
    	}
		    	
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Mode").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strMode, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Mode")),true,"Mode does not match");
		}else{
			strMessage = strMessage + " Mode field value is empty  ";
		}
		    	
		    	
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OriginCountry").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOriginDesc, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OriginCountry")),true,"Origin Desc Does not match");
		}else{
			strMessage = strMessage +" OriginDesc field value  is empty  ";
		}
		
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"DestinationPort").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDestinationPortDesc, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"DestinationPort")),true,"Destination Port Desc does not match");
		}else{
			strMessage = strMessage + " DestinationPortDesc  field value is empty ";
		}
		    	
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Category").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCategory, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Category")),true,"Category Does not match");    	
		}else{
			strMessage = strMessage + "Category field value  is empty ";
		}
		    	
		    	
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue2(strViewFormNamePO,"ShipDate").equals("")){
			System.out.println("strShipDate:"+strShipDate+" ----"+"field data"+objAdjunoLIMAPoManagerPOM.getFieldValue2(strViewFormNamePO,"ShipDate"));
			
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strShipDate, objAdjunoLIMAPoManagerPOM.getFieldValue2(strViewFormNamePO,"ShipDate")),true,"ShipDate does not match");
		}else{
			strMessage = strMessage + "ShipDate  field value  is empty ";
		}
		
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Destination").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDestinationDesc, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Destination")),true,"DestinationDesc Does not match");			
		}else{
			strMessage = strMessage + " DestinationDesc  field value  is empty ";
		}
		    	
		    	
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingAgent").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strBuyingAgent, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingAgent")),true,"BuyingAgent does not match");
		}else{
			strMessage = strMessage + "BuyingAgent field value  is empty ";
		}
		    	
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingTerms").equals("")){
			objSoftAssert.assertEquals(compareTwoStringValuesToSame(strBuyingTerms, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingTerms")),true,"strBuyingTerms does not match");
			
		}else{
			strMessage = strMessage + "BuyingTerms field value  is empty ";
		}
		    	
		objSoftAssert.assertEquals(strMessage.equals(""), true,"Error Message on checkValuesInOrderSummary:"+strMessage);
	    	
	    objSoftAssert.assertAll();    		    
	 }
	    
	    
	@Test(priority=180)
	public void test_verifyValuesInContractSummary(){	
	    SoftAssert objSoftAssert = new SoftAssert();
	    String strMessage ="";
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Buyer").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strBuyer, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Buyer")),true,"Buyer Does not match");
	    }else{
	    	strMessage = strMessage + strBuyer +" value is null on this field ";
	    }
	    
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Department").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDepartment, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Department")),true,"Department Does not match");
	    }else{
	    	strMessage = strMessage + strDepartment +" value is null on this field ";
	    }
	    
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Season").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strSeason, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Season")),true,"Season does not match");
	    }else{
	    	strMessage = strMessage + "Season value is empty on this field ";
	    }
	    
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"LoadType").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strLoadType, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"LoadType")),true,"LoadType Does not match");
	    	
		}else{
			 strMessage = strMessage + "LoadType value is Empty on this field ";
		}
			    		    	
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"PurchaseCurrency").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strPurchaseCurrency, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"PurchaseCurrency")),true,"PurchaseCurrency does not match");
	    }else{
	    	strMessage = strMessage + strPurchaseCurrency +" value is null on this field ";
	    }
		    	
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"RetailCurrency").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strRetailCurrency, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"RetailCurrency")),true,"RetailCurrency does not match");
	    }else{
	    	strMessage = strMessage + strRetailCurrency +" value is null on this field ";
	    }
		    	
	    if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Merchandiser").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strMerchandiser, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Merchandiser")),true,"Merchandiser Does not match");
	    	
	    }else{
	    	strMessage = strMessage + strMerchandiser +" value is null on this field ";
	    }
		    	
	    if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"QuantityTolerance").equals("")){
	    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strQuantityTolerance, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"QuantityTolerance")),true,"QuantityTolerance Does not match");
	    }else{
	    	strMessage = strMessage + strQuantityTolerance +" value is null on this field ";
	    }
		    			    	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Error Message on checkValues In ProductSummary:"+strMessage);
	    
	    objSoftAssert.assertAll();
	 }
	    
	
	    @Test(priority=185)
	    public void test_verifyValuesInContractSummaryDetails(){	
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	String strMessage = "";
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Product").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNewProductCode, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Product")),true,"Product Does not match");
		    }else{
		    	strMessage = strMessage + strNewProductCode +" value is null on this field ";
		    }
		    	
	    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Identifier").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNewIdentifie, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Identifier")),true,"Identifier does not match");
		    }else{
		    	strMessage = strMessage + strNewIdentifie +" value is null on this field ";
		    }
		    	
	    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"SKU").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNewSKUCode, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"SKU")),true,"SKU Does not match");
		    		
	    	}else{
	    		strMessage = strMessage + strNewSKUCode +" value is null on this field ";
		    }
			    
		    	
	    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Description").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDescription, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Description")),true,"Description Desc Does not match");
		    }else{
		    	strMessage = strMessage + strDescription +" value is null on this field ";
		    }
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Quantity").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strQuantity, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Quantity")),true," Quantity does not match");
		   	}else{
		   		strMessage = strMessage + strQuantity +" value is null on this field ";
	    	}
		    		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Colour").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strColor, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Colour")),true," Colour does not match");
		    }else{
		   		strMessage = strMessage + strColor +" value is null on this field ";
		   	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"PrimarySize").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strPrimarySize, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"PrimarySize")),true,"PrimarySize does not match");
	    	}else{
	    		strMessage = strMessage + strPrimarySize +" value is null on this field ";
	    	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"SecondarySize").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strSecondarySize, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"SecondarySize")),true,"SecondarySize does not match");
		   	}else{
		   		strMessage = strMessage + strSecondarySize +" value is null on this field ";
	    	}
		    	
	    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Cartons").equals("")){
		    	objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCartons, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Cartons")),true,"Cartons does not match");
		   	}else{
		   		strMessage = strMessage + strCartons +" value is null on this field ";
		   	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Cube").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCube, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Cube")),true,"Cube does not match");
	    	}else{
	    		strMessage = strMessage + strCube +" value is null on this field ";
		    }
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Weight").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strWeight, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"Weight")),true,"Weight does not match");
		   	}else{
		   		strMessage = strMessage +strWeight  +" value is null on this field ";
	    	}
	    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"WeightType").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strWeightType, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"WeightType")),true,"WeightType does not match");
		   	}else{
		   		strMessage = strMessage + strWeightType +" value is null on this field ";
		   	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"PackType").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strPackType, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"PackType")),true,"PackType does not match");
		   	}else{
		   		strMessage = strMessage + strPackType +" value is null on this field ";
	    	}
		    	
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"UnitPrice").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strUnitPrice, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"UnitPrice")),true,"UnitPrice does not match");
	    	}else{
	    		strMessage = strMessage + strUnitPrice +" value is null on this field ";
		   	}
		    	
		    if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"RetailPrice").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strRetailPrice, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"RetailPrice")),true,"RetailPrice does not match");
		   	}else{
		   		strMessage = strMessage + strRetailPrice +" value is null on this field ";
		   	}
	    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"LandedCost").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strProjectedLandedCost, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"LandedCost")),true,"LandedCost does not match");
	    	}else{
	    		strMessage = strMessage + strProjectedLandedCost +" value is null on this field ";
	    	}
		    	
	    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductSeason").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strSeason, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductSeason")),true,"Product Season does not match");
	    	}else{
	    		strMessage = strMessage + strSeason +" value is null on this field ";
	    	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductPriority").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strPriority, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductPriority")),true,"Priority does not match");
		   	}else{
	    		strMessage = strMessage + strPriority +" value is null on this field ";
	    	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductDepartment").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDepartment, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductDepartment")),true,"Department does not match");
		   	}else{
		   		strMessage = strMessage + strDepartment +" value is null on this field ";
		   	}
		    	
		   	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductLoadType").equals("")){
		   		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strLoadType, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ContractSummaryDetails",0,"ProductLoadType")),true,"ProductLoadType does not match");
		   	}else{
		   		strMessage = strMessage + strLoadType +" value is null on this field ";
		   	}
		   	   
		   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
	    	objSoftAssert.assertAll();
	    }
	    
	    
	    
	    @Test(priority=185)
	    public void test_verifyValuesInProductCardTab(){	
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	String strMessage = "";
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    	
	    	 	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductProduct").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNewProductCode, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductProduct")),true,"Product Does not match");
		    	}else{
		    		strMessage = strMessage + strNewProductCode +" not found ";
		    	}
		    	//GRID_ProductCard
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductIdentifier").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNewIdentifie, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductIdentifier")),true,"Identifier does not match");
		    	}else{
		    		strMessage = strMessage + strNewIdentifie +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductSKU").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNewSKUCode, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductSKU")),true,"SKU Does not match");
		    	
		    	}else{
		    		strMessage = strMessage + strNewSKUCode +" not found ";
		    	}
			    
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductDescription").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDescription, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductDescription")),true," Product Description Does not match");
		    	}else{
		    		strMessage = strMessage + strDescription +" not found ";
		    	}
		    	
		    	    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CustomerReference").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strVendorRef, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CustomerReference")),true,"vendor Reference Desc Does not match");
		    	}else{
		    		strMessage = strMessage + strVendorRef +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CommodityCode").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCommodity, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CommodityCode")),true," commodity code does not match");
		    	}else{
		    		strMessage = strMessage + strCommodity +" not found ";
		    	}
		    		    		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"DutyRate").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDutyRate, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"DutyRate")),true," DutyRate does not match");
		    	}else{
		    		strMessage = strMessage + strDutyRate +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"QARequest").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strQARequest, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"QARequest")),true," QARequest does not match");
		    	}else{
		    		strMessage = strMessage + strQARequest +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"PiecesPerInner").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strPiecesPerInner, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"PiecesPerInner")),true," Inner EAN does not match");
		    	}else{
		    		strMessage = strMessage + strPiecesPerInner +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"InnersPerOuter").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strInnersPerOuter, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"InnersPerOuter")),true," InnersPerOuter does not match");
		    	}else{
		    		strMessage = strMessage + strInnersPerOuter +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CartonLength").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCartonLength, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CartonLength")),true," Carton Length does not match");
		    	}else{
		    		strMessage = strMessage + strCartonLength +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CartonWidth").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCartonWidth, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CartonWidth")),true," CartonWidth does not match");
		    	}else{
		    		strMessage = strMessage +strCartonWidth  +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CartonDepth").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCartonDepth, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"CartonDepth")),true," CartonDepth does not match");
		    	}else{
		    		strMessage = strMessage + strCartonDepth +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"OuterWeight").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOuterWeight, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"OuterWeight")),true," OuterWeight does not match");
		    	}else{
		    		strMessage = strMessage + strOuterWeight +" not found ";
		    	}
		    	
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"OuterCartonEAN").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOuterEAN, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"OuterCartonEAN")),true," OuterCartonEAN does not match");
		    	}else{
		    		strMessage = strMessage + strOuterEAN +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"InnerCartonEAN").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strInnerEAN, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"InnerCartonEAN")),true," InnerCartonEAN does not match");
		    	}else{
		    		strMessage = strMessage + strInnerEAN +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"TI").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strTI, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"TI")),true," TI does not match");
		    	}else{
		    		strMessage = strMessage + strTI +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"HI").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strHI, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"HI")),true," HI does not match");
		    	}else{
		    		strMessage = strMessage + strHI +" not found ";
		    	}
		    	
		    	if(!objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"NetWeight").equals("")){
		    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strNetWeight, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"NetWeight")),true," NetWeight does not match");
		    	}else{
		    		strMessage = strMessage + strNetWeight +" not found ";
		    	}
		    	
		    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Error Message on checkValuesInProductCard:"+strMessage);
		    	objSoftAssert.assertAll();
	    	
	    }
	    
	*/    
	  /*  @Test(priority=195)
	    public void checkDataInAllocation() {	    
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		    if(strSiteType.equalsIgnoreCase("lima")){	
		    	driver.navigate().back();
		    	wait(3000);
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getLnkAllocation()),true,"not able to click on Purchase Order");
		    	
		    }else{
	    		throw new SkipException("skipping this test in WFL");
	    	}	
		    objSoftAssert.assertAll();
	    }*/
	    
  
	    
	    @Test(priority=200)
	    public void test_55_checkPoInConfirmation(){	    
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	String strMessage ="";
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		   	objAdjunoLIMAPoManagerPOM.callMouseHover(strformNamePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkConfirmation());
		    wait(2000);
		    	     
		    	
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strPoConfirmationFormName,"PARAM_ConfirmationStatus"),true,"Confirmation Status is not found");
		    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strPoConfirmationFormName,"PARAM_PONumber"),true,"PONumber field is not found");
		    	
		    	if (!isNullOrBlank(strConfirmationStatusAny)) {
					objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strConfirmationStatusAny, strPoConfirmationFormName,"PARAM_ConfirmationStatus"), true,"not able to  set value in PO number field");
				}else{
					strMessage = strMessage + strConfirmationStatusAny +" not found ";
				}
	
				if (!isNullOrBlank(strOrderNumber)) {
					objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderNumber, strPoConfirmationFormName,"PARAM_PONumber"), true,"not able to  set value in status field");
				}else{
					strMessage = strMessage + strOrderNumber +" not found ";
				}
				
				
				objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
				objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true," Search chevron is not clicked");
				wait(5000);
				objSoftAssert.assertEquals(strMessage.equals(""),true,"***** Test Id: 55 -"+strMessage+" *****");
		  
				objSoftAssert.assertAll();
	    
	    }		    	
	    
	    
	    @Test(priority=205)
	    public void verifyStausInPOConfirmation(){	    
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	String strMessage ="";
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    	
	    	if(compareTwoStringValuesToSame(strOrderNumber, objAdjunoLIMAPoManagerPOM.getTrPONumber_Multi().getText())){
		    		if(objAdjunoLIMAPoManagerPOM.getTrStatus().getText().equalsIgnoreCase("Pending")){
		    			
		    		}else{
		    			strMessage = strMessage + " Status(Pending) does not match";
		    		}
		    	}else{
		    		strMessage = strMessage + strOrderNumber+  " does not match";
		    	}	
		    	
		    	objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);
	    		objSoftAssert.assertAll();	    	
	    }
	    
	    
	    @Test(priority=210)
	    public void test_AcessPriority(){	    
	    	SoftAssert objSoftAssert = new SoftAssert();
	    		
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		    String title = objAdjunoLIMAPoManagerPOM.callMouseHover(strPriorityFormName,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPriority());
		    	
		    	boolean bFlag = true;
		    	
		    	if (!(isNullOrBlank(title)))
		    	{
		    		if (title.equalsIgnoreCase(strPageTitlePriority))
		    			bFlag = true;
		    		else
		    			bFlag = false;
		    	}else{
		    		bFlag = false;
		    	}	       
		    	objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");
		   	
	    	objSoftAssert.assertAll();	    	
	    }
	    
	    
	    @Test(priority=215)
	    public void searchForPoNumberInPriority(){	    
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
	    	String strMessage ="";
	    		if (!isNullOrBlank(strOrderNumber)) 
		    	{
		    		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strPriorityFormName,"PARAM_PONumber"), true,"Order number field is not set");
		    		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderNumber, strPriorityFormName,"PARAM_PONumber"), true,"Order number field is not set");
		    	}else{
		    		strMessage = strMessage + " order number is null";
		    	}
		    			    	
		    
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true," Search chevron is not clicked");
	    
	    	objSoftAssert.assertAll();
	    	
	    }
	    	
	    
	/* @Test(priority=83)
	    public void verifyProcessCompleteMessage(){
	    	SoftAssert objSoftAssert = new SoftAssert();
	    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getTxtUpdateMessage()),true,"Process is not completed");		 
		    objSoftAssert.assertAll();
	    }*/
	    
	  
	  
	    
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
  
	public boolean compareTwoStringValuesToSame(String strValue1,String strValue2) {
		boolean bFlag = true;
		if ((!(isNullOrBlank(strValue1))) && (!(isNullOrBlank(strValue2)))) {
			if (strValue1.equalsIgnoreCase(strValue2)) {
			} else {
				bFlag = false;
			}
		} else {
			bFlag = false;
		}

		return bFlag;
	}

}
