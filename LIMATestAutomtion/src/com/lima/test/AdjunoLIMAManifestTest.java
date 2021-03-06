package com.lima.test;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.Manifest;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoLIMAManifestPOM;

public class AdjunoLIMAManifestTest {
	
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
    Manifest searchManifest;
        
	String strSupplier;
	String strCustomer;
	String strVendor;
	String strCustomerVal;
	String strModeSea;
	String strStatusAny;
	String strModeAir;
	String strFormNameManifest;
	String strInvalidPoNumber;
	String strStatusAwaiting;
	String strValidPoNumber;
	NodeList productCaptions;
	NodeList containerCaptions;
	NodeList productDeatailCaptions;
	String strQty;
	String strCube;
	String strCartons;
	String strGrossWeight;
	String strNetWeight;
	String strPackType;
	String strProductFreightTerms;
	String strProductPallets;
	String strHouseBill;
	String strDocType;
	String strContainer;
	String strSeal;
	String strLoading;
	String strEquipment;
	String strVGM;
	String strKPI;
	String strformNameManifestReview;
	String strTrackNO;
	String strPOStatusFormName;
	long nRow;
	ArrayList<Manifest> manifestList;
	String strStatusManifest;
	Manifest searchManifestValue;
	String strFormNameManifestCancel;
	long nRow2;
	String strTrackNO2;
	String strDailogMessage;
	String strMailId;
	String strFormNameComplete;
        
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
            
            Node manifestPageTitle = (Node) xPath.evaluate("/config/LIMA/Manifest_Page_Titile", dDoc, XPathConstants.NODE);
            strManifestPageTitle = manifestPageTitle.getTextContent();
         /*   
            Node catalogPageTitle = (Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
            strCatalogPageTitle = catalogPageTitle.getTextContent();
            
            Node catalogFormName = (Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
            strCatalogFormName = catalogFormName.getTextContent();*/
            
            Node testLIMAURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node POStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
  	        strPOStatusFormName = POStatusFormName.getTextContent();
          
  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	            
  	        Node pageTitleHome = (Node) xPath.evaluate("/config/LIMA/Page_Title_HomePage", dDoc, XPathConstants.NODE);
  	        strPageTitleHome = pageTitleHome.getTextContent();
  	         
	        
	        Node formNameManifest = (Node) xPath.evaluate("/config/Generic/Form_Name_Manifest", dDoc, XPathConstants.NODE);
	        strFormNameManifest = formNameManifest.getTextContent();
	        //
	              
	        Node formNameComplete = (Node) xPath.evaluate("/config/LIMA/Complete_Form_Name", dDoc, XPathConstants.NODE);
	        strFormNameComplete = formNameComplete.getTextContent();

	        Node formNameManifestReview = (Node) xPath.evaluate("/config/Generic/Form_Name_Manifest_Review", dDoc, XPathConstants.NODE);
	        strformNameManifestReview = formNameManifestReview.getTextContent();
	        
	        Node formNameManifestCancel = (Node) xPath.evaluate("/config/Generic/Form_Name_Manifest_Cancel", dDoc, XPathConstants.NODE);
            strFormNameManifestCancel = formNameManifestCancel.getTextContent();
	        
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
            
            Node vendor = (Node) xPath1.evaluate("/manifest/Search_Param/Vendor", dDoc1, XPathConstants.NODE);
            strVendor = vendor.getTextContent();
            
            Node  customerVal = (Node) xPath1.evaluate("/manifest/Search_Param/Customer_Val", dDoc1, XPathConstants.NODE);
            strCustomerVal = customerVal.getTextContent();
            
            Node modeSea = (Node) xPath1.evaluate("/manifest/Search_Param/Mode_Sea", dDoc1, XPathConstants.NODE);
            strModeSea = modeSea.getTextContent();
            
            Node  statusAwiting = (Node) xPath1.evaluate("/manifest/Search_Param/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strStatusAwaiting = statusAwiting.getTextContent();
         
            
            Node  statusAny = (Node) xPath1.evaluate("/manifest/Search_Param/Status_Any", dDoc1, XPathConstants.NODE);
            strStatusAny = statusAny.getTextContent();
                
            Node  modeAir = (Node) xPath1.evaluate("/manifest/Search_Param/Mode_Air", dDoc1, XPathConstants.NODE);
            strModeAir = modeAir.getTextContent();
            
            Node  invalidPoNum = (Node) xPath1.evaluate("/manifest/Search_Param/Invalid_Po", dDoc1, XPathConstants.NODE);
            strInvalidPoNumber = invalidPoNum.getTextContent();
            
            Node  validPoNum = (Node) xPath1.evaluate("/manifest/Search_Param/Valid_Po", dDoc1, XPathConstants.NODE);
            strValidPoNumber = validPoNum.getTextContent();
            
            Node  qty = (Node) xPath1.evaluate("/manifest/Search_Param/Quantity", dDoc1, XPathConstants.NODE);
            strQty = qty.getTextContent();
                        
            Node  cube = (Node) xPath1.evaluate("/manifest/Search_Param/Cube", dDoc1, XPathConstants.NODE);
            strCube = cube.getTextContent();
            
            Node cartons = (Node) xPath1.evaluate("/manifest/Search_Param/Cartons", dDoc1, XPathConstants.NODE);
            strCartons = cartons.getTextContent();
            
            Node netWeight = (Node) xPath1.evaluate("/manifest/Search_Param/NetWeight", dDoc1, XPathConstants.NODE);
            strNetWeight = netWeight.getTextContent();
            
            Node grWeight = (Node) xPath1.evaluate("/manifest/Search_Param/GrWeight", dDoc1, XPathConstants.NODE);
            strGrossWeight = grWeight.getTextContent();
            
            Node packtype = (Node) xPath1.evaluate("/manifest/Search_Param/PackType", dDoc1, XPathConstants.NODE);
            strPackType = packtype.getTextContent();
            
            Node freightTerms = (Node) xPath1.evaluate("/manifest/Search_Param/FreightTerms", dDoc1, XPathConstants.NODE);
            strProductFreightTerms = freightTerms.getTextContent();
            
            Node productPallets = (Node) xPath1.evaluate("/manifest/Search_Param/ProductPallet", dDoc1, XPathConstants.NODE);
            strProductPallets = productPallets.getTextContent();
            
            Node houseBill = (Node) xPath1.evaluate("/manifest/Search_Param/HouseBill", dDoc1, XPathConstants.NODE);
            strHouseBill = houseBill.getTextContent();
            
            Node docType = (Node) xPath1.evaluate("/manifest/Search_Param/DocType", dDoc1, XPathConstants.NODE);
            strDocType = docType.getTextContent();
            
            Node container = (Node) xPath1.evaluate("/manifest/Search_Param/Container", dDoc1, XPathConstants.NODE);
            strContainer = container.getTextContent();
            
            Node seal = (Node) xPath1.evaluate("/manifest/Search_Param/Seal", dDoc1, XPathConstants.NODE);
            strSeal = seal.getTextContent();
            
            Node loading = (Node) xPath1.evaluate("/manifest/Search_Param/Loading", dDoc1, XPathConstants.NODE);
            strLoading = loading.getTextContent();
            
            Node equipment = (Node) xPath1.evaluate("/manifest/Search_Param/Equipment", dDoc1, XPathConstants.NODE);
            strEquipment = equipment.getTextContent();
            
            Node vgm = (Node) xPath1.evaluate("/manifest/Search_Param/VGM", dDoc1, XPathConstants.NODE);
            strVGM = vgm.getTextContent();
            
            Node kpi = (Node) xPath1.evaluate("/manifest/Search_Param/KPI", dDoc1, XPathConstants.NODE);
            strKPI = kpi.getTextContent();
            
            Node statusManifest = (Node) xPath1.evaluate("/manifest/Search_Param/Status_Manifest", dDoc1, XPathConstants.NODE);
            strStatusManifest = statusManifest.getTextContent();
            //
            
            Node dailogMessage = (Node) xPath1.evaluate("/manifest/Dailog_Value/Message", dDoc1, XPathConstants.NODE);
            strDailogMessage = dailogMessage.getTextContent();
            
            Node  mailId= (Node) xPath1.evaluate("/manifest/Dailog_Value/email", dDoc1, XPathConstants.NODE);
            strMailId = mailId.getTextContent();
            
            
            productCaptions = (NodeList) xPath1.evaluate("/manifest/Product_Captions", dDoc1, XPathConstants.NODESET);
            
            productDeatailCaptions = (NodeList) xPath1.evaluate("/manifest/Product_Detail_Captions", dDoc1, XPathConstants.NODESET);
            containerCaptions = (NodeList) xPath1.evaluate("/manifest/Container_Captions", dDoc1, XPathConstants.NODESET);
          
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
    public void test_accessManifest()
    {
    	 SoftAssert objSoftAssert = new  SoftAssert();
    	 objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
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
         try{
             wait(3000);
             objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getBtnOK1());
             wait(3000);
             }
             catch(NoSuchElementException e)
             {
             	
             }
         objSoftAssert.assertAll();
    }
   	    
   	    
    @Test(priority=10)
   	public void test_1_VerifyExixtanceOFChevrons()
    {    	
       	SoftAssert objSoftAssert = new SoftAssert();
       	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
           
       	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesChevronExist(objAdjunoLIMAManifestPOM.getChvSearch()), true,"***** Test Id:2 - search Chevorn not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesChevronExist(objAdjunoLIMAManifestPOM.getChvSelect()), true,"***** Test Id:2 - Edit Chevorn not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesChevronExist(objAdjunoLIMAManifestPOM.getChvComplete()), true,"***** Test Id :2 - Complete Chevorn not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesChevronExist(objAdjunoLIMAManifestPOM.getChvReview()), true," ***** Test Id :2- Review Chevorn not found *****");
        objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesChevronExist(objAdjunoLIMAManifestPOM.getChvManifest2()), true,"***** Test Id :2- Manofest Chevorn not found *****");
        
           
        objSoftAssert.assertAll();
   }

   	    
    @Test(priority=15)
   	public void test_3_VerifyExistanceOFFields()
   	{
   	    SoftAssert objSoftAssert = new SoftAssert();
   	    objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_OriginPort"), true,"***** Test Id :3 - OriginPort field is not found *****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_DestinationPort"), true,"***** Test Id :3 - DestinationPort field is not found *****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Mode"), true,"***** Test Id :3 - Mode  field is not found *****");
   	 
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Carrier"), true,"***** Test Id :3 - Carrier field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Vessel"), true,"***** Test Id :3 - Vessel field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_ETDFrom"), true,"***** Test Id :3 - ETD From field is not found *****");
	 
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_ETDTo"), true,"***** Test ID:3- ETD To field is not found *****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Status"), true,"***** Test Id :3 - Status field is not found *****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Container"), true,"***** Test Id :3 - Container field is not found *****");
   	 
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Housebill"), true,"***** Test Id :3 - Housebill field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Supplier"), true,"***** Test Id :3 - Supplier field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_LoadRef"), true,"***** Test Id :3 - LoadRef field is not found *****");
	 
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_CustomerReference"), true,"***** Test Id :3 - CustomerReference field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_OrderNumber"), true,"***** Test Id :3 - OrderNumber field is not found *****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"Param_Product"), true,"***** Test Id :3 - Product field is not found *****");
   	 	
   	 	objSoftAssert.assertAll();
   	}
    
    
    @Test(priority =18)
    public void clearFields(){
    	SoftAssert objSoftAssert = new SoftAssert();
     	   
     	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OriginPort"), true,"Can't clear OriginPort field");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_DestinationPort"), true,"Can't clear DestinationPort field");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Mode"), true,"Can't clear Mode field");
   	 
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Carrier"), true,"Can't clear Carrier field");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Vessel"), true,"Can't clear Vessel field ");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_ETDFrom"), true,"Can't clear ETDFrom field");
	 
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_ETDTo"), true,"Can't clear ETDTo field ");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Status"), true,"Can't clear Status field ");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Container"), true,"Can't clear Container field ");
   	 
   	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Housebill"), true,"Can't clear Housebill field ");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Supplier"), true,"Can't clear Supplier field ");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_LoadRef"), true,"Can't clearLoadRef field ");
	 
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_CustomerReference"), true,"Can't clear CustomerReference field ");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_OrderNumber"), true,"Can't clear OrderNumber field ");
	 	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField(strManifestSearchFormName,"Param_Product"), true,"Can't clear Product field");
   	 	

   	 	objSoftAssert.assertAll();
    }
    
    
   @Test(priority=20)
   	public void test_4_SetAwaitingAndInvalidPo()
   	{
   	    SoftAssert objSoftAssert = new SoftAssert();
   	    String strMessage = "";
   	    objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
 	
   	    clearFields();
	 	wait(3000);
   	    if (!isNullOrBlank(strInvalidPoNumber)){
   	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strInvalidPoNumber, strManifestSearchFormName,"Param_OrderNumber"), true,"***** Test Id :4 - po num field is not set *****");
	    }else{
	   		strMessage = strMessage + " invalid Po number is empty in xml";
	   	}
 	  
   	    if (!isNullOrBlank(strStatusAwaiting)){
   	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAwaiting, strManifestSearchFormName,"Param_Status"), true,"***** Test Id :4 - status field is not set *****");
	    }else{
	   		strMessage = strMessage + " status field  is empty in xml";
	   	}
   	    
   	    if (!isNullOrBlank(strModeSea)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"***** Test Id :4 - Mode field is not set *****");
	    }else{
	   		strMessage = strMessage + " MOde field is empty in xml";
	   	}
   	    
   	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvSelect()),true,"***** Test Id :4 - Search chevron is not clicked *****");
		wait(5000);
		
		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getNoItem()),true, "***** Test Id :4 - Result items are found *****");
		
   	    objSoftAssert.assertAll();
   	}    
    
    
    @Test(priority=25)
   	public void test_5_SetAwaitingAndValidPo()
   	{
   	    SoftAssert objSoftAssert = new SoftAssert();
   	    String strMessage = "";
   	    objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
 	
   	    clearFields();
	 	wait(3000);
   	    if (!isNullOrBlank(strValidPoNumber)){
   	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strValidPoNumber, strManifestSearchFormName,"Param_OrderNumber"), true,"***** Test Id: 5 - po num field value is not set *****");
	    }else{
	   		strMessage = strMessage + " invalid Po number is empty in xml";
	   	}
 	  
 	  
   	    if (!isNullOrBlank(strStatusAwaiting)){
   	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAwaiting, strManifestSearchFormName,"Param_Status"), true,"***** Test Id: 5 - status field value is not set *****");
	    }else{
	   		strMessage = strMessage + " status value is empty in xml";
	   	}
   	    
   	    if (!isNullOrBlank(strModeSea)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"**** Test Id: 5 - Mode field value is not set *****");
	    }else{
	   		strMessage = strMessage + " MOde value is empty in xml";
	   	}
   	    
   	    
   	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestRefineSearch"),true,"**** Test Id: 5 - Refine Search is not clicked*****");
   	    wait(5000);
 	
		
		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getNoItem()),false, "Result items are not found");
		
   	    objSoftAssert.assertAll();
   	}
    
    @Test(priority=28)
   	public void test_setAwaiting()
   	{
   	    SoftAssert objSoftAssert = new SoftAssert();
   	    String strMessage = "";
   	    objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
 	
   	 	clearFields();
	 	wait(3000);
   	   
 	  
   	    if (!isNullOrBlank(strStatusAwaiting)){
   	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusAwaiting, strManifestSearchFormName,"Param_Status"), true,"status field is not set");
	    }else{
	   		strMessage = strMessage + " status value is empaty in xml";
	   	}
   	    
   	    if (!isNullOrBlank(strModeSea)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"Mode field is not set");
	    }else{
	   		strMessage = strMessage + " MOde value is empaty in xml";
	   	}
   	    
   	    
   	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestRefineSearch"),true," Refine Search is not clicked");
   	    wait(5000);
 	
		
		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getNoItem()),false, "Result items are not found");
		
   	    objSoftAssert.assertAll();
   	}
    
    
    @Test(priority = 30)
    public void test_6_VerifyElementsInSelectChevron()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThOriginPort()), true, "***** Test Id: 6- Origin Port is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThVessel()), true, "***** Test Id: 6- Vessel is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThCarrier()), true, "***** Test Id: 6- Carrier is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThETDDate()), true, "***** Test Id: 6- ETD Date is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThETADate()), true, "***** Test Id: 6- ETA Date is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThCarrier()), true, "***** Test Id: 6- Carrier is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThLoading()), true, "***** Test Id: 6- Loading is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThLoadRef()), true, "***** Test Id: 6- Loading ref is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThContainer()), true, "***** Test Id: 6- Conatiner is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThEquipment()), true, "***** Test Id: 6- Equipment is not displayed *****");
    	
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThVendor()), true, "***** Test Id: 6- Vedor is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThFreightTerms()), true, "***** Test Id: 6- FreightTerms is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThPONumber()), true, "***** Test Id: 6- PO Number is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThProduct()), true, "***** Test Id: 6- Product is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThPackType()), true, "***** Test Id: 6- Pack Type is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThDC()), true, "***** Test Id: 6- DC is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThDailog()), true, "***** Test Id: 6- Dailog is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThHBL()), true, "***** Test Id: 6- HBL is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThIdentifier()), true, "***** Test Id: 6- Identifier is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThCustomerRef()), true, "***** Test Id: 6- Customer ref is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThBkdQty()), true, "***** Test Id: 6- BKD Qty is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThQty()), true, "***** Test Id: 6- Qty is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThCtns()), true, "***** Test Id: 6- Ctns is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThCBM()), true, "***** Test Id: 6- CBM is not displayed *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getThmanifestStatus()), true, "***** Test Id: 6- Manifest status is not displayed *****");
    	
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority = 35)
    public void test_10_clickOnPo()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getLnkProduct()),true,"***** Test Id: 10 - po number not cliked *****");
    	
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority = 40)
    public void test_11_ClickOnManifestWithOutSelectingProduct()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	driver.navigate().back();
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvManifest()),true,"***** Test Id:11- manifest not cliked *****");
    	wait(3000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getWarningMessage().getText(), "You must make selection(s) before progressing","***** Test Id:11- Warning message is not shown *****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 45)
    public void test_12_ClickOnManifestWithSelectingProduct()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	
    	for (int i = 0; i <= objAdjunoLIMAManifestPOM.getSelectBox().size()-1; i++) {
    		
			
    			if(objAdjunoLIMAManifestPOM.getLstDialog().get(i).getText().equalsIgnoreCase("0")){
    				objAdjunoLIMAManifestPOM.getSelectBox().get(i).click();
    				wait(2000);
        	    	
    				searchManifest = new Manifest(objAdjunoLIMAManifestPOM.getTrOriginPort().get(i).getText(), objAdjunoLIMAManifestPOM.getTrVessel().get(i).getText(), objAdjunoLIMAManifestPOM.getTrETDDate().get(i).getText(), objAdjunoLIMAManifestPOM.getTrETADate().get(i).getText(), objAdjunoLIMAManifestPOM.getTrCarrier().get(i).getText(), objAdjunoLIMAManifestPOM.getTrLoading().get(i).getText(), objAdjunoLIMAManifestPOM.getTrLoadRef().get(i).getText(), objAdjunoLIMAManifestPOM.getTrContainer().get(i).getText(), objAdjunoLIMAManifestPOM.getTrEquipment().get(i).getText(), objAdjunoLIMAManifestPOM.getTrVendor().get(i).getText(), objAdjunoLIMAManifestPOM.getTrHBL().get(i).getText(), objAdjunoLIMAManifestPOM.getTrPONumber().get(i).getText(), objAdjunoLIMAManifestPOM.getTrProduct().get(i).getText(), objAdjunoLIMAManifestPOM.getTrIdentifier().get(i).getText(), objAdjunoLIMAManifestPOM.getTrCustomerRef().get(i).getText(),objAdjunoLIMAManifestPOM.getTrBkdQty().get(i).getText(), objAdjunoLIMAManifestPOM.getTrQty().get(i).getText(), objAdjunoLIMAManifestPOM.getTrCtns().get(i).getText(), objAdjunoLIMAManifestPOM.getTrCBM().get(i).getText(), objAdjunoLIMAManifestPOM.getTrPackType().get(i).getText(), objAdjunoLIMAManifestPOM.getTrFreightTerms().get(i).getText(), objAdjunoLIMAManifestPOM.getTrDC().get(i).getText(), objAdjunoLIMAManifestPOM.getTrmanifestStatus().get(i).getText(), objAdjunoLIMAManifestPOM.getTrDailog().get(i).getText());
    			
    				break;
    			}else{
        			System.out.println("dsdsd");
        		}
			
		}
    	
    	
    
  //  	System.out.println("this is my product:"+objAdjunoLIMAManifestPOM.getTrProduct().getText());
    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvManifest()),true,"manifest not cliked");
    	wait(3000);
    	
    	objSoftAssert.assertAll();
    	
    }
    
    
    @Test(priority=48)
    public void test_getGridRow(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	nRow = objAdjunoLIMAManifestPOM.getNoOrRowsinGrid(strFormNameManifest,"GridProducts");
    	System.out.println("row 1:"+nRow);
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority = 50)
    public void test_getManifestGridData()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	manifestList = new ArrayList<Manifest>();
    	for (int i = 0; i <= nRow-1; i++) {
    		
    		Manifest chvManifest = new Manifest(objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"Supplier").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"OrderNumber").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"SKU").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"ProductIdentifier").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"BookedQuantity").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"ShippedQuantity").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"Cartons").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"Cube").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifest,"GridProducts",i,"PackType").getText());
        	manifestList.add(chvManifest);
		}
    
    	objSoftAssert.assertAll();
    }
    
  /*  @Test(priority = 55)
    public void compareManifest()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
    	String strbkdqty;
    	String strqty;
    	String strCtn;
    	String strCbm;
    	int cbm =0;
    	int ctn =0;
    	int qty =0;
    	int bkdqty = 0;
    	for (int i = 0; i <= manifestList.size()-1; i++) {
    		
    		
    		objSoftAssert.assertEquals(searchManifest.getVendor().equalsIgnoreCase(manifestList.get(i).getVendor()),true,"Vendor does not match");
    		
        	objSoftAssert.assertEquals(searchManifest.getPoNumber().equalsIgnoreCase(manifestList.get(i).getPoNumber()),true,"po Number does not match");
        
        	objSoftAssert.assertEquals(searchManifest.getProduct().equalsIgnoreCase(manifestList.get(i).getProduct()),true," product does not match");
        	
    		objSoftAssert.assertEquals(searchManifest.getIdentifier().equalsIgnoreCase(manifestList.get(i).getIdentifier()),true,"identifier does not match");
    		
    		
    		objSoftAssert.assertEquals(searchManifest.getPackType().equalsIgnoreCase(manifestList.get(i).getPackType()),true,"Pack type does not match");
    		
    		strbkdqty = manifestList.get(i).getBkdQty();
        	bkdqty = bkdqty+objAdjunoLIMAManifestPOM.StringToInt(strbkdqty);
        	
        	strqty = manifestList.get(i).getQty();
        	qty = qty+objAdjunoLIMAManifestPOM.StringToInt(strqty);
        	
        	strCtn = manifestList.get(i).getCtns();
        	ctn = ctn+objAdjunoLIMAManifestPOM.StringToInt(strCtn);
        	
        	strCbm = manifestList.get(i).getCbm();
        	cbm = cbm+objAdjunoLIMAManifestPOM.StringToInt(strCbm);
		}
    	
    	strbkdqty = objAdjunoLIMAManifestPOM.IntToString(bkdqty);
    	strqty = objAdjunoLIMAManifestPOM.IntToString(qty);
    	strCtn = objAdjunoLIMAManifestPOM.IntToString(ctn);	
    	strCbm = objAdjunoLIMAManifestPOM.IntToString(cbm);
    	
    	System.out.println("final cbm:"+cbm);
    	System.out.println("final strbkdQty:"+bkdqty);
    	System.out.println("final Qty:"+qty);
    	System.out.println("final ctn:"+ctn);
    	
    	objSoftAssert.assertEquals(searchManifest.getBkdQty().contains(strbkdqty),true," Bkd Qty does not match");
    	
    	objSoftAssert.assertEquals(searchManifest.getQty().equalsIgnoreCase(strqty),true,"qty does not match");
    	
    	objSoftAssert.assertEquals(searchManifest.getCtns().equalsIgnoreCase(strCtn),true,"ctns does not match");
    	
    	objSoftAssert.assertEquals(searchManifest.getCbm().equalsIgnoreCase(strCbm),true,"ctns does not match");
    	
   	    	
    	objSoftAssert.assertAll();
    }*/
    
    @Test(priority = 60)
    public void test_13_VerifyExistanceOfGrid()
    {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	   
   	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameManifest,"GridProducts"),true,"***** Test Id: 13 - GridProducts does not exist *****");
   	   
   	   
   	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameManifest,"GridExistingProducts"),true,"***** Test Id: 13 - GridExistingProducts does not exist *****");
 	   
   	 
   	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameManifest,"GridContainer"),true,"***** Test Id: 13 - GridContainer does not exist *****");
 	   
	   objSoftAssert.assertAll();
    }
    
   
   @Test(priority = 65)
   public void test_14_VerifyGridContainerCaptions()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	   String strMessage="";
  	   ArrayList<String> list = new ArrayList<String>();
		    		    	
  	   list = objAdjunoLIMAManifestPOM.getCaptionsList(strFormNameManifest,"GridContainer");		    	    	
  	   
  	   strMessage = objAdjunoLIMAManifestPOM.verifyCaptionsONGrid(list,containerCaptions,9);
		    	
				
	   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id:14 -"+strMessage+ " *****");
	   objSoftAssert.assertAll();
   }
   
   
   @Test(priority = 68)
   public void test_16_VerifyGridProductCaption()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	   String strMessage="";
  	   ArrayList<String> list = new ArrayList<String>();
		    		    	
  	   list = objAdjunoLIMAManifestPOM.getCaptionsList(strFormNameManifest,"GridProducts");		    	    	
  	  
  	   strMessage = objAdjunoLIMAManifestPOM.verifyCaptionsONGrid(list,productCaptions,28);
		    	
				
	   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id:16 -"+strMessage+ " *****");
	   objSoftAssert.assertAll();
   }
   
   
   @Test(priority = 70)
   public void test_15_ContainerGridField()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"Container",strContainer),true,"***** Test Id:15 - Container field value is not set *****");
  	   
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"Seal",strSeal),true,"***** Test Id:15 - Seal field value is not set *****");
  	 
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"Loading",strLoading),true,"***** Test Id:15 - Loading field value is not set *****");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"Equipment",strEquipment),true,"***** Test Id:15 - Equipment field value is not set *****");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"VGM",strVGM),true,"***** Test Id:15 - VGM field value is not set");
  //   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"VGM",strPackType),true,"pack type is not set");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"ManifestKPI",strKPI),true,"***** Test Id:15 - KPI field value is not set *****");
  //   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridContainer",0,"ProductPallets",strProductPallets),true,"Pallet is not set");
  	   
  	   
  	   wait(5000);
  	
  	   objSoftAssert.assertAll();
   } 
   
   @Test(priority = 75)
   public void test_setProductGridField()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	   
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"PackType",strPackType),true,"pack type is not set");
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"ProductFreightTerms",strProductFreightTerms),true,"FreightTerms is not set");
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"ProductPallets",strProductPallets),true,"Pallet is not set");
	   
	   
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"DocType",strDocType),true,"Doc type is not set");
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"HouseBill",strHouseBill),true,"House bill is not set");
	 
	   
	   try{
		   if(objAdjunoLIMAManifestPOM.getProductLink().isDisplayed()){
			   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getProductLink()), true,"Product Link is not clicked");
			   wait(2000);
		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUShippedQuantity",strQty),true,"qty is not set");
		  	   
		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUCube",strCube),true,"qty is not set");
		  	 
		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUCartons",strCartons),true,"cartons is not set");
		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUGrossWeight",strGrossWeight),true,"Gross weight is not set");
		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUNetWeight",strNetWeight),true,"net Weight is not set");
		  	  
		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strFormNameManifest, "btnApply"), true,"apply button not clicked");
		   }      
		   }
		catch(Exception ex){
			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"ShippedQuantity",strQty),true,"qty is not set");
			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"Cube",strCube),true,"qty is not set");
			  	 
		  	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"Cartons",strCartons),true,"cartons is not set");
		  	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"GrossWeight",strGrossWeight),true,"Gross weight is not set");
		  	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"NetWeight",strNetWeight),true,"net Weight is not set");
		  	 
		   
		
		 
	   }
	  
  	   objSoftAssert.assertAll();
  	   
   } 
    
   @Test(priority = 85)
   public void test_22_to_27_ValidateContainer()
   {
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   String strMessage ="";
  	   
  	   String strErrorCBMBelowMinimum = objAdjunoLIMAManifestPOM.getValidationMessageFieldElement(strFormNameManifest,"CBMBelowMinimum");
	   	if(!strErrorCBMBelowMinimum.equals("")){
	   		if(strErrorCBMBelowMinimum.contains("&#39;")){
	   			strErrorCBMBelowMinimum = strErrorCBMBelowMinimum.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorCBMBelowMinimum.equalsIgnoreCase("> Total Cube is Below Minimum Fill");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:22 - The Mandatory field error message mismatch *****");	   			
	   	}else{
	   		strMessage = strMessage + "CBMBelowMinimum field error message is empty ";
	   	}
	   	
	   	String strErrorCBMBelowOptimum = objAdjunoLIMAManifestPOM.getValidationMessageFieldElement(strFormNameManifest,"CBMBelowOptimum");
	   	if(!strErrorCBMBelowOptimum.equals("")){
	   		if(strErrorCBMBelowOptimum.contains("&#39;")){
	   			strErrorCBMBelowOptimum = strErrorCBMBelowOptimum.replaceAll("&#39;", "'");
		   	}
	   		boolean bFlag = strErrorCBMBelowOptimum.equalsIgnoreCase("> Total Cube is Below Optimum Fill");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:22 - The Mandatory field error message mismatch *****");	   			
	   	}else{
	   		strMessage = strMessage + "CBMBelowOptimum field error message is empty ";
	   	}
	   	
	   	//above min and below max
	   	
	   	int cubeVal = objAdjunoLIMAManifestPOM.StringToInt(strCube);
	   	cubeVal = cubeVal+20;
	   	
	   	String strCube1 = objAdjunoLIMAManifestPOM.IntToString(cubeVal);
	   	
	   	try{
	    if(objAdjunoLIMAManifestPOM.getProductLink().isDisplayed()){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getProductLink()), true,"Product Link is not clicked");
	 	   	wait(2000);
	  	   
	 	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUCube",strCube1),true,"qty is not set");
	 	  
	 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strFormNameManifest, "btnApply"), true,"apply button not clicked");
	 	   wait(2000);
	    }
	    }
	    catch(Exception ex){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"Cube",strCube1),true,"qty is not set");
	    }
	  
	   	String strErrorAboveMin = objAdjunoLIMAManifestPOM.getValidationMessageFieldElement(strFormNameManifest,"CBMBelowMinimum");
	   	if(strErrorAboveMin.equals("")){
	   		
	   	}else{
	   		strMessage = strMessage + strErrorAboveMin;
	   	}
	   		   	
	   	//above max
	   	
		int cubeVal2 = objAdjunoLIMAManifestPOM.StringToInt(strCube);
	   	cubeVal2 = cubeVal2+40;
	   	
	   	String strCube2 = objAdjunoLIMAManifestPOM.IntToString(cubeVal2);
	   	
	   	try{
	    if(objAdjunoLIMAManifestPOM.getProductLink().isDisplayed()){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getProductLink()), true,"Product Link is not clicked");
	 	   	wait(2000);
	  	   
	 	   
	 	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUCube",strCube2),true,"qty is not set");
	 	   	
	 	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strFormNameManifest, "btnApply"), true,"apply button not clicked");
	 	   	wait(2000);
	 	  
	    }
	    }
	   	catch(Exception ex){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"Cube",strCube2),true,"qty is not set");
	   	}
	   	
	   	
	   	String strUtilisationText = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameManifest,"UtilisationText");
	   	
	   	objSoftAssert.assertEquals(strUtilisationText.equalsIgnoreCase("Maximum Utilisation Exceeded"), true,"utilization message does not match");
	   	//CBMBelowOptimum
	   	
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test Id: 22 -"+strMessage+" *****");
	   	
	   	objAdjunoLIMAManifestPOM.getValidationMessageFieldElement(strFormNameManifest,"CBMBelowMinimum");
  	  
	   	objAdjunoLIMAManifestPOM.getValidationMessageFieldElement(strFormNameManifest,"CBMAboveMaximum");
  	   
	   	objSoftAssert.assertAll();
   }
    
    @Test(priority = 90)
    public void test_28_VerifyGridProductDetailCaption()
    {
 	   
 	   SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	   
   	   String strMessage="";
   	   ArrayList<String> list = new ArrayList<String>();
 		    		    	
   	   list = objAdjunoLIMAManifestPOM.getCaptionsList(strFormNameManifest,"GridExistingProducts");		    	    	
   	  
   	   strMessage = objAdjunoLIMAManifestPOM.verifyCaptionsONGrid(list,productDeatailCaptions,14);
 				
 	   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id: 28 -"+strMessage+" *****");
 	   objSoftAssert.assertAll();
    }
    
    @Test(priority = 93)
    public void test_setGridvalues()
    {
 	   
    	SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	   
   	   
   	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"PackType",strPackType),true,"pack type is not set");
 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"ProductFreightTerms",strProductFreightTerms),true,"FreightTerms is not set");
 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"ProductPallets",strProductPallets),true,"Pallet is not set");
 	   
 	   
 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"DocType",strDocType),true,"Doc type is not set");
 	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"HouseBill",strHouseBill),true,"House bill is not set");
 	 
 	   
 	   try{
 		   if(objAdjunoLIMAManifestPOM.getProductLink().isDisplayed()){
 			   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getProductLink()), true,"Product Link is not clicked");
 			   wait(2000);
 		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUShippedQuantity",strQty),true,"qty is not set");
 		  	   
 		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUCube",strCube),true,"qty is not set");
 		  	 
 		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUCartons",strCartons),true,"cartons is not set");
 		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUGrossWeight",strGrossWeight),true,"Gross weight is not set");
 		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridSKUs",0,"SKUNetWeight",strNetWeight),true,"net Weight is not set");
 		  	  
 		  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strFormNameManifest, "btnApply"), true,"apply button not clicked");
 		   }      
 		   }
 		catch(Exception ex){
 			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"ShippedQuantity",strQty),true,"qty is not set");
 			objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"Cube",strCube),true,"qty is not set");
 			  	 
 		  	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"Cartons",strCartons),true,"cartons is not set");
 		  	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"GrossWeight",strGrossWeight),true,"Gross weight is not set");
 		  	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForGridCell(strFormNameManifest,"GridProducts",0,"NetWeight",strNetWeight),true,"net Weight is not set");
 		  	 
 		   
 		
 		 
 	   }
 	  
   	   objSoftAssert.assertAll();
    } 
     
   
   
/*   @Test(priority = 95)
   public void verifyValueEntered()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   wait(5000);
  	   String strTotalQty = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameManifest,"TotalShippedQuantity");
  	   String strTotalCtn = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameManifest,"TotalCartons");
  	   String strTotalGrWeight = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameManifest,"TotalGrossWeight");
  	   String strTotalCube = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameManifest,"TotalCube");
  	    
  	   System.out.println("strTotalQty:"+strTotalQty);
  	   System.out.println("strTotalCtn:"+strTotalCtn);
  	   System.out.println("strTotalGrWeight:"+strTotalGrWeight);
  	   System.out.println("strTotalCube:"+strTotalCube);
  	  
  	   int cubeVal =  objAdjunoLIMAManifestPOM.StringToInt(strCube);
  	   cubeVal = (int) (cubeVal * nRow);
  	   
  	   int qtyVal =  objAdjunoLIMAManifestPOM.StringToInt(strQty);
  	   qtyVal = (int) (qtyVal * nRow);
  	   
  	   int cartonsVal =  objAdjunoLIMAManifestPOM.StringToInt(strCartons);
  	   cartonsVal = (int) (cartonsVal * nRow);
  	 
  	 
  	   int grossWeightsVal =  objAdjunoLIMAManifestPOM.StringToInt(strGrossWeight);
  	   grossWeightsVal = (int) (grossWeightsVal * nRow);
  	    
  	   strCube = objAdjunoLIMAManifestPOM.IntToString(cubeVal);
  	   strQty = objAdjunoLIMAManifestPOM.IntToString(qtyVal);
  	   strCartons = objAdjunoLIMAManifestPOM.IntToString(cartonsVal);
  	   strGrossWeight = objAdjunoLIMAManifestPOM.IntToString(grossWeightsVal);
  	   
  	   objSoftAssert.assertEquals(strCube.equalsIgnoreCase(strTotalCube), true,"cube does not match in container");
  	   
  	   objSoftAssert.assertEquals(strQty.equalsIgnoreCase(strTotalQty), true,"Qty does not match in container");
	   
  	   objSoftAssert.assertEquals(strCartons.equalsIgnoreCase(strTotalCtn), true,"Cartons does not match in container");
	   
  	   objSoftAssert.assertEquals(strGrossWeight.equalsIgnoreCase(strTotalGrWeight), true,"total kgs does not match in container");
	   
  	   objSoftAssert.assertAll();
   }*/
   
   
   
   
   @Test(priority=98)
   public void test_29_ClickReview(){
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	 //  setProductGridField();
	   
	   
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvReview2()),true,"***** Test Id:29- Review chevron is not clicked *****");
	   wait(5000);
		
	   objSoftAssert.assertAll();	   
   }
   
   /*@Test(priority=100)
   public void verifyReviewData(){
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "Container"), strContainer,"Container value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "Seal"), strSeal,"Seal value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "Loading"), strLoading,"Loading value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "Equipment"), strEquipment,"Equipment value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "ShippedQuantity"), strQty,"Qty value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "Cartons"), strCartons,"cartons value mismatch");
  	   
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "GrossWeight").contains(strGrossWeight),true ,"Gross weight value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "NetWeight").contains(strNetWeight), true,"net weight value mismatch");
  	   objSoftAssert.a  ssertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "Cube").contains(strCube), true,"cbm value mismatch");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getGridCellElementValue(strformNameManifestReview, "GridContainer", 0, "ManifestKPI"), strKPI,"KPI value mismatch");
		
	   objSoftAssert.assertAll();	   
   }*/
   
   @Test(priority=105)
   public void test_31_clickComplete(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   	   
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvComplete2()),true,"***** Test Id: 31- Complete chevron is not clicked");
	   wait(6000);
		
	   objSoftAssert.assertAll();
   }
   
   @Test(priority=110)
   public void test_verifyCompleteMessge(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   String strMessage ="";
	   wait(6000);
	   if(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameComplete, "UpdateMessage")){
		   String strUpdatedMessage = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameComplete, "UpdateMessage");
		   objSoftAssert.assertEquals(strUpdatedMessage.contains("Manifest Completed. "), true,"Manifest complete message mismatch");
	   }else{
		   strMessage = strMessage + " Manifest process is not completed ";
	   }
  	  
 
  	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
  	   objSoftAssert.assertAll();
  }
   
   
   @Test(priority=115,dependsOnMethods="test_verifyCompleteMessge")
   public void test_getTrackRefNo(){
	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   objAdjunoLIMAManifestPOM.getBtnViewDetail().click();
       wait(3000);
       String strTrackDetails = objAdjunoLIMAManifestPOM.getTrackReference().getText();
       
       String[] vals = strTrackDetails.split("Track Reference ");
       
       vals[1] = vals[1].replace("[", " ").replace("]", " ");
       String valss[] = vals[1].split(" ",12);
       
       strTrackNO = valss[1];
       System.out.println("tRACK NUMBER:"+strTrackNO);
       wait(2000);
      
  }
   
   
   @Test(priority=125,dependsOnMethods="test_getTrackRefNo")	 
   public void test_clickOnTrackLink(){	    
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = "";
	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	    	
	   objAdjunoLIMAManifestPOM.callMouseHover("",objAdjunoLIMAManifestPOM.getLnkTrack(),objAdjunoLIMAManifestPOM.getLnkEdit());
	   wait(2000);
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getFldRefNo()),true,"Reference field is not exist");
		    	
	   if(!isNullOrBlank(strTrackNO)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForWebElement(objAdjunoLIMAManifestPOM.getFldRefNo(),strTrackNO),true,"Reference field value is not set");   
			    	  
			 objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getBtnApply());
	   }else{
        	strMessage =  "Track number is empty";		        	
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	   objSoftAssert.assertAll();
	    	
	}	
   
   	@Test(priority=127)
	public void test_checkForManifestDisplay() throws ParseException {	    
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
		   
	//	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getTxtPurchaseOrder()), true,"Title Manifest is not found");
		    	    	
		long min = objAdjunoLIMAManifestPOM.getTrackValue(strUserName);
		if(min<=355){
		    		
		}else{
			strMessage = strMessage +"Not a todays date";
		}
		    	
		objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
		    	
		objSoftAssert.assertAll();
	}
   	
  /* 	@Test(priority=129)
	public void test_34_ManifestLink() {	    
	    SoftAssert objSoftAssert = new SoftAssert();
	    	
	    objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getLnkManifest2()),true,"***** Test Id:34- not able to click on Manifest *****");
	   	objSoftAssert.assertAll();
	    
	}*/
   

  /* @Test(priority = 130)
   public void goToPoStatus(){        
       SoftAssert objSoftAssert = new SoftAssert();
	   
	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
       
	   objAdjunoLIMAManifestPOM.callMouseHover("", objAdjunoLIMAManifestPOM.getLnkFind(), objAdjunoLIMAManifestPOM.getLnkPOStatus());
      
         
      objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.verifyPageIsLoaded(strPOStatusFormName), true, "PO Status Page is not displayed");
      objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clearInputField("", "Param_14PO"),  true, "PO Number is not cleared");
      objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(searchManifest.getPoNumber(), strPOStatusFormName, "Param_14PO"), true, "PO Number is not set in PO Status page");
      objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getBtnApply()), true, "Run button is not clicked");
     // objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getPoStatusReport()), true, "PO Status report page is not loaded");
      
      
      objSoftAssert.assertAll();
   
       
	}*/

   
   @Test(priority=130)
   public void test_accessManifest2(){
	   test_accessManifest();
	   
   }
   
   @Test(priority=135)
   public void test_41_SetStausAsManifest(){
	   
	   	SoftAssert objSoftAssert = new SoftAssert();
	   	String strMessage ="";
	   	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   	clearFields();
	 	wait(3000);
  	   
	  
  	    if (!isNullOrBlank(strStatusManifest)){
  	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strStatusManifest, strManifestSearchFormName,"Param_Status"), true,"***** Test Id:41- status field is not set *****");
	    }else{
	   		strMessage = strMessage + " status value is empty";
	   	}
  	    
  	    if (!isNullOrBlank(strModeSea)){
	    	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strModeSea, strManifestSearchFormName,"Param_Mode"), true,"***** Test Id:41-Mode field is not set *****");
	    }else{
	   		strMessage = strMessage + " MOde value is empty";
	   	}
  	    
  	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvSelect()),true,"***** Test Id:41-Manifest Search button is not clicked *****");
		wait(5000);
		
		objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getNoItem()),false, "***** Test Id:41-Result items are not found *****");
		
  	    objSoftAssert.assertAll();
  	    
	   
   }
   
   @Test(priority=145)
   public void test_selectManifestedProduct(){
	   
	   	SoftAssert objSoftAssert = new SoftAssert();
	   	String strMessage ="";
	   	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   	for (int j = 0; j <=objAdjunoLIMAManifestPOM.getManifestStatus().size()-1 ; j++) {
	   		if (objAdjunoLIMAManifestPOM.getManifestStatus().get(j).getText().equalsIgnoreCase("Manifested")) {
				objAdjunoLIMAManifestPOM.getSelectBox().get(j).click();
				wait(2000);
				searchManifestValue = new Manifest(objAdjunoLIMAManifestPOM.getTrOriginPort().get(j).getText(), objAdjunoLIMAManifestPOM.getTrVessel().get(j).getText(), objAdjunoLIMAManifestPOM.getTrETDDate().get(j).getText(), objAdjunoLIMAManifestPOM.getTrETADate().get(j).getText(), objAdjunoLIMAManifestPOM.getTrCarrier().get(j).getText(), objAdjunoLIMAManifestPOM.getTrLoading().get(j).getText(), objAdjunoLIMAManifestPOM.getTrLoadRef().get(j).getText(), objAdjunoLIMAManifestPOM.getTrContainer().get(j).getText(), objAdjunoLIMAManifestPOM.getTrEquipment().get(j).getText(), objAdjunoLIMAManifestPOM.getTrVendor().get(j).getText(), objAdjunoLIMAManifestPOM.getTrHBL().get(j).getText(), objAdjunoLIMAManifestPOM.getTrPONumber().get(j).getText(), objAdjunoLIMAManifestPOM.getTrProduct().get(j).getText(), objAdjunoLIMAManifestPOM.getTrIdentifier().get(j).getText(), objAdjunoLIMAManifestPOM.getTrCustomerRef().get(j).getText(),objAdjunoLIMAManifestPOM.getTrBkdQty().get(j).getText(), objAdjunoLIMAManifestPOM.getTrQty().get(j).getText(), objAdjunoLIMAManifestPOM.getTrCtns().get(j).getText(), objAdjunoLIMAManifestPOM.getTrCBM().get(j).getText(), objAdjunoLIMAManifestPOM.getTrPackType().get(j).getText(), objAdjunoLIMAManifestPOM.getTrFreightTerms().get(j).getText(), objAdjunoLIMAManifestPOM.getTrDC().get(j).getText(), objAdjunoLIMAManifestPOM.getTrmanifestStatus().get(j).getText(), objAdjunoLIMAManifestPOM.getTrDailog().get(j).getText());
				break;
			}else{
				if(j==objAdjunoLIMAManifestPOM.getManifestStatus().size()-1){
					strMessage = strMessage + "Stutus As Manifested Results not Found";
				}
				
			}
	   		
		}
	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage); 	    
   	    objSoftAssert.assertAll();
	  	
   }
   
   
   @Test(priority=150)
   public void test_42_CancelManifestChevron(){
	   
	   	SoftAssert objSoftAssert = new SoftAssert();
	  
	   	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"FORK_ManifestCancel"),true,"***** Test Id:42- Cancel Manifest button is not present *****");
   	    
	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestCancel"),true,"***** Test Id:42- Cancel Manidest button is not clicked *****");
   	    wait(5000);
   	    
	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.getCancelTitle().getText(),"Cancel","***** Test Id:42- Cancel title mismatch *****");
   	    wait(5000);
   	    
   	    objSoftAssert.assertAll();
	  	
   }
   
   
   @Test(priority=155)
   public void test_43_VerifyCancelTabSections(){
	   
	   	SoftAssert objSoftAssert = new SoftAssert();
	  
	   	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   	   
	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameManifestCancel,"SKUs"),true,"***** Test Id:43-Product to cancel section does not exist *****");
	   	   
	   	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameManifestCancel,"Containers"),true,"***** Test Id:43-container to cancel section does not exist *****");
	 	
   	    objSoftAssert.assertAll();
	  	
   }
   
   
   @Test(priority=160)
   public void test_getProductToCancelRow(){
   	SoftAssert objSoftAssert = new SoftAssert();
   	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	nRow2 = objAdjunoLIMAManifestPOM.getNoOrRowsinGrid(strFormNameManifestCancel,"SKUs");
   	
   	objSoftAssert.assertAll();
   }
   
   
   /*@Test(priority = 165)
   public void getProductToCancelData()
   {
   	SoftAssert objSoftAssert = new SoftAssert();
   	objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
   	manifestList = new ArrayList<Manifest>();
   	for (int i = 0; i <= nRow2-1; i++) {
   		
   		Manifest chvManifest = new Manifest(objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"Supplier").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"OrderNumber").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"SKU").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"ProductIdentifier").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"BookedQuantity").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"ShippedQuantity").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"Cartons").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"Cube").getText(), objAdjunoLIMAManifestPOM.getGridCellElement(strFormNameManifestCancel,"SKUs",i,"PackType").getText());
       	manifestList.add(chvManifest);
		}
   
   	objSoftAssert.assertAll();
   }*/
   
   
   @Test(priority = 170)
   public void test_44_VerifyCancelContainerCaption()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	   String strMessage="";
  	   ArrayList<String> list = new ArrayList<String>();
		    		    	
  	   list = objAdjunoLIMAManifestPOM.getCaptionsList(strFormNameManifestCancel,"Containers");		    	    	
  	   
  	   strMessage = objAdjunoLIMAManifestPOM.verifyCaptionsONGrid(list,containerCaptions,9);
		    	
				
	   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id:44 -"+ strMessage +" ****");
	   objSoftAssert.assertAll();
   }
   
   
  /* @Test(priority = 175)
   public void test_45_VerifyCancelProductCaption()
   {
	   
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	   String strMessage="";
  	   ArrayList<String> list = new ArrayList<String>();
		    		    	
  	   list = objAdjunoLIMAManifestPOM.getCaptionsList(strFormNameManifestCancel,"SKUs");		    	    	
  	  
  	   strMessage = objAdjunoLIMAManifestPOM.verifyCaptionsONGrid(list,productCaptions,28);
		    	
				
	   objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id:45-"strMessage+" *****");
	   objSoftAssert.assertAll();
   }*/
   
   @Test(priority=185)
   public void test_46_clickCompleteChevron(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);	   
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvComplete2()),true," Complete chevron is not clicked");
	   
	   wait(5000);
	   objSoftAssert.assertAll();
	   
	   
   }
   
   
   @Test(priority=186)
   public void test_verifyCancelCompleteMessge(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   String strMessage ="";
	   
	   if(objAdjunoLIMAManifestPOM.checkDoesElementExist(strFormNameComplete, "UpdateMessage")){
		   String strUpdatedMessage = objAdjunoLIMAManifestPOM.getWebElementValue(strFormNameComplete, "UpdateMessage");
		   objSoftAssert.assertEquals(strUpdatedMessage.contains("1 product over 1 shipments cancelled"), true," Cancel Manifest complete message mismatch");
	   }else{
		   strMessage = strMessage + " Cancel Manifest process is not completed ";
	   }
  	  
 
  	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
  	   objSoftAssert.assertAll();
  }
   
   
 
   
   @Test(priority=190)
   public void test_47_GetCancelTrackRefNo(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   objAdjunoLIMAManifestPOM.getBtnViewDetail().click();
       wait(3000);
       String strTrackDetails = objAdjunoLIMAManifestPOM.getTrackReference().getText();
       
       String[] vals = strTrackDetails.split("Track Reference ");
       
       vals[1] = vals[1].replace("[", " ").replace("]", " ");
       String valss[] = vals[1].split(" ",12);
       
       strTrackNO2 = valss[1];
       System.out.println("tRACK NUMBER 2:"+strTrackNO2);
       wait(2000);
      objSoftAssert.assertAll();
  }
   
  /* @Test(priority=198)
   public void test_48_AccessTrack(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = "";
	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	    	
	   objAdjunoLIMAManifestPOM.callMouseHover("",objAdjunoLIMAManifestPOM.getLnkTrack(),objAdjunoLIMAManifestPOM.getLnkEdit());
	   wait(2000);
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getFldRefNo()),true,"***** Test Id:48-Reference field is not exist *****");
		    	
	   if(!isNullOrBlank(strTrackNO2)){	
			 objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValueForWebElement(objAdjunoLIMAManifestPOM.getFldRefNo(),strTrackNO2),true,"***** Test Id:48-Reference field value is not set *****");   
			    	  
			 objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getBtnApply());
	   }else{
        	strMessage =  "Track number is empty";		        	
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
	   objSoftAssert.assertAll();
	    	
	}	
   
   	@Test(priority=200)
	public void test_49_checkForManifestLink() throws ParseException {	    
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
		   
	//	objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getTxtPurchaseOrder()), true,"Title Manifest is not found");
		    	    	
		long min = objAdjunoLIMAManifestPOM.getTrackValue(strUserName);
		if(min<=275){
		    		
		}else{
			strMessage = strMessage +"Not a todays date";
		}
		    	
		objSoftAssert.assertEquals(strMessage.equals(""), true,"***** Test Id:49-"+strMessage+" *****");
		    	
		objSoftAssert.assertAll();
	}
   	
   	@Test(priority=201)
	public void test_49_ManifestLink() {	    
	    SoftAssert objSoftAssert = new SoftAssert();
	    	
	    objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	    objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButtonUsingWebElement(objAdjunoLIMAManifestPOM.getLnkManifest2()),true,"***** Test Id:49- not able to click on Manifest *****");
	   	objSoftAssert.assertAll();
	    
	}*/
   
   @Test(priority=202)
   public void test_accessManifest3(){
	   test_accessManifest();
	   
   }
   
   @Test(priority=205)
   public void test_56_setManifestStaus2(){
	   test_41_SetStausAsManifest();
	   
	   test_selectManifestedProduct();
   }
   
   @Test(priority=210)
   public void test_56_clickAddDailog(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   

	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist(strManifestSearchFormName,"FORK_ManifestDialog"),true,"***** Test Id:56- Add Dialog is not present *****");
  	    
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickButton(strManifestSearchFormName,"FORK_ManifestDialog"),true,"***** Test Id:56- Add Dialog is not clicked *****");
  	   
	   wait(5000);
  	   objSoftAssert.assertAll();
   }
   
   @Test(priority=212)
   public void test_57_verifyDailogFields(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getDailogTitle()),true,"***** Test Id:57- Dailog page header is present *****");
	   
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist("LIMA_SUT_Dialog_INPUT","DialogSubject"),true,"***** Test Id:55- subject field is not present *****");
	    
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist("LIMA_SUT_Dialog_INPUT","DialogMessage"),true,"***** Test Id:55- message Dialog field is not present *****");
	    
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.checkDoesElementExist("LIMA_SUT_Dialog_INPUT","CopyEmail"),true," email field is not present");
	    
	   objSoftAssert.assertAll();
  	   
   }
   
   
   @Test(priority=215)
   public void test_59_SetValuesInDailogPage(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
  	   
  	 //Dailog_Value
  	   
  //	  objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(, "LIMA_SUT_Dialog_INPUT","DialogSubject"),true," subject field is not present");
	    
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strDailogMessage,"LIMA_SUT_Dialog_INPUT","DialogMessage"),true,"***** Test Id:59- Message Dialog field Value is not set *****");
	    
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.setFieldValue(strMailId,"LIMA_SUT_Dialog_INPUT","CopyEmail"),true,"***** Test Id:59- email field value is not set *****");
	    
	   objSoftAssert.assertAll();
  	   
   }
   
     
   @Test(priority=218)
   public void test_60_clickDailogComplete(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   	   
	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.clickChevorn(objAdjunoLIMAManifestPOM.getChvComplete2()),true,"***** Test Id:60- Complete chevron is not clicked *****");
	   wait(5000);
		
	   objSoftAssert.assertAll();
   }
   
     
  /* @Test(priority=220)
   public void verifyDailogCompleteMessge(){
	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMAManifestPOM = new AdjunoLIMAManifestPOM(driver);
	   
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getUpdateMessage()), true,"Update Message not found");
  	   objSoftAssert.assertEquals(objAdjunoLIMAManifestPOM.isElementPresent(objAdjunoLIMAManifestPOM.getProcess()), true,"process 100% not completed");
  	  
  	   objSoftAssert.assertAll();
  }*/
	   
   
   @AfterTest
   public void closeBrowser(){
	   driver.close();
   }
}
