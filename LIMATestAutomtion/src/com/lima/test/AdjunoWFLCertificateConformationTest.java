package com.lima.test;
import java.util.ArrayList;
import java.util.List;
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
import com.lima.pagefactory.AdjunoWFLCertificateComfirmationPOM;


public class AdjunoWFLCertificateConformationTest {
    
    WebDriver driver;
    
    long nPage_Timeout = 0;
    String strTestURL;
    
    String strLIMAUserName;
    String strLIMAPassword;
    String strCustomerUserName;
    String strCustomerPassword;
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoUILibrary objAdjunoUILibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoWFLCertificateComfirmationPOM objAdjunoWFLCertificateConformationParamPOM;

    String strCertificateConformationFormName;
    String strCertificateConfirmationTitle;
    String strSearchCertificateFormName;
    String strCatalogTitle;
    String strCatalogFormName;
    String strCertificatePageFormName;
    String strCompletePageFormName;
    String strCustomerPageFormName;
    
    NodeList searchParams;
    
    String strStatusAwaiting;
    String strStatusAny;
    String strStatusCertified;
    String strOriginPort;
    String strVendor;
    String strWFLreference;
    String strProduct;
    
    String strCount = "";
    String strCustomer;
    String strSupplier;
    String strCountry;
    String strLoCode;
    
    ArrayList<String> catalogSupplierList;
    ArrayList<String> catalogCountryList;
    ArrayList<String> catalogCustomerList;
    ArrayList<String> catalogLoCodeList;
    
    ArrayList<String> dropdownVendorList;
    ArrayList<String> dropdownOriginPortList;
    ArrayList<String> dropdownCustomerList;

	String strStatusPending;
    
 	
 	
 	@BeforeTest
    public void Setup()
    {
        objAdjunoLIMALibrary = new AdjunoLIMALibrary();
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());
            XPath xPath = XPathFactory.newInstance().newXPath();
            
            Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout", dDoc, XPathConstants.NODE);
            nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());
            
            Node testURL = (Node) xPath.evaluate("/config/WFL/WFL_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testURL.getTextContent();
            
            Node limaUserName = (Node) xPath.evaluate("/config/WFL/WFL_UserName", dDoc, XPathConstants.NODE);
            strLIMAUserName = limaUserName.getTextContent();
            
            Node limaPassword = (Node) xPath.evaluate("/config/WFL/WFL_Password", dDoc, XPathConstants.NODE);
            strLIMAPassword = limaPassword.getTextContent(); 
            
            Node customerUserName = (Node) xPath.evaluate("/config/WFL/WFL_Customer_UserName", dDoc, XPathConstants.NODE);
            strCustomerUserName = customerUserName.getTextContent();
            
            Node customerPassword = (Node) xPath.evaluate("/config/WFL/WFL_Customer_Password", dDoc, XPathConstants.NODE);
            strCustomerPassword = customerPassword.getTextContent(); 
            
            Node pageTitleCertificateConfirmation = (Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Certificate_Confirmation", dDoc, XPathConstants.NODE);
            strCertificateConfirmationTitle = pageTitleCertificateConfirmation.getTextContent();
           
            Node pageTitleCatalog = (Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
            strCatalogTitle = pageTitleCatalog.getTextContent();
           
            Node formNameCertificateConfirmation = (Node) xPath.evaluate("/config/WFL/Form_Name_Certificate_Confirmation", dDoc, XPathConstants.NODE);
            strCertificateConformationFormName = formNameCertificateConfirmation.getTextContent();
         
            Node formNameCatalog = (Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
            strCatalogFormName = formNameCatalog.getTextContent();
            
            Node formNameCertificatePage = (Node) xPath.evaluate("/config/WFL/Form_Name_Certificate_Page", dDoc, XPathConstants.NODE);
            strCertificatePageFormName = formNameCertificatePage.getTextContent();
       
            Node formNameCompletePage = (Node) xPath.evaluate("/config/WFL/Form_Name_Complete_Page", dDoc, XPathConstants.NODE);
            strCompletePageFormName = formNameCompletePage.getTextContent();
            
            Node formNameCustomerPage = (Node) xPath.evaluate("/config/WFL/Form_Name_Customer_Page", dDoc, XPathConstants.NODE);
            strCustomerPageFormName = formNameCustomerPage.getTextContent();
       
           /* Node formNameCertificateSearch = (Node) xPath.evaluate("/config/WFL/Search_Form_Name_Certificate_Confirmation", dDoc, XPathConstants.NODE);
            strSearchCertificateFormName = formNameCertificateSearch.getTextContent();*/
          
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.get(strTestURL);

            driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);
            
            objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
            objAdjunoLIMALoginPOM.setUserName(strLIMAUserName);
            objAdjunoLIMALoginPOM.setPassword(strLIMAPassword);
            objAdjunoLIMALoginPOM.clickLogon();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getWFLCertificateFileName());
            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            searchParams = (NodeList) xPath1.evaluate("Catalog/Certificate_Confirmation/SearchParams", dDoc1, XPathConstants.NODESET);
            
            
            Node typeSupplier = (Node) xPath1.evaluate("/Catalog/SearchParams/Type_Supplier", dDoc1, XPathConstants.NODE);
            strSupplier = typeSupplier.getTextContent();
         
            Node typeCustomer = (Node) xPath1.evaluate("/Catalog/SearchParams/Type_Customer", dDoc1, XPathConstants.NODE);
            strCustomer = typeCustomer.getTextContent();
            
            Node typeCountry = (Node) xPath1.evaluate("/Catalog/SearchParams/Type_Country", dDoc1, XPathConstants.NODE);
            strCountry = typeCountry.getTextContent();
        
            Node typeLoCode = (Node) xPath1.evaluate("/Catalog/SearchParams/Type_LoCode", dDoc1, XPathConstants.NODE);
            strLoCode = typeLoCode.getTextContent();
        
         // search = (NodeList) xPath1.evaluate("/Cargo_Receipt/Search", dDoc1, XPathConstants.NODESET);
            
         // receiptCaption = (NodeList) xPath1.evaluate("/Cargo_Receipt/Receipt_Caption", dDoc1, XPathConstants.NODESET);
             
         // receiptDetailsCaption = (NodeList) xPath1.evaluate("/Cargo_Receipt/Receipt_Details_Caption", dDoc1, XPathConstants.NODESET);
            
         // receiptDetails = (NodeList) xPath1.evaluate("/Cargo_Receipt/Receipt_Details", dDoc1, XPathConstants.NODESET);
            
             
        }catch(Exception ex){
            ex.printStackTrace();
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
    
    
    
    //Title  
    @Test(priority=1)
    public void accessCatalog()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        
        String strTitle = objAdjunoWFLCertificateConformationParamPOM.callMouseHover(strCatalogFormName,objAdjunoWFLCertificateConformationParamPOM.getLnkTools(), objAdjunoWFLCertificateConformationParamPOM.getLnkCatalog());
        
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strCatalogTitle))
                bFlag = true;
            else
                bFlag = false;
        }          
    
        objSoftAssert.assertEquals(bFlag, true, "Title of the page is wrong");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getLnklighthouse()),true,"Light house link is not clicked");
        wait(5000);
     
        objSoftAssert.assertAll();
    }
    
    
    private boolean isNullOrBlank(String s) {
        // TODO Auto-generated method stub
        return (s==null || s.trim().equals(""));
    }
    
        
    
    @Test(priority=5)
    public void test_4d001_GetCatalogValues()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
           
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** Test ID : 4.001 - Name Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** Test ID : 4.001 - Description Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** Test ID : 4.001 - Associated item Field element not cleared *****");
        
        String strMessage="";
    
        //supplier
        //Setting the field value Supplier setFieldValue
        if (!isNullOrBlank(strSupplier)){
             wait(3000);
             objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
             objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field value is not entered as Supplier*****");
        }else{
               strMessage = strMessage + " ***** Test ID : 4.001 - Type Field element is Empty *****";
           }
        
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getBtnApply()),true,"***** Test ID : 4.001 - Apply button is not clicked *****");
        wait(5000);
        
        int nCount2 = objAdjunoWFLCertificateConformationParamPOM.valCount();
        
        catalogSupplierList = new ArrayList<String>();
        catalogSupplierList = objAdjunoWFLCertificateConformationParamPOM.getSupplierData(nCount2);
        wait(2000);
         
        
        //customer
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** Test ID : 4.001 - Name Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** Test ID : 4.001 - Description Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** Test ID : 4.001 - Associated item Field element not cleared *****");
        
        //Setting the field value Customer setFieldValue
        if(!isNullOrBlank(strCustomer))
        {
              wait(3000);
                 objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
                 objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strCustomer, strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field value is not entered as Customer*****"); 
                 }else{
            strMessage = strMessage + "***** Test ID : 4.001 - Type Field element is Empty *****";
        }
       
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getBtnApply()),true,"***** Test ID : 4.001 - Apply button is not clicked *****");
        wait(5000);

        int nCount = objAdjunoWFLCertificateConformationParamPOM.valCount();
        catalogCustomerList = new ArrayList<String>();
        catalogCustomerList = objAdjunoWFLCertificateConformationParamPOM.getSupplierData(nCount);
        wait(2000);
        
        
        //country
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** Test ID : 4.001 - Name Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** Test ID : 4.001 - Description Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** Test ID : 4.001 - Associated item Field element not cleared *****");
       
        
        //Setting the field value Country setFieldValue
        if(!isNullOrBlank(strCountry))
        {
              wait(3000);
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strCountry, strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field value is not entered as Country*****");
        }else{
            strMessage = strMessage + "***** Test ID : 4.001 - Type Field element is Empty *****";
        }
       
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getBtnApply()),true,"***** Test ID : 4.001 - Apply button is not clicked *****");
        wait(5000);
        
        int nCount3 = objAdjunoWFLCertificateConformationParamPOM.valCount();
        catalogCountryList = new ArrayList<String>();
        catalogCountryList = objAdjunoWFLCertificateConformationParamPOM.getSupplierData(nCount3);
        wait(2000);
               

        //LoCode
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** Test ID : 4.001 - Name Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** Test ID : 4.001 - Description Field element not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** Test ID : 4.001 - Associated item Field element not cleared *****");
       
        //Setting the field value LODOCE setFieldValue
        if(!isNullOrBlank(strLoCode))
        {
        		
              wait(3000);
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getBtnPagePrev()),true,"***** Test ID : 4.001 - Previous Button didn't click *****");
              wait(1000);
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field element not cleared *****");
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strLoCode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 4.001 - Type Field value is not entered as LoCODE*****");
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getBtnApply()),true,"***** Test ID : 4.001 - Apply button is not clicked *****");
              wait(5000);
                                
              int nCount4 = objAdjunoWFLCertificateConformationParamPOM.valCount();
              catalogLoCodeList = new ArrayList<String>();
              catalogLoCodeList = objAdjunoWFLCertificateConformationParamPOM.getSupplierData(nCount4);
              wait(2000);
                 
             
        }else{
        	
              strMessage = strMessage + "***** Test ID : 4.001 - Type Field is Empty *****";
        }
       
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getBtnApply()),true,"***** Test ID : 4.001 - Apply button is not clicked *****");
        wait(5000);
        
        int nCount4 = objAdjunoWFLCertificateConformationParamPOM.valCount();
        catalogLoCodeList= new ArrayList<String>();
        catalogLoCodeList = objAdjunoWFLCertificateConformationParamPOM.getSupplierData(nCount4);
        wait(2000);
               
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        
        objSoftAssert.assertAll();
    }
  
    
    
    @Test(priority=8)
    public void test_4d005_AccessCertificateConfirmation()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        
        String strTitle = objAdjunoWFLCertificateConformationParamPOM.callMouseHover(strCertificateConformationFormName,objAdjunoWFLCertificateConformationParamPOM.getLnkTools(), objAdjunoWFLCertificateConformationParamPOM.getLnkCertificateConfirmation());
        
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strCertificateConfirmationTitle))
                bFlag = true;
            else
                bFlag = false;
        }          
        objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 4.005 - Title of the page is wrong");              
        objSoftAssert.assertAll();
    }
    
    
    //Chev check
    @Test(priority=10)
    public void checkForChevorons(){
        
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
    
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesChevronExist(objAdjunoWFLCertificateConformationParamPOM.getChvSearch()), true,"search Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesChevronExist(objAdjunoWFLCertificateConformationParamPOM.getChvSelect()), true,"select Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesChevronExist(objAdjunoWFLCertificateConformationParamPOM.getChvCertificates()), true,"certificates Chevorn not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesChevronExist(objAdjunoWFLCertificateConformationParamPOM.getChvComplete()), true,"complete Chevorn not found");
        
        objSoftAssert.assertAll();
    }
    
  
    //Fields Check
    @Test(priority=14)
    public void checkForExistanceOfFields()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
                
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_OriginPort"), true,"origin port not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"Param_ETDFrom"), true,"etd from not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"Param_ETDTo"), true,"etd to not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_Mode"), true,"mode not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_Vessel"), true,"booked vessel not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_Status"), true,"status not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_Vendor"), true,"Vendor not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_Customer"), true,"customer not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"Param_CustomerReference"), true,"vender ref not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_PONumber"), true,"po number not found");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificateConformationFormName,"PARAM_Product"), true,"product not found");
        
        objSoftAssert.assertAll();
    }
    
    @Test(priority=19)
    public void clearFields()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
    
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_OriginPort"), true,"***** origin port not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"Param_ETDFrom"), true,"***** etd from not cleared *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"Param_ETDTo"), true,"***** etd to not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Mode"), true," ***** mode not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Vessel"), true," ***** booked vessel not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Status"), true,"***** status not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Vendor"), true,"***** Vendor not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Customer"), true," ***** customer not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"Param_CustomerReference"), true," ***** order number not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_PONumber"), true," ***** WFL reference not cleared ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Product"), true," ***** product not cleared ***** ");

        objSoftAssert.assertAll(); 
    }
   
    
    
    @Test(priority=20)
    public void searchProducts()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
           
        clearFields();
        
        //get data from certificateConfirmation.xml
        Element el;          
        for(int i=0; i <= searchParams.getLength()-1; i++)
        { 
            if (searchParams.item(i).getNodeType() == Node.ELEMENT_NODE)
            {
                el = (Element) searchParams.item(i);
                
                strStatusAny = el.getElementsByTagName("Status_Any").item(0).getTextContent();
                strStatusAwaiting = el.getElementsByTagName("Status_Awaiting").item(0).getTextContent();
                strStatusCertified = el.getElementsByTagName("Status_Certified").item(0).getTextContent();
                strOriginPort = el.getElementsByTagName("Origin_Port").item(0).getTextContent();
                strVendor = el.getElementsByTagName("Vendor_Ref").item(0).getTextContent();
                strWFLreference = el.getElementsByTagName("WFL_Ref").item(0).getTextContent();
                strProduct = el.getElementsByTagName("Product").item(0).getTextContent();
                               
            }   
        }
        
         
        //Setting the field value Status Any:setFieldValue
        if(!isNullOrBlank(strStatusAny))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusAny,strCertificateConformationFormName,"PARAM_Status"),true,"Status is not set");         
        }else{
              strMessage = strMessage + "status is null";
        }
       
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickChevorn(objAdjunoWFLCertificateConformationParamPOM.getChvSelect()),true,"Not able to click Select Chevron");
        wait(3000);
            
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Customer"), true,"origin port not cleared");
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll(); 
    }

    
    //verify dropdowns
    @Test(priority=25)
    public void test_4d006_13_VerifyingDropdowns()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage=""; 
      
        dropdownCustomerList = new ArrayList<String>();
        
        //Customer drop down
        dropdownCustomerList =objAdjunoWFLCertificateConformationParamPOM.getDropDownList(strCertificateConformationFormName, "PARAM_Customer");
        strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyCatalogData(catalogCustomerList, dropdownCustomerList);
        
               
        //Origin Port
        dropdownOriginPortList =objAdjunoWFLCertificateConformationParamPOM.getDropDownList(strCertificateConformationFormName, "PARAM_OriginPort");
        strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyCatalogData(catalogLoCodeList, dropdownOriginPortList);
        
        
        //Vendor 
        dropdownVendorList =objAdjunoWFLCertificateConformationParamPOM.getDropDownList(strCertificateConformationFormName, "PARAM_Vendor");
        strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyCatalogData(catalogSupplierList, dropdownVendorList);
        
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();  
    }

    
    @Test(priority=30)
    public void test_4d010_StatusAwaiting()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
           
        clearFields(); 
                
        //Setting the field value Status Awaiting:setFieldValue
        if(!isNullOrBlank(strStatusAwaiting))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusAwaiting,strCertificateConformationFormName,"PARAM_Status"),true,"***** Test ID : 4.010 - Awaiting Status is not set *****");         
        }else{
            strMessage = strMessage + " ***** Test ID : 4.010 - status is Empty *****";
        }
       
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificateConformationFormName, "FORK_POCertification_RefineSearch"),true," ***** Test ID : 4.010 - Not able to click Refine Search button *****");
	    wait(3000); 
	    
	    strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyStatusForAllProduct(strStatusAwaiting,"");
	   
	    objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();       
    }
	

    
    @Test(priority=33)
    public void test_4d012_CheckVendor()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
	    //Setting the vendor value
	    objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Status"), true,"***** Test ID : 4.012 - status field is not cleared ***** ");
	    //Setting the field value Status Any:setFieldValue
        if(!isNullOrBlank(strStatusAny))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusAny,strCertificateConformationFormName,"PARAM_Status"),true,"***** Test ID : 4.012 - Any Status is not set ***** ");         
    
        }else{
        
        	strMessage = strMessage + " ***** Test ID : 4.012 - status is Empty *****";
        }
        
	    //Setting the field value Vendor:setFieldValue
        if(!isNullOrBlank(strVendor))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strVendor,strCertificateConformationFormName,"PARAM_Vendor"),true,"***** Test ID : 4.012 - Vendor is not set *****");         
        }else{
            strMessage = strMessage + "***** Test ID : 4.012 - Vendor is Empty *****";
        }
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vessel"),true," ***** Test ID : 4.012 - Vessel is not null ***** ");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Mode"),true,"***** Test ID : 4.012 - Mode is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Customer"),true,"***** Test ID : 4.012 - Customer is not null *****");
               
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificateConformationFormName, "FORK_POCertification_RefineSearch"),true,"***** Test ID : 4.012 - Not able to click Refine Search button *****");
	    wait(3000); 
	    
	    List<WebElement> weVendorList = objAdjunoWFLCertificateConformationParamPOM.getLstSupplier();
	       
	    strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyVendorSearchData(strVendor,weVendorList);
	   	               
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
           
    }

    

    @Test(priority=35)
    public void test_4d014_CheckWFLReference()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
        clearFields();
        
        if(!isNullOrBlank(strStatusAny))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusAny,strCertificateConformationFormName,"PARAM_Status"),true,"***** Test ID : 4.014 - Any Status is not set ***** ");         
        }else{
            strMessage = strMessage + "***** Test ID : 4.014 - Status is null ***** ";
        }
            
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vessel"),true," ***** Test ID : 4.014 - Vessel is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Mode"),true,"***** Test ID : 4.014 - Mode is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vendor"),true,"***** Test ID : 4.014 - Vendor is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Customer"),true,"***** Test ID : 4.014 - Customer is not null *****");
        
	    //Setting the field value WFL Reference:setFieldValue
        if(!isNullOrBlank(strWFLreference))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strWFLreference,strCertificateConformationFormName,"PARAM_PONumber"),true,"***** Test ID : 4.014 - WFL reference number is not set *****");         
        }else{
            strMessage = strMessage + "***** Test ID : 4.014 - WFL Reference is Empty *****";
        }
       
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificateConformationFormName, "FORK_POCertification_RefineSearch"),true," ***** Test ID : 4.014 - Not able to click Refine Search button *****");
	    wait(3000); 
	  
	    List<WebElement> weVendorList = objAdjunoWFLCertificateConformationParamPOM.getLstWflref();
		  	    
	    strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyVendorSearchData(strWFLreference,weVendorList);
	 
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();       
    }
 
    
    
    @Test(priority=38)
    public void test_4d015_CheckProduct()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
    
        clearFields();
     
        if(!isNullOrBlank(strStatusAny))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusAny,strCertificateConformationFormName,"PARAM_Status"),true,"***** Test ID : 4.015 - Status is not set *****");         
        }else{
            strMessage = strMessage + "***** Test ID : 4.015 - status is null *****";
        }
        
        
	    //Setting the field value WFL Reference:setFieldValue
	    
        if(!isNullOrBlank(strProduct))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strProduct,strCertificateConformationFormName,"PARAM_Product"),true,"***** Test ID : 4.015 - Product value is not set *****");         
        }else{
            strMessage = strMessage + "***** Test ID : 4.015 - Product is null *****";
        }
      
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vessel"),true," ***** Test ID : 4.015 - Vessel is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Mode"),true," ***** Test ID : 4.015 - Mode is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vendor"),true,"***** Test ID : 4.015 - Vendor is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Customer"),true,"***** Test ID : 4.015 - Customer is not null *****");
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificateConformationFormName, "FORK_POCertification_RefineSearch"),true,"***** Test ID : 4.015 - Not able to click Refine Search button *****");
	    wait(3000); 

	    List<WebElement> weVendorList = objAdjunoWFLCertificateConformationParamPOM.getLstProduct();

	    strMessage = objAdjunoWFLCertificateConformationParamPOM.verifyVendorSearchData(strProduct,weVendorList);
	 
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
    }

    
    
    
    @Test(priority=40)
    public void test_4d020_CertificatePage()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        boolean bFlag;
    
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.verifyPageIsLoaded(strCertificatePageFormName), true, "***** Test ID : 4.020 - Certificate page is not loaded *****");
        
        clearFields();
     
        if(!isNullOrBlank(strStatusAwaiting))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusAwaiting,strCertificateConformationFormName,"PARAM_Status"),true," ***** Test ID : 4.020 - Status is not set *****");         
        }else{
            strMessage = strMessage + "***** Test ID : 4.020 - status is null *****";
        }
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vessel"),true,"***** Test ID : 4.020 - Vessel is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Mode"),true,"***** Test ID : 4.020 - Mode is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Vendor"),true,"***** Test ID : 4.020 - Vendor is not null *****");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue("",strCertificateConformationFormName,"PARAM_Customer"),true,"***** Test ID : 4.020 - Customer is not null *****");
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificateConformationFormName, "FORK_POCertification_RefineSearch"),true," ***** Test ID : 4.020 - Not able to click Refine Search button *****");
	    wait(3000); 
        
	    if(objAdjunoWFLCertificateConformationParamPOM.getNoOrRowsResulted() > 0)
	    {
	    	objAdjunoWFLCertificateConformationParamPOM.selectMulitpleCheckBox(2);
	    	bFlag = true;
	    }
	    else
	    {
	    	bFlag = false;
	    }
	    objSoftAssert.assertEquals(bFlag, true," ***** Test ID : 4.020 - Checkbox is not selected *****");
	    
	    objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickChevorn(objAdjunoWFLCertificateConformationParamPOM.getChvCertificates1()),true," ***** Test ID : 4.020 - Not able to click Certificate Chevron ***** Test ID : 4.020 - ");
        wait(3000);
        
        //verify Certificate Grid is present
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.checkDoesElementExist(strCertificatePageFormName, "GridCertification"), true," ***** Test ID : 4.020 - Grid not present *****");
	   
        //select the row and click
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.selectCertificateGridValue(strCertificatePageFormName,"GridCertification", 0,"Supplier"),true," ***** Test ID : 4.020 - Didn't select 1st row from Grid *****");
	
        //Clicking the check boxes
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickCheckBox(strCertificatePageFormName, "FORMPackListInd"), true," ***** Test ID : 4.020 - Packing List didn't click *****");
        
        //Clicking the check boxes
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickCheckBox(strCertificatePageFormName, "FORMCommercialInvoiceInd"), true," ***** Test ID : 4.020 - Commercial Invoice didn't click ****");
        
        //Clicking the check boxes
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickCheckBox(strCertificatePageFormName, "FORMHealthCertInd"), true," ***** Test ID : 4.021 - CertNot Applicable didn't click *****");
        
        //Setting the Health Certificate no
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(objAdjunoWFLCertificateConformationParamPOM.getSaltString(), strCertificatePageFormName, "FORMCertificateNo"), true," ***** Test ID : 4.021 - Health Certificate no not set *****");
        
        //Click on Apply button
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificatePageFormName, "SaveChanges"), true," ***** Test ID : 4.021 - Apply button didn't click *****");
        
        wait(2000);
        String strValidation="";

        //Verifying Certification requirements have been fulfilled.
        strValidation = objAdjunoWFLCertificateConformationParamPOM.validElementExist(strCertificatePageFormName, "Panel2");
        objSoftAssert.assertEquals(strValidation.equalsIgnoreCase("Certification requirements have been fulfilled. Shipping may commence."),true," ***** Test ID : 4.021 - Validation message isn't displayed *****");
        
        objSoftAssert.assertAll();      
    }
    
    
    @Test(priority=45)
    public void test_4d023_PickListPartComplete()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
        //select the row and click
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.selectCertificateGridValue(strCertificatePageFormName,"GridCertification", 1,"Supplier"),true,"***** Test ID : 4.022 - Didn't click on 2nd row from the grid *****");
	
        //Clicking the check boxes
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickCheckBox(strCertificatePageFormName, "FORMPackListInd"), true,"***** Test ID : 4.023 - Packing List didn't select check box *****");
       
        //Click on Apply button
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificatePageFormName, "SaveChanges"), true,"***** Test ID : 4.023 - Apply button didn't click *****");
        wait(1000);
        
        //Verifying Status
        String strGridStautsValue = objAdjunoWFLCertificateConformationParamPOM.getGridCellValue(strCertificatePageFormName, "GridCertification", 1, "Certified2");
        objSoftAssert.assertEquals(strGridStautsValue.equalsIgnoreCase("Part-Complete"),true,"***** Test ID : 4.023 - Part Complete message isn't displayed *****");
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
    }
    
     
    @Test(priority=47)
    public void test_4d024_CommericalPartComplete()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
        //select the row and click
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.selectCertificateGridValue(strCertificatePageFormName,"GridCertification", 1,"Supplier"),true,"***** Test ID : 4.024 - Didn't click on 2nd row from the grid *****");
	
        //Clicking the check boxes
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickCheckBox(strCertificatePageFormName, "FORMCommercialInvoiceInd"), true,"***** Test ID : 4.024 - Commercial Invoice didn't click *****");

        //Click on Apply button
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificatePageFormName, "SaveChanges"), true,"***** Test ID : 4.024 - Apply button didn't click *****");
        wait(1000);
        
        //Verifying Status
        String strGridStautsValue = objAdjunoWFLCertificateConformationParamPOM.getGridCellValue(strCertificatePageFormName, "GridCertification", 1, "Certified2");
        objSoftAssert.assertEquals(strGridStautsValue.equalsIgnoreCase("Part-Complete"),true,"***** Test ID : 4.024 - Part Complete message isn't displayed *****");
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll(); 
    }

    
    
    @Test(priority=49)
    public void test_4d025_HealthCPartComplete()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
        //select the row and click
        //objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.selectCertificateGridValue(strCertificatePageFormName,"GridCertification", 2,"Supplier"),true,"***** Test ID : 4.025 - Didn't click on 2nd row from the grid *****");
        objAdjunoWFLCertificateConformationParamPOM.selectCertificateGridValue(strCertificatePageFormName,"GridCertification", 3,"Supplier");
        //Clicking the check boxes
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickCheckBox(strCertificatePageFormName, "FORMHealthCertInd"), true,"***** Test ID : 4.026 - CertNot Applicable didn't click *****");
        
        //Setting the Health Certificate no
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(objAdjunoWFLCertificateConformationParamPOM.getSaltString(), strCertificatePageFormName, "FORMCertificateNo"), true,"***** Test ID : 4.026 - Health Certificate number not set *****");
        
        //SetField HS Code
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(objAdjunoWFLCertificateConformationParamPOM.getRandomString(), strCertificatePageFormName, "FORMHSCode"), true,"***** Test ID : 4.027 - HS CODE number not set *****");
        
        //Click on Apply button
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificatePageFormName, "SaveChanges"), true,"***** Test ID : 4.027 - Apply button didn't click *****");
        wait(1000);
        
        //Verifying Status
        String strGridStautsValue = objAdjunoWFLCertificateConformationParamPOM.getGridCellValue(strCertificatePageFormName, "GridCertification", 2, "Certified2");
        objSoftAssert.assertEquals(strGridStautsValue.equalsIgnoreCase("Part-Complete"),true,"***** Test ID : 4.027 - Part Complete message isn't displayed *****");
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
    }

    
    @Test(priority=50)
    public void test_4d029_CompletePage()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickChevorn(objAdjunoWFLCertificateConformationParamPOM.getChvComplete1()),true," ***** Test ID : 4.029 - Not able to click Complete Chevron *****");
        wait(5000);
        
      //objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.verifyPageIsLoaded(strCompletePageFormName), true, "Complete page is not loaded");
        
       // objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.validElementExist(strCompletePageFormName, "UpdateMessage"),true,"***** Test ID : 4.030 - Complete status is not loaded *****");
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCompletePageFormName, "FORK_CertConfSearch"),true," ***** Test ID : 4.029 - Search button is not clicked *****");
        wait(3000);
         
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();

    }
    

/*       
    @Test(priority=51)
    public void Deletelater()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_OriginPort"), true,"origin port not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"Param_ETDFrom"), true,"etd from not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"Param_ETDTo"), true,"etd to not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Mode"), true,"mode not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Vessel"), true,"booked vessel not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Status"), true,"status not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Vendor"), true,"Vendor not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Customer"), true,"customer not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"Param_CustomerReference"), true,"order number not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_PONumber"), true,"WFL reference not cleared");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clearInputField(strCertificateConformationFormName,"PARAM_Product"), true,"product not cleared");
     
        if(!isNullOrBlank(strStatusCertified))
        {
              objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.setFieldValue(strStatusCertified,strCertificateConformationFormName,"PARAM_Status"),true,"Status is not set");         
        }else{
            strMessage = strMessage + "status is null";
        }
     
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCertificateConformationFormName, "FORK_POCertification_RefineSearch"),true,"Not able to click Refine Search button");
	    wait(3000); 
	    
	    objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickChevorn(objAdjunoWFLCertificateConformationParamPOM.getChvSelect()),true,"Not able to click Select Chevron");
        wait(3000);
        
    	objAdjunoWFLCertificateConformationParamPOM.selectMulitpleCheckBox(1);
    	
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickChevorn(objAdjunoWFLCertificateConformationParamPOM.getChvCertificates1()),true,"Not able to click Certificate Chevron");
        wait(3000);
        
     	objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.selectCertificateGridValue(strCertificatePageFormName,"GridCertification", 0,"Supplier"),true,"Didn't select 1st row from Grid");
	    
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickChevorn(objAdjunoWFLCertificateConformationParamPOM.getChvComplete1()),true,"Not able to click Select Chevron");
        wait(5000);
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.verifyPageIsLoaded(strCompletePageFormName), true, "Complete page is not loaded");
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.validElementExist(strCompletePageFormName, "UpdateMessage"),true,"Complete status is not loaded"); 
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.clickButton(strCompletePageFormName, "FORK_CertConfSearch"),true,"Search button is not clicked");
        wait(3000);
       
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
    }
*/    
    
     
    @Test(priority=55)
    public void Logoff()
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
    
        WebElement btnLogoff = objAdjunoWFLCertificateConformationParamPOM.getLnkLogoff();
        btnLogoff.click();
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
    }
    
    
    @Test(priority=60)
    public void customerLogin()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
    	
    	objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
        objAdjunoLIMALoginPOM.setUserName(strCustomerUserName);
        objAdjunoLIMALoginPOM.setPassword(strCustomerPassword);
        objAdjunoLIMALoginPOM.clickLogon();
        
        objSoftAssert.assertEquals(strMessage.equals(""),true, strMessage);
        objSoftAssert.assertAll();
    }
 
    
    
    @Test(priority=65)
    public void test_4d016_VerifyCustomerDropDown()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoWFLCertificateConformationParamPOM = new AdjunoWFLCertificateComfirmationPOM(driver);
        String strMessage ="";
        	
        String strTitle = objAdjunoWFLCertificateConformationParamPOM.callMouseHover(strCustomerPageFormName,objAdjunoWFLCertificateConformationParamPOM.getLnkTools(), objAdjunoWFLCertificateConformationParamPOM.getLnkCertificateConfirmation());
        
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strCertificateConfirmationTitle))
                bFlag = true;
            else
                bFlag = false;
        }          
               	
        objSoftAssert.assertEquals(strMessage.equals(""), true, "***** Test ID : 4.016 - Title of the page is wrong *****");              
       
        objAdjunoWFLCertificateConformationParamPOM.clickButtonUsingWebElement(objAdjunoWFLCertificateConformationParamPOM.getArrowCustomerDD());
        objSoftAssert.assertEquals(objAdjunoWFLCertificateConformationParamPOM.CustdropdownValue(), true, "***** Test ID : 4.016 - Drop down has extra value *****");
        
        objSoftAssert.assertAll();
    }
    
    
    @AfterTest
    public void closeBrowser()
    {
         driver.close();
    }

    
}