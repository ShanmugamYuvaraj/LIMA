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

public class AdjunoWFLPoManagerTest {
		
 	WebDriver driver;

    long nPage_Timeout = 0;
    String strTestURL;
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
    String strCertificateType;
    
    String strConsignee;
    String strOrderNumber;
    String strOrderType;
    String strCustomer;
    String strWFLRef;
    String strWFLRefInvalid;
    String strLineType2;
    String strLineType3;
    String strLineType1;
    
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
    String strSeason;
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
    NodeList productCardValue;

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
            
            	            
            Node testLIMAURL = (Node) xPath.evaluate("/config/WFL/WFL_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/WFL/WFL_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/WFL/WFL_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	            
  	        Node pageTitleHome = (Node) xPath.evaluate("/config/WFL/WFL_Title_HomePage", dDoc, XPathConstants.NODE);
  	        strPageTitleHome = pageTitleHome.getTextContent();
  	            
  	        Node pageTitlePoManager = (Node) xPath.evaluate("/config/WFL/WFL_Title_PO_Manager", dDoc, XPathConstants.NODE);
	        strPageTitlePoManager = pageTitlePoManager.getTextContent();
  	                       
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
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrPoManagerWFLXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            orderSummary = (NodeList) xPath1.evaluate("/PO_Manager/Order_Summary", dDoc1, XPathConstants.NODESET);
            newProduct = (NodeList) xPath1.evaluate("/PO_Manager/New_Product", dDoc1, XPathConstants.NODESET);
            orderHeader = (NodeList) xPath1.evaluate("/PO_Manager/Order_Header", dDoc1, XPathConstants.NODESET);
            productSummary = (NodeList) xPath1.evaluate("/PO_Manager/Product_Summary", dDoc1, XPathConstants.NODESET);
            productSummaryDetailsCaptions = (NodeList) xPath1.evaluate("/PO_Manager/Wfl_Product_Summary_Details_Captions", dDoc1, XPathConstants.NODESET); 
            productCardCaptions = (NodeList) xPath1.evaluate("/PO_Manager/Wfl_Product_Card_Captions", dDoc1,XPathConstants.NODESET);      
            productCardValue = (NodeList) xPath1.evaluate("/PO_Manager/Product_Card_Value", dDoc1, XPathConstants.NODESET);
            
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
    public void test_1d003_AccessPOManager()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
        
    	String strTitle = objAdjunoLIMAPoManagerPOM.callMouseHover(strformNamePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPoManager());
    	
    	boolean bFlag = true;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitlePoManager))
    			bFlag = true;
    		else
    			bFlag = false;
    	}else{
    		bFlag = false;
    	}	       
    	objSoftAssert.assertEquals(bFlag, true, "***** Test ID :1.003 - Title of the page is wrong *****");
    	objSoftAssert.assertAll();

    }
    
    
    
	@Test(priority=10)
    public void checkForChevorons()
	{
    	
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesChevronExist(objAdjunoLIMAPoManagerPOM.getChvSearch()), true,"search Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesChevronExist(objAdjunoLIMAPoManagerPOM.getChvEdit()), true,"edit Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesChevronExist(objAdjunoLIMAPoManagerPOM.getChvComplete1()), true,"Complete Chevorn not found");
        
        
    	objSoftAssert.assertAll();
    }

    
	@Test(priority=15)
    public void checkForExistanceOfFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
      
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strformNamePoManager,"PARAM_OrderNumber"), true,"WFL Reference field is not found");
 	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strformNamePoManager,"PARAM_Customer"), true,"Customer field is not found");
 	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strformNamePoManager,"PARAM_CustomerReference"), true,"Order Number field is not found");	 	        
       
        objSoftAssert.assertAll();
    }
    
    
	@Test(priority=20)
    public void clearsearchFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
        Element el;	         
        strOrderNumber = objAdjunoLIMAPoManagerPOM.generateRandomPO2();
    	for(int i=0; i <= orderHeader.getLength()-1; i++)
         {
         	if (orderHeader.item(i).getNodeType() == Node.ELEMENT_NODE)
         	{
         		el = (Element) orderHeader.item(i);
 			//	strOrderNumber = el.getElementsByTagName("Order_Number").item(0).getTextContent(); 
 				
 				strOrderType = el.getElementsByTagName("Order_Type").item(0).getTextContent(); 
 				
 				strConsignee = el.getElementsByTagName("Consignee").item(0).getTextContent();
 				
 				strCustomer = el.getElementsByTagName("Customer").item(0).getTextContent();
 				
 				strWFLRef = el.getElementsByTagName("WLF_Ref").item(0).getTextContent();
 				
 				strWFLRefInvalid = el.getElementsByTagName("WLF_Ref_Invalid").item(0).getTextContent();
 				
 				
         	}	
         }
       	
        objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderNumber"), true,"can't clear WFL Reference Field");		    
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_CustomerReference"),true,"can't clear Order Number  Field");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_Customer"),true,"can't clear Customer Field");
		wait(3000);
				        
              	        	        	        
        objSoftAssert.assertAll();
           
    }
    	    
	 @Test(priority =22)
	 public void test_1d004_checkForCustomerMandatoryField()
	 {
	   	SoftAssert objSoftAssert = new SoftAssert();
	   	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		String strMessage = "";
	   	
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue("",strformNamePoManager,"PARAM_Customer"),true,"can't set Customer field empty");
		
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true,"***** Test ID :1.004- Not able to click Edit Chevron *****");
	   	wait(5000);
	   	
	   	String strErrorMessageCustomer = objAdjunoLIMAPoManagerPOM.getValidationMessageFieldElement(strformNamePoManager,"PARAM_Customer");
	   	if(!strErrorMessageCustomer.equals("")){
	   		if(strErrorMessageCustomer.contains("&#39;")){
		   		strErrorMessageCustomer = strErrorMessageCustomer.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorMessageCustomer.equalsIgnoreCase("> 'Customer' is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"***** Test ID :1.004-The Mandatory field error message is not dispalyed *****");	   			
	   	}else{
	   		strMessage = strMessage + "***** Test ID :1.004- Customer field error message is empty ***** ";
	   	}
	    
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);	  		
	   	objSoftAssert.assertAll();	
	 }
	   	
	@Test(priority=23)
	public void setFieldValue()
	{
	   	SoftAssert objSoftAssert = new SoftAssert();
	   	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	   	String strMessage = "";
	   		
	   	if(!isNullOrBlank(strCustomer))
	   	{
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderNumber"), true,"can't clear WFL Reference Field");
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCustomer,strformNamePoManager,"PARAM_Customer"),true,"Customer field field value is not set");    	   
	   	}
	   	else{
	   		strMessage = strMessage + " Customer is empty ";
	   	}
   
	   	
	   	objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);   
	   		
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true,"Not able to click Edit Chevron");
	   	wait(5000);
   		
	   	objSoftAssert.assertAll();
	}
	   	  
	    
    @Test(priority=25)
	public void accessPOManagerEdit()
    {
	    SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
        
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	String strTitle = "";
		if (objAdjunoUILibrary.isPageLoaded(strEditFormNamePOManager))
		{
			strTitle = driver.getTitle();
		}
    	
    	boolean bFlag = true;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitlePoManager))
    			bFlag = true;
    		else
    			bFlag = false;
    	}else{
    		bFlag = false;
    	}	       
    	objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");
    	objSoftAssert.assertAll();
    }
	      
	    
    @Test(priority=26)
	public void test_1d005_VerifyCustomerMandatoryField() throws ParseException
    {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);	 
		
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strEditFormNamePOManager,"Customer").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCustomer, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strEditFormNamePOManager,"Customer")),true,"***** Test ID :1.005 - Customer field value does not match *****");
    	}else{
    		strMessage = strMessage +  " Customer filed value is Empty ";
    	}
	
	
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate").equals("")){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
			String strOrderDate =objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate");
		
			objSoftAssert.assertEquals(strOrderDate.equals(simpleDateFormat.format(new Date())),true,"***** Test ID :1.005 - order date field value does not match *****");
		}else{
			strMessage = strMessage + "Order date value  field is empty ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true," ***** Test ID :1.005 - "+strMessage+  " *****");
		objSoftAssert.assertAll();
	}
       
    @Test(priority=27)
	public void setCustomerAndOrderNo()
    {
	    SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
        String strMessage = "";       
     			
        boolean bFlag = objAdjunoLIMAPoManagerPOM.verifyPoManagerPage(strformNamePoManager,strPageTitlePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPoManager());
		objSoftAssert.assertEquals(bFlag, true,"Title of the page is wrong");
		        
	   	if(!isNullOrBlank(strCustomer))
	   	{
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCustomer,strformNamePoManager,"PARAM_Customer"),true,"Customer field value is not set");    	   
	   	}
	   	else{
	   		strMessage = strMessage + " Customer field value is empty ";
	  	}
	   			
	   	if(!isNullOrBlank(strOrderNumber))
   		{
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderNumber,strformNamePoManager,"PARAM_CustomerReference"),true,"Order Number field is not set");    	   
   		}
   		else{
   		  strMessage = strMessage + " Order Number is empty ";
   		}
 
	   	
	   	objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);   
	   		   	
	    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true,"Not able to click Edit Chevron");
		wait(5000);
		    
		objSoftAssert.assertAll();
  }
  
  
    @Test(priority=28)
	public void test_1d006_VerifyCustomerAndOrderNumberMandatoryField()
    {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
		wait(5000);
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"_CustomerReference").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOrderNumber, objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"_CustomerReference")),true," ***** Test ID :1.006 - Order Number field value does not match *****");
    	}else{
    		strMessage = strMessage  + "WfL reference field value is empty ";
    	}
		
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strEditFormNamePOManager,"Customer").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCustomer, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strEditFormNamePOManager,"Customer")),true," ***** Test ID :1.006 - customer does not match *****");
    	}else{
    		strMessage = strMessage +  "Customer field value is empty ";
    	}
		   		
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate").equals("")){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
    		String strOrderDate =objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate");
    		
    		objSoftAssert.assertEquals(strOrderDate.equals(simpleDateFormat.format(new Date())),true,"***** Test ID :1.006 - order date does not match *****");
    	}else{
    		strMessage = strMessage + "Order date field value is empty ";
    	}
		
		objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :1.006 - " + strMessage+ " *****")	;
		objSoftAssert.assertAll();
    }
  
    @Test(priority=29)
    public void setCustomerAndRef(){
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    String strMessage = "";       
   	     	    	
	    
   		boolean bFlag = objAdjunoLIMAPoManagerPOM.verifyPoManagerPage(strformNamePoManager,strPageTitlePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPoManager());
	    objSoftAssert.assertEquals(bFlag, true,"Title of the page is wrong");
   		
   		if(!isNullOrBlank(strCustomer))
   		{
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_Customer"), true,"can't clear Customer Field");		    
			    
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCustomer,strformNamePoManager,"PARAM_Customer"),true,"Customer field is not set");    	   
   		}
   		else{
   			strMessage = strMessage + " Customer field value is empty ";
   		}
   			
   		if(!isNullOrBlank(strWFLRef))
   		{
   			
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_CustomerReference"),true,"can't clear Order Number Field");
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderNumber"),true,"can't clear WFL Ref Field");
   			
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strWFLRef,strformNamePoManager,"PARAM_OrderNumber"),true," WFL Ref field is not set");    	   
   		}
   		else{
   			strMessage = strMessage + " WFL Ref field is empty ";
   		}

   		objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);   
   		   	
   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true,"Not able to click Edit Chevron");
   		wait(5000);
	    
   		objSoftAssert.assertAll();
   
    }


    @Test(priority=30)
	public void test_1d007_VerifyCustomerAndWFLRefMandatoryField()
    {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		wait(4000);
		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);	 
		
		if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strEditFormNamePOManager,"Customer").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCustomer, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strEditFormNamePOManager,"Customer")),true,"***** Test ID :1.007 - customer field value does not match  *****");
    	}else{
    		strMessage = strMessage  + "Customer Field value is empty  ";
    	}
		
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"OrderNumber").equals("")){
    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strWFLRef, objAdjunoLIMAPoManagerPOM.getFieldValue(strEditFormNamePOManager,"OrderNumber")),true,"***** Test ID :1.007 - order Number field value does not match *****");
    	}else{
    		strMessage = strMessage +   "Order Number Field Value is empty ";
    	}
		   		
		if(!objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate").equals("")){
	    	String strOrderDate =objAdjunoLIMAPoManagerPOM.getFieldValue2(strEditFormNamePOManager,"OrderDate");
	    	objSoftAssert.assertEquals(strOrderDate.equals("09 Aug 2017"),true,"***** Test ID :1.007- order date does not match *****");
		}else{
			strMessage = strMessage  +" Order Date field value is emplty ";
		}
			
		objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :1.007 -" +strMessage+ " *****")	;
		objSoftAssert.assertAll();
		
    }


    @Test(priority=31)
    public void test_1d008_VerifyCustomerAndInvalidRefMandatoryField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage = "";       
    		
   		boolean bFlag = objAdjunoLIMAPoManagerPOM.verifyPoManagerPage(strformNamePoManager,strPageTitlePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPoManager());
	    objSoftAssert.assertEquals(bFlag, true,"Title of the page is wrong");
	    
   		if(!isNullOrBlank(strCustomer)){
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCustomer,strformNamePoManager,"PARAM_Customer"),true,"***** Test ID :1.008 - Customer field value is not set *****");    	   
   		}else{
   			strMessage = strMessage + " Customer field value is empty ";
   		}
   			
   		if(!isNullOrBlank(strWFLRefInvalid)){
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderNumber"),true," can't clear Order Number field");
   			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strWFLRefInvalid,strformNamePoManager,"PARAM_OrderNumber"),true,"***** Test ID :1.008 - WFL Ref field is not set *****");    	   
   		}else{
   			strMessage = strMessage + " WFL Ref field value is empty ";
   		}

   	
   		String strErrorMessagePONumber = objAdjunoLIMAPoManagerPOM.getValidationMessageFieldElement(strformNamePoManager,"PARAM_OrderNumber");
   		if(!strErrorMessagePONumber.equals("")){
   			boolean bFlag2 = strErrorMessagePONumber.trim().equalsIgnoreCase("> The PO Number entered does not exist for this customer.");
   		    
   	   		objSoftAssert.assertEquals(bFlag2, true,"Error Message should be shown for invalid WFL Ref");
   		}else{
   			strMessage = strMessage + " Po number error message is empty ";
   		}
   		
   		objSoftAssert.assertEquals(strMessage.equals(""),true,"c"+strMessage +" *****");   
   		   	
   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true," ***** Test ID :1.008 - Not able to click Edit Chevron *****");
   		wait(5000);
	    
   		
   		objSoftAssert.assertAll();
   
    }
	

    @Test(priority=32)
    public void newPOCreate()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage = "";
    
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
     		
     			strLineType2 = el.getElementsByTagName("Line_Type_2").item(0).getTextContent();
     		
     			strLineType1 = el.getElementsByTagName("Line_Type_1").item(0).getTextContent();
     		
     			strLineType3 = el.getElementsByTagName("Line_Type_3").item(0).getTextContent();
				 
    		}	
     	}
    	//set value in the screen
    	boolean bFlag = objAdjunoLIMAPoManagerPOM.verifyPoManagerPage(strformNamePoManager,strPageTitlePoManager,objAdjunoLIMAPoManagerPOM.getLnkTool(),objAdjunoLIMAPoManagerPOM.getLnkPoManager());
		objSoftAssert.assertEquals(bFlag, true,"Title of the page is wrong");
	   		
	   	if(!isNullOrBlank(strCustomer)){
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_Customer"), true,"can't clear Customer Field");		    				    
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCustomer,strformNamePoManager,"PARAM_Customer"),true,"Customer field is not set");    	   
	   	}else{
	   		strMessage = strMessage + " Customer field value is null ";
	   	}
	   			
	   	if(!isNullOrBlank(strOrderNumber)){
	   			
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_CustomerReference"),true,"can't clear Order Number Field");
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strformNamePoManager,"PARAM_OrderNumber"),true,"can't clear WFL Ref Field");
	   			
	   		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderNumber,strformNamePoManager,"PARAM_CustomerReference"),true," Order Number field is not set");    	   
	   	}else{
	   		strMessage = strMessage + " order Number field value is null ";
	   	}
	   	
	   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvEdit()),true,"Not able to click Edit Chevron");
	   	wait(5000);
	   	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	   	objSoftAssert.assertAll();
    }  


    @Test(priority=33)
	public void verifyElementsInEditChevron()
    {
	    SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);	    
	  	
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getTxtEdit()), true, "Edit title not found");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Panel_OrderNo"), true,"Order header section not found");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Panel_POH1"), true,"Order Summary section not found");
		//  objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"TabPanel_Detail"), true,"c");
		// tab header verify
					
		//objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"GRID_ContractSummaryDetails"), true,"Product summary Grid not found");
			
		objSoftAssert.assertAll();
	 }


    @Test(priority=35)
    public void test_1d014_VerifyEditChevronField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OrderNumber"), true,"WFL ref field not found");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Customer"), true,"Customer field not found");
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager, "_CustomerReference"),true, " order Number field not found");
		
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginCountryDesc"),true,"OriginCountryDesc field is not found");    	   
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"SupplierCode"),true,"suppliercode field is not found");    	   
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Supplier"),true,"supplier field is not found");    	   
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Mode"),true,"Mode field is not found");    	   
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginPortDesc"),true,"origin Port desc field is not found");   			      			    
		
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DestinationPortDesc"), true,"Destination Port Desc field is not found");
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginCountry"), true,"Origin Country field is not found");
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OriginPort"), true,"OriginPort field is not found");
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Category"),true,"Category field is not found");    	   
	    
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Department"),true,"Department field is not found");    	   
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"ExFactoryDate"),true,"ExFactoryDate field is not found");    	   
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"OrderCallOff"),true,"OrderCallOff field is not found");    	   
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"LatestShipDate"),true,"LatestShipDate field is not found");   			      			    
		
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DestinationPort"), true,"DestinationPort Port Desc field is not found");
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"DeliveryDate"), true,"DeliveryDate field is not found");
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Channel"), true,"Channel field is not found");
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"ShipDate"), true,"ShipDate field is not found");
    		
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"CertificateType"), true,"Certificate Type field is not found");
    	
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
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.checkDoesElementExist(strEditFormNamePOManager,"Refrigeration"),true,"Refrigeration field is not found");
  
    	
		objSoftAssert.assertAll();
    }



    
    @Test(priority=40)
    public void test_1d017_verifyMandatotyFields() 
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage="";
	
    	String strErrorMessageCertificateType = objAdjunoLIMAPoManagerPOM.getValidationMessageFieldElement(strEditFormNamePOManager,"CertificateType");
    	if(!strErrorMessageCertificateType.equals("")){
    		
    		if(strErrorMessageCertificateType.contains("&#39;")){
         		strErrorMessageCertificateType = strErrorMessageCertificateType.replaceAll("&#39;", "'");
    	   	}
         	
        	boolean bFlag2 = strErrorMessageCertificateType.equalsIgnoreCase("> 'Certificate Type' is a required field");
        	objSoftAssert.assertEquals(bFlag2, true,"***** Test ID :1.017 - Error Message should be shown for blank Certificate *****");
    	}else{
    		strMessage = strMessage + " certificate type error message is empty";
    	}
     	
    	checkMandatoryField();
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :1.017 -"+strMessage+" *****");
    	objSoftAssert.assertAll();
    }

  
   
  
  
    @Test(priority=50)
    public void verifyCaptionsInProductSummary()
    {
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	
    	String strMessage="";
    	
    	ArrayList<String> list = new ArrayList<String>();
    	
    	list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_ContractSummaryDetails");
    	
    	strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list,productSummaryDetailsCaptions,33);
    	
    	
		
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"Product summary  Tab:"+strMessage);
					
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
		   strMessage = strMessage +"new Product code field value is null";
	   }
	   
	   if(!isNullOrBlank(strNewIdentifie))
	   {
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strNewIdentifie,strEditFormNamePOManager,"tmpIdentifier"),true,"");    	   
	   }else{
		   strMessage = strMessage +" new identifie field value is null";
	   }
	   
	   if(!isNullOrBlank(strNewSKUCode))
	   {
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strNewSKUCode,strEditFormNamePOManager,"tmpSKU"),true,"");    	   
	   }else{
		   strMessage = strMessage +" new Sku code field value is null";
	   }
	   
	   if(!isNullOrBlank(strDescription))
	   {
		  objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDescription,strEditFormNamePOManager,"tmpDescription"),true,"");    	   
	   }else{
		   strMessage = strMessage +" Description field value is null";
	   }
		    
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButton(strEditFormNamePOManager,"butAddNewProduct"),true,"Add Product Button not clicked");
	   
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getYesButton()),true,"Yes Button not clicked");
	   wait(2000);
	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	   objSoftAssert.assertAll();
	 }	 
  
    public void checkMandatoryField(){
    	SoftAssert objSoftAssert = new SoftAssert();
	  
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	String strMessage ="";
	  
    	String strErrorMessageCarton = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Quantity");
    	if(!strErrorMessageCarton.equals("")){
    		boolean bFlag2 = strErrorMessageCarton.equalsIgnoreCase("> Quantity must be greater than Zero.");
        	
        	objSoftAssert.assertEquals(bFlag2, true,"Error Message should be shown for blank cartons");   	  
    	}else{
    		strMessage = strMessage + "carton error message is null";
    	}
    	
    	String strErrorMessageShipDate = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"ContractShipDate");

    	if(!strErrorMessageShipDate.equals("")){
    		if(strErrorMessageShipDate.contains("&#39;")){
        		strErrorMessageShipDate = strErrorMessageShipDate.replaceAll("&#39;", "'");
        	}
        														
        	boolean bFlag3 = strErrorMessageShipDate.equalsIgnoreCase("> 'Ship Date' is a required field");
        	objSoftAssert.assertEquals(bFlag3, true,"Error Message should be shown for blank shipDate");
    	}else{
    		strMessage = strMessage + " ship date error message is null ";
    	}
    	
    		  
    	String strErrorMessageDeliveryDate = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"ContractDeliveryDate");
    	if(!strErrorMessageDeliveryDate.equals("")){
    		if(strErrorMessageDeliveryDate.contains("&#39;")){
        		
        		strErrorMessageDeliveryDate=strErrorMessageDeliveryDate.replaceAll("&#39;", "'");
        	}
        	
        	boolean bFlag4 = strErrorMessageDeliveryDate.equalsIgnoreCase("> 'Delivery Date' is a required field");
    	  
        	objSoftAssert.assertEquals(bFlag4, true,"Error Message should be shown for blank Delivery Date");
    	  
    	}else{
    		strMessage =strMessage + " Delivery Date error message is null ";
    	}
    	
    	String strErrorMessageLineType = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineType");
    	if(!strErrorMessageLineType.equals("")){
    		if(strErrorMessageLineType.contains("&#39;")){
        		strErrorMessageLineType = strErrorMessageLineType.replaceAll("&#39;", "'");
        	}
        	
        	boolean bFlag5 = strErrorMessageLineType.equalsIgnoreCase("> 'LineType' is a required field");
    	  
        	objSoftAssert.assertEquals(bFlag5, true,"Error Message should be shown for blank Line Type ");
    	  
    	}else{
    		strMessage =strMessage + " LineType error message is null ";
    	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickOnNextTab(strEditFormNamePOManager,"TabPanel_Detail"),true,"Product card tab not clicked");
	  
    	String strErrorMessageCerificate = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ProductCard",0,"ProductCertificateType");
	  
    	if(!strErrorMessageCerificate.equals("")){

        	if(strErrorMessageCerificate.contains("&#39;")){
        		strErrorMessageCerificate =strErrorMessageCerificate.replaceAll("&#39;", "'");
        	}
        	
        	boolean bFlag6 = strErrorMessageCerificate.equalsIgnoreCase("> 'Certificate Type' is a required field");
    	        
        	objSoftAssert.assertEquals(bFlag6, true,"Error Message should be shown for blank Certificate type in Product card");
    	  
    	}else{
    		strMessage =strMessage + " Certificate Type error message is null ";
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickOnPreviousTab(strEditFormNamePOManager,"TabPanel_Detail"),true," Product summary tab not clicked");
	  
    	objSoftAssert.assertAll();
  } 
  
  @Test(priority=65)
  public void setOrderSummuryFields(){
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
         			         		
        	strCertificateType = el.getElementsByTagName("Certificate_Type").item(0).getTextContent();
         		
         }	
         	
       }
    	
    	
	   if(!isNullOrBlank(strOriginDesc)){
		    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOriginDesc,strEditFormNamePOManager,"OriginCountryDesc"),true,"Origin Country Desc field is not set");    	   
	   }else{
		   strMessage = strMessage + " origin Desc field value is null";
	   }
		    
	   if(!isNullOrBlank(strSupplier)){
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strSupplier,strEditFormNamePOManager,"Supplier"),true,"supplier field is not set");    	   
	   }else{
		   strMessage = strMessage + " supplier field value is null";
	   }
	   
	   if(!isNullOrBlank(strMode)){
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strMode,strEditFormNamePOManager,"Mode"),true,"Mode field is not set");    	   
	   }else{
		   strMessage = strMessage + " mode field value is null";
	   }
		    
	   if(!isNullOrBlank(strDestinationPortDesc)) {
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDestinationPortDesc,strEditFormNamePOManager,"DestinationPortDesc"),true,"Destination Port Desc field is not set");    	   
	   }else{
		   strMessage = strMessage + " DestinationPort Desc field value is null";
	   }
		    
	   if(!isNullOrBlank(strCategory)){
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCategory,strEditFormNamePOManager,"Category"),true,"Category field is not set");    	   
	   }else{
		   strMessage = strMessage + " Category field value is null";
	   }
	   
		    
	   if(!isNullOrBlank(strDepartment)){
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDepartment,strEditFormNamePOManager,"Department"),true,"Department field is not set");    	   
	   }else{
		   strMessage = strMessage + " Department field value is null";
	   }
	   
	   strShipDate = objAdjunoLIMAPoManagerPOM.getDate(15,"dd MMM yyyy");
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strShipDate,strEditFormNamePOManager,"ShipDate"),true,"Ship Date field is not set");    	   
	  
	   strDeliveryDate = objAdjunoLIMAPoManagerPOM.getDate(30,"dd MMM yyyy");
	   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strDeliveryDate,strEditFormNamePOManager,"DeliveryDate"),true,"Delivery Date field is not set");    	   
	  
	   
	   if(!isNullOrBlank(strCertificateType)){
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strCertificateType,strEditFormNamePOManager,"CertificateType"),true," CertificateType field is not set");    	   
	   }else{
		   strMessage = strMessage + " CertificateType field value is null";
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);    
	   objSoftAssert.assertAll();
    }
    
  
  	@Test(priority=70)
  	public void setValueForProductSummaryTabFields(){
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
         		
  				strSeason = el.getElementsByTagName("Season").item(0).getTextContent(); 
         		
  				strLoadType = el.getElementsByTagName("Load_Type").item(0).getTextContent(); 
         		
  				strRefrigeration= el.getElementsByTagName("Refrigeration").item(0).getTextContent();
  				
  			}	
         	
  		}
  		if(!isNullOrBlank(strBuyer)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strBuyer,strEditFormNamePOManager,"Buyer"),true,"Buyer field is not set");    	   
  		}else{
  			strMessage = strMessage + " Buyer field value is null";
  		}
		    
		    
  		if(!isNullOrBlank(strBuyingAgent)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strBuyingAgent,strEditFormNamePOManager,"BuyingAgent"),true,"Buying Agent field is not set");    	   
  		}else{
  			strMessage = strMessage + " buying Agent field value is null";
  		}
		    
  		if(!isNullOrBlank(strBuyingTerms)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strBuyingTerms,strEditFormNamePOManager,"BuyingTerms"),true,"Buying Terms field is not set");    	   
  		}else{
  			strMessage = strMessage + " Buying Terms field value is null";
  		}
		    
  		if(!isNullOrBlank(strPurchaseCurrency)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strPurchaseCurrency,strEditFormNamePOManager,"PurchaseCurrency"),true,"Purchase Currency field is not set");    	   
  		}else{
  			strMessage = strMessage + " Purchase Currency field value is null";
  		}
		    
  		if(!isNullOrBlank(strRetailCurrency)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strRetailCurrency,strEditFormNamePOManager,"RetailCurrency"),true,"Retail Currency field is not set");    	   
  		}else{
  			strMessage = strMessage + " Retail Currency field value is null";
  		}
    
  		if(!isNullOrBlank(strMerchandiser)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strMerchandiser,strEditFormNamePOManager,"Merchandiser"),true,"Merchandiser field is not set");    	   
  		}else{
  			strMessage = strMessage + " Mechandiser field value is null";
  		}
    
  		if(!isNullOrBlank(strQuantityTolerance)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strQuantityTolerance,strEditFormNamePOManager,"QuantityTolerance"),true,"QuantityTolerance field is not set");    	   
  		}else{
  			strMessage = strMessage + " quantity Tolerence field value is null";
  		}
    
  		if(!isNullOrBlank(strSeason)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strSeason,strEditFormNamePOManager,"Season"),true,"Season field is not set");    	   
  		}else{
  			strMessage = strMessage + " season field value is null";
  		}
    
  		if(!isNullOrBlank(strLoadType)){
  			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strLoadType,strEditFormNamePOManager,"LoadType"),true,"Load type field is not set");    	   
  		}else{
  			strMessage = strMessage + " LoadType field value is null";
  		}
    
  		if(!isNullOrBlank(strRefrigeration)){
    		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strRefrigeration,strEditFormNamePOManager,"Refrigeration"),true,"Refrigeration field is not set");    	   
    	}else{
    		strMessage = strMessage + " Refrigeration field value is null";
    	}	
    
   
  		objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
  		objSoftAssert.assertAll();
  	}
  

	    	
  	@Test(priority=75)
  	public void setProductSummaryGridValue(){
  		SoftAssert objSoftAssert = new SoftAssert();
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  		String strMessage = "";
  		wait(5000);
	 
	 
	   if(!isNullOrBlank(strWeight)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Weight",strWeight),true,"Weight field value is not set");    	   
	   }else{
    	  strMessage = strMessage +" Weight field value is null";
	   }
	   
	   if(!isNullOrBlank(strCube)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Cube",strCube),true,"Cube field value is not set");    	   
	   }else{
		   strMessage = strMessage +" cube field value is null";
	   }
	   if(!isNullOrBlank(strWeightType)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"WeightType",strWeightType),true,"Weight Type field value is not set");    	   
	   }else{
		   strMessage = strMessage +" Weight Type field value is null";
	   }
	   
	   if(!isNullOrBlank(strPackType)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"PackType",strPackType),true,"Pack Type field value is not set");    	   
	   }else{
		   strMessage = strMessage +" Pack Type field value is null";
	   }
	   		     
	   if(!isNullOrBlank(strUnitPrice)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"UnitPrice",strUnitPrice),true,"Unit Price field value is not set");    	   
	   }else{
		   strMessage = strMessage +" Unit Price field value is null";
	   }
	   
	   if(!isNullOrBlank(strRetailPrice)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"RetailPrice",strRetailPrice),true,"Retail Price field value is not set");    	   
	   }else{
		   strMessage = strMessage +" Retail Price field value is null";
	   }
	   
	   if(!isNullOrBlank(strPriority)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Priority",strPriority),true,"Priority field value is not set");    	   
	   }else{
		   strMessage = strMessage +" Priority field value is null";
	   }
	   
	   if(!isNullOrBlank(strProjectedLandedCost)){	
		   objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LandedCost",strProjectedLandedCost),true,"Project landed cost field value is not set");    	   
	   }else{
		   strMessage = strMessage +" project Landed cost field value is null";
	   }
	  	  
	   if(!isNullOrBlank(strCartons)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"Quantity",strCartons),true,"Cartons field value is not set");    	   
	   }else{
			strMessage = strMessage +" Cartons field value is null";
	   }
		   
	   if(!isNullOrBlank(strLineType2)){	
		   	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineType",strLineType2),true," LineType field value is not set");    	   
	   }else{
		   strMessage = strMessage +" Line Type field value is null";
	    }
		
	   
	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	   objSoftAssert.assertAll();
 }
 
 
  	@Test(priority=80)
  	public void test_1d010_VerifyLineConsigneeMandatoryField(){
  		SoftAssert objSoftAssert = new SoftAssert();
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  		String strMessage ="";
	//	 objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineConsignee",""),true," can't clear Line Consignee field");
	//	 objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"ProductDestination",""),true," can't clear Product Destination field");
		 
		 String strErrorMessageConsignee = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineConsigneeDesc");
		 if(!strErrorMessageConsignee.equals("")){
		     boolean bFlag = strErrorMessageConsignee.equalsIgnoreCase("> &#39;Line Consignee&#39; is a required field");
		        		       
		     objSoftAssert.assertEquals(bFlag, true,"***** Test ID :1.010 - The Mandatory field error message is not dispalyed for Line Consignee *****");
		 }else{
			 strMessage =strMessage + " consignee error message is empty ";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :1.010 -"+strMessage +" *****");
		 objSoftAssert.assertAll();
			 
  }
 
  	@Test(priority=85)
  	public void test_1d019_CheckLineConsigneeAndProductDestiMandatoryField()
  	{
  		SoftAssert objSoftAssert = new SoftAssert();
  		String strMessage ="";
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  	
	 
		if(!isNullOrBlank(strLineType1)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineType",strLineType1),true,"***** Test ID :1.019 -LineType field value field value is not set *****");    	   
		}else{
			strMessage = strMessage +" Line Type field value is empty";
		}
	
		
		wait(4000);
		String strErrorMessageConsignee = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineConsigneeDesc");
		 if(!strErrorMessageConsignee.equals("")){
			 boolean bFlag2 = strErrorMessageConsignee.equalsIgnoreCase("> &#39;Line Consignee&#39; is a required field");
				
		     objSoftAssert.assertEquals(bFlag2, true,"***** Test ID :1.019 -The Mandatory field error message is not dispalyed for Line Consignee *****");
				
		     
		 }else{
			 strMessage =strMessage + " consignee error message is empty ";
		 } 
		
		String strErrorMessageProductDest = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"ProductDestinationDesc");
		if(!strErrorMessageProductDest.equals("")){
			 boolean bFlag2 = strErrorMessageProductDest.equalsIgnoreCase("> &#39;Product Destination&#39; is a required field");
		
			objSoftAssert.assertEquals(bFlag2, true,"***** Test ID :1.019 -The Mandatory field error message is not dispalyed for Product Destination *****");	
		     
		 }else{
			 strMessage =strMessage + " Product destination error message is empty ";
		 }
     
		objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :1.019 -"+strMessage+" *****");
		objSoftAssert.assertAll();
	 
  	}
 
  	@Test(priority=90)
  	public void test_1d020_VerifyProductDestintionMandatoryField()
  	{
  		SoftAssert objSoftAssert = new SoftAssert();
  		String strMessage ="";
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	 
		 if(!isNullOrBlank(strLineType3))
		 {	
			 objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineType",strLineType3),true,"***** Test ID :1.020 - LineType field value field value is not set *****");    	   
		 }else{
			 strMessage = strMessage +" Line Type field is empty";
		 }
	 
		 String strErrorMessageProductDest = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"ProductDestinationDesc");
		 if(!strErrorMessageProductDest.equals("")){
			 boolean bFlag = strErrorMessageProductDest.equalsIgnoreCase("> &#39;Product Destination&#39; is a required field");
			 
			 objSoftAssert.assertEquals(bFlag, true,"***** Test ID :1.020 -The Mandatory field error message is not dispalyed for Product Destination *****");
	     
		 }else{
			 strMessage = strMessage + " product destination error message is empty ";
		 }
		 
		 if(!isNullOrBlank(strLineType2)){	
		    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineType",strLineType2),true,"***** Test ID :1.020 - LineType field value is not set *****");    	   
		 }else{
		    strMessage = strMessage +" Line Type field is empty";
		 }
		 
		 if(!isNullOrBlank(strConsignee)) {	
		    objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"LineConsigneeDesc",strConsignee),true,"***** Test ID :1.020 - LineConsignee field value is not set *****");    	   
		 }else{
		    strMessage = strMessage +" Line Consignee is empty";
		 }
		 objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test ID :1.020 -" +strMessage+" *****"); 
		 objSoftAssert.assertAll();
	}
 
  	@Test(priority=95)
  	public void test_1d011_VerifyBestBeforeDateMandatoryField()
  	{
  		SoftAssert objSoftAssert = new SoftAssert();
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  		String strMessage = "";
  		wait(4000);  
  		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"BestBeforeDate",""),true," can't clear Best Before field");
  		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"DisplayUntilDate",""),true," can't clear Display Until field");
		
  		String previousDate = objAdjunoLIMAPoManagerPOM.getDate(-1,"dd MMM yyyy");
		 	 
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"BestBeforeDate",previousDate),true,"***** Test ID :1.011 - Best before date field value is not set *****");    	   
		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"DisplayUntilDate",previousDate),true,"***** Test ID :1.011 - Display Until Date field value is not set *****");
		 			 		 
		String strErrorMessageBestBefore = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"BestBeforeDate");
		if(!strErrorMessageBestBefore.equals("")){
			boolean bFlag = strErrorMessageBestBefore.equalsIgnoreCase("> Best Before Date cannot be in the past");			   
			objSoftAssert.assertEquals(bFlag, true,"***** Test ID :1.011 -The Mandatory field error message is not dispalyed for Best Before Date");
		}else{
			strMessage = strMessage + " Best Before Date error message is Empaty ";
		}
		  
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID :1.011 - "+strMessage+ " *****");
		objSoftAssert.assertAll();
	 
  	}
 
  	
  	@Test(priority=97)
  	public void test_1d012_VerifyDisplayUntilDateMandatoryField()
  	{
  		SoftAssert objSoftAssert = new SoftAssert();
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  		String strMessage = "";
  		
		String strErrorMessageDisplayUntil = objAdjunoLIMAPoManagerPOM.getValidationMessageGridElement(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"DisplayUntilDate");
	   
		if(!strErrorMessageDisplayUntil.equals("")){
			boolean bFlag2 = strErrorMessageDisplayUntil.equalsIgnoreCase("> Display Until Date cannot be in the past");			   
			objSoftAssert.assertEquals(bFlag2, true,"***** Test ID :1.012 -The Mandatory field error message is not dispalyed for Display Until Date *****");
		}else{
			strMessage = strMessage + " Display until Date error message is empaty ";
		}   
	   
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID :1.012 -"+strMessage+ " *****");
		objSoftAssert.assertAll();
	 
  	}
  	@Test(priority=98)
  	public void setBestBeforeVal()
  	{
  		SoftAssert objSoftAssert = new SoftAssert();
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  		wait(4000);
  		
  		String bestBeforeDate = objAdjunoLIMAPoManagerPOM.getDate(50,"dd MMM yyyy HH:mm");
  		String dipalyUntilDate = objAdjunoLIMAPoManagerPOM.getDate(60,"dd MMM yyyy HH:mm");
	     
  		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"BestBeforeDate",bestBeforeDate),true," can't set Best Before field");
  		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ContractSummaryDetails",0,"DisplayUntilDate",dipalyUntilDate),true," can't set Display Until field");
		
  		objSoftAssert.assertAll();
  	}
 
  	@Test(priority=100)
  	public void verifyCaptionsInProductCard()
  	{
    	
  		SoftAssert objSoftAssert = new SoftAssert();
  		objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
  		String strMessage="";
    	
  		ArrayList<String> list = new ArrayList<String>();
    	
  		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickOnNextTab(strEditFormNamePOManager,"TabPanel_Detail"),true,"Product card tab not clicked");
    	
  		list = objAdjunoLIMAPoManagerPOM.getCaptionsList(strEditFormNamePOManager,"GRID_ProductCard");
    	
  		strMessage = objAdjunoLIMAPoManagerPOM.verifyCaptionsONGrid(list,productCardCaptions,22);
    	
  		
  		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Product card Tab:"+strMessage);
					
  		objSoftAssert.assertAll();
  	}
    
  	@Test(priority=105)
  	public void setValueInProductCardTab(){
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
	    	 
	    if(!isNullOrBlank(strCommodity)){	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CommodityCode",strCommodity),true,"Commodity field value is not set");    	   
	    }else{
	    	strMessage = strMessage +" Commodity field value is empty";
	    }
	    
	    if(!isNullOrBlank(strDutyRate)){	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"DutyRate",strDutyRate),true,"Duty Rate field value is not set");    	   
		}else{
			strMessage = strMessage +" DutyRate field value is empty";
		}
			    
	    if(!isNullOrBlank(strQARequest)){	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"QARequest",strQARequest),true," QARequest field value is not set");    	   
	    }else{
	    	strMessage = strMessage +" QARequest field value is null";
	    }	
	    
	    if(!isNullOrBlank(strPiecesPerInner)){	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"PiecesPerInner",strPiecesPerInner),true,"PiecesPerInner field value is not set");    	   
	    }else{
	    	strMessage = strMessage +" PiecesPerInner field value is null";
	    }
	    	
		if(!isNullOrBlank(strNetWeight)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"NetWeight",strNetWeight),true,"NetWeight field value is not set");    	   
		}else{
			strMessage = strMessage +" NetWeight field value is null";
		}
			 
		if(!isNullOrBlank(strInnersPerOuter)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"InnersPerOuter",strInnersPerOuter),true,"InnersPerOuter field value is not set");    	   
		}else{
			strMessage = strMessage +" Inners Per Outer field value is null";
		}
			    
		if(!isNullOrBlank(strCartonLength)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CartonLength",strCartonLength),true,"CartonLength field value is not set");    	   
		}else{
			strMessage = strMessage +" CartonLength field value is null";
		}
			 
		if(!isNullOrBlank(strCartonWidth)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CartonWidth",strCartonWidth),true,"Carton Width field value is not set");    	   
		}else{
			strMessage = strMessage +" Carton Width field value is null";
		}
		
		if(	!isNullOrBlank(strCartonDepth)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"CartonDepth",strCartonDepth),true,"Carton Depth field value is not set");    	   
		}else{
			strMessage = strMessage +" Carton Depth field value is null";
		}
		
		if(!isNullOrBlank(strOuterWeight)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"OuterWeight",strOuterWeight),true,"OuterWeight field value is not set");    	   
		}else{
			strMessage = strMessage +" OuterWeight field value is null";
		}
			
		if(!isNullOrBlank(strOuterEAN)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"OuterCartonEAN",strOuterEAN),true,"OuterEAN field value is not set");    	   
		}else{
			strMessage = strMessage +" OuterEAN field value is null";
		}
		
		if(!isNullOrBlank(strInnerEAN)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"InnerCartonEAN",strInnerEAN),true,"Inner EAN field value is not set");    	   
		}else{
			strMessage = strMessage +" Inner EAN field value is null";
		}
		
		if(!isNullOrBlank(strTI)){	
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"TI",strTI),true,"TI field value is not set");    	   
		}else{
			strMessage = strMessage +" TI field value is null";
		}
		
		if(!isNullOrBlank(strHI)){	
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValueForGridCell(strEditFormNamePOManager,"GRID_ProductCard",0,"HI",strHI),true,"HI field value is not set");    	   
		}else{
			strMessage = strMessage +" HI field value is null";
		}	
			 
		objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
		objSoftAssert.assertAll();
    }
    
    
  	@Test(priority=130)
    public void test_1d025_ClickComplete()
  	{
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoManagerPOM.getChvComplete2()),true,"***** Test ID :1.025 - Not able to click complete Chevron *****");
	    wait(10000);
	    objSoftAssert.assertAll();
    }
    
  	  /* @Test(priority=135)
   	public void getTrackRefNo(){	    
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    if(strSiteType.equalsIgnoreCase("lima")){	
	    	objAdjunoLIMAPoManagerPOM.getBtnViewDetail().click();
	    	wait(3000);
	    	String poDetails = objAdjunoLIMAPoManagerPOM.getTxtViewDetail().getText();
	    	String[] vals = poDetails.split("Track");
						
			String valss[] = vals[1].split("'",14);
			strTrackN0 = valss[1];
			
	    }else{
    		throw new SkipException("skipping this test in WFL");
    	}	
    }
    
    
    @Test(priority=145)
    public void checkForPurchaseOrderDisplay() throws ParseException{	    
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getTxtPurchaseOrder()), true,"Title Purchase Order is not found");
	    	    	
	    	long min = objAdjunoLIMAPoManagerPOM.getTrackValue(strUserName);
	    	if(min<=275){
	    		
	    	}else{
	    		strMessage = "Not a todays date";
	    	}
	    	
	    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	    	
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=150)
    public void checkDataInPurchaseOrder() {	    
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getLnkPurchaseOrder()),true,"not able to click on Purchase Order");
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}	
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=155)
    public void viewPoInEditMode(){	
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){	
	    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickButtonUsingWebElement(objAdjunoLIMAPoManagerPOM.getBtnApply()), true,"Apply button is not clicked");
	    	wait(3000);
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=160)
    public void checkPageLoaded(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	boolean bFlag;
    	objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){	
			 if (objAdjunoUILibrary.isPageLoaded(strViewFormNamePO))
			 {
				 bFlag = true; 
			 }
			 else{
				 bFlag = false;
				 
			 }
			
			 objSoftAssert.assertEquals(bFlag, true,"Po Page is not Loaded");	 
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}
	  objSoftAssert.assertAll();
    }
    
    @Test(priority=170)
    public void checkValuesOnOrderHead(){	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){	
	    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"OrderNumber").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOrderNumber, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"OrderNumber")),true,"order number Does not match");
	    	}else{
	    		strMessage = strMessage + strOrderNumber + " value is null on this field ";	    		
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OrderType").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOrderType, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OrderType")),true,"order Type does not match");
	    	}else{
	    		strMessage = strMessage + strOrderType + " value is null on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Consignee").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strConsignee, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Consignee")),true,"Consignee does not match");
	    	
	    	}else{
	    		strMessage = strMessage + strConsignee +" value is null on this field ";
	    	}
		    	    	
	    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Error Message on check Values On OrderHead:"+strMessage);
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	} 		
	    objSoftAssert.assertAll();
    }
    
    
    @Test(priority=175)
    public void checkValuesInOrderSummary(){	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Supplier").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strSupplier, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Supplier")),true,"Supplier Does not match");
	    	}else{
	    		strMessage = strMessage + strSupplier +" value is null on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Mode").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strMode, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Mode")),true,"Mode does not match");
	    	}else{
	    		strMessage = strMessage + strMode +" value is null on this field ";
	    	}
	    	
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OriginCountry").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strOriginDesc, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"OriginCountry")),true,"Origin Desc Does not match");
	    	}else{
	    		strMessage = strMessage + strOriginDesc +" value is null on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"DestinationPort").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDestinationPortDesc, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"DestinationPort")),true,"Destination Port Desc does not match");
	    	}else{
	    		strMessage = strMessage + strDestinationPortDesc +" value is null on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Category").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strCategory, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"Category")),true,"Category Does not match");
	    	
	    	}else{
	    		strMessage = strMessage + strCategory +" value is null on this field ";
	    	}
	    	
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"ShipDate").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strShipDate, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"ShipDate")),true,"ShipDate does not match");
	    	}else{
	    		strMessage = strMessage + strShipDate +" value is null on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Destination").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDestinationDesc, objAdjunoLIMAPoManagerPOM.getFieldValue(strViewFormNamePO,"Destination")),true,"DestinationDesc Does not match");
	    	
	    	}else{
	    		strMessage = strMessage + strDestinationDesc +" value is null on this field ";
	    	}
	    	
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingAgent").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strBuyingAgent, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingAgent")),true,"BuyingAgent does not match");
	    	}else{
	    		strMessage = strMessage + strBuyingAgent +" value field value field value is not set on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingTerms").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strBuyingTerms, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"BuyingTerms")),true,"strBuyingTerms does not match");
	    	
	    	}else{
	    		strMessage = strMessage + strBuyingTerms +" value is null on this field ";
	    	}
	    	
	    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Error Message on checkValuesInOrderSummary:"+strMessage);
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}
    	
	    objSoftAssert.assertAll();    		    
    }
    
    
    @Test(priority=180)
    public void checkValuesInContractSummary(){	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){
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
	    		strMessage = strMessage + strSeason +" value is null on this field ";
	    	}
	    	
	    	if(!objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"LoadType").equals("")){
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strLoadType, objAdjunoLIMAPoManagerPOM.getDropDownFieldValue(strViewFormNamePO,"LoadType")),true,"LoadType Does not match");
	    	
	    	}else{
	    		strMessage = strMessage + strLoadType +" value is null on this field ";
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
	    			    	
	    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Error Message on checkValuesInProductSummary:"+strMessage);
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}    
	    objSoftAssert.assertAll();
    }
    

    @Test(priority=185)
    public void checkValuesInContractSummaryDetails(){	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){	    		    
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
	    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"Error Message on checkValuesInContractSummeryDetails:"+strMessage);
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}		    	
    	objSoftAssert.assertAll();
    }
    
    
    
    @Test(priority=185)
    public void checkValuesInProductCard(){	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	
    	if(strSiteType.equalsIgnoreCase("lima")){	    
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
	    		objSoftAssert.assertEquals(compareTwoStringValuesToSame(strDescription, objAdjunoLIMAPoManagerPOM.getGridCellElementValue(strViewFormNamePO,"GRID_ProductCard",0,"ProductDescription")),true,"Description Desc Does not match");
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
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}   
    	objSoftAssert.assertAll();
    	
    }
    
    
    @Test(priority=195)
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
    }
    

    
    @Test(priority=200)
    public void checkPoINConfirmation(){	    
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    if(strSiteType.equalsIgnoreCase("lima")){	
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
			objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);
	    }else{
    		throw new SkipException("skipping this test in WFL");
    	}	
		objSoftAssert.assertAll();
    
    }		    	
    
    
    @Test(priority=205)
    public void verifyStausInPOConfirmation(){	    
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	if(strSiteType.equalsIgnoreCase("lima")){
	    	if(compareTwoStringValuesToSame(strOrderNumber, objAdjunoLIMAPoManagerPOM.getTrPONumber_Multi().getText())){
	    		if(objAdjunoLIMAPoManagerPOM.getTrStatus().getText().equalsIgnoreCase("Pending")){
	    			
	    		}else{
	    			strMessage = strMessage + " Status(Pending) does not match";
	    		}
	    	}else{
	    		strMessage = strMessage + strOrderNumber+  " does not match";
	    	}	
	    	
	    	objSoftAssert.assertEquals(strMessage.equals(""),true,strMessage);
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}	
    	objSoftAssert.assertAll();	    	
    }
    
    
    @Test(priority=210)
    public void checkPriority(){	    
    	SoftAssert objSoftAssert = new SoftAssert();
    		
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
	    if(strSiteType.equalsIgnoreCase("lima")){
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
	    }else{
    		throw new SkipException("skipping this test in WFL");
    	}	
    	objSoftAssert.assertAll();	    	
    }
    
    
    @Test(priority=215)
    public void searchForPoNumberInPriority(){	    
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	String strMessage ="";
    	if(strSiteType.equalsIgnoreCase("lima")){ 
	    	if (!isNullOrBlank(strOrderNumber)) 
	    	{
	    		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clearInputField(strPriorityFormName,"PARAM_PONumber"), true,"Order number field field value is not set");
	    		objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.setFieldValue(strOrderNumber, strPriorityFormName,"PARAM_PONumber"), true,"Order number field field value is not set");
	    	}else{
	    		strMessage = strMessage + " order number is null";
	    	}
	    	
	    	
	    	objAdjunoLIMAPoConfirmationPOM = new AdjunoLIMAPoConfirmationPOM(driver);
			objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.clickChevorn(objAdjunoLIMAPoConfirmationPOM.getChvSelect()),true," Search chevron is not clicked");
    	}else{
    		throw new SkipException("skipping this test in WFL");
    	}
    	objSoftAssert.assertAll();
    	
    }*/
    	
    
/* @Test(priority=83)
    public void verifyProcessCompleteMessage(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAPoManagerPOM = new AdjunoLIMAPoManagerPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMAPoManagerPOM.isElementPresent(objAdjunoLIMAPoManagerPOM.getTxtUpdateMessage()),true,"Process is not completed");		 
	    objSoftAssert.assertAll();
    }*/
    
  
  
    
	@AfterTest
    public void closeBrowser(){
    	driver.close();
    }
    
    public boolean compareTwoStringValuesToSame(String strValue1, String strValue2)
	{
	       boolean bFlag = true;
	        if ((!(isNullOrBlank(strValue1))) &&  (!(isNullOrBlank(strValue2))))
	        {	            
	            if (strValue1.equalsIgnoreCase(strValue2)){}
	            else
	            {
	                bFlag = false;
	            }
	        }
	        else
	        {
	            bFlag = false;
	        }
	        
	        return bFlag;
	}   
}