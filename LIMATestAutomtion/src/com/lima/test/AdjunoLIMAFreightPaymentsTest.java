package com.lima.test;

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

import com.lima.dto.FreightPayments;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMAFreightPaymentsPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMAFreightPaymentsTest {
	
	WebDriver driver;
	
	long nPage_Timeout = 0;
    String strTestURL;
    String strLIMAUserName;
    String strLIMAPassword;
	
	AdjunoLIMAFreightPaymentsPOM objAdjunoLIMAFreightPaymentsPOM;
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	ArrayList<FreightPayments> lstSearchResults;
	
	NodeList nlSearchParamsFreightPayments;
	NodeList nlCatalogParams;
	NodeList nlInvoiceGrid;
	NodeList nlContainerCharges;
	NodeList nlRateCardDetails;
	
	String strSearchFormNameFreightPayment;
	String strCatalogConfigurationFormName;
	String strCatalogFormName;
	String strFreightPaymentsUpdateFormName;
	String strSeaFreightPaymentTariffFormName;
	
	String strInvalidContainer = "";
	String strCatalogChargeDescription = "";
	String strCatalogInvoiceType = "";
	String strCatalogCarrier = "";
	String strCatalogCarrierType = "";
	String strFieldValue = "";
	String strDaysFromETD = "";
	String strDaysFromETA = "";
	String strDaysFromDelivery = "";
	String strDays = "";
	String strInvoiceCurrency = "";
	String strROE = "";
	String strComments = "";
	String strChargeType = "";
	String strInvoiceRate = "";
	String strPaymentStatusAuthorised = "";
	String strPaymentStatusQuery = "";
	String strInvoiceNumber = "";
	String strQueryComment = "";
	String strStatusQuery = "";
	String strInvoiceType = "";
	String strPendingStatus = "";
	String strMode = "";
	String strAuthorisedStatus = "";
	String strCarrier = "";
	String strCarrierType = "";
	String strContainerNo = "";
	String strInvoiceValue = "";
	String strCY_CY = "";
	String strCFS_CY = "";
	String strCFS_CFS = "";
	String strLoadingCYCY = "";
	String strLoadingCFSCY = "";
	String strLoadingCFSCFS = "";
	String strCatalog_Type = "";
	
	int invoiceDueRows;
	int AddRows;
	int ratecardRows;
	
	boolean bSearchResultsProductsFound = true;
	
	
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
            
            Node CatalogConfigurationFormName = (Node) xPath.evaluate("/config/LIMA/Catalog_Configuration_Form_Name", dDoc, XPathConstants.NODE);
            strCatalogConfigurationFormName = CatalogConfigurationFormName.getTextContent();
            
            Node SearchFormNameFreightPayment = (Node) xPath.evaluate("config/LIMA/Search_Form_Name_Freight_Payments", dDoc, XPathConstants.NODE);
            strSearchFormNameFreightPayment = SearchFormNameFreightPayment.getTextContent();
            
            Node CatalogFormName = (Node) xPath.evaluate("config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
            strCatalogFormName = CatalogFormName.getTextContent();
            
            Node FreightPaymentsUpdateFormName = (Node) xPath.evaluate("config/LIMA/Freight_Payments_Update_Form_Name", dDoc, XPathConstants.NODE);
            strFreightPaymentsUpdateFormName = FreightPaymentsUpdateFormName.getTextContent();
            	
            Node SeaFreightPaymentTariffFormName = (Node) xPath.evaluate("config/LIMA/Seafreight_Tariff_Form_Name", dDoc,XPathConstants.NODE);
            strSeaFreightPaymentTariffFormName = SeaFreightPaymentTariffFormName.getTextContent();
            
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getFreightPaymentsXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
           
            nlSearchParamsFreightPayments = (NodeList)xPath1.evaluate("/Freight_Payments/Search_Params", dDoc1, XPathConstants.NODESET);
            nlCatalogParams = (NodeList)xPath1.evaluate("/Freight_Payments/Catalog_Params", dDoc1, XPathConstants.NODESET);
            nlInvoiceGrid = (NodeList)xPath1.evaluate("/Freight_Payments/Invoice_Grid", dDoc1, XPathConstants.NODESET);
            nlContainerCharges = (NodeList)xPath1.evaluate("/Freight_Payments/Container_Charges_Grid", dDoc1, XPathConstants.NODESET);
            nlRateCardDetails = (NodeList)xPath1.evaluate("/Freight_Payments/Rate_Card_PopUp", dDoc1, XPathConstants.NODESET);
            
            Element el;
            for(int i=0; i <= nlSearchParamsFreightPayments.getLength() -1; i++)
             {
                 if (nlSearchParamsFreightPayments.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		 el = (Element) nlSearchParamsFreightPayments.item(i); 
            		 
            		 strInvalidContainer = el.getElementsByTagName("Invalid_Container").item(0).getTextContent();
            		 strInvoiceType = el.getElementsByTagName("Invoice_Type").item(0).getTextContent();
            		 strPendingStatus = el.getElementsByTagName("Status_Pending").item(0).getTextContent();
            		 strAuthorisedStatus = el.getElementsByTagName("Status_Authorised").item(0).getTextContent();
            		 strCarrier = el.getElementsByTagName("Carrier").item(0).getTextContent();
            		 strCarrierType = el.getElementsByTagName("Carrier_Type").item(0).getTextContent();
            		 strInvoiceCurrency = el.getElementsByTagName("Invoice_Currency").item(0).getTextContent();
            		 strROE = el.getElementsByTagName("ROE").item(0).getTextContent();
            		 strComments = el.getElementsByTagName("Comments").item(0).getTextContent();
            		 strChargeType = el.getElementsByTagName("Charge_Type").item(0).getTextContent();
            		 strInvoiceRate = el.getElementsByTagName("Invoice_Rate").item(0).getTextContent();
            		 strPaymentStatusAuthorised = el.getElementsByTagName("Payment_Status_Authorised").item(0).getTextContent();
            		 strPaymentStatusQuery = el.getElementsByTagName("Payment_Status_Query").item(0).getTextContent();
            		 strQueryComment = el.getElementsByTagName("Query_Status_Comment").item(0).getTextContent();
            		 strStatusQuery = el.getElementsByTagName("Status_Query").item(0).getTextContent();
            		 strCY_CY = el.getElementsByTagName("Invoice_Number_CYCY").item(0).getTextContent();
            		 strCFS_CY = el.getElementsByTagName("Invoice_Number_CFSCY").item(0).getTextContent();
            		 strCFS_CFS = el.getElementsByTagName("Invoice_Number_CFSCFS").item(0).getTextContent();
            		 strLoadingCYCY = el.getElementsByTagName("Loading_CYCY").item(0).getTextContent();
            		 strLoadingCFSCY = el.getElementsByTagName("Loading_CFSCY").item(0).getTextContent();
            		 strLoadingCFSCFS = el.getElementsByTagName("Loading_CFSCFS").item(0).getTextContent();
            	}
             }
            Element el1;
            for(int i=0; i<=nlCatalogParams.getLength()-1; i++)
            {
            	if(nlCatalogParams.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		el1 = (Element)nlCatalogParams.item(i);
            		strCatalogChargeDescription = el1.getElementsByTagName("Charge_Description").item(0).getTextContent();
            		strCatalogInvoiceType = el1.getElementsByTagName("Invoice_Type").item(0).getTextContent();
            		strCatalogCarrier = el1.getElementsByTagName("Carrier").item(0).getTextContent();
            		strCatalogCarrierType = el1.getElementsByTagName("Carrier_Type").item(0).getTextContent();
            		strFieldValue = el1.getElementsByTagName("Field_Value").item(0).getTextContent();
            		strDaysFromETD = el1.getElementsByTagName("Days_From_ETD").item(0).getTextContent();
            		strDaysFromETA = el1.getElementsByTagName("Days_From_ETA").item(0).getTextContent();
            		strDaysFromDelivery = el1.getElementsByTagName("Days_From_Delivery").item(0).getTextContent();
            		strDays = el1.getElementsByTagName("No_Days").item(0).getTextContent();
            		strCatalog_Type = el1.getElementsByTagName("Catalog_Type").item(0).getTextContent();
            		
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
    
    public boolean compareTwoStringValuesToSame(String strValue1,String strValue2) 
    {
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
    

    
    @Test(priority = 25)
    public void test_Scn_5_TcNo_1_AccessFreightPayments()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	wait(3000);
    	objAdjunoLIMAFreightPaymentsPOM.callMouseHover(strSearchFormNameFreightPayment, objAdjunoLIMAFreightPaymentsPOM.getLnkTools(), objAdjunoLIMAFreightPaymentsPOM.getLnkFreightPayments());
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strSearchFormNameFreightPayment), true, "*****Scn_5_TcNo_1_Freight payments Search page is  not loaded*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 30)
    public void test_Scn_6_TcNo_1_EnterOneSearchCriteria()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_Mode"), true, "*****test_Scn_6_TcNo_1_Mode field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_Carrier"), true, "*****test_Scn_6_TcNo_1_Carrier field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_CarrierType"), true, "*****test_Scn_6_TcNo_1_Carrier Type field is not cleared in  Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_OriginCountry"), true, "*****test_Scn_6_TcNo_1_Origin Country field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_OriginPort"), true, "*****test_Scn_6_TcNo_1_Origin Port field is not cleared in Feright Search Chevron *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_DestinationCountry"), true, "*****test_Scn_6_TcNo_1_Destination Country field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_DestinationPort"), true, "*****test_Scn_6_TcNo_1_Destination Port field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_Vessel"), true, "*****test_Scn_6_TcNo_1_Vessel field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_ETDFrom"), true, "*****test_Scn_6_TcNo_1_ETD From field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_ETDTo"), true, "*****test_Scn_6_TcNo_1_ETD To field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_ETAFrom"), true, "*****test_Scn_6_TcNo_1_ETA From field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_ETATo"), true, "*****test_Scn_6_TcNo_1_ETA To field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_ForwarderRef"), true, "*****test_Scn_6_TcNo_1_Forwarder Ref field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_6_TcNo_1_Invoice Type field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_6_TcNo_1_Invoice No field is not cleared in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_6_TcNo_1_Status field is not cleared in Feright Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_6_TcNo_1_Invoice Type field is not set to \"Freight\" in Search page in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_6_TcNo_1_Status is not set as Pending in Feright Search Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strMode, strSearchFormNameFreightPayment, "PARAM_Mode"), true, "*****test_Scn_6_TcNo_1_Mode is not set in Feright Search Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect1()), true, "*****test_Scn_6_TcNo_1_Select Chevron is not clicked in Feright Search Chevron*****");
    	
    	List<FreightPayments> list = new ArrayList<FreightPayments>();  
    	list = objAdjunoLIMAFreightPaymentsPOM.getStatus();
    	
    	strMessage = objAdjunoLIMAFreightPaymentsPOM.verifyStaus(strAuthorisedStatus,list);
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "*****_Scn_6_TcNo_1_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 35)
    public void test_Scn_6_TcNo_2_EnterMultipleSearchCriteria()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strCarrier, strSearchFormNameFreightPayment, "PARAM_Carrier"), true, "*****test_Scn_6_TcNo_2_Carrier field is not set in Feright Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strCarrierType, strSearchFormNameFreightPayment, "PARAM_CarrierType"), true, "*****test_Scn_6_TcNo_2_Carrier type field is not set in Feright Select Chevron*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strSearchFormNameFreightPayment, "FORK_FreightPayment_SUT_RefineSearch"), true, "*****test_Scn_6_TcNo_2_Refine Search button is not clicked in Feright Search Chevron*****");
    	
    	for(int i=0;i<=objAdjunoLIMAFreightPaymentsPOM.getLstCarrier().size()-1;i++)
    	{
    		if(objAdjunoLIMAFreightPaymentsPOM.getLstCarrier().get(i).getText().equals(strCarrier))
    		{
    			
    		}
    		else
    		{
    			strMessage = "*****_Scn_6_TcNo_1_Search Creteria is not found in the resultant grid*****";
    		}
    	
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 40)
    public void test_Scn_7_TcNo_4_VerifySearchCreteria()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage = "";
    	
    	wait(10000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_7_TcNo_4_Status is not set as Pending in Feright Select Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strSearchFormNameFreightPayment, "FORK_FreightPayment_SUT_RefineSearch"), true, "*****test_Scn_7_TcNo_4_Refine Search button is not clicked  in Feright Select Chevron*****");
    	strContainerNo = objAdjunoLIMAFreightPaymentsPOM.gettrContainerNo().getText();
    	
    	List<FreightPayments> list = new ArrayList<FreightPayments>();  
    	list = objAdjunoLIMAFreightPaymentsPOM.getStatus();
    	wait(3000);
    	strMessage = objAdjunoLIMAFreightPaymentsPOM.verifyStaus(strAuthorisedStatus,list);
    	objSoftAssert.assertEquals(strMessage.equals(""),true, "*****_Scn_7_TcNo_4_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 45)
    public void test_Scn_7_TcNo_5_VerifyUpdatePage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strReturnMessage = "";
    	
    	lstSearchResults = new ArrayList<FreightPayments>();
        
        if ( objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsResulted() > 0)
        {
            lstSearchResults =  objAdjunoLIMAFreightPaymentsPOM.selectMulitpleProducts(1);
            
         objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()),true,"*****_Scn_7_TcNo_5_Update chevron is not clicked*****");
            
        }
        else
        {
            bSearchResultsProductsFound = false;
              objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****test_Scn_7_TcNo_5_No Products in Search Results. under Select Chevron*****");
        }
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strFreightPaymentsUpdateFormName), true, "*****_Scn_7_TcNo_5_Update page is not loaded*****");
    	
    	if(bSearchResultsProductsFound)
  		  {
    		strReturnMessage = objAdjunoLIMAFreightPaymentsPOM.verifyProductsDataOnGrid(strFreightPaymentsUpdateFormName,"Grid_ContainerCharges",lstSearchResults);
  				
  			  	objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "*****_Scn_7_TcNo_5_"+strReturnMessage+"*****");
  			  
  		  }
  		  else
  			  objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****_Scn_7_TcNo_5_No Products in Search Results.*****");

    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 50)
    public void test_Scn_8_TcNo_2_VerifyChevrons()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	objAdjunoLIMAFreightPaymentsPOM.callMouseHover(strSearchFormNameFreightPayment, objAdjunoLIMAFreightPaymentsPOM.getLnkTools(), objAdjunoLIMAFreightPaymentsPOM.getLnkFreightPayments());
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getChevSearch()), true, "*****_Scn_8_TcNo_2_Search Chevron is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getChevSelect()), true, "*****_Scn_8_TcNo_2_Select Chevron is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate()), true, "*****_Scn_8_TcNo_2_Update Chevron is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getChevComplete()), true, "*****_Scn_8_TcNo_2_Complete Chevron is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strSearchFormNameFreightPayment), true, "*****_Scn_8_TcNo_2_Despatch Search page is not displayed*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 55)
    public void test_Scn_8_TcNo_3_VerifySearchFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_Mode"), true,"*****_Scn_8_TcNo_3_Mode Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_Carrier"), true,"*****_Scn_8_TcNo_3_Carrier Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_CarrierType"), true,"*****_Scn_8_TcNo_3_CarrierType Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_OriginCountry"), true,"*****_Scn_8_TcNo_3_Origin Country Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_OriginPort"), true,"*****_Scn_8_TcNo_3_Origin Port Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_DestinationCountry"), true,"*****_Scn_8_TcNo_3_Destination Country Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_DestinationPort"), true,"*****_Scn_8_TcNo_3_Destination Port Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_Vessel"), true,"*****_Scn_8_TcNo_3_Vessel Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_ETDFrom"), true,"*****_Scn_8_TcNo_3_ETD From Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_ETDTo"), true,"*****_Scn_8_TcNo_3_ETD To Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_ETAFrom"), true,"*****_Scn_8_TcNo_3_ETA From Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_ETATo"), true,"*****_Scn_8_TcNo_3_ETA To Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_ForwarderRef"), true,"*****_Scn_8_TcNo_3_ForwarderRef Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true,"*****_Scn_8_TcNo_3_Invoice Type Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true,"*****_Scn_8_TcNo_3_Invoice No Search field is not displayed in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strSearchFormNameFreightPayment, "PARAM_Status"), true,"*****_Scn_8_TcNo_3_Status Search field is not displayed in Freight Search page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 60)
    public void test_Scn_8_TcNo_4_VerifyUpdateOneContainer()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_Carrier"), true, "*****test_Scn_8_TcNo_4_Carrier field is not cleared in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_CarrierType"), true, "*****test_Scn_8_TcNo_4_Carrier Type field is not cleared in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strPendingStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_8_TcNo_4_Status is not set as pending in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_8_TcNo_4_Invoice Type fiels is not set in Search page in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect()), true, "*****_Scn_8_TcNo_4_Select Chevron is not clicked under Search page in Freight Search page*****");
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickCheckBoxs(objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox()), true, "*****_Scn_8_TcNo_4_Check box is not clicked in resultant grid under  despatch Select page*****");//Change it later
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****_Scn_8_TcNo_4_Update chevron is not clicked under Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strFreightPaymentsUpdateFormName), true, "*****_Scn_8_TcNo_4_ Update page is not loaded*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 65)
    public void test_Scn_8_TcNo_5_VerifyUpdatePageGrids()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage="";
    	String strErrorMessage = "";
    	 
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "Panel1"), true, "*****test_Scn_8_TcNo_5_Summary Grid is not displayed in Despatch Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "Grid_Invoice"), true, "*****test_Scn_8_TcNo_5_Invoice Grid is not displayed in Despatch Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "Grid_ContainerCharges"), true, "*****test_Scn_8_TcNo_5_Container Charges Grid is not displayed in Despatch Update Chevron*****");
    	
    	//verify fields in summary section
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "General_Vessel"), true, "*****test_Scn_8_TcNo_5_Vessel field is not displayed in summary section in Despatch Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "General_Carrier"), true, "*****test_Scn_8_TcNo_5_Carrier field is not displayed in summary section in Despatch Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "General_Voyage"), true, "*****test_Scn_8_TcNo_5_Voyage field is not displayed in summary section in Despatch Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "General_NoContainers"), true, "*****test_Scn_8_TcNo_5_NoContainers field is not displayed in summary section in Despatch Update Chevron*****");
    	
    	//verify fields in Invoice Grid

        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMAFreightPaymentsPOM.getCaptionsList(strFreightPaymentsUpdateFormName,"Grid_Invoice");
                
        strMessage = objAdjunoLIMAFreightPaymentsPOM.verifyCaptionsONGrid(list,nlInvoiceGrid,9);
               
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_Scn_8_TcNo_5_Invoice grid Caption: "+strMessage+"******");
        
        //verify mandatory fields
        strErrorMessage = objAdjunoLIMAFreightPaymentsPOM.getValidationMessageGridElement(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceNumber");
        if(!strErrorMessage.equals("") )
    	{
    		if(strErrorMessage.contains("&#39;"))
    		{
    			strErrorMessage = strErrorMessage.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strErrorMessage.equalsIgnoreCase("> 'Invoice Number' is a required field"), true, "*****test_Scn_8_TcNo_5_'> Invoice Number is the required field' message is not displayed*****");
    		}	
    	}
        else{
    		strErrorMessage = "*****test_Scn_8_TcNo_5_"+strErrorMessage + "validation message is not dispalyed*****";
    		objSoftAssert.assertEquals(strErrorMessage.equals(""), true,"*****test_Scn_8_TcNo_5_"+strErrorMessage+"*****");
    	}
        
        //Enter Invoice Number
        strInvoiceNumber = objAdjunoLIMAFreightPaymentsPOM.getRandomString();
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceNumber", strInvoiceNumber), true, "*****test_Scn_8_TcNo_5_Invoice number is not set*****");
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceNumber").equals(strInvoiceNumber), true, "*****test_Scn_8_TcNo_5_Invoice Number is wrong*****");
        
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 65)
    public void test_Scn_8_TcNo_6_UpdateFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceCurrency", strInvoiceCurrency), true, "*****test_Scn_8_TcNo_6_Invoice Currency is not set*****");
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "ROE", strROE), true, "*****test_Scn_8_TcNo_6_ROE is not set*****");
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "Comments", strComments), true, "*****test_Scn_8_TcNo_6_Comments is not set*****");
        strInvoiceValue = objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceValue");
        
        //click on tariff hyperlink
//        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickLinkUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkTariff()), true, "*****test_Scn_8_TcNo_6_Tariff hyper link is clicked*****");
//        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strSeaFreightPaymentTariffFormName), true, "*****test_Scn_8_TcNo_6_Seafreight Tariff page is not displayed*****");
 //        objAdjunoLIMAFreightPaymentsPOM.closeWindow1();
        		 
        ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMAFreightPaymentsPOM.getCaptionsList(strFreightPaymentsUpdateFormName,"Grid_ContainerCharges");
                
        strMessage = objAdjunoLIMAFreightPaymentsPOM.verifyCaptionsONGrid(list,nlContainerCharges,13);
               
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_Scn_8_TcNo_6_ContainerCharges grid Caption: "+strMessage+"******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 70)
    public void test_Scn_8_TcNo_7_VerifyRateCard()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isHyperlinkPresent(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkRateCard()), true, "*****test_Scn_8_TcNo_7_Rate Card hyperlink is not clicked in Container Charges Grid under Freight Update Chevron*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard"), true, "*****test_Scn_8_TcNo_7_Rate card pop is not displayed after clicking Rate Card hyperlink in Container Charges Grid under Freight Update Chevron*****");
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMAFreightPaymentsPOM.getCaptionsList(strFreightPaymentsUpdateFormName,"Grid_HiddenRateCard");
                
        strMessage = objAdjunoLIMAFreightPaymentsPOM.verifyCaptionsONGrid(list,nlRateCardDetails,5);
               
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_Scn_8_TcNo_7_Rate Card grid Caption: "+strMessage+"******");
        
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getBtnRateCardAddRow()), true, "*****test_Scn_8_TcNo_7_Add Row button is not displayed in Rate Card details popup*****");
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getBtnRateCardDeleteRow()), true, "*****test_Scn_8_TcNo_7_ Delete Row button is not displayed in Rate Card details popup*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "OKRateCard"), true, "*****test_Scn_8_TcNo_7_ Ok Button is not displayed in Rate Card details popup*****");
        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strFreightPaymentsUpdateFormName, "CancelRateCard"), true, "*****test_Scn_8_TcNo_7_Cancel Button is not displayed in Rate Card details popup*****");
        
    	objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 75)
    public void test_Scn_8_TcNo_8_VerifyAddRowFunctionInRateCard()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	int Rows;
    		
    	Rows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnRateCardAddRow()), true, "*****test_Scn_8_TcNo_8_Add Row button is not clicked in Rate Card Details popup*****");
    	AddRows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	objSoftAssert.assertEquals(AddRows == Rows+1, true, "*****test_Scn_8_TcNo_8_Row is not added*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", AddRows-1, "Hidden_ChargeType", strChargeType), true, "*****test_Scn_8_TcNo_8_Charge Type is not set in Rate Card pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", AddRows-1, "Hidden_InvoiceRate", strInvoiceRate), true, "*****test_Scn_8_TcNo_8_Invoice Rate is not set in Rate Card pop up*****");
   
    	//objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkFieldIsReadOnlyInGrid(strFreightPaymentsUpdateFormName,"Grid_HiddenRateCard",AddRows-1,"Hidden_No",""),true,"*****test_Scn_8_TcNo_8_No field is not in Read only mode*****");
    	objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 80)
    public void test_Scn_8_TcNo_9_VerifyDeleteRowFunctionInRateCard()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	int Rows;
    	int DeleteRows;
    	
    	Rows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnRateCardDeleteRow()), true, "*****test_Scn_8_TcNo_8_Delete Row button is clicked in Rate Card Details popup*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnYes()), true);
    	DeleteRows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	
    	objSoftAssert.assertEquals(DeleteRows == Rows-1, true, "*****test_Scn_8_TcNo_8_Row is not deleted in Rate card details popup*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 85)
    public void test_Scn_8_TcNo_10_VerifyCancelButtonFunction()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strFreightPaymentsUpdateFormName,"CancelRateCard"), true, "*****test_Scn_8_TcNo_10_Cancel Button is not clicked in Rate Card Detils popup");
    	objSoftAssert.assertEquals(strInvoiceValue.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceValue")), true, "*****test_Scn_8_TcNo_10_Invoice value is wrong in Invoice Grid in Freight Update Chevron after updating the invoice value in rate card popup*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 90)
    public void test_Scn_8_TcNo_11_VerifyOKButtonFunction()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	Float InvoiceValue = Float.parseFloat(strInvoiceValue);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isHyperlinkPresent(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkRateCard()), true, "*****test_Scn_8_TcNo_11_Rate Card HyperLink is not clicked in Container Charges grid in freight Update Chevron*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnRateCardAddRow()), true, "*****test_Scn_8_TcNo_11_Add Row button is not clicked in Rate Card Details popup*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", AddRows-1, "Hidden_ChargeType",strChargeType), true, "*****test_Scn_8_TcNo_11_Charge Type field value is not set in Rate Card pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", AddRows-1, "Hidden_InvoiceRate", strInvoiceRate), true, "*****test_Scn_8_TcNo_11_Invoice Rate field value is not set in Rate Card pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strFreightPaymentsUpdateFormName, "OKRateCard"), true, "*****test_Scn_8_TcNo_11_OK Button is not clicked in Rate Card popup*****");
    	objSoftAssert.assertEquals(InvoiceValue < Float.parseFloat(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceValue")), true, "*****test_Scn_8_TcNo_11_Invoice value is wrong in Invoice Grid in Freight Update Chevron after updating the invoice value in rate card popup*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 95)
    public void test_Scn_9_TcNo_12_VerifyAuthorisationProcess()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_ContainerCharges", 0, "Charges_PaymentStatus", strPaymentStatusAuthorised), true, "*****test_Scn_8_TcNo_12_Payment Status is not set as Authorised in Conatiner Charges Grid under Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevComplete1()), true, "*****test_Scn_8_TcNo_12_Complete Chevron is not clicked*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 100)
    public void test_Scn_8_TcNo_13_VerifyAuthorisedContainerInSearchPage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	String strMessage = "";
    	
    	wait(6000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceNumber, strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_8_TcNo_13_Invoice Number is not set in Freight Search Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_8_TcNo_13_Status is not set in Freight Search Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_8_TcNo_13_Invoice Type is not set in Freight Search Page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect1()), true, "*****test_Scn_8_TcNo_13_Select Chevron is clicked under Freight Search page*****");
    	try{
    	objSoftAssert.assertEquals(strContainerNo.equals(objAdjunoLIMAFreightPaymentsPOM.getTrContainerNo().getText()), true, "*****test_Scn_8_TcNo_13_Container Number do not match in the resultant grid under Select chevron*****");
    	objSoftAssert.assertEquals(strAuthorisedStatus.equals(objAdjunoLIMAFreightPaymentsPOM.getTrStatus().getText()), true, "*****test_Scn_8_TcNo_13_Staus is not Authorised in the resultant grid under Select chevron*****");
    	}
    	catch(NoSuchElementException e)
    	{
    		strMessage = "*****test_Scn_8_TcNo_13_Updated Container is not displayed under Authorised Status in Select Chevron*****";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
    	objSoftAssert.assertAll();
    }
    
//    @Test(priority = 105)
//    public void test_Scn_8_TcNo_14_VerifyContainerStatus()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
//    	
//    	//setting payment status as query
//    	String strErrorMessage = "";
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickCheckBox(objAdjunoLIMAFreightPaymentsPOM.getTrCheckBox()), true, "*****test_Scn_8_TcNo_14_Check box is not selected under Search page*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****test_Scn_8_TcNo_14_Update Chevron is not clicked*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_ContainerCharges", 0, "Charges_PaymentStatus", strPaymentStatusQuery), true, "*****test_Scn_8_TcNo_14_Payment Status is not set to Query*****");
//    	strErrorMessage = objAdjunoLIMAFreightPaymentsPOM.getValidationMessageGridElement(strFreightPaymentsUpdateFormName, "Grid_ContainerCharges", 0, "Charges_Comments");
//        if(!strErrorMessage.equals("") )
//    	{
//    		if(strErrorMessage.contains("&#39;"))
//    		{
//    			strErrorMessage = strErrorMessage.replace("&#39;", "'");
//    			objSoftAssert.assertEquals(strErrorMessage.equalsIgnoreCase("> 'Comments' is a required field"), true, "*****test_Scn_8_TcNo_14'> Comments is the required field' message is not displayed*****");
//    		}	
//    	}
//        else{
//    		strErrorMessage = "*****test_Scn_8_TcNo_14"+strErrorMessage + "validation message is null*****";
//    		objSoftAssert.assertEquals(strErrorMessage.equals(""), true,"*****test_Scn_8_TcNo_14"+strErrorMessage+"*****");
//    	}
//        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_ContainerCharges", 0, "Charges_Comments", strQueryComment), true, "*****test_Scn_8_TcNo_14Comments field is not set in Container charges grid*****");
//        objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "Comments", strQueryComment), true, "*****test_Scn_8_TcNo_14Comments field is not set in Invoice grid*****");
//        objSoftAssert.assertAll(); 	
//    }
    
//    @Test(priority = 110)
//    public void test_Scn_8_TcNo_15_VerifyContainerUnderQueryStatus()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevComplete1()), true, "*****test_Scn_8_TcNo_15_Complete chevron is not clicked*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strStatusQuery, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_8_TcNo_15_Status is not set to Query under Serach page*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect1()), true, "*****test_Scn_8_TcNo_15_Select Chevron is clicked*****");
//    	
//    	objSoftAssert.assertEquals(strContainerNo.equals(objAdjunoLIMAFreightPaymentsPOM.getTrContainerNo().getText()), true, "*****test_Scn_8_TcNo_15_Container Number do not match*****");
//    	objSoftAssert.assertEquals(strAuthorisedStatus.equals(objAdjunoLIMAFreightPaymentsPOM.getTrStatus().getText()), true, "*****test_Scn_8_TcNo_15_Staus is not Query*****");
//    	objSoftAssert.assertAll();
//    }
    
//    @Test(priority = 115)
//    public void test_Scn_8_TcNo_16_SelectMultipleContainers()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_8_TcNo_16_Invoice Type field is not set*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strPendingStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_8_TcNo_16_Status field is not set*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_8_TcNo_16_Invoice No field is not cleared*****");
//    	objAdjunoLIMAFreightPaymentsPOM.selectMulitpleProducts(2);
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****test_Scn_8_TcNo_16_Update Chevron is not clicked*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strFreightPaymentsUpdateFormName), true, "*****test_Scn_8_TcNo_16_Update page is not loaded*****");
//    	objSoftAssert.assertAll();
//    	
//    }
    
    @Test(priority = 117)
    public void test_Scn_8_TcNo_23_VerifyTheRatePopUpAfterSelectionACY_CYShipment()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue("", strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_8_TcNo_23_CYCY Shipment Invoice Number is not Cleared in Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_8_TcNo_23_Status is not set as Authorised in Search Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_8_TcNo_23_Invoice Type is not set to Freight in Search Page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect1()), true, "*****test_Scn_8_TcNo_23_Select Chevron is not clicked in Freight Search Page*****");
    	for(int i = 0;i<=objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().size()-1; i++)
    	{
    		if(objAdjunoLIMAFreightPaymentsPOM.getLstLoading().get(i).getText().equals(strLoadingCYCY))
    		{
    			objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().get(i).click();
    			break;
    		}
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickCheckBox(objAdjunoLIMAFreightPaymentsPOM.getTrCheckBox()), true, "*****test_Scn_8_TcNo_23_Check box is not clicked in the resulant grid under Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****test_Scn_8_TcNo_23_Update Chevron is not clicked in Select page*****");
    	wait(2000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickLinkUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkRateCard()), true, "*****test_Scn_8_TcNo_23_Rate Card hyperlink is not clicked in Container Charges Grid under Update chevron*****");
    	
    	
    }
    
    @Test(priority = 120)
    public void test_Scn_9_TcNo_1_VerifyTheProjectedValuebySelectingaSingleShipmentCYCY()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	Double InvoiceRate = 0.00;
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	objAdjunoLIMAFreightPaymentsPOM.callMouseHover(strSearchFormNameFreightPayment, objAdjunoLIMAFreightPaymentsPOM.getLnkTools(), objAdjunoLIMAFreightPaymentsPOM.getLnkFreightPayments());
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_9_TcNo_1_Invoice Type field is not set in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_9_TcNo_1_Status field is not set in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue("",strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_9_TcNo_1_Invoice Number field is not set in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect()), true, "*****test_Scn_9_TcNo_1_Select Chevron is not clicked in Freight Search page*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strSearchFormNameFreightPayment, "FORK_FreightPayment_SUT_RefineSearch"),true, "*****test_Scn_9_TcNo_1_Refine Search button is not clicked in Freight Search page*****");
    	for(int i = 0;i<=objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().size()-1; i++)
    	{
    		if(objAdjunoLIMAFreightPaymentsPOM.getLstLoading().get(i).getText().equals(strLoadingCYCY))
    		{
    			objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().get(i).click();
    			break;
    		}
    	}
//    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickCheckBox(objAdjunoLIMAFreightPaymentsPOM.getTrCheckBox()), true, "*****test_Scn_9_TcNo_1_Check box is not selected under Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****test_Scn_9_TcNo_1_Update Chevron is not clicked in Freight Select Chevron*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isHyperlinkPresent(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkRateCard()), true, "*****test_Scn_9_TcNo_1_Rate Card hyperLink is not clicked in Container Charges Grid under Update Chevron*****");
    	ratecardRows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	for(int i = 0; i<=ratecardRows-1;i++)
    	{
    		InvoiceRate = Double.parseDouble(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", i, "Hidden_InvoiceRate"))+ InvoiceRate; 
    		
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strFreightPaymentsUpdateFormName, "OKRateCard"), true, "*****test_Scn_9_TcNo_1_OK Button is not clicked in Rate Card popup*****");
    	objSoftAssert.assertEquals(InvoiceRate == Double.parseDouble(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceValue")), true, "*****test_Scn_9_TcNo_1_The Sum of Invoice Rate is not displayed in Invoice Value in Invoice Grid under Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevComplete1()), true, "*****test_Scn_9_TcNo_1_Complete Chevron is not clicked in Update Chevron*****");
    	objSoftAssert.assertAll();
    }
    
    /*@Test(priority = 125)
    public void test_Scn_9_TcNo_2_VerifyTheProjectedValueBySelectingASingleShipmentCFSCY()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	Double InvoiceRate = 0.00;
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_9_TcNo_2_Invoice Number field is not set in Freight Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_9_TcNo_2_Status field is not set in Freight Search Page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue("",strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_9_TcNo_2_Invoice Number field is not set in Freight Search page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect1()),true, "*****test_Scn_9_TcNo_2_Select Chevron is not clicked in Freight Search page*****");
    	for(int i = 0;i<=objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().size()-1; i++)
    	{
    		if(objAdjunoLIMAFreightPaymentsPOM.getLstLoading().get(i).getText().equals(strLoadingCFSCY))
    		{
    			objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().get(i).click();
    			break;
    		}
    	}
    	//objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickCheckBox(objAdjunoLIMAFreightPaymentsPOM.getTrCheckBox()), true, "*****test_Scn_9_TcNo_2_Check box is not selected under Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****test_Scn_9_TcNo_2_Update Chevron is not clicked in Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isHyperlinkPresent(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkRateCard()), true, "*****test_Scn_9_TcNo_2_Rate Card hyperLink is not clicked in Container Charges Grid under Update Chevron*****");
    	
    	wait(3000);
    	ratecardRows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard",ratecardRows-1, "Hidden_InvoiceRate", strInvoiceRate), true, "*****test_Scn_9_TcNo_2_Invoice Rate value is not set in Rate Card Details popup*****");
    	
    	for(int i = 0; i<=ratecardRows-1;i++)
    	{
    		InvoiceRate = Double.parseDouble(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", i, "Hidden_InvoiceRate"))+ InvoiceRate; 
    	}
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strFreightPaymentsUpdateFormName, "OKRateCard"), true, "*****test_Scn_9_TcNo_2_OK Button is not clicked in Rate Card popup*****");
    	objSoftAssert.assertEquals(InvoiceRate == Double.parseDouble(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceValue")), true, "*****test_Scn_9_TcNo_2_The Sum of Invoice Rate is not displayed in Invoice Value in Invoice grid under Update Chevron*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevComplete1()), true, "*****test_Scn_9_TcNo_1_Complete Chevron is not clicked under Upade Chevron*****");
    	objSoftAssert.assertAll();
    }*/
    
    /*@Test(priority = 130)
    public void test_Scn_9_TcNo_3_VerifyTheProjectedValueBySelectingASingleShipmentCFS_CFS()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	Double InvoiceRate = 0.00;
    	wait(5000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strInvoiceType, strSearchFormNameFreightPayment, "PARAM_InvoiceType"), true, "*****test_Scn_9_TcNo_3_Invoice Number field is not set *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strAuthorisedStatus, strSearchFormNameFreightPayment, "PARAM_Status"), true, "*****test_Scn_9_TcNo_3_Status field is not set*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue("",strSearchFormNameFreightPayment, "PARAM_InvoiceNo"), true, "*****test_Scn_9_TcNo_3_Invoice Number field is not set *****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevSelect1()),true, "*****test_Scn_9_TcNo_3_Select Chevron is not clicked*****");
    	for(int i = 0;i<=objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().size()-1; i++)
    	{
    		if(objAdjunoLIMAFreightPaymentsPOM.getLstLoading().get(i).getText().equals(strLoadingCFSCFS))
    		{
    			objAdjunoLIMAFreightPaymentsPOM.getLstCheckBox().get(i).click();
    			break;
    		}
    	}
    	//objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickCheckBox(objAdjunoLIMAFreightPaymentsPOM.getTrCheckBox()), true, "*****test_Scn_9_TcNo_3_Check box is not selected under Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevUpdate1()), true, "*****test_Scn_9_TcNo_3_Update Chevron is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isHyperlinkPresent(objAdjunoLIMAFreightPaymentsPOM.getHyperLnkRateCard()), true, "*****test_Scn_9_TcNo_3_Rate Card hyperLink is not clicked*****");
    	wait(2000);
    	ratecardRows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard",ratecardRows-1, "Hidden_InvoiceRate", strInvoiceRate), true, "*****test_Scn_9_TcNo_2_Cost is not added in Invoice rate field*****");
    	for(int i = 0; i<=ratecardRows-1;i++)
    	{
    		InvoiceRate = Double.parseDouble(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_HiddenRateCard", i, "Hidden_InvoiceRate"))+ InvoiceRate; 	
    	}
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButton(strFreightPaymentsUpdateFormName, "OKRateCard"), true, "*****test_Scn_9_TcNo_3_OK Button is not clicked in Rate Card popup*****");
    	objSoftAssert.assertEquals(InvoiceRate == Double.parseDouble(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strFreightPaymentsUpdateFormName, "Grid_Invoice", 0, "InvoiceValue")), true, "*****test_Scn_9_TcNo_3_The Sum of Invoice Rate is not displayed in Invoice Value*****");
    	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickChevron(objAdjunoLIMAFreightPaymentsPOM.getChevComplete1()), true, "*****test_Scn_9_TcNo_3_Complete Chevron is not clicked*****");
    	objSoftAssert.assertAll();
    }*/
   
    @Test(priority = 135)
    public void Test_Scn1_TCNo_1_VerifyFreightPaymentsConfigurationPageDisplayed()
    {
    	 SoftAssert objSoftAssert = new SoftAssert();
    	 objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	 
    	 wait(3000);
    	 objAdjunoLIMAFreightPaymentsPOM.callMouseHover(strSearchFormNameFreightPayment, objAdjunoLIMAFreightPaymentsPOM.getLnkTools(), objAdjunoLIMAFreightPaymentsPOM.getLnkCatalog());
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strCatalogFormName), true, "*****Scn1_TCNo_1_Catalog page is not loaded*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickLinkUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getLnkLightHouse()), true, "*****Scn1_TCNo_1_LightHouseLink is not clicked in \"Welcome to Catalog\" page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strCatalogFormName, "Param_Name"), true, "*****test ID:14_\"Item field\" is not cleared under Catalog Item Page******");
     	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strCatalogFormName, "Param_Type"), true, "*****test ID:14_\"Type field\" is not cleared under Catalog Item Page******");
     	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strCatalogFormName, "Param_Description"), true, "*****test ID:14_\"Description field\" is not cleared under Catalog Item Page******");
     	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"), true, "*****test ID:14_\"Associated Item field\" is not cleared under Catalog Item Page******");
     	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"), true, "*****test ID:14_\"Associated Type field\" is not cleared under Catalog Item Page******");
     	 
     	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValue(strCatalog_Type, strCatalogFormName, "Param_Type"), true, "*****Scn1_TCNo_1_In Catalog Items page \"Type\" is not set as \"Configuration\"*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn1_TCNo_1_Apply button is not clicked in \"Catalog Items page'\" page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickLinkUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getLnkConfiguration()), true, "*****Scn1_TCNo_1_Configuration link is not clicked in \"Catalog Items\" page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn1_TCNo_1_Apply button is not clicked in \"View Configuration Item 'Configuration'\" page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strCatalogConfigurationFormName), true, "*****Scn1_TCNo_1_\"Edit an Item page\" is not loaded*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFinance()), true, "*****Scn1_TCNo_1_Finance tab is not clicked in \"Edit an Item page\"*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFreightPayment()), true, "*****Scn1_TCNo_1_FreightPayment tab is not clicked in \"Edit an Item page\"*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strCatalogConfigurationFormName), true,"*****Scn1_TCNo_1_Configuration page is not loaded*****");
    	 objSoftAssert.assertAll();
    }
    
    @Test(priority = 140)
    public void Test_Scn1_TCNo_2_VerifyGridsInFreightPaymentsConfigurationPage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
   	 	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strCatalogConfigurationFormName, "Grid_ChargesByCarrier"), true, "*****Scn1_TCNo_2_Charge By Carrier grid is not displayed under Freight Payments tab*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strCatalogConfigurationFormName, "Grid_InvoicesDue"), true, "*****Scn1_TCNo_2_Invoice Due grid is not displayed under Freight Payments tab*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.checkDoesFieldsExist(strCatalogConfigurationFormName, "Grid_InvoiceType"), true, "*****Scn1_TCNo_2_Invoice Type grid is not displayed under Freight Payments tab*****");
   	 	objSoftAssert.assertAll();
    }
    
    @Test(priority = 145)
    public void Test_Scn2_TcNo_1_SetChargesByCarrierGridFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
   	 	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnAddRowChargeByCarrier()), true, "*****Scn2_TCNo_1_Add button is not clicked inside Charge By Carrier grid under Freight Payments Tab*****");
   	 	int Rows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_ChargesByCarrier");
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_ChargeDescription",strCatalogChargeDescription ), true, "*****Scn2_TCNo_1_Charge Description field is not set in Charges by Carrier grid under Freight Payments Tab*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_InvoiceType", strCatalogInvoiceType), true, "*****Scn2_TCNo_1_Invoice type field is not set in Charges by Carrier grid under Freight Payments Tab*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_Carrier", strCatalogCarrier), true, "*****Scn2_TCNo_1_Carrier field is not set in Charges by Carrier grid under Freight Payments Tab*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_CarrierType", strCatalogCarrierType), true, "*****Scn2_TCNo_1_Carrier Type field is not set in Charges bu Carrier grid under Freight Payments Tab*****");
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn2_TCNo_1_Apply Button is not clicked under Freight Payments Tab*****");
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn2_TCNo_1_Apply Button is not clicked under \"View Configuration Item 'Configuration'\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFinance()), true, "*****Scn2_TCNo_1_Finance button is not clicked under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFreightPayment()), true, "*****Scn2_TCNo_1_FreightPayment button is not clicked under \"Edit an Item\" page*****");
   	 	wait(2000);
   	 	
   	 	objSoftAssert.assertEquals(strCatalogChargeDescription.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_ChargeDescription")), true, "*****Scn2_TCNo_1_Charge Description field value is not saved under Freight Payments tab*****");
   	 	objSoftAssert.assertEquals(strCatalogInvoiceType.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_InvoiceType")), true, "*****Scn2_TCNo_1_Invoice type field value is not saved Freight Payments tab*****");
   	 	objSoftAssert.assertEquals(strCatalogCarrier.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_Carrier")), true, "*****Scn2_TCNo_1_Carrier field value is not saved Freight Payments tab*****");
   	 	objSoftAssert.assertEquals(strCatalogCarrierType.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_CarrierType")), true, "*****Scn2_TCNo_1_Carrier Type field value is not saved Freight Payments tab*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn2_TCNo_1_Apply Button is not clicked under Freight Payments tab*****");
   	 	objSoftAssert.assertAll();
    }
    
    @Test(priority = 150)
    public void Test_Scn2_TcNo_2_VerifyChargeByCarrierGridFieldsAreConfigurable()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
   	 	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
   	 	
   	 
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn2_TCNo_2_Apply button is not clicked under \"View Configuration Item 'Configuration'\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.verifyPageIsLoaded(strCatalogConfigurationFormName), true, "*****Scn2_TCNo_2_\"Edit an Item\" page is not loaded*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFinance()), true, "*****Scn2_TCNo_2_Finance button is not clicked under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFreightPayment()), true, "*****Scn2_TCNo_2_FreightPayment button is not clicked under \"Edit an Item\" page*****");
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnAddRowChargeByCarrier()), true, "*****Scn2_TCNo_2_Add button is not clicked inside Charge By Carrier grid under \"Edit an Item\" page*****");
   	 	int Rows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_ChargesByCarrier");
   	 
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_ChargeDescription",strCatalogChargeDescription ), true, "*****Scn2_TCNo_2_Charge Description field is not set as * in Charges by Carrier grid under \"Edit an Item\" page*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_InvoiceType", strFieldValue), true, "*****Scn2_TCNo_2_Invoice type field is not set as * in Charges by Carrier grid under \"Edit an Item\" page*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_Carrier", strFieldValue), true, "*****Scn2_TCNo_2_Carrier field is not set as * in Charges by Carrier grid under \"Edit an Item\" page*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_CarrierType", strFieldValue), true, "*****Scn2_TCNo_2_Carrier Type field is not set as * in Charges bu Carrier grid under \"Edit an Item\" page*****");	
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn2_TCNo_2_Apply Button is not clicked under \"Edit an Item\" page*****");
	 	
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn2_TCNo_2_Apply Button is not clicked under \"View Configuration Item 'Configuration'\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFinance()), true, "*****Scn2_TCNo_2_Finance tab is not clicked under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFreightPayment()), true, "*****Scn2_TCNo_2_FreightPayment tab is not clicked under \"Edit an Item\" page*****");
   	 	
   	 	objSoftAssert.assertEquals(strFieldValue.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_InvoiceType")), true, "*****Scn2_TCNo_2_Invoice type field value is not saved under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(strFieldValue.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_Carrier")), true, "*****Scn2_TCNo_2_Carrier field value is not saved under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(strFieldValue.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_CarrierType")), true, "*****Scn2_TCNo_2_Carrier Type field value is not saved under \"Edit an Item\" page*****");
	 	objSoftAssert.assertAll();
    }
    
    @Test(priority = 155)
    public void Test_Scn2_TcNo_4_VerifyDeleteRowButtonInChargeByCarrierGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
   	 	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
   	 	int Rows; 
   	 	int Rows1;
   	 	
   	 	Rows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_ChargesByCarrier");
   	 	
   	 	//verifying confirmation msg is displayed
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_InvoiceType",strFieldValue), true, "*****Scn2_TcNo_4_Invoice type field value is not saved under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnDeleteRowChargeByCarrier()), true, "*****Scn2_TcNo_4_Delete button is not clicked in Charges by Carrier grid under \"Edit an Item\" page*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getConfirmationMsg()), true, "*****Scn2_TcNo_4_Delete Row Confirmation pop up is not displayed*****");
   	 	String strMessage = objAdjunoLIMAFreightPaymentsPOM.getWebElementCellValue(objAdjunoLIMAFreightPaymentsPOM.getConfirmationMsg());
   	 	objSoftAssert.assertEquals(strMessage.equals("Are you sure you want to delete the selected row?"), true, "*****Scn2_TcNo_4_Delete Row Confirmation message is wrong*****");
   	 	
   	 	//verifying the functionality of NO button in pop up
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnNo()), true, "*****Scn2_TcNo_4_No button is not clicked in Confirmation Message pop up*****");
   	 	Rows1 = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_ChargesByCarrier");
   	 	objSoftAssert.assertEquals(Rows1 == Rows, true, "*****Scn2_TcNo_4_The No button has Issues*****");
   	 	
   	 	//verifying the functionality of YES Button in pop up
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_ChargesByCarrier", Rows-1, "FP_InvoiceType",strFieldValue), true, "*****Scn2_TcNo_4_Invoice type field value is not saved*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnDeleteRowChargeByCarrier()), true, "*****Scn2_TcNo_4_Delete button is not clicked in Charges by Carrier grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnYes()), true, "*****Scn2_TcNo_4_Yes button is not clicked in Confirmation Message pop up*****"); 
   	 	Rows1 = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_ChargesByCarrier");
   	 	objSoftAssert.assertEquals(Rows1 == Rows-1, true, "*****Scn2_TcNo_4_The row is not deleted in Charge by Carrier Grid under Freight Grid*****");
   	 	objSoftAssert.assertAll(); 	
    }
    
    @Test(priority = 160)
    public void Test_Scn3_TcNo1_VerifyConfigurableFieldsInInvoiceDueGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
   	 	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
   	 	
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnAddRowInvoiceDue()), true, "*****Scn3_Tcn1_Add Row button is not clicked in Invoice Due Grid*****");
   	 	invoiceDueRows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_InvoicesDue");
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "InvoiceDue_InvoiceType", strCatalogInvoiceType), true, "*****Scn3_Tcn1_Invoice type is not set in Invoice due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "InvoiceDue_Carrier", strCatalogCarrier), true, "*****Scn3_Tcn1_Carrier is not set in invoice due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "InvoiceDue_CarrierType", strCatalogCarrierType), true, "*****Scn3_Tcn1_Carrier type is not set in invoice due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "DaysFromETD", strDaysFromETD), true, "*****Scn3_Tcn1_Days From ETD is not set in invoice due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "DaysFromETA", strDaysFromETA), true, "*****Scn3_Tcn1_Days From ETA is not set in invoice due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "DaysFromDelivery", strDaysFromDelivery), true, "*****Scn3_Tcn1_Days From Delivery is not set in invoice due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn3_Tcn1_Apply button is not clicked for Invoice due button*****");
   	 	
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnApply()), true, "*****Scn3_TCNo_1_Apply Button is not clicked*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFinance()), true, "*****Scn3_TCNo_1_Finance button is not clicked*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnFreightPayment()), true, "*****Scn3_TCNo_1_FreightPayment button is not clicked*****");
   	 	
	 	objSoftAssert.assertEquals(strCatalogInvoiceType.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "InvoiceDue_InvoiceType")), true, "*****Scn3_TCNo_1_Invoice type field value is not saved in Invoice due grid*****");
   	 	objSoftAssert.assertEquals(strCatalogCarrier.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "InvoiceDue_Carrier")), true, "*****Scn3_TCNo_1_Carrier field value is not saved in Invoice due grid*****");
   	 	objSoftAssert.assertEquals(strCatalogCarrierType.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_InvoicesDue",invoiceDueRows-1, "InvoiceDue_CarrierType")), true, "*****Scn3_TCNo_1_Carrier Type field value is not saved in Invoice due grid*****");
   	 	objSoftAssert.assertEquals(strDaysFromETD.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "DaysFromETD")), true, "*****Scn3_TCNo_1_Days From ETD value is not saved in Invoice due grid***** ");
   	 	objSoftAssert.assertEquals(strDaysFromETA.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "DaysFromETA")), true, "*****Scn3_TCNo_1_Days From ETA value is not saved in Invoice due grid***** ");
   	 	objSoftAssert.assertEquals(strDaysFromDelivery.equals(objAdjunoLIMAFreightPaymentsPOM.getGridCellElementValue(strCatalogConfigurationFormName, "Grid_InvoicesDue", invoiceDueRows-1, "DaysFromDelivery")), true, "*****Scn3_TCNo_1_Days From Delivery value is not saved in Invoice due grid***** ");
   	 	objSoftAssert.assertAll();
   	 	
    }
    
    @Test(priority = 165)
    public void Test_Scn_3_TcNo_4_VerifyDeleteButtonInInvoiceDueGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMAFreightPaymentsPOM = new AdjunoLIMAFreightPaymentsPOM(driver);
    	
    	int Rows; 
   	 	int Rows1;
   	 	
   	 	Rows = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_InvoicesDue");
   	 	
   	 	//verifying confirmation msg is displayed
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", Rows-1, "InvoiceDue_InvoiceType",strFieldValue), true, "*****Scn_3_TcNo_4_Invoice type field value is not saved*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnDeleteRowInvoiceDue()), true, "*****Scn_3_TcNo_4_Delete button is not clicked in Invoice Due grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.isElementPresent(objAdjunoLIMAFreightPaymentsPOM.getConfirmationMsg()), true, "*****Scn_3_TcNo_4_Delete Row Confirmation pop up is not displayed*****");
   	 	String strMessage = objAdjunoLIMAFreightPaymentsPOM.getWebElementCellValue(objAdjunoLIMAFreightPaymentsPOM.getConfirmationMsg());
   	 	objSoftAssert.assertEquals(strMessage.equals("Are you sure you want to delete the selected row?"), true, "*****Scn_3_TcNo_4_Delete Row Confirmation message is wrong*****");
   	 	
   	 	//verifying the functionality of NO button in pop up
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnNo()), true, "*****Scn_3_TcNo_4_No button is not clicked in Confirmation Message pop up*****");
   	 	Rows1 = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_InvoicesDue");
   	 	objSoftAssert.assertEquals(Rows1 == Rows, true, "*****Scn3_TcNo4__The No button has Issues*****");
   	 	
   	 	//verifying the functionality of YES Button in pop up
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.setFieldValueForGridCell(strCatalogConfigurationFormName, "Grid_InvoicesDue", Rows-1, "InvoiceDue_InvoiceType",strFieldValue), true, "*****Scn_3_TcNo_4__Invoice type field value is not saved*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnDeleteRowInvoiceDue()), true, "*****Scn_3_TcNo_4_Delete button is not clicked in Charges by Carrier grid*****");
   	 	objSoftAssert.assertEquals(objAdjunoLIMAFreightPaymentsPOM.clickButtonUsingWebElement(objAdjunoLIMAFreightPaymentsPOM.getBtnYes()), true, "*****Scn_3_TcNo_4_Yes button is not clicked in Confirmation Message pop up*****"); 
   	 	Rows1 = (int) objAdjunoLIMAFreightPaymentsPOM.getNoOfRowsGrid(strCatalogConfigurationFormName, "Grid_InvoicesDue");
   	 	objSoftAssert.assertEquals(Rows1 == Rows-1, true, "*****Scn_3_TcNo_4_The row is not deleted*****");
    	
	 	
   	 	objSoftAssert.assertAll();
    }
    
    @AfterTest()
    public void closeBrowser()
    {
    	driver.close();
    }
    
    
    
    
    
    
    
}
