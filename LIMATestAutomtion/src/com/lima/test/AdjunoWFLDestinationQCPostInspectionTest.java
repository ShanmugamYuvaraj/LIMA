package com.lima.test;

import java.text.ParseException;
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

import com.lima.dto.DestinationQCPostInspection;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoWFLDestinationQCPostInspectionPOM;

public class AdjunoWFLDestinationQCPostInspectionTest {
	 WebDriver driver;
	    
	 long nPage_Timeout = 0;
	 String strTestURL;
	   
	 String strLIMAUserName;
	 String strLIMAPassword;
	 String strCustomerUserName;
	 String strCustomerPassword;
	 String strCatalogFormName;
	 String strCatalogPageTitle;
	 String strDestinationQCPostInspectionFormName;
	 String strDestinationQCPostInspectionPageTitle;
	 String strDestinationQCPostInspectionGridFormName;
	 String strDestinationQCPostInspectionCompleteValidationMsgFormName;
	 
	 AdjunoLIMALibrary objAdjunoLIMALibrary;
	 AdjunoUILibrary objAdjunoUILibrary;
	 AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	 
	 AdjunoWFLDestinationQCPostInspectionPOM objAdjunoWFLDestinationQCPostInspectionPOM;
	 
	 NodeList searchParams;
	 NodeList nlSearchParamsDestinationQCPostInspection;
	 NodeList nlSearchParamsCatalog;
	 
	 String strSupplier ="";
	 String strCustomer ="";
	 String strCountry ="";
	 String strLoCode ="";
	 String strMode ="";
	 String strVessel ="";
	 String strDisposalAgent ="";
	 String completeValidationMessage ="";
	 String gridValidationMessage ="";
	 
	 String strModeValue ="";
	 String strModeAirValue ="";
	 String strVesselValue ="";
	 String strStatusValue ="";
	 String strContainerValue ="";
	 String strHouseBillValue ="";
	 String strVendorValue ="";
	 String strProductValue ="";
	 String strWFLRefDisposal ="";
	 String strWFLRefDisposalAgent ="";
	 String strWFLRefInspectionOutcome ="";
	 String strWFLDisposalValue ="";
	 String strWFLDisposalAgentvalue ="";
	 String strWFLInspectionOutcomevalue ="";
	 String strWFLInspectionOutcomeFailValue ="";
	 String strCompleteStatusValue ="";
	 String strInCompleteStatusValue ="";
	 String strAnyStatusValue ="";
	 String strAwaitingStatusValue ="";
	 String strOrderNumberValue ="";
	 String strCustomerValue ="";
	 String strOrginPortValue ="";
	 String strDestinationWFLRefLinkQCFormName ="";
	 String StrValidationMessage ="";
	 String strNoInspectionRequiredValue ="";
	 String strWFLGridValidationMessageValue ="";
	 String strWFLGridCompleteValidationMessageValue ="";
	 String strSetDisposalValue ="";
	 String strSetDisposalAgentValue ="";
	 String strWFLETAFromDate ="";
	 String strWFLETAToDate ="";
	  	 
	 boolean bSearchResultsProductsFound = true;
	 ArrayList<DestinationQCPostInspection> lstSearchResults;
	 
	 ArrayList<String> catalogSupplierList;
	 ArrayList<String> catalogCustSupplierList;
	 ArrayList<String> catalogCountryList;
	 ArrayList<String> catalogCustomerList;
	 ArrayList<String> catalogLoCodeList;
	 ArrayList<String> catalogModeList;
	 ArrayList<String> catalogVesselList;
	 ArrayList<String> catalogDisposalAgentList;
	 
	 ArrayList<String> customerList;
	 ArrayList<String> orginList;
	 ArrayList<String> originPortList;
	 ArrayList<String> modeList;
	 ArrayList<String> vesselList;
	 ArrayList<String> statusList;
	 ArrayList<String> vendorList;
	 ArrayList<String> custvendorList;
	 
	 ArrayList<String> lstInspectionOutcome;
	 ArrayList<String> lstDisposal;
	 ArrayList<String> lstDisposalAgent;
	
	 
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
	        
	    	 Node CatalogPageTitle=(Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
	    	 strCatalogPageTitle=CatalogPageTitle.getTextContent();
	            
	    	 Node CatalogFormName=(Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	    	 strCatalogFormName=CatalogFormName.getTextContent();
	    	 
	    	 Node DestinationQCPostInspectionFormName=(Node) xPath.evaluate("/config/WFL/WFL_DestinationQC_PostInspection_FormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostInspectionFormName=DestinationQCPostInspectionFormName.getTextContent();
	    	 
	    	 
	    	 Node DestinationQCPostInspectionPageTitle=(Node) xPath.evaluate("/config/WFL/WFL_DestinationQC_PostInspection_Page_Title", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostInspectionPageTitle=DestinationQCPostInspectionPageTitle.getTextContent();
	    	 
	    	 Node DestinationQCPostInspectionWFLRefLinkFormName=(Node) xPath.evaluate("/config/WFL/WFL_Destination_QC_PostInspection_WFLRefFormName", dDoc, XPathConstants.NODE);
	    	 strDestinationWFLRefLinkQCFormName=DestinationQCPostInspectionWFLRefLinkFormName.getTextContent();
	    	 
	    	 Node DestinationQCPostInspectionGridFormName=(Node) xPath.evaluate("/config/WFL/WFL_Destination_QC_PostInspection_GridFormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostInspectionGridFormName=DestinationQCPostInspectionGridFormName.getTextContent();
	           
	    	 Node DestinationQCPostInspectionCompleteValidationMsgFormName=(Node) xPath.evaluate("/config/WFL/WFL_Complete_FieldValidation_FormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostInspectionCompleteValidationMsgFormName=DestinationQCPostInspectionCompleteValidationMsgFormName.getTextContent();
	           
	            
	        
	    	 driver = new FirefoxDriver();
	    	 driver.manage().window().maximize();
	    	 driver.get(strTestURL);

	    	 driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);
	            
	    	 objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
	    	 objAdjunoLIMALoginPOM.setUserName(strLIMAUserName);
	    	 objAdjunoLIMALoginPOM.setPassword(strLIMAPassword);
	    	 objAdjunoLIMALoginPOM.clickLogon();
	    
	     } 
	     catch(Exception e){
	    	 e.printStackTrace();
	        	
	     }
	     

	     DocumentBuilderFactory domFactory1 = DocumentBuilderFactory.newInstance();
	     try {
	    	 DocumentBuilder builder = domFactory1.newDocumentBuilder();
	    	 Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getstrWFLDestinationQCPostInspection());
	            
	    	 XPath xPath1 = XPathFactory.newInstance().newXPath();
	            
	    	 nlSearchParamsCatalog = (NodeList) xPath1.evaluate("/Catalog/SearchParams", dDoc1, XPathConstants.NODESET);
	            
	    	 nlSearchParamsDestinationQCPostInspection=(NodeList) xPath1.evaluate("/Catalog/Destination_QC_PostInspection/SearchParams", dDoc1, XPathConstants.NODESET);
	            
	    	 Element el;
	    	 for(int i=0; i <= nlSearchParamsCatalog.getLength() -1; i++)
	    	 {
	    		 if (nlSearchParamsCatalog.item(i).getNodeType() == Node.ELEMENT_NODE)
	    		 {
	    			 el = (Element) nlSearchParamsCatalog.item(i);
	    			 strSupplier = el.getElementsByTagName("Type_Supplier").item(0).getTextContent(); 
	                     
	    			 strCustomer = el.getElementsByTagName("Type_Customer").item(0).getTextContent(); 
	                     
	    			 strCountry = el.getElementsByTagName("Type_Country").item(0).getTextContent(); 
	                     
	    			 strLoCode = el.getElementsByTagName("Type_LoCode").item(0).getTextContent(); 
	                     
	    			 strMode = el.getElementsByTagName("Type_Mode").item(0).getTextContent(); 
	                     
	    			 strVessel = el.getElementsByTagName("Type_Vessel").item(0).getTextContent(); 
	                     
	    			 strDisposalAgent = el.getElementsByTagName("Type_DisposalAgent").item(0).getTextContent(); 
	            
	    		 }
	    	 }
	    	 
	    	 Element ele;
	    	 for(int i=0; i <= nlSearchParamsDestinationQCPostInspection.getLength() -1; i++)
	    	 {
	    		 if (nlSearchParamsDestinationQCPostInspection.item(i).getNodeType() == Node.ELEMENT_NODE)
	    		 {
	    			 ele = (Element) nlSearchParamsDestinationQCPostInspection.item(i);
	                     
	    			 strOrginPortValue = ele.getElementsByTagName("Origin_Port").item(0).getTextContent(); 
	                     
	    			 strModeValue = ele.getElementsByTagName("Mode").item(0).getTextContent(); 
	    			 
	    			 strModeAirValue = ele.getElementsByTagName("Mode_Air").item(0).getTextContent();
	                     
	    			 strVesselValue = ele.getElementsByTagName("Vessel").item(0).getTextContent(); 
	                     
	    			 strStatusValue = ele.getElementsByTagName("Status_Awaiting").item(0).getTextContent(); 
	                     
	    			 strContainerValue = ele.getElementsByTagName("Container").item(0).getTextContent(); 
	                     
	    			 strHouseBillValue = ele.getElementsByTagName("House_Bill").item(0).getTextContent(); 
	                     
	    			 strVendorValue = ele.getElementsByTagName("Vendor").item(0).getTextContent(); 
	                     
	    			 strProductValue = ele.getElementsByTagName("Product").item(0).getTextContent(); 
	                     	    			 
	    			 strWFLRefDisposalAgent = ele.getElementsByTagName("WFL_RefDisposalAgent").item(0).getTextContent(); 
	    			 
	    			 strWFLRefDisposal = ele.getElementsByTagName("WFL_RefDisposal").item(0).getTextContent(); 
	    			 	    			 	    			 	    			 
	    			 strWFLDisposalValue = ele.getElementsByTagName("WFL_Disposal_Value").item(0).getTextContent(); 
	    			 
	    			 strWFLDisposalAgentvalue = ele.getElementsByTagName("WFL_DisposalAgent_Value").item(0).getTextContent(); 
	    			 
	    			 strSetDisposalValue = ele.getElementsByTagName("WFL_SetDisposal_Value").item(0).getTextContent(); 
	    			 
	    			 strSetDisposalAgentValue = ele.getElementsByTagName("WFL_SetDisposal_Agent_Value").item(0).getTextContent(); 
	                
	    			 strWFLInspectionOutcomevalue = ele.getElementsByTagName("WFL_Inspection_Outcome_Value").item(0).getTextContent(); 

	    			 strWFLInspectionOutcomeFailValue = ele.getElementsByTagName("WFL_Inspection_Outcome_Fail_Value").item(0).getTextContent(); 
	    			 
	    			 strWFLGridValidationMessageValue = ele.getElementsByTagName("WFL_Grid_ValidationMsg").item(0).getTextContent(); 
	    			 
	    			 strWFLGridCompleteValidationMessageValue = ele.getElementsByTagName("WFL_Grid_CompleteValidationMsg").item(0).getTextContent(); 
	    			 
	    			 strCompleteStatusValue = ele.getElementsByTagName("Status_Complete").item(0).getTextContent();
	                     
	    			 strInCompleteStatusValue=ele.getElementsByTagName("Status_InComplete").item(0).getTextContent();
	                     
	    			 strAnyStatusValue = ele.getElementsByTagName("Status_Any").item(0).getTextContent();
	                     
	    			 strOrderNumberValue=ele.getElementsByTagName("Order_Number").item(0).getTextContent();
	                     
	    			 strCustomerValue=ele.getElementsByTagName("Customer").item(0).getTextContent();
	    			 
	    			 StrValidationMessage=ele.getElementsByTagName("WFL_ValidationMsg").item(0).getTextContent();
	    			 
	    			 strNoInspectionRequiredValue =ele.getElementsByTagName("No_Inspection_Required").item(0).getTextContent();
	                   
	    			 strWFLETAFromDate =ele.getElementsByTagName("ETD_From").item(0).getTextContent();

	    			 strWFLETAToDate =ele.getElementsByTagName("ETD_To").item(0).getTextContent();

	                 
	    		 }
	    	 }
	               
	     }catch(Exception ex){
	    	 ex.printStackTrace();
	     }
	                 
	 }
	 
	    

	 public void wait(int ms) {
		 try {
			 Thread.sleep(ms);
		 } catch (InterruptedException e) {
			e.printStackTrace();
		 }
	 }
	 

	 //Access Catalog
	 @Test(priority=1)	
	 public void accessCatalog(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		 
		 wait(5000);
		 String strTitlecatalog=objAdjunoWFLDestinationQCPostInspectionPOM.callMouseHover(strCatalogFormName, objAdjunoWFLDestinationQCPostInspectionPOM.getLnkTools(), objAdjunoWFLDestinationQCPostInspectionPOM.getLnkCatalog());
		 boolean bFlag1= true;	
	
		 if (isNullOrBlank(strTitlecatalog))
			 bFlag1 = false;
		        
		 if (!(isNullOrBlank(strTitlecatalog)))
		 {
			 if (strTitlecatalog.equalsIgnoreCase(strCatalogPageTitle))
				 bFlag1 = true;
			 else
				 bFlag1 = false;
		 }    	
		 objSoftAssert.assertEquals(bFlag1, true, "page Title of the Catalog Tool is wrong");
			
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getLnklighthouse()),true,"***** In Catalog Tool \"LightHouse\" button is not clicked *****");

		 objSoftAssert.assertAll();
		 }
	 
	 //clear CatalogField
	 @Test(priority=5)
	 public void clearCatalogField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 wait(6000);
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** In Catalog Tool under lighthouse page \"Name\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** In Catalog Tool under lighthouse page \"Type\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** In Catalog Tool under lighthouse page \"Name\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** In Catalog Tool under lighthouse page \"Associated item\" Field is not cleared *****");
		 	
		 objSoftAssert.assertAll();
	 }
	 
	 // get the catalog values on different field 	
	 @Test(priority=10)
	 public void getCatalogValues()
	 {
		 SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage ="";
		 //set field value as Customer 
		 clearCatalogField(); 
		 if(!isNullOrBlank(strCustomer)){
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strCustomer, strCatalogFormName, "Param_Type"),true,"***** Test ID : 7.002 - In Catalog Tool under lighthouse page\"Type\" Field value is not entered as Customer *****");
		 }else{
			 strMessage=strMessage+" \"Customer\" Value is Empty in Xml";
		 }
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"*****Test ID : 7.002 - In Catalog Tool under lighthouse page \"Apply\" button is not Clicked *****");
		 catalogCustomerList=objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogData();

		 if(catalogCustomerList.size()>0){
				
		 }else{
			 strMessage = strMessage +" In Catalog Tool under lighthouse page \"Customer\" Type doesn't have data";
		 }
		       				
		//set field value  as country
		 clearCatalogField();
		
		 	if(!isNullOrBlank(strCountry))
		 	{
		 		wait(1000);
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strCountry, strCatalogFormName,"Param_Type"), true,"***** Test ID : 7.003 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as Country*****");
		    	
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 7.003 - \"Country\" value empty in xml *****";
		 	}
			       
		 	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.003 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		 	wait(5000);
			        
		 	int nCount3 = objAdjunoWFLDestinationQCPostInspectionPOM.valCount();
		 	catalogCountryList = new ArrayList<String>();
		 	catalogCountryList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogTableData(nCount3);
		 	wait(5000);
		 	
		 	if(catalogCountryList.size()>0){
		 	}else{
		 		strMessage = strMessage +" \"origin\" Type doesn't have data";
		 	}
			  			     
		 	//Setting the field value LODOCE setFieldValue
		 	if(!isNullOrBlank(strLoCode))
		 	{
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 7.004 - In Catalog Tool under lighthouse page \"Previous\" button is not clicked *****");
		 		wait(1000);
		 		clearCatalogField();
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strLoCode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 7.004 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as LoCODE *****"); 
		 		
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.004 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		 		wait(5000);
			                                
		 		int nCount4 = objAdjunoWFLDestinationQCPostInspectionPOM.valCount();
		 		catalogLoCodeList = new ArrayList<String>();
		 		catalogLoCodeList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogTableData1(nCount4);
		 		wait(5000);
			       
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 7.004 - \"LOCODE\" value empty in xml *****";
		 	}
		 	if(catalogLoCodeList.size()>0){   
		 		
		 	}else{
		 		strMessage = strMessage +" In Catalog Tool under lighthouse page \"OriginPort\" Type doesn't have data";
		 	}

		 	//set the mode value in textbox
		 	if(!isNullOrBlank(strMode)){
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnPageNext()),true,"***** Test ID : 7.005 - In Catalog Tool under lighthouse page \"Previous\" button is not clicked *****");
		 		wait(1000);
		 		clearCatalogField();
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strMode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 7.005 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered *****"); 
		 		
		 		wait(5000);
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.005 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		 		wait(5000);
		 		catalogModeList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogData();
			      
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 7.005 - \"Mode\" value is Empty in xml *****";
		 	}       
		 	if(catalogModeList.size()>0){
			        	
		 	}else{
		 		strMessage = strMessage +" In Catalog Tool under lighthouse page \"Mode\" Type doesn't have data";
		 	}

		 	//set the Vessel value in textbox
		 	if(!isNullOrBlank(strVessel)){
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 7.006 - In Catalog Tool under lighthouse page \"Previous\" button is not clicked *****");
		 		wait(1000);
		 		clearCatalogField();
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strVessel, strCatalogFormName,"Param_Type"), true,"***** Test ID : 7.006 - In Catalog Tool under lighthouse page \"Type\' Field value is not entered as Vessel*****");
		 		
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.006 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		 		wait(5000);
			             
		 		int nCount5 = objAdjunoWFLDestinationQCPostInspectionPOM.valCount();
		 		catalogVesselList = new ArrayList<String>();
		 		catalogVesselList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogTableData(nCount5);         
		 	}else{    	
		 		strMessage = strMessage + "***** Test ID : 7.006 - \"vessel\" value is Empty in xml *****";
		 	}
		 	if(catalogVesselList.size()>0){
			        	
		 	}else{
		 		strMessage = strMessage +" In Catalog Tool under lighthouse page \"Vessel\" Type doesn't have data";
		 	}
				     
		 	//set the Suppliers value in textbox
		 	if(!isNullOrBlank(strSupplier)){
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 7.008 - In Catalog Tool under lighthouse page \"Previous\" button is not clicked *****");
		 		wait(1000);
		 		clearCatalogField();
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 7.008 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as Supliers*****");
		 		
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.008 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		     		
		 		catalogSupplierList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogSupplierTableData();
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 7.008 - \"Supplier\" value is Empty in xml *****";
		 	}
		 	if(catalogSupplierList.size()>0){
				        	
		 	}else{
		 		strMessage = strMessage +" In Catalog Tool under lighthouse page \"Supplier\" Type doesn't have data";
		 	}
		 	
		 	//set the DisposalAgent value in textbox
		 	if(!isNullOrBlank(strDisposalAgent)){
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 7.031 - In Catalog Tool under lighthouse page \"Previous\" Button is not clicked *****");
		 		wait(1000);
		 		clearCatalogField();
		 		wait(5000);
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.031 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		     		
		 		catalogDisposalAgentList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogData();
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 7.031- \"DisposalAgent\" value is Empty in xml *****";
		 	}
		 	if(catalogDisposalAgentList.size()>0){
				        	
		 	}else{
		 		strMessage = strMessage +" In Catalog Tool under lighthouse page \"DisposalAgent\" Type doesn't have data";
		 	}
		 
		   //set the Suppliers value in textbox
		 	if(!isNullOrBlank(strSupplier)){
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 7.001 - In Catalog Tool under lighthouse page \"Previous\" button is not clicked *****");
		 		wait(1000);
		 		clearCatalogField();
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 7.001 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as Suplier *****");
		 		
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()),true,"***** Test ID : 7.001 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		     		
		 		catalogCustSupplierList = objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogTableData(objAdjunoWFLDestinationQCPostInspectionPOM.getLstNameDescription(), objAdjunoWFLDestinationQCPostInspectionPOM.getLstSupplierDescriptionField());
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 7.001 - \"Supplier\" value is Empty in xml *****";
		 	}
		 	if(catalogCustSupplierList.size()>0){
				        	
		 	}else{
		 		strMessage = strMessage +" In Catalog Tool under lighthouse page \"Supplier\" Type doesn't have data";
		 	}
		 	
		 	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, strMessage);
		 	objSoftAssert.assertAll();
	 }
	 
	 private boolean isNullOrBlank(String s)
	 {
		 return (s==null || s.trim().equals(""));
	 }
	 
	 //verify customer dropdown values 
	 @Test(priority=15)
	 public void test_7d002_VerifyCustomerValues(){
			 
		 SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage ="";
		 objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		 String strTitle= objAdjunoWFLDestinationQCPostInspectionPOM.callMouseHover(strDestinationQCPostInspectionFormName, objAdjunoWFLDestinationQCPostInspectionPOM.getLnkTools(), objAdjunoWFLDestinationQCPostInspectionPOM.getLnkDestinationQCPostInspection());
		 boolean bFlag = true;
			
		 if (isNullOrBlank(strTitle))
			 bFlag = false;
			
		 if (!(isNullOrBlank(strTitle)))
		 {
			 if (strTitle.equalsIgnoreCase(strDestinationQCPostInspectionPageTitle))
				 bFlag = true;
			 else	
				 bFlag = false;
		 }       		   
	   
		 objSoftAssert.assertEquals(bFlag, true, "Page Title of the Destination QC Post Inspection Tool is wrong");
	       
		 customerList = objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_21Customer");
	       
		 if(customerList.size()>0){
				
		 }else{
			 strMessage = strMessage +" \"customer\" Drop drown doesn't have value";
		 }
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,strMessage);
				
		 //verify the customer dropdown list into catalog values
			
		 strMessage= strMessage + objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogCustomerList, customerList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.002"+ strMessage +"*****");
		 objSoftAssert.assertAll();
	 }
	
	//verify the Orgin Dropdown Data
	@Test(priority=20)
	public void test_7d003_VerifyOrginDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		orginList = objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_01Origin");
		
		if(orginList.size()>0){
				
		}else{
			strMessage=strMessage+"\"Origin\" Drop Down doesn't have any Value";
		}
		//verify the customer dropdown list into catalog values
			
		strMessage= strMessage + objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogCountryList, orginList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.003"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//verify Origin port Drop Down Value
	@Test(priority=25)
	public void test_7d004_VerifyOrginPortDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		originPortList=objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_02OriginPort");
		
		if(originPortList.size()>0){
		}else{
			strMessage=strMessage+"\"Orgin Port\" Drop Down doesn't have any Value";
		}		
		//verify the customer dropdown list into catalog values
			
		strMessage= strMessage + objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogLoCodeList, originPortList);	
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.004"+ strMessage +"*****");
			
		objSoftAssert.assertAll();
			
	}
		
	//verify Mode Drop Down Values
	@Test(priority=30)
	public void test_7d005_VerifyModeDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		
		modeList=objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_03Mode");
		
		if(modeList.size()>0){
		}else{
			strMessage=strMessage+"\"Mode\" Drop Down doesn't have any Value";
		}
		//verify the customer dropdown list into catalog values
		strMessage= strMessage + objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogModeList, modeList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.005"+ strMessage +"*****");
		objSoftAssert.assertAll();		
	}
		

	//verify Vessel Drop Down Values
	@Test(priority=35)
	public void test_7d006_VerifyVesselDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		vesselList=objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_04Vessel");
		
		if(vesselList.size()>0){
		}else{
			strMessage=strMessage+"\"Vessel\" Drop Down doesn't have any Value";
		}
		//verify the customer dropdown list into catalog values
		strMessage= strMessage + objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogVesselList, vesselList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.006"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//verify Status drop Down Values
	@Test(priority=40)
	public void test_7d007_VerifystatusDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		statusList=objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_13Status");
	
		if(statusList.size()==4){
		}else{
			strMessage=strMessage+"\"Status\" Drop Down doesn't have any Value";
		}
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID : 7.007"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
		
	//verify the vendor drop down values
	@Test(priority=45)
	public void test_7d008_VerifyVendorDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		vendorList=objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_05Vendor");
		
		if(vendorList.size()>0){
		}else{
			strMessage=strMessage+"\"Vendor\" Drop Down doesn't have any Value";
		}
			
		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogSupplierList, vendorList);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.008"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//clear the field 
	public void clearFields(){
		SoftAssert objSoftAssert = new SoftAssert();
		
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_01Origin"), true,"***** In Destination QC PostInspection Tool \"Orgin\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_02OriginPort"), true,"***** In Destination QC PostInspection Tool \"Orgin port\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_03Mode"), true,"***** In Destination QC PostInspection Tool \"mode\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_04Vessel"), true,"***** In Destination QC PostInspection Tool \"vessel\" Field element not cleared *****");		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_11ETAFrom"), true,"***** In Destination QC PostInspection Tool \"ETA From\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_12ETATo"), true,"***** In Destination QC PostInspection Tool \"ETA To\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_13Status"), true,"***** In Destination QC PostInspection Tool \"status\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_06Container"), true,"***** In Destination QC PostInspection Tool \"container\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_09HouseBill"), true,"***** In Destination QC PostInspection Tool \"HouseBill\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_05Vendor"), true,"***** In Destination QC PostInspection Tool \"Vendor\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_14CustomerReference"), true,"***** In Destination QC PostInspection Tool \"Customer Reference\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_07PO"), true,"***** In Destination QC PostInspection Tool \"WFL Reference\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_08Product"), true,"***** In Destination QC PostInspection Tool \"Product\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_19URN"), true,"***** In Destination QC PostInspection Tool \"UCR/URN\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clearInputField(strDestinationQCPostInspectionFormName, "PARAM_21Customer"), true,"***** In Destination QC PostInspection Tool \"customer\" Field element not cleared *****");
			
		objSoftAssert.assertAll();
	}
	
	//Set Order Number Value and Inspect DropDown
	@Test(priority=55)	
	public void test_7d020_SetValueofOrderNumberDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.020 - In Destination QC PostInspection Tool \"Select\" chevron is not clicked *****");

		clearFields();
									
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.020 - In Destination QC PostInspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
									
		if(!isNullOrBlank(strOrderNumberValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strOrderNumberValue, strDestinationQCPostInspectionFormName,"PARAM_14CustomerReference"), true,"***** Test ID : 7.020 - In Destination QC PostInspection Tool under select chevron \"Order Number\" DropDown value is not Entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
									
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.020 - In Destination QC PostInspection Tool under select chevron \"RefineSearch\" button is not clicked *****");	
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getLnkWFLReference()), true ," Test ID : 7.020 - In Destination QC PostInspection Tool under select chevron \"Wfl Reference link\" is not Clicked *****");
		wait(5000);
		String window= objAdjunoWFLDestinationQCPostInspectionPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()), true , "***** Test ID : 7.020 - In Destination QC PostInspection Tool under Purchase order page \"Apply\" button not clicked *****");

		wait(1000);						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getLnkProductCard()), true ," Test ID : 7.020 - In Destination QC PostInspection Tool under Purchase order page \"ProductCard\" link not Clicked *****");
		wait(1000);							
		String orderNumber=objAdjunoWFLDestinationQCPostInspectionPOM.getGridCellValue(strDestinationWFLRefLinkQCFormName, "GRID_ProductCard", 0, "CustomerReference");
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strOrderNumberValue, orderNumber), true , " Test ID : 7.020 - In Destination QC PostInspection Tool under purchase order page, The \"Order Number\" value is not Matching with the \"Order Number\" value entered under Select chevron  ***** ");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.020"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//set Value to OrginPort DropDown
	@Test(priority=60)
	public void test_7d011_SetOrginPortDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.011 - In Destination QC PostInspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strOrginPortValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strOrginPortValue, strDestinationQCPostInspectionFormName,"PARAM_02OriginPort"), true,"***** Test ID : 7.011 - In Destination QC PostInspection Tool under select chevron \"OrginPort\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"OrginPort\" Value is Empty in XML";
		}

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.011 - In Destination QC PostInspection Tool under select chevron \"Refine Search\" button not clicked *****");
								
		List<WebElement> lstWFLReference=objAdjunoWFLDestinationQCPostInspectionPOM.getLstWFLRef();

		for(int i=0;i<lstWFLReference.size();i++){
			lstWFLReference.get(i).click();
			String window= objAdjunoWFLDestinationQCPostInspectionPOM.getWindowIds();
			String [] pageWindow=window.split(";");
			driver.switchTo().window(pageWindow[1]);
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()), true , "***** Test ID : 7.011 - In Destination QC PostInspection Tool under Purchase page \"Apply\" button not clicked *****");
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strOrginPortValue, objAdjunoWFLDestinationQCPostInspectionPOM.getFieldValue(strDestinationWFLRefLinkQCFormName, "OriginPort")), true , " Test ID : 7.011 - In Destination QC PostInspection Tool under purchase order page The \"OriginPort\" value is not Matching with the value which was entered under Select chevron \"Origin Port\" value ***** ");
			driver.close();
			driver.switchTo().window(pageWindow[0]);
		}
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.011"+ strMessage +"*****");
		objSoftAssert.assertAll();
								
	}
	
	//Set the Customer DropDown Values and inspect the result
	@Test(priority=65)
	public void test_7d024_SetCustomerDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();					
		
		if(!isNullOrBlank(strCustomerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strCustomerValue, strDestinationQCPostInspectionFormName,"PARAM_21Customer"), true,"***** Test ID : 7.024 - In Destination QC PostInspection Tool under select chevron \"Customer\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"CustomerValue\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLRefDisposal)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposal, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.024 - In Destination QC PostInspection Tool under select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.024 - In Destination QC PostInspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.024 - In Destination QC PostInspection Tool under select chevron \"Refine Search\" button not clicked *****");
								
		List<WebElement> lstWFLReference=objAdjunoWFLDestinationQCPostInspectionPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
		lstWFLReference.get(i).click();
		String window= objAdjunoWFLDestinationQCPostInspectionPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getCatalogbtnApply()), true , "***** Test ID : 7.024 - In Destination QC PostInspection Tool under Purchase Order page \"Apply\" button is not clicked *****");
				
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strCustomerValue, objAdjunoWFLDestinationQCPostInspectionPOM.getFieldValue(strDestinationWFLRefLinkQCFormName, "Customer")), true , " Test ID : 7.024 - In Destination QC PostInspection Tool under purchase order page \"Customer\" value are not Matching with the customer value which was entered under Select chevron \"Customer\" field ***** ");
		wait(1000);
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		}
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.024"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
		
	//login as a customer
	@Test(priority=75)
	public void LoginCustomer(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickLogout(objAdjunoWFLDestinationQCPostInspectionPOM.getLnkLogout()), true , "***** Logout button not Clicked *****");
		objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
		objAdjunoLIMALoginPOM.setUserName(strCustomerUserName);
		objAdjunoLIMALoginPOM.setPassword(strCustomerPassword);
		objAdjunoLIMALoginPOM.clickLogon();
			
	}
	
	//logged as a customer and verify the customer dropdown
	@Test(priority=80)
	public void test_7d009_VerifyCustCustomerDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		String strTitle= objAdjunoWFLDestinationQCPostInspectionPOM.callMouseHover(strDestinationQCPostInspectionFormName, objAdjunoWFLDestinationQCPostInspectionPOM.getLnkTools(), objAdjunoWFLDestinationQCPostInspectionPOM.getLnkDestinationQCPostInspection());
		boolean bFlag = true;
			
		if (isNullOrBlank(strTitle))
			bFlag = false;
			
		if (!(isNullOrBlank(strTitle)))
		{
			if (strTitle.equalsIgnoreCase(strDestinationQCPostInspectionPageTitle))
				bFlag = true;
			else	
				bFlag = false;
		}       		   
	   
		objSoftAssert.assertEquals(bFlag, true, "Page Title of the Destination QC Post-Inspection Tool is wrong");
			
		customerList = objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_21Customer");
		System.out.println("customerList size"+customerList.size());
		if(customerList.size()==1){
				
		}else{
			strMessage = strMessage +" In Destination QC Post Inspection Tool under Search Chevron \"customer\" Drop drown doesn't have value";
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.009"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//verify the vendor drop down values
	@Test(priority=82)
	public void test_7d001_VerifyCustVendorDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostInspectionPOM=new AdjunoWFLDestinationQCPostInspectionPOM(driver);
		custvendorList=objAdjunoWFLDestinationQCPostInspectionPOM.getDropdownValues(strDestinationQCPostInspectionFormName, "PARAM_05Vendor");
				
		if(custvendorList.size()>0){
		}else{
			strMessage=strMessage+" In Destination QC Post Inspection Tool under Search Chevron \"Vender\" Drop Down doesn't have any Value";
		}
					
		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogData(catalogCustSupplierList, custvendorList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.001"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set the value To Mode dropdown box and inspect the values
	@Test(priority=85)
	public void test_7d012_SetValueModeDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
	
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.012 -  In Destination QC Post Inspection Tool under Search Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.012 -  In Destination QC Post Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
		
		if(!isNullOrBlank(strModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strModeValue, strDestinationQCPostInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 7.012 - In Destination QC Post Inspection Tool under Select Chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strAnyStatusValue)){	
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.012 -  In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.0012 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		wait(5000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strModeValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstMode());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.012"+ strMessage +"*****");
		objSoftAssert.assertAll();
	
	}
	
	//Set the value in Vessel dropdown  and inspect the values
	@Test(priority=90)
	public void test_7d013_SetValueVesselDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strVesselValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strVesselValue, strDestinationQCPostInspectionFormName,"PARAM_04Vessel"), true,"***** Test ID : 7.013 - In Destination QC Post Inspection Tool under Select Chevron \"Vessel\" Field value is not entered *****");
		 }else{
		 	strMessage=strMessage+"\"Vessel\" Value is Empty in XML";
		 }
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.013 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.013 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
				
		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strVesselValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstVessel());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.013"+ strMessage +"*****");
		objSoftAssert.assertAll();
	
	}
	

	@Test(priority=92)
	public void test_7d014_SetValueETAFromCalender() throws ParseException{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.014 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLETAFromDate)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLETAFromDate, strDestinationQCPostInspectionFormName,"PARAM_11ETAFrom"), true,"***** Test ID : 7.014 - In Destination QC Post Inspection Tool under Select Chevron \"ETAFrom\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"ETA Fron Date\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.014 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		wait(5000);
		strMessage =strMessage +objAdjunoWFLDestinationQCPostInspectionPOM.compareFromDates(strWFLETAFromDate, objAdjunoWFLDestinationQCPostInspectionPOM.getLstETAFromDate());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.014 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority=94)
	public void test_7d015_SetValueETAToCalender() throws ParseException{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.015 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLETAToDate)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLETAToDate, strDestinationQCPostInspectionFormName,"PARAM_12ETATo"), true,"***** Test ID : 7.015 - In Destination QC Post Inspection Tool under Select Chevron \"ETA To\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"ETA To Date\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.015 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostInspectionPOM.compareToDates(strWFLETAToDate, objAdjunoWFLDestinationQCPostInspectionPOM.getLstETAToDate());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.015 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//Set the value in status dropdown  and inspect the values
	@Test(priority=95)
	public void test_7d016_SetValueStatusDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();	
		
		if(!isNullOrBlank(strCompleteStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strCompleteStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.016 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
			
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.016 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
				
		wait(5000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strStatusValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstStatus());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.016"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set the value in Container dropdown  and inspect the values
	@Test(priority=100)
	public void test_7d017_SetValueContainerDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
	
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strContainerValue, strDestinationQCPostInspectionFormName,"PARAM_06Container"), true,"***** Test ID : 7.017 - In Destination QC Post Inspection Tool under Select Chevron \"Container\" Field value is not entered *****");
		 }else{
		 	strMessage=strMessage+"\"Container\" Value is Empty in XML";
		 }
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.017 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.017 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strContainerValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstContainer());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.017"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//Set the value in HouseBill dropdown  and inspect the values
	@Test(priority=105)
	public void test_7d018_SetValueHouseBillDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strHouseBillValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strHouseBillValue, strDestinationQCPostInspectionFormName,"PARAM_09HouseBill"), true,"***** Test ID : 7.018 - In Destination QC Post Inspection Tool under Select Chevron \"House Bill\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Housebill\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.018 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.018 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strHouseBillValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstHouseBill());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.018"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
	
	//Set the value in Vendor dropdown  and inspect the values
	@Test(priority=110)
	public void test_7d019_SetValueVendorDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strVendorValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strVendorValue, strDestinationQCPostInspectionFormName,"PARAM_05Vendor"), true,"***** Test ID : 7.019 - In Destination QC Post Inspection Tool under Select Chevron \"Vendor\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Vendor\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.019 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.019 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strVendorValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstVendor());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.019"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//Set the value in product dropdown  and inspect the values
	@Test(priority=115)
	public void test_7d022_SetValueProductDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
	
		if(!isNullOrBlank(strProductValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strProductValue, strDestinationQCPostInspectionFormName,"PARAM_08Product"), true,"***** Test ID : 7.022 - In Destination QC Post Inspection Tool under Select Chevron \"Product\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Product\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.022 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.022 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		strMessage=strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strProductValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstProduct());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.022"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
	
	//Set the value in WFLRef dropdown  and inspect the values
	@Test(priority=120)
	public void test_7d021_SetValueWFLRefDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.021 - In Destination QC Post Inspection Tool under Select Chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.021 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.021 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		strMessage = objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strWFLRefDisposalAgent,objAdjunoWFLDestinationQCPostInspectionPOM.getLstWFLRef());
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.021"+ strMessage +"*****");
		objSoftAssert.assertAll();
						
	}
	
	//Check the duplicate value is present or not
	@Test(priority=125)
	public void test_7d025_CheckDuplicateValuePresent(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.025 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strModeAirValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strModeAirValue, strDestinationQCPostInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 7.025 - In Destination QC Post Inspection Tool under Select Chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.025 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostInspectionPOM.duplicateValue(objAdjunoWFLDestinationQCPostInspectionPOM.getLstContainer());
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostInspectionPOM.duplicateValue(objAdjunoWFLDestinationQCPostInspectionPOM.getLstMode());
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.025"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
	}
	
	//Search for pre-inspection Status value
	@Test(priority=130)
	public void test_7d026_VerifyinspectionRequiredField(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strCompleteStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strCompleteStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.0026 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
								
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.0026 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strCompleteStatusValue, objAdjunoWFLDestinationQCPostInspectionPOM.getLstStatus());
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.026"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
	}
	
	//Search the product for No Inspection Required
	@Test(priority=135)
	public void test_7d027_NoInspectionRequiredValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
						
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.027 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strNoInspectionRequiredValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strNoInspectionRequiredValue, strDestinationQCPostInspectionFormName,"PARAM_09HouseBill"), true,"***** Test ID : 7.027 - In Destination QC Post Inspection Tool under Select Chevron \"HouseBill\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLProductDespatched\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.027 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
						
		String strValidationMsg=strMessage + objAdjunoWFLDestinationQCPostInspectionPOM.getStrValidationMsg().getText();
		objSoftAssert.assertEquals(strValidationMsg,StrValidationMessage, " ***** Test ID : 7.027 - In Destination QC Post Inspection Tool under Select Chevron \"No Inspection Required\" Product is Displaying *****");
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.027"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}	
	//Inspect the Disposal Agent Drop Down
	@Test(priority=155)
	public void test_7d031_VerifyDisposaAgentlDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
						
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool under Select Chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
			        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
					   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
							             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.0031 - In Destination QC Post Inspection Tool under Select Chevron \"No productsare displayed\" *****");
		}
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomeFailValue), true , " Test ID : 7.031 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection outcome\" Drop Down Value is not entered *****");
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkDisposalAgentComboBox()), true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool under post inspection Chevron \"Disposal Agent\" Drop down is not clicked *****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkDisposalAgentArrow()), true,"***** Test ID : 7.031 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Disposal Agent\" Drop down Arrow not clicked *****");
		wait(5000);
		lstDisposalAgent=objAdjunoWFLDestinationQCPostInspectionPOM.getGridDropdownvalues(objAdjunoWFLDestinationQCPostInspectionPOM.getLstDisposalAgentDropDownValues());
		wait(5000);
		strMessage=objAdjunoWFLDestinationQCPostInspectionPOM.verifyCatalogAndGridDropdownData(catalogDisposalAgentList, lstDisposalAgent);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.031"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set the new value of Corresponding field and inspect the Refine Search values
	@Test(priority=140)
	public void test_7d028_VerifyRefineSearchResult(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		wait(3000);
		clearFields();
		wait(5000);		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.028 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strModeAirValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strModeAirValue, strDestinationQCPostInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 7.028 - In Destination QC Post Inspection Tool under Select Chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strContainerValue, strDestinationQCPostInspectionFormName,"PARAM_06Container"), true,"***** Test ID : 7.028 - In Destination QC Post Inspection Tool under Select Chevron \"Container\" Field value is not entered *****");
		 }else{
		 	strMessage=strMessage+"\"Container\" Value is Empty in XML";
		 }
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.028 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		wait(5000);
		
//		strMessage= strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strModeAirValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstMode());
		wait(5000);
		String strMode =objAdjunoWFLDestinationQCPostInspectionPOM.getStrMode().getText();
		strModeAirValue.equalsIgnoreCase(strMode);
		wait(5000);
//		strMessage= strMessage+objAdjunoWFLDestinationQCPostInspectionPOM.verifySearchValue(strContainerValue,objAdjunoWFLDestinationQCPostInspectionPOM.getLstContainer());

		String strContainer =objAdjunoWFLDestinationQCPostInspectionPOM.getStrContainer().getText();
		strContainerValue.equalsIgnoreCase(strContainer);
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.028"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
			
	}
	
	
	//Inspect the Inspection Outcome Drop Down
	@Test(priority=145)
	public void test_7d029_VerifyInspectionOutcomeDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.029 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.029 - In Destination QC Post Inspection Tool under Select Chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.029 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");
		wait(5000);
		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
			   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.029 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.029 - In Destination QC Post Inspection Tool under Select Chevron\"No Products are displayed\" *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkInspectionOutcomeComboBox()), true,"***** Test ID : 7.029 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection Outcome\" Drop down is not clicked *****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkInspectionOutcomeArrow()), true,"***** Test ID : 7.029 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection Outcome\" Drop down Arrow is not clicked *****");
		wait(5000);
		lstInspectionOutcome=objAdjunoWFLDestinationQCPostInspectionPOM.getGridDropdownvalues(objAdjunoWFLDestinationQCPostInspectionPOM.getInspectionOutcomeDropDownValues());
		if(lstInspectionOutcome.size()>0){
				
		}else{
			 strMessage = strMessage +" In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection Outcome\" Drop drown deosn't have value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.029"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	
	//Inspect the Disposal Drop Down
	@Test(priority=150)
	public void test_7d030_VerifyDisposalDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.030 - In Destination QC Post Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.030 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.030 - In Destination QC Post Inspection Tool under Select Chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.030 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
	        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.030 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
				             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.030 - In Destination QC Post Inspection Tool under Select Chevron \"No Products are displayed\" *****");
		}
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomeFailValue), true , " Test ID : 7.030 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection outcome\" Drop Down Value is not entered *****");

		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkDisposalArrow()), true,"***** Test ID : 7.030 - In Destination QC Post Inspection Tool under Post Inspection Chevron \"Disposal\" Drop down Arrow is not clicked *****");
		wait(5000);
		lstDisposal=objAdjunoWFLDestinationQCPostInspectionPOM.getGridDropdownvalues(objAdjunoWFLDestinationQCPostInspectionPOM.getLstDisposalDropDownValues());
		if(lstDisposal.size()>0){
					
		}else{
			strMessage = strMessage +" In Destination QC Post Inspection Tool under Post Inspection Chevron \"Disposal\" Drop drown doesn't have value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.030"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//set the disposal agent value and check it is accepted
	@Test(priority=160)
	public void test_7d032_SetDisposalAgentDropDownvalue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool under Select Chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
	        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
				             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.032 - In Destination QC Post Inspection Tool under Select Chevron \"No products are displayed\" *****");
		}
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomeFailValue), true , " Test ID : 7.032 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection outcome\" Drop Down Value is not entered *****");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkDisposalAgentComboBox()), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool under Post Inspection Chevron \"DisposalAgent\" Drop down is not clicked *****");
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "DisposalAgent", strWFLDisposalAgentvalue), true , " Test ID : 7.032 - In Destination QC Post Inspection Tool under Post Inspection Chevron \"Disposal Agent\" Drop Down Value is not Set *****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Disposal", strWFLDisposalValue), true , " Test ID : 7.032 - In Destination QC Post Inspection Tool under Post Inspection Chevron \"Disposal\" Drop Down Value is not Set *****");
	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvComplete()), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool under Complete Chevron \"Search Again\" Button is not Clicked *****" );
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool \"Select\" chevron is not Clicked *****" );
			
		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.032 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
				             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.032 - In Destination QC Post Inspection Tool under select Chevron \"No products are displayed\" *****");
		}

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strWFLDisposalAgentvalue, objAdjunoWFLDestinationQCPostInspectionPOM.getGridCellValue(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "DisposalAgent")), true , "*****  Test ID : 7.032 - In Destination QC Post Inspection Tool \"Disposal Agent\" Value is Not matching *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.032"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//set the disposal value and check it is accepted
	@Test(priority=165)
	public void test_7d035_SetDisposalDropDownvalue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.035 - In Destination QC Post Inspection \"Select\" chevron is not clicked *****");
		clearFields();
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.035 - In Destination QC Post Inspection under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.035 - In Destination QC Post Inspection under Select Chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.035 - In Destination QC Post Inspection under Select Chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
		        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.035 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
					             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.035 - In Destination QC Post Inspection Tool under Select Chevron \"No Products are displayed\" *****");
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getClkInspectionOutcomeComboBox()), true,"***** Test ID : 7.035 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Disposal\" Drop down field is not clicked *****");
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomeFailValue), true , " Test ID : 7.035 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Inspection Outcome\" Drop Down Value is not entered *****");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Disposal", strWFLDisposalValue), true , " Test ID : 7.035 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Disposal\" Drop Down Value is not entered *****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "DisposalAgent", strWFLDisposalAgentvalue), true , " Test ID : 7.035 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Disposal Agent\" Drop Down Value is not Entered*****");
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvComplete()), true,"***** Test ID : 7.035 - In Destination QC Post Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 7.035 - In Destination QC Post Inspection Tool under Complete Chevron \"Search Again\" Button is not Clicked *****" );
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.035 - In Destination QC Post Inspection Tool \"Select\" chevron is not Clicked *****" );
				
		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
	        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
					   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.035 - In Destination QC Post Inspection Tool \"PostInspection\" chevron is not clicked *****");
				             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.035 - In Destination QC Post Inspection Tool under select Chevron \"No Products are displayed\" *****");
		}

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strWFLDisposalValue, objAdjunoWFLDestinationQCPostInspectionPOM.getGridCellValue(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Disposal")), true , "*****  Test ID : 7.035 - In Destination QC Post Inspection Tool under PostInspection Chevron \"Disposal\" Drop Down Value is Not matched *****");
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.035"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//set the inspection Outcome dropdown value and check it is accepted
	@Test(priority=170)
	public void test_7d033_SetInspectionOutcomeDropDownvaluePass(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.033 - In Destination QC Post-Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
					
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.033 - In Destination QC Post-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.033 - In Destination QC Post-Inspection Tool under select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.033 - In Destination QC Post-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
			        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
					   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.033 - In Destination QC Post-Inspection Tool \"PostInspection\" chevron is not clicked *****");
						             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.033 - In Destination QC Post-Inspection Tool under select chevron \"No Products are not displayed\" *****");
		}
								
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomevalue), true , " Test ID : 7.033 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Inspection outcome\" Drop Down Value is not entered *****");
		wait(5000);
	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.checkFieldIsReadOnlyInGrid(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Disposal"), false , " Test ID : 7.033 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Disposal\" Drop down is not read only *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.checkFieldIsReadOnlyInGrid(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "DisposalAgent"), false , " Test ID : 7.033 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Disposal Agent\" Drop down is not read only *****");

		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.033"+ strMessage +"*****");
		objSoftAssert.assertAll();
						
	}
	
	//set the inspection outcome dropdown value and check it is accepted and check manditory fields
	@Test(priority=175)
	public void test_7d034_SetInspectionOutcomeDropDownvalueFail(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.034 - In Destination QC Post-Inspection Tool \"select\" chevron is not clicked *****");
		clearFields();
						
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.034 - In Destination QC Post-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.034 - In Destination QC Post-Inspection Tool under select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.034 - In Destination QC Post-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
				        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
						   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.034 - In Destination QC Post-Inspection Tool \"PostInspection\" chevron is not clicked *****");
							             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.034 - In Destination QC Post-Inspection Tool under select chevron \"No Products are displayed\" *****");
		}
										
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomeFailValue), true , " Test ID : 7.034 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Inspection outcome\" Drop Down Value is not entered *****");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strWFLInspectionOutcomeFailValue, objAdjunoWFLDestinationQCPostInspectionPOM.getGridCellValue(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome")), true , "*****  Test ID : 7.034 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Inspection Outcome\" Drop Down Value is Not matching *****");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvComplete()), true,"***** Test ID : 7.034 - In Destination QC Post-Inspection Tool \"Complete\" chevron is not clicked *****");
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDestinationQCPostInspectionPOM.getValidationMessageOtherthenField(objAdjunoWFLDestinationQCPostInspectionPOM.getStrGridValidationMsg())), true , "*****  Test ID : 7.034 - In Destination QC Post-Inspection Tool under PostInspection chevron \"mandatory Validation\" Message is Not matching *****");
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.034"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//inspect Search Again Button Present or not
	@Test(priority=180)
	public void test_7d036_InspectSearchAgainButton(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.036 - In Destination QC Post-Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
							
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.036 - In Destination QC Post-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.036 - In Destination QC Post-Inspection Tool under select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
							
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.036 - In Destination QC Post-Inspection Tool under select chevron \"Refine Search\" button not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
					        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
							   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.036 - In Destination QC Post-Inspection Tool \"PostInspection\" chevron is not clicked *****");
								             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.036 - In Destination QC Post-Inspection Tool under select chevron \"No Products are displayed\" *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomeFailValue), true , " Test ID : 7.036 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Inspection outcome\"Drop Down Value is not entered *****");
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Disposal", strWFLDisposalValue), true , " Test ID : 7.036 - In Destination QC Post-Inspection Tool under Post Inspection chevron \"Disposal\" Drop Down Value is not entered *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "DisposalAgent", strWFLDisposalAgentvalue), true , " Test ID : 7.036 - In Destination QC Post-Inspection Tool under Post Inspection chevron \"Disposal Agent\" Drop Down Value is not entered *****");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvComplete()), true, "***** Test ID : 7.036 - In Destination QC Post-Inspection Tool \"Complete\" chevron is not clicked *****");
		wait(5000);
		if(objAdjunoWFLDestinationQCPostInspectionPOM.checkDoesChevronExist(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSearchAgain())){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSearchAgain()), true, "***** Test ID : 7.036 - In Destination QC Post-Inspection Tool under complete chevron \"Search Again\" button is not clicked *****");

		}else{
			strMessage= strMessage+" In Destination QC postInspection Tool under Complete chevron \"Search Again\" button is not present ";
		}
		wait(1500);
		
		if (objAdjunoWFLDestinationQCPostInspectionPOM.verifyPageIsLoaded(strDestinationQCPostInspectionFormName)) {
			
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.checkDoesElementExist(strDestinationQCPostInspectionFormName,"PARAM_01Origin"),true,"***** Test ID : 7.036 - In Destination QC Post-Inspection Tool under search chevron\"Origin\" field is not present *****");
			         
		}else{
		boolean	bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 7.036 - In Destination QC Post-Inspection Tool \"Search page\" is not loaded*****");
		}
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.036"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//inspect completion stage
	@Test(priority=185)
	public void test_7d037_InspectCompletionStage(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvSelect()), true,"***** Test ID : 7.037 - In Destination QC Post-Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
								
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 7.037 - In Destination QC Post-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strWFLRefDisposalAgent)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValue(strWFLRefDisposalAgent, strDestinationQCPostInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 7.037 - In Destination QC Post-Inspection Tool under select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
								
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostInspectionPOM.getChvRefineSearch()), true,"***** Test ID : 7.037 - In Destination QC Post-Inspection Tool under select chevron \"Refine Search\" button not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostInspection>();
						        
		if ( objAdjunoWFLDestinationQCPostInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostInspectionPOM.selectMulitpleProducts(1);
								   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvPostInspectionChecks()),true,"***** Test ID : 7.037 - In Destination QC Post-Inspection Tool \"PostInspection\" chevron is not clicked *****");
									             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 7.037 - In Destination QC Post-Inspection Tool under select chevron \"No Products are displayed\" *****");
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.setFieldValueForGridCell(strDestinationQCPostInspectionGridFormName, "GridHouseBill", 0, "Outcome", strWFLInspectionOutcomevalue), true , " Test ID : 6.037 - In Destination QC Post-Inspection Tool under PostInspection chevron \"Inspection Outcome\" Drop Down Value is not entered *****");
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPostInspectionPOM.getChvComplete()), true, "***** Test ID : 7.037 - In Destination QC Post-Inspection Tool \"Complete\" chevron is not clicked *****");
		wait(5000);
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostInspectionPOM.compareTwoStringValuesToSame(objAdjunoWFLDestinationQCPostInspectionPOM.getElemetValue(strDestinationQCPostInspectionCompleteValidationMsgFormName, "UpdateMessage"), strWFLGridCompleteValidationMessageValue), true ,"***** Test ID : 7.037 - In Destination QC Post-Inspection Tool under complete chevron \"Completion validation message\" is not Verified *****");
		
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7.037"+ strMessage +"*****");
		objSoftAssert.assertAll();
	 
	}
	
	@AfterTest
	public void closeBrwser(){
		driver.close();
	}
	
}
