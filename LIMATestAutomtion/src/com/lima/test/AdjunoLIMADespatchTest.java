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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.Despatch;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMADespatchPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;



public class AdjunoLIMADespatchTest {
	
	WebDriver driver;
	
	long nPage_Timeout = 0;
    String strTestURL;
    
    String strLIMAUserName;
    String strLIMAPassword;
    String strPageTitleDespatch;
    String strSearchFormNameDespatch;
    String strDespatchFormName;
    String strSearchFormNameManifest;
    String strPOStatusFormName;
    
    NodeList nlSearchParamsDespatch;
    NodeList nlLegsGridDespatch;
    NodeList nlContainerGridDespatch;
    NodeList nlSearchParamsManifest;
    NodeList nlInputFieldsDespatch;
    ArrayList<Despatch> lstSearchResults;
    
    Despatch product;
    Despatch Manifest;

    boolean bSearchResultsProductsFound = true;
    String strOriginPort = "";
    String strDestinationPort = "";
    String strMode = "";
    String strCarrier = "";
    String strInvalidCarrier = "";
    String strETDFrom = "";
    String strETDTO = "";
    String strStatus = "";
    String strStatusDespatched = "";
    String strStatusAwaitingTranshipment = "";
    String strVessel = "";
    String strInvalidVessel = "";
    String strContainer = "";
    String strManifestedContainerNumber = "";
    String strInvalidContainer = "";
    String strHBL = "";
    String strVendor = "";
    String strLoading = "";
    String strManifestStatus = "";
    String strManifestPONumber = "";
    
    String strPastETADate = "";
    String strTransShipmentStatusAwaiting = "";
    String strTransShipmentStatusNA = "";
    String strVoyageTypeDespatch = "";
    String strOriginPortDespatch = "";
    String strDestinationPortDespatch = "";
    String strVesselDespatch = "";
    String strVoyageDespatch = "";
    String strCTDDespatch = "";
    String strETADespatch = "";
    String strTransShipmentStatusDespatch = "";
    String strKPIDespatch = "";
    String strproductNo = "";
          
    String strTrackNO = "";
    
    
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMADespatchPOM objAdjunoLIMADespatchPOM;

    
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
            
            Node pageTitleDespatch = (Node) xPath.evaluate("/config/LIMA/Page_Title_Despatch", dDoc, XPathConstants.NODE);
            strPageTitleDespatch = pageTitleDespatch.getTextContent();
            
            Node searchFormNameDespatch = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Despatch", dDoc, XPathConstants.NODE);
            strSearchFormNameDespatch = searchFormNameDespatch.getTextContent();
            
            Node despatchFormName = (Node) xPath.evaluate("/config/LIMA/Despatch_Form_Name", dDoc, XPathConstants.NODE);
            strDespatchFormName = despatchFormName.getTextContent();
            
            Node searchFormNameManifest = (Node) xPath.evaluate("/config/Generic/Search_Form_Name_Manifest", dDoc, XPathConstants.NODE);
            strSearchFormNameManifest = searchFormNameManifest.getTextContent();
            
            Node POStatusFormName = (Node) xPath.evaluate("/config/LIMA/PO_Status_Form_Name", dDoc, XPathConstants.NODE);
            strPOStatusFormName = POStatusFormName.getTextContent();
            
            driver = new FirefoxDriver();
            driver.manage().window().maximize();

            driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

            driver.get(strTestURL);
            
            objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
            
            objAdjunoLIMALoginPOM.setUserName(strLIMAUserName);
            objAdjunoLIMALoginPOM.setPassword(strLIMAPassword);
            objAdjunoLIMALoginPOM.clickLogon();

          	}
        catch (Exception e) {
            e.printStackTrace();
        }
        
        DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
        try{
        	
        	DocumentBuilder builder1 = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getAdjunoLIMADespatchXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            nlSearchParamsDespatch = (NodeList)xPath1.evaluate("/Despatch/SearchParams", dDoc1, XPathConstants.NODESET);
     
            nlLegsGridDespatch = (NodeList) xPath1.evaluate("/Despatch/GridLeg_Despatch", dDoc1, XPathConstants.NODESET);
            nlContainerGridDespatch = (NodeList) xPath1.evaluate("/Despatch/Container_Despatch", dDoc1, XPathConstants.NODESET);
            nlInputFieldsDespatch = (NodeList) xPath1.evaluate("/Despatch/Despatch_Input", dDoc1, XPathConstants.NODESET);
            Element el;
            for(int i=0; i <= nlSearchParamsDespatch.getLength() -1; i++)
             {
                 if (nlSearchParamsDespatch.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		 el = (Element) nlSearchParamsDespatch.item(i); 
            		 	 
            		 strMode = el.getElementsByTagName("Mode").item(0).getTextContent();
            		 
            		 strCarrier = el.getElementsByTagName("Carrier").item(0).getTextContent();
            		 
            		 strInvalidCarrier = el.getElementsByTagName("Invalid_Carrier").item(0).getTextContent();
            		 
            		 strETDTO = el.getElementsByTagName("ETD_To").item(0).getTextContent();
            		 
            		 strStatus = el.getElementsByTagName("Status").item(0).getTextContent();
            		 
            		 strStatusDespatched = el.getElementsByTagName("Status_Despatched").item(0).getTextContent();
            		 
            		 strStatusAwaitingTranshipment = el.getElementsByTagName("Status_Awaiting_Transhipment").item(0).getTextContent();
            		 
            		 strVessel = el.getElementsByTagName("Vessel").item(0).getTextContent();
            		 
            		 strInvalidVessel = el.getElementsByTagName("Invalid_Vessel").item(0).getTextContent();
            		 
            		 strContainer = el.getElementsByTagName("Container").item(0).getTextContent();
            		 
            		 strManifestedContainerNumber = el.getElementsByTagName("Manifested_Container_Number").item(0).getTextContent();
            		 
            		 strInvalidContainer = el.getElementsByTagName("Invalid_Container").item(0).getTextContent();
            		 
            		 strHBL = el.getElementsByTagName("HBL").item(0).getTextContent();
            		 
            		 strVendor = el.getElementsByTagName("Vendor").item(0).getTextContent();
            		 
            		 strLoading = el.getElementsByTagName("Loading").item(0).getTextContent();
            		 
            		 strManifestPONumber = el.getElementsByTagName("PONumber_Manifest").item(0).getTextContent();
            		 
            		 strManifestStatus = el.getElementsByTagName("Status_Manifest").item(0).getTextContent();
            		 
            		 strPastETADate = el.getElementsByTagName("Past_ETA").item(0).getTextContent();
            		 
            		 strTransShipmentStatusAwaiting = el.getElementsByTagName("TransShipment_Status_Awaiting").item(0).getTextContent();
            		 
            		 strTransShipmentStatusNA = el.getElementsByTagName("Transhipment_Status_NA").item(0).getTextContent();
            		 
            		 strVoyageTypeDespatch = el.getElementsByTagName("VoyageType_Despatch").item(0).getTextContent();
            		 
            		 strOriginPortDespatch = el.getElementsByTagName("OriginPort_Despatch").item(0).getTextContent();
            		 
            		 strDestinationPortDespatch = el.getElementsByTagName("DestinationPort_Despatch").item(0).getTextContent();
            		 
            		 strVesselDespatch = el.getElementsByTagName("Vessel_Despatch").item(0).getTextContent();
            		 
            		 strVoyageDespatch = el.getElementsByTagName("Voyage_Despatch").item(0).getTextContent();
            		 
            		 strTransShipmentStatusDespatch = el.getElementsByTagName("TranshipmentStatus_Despatch").item(0).getTextContent();
            		 
            		 strKPIDespatch = el.getElementsByTagName("KPI_Despatch").item(0).getTextContent();
            		 
            		 strCTDDespatch = el.getElementsByTagName("CTD_Despatch").item(0).getTextContent();
            		 
            		 strETADespatch = el.getElementsByTagName("ETA_Despatch").item(0).getTextContent();
            	}
             }
            
        }
        
        catch (Exception e) {
            e.printStackTrace();
        }
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
    
    private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
    
    @Test(priority = 1)
    public void Test_1_accessDespatch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
        
        String strTitle = objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameDespatch,objAdjunoLIMADespatchPOM.getLnkTools(),objAdjunoLIMADespatchPOM.getLnkDespatch());
                
        boolean bFlag = true;
        
        if (isNullOrBlank(strTitle))
            bFlag = false;
        
        if (!(isNullOrBlank(strTitle)))
        {
            if (strTitle.equalsIgnoreCase(strPageTitleDespatch))
                bFlag = true;
            else
                bFlag = false;
        }
        
        objSoftAssert.assertEquals(bFlag, true, "*****test:ID_1_Title of the page is wrong for Despatch tool*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 2)
    public void Test_1_checkChevrons()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesChevronExist(objAdjunoLIMADespatchPOM.getChevSearch()), true,"*****test:ID_1_Search chevron is not found in Despatch Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesChevronExist(objAdjunoLIMADespatchPOM.getChevSelect()), true,"*****test:ID_1_Select chevron is not found in Despatch Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesChevronExist(objAdjunoLIMADespatchPOM.getChevDespatch()), true,"*****test:ID_1_Despatch chevron is not found in Despatch Search page*****");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesChevronExist(objAdjunoLIMADespatchPOM.getChevComplete()), true,"*****test:ID_1_Complete chevron is not found in Despatch Search page*****");
        
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 3)
    public void Test_1_checkSearchParamsFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_02OriginPort"), true,"*****test:ID_1_Origin Port field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_03DestinationPort"), true,"*****test:ID_1_Destination field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_04Mode"), true,"*****test:ID_1_Mode field is not displayed in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_05Carrier"), true,"*****test:ID_1_Carrier field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_06Vessel"), true,"*****test:ID_1_Vessel field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_07ETDFrom"), true,"*****test:ID_1_ETDFrom field is not displayed in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_08ETDTo"), true,"*****test:ID_1_ETDTo field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_09Container"), true,"*****test:ID_1_Container field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_10Housebill"), true,"*****test:ID_1_Housebill field is not displayed in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_13Supplier"), true,"*****test:ID_1_Supplier field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_14Loading"), true,"*****test:ID_1_Loading field is not displayed in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesFieldsExist(strSearchFormNameDespatch, "Param_15Status"), true,"*****test:ID_1_Status field is not displayed in Despatch Search page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 4)
    public void Test_11_verifySingleInvalidValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_02OriginPort"), true,"*****test:ID_11_Origin port field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_03DestinationPort"), true,"*****test:ID_11_Destination port field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_04Mode"), true,"*****test:ID_11_Mode field is not cleared in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_05Carrier"), true,"*****test:ID_11_Carrier field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_06Vessel"), true,"*****test:ID_11_Vessel field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_07ETDFrom"), true,"*****test:ID_11_ETD from field is not cleared in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_08ETDTo"), true,"*****test:ID_11_ETD to field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_09Container"), true,"*****test:ID_11_Container field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_10Housebill"), true,"*****test:ID_11_Housebill field is not cleared in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_13Supplier"), true,"*****test:ID_11_Supplier field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_14Loading"), true,"*****test:ID_11_Loading field is not cleared in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test:ID_11_Status field is not cleared*****");
    	
    	if (!isNullOrBlank(strMode)) {
    		objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strMode, strSearchFormNameDespatch, "Param_04Mode"),true,"*****test:ID_11_The Mode value is not set in Mode Field in Despatch Search page*****");
    	}else{
    		strMessage = strMessage + " Mode is null ";
    	}
    	
    	if (!isNullOrBlank(strStatus)){
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****test:ID_11_The Status value is not set in Status field in Despatch Search page*****");
    	}else{
    		strMessage = strMessage + " status is null ";
    	}
    	
    	if (!isNullOrBlank(strInvalidContainer)){
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strInvalidContainer, strSearchFormNameDespatch, "Param_09Container"),true,"*****test:ID_11_The Container value is not set in Container Field in Despatch Search page*****");
    	}else{
    		strMessage = strMessage + " invalid container is null ";
    	}
    	
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevSelect()), true,"*****test:ID_11_Select Chevron is not clicked in Despatch Search page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtErrMsg()), true,"*****test:ID_11_No items were found message is not displayed when Invalid Container Number is set in Container field in Despatch Select page*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 5)
    public void Test_12_verifyMultiInvalidValue()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage = "";
    	
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_04Mode"), true,"*****test:ID_12_Mode port field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test:ID_12_Status port field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_09Container"), true,"*****test:ID_12_Container field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_05Carrier"), true,"*****test:ID_12_Carrier field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_06Vessel"), true,"*****test:ID_12_Vessel field is not cleared in Despatch Select page*****");
    	
    	
    	if (!isNullOrBlank(strMode)) {
    		objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strMode, strSearchFormNameDespatch, "Param_04Mode"),true,"*****test:ID_12_The Mode value is not set in Mode field in Despatch Select page*****");
    	}else{
    		strMessage = strMessage + " Mode is null ";
    	}
    	
    	if (!isNullOrBlank(strStatus)){
        	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****test:ID_12_The Status value is not set in Status field in Despatch Select page*****");
        }else{
    		strMessage = strMessage + " Status is null ";
    	}
    	
    	if (!isNullOrBlank(strInvalidContainer)){
        	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strInvalidContainer, strSearchFormNameDespatch, "Param_09Container"),true,"*****test:ID_12_The Invalid Container value is not set in Container filed in Despatch Select page*****");
        }else{
    		strMessage = strMessage + " invalid container is null ";
    	}
    	
    	if (!isNullOrBlank(strInvalidCarrier)){
        	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strInvalidCarrier, strSearchFormNameDespatch, "Param_05Carrier"),true,"*****test:ID_12_The Carrier value is not set in Carrier field in Despatch Select page*****");
        }else{
    		strMessage = strMessage + " Invalid Carrier is null ";
    	}
    	
    	if (!isNullOrBlank(strInvalidVessel)){
        	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strInvalidVessel, strSearchFormNameDespatch, "Param_06Vessel"),true,"*****test:ID_12_The Vessel value is not set in Vessel field in Despatch Select page*****");
        }else{
    		strMessage = strMessage + " Invalid Vessel is null ";
    	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true,"*****test:ID_12_Refine Search Button  is not clicked in Despatch Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtErrMsg()), true,"*****test:ID_12_No items were found message is not displayed when multi invalid Search creteria is given in Search field  in Despatch Select page*****");

    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test:ID_12_"+strMessage+"*****");
    	objSoftAssert.assertAll();
    	
    }
     
    @Test(priority = 7)
    public void Test_9_verifyValidSingleSearchCriteria()
    {
    	String strMessage ="";
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test ID:_9_Status  field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_09Container"), true,"*****test ID:_9_Container field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_05Carrier"), true,"*****test ID:_9_Carrier field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_06Vessel"), true,"*****test ID:_9_Vessel field is not cleared in Despatch Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatusDespatched, strSearchFormNameDespatch, "Param_15Status"), true,"*****test ID:_9_Status value is not set in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strContainer, strSearchFormNameDespatch, "Param_09Container"), true,"*****test ID:_9_ Valid Container Number value is not set in Despatch Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true,"*****test ID:_9_Refine Search Button  is not clicked in Despatch Select page*****");
    	
    	wait(3000);
    	strMessage = objAdjunoLIMADespatchPOM.verifySearchCriteria(strSearchFormNameDespatch, "Param_09Container");
    	
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:_9_Error message for Container field: "+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 8)
    public void Test_13_verifyAwaitingStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage="";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_04Mode"), true,"*****test ID:_13_Mode port field is not cleared in Despatch Select page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test ID:_13_Status port field is not cleared in Despatch Select page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_09Container"), true,"*****test ID:_13_Container field is not cleared in Despatch Select page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_05Carrier"), true,"*****test ID:_13_Carrier field is not cleared in Despatch Select page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_06Vessel"), true,"*****test ID:_13_Vessel field is not cleared in Despatch Select page*****");
     
     	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strMode, strSearchFormNameDespatch, "Param_04Mode"),true,"*****test ID:_13_The Mode value is not set in Mode field in Despatch Select page*****");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:_13_The Status value is not set in Status field in Despatch Select page*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true,"*****test ID:_13_Refine Search Button  is not clicked in Despatch Select page*****");
       
        strMessage = objAdjunoLIMADespatchPOM.verifyStatusForAllProduct(strStatus);
    	
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:_13_error message for Status Awaiting :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 9)
    public void Test_14_verifyDespatchedStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage="";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test ID:_14_Status  field is not cleared in Despatch Select page");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatusDespatched, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:_14_The Status value is not set for Despatched in Despatch Select page");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true,"*****test ID:_14_Refine Search Button  is not clicked in Despatch Select page");
    	
    	wait(2000);
    	strMessage = objAdjunoLIMADespatchPOM.verifyStatusForAllProduct(strStatusDespatched);
    	
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:_14_Error message for Status Awaiting :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 10)
    public void Test_15_verifyAwaitingTranshipmentStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage="";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test ID:_15_Status field is not cleared in Despatch Select page");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatusAwaitingTranshipment, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:_15_The Status value is not set for Despatched in Despatch Select page");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true,"*****test ID:_15_Refine Search Button  is not clicked in Despatch Select page");
    	
    	strMessage = objAdjunoLIMADespatchPOM.verifyStatusForAllProduct(strStatusDespatched);
    	
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:_15_Error message for Status Awaiting :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 11)
    public void Test_16_manifestedContainerStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage ="";
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameDespatch, "Param_15Status"), true,"*****test ID:_16_Status  field is not cleared in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:_16_The Status value is not set for Awaiting in Despatch Select page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestedContainerNumber, strSearchFormNameDespatch, "Param_09Container"), true,"*****test ID:_16_The Manifested container number is not set in Despatch Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true, "*****test ID:_16_Refine Search button is not clicked in Despatch Select page*****");
    	strMessage = objAdjunoLIMADespatchPOM.verifyStatusForAllProduct(strStatus);
    	
    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:_16_Error message for Status Awaiting :"+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 12)
    public void Test_17_verifyContainerData()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	product = objAdjunoLIMADespatchPOM.getDespatchData();
    	objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameManifest, objAdjunoLIMADespatchPOM.getLnkTools(), objAdjunoLIMADespatchPOM.getLnkManifest());
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestPONumber, strSearchFormNameManifest, "Param_OrderNumber"),true, "*****test ID:_17_PO number is not set in Maifest Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestedContainerNumber, strSearchFormNameManifest, "Param_Container"), true, "*****test ID:_17_Container Number is not set in Maifest Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestStatus, strSearchFormNameManifest, "Param_Status"), true, " *****test ID:_17_Status is not set as Manifested  in Maifest Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevSelect()), true, "*****test ID:_17_Select chevron is not clickled in Maifest Search page*****");
    	Manifest = objAdjunoLIMADespatchPOM.getManifestData();
    	strproductNo = objAdjunoLIMADespatchPOM.getManifestProductNumber().getText();
    	objSoftAssert.assertEquals(product.getStrContainer().equalsIgnoreCase(Manifest.getStrManifestContainer()),true,"*****test ID:_17_Conatiner Number details in Manifest do not match with the Container details in Despatch for same Container*****");
    	objSoftAssert.assertEquals(product.getStrOriginPort().equalsIgnoreCase(Manifest.getStrManifestOriginPort()), true,"*****test ID:_17_Origin Port details in Manifest do not match with the Orgin Port details in Despatch for same Container*****");
    	objSoftAssert.assertEquals(product.getStrCarrier().equalsIgnoreCase(Manifest.getStrManifestCarrier()), true,"*****test ID:_17_Carrier data do not match with the Carrier data in Despatch for same Conatiner*****");
    	
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 13)
    public void Test_21_22_verifyDispatchPageLoaded()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameDespatch, objAdjunoLIMADespatchPOM.getLnkTools(), objAdjunoLIMADespatchPOM.getLnkDespatch());
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.verifyPageIsLoaded(strSearchFormNameDespatch), true, "*****test ID:_21_Despatch Search page is not loaded*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:_21_The Status value is not set for Awaiting in Despatch Search page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestedContainerNumber, strSearchFormNameDespatch, "Param_09Container"), true,"*****test ID:_21_The Manifested Container Number is not set in Despatch Search page*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevSelect()), true, "*****test ID:_21_Select Chevron is not clicked in Despatch Search page*****");
    	lstSearchResults = new ArrayList<Despatch>();
        
        if ( objAdjunoLIMADespatchPOM.getNoOrRowsResulted() > 0)
        {
            lstSearchResults =  objAdjunoLIMADespatchPOM.selectMulitpleProducts(1);
            
        
         objSoftAssert.assertEquals(bSearchResultsProductsFound, true);
         objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevDespatch1()),true,"*****test ID:_21_Not able to click on Despatch chevron under Select page*****");
            
        }
        else
        {
            bSearchResultsProductsFound = false;     
        }
        objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "*****test ID:_21_No Products in Search Results. in Despatch Select page*****");
        wait(3000);
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.verifyPageIsLoaded(strDespatchFormName), true, "*****test ID:_21_Despatch Chevron page is not loaded*****");
        wait(3000);
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesElementExist(strDespatchFormName, "GridLegs"), true, "*****test ID:_22_Legs grid is not displayed in Despatch Chevron*****");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.checkDoesElementExist(strDespatchFormName, "GridContainer"), true, "*****test ID:_22_Containers grid is not displayed in Despatch Chevron*****");
        
        
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 14)
    public void Test_23_verifyLegsGridCaption()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
        String strMessage="";
        
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMADespatchPOM.getCaptionsList(strDespatchFormName,"GridLegs");
                
        strMessage = objAdjunoLIMADespatchPOM.verifyCaptionsONGrid(list,nlLegsGridDespatch,9);
               
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:_24_ Legs grid Caption: "+strMessage+"******");
                    
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 15)
    public void Test_25_verifyCTDDateField()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	String strCTDDate = objAdjunoLIMADespatchPOM.getGridCellValue(strDespatchFormName, "GridLegs", 0, "CTD");
    	objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameManifest, objAdjunoLIMADespatchPOM.getLnkTools(), objAdjunoLIMADespatchPOM.getLnkManifest());
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.verifyPageIsLoaded(strSearchFormNameManifest), true,"******test ID:_25_Manifest Search page is not loaded");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strSearchFormNameManifest, "Param_OrderNumber"), true,"******test ID:_25_PO Number field is not cleared in manifest ");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strMode, strSearchFormNameManifest, "Param_Mode"), true,"******test ID:_25_Mode is set in manifest");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestStatus, strSearchFormNameManifest, "Param_Status"), true,"******test ID:_25_Status is set in manifest");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestedContainerNumber, strSearchFormNameManifest, "Param_Container"), true,"******test ID:_25_Container is set in manifest");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestPONumber, strSearchFormNameManifest, "Param_OrderNumber"), true, "******test ID:_25_PO Number is not set in manifest");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevSelect()), true, "******test ID:_25_Select Chevron is not clicked in Manifest");
    	wait(3000);
    	String strManifestETDDate = objAdjunoLIMADespatchPOM.getWebElementCellValue(objAdjunoLIMADespatchPOM.getThManifestETD());
    	
    	
    	objSoftAssert.assertEquals(strCTDDate.equalsIgnoreCase(strManifestETDDate), true,"*****test ID:_25_ Despatch CTD and manifest ETD values do not match*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 16)
    public void Test_27_verifyInvalidETADate()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	lstSearchResults = new ArrayList<Despatch>();
    	objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameDespatch, objAdjunoLIMADespatchPOM.getLnkTools(), objAdjunoLIMADespatchPOM.getLnkDespatch());
  
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strMode, strSearchFormNameDespatch, "Param_04Mode"),true,"*****_27_The Mode value is not set for Despatched*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****_27_The Status value is not set for Despatched*****");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestedContainerNumber, strSearchFormNameDespatch, "Param_09Container"),true,"*****_27_The Container value is not set for Despatched*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevSelect()), true,"*****_27_Select chevron is not clicked");
    
        lstSearchResults =  objAdjunoLIMADespatchPOM.selectMulitpleProducts(1);
    
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevDespatch1()),true,"*****_27_Not able to click on Despatch chevron under Select page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 0, "ETA", strPastETADate), true, "*****_27_ETA date is not set to past value*****");
    	wait(2000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevComplete1()), true, "*****_27_Complete chevron is not clicked under Despatch page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtErrMsgDespatch()), true, "*****_27_'You must correct all validation failures before progressing.' message is not displayed*****");
    	objSoftAssert.assertAll();  	
    }
    
    @Test(priority = 17)
    public void Test_28_verifyContainersGridCaption()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
        String strMessage="";
        
        ArrayList<String> list = new ArrayList<String>();
                    
        list = objAdjunoLIMADespatchPOM.getCaptionsList(strDespatchFormName,"GridContainer");
                
        strMessage = objAdjunoLIMADespatchPOM.verifyCaptionsONGrid(list,nlContainerGridDespatch,9);
        
        objSoftAssert.assertEquals(strMessage.equals(""), true," *****test ID:28_Container grid Caption: "+strMessage+"******");
                    
        objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 18)
    public void Test_29_verifyContainerGridStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	   	
    	String strGridStatus = objAdjunoLIMADespatchPOM.getGridCellValue(strDespatchFormName, "GridContainer", 0, "Status");
    	
    	objSoftAssert.assertEquals(strGridStatus.equalsIgnoreCase("Awaiting"), true, "*****test ID:29_Awaiting status is not set in Container grid under Despatch Chevron******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 19)
    public void Test_30_verifyInvalidTransShipmentStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 0, "TranshipmentStatus", strTransShipmentStatusAwaiting), true, "*****test ID:30_Transhipment Status is not set to awaiting in Legs Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevComplete1()), true, "*****test ID:30_Complete Chevron is not clicked under Despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtErrMsgDespatch()), true, "*****test ID:30_'You must correct all validation failures before progressing.' message is not displayed in Despatch Chevron when all the Mandatory fields are not filled******");
    	objSoftAssert.assertAll();
    	
    }
    
    @Test(priority = 20)
    public void Test_31_verifyAddLegFunction()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	long nRow = objAdjunoLIMADespatchPOM.getNoOfGridRows(strDespatchFormName, "GridLegs");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnAddLeg()), true,"Add leg button is not clicked in Legs Grid under Despatch chevron");
    	wait(3000);
    	long nAddRows = objAdjunoLIMADespatchPOM.getNoOfGridRows(strDespatchFormName, "GridLegs");
    	
    	objSoftAssert.assertEquals(nAddRows==nRow+1, true, "*****test ID:31_Add leg button has issues in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 21)
    public void Test_32_enterValueInFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "Leg", strVoyageTypeDespatch), true, "*****test ID:32_Voyage type value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "LegOriginPort", strOriginPortDespatch), true, "*****test ID:32_OriginPort value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "LegDestinationPort", strDestinationPortDespatch), true, "*****test ID:32_Destination port value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "Vessel", strVesselDespatch), true, "*****test ID:32_Vessel value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "Voyage", strVoyageDespatch), true, "*****test ID:32_Voyage value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "CTD", strCTDDespatch), true, "*****test ID:32_CTD value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "ETA", strETADespatch), true, "*****test ID:32_ETA value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "TranshipmentStatus", strTransShipmentStatusDespatch), true, "*****test ID:32_TranshipmentStatus value is not set in Leg Grid under despatch Chevron******");
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "DespatchKPI", strKPIDespatch), true, "*****test ID:32_KPI value is not set in Leg Grid under despatch Chevron******");
        
        
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 22)
    public void Test_35_verifyMultiVoyageType() 
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage="";
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 0, "ETA", objAdjunoLIMADespatchPOM.setFutureDate()), true, "*****test ID:35_ETA is not set to the future date in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 1, "ETA", strETADespatch), true, "*****test ID:35_ETA value is not set to less than previous leg date in Leg Grid under despatch Chevron");
    	
    	String strErrorMessage = objAdjunoLIMADespatchPOM.getValidationMessageGridElement(strDespatchFormName, "GridLegs", 1, "CTD");
    	if(!strErrorMessage.equals("")){
    		objSoftAssert.assertEquals(strErrorMessage.equalsIgnoreCase("> CTD must be after previous leg ETA"), true, "*****test ID:35_'> CTD must be after previous leg ETA' message is not displayed in ETA Field when ETA value is less than CTD date in Leg Grid under despatch Chevron");
    	}else{
    		strMessage = "*****test ID:35_"+strMessage + "validation message is null******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:35_"+strMessage+"******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 23)
    public void Test_36_37_38_verifyDeleteLegFunction()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	long nRows; 
    	long nRows1;
    	long nRows3;
    	nRows = objAdjunoLIMADespatchPOM.getNoOfGridRows(strDespatchFormName, "GridLegs");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnDeleteLeg()), true,"*****test ID:36_Delete leg button is not clicked in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtDeleteLeg()), true,"*****test ID:36_Delete leg confirmation pop up is not dispalyed in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnNO()), true,"*****test ID:36_No button is not clicked in Delete leg confirmation pop up in Leg Grid under despatch Chevron******");
    	nRows1 = objAdjunoLIMADespatchPOM.getNoOfGridRows(strDespatchFormName, "GridLegs");
    
    	//verifying No button function
    	objSoftAssert.assertEquals(nRows==nRows1, true,"No button in delete leg confirmation pop up has issues");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnDeleteLeg()), true,"*****test ID:37_Delete leg button is not clicked in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtDeleteLeg()), true,"*****test ID:37_Delete leg confirmation pop up is not dispalyed in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnYes()), true,"*****test ID:37_Yes button is not clicked in Delete leg confirmation pop up in Leg Grid under despatch Chevron******");
    	nRows3 = objAdjunoLIMADespatchPOM.getNoOfGridRows(strDespatchFormName, "GridLegs");
    	
    	//verifying Yes button function
    	objSoftAssert.assertEquals(nRows3==nRows-1, true,"*****test ID:38_Yes button in delete leg confirmation pop up has issues******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 24)
    public void Test_39_verifyDespatchComplete()
    {
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForGridCell(strDespatchFormName, "GridLegs", 0, "TranshipmentStatus", strTransShipmentStatusNA), true, "*****test ID:39_TransShipment status is not set to N/A in Leg Grid under despatch Chevron******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevComplete1()), true, "*****test ID:39_Complete Chevron is not clicked under Despatch page******");
    	wait(6000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtDespatchedMsg()), true, "*****test ID:39_'1 Shipment has been Despatched' message is not displayed in Despatch Complete page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtProcessedMsg()), true, "*****test ID:39_100% processed message is not displayed in Despatch Complete page******");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 25)
    public void Test_40_verifyViewDetail()
    {
    	
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	wait(2000);
    	objAdjunoLIMADespatchPOM.getBtnViewDetail().click();
    	
    	wait(3000);
    	String strDespatchDetails = objAdjunoLIMADespatchPOM.getTxtViewDetail().getText();   	
    //	String strTrackReferenceNo = strDespatchDetails.substring(strDespatchDetails.indexOf("LIMAUAT"),strDespatchDetails.indexOf("].", strDespatchDetails.indexOf("LIMAUAT")));
    	String[] vals = strDespatchDetails.split("Track Reference ");
    
    	vals[1] = vals[1].replace("[", " ").replace("]", " ");
    	String valss[] = vals[1].split(" ",12);
    	
    	strTrackNO = valss[1];
    	System.out.println("track ref num: - " + strTrackNO);
    //	System.out.println("track ref num: - " + strTrackReferenceNo);
    	wait(2000);
    		
    
    }
    
    @Test(priority = 26)
    public void Test_41_AccessEdit() 
    {
        SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
        String strMessage = "";
        objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameDespatch,objAdjunoLIMADespatchPOM.getLnkTrack(),objAdjunoLIMADespatchPOM.getLnkEdit());
        wait(2000);

        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getFldRefNo()), true,"*****test ID:41_Reference field is do exist in Edit or Create Track page******");

        if (!isNullOrBlank(strTrackNO)) {
            objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValueForWebElement(objAdjunoLIMADespatchPOM.getFldRefNo(),strTrackNO), true,"*****test ID:41_Track Number is set in Reference field in Edit or Create Track page******");
        }else{
        	strMessage ="*****test ID:41_"+strMessage + " Track number is empty****** ";
        }
        
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnTrackApply()), true,"*****test ID:41_Apply button is not clicked in Edit or Create Track page******");
            
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 27)
    public void Test_41_checkForShipmentOrderDisplay() throws ParseException{        
        SoftAssert objSoftAssert = new SoftAssert();
        String strMessage ="";
        objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
        objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtShipmentTrack()), true, "*****test ID:41_Shipment track page is not loaded******");
                        
       long min = objAdjunoLIMADespatchPOM.getTrackValue(strLIMAUserName);
      
       if(min<=350){
                    
       }else{
           strMessage = "*****test ID:41_Despatch is updated to  todays date Shipment page******";
       }
                
       objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:41_"+strMessage+"******");
                
       objSoftAssert.assertAll();
    }
    
////    @Test(priority = 28)
////    public void verifyAdviceDispatchPageDisplayed()
////    {
////    	SoftAssert objSoftAssert = new SoftAssert();
////    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
////    	
////    }
    
    @Test(priority = 29)
    public void Test_43_verifyPOStatusReport()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameDespatch, objAdjunoLIMADespatchPOM.getLnkFind(), objAdjunoLIMADespatchPOM.getLnkPOStatus());
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.verifyPageIsLoaded(strPOStatusFormName), true, "*****test ID:43_PO Status Page is not displayed******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clearInputField(strPOStatusFormName, "Param_11Mode"), true, "*****test ID:43_Mode field is not cleared in PO Status page");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestPONumber, strPOStatusFormName, "Param_16PO"), true, "*****test ID:43_ Updated PO Number is not set in PO Status page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strproductNo, strPOStatusFormName, "Param_17Item"), true, "*****test ID:43_Product Number is not set in PO Status page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButtonUsingWebElement(objAdjunoLIMADespatchPOM.getBtnRun()), true, "*****test ID:43_Run button is not clicked in PO Status page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getPoStatusReport()), true, "*****test ID:43_PO Status report page is not loaded******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 30)
    public void Test_44_verifyPOStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage = "";
    	String strStatus = objAdjunoLIMADespatchPOM.getWebElementCellValue(objAdjunoLIMADespatchPOM.getPoStatus_Status());
    	try
    	{
    	objSoftAssert.assertEquals(strStatus.equalsIgnoreCase("Shipped"), true, "*****test ID:44_POStatus is not Shipped for the despatched PO Number under PO Status page******");
    	}
    	catch(NoSuchElementException e)
    	{
    		strMessage = "No records are found in PO Status report";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test ID:44_"+strMessage+"******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 31)
    public void Test_45_clickHyperlinkStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage="";
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getPoStatus_Status()), true, "*****test ID:45_Shipped hyperLink do not exist in PO Status report page******");
    	String strhref = objAdjunoLIMADespatchPOM.getPoStatus_Status().getAttribute("href");
    	if(!isNullOrBlank(strhref))
    	{
    	
    	}
    	else
    	{
    		strMessage = "*****test ID:45_"+strMessage + " Status is not a hyperLink in PO Status report page******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objAdjunoLIMADespatchPOM.getPoStatus_Status().click();
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtShipped()), true, "*****test ID:45_Shipped page is not displayed when \"Shipped\" link is clicked in PO Status Report page ******");
    	objSoftAssert.assertAll();
    }
        
    @Test(priority = 32)
    public void Test_46_verifyHyperLinkPONumber()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	String strMessage="";
    	driver.navigate().back();
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getPoStatus_PONumber()), true, "*****test ID:46_PO Number hyperLink do not exist  in PO Status Report page ******");
    	String strhref = objAdjunoLIMADespatchPOM.getPoStatus_PONumber().getAttribute("href");
    	if(!isNullOrBlank(strhref))
    	{
    	
    	}
    	else
    	{
    		strMessage = "*****test ID:46_"+strMessage + "It is not a hyperLink******";
    	}
    	objSoftAssert.assertEquals(strMessage.equals(""), true,strMessage);
    	objAdjunoLIMADespatchPOM.getPoStatus_PONumber().click();
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtPurchaseOrder()), true, "*****test ID:46_Purchase Order page is not displayed******");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 33)
    public void Test_48_verifyDespatchedContainerInAwaitingStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
    	
    	objAdjunoLIMADespatchPOM.callMouseHover(strSearchFormNameDespatch, objAdjunoLIMADespatchPOM.getLnkTools(), objAdjunoLIMADespatchPOM.getLnkDespatch());
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.verifyPageIsLoaded(strSearchFormNameDespatch), true, "Despatch search page is not displayed");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strMode, strSearchFormNameDespatch, "Param_04Mode"),true,"*****test ID:48_The Mode value is not set in Despatch Search page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatus, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:48_The Status value is not set for Awaiting in Despatch Search page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strManifestedContainerNumber, strSearchFormNameDespatch, "Param_09Container"), true,"*****test ID:48_The Despatched container number is not set Despatch Search page******");
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickChevron(objAdjunoLIMADespatchPOM.getChevSelect()), true,"*****test ID:48_Select Chevron is not clicked******");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.isElementPresent(objAdjunoLIMADespatchPOM.getTxtErrMsg()), true,"*****test ID:48_No items were found message is not displayed for the Despatched Container under Awaiting Status******");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 34)
    public void Test_49_verifyDespatchedContainerStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADespatchPOM = new AdjunoLIMADespatchPOM(driver);
      
    	String strMessage ="";
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.setFieldValue(strStatusDespatched, strSearchFormNameDespatch, "Param_15Status"),true,"*****test ID:49_The Status value is not set for Despatched in Despatch Select page******");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADespatchPOM.clickButton(strSearchFormNameDespatch, "FORK_DespatchRefineSearch"), true,"*****test ID:49_Refine Search Button  is not clicked in Despatch Select page******");
    	
    	
    	strMessage = objAdjunoLIMADespatchPOM.verifyStatusForAllProduct(strStatusDespatched);
    	    	
    	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test ID:49_Error message for Status Awaiting :"+strMessage+"******");
    	objSoftAssert.assertAll();
    	
    }
    
    @AfterTest
    public void closeBrowser()
    {
    	driver.close();
    }
    
         
}
