package com.lima.test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAManifestPOM;

public class AdjunoWFLManifestTest {
	
	WebDriver driver;

    long nPage_Timeout = 0;
    String strTestURL;
    
    String strUserName;
    String strPassword;
    
    String strManifestSearchFormName;
    String strManifestPageTitle;
    String strPageTitleHome;
    String strPageTitleManifest;
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMAManifestPOM objAdjunoLIMAManifestPOM;

    String strCatalogPageTitle;

	String strCatalogFormName;

	String strSupplier;
    
	ArrayList<String> supplierList;

	ArrayList<String> vendorList;
	
	ArrayList<String> customerList;

	ArrayList<String> customerCatalogList;

    String strCustomer;
    String strVendor;
    String strModeSea;
    String strCustomerVal;
    String strStatusAny;
    String strModeAir;
    String strRefNum;
	String strFormNameManifest;
	String strEquipmentRefrigerator;
	String strCertificate;
	String strEquipmentRefrigerator2;

	String strAwating;

	String strContainer;

	String strManifest;

	 
    public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
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
            
            Node formNamePoManager = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_Manifest", dDoc, XPathConstants.NODE);
            strManifestSearchFormName = formNamePoManager.getTextContent();
            
            Node manifestPageTitle = (Node) xPath.evaluate("/config/WFL/Manifest_Page_Titile", dDoc, XPathConstants.NODE);
            strManifestPageTitle = manifestPageTitle.getTextContent();
            
            Node catalogPageTitle = (Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
            strCatalogPageTitle = catalogPageTitle.getTextContent();
            
            Node catalogFormName = (Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
            strCatalogFormName = catalogFormName.getTextContent();
            
            Node testLIMAURL = (Node) xPath.evaluate("/config/WFL/WFL_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/WFL/WFL_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/WFL/WFL_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	            
  	        Node pageTitleHome = (Node) xPath.evaluate("/config/WFL/WFL_Title_HomePage", dDoc, XPathConstants.NODE);
  	        strPageTitleHome = pageTitleHome.getTextContent();
  	            
  	        Node pageTitlePoManager = (Node) xPath.evaluate("/config/WFL/WFL_Title_PO_Manager", dDoc, XPathConstants.NODE);
	        strPageTitleManifest = pageTitlePoManager.getTextContent();
	        
	        Node formNameManifest = (Node) xPath.evaluate("/config/Generic/Form_Name_Manifest", dDoc, XPathConstants.NODE);
	        strFormNameManifest = formNameManifest.getTextContent();
  	                       
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
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getManifestXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            Node supplier = (Node) xPath1.evaluate("/manifest/Search_Param/Supplier", dDoc1, XPathConstants.NODE);
            strSupplier = supplier.getTextContent();
            
            Node customer = (Node) xPath1.evaluate("/manifest/Search_Param/Customer", dDoc1, XPathConstants.NODE);
            strCustomer = customer.getTextContent();
            
            Node modeSea = (Node) xPath1.evaluate("/manifest/Search_Param/Mode_Sea", dDoc1, XPathConstants.NODE);
            strModeSea = modeSea.getTextContent();
            
            Node vendor = (Node) xPath1.evaluate("/manifest/Search_Param/Vendor", dDoc1, XPathConstants.NODE);
            strVendor = vendor.getTextContent();
            
            Node  customerVal = (Node) xPath1.evaluate("/manifest/Search_Param/Customer_Val", dDoc1, XPathConstants.NODE);
            strCustomerVal = customerVal.getTextContent();
               
            Node  statusAny = (Node) xPath1.evaluate("/manifest/Search_Param/Status_Any", dDoc1, XPathConstants.NODE);
            strStatusAny = statusAny.getTextContent();
                
            Node  modeAir = (Node) xPath1.evaluate("/manifest/Search_Param/Mode_Air", dDoc1, XPathConstants.NODE);
            strModeAir = modeAir.getTextContent();
            
            Node container = (Node) xPath1.evaluate("/manifest/Search_Param/Container", dDoc1, XPathConstants.NODE);
            strContainer = container.getTextContent();
            
            //strRefNum
            Node  refNum = (Node) xPath1.evaluate("/manifest/Search_Param/Ref_Num", dDoc1, XPathConstants.NODE);
            strRefNum = refNum.getTextContent();
            
            Node  awaiting = (Node) xPath1.evaluate("/manifest/Search_Param/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strAwating = awaiting.getTextContent();
            
            Node  manifest = (Node) xPath1.evaluate("/manifest/Search_Param/Status_Manifest", dDoc1, XPathConstants.NODE);
            strManifest = manifest.getTextContent();
            
          
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
        objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
        
        String strTitle = objAdjunoLIMAManifestPOM.callMouseHover(strCatalogFormName,objAdjunoLIMAManifestPOM.getLnkTools(), objAdjunoLIMAManifestPOM.getLnkCatalog());
        
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
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getLnkLightHouse()),true,"Light house link is not clicked");
         wait(5000);
     
        objSoftAssert.assertAll();

    }
    
    @Test(priority=5)
    public void clearCatalogfields()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
                 
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"Item not cleared");
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"Description not cleared");
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"AssociatedItem not cleared");
        
        objSoftAssert.assertAll();
    }
    
    
    @Test(priority=10)
    public void setSupplierValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
        objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
         
        if (!isNullOrBlank(strSupplier)){
        	
        	 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"Can't clear type field");
        	 wait(2000);
 			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"Type field is not set");
 	    }else{
 	   		strMessage = strMessage + " Type is null";
 	   	}
              
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getBtnApply()), true,"not able to click apply button"); 
        wait(5000); 
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
        objSoftAssert.assertAll();
    }
    
    @Test(priority=13)
    public void getSupplierData(){
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
        supplierList = new ArrayList<String>();
        supplierList=objAdjunoLIMAManifestPOM.getCatalogData();
        
        System.out.println("size of the supplier List:"+supplierList.size());
       
        objSoftAssert.assertAll();
    }
    
    @Test(priority=15)
    public void setCustomerValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage ="";
        objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
         
        if (!isNullOrBlank(strCustomer)){
        	 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"Can't clear type field");
        	 wait(2000);
        	 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strCustomer, strCatalogFormName,"Param_Type"), true,"Type field is not set");
 	    }else{
 	   		strMessage = strMessage + " Type is null";
 	   	}
              
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getBtnApply()), true,"not able to click apply button"); 
        wait(5000); 
        objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
        objSoftAssert.assertAll();
    }
    
    @Test(priority=18)
    public void getCustomerData(){
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
        customerCatalogList = new ArrayList<String>();
        customerCatalogList=objAdjunoLIMAManifestPOM.getCatalogData();
        
        System.out.println("size of the customer List:"+customerCatalogList.size());
       
        objSoftAssert.assertAll();
    }
    
    @Test(priority=25)
    public void accessMaifest()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 String strTitle = objAdjunoLIMAManifestPOM.callMouseHover(strManifestSearchFormName,objAdjunoLIMAManifestPOM.getLnkTools(), objAdjunoLIMAManifestPOM.getLnkManifest());
         
         boolean bFlag = true;
         
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strManifestPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");
         objSoftAssert.assertAll();
    }
    
    @Test(priority=30)
    public void test_5d003_VerifyVenderDropDownData()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
        vendorList = new ArrayList<String>();
        vendorList = objAdjunoLIMAManifestPOM.getDropDownList(strManifestSearchFormName,"Param_Supplier");
        
        strMessage = objAdjunoLIMAManifestPOM.verifyCatalogData(supplierList,vendorList);
        
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,"***** Test Id:5.003-"+strMessage+" *****");
        
        objSoftAssert.assertAll();
    }
   
    
   @Test(priority=35)
    public void test_5d002_VerifyCustomerDropDownData()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	customerList = new ArrayList<String>();
    //	objAdjunoLIMAManifestPOM.getDrpDownImag().click();
    
      //  customerList = objAdjunoLIMAManifestPOM.getDropDownList(strManifestSearchFormName,"PARAM_Customer");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"***** Test Id:5.002-Can't clear Customer field *****");
    	wait(3000);
        objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getArrowCustomerDD());
        customerList=objAdjunoLIMAManifestPOM.getCustdropdownValue();
               
        strMessage = objAdjunoLIMAManifestPOM.verifyCatalogData2(customerCatalogList,customerList);
        objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,"***** Test Id:5.002-"+strMessage+ " *****");
        
        objSoftAssert.assertAll();
    }
    
   @Test(priority=45)
   public void test_5d004_VerifyVendorSearchData()
   {
   		SoftAssert objSoftAssert = new SoftAssert();
   		String strMessage = "";
   		objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	
   		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");	
   		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear vendor field");
   		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"Can't clear Customer field");
   		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear Ref num field");
   		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't status num field");
   		wait(3000);
   	
   		if (!isNullOrBlank(strVendor))
   		{		
   			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strVendor, strManifestSearchFormName,"Param_Supplier"), true,"***** Test Id:5.004- Supplier field value is not set *****");
   		}else{
   			strMessage = strMessage + " Vendor value is empty in xml";
   		}
   		
   		if (!isNullOrBlank(strModeSea))
   		{
   			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"***** Test Id:5.004-Mode field value is not set *****");
   		}else{
   			strMessage = strMessage + " Mode value is empty in xml";
   		}
   	
   		if (!isNullOrBlank(strStatusAny))
   		{
   			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAny, strManifestSearchFormName,"Param_Status"), true,"***** Test Id:5.004-status field value is not set *****");
   		}else{
   			strMessage = strMessage + " status value is empty in xml";
   		}
   	
   		
   		
   	
   		//wait(2000);
   		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvSelect()),true,"***** Test Id:5.004- Search chevron is not clicked *****");
   		wait(5000);
   	
   		strMessage = objAdjunoLIMAManifestPOM.verifyVendorSearchData(strVendor);
   	
   		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,"***** Test Id:5.004-" + strMessage + " *****");

		objSoftAssert.assertAll();

   }


        
    @Test(priority=50)
    public void test_5d006_VerifyCustomerSearchData()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"Can't clear Customer field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear vendor field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear Ref num field"); 	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't status num field");
    	wait(3000);
    	if (!isNullOrBlank(strCustomerVal)){
         	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strCustomerVal, strManifestSearchFormName,"PARAM_Customer"), true,"***** Test Id:5.006-Customer field is not set *****");
  	    }else{
  	   		strMessage = strMessage + " customer value is empty in xml";
  	   	}
    	  
    	  
    	if (!isNullOrBlank(strStatusAny)){
         	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAny, strManifestSearchFormName,"Param_Status"), true,"***** Test Id:5.006-status field is not set *****");
  	    }else{
  	   		strMessage = strMessage + " status value is empty in xml";
  	   	}
    	
    	
    	if (!isNullOrBlank(strModeSea)){
  			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"***** Test Id:5.006-Mode field is not set *****");
  	    }else{
  	   		strMessage = strMessage + " Mode value is empty in xml";
  	   	}
    	   	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestRefineSearch"),true," Refine Search is not clicked");
    	wait(5000);
    	
    	strMessage = objAdjunoLIMAManifestPOM.verifyCustomerSearchData(strCustomerVal);
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,"***** Test Id:5.006 -"+strMessage+" *****");
        
        objSoftAssert.assertAll();
    	
    }
    
    @Test(priority=55)
    public void test_5d007_VerifyEquipmetRefrigeration(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"Can't clear Customer field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear vendor field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear Ref num field");	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't status num field");
    	wait(3000);
    	
    	if (!isNullOrBlank(strModeAir)){
 			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeAir, strManifestSearchFormName,"Param_Mode"), true,"Mode field is not set");
 	    }else{
 	   		strMessage = strMessage + " Mode value is empty in xml";
 	   	}
    	
    	if (!isNullOrBlank(strStatusAny)){
         	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAny, strManifestSearchFormName,"Param_Status"), true,"***** Test Id:5.006-status field is not set *****");
  	    }else{
  	   		strMessage = strMessage + " status value is empty in xml";
  	   	}
    	
    
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestRefineSearch"),true," Refine Search is not clicked");
    	wait(5000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getEquipmentRef()),false,"Equipment Refrigeration is found");
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,"***** Test Id:5.007-"+strMessage+ " *****");
        
        objSoftAssert.assertAll();
    }

    
    @Test(priority=60)
    public void test_5d008_VerifyEquipmetRefrigeration(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"Can't clear Customer field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear vendor field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear Ref num field");	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't status num field");
    	wait(3000);
    	
    	if (!isNullOrBlank(strModeSea)){
 			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"Mode field is not set");
 	    }else{
 	   		strMessage = strMessage + " Mode value is empty in xml";
 	   	}
    	//WF-0001442
    	
    	if (!isNullOrBlank(strRefNum)){
			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strRefNum, strManifestSearchFormName,"Param_OrderNumber"), true,"WFl ref field is not set");
	    }else{
	   		strMessage = strMessage + " Refno value is empty in xml";
	   	}
    	
    	if (!isNullOrBlank(strStatusAny)){
         	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAny, strManifestSearchFormName,"Param_Status"), true,"***** Test Id:5.006-status field is not set *****");
  	    }else{
  	   		strMessage = strMessage + " status value is empty in xml";
  	   	}
    	
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestRefineSearch"),true," Refine Search is not clicked");
    	wait(5000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getEquipmentRef()),true,"Equipment Refrigeration is not found");
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,"***** Test Id:5.008-"+strMessage+ " *****");
        
        objSoftAssert.assertAll();
    }
 
    @Test(priority=65)
    public void test_5d009_VerifyQty(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getBkdCtns()),true,"***** Test Id:5.009-Bkd Ctns is not found *****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getCtns()),true,"***** Test Id:5.009- Ctns is not found *****");
    	
        objSoftAssert.assertAll();
    }
    
    
    @Test(priority=70)
    public void test_5d010_VerifyRefrigeration(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getRefrigeration()), true,"Refrigeration is not found");
    	if(objAdjunoLIMAManifestPOM.getRefrigeration().getText().equalsIgnoreCase("-22 Degrees Centigrade")){
    		
    	}else{
    		strMessage = "Refrigeration does not match";
    	}

    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""),true,strMessage);
        
        objSoftAssert.assertAll();
    }
    
    
    @Test(priority=75)
    public void selectDifferentRefrigirators(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"Can't clear Customer field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear vendor field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear Ref num field");	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't status num field");
    	wait(3000);
    	
    	if (!isNullOrBlank(strModeSea)){
 			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"Mode field is not set");
 	    }else{
 	   		strMessage = strMessage + " Mode value is empty in xml";
 	   	}
    	
    	if (!isNullOrBlank(strStatusAny)){
         	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAny, strManifestSearchFormName,"Param_Status"), true,"***** Test Id:5.006-status field is not set *****");
  	    }else{
  	   		strMessage = strMessage + " status value is empty in xml";
  	   	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestRefineSearch"),true," Refine Search is not clicked");
    	wait(5000);
    	
    	strMessage = objAdjunoLIMAManifestPOM.clickProductofDifferentRefrigiration();
    	    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvManifest()),true," Search chevron is not clicked");
    	wait(10000);
    	
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
    	objSoftAssert.assertAll();
    }
    
   @Test(priority=80)
    public void test_5d014validateEquipment(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	String strErrorMessageEquipment = objAdjunoLIMAManifestPOM.getValidationMessageGridElement(strFormNameManifest,"GridContainer",0,"Equipment");
    	if(!strErrorMessageEquipment.equals("")){
    		if(strErrorMessageEquipment.contains("&#39;")){
    			strErrorMessageEquipment = strErrorMessageEquipment.replaceAll("&#39;", "'");
        	}
    		boolean bFlag2 = strErrorMessageEquipment.equalsIgnoreCase("> This Manifest contains multiple refrigeration temperatures.  Only products of the same temperature should be manifested together.");
        	
        	objSoftAssert.assertEquals(bFlag2, true,"**** Test Id:5.014-Error Message should be shown for Eqipment is differnt *****");   	  
    	}else{
    		strMessage = strMessage + "Equipment validation message  mismatch";
    	}
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test Id:5.014-"+strMessage+" *****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority=85)
    public void test_5d015_VerifyDCField(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	
    	driver.navigate().back();
    	wait(5000);
    	strMessage = objAdjunoLIMAManifestPOM.checkDCField();
    	
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test Id:5.015-"+strMessage+" *****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority=90)
    public void test_5d017_VerifyEquipmentValidatation(){
    	
    	accessMaifest();
    	wait(4000);
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"PARAM_Customer"), true,"Can't clear Customer field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear vendor field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't status num field");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear Ref num field");	
    	wait(3000);
    	
    	if (!isNullOrBlank(strModeSea)){
 			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"Mode field is not set");
 	    }else{
 	   		strMessage = strMessage + " Mode value is empty in xml";
 	   	}
    	
    	if (!isNullOrBlank(strManifest)){
			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strManifest, strManifestSearchFormName,"Param_Status"), true,"Status field is not set");
	    }else{
	   		strMessage = strMessage + " Status value is empty in xml";
	   	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvSelect()),true," Refine Search is not clicked");
    	wait(5000);
    	   	
    	for (int i = 0; i <= objAdjunoLIMAManifestPOM.getWeEquipment2().size()-1 ; i++) {
			if(objAdjunoLIMAManifestPOM.getWeEquipment2().get(i).getText().contains("AR") && !objAdjunoLIMAManifestPOM.getWeRefrigeration2().get(i).getText().isEmpty()){
				objAdjunoLIMAManifestPOM.getSelectBox().get(i).click();
				wait(5000);
				strEquipmentRefrigerator2 = objAdjunoLIMAManifestPOM.getWeEquipmentRef().get(i).getText();
				
				System.out.println("strEquipmentRefrigerator2: "+strEquipmentRefrigerator2);
				
				strCertificate = objAdjunoLIMAManifestPOM.getWeCertificate().get(i).getText();
				
				System.out.println("strCertificate: "+strCertificate);
				
				objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvManifest()),true," Search chevron is not clicked");
		    	wait(10000);
				break;
			}else{
				if(i==objAdjunoLIMAManifestPOM.getWeEquipment2().size()-1){
					strMessage = "Wont find an Equipment with AR ";
				}
			}
		}
    	
    	
    	strEquipmentRefrigerator = objAdjunoLIMAManifestPOM.getGridCellElementValue(strFormNameManifest, "GridContainer", 0, "EquipmentRefrigeration");
    	
    	System.out.println("strEquipmentRefrigerator: "+strEquipmentRefrigerator);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"EquipmentRefrigeration",""), true,"Equipment Refrigerator field is not set");
    	wait(3000);
    	String strErrorMessageEquipment = objAdjunoLIMAManifestPOM.getValidationMessageGridElement(strFormNameManifest,"GridContainer",0,"EquipmentRefrigeration");
    	if(!strErrorMessageEquipment.equals("")){
    		if(strErrorMessageEquipment.contains("&#39;")){
    			strErrorMessageEquipment = strErrorMessageEquipment.replaceAll("&#39;", "'").replaceAll("&gt;", ">");
        	}
   
    		boolean bFlag2 = strErrorMessageEquipment.equalsIgnoreCase("> 'Equipment Refrigeration' is a required field");
        	
        	objSoftAssert.assertEquals(bFlag2, true,"Error Message should be shown for Eqipment is differnt");   	  
    	}else{
    		strMessage = strMessage + "Equipment error message is null";
    	}
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id:5.017"+ strMessage+" *****");
    	objSoftAssert.assertAll();

    }
    
    
    @Test(priority=95)
    public void test_5d018_EquipmentValidation2(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"EquipmentRefrigeration","+24 Degrees Centigrade"), true,"Equipment Refrigerator field is not set");
    	wait(3000);
    	String strErrorMessageEquipment = objAdjunoLIMAManifestPOM.getValidationMessageGridElement(strFormNameManifest,"GridContainer",0,"EquipmentRefrigeration");
    	if(!strErrorMessageEquipment.equals("")){
    		if(strErrorMessageEquipment.contains("&#39;")){
    			strErrorMessageEquipment = strErrorMessageEquipment.replaceAll("&#39;", "'").replaceAll("&gt;", ">");
        	}
    		boolean bFlag2 = strErrorMessageEquipment.equalsIgnoreCase("> The Equipment refrigeration level does not match the product refrigeration level");
        	
        	objSoftAssert.assertEquals(bFlag2, true,"***** Test Id:5.017-Error Message should be shown for Eqipment is differnt *****");   	  
    	}else{
    		strMessage = strMessage + "Equipment error message is null";
    	}
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test Id:5.018"+ strMessage+" *****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority=100)
    public void test_5d019_setEquipmentRefrigerationPrivousValue(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"EquipmentRefrigeration",strEquipmentRefrigerator), true,"***** Test Id:5.018 -EquipmentRefrigerator field value is not set *****");
    	wait(3000);
    	String strErrorMessageEquipment = objAdjunoLIMAManifestPOM.getValidationMessageGridElement(strFormNameManifest,"GridContainer",0,"EquipmentRefrigeration");
    	if(strErrorMessageEquipment.equals("")){
    		
    	}else{
    		strMessage = strMessage +strErrorMessageEquipment;
    	}
    	
    	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test Id:5.019-"+strMessage+" *****");
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=105)
    public void test_5d020_VerifyRefrigerationIsReadOnlyInGrid(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	//String strMessage = "";
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	boolean bFlag = objAdjunoLIMAManifestPOM.checkFieldIsReadOnlyInGrid(strFormNameManifest,"GridProducts",0,"ProductRefrigeration");
    	
    	objSoftAssert.assertEquals(bFlag, false, "Refrigeration is user Enterable");
    	//objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, strMessage);
    	objSoftAssert.assertAll();
    
    }
    
    
    @Test(priority=110)
    public void test_5d023_verifyEquipmentRefrigerationValue(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	strEquipmentRefrigerator = strEquipmentRefrigerator.replaceAll("[^\\d.]", "");
    	strEquipmentRefrigerator2 = strEquipmentRefrigerator2 .replaceAll("[^\\d.]", "");
    	System.out.println("strEquipmentRefrigerator"+strEquipmentRefrigerator+ "   strEquipmentRefrigerator2"+strEquipmentRefrigerator2);
    	objSoftAssert.assertEquals(strEquipmentRefrigerator.equalsIgnoreCase(strEquipmentRefrigerator2),true,"Equipment Refrigeration does not match");
    	
    	//objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"Container",strContainer),true,"***** Test Id:5.023 -Container field value is not set *****");
    	objSoftAssert.assertAll();
    
    }
    
    
    @Test(priority=115)
    public void test_5d021_verifyCerificate(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	WebElement we = objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",0,"CertInd");
    	String strCerti = we.getText();
    	System.out.println("CertInd:"+strCerti);
    	if(strCertificate.equalsIgnoreCase("yes")){
    		strCertificate = "Y";
    	}else if(strCertificate.equalsIgnoreCase("no")){
    		strCertificate = "N";
    	}
    	objSoftAssert.assertEquals(strCertificate.equalsIgnoreCase(strCerti),true,"***** Test Id:5.021- Certificate does not match *****");
    	  	
    	
    	
    	objSoftAssert.assertAll();
    
    }
    
    @Test(priority=118)
    public void test_5d025_ClickReview(){
 	   
 	   SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
 	 //  setProductGridField();
 	   	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvReview2()),true,"***** Test Id:5.025-Review chevron is not clicked *****");
 	   wait(5000);
 		
 	   objSoftAssert.assertAll();	   
    }
    	
    
    @Test(priority=130)
    public void test_5d026_ClickComplete(){
 	   SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
 	   	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvComplete2()),true,"***** Test Id:5.025- Complete chevron is not clicked *****");
 	   wait(5000);
 		
 	   objSoftAssert.assertAll();
    }
    
    
    @AfterTest
    public void closeBrwser(){
    	driver.close();
    }
    
}
