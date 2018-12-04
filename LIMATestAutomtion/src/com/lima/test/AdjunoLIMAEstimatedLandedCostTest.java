package com.lima.test;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.EstimatedLandedCost;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMAEstimatedLandedCostPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMAEstimatedLandedCostTest {
	 WebDriver driver;
	    
	 long nPage_Timeout = 0;
	 String strTestURL;   
	 String strLIMAUserName;
	 String strLIMAPassword;
	 
	 NodeList nlSearchParamsEstimatedLandedCost;
	 NodeList nProductDetails;
	 NodeList nLoadingDetails;
	 
	 String strInvalidFormatDateEstimatedShipDate;
	 String strvalidFormatDateEstimatedShipDate;
	 String strInvalidFormatDateIntendedDeliveryDate;
	 String strvalidFormatDateIntendedDeliveryDate;
	 String strModeValue;
	 String strDateFieldValidFormatMessage;
	 String strTotalPieces;
	 String strPiecesPerCarton;
	 String strOriginCountry;
	 String strDestinationCountry;
	 String strDestination;
	 String strCostperPiece;
	 String strRetailPrice;
	 String strDutyRate;
	 String strDimensionsL;
	 String strDimensionsW;
	 String strDimensionsH;
	 String strCBMPerCarton;
	 String hyperlinkSeaFreight;
	 String strCurrency;
	 String strEquipment;
	 String strEquipmentAll;
	 String strCatalogEquipment;
	 String strCatalogVendor;
	 String strCatalogDestination;
	 String strCatalogLOCODE;
	 String strCatalogCurrency;
	 
	 String strFirstOriginvalue;
	 String strSecondOriginvalue;
	 String strFirstDestinationvalue;
	 String strSecondDestinationvalue;
	 
	 ArrayList<String> lstOriginCountryValues;
	 ArrayList<String> lstDestinationCountryValues;
	 ArrayList<String> lstDestinationValues;
	 ArrayList<String> equipmentList;
	 ArrayList<String> catalogOriginPortList;
	 ArrayList<String> catalogDestinationPortList;
	 ArrayList<String> lstMarginValues;
	 
	 ArrayList<String> fclFreightCostOriginCountryList;
	 ArrayList<String> fclFreightCostDestinationCountryList;
	 ArrayList<String> fclFreightCostOriginPortList;
	 ArrayList<String> fclFreightCostDestinationPortList;
	 ArrayList<String> fclLandedCostChargeDescriptionList;
	 ArrayList<String> fclLandedCostDestinationCountryList;
	 ArrayList<String> fclLandedCostDestinationPortList;
	 ArrayList<String> fclHaulageCostExportList;
	 
	 String ContainerValue = null;
	 String ContainerValueLandedCost=null;
	 String ContainerValueHaulageCost=null;
	 String tariffToolCurrencyValue;
	 String tariffToolFCLLandedCostsCurrencyValue;
	 String tariffToolFCLHaulageCostsCurrencyValue;
	 
	 String strEstimatedLandedCostFormName;
	 String strEstimatedLandedCostPageTitle;
	 String strCatalogFormName;
	 String strCatalogPageTitle;
	 String strCatalogEquipmentFormName;
	 
	 ArrayList<String> strModeDropDownValue;
	 ArrayList<String> lstVendorDropdownValue;
	 ArrayList<String> lstEquipmentDropdownValue;
	 ArrayList<String> lstOriginPortToolDropDownValues;
	 ArrayList<String> lstDestinationPortDropDownValues;
	 ArrayList<String> lstLoadingDropdownValue;
	 ArrayList<String> lstCurrencyDropDownValues;
	 
	 List<WebElement> lstSupplierDescription;
	 List<WebElement> lstDestinationDescription;
	 List<WebElement> lstCurrencyDescription;
	 List<WebElement> lstLOCODEDescription;
	 
	 ArrayList<String> lstOriginCountryDropDownValues;
	 ArrayList<String> lstDestinationCountryDropDownValues;
	 
	 boolean bSearchResultsProductsFound = true;
	 AdjunoLIMAEstimatedLandedCostPOM objAdjunoLIMAEstimatedLandedCostPOM;
	 
	 AdjunoLIMALibrary objAdjunoLIMALibrary;
	 AdjunoUILibrary objAdjunoUILibrary;
	 AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	 
	 EstimatedLandedCost estGridValues;
	 
	 
	 @BeforeTest
	 public void setup() {
		 objAdjunoLIMALibrary = new AdjunoLIMALibrary();

		 DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		 try {
			 DocumentBuilder builder = domFactory.newDocumentBuilder();
			 Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());

			 XPath xPath = XPathFactory.newInstance().newXPath();

			 Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout", dDoc, XPathConstants.NODE);
			 nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());

			 Node testURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL",dDoc, XPathConstants.NODE);
			 strTestURL = testURL.getTextContent();

			 Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
			 strLIMAUserName = limaUserName.getTextContent();

			 Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
			 strLIMAPassword = limaPassword.getTextContent();
				
			 Node estimatedLandedCostFormName = (Node) xPath.evaluate("/config/LIMA/Form_Name_EstimatedLandedCost", dDoc, XPathConstants.NODE);
			 strEstimatedLandedCostFormName = estimatedLandedCostFormName.getTextContent();

			 Node estimatedLandedCostPageTitle = (Node) xPath.evaluate("/config/LIMA/PageTitle_EstimatedLandedCost", dDoc, XPathConstants.NODE);
			 strEstimatedLandedCostPageTitle = estimatedLandedCostPageTitle.getTextContent();
	    	
			 Node CatalogFormName=(Node) xPath.evaluate("/config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	    	 strCatalogFormName=CatalogFormName.getTextContent();
	    	 
	    	 Node CatalogPageTitle=(Node) xPath.evaluate("/config/LIMA/Catalog_PageTitle", dDoc, XPathConstants.NODE);
	    	 strCatalogPageTitle=CatalogPageTitle.getTextContent();
	    	 
	    	 Node CatalogEquipmentFormName=(Node) xPath.evaluate("/config/LIMA/Catalog_EquipmentFormName", dDoc, XPathConstants.NODE);
	    	 strCatalogEquipmentFormName=CatalogEquipmentFormName.getTextContent();

			 driver = new FirefoxDriver();
			 driver.manage().window().maximize();

			 driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

			 driver.get(strTestURL);

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
			 Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrLIMAEstimatedLandedCost());

			 XPath xPath1 = XPathFactory.newInstance().newXPath();
			 
			 nlSearchParamsEstimatedLandedCost = (NodeList) xPath1.evaluate("/EstimatedLandedCost/SearchParams", dDoc1, XPathConstants.NODESET);
			 nProductDetails = (NodeList) xPath1.evaluate("/EstimatedLandedCost/ColumnDetails", dDoc1,XPathConstants.NODESET);
			 nLoadingDetails = (NodeList) xPath1.evaluate("/EstimatedLandedCost/Loading", dDoc1,XPathConstants.NODESET);

			 Element el;
	            for(int i=0; i <= nlSearchParamsEstimatedLandedCost.getLength() -1; i++)
	            {
	            	if (nlSearchParamsEstimatedLandedCost.item(i).getNodeType() == Node.ELEMENT_NODE)
	            	{
	            		el = (Element) nlSearchParamsEstimatedLandedCost.item(i);
	                	 
	            		strInvalidFormatDateEstimatedShipDate = el.getElementsByTagName("EstimatedShipDate_InvalidFormat").item(0).getTextContent(); 
				
	            		strvalidFormatDateEstimatedShipDate = el.getElementsByTagName("EstimatedShipDate_validFormat").item(0).getTextContent(); 

	            		strInvalidFormatDateIntendedDeliveryDate = el.getElementsByTagName("IntendedDeliverydate_InvalidFormat").item(0).getTextContent(); 

	            		strvalidFormatDateIntendedDeliveryDate = el.getElementsByTagName("IntendedDeliverydate_validFormat").item(0).getTextContent(); 

	            		strModeValue = el.getElementsByTagName("Mode_Value").item(0).getTextContent(); 

	            		strDateFieldValidFormatMessage = el.getElementsByTagName("ValidDateFormatValidationMsg").item(0).getTextContent(); 

	            		strTotalPieces = el.getElementsByTagName("TotalPieces").item(0).getTextContent(); 

	            		strPiecesPerCarton = el.getElementsByTagName("PiecesPerCarton").item(0).getTextContent(); 
	            	
	            		strOriginCountry = el.getElementsByTagName("OriginCountry").item(0).getTextContent(); 

	            		strDestinationCountry = el.getElementsByTagName("DestinationCountry").item(0).getTextContent(); 

	            		strDestination = el.getElementsByTagName("Destination").item(0).getTextContent(); 

	            		strCostperPiece= el.getElementsByTagName("CostperPiece").item(0).getTextContent(); 

	            		strRetailPrice = el.getElementsByTagName("RetailPrice").item(0).getTextContent(); 

	            		strDutyRate = el.getElementsByTagName("DutyRate").item(0).getTextContent(); 

	            		strDimensionsL = el.getElementsByTagName("Dimensions_L").item(0).getTextContent(); 

	            		strDimensionsW = el.getElementsByTagName("Dimensions_W").item(0).getTextContent(); 

	            		strDimensionsH = el.getElementsByTagName("Dimensions_H").item(0).getTextContent(); 

	            		strCBMPerCarton = el.getElementsByTagName("CBMperCarton").item(0).getTextContent(); 

	            		strCurrency = el.getElementsByTagName("Currency").item(0).getTextContent(); 

	            		strEquipment = el.getElementsByTagName("Equipment").item(0).getTextContent(); 

	            		strEquipmentAll = el.getElementsByTagName("Equipment_All").item(0).getTextContent(); 
	            		
	            		strCatalogEquipment = el.getElementsByTagName("Catalog_Equipment").item(0).getTextContent(); 

	            		strCatalogVendor = el.getElementsByTagName("Catalog_Vendor").item(0).getTextContent(); 

	            		strCatalogDestination = el.getElementsByTagName("Catalog_Destination").item(0).getTextContent(); 

	            		strCatalogLOCODE = el.getElementsByTagName("Catalog_LOCODE").item(0).getTextContent(); 

	            		strCatalogCurrency = el.getElementsByTagName("Catalog_Currency").item(0).getTextContent(); 

	            		
	            		
	            	}
	            }
	                     
			
		 }catch (Exception e) {	
			 e.printStackTrace();
		 }
	 }
		
	 private boolean isNullOrBlank(String s) {
		 return (s == null || s.trim().equals(""));
	 }
		
	 public void wait(int ms) {
		 try {
			 Thread.sleep(ms);
		 } catch (InterruptedException e) {
			 e.printStackTrace();
		 }
	 }
	 
	 @Test(priority=1)	
	 public void accessCatalog(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM=new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 wait(5000);
		 String strTitlecatalog=objAdjunoLIMAEstimatedLandedCostPOM.callMouseHover(strCatalogFormName, objAdjunoLIMAEstimatedLandedCostPOM.getLnkTools(), objAdjunoLIMAEstimatedLandedCostPOM.getLnkCatalog());
		 boolean bFlag1= true;	
		 System.out.println(strTitlecatalog);
		 if (isNullOrBlank(strTitlecatalog))
			 bFlag1 = false;
		        
		 if (!(isNullOrBlank(strTitlecatalog)))
		 {
			 if (strTitlecatalog.equalsIgnoreCase(strCatalogPageTitle))
				 bFlag1 = true;
			 else
				 bFlag1 = false;
		 }    	
		 objSoftAssert.assertEquals(bFlag1, true, "Page Title of the Catalog tool is wrong");
			
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getLnklighthouse()),true,"***** In catalog tool \"LightHouse\" button is not clicked *****");

		 objSoftAssert.assertAll();
	 }
	 	
	 public void clearCatalogField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 wait(5000);
		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** In catalog tool under lighthouse page \"Name\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** In catalog tool under lighthouse page \"Type\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** In catalog tool under lighthouse page \"Description\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** In catalog tool under lighthouse page \"Associated items\" Field is not cleared *****");
		 	
		 objSoftAssert.assertAll();
	 }
	 
	 // get the catalog values on different field 	
	 @Test(priority=5)
	 public void getCatalogValues()
	 {	
		 SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage ="";
	
		 clearCatalogField();
		 if(!isNullOrBlank(strCatalogEquipment)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCatalogEquipment, strCatalogFormName, "Param_Type"),true,"***** Test ID : 23 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 23 - \"Equipment\" Value is Empty in Xml *****";
		 }
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getCatalogbtnApply()),true,"*****Test ID : 23 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		 List<WebElement> catalogEquipmentList= objAdjunoLIMAEstimatedLandedCostPOM.getLstCatalogEquipment();
		 System.out.println("1--- "+catalogEquipmentList.size());
		 equipmentList =new ArrayList<String>();
			
		 equipmentList = objAdjunoLIMAEstimatedLandedCostPOM.getCatalogData(catalogEquipmentList, "CatalogEquipment_OptimumFill", "CatalogEquipment_CatalogItemDescription");
		 if(equipmentList.size()>0){
			 System.out.println("23. "+ equipmentList.size());
		 }else{
			 strMessage = strMessage +" ***** Test ID :23 - \"Equipment\" Type doesn't have data *****";
		 }
		 
		 clearCatalogField();
		 if(!isNullOrBlank(strCatalogVendor)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCatalogVendor, strCatalogFormName, "Param_Type"),true,"***** Test ID : 22 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 22 - \"Supplier\" Value is Empty in Xml *****";
		 }
		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getCatalogbtnApply()),true,"*****Test ID : 22 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		  
		 lstSupplierDescription = objAdjunoLIMAEstimatedLandedCostPOM.getLstCatalogDescription();
		 if(lstSupplierDescription.size()>0){
			 System.out.println("22."+lstSupplierDescription.size());
		 }else{
			 strMessage=strMessage+"*****Test ID : 22 - \"Supplier\" Type doesn't have any value *****";

		 }
		 
		//OriginPort
		 clearCatalogField();
		 if(!isNullOrBlank(strCatalogLOCODE)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCatalogLOCODE, strCatalogFormName, "Param_Type"),true,"***** Test ID : 20 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 20 - \"LOCODE\" Value is Empty in Xml *****";
		 }
		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getCatalogbtnApply()),true,"*****Test ID : 20 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		 		 
		 int nCount3 = objAdjunoLIMAEstimatedLandedCostPOM.valCount();
		 System.out.println(" ncount "+nCount3);
		 catalogOriginPortList = new ArrayList<String>();
		 catalogOriginPortList = objAdjunoLIMAEstimatedLandedCostPOM.getCatalogTableData(nCount3,strOriginCountry,"CatalogLOCODE_CatalogItemDescription");
		 wait(5000);
		 System.out.println("20.catalogOriginPortList - 5 -- "+catalogOriginPortList.size());
		 if(catalogOriginPortList.size()>0){
			 for(int i=0;i<catalogOriginPortList.size();i++){
				 System.out.println(i+".** "+catalogOriginPortList.get(i));
			 }
		 }else{
		 	strMessage = strMessage +"***** Test ID : 20 - \"Origin Port\" Type doesn't have data *****";
		 }
		 
		 
		 clearCatalogField();
		 if(!isNullOrBlank(strCatalogDestination)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCatalogDestination, strCatalogFormName, "Param_Type"),true,"***** Test ID : 6 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 6 - \"Destination\" Value is Empty in Xml *****";
		 }
		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getCatalogbtnApply()),true,"*****Test ID : 6 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		 
		 lstDestinationDescription = objAdjunoLIMAEstimatedLandedCostPOM.getLstCatalogDescription();
		 if(lstDestinationDescription.size()>0){
			 System.out.println("6."+lstDestinationDescription.size());
		 }else{
			 strMessage=strMessage+"*****Test ID : 6 - \"Destination\" Type doesn't have any value *****";

		 }
		 
		 clearCatalogField();
		 if(!isNullOrBlank(strCatalogCurrency)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCatalogCurrency, strCatalogFormName, "Param_Type"),true,"***** Test ID : 25 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 25 - \"Currency\" Value is Empty in Xml *****";
		 }
		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getCatalogbtnApply()),true,"*****Test ID : 25 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		 
		 lstCurrencyDescription = objAdjunoLIMAEstimatedLandedCostPOM.getLstCatalogEquipment();
		 if(lstCurrencyDescription.size()>0){
			 System.out.println("25."+lstCurrencyDescription.size());
		 }else{
			 strMessage=strMessage+"***** Test ID : 25 - \"Currency\" Type doesn't have any value *****";

		 }
		 
		 //DestinationPort
		 wait(5000);
		 clearCatalogField();
		 if(!isNullOrBlank(strCatalogLOCODE)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCatalogLOCODE, strCatalogFormName, "Param_Type"),true,"***** Test ID : 20 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 21 - \"LOCODE\" Value is Empty in Xml *****";
		 }
		 wait(5000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getCatalogbtnApply()),true,"*****Test ID : 20 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		 		 
		 int nCount4 = objAdjunoLIMAEstimatedLandedCostPOM.valCount();
		 System.out.println(" ncount "+nCount4);
		 catalogDestinationPortList = new ArrayList<String>();
		 catalogDestinationPortList = objAdjunoLIMAEstimatedLandedCostPOM.getCatalogTableData(nCount4,strDestinationCountry,"CatalogLOCODE_CatalogItemDescription");
		 wait(5000);
		 System.out.println("21.catalogDestinationPortList - 24 -- "+catalogDestinationPortList.size());
		 if(catalogDestinationPortList.size()>0){
			 for(int i=0;i<catalogDestinationPortList.size();i++){
				 System.out.println(i+".** "+catalogDestinationPortList.get(i));
			 }
		 }else{
		 	strMessage = strMessage +"***** Test ID : 21- \"Destination Port\" Type doesn't have data *****";
		 }

		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, strMessage);
		 objSoftAssert.assertAll();
		 
	 }
		 
	 @Test(priority=10)
	 public void test_1_CheckforEstimatedLandedCostPage(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 wait(3000);
		 String strTitle = objAdjunoLIMAEstimatedLandedCostPOM.callMouseHover(strEstimatedLandedCostFormName,objAdjunoLIMAEstimatedLandedCostPOM.getLnkTools(),objAdjunoLIMAEstimatedLandedCostPOM.getLnkEstimatedLandedCost());

		 boolean bFlag = true;

		 if (isNullOrBlank(strTitle))
			 bFlag = false;

		 if (!(isNullOrBlank(strTitle))) {
			 if (strTitle.equalsIgnoreCase(strEstimatedLandedCostPageTitle))
				 bFlag = true;
			 else
				 bFlag = false;
		 }
		 System.out.println(strTitle);
		 objSoftAssert.assertEquals(bFlag, true, "***** Page Title of the Estimated Landed Cost is wrong *****");
		 objSoftAssert.assertAll();
	 }
	
	 public void ClearShipmentsPanelField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_Product"), true , " In Estimated Landed cost tool under shipments Panel \"Product\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Combobox_Mode"), true," In Estimated Landed cost tool under shipments Panel \"mode\" dropdown field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_EstimatedShipDate"), true," In Estimated Landed cost tool under shipments Panel \"Estimated Ship Date\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate"), true , " In Estimated Landed cost tool under shipments Panel \"Intended Delivery Date\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Combobox_OriginCountry"), true," In Estimated Landed cost tool under shipments Panel \"Origin Country\" dropdown field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Combobox_DestinationCountry"), true," In Estimated Landed cost tool under shipments Panel \"Destination Country\" dropdown field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Combobox_Destination"), true," In Estimated Landed cost tool under shipments Panel \"Destination\" dropdown field is not cleared ");

		 objSoftAssert.assertAll();
	 }
		
	 public void ClearCostsPanelField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
	   
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_CostPerPiece"), true , " In Estimated Landed cost tool under Costs Panel \"Cost per piece\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_TotalPieces"), true," In Estimated Landed cost tool under Costs Panel \"Total Pieces\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_PiecesPerCarton"), true," In Estimated Landed cost tool under Costs Panel \"Pieces Per Carton\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_DutyRate"), true , " In Estimated Landed cost tool under Costs Panel \"Duty Rate %\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_CBMPerCarton"), true," In Estimated Landed cost tool under Costs Panel \"CBM per Carton\" field is not cleared ");
		
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_RetailPrice"), true," In Estimated Landed cost tool under Costs Panel \"Retail Price\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_AdditionalCosts"), true," In Estimated Landed cost tool under Costs Panel \"Additional Costs (Total)\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_Dimensions_L"), true," In Estimated Landed cost tool under Costs Panel \"Textbox_Dimensions_L\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_Dimensions_W"), true," In Estimated Landed cost tool under Costs Panel \"Textbox_Dimensions_W\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_Dimensions_H"), true," In Estimated Landed cost tool under Costs Panel \"Textbox_Dimensions_H\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName,"Textbox_Weight"), true," In Estimated Landed cost tool under Costs Panel \"Total Weight\" field is not cleared ");

		 objSoftAssert.assertAll();
	 }
	 
	 public void ClearHiddenField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
	   
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_OriginPort"), true , " In Estimated Landed cost tool under Costs Panel \"OriginPort\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_DestinationPort"), true , " In Estimated Landed cost tool under Costs Panel \"DestinationPort\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_Vendor"), true , " In Estimated Landed cost tool under Costs Panel \"Vendor\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_Loading"), true , " In Estimated Landed cost tool under Costs Panel \"Loading\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_Equipment"), true , " In Estimated Landed cost tool under Costs Panel \"Equipment\" field is not cleared ");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_PONumber"), true , " In Estimated Landed cost tool under Costs Panel \"PONumber\" field is not cleared ");
		 
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=15)
	 public void test_2_CheckMandatoryfields(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 wait(8000);
		 String validationMsgMode = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Combobox_Mode");
		 	System.out.println("--1."+validationMsgMode);
		 if(!validationMsgMode.equals(""))
		 {
			 if(validationMsgMode.contains("&#39;"))
			 {
				 validationMsgMode = validationMsgMode.replace("&#39;", "'");
				 objSoftAssert.assertEquals(validationMsgMode.equalsIgnoreCase("> 'Combobox_Mode' is a required field"), true, "*****test_2_''Mode' is the required field' message is not displayed*****");
			 }else{
				 strMessage = "***** test_2_ "+ strMessage + "In Estimated Landed Cost Tool under Shipments Panel 'Mode' Field validation message is null *****";
			 }
		 }
		
		 String validationMsgEstimatedShipDate = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate");
		 System.out.println("--2."+validationMsgEstimatedShipDate);

		 if(!validationMsgEstimatedShipDate.equals(""))
		 {
			 if(validationMsgEstimatedShipDate.contains("&#39;"))
			 {
				 validationMsgEstimatedShipDate = validationMsgEstimatedShipDate.replace("&#39;", "'");
				 objSoftAssert.assertEquals(validationMsgEstimatedShipDate.equalsIgnoreCase("> 'Estimated Ship Date' is a required field"), true, "*****test_2_''Estimated Ship Date' is the required field' message is not displayed*****");
			 }else{
				 strMessage = "***** test_2_ "+ strMessage + " In Estimated Landed Cost Tool under Shipments Panel 'Estimated Ship Date' Field validation message is null*****";
			 }
		 }
		
		 String validationMsgIntendedDeliveryDate = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate");
//		 System.out.println("--3."+validationMsgIntendedDeliveryDate);

		 if(!validationMsgIntendedDeliveryDate.equals(""))
		 {
			 if(validationMsgIntendedDeliveryDate.contains("&#39;"))
			 {
				 validationMsgIntendedDeliveryDate = validationMsgIntendedDeliveryDate.replace("&#39;", "'");
				 objSoftAssert.assertEquals(validationMsgIntendedDeliveryDate.equalsIgnoreCase("> 'Intended Delivery Date' is a required field"), true, "*****test_2_''Intended Delivery Date' is the required field' message is not displayed*****");
			 }else{
				 strMessage = "***** test_2_ "+ strMessage + " In Estimated Landed Cost Tool under Shipments Panel 'Intended Delivery Date' Field validation message is null*****";
			 }
		 }
		
		 String validationMsgOriginCountry = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Combobox_OriginCountry");
	//	 System.out.println("--4."+validationMsgOriginCountry);

		 if(!validationMsgOriginCountry.equals(""))
		 {
			 if(validationMsgOriginCountry.contains("&#39;"))
			 {
				 validationMsgOriginCountry = validationMsgOriginCountry.replace("&#39;", "'");
				 objSoftAssert.assertEquals(validationMsgOriginCountry.equalsIgnoreCase("> 'Combobox_OriginCountry' is a required field"), true, "*****test_2_''Origin Country' is the required field' message is not displayed*****");
			 }else{
				 strMessage = "***** test_2_ "+ strMessage + " In Estimated Landed Cost Tool under Shipments Panel 'Origin Country' Field validation message is null*****";
			 }
		 }
		
		 String validationMsgDestinationCountry = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Combobox_DestinationCountry");
//		 System.out.println("--5."+validationMsgDestinationCountry);

		 if(!validationMsgDestinationCountry.equals(""))
		 {
			 if(validationMsgDestinationCountry.contains("&#39;"))
			 {
				 validationMsgDestinationCountry = validationMsgDestinationCountry.replace("&#39;", "'");
				 objSoftAssert.assertEquals(validationMsgDestinationCountry.equalsIgnoreCase("> 'Combobox_DestinationCountry' is a required field"), true, "*****test_2_''Destination Country' is the required field' message is not displayed*****");
			 }else{
				 strMessage = "***** test_2_ "+ strMessage + " In Estimated Landed Cost Tool under Shipments Panel 'Destination Country' Field validation message is null*****";
			 }
		 }
		
		 String validationMsgDestination = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Combobox_Destination");
//		 System.out.println("--6."+validationMsgDestination);

		 if(!validationMsgDestination.equals(""))
		 {
			 if(validationMsgDestination.contains("&#39;"))
			 {
				 validationMsgDestination = validationMsgDestination.replace("&#39;", "'");
				 objSoftAssert.assertEquals(validationMsgDestination.equalsIgnoreCase("> 'Combobox_Destination' is a required field"), true, "*****test_2_''Destination' is the required field' message is not displayed*****");
			 }else{
				 strMessage = "***** test_2_ "+ strMessage + " In Estimated Landed Cost Tool under Shipments Panel 'Destination' Field validation message is null*****";
			 }
		 }
			
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  strMessage);
		 objSoftAssert.assertAll();
		
	 }
	
	 @Test(priority=20)
	 public void test_3_InspectModeDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 strModeDropDownValue = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_Mode");
		
		 if(strModeDropDownValue.size()==1){
			 for(int i=0;i< strModeDropDownValue.size();i++){
				 objSoftAssert.assertEquals(strModeDropDownValue.get(i).equalsIgnoreCase("Sea"), true ,"***** test_3_ In Estimated Landed Cost Tool under Shipments Panel \"Mode\" DropDown value is not matching with \"Sea\" *****");
			 }
				
		 }else{
			 strMessage = strMessage + " In Estimated Landed Cost Tool under Shipments Panel \"Mode\" Drop Down doesn't have any data";
		 }
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_3_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }

	 @Test(priority=20)
	 public void InspectOriginCountryDropdownValues(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 lstOriginCountryValues=new ArrayList<String>();
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strModeValue, strEstimatedLandedCostFormName, "Combobox_Mode"), true, "***** test_4_ In Estimated Landed Cost Tool under Shipments Panel \"Mode\" Field Value is not Set *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strvalidFormatDateEstimatedShipDate, strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate"), true, "***** test_4_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship Date\" Field Value is not Set *****");
		 wait(15000);
		 lstOriginCountryValues = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_OriginCountry");
		 System.out.println("lstOriginCountryValues Sise is "+ lstOriginCountryValues.size());
		 if(lstOriginCountryValues.size()>0){
		//		strMessage=strMessage+objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(lstOriginCountryDropDownValues, lstOriginCountryValues);

		 }else{
			 strMessage = strMessage +"In Estimated Landed Cost Tool under Shipments Panel \"Origin Country\" Drop Down doesn't have data.";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_4_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=25)
	 public void InspectDestinationCountryDropdownValues(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 lstDestinationCountryValues=new ArrayList<String>();
		 String strMessage="";
		
		 wait(10000);
		 lstDestinationCountryValues = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_DestinationCountry");
		 System.out.println("lstDestinationCountryValues Sise is "+lstDestinationCountryValues.size());
		
		 if(lstDestinationCountryValues.size()>0){
		//	strMessage=strMessage+objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(lstDestinationCountryDropDownValues, lstDestinationCountryValues);
		 }else{
			 strMessage = strMessage +"In Estimated Landed Cost Tool under Shipments Panel \"Destination Country\" Drop Down doesn't have data.";
		 }
	
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_5_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=35)
	 public void test_6_InspectDestinationDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 wait(7000);
		 lstDestinationValues = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_Destination");
		 System.out.println("lstDestinationValues Sise is "+lstDestinationValues.size());
		
		 if(lstDestinationValues.size()>0){
			strMessage= strMessage+objAdjunoLIMAEstimatedLandedCostPOM.verifyDropDownValues(lstDestinationValues, lstDestinationDescription);
		 }else{
			 strMessage = strMessage +"In Estimated Landed Cost Tool under Shipments Panel \"Destination\" Drop Down doesn't have data.";
		 }
	
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_6_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=40)
	 public void test_7_SetInvalidDateinEstimatedShipDateField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strInvalidFormatDateEstimatedShipDate, strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate"), true, "***** test_7_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship Daate\" Field Value is not Set *****");
		 wait(5000);
		 ArrayList<String> validationMsgEstimatedShipDate = objAdjunoLIMAEstimatedLandedCostPOM.getListofValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate");
		 System.out.println("21."+validationMsgEstimatedShipDate.get(1));
		 wait(5000);
		 if(!validationMsgEstimatedShipDate.get(1).equals(""))
		 {
			 objSoftAssert.assertEquals(validationMsgEstimatedShipDate.get(1).contains(strDateFieldValidFormatMessage), true, "*****test_7_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship Date\" field mandatory message is not matching with the 'Mandatory' message displayed under \"Estimated Ship Date\" field *****");
		 }else{
			 strMessage = strMessage +" In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship Date\" field validation Message Value is Null";
		 }
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_7_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }

	 @Test(priority=45)
	 public void test_8_SetValidDateinEstimatedShipDateField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strvalidFormatDateEstimatedShipDate, strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate"), true, "***** test_8_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship Date\" Field Value is not Set *****");
		 wait(5000);
		 ArrayList<String> validationMsgEstimatedShipDate = objAdjunoLIMAEstimatedLandedCostPOM.getListofValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate");
		 wait(5000);
		 if(validationMsgEstimatedShipDate.size()==0){
			
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship Date\" field value is not Accepted";
		 }
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_8_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=50)
	 public void test_9_SetInvalidDateinIntendedDeliveryDateField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strInvalidFormatDateIntendedDeliveryDate, strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate"), true, "***** test_9_ In Estimated Landed Cost Tool under Shipments Panel \"Intended Delivery Date\" Field Value is not Set *****");
		 wait(5000);
		 ArrayList<String> validationMsgIntendedDeliveryDate = objAdjunoLIMAEstimatedLandedCostPOM.getListofValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate");
		 System.out.println("21."+validationMsgIntendedDeliveryDate.get(1));
		 wait(5000);
		 if(!validationMsgIntendedDeliveryDate.get(1).equals(""))
		 {
			 objSoftAssert.assertEquals(validationMsgIntendedDeliveryDate.get(1).contains(strDateFieldValidFormatMessage), true, "*****test_7_ In Estimated Landed Cost Tool under Shipments Panel \"Intended Delivery Date\" field mandatory message is not matching with the 'Mandatory' message displayed under \"Intended Delivery Date\" field *****");
		 }else{
			 strMessage = strMessage +" In Estimated Landed Cost Tool under Shipments Panel \"Intended Delivery Date\" field validation Message Value is Null";
		 }
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_9_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }

	 @Test(priority=55)
	 public void test_10_SetValidDateinIntendedDeliveryDateField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strvalidFormatDateIntendedDeliveryDate, strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate"), true, "***** test_10_ In Estimated Landed Cost Tool under Shipments Panel \"Intended Delivery date\" Field Value is not Set *****");
		 wait(5000);
		 ArrayList<String> validationMsgIntendedDeliveryDate = objAdjunoLIMAEstimatedLandedCostPOM.getListofValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate");
		 
		 if(validationMsgIntendedDeliveryDate.size()==0){
			
		 }else{
			 strMessage = strMessage+" In Estimated Landed Cost Tool under Shipments Panel \"Intended Delivery Date\" field value is not Accepted";
		 }
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_10_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=60)
	 public void test_11_InspectAvailableOriginsColumnValues(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 ClearShipmentsPanelField();
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strModeValue, strEstimatedLandedCostFormName, "Combobox_Mode"), true, "***** test_11_ In Estimated Landed Cost Tool under Shipments Panel \"Mode\" Field Value is not Set *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strvalidFormatDateEstimatedShipDate, strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate"), true, "***** test_11_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship date\" Field Value is not Set *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreOriginCountries"), true, "***** test_11_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreOriginCountriesButton\" is not Clicked *****");
		
		 wait(10000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableOriginColumn().size()>0){
				 System.out.println("11."+objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableOriginColumn().size());
				
				 strMessage = strMessage +objAdjunoLIMAEstimatedLandedCostPOM.verifyDropDownValues(lstOriginCountryValues,objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableOriginColumn());
				
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_11_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 wait(4000);
			 }
		 }catch(Exception e){
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Available Origins\" Column doesn't have data ";
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_11_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");

		 }
	 
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_11_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=65)
	 public void test_12_InspectAvailableDestinationColumnValues(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreDestinationCountries"), true, "***** test_12_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreDestinationCountriesButton\" is not Clicked *****");
		 wait(4000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableDestinationColumn().size()>0){
				 System.out.println("12."+objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableDestinationColumn().size());

				 strMessage = strMessage +objAdjunoLIMAEstimatedLandedCostPOM.verifyDropDownValues(lstDestinationCountryValues, objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableDestinationColumn());
				
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_12_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");

			 }
		 }catch(Exception e){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_12_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Available Destinations\" Column doesn't have data ";
		
		 }
		
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_12_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=70)
	 public void test_13_SelectAvailableOriginColumnValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";		
	
		 wait(10000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreOriginCountries"), true, "***** test_13_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreOriginCountriesButton\" is not Clicked *****");
		 wait(4000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableOriginColumn().size()>0){
				 strFirstOriginvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstValue().getText();
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstValue()), true , "***** test_13_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Origin Column *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_SelectMoreCountries"), true , "***** test_13_ In Estimated Landed Cost Tool under Shipments Panel \"Forward\" button is not Clicked *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_13_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 wait(4000);
				
				 objSoftAssert.assertEquals(strFirstOriginvalue, objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Combobox_OriginCountry"), "***** test_13_ In Estimated Landed Cost Tool under Shipments Panel, the value which was selected under \"Available Origins\" Column is not matching with the value displaying in \"Origin Country\" dropdown *****");
				 System.out.println("13. "+strFirstOriginvalue +"," +objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Combobox_OriginCountry"));
			
			 }
			 
		 }catch(Exception e){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_13_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Available Origins\" Column doesn't have data ";

		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_13_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=75)
	 public void test_14_SelectAvailableDestinationColumnValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";		
	
		 wait(1000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreDestinationCountries"), true, "***** test_14_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreDestinationCountriesButton\" is not Clicked *****");
		 wait(4000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getLstAvailableDestinationColumn().size()>0){
				 strFirstDestinationvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstDestinationValue().getText();
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstDestinationValue()), true , "***** test_14_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Destination Column *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_SelectMoreCountries"), true , "***** test_14_ In Estimated Landed Cost Tool under Shipments Panel \"Forward\" button is not Clicked *****");

				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_14_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 wait(4000);
				
				 objSoftAssert.assertEquals(strFirstDestinationvalue, objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Combobox_DestinationCountry"), "***** test_14_ In Estimated Landed Cost Tool under Shipments Panel, the value which was selected under \"Available Destination\" Column is not matching with the value displaying in \"Destination Country\" dropdown *****");
				 System.out.println("14. "+strFirstDestinationvalue +"," +objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Combobox_DestinationCountry"));
			
			 }
		 }catch(Exception e){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_14_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Available Destinations\" Column doesn't have data ";
		
		 }
			
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_14_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=80)
	 public void test_15_SelectMultipleValuesUnderAvailableOriginColumn(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreOriginCountries"), true, "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreOriginCountriesButton\" is not Clicked *****");
		 wait(4000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedOrigins().size()>0){
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedOriginfirstvalue()), true , "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Destination Column *****");
				 wait(5000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button1"), true , "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel \"Backward\" button is not Clicked *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");

			 }
		 }catch(Exception e){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Selected Origins\" Column doesn't have data ";
		
		 }
		
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreOriginCountries"), true, "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreOriginCountriesButton\" is not Clicked *****");
		 wait(4000);
		 if(objAdjunoLIMAEstimatedLandedCostPOM.getAvailableOrigins().size()>0){
			 wait(4000);
			 strFirstOriginvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstValue().getText();
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstValue()), true , "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Origin Column *****");
			 wait(4000);
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_SelectMoreCountries"), true , "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel \"Forward\" button is not Clicked *****");
			 wait(4000);
			 
			 strSecondOriginvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstValue().getText();
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstValue()), true , "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Origin Column *****");
			 wait(4000);
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_SelectMoreCountries"), true , "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel \"Forward\" button is not Clicked *****");
			 wait(5000);
			
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 wait(5000);
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyElementIsInReadOnlyMode(strEstimatedLandedCostFormName, "Textbox_OriginCountry"), true, "***** test_15_ In Estimated Landed Cost Tool under Shipments Panel \"Origin Country\" is not read only *****");
			 wait(5000);
			 System.out.println("1."+objAdjunoLIMAEstimatedLandedCostPOM.verifyElementIsInReadOnlyMode(strEstimatedLandedCostFormName, "Textbox_OriginCountry"));
			 String strOriginValue = objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_OriginCountry");
			 System.out.println("strOriginValue "+ strOriginValue);
			 String[] strOriginValues = strOriginValue.split(",");
			 System.out.println(strOriginValues.length);
			 for(int i=0;i<strOriginValues.length;i++){
				 if(strFirstOriginvalue.equalsIgnoreCase(strOriginValues[i])){
					 System.out.println(strFirstOriginvalue +","+strOriginValues[i]);
					
				 }else if(strSecondOriginvalue.equalsIgnoreCase(strOriginValues[i])){
					 System.out.println(strSecondOriginvalue +","+strOriginValues[i]);

				 }else{
					 strMessage = strMessage +" In Estimated Landed Cost Tool the Selected value is not displayed in \"Origin Country\" dropdown field under shipments panel ";
				 }
			 }
		 }else{
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Available Origins\" Column doesn't have data ";
		 }
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_15_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=85)
	 public void test_16_SelectMultipleValuesUnderAvailableDestinationColumn(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreDestinationCountries"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreDestinationCountriesButton\" is not Clicked *****");
		 wait(4000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedDestination().size()>0){
				 wait(4000);			
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedDestinationfirstValue()), true , "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Selected Destination Column *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button1"), true , "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel \"Backward\" button is not Clicked *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");

			 }
		 }catch(Exception e){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Selected Destination\" Column doesn't have data ";
		
		 }
	
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreDestinationCountries"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreDestinationCountriesButton\" is not Clicked *****");
		 wait(4000);
		 try{
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getAvailableDestination().size()>0){
				 wait(4000);
				 strFirstDestinationvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstDestinationValue().getText();
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstDestinationValue()), true , "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Destination Column *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_SelectMoreCountries"), true , "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel \"Forward\" button is not Clicked *****");
				 wait(4000);
				
				 strSecondDestinationvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstDestinationValue().getText();
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectFirstDestinationValue()), true , "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Available Destination Column *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_SelectMoreCountries"), true , "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel \"Forward\" button is not Clicked *****");
				 wait(4000);
				
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyElementIsInReadOnlyMode(strEstimatedLandedCostFormName, "Textbox_DestinationCountry"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel \"Destination Country\" is not read only *****");

				 wait(4000);
				 String strDestinationValue = objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_DestinationCountry");
				 System.out.println("strOriginValue "+ strDestinationValue);
				 String[] strDestinationValues = strDestinationValue.split(",");
				 System.out.println(strDestinationValues.length);
				 for(int i=0;i<strDestinationValues.length;i++){
					 if(strFirstDestinationvalue.equalsIgnoreCase(strDestinationValues[i])){
						 System.out.println(strFirstDestinationvalue +","+strDestinationValues[i]);
						
					 }else if(strSecondDestinationvalue.equalsIgnoreCase(strDestinationValues[i])){
						 System.out.println(strSecondDestinationvalue +","+strDestinationValues[i]);

					 }else{
						 strMessage = strMessage +" In Estimated Landed Cost Tool the Selected value is not displayed in \"Destination Country\" dropdown field under shipments panel ";
					 }
				 }
			 }
		 }catch(Exception e){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_16_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Available Destination\" Column doesn't have data ";
		
		 }		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_16_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=90)
	 public void test_17_RemoveSelectedValuesFromAvailableOriginColumn(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(5000);

		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreOriginCountries"), true, "***** test_17_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreOriginCountriesButton\" is not Clicked *****");
	      wait(4000);
	      try{
	    	  if(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedOrigins().size()>0){
	 			 wait(4000);
	 			 strFirstOriginvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectedOriginfirstvalue().getText();
	 			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedOriginfirstvalue()), true , "***** test_17_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Selected Origin Column *****");
	 			 wait(4000);
	 			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button1"), true , "***** test_17_ In Estimated Landed Cost Tool under Shipments Panel \"Backward\" button is not Clicked *****");
	 			 wait(4000);
	 		
	 			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_17_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
	 			 wait(5000);
	 			 
	 			 String strOriginValue = objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Combobox_OriginCountry");
	 			objSoftAssert.assertEquals(strFirstOriginvalue.contains(strOriginValue), false, "***** test_17_ In Estimated Landed Cost Tool under Shipments Panel \"Origin Country\" value id not removed *****");
	 	
	 		 }
	      }catch(Exception e){
	    	  objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_17_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 strMessage = strMessage+" In Estimated Landed Cost Tool under \"Selected Origins\" Column doesn't have data ";
			
	      }		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_17_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=95)
	 public void test_18_RemoveValuesFromSelectedDestinationColumn(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreDestinationCountries"), true, "***** test_18_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreDestinationCountriesButton\" is not Clicked *****");
		 wait(4000);
		try{
			if(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedDestination().size()>0){
				 wait(4000);
				 strFirstDestinationvalue = objAdjunoLIMAEstimatedLandedCostPOM.getSelectedDestinationfirstValue().getText();
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedDestinationfirstValue()), true , "***** test_18_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Selected Destination Column *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button1"), true , "***** test_18_ In Estimated Landed Cost Tool under Shipments Panel \"Backward\" button is not Clicked *****");
				 wait(4000);
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_18_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 wait(4000);		
				 String strDestinationValue = objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Combobox_DestinationCountry");
			
				 objSoftAssert.assertEquals(strFirstDestinationvalue.contains(strDestinationValue), false,"***** test_18_ In Estimated Landed Cost Tool under Shipments Panel \"Destination Country\" value is not removed *****");

			 }
		}catch(Exception e){
			objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_18_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
			strMessage = strMessage+" In Estimated Landed Cost Tool under \"Selected Destination\" Column doesn't have data ";
		
		}		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_18_ "+ strMessage +"*****" );
		objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=100)
	 public void test_27_CheckCostsPannelMandatoryFields(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 String validationMsgCostPerPiece = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_CostPerPiece");
		
		 if(!validationMsgCostPerPiece.equals(""))
		 {
			 if(validationMsgCostPerPiece.contains("&#39;"))
			 {
				 validationMsgCostPerPiece = validationMsgCostPerPiece.replace("&#39;", "'");
				 System.out.println("1."+validationMsgCostPerPiece);
				 objSoftAssert.assertEquals(validationMsgCostPerPiece.equalsIgnoreCase("> 'Cost per Piece' is a required field"), true, "*****test_27_'Cost Per Piece' is the required field message is not displayed*****");
			 }else{
				 strMessage = "***** test_27_ "+ strMessage + "In Estimated Landed Cost Tool under Costs Panel 'Cost per Piece' Field validation message is null *****";
			 }
		 }
		 
		 String validationMsgTotalPieces = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_TotalPieces");
		 
		 if(!validationMsgTotalPieces.equals(""))
		 {
			 if(validationMsgTotalPieces.contains("&#39;"))
			 {
				 validationMsgTotalPieces = validationMsgTotalPieces.replace("&#39;", "'");
				 System.out.println("2."+validationMsgTotalPieces);
				 objSoftAssert.assertEquals(validationMsgTotalPieces.equalsIgnoreCase("> 'Total Pieces' is a required field"), true, "*****test_27_'Total Pieces' is the required field message is not displayed*****");
			 }else{
				 strMessage = "***** test_25_ "+ strMessage + "In Estimated Landed Cost Tool under Costs Panel 'Total Pieces' Field validation message is null *****";
			 }
		 }
		 
		 String validationMsgPiecesPerCarton = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_PiecesPerCarton");
		 if(!validationMsgPiecesPerCarton.equals(""))
		 {
			 if(validationMsgPiecesPerCarton.contains("&#39;"))
			 {
				 validationMsgPiecesPerCarton = validationMsgPiecesPerCarton.replace("&#39;", "'");
				 System.out.println("3."+validationMsgPiecesPerCarton);

				 objSoftAssert.assertEquals(validationMsgPiecesPerCarton.equalsIgnoreCase("> 'Pieces per Carton' is a required field"), true, "*****test_27_'Pieces per carton' is the required field message is not displayed*****");
			 }else{
				 strMessage = "***** test_27_ "+ strMessage + "In Estimated Landed Cost Tool under Costs Panel 'Pieces per carton' Field validation message is null *****";
			 }
		 }
		 
		 String validationMsgCBMPerCarton = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_CBMPerCarton");
		 if(!validationMsgCBMPerCarton.equals(""))
		 {
			 if(validationMsgCBMPerCarton.contains("&#39;"))
			 {
				 validationMsgCBMPerCarton = validationMsgCBMPerCarton.replace("&#39;", "'");
				 System.out.println("4."+validationMsgCBMPerCarton);

				 objSoftAssert.assertEquals(validationMsgCBMPerCarton.equalsIgnoreCase("> 'CBM per Carton' is a required field"), true, "*****test_27_'CBM per carton' is the required field message is not displayed*****");
			 }else{
				 strMessage = "***** test_27_ "+ strMessage + "In Estimated Landed Cost Tool under Costs Panel 'CBM per carton' Field validation message is null *****";
			 }
		 }
		 
		 String validationMsgDutyRate = objAdjunoLIMAEstimatedLandedCostPOM.getValidationMessageFieldElement(strEstimatedLandedCostFormName, "Textbox_DutyRate");
		 if(!validationMsgDutyRate.equals(""))
		 {
			 if(validationMsgDutyRate.contains("&#39;"))
			 {
				 validationMsgDutyRate = validationMsgDutyRate.replace("&#39;", "'");
				 System.out.println("5."+validationMsgDutyRate);

				 objSoftAssert.assertEquals(validationMsgDutyRate.equalsIgnoreCase("> 'Duty Rate %' is a required field"), true, "*****test_27_'Duty Rate' is the required field message is not displayed*****");
			 }else{
				 strMessage = "***** test_27_ "+ strMessage + "In Estimated Landed Cost Tool under Costs Panel 'Duty Rate' Field validation message is null *****";
			 }
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  strMessage);
		 objSoftAssert.assertAll();
		 
	 }
	 
	 @Test(priority=102)
	 public void test_25_InspectCurrencyDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(5000);
		 lstCurrencyDropDownValues = new  ArrayList<String>();
		 lstCurrencyDropDownValues = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_CurrencyPerPiece");
		 System.out.println("25.Currency dropdown -- 45 -- "+lstCurrencyDropDownValues.size());
		 wait(5000);
		 if(lstCurrencyDropDownValues.size()>0){
			 strMessage = strMessage+ objAdjunoLIMAEstimatedLandedCostPOM.verifyDropDownValues(lstCurrencyDropDownValues, lstCurrencyDescription);
		 }else{
			 strMessage = strMessage+" In Estimated Landad Cost under Shipments Panel \"Currency\" DropDown doesn't have value";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_25_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }
	 
	 @Test(priority=105)
	 public void test_28_InspectTotalCtnsField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 ClearCostsPanelField();
		 if(!isNullOrBlank(strTotalPieces)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strTotalPieces, strEstimatedLandedCostFormName, "Textbox_TotalPieces"), true, "***** test_28_ In Estimated Landed Cost Tool under Costs pannel \"Total Pieces\" text field value is not entered *****");

		 }else{
			 strMessage = strMessage+" \"strTotalPiece\" value emplty in xml";
		 }
		 wait(2000);
		 if(!isNullOrBlank(strPiecesPerCarton)){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strPiecesPerCarton, strEstimatedLandedCostFormName, "Textbox_PiecesPerCarton"), true, "***** test_28_ In Estimated Landed Cost Tool under Costs pannel \"Pieces per carton\" text field value is not entered *****");

		 }else{
			 strMessage = strMessage+" \"strPiecesPerCarton\" value emplty in xml";
		 }
		 wait(4000);

		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_TotalCtns").equals(objAdjunoLIMAEstimatedLandedCostPOM.verifyTotalCtnsValue(strTotalPieces, strPiecesPerCarton)), true ,"***** test_28_ In Estimated Landed Cost Tool under Costs panel \"Total Ctns\" value is not equal to \"Total Pieces/Pieces per Carton\" *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyElementIsInReadOnlyMode(strEstimatedLandedCostFormName, "Textbox_TotalCtns"), true, "***** test_28_ In Estimated Landed Cost Tool under Shipments Panel \"Total Ctns\" field is not read only *****");

		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_28_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=110)
	 public void test_29_InspectTotalCBMField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 ClearShipmentsPanelField();
		 ClearCostsPanelField();
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreOriginCountries"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreOriginCountriesButton\" is not Clicked *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedOriginfirstvalue()), true , "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Selected Origin Column *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button1"), true , "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Backward\" button is not Clicked *****");
		 wait(4000);
	
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
				 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_AddMoreDestinationCountries"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"AddMoreDestinationCountriesButton\" is not Clicked *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getSelectedDestinationfirstValue()), true , "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel First Value is not Clicked under Selected Destination Column *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button1"), true , "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Backward\" button is not Clicked *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_Ok_Origins"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel 'OK' button is not Clicked *****");
		
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strModeValue, strEstimatedLandedCostFormName, "Combobox_Mode"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Mode\" Field Value is not Set *****");
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strvalidFormatDateEstimatedShipDate, strEstimatedLandedCostFormName, "Textbox_EstimatedShipDate"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Ship date\" Field Value is not Set *****");
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strvalidFormatDateIntendedDeliveryDate, strEstimatedLandedCostFormName, "Textbox_IntendedDeliveryDate"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Intended Delivery date\" Field Value is not Set *****");
		 wait(4000);
//		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getOriginCountryArrow()), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Origin Country\" Arrow is not clicked *****");
		 wait(12000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strOriginCountry, strEstimatedLandedCostFormName, "Combobox_OriginCountry"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Origin Country\" Field Value is not Set *****");
		 wait(3000);
//	     objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getDestinationCountryArrow()), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Destination Country\" Arrow is not clicked *****");
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDestinationCountry, strEstimatedLandedCostFormName, "Combobox_DestinationCountry"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Destination Country\" Field Value is not Set *****");
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDestination, strEstimatedLandedCostFormName, "Combobox_Destination"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Destination\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCostperPiece, strEstimatedLandedCostFormName, "Textbox_CostPerPiece"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Cost Per Piece\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strRetailPrice, strEstimatedLandedCostFormName, "Textbox_RetailPrice"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Retail Price\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDutyRate, strEstimatedLandedCostFormName, "Textbox_DutyRate"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Duty Rate\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strTotalPieces, strEstimatedLandedCostFormName, "Textbox_TotalPieces"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"Total Pieces\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strPiecesPerCarton, strEstimatedLandedCostFormName, "Textbox_PiecesPerCarton"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"PiecesPerCarton\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCBMPerCarton, strEstimatedLandedCostFormName, "Textbox_CBMPerCarton"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"CBMPerCarton\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCurrency, strEstimatedLandedCostFormName, "Combobox_CurrencyPerPiece"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"CurrencyPerPiece\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCurrency, strEstimatedLandedCostFormName, "Combobox_CurrencyRetail"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"CurrencyRetail\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCurrency, strEstimatedLandedCostFormName, "Combobox_ResultsCurrency"), true, "***** test_29_ In Estimated Landed Cost Tool under Shipments Panel \"ResultsCurrency\" Field Value is not Set *****");

		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_TotalCBM").contains(objAdjunoLIMAEstimatedLandedCostPOM.verifyTotalCBMValue(objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_TotalCtns"), strCBMPerCarton)), true,"***** test_29_ In Estimated Landed Cost Tool under Costs Panel \"Total CBM\" value is not equal to \"Total Ctns * CBM Per Carton\" *****");
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyElementIsInReadOnlyMode(strEstimatedLandedCostFormName, "Textbox_TotalCBM"), true, "***** test_29_ In Estimated Landed Cost Tool under Costs Panel \"Total CBM\" field is not read only *****");

		 objSoftAssert.assertAll();
		 
	 }
	 
	 @Test(priority=115,dependsOnMethods="test_29_InspectTotalCBMField")
	 public void test_30_VerifyEstimatedLandedCostGridPresent(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage ="";
		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost"), true ,"***** test_30_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Landed Cost Grid\" is not populated *****");
		 
		 ArrayList<String> list = new ArrayList<String>();
		 list = objAdjunoLIMAEstimatedLandedCostPOM.getCaptionsList(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost");
		
		 strMessage = objAdjunoLIMAEstimatedLandedCostPOM.verifyCaptionsONGrid(list,nProductDetails, 11);

		 if (strMessage.equals("")) {

		 } else {
			 strMessage = "In Estimated Landed Cost under estimated landed cost Grid "+ strMessage;
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 30 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
		
	 }
	 
	 @Test(priority=120)
	 public void test_19_ClickMoreOptionButton(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Button_ShowMoreOptions")){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_ShowMoreOptions"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"Show More Options\" button is not Clicked *****");
			 wait(5000); 
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_OriginPort"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"Origin Port\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_DestinationPort"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"Destination Port\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_Vendor"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"Vendor\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_Loading"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"Loading\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_Equipment"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"Equipment\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Textbox_PONumber"), true, "***** test_19_ In Estimated Landed Cost Tool under Shipments Panel \"PO Number\" Textbox field is not displayed *****");
			 
		 }else{
			 strMessage = strMessage+" In Estimated Landed Cost Tool under Shipments Panel \"Show more options\" is not present ";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_19_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }
	 
	 @Test(priority=125)
	 public void test_20_InspectOriginPortDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(4000);
		 lstOriginPortToolDropDownValues = new  ArrayList<String>();
		 lstOriginPortToolDropDownValues = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_OriginPort");
		 System.out.println("20.Origin dropdown -"+lstOriginPortToolDropDownValues.size());
		 wait(4000);
		 if(lstOriginPortToolDropDownValues.size()>0){
			
			 strMessage = strMessage+ objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(catalogOriginPortList, lstOriginPortToolDropDownValues);
		 }else{
			 strMessage = strMessage+" In Estimated Landad Cost under Shipments Panel \"Origin Port\" DropDown doesn't have value";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_20_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }
	 
		 
	 @Test(priority=130)
	 public void test_22_VerifyVendorDropDownValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(4000);
		 lstVendorDropdownValue = new  ArrayList<String>();
		 lstVendorDropdownValue = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_Vendor");
		 wait(4000);
		 if(lstVendorDropdownValue.size()>0){
			 System.out.println("22.a "+lstVendorDropdownValue.size());
			 strMessage = strMessage + objAdjunoLIMAEstimatedLandedCostPOM.verifyDropDownValues(lstVendorDropdownValue, lstSupplierDescription);

		 }else{
			 strMessage = strMessage+ "In Estimated Landed Cost Tool under Shipment Panel \"Vendor\" dropdown doesn't have data";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_22_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }
	 
	 @Test(priority=135)
	 public void test_23_VerifyEquipmentDropDownValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(4000);
		 lstEquipmentDropdownValue = new  ArrayList<String>();
		 lstEquipmentDropdownValue = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_Equipment");
		 wait(4000);
		 if(lstEquipmentDropdownValue.size()>0){
			 System.out.println("23.a "+lstEquipmentDropdownValue.size());
			 strMessage = strMessage + objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(equipmentList, lstEquipmentDropdownValue);

		 }else{
			 strMessage = strMessage+ "In Estimated Landed Cost Tool under Shipment Panel \"Equipment\" dropdown doesn't have data";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_23_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }
	 
	 @Test(priority=140)
	 public void test_24_VerifyLoadingDropDownValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(4000);
		 lstLoadingDropdownValue = new  ArrayList<String>();
		 lstLoadingDropdownValue = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_Loading");
		 System.out.println("24.lstLoadingDropdownValue-- "+lstLoadingDropdownValue.size());
		 wait(4000);
		 if(lstLoadingDropdownValue.size()>0){
			 strMessage = objAdjunoLIMAEstimatedLandedCostPOM.verifyCaptionsONGrid1(lstLoadingDropdownValue,nLoadingDetails, 2);

			 if (strMessage.equals("")) {

			 } else {
				 strMessage = "In Estimated Landed Cost under estimated landed cost Grid "+ strMessage;
			 }
			 
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool under Shipment Panel \"Loading\" dropdown doesn't have data";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_24_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }

	 @Test(priority=145)
	 public void test_21_InspectDestinationPortDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(10000);
		 lstDestinationPortDropDownValues = new  ArrayList<String>();
		 lstDestinationPortDropDownValues = objAdjunoLIMAEstimatedLandedCostPOM.getDropdownValues(strEstimatedLandedCostFormName, "Combobox_DestinationPort");
		 
		 System.out.println("21.destination dropdown -- "+lstDestinationPortDropDownValues.size());
		 wait(4000);
		 if(lstDestinationPortDropDownValues.size()>0){
			 strMessage = strMessage+ objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(catalogDestinationPortList, lstDestinationPortDropDownValues);
		 }else{
			 strMessage = strMessage+" In Estimated Landad Cost under Shipments Panel \"Destination Port\" DropDown doesn't have value";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_21_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();	
	 }
	 
	 @Test(priority=150)
	 public void test_26_ClickLessOptionButton(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Button_HideMoreOptions")){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_HideMoreOptions"), true, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"Hide More Options\" button is not Clicked *****");
			 wait(4000); 
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_OriginPort"), false, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"Origin Port\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_DestinationPort"), false, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"Destination Port\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_Vendor"), false, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"Vendor\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_Loading"), false, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"Loading\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Combobox_Equipment"), false, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"Equipment\" dropdown field is not displayed *****");
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Textbox_PONumber"), false, "***** test_26_ In Estimated Landed Cost Tool under Shipments Panel \"PO Number\" Textbox field is not displayed *****");
			 
		 }else{
			 strMessage = strMessage+" In Estimated Landed Cost Tool under Shipments Panel \"Hide more options\" is not present ";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_26_"+ strMessage +" *****" );
		 objSoftAssert.assertAll();
	 }
			
	 @Test(priority=160)
	 public void test_31_VerifyEstimatedLandedCostGridnotPresent(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_DutyRate"), true,"***** test_31_ In Estimated Landed Cost Tool under Costs Panel \"Duty Rate\" field is not cleared *****");
		 wait(6000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_CBMPerCarton"), true,"***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"CBM per Carton\" field is not cleared *****");
		 wait(6000);
		 System.out.println("31. "+objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Hyperlink_Tariff"));
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Hyperlink_Tariff"), false ,"***** test_31_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Landed Cost Grid\" is Still Populating *****");

		 objSoftAssert.assertAll();

	 }
	 
	 @Test(priority=165)
	 public void test_32_VerifyTotalCBMFieldwithWLH(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(6000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Textbox_CBMPerCarton"), true,"***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"CBM per Carton\" field is not cleared *****");
		 wait(4000);		
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDutyRate, strEstimatedLandedCostFormName, "Textbox_DutyRate"), true, "***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"Duty Rate\" Field Value is not Set *****");
		 wait(4000);		 
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strCurrency, strEstimatedLandedCostFormName, "Combobox_ResultsCurrency"), true, "***** test_32_ In Estimated Landed Cost Tool under Shipments Panel \"ResultsCurrency\" Field Value is not Set *****");
		 wait(2000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDimensionsL, strEstimatedLandedCostFormName, "Textbox_Dimensions_L"), true, "***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"Dimensions_L\" Field Value is not Set *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDimensionsW, strEstimatedLandedCostFormName, "Textbox_Dimensions_W"), true, "***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"Dimensions_W\" Field Value is not Set *****");
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strDimensionsH, strEstimatedLandedCostFormName, "Textbox_Dimensions_H"), true, "***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"Dimensions_H\" Field Value is not Set *****");
		 wait(4000);
		 String strCBMperCarton=objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_CBMPerCarton");
		 String StrCBMperCartonvalue=objAdjunoLIMAEstimatedLandedCostPOM.verifyCBMperCarton(strDimensionsL, strDimensionsW, strDimensionsH);
		 System.out.println(strCBMperCarton+" ==++StrCBMperCartonvalue "+StrCBMperCartonvalue);
		
		 String strCBMValue = objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(StrCBMperCartonvalue, ".######");
	//	 String strFieldCBMCartinValue= objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(strCBMperCarton, ".######");
		 System.out.println(strCBMValue +" =======is not matching with "+ strCBMperCarton);
		 
		 objSoftAssert.assertEquals(strCBMValue.equals(strCBMperCarton), true ,"***** test_32_ In Estimated Landed Cost Tool under Cost Panel \"CBM per Carton\" Field Value is not equal to \"L*W*H\"  *****");
	
		 wait(4000);
		 String totalCBMValue =objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_TotalCBM");
		 wait(2000);
		 String verifyTotalCBMValue =objAdjunoLIMAEstimatedLandedCostPOM.verifyTotalCBMValue(objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_TotalCtns"), objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "Textbox_CBMPerCarton"));
		 String strtotalCBMValue = objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(verifyTotalCBMValue, ".##");

		 objSoftAssert.assertEquals(totalCBMValue.equals(strtotalCBMValue), true,"***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"total CBM\" value is not equal to \"Total Ctns * CBM Per Carton\" *****");
		 System.out.println(totalCBMValue +"------ is not matching with "+strtotalCBMValue);
		 
		 wait(4000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyElementIsInReadOnlyMode(strEstimatedLandedCostFormName, "Textbox_TotalCBM"), true, "***** test_32_ In Estimated Landed Cost Tool under Costs Panel \"Total CBM\" field is not read only *****");

		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost"), true ,"***** test_32_ In Estimated Landed Cost Tool under Shipments Panel \"Estimated Landed Cost Grid\" is not populated *****");
		 
		 ArrayList<String> list = new ArrayList<String>();
		 list = objAdjunoLIMAEstimatedLandedCostPOM.getCaptionsList(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost");
		
		 strMessage = objAdjunoLIMAEstimatedLandedCostPOM.verifyCaptionsONGrid(list,nProductDetails, 11);

		 if (strMessage.equals("")) {

		 } else {
			 strMessage = "In Estimated Landed Cost under estimated landed cost Grid "+ strMessage;
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 32 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
		 
	 }
	 
	 
	 @Test(priority=170)
	 public void test_33_VerifySeafreightTariffVersionPresent(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(5000);
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Hyperlink_Tariff")){
			 
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"SeaFreightTariff Hyperlink\" is not present";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 33 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
	 
	 }

	 @Test(priority=175)
	 public void test_36_CheckSaveItemsButtonDisable(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 wait(5000);
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Button_SaveSelectedElements")){
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyButtonisDisable(strEstimatedLandedCostFormName, "Button_SaveSelectedElements"), true, "***** test_36_ In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"Save Button\" is not Disable ");
		 
			 System.out.println(objAdjunoLIMAEstimatedLandedCostPOM.verifyButtonisDisable(strEstimatedLandedCostFormName, "Button_SaveSelectedElements"));
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"Save Button\" is not present ";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 36 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=180)
	 public void test_40_VerifyMarginFieldValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")){
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")>0){
				 String StrFirstMarginValue = objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", 0, "Textbox_Margin");
				 String StrFirstProductLandedCostValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", 0, "Textbox_ProductLandedCost");
				 String StrFirstMarginValue1=objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(StrFirstMarginValue, ".#");
				 String StrFirstProductLandedCostValue1=objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(StrFirstProductLandedCostValue, ".#");

				 System.out.println("40. "+StrFirstMarginValue1+" and "+objAdjunoLIMAEstimatedLandedCostPOM.getDifference(strRetailPrice, StrFirstProductLandedCostValue1));
				 objSoftAssert.assertEquals(StrFirstMarginValue1.equals(objAdjunoLIMAEstimatedLandedCostPOM.getDifference(strRetailPrice, StrFirstProductLandedCostValue1)), true,"***** Test ID : 40 - In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"Margin\" field value is not equal to \"difference between Retail Price and Product Landed Cost\" *****");
				 
			 }else{
				 strMessage = strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"No rows are Populated\" ";
			 }
			
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool \"Estimated Landed Cost Grid\" is not displayed ";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 40 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=185)
	 public void test_42_VerifyProductCostFieldValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")){
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")>0){
				 String StrProductCostGridValue = objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", 0, "Textbox_ProductCost");
							
				 objSoftAssert.assertEquals(StrProductCostGridValue.equals(strCostperPiece), true,"***** Test ID : 42 - In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"Product Cost\" field value is not equal to \"The value which was entered in Cost Per Piece\" field value *****");
				 System.out.println(StrProductCostGridValue +"--------------------"+strCostperPiece);
					
			 }else{
				 strMessage = strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"No rows are Populated\" ";
			 }

		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool \"Estimated Landed Cost Grid\" is not displayed ";
		 }	
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 42 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=190)
	 public void test_43_VerifyProductLandedCostFieldValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")){
			 if(objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")>0){
				 String StrProductLandedCostGridValue = objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", 0, "Textbox_ProductLandedCost");
				 
				 double strProductShippingCost= Double.parseDouble(objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", 0, "Textbox_ProductShippingCost"));
				 double strProductCost= Double.parseDouble(objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", 0, "Textbox_ProductCost"));
				 String strCalculateProductLandedCost= new Double(strProductShippingCost+strProductCost).toString();
				 String StrProductLandedCostGridValue1=objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(StrProductLandedCostGridValue, ".#");
				 String strCalculateProductLandedCost1=objAdjunoLIMAEstimatedLandedCostPOM.getDecimalValues(strCalculateProductLandedCost, ".#");
				 
				 objSoftAssert.assertEquals(StrProductLandedCostGridValue1.equals(strCalculateProductLandedCost1), true,"***** Test ID : 43 - In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"Product Landed Cost\" field value is not equal to \"Product Shipping Cost + Product Cost\" value *****");
				 System.out.println(StrProductLandedCostGridValue1 +"----------43----------"+strCalculateProductLandedCost1);

			 }else{
				 strMessage = strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"No rows are Populated\" ";
			 }
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool \"Estimated Landed Cost Grid\" is not displayed ";
		 }	
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 43 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
	 }
	 
	 @Test(priority=195)
	 public void test_44_VerifyEquipmentFieldValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 
		 wait(3000);
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Button_ShowMoreOptions")){
						 
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Button_ShowMoreOptions"), true, "***** test_44_ In Estimated Landed Cost Tool under Shipments Panel \"Show More Options\" button is not Clicked *****");
			 wait(3000);
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_Equipment"), true,"***** test_44_ In Estimated Landed Cost Tool under Shipments Panel \"Equipment\" field is not cleared *****");
			 wait(3000);
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strEquipment, strEstimatedLandedCostFormName, "Combobox_Equipment"), true,"***** test_44_ In Estimated Landed Cost Tool under Shipments Panel \"Equipment\" field value is not entered *****");
			 wait(5000);
			 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")){
				 if(objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")>0){
					 strMessage = strMessage + objAdjunoLIMAEstimatedLandedCostPOM.verifySearchValue(strEquipment, objAdjunoLIMAEstimatedLandedCostPOM.getLstEquipmentGrid());

				 }else{
					 strMessage = strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"No rows are Populated\" ";
				 }
				 
			 }else{
				 strMessage = strMessage+"In Estimated Landed Cost Tool \"Estimated Landed Cost Grid\" is not displayed ";
			 }	
		 }else{
			 strMessage = strMessage+" In Estimated Landed Cost Tool under Shipments Panel \"Show more options\" is not present ";
		 }
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clearInputField(strEstimatedLandedCostFormName, "Combobox_Equipment"), true,"***** test_44_ In Estimated Landed Cost Tool under Shipments Panel \"Equipment\" field is not cleared *****");
		 wait(3000);
		 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.setFieldValue(strEquipmentAll, strEstimatedLandedCostFormName, "Combobox_Equipment"), true,"***** test_44_ In Estimated Landed Cost Tool under Shipments Panel \"Equipment\" field value is not entered *****");
		 wait(5000);
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 44 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
		 
		 
	 }
	 
	 @Test(priority=200)
	 public void test_34_ClickonSeafreightTariffVersion() throws AWTException{
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Hyperlink_Tariff")){
			 hyperlinkSeaFreight =objAdjunoLIMAEstimatedLandedCostPOM.getHyperlink().getText();
			 System.out.println(hyperlinkSeaFreight);
			 
			 String[] ToolVersion =hyperlinkSeaFreight.split(": ");
			 
			 System.out.println(ToolVersion[0] +","+ ToolVersion[1]);
			 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Hyperlink_Tariff"), true,"***** test_34_ In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"SeaFreightTariff Hyperlink\" is not clicked *****");
			 wait(5000);
			 String strTariffVersion = objAdjunoLIMAEstimatedLandedCostPOM.getFieldValue(strEstimatedLandedCostFormName, "TariffVersion");
			 System.out.println("123 - "+strTariffVersion);
			 wait(5000);
			 objSoftAssert.assertEquals(strTariffVersion.equals(ToolVersion[1]), true,"***** test_34_ In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"Tariff version\" is not matching with the tariff version which was found under \"Tariff Version\" page *****");
			 System.out.println(strTariffVersion +","+ToolVersion[1]);
			 wait(20000);
			 
			 getCountryDropDownVanues();
	
			 Actions action=new Actions(driver);
			 action.sendKeys(Keys.ESCAPE).perform();
			
			
		}else{
			strMessage =strMessage+"In Estimated Landed Cost Tool under Estimated Landed Cost Grid \"SeaFreightTariff Hyperlink\" is not present";
		}
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 34 - "+ strMessage +" *****");
		 objSoftAssert.assertAll();
	  }
	 
	 public void getCountryDropDownVanues(){
		 lstOriginCountryDropDownValues=new ArrayList<String>();
		 lstDestinationCountryDropDownValues=new ArrayList<String>();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 //Origin Country
		 long rowCount= objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts");
		 for(int i=0;i< rowCount;i++){
			String originCountryValue= objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_OriginCountry");
			
			lstOriginCountryDropDownValues.add(originCountryValue);
			System.out.println(lstOriginCountryDropDownValues.get(i));
		 }
		 System.out.println("------------------------------------------------");
		 System.out.println(lstOriginCountryDropDownValues.size());
		 
		 //Destination Country 
		 for(int i=0;i< rowCount;i++){
				String destinationCountryValue= objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_DestinationCountry");
				
				lstDestinationCountryDropDownValues.add(destinationCountryValue);
				System.out.println(lstDestinationCountryDropDownValues.get(i));
			 }
			 System.out.println(lstDestinationCountryDropDownValues.size());
 
	 }
	 
	 @Test(priority=205)
	 public void test_4_InspectOriginCountryDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 System.out.println("lstOriginCountryValues Sise is "+ lstOriginCountryValues.size());
		 if(lstOriginCountryValues.size()>0){
				strMessage=strMessage+objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(lstOriginCountryDropDownValues, lstOriginCountryValues);

		 }else{
			 strMessage = strMessage +"In Estimated Landed Cost Tool under Shipments Panel \"Origin Country\" Drop Down doesn't have data.";
		 }
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** test_4_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=210)
	 public void test_5_InspectDestinationCountryDropdown(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		
		 System.out.println("lstDestinationCountryValues Size is "+lstDestinationCountryValues.size());
		
		 if(lstDestinationCountryValues.size()>0){
			strMessage=strMessage+objAdjunoLIMAEstimatedLandedCostPOM.verifyCatalogData(lstDestinationCountryDropDownValues, lstDestinationCountryValues);
		 
		 }else{
			 strMessage = strMessage +"In Estimated Landed Cost Tool under Shipments Panel \"Destination Country\" Drop Down doesn't have data.";
		 }
	
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** Test_5_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
	
	 @Test(priority=215)
	 public void test_39_VerifyRankFieldValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 ArrayList<String> lstMarginValues=new ArrayList<String>();
		 ArrayList<String> lstoldMarginValues=new ArrayList<String>();
		 ArrayList<String> lstRankValues=new ArrayList<String>();
		 
		 wait(4000);
		 if(objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")>0){
			 long rowCount= objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost");
			 
				 lstMarginValues= objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", rowCount, "Textbox_Margin");
				 lstRankValues= objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost", rowCount, "ImportanceRank");

				 lstoldMarginValues.addAll(lstMarginValues);
				 Collections.sort(lstMarginValues);
				 System.out.println("after sorting "+lstMarginValues);
				  
				 Collections.reverse(lstMarginValues);
				 System.out.println("after reversing "+lstMarginValues);
				 
				 if(lstMarginValues.get(0).equalsIgnoreCase(lstMarginValues.get(1))){
					 System.out.println("***** Test ID - 39. First and second margin values are same in Estimated Landed Cost *****");
				 }else{
				//	 String firstMarginValue=lstMarginValues.get(0);
					 System.out.println("30.firstMarginValue "+lstMarginValues.get(0));
					
					 System.out.println("39.rank value - "+objAdjunoLIMAEstimatedLandedCostPOM.verifyRankValue(lstMarginValues.get(0), lstoldMarginValues, lstRankValues));
					 
					 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.verifyRankValue(lstMarginValues.get(0), lstoldMarginValues, lstRankValues).equalsIgnoreCase("011")||objAdjunoLIMAEstimatedLandedCostPOM.verifyRankValue(lstMarginValues.get(0), lstoldMarginValues, lstRankValues).equalsIgnoreCase("1"), true, "***** Test id-39- In Estimated Landed Cost Under Estimated Landed Cost Grid \"The First highest Margin Value\" is not having Rank \"1\"");
				 
				 }
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"No Rows Are Populated\"";
		 }

		 objSoftAssert.assertAll();
	 
	 }
	 
	 @Test(priority=220)
	 public void test_41_VerifyProductShippingCostFieldValue(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoLIMAEstimatedLandedCostPOM = new AdjunoLIMAEstimatedLandedCostPOM(driver);
		 String strMessage="";
		 double tarifftoolFCLFreightCostsValue = 0;
		 double tarifftoolFCLLandedCostsValue = 0;
		 double tarifftoolFCLHaulageCostsValue = 0;
		 fclFreightCostOriginCountryList=new ArrayList<String>();
		 fclFreightCostDestinationCountryList=new ArrayList<String>();
		 fclFreightCostOriginPortList=new ArrayList<String>();
		 fclFreightCostDestinationPortList=new ArrayList<String>();
		 fclLandedCostChargeDescriptionList=new ArrayList<String>();
	 
		 if(objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_EstimatedLandedCost")>0){

			 EstimatedLandedCost estGridValues =new EstimatedLandedCost(objAdjunoLIMAEstimatedLandedCostPOM.getLstGridRankValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridMarginValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridProductLandedCostValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridProductShippingCostValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridProductCostValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridTransitTimeValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridEstDeliveryDateValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridOriginPortValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridModeValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridDestinationPortValue().get(0).getText(), objAdjunoLIMAEstimatedLandedCostPOM.getLstGridEquipmentValue().get(0).getText());
			 			 
			 if(objAdjunoLIMAEstimatedLandedCostPOM.checkDoesElementExist(strEstimatedLandedCostFormName, "Hyperlink_Tariff")){
				 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButton(strEstimatedLandedCostFormName, "Hyperlink_Tariff"), true, "***** Test_41_ In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"Hyperlink_Tariff\" is not clicked *****");
				 wait(4000);
				 System.out.println("41. "+objAdjunoLIMAEstimatedLandedCostPOM.getStrFCLFreightCosts().isDisplayed());
				 if(!objAdjunoLIMAEstimatedLandedCostPOM.getStrFCLFreightCosts().isDisplayed()){
					 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getFCLFreightCosts()), true, "***** Test_41_ In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"FCL Freight Costs\" button is not clicked *****");

				 }
				 //FCL Freight Cost
				 wait(4000);
				 long rowCountFreightCosts=objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts");
				 System.out.println("41.FCL Freight Costs Row Count - "+rowCountFreightCosts);
				 fclFreightCostOriginCountryList= objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", rowCountFreightCosts, "FCLFreightCosts_OriginCountry");
				 fclFreightCostDestinationCountryList= objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", rowCountFreightCosts, "FCLFreightCosts_DestinationCountry");
				 fclFreightCostOriginPortList= objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", rowCountFreightCosts, "FCLFreightCosts_OriginPort");
				 fclFreightCostDestinationPortList= objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", rowCountFreightCosts, "FCLFreightCosts_DestinationPort");
				 
				 for(int i=0;i<fclFreightCostOriginCountryList.size();i++){
					 if(fclFreightCostOriginCountryList.get(i).equals(strOriginCountry) && fclFreightCostDestinationCountryList.get(i).equals(strDestinationCountry) && fclFreightCostOriginPortList.get(i).equals(estGridValues.getOriginPort()) && fclFreightCostDestinationPortList.get(i).equals(estGridValues.getDestinationPort())){
						 System.out.println("-a-"+fclFreightCostOriginCountryList.get(i)+" is matching*** "+strOriginCountry );
						 System.out.println("-a-"+fclFreightCostDestinationCountryList.get(i)+" is matching*** "+strDestinationCountry );
						 System.out.println("-a-"+fclFreightCostOriginPortList.get(i)+" is matching*** "+estGridValues.getOriginPort() );
						 System.out.println("-a-"+fclFreightCostDestinationPortList.get(i)+" is matching*** "+estGridValues.getDestinationPort() );
						 ArrayList<String> list = new ArrayList<String>();
							
						 list = objAdjunoLIMAEstimatedLandedCostPOM.getCaptionsList(strEstimatedLandedCostFormName,"Grid_FCLFreightCosts");
						 for(int j=0;j<list.size();j++){
							 if(estGridValues.getEquipment().equals(list.get(j))){
								 if(estGridValues.getEquipment().equals("20FT")){
									 ContainerValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_20ft");

								 }else if(estGridValues.getEquipment().equals("40FT")){
									ContainerValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_40ft");

								 }else if(estGridValues.getEquipment().equals("40HC")){
									 ContainerValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_40hc");

								 }else if(estGridValues.getEquipment().equals("45FT")){
									 ContainerValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_45ft");

								 }
							 }
						 }
						 System.out.println("a-"+ContainerValue);
						 tariffToolCurrencyValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLFreightCosts", i, "FCLFreightCosts_Currency");
						 tarifftoolFCLFreightCostsValue =objAdjunoLIMAEstimatedLandedCostPOM.currencyConversion(tariffToolCurrencyValue, strCurrency, ContainerValue);
						 System.out.println("41- "+tarifftoolFCLFreightCostsValue);
					 
					 }
				 }
				 
				 //FclLanded Costs grid value
				 wait(4000);
				 if(!objAdjunoLIMAEstimatedLandedCostPOM.getStrFCLLandSideCosts().isDisplayed()){
					 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getFCLLandsideCosts()), true, "***** Test_41_ In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"FCL Landside Costs\" button is not clicked *****");
				 }
				 wait(4000);
				 long rowCountLandSideCosts=objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_FCLLandsideCosts");
				 System.out.println("41.FCL LandSide Costs Row Count - "+rowCountLandSideCosts);
				 fclLandedCostChargeDescriptionList = objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLLandsideCosts", rowCountLandSideCosts, "FCLLandsideCharges_ChargeType");
				 fclLandedCostDestinationCountryList = objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLLandsideCosts", rowCountLandSideCosts, "FCLLandsideCharges_DestinationCountry");
				 fclLandedCostDestinationPortList = objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLLandsideCosts", rowCountLandSideCosts, "FCLLandsideCharges_DestinationPort");

				 for(int i=0;i<fclLandedCostChargeDescriptionList.size();i++){
					 if(fclLandedCostChargeDescriptionList.get(i).equalsIgnoreCase("FCL Terminals") && fclLandedCostDestinationCountryList.get(i).equalsIgnoreCase(strDestinationCountry) && fclLandedCostDestinationPortList.get(i).equalsIgnoreCase(estGridValues.getDestinationPort())){
						 System.out.println("-b-"+fclLandedCostChargeDescriptionList.get(i)+" is matching*** "+"\"FCL Terminals\"" );
						 System.out.println("-b-"+fclLandedCostDestinationCountryList.get(i)+" is matching*** "+strDestinationCountry );
						 System.out.println("-b-"+fclLandedCostDestinationPortList.get(i)+" is matching*** "+estGridValues.getDestinationPort() );
						ContainerValueLandedCost = objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLLandsideCosts", i, "FCLLandsideCharges_Cost");
						tariffToolFCLLandedCostsCurrencyValue=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLLandsideCosts", i, "FCLLandsideCharges_Currency");
						 System.out.println("b--"+ContainerValueLandedCost);
						 tarifftoolFCLLandedCostsValue =objAdjunoLIMAEstimatedLandedCostPOM.currencyConversion(tariffToolFCLLandedCostsCurrencyValue, strCurrency, ContainerValueLandedCost);
						 System.out.println("41- "+tarifftoolFCLLandedCostsValue);
					}
					 /* else{
						 strMessage = strMessage+"In Estimated Landed Cost Under FCL LandSide Costs Grid \" FCL Terminals\" Charge value is not present" ;
					 }*/

				 }
			
				 //FCLHualageCost
				 wait(4000);
				 if(!objAdjunoLIMAEstimatedLandedCostPOM.getStrFCLHaulageCosts().isDisplayed()){
					 objSoftAssert.assertEquals(objAdjunoLIMAEstimatedLandedCostPOM.clickButtonUsingWebElement(objAdjunoLIMAEstimatedLandedCostPOM.getFCLHaulageCosts()), true, "***** Test_41_ In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"FCL Haulage Costs\" button is not clicked *****");
				 }
				 long rowCountHaulageCosts=objAdjunoLIMAEstimatedLandedCostPOM.getNoOfRowsinGrid(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts");
				 System.out.println("41.FCL Haulage Costs Row Count - "+rowCountHaulageCosts);
				 fclHaulageCostExportList = objAdjunoLIMAEstimatedLandedCostPOM.getListGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts", rowCountHaulageCosts, "FCLHaulageCosts_ExPort");
				
				 for(int i=0;i<fclHaulageCostExportList.size();i++){
					 if(fclHaulageCostExportList.get(i).equalsIgnoreCase(estGridValues.getDestinationPort())){
						 tariffToolFCLHaulageCostsCurrencyValue = objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts", i, "FCLHaulageCosts_Currency");
						 
						 ArrayList<String> list = new ArrayList<String>();
							
						 list = objAdjunoLIMAEstimatedLandedCostPOM.getCaptionsList(strEstimatedLandedCostFormName,"Grid_FCLHaulageCosts");
						 for(int j=0;j<list.size();j++){
							 if(estGridValues.getEquipment().equals(list.get(j))){
								 if(estGridValues.getEquipment().equals("20FT")){
									 ContainerValueHaulageCost=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts", i, "FCLHaulageCosts_20FT");

								 }else if(estGridValues.getEquipment().equals("40FT")){
									 ContainerValueHaulageCost=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts", i, "FCLHaulageCosts_40FT");

								 }else if(estGridValues.getEquipment().equals("40HC")){
									 ContainerValueHaulageCost=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts", i, "FCLHaulageCosts_40HC");

								 }else if(estGridValues.getEquipment().equals("45FT")){
									 ContainerValueHaulageCost=objAdjunoLIMAEstimatedLandedCostPOM.getGridCellValue(strEstimatedLandedCostFormName, "Grid_FCLHaulageCosts", i, "FCLHaulageCosts_45FT");

								 }
							 }
						 }
					 }
				 }
				 System.out.println("c--"+ContainerValueHaulageCost);
				 tarifftoolFCLHaulageCostsValue =objAdjunoLIMAEstimatedLandedCostPOM.currencyConversion(tariffToolFCLHaulageCostsCurrencyValue, strCurrency, ContainerValueHaulageCost);
				 System.out.println("41- "+tarifftoolFCLHaulageCostsValue);
				 double totalTariffCostValue = objAdjunoLIMAEstimatedLandedCostPOM.getAddDoubleValues(tarifftoolFCLFreightCostsValue, tarifftoolFCLLandedCostsValue, tarifftoolFCLHaulageCostsValue);
				 System.out.println("Total Tariff Value"+totalTariffCostValue); 
				
				 wait(4000);
				 double shippingCostValue=objAdjunoLIMAEstimatedLandedCostPOM.getShippingCostValuae(estGridValues.getEquipment(), totalTariffCostValue, objAdjunoLIMAEstimatedLandedCostPOM.getReadonlyFieldValue("LIMA_Finance_EstimatedLandedCost_Tool_Textbox_TotalCBM"), strTotalPieces);
				 System.out.println("41-f- ShippingCost Value"+shippingCostValue);
			 }else{
				 strMessage = strMessage+" In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"Hyperlink_Tariff\" is not present ";
			 }
			 
		 }else{
			 strMessage = strMessage+"In Estimated Landed Cost Tool Under Estimated Landed Cost Grid \"No Rows Are Populated\"";
		 }
		 wait(4000);
		 Actions action=new Actions(driver);
		 action.sendKeys(Keys.ESCAPE).perform();
				 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** Test_41_ "+ strMessage +"*****" );
		 objSoftAssert.assertAll();
	 }
		
	 @AfterTest
	 public void closeBrwser(){
		 driver.close();
	 }
	 
}
