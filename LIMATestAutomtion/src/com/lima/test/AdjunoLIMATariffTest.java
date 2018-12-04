package com.lima.test;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
import com.lima.pagefactory.AdjunoLIMATariffPOM;


public class AdjunoLIMATariffTest 
{
	WebDriver driver;
	AdjunoLIMATariffPOM objAdjunoLIMATariffPOM;
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	AdjunoUILibrary objAdjunoUILibrary;
	
	
	Long nPage_Timeout;
	String strTestURL;
	String strLIMAUserName;
	String strLIMAPassword;
	String strSearchFormNameTariff;
	String strSeaFreightPaymentFormName;
	
	String strTypeSeaFreightType;
	String strAirFreightPaymentFormName;
	String strRoadFreightTariffFormName;
	
	NodeList nlSeafreightTariffHistoryGrid;
	NodeList nlSearchParams;
	NodeList nlFCLFreightCostGrid;
	NodeList nlGOHGrid;
	NodeList nlAdditionalFreightCostGrid;
	NodeList nlFCLDestinationLandsideCostsGrid;
	NodeList nlFCLOriginLandsideCostsGrid;
	NodeList nlFCLHaulageCostGrid;
	NodeList nlFCLAdditionalHaulageCostGrid;
	NodeList nlFCLDemurrageandStorageCostsGrid;
	NodeList nlLCLFreightCostsGrid;
	NodeList nlLCLAdditionalFreightChargesGrid;
	NodeList nlLCLLandsideCostGrid;
	NodeList nlLCLHaulageCostGrid;
	NodeList nlLCLStorageCostsGrid;
	NodeList nlAmendmentLogGrid;
	NodeList nlSeaFreightGridData;
	NodeList nlAirEcoAirRatesgridData;
	NodeList nlExpressServiceGrid;
	NodeList nlAirLandsideCostGrid;
	NodeList nlAirHaulageCostsGrid;
	NodeList nlAirStorageCostsGrid;
	NodeList nlAirAmendmentLogGrid;
	NodeList nlAirFreightTariffGridData;
	NodeList nlFullTrailerCostGrid;
	NodeList nlFullTailerGOHCostsGrid;
	NodeList nlFullTrailerLandsideCostsGrid;
	NodeList nlFullTrailerDeliveryCostGrid;
	NodeList nlGroupageTrailerCostGrid;
	NodeList nlGoupageGOHCostGrid;
	NodeList nlGroupageLandsideCostGrid;
	NodeList nlGroupageHaulageCostGrid;
	NodeList nlRoadAmenendmentLogGrid;
	NodeList nlAirFreightTariffHistoryGrid;
	NodeList nlRoadFreightGridData;
	
	String strTariffVersion = "";
	String strOriginPortSeaFreight = "";
	String strDestinationCountrySeaFreight = "";
	String strCurrencySeaFreight = "";
	String strValidFromDate = "";
	String strValidToDate = "";
	String strHangingTypeSeaFreight = "";
	String strChargeSeaFreight = "";
	String strCostSeaFreight = "";
	String strUnitOfMeasureSeaFreight = "";
	String strDestinationSeaFreight = "";
	String strFSCSeaFreight = "";
	String strTypeAirFreightType = "";
	String strOriginPortAirFreight = "";
	String strServiceTypeAirFreight = "";
	String strCostAirFreight = "";
	String strUnitOfMeasureAirFreight = "";
	String strCurrencyAirfreight = "";
	String strDestinationAirfreight = "";
	String strMinimumChargeAirFreight = "";
	String strFromDayAirFreight = "";
	String strToDayAirFreight = "";
	String strChargeTypeAirFreight = "";
	String strChangeTypeAirFreight = "";
	String strSectionAirFreight = "";
	String strChangeDescriptionAirFreight = "";
	String strLoggedByAirFreight = "";
	String strCommentsAirFreight = "";
	String strTypeRoadFreightType = "";
	String strOriginRoadFreight = "";
	String strDestinationCountryRoadFreight = "";
	String strCarrierRoadFreight = "";
	String strCurrencyRoadFreight = "";
	String strCostRoadFreight = "";
	String strUnitOfMeasureRaodFreight = "";
	String strHangingTypeRoadFreight = "";
	String strChargeTypeRoadFreight = "";
	String strDeliveryTypeRoadFreight = "";
	String strMinBarsRoadFreight = "";
	String strMaxBarsRoadFreight = "";
	String strMinimumChargeSeaFreight = "";
	String strLCLUnitOfMeasureSeaFreight = "";
    String strLCLChangeTypeSeaFreight = "";
    String strLCLCurrencyGBPSeaFreight = "";
    String strLCLChargePerPalletSeaFreight = "";
    String strLCLMinimumChargeSeaFreight = "";
    String strFCLDestinationLandsideCostsCharge = "";
    String strDemurrageAndStorageCostsCharge = "";
    String strAdditionalFreightCostsCharge = "";
    String strAdditionalFreightCostsCurrency = "";
    
	
	
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
            
            Node searchFormNameTariff = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Tariff", dDoc, XPathConstants.NODE);
            strSearchFormNameTariff = searchFormNameTariff.getTextContent();
            
            Node seaFreightPaymentFormName = (Node) xPath.evaluate("/config/LIMA/Seafreight_Tariff_Form_Name", dDoc, XPathConstants.NODE);
            strSeaFreightPaymentFormName = seaFreightPaymentFormName.getTextContent();	
            
            Node airFreightPaymentFormName = (Node) xPath.evaluate("/config/LIMA/Airfreight_Tariff_Form_Name", dDoc, XPathConstants.NODE);
            strAirFreightPaymentFormName = airFreightPaymentFormName.getTextContent();
            
            Node roadFreightTariffFormName = (Node) xPath.evaluate("/config/LIMA/Roadfreight_Tariff_form_Name", dDoc, XPathConstants.NODE);
            strRoadFreightTariffFormName = roadFreightTariffFormName.getTextContent();
            
            FirefoxProfile myprofile=new FirefoxProfile();
        	String key = "browser.helperApps.neverAsk.saveToDisk";
        	String value = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
        	
        	myprofile.setPreference(key, value);
            
    		
        	driver=new FirefoxDriver(myprofile);
           
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
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getTariffXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            nlSeafreightTariffHistoryGrid = (NodeList)xPath1.evaluate("/Tariff/Seafreight_Tariff_History_Grid", dDoc1, XPathConstants.NODESET);
            nlSearchParams = (NodeList)xPath1.evaluate("/Tariff/Search_Param", dDoc1, XPathConstants.NODESET);
            nlFCLFreightCostGrid = (NodeList)xPath1.evaluate("/Tariff/FCL_Freight_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlGOHGrid = (NodeList)xPath1.evaluate("/Tariff/Sea_FreightTariff_GOH_Grid", dDoc1, XPathConstants.NODESET);
            nlAdditionalFreightCostGrid = (NodeList)xPath1.evaluate("/Tariff/Sea_Freight_Additional_Freight_Cost", dDoc1, XPathConstants.NODESET);
            nlFCLDestinationLandsideCostsGrid = (NodeList)xPath1.evaluate("/Tariff/FCL_Destination_Landside_Costs_Grid", dDoc1, XPathConstants.NODESET);
            nlFCLOriginLandsideCostsGrid = (NodeList)xPath1.evaluate("/Tariff/FCL_Origin_Landside_Costs", dDoc1, XPathConstants.NODESET);
            nlFCLHaulageCostGrid = (NodeList)xPath1.evaluate("Tariff/FCL_Haulage_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlFCLAdditionalHaulageCostGrid = (NodeList)xPath1.evaluate("Tariff/FCl_Additional_Haulage_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlFCLDemurrageandStorageCostsGrid = (NodeList)xPath1.evaluate("Tariff/FCL_Demurrage_and_Storage_Costs_Grid", dDoc1, XPathConstants.NODESET);
            nlLCLFreightCostsGrid = (NodeList)xPath1.evaluate("Tariff/LCL_Freight_Costs", dDoc1, XPathConstants.NODESET);
            nlLCLAdditionalFreightChargesGrid = (NodeList)xPath1.evaluate("Tariff/LCL_Additional_LCL_Freight_Costs", dDoc1, XPathConstants.NODESET);
            nlLCLLandsideCostGrid = (NodeList)xPath1.evaluate("Tariff/LCL_Landside_Costs", dDoc1, XPathConstants.NODESET);
            nlLCLHaulageCostGrid = (NodeList)xPath1.evaluate("Tariff/LCL_Haulage_Costs", dDoc1, XPathConstants.NODESET);
            nlLCLStorageCostsGrid = (NodeList)xPath1.evaluate("Tariff/LCL_Storage_Costs", dDoc1, XPathConstants.NODESET);
            nlAmendmentLogGrid = (NodeList)xPath1.evaluate("Tariff/Amendment_Log_Grid", dDoc1, XPathConstants.NODESET);
            nlSeaFreightGridData = (NodeList)xPath1.evaluate("Tariff/SeaFreight_Grid_Data", dDoc1, XPathConstants.NODESET);
            nlAirFreightTariffHistoryGrid = (NodeList)xPath1.evaluate("/Tariff/AirFreight_Tariff_History", dDoc1, XPathConstants.NODESET);
            nlAirEcoAirRatesgridData = (NodeList)xPath1.evaluate("Tariff/Air_Eco_Air_Rates_grid", dDoc1, XPathConstants.NODESET);
            nlExpressServiceGrid = (NodeList)xPath1.evaluate("/Tariff/Express_Service_Grid", dDoc1, XPathConstants.NODESET);
            nlAirLandsideCostGrid = (NodeList)xPath1.evaluate("/Tariff/Air_Landside_Costs_Grid", dDoc1, XPathConstants.NODESET);
            nlAirHaulageCostsGrid = (NodeList)xPath1.evaluate("/Tariff/Air_Haulage_Costs_Grid", dDoc1, XPathConstants.NODESET);
            nlAirStorageCostsGrid = (NodeList)xPath1.evaluate("/Tariff/Air_Storage_Costs_Grid", dDoc1, XPathConstants.NODESET);
            nlAirAmendmentLogGrid = (NodeList)xPath1.evaluate("/Tariff/Air_Amendment_Log_Grid", dDoc1, XPathConstants.NODESET);
            nlAirFreightTariffGridData = (NodeList)xPath1.evaluate("/Tariff/Airfreight_Grid_Data", dDoc1, XPathConstants.NODESET);
            nlFullTrailerCostGrid = (NodeList)xPath1.evaluate("/Tariff/FullTrailer_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlFullTailerGOHCostsGrid = (NodeList)xPath1.evaluate("/Tariff/FullTrailer_GOH_Costs_Grid", dDoc1, XPathConstants.NODESET);
            nlFullTrailerLandsideCostsGrid = (NodeList)xPath1.evaluate("/Tariff/FullTrailer_Landside_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlFullTrailerDeliveryCostGrid = (NodeList)xPath1.evaluate("/Tariff/FullTrailer_Delivery_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlGroupageTrailerCostGrid = (NodeList)xPath1.evaluate("/Tariff/Groupage_Trailer_Costs", dDoc1, XPathConstants.NODESET);
            nlGoupageGOHCostGrid = (NodeList)xPath1.evaluate("/Tariff/Groupage_GOH_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlGroupageLandsideCostGrid = (NodeList)xPath1.evaluate("/Tariff/Groupage_Landside_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlGroupageHaulageCostGrid = (NodeList)xPath1.evaluate("/Tariff/Groupage_Haulage_Cost_Grid", dDoc1, XPathConstants.NODESET);
            nlRoadFreightGridData = (NodeList)xPath1.evaluate("/Tariff/RoadFreight_Grid_Data", dDoc1, XPathConstants.NODESET);
            nlRoadAmenendmentLogGrid = (NodeList)xPath1.evaluate("/Tariff/RoadFreight_AmendmentLog_grid", dDoc1, XPathConstants.NODESET);
            
            
            Element el;
            for(int i=0; i <= nlSearchParams.getLength() -1; i++)
             {
                 if (nlSearchParams.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		 el = (Element) nlSearchParams.item(i); 
            		 
            		 strTypeSeaFreightType = el.getElementsByTagName("SeaFreight_Type").item(0).getTextContent();	 
            		 strTypeAirFreightType = el.getElementsByTagName("AirFreight_Type").item(0).getTextContent();
            		 strTypeRoadFreightType = el.getElementsByTagName("RoadFreight_Type").item(0).getTextContent();
            	}
             }
            Element el1;
            for(int i=0;i<=nlSeaFreightGridData.getLength()-1;i++)
            {
            	if(nlSeaFreightGridData.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		el1 = (Element) nlSeaFreightGridData.item(i);
            		strOriginPortSeaFreight = el1.getElementsByTagName("Origin_Port").item(0).getTextContent();
            		strDestinationCountrySeaFreight = el1.getElementsByTagName("Destination_Country").item(0).getTextContent();
            		strCurrencySeaFreight = el1.getElementsByTagName("Currency").item(0).getTextContent();
            		strHangingTypeSeaFreight = el1.getElementsByTagName("Hanging_Type").item(0).getTextContent();
            		strChargeSeaFreight = el1.getElementsByTagName("Charge").item(0).getTextContent();
            		strCostSeaFreight = el1.getElementsByTagName("Cost").item(0).getTextContent();
            		strUnitOfMeasureSeaFreight = el1.getElementsByTagName("Unit_of_Measure").item(0).getTextContent();
            		strDestinationSeaFreight = el1.getElementsByTagName("Destination").item(0).getTextContent();
            		strFSCSeaFreight = el1.getElementsByTagName("FSC").item(0).getTextContent();
            		strMinimumChargeSeaFreight = el1.getElementsByTagName("Minimum_Charge").item(0).getTextContent();
            		strLCLUnitOfMeasureSeaFreight = el1.getElementsByTagName("UnitOfMeasure").item(0).getTextContent();
            		strLCLChangeTypeSeaFreight = el1.getElementsByTagName("Change_Type").item(0).getTextContent();
            		strLCLCurrencyGBPSeaFreight = el1.getElementsByTagName("Currency_GBP").item(0).getTextContent();
            		strLCLChargePerPalletSeaFreight = el1.getElementsByTagName("Charge_Per_Pallet").item(0).getTextContent();
            		strLCLMinimumChargeSeaFreight = el1.getElementsByTagName("Minimum_Charge").item(0).getTextContent();
            		strFCLDestinationLandsideCostsCharge = el1.getElementsByTagName("FCL_Destination_Landside_Costs_Charge").item(0).getTextContent();
            		strDemurrageAndStorageCostsCharge = el1.getElementsByTagName("Demurrage_and_Storage_Costs_Charge").item(0).getTextContent();
            		strAdditionalFreightCostsCharge = el1.getElementsByTagName("Additional_Freight_Costs_Charge").item(0).getTextContent();
            		strAdditionalFreightCostsCurrency = el1.getElementsByTagName("Additional_Freight_Costs_Currency").item(0).getTextContent();
            		
            	}
            }
            Element el2;
            for(int i=0;i<=nlAirFreightTariffGridData.getLength()-1;i++)
            {
            	if(nlAirFreightTariffGridData.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
            		el2 = (Element) nlAirFreightTariffGridData.item(i);
            		strOriginPortAirFreight = el2.getElementsByTagName("Origin_Port").item(0).getTextContent();
            		strServiceTypeAirFreight = el2.getElementsByTagName("Service_Type").item(0).getTextContent();
            		strCostAirFreight = el2.getElementsByTagName("Cost").item(0).getTextContent();
            		strUnitOfMeasureAirFreight = el2.getElementsByTagName("Unit_Of_Measure").item(0).getTextContent();
            		strCurrencyAirfreight = el2.getElementsByTagName("Currency").item(0).getTextContent();
            		strDestinationAirfreight = el2.getElementsByTagName("Destination").item(0).getTextContent();
            		strMinimumChargeAirFreight = el2.getElementsByTagName("Minimum_Charge").item(0).getTextContent();
            		strFromDayAirFreight = el2.getElementsByTagName("From_Day").item(0).getTextContent();
            		strToDayAirFreight = el2.getElementsByTagName("From_To").item(0).getTextContent();
            		strChangeTypeAirFreight = el2.getElementsByTagName("Change_Type").item(0).getTextContent();
            		strSectionAirFreight = el2.getElementsByTagName("Section").item(0).getTextContent();
            		strChangeDescriptionAirFreight = el2.getElementsByTagName("Change_Description").item(0).getTextContent();
            		strLoggedByAirFreight = el2.getElementsByTagName("Logged_By").item(0).getTextContent();
            		strCommentsAirFreight = el2.getElementsByTagName("Comments").item(0).getTextContent();
            	}
            }
            Element el3;
            for(int i=0;i <= nlRoadFreightGridData.getLength()-1;i++)
            {
            	if(nlRoadFreightGridData.item(i).getNodeType() == Node.ELEMENT_NODE)
            	{
	            	el3 = (Element) nlRoadFreightGridData.item(i);
	            	strOriginRoadFreight = el3.getElementsByTagName("Origin").item(0).getTextContent();
	            	strDestinationCountryRoadFreight = el3.getElementsByTagName("Destination_Country").item(0).getTextContent();
	            	strCarrierRoadFreight = el3.getElementsByTagName("Carrier").item(0).getTextContent();
	            	strCurrencyRoadFreight = el3.getElementsByTagName("Currency").item(0).getTextContent();
	            	strCostRoadFreight = el3.getElementsByTagName("Cost").item(0).getTextContent();
	            	strUnitOfMeasureRaodFreight = el3.getElementsByTagName("Unit_Of_Measure").item(0).getTextContent();
	            	strHangingTypeRoadFreight = el3.getElementsByTagName("Hanging_Type").item(0).getTextContent();
	            	strChargeTypeRoadFreight = el3.getElementsByTagName("Charge_Type").item(0).getTextContent();
	            	strDeliveryTypeRoadFreight = el3.getElementsByTagName("Delivery_Type").item(0).getTextContent();
	            	strMinBarsRoadFreight = el3.getElementsByTagName("Min_Bars").item(0).getTextContent();
	            	strMaxBarsRoadFreight = el3.getElementsByTagName("Max_Bars").item(0).getTextContent();
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
    public void deleteFilesInDownLoads()
    {
    	File SeadownloadedFile = new File("C:\\Users\\Anand\\Downloads\\SeafreightTemplate.xlsx");
    	File AirdownloadedFile = new File("C:\\Users\\Anand\\Downloads\\AirfreightTemplate.xlsx");
    	File RoaddownloadedFile = new File("C:\\Users\\Anand\\Downloads\\RoadfreightTemplate.xlsx");
        if(SeadownloadedFile.exists()){
        	SeadownloadedFile.delete();
        	System.out.println("deleted");
        }
        else
        {
        	System.out.println("Sea Freight files do not exist");
        }
        if(AirdownloadedFile.exists()){
        	AirdownloadedFile.delete();
        }
        else
        {
        	System.out.println("Air Freight files do not exist");
        }
        if(RoaddownloadedFile.exists()){
        	RoaddownloadedFile.delete();
        }
        else
        {
        	System.out.println("Road Freight files do not exist");
        }
    }
   @Test(priority = 5)
    public void test_LIMA_TARR_SF_001_CheckForTariffPage()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	
    	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_001_Step_No_1_Untitled page of \"Type Comobox\" is not displayed os not displayed in \"Tariff Tool\"*****");
    	objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOk());
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 10)
    public void test_LIMA_TARR_SF_002_ViewSeaFreightTariffHistoryResults()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_SF_002_Step_No_1_Type Combo Box Arrow button is not clicked in \"Tariff Search page\"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeSeaFreightType,strSearchFormNameTariff,"ComboboxTypeTariff"), true, "*****test_LIMA_TARR_SF_002_Step_No_1_'SeaFreight' is not set in the Type Combox in \"Tariff Search page\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_SF_002_Step_No_1_Seafreight Tariff History Grid is not displayed in after 'Sea Freight' is set in Type ComboBox*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSearchFormNameTariff,"Grid_SeaFreight");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlSeafreightTariffHistoryGrid,7);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_002_Step_No_2_SeaFreightTariffHistoryGridCaption: "+strMessage+"*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_SF_002_Step_No_4_Create New Tariff  button is not displayed in \"Tariff Serach page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "DownloadTemplate"), true, "*****test_LIMA_TARR_SF_002_Step_No_4_Download Seafreight Template link is not displayed in \"Tariff Serach page\"*****");
        int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSearchFormNameTariff, "Grid_SeaFreight");
        
        for(int i=0;i<nRows-1;i++)
        {
        	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isGridElementPresent(strSearchFormNameTariff, "Grid_SeaFreight", i, "ViewTariff"), true, "*****test_LIMA_TARR_SF_002_Step_No_4_'View Tariff Link' is not displayed in 'Seafreight Tariff History grid'*****");
        	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isGridElementPresent(strSearchFormNameTariff, "Grid_SeaFreight", i, "OpenReport"), true, "*****test_LIMA_TARR_SF_002_Step_No_4_'Open Report link' is not displayed in 'Seafreight Tariff History grid'*****");
        }
        
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 15)
    public void test_LIMA_TARR_SF_003_CheckPagination()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strValue = "";
    	
    	strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
        String[] strVal = strValue.split("of ");
		int nAllRows = Integer.parseInt(strVal[1]);
		
		if(nAllRows>10)
		{
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnNext()), true, "*****test_LIMA_TARR_SF_003_Step_No_1_\"Next page\" button is not clicked in \"SeaTariff History grid\" in Tariff Search page *****");
			wait(2000);
			strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
			strVal = strValue.split("rows ");
			String strVals[] = strVal[1].split("of ");
			objSoftAssert.assertEquals(strVals[0].contains("11 -"), true, "*****test_LIMA_TARR_SF_003_Step_No_1_Next page is not displayed*****");
	
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnPrevious()), true, "*****test_LIMA_TARR_SF_003_Step_No_2_Previous Button is not clicked*****");
			strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
			strVal = strValue.split("rows ");
			String strVals1[] = strVal[1].split("of ");
			objSoftAssert.assertEquals(strVals1[0].contains("1 -"), true, "*****test_LIMA_TARR_SF_003_Step_No_2_Previous page is not displayed*****");
			
		}
		else{} 
		objSoftAssert.assertAll();
    }
    
    @Test(priority = 20)
    public void test_LIMA_TARR_SF_004_CheckViewTarifflink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	wait(3000);

    	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_SF_004_Step_No_1_View Deatil is not a hyper link*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_SF_004_Step_No_1_View SeaFreight Tariff page is not loaded*****");
    	
    	objSoftAssert.assertAll();
    }
    
//    @Test(priority = 25)
//    public void test_LIMA_TARR_SF_005_CheckOpenReportLink()
//    {
//    	SoftAssert objSoftAssert = new SoftAssert();
//    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
//    	String strMessage = "";
//    	wait(3000);
//    	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
//    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_005_Step_No_1_Untitled page with type text field is not displayed*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_SF_005_Step_No_1_Type Combo Box Arrow button is not clicked*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeSeaFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_SF_005_Step_No_2_Sea Freight is not set in type field*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_SF_005_Step_No_2_Sea Freight grid is not displayed*****");
//    	wait(2000);
//    	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "OpenReport");
//    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_SF_004_Step_No_3_Open Report is not a hyper link*****");
//    	objAdjunoLIMATariffPOM.getGridCellElement(strSeaFreightPaymentFormName, "Grid_SeaFreight", 0, "OpenReport").click();
//    	objSoftAssert.assertAll();
//    }
    
    @Test(priority = 30)
    public void test_LIMA_TARR_SF_006_CheckDownloadSeaFreightTemplateLink()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	wait(3000);
    	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_006_Step_No_1_Untitled page with type text field is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_SF_006_Step_No_1_Type Combo Box Arrow button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeSeaFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_SF_006_Step_No_2_Sea Freight is not set in type field*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_SF_006_Step_No_2_Sea Freight grid is not displayed*****");
    	strMessage = objAdjunoLIMATariffPOM.isHyperlinkPresent(strSearchFormNameTariff,"DownloadTemplate");
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_SF_006_Step_No_3_Download Seafreight Template is not a hyper link*****");
    	wait(10000);
    	
    	wait(3000);
    	objAdjunoLIMATariffPOM.getElement(strSearchFormNameTariff, "DownloadTemplate").click();
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isFileDownloaded_Ext("C:\\Users\\Anand\\Downloads",".xlsx"), true, "*****test_LIMA_TARR_SF_006_Step_No_3_The XLSX version of the template is not downloaded on the user's local drive*****");
    	objSoftAssert.assertAll();
    }

    @Test(priority = 40)
    public void test_LIMA_TARR_SF_007_VerifySeaFreightTariffTabsFCLFreightCostsGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_007_Step_No_1_Untitled page with type text field is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_SF_007_Step_No_1_Type Combo Box Arrow button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeSeaFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_SF_007_Step_No_2_Sea Freight is not set in type field*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_SF_007_Step_No_2_Sea Freight grid is not displayed*****");
    	wait(2000);
    	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_SF_007_Step_No_2_View Deatil is not a hyper link*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_SF_007_Step_No_3_View SeaFreight Tariff page is not loaded*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_FCLFreightCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFCLFreightCostGrid,20);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_007_Step_No_3_SeaFreightTariffHistoryGridCaption: "+strMessage+"*****");
        
       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsAddRow()), true, "*****test_LIMA_TARR_SF_007_Step_No_4_Add Row button is not disabled in FCL Freight Cost Grid*****");
       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_007_Step_No_4_Delete Row button is not disabled in FCL Freight Cost Grid*****");
       
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 45)
    public void test_LIMA_TARR_SF_008_VerifySeaFreightTariffTabsFCLFreightCostsGOHCostsGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_GOHCharges"), true, "*****test_LIMA_TARR_SF_008_Step_No_1_GOH Charges Grid is not displayed*****");
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_GOHCharges");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlGOHGrid,15);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_008_Step_No_1_GOHGridCaption: "+strMessage+"*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGOHAddRow()), true, "*****test_LIMA_TARR_SF_008_Step_No_2_Add Row button is not disabled in GOH Grid*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGOHDeleteRow()), true, "*****test_LIMA_TARR_SF_008_Step_No_2_Delete Row button is not disabled in GOH Grid*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 50)
    public void test_LIMA_TARR_SF_009_VerifySeaFreightTariffTabsFCLFreightCostsAdditionalFreightCostsGridGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_009_Step_No_1_Additional Freight Charges Grid is not displayed*****");
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_FCLAdditionalFreightCharges");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAdditionalFreightCostGrid,12);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_009_Step_No_1_AdditionalFreightCostGrid: "+strMessage+"*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAdditionalFreightCostAddRow()), true, "*****test_LIMA_TARR_SF_009_Step_No_2_Add Row button is not disabled in GOH Grid*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAdditionalFreightCostDeleteRow()), true, "*****test_LIMA_TARR_SF_009_Step_No_2_Delete Row button is not disabled in GOH Grid*****");
        objSoftAssert.assertAll();
    }
    	
    @Test(priority = 55)
    public void test_LIMA_TARR_SF_010_VerifySeaFreightTariffTabsFCLFreightCostsCommentsGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Panel_FCLFreightComments"), true, "*****test_LIMA_TARR_SF_010_Step_No_1_Comments is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strSeaFreightPaymentFormName, "TextArea_FCLFreightComments"), true, "*****test_LIMA_TARR_SF_010_Step_No_2_Comments text field is not in readable mode*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 60)
    public void test_LIMA_TARR_SF_011_VerifySeaFreightTariffTabsFCLLandsideCostsFCLDestinationLandsideCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_011_Step_No_1_FCL Landside Destination Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_011_Step_No_1_FCL Landside Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLLandsideCostsAddRow()), true, "*****test_LIMA_TARR_SF_011_Step_No_2_Add Row button is not disabled in FCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_011_Step_No_2_Delete Row button is not disabled in FCL Landside Costs Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_FCLLandsideCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFCLDestinationLandsideCostsGrid,10);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_011_Step_No_3_Destination Land side Costs Grid: "+strMessage+"*****");
    	
    	objSoftAssert.assertAll();
    }
  
    @Test(priority = 65)
    public void test_LIMA_TARR_SF_012_VerifySeaFreightTariffTabsFCLLandsideCostsFCLOriginLandsideCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_012_Step_No_1_FCL Origin Landside Costs Grid is not displayed in \"View New Sea *****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_FCLOriginLandsideCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFCLOriginLandsideCostsGrid,11);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_011_Step_No_3_Destination Land side Costs Grid: "+strMessage+"*****");
    	
    	objSoftAssert.assertAll();	
    }
    
    @Test(priority = 70)
    public void test_LIMA_TARR_SF_013_VerifySeaFreightTariffTabsFCLHaulageCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_013_Step_No_1_FCL Haulage Cost tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_013_Step_No_1_Haulage Cost Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLHaulageCostsAddRow()), true, "*****test_LIMA_TARR_SF_013_Step_No_2_Add Row button is not disabled in FCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLHaulageCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_013_Step_No_2_Delete Row button is not disabled in FCL Haulage Costs Grid*****");
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_FCLHaulageCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFCLHaulageCostGrid,14);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_013_Step_No_3_Haulage Costs Grid: "+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 75)
    public void test_LIMA_TARR_SF_014_VerifySeaFreightTariffTabsFCLHaulageCostsAdditionalHaulageCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLAdditionalHaulageCostAddRow()), true, "*****test_LIMA_TARR_SF_014_Step_No_1_Add Row button is not disabled in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLAdditionalHaulageCostDeleteRow()), true, "*****test_LIMA_TARR_SF_014_Step_No_1_Delete Row button is not disabled in FCL Additional Haulage Costs Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_FCLAdditionalHaulageCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFCLAdditionalHaulageCostGrid,16);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_014_Step_No_2_Additional Haulage Costs Grid: "+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 80)
    public void test_LIMA_TARR_SF_015_VerifySeaFreightTariffTabsFCLHaulageCostsDemurrageandStorageCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLDemurrageAndStorageCostAddRow()), true, "*****test_LIMA_TARR_SF_015_Step_No_1_Add Row button is not disabled in Demurrage and Storage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLDemurrageAndStorageDeleteRow()), true, "*****test_LIMA_TARR_SF_014_Step_No_1_Delete Row button is not disabled in Demurrage and Storage Costs Grid*****");
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_RentDemurrage");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFCLDemurrageandStorageCostsGrid,17);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_015_Step_No_2_Demurrage and Storage Costs Grid: "+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 85)
    public void test_LIMA_TARR_SF_016_VerifySeaFreightTariffTabsFCLHaulageCostsComments()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Panel_FCLHaulageCostsComments"), true, "*****test_LIMA_TARR_SF_016_Step_No_1_Comments is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strSeaFreightPaymentFormName, "TextArea_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_016_Step_No_1_Comments text field is not in readable mode*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 90)
    public void test_LIMA_TARR_SF_017_VerifySeaFreightTariffTabsLCLFreightCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_017_Step_No_1_LCL Freight Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_017_Step_No_1_LCL Freight Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLFreightCostsAddRow()), true, "*****test_LIMA_TARR_SF_017_Step_No_3_Add Row button is not disabled in LCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLFreightCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_017_Step_No_3_Delete Row button is not disabled in LCL Freight Costs Grid*****");
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_LCLFreightCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlLCLFreightCostsGrid,12);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_017_Step_No_2_LCL Freight Costs Grid: "+strMessage+"*****");
        objSoftAssert.assertAll(); 	
    }
    
    @Test(priority = 95)
    public void test_LIMA_TARR_SF_018_VerifySeaFreightTariffTabsLCLFreightCostsAdditionalLCLFreightCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLAdditionalFreightChargesAddRow()), true, "*****test_LIMA_TARR_SF_018_Step_No_1_Add Row button is not disabled in LCL Additional Freight Charges Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLAdditionalFreightChargesDeleteRow()), true, "*****test_LIMA_TARR_SF_018_Step_No_1_Delete Row button is not disabled in LCL Additional Freight Charges Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_LCLAdditionalLCLFreightCharges");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlLCLAdditionalFreightChargesGrid,13);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_018_Step_No_2_Additional Freight Charges Grid: "+strMessage+"*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 100)
    public void test_LIMA_TARR_SF_017_VerifySeaFreightTariffTabsLCLFreightCostsComments()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Panel_LCLFreightComments"), true, "*****test_LIMA_TARR_SF_017_Step_No_1_Comments is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strSeaFreightPaymentFormName, "TextArea_LCLFreightComments"), true, "*****test_LIMA_TARR_SF_017_Step_No_1_Comments text field is not in readable mode*****");
    	
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 105)
    public void test_LIMA_TARR_SF_020_VerifySeaFreightTariffTabsLCLLandsideCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_020_Step_No_1_LCL LansideCosts tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_020_Step_No_1_LCL Landside Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLLandsideCostsAddRow()), true, "*****test_LIMA_TARR_SF_020_Step_No_2_Add Row button is not disabled in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLLandsideCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_020_Step_No_2_Delete Row button is not disabled in LCL Landside Costs Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_LCLLandsideCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlLCLLandsideCostGrid,11);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_020_Step_No_3_LCL Land side Costs Grid: "+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 110)
    public void test_LIMA_TARR_SF_021_VerifySeaFreightTariffTabsLCLHaulageCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_021_Step_No_1_LCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_021_Step_No_1_LCL Haulage Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLHaulageCostAddRow()), true, "*****test_LIMA_TARR_SF_021_Step_No_2_Add Row button is not disabled in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLHaulageCostDeleteRow()), true, "*****test_LIMA_TARR_SF_021_Step_No_2_Delete Row button is not disabled in LCL Haulage Costs Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_LCLHaulageCosts");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlLCLHaulageCostGrid,9);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_021_Step_No_3_LCL Haulage Costs Grid: "+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 115)
    public void test_LIMA_TARR_SF_022_VerifySeaFreightTariffTabsLCLHaulageCostsLCLStorageCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLStorageChangesAddRow()), true, "*****test_LIMA_TARR_SF_022_Step_No_1_Add Row button is not disabled in LCL Storage Changes Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnLCLStorageChangesDeleteRow()), true, "*****test_LIMA_TARR_SF_022_Step_No_1_Delete Row button is not disabled in LCL Storage Changes Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_LCLStorageChanges");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlLCLStorageCostsGrid,10);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_021_Step_No_3_LCL Storage Changes Grid: "+strMessage+"*****");
        objSoftAssert.assertAll();
    }
    
    @Test(priority = 120)
    public void test_LIMA_TARR_SF_023_VerifySeaFreightTariffTabsLCLHaulageCostsComments()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Panel_LCLHaulageComments"), true, "*****test_LIMA_TARR_SF_023_Step_No_1_Comments is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strSeaFreightPaymentFormName, "TextArea_LCLHaulageComments"), true, "*****test_LIMA_TARR_SF_023_Step_No_1_Comments text field is not in readable mode*****");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 125)
    public void test_LIMA_TARR_SF_024_VerifySeaFreightTariffTabsLCLHaulageCostsLCLStorageCosts()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_AmendmentLog"), true, "*****test_LIMA_TARR_SF_024_Step_No_1_Amendment Log tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_SF_024_Step_No_1_Amendment Log Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLLandsideCostsAddRow()), true, "*****test_LIMA_TARR_SF_024_Step_No_2_Add Row button is not disabled in Amendment Log Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_024_Step_No_2_Delete Row button is not disabled in Amendment Log Grid*****");
    	
    	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_AmendmentLog");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAmendmentLogGrid,6);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_024_Step_No_3_Amendment Log Grid: "+strMessage+"*****");
    	objSoftAssert.assertAll();
    	
    }
    
    
    @Test(priority = 130)
    public void test_LIMA_TARR_SF_025_SeaFreight_CreateNewTariff_UpdateanExistingTariff_AddANewRowOnFCLFreightCostGrid()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	String strMessage1 = "";	
    	
    	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_025_Step_No_1_An Untitled Page with Type Combobox is not displayed.*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_SF_025_Step_No_1_Type Combo Box Arrow button is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeSeaFreightType,strSearchFormNameTariff,"ComboboxTypeTariff"), true, "*****test_LIMA_TARR_SF_025_Step_No_2_SeaFreight is not set in the type field*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_SF_025_Step_No_2_Seafreight Tariff History Grid is not displayed*****");
    	
    	wait(2000);
    	strTariffVersion = objAdjunoLIMATariffPOM.getGridCellValue(strSearchFormNameTariff, "Grid_SeaFreight", 0, "TariffVersion");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButton(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_SF_025_Step_No_3_Create new tariff button is not clicked*****");
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "LIMA_Tariff_Settings"), true, "*****test_LIMA_TARR_SF_025_Step_No_3_Create New Seafreight Tariff page is not displayed *****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strSeaFreightPaymentFormName, "TariffVersion"), true, "*****test_LIMA_TARR_SF_025_Step_No_4_Tariff version field is not in read only mode*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.setPresentDate("dd MMM YYYY"), strSeaFreightPaymentFormName, "ValidFrom"), true);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.setFutureDate("dd MMM YYYY"), strSeaFreightPaymentFormName, "ValidTo"), true);
    	wait(2000);
   	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsAddRow()), true, "*****test_LIMA_TARR_SF_025_Step_No_5_Add Row button is clicked in FCL Freight cost grid*****");
    	wait(2000);
    	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts");
    	
    	String strOriginPortErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort");
    	
    	String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry");
    	String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency");
    	String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom");
    	String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo");
    	
    	if(!strOriginPortErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
    	{
    		if(strOriginPortErrMsg.contains("&#39;"))
    		{
    			strOriginPortErrMsg = strOriginPortErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strOriginPortErrMsg.equalsIgnoreCase("> 'Origin Port' is a required field"), true, "*****test_LIMA_TARR_SF_025_Step_No_5_'Open Port' is the required field message is not displayed*****");
    		}
    		if(strDestinationCountryErrMsg.contains("&#39;"))
    		{
    			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_SF_025_Step_No_5_'Destination Country' is the required field message is not displayed");
    		}
    		if(strCurrencyErrMsg.contains("&#39;"))
    		{
    			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_025_Step_No_5_'Currency' is the required field message is not displayed*****");
    		}
    		if(strValidFromErrMsg.contains("&#39;"))
    		{
    			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_SF_025_Step_No_5_'Valid From' is the required field message is not displayed*****");
    		}
    		if(strValidToErrMsg.contains("&#39;"))
    		{
    			strValidToErrMsg = strValidToErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> 'Valid To' is a required field"), true, "*****test_LIMA_TARR_SF_025_Step_No_5_'Valid To' is the required field message is not displayed*****");
    		}
    	}
    	else
    	{
    		strMessage = "*****test_LIMA_TArr_SF_025_Step_No_5_"+strMessage + "validation message is null*****";
    	}
    	strValidFromDate = objAdjunoLIMATariffPOM.setPresentDate("dd MMM YYYY");
    	strValidToDate = objAdjunoLIMATariffPOM.setFutureDate("dd MMM YYYY");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Origin Port value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Destination Country value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Currency value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Valid From value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Valid To value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Apply button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_025_Step_No_6_The user is not redirected to Seafreight Tariff History page*****");
    	wait(2000);
    	strMessage1 = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
    	objSoftAssert.assertEquals(strMessage1.equals(""), true, "*****test_LIMA_TARR_SF_025_Step_No_7_View Deatil is not a hyper link*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_SF_025_Step_No_7_View SeaFreight Tariff page is not loaded*****");
    	objSoftAssert.assertEquals(strOriginPortSeaFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort")), true, "*****test_LIMA_TARR_SF_025_Step_No_7_Origin Port do not match in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(strDestinationCountrySeaFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry")), true, "*****test_LIMA_TARR_SF_025_Step_No_7_Destination Country do not match in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(strCurrencySeaFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency")), true, "*****test_LIMA_TARR_SF_025_Step_No_7_Currency do not match in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(strValidFromDate.equals(objAdjunoLIMATariffPOM.getGridCellValue(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom")), true, "*****test_LIMA_TARR_SF_025_Step_No_7_Valid From value do not match in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(strValidToDate.equals(objAdjunoLIMATariffPOM.getGridCellValue(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo")), true, "*****test_LIMA_TARR_SF_025_Step_No_7_Valid To value do not match in FCL Freight Cost Grid*****");
    	driver.navigate().back();
    	objSoftAssert.assertAll();
    }
    
    @Test(priority = 135)
    public void test_LIMA_TARR_SF_026_FCLFreightCost_MandatoryFieldCheck()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButton(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_SF_026_Step_No_1_Create new tariff button is not clicked*****");
    	wait(4000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "LIMA_Tariff_Settings"), true, "*****test_LIMA_TARR_SF_026_Step_No_2_Create New Seafreight Tariff page is not displayed *****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strSeaFreightPaymentFormName, "TariffVersion"), true, "*****test_LIMA_TARR_SF_026_Step_No_2_Tariff version field is not in read only mode*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsAddRow()), true, "*****test_LIMA_TARR_SF_026_Step_No_3_Add Row button is clicked in FCL Freight cost grid*****");
    	wait(2000);
    	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts");
    	String strOriginPortErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort");
    	String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry");
    	String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency");
    	String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom");
    	String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo");
    	
    	if(!strOriginPortErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
    	{
    		if(strOriginPortErrMsg.contains("&#39;"))
    		{
    			strOriginPortErrMsg = strOriginPortErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strOriginPortErrMsg.equalsIgnoreCase("> 'Origin Port' is a required field"), true, "*****test_LIMA_TARR_SF_026_Step_No_3_'Open Port' is the required field message is not displayed*****");
    		}
    		if(strDestinationCountryErrMsg.contains("&#39;"))
    		{
    			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_SF_026_Step_No_3_'Destination Country' is the required field message is not displayed");
    		}
    		if(strCurrencyErrMsg.contains("&#39;"))
    		{
    			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_026_Step_No_3_'Currency' is the required field message is not displayed*****");
    		}
    		if(strValidFromErrMsg.contains("&#39;"))
    		{
    			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_SF_026_Step_No_3_'Valid From' is the required field message is not displayed*****");
    		}
    		if(strValidToErrMsg.contains("&#39;"))
    		{
    			strValidToErrMsg = strValidToErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> 'Valid To' is a required field"), true, "*****test_LIMA_TARR_SF_026_Step_No_3_'Valid To' is the required field message is not displayed*****");
    		}
    	}
    	else
    	{
    		strMessage = "*****test_LIMA_TARR_SF_026_Step_No_4_"+strMessage + "validation message is null*****";
    	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Destination Country value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Currency value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Valid From value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Valid To value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Apply button is not clicked*****");
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_4_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_026_Step_No_5_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_5_FCL Freight Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Origin Port value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry", ""), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Destination Country value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Currency value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Valid From value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Valid To value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Apply button is not clicked*****");
    	
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_026_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_7_FCL Freight Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Origin Port value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Destination Country value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Currency value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Valid From value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Valid To value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Apply button is not clicked*****");
    	
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_8_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_026_Step_No_9_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_9_FCL Freight Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Origin Port value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Destination Country value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Currency value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom", ""), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Valid From value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Valid To value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Apply button is not clicked*****");
    	
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_10_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_026_Step_No_11_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_11_FCL Freight Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_OriginPort", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Origin Port value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Destination Country value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Currency value is not cleared in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Valid From value is not set in FCL Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nRows-1, "FCLFreightCosts_ValidTo", ""), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Valid To value is not cleared in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Apply button is not clicked*****");
    	
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Validation Error pop up is not displayed*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_026_Step_No_13_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_026_Step_No_13_FCL Freight Costs Grid is not displayed*****");
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLFreightCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_026_Step_No_15_Delete Row is not clicked*****");
    	wait(2000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_026_Step_No_15_Yes Button is not clicked in delete row confirmation pop up*****");
    	int nRow1 = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts");
    	objSoftAssert.assertEquals(nRow1 == nRows-1, true, "*****test_LIMA_TARR_SF_026_Step_No_15_Row is not deleted in FCL Freight Costs grid*****");
    	
    	objSoftAssert.assertAll();
    }
   
    @Test(priority = 140)
    public void test_LIMA_TARR_SF_027_FCLFreightCostsGOHCostsMandatoryFieldCheck()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_GOHCharges");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGOHAddRow()), true, "*****test_LIMA_TARR_SF_027_Step_No_1_Add Row button is clicked in FCL GOH Costs Grid*****");
    	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_GOHCharges");
    	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_027_Step_No_1_Row is not added in GOH grid*****");
    	
    	String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nAddedRows-1, "FCLFreightCosts_OriginPort");
    	String strHangingTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts", nAddedRows-1, "FCLFreightCosts_DestinationCountry");
    	
    	if(!strDestinationCountryErrMsg.equals("")||!strHangingTypeErrMsg.equals(""))
    	{
    		if(strDestinationCountryErrMsg.contains("&#39;"))
    		{
    			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_SF_027_Step_No_1_'Open Port' is the required field message is not displayed*****");
    		}
    		if(strHangingTypeErrMsg.contains("&#39;"))
    		{
    			strHangingTypeErrMsg = strHangingTypeErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strHangingTypeErrMsg.equalsIgnoreCase("> 'Hanging Type' is a required field"), true, "*****test_LIMA_TARR_SF_027_Step_No_1_'Hanging Type' is the required field message is not displayed");
    		}
    	}
    		
    		else
        	{
        		strMessage = "*****test_LIMA_TARR_SF_027_Step_No_1_"+strMessage + "validation message is null*****";
        	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_027_Step_No_2_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_027_Step_No_2_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in GOHCharges"), true, "*****test_LIMA_TARR_SF_027_Step_No_2_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_027_Step_No_3_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_GOHCharges"), true, "*****test_LIMA_TARR_SF_027_Step_No_3_GOH Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_GOHCharges", nAddedRows-1, "GOHCharges_DestinationCountry", ""), true, "*****test_LIMA_TARR_SF_027_Step_No_4_Destination Country value is not cleared in GOH Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_GOHCharges", nAddedRows-1, "GOHCharges_HangingType", strHangingTypeSeaFreight), true, "*****test_LIMA_TARR_SF_027_Step_No_4_Hanging Type value is not set in GOH Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_027_Step_No_4_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_027_Step_No_4_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in GOHCharges"), true, "*****test_LIMA_TARR_SF_027_Step_No_4_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_027_Step_No_5_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_GOHCharges"), true, "*****test_LIMA_TARR_SF_027_Step_No_5_GOH Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_GOHCharges", nAddedRows-1, "GOHCharges_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_027_Step_No_6_Destination Country value is not set in GOH Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_GOHCharges", nAddedRows-1, "GOHCharges_HangingType", ""), true, "*****test_LIMA_TARR_SF_027_Step_No_6_Hanging Type value is not set in GOH Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_027_Step_No_6_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_027_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in GOHCharges"), true, "*****test_LIMA_TARR_SF_027_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_027_Step_No_6_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_GOHCharges"), true, "*****test_LIMA_TARR_SF_027_Step_No_6_GOH Costs Grid is not displayed*****");
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGOHDeleteRow()), true, "*****test_LIMA_TARR_SF_027_Step_No_7_Delete Row is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_027_Step_No_7_Delete Row dailog box is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_027_Step_No_8_Yes Button is not clicked in delete row confirmation pop up*****");
    	int nDelteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_GOHCharges");
    	objSoftAssert.assertEquals(nDelteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_027_Step_No_8_Row is not deleted in GOH grid*****");
    	
    	objSoftAssert.assertAll();	
    }
    
     @Test(priority = 145)
    public void test_LIMA_TARR_SF_030_FCLFreightCostsAdditionalFreightCostsMandatoryFieldCheck()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
    	String strMessage = "";
    	
    	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAdditionalFreightCostAddRow()), true, "*****test_LIMA_TARR_SF_030_Step_No_1_Add Row button is clicked in FCL Additional Freight Costs Grid*****");
    	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges");
    	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_030_Step_No_1_Row is not added in Additional Freight Charges grid");
    	
    	String strCharge = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_ChargeType");
    	String strDestinationCountry = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_DestinationCountry");
    	String strCurrency = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Currency");
    	String strCost = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Cost");
    	String strUnitOfMeasure = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_UnitOfMeasure"); 
    	
    	if(!strCharge.equals("")||!strDestinationCountry.equals("")||!strCurrency.equals("")||!strCost.equals("")||!strUnitOfMeasure.equals(""))
    	{
    		if(strCharge.contains("&#39;"))
    		{
    			strCharge = strCharge.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCharge.equalsIgnoreCase("> 'Charge' is a required field"), true, "*****test_LIMA_TARR_SF_030_Step_No_1_'Charge' is the required field message is not displayed*****");
    		}
    		if(strDestinationCountry.contains("&#39;"))
    		{
    			strDestinationCountry = strDestinationCountry.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strDestinationCountry.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_SF_030_Step_No_1_'Destination Country' is the required field message is not displayed");
    		}
    		if(strCurrency.contains("&#39;"))
    		{
    			strCurrency = strCurrency.replace("&#39;", "'");
    			
    			objSoftAssert.assertEquals(strCurrency.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_030_Step_No_1_'Currency' is the required field message is not displayed*****");
    		}
    		if(strCost.contains("&#39;"))
    		{
    			strCost = strCost.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCost.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_SF_030_Step_No_1_'Cost' is the required field message is not displayed*****");
    		}
    		if(strUnitOfMeasure.contains("&#39;"))
    		{
    			strUnitOfMeasure = strUnitOfMeasure.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strUnitOfMeasure.equalsIgnoreCase("> 'Unit of Measure' is a required field"), true, "*****test_LIMA_TARR_SF_030_Step_No_1_'Unit of Measure' is the required field message is not displayed*****");
    		}
    	}
    		
    		else
        	{
        		strMessage = "*****test_LIMA_TARR_SF_030_Step_No_1_"+strMessage + "validation message is null*****";
        	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_030_Step_No_2_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_2_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_2_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_030_Step_No_3_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_3_Additional Freight Cost grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_ChargeType", ""), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Origin Port value is not cleared in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Destination Country value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Currency", strAdditionalFreightCostsCurrency), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Currency value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Cost",strCostSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Costs value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Unit of Measure value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Apply button is not clicked*****");
    	
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_4_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_030_Step_No_5_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_5_Additional Freight Cost grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_ChargeType", strAdditionalFreightCostsCharge), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Origin Port value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_DestinationCountry", ""), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Destination Country value is not cleared in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Currency", strAdditionalFreightCostsCurrency), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Currency value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Cost",strCostSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Cost value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Unit of Measure value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Apply button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_030_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_7_Additional Freight Cost grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_ChargeType", strAdditionalFreightCostsCharge), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Origin Port value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Destination Country value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Currency", ""), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Currency value is not cleared in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Cost",strCostSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Costs value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Unit of Measure value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Apply button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_8_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_030_Step_No_9_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_9_Additional Freight Cost grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_ChargeType", strAdditionalFreightCostsCharge), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Origin Port value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Destination Country value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Currency", strAdditionalFreightCostsCurrency), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Currency value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Cost",""), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Cost value is not cleared in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Unit of Measure value is not set in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Apply button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_10_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_030_Step_No_11_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_11_Additional Freight Cost grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_ChargeType", strAdditionalFreightCostsCharge), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Origin Port value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_DestinationCountry", strDestinationCountrySeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Destination Country value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Currency", strAdditionalFreightCostsCurrency), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Currency value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_Cost",strCostSeaFreight), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Costs value is not set in FCL Additional Freight Cost Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges", nAddedRows-1, "FCLAdditionalFreightCharges_UnitOfMeasure",""), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Unit of Measure value is not cleared in FCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_030_Step_No_6_Apply button is not clicked*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_030_Step_No_13_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges"), true, "*****test_LIMA_TARR_SF_030_Step_No_13_Additional Freight Cost grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAdditionalFreightCostDeleteRow()), true, "*****test_LIMA_TARR_SF_030_Step_No_14_Delete Row is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_030_Step_No_14_Delete Row dailog box is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_030_Step_No_15_Yes Button is not clicked in delete row confirmation pop up*****");
    	int nDelteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLAdditionalFreightCharges");
    	objSoftAssert.assertEquals(nDelteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_030_Step_No_15_Row is not deleted in Additional Freight Cost grid*****");
    	objSoftAssert.assertAll();
    	
    }
     
     @Test(priority = 150)
     public void test_LIMA_TARR_SF_031_FCLLandsideCostsFCLDestinationLandsideCosts_MandatoryFieldCheck()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
     	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
     	String strMessage = "";
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_1_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_1_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLLandsideCostsAddRow()), true, "*****test_LIMA_TARR_SF_031_Step_No_2_Add Row button is not clicked in FCL Destination Landside Costs grid*****");
    	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts");
    	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_031_Step_No_2_Row is not added in FCL Destination Landside Costs grid");
    	
    	String strCharge = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_ChargeType");
    	String strCurrency = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Currency");
    	String strCost = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Cost");
    	String strUnitOfMeasure = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_UnitOfMeasure"); 
    	
    	if(!strCharge.equals("")||!strCurrency.equals("")||!strCost.equals("")||!strUnitOfMeasure.equals(""))
    	{
    		if(strCharge.contains("&#39;"))
    		{
    			strCharge = strCharge.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCharge.equalsIgnoreCase("> 'Charge' is a required field"), true, "*****test_LIMA_TARR_SF_031_Step_No_2_'Charge' is the required field message is not displayed*****");
    		}
    		if(strCurrency.contains("&#39;"))
    		{
    			strCurrency = strCurrency.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCurrency.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_031_Step_No_2_'Currency' is the required field message is not displayed*****");
    		}
    		if(strCost.contains("&#39;"))
    		{
    			strCost = strCost.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCost.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_SF_031_Step_No_2_'Cost' is the required field message is not displayed*****");
    		}
    		if(strUnitOfMeasure.contains("&#39;"))
    		{
    			strUnitOfMeasure = strUnitOfMeasure.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strUnitOfMeasure.equalsIgnoreCase("> 'Unit of Measure' is a required field"), true, "*****test_LIMA_TARR_SF_031_Step_No_2_'Unit of Measure' is the required field message is not displayed*****");
    		}
    	}
    		
    		else
        	{
        		strMessage = "*****test_LIMA_TARR_SF_031_Step_No_2_"+strMessage + "validation message is null*****";
        	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_031_Step_No_3_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_031_Step_No_3_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_3_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_031_Step_No_4_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_5_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_5_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_ChargeType", ""), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Origin Port value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Cost value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Unit of Measure value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_031_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_8_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_8_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_ChargeType", strFCLDestinationLandsideCostsCharge), true, "*****test_LIMA_TARR_SF_031_Step_No_9_Origin Port value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Currency", ""), true, "*****test_LIMA_TARR_SF_031_Step_No_9_Destination Country value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_9_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_9_Step_No_10_Unit of Measure value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_031_Step_No_9_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_031_Step_No_9_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_9_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_031_Step_No_10_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_11_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_11_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_ChargeType", strFCLDestinationLandsideCostsCharge), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Origin Port value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Cost", ""), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Cost value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_UnitOfMeasure",strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Unit of Measure value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_031_Step_No_13_OK Button is not clicked in Validation error pop up*****");
   	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_14_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_14_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_ChargeType", strFCLDestinationLandsideCostsCharge), true, "*****test_LIMA_TARR_SF_031_Step_No_15_Origin Port value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_15_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_031_Step_No_15_Cost value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts", nAddedRows-1, "FCLLandsideCharges_UnitOfMeasure",""), true, "*****test_LIMA_TARR_SF_031_Step_No_15_Unit of Measure value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_031_Step_No_12_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_031_Step_No_15_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_15_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_031_Step_No_16_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_17_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_031_Step_No_17_FCL Destination Landside Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLLandsideCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_031_Step_No_18_Delete Row is not clicked*****");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_031_Step_No_18_Delete Row dailog box is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_031_Step_No_19_Yes Button is not clicked in delete row confirmation pop up*****");
    	int nDelteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLLandsideCosts");
    	objSoftAssert.assertEquals(nDelteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_031_Step_No_19_Row is not deleted in FCL Destination Landside Cost grid*****");
    	
    	objSoftAssert.assertAll();
     }
    
     @Test(priority = 155)
     public void test_LIMA_TARR_SF_032_FCLLandsideCostsOriginLandsideCostsMandatoryFieldCheck()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
      	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
      	String strMessage = "";
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_1_FCL Origin Landside Costs Grid is not displayed*****");
     	
    	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts");
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLOriginLandsideCostsAddRow()), true, "*****test_LIMA_TARR_SF_032_Step_No_1_Add Row button is not clicked in FCL Origin Landside Costs grid*****");
    	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts");
    	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_032_Step_No_1_Row is not added in FCL Origin Landside Costs grid");
    	
    	String strCharge = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_ChargeType");
    	String strCurrency = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Currency");
    	String strCost = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Cost");
    	 
    	
    	if(!strCharge.equals("")||!strCurrency.equals("")||!strCost.equals(""))
    	{
    		if(strCharge.contains("&#39;"))
    		{
    			strCharge = strCharge.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCharge.equalsIgnoreCase("> 'Charge' is a required field"), true, "*****test_LIMA_TARR_SF_032_Step_No_1_'Charge' is the required field message is not displayed*****");
    		}
    		if(strCurrency.contains("&#39;"))
    		{
    			strCurrency = strCurrency.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCurrency.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_032_Step_No_1_'Currency' is the required field message is not displayed*****");
    		}
    		if(strCost.contains("&#39;"))
    		{
    			strCost = strCost.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCost.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_SF_032_Step_No_1_'Cost' is the required field message is not displayed*****");
    		}
    		
    	}
    		
    		else
        	{
        		strMessage = "*****test_LIMA_TARR_SF_032_Step_No_2_"+strMessage + "validation message is null*****";
        	}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_032_Step_No_2_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_032_Step_No_2_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_2_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_032_Step_No_3_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_4_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_4_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_032_Step_No_5_Origin Port value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_032_Step_No_5_Currency value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_032_Step_No_5_Cost value is not set in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_032_Step_No_5_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_032_Step_No_5_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_5_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_032_Step_No_6_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_7_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_7_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_ChargeType", ""), true, "*****test_LIMA_TARR_SF_032_Step_No_8_Origin Port value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_032_Step_No_8_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_032_Step_No_8_Cost value is not set in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_032_Step_No_8_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_032_Step_No_8_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_8_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_032_Step_No_9_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_10_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_10_FCL Destination Landside Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_032_Step_No_11_Origin Port value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_032_Step_No_11_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts", nAddedRows-1, "FCLOriginLandsideCosts_Cost", ""), true, "*****test_LIMA_TARR_SF_032_Step_No_11_Cost value is not cleared in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_032_Step_No_11_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_032_Step_No_11_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_11_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_032_Step_No_12_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "TabFCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_16_FCL Landside Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts"), true, "*****test_LIMA_TARR_SF_032_Step_No_16_FCL Destination Landside Costs Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLOriginLandsideCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_032_Step_No_17_Delete Row button is not clicked in Origin Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_032_Step_No_17_Delete row dialog box is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_032_Step_No_18_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDelteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLOriginLandsideCosts");
    	objSoftAssert.assertEquals(nDelteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_032_Step_No_19_Row is not deleted in FCL Origin Landside Cost grid*****");
    	
    	objSoftAssert.assertAll();
     }
     
     @Test(priority = 160)
     public void test_LIMA_TARR_SF_033_FCLHaulageCostsMandatoryFieldCheck()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
       	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
       	String strMessage = "";
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_1_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_1_FCL Haulage Costs Grid is not displayed*****");
       	
       	int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLHaulageCostsAddRow()), true, "*****test_LIMA_TARR_SF_033_Step_No_2_Add Row button is not clicked in FCL Haulage Costs grid*****");
    	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts");
    	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_033_Step_No_2_Row is not added in FCL Haulage Costs grid");
    	
    	String strDestination = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Destination");
    	String strCurrency = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Currency");
    	String strFSC = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_FSC");
    	 
    	
    	if(!strDestination.equals("")||!strCurrency.equals("")||!strFSC.equals(""))
    	{
    		if(strDestination.contains("&#39;"))
    		{
    			strDestination = strDestination.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strDestination.equalsIgnoreCase("> 'Destination' is a required field"), true, "*****test_LIMA_TARR_SF_033_Step_No_1_'Destination' is the required field message is not displayed*****");
    		}
    		if(strCurrency.contains("&#39;"))
    		{
    			strCurrency = strCurrency.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCurrency.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_033_Step_No_1_'Currency' is the required field message is not displayed*****");
    		}
    		if(strFSC.contains("&#39;"))
    		{
    			strFSC = strFSC.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strFSC.equalsIgnoreCase("> 'FSC %' is a required field"), true, "*****test_LIMA_TARR_SF_033_Step_No_1_'FSC %' is the required field message is not displayed*****");
    		}
    		
    	}
    	
			else
        	{
        		strMessage = "*****test_LIMA_TARR_SF_033_Step_No_2_"+strMessage + "validation message is null*****";
        	}
        
        objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_033_Step_No_3_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_033_Step_No_3_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_3_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_033_Step_No_4_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_5_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_5_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Destination", ""), true, "*****test_LIMA_TARR_SF_033_Step_No_6_Destination value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_6_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_FSC", strFSCSeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_6_FSC % value is not set in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_033_Step_No_6_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_033_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_033_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_8_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_8_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Destination", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Destination value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Currency value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_FSC", strFSCSeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_9_FSC % value is not set in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_033_Step_No_10_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_8_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_8_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Destination", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Destination value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Currency value is not cleared in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_FSC", strFSCSeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_9_FSC % value is not set in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_9_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_033_Step_No_10_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_11_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_11_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Destination", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_12_Destination value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_033_Step_No_12_Currency value is not set in FCL Destination Landed Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts", nAddedRows-1, "FCLHaulageCosts_FSC", ""), true, "*****test_LIMA_TARR_SF_033_Step_No_12_FSC % value is not cleared in FCL Destination Landed Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_033_Step_No_12_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_033_Step_No_12_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_033_Step_No_13_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_14_FCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_033_Step_No_14_FCL Haulage Costs Grid is not displayed*****");
    	
    	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLHaulageCostsDeleteRow()), true, "*****test_LIMA_TARR_SF_033_Step_No_15_Delete Row button is not clicked in Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_033_Step_No_15_Delete row dialog box is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_033_Step_No_16_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts");
    	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_033_Step_No_16_Row is not deleted in FCL Haulage Cost grid*****");
    	
       	objSoftAssert.assertAll();
     }
     
     @Test(priority = 165)
     public void test_LIMA_TARR_SF_034_AdditionalHaulageCosts_MandatoryFieldCheck()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
        String strMessage = "";
         
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_1_FCL Haulage Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_1_FCL Haulage Costs Grid is not displayed*****");
        	
        int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAdditionalHaulageCostAddRow()), true, "*****test_LIMA_TARR_SF_034_Step_No_1_Add Row button is not clicked in FCL Haulage Costs grid*****");
     	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts");
     	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_034_Step_No_1_Row is not added in FCL Addtional Haulage Costs grid under FCL Haulage Cost tab*****");
     	 
     	String strChargeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_ChargeType");
    	String strDestinationErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Destination");
    	String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Currency");
    	 
    	
    	if(!strChargeTypeErrMsg.equals("")||!strDestinationErrMsg.equals("")||!strCurrencyErrMsg.equals(""))
    	{
    		if(strChargeTypeErrMsg.contains("&#39;"))
    		{
    			strChargeTypeErrMsg = strChargeTypeErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strChargeTypeErrMsg.equalsIgnoreCase("> 'Charge Type' is a required field"), true, "*****test_LIMA_TARR_SF_034_Step_No_1_'Charge Type' is the required field message is not displayed*****");
    		}
    		if(strDestinationErrMsg.contains("&#39;"))
    		{
    			strDestinationErrMsg = strDestinationErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strDestinationErrMsg.equalsIgnoreCase("> 'Destination' is a required field"), true, "*****test_LIMA_TARR_SF_034_Step_No_1_'Destination' is the required field message is not displayed*****");
    		}
    		if(strCurrencyErrMsg.contains("&#39;"))
    		{
    			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_034_Step_No_1_'Currency' is the required field message is not displayed*****");
    		}
    		
    	}
    	
			else
        	{
        		strMessage = "*****test_LIMA_TARR_SF_034_Step_No_1_"+strMessage + "validation message is null*****";
        	}
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);

    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_034_Step_No_2_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_034_Step_No_2_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_2_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_034_Step_No_3_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_4_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_4_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_ChargeType", ""), true, "*****test_LIMA_TARR_SF_034_Step_No_5_ChargeType value is not cleared in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Destination", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_034_Step_No_5_Destination value is not set in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_034_Step_No_5_Currency value is not set in FCL Additional Haulage Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_034_Step_No_5_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_034_Step_No_5_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_5_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_034_Step_No_6_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_7_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_7_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_034_Step_No_8_ChargeType value is not set in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Destination", ""), true, "*****test_LIMA_TARR_SF_034_Step_No_8_Destination value is not cleared in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_034_Step_No_8_Currency value is not set in FCL Additional Haulage Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_034_Step_No_8_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_034_Step_No_8_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_8_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_034_Step_No_9_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_10_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_10_FCL Haulage Costs Grid is not displayed*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_034_Step_No_11_ChargeType value is not set in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Destination", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_034_Step_No_11_Destination value is not set in FCL Additional Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts", nAddedRows-1, "FCLAdditionalHaulageCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_034_Step_No_11_Currency value is not cleared in FCL Additional Haulage Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_034_Step_No_11_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_034_Step_No_11_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_11_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_034_Step_No_12_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_13_FCL Haulage Costs tab is not clicked*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts"), true, "*****test_LIMA_TARR_SF_034_Step_No_13_FCL Haulage Costs Grid is not displayed*****");
       	wait(3000);
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAdditionalHaulageCostDeleteRow()), true, "*****test_LIMA_TARR_SF_034_Step_No_14_Delete Row button is not clicked in Additional Haulage Cost grid under FCL Haulage Cost tab*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_034_Step_No_14_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_FCLAdditionalHaulageCosts");
    	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_034_Step_No_15_Row is not deleted in FCL Haulage Cost grid*****");
    
     	objSoftAssert.assertAll();     
     }
     
     @Test(priority = 170)
     public void test_LIMA_TARR_SF_035_DemurrageAndStorageCosts_MandatoryFieldCheck()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
        String strMessage = "";
         
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_035_Step_No_1_FCL Haulage Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_035_Step_No_1_FCL Haulage Costs Grid is not displayed*****");
         
        int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_RentDemurrage");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnDemurrangeAndStorageCostsAddRow()), true, "*****test_LIMA_TARR_SF_035_Step_No_1_Add Row button is not clicked in FCL Demurrage and Storage Costs grid*****");
      	int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_RentDemurrage");
      	objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_035_Step_No_1_Row is not added in Demurrage and Storage Cost grid under FCL Haulage Cost tab*****");
      	
      	String strChargeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_RentDemurrage", nAddedRows-1, "RentDemurrage_ChargeType");
      	if(!strChargeTypeErrMsg.equals(""))
      	{
      		if(strChargeTypeErrMsg.contains("&#39;"))
    		{
    			strChargeTypeErrMsg = strChargeTypeErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strChargeTypeErrMsg.equalsIgnoreCase("> 'Charge Type' is a required field"), true, "*****test_LIMA_TARR_SF_035_Step_No_1_'Charge Type' is the required field message is not displayed*****");
    		}
      		
      		else
      		{
      			strMessage = "*****test_LIMA_TARR_SF_035_Step_No_1_"+strMessage + "validation message is null*****";
      		}
      	}
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	wait(5000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFCLDemurrageAndStorageDeleteRow()), true, "*****test_LIMA_TARR_SF_035_Step_No_15_Delete Row button is not clicked in Demurrage and Storage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_035_Step_No_15_Delete row dialog box is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_035_Step_No_16_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_RentDemurrage");
    	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_035_Step_No_16_Row is not deleted in  Demurrage and Storage Costs grid*****");
    	
         
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 175)
     public void test_LIMA_TARR_SF_036_LCLFreightCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_1_LCL Freight Costs tab is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_1_LCL Freight Costs Grid is not displayed*****");
     	
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAddRowLCLFreightCosts()), true, "*****test_LIMA_TARR_SF_036_Step_No_2_Add Row button is not clicked in LCL Freight Costs grid*****");
         int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts");
      	 
      	 objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_036_Step_No_2_Row is not added under LCL Freight Costs tab*****");
      	 
      	 String strOriginCFSErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_OriginCFS");
      	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_Currency");
      	 String strChargeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_PerWM");
     	
     	 if(!strOriginCFSErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strChargeErrMsg.equals(""))
     	 {
     		 if(strOriginCFSErrMsg.contains("&#39;"))
     		 {
     			 strOriginCFSErrMsg = strOriginCFSErrMsg.replace("&#39;", "'");
     			 
     			 objSoftAssert.assertEquals(strOriginCFSErrMsg.equalsIgnoreCase("> 'Origin CFS' is a required field"), true, "*****test_LIMA_TARR_SF_036_Step_No_2_'Charge Type' is the required field message is not displayed*****");
     		 }
     		 if(strCurrencyErrMsg.contains("&#39;"))
     		 {
     			 strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
     			 

     			 objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_036_Step_No_2_'Destination' is the required field message is not displayed*****");
     		 }
     		 if(strChargeErrMsg.contains("&#39;"))
     		 {
     			 strChargeErrMsg = strChargeErrMsg.replace("&#39;", "'");
     			 objSoftAssert.assertEquals(strChargeErrMsg.equalsIgnoreCase("> 'Charge Per W/M' is a required field"), true, "*****test_LIMA_TARR_SF_036_Step_No_2_'Currency' is the required field message is not displayed*****");
     		 }
     	 }else{
     		 strMessage = "*****test_LIMA_TARR_SF_036_Step_No_2_"+strMessage + "validation message is null*****";
     	 }

     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_036_Step_No_3_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_036_Step_No_3_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_3_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_036_Step_No_4_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_5_ LCl Freight Costs tab is not clicked*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_5_ LCL Freight Costs Grid is not displayed*****");
    	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_OriginCFS", ""), true, "*****test_LIMA_TARR_SF_036_Step_No_6_Origin CFS value is not set in LCL Freight Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_036_Step_No_6_CurrencyType value is not set in LCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_PerWM", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_036_Step_No_6_Charge PerW/M value is not set in LCL Freight Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_036_Step_No_6_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_036_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_036_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_8_ LCl Freight Costs tab is not clicked*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_8_ LCL Freight Costs Grid is not displayed*****");
       	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_OriginCFS", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_036_Step_No_9_Origin CFS value is not set in LCL Freight Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_036_Step_No_9_CurrencyType value is not set in LCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_PerWM", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_036_Step_No_9_Charge PerW/M value is not set in LCL Freight Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_036_Step_No_9_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_036_Step_No_9_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_9_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_036_Step_No_10_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_11_ LCl Freight Costs tab is not clicked*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_11_ LCL Freight Costs Grid is not displayed*****");
       	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_OriginCFS", strOriginPortSeaFreight), true, "*****test_LIMA_TARR_SF_036_Step_No_12_Origin CFS value is not set in LCL Freight Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_036_Step_No_12_CurrencyType value is not set in LCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts", nAddedRows-1, "LCLFreightCosts_PerWM", ""), true, "*****test_LIMA_TARR_SF_036_Step_No_12_Charge PerW/M value is not set in LCL Freight Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_036_Step_No_12_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_036_Step_No_12_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_036_Step_No_12_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_12_ LCl Freight Costs tab is not clicked*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_036_Step_No_12_ LCL Freight Costs Grid is not displayed*****");
      	wait(3000);
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnDeleteRowLCLFreightCosts()), true, "*****test_LIMA_TARR_SF_036_Step_No_13_Delete Row button is not clicked under LCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_036_Step_No_14_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLFreightCosts");
    	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_036_Step_No_14_Row is not deleted in LCL Freight Cost grid*****");
    
    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
     	objSoftAssert.assertAll(); 
     	     }
     
     @Test(priority = 176)
     public void test_LIMA_TARR_SF_037_AdditionalLCLFreightCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_1_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_1_LCL Additional LCL Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         	
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnLCLAdditionalFreightChargesAddRow()), true, "*****test_LIMA_TARR_SF_037_Step_No_1_Add Row button is not clicked in LCL Additional LCL Freight Costs grid in Create New Seafreight Tariff page*****");
      	 int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges");
      	 objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_037_Step_No_1_Row is not added in LCL Additional  Freight Costs grid under LCL Freights Cost tab  in Create New Seafreight Tariff page*****");
      	
    	 wait(3000);
      	 String strChargeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_ChargeType");
      	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Currency");
    	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Cost");
    	 String strMinChargeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_MinimumCharge");
    	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_UnitOfMeasure");
    	 
    	 if(!strChargeTypeErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strMinChargeErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals(""))
     	{
     		if(strChargeTypeErrMsg.contains("&#39;"))
     		{
     			strChargeTypeErrMsg = strChargeTypeErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strChargeTypeErrMsg.equalsIgnoreCase("> 'Charge Type' is a required field"), true, "*****test_LIMA_TARR_SF_037_Step_No_1_'Charge Type' is the required field message is not displayed in LCL Additional Freight Cost grid under LCL Freight Cost tab in Create new SeaFreight Tariff page*****");
     		}
     		if(strCurrencyErrMsg.contains("&#39;"))
     		{
     			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_037_Step_No_1_'Currency' is the required field message is not displayed in LCL Additional Freight Cost grid under LCL Freight Cost tab in Create new SeaFreight Tariff page*****");
     		}
     		if(strCostErrMsg.contains("&#39;"))
     		{
     			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_SF_037_Step_No_1_'Cost' is the required field message is not displayed  in LCL Additional Freight Cost grid under LCL Freight Cost tab in Create new SeaFreight Tariff page*****");
     		}
     		if(strMinChargeErrMsg.contains("&#39;"))
     		{
     			strMinChargeErrMsg = strMinChargeErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strMinChargeErrMsg.equalsIgnoreCase("> 'Minimum Charge' is a required field"), true, "*****test_LIMA_SF_037_Step_No_1_'Minimum Charge is a required field message is not displayed in LCL Additional Freight Cost grid under LCL Freight Cost tab in Create new SeaFreight Tariff page*****");
     		}
     		
     		if(strUnitOfMeasureErrMsg.contains("&#39;"))
     		{
     			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit of Measure' is a required field"), true, "*****test_LIMA_SF_037_Step_No_1_'Unit of Measure' is a required field message is not displayed in LCL Additional Freight Cost grid under LCL Freight Cost tab in Create new SeaFreight Tariff page*****");
     		}
     		
     	}
    	 else
    	 {
    		 strMessage = "*****test_LIMA_SF_037_Step_No_5_Validation Message is empty*****";
    	 }
    	 objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_037_Step_No_2_Apply button is not clicked in Create New SeaFreight Tariff page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_2_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_2_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_037_Step_No_3_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_3_Page is not in FCL Freight cost page after Validation pop up is closed*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_4_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_4_LCL Additional LCL Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_ChargeType", ""), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Charge field is not cleared in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Currency field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Cost field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_MinimumCharge", strMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Minimum Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_UnitOfMeasure", strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Unit Of Measure field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Apply button is not clicked in Create New SeaFreight Tariff page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_5_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_037_Step_No_6_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_6_Page is not in FCL Freight cost page after Validation pop up is closed*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_7_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_7_LCL Additional LCL Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Currency", ""), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Currency field is not cleared in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Cost field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_MinimumCharge", strMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Minimum Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_UnitOfMeasure", strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Unit Of Measure field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Apply button is not clicked in Create New SeaFreight Tariff page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_8_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_037_Step_No_9_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_9_Page is not in FCL Freight cost page after Validation pop up is closed*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_10_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_10_LCL Additional LCL Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Currency field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Cost", ""), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Cost field is not cleared in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_MinimumCharge", strMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Minimum Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_UnitOfMeasure", strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Unit Of Measure field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Apply button is not clicked in Create New SeaFreight Tariff page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_11_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_037_Step_No_12_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_12_Page is not in FCL Freight cost page after Validation pop up is closed*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_13_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_13_LCL Additional LCL Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Currency field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Cost field is not cleared in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_MinimumCharge", ""), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Minimum Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_UnitOfMeasure", strUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Unit Of Measure field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Apply button is not clicked in Create New SeaFreight Tariff page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_14_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_037_Step_No_15_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_15_Page is not in FCL Freight cost page after Validation pop up is closed*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_16_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_16_LCL Additional LCL Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_ChargeType", strChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Charge field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Currency field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Cost field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_MinimumCharge", strMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Minimum Charge field is not cleared in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges", nAddedRows-1, "LCLAdditionalLCLFreightCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Unit Of Measure field is not set in Additional LCL Freight Cost grid under LCL Freight Cost tab in Create New Seafreight Tariff page*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Apply button is not clicked in Create New SeaFreight Tariff page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_17_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_037_Step_No_18_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
    	 wait(3000);
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_18_Page is not in FCL Freight cost page after Validation pop up is closed*****");
    	 
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLFreightCosts"), true, "*****test_LIMA_TARR_SF_037_Step_No_19_LCL Freigt Costs tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges"), true, "*****test_LIMA_TARR_SF_037_Step_No_19_LCL Additional Freight Costs Grid is not displayed in Create New Seafreight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnLCLAdditionalFreightChargesDeleteRow()), true, "*****test_LIMA_TARR_SF_037_Step_No_20_Delete Row button is not clicked in LCL Additional Freight Cost grid in Create New SeaFreight Tariff page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_037_Step_No_20_Delete row dialog box is not displayed in LCL Additional Freight Cost grid in Create New SeaFreight Tariff page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_037_Step_No_21_Yes Button is not clicked in delete row button pop up in LCL Additional Freight Cost grid in Create New SeaFreight Tariff page*****");
      	
     	 int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLAdditionalLCLFreightCharges");
     	 objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_037_Step_No_21_Row is not deleted in LCL Additional Freight Cost grid*****");
     	
    	 objSoftAssert.assertAll();
     }
     
     @Test(priority = 180)
     public void test_LIMA_TARR_SF_038_LCLLandSideCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_1_LCL Landside Costs tab is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_1_LCL Landside Costs Grid is not displayed*****");
     	
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAddRowLCLLanadsideCosts()), true, "*****test_LIMA_TARR_SF_038_Step_No_2_Add Row button is not clicked in LCL Landside Costs grid*****");
         int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts");
      	 objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_038_Step_No_2_Row is not added under LCL Landside Costs tab*****");
      	 
      	 String strChargetypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_ChargeType");
      	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Currency");
      	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Cost");
      	 String strUnitofMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCharges_UnitOfMeasure");

     	 if(!strChargetypeErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitofMeasureErrMsg.equals(""))
     	 {
     		 if(strChargetypeErrMsg.contains("&#39;"))
     		 {
     			strChargetypeErrMsg = strChargetypeErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strChargetypeErrMsg.equalsIgnoreCase("> 'Charge Type' is a required field"), true, "*****test_LIMA_TARR_SF_038_Step_No_2_'Charge Type' is the required field message is not displayed*****");
     		 }
     		 if(strCurrencyErrMsg.contains("&#39;"))
     		 {
     			 strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
     			 objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_038_Step_No_2_'Currency' is the required field message is not displayed*****");
     		 }
     		 if(strCostErrMsg.contains("&#39;"))
     		 {
     			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_SF_038_Step_No_2_'Cost' is the required field message is not displayed*****");
     		 }
     		if(strUnitofMeasureErrMsg.contains("&#39;"))
    		 {
     			strUnitofMeasureErrMsg = strUnitofMeasureErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strUnitofMeasureErrMsg.equalsIgnoreCase("> 'Unit of Measure' is a required field"), true, "*****test_LIMA_TARR_SF_038_Step_No_2_'Unit of Measure' is the required field message is not displayed*****");
    		 }
     	 }else{
     		 strMessage = "*****test_LIMA_TARR_SF_038_Step_No_2_"+strMessage + "validation message is null*****";
     	 }

     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_038_Step_No_3_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_038_Step_No_3_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_3_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_038_Step_No_4_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_5_LCL Landside Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_5_LCL Landside Costs Grid is not displayed*****");
    	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_ChargeType", ""), true, "*****test_LIMA_TARR_SF_038_Step_No_6_ChargeType value is not set in LCL Landside Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_6_CurrencyType value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_6_Cost value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCharges_UnitOfMeasure", strLCLUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_6_UnitOfMeasure value is not set in LCL Landside Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_038_Step_No_6_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_038_Step_No_6_Validation Error pop up is not displayed*****");    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_038_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_8_LCL Landside Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_8_LCL Landside Costs Grid is not displayed*****");
    	
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_ChargeType", strLCLChangeTypeSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_9_ChargeType value is not set in LCL Landside Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_038_Step_No_9_CurrencyType value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_9_Cost value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCharges_UnitOfMeasure", strLCLUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_9_UnitOfMeasure value is not set in LCL Landside Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_038_Step_No_9_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_038_Step_No_9_Validation Error pop up is not displayed*****");    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_9_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_038_Step_No_10_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_11_LCL Landside Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_11_LCL Landside Costs Grid is not displayed*****");
    	
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_ChargeType", strLCLChangeTypeSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_12_ChargeType value is not set in LCL Landside Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_12_CurrencyType value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Cost", ""), true, "*****test_LIMA_TARR_SF_038_Step_No_12_Cost value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCharges_UnitOfMeasure", strLCLUnitOfMeasureSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_12_UnitOfMeasure value is not set in LCL Landside Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_038_Step_No_12_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_038_Step_No_12_Validation Error pop up is not displayed*****");    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_038_Step_No_13_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_14_LCL Landside Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_14_LCL Landside Costs Grid is not displayed*****");
    	
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_ChargeType", strLCLChangeTypeSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_15_ChargeType value is not set in LCL Landside Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Currency", strCurrencySeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_15_CurrencyType value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCosts_Cost", strCostSeaFreight), true, "*****test_LIMA_TARR_SF_038_Step_No_15_Cost value is not set in LCL Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts", nAddedRows-1, "LCLLandsideCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_SF_038_Step_No_15_UnitOfMeasure value is not set in LCL Landside Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_038_Step_No_15_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_038_Step_No_15_Validation Error pop up is not displayed*****");    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_15_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_038_Step_No_16_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLLansideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_17_LCL Landside Costs tab is not clicked*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts"), true, "*****test_LIMA_TARR_SF_038_Step_No_17_LCL Landside Costs Grid is not displayed*****");
        wait(3000);
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnDeleteRowLCLLandsideCosts()), true, "*****test_LIMA_TARR_SF_038_Step_No_18_Delete Row button is not clicked under LCL Freight Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_038_Step_No_18_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLLandsideCosts");
    	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_038_Step_No_18_Row is not deleted in LCL Landside Cost grid*****");
    
    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
     	objSoftAssert.assertAll(); 
     	      
     }
     
     @Test(priority = 185)
     public void test_LIMA_TARR_SF_039_LCLHaulageCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_1_LCL Haulage Costs tab is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_1_LCL Haulage Costs Grid is not displayed*****");
     	
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAddRowLCLHaulageCosts()), true, "*****test_LIMA_TARR_SF_039_Step_No_2_Add Row button is not clicked in LCL Haulage Costs grid*****");
         int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts");
         
      	 objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_039_Step_No_2_Row is not added under LCL Haulage Costs tab*****");
      	 
      	 String strDestinationErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_DestinationPort");
      	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_Currency");
      	 String strChargePerPalletErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_PerPallet");
      	 String strMinimumChargeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_MinimumCharge");

     	 if(!strDestinationErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strChargePerPalletErrMsg.equals("")||!strMinimumChargeErrMsg.equals(""))
     	 {
     		 if(strDestinationErrMsg.contains("&#39;"))
     		 {
     			strDestinationErrMsg = strDestinationErrMsg.replace("&#39;", "'");
     			 
     			objSoftAssert.assertEquals(strDestinationErrMsg.equalsIgnoreCase("> 'Destination' is a required field"), true, "*****test_LIMA_TARR_SF_039_Step_No_2_'Destination' is the required field message is not displayed*****");
     		 }
     		 if(strCurrencyErrMsg.contains("&#39;"))
     		 {
     			 strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
     			 objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_SF_039_Step_No_2_'Currency' is the required field message is not displayed*****");
     		 }
     		 if(strChargePerPalletErrMsg.contains("&#39;"))
     		 {
     			strChargePerPalletErrMsg = strChargePerPalletErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strChargePerPalletErrMsg.equalsIgnoreCase("> 'Charge Per Pallet' is a required field"), true, "*****test_LIMA_TARR_SF_039_Step_No_2_'Charge Per Pallet' is the required field message is not displayed*****");
     		 }
     		if(strMinimumChargeErrMsg.contains("&#39;"))
    		 {
     			strMinimumChargeErrMsg = strMinimumChargeErrMsg.replace("&#39;", "'");
    			objSoftAssert.assertEquals(strMinimumChargeErrMsg.equalsIgnoreCase("> 'Minimum Charge' is a required field"), true, "*****test_LIMA_TARR_SF_039_Step_No_2_'Minimum Charge' is the required field message is not displayed*****");
    		 }
     	 }else{
     		 strMessage = "*****test_LIMA_TARR_SF_039_Step_No_2_"+strMessage + "validation message is null*****";
     	 }

     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_039_Step_No_3_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_039_Step_No_3_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_3_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_039_Step_No_4_OK Button is not clicked in Validation error pop up*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_5_LCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_5_LCL Haulage Costs Grid is not displayed*****");
     	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_DestinationPort", ""), true, "*****test_LIMA_TARR_SF_039_Step_No_6_ DestinationPort value is not set in LCL Haulage Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_Currency", strLCLCurrencyGBPSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_6_ CurrencyType value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_PerPallet", strLCLChargePerPalletSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_6_ Charge Per Pallet value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_MinimumCharge", strLCLMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_6_ Minimum Charge value is not set in LCL Haulage Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_039_Step_No_6_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_039_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_039_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_8_LCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_8_LCL Haulage Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_DestinationPort", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_9_ DestinationPort value is not set in LCL Haulage Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_Currency", ""), true, "*****test_LIMA_TARR_SF_039_Step_No_9_ CurrencyType value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_PerPallet", strLCLChargePerPalletSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_9_ Charge Per Pallet value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_MinimumCharge", strLCLMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_9_ Minimum Charge value is not set in LCL Haulage Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_039_Step_No_9_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_039_Step_No_9_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_9_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_039_Step_No_10_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_11_LCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_11_LCL Haulage Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_DestinationPort", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_12_ DestinationPort value is not set in LCL Haulage Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_Currency", strLCLCurrencyGBPSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_12_ CurrencyType value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_PerPallet", ""), true, "*****test_LIMA_TARR_SF_039_Step_No_12_ Charge Per Pallet value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_MinimumCharge", strLCLMinimumChargeSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_12_ Minimum Charge value is not set in LCL Haulage Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_039_Step_No_12_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_039_Step_No_12_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_12_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_039_Step_No_13_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_14_LCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_14_LCL Haulage Costs Grid is not displayed*****");
     	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_DestinationPort", strDestinationSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_15_ DestinationPort value is not set in LCL Haulage Costs Grid*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_Currency", strLCLCurrencyGBPSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_15_ CurrencyType value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_PerPallet", strLCLChargePerPalletSeaFreight), true, "*****test_LIMA_TARR_SF_039_Step_No_15_ Charge Per Pallet value is not set in LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts", nAddedRows-1, "LCLHaulageCosts_MinimumCharge", ""), true, "*****test_LIMA_TARR_SF_039_Step_No_15_ Minimum Charge value is not set in LCL Haulage Costs Grid*****");

    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_039_Step_No_15_Apply button is not clicked*****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_039_Step_No_15_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_15_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_039_Step_No_16_OK Button is not clicked in Validation error pop up*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_17_LCL Haulage Costs tab is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts"), true, "*****test_LIMA_TARR_SF_039_Step_No_17_LCL Haulage Costs Grid is not displayed*****");
    	wait(3000);
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnDeleteRowLCLHaulageCosts()), true, "*****test_LIMA_TARR_SF_039_Step_No_18_Delete Row button is not clicked under LCL Haulage Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_039_Step_No_18_Yes Button is not clicked in delete row button pop up*****");
     	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_LCLHaulageCosts");
    	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_039_Step_No_18_Row is not deleted in LCL Haulage Cost grid*****");
    
    	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
     	objSoftAssert.assertAll(); 
     	
         
        
     }
     
     @Test(priority = 190)
     public void test_LIMA_TARR_SF_040_AmendmentMandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_AmendmentLog"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_Amendment Log tab is not clicked in Create New Seafreight Tariff page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_Untitled Grid is not displayed under Amendment Log Tab in Create New SeaFreight Tariff page*****");
         
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strSeaFreightPaymentFormName,"Grid_AmendmentLog");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAmendmentLogGrid,6);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_040_Step_No_1_Amendment Log Grid: "+strMessage+"*****");
     	
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_AmendmentLog");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnSeaAmendmentLogAddRow()), true, "*****test_LIMA_TARR_SF_040_Step_No_2_Add Row button is not clicked in Amendment Log grid in Create New Seafreight Tariff page*****");
      	 int nAddedRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_AmendmentLog");
      	 objSoftAssert.assertEquals(nAddedRows == nRows+1, true, "*****test_LIMA_TARR_SF_040_Step_No_1_Row is not added in Amendment Log grid under Amendment Log tab in Create New Seafreight Tariff page*****");
      	
      	 String strDateErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_AmendmentLog", nAddedRows-1, "AmendmentLog_Date");
      	 String strSectionErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_AmendmentLog", nAddedRows-1, "AmendmentLog_Section");
      	 String strChangeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_AmendmentLog", nAddedRows-1, "AmendmentLog_ChangeType");
      	 String strChangeDescriptionErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_AmendmentLog", nAddedRows-1, "AmendmentLog_ChangeDescription");
      	 String strLoggedByErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_AmendmentLog", nAddedRows-1, "AmendmentLog_LoggedBy");
      	 String strCommentsErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strSeaFreightPaymentFormName, "Grid_AmendmentLog", nAddedRows-1, "AmendmentLog_Comments");
      	
      	if(!strDateErrMsg.equals(""))
      	{
      		strDateErrMsg = strDateErrMsg.replace("&#39;", "'");
     		objSoftAssert.assertEquals(strDateErrMsg.equalsIgnoreCase("> You cannot save an empty row"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_'You cannot save an empty row' message is not displayed for Date field in Amendment Log grid under AmendmentLog Tab in Create new SeaFreight Tariff page*****");
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_SF_040_Step_No_2_Validation Message is not displayed in Date Field under Amendment Log Grid under Create new SeaFreight Tariff page";
      	}
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	
      	if(!strSectionErrMsg.equals(""))
      	{
      		strSectionErrMsg = strSectionErrMsg.replace("&#39;", "'");
     		objSoftAssert.assertEquals(strSectionErrMsg.equalsIgnoreCase("> You cannot save an empty row"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_'You cannot save an empty row' message is not displayed for Section field in Amendment Log grid under AmendmentLog Tab in Create new SeaFreight Tariff page*****");
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_SF_040_Step_No_2_Validation Message is not displayed in Section Field under Amendment Log Grid under Create new SeaFreight Tariff page";
      	}
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	
      	if(!strChangeTypeErrMsg.equals(""))
      	{
      		strChangeTypeErrMsg = strChangeTypeErrMsg.replace("&#39;", "'");
     		objSoftAssert.assertEquals(strChangeTypeErrMsg.equalsIgnoreCase("> You cannot save an empty row"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_'You cannot save an empty row' message is not displayed for Change Type field in Amendment Log grid under AmendmentLog Tab in Create new SeaFreight Tariff page*****");
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_SF_040_Step_No_2_Validation Message is not displayed in Change Type Field under Amendment Log Grid under Create new SeaFreight Tariff page";
      	}
        
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	
      	if(!strChangeDescriptionErrMsg.equals(""))
      	{
      		strChangeDescriptionErrMsg = strChangeDescriptionErrMsg.replace("&#39;", "'");
     		objSoftAssert.assertEquals(strChangeDescriptionErrMsg.equalsIgnoreCase("> You cannot save an empty row"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_'You cannot save an empty row' message is not displayed for Change Description field in Amendment Log grid under AmendmentLog Tab in Create new SeaFreight Tariff page*****");
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_SF_040_Step_No_2_Validation Message is not displayed in Change Description Field under Amendment Log Grid under Create new SeaFreight Tariff page";
      	}
        
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	
      	if(!strLoggedByErrMsg.equals(""))
      	{
      		strLoggedByErrMsg = strLoggedByErrMsg.replace("&#39;", "'");
     		objSoftAssert.assertEquals(strLoggedByErrMsg.equalsIgnoreCase("> You cannot save an empty row"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_'You cannot save an empty row' message is not displayed for Logged By field in Amendment Log grid under AmendmentLog Tab in Create new SeaFreight Tariff page*****");
      	}
        
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_SF_040_Step_No_2_Validation Message is not displayed in Logged By Field under Amendment Log Grid under Create new SeaFreight Tariff page";
      	}
      	
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	
      	if(!strCommentsErrMsg.equals(""))
      	{
      		strCommentsErrMsg = strCommentsErrMsg.replace("&#39;", "'");
     		objSoftAssert.assertEquals(strCommentsErrMsg.equalsIgnoreCase("> You cannot save an empty row"), true, "*****test_LIMA_TARR_SF_040_Step_No_1_'You cannot save an empty row' message is not displayed for Comments field in Amendment Log grid under AmendmentLog Tab in Create new SeaFreight Tariff page*****");
      	}
      	
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_SF_040_Step_No_2_Validation Message is not displayed in Comments Field under Amendment Log Grid under Create new SeaFreight Tariff page";
      	}
      	objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_040_Step_No_3_Apply button is not clicked in Create New SeaFreight Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUp()), true, "*****test_LIMA_TARR_SF_040_Step_No_3_Validation Error pop up is not displayed in Create New SeaFreight Tariff page*****");
   	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsg().getText().equals("Validation error in AmendmentLog"), true, "*****test_LIMA_TARR_SF_040_Step_No_3_Validation Error Message is wrong in Create New SeaFreight Tariff page*****");
   	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_SF_040_Step_No_4_OK Button is not clicked in Validation error pop up in Create New SeaFreight Tariff page*****");
   	    wait(3000);
   	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_FCLFreightCosts"), true, "*****test_LIMA_TARR_SF_040_Step_No_4_Page is not in FCL Freight cost page after Validation pop up is closed*****");
   	    
	   	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strSeaFreightPaymentFormName, "TabPanel_Tariff", "LIMA_Tariff_AmendmentLog"), true, "*****test_LIMA_TARR_SF_040_Step_No_23_Amendment Log tab is not clicked in Create New Seafreight Tariff page*****");
	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSeaFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_SF_040_Step_No_23_Untitled Grid is not displayed under Amendment Log Tab in Create New SeaFreight Tariff page*****");
	     
	   	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnSeaAmendmentDeleteRow()), true, "*****test_LIMA_TARR_SF_040_Step_No_24_Delete Row button is not clicked in Amendement Log grid in Create New SeaFreight Tariff page*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_SF_040_Step_No_24_Delete row dialog box is not displayed in Amendement Log grid in Create New SeaFreight Tariff page*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_SF_040_Step_No_25_Yes Button is not clicked in delete row button pop up in Amendement Log grid in Create New SeaFreight Tariff page*****");
	  	
	 	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSeaFreightPaymentFormName, "Grid_AmendmentLog");
	 	objSoftAssert.assertEquals(nDeleteRow == nAddedRows-1, true, "*****test_LIMA_TARR_SF_037_Step_No_21_Row is not deleted in Amendement Log grid*****");
	 	
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 1050)
     public void test_LIMA_TARR_AF_001_CheckForTariffPage()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
        
        
        objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_AF_001_Step_No_1_Untitled page of Type Comobox is not displayed*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_001_Step_No_1_ Type ComboBox is not displayed*****");
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 1055)
     public void test_LIMA_TARR_AF_002_ViewAirFreightTariffHistoryResults()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_AF_002_Step_No_2_Type Combo Box Arrow button is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeAirFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_002_Step_No_1_Value is not set in Type Combo Box as 'AirFreight' ");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_AF_002_Step_No_1_Air Freight Tariff History Grid is not displayed*****");
         
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strSearchFormNameTariff,"Grid_SeaFreight");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlSeafreightTariffHistoryGrid,7);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_AF_002_Step_No_2_AirFreightTariffHistoryGridCaption: "+strMessage+"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_AF_002_Step_No_4_Create New Tariff Button is not displayed*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "DownloadTemplate"), true, "*****test_LIMA_TARR_AF_002_Step_No_4_Download Airfreight template link is not displayed*****");
         
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSearchFormNameTariff, "Grid_SeaFreight");
         
         for(int i=0;i<nRows-1;i++)
         {
         	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isGridElementPresent(strSearchFormNameTariff, "Grid_SeaFreight", i, "ViewTariff"), true, "*****test_LIMA_TARR_AF_002_Step_No_4_View Tariff Link is not displayed*****");
         	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isGridElementPresent(strSearchFormNameTariff, "Grid_SeaFreight", i, "OpenReport"), true, "*****test_LIMA_TARR_SF_002_Step_No_4_Open Report link is not displayed*****");
         }
 		 
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1060)
     public void test_LIMA_TARR_AF_003_CheckPagination()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         
         String strValue = "";
     	
     	 strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
         String[] strVal = strValue.split("of ");
 		 int nAllRows = Integer.parseInt(strVal[1]);
 		
 		if(nAllRows>10)
 		{
 			wait(2000);
 			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnNext()), true, "*****test_LIMA_TARR_AF_003_Step_No_1_Next Button is not clicked*****");
 			wait(2000);
 			strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
 			strVal = strValue.split("rows ");
 			String strVals[] = strVal[1].split("of ");
 			objSoftAssert.assertEquals(strVals[0].contains("11 -"), true, "*****test_LIMA_TARR_AF_003_Step_No_1_Next page is not displayed*****");
 	
 			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnPrevious()), true, "*****test_LIMA_TARR_AF_003_Step_No_2_Previous Button is not clicked*****");
 			strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
 			strVal = strValue.split("rows ");
 			String strVals1[] = strVal[1].split("of ");
 			objSoftAssert.assertEquals(strVals1[0].contains("1 -"), true, "*****test_LIMA_TARR_AF_003_Step_No_2_Previous page is not displayed*****");
 			
 		}
 		else{}
 		objSoftAssert.assertAll();
     }
     
     @Test(priority = 1065)
     public void test_LIMA_TARR_AF_004_CheckViewTarifflink()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         
        String strMessage = "";
     	wait(3000);

     	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_AF_004_Step_No_1_View Deatil is not a hyper link*****");
     	wait(3000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_AF_004_Step_No_1_View SeaFreight Tariff page is not loaded*****");
     	objSoftAssert.assertAll();  
     }
     
//     @Test(priority = 1070)
//     public void test_LIMA_TARR_AF_005_CheckOpenReportLink()
//     {
//    	SoftAssert objSoftAssert = new SoftAssert();
//     	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
//     	String strMessage = "";
//     	wait(3000);
//     	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
//     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_AF_005_Step_No_1_Untitled page with type text field is not displayed*****");
//    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_AF_005_Step_No_1_Type Combo Box Arrow button is not clicked*****");
//     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeAirFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_005_Step_No_2_Sea Freight is not set in type field*****");
//     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_AF_005_Step_No_2_Sea Freight grid is not displayed*****");
//     	wait(2000);
//     	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "OpenReport");
//     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_AF_004_Step_No_3_Open Report is not a hyper link*****");
//     	objAdjunoLIMATariffPOM.getGridCellElement(strSeaFreightPaymentFormName, "Grid_SeaFreight", 0, "OpenReport").click();
//     	objSoftAssert.assertAll();
//     }
     
     @Test(priority = 1075)
     public void test_LIMA_TARR_AF_006_CheckDownloadAirFreightTemplateLink()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
     	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
     	String strMessage = "";
     	wait(3000);
     	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_AF_006_Step_No_1_Untitled page with type text field is not displayed*****");
     	wait(3000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_AF_006_Step_No_1_Type Combo Box Arrow button is not clicked*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeAirFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_006_Step_No_2_Sea Freight is not set in type field*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_AF_006_Step_No_2_Air Freight grid is not displayed*****");
     	strMessage = objAdjunoLIMATariffPOM.isHyperlinkPresent(strSearchFormNameTariff,"DownloadTemplate");
     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_AF_006_Step_No_3_Download Seafreight Template is not a hyper link*****");
     	wait(2000);
     	objAdjunoLIMATariffPOM.getElement(strSearchFormNameTariff, "DownloadTemplate").click();
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isFileDownloaded_Ext("C:\\Users\\Anand\\Downloads",".xlsx"), true, "*****test_LIMA_TARR_AF_006_Step_No_3_The XLSX version of the template is not downloaded on the user's local drive*****");
     	objSoftAssert.assertAll();
     }
     
     @Test(priority = 1080)
     public void test_LIMA_TARR_AF_007_VerifyAirFreightTariffTabs_AirFreightCosts_AirEcoAirRates()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
      	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
      	String strMessage = "";
      	
      	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_AF_007_Step_No_1_Untitled page with type text field is not displayed*****");
      	wait(3000);
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_AF_007_Step_No_1_Type Combo Box Arrow button is not clicked*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.getDropDownValue(strSearchFormNameTariff, "ComboboxTypeTariff", 1), strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_007_Step_No_2_Air Freight is not set in type field*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_AF_007_Step_No_2_Air Freight grid is not displayed*****");
      	wait(2000);
      	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_AF_007_Step_No_3_View Deatil is not a hyper link*****");
     	wait(3000);
     	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_AirRates");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAirEcoAirRatesgridData,25);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_007_Step_No_3_Air Rates Grid: "+strMessage+"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirRatesAddRow()), true, "*****test_LIMA_TARR_AF_007_Step_No_4_Add Row button is not disabled in Air Rates Grid*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirRatesDeleteRow()), true, "*****test_LIMA_TARR_AF_007_Step_No_4_Delete Row button is disabled in Air Rates Grid*****");
        
    	objSoftAssert.assertAll();	
     }
     
     @Test(priority = 1085)
     public void test_LIMA_TARR_AF_008_VerifyAirFreightTariffTabs_AirFreightCosts_ExpressService()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
       	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
       	String strMessage = "";
       	
       	ArrayList<String> list = new ArrayList<String>();
        
        list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_ExpressService");
        
        strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlExpressServiceGrid,23);
        objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_008_Step_No_1_Express Service Grid: "+strMessage+"*****");
        
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnExpressServiceAddRow()), true, "*****test_LIMA_TARR_AF_008_Step_No_2_Add Row button is not disabled in Express Service Grid*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnExpressServiceDeleteRow()), true, "*****test_LIMA_TARR_AF_008_Step_No_2_Delete Row button is disabled in Express Service Grid*****");
        
       	objSoftAssert.assertAll();
     }
     
     @Test(priority = 1086)
     public void test_LIMA_TARR_AF_009_VerifyAirFreightTariffTabs_AirFreightCosts_AirChargeComments()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Panel_AirCharges_Comments"), true, "*****test_LIMA_TARR_AF_009_Step_No_1_Air Charge Comments fiels do not exist*****");
         try
         {
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strAirFreightPaymentFormName, "TextArea_AirCharges_Comments"), true, "*****test_LIMA_TARR_AF_009_Step_No_1_Air Charge Comments field is not in readonly mode*****");
         }
         catch(NullPointerException e)
         {
        	 strMessage = "*****test_LIMA_TARR_AF_009_Step_No_1_Air Charge Comments field is not in Readonly mode*****";
         }
         objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
         objSoftAssert.assertAll(); 	 
     }
     
     @Test(priority = 1090)
     public void test_LIMA_TARR_AF_010_VerifyAirFreightTariffTabsAirFreightCosts_ExpressServiceComments()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Panel_SpecialServiceComment"), true, "*****test_LIMA_TARR_AF_010_Step_No_1_Express Services Comments fiels do not exist*****");
         try
         {
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strAirFreightPaymentFormName, "TextArea_ExpressService_Comments"), true, "*****test_LIMA_TARR_AF_010_Step_No_1_Express Services Comments field is not in readonly mode*****");
         }
         catch(NullPointerException e)
         {
        	 strMessage = "*****test_LIMA_TARR_AF_010_Step_No_1_Express Services Comments field is not in Readonly mode*****";
         }
         objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1095)
     public void test_LIMA_TARR_AF_011_VerifyAirFreightTariffTabs_AirLandsideCosts_AirLandsideCostsGrid()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirLandsideCosts"), true, "*****test_LIMA_TARR_AF_011_Step_No_1_Air Landside Costs tab is not clicked*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_AF_011_Step_No_2_Air Landside Cost Grid is not displayed*****");
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_LandsideCharges");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAirLandsideCostGrid,11);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_AF_011_Step_No_1_Air Landside Costs Grid: "+strMessage+"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirLandsideCostsAddRow()), true, "*****test_LIMA_TARR_AF_011_Step_No_3_Add Row button is not disabled in Air Landside Cost Grid*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirLandsideCostsDeleteRow()), true, "*****test_LIMA_TARR_AF_011_Step_No_3_Delete Row button is disabled in Air Landside Cost Grid*****");
         
         
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1100)
     public void test_LIMA_TARR_AF_012_VerifyAirFreightTariffTabs_AirLandsideCosts_LandsideChargesCommentsGrid()
     {
    	 
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Panel_LandsideCharges_Comments"), true, "*****test_LIMA_TARR_AF_012_Step_No_1_Landside Charges Comments fiels do not exist*****");
         try
         {
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strAirFreightPaymentFormName, "TextArea_ExpressService_Comments"), true, "*****test_LIMA_TARR_AF_012_Step_No_1_Landside Charges Comments field is not in readonly mode*****");
         }
         catch(NullPointerException e)
         {
        	 strMessage = "*****test_LIMA_TARR_AF_012_Step_No_1_Landside Charges Comments field is not in Readonly mode*****";
         }
         objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
         objSoftAssert.assertAll();
     }
     
    
     @Test(priority = 1105)
     public void test_LIMA_TARR_AF_013_VerifyAirFreightTariffTabs_AirHaulageCosts_AirHaulageCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_SF_013_Step_No_1_Air Haulage Cost tab is not clicked*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_SF_013_Step_No_1_Air Haulage Cost Grid is not displayed*****");
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_HaulageCosts");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAirHaulageCostsGrid,9);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_013_Step_No_2_Air Haulage Costs Grid: "+strMessage+"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirHaulageCostsAddRow()), true, "*****test_LIMA_TARR_AF_013_Step_No_2_Add Row button is not disabled in Air Haulage Cost Grid*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirHaulageCostsDeleteRow()), true, "*****test_LIMA_TARR_AF_013_Step_No_2_Delete Row button is disabled in Air Haulage Cost Grid*****");
         
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1110)
     public void test_LIMA_TARR_AF_014_VerifyAirFreightTariffTabs_AirHaulageCosts_AirStorageCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_SF_014_Step_No_1_Air Storage Cost tab is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_SF_014_Step_No_1_Air Storage Cost grid is not not displayed*****");
         
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_StorageCharges");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAirStorageCostsGrid,10);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_014_Step_No_2_Air Storage Costs Grid: "+strMessage+"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirStorageCostsAddRow()), true, "*****test_LIMA_TARR_AF_014_Step_No_3_Add Row button is not disabled in Air Storage Cost Grid*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirStorageCostsDeleteRow()), true, "*****test_LIMA_TARR_AF_014_Step_No_3_Delete Row button is disabled in Air Storage Cost Grid*****");
         
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1115)
     public void test_LIMA_TARR_AF_015_VerifyAirFreightTariffTabs_AirHaulageCosts_HaulageComments()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Panel_LCLHaulageComments"), true, "*****test_LIMA_TARR_AF_015_Step_No_1_Haulage Costs Comments fiels do not exist*****");
         try
         {
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strAirFreightPaymentFormName, "TextArea_Haulage_Comments"), true, "*****test_LIMA_TARR_AF_015_Step_No_1_Haulage Comments field is not in readonly mode*****");
         }
         catch(NullPointerException e)
         {
        	 strMessage = "*****test_LIMA_TARR_AF_015_Step_No_1_Haulage Costs Comments field is not in Readonly mode*****";
         }
         objSoftAssert.assertEquals(strMessage.equals(""), true, strMessage);
         objSoftAssert.assertAll();  
     }
     
     @Test(priority = 1120)
     public void test_LIMA_TARR_AF_016_VerifyAirFreightTariffTabs_AmendmentLog()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AmendmentLog"), true, "*****test_LIMA_TARR_AF_016_Step_No_1_Amendment Log Cost tab is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_AF_016_Step_No_1_Amendment Log grid is not not displayed*****");
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_AmendmentLog");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAirAmendmentLogGrid,6);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_AF_016_Step_No_2_Air Amendment Log Costs Grid: "+strMessage+"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirAmendmentLogAddRow()), true, "*****test_LIMA_TARR_AF_016_Step_No_3_Add Row button is not disabled in Air Amendment log Grid*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnAirAmendmentLogDeleteRow()), true, "*****test_LIMA_TARR_AF_016_Step_No_3_Delete Row button is disabled in Air Amendment log Grid*****");
         
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1125)
     public void test_LIMA_TARR_AF_017_AirFreight_CreateNewTariff_UpdateAnExistingTariff_AddANewRowOnAirEcoAirRatesGrid()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_AF_017_Step_No_1_An Untitled Page with Type Combobox is not displayed*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_AF_017_Step_No_1_Type Combo Box Arrow button is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.getDropDownValue(strSearchFormNameTariff, "ComboboxTypeTariff", 1), strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_017_Step_No_3_Airfreight Tariff History Grid is not displayed*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_3_AirFreight Tariff history grid is not displayed*****");
         wait(2000);
         strTariffVersion = objAdjunoLIMATariffPOM.getGridCellValue(strSearchFormNameTariff, "Grid_SeaFreight", 0, "TariffVersion");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButton(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_AF_017_Step_No_4_Create new tariff button is not clicked*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.setPresentDate("dd MMM YYYY"), strAirFreightPaymentFormName, "ValidFrom"), true);
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.setFutureDate("dd MMM YYYY"), strAirFreightPaymentFormName, "ValidTo"), true);
         wait(1000);
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "PanelHeader"), true, "*****test_LIMA_TARR_AF_017_Step_No_4_Create New Airfreight Tariff page is not displayed *****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strAirFreightPaymentFormName, "TariffVersion"), true, "*****test_LIMA_TARR_AF_017_Step_No_5_Tariff version field is not in read only mode*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirRatesAddRow()), true, "*****test_LIMA_TARR_AF_017_Step_No_5_Add Row button is clicked in Air Rates grid*****");
         
         wait(2000);
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AirRates");
     	 
         String strOriginPortErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort");
     	 String strServiceTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType");
     	 String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom");
    	 String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo");
     	 
     	 if(!strOriginPortErrMsg.equals("")||!strServiceTypeErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
     	{
     		if(strOriginPortErrMsg.contains("&#39;"))
     		{
     			strOriginPortErrMsg = strOriginPortErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strOriginPortErrMsg.equalsIgnoreCase("> 'Origin Port' is a required field"), true, "*****test_LIMA_TARR_AF_017_Step_No_6_'Open Port' is the required field message is not displayed*****");
     		}
     		if(strServiceTypeErrMsg.contains("&#39;"))
     		{
     			strServiceTypeErrMsg = strServiceTypeErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strServiceTypeErrMsg.equalsIgnoreCase("> 'Service Type ' is a required field"), true, "*****test_LIMA_TARR_AF_017_Step_No_6_'Service Type' is the required field message is not displayed");
     		}
     		
     		if(strValidFromErrMsg.contains("&#39;"))
     		{
     			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_AF_017_Step_No_6_'Valid From' is the required field message is not displayed*****");
     		}
     		if(strValidToErrMsg.contains("&quot;"))
     		{
     				strValidToErrMsg = strValidToErrMsg.replace("&quot;", "\"");
     				objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> The \"Air Rate To\" date must be later than the \"Air Rate From\" date."), true, "*****test_LIMA_TARR_SF_017_Step_No_6_'Valid To' is the required field message is not displayed*****");
     		}
     			
     	}
     	else
     	{
     		strMessage = "*****test_LIMA_TARR_AF_017_Step_No_6_"+strMessage + "validation message is null*****";
     	}
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort", strOriginPortAirFreight), true, "*****test_LIMA_TARR_SF_017_Step_No_7_Origin Port value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType", strServiceTypeAirFreight), true, "*****test_LIMA_TARR_SF_017_Step_No_7_Service Type value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_SF_017_Step_No_7_Valid From value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_SF_017_Step_No_7_Valid To value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_SF_025_Step_No_6_Apply button is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_SF_017_Step_No_7_The user is not redirected to Airfreight Tariff History page*****");
    	wait(2000);
    	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
    	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_AF_017_Step_No_7_View Deatil is not a hyper link*****");
    	wait(7000);
    	int nRows1 = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AirRates");
    	
    	objSoftAssert.assertEquals(strOriginPortAirFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort")), true, "*****test_LIMA_TARR_SF_017_Step_No_8_Origin Port do not match in Air Rates Grid*****");
    	objSoftAssert.assertEquals(strServiceTypeAirFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType")), true, "*****test_LIMA_TARR_SF_017_Step_No_8_Service Type do not match in Air Rates Grid*****");
    	objSoftAssert.assertEquals(strValidFromDate.equals(objAdjunoLIMATariffPOM.getGridCellValue(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom")), true, "*****test_LIMA_TARR_SF_017_Step_No_8_Valid From value do not match in Air Rates Grid*****");
    	objSoftAssert.assertEquals(strValidToDate.equals(objAdjunoLIMATariffPOM.getGridCellValue(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo")), true, "*****test_LIMA_TARR_SF_017_Step_No_8_Valid To value do not match in Air Rates Grid*****");
    	driver.navigate().back();
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 1130)
     public void test_LIMA_TARR_AF_018_AirFreightCostsAirOrEcoAirRates_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_AF_018_Step_No_1_An Untitled Page with Type Combobox is not displayed*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_AF_018_Step_No_1_Type Combo Box Arrow button is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.getDropDownValue(strSearchFormNameTariff, "ComboboxTypeTariff", 1), strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_AF_018_Step_No_3_Airfreight Tariff History Grid is not displayed*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_3_AirFreight Tariff history grid is not displayed*****");
         wait(2000);
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButton(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_AF_018_Step_No_4_Create New Tariff button is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strAirFreightPaymentFormName), true, "*****test_LIMA_TARR_AF_018_Step_No_4_Create New Tariff history page is not displayed*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirRatesAddRow()), true, "*****test_LIMA_TARR_AF_017_Step_No_5_Add Row button is clicked in Air Rates grid*****");
         wait(2000);
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AirRates");
     	 String strOriginPortErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort");
     	 String strServiceTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType");
     	 String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom");
    	 String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo");
     	 
     	 if(!strOriginPortErrMsg.equals("")||!strServiceTypeErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
     	{
     		if(strOriginPortErrMsg.contains("&#39;"))
     		{
     			strOriginPortErrMsg = strOriginPortErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strOriginPortErrMsg.equalsIgnoreCase("> 'Origin Port' is a required field"), true, "*****test_LIMA_TARR_AF_018_Step_No_5_'Open Port' is the required field message is not displayed*****");
     		}
     		if(strServiceTypeErrMsg.contains("&#39;"))
     		{
     			strServiceTypeErrMsg = strServiceTypeErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strServiceTypeErrMsg.equalsIgnoreCase("> 'Service Type ' is a required field"), true, "*****test_LIMA_TARR_AF_018_Step_No_5_'Service Type' is the required field message is not displayed");
     		}
     		
     		if(strValidFromErrMsg.contains("&#39;"))
     		{
     			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_AF_018_Step_No_5_'Valid From' is the required field message is not displayed*****");
     		}
     		if(strValidToErrMsg.contains("&quot;"))
     		{
     				strValidToErrMsg = strValidToErrMsg.replace("&quot;", "\"");
     				objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> The \"Air Rate To\" date must be later than the \"Air Rate From\" date."), true, "*****test_LIMA_TARR_AF_018_Step_No_5_'Valid To' is the required field message is not displayed*****");
     		}
     			
     	}
     	else
     	{
     		strMessage = "*****test_LIMA_TARR_AF_018_Step_No_5_"+strMessage + "validation message is null*****";
     	}
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_5_Apply button is not clicked*****");
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_018_Step_No_5_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_5_Validation Error Message is wrong*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_018_Step_No_5_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_018_Step_No_5_Air/Eco Air Rates Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort", strOriginPortAirFreight), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Origin Port value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType", ""), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Service Type value is not cleared in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Valid From value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Valid To value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Apply button is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Apply button is not clicked*****");
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_018_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_018_Step_No_7_Air/Eco Air Rates Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort", strOriginPortAirFreight), true, "*****test_LIMA_TARR_AF_017_Step_No_8_Origin Port value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType", strServiceTypeAirFreight), true, "*****test_LIMA_TARR_AF_017_Step_No_8_Service Type value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom", ""), true, "*****test_LIMA_TARR_AF_017_Step_No_8_Valid From value is not cleared in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_AF_017_Step_No_8_Valid To value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_8_Apply button is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Apply button is not clicked*****");
    	wait(1000);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_018_Step_No_8_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_8_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_018_Step_No_9_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_018_Step_No_9_Air/Eco Air Rates Grid is not displayed*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_OriginPort", strOriginPortAirFreight), true, "*****test_LIMA_TARR_AF_017_Step_No_10_Origin Port value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ServiceType", strServiceTypeAirFreight), true, "*****test_LIMA_TARR_AF_017_Step_No_10_Service Type value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_AF_017_Step_No_10_Valid From value is not set in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AirRates", nRows-1, "AirRates_ValidTo", ""), true, "*****test_LIMA_TARR_AF_017_Step_No_10_Valid To value is not cleared in Air Rates Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_10_Apply button is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Apply button is not clicked*****");
    	wait(1000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_018_Step_No_10_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_10_Validation Error Message is wrong*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_018_Step_No_11_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_018_Step_No_11_Air/Eco Air Rates Grid is not displayed*****");
    
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirRatesDeleteRow()), true, "*****test_LIMA_TARR_AF_018_Step_No_12_DeleteRow Button is not clicked*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_018_Step_No_12_Yes Button is not clicked in Delete Row Dailog box*****");
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AirRates");
    	
    	objSoftAssert.assertEquals(nDeleteRow == nRows-1, true, "*****test_LIMA_TARR_AF_018_Step_No_12_Row is not deleted in Air Rates Grid*****");
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 1135)
     public void test_LIMA_TARR_AF_017_AirFreightCostExpressService_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
        
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnExpressServiceAddRow()), true, "*****test_LIMA_TARR_AF_017_Step_No_1_Add Row button is clicked in Express Service grid*****");
         int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_ExpressService");
        // objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_017_Step_No_1_Apply button is not clicked in Create Air Freight Tariff page*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_ExpressService"), true, "*****test_LIMA_TARR_AF_017_Step_No_1_Express Service Grid is not displayed******");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnExpressServiceDeleteRow()), true, "*****test_LIMA_TARR_AF_017_Step_No_2_DeleteRow Button is not clicked in Express Service*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_017_Step_No_2_Yes Button is not clicked in Delete Row Dailog box for Express Service*****");
     	 int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_ExpressService");
     	 
     	 objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_AF_017_Step_No_2_Row is not deleted in Express Service grid*****");
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1140)
     public void test_LIMA_TARR_AF_020_AirLandsideCostsAirLandsideCost_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirLandsideCosts"), true, "*****test_LIMA_TARR_AF_020_Step_No_1_Air Landside Costs tab is not clicked*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_AF_020_Step_No_1_Air Landside Cost Grid is not displayed*****");
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_LandsideCharges");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirLandsideCostsAddRow()), true, "*****test_LIMA_TARR_AF_020_Step_No_2_Add Row button is not clicked in Air Landside Cost Grid*****");
         int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_LandsideCharges");
         objSoftAssert.assertEquals(nAddRow == nRows+1, true, "*****test_LIMA_TARR_AF_020_Step_No_2_Row is not added in Air Landside Grid*****");
         
         String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_LandsideCharges", nRows-1, "AirLandsideCharges_Currency");
     	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_LandsideCharges", nRows-1, "AirLandsideCharges_Cost");
     	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_LandsideCharges", nRows-1, "AirLandsideCharges_UnitOfMeasure");
    	 
     	 
     	 if(!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals(""))
     	{
     		if(strCurrencyErrMsg.contains("&#39;"))
     		{
     			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_AF_020_Step_No_2_'Currency' is the required field message is not displayed*****");
     		}
     		if(strCostErrMsg.contains("&#39;"))
     		{
     			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_AF_020_Step_No_2_'Cost' is the required field message is not displayed");
     		}
     		
     		if(strUnitOfMeasureErrMsg.contains("&#39;"))
     		{
     			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
     			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit of Measure' is a required field"), true, "*****test_LIMA_TARR_AF_020_Step_No_2_'Unit Of Measure' is the required field message is not displayed*****");
     		}
     		
     	}
     	else
     	{
     		strMessage = "*****test_LIMA_TARR_AF_020_Step_No_2_"+strMessage + "validation message is null*****";
     	}
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_3_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_3_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_020_Step_No_3_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_3_Validation Error Message is wrong*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_020_Step_No_4_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_020_Step_No_4_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirLandsideCosts"), true, "*****test_LIMA_TARR_AF_020_Step_No_5_Air Landside Costs tab is not clicked*****");	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_AF_020_Step_No_5_Air Landside Cost Grid is not displayed*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_Currency", ""), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Currency value is not cleared in Air Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_Cost",strCostAirFreight ), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Cost value is not set in Air Landside Costs Grid*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_UnitOfMeasure", strUnitOfMeasureAirFreight), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Unit of Measure value is not set in Air Landside Costs Grid*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Validation Error pop up is not displayed*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Validation Error Message is wrong*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_020_Step_No_7_OK Button is not clicked in Validation error pop up*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_020_Step_No_7_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirLandsideCosts"), true, "*****test_LIMA_TARR_AF_020_Step_No_8_Air Landside Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_AF_020_Step_No_8_Air Landside Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_020_Step_No_9_\"Currency\" value is not set in Air Landside Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_Cost","" ), true, "*****test_LIMA_TARR_AF_020_Step_No_9_\"Cost\" value is not cleared in Air Landside Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_UnitOfMeasure", strUnitOfMeasureAirFreight), true, "*****test_LIMA_TARR_AF_020_Step_No_9_\"Unit of Measure\" value is not set in Air Landside Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_9_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_9_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_020_Step_No_9_Validation Error pop up is not displayed in \"Create New Air Tariff\" page when mamdatory field \"Cost\" is left blank*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_9_Validation Error Message is wrong when mandatory field \"Cost\" is left blank in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
        
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_020_Step_No_10_OK Button is not clicked in Validation error popup in \"Create new Airfreight Tariff\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_020_Step_No_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirLandsideCosts"), true, "*****test_LIMA_TARR_AF_020_Step_No_11_Air Landside Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_AF_020_Step_No_11_Air Landside Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_020_Step_No_11_\"Currency\" value is not set in Air Landside Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_Cost",strCostAirFreight ), true, "*****test_LIMA_TARR_AF_020_Step_No_11_\"Cost\" value is not set in Air Landside Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_LandsideCharges", nAddRow-1, "AirLandsideCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_AF_020_Step_No_11_\"Unit of Measure\" value is not cleared in Air Landside Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_11_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_020_Step_No_11_Apply button is not clicked in Create New Air Tariff page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_020_Step_No_11_Validation Error pop up is not displayed in \"Create New Air Tariff\" page when mamdatory field \"Unit of Measure\" is left blank*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_11_Validation Error Message is wrong when mandatory field \"Unit of Measure\" is left blank in  \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
        
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_020_Step_No_12_OK Button is not clicked in Validation error popup in \"Create new Airfreight Tariff\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_020_Step_No_12_Air Landside Costs Grid  is not displayed in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirLandsideCosts"), true, "*****test_LIMA_TARR_AF_020_Step_No_13_Air Landside Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_AF_020_Step_No_13_Air Landside Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirLandsideCostsDeleteRow()), true, "*****test_LIMA_TARR_AF_020_Step_No_14_\"Delete Row Button\" is not clicked in \"Air Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_AF_020_Step_No_14_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Airf Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_020_Step_No_15_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Air Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_LandsideCharges");
    	objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_AF_020_Step_No_15_Row is not deleted in \"Air Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Landside Cost Tab\"*****");
    	objSoftAssert.assertAll();
         
     }
     
     @Test(priority = 1140)
     public void test_LIMA_TARR_AF_021_AirHaulageCostsMandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_1_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_1_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
         int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_HaulageCosts");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirHaulageCostsAddRow()), true, "*****test_LIMA_TARR_AF_021_Step_No_2_Add Row button is not clicked in Air Haulage Cost Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_HaulageCosts");
         objSoftAssert.assertEquals(nAddRow == nRows+1, true, "*****test_LIMA_TARR_AF_021_Step_No_2_Row is not added in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
         String strDestinationErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_DesctinationPort");
     	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_Currency");
     	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_PerPallet");
    	 String strMinimumChargeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_MinimumCharge");
         
    	 if(!strDestinationErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strMinimumChargeErrMsg.equals(""))
      		{
      		if(strDestinationErrMsg.contains("&#39;"))
      		{
      			strDestinationErrMsg = strDestinationErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strDestinationErrMsg.equalsIgnoreCase("> 'Destination' is a required field"), true, "*****test_LIMA_TARR_AF_021_Step_No_2_\"'Destination' is the required field\" message is not displayed as Destination field is 'Mandatory' in \"Air Haulage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		if(strCurrencyErrMsg.contains("&#39;"))
      		{
      			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_AF_021_Step_No_2_\"'Currency' is the required field \" message is not displayed as Currency field is 'Mandatory' in \"Air Haulage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      		if(strCostErrMsg.contains("&#39;"))
      		{
      			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_AF_021_Step_No_2_\"'Cost' is the required field\" message is not displayed as Cost field is 'Mandatory' in \"Air Haulage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      		if(strMinimumChargeErrMsg.contains("&#39;"))
      			
      		{
      			strMinimumChargeErrMsg = strMinimumChargeErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strMinimumChargeErrMsg.equalsIgnoreCase("> 'Minimum Charge' is a required field"), true, "*****test_LIMA_TARR_AF_021_Step_No_2_\"'Minimum Charge' is the required field\" message is not displayed as Minimum Charge field is 'Mandatory' in \"Air Haulage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_AF_021_Step_No_2_"+strMessage + "validation message is empty*****";
      	}
    	 
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_021_Step_No_3_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_3_Validation Error Message is wrong when all the mandatory fields are blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_021_Step_No_4_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_021_Step_No_4_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_5_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_5_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_DesctinationPort", ""), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Destination\" value is not cleared in Air Haulage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Currency\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_PerPallet", strCostAirFreight), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Cost\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_MinimumCharge", strMinimumChargeAirFreight), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Minimum Charge\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_021_Step_No_6_\"Validation Error pop up\" is not displayed when \"Destination\" Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_6_Validation Error Message is wrong when \"Destination\"  mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_021_Step_No_7_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_021_Step_No_7_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_8_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_8_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_DesctinationPort", strDestinationAirfreight), true, "*****test_LIMA_TARR_AF_021_Step_No_9_\"Destination\" value is not set in Air Haulage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_Currency", ""), true,  "*****test_LIMA_TARR_AF_021_Step_No_9_\"Currency\" value is not cleared in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_PerPallet", strCostAirFreight), true, "*****test_LIMA_TARR_AF_021_Step_No_9_\"Cost\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_MinimumCharge", strMinimumChargeAirFreight), true, "*****test_LIMA_TARR_AF_021_Step_No_9_\"Minimum Charge\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_021_Step_No_9_\"Validation Error pop up\" is not displayed when \"Currency\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_9_Validation Error Message is wrong when \"Currency\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_021_Step_No_10_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_021_Step_No_10_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_11_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_11_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_DesctinationPort", strDestinationAirfreight), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Destination\" value is not set in Air Haulage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Currency\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_PerPallet", ""), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Cost\" value is not cleared in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_MinimumCharge", strMinimumChargeAirFreight), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Minimum Charge\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_021_Step_No_12_\"Validation Error pop up\" is not displayed when \"Cost\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_12_Validation Error Message is wrong when \"Cost\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_021_Step_No_13_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_021_Step_No_13_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_14_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_14_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_DesctinationPort", strDestinationAirfreight), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Destination\" value is not set in Air Haulage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Currency\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_PerPallet", strCostAirFreight), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Cost\" value is not set in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_HaulageCosts", nAddRow-1, "HaulageCosts_MinimumCharge", ""), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Minimum Charge\" value is not cleared in Air Haulage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_021_Step_No_15_\"Validation Error pop up\" is not displayed when \"MinimumCharge\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_15_Validation Error Message is wrong when \"MinimumCharge\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_021_Step_No_16_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_021_Step_No_16_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_17_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_021_Step_No_17_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirHaulageCostsDeleteRow()), true, "*****test_LIMA_TARR_AF_021_Step_No_18_\"Delete Row Button\" is not clicked in \"Air Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_AF_021_Step_No_18_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Air Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_021_Step_No_19_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Air Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
    	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_HaulageCosts");
    	objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_AF_021_Step_No_19_Row is not deleted in \"Air Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
    	
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 1145)
     public void test_LIMA_TARR_AF_022_AirHaulageCosts_AirStorageCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_1_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_HaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_1_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_Air Storage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_StorageCharges");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirStorageCostsAddRow()), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"Add Row button\" is not clicked in \"Air Storage Costs grid\" in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_StorageCharges");
         objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_AF_022_Step_No_2_Row is not added in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
         String strChargeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ChargeType");
     	 String strFromDayErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_FromDay");
     	 String strToDayErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ToDay");
     	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_Currency");
    	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_UnitOfMeasure");
         
    	 if(!strChargeTypeErrMsg.equals("")||!strFromDayErrMsg.equals("")||!strToDayErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals(""))
      		{
      		if(strChargeTypeErrMsg.contains("&#39;"))
      		{
      			strChargeTypeErrMsg = strChargeTypeErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strChargeTypeErrMsg.equalsIgnoreCase("> 'Charge Type' is a required field"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"'Charge Type' is the required field\" message is not displayed as Charge Type field is a mandatory  in \"Air Storage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		if(strFromDayErrMsg.contains("&#39;"))
      		{
      			strFromDayErrMsg = strFromDayErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strFromDayErrMsg.equalsIgnoreCase("> 'From Day' is a required field"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"'From Day' is the required field \" message is not displayed as From Day field is mandatory in \"Air Storage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      		if(strToDayErrMsg.contains("&#39;"))
      		{
      			strToDayErrMsg = strToDayErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strToDayErrMsg.equalsIgnoreCase("> 'To Day' is a required field"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"'To Day' is the required field\" message is not displayed as ToDay field is mandatory in \"Air Storage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      		if(strCurrencyErrMsg.contains("&#39;"))
      			
      		{
      			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Air Storage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      		if(strUnitOfMeasureErrMsg.contains("&#39;"))
      		{
      			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit of Measure' is a required field"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"'Unit of Measure' is the required field\" message is not displayed as Unit of Measure field is mandatory in \"Air Storage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      		}
      		
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_AF_022_Step_No_2_"+strMessage + "validation message is empty*****";
      	}
    	 
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_022_Step_No_3_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Air Storage Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_3_Validation Error Message is wrong when all the mandatory fields are blank in \"Air Storage Cost Grid\" in  \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_022_Step_No_4_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_022_Step_No_4_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_5_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_AF_022_Step_No_5_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ChargeType", ""), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"Charge Type\" value is not cleared in Air Storage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_FromDay", strFromDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"FromDay\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ToDay", strToDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"FromTo\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"Currency\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_UnitOfMeasure", strUnitOfMeasureAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"Unit Of Measure\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_022_Step_No_6_\"Validation Error pop up\" is not displayed when \"Charge Type\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_6_Validation Error Message is wrong when \"Charge Type\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_022_Step_No_7_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_022_Step_No_7_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_8_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_AF_022_Step_No_8_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ChargeType",strChargeTypeAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"Charge Type\" value is not set in Air Storage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_FromDay", ""), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"FromDay\" value is not cleared in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ToDay", strToDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"FromTo\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"Currency\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_UnitOfMeasure", strUnitOfMeasureAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"Unit Of Measure\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_022_Step_No_9_\"Validation Error pop up\" is not displayed when \"FromDay\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_9_Validation Error Message is wrong when \"FromDay\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_022_Step_No_10_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_022_Step_No_10_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_11_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_AF_022_Step_No_11_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ChargeType",strChargeTypeAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"Charge Type\" value is not set in Air Storage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_FromDay", strFromDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"FromDay\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ToDay", strToDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"FromTo\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_Currency", ""), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"Currency\" value is not cleared in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_UnitOfMeasure", strUnitOfMeasureAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"Unit Of Measure\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_022_Step_No_12_\"Validation Error pop up\" is not displayed when \"Currency\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_12_Validation Error Message is wrong when \"Currency\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_022_Step_No_13_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_022_Step_No_13_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_14_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_AF_022_Step_No_14_Air Haulage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ChargeType",strChargeTypeAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"Charge Type\" value is not set in Air Storage Costs Grid in  \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_FromDay", strFromDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"FromDay\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_ToDay", strToDayAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"FromTo\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_Currency", strCurrencyAirfreight), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"Currency\" value is not set in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_StorageCharges", nAddRow-1, "StorageCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"Unit Of Measure\" value is not cleared in Air Storage Costs Grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
         
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_022_Step_No_15_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_15_Validation Error Message is wrong when \"UnitOfMeasure\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_022_Step_No_16_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Air Haulage Costs\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_022_Step_No_16_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_022_Step_No_17_Air Haulage Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_StorageCharges"), true, "*****test_LIMA_TARR_AF_022_Step_No_17_Air Storage Cost Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirStorageCostsDeleteRow()), true, "*****test_LIMA_TARR_AF_022_Step_No_18_\"Delete Row Button\" is not clicked in \"Air Storage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_AF_022_Step_No_18_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Air Storage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_022_Step_No_19_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Air Storage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
    	
    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_StorageCharges");
    	objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_AF_021_Step_No_19_Row is not deleted in \"Air Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Air Haulage Cost Tab\"*****");
    	
     	objSoftAssert.assertAll();
     }
     
     @Test(priority = 1150)
     public void test_LIMA_TARR_AF_023_AmendmentLogMandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AmendmentLog"), true, "*****test_LIMA_TARR_AF_023_Step_No_1_Amendment Log tab is not clicked in \"Create new Airfreight Tariff\" page*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_AF_023_Step_No_1_Amendment Log grid is not not displayed in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\" tab*****");
         ArrayList<String> list = new ArrayList<String>();
         
         list = objAdjunoLIMATariffPOM.getCaptionsList(strAirFreightPaymentFormName,"Grid_AmendmentLog");
         
         strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlAirAmendmentLogGrid,6);
         objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_SF_016_Step_No_1_Air Amendment Log Costs Grid: "+strMessage+"*****");
         
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AmendmentLog");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirAmendmentLogAddRow()), true, "*****test_LIMA_TARR_AF_023_Step_No_2_\"Add Row button\" is not clicked in \"Air Amendment Log grid\" in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
         int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AmendmentLog");
         objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_AF_022_Step_No_2_Row is not added in Air Amendment log Grid in \"Create new Airfreight Tariff\" page under \"Amendment Log tab\"*****");
         
         String strChangeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_ChangeType");
     	 String strSectionErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_Section");
     	 String strDateErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_Date");
     	 String strChangeDescriptionErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_ChangeDescription");
    	 String strLoggedByErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_LoggedBy");
    	 String strCommentsErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_Comments");
         
    	 if(!strChangeTypeErrMsg.equals("")||!strSectionErrMsg.equals("")||!strDateErrMsg.equals("")||!strChangeDescriptionErrMsg.equals("")||!strLoggedByErrMsg.equals(""))
      		{
      		if(strChangeTypeErrMsg.contains("&#39;"))
      		{
      			strChangeTypeErrMsg = strChangeTypeErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strChangeTypeErrMsg.equalsIgnoreCase("> 'Change Type' is a required field"), true, "*****test_LIMA_TARR_AF_023_Step_No_2_\"'Charge Type' is the required field\" message is not displayed as Charge Type field is a mandatory  in \"Air Amendment Logs\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      		}
      		if(strSectionErrMsg.contains("&#39;"))
      		{
      			strSectionErrMsg = strSectionErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strSectionErrMsg.equalsIgnoreCase("> 'Section' is a required field"), true, "*****test_LIMA_TARR_AF_023_Step_No_2_\"'Section' is the required field \" message is not displayed as From Day field is mandatory in \"Air Amendment Logs\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      		}
      		
      		if(strDateErrMsg.contains("&#39;"))
      		{
      			strDateErrMsg = strDateErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strDateErrMsg.equalsIgnoreCase("> 'Date' is a required field"), true, "*****test_LIMA_TARR_AF_023_Step_No_2_\"'Date' is the required field\" message is not displayed as ToDay field is mandatory in \"Air Amendment Logs\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      		}
      		
      		if(strChangeDescriptionErrMsg.contains("&#39;"))
      			
      		{
      			strChangeDescriptionErrMsg = strChangeDescriptionErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strChangeDescriptionErrMsg.equalsIgnoreCase("> 'ChangeDescription' is a required field"), true, "*****test_LIMA_TARR_AF_023_Step_No_2_\"'ChangeDescription' is the required field\" message is not displayed as Currency field is mandatory in \"Air Amendment Logs\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      		}
      		      		
      			objSoftAssert.assertEquals(strLoggedByErrMsg.equalsIgnoreCase("> Cannot Save an empty row"), true, "*****test_LIMA_TARR_AF_022_Step_No_2_\"> Cannot Save an empty row\" message is not displayed as \"LoggedBy\" field is mandatory in \"Air Amendment Logs\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      		
      		if(strCommentsErrMsg.contains("&#39;"))
      		{
      			strCommentsErrMsg = strCommentsErrMsg.replace("&#39;", "'");
      			objSoftAssert.assertEquals(strCommentsErrMsg.equalsIgnoreCase("> 'Comments' is a required field"), true, "*****test_LIMA_TARR_AF_023_Step_No_2_\"'Comments' is the required field\" message is not displayed as Comment field is mandatory in \"Air Storage Costs\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      		}
      	}
      	else
      	{
      		strMessage = "*****test_LIMA_TARR_AF_023_Step_No_2_"+strMessage + "validation message is empty*****";
      	}
    	 
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_023_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_023_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_023_Step_No_3_\"Validation Error pop up\" is not displayed when all mandatory field are blank in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_023_Step_No_3_Validation Error Message is wrong when all mandatory field are blank in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_023_Step_No_4_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_023_Step_No_4_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
      	
      	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AmendmentLog"), true, "*****test_LIMA_TARR_AF_023_Step_No_20_Amendment log Costs tab is not clicked in \"Create new Airfreight Tariff\" page*****");
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_AF_023_Step_No_20_Amendment log Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
       	
       	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_ChangeType",strChangeTypeAirFreight), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"Change Type\" value is not set in Amendment Log Grid in  \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_Section", strSectionAirFreight), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"Section\" value is not set in Amendment Log Grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_Date", strValidFromDate), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"Date\" value is not set in Amendment Log Grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_ChangeDescription", strChangeDescriptionAirFreight), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"ChangeDescriptionAirFreight\" value is not set in Amendment Log Grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_LoggedBy", strLoggedByAirFreight), true, "*****test_LIMA_TARR_AF_022_Step_No_21_\"Logged By \" value is not set in Amendment Log Grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strAirFreightPaymentFormName, "Grid_AmendmentLog", nAddRow-1, "AmendmentLog_Comments", ""), true, "*****test_LIMA_TARR_AF_022_Step_No_21_\"Comments\" value is not cleared in Amendment Log Grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
    	 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
////      objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
////      objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_AF_023_Step_No_21_\"Validation Error pop up\" is not displayed when \"Comment\", Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
////     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Airfreight"), true, "*****test_LIMA_TARR_AF_023_Step_No_21_Validation Error Message is wrong when \"Comment\" , Mandatory field is blank in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
////     	
////     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_AF_023_Step_No_22_OK Button is not clicked in Validation error pop up in \"Create new Airfreight Tariff\" page under \"Amendment Log\" tab*****");
////     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AirRates"), true, "*****test_LIMA_TARR_AF_023_Step_No_22_\"Air Rates\" Grid  is not displayed under \"Air Freight Costs\" grid in \"Create new Airfreight Tariff\" page*****");
////     	
////     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strAirFreightPaymentFormName, "TabPanel_Tariff", "AirHaulageCosts"), true, "*****test_LIMA_TARR_AF_023_Step_No_23_Amendment Log tab is not clicked in \"Create new Airfreight Tariff\" page*****");
////      objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strAirFreightPaymentFormName, "Grid_AmendmentLog"), true, "*****test_LIMA_TARR_AF_023_Step_No_23_Amendment Log  Grid is not displayed in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
////      	
////      objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnAirAmendmentLogDeleteRow()), true, "*****test_LIMA_TARR_AF_023_Step_No_24_\"Delete Row Button\" is not clicked in \"Amendment Log\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
////    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_AF_023_Step_No_24_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Amendment Log\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
////     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_023_Step_No_25_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Amendment Log\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
////    	
////    	int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strAirFreightPaymentFormName, "Grid_AmendmentLog");
////    	objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_AF_023_Step_No_25_Row is not deleted in \"Amendment Log\" grid in \"Create new Airfreight Tariff\" page under \"Amendment Log Tab\"*****");
////    	
        objSoftAssert.assertAll();
     }
     
     @Test(priority = 1155)
     public void test_LIMA_TARR_RF_001_CheckforTariffPage()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         
         objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_RF_001_Tariff  untitled page is not displayed with \"Type ComboBox\"*****");
         
     }
     
     @Test(priority = 1160)
     public void test_LIMA_TARR_RF_002_ViewRoadFreightTariffHistoryResults()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_RF_001_Step_No_1_Type Combo Box Arrow button is not clicked in Tariff Search page*****");
     	
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeRoadFreightType,strSearchFormNameTariff,"ComboboxTypeTariff"), true, "*****test_LIMA_TARR_RF_002_Step_No_1_\"RoadFreight\" is not set in the \"Type ComboBox\" in Tariff Search page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_RF_002_Step_No_1_\"Roadfreight Tariff History Grid\" is not displayed in Tariff Search page below Type ComboBox*****");
     	
     	 ArrayList<String> list = new ArrayList<String>();
     	 list = objAdjunoLIMATariffPOM.getCaptionsList(strSearchFormNameTariff,"Grid_SeaFreight");
      
     	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlSeafreightTariffHistoryGrid,7);
     	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_002_Step_No_2_RoadFreightTariffHistoryGridCaption: "+strMessage+"*****");
      
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_RF_002_Step_No_4_\"Create New Tariff button\" is not displayed in Tariff Search page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "DownloadTemplate"), true, "*****test_LIMA_TARR_RF_002_Step_No_4_\"Download Seafreight Template link\" is not displayed in Tariff Search page*****");
     	 int nRows = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strSearchFormNameTariff, "Grid_SeaFreight");
      
     	 for(int i=0;i<nRows-1;i++)
     	 {
     		 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isGridElementPresent(strSearchFormNameTariff, "Grid_SeaFreight", i, "ViewTariff"), true, "*****test_LIMA_TARR_RF_002_Step_No_4_View Tariff Link is not displayed in Tariff Search page under \"Roadfreight Tariff History grid\"*****");
     		 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isGridElementPresent(strSearchFormNameTariff, "Grid_SeaFreight", i, "OpenReport"), true, "*****test_LIMA_TARR_RF_002_Step_No_4_Open Report link is not displayed in Tariff Search page under \"Roadfreight Tariff History grid\"*****");
     	 }
         
     }
     
     @Test(priority = 1165)
     public void test_LIMA_TARR_RF_003_CheckPagination()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         
         String strValue = "";
         strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
         String[] strVal = strValue.split("of ");
		 int nAllRows = Integer.parseInt(strVal[1]);
		
		if(nAllRows>10)
		{
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnNext()), true, "*****test_LIMA_TARR_RF_003_Step_No_1_\"Next page\" button is not clicked in \"RoadTariff History grid\" in Tariff Search page *****");
			wait(2000);
			strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
			strVal = strValue.split("rows ");
			String strVals[] = strVal[1].split("of ");
			objSoftAssert.assertEquals(strVals[0].contains("11 -"), true, "*****test_LIMA_TARR_RF_003_Step_No_1_Page 2 is not displayed when number of Rows is more than 10 in \"RoadFreight Tariff History grid\"*****");
	
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnPrevious()), true, "*****test_LIMA_TARR_RF_003_Step_No_2_\"Previous page\" button is not clicked in \"RoadTariff History grid\" in Tariff Search page *****");
			strValue = objAdjunoLIMATariffPOM.getTxtNoRows().getText();
			strVal = strValue.split("rows ");
			String strVals1[] = strVal[1].split("of ");
			objSoftAssert.assertEquals(strVals1[0].contains("1 -"), true, "*****test_LIMA_TARR_RF_003_Step_No_2_Previous page i.e page1 is not displayed when number of Rows is more than 10 in \"RoadFreight Tariff History grid\"*****");	
		}
		else{} 
		objSoftAssert.assertAll();
     }
     
     @Test(priority = 1170)
     public void test_LIMA_TARR_RF_004_CheckViewTariffLink()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
     	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
     	String strMessage = "";
     	wait(3000);
 
     	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_RF_004_Step_No_1_\"View Deatil\" is not a hyper link in RoadFreight Tariff History grid*****");
     	wait(3000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_RF_004_Step_No_1_\"View RoadFreight Tariff page\" is not loaded after View Detail link  is clicked in \"RoadFright Tariff History grid\"*****");
     	
     	objSoftAssert.assertAll();
     }
    
     @Test(priority = 1180)
     public void test_LIMA_TARR_RF_006_CheckDownloadRoadFreightTemplateLink()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
     	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
     	String strMessage = "";
     	
     	wait(3000);
     	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_RF_006_Step_No_1_Tariff untitled page is not displayed with \"Type ComboBox\" *****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_RF_006_Step_No_1_Type Combo Box Arrow button is not clicked in Tariff Search page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeRoadFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_RF_006_Step_No_2_\"Road Freight\" is not set in Type ComboBox in Tariff Search page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_RF_006_Step_No_2_Road Freight Tariff History grid is not displayed in Tariff Search page*****");
     	strMessage = objAdjunoLIMATariffPOM.isHyperlinkPresent(strSearchFormNameTariff,"DownloadTemplate");
     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_RF_006_Step_No_3_Download Roadfreight Template is not a hyper link in in Tariff Search page*****");
     	wait(2000);
     	
     	objAdjunoLIMATariffPOM.getElement(strSearchFormNameTariff, "DownloadTemplate").click();
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isFileDownloaded_Ext("C:\\Users\\Anand\\Downloads",".xlsx"), true, "*****test_LIMA_TARR_RF_006_Step_No_3_The XLSX version of the template is not downloaded in the user's local drive*****");
     	objSoftAssert.assertAll();
      		
     }
     
     @Test(priority = 1185)
     public void test_LIMA_TARR_RF_007_VerifyRoadFreightTariff_FullTrailerCostsTabs_FullTrailerCostsGrid()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
      	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
      	String strMessage = "";
      	
      	objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_RF_007_Step_No_1_Tariff untitled page is not displayed with \"Type ComboBox\" *****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_RF_007_Step_No_1_Type Combo Box Arrow button is not clicked in Tariff Search page*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeRoadFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_RF_007_Step_No_2_\"Road Freight\" is not set in Type ComboBox in Tariff Search page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_RF_007_Step_No_2_Road Freight Tariff History grid is not displayed in Tariff Search page*****");
      	
     	wait(3000);
     	strMessage = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
     	objSoftAssert.assertEquals(strMessage.equals(""), true, "*****test_LIMA_TARR_RF_007_Step_No_3_\"View Deatil\" is not a hyper link in RoadFreight Tariff History grid*****");
     	wait(3000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_RF_007_Step_No_3_\"View RoadFreight Tariff page\" is not loaded after View Detail link  is clicked in \"RoadFright Tariff History grid\"*****");
     	
     	ArrayList<String> list = new ArrayList<String>();
     	list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_FullTrailerCharges");
      
     	strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFullTrailerCostGrid,13);
     	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_007_Step_No_3_FullTrailerCostsGridCaption: "+strMessage+"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerCostsAddRow()), true, "*****test_LIMA_TARR_RF_007_Step_No_4_\"Add Row Button\" is not disabled in \"Full Trailer Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerCostsDeleteRow()),true, "*****test_LIMA_TARR_RF_007_Step_No_4_\"Delete Row Button\" is not disabled in \"Full Trailer Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
     	objSoftAssert.assertAll();
     }
     
     @Test(priority = 1190)
     public void test_LIMA_TARR_RF_008_VerifyRoadFreightTariff_FullTrailerCostsTab_FullTrailerGOHCostsGrid()
     {
    	SoftAssert objSoftAssert = new SoftAssert();
       	objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
       	String strMessage = "";
       	
       	ArrayList<String> list = new ArrayList<String>();
     	list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_FullTrailerGOH");
      
     	strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFullTailerGOHCostsGrid,11);
     	objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_008_Step_No_1_FullTrailerGOHCostsGridCaption: "+strMessage+"*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerGOHCostsAddRow()),true, "*****test_LIMA_TARR_RF_008_Step_No_2_\"Add Row Button\" is not disabled in \"Full Trailer GOH Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerGOHCostDeleteRow()),true, "*****test_LIMA_TARR_RF_008_Step_No_2_\"Delete Row Button\" is not disabled in \"Full Trailer GOH Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
     	
       	objSoftAssert.assertAll();
     }
     
     @Test(priority = 1195)
     public void test_LIMA_TARR_RF_009_VerifyRoadFreightTariff_FullTrailerCostsTabs_FullTrailerLandsideCostsGrid()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_LandsideCharges");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFullTrailerLandsideCostsGrid,10);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_009_Step_No_1_FullTrailerLandsideCostsGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerLandsideCostsAddRow()),true, "*****test_LIMA_TARR_RF_009_Step_No_2_\"Add Row Button\" is not disabled in \"Full Trailer Landside Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerLandsideCostsDeleteRow()),true, "*****test_LIMA_TARR_RF_009_Step_No_2_\"Delete Row Button\" is not disabled in \"Full Trailer Landside Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
      	 objSoftAssert.assertAll();
     }
     
     @Test(priority = 1200)
     public void test_LIMA_TARR_RF_010_VerifyRoadFreightTariff_FullTrailerCostsTab_FullTrailerDeliveryCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_DeliveryCharges");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlFullTrailerDeliveryCostGrid,9);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_010_Step_No_1_FullTrailerDeliveryCostsGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerDeliveryCostAddRow()),true, "*****test_LIMA_TARR_RF_010_Step_No_2_\"Add Row Button\" is not disabled in \"Full Trailer Delivery Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnFullTrailerDeliveryCostDeleteRow()),true, "*****test_LIMA_TARR_RF_010_Step_No_2_\"Delete Row Button\" is not disabled in \"Full Trailer Delivery Costs Grid\" in View Roadfreight Tariff page under Full Tailer Costs tab*****");
      	 objSoftAssert.assertAll();
     }
     
     @Test(priority = 1205)
     public void test_LIMA_TARR_RF_011_VerifyRoadFreightTariff_GroupageTrailerCostsTabs_GroupageTrailerCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_011_Step_No_1_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_GroupageTrailerCharges");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlGroupageTrailerCostGrid,13);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_011_Step_No_2_GroupageTrailerCostGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageTrailerCostsAddRow()),true, "*****test_LIMA_TARR_RF_011_Step_No_3_\"Add Row Button\" is not disabled in \"Groupage Trailer Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageTrailerCostDeleteRow()),true, "*****test_LIMA_TARR_RF_011_Step_No_3_\"Delete Row Button\" is not disabled in \"Groupage Trailer Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	 objSoftAssert.assertAll();   
     }
     
     @Test(priority = 1210)
     public void test_LIMA_TARR_RF_012_VerifyRoadFreightTariff_GroupageTrailerCostsTabs_GroupageGOHCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_GroupageGOH");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlGoupageGOHCostGrid,10);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_011_Step_No_1_GroupageGOHCostGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageGOHCostsAddRow()),true, "*****test_LIMA_TARR_RF_012_Step_No_2_\"Add Row Button\" is not disabled in \"Groupage GOH Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageGOHCostsDeleteRow()),true, "*****test_LIMA_TARR_RF_012_Step_No_2_\"Delete Row Button\" is not disabled in \"Groupage GOH Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	
         objSoftAssert.assertAll(); 
     }
     
     @Test(priority = 1215)
     public void test_LIMA_TARR_RF_013_VerifyRoadFreightTariff_GroupageTrailerCostsTab_GroupageLandsideCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_GroupageLandsideCharges");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlGroupageLandsideCostGrid,12);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_013_Step_No_1_GroupageLandsideCostGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageLandsideAddRow()),true, "*****test_LIMA_TARR_RF_013_Step_No_2_\"Add Row Button\" is not disabled in \"Groupage Landside Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageLandsideDeleteRow()),true, "*****test_LIMA_TARR_RF_013_Step_No_2_\"Delete Row Button\" is not disabled in \"Groupage Landside Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	
         objSoftAssert.assertAll();
     }
     
     @Test(priority = 1220)
     public void test_LIMA_TARR_RF_014_VerifyRoadFreightTariff_GroupageTrailerCostsTab_GroupageHaulageCosts()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_GroupageHaulageCharges");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlGroupageHaulageCostGrid,9);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_014_Step_No_1_GroupageHaulageCostGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageHaulageCostAddRow()),true, "*****test_LIMA_TARR_RF_014_Step_No_2_\"Add Row Button\" is not disabled in \"Groupage Haulage Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnGroupageHaulageCostDeleteRow()),true, "*****test_LIMA_TARR_RF_014_Step_No_2_\"Delete Row Button\" is not disabled in \"Groupage Haulage Costs Grid\" in View Roadfreight Tariff page under Groupage Trailer Costs tab*****");
      	
         objSoftAssert.assertAll(); 
     }
     
     @Test(priority = 1225)
     public void test_LIMA_TARR_RF_015_RoadFreight_CreateNewTariff_Update_An_Existing_Tariff_AddNewRowOnFullTrailerCostGrid()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         String strMessage1 = "";
         
         objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_RF_015_Step_No_1_Tariff untitled page is not displayed with \"Type ComboBox\"*****");
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_RF_015_Step_No_2_Type Combo Box Arrow button is not clicked in Tariff Search page*****");
      	
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeRoadFreightType,strSearchFormNameTariff,"ComboboxTypeTariff"), true, "*****test_LIMA_TARR_RF_015_Step_No_2_\"RoadFreight\" is not set in the \"Type ComboBox\" in Tariff Search page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_RF_015_Step_No_2_\"Roadfreight Tariff History Grid\" is not displayed in Tariff Search page below Type ComboBox*****");
     	
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButton(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_RF_015_Step_No_3_\"Create New Tariff button\" Is not clicked in Tariff Search page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strRoadFreightTariffFormName), true, "*****test_LIMA_TARR_RF_015_Step_No_3_\"Create Road Freight Tariff\" page is not displayed after Create new Tariff button is clicked in tariff search page*****");
     	 
     	 wait(2000);
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "PanelHeader"), true, "*****test_LIMA_TARR_RF_015_Step_No_4_\"Tariff Version Grid\" is not displayed in Create Road Freight Tariff page*****");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyElementIsInReadOnlyMode(strRoadFreightTariffFormName, "TariffVersion"), true, "*****test_LIMA_TARR_RF_015_Step_No_4_\"Tariff Version Field\" is not in Read Only mode in Tariff Version Grid in Create Road Freight Tariff page*****"); 
     	 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.setPresentDate("dd MMM YYYY"), strRoadFreightTariffFormName, "ValidFrom"), true);
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(objAdjunoLIMATariffPOM.setFutureDate("dd MMM YYYY"), strRoadFreightTariffFormName, "ValidTo"), true);
        
     	 String strTariffVersion = objAdjunoLIMATariffPOM.getFieldValue(strRoadFreightTariffFormName, "TariffVersion");
     	 int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerCharges");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerCostsAddRow()), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"Add Row Button\" is not clicked in \"Full Trailer Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_015_Step_No_5_New Row is not added in \"Full Trailer Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 
     	 String strOriginErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin");
     	 String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry");
     	 String strCarrierErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier");
     	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency");
     	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost");
     	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure");
     	 String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom");
     	 String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo");
     	 
     	if(!strOriginErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCarrierErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
  		{
  		if(strOriginErrMsg.contains("&#39;"))
  		{
  			strOriginErrMsg = strOriginErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strOriginErrMsg.equalsIgnoreCase("> 'Origin' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Origin' is the required field\" message is not displayed as Origin field is a mandatory  in \"Full Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Tailer\" tab*****");
  		}
  		if(strDestinationCountryErrMsg.contains("&#39;"))
  		{
  			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_2_\"'Destination Country' is the required field \" message is not displayed as DestinationCountry field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  		}
  		
  		if(strCarrierErrMsg.contains("&#39;"))
  		{
  			strCarrierErrMsg = strCarrierErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strCarrierErrMsg.equalsIgnoreCase("> 'Carrier' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Carrier' is the required field\" message is not displayed as Carrier field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  		}
  		
  		if(strCurrencyErrMsg.contains("&#39;"))
  			
  		{
  			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  		}
  		      		
  		if(strCostErrMsg.contains("&#39;"))
  		{
  			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  		}
  		
  		if(strUnitOfMeasureErrMsg.contains("&#39;"))
  		{
  			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Unit Of Measure' is the required field\" message is not displayed as UnitOfMeasure field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  		}
  		
  		if(strValidFromErrMsg.contains("&#39;"))
  		{
  			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
  			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Valid From' is the required field\" message is not displayed as ValidFrom field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  		}
  		
  		if(strValidToErrMsg.contains("&quot;"))
     	{
			strValidToErrMsg = strValidToErrMsg.replace("&quot;", "\"");
			objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> The \"Rate To\" date must be later than the \"Rate From\" date."), true, "*****test_LIMA_TARR_RF_015_Step_No_5_\"'Valid To' is the required field\" message is not displayed as ValidTo field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	}
  	}
  	else
  	{
  		strMessage = "*****test_LIMA_TARR_RF_015_Step_No_5_"+strMessage + "validation message is empty*****";
  	}
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"Apply Button\" is not clicked in \"Create New RoadFreight Tariff\" page*****");
     	wait(2000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_RF_015_Step_No_6_Tariff Search page is not displayed after clicking apply button in \"Create New Roadfreight Tariff \" page*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"RoadFreight Tariff History Grid\" is not not displayed in Tariff Search page*****");
     	objSoftAssert.assertEquals(strTariffVersion.equals(objAdjunoLIMATariffPOM.getGridCellValue(strSearchFormNameTariff, "Grid_SeaFreight", 0, "TariffVersion")), true, "*****test_LIMA_TARR_RF_015_Step_No_6_RoadFreight Tariff Version created in \"Create New RoadFreight Tariff page\" is not  displayed in \"RoadFreight Tariff History Grid\" in Tariff Search page*****");
     	
     	strMessage1 = objAdjunoLIMATariffPOM.gridHyperlinkPresent(strSearchFormNameTariff, "Grid_SeaFreight", 0, "ViewTariff");
    	objSoftAssert.assertEquals(strMessage1.equals(""), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"View Deatil\" is not a hyper link in \"RoadFreight Tariff History Grid\" *****");
    	wait(3000);
    	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSeaFreightPaymentFormName), true, "*****test_LIMA_TARR_SF_015_Step_No_7_View RoadFreight Tariff page is not loaded*****");
    	objSoftAssert.assertEquals(strOriginRoadFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"Origin Port Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\"*****");
    	objSoftAssert.assertEquals(strDestinationCountryRoadFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"Destination Country Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
    	objSoftAssert.assertEquals(strCarrierRoadFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"Carrier Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
    	objSoftAssert.assertEquals(strCurrencyRoadFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"Currency Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
    	objSoftAssert.assertEquals(strCostRoadFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"Cost Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
    	objSoftAssert.assertEquals(strUnitOfMeasureRaodFreight.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"UnitOfMeasure Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
    	objSoftAssert.assertEquals(strValidFromDate.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"ValidFrom Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
    	objSoftAssert.assertEquals(strValidToDate.equals(objAdjunoLIMATariffPOM.getGridCellValue(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo")), true, "*****test_LIMA_TARR_RF_015_Step_No_7_\"ValidTo Value\" in Full Tariler Cost grid under \"View RoadFreight Tariff page\" do not match with value set in \"Create New RoadFreight Tariff page\" *****");
     	objSoftAssert.assertAll();
     }
     
     @Test(priority = 1230)
     public void test_LIMA_TARR_RF_016_VerifyRoadFreightTariffTabs_AmendmentLog()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "AmendmentLog"), true, "*****test_LIMA_TARR_RF_016_Step_No_1_\"Amendment Log \" tab is not clicked in Create New RoadTariff page*****");
         ArrayList<String> list = new ArrayList<String>();
      	 list = objAdjunoLIMATariffPOM.getCaptionsList(strRoadFreightTariffFormName,"Grid_AmendmentLog");
       
      	 strMessage = objAdjunoLIMATariffPOM.verifyCaptionsONGrid(list,nlRoadAmenendmentLogGrid,6);
      	 objSoftAssert.assertEquals(strMessage.equals(""), true,"*****test_LIMA_TARR_RF_016_Step_No_1_RoadAmendmentLogGridCaption: "+strMessage+"*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnRoadAmendmentLogAddRow()),true, "*****test_LIMA_TARR_RF_016_Step_No_2_\"Add Row Button\" is not disabled in \"Amendment Log Grid\" in View Roadfreight Tariff page under Amendment Log tab*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkElementIsDisabled(objAdjunoLIMATariffPOM.getBtnRoadAmendmentLogDeleteRow()),true, "*****test_LIMA_TARR_RF_016_Step_No_2_\"Delete Row Button\" is not disabled in \"Amendment Log Grid\" in View Roadfreight Tariff page under Amendment Log tab*****");
      	
      	 objSoftAssert.assertAll();
         
     }
     
     @Test(priority = 1235)
     public void test_LIMA_TARR_RF_017_FullTrailerCostsTab_FullTrailerCostsGridMandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         objAdjunoLIMATariffPOM.callMouseHover(strSearchFormNameTariff, objAdjunoLIMATariffPOM.getLnkTools(), objAdjunoLIMATariffPOM.getLnkTariff());
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strSearchFormNameTariff), true, "*****test_LIMA_TARR_RF_017_Step_No_1_Tariff Search page is not dispalyed with Type Combobox*****");
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnComboBoxType()),true, "*****test_LIMA_TARR_RF_017_Step_No_3_Type Combo Box Arrow button is not clicked in Tariff Search page*****");
      	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValue(strTypeRoadFreightType, strSearchFormNameTariff, "ComboboxTypeTariff"), true, "*****test_LIMA_TARR_RF_017_Step_No_3_\"Road Freight\" is not set in Type ComboBox in Tariff Search page*****");
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strSearchFormNameTariff, "Grid_SeaFreight"), true, "*****test_LIMA_TARR_RF_017_Step_No_3_Road Freight Tariff History grid is not displayed in Tariff Search page*****");
       	
      	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButton(strSearchFormNameTariff, "CreateNewTariff"), true, "*****test_LIMA_TARR_RF_017_Step_No_4_\"Create New Tariff button\" Is not clicked in Tariff Search page*****");
    	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.verifyPageIsLoaded(strRoadFreightTariffFormName), true, "*****test_LIMA_TARR_RF_017_Step_No_4_\"Create Road Freight Tariff\" page is not displayed after Create new Tariff button is clicked in tariff search page*****");
    	 
    	 int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerCharges");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerCostsAddRow()), true, "*****test_LIMA_TARR_RF_017_Step_No_6_\"Add Row Button\" is not clicked in \"Full Trailer Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_017_Step_No_6_New Row is not added in \"Full Trailer Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 
     	 String strOriginErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin");
    	 String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry");
    	 String strCarrierErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier");
    	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency");
    	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost");
    	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure");
    	 String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom");
    	 String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo");
    	 
    	if(!strOriginErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCarrierErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
 		{
	 		if(strOriginErrMsg.contains("&#39;"))
	 		{
	 			strOriginErrMsg = strOriginErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strOriginErrMsg.equalsIgnoreCase("> 'Origin' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Origin' is the required field\" message is not displayed as Origin field is a mandatory  in \"Full Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Tailer\" tab*****");
	 		}
	 		if(strDestinationCountryErrMsg.contains("&#39;"))
	 		{
	 			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Destination Country' is the required field \" message is not displayed as DestinationCountry field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCarrierErrMsg.contains("&#39;"))
	 		{
	 			strCarrierErrMsg = strCarrierErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCarrierErrMsg.equalsIgnoreCase("> 'Carrier' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Carrier' is the required field\" message is not displayed as Carrier field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCurrencyErrMsg.contains("&#39;"))
	 			
	 		{
	 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		      		
	 		if(strCostErrMsg.contains("&#39;"))
	 		{
	 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strUnitOfMeasureErrMsg.contains("&#39;"))
	 		{
	 			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Unit Of Measure' is the required field\" message is not displayed as UnitOfMeasure field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strValidFromErrMsg.contains("&#39;"))
	 		{
	 			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Valid From' is the required field\" message is not displayed as ValidFrom field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strValidToErrMsg.contains("&quot;"))
	    	{
				strValidToErrMsg = strValidToErrMsg.replace("&quot;", "\"");
				objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> The \"Rate To\" date must be later than the \"Rate From\" date."), true, "*****test_LIMA_TARR_RF_015_Step_No_6_\"'Valid To' is the required field\" message is not displayed as ValidTo field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	    	}
 		}
	 	else
	 	{
	 		strMessage = "*****test_LIMA_TARR_RF_015_Step_No_6_"+strMessage + "validation message is empty*****";
	 	}
         
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_7_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_7_Validation Error Message is wrong when all the mandatory fields are blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_8_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Costs\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_8_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
  	 
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", ""), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"Origin field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_015_Step_No_9_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_9_\"Validation Error pop up\" is not displayed when \"Origin Port\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_9_Validation Error Message is wrong when \"Origin Port\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_10_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_10_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
  	 
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", ""), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"Destination Country field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_015_Step_No_11_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	wait(2000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_11_\"Validation Error pop up\" is not displayed when \"Destination Country\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_11_Validation Error Message is wrong when \"Destination Country\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_12_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_12_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
  	 
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", ""), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"Carrier field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_015_Step_No_13_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_13_\"Validation Error pop up\" is not displayed when \"Carrier\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_13_Validation Error Message is wrong when \"Carrier\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_14_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_14_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
  	 
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", ""), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"Currency field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_015_Step_No_15_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_15_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_15_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_14_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_14_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", ""), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Cost field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_17_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_17_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_14_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_14_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"UnitOfMeasure field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(2000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_19_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_19_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_20_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_20_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", ""), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"ValidFrom field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"ValidTo field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_21_\"Validation Error pop up\" is not displayed when \"ValidFrom\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_21_Validation Error Message is wrong when \"ValidFrom\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_22_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_22_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Origin field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Destination Country field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Carrier field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Currency field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Cost field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"UnitOfMeasure field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"ValidFrom field\" is not set in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "TrailerRates_ValidTo", ""), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"ValidTo field\" is not cleared in \"Full Tailer Cost Grid\" in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
     	
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
     	wait(1000);
     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_017_Step_No_23_\"Validation Error pop up\" is not displayed when \"ValidTo\" mandatory field is blank  in \"Full Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_017_Step_No_23_Validation Error Message is wrong when \"ValidTo\" mandatory field is blank in \"Full Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
  	    wait(1000);
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_24_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_24_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	
	  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerCostsDeleteRow()), true, "*****test_LIMA_TARR_RF_017_Step_No_25_\"Delete Row\" button is not clicked in \"Full Trailer Cost\"grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_017_Step_No_25_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Full Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\"*****");
	 	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_017_Step_No_26_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Full Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\"*****");
		
		int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerCharges");
		objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_017_Step_No_26_Row is not deleted in \"Full Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost Tab\"*****");
		
        objSoftAssert.assertAll(); 
     }
     
     @Test(priority = 1235)
     public void test_LIMA_TARR_RF_018_FullTrailerCostsTab_FullTrailerGOHCostGrid_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerGOH");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerGOHCostsAddRow()), true, "*****test_LIMA_TARR_RF_018_Step_No_1_\"Add Row Button\" is not clicked in \"Full Trailer Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerGOH");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_018_Step_No_1_New Row is not added in \"Full Trailer Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 
     	 String strOriginErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Origin");
    	 String strCarrierErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Carrier");
    	 String strHangingTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_HangingType");
    	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Currency");
    	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Cost");
    	 
    	if(!strOriginErrMsg.equals("")||!strCarrierErrMsg.equals("")||!strHangingTypeErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals(""))
 		{
	 		if(strOriginErrMsg.contains("&#39;"))
	 		{
	 			strOriginErrMsg = strOriginErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strOriginErrMsg.equalsIgnoreCase("> 'Origin' is a required field"), true, "*****test_LIMA_TARR_RF_018_Step_No_1_\"'Origin' is the required field\" message is not displayed as Origin field is a mandatory  in \"Full Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Tailer\" tab*****");
	 		}
	 		
	 		if(strCarrierErrMsg.contains("&#39;"))
	 		{
	 			strCarrierErrMsg = strCarrierErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCarrierErrMsg.equalsIgnoreCase("> 'Carrier' is a required field"), true, "*****test_LIMA_TARR_RF_018_Step_No_1_\"'Carrier' is the required field\" message is not displayed as Carrier field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strHangingTypeErrMsg.contains("&#39;"))
	 		{
	 			strHangingTypeErrMsg = strHangingTypeErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strHangingTypeErrMsg.equalsIgnoreCase("> 'Hanging Type ' is a required field"), true, "*****test_LIMA_TARR_RF_018_Step_No_1_\"'Hanging Type' is the required field\" message is not displayed as Hanging Type field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCurrencyErrMsg.contains("&#39;"))
	 			
	 		{
	 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_018_Step_No_1_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		      		
	 		if(strCostErrMsg.contains("&#39;"))
	 		{
	 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_018_Step_No_1_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Full Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
 		}
	 		
	 		else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_018_Step_No_1_"+strMessage + "validation message is empty*****";
		 	}
	         
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_2_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_2_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_018_Step_No_2_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Full GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_2_Validation Error Message is wrong when all the mandatory fields are blank in \"Full GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_3_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerGOH"), true, "*****test_LIMA_TARR_RF_017_Step_No_3_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	 
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Origin", ""), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Origin field\" is not cleared in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Currency field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Cost field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_018_Step_No_4_\"Validation Error pop up\" is not displayed when \"Origin Port\" mandatory field is blank  in \"Full GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_4_Validation Error Message is wrong when \"Origin Port\" mandatory field is blank in \"Full GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_018_Step_No_5_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_018_Step_No_5_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Origin field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Carrier", ""), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Carrier field\" is not cleared in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Currency field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Cost field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_018_Step_No_6_\"Validation Error pop up\" is not displayed when \"Carrier\" mandatory field is blank  in \"Full GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_6_Validation Error Message is wrong when \"Carrier\" mandatory field is blank in \"Full GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_018_Step_No_7_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_018_Step_No_7_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Origin field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_HangingType", ""), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Carrier field\" is not cleared in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Currency field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Cost field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_018_Step_No_8_\"Validation Error pop up\" is not displayed when \"HangingType\" mandatory field is blank  in \"Full GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_8_Validation Error Message is wrong when \"HangingType\" mandatory field is blank in \"Full GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_018_Step_No_9_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_018_Step_No_9_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Origin field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Currency", ""), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Currency field\" is not cleared in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Cost field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_018_Step_No_10_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Full GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_10_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Full GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_018_Step_No_11_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_018_Step_No_11_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Origin field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Carrier", strCarrierRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Carrier field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Currency field\" is not set in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_FullTrailerGOH", nAddRow-1, "TrailerGOH_Cost", ""), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Cost field\" is not cleared in \"Full GOH Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1200);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_018_Step_No_12_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Full GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_018_Step_No_12_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Full GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_018_Step_No_13_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_018_Step_No_13_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerGOHCostDeleteRow()), true, "*****test_LIMA_TARR_RF_018_Step_No_14_\"Delete Row\" button is not clicked in \"Full GOH Cost\"grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_018_Step_No_14_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Full GOH Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		 	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_018_Step_No_15_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Full GOH Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\"tab*****");
			
			int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_FullTrailerGOH");
			objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_018_Step_No_15_Row is not deleted in \"Full GOH Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost Tab\"*****");
			
		  	objSoftAssert.assertAll();
 		}
     
     @Test(priority = 1240)
     public void test_LIMA_TARR_RF_019_FullTrailerCostsTab_FullLandsideCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_LandsideCharges");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerLandsideCostsAddRow()), true, "*****test_LIMA_TARR_RF_019_Step_No_1_\"Add Row Button\" is not clicked in \"Full Trailer Landside Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_LandsideCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_019_Step_No_1_New Row is not added in \"Full Trailer Landside Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 
     	 String strChargeTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_ChargeType");
    	 String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_DestinationCountry");
    	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Currency");
    	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Cost");
    	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_UnitOfMeasure");
    	 
    	if(!strChargeTypeErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals(""))
 		{
	 		if(strChargeTypeErrMsg.contains("&#39;"))
	 		{
	 			strChargeTypeErrMsg = strChargeTypeErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strChargeTypeErrMsg.equalsIgnoreCase("> 'Charge Type' is a required field"), true, "*****test_LIMA_TARR_RF_019_Step_No_1_\"'Charge' is the required field\" message is not displayed as Charge field is a mandatory  in \"Full Trailer Landside Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Tailer\" tab*****");
	 			
	 		}
	 		
	 		if(strDestinationCountryErrMsg.contains("&#39;"))
	 		{
	 			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_RF_019_Step_No_1_\"'Destination Country' is the required field\" message is not displayed as DestinationCountry field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCurrencyErrMsg.contains("&#39;"))
	 		{
	 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_019_Step_No_1_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCostErrMsg.contains("&#39;"))
	 			
	 		{
	 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_019_Step_No_1_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		      		
	 		if(strUnitOfMeasureErrMsg.contains("&#39;"))
	 		{
	 			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_019_Step_No_1_\"'Unit Of Measure' is the required field\" message is not displayed as Unit Of Measure field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
 		}
	 		
	 		else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_019_Step_No_1_"+strMessage + "validation message is empty*****";
		 	}
	         
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_2_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_2_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_019_Step_No_2_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_019_Step_No_2_Validation Error Message is wrong when all the mandatory fields are blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_3_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_LandsideCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_3_\"Full Trailer Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	  	 
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_ChargeType", ""), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"ChargeType field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"DestinationCountry field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_019_Step_No_4_\"Validation Error pop up\" is not displayed when \"ChargeType\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_019_Step_No_4_Validation Error Message is wrong when \"ChargeType\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_019_Step_No_5_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_019_Step_No_5_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_ChargeType", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"Charge field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_DestinationCountry", ""), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"DestinationCountry field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_019_Step_No_6_\"Validation Error pop up\" is not displayed when \"DestinationCountry\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_019_Step_No_6_Validation Error Message is wrong when \"DestinationCountry\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_019_Step_No_7_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_019_Step_No_7_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_ChargeType", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"Charge field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"DestinationCountry field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Currency", ""), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"Currency field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_019_Step_No_8_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_019_Step_No_8_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_019_Step_No_9_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_019_Step_No_9_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_ChargeType", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"Charge field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"DestinationCountry field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Cost", ""), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"Cost field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_019_Step_No_10_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_019_Step_No_10_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_019_Step_No_11_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_019_Step_No_11_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_ChargeType", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"Charge field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"DestinationCountry field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_LandsideCharges", nAddRow-1, "TrailerLandsideCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"UnitOfMeasure field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1200);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_019_Step_No_12_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_019_Step_No_12_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_019_Step_No_13_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_019_Step_No_13_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerLandsideCostsDeleteRow()), true, "*****test_LIMA_TARR_RF_019_Step_No_14_\"Delete Row\" button is not clicked in \"Full Trailer Landside Cost Grid\"grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_019_Step_No_14_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Full Trailer Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		 	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_AF_019_Step_No_15_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Full Trailer Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\"tab*****");
			
			int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_LandsideCharges");
			objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_019_Step_No_15_Row is not deleted in \"Full Trailer Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost Tab\"*****");
			
		  	objSoftAssert.assertAll();

     }
     
     @Test(priority = 1245)
     public void test_LIMA_TARR_RF_020_FullTrailerCostTab_FullTrailerDeliveryCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_DeliveryCharges");
         wait(8000);
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerDeliveryCostAddRow()), true, "*****test_LIMA_TARR_RF_020_Step_No_1_\"Add Row Button\" is not clicked in \"Full Trailer Landside Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_DeliveryCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_020_Step_No_1_New Row is not added in \"Full Trailer Landside Cost grid\" in Create New RaodFreight tariff page under Full Tailer Cost Tab*****");
     	 
     	 String strDestinationErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DestinationCountry");
    	 String strDeliveryTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DeliveryType");
    	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Currency");
    	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Cost");
    	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_UnitOfMeasure");
    	 
    	if(!strDestinationErrMsg.equals("")||!strDeliveryTypeErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals(""))
 		{
	 		if(strDestinationErrMsg.contains("&#39;"))
	 		{
	 			strDestinationErrMsg = strDestinationErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strDestinationErrMsg.equalsIgnoreCase("> 'Destination' is a required field"), true, "*****test_LIMA_TARR_RF_020_Step_No_1_\"'Destination' is the required field\" message is not displayed as Destination field is a mandatory  in \"Full Trailer Landside Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Full Tailer\" tab*****");
	 		}
	 		
	 		if(strDeliveryTypeErrMsg.contains("&#39;"))
	 		{
	 			strDeliveryTypeErrMsg = strDeliveryTypeErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strDeliveryTypeErrMsg.equalsIgnoreCase("> 'Delivery Type' is a required field"), true, "*****test_LIMA_TARR_RF_020_Step_No_1_\"'Delivery Type' is the required field\" message is not displayed as Delivery Type field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCurrencyErrMsg.contains("&#39;"))
	 		{
	 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_020_Step_No_1_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		
	 		if(strCostErrMsg.contains("&#39;"))
	 			
	 		{
	 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_020_Step_No_1_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
	 		      		
	 		if(strUnitOfMeasureErrMsg.contains("&#39;"))
	 		{
	 			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
	 			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_020_Step_No_1_\"'Unit Of Measure' is the required field\" message is not displayed as Unit Of Measure field is mandatory in \"Full Trailer Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	 		}
 		}
	 		
	 		else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_020_Step_No_1_"+strMessage + "validation message is empty*****";
		 	}
	         
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_2_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_2_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_020_Step_No_2_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_2_Validation Error Message is wrong when all the mandatory fields are blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_017_Step_No_3_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_DeliveryCharges"), true, "*****test_LIMA_TARR_RF_017_Step_No_3_\"Full Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	wait(2000);
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DestinationCountry", ""), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"Destination field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"DeliveryType field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_020_Step_No_4_\"Validation Error pop up\" is not displayed when \"Destination\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_4_Validation Error Message is wrong when \"Destination\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_020_Step_No_5_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_020_Step_No_5_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	wait(2000);
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"Destination field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DeliveryType", ""), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"DeliveryType field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_020_Step_No_6_\"Validation Error pop up\" is not displayed when \"DeliveryType\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_6_Validation Error Message is wrong when \"DeliveryType\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_020_Step_No_7_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_020_Step_No_7_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	wait(2000);
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"Destination field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"DeliveryType field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Currency", ""), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"Currency field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_020_Step_No_8_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_8_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_020_Step_No_9_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_020_Step_No_9_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	wait(2000);
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"Destination field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"DeliveryType field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Cost", ""), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"Cost field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"UnitOfMeasure field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1000);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_020_Step_No_10_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_10_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_020_Step_No_11_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_020_Step_No_11_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	wait(5000);
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"Destination field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****"); 
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"DeliveryType field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"Currency field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"Cost field\" is not set in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_DeliveryCharges", nAddRow-1, "DeliveryCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"UnitOfMeasure field\" is not cleared in \"Full Trailer Landside Cost Grid\" in \"Create new RoadFreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	     	
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	     	wait(1200);
	     	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"Apply button\" is not clicked in \"Create New RoadFreight Tariff page\"*****");
	        objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_020_Step_No_12_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Full Trailer Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	    objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_020_Step_No_12_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Full Trailer Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
	  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_020_Step_No_13_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_FullTrailerCharges"), true, "*****test_LIMA_TARR_RF_020_Step_No_13_\"Full Trailer Landside Cost Grid\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
		  	
		  	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnFullTrailerDeliveryCostDeleteRow()), true, "*****test_LIMA_TARR_RF_020_Step_No_14_\"Delete Row\" button is not clicked in \"Full Trailer Landside Cost Grid\"grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
			objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_020_Step_No_14_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Full Trailer Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\" tab*****");
		 	objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_020_Step_No_15_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Full Trailer Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost\"tab*****");
			
			int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_DeliveryCharges");
			objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_020_Step_No_15_Row is not deleted in \"Full Trailer Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Full Trailer Cost Tab\"*****");
			
		  	objSoftAssert.assertAll();
         
     }
     
     @Test(priority = 1250)
     public void test_LIMA_TARR_RF_021_GroupageTrailerCostsTab_GroupageTrailerCostsGrid_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
          
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_1_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageTrailerCostsAddRow()), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"Add Row Button\" is not clicked in \"Groupage Trailer Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_021_Step_No_2_New Row is not added in \"Groupage Trailer Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 
     	 String strOriginErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin");
	   	 String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry");
	   	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency");
	   	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_FullTrailerCharges", nAddRow-1, "GroupageTrailer_Cost");
	   	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure");
	   	 String strValidFromErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom");
	   	 String strValidToErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo");
	   	 
	   	 if(!strOriginErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals("")||!strValidFromErrMsg.equals("")||!strValidToErrMsg.equals(""))
			{
		 		if(strOriginErrMsg.contains("&#39;"))
		 		{
		 			strOriginErrMsg = strOriginErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strOriginErrMsg.equalsIgnoreCase("> 'Origin' is a required field"), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Origin' is the required field\" message is not displayed as Origin field is a mandatory  in \"Groupage Trailer Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Tailer\" tab*****");
		 		}
		 		if(strDestinationCountryErrMsg.contains("&#39;"))
		 		{
		 			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Destination Country' is the required field \" message is not displayed as DestinationCountry field is mandatory in \"Groupage Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strCurrencyErrMsg.contains("&#39;"))
		 			
		 		{
		 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Groupage Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		      		
		 		if(strCostErrMsg.contains("&#39;"))
		 		{
		 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Groupage Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strUnitOfMeasureErrMsg.contains("&#39;"))
		 		{
		 			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Unit Of Measure' is the required field\" message is not displayed as UnitOfMeasure field is mandatory in \"Groupage Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strValidFromErrMsg.contains("&#39;"))
		 		{
		 			strValidFromErrMsg = strValidFromErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strValidFromErrMsg.equalsIgnoreCase("> 'Valid From' is a required field"), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Valid From' is the required field\" message is not displayed as ValidFrom field is mandatory in \"Groupage Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strValidToErrMsg.contains("&quot;"))
		    	{
					strValidToErrMsg = strValidToErrMsg.replace("&quot;", "\"");
					objSoftAssert.assertEquals(strValidToErrMsg.equalsIgnoreCase("> The \"Rate To\" date must be later than the \"Rate From\" date."), true, "*****test_LIMA_TARR_RF_021_Step_No_2_\"'Valid To' is the required field\" message is not displayed as ValidTo field is mandatory in \"Groupage Trailer Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		    	}
			}
		 	else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_021_Step_No_3_"+strMessage + "validation message is empty*****";
		 	}
	        
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_3_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Groupage Trailer Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_021_Step_No_3_Validation Error Message is wrong when all the mandatory fields are blank in \"Groupage Trailer Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_4_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_4_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_4_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Origin field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Destination Country field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Currency field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Cost field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"UnitOfMeasure field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"ValidFrom field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"ValidTo field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_5_\"Validation Error pop up\" is not displayed when \"Origin\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_5_Validation Error Message is wrong when \"Origin\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_6_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_6_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_6_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Origin field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Destination Country field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Currency field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Cost field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"UnitOfMeasure field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"ValidFrom field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"ValidTo field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_7_\"Validation Error pop up\" is not displayed when \"DestinationCountry\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_7_Validation Error Message is wrong when \"DestinationCountry\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_8_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_8_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_8_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Origin field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Destination Country field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Currency field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Cost field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"UnitOfMeasure field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"ValidFrom field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"ValidTo field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_9_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_9_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_10_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_10_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_10_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Origin field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Destination Country field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Currency field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Cost field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"UnitOfMeasure field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"ValidFrom field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"ValidTo field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_11_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_11_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_12_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_12_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_12_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Origin field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Destination Country field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Currency field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Cost field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"UnitOfMeasure field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"ValidFrom field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"ValidTo field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_13_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_13_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_14_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_14_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_14_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Origin field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Destination Country field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Currency field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Cost field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"UnitOfMeasure field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"ValidFrom field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", strValidToDate), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"ValidTo field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_15_\"Validation Error pop up\" is not displayed when \"ValidFrom\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_15_Validation Error Message is wrong when \"ValidFrom\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_16_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_16_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_16_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Origin field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Destination Country field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Currency field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Cost field\" is not set in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"UnitOfMeasure field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidFrom", strValidFromDate), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"ValidFrom field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges", nAddRow-1, "GroupageTrailer_ValidTo", ""), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"ValidTo field\" is not cleared in \"Groupage Trailer Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_021_Step_No_17_\"Validation Error pop up\" is not displayed when \"ValidTo\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_021_Step_No_17_Validation Error Message is wrong when \"ValidTo\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_021_Step_No_18_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_021_Step_No_18_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges"), true, "*****test_LIMA_TARR_RF_021_Step_No_18_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageTrailerCostDeleteRow()), true, "*****test_LIMA_TARR_RF_021_Step_No_19_\"Delete Row\" button is not clicked in \"Groupage Trailer Cost Cost Grid\"grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_021_Step_No_19_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Groupage Trailer Cost Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_021_Step_No_20_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Groupage Trailer Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\"tab*****");
			
		   int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageTrailerCharges");
		   objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_021_Step_No_20_Row is not deleted in \"Groupage Trailer Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost Tab\"*****");
			
		   objSoftAssert.assertAll();
     }
     
     @Test(priority = 1255)
     public void test_LIMA_TARR_RF_022_GroupageTrailerCostsTab_GroupageGOHCosts_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
          
         objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_1_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageGOH");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageGOHCostsAddRow()), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"Add Row Button\" is not clicked in \"Groupage GOH Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageGOH");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_022_Step_No_2_New Row is not added in \"Groupage GOH Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 
     	 String strOriginErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin");
	   	 String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry");
	   	 String strHangingTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType");
	   	 String strMinBarsErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars");
	   	 String strMaxBarsErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars");
	   	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency");
	   	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost");
	   	 
	   	 if(!strOriginErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strHangingTypeErrMsg.equals("")||!strMinBarsErrMsg.equals("")||!strMaxBarsErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals(""))
			{
		 		if(strOriginErrMsg.contains("&#39;"))
		 		{
		 			strOriginErrMsg = strOriginErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strOriginErrMsg.equalsIgnoreCase("> 'Origin' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Origin' is the required field\" message is not displayed as Origin field is a mandatory  in \"Groupage GOH Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Tailer\" tab*****");
		 		}
		 		if(strDestinationCountryErrMsg.contains("&#39;"))
		 		{
		 			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Destination Country' is the required field \" message is not displayed as DestinationCountry field is mandatory in \"Groupage GOH Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strHangingTypeErrMsg.contains("&#39;"))
		 			
		 		{
		 			strHangingTypeErrMsg = strHangingTypeErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strHangingTypeErrMsg.equalsIgnoreCase("> 'Hanging Type ' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Hanging Type' is the required field\" message is not displayed as Hanging Type field is mandatory in \"Groupage GOH Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		      		
		 		if(strMinBarsErrMsg.contains("&#39;"))
		 		{
		 			strMinBarsErrMsg = strMinBarsErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strMinBarsErrMsg.equalsIgnoreCase("> 'Min Bars' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Min Bars' is the required field\" message is not displayed as Min Bars field is mandatory in \"Groupage GOH Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strMaxBarsErrMsg.contains("&#39;"))
		 		{
		 			strMaxBarsErrMsg = strMaxBarsErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strMaxBarsErrMsg.equalsIgnoreCase("> 'Max Bars' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Max Bars' is the required field\" message is not displayed as Max Bars field is mandatory in \"Groupage GOH Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strCurrencyErrMsg.contains("&#39;"))
		 		{
		 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Groupage GOH Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strCostErrMsg.contains("&#39;"))
		    	{
		 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
					objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_022_Step_No_2_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Groupage GOH Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		    	}
			}
		 	else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_022_Step_No_3_"+strMessage + "validation message is empty*****";
		 	}
	        
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_3_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Groupage GOH Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_022_Step_No_3_Validation Error Message is wrong when all the mandatory fields are blank in \"Groupage GOH Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_4_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_4_\"Groupage GOH Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_4_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Origin field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Destination Country field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"HangingType field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"MinBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"MaxBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Currency field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Cost field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_5_\"Validation Error pop up\" is not displayed when \"Origin\" mandatory field is blank  in \"Groupage GOH Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_5_Validation Error Message is wrong when \"Origin\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_6_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_6_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_6_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Origin field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Destination Country field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"HangingType field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"MinBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"MaxBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Currency field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Cost field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_7_\"Validation Error pop up\" is not displayed when \"DestinationCountry\" mandatory field is blank  in \"Groupage GOH Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_7_Validation Error Message is wrong when \"DestinationCountry\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_8_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_8_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_8_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Origin field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Destination Country field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"HangingType field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"MinBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"MaxBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Currency field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Cost field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_9_\"Validation Error pop up\" is not displayed when \"HangingType\" mandatory field is blank  in \"Groupage GOH Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_9_Validation Error Message is wrong when \"HangingType\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_10_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_10_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_10_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Origin field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Destination Country field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"HangingType field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"MinBars field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"MaxBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Currency field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Cost field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_11_\"Validation Error pop up\" is not displayed when \"MinBars\" mandatory field is blank  in \"Groupage GOH Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_11_Validation Error Message is wrong when \"MinBars\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_12_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_12_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_12_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Origin field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Destination Country field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"HangingType field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"MinBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"MaxBars field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Currency field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Cost field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_13_\"Validation Error pop up\" is not displayed when \"MaxBars\" mandatory field is blank  in \"Groupage GOH Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_13_Validation Error Message is wrong when \"MaxBars\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_14_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_14_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_14_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Origin field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Destination Country field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"HangingType field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"MinBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"MaxBars field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Currency field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Cost field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_15_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Groupage GOH Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_15_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_16_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_16_\"Groupage GOH Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_16_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Origin", strOriginRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Origin field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Destination Country field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_HangingType", strHangingTypeRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Currency field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MinBars", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"MinBars field\" is not set in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_MaxBars", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"MaxBars field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Currency field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageGOH", nAddRow-1, "GroupageGOH_Cost", ""), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Cost field\" is not cleared in \"Groupage GOH Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_022_Step_No_17_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_022_Step_No_17_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Groupage GOH Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_022_Step_No_18_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_022_Step_No_18_\"Groupage GOH Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageGOH"), true, "*****test_LIMA_TARR_RF_022_Step_No_18_\"Groupage GOH Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageGOHCostsDeleteRow()), true, "*****test_LIMA_TARR_RF_022_Step_No_19_\"Delete Row\" button is not clicked in \"Groupage GOH Cost Grid\"grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_022_Step_No_19_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Groupage GOH  Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_022_Step_No_20_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Groupage GOH Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\"tab*****");
			
		   int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageGOH");
		   objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_022_Step_No_20_Row is not deleted in \"Groupage Trailer Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost Tab\"*****");
			
		   objSoftAssert.assertAll();
     }
     
     @Test(priority = 1260)
     public void test_LIMA_TARR_RF_023_GroupageTrailerCostsTab_GroupageLandsideCostsGrid_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageLandsideAddRow()), true, "*****test_LIMA_TARR_RF_023_Step_No_1_\"Add Row Button\" is not clicked in \"Groupage Landside Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_023_Step_No_1_New Row is not added in \"Groupage Landside Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 
     	 String strChargeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Charge");
	   	 String strDestinationCountryErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_DestinationCountry");
	   	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Currency");
	   	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Cost");
	   	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_UnitOfMeasure");
	   	 if(!strChargeErrMsg.equals("")||!strDestinationCountryErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals(""))
			{
		 		if(strChargeErrMsg.contains("&#39;"))
		 		{
		 			strChargeErrMsg = strChargeErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strChargeErrMsg.equalsIgnoreCase("> 'Charge' is a required field"), true, "*****test_LIMA_TARR_RF_023_Step_No_1_\"'Charge' is the required field\" message is not displayed as Charge field is a mandatory  in \"Groupage Landside Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Tailer\" tab*****");
		 		}
		 		if(strDestinationCountryErrMsg.contains("&#39;"))
		 		{
		 			strDestinationCountryErrMsg = strDestinationCountryErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strDestinationCountryErrMsg.equalsIgnoreCase("> 'Destination Country' is a required field"), true, "*****test_LIMA_TARR_RF_023_Step_No_1_\"'Destination Country' is the required field \" message is not displayed as DestinationCountry field is mandatory in \"Groupage Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strCurrencyErrMsg.contains("&#39;"))
		 			
		 		{
		 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_023_Step_No_1_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Groupage Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		      		
		 		if(strCostErrMsg.contains("&#39;"))
		 		{
		 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_023_Step_No_1_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Groupage Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strUnitOfMeasureErrMsg.contains("&#39;"))
		 		{
		 			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_023_Step_No_1_\"'Unit Of Measure' is the required field\" message is not displayed as Unit Of Measure field is mandatory in \"Groupage Landside Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 	
			}
		 	else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_023_Step_No_3_"+strMessage + "validation message is empty*****";
		 	}
	   	 
	   	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_2_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_2_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_023_Step_No_2_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Groupage Landside Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_023_Step_No_2_Validation Error Message is wrong when all the mandatory fields are blank in \"Groupage Landside Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_023_Step_No_3_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_023_Step_No_3_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges"), true, "*****test_LIMA_TARR_RF_023_Step_No_3_\"Groupage Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Charge", ""), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Charge field\" is not cleared in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Destination Country field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Currency field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Cost field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"UnitOfMeasure field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	      
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_023_Step_No_4_\"Validation Error pop up\" is not displayed when \"Charge\" mandatory field is blank  in \"Groupage Landside Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_023_Step_No_4_Validation Error Message is wrong when \"Charge\" mandatory field is blank in \"Groupage Landside Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_023_Step_No_5_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_023_Step_No_5_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges"), true, "*****test_LIMA_TARR_RF_023_Step_No_5_\"Groupage Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Charge", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Charge field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_DestinationCountry", ""), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Destination Country field\" is not cleared in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Currency field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Cost field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"UnitOfMeasure field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	      
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_023_Step_No_6_\"Validation Error pop up\" is not displayed when \"DestinationCountry\" mandatory field is blank  in \"Groupage Landside Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_023_Step_No_6_Validation Error Message is wrong when \"DestinationCountry\" mandatory field is blank in \"Groupage Landside Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_023_Step_No_7_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_023_Step_No_7_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges"), true, "*****test_LIMA_TARR_RF_023_Step_No_7_\"Groupage Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Charge", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"Charge field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"DestinationCountry field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Currency", ""), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"Currency field\" is not cleared in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"Cost field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"UnitOfMeasure field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	      
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_023_Step_No_8_\"Validation Error pop up\" is not displayed when \"Currency\" mandatory field is blank  in \"Groupage Landside Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_023_Step_No_8_Validation Error Message is wrong when \"Currency\" mandatory field is blank in \"Groupage Landside Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_023_Step_No_9_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_023_Step_No_9_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges"), true, "*****test_LIMA_TARR_RF_023_Step_No_9_\"Groupage Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Charge", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"Charge field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"DestinationCountry field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"Currency field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Cost", ""), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"Cost field\" is not cleared in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"UnitOfMeasure field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	      
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_023_Step_No_10_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Groupage Landside Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_023_Step_No_10_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Groupage Landside Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_023_Step_No_11_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_023_Step_No_11_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges"), true, "*****test_LIMA_TARR_RF_023_Step_No_11_\"Groupage Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Charge", strChargeTypeRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"Charge field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_DestinationCountry", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"DestinationCountry field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"Currency field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"Cost field\" is not set in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges", nAddRow-1, "GroupageLandsideCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"UnitOfMeasure field\" is not cleared in \"Groupage Landside Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	      
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_023_Step_No_12_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Groupage Landside Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_023_Step_No_12_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Groupage Landside Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_023_Step_No_13_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_023_Step_No_13_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageLandsideCharges"), true, "*****test_LIMA_TARR_RF_023_Step_No_13_\"Groupage Landside Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageLandsideDeleteRow()), true, "*****test_LIMA_TARR_RF_023_Step_No_19_\"Delete Row\" button is not clicked in \"Groupage Landside Cost Grid\"grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_023_Step_No_19_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Groupage Landside  Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   wait(2000);
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_023_Step_No_20_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Groupage Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\"tab*****");
			
		   int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageGOH");
		   objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_023_Step_No_20_Row is not deleted in \"Groupage Landside Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost Tab\"*****");
			
		   objSoftAssert.assertAll(); 
     }
     
     @Test(priority = 1265)
     public void test_LIMA_TARR_RF_024_GroupageTrailerCostsTab_GroupageHaulageCostsGrid_MandatoryFieldCheck()
     {
    	 SoftAssert objSoftAssert = new SoftAssert();
         objAdjunoLIMATariffPOM = new AdjunoLIMATariffPOM(driver);
         String strMessage = "";
         
         int nRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges");
     	 objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageHaulageCostAddRow()), true, "*****test_LIMA_TARR_RF_024_Step_No_1_\"Add Row Button\" is not clicked in \"Groupage Haulage Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 int nAddRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges");
     	 objSoftAssert.assertEquals(nAddRow == nRow+1, true, "*****test_LIMA_TARR_RF_024_Step_No_1_New Row is not added in \"Groupage Haulage Cost grid\" in Create New RaodFreight tariff page under Groupage Trailer Cost Tab*****");
     	 
    	 String strDeliveryTypeErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType");
	   	 String strDestinationErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination");
	   	 String strMinCBMErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM");
	   	 String strMaxCBMErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM");
	   	 String strCurrencyErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency");
	   	 String strCostErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost");
	   	 String strUnitOfMeasureErrMsg = objAdjunoLIMATariffPOM.getValidationMessageGridElement(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure");
	   	 
	   	 if(!strDeliveryTypeErrMsg.equals("")||!strDestinationErrMsg.equals("")||!strCurrencyErrMsg.equals("")||!strCostErrMsg.equals("")||!strUnitOfMeasureErrMsg.equals("")||!strMinCBMErrMsg.equals("")||!strMaxCBMErrMsg.equals(""))
			{
		 		if(strDeliveryTypeErrMsg.contains("&#39;"))
		 		{
		 			strDeliveryTypeErrMsg = strDeliveryTypeErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strDeliveryTypeErrMsg.equalsIgnoreCase("> ' Delivery Type' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'DeliveryType' is the required field\" message is not displayed as DeliveryType field is a mandatory  in \"Groupage Haulage Cost\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Tailer\" tab*****");
		 		}
		 		if(strDestinationErrMsg.contains("&#39;"))
		 		{
		 			strDestinationErrMsg = strDestinationErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strDestinationErrMsg.equalsIgnoreCase("> 'Destination' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'Destination' is the required field \" message is not displayed as Destination field is mandatory in \"Groupage Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strCurrencyErrMsg.contains("&#39;"))
		 			
		 		{
		 			strCurrencyErrMsg = strCurrencyErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCurrencyErrMsg.equalsIgnoreCase("> 'Currency' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'Currency' is the required field\" message is not displayed as Currency field is mandatory in \"Groupage Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		      		
		 		if(strCostErrMsg.contains("&#39;"))
		 		{
		 			strCostErrMsg = strCostErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strCostErrMsg.equalsIgnoreCase("> 'Cost' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'Cost' is the required field\" message is not displayed as Cost field is mandatory in \"Groupage Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strUnitOfMeasureErrMsg.contains("&#39;"))
		 		{
		 			strUnitOfMeasureErrMsg = strUnitOfMeasureErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strUnitOfMeasureErrMsg.equalsIgnoreCase("> 'Unit Of Measure' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'Unit Of Measure' is the required field\" message is not displayed as UnitOfMeasure field is mandatory in \"Groupage Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strMinCBMErrMsg.contains("&#39;"))
		 		{
		 			strMinCBMErrMsg = strMinCBMErrMsg.replace("&#39;", "'");
		 			objSoftAssert.assertEquals(strMinCBMErrMsg.equalsIgnoreCase("> 'Min CBM' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'Min CBM' is the required field\" message is not displayed as ValidFrom field is mandatory in \"Groupage Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		 		}
		 		
		 		if(strMaxCBMErrMsg.contains("&#39;"))
		    	{
		 			strMaxCBMErrMsg = strMaxCBMErrMsg.replace("&#39;", "'");
					objSoftAssert.assertEquals(strMaxCBMErrMsg.equalsIgnoreCase("> 'Max CBM' is a required field"), true, "*****test_LIMA_TARR_RF_024_Step_No_2_\"'Max CBM' is the required field\" message is not displayed as ValidTo field is mandatory in \"Groupage Haulage Cost\" grid in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		    	}
			}
		 	else
		 	{
		 		strMessage = "*****test_LIMA_TARR_RF_024_Step_No_3_"+strMessage + "validation message is empty*****";
		 	}
	        
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_3_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_3_\"Validation Error pop up\" is not displayed when all the mandatory fields are blank  in \"Groupage Haulage Cost Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_RF_024_Step_No_3_Validation Error Message is wrong when all the mandatory fields are blank in \"Groupage Haulage Cost Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_4_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_4_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_4_\"Groupage Trailer Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 //
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"DeliveryType field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"Destination  field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"MinBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"MaxBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"Currency field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"Cost field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"UnitOfMeasure field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_5_\"Validation Error pop up\" is not displayed when \"DeliveryType\" mandatory field is blank  in \"Groupage Haulage Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_5_Validation Error Message is wrong when \"DeliveryType\" mandatory field is blank in \"Groupage Haulage Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_6_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_6_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_6_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"DeliveryType field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"Destination  field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"MinBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"MaxBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"Currency field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"Cost field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"UnitOfMeasure field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_7_\"Validation Error pop up\" is not displayed when \"Destination\" mandatory field is blank  in \"Groupage Haulage Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_7_Validation Error Message is wrong when \"Destination\" mandatory field is blank in \"Groupage Haulage Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_8_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_8_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_8_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"DeliveryType field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"Destination field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"MinBars field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"MaxBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"Currency field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"Cost field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"UnitOfMeasure field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_9_\"Validation Error pop up\" is not displayed when \"MinBars\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_9_Validation Error Message is wrong when \"MinBars\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_10_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_10_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_10_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"DeliveryType field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"Destination  field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"MinBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"MaxBars field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"Currency field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"Cost field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"UnitOfMeasure field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_11_\"Validation Error pop up\" is not displayed when \"MaxBars\" mandatory field is blank  in \"Groupage Trailer Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_11_Validation Error Message is wrong when \"MaxBars\" mandatory field is blank in \"Groupage Trailer Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_12_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_12_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_12_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"DeliveryType field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"Destination field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"MinBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"MaxBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"Currency field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"Cost field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"UnitOfMeasure field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_13_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Groupage Haulage Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_13_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Groupage Haulage Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_14_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_14_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_14_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"DeliveryType field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"Destination field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"MinBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"MaxBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"Currency field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"Cost field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", strUnitOfMeasureRaodFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"UnitOfMeasure field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_15_\"Validation Error pop up\" is not displayed when \"Cost\" mandatory field is blank  in \"Groupage Haulage Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_15_Validation Error Message is wrong when \"Cost\" mandatory field is blank in \"Groupage Haulage Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_16_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_16_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_16_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_DeliveryType", strDeliveryTypeRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"DeliveryType field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****"); 
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Destination", strDestinationCountryRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"Destination field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MinCBM", strMinBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"MinBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_MaxCBM", strMaxBarsRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"MaxBars field\" is not set in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Currency", strCurrencyRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"Currency field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_Cost", strCostRoadFreight), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"Cost field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.setFieldValueForGridCell(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges", nAddRow-1, "GroupageHaulageCharges_UnitOfMeasure", ""), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"UnitOfMeasure field\" is not cleared in \"Groupage Haulage Grid\" in \"Create new Airfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	    	
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       wait(1000);
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnApply()), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"Apply button\" is not clicked in \"Create New Air Tariff page\"*****");
	       objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getValidationErrPopUpAirFreight()), true, "*****test_LIMA_TARR_RF_024_Step_No_17_\"Validation Error pop up\" is not displayed when \"UnitOfMeasure\" mandatory field is blank  in \"Groupage Haulage Grid\" in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.getValidationErrMsgAirFreight().getText().equals("Validation error in LIMA_Tariff_Roadfreight"), true, "*****test_LIMA_TARR_AF_024_Step_No_17_Validation Error Message is wrong when \"UnitOfMeasure\" mandatory field is blank in \"Groupage Haulage Grid\" in  \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
	 	
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnOK()), true, "*****test_LIMA_TARR_RF_024_Step_No_18_OK Button is not clicked in Validation error pop up in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickTab(strRoadFreightTariffFormName, "TabPanel_Tariff", "GroupageTrailerCosts"), true, "*****test_LIMA_TARR_RF_024_Step_No_18_\"Groupage Trailer Cost\" tab is not clicked in Create New RoadTariff page*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.checkDoesElementExist(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges"), true, "*****test_LIMA_TARR_RF_024_Step_No_18_\"Groupage Haulage Cost\" Grid  is not displayed under in \"Create new Roadfreight Tariff\" page*****");
	 	 
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnGroupageHaulageCostDeleteRow()), true, "*****test_LIMA_TARR_RF_024_Step_No_19_\"Delete Row\" button is not clicked in \"Groupage Haulage Cost Grid\"grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.isElementPresent(objAdjunoLIMATariffPOM.getDeleteRowPopUp()), true, "*****test_LIMA_TARR_RF_024_Step_No_19_\"Delete Row popup\" is not displayed when Delete row button is clicked in \"Groupage Haulage Cost  Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\" tab*****");
		   objSoftAssert.assertEquals(objAdjunoLIMATariffPOM.clickButtonUsingWebElement(objAdjunoLIMATariffPOM.getBtnYes()), true, "*****test_LIMA_TARR_RF_024_Step_No_20_Yes Button is not clicked in \"Delete Row popup\" when Delete row button is clicked in \"Groupage Haulage Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost\"tab*****");
			
		   int nDeleteRow = (int) objAdjunoLIMATariffPOM.getNoOfRowsGrid(strRoadFreightTariffFormName, "Grid_GroupageHaulageCharges");
		   objSoftAssert.assertEquals(nDeleteRow == nAddRow-1, true, "*****test_LIMA_TARR_RF_024_Step_No_20_Row is not deleted in \"Groupage Trailer Cost Grid\" grid in \"Create new Roadfreight Tariff\" page under \"Groupage Trailer Cost Tab\"*****");
			
		   objSoftAssert.assertAll();
         
     }
   
     @AfterTest
     public void closeBrowser()
     {
          driver.close();
     }
}
     
     

