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

import com.lima.dto.DestinationQCPostDisposal;
import com.lima.dto.DestinationQCPostInspection;
import com.lima.dto.DestinationQCPreInspection;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoWFLDestinationQCPostDisposalPOM;
import com.lima.pagefactory.AdjunoWFLDestinationQCPostInspectionPOM;
import com.lima.pagefactory.AdjunoWFLDestinationQCPreInspectionPOM;

public class AdjunoWFLDestinationQCPostDisposalTest {
	
	 WebDriver driver;
	    
	 long nPage_Timeout = 0;
	 String strTestURL;
	    
	 String strLIMAUserName;
	 String strLIMAPassword;
	 String strCustomerUserName;
	 String strCustomerPassword;
	 String strCatalogFormName;
	 String strCatalogPageTitle;
	 String strDestinationQCPostDisposalFormName;
	 String strDestinationQCPostDisposalPageTitle;
	 String strDestinationQCPostDisposalWFLRefFormName;
	 String strDestinationQCPostDisposalGridFormName;
	 String strDestinationQCPostDisposalCompleteValidationMsgFormName;
	 
	 boolean bSearchResultsProductsFound = true;
	 ArrayList<DestinationQCPostDisposal> lstSearchResults;
	 
	 String strSupplier ="";
	 String strCustomer ="";
	 String strCountry ="";
	 String strLoCode ="";
	 String strMode ="";
	 String strVessel ="";
	 
	 String strModeValue ="";
	 String strModeAirValue ="";
	 String strVesselValue ="";
	 String strStatusValue ="";
	 String strContainerValue ="";
	 String strHouseBillValue ="";
	 String strVendorValue ="";
	 String strProductValue ="";
	 String strWFLRefValue ="";
	 String strAnyStatusValue ="";
	 String strAwaitingStatusValue ="";
	 String strOrginPortValue ="";
	 String strCompleteStatusValue ="";
	 String strOrderNumberValue ="";
	 String strCustomerValue ="";
	 String StrValidationMessage ="";
	 String strNoInspectionRequiredValue ="";
	 String strWFLDisposalCertNumber ="";
	 String strWFLDisposalCertNumber1 = "";
	 String strWFLGridValidationMessageValue ="";
	 String gridValidationMessage ="";
	 String strWFLRefDisposalCertNumber ="";
	 String completeValidationMessage ="";
	 String strWFLGridCompleteValidationMessageValue ="";
	 String strWFLRefDisposalMandatoryField ="";
	 String strWFLETAFromDate ="";
	 String strWFLETAToDate ="";
	 String strPastDate = "";
	 String strPresentDate = "";
	 String strFutureDate = "";
	 
	 ArrayList<String> catalogSupplierList;
	 ArrayList<String> catalogCountryList;
	 ArrayList<String> catalogCustomerList;
	 ArrayList<String> catalogLoCodeList;
	 ArrayList<String> catalogModeList;
	 ArrayList<String> catalogVesselList;
	 ArrayList<String> catalogCustSupplierList;
	 
	 ArrayList<String> customerList;
	 ArrayList<String> orginList;
	 ArrayList<String> originPortList;
	 ArrayList<String> modeList;
	 ArrayList<String> vesselList;
	 ArrayList<String> statusList;
	 ArrayList<String> vendorList;
	 ArrayList<String> custvendorList;
	
	 AdjunoLIMALibrary objAdjunoLIMALibrary;
	 AdjunoUILibrary objAdjunoUILibrary;
	 AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	 
	 
	 AdjunoWFLDestinationQCPostDisposalPOM objAdjunoWFLDestinationQCPostDisposalPOM;
	 
	 NodeList searchParams;
	 NodeList nlSearchParamsDestinationQCPostDisposal;
	 NodeList nlSearchParamsCatalog;
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
	    	 
	    	 Node DestinationQCPostDisposalFormName=(Node) xPath.evaluate("/config/WFL/WFL_DestinationQC_PostDisposal_FormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostDisposalFormName=DestinationQCPostDisposalFormName.getTextContent();
	    	 
	    	 
	    	 Node DestinationQCPostDisposalPageTitle=(Node) xPath.evaluate("/config/WFL/WFL_DestinationQC_PostDisposal_Page_Title", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostDisposalPageTitle=DestinationQCPostDisposalPageTitle.getTextContent();
	    	 
	    	 Node DestinationQCPostDisposalWFLRefLinkFormName=(Node) xPath.evaluate("/config/WFL/WFL_Destination_QC_PostDisposal_WFLRefFormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostDisposalWFLRefFormName=DestinationQCPostDisposalWFLRefLinkFormName.getTextContent();
	    	 
	    	 Node DestinationQCPostDisposalGridFormName=(Node) xPath.evaluate("/config/WFL/WFL_Destination_QC_PostDisposal_GridFormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostDisposalGridFormName=DestinationQCPostDisposalGridFormName.getTextContent();
	    	 
	    	 Node DestinationQCPostDisposalCompleteValidationMsgFormName=(Node) xPath.evaluate("/config/WFL/WFL_Complete_FieldValidation_FormName", dDoc, XPathConstants.NODE);
	    	 strDestinationQCPostDisposalCompleteValidationMsgFormName=DestinationQCPostDisposalCompleteValidationMsgFormName.getTextContent();
	            
	        
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
	    	 Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getstrWFLDestinationQCPostDisposal());
	            
	    	 XPath xPath1 = XPathFactory.newInstance().newXPath();
	            
	    	 nlSearchParamsCatalog = (NodeList) xPath1.evaluate("/Catalog/SearchParams", dDoc1, XPathConstants.NODESET);
	            
	    	 nlSearchParamsDestinationQCPostDisposal=(NodeList) xPath1.evaluate("/Catalog/Destination_QC_PostDisposal/SearchParams", dDoc1, XPathConstants.NODESET);
	            
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
	                     
	            
	    		 }
	    	 }
	    	 
	    	 Element ele;
	    	 for(int i=0; i <= nlSearchParamsDestinationQCPostDisposal.getLength() -1; i++)
	    	 {
	    		 if (nlSearchParamsDestinationQCPostDisposal.item(i).getNodeType() == Node.ELEMENT_NODE)
	    		 {
	    			 ele = (Element) nlSearchParamsDestinationQCPostDisposal.item(i);
	                     
	    			 strOrginPortValue = ele.getElementsByTagName("Origin_Port").item(0).getTextContent(); 
                     
	    			 strModeValue = ele.getElementsByTagName("Mode").item(0).getTextContent(); 
	    			 
	    			 strModeAirValue = ele.getElementsByTagName("Mode_Air").item(0).getTextContent();
	                     
	    			 strVesselValue = ele.getElementsByTagName("Vessel").item(0).getTextContent(); 
	                     
	    			 strStatusValue = ele.getElementsByTagName("Status_Awaiting").item(0).getTextContent(); 
	                     
	    			 strContainerValue = ele.getElementsByTagName("Container").item(0).getTextContent(); 
	                     
	    			 strHouseBillValue = ele.getElementsByTagName("House_Bill").item(0).getTextContent(); 
	                     
	    			 strVendorValue = ele.getElementsByTagName("Vendor").item(0).getTextContent(); 
	                     
	    			 strProductValue = ele.getElementsByTagName("Product").item(0).getTextContent(); 
	                     
	    			 strWFLRefValue = ele.getElementsByTagName("WFL_Ref").item(0).getTextContent(); 
	    			 
	    			 strCompleteStatusValue = ele.getElementsByTagName("Status_Complete").item(0).getTextContent();
	    			 
	    			 strAnyStatusValue = ele.getElementsByTagName("Status_Any").item(0).getTextContent();
	    			 
	    			 strOrderNumberValue=ele.getElementsByTagName("Order_Number").item(0).getTextContent();

	    			 strCustomerValue=ele.getElementsByTagName("Customer").item(0).getTextContent();
	    			 
	    			 StrValidationMessage=ele.getElementsByTagName("WFL_ValidationMsg").item(0).getTextContent();
	    			 
	    			 strNoInspectionRequiredValue =ele.getElementsByTagName("No_Inspection_Required").item(0).getTextContent();
	    			 
	    			 strWFLRefDisposalCertNumber =ele.getElementsByTagName("WFL_Ref_Disposal_Certificate_No").item(0).getTextContent();
	    			 
	    			 strWFLRefDisposalMandatoryField =ele.getElementsByTagName("WFL_Ref_Mandatory_Field").item(0).getTextContent();
	    			 
	    			 strWFLGridValidationMessageValue =ele.getElementsByTagName("WFL_Grid_ValidationMsg").item(0).getTextContent();
	    			 
	    			 strWFLGridCompleteValidationMessageValue = ele.getElementsByTagName("WFL_Grid_CompleteValidationMsg").item(0).getTextContent();
	                 
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
		 objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
	//	 String strMessage ="";
		 String strTitlecatalog=objAdjunoWFLDestinationQCPostDisposalPOM.callMouseHover(strCatalogFormName, objAdjunoWFLDestinationQCPostDisposalPOM.getLnkTools(), objAdjunoWFLDestinationQCPostDisposalPOM.getLnkCatalog());
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
		 objSoftAssert.assertEquals(bFlag1, true, "Page Title of the Catalog tool is wrong");
			
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getLnklighthouse()),true,"***** In catalog tool \"LightHouse\" button is not clicked *****");

		 objSoftAssert.assertAll();
	 }
	 
	 //clear CatalogField
	 @Test(priority=5)
	 public void clearCatalogField(){
		 SoftAssert objSoftAssert = new SoftAssert();
		 wait(5000);
		 
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** In catalog tool under lighthouse page \"Name\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** In catalog tool under lighthouse page \"Type\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** In catalog tool under lighthouse page \"Description\" Field is not cleared *****");
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** In catalog tool under lighthouse page \"Associated items\" Field is not cleared *****");
		 	
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
		 wait(5000); 
		 if(!isNullOrBlank(strCustomer)){
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strCustomer, strCatalogFormName, "Param_Type"),true,"***** Test ID : 8.002 - In catalog tool under lighthouse page \"Type Field\" value is not entered *****");
		 }else{
			 strMessage=strMessage+"*****Test ID : 8.002 - \"Customer\" Value is Empty in Xml *****";
		 }
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"*****Test ID : 8.002 - In catalog tool under lighthouse page \"Apply\" button is not Clicked *****");
		 wait(5000);
		 catalogCustomerList=objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogData();
		 System.out.println("1. catalogCustomerList-12 "+catalogCustomerList.size());
		 if(catalogCustomerList.size()>0){
				
		 }else{
			 strMessage = strMessage +"*****Test ID : 8.002 - In catalog tool \"Customer\" Type doesn't have data *****";
		 }
		       	
		 //Origin
		 clearCatalogField();
		 //set field value as country
		 if(!isNullOrBlank(strCountry))
		 {
			 wait(5000);
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strCountry, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.003 - In catalog tool under lighthouse page \"Type\" Field value is not entered ****");
			 
		 }else{
			 strMessage = strMessage + "***** Test ID : 8.003 - \"Country\" value empty in xml *****";
		 }
			       
		 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"***** Test ID : 8.003 - In catalog tool under lighthouse page \"Apply\" button is not clicked *****");
		 wait(5000);
			        
		 int nCount3 = objAdjunoWFLDestinationQCPostDisposalPOM.valCount();
	//	 System.out.println(" ncount "+nCount3);
		 catalogCountryList = new ArrayList<String>();
		 catalogCountryList = objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogTableData(nCount3);
		 wait(5000);
		 System.out.println("2.catalogCountryList-236 "+catalogCountryList.size());
		 if(catalogCountryList.size()>0){
		 }else{
		 	strMessage = strMessage +"***** Test ID : 8.003 - \"origin\" Type doesn't have data *****";
		 }
			   
		 //OriginPort
			     
		 //Setting the field value LODOCE setFieldValue
		 if(!isNullOrBlank(strLoCode))
		 {
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 8.004 - In catalog tool under lighthouse page \"Previous\" button is not clicked *****");
			 wait(5000);
			 clearCatalogField();
				wait(5000);
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strLoCode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.004 - In catalog tool under lighthouse page \"Type\" Field value is not entered *****"); 
			 
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"***** Test ID : 8.004 - In catalog tool under lighthouse page \"Apply\" button is not clicked *****");
			 wait(5000);
			                                
			 int nCount4 = objAdjunoWFLDestinationQCPostDisposalPOM.valCount();
			 catalogLoCodeList = new ArrayList<String>();
			// System.out.println("ncount4"+nCount4);
			 catalogLoCodeList = objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogTableData1(nCount4);
			 wait(5000);
		 }else{
			 strMessage = strMessage + "***** Test ID : 8.004 - \"LOCODE\" value empty in xml *****";
		 }
		 System.out.println("3.catalogCountryList-182 "+catalogLoCodeList.size());   

		 if(catalogCountryList.size()>0){   
		 		
		 }else{
			 strMessage = strMessage +"***** Test ID : 8.004 - \"Origin Port\" Type  doesn't have data *****";
		 }

		 //Mode
		    
		 //set the mode value in textbox
	 	 if(!isNullOrBlank(strMode)){
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 8.005 - In catalog tool under lighthouse page \"Previous\" button is not clicked *****");
			 wait(5000);
			 clearCatalogField();
			wait(5000);
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strMode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.005 - In catalog tool under lighthouse page \"Type\" Field value is not entered *****"); 
			
			 wait(5000);
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"***** Test ID : 8.005 - In catalog tool under lighthouse page \"Apply\" button is not clicked *****");
			 wait(5000);
			 catalogModeList = objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogData();
			      
		 }else{
			 strMessage = strMessage + "***** Test ID : 8.005 - \"Mode\" value empty in xml *****";
		 }  
		 System.out.println("4. catalogModeList-7 "+catalogModeList.size());
		 if(catalogModeList.size()>0){
			        	
		 }else{
			 strMessage = strMessage +"***** Test ID : 8.005 - \"Mode\" Type doesn't have data *****";
		 }

			        
		 //set the Vessel value in textbox
		 if(!isNullOrBlank(strVessel)){
			 wait(5000);
			 clearCatalogField();
			 wait(5000);
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strVessel, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.006 -  In catalog tool under lighthouse page \"Type\" Field value is not entered *****");
			
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"***** Test ID : 8.006 - In catalog tool under lighthouse page \"Apply\" button is not clicked *****");
			 wait(5000);
			             
			 int nCount5 = objAdjunoWFLDestinationQCPostDisposalPOM.valCount();
			 catalogVesselList = new ArrayList<String>();
		//	 System.out.println("ncount5 "+nCount5);
			 catalogVesselList = objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogTableData(nCount5);         
		 }else{    	
			 strMessage = strMessage + "***** Test ID : 8.006 - \"Vessel\" value empty in xml *****";
		 }
		 System.out.println("5.catalogVesselList-1757 "+catalogVesselList.size());
		 if(catalogVesselList.size()>0){
			        	
		 }else{
			 strMessage = strMessage +" ***** Test ID : 8.006 - \"Vessel\" Type doesn't have data *****";
		 }
			    
		 //set the Suppliers value in textbox
		 if(!isNullOrBlank(strSupplier)){
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 8.008 - In catalog tool under lighthouse page \"Previous\" button is not clicked *****");
			 wait(5000);
			 clearCatalogField();
				wait(5000);
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.008 - In catalog tool under lighthouse page \"Type\" Field value is not entered *****");
			
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"***** Test ID : 8.008 - In catalog tool under lighthouse page \"Apply\" button is not clicked *****");
		     		
			 catalogSupplierList = objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogSupplierTableData();
		 }else{
			 strMessage = strMessage + "***** Test ID : 8.008 - \"Supplier\" value is empty in xml *****";
		 }
		 	
		System.out.println("6.catalogSupplierList "+catalogSupplierList.size());
		 if(catalogSupplierList.size()>0){
				        	
		 }else{
			 strMessage = strMessage +" ***** Test ID : 8.008 - \"Supplier\" Type doesn't have data *****";
		 }
		 
		//set the Suppliers value in textbox
		 if(!isNullOrBlank(strSupplier)){
		 	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getcatalogbtnPageNext()),true,"***** Test ID : 8.001 - In catalog tool under lighthouse page \"Previous\" button is not clicked *****");
		 	wait(5000);
		 	clearCatalogField();
			wait(5000);
		 	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 8.001 -  In catalog tool under lighthouse page \"Type\" Field value is not entered *****");
		 		
		 	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()),true,"***** Test ID : 8.001 -  In catalog tool under lighthouse page \"Apply\" button is not clicked *****");
		     		
		 	catalogCustSupplierList = objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogTableData(objAdjunoWFLDestinationQCPostDisposalPOM.getLstNameDescription(), objAdjunoWFLDestinationQCPostDisposalPOM.getLstSupplierDescriptionField());
		 }else{
		 	strMessage = strMessage + "***** Test ID : 8.001 - \"Supplier\" value is empty in xml *****";
		 }
		 System.out.println("7.catalogCustSupplierList "+catalogCustSupplierList.size());
		 if(catalogCustSupplierList.size()>0){
				        	
		 }else{
		 	strMessage = strMessage +"***** Test ID : 8.001 - \"Supplier\" Type doesn't have data *****";
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
	 public void test_8d002_VerifyCustomerValues(){	
		 SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage ="";
		 objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		 String strTitle= objAdjunoWFLDestinationQCPostDisposalPOM.callMouseHover(strDestinationQCPostDisposalFormName, objAdjunoWFLDestinationQCPostDisposalPOM.getLnkTools(), objAdjunoWFLDestinationQCPostDisposalPOM.getLnkDestinationQCPostDisposal());
		 boolean bFlag = true;
			
		 if (isNullOrBlank(strTitle))
			 bFlag = false;
			
		 if (!(isNullOrBlank(strTitle)))
		 {
			 if (strTitle.equalsIgnoreCase(strDestinationQCPostDisposalPageTitle))
				 bFlag = true;
			 else	
				 bFlag = false;
		 }       		   
	   
		 objSoftAssert.assertEquals(bFlag, true, "Page Title of the Destination QC Post-Disposal Tool is wrong");
	       
		 customerList = objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_21Customer");

		 if(customerList.size()>0){
				
		 }else{
			 strMessage = strMessage +" In Destination QC Post-Disposal Tool under search chevron \"customer\" Drop drown doesn't have value";
		 }			
		 strMessage= strMessage + objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogCustomerList, customerList);
		 
		 objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.002"+ strMessage +"*****");	
		 objSoftAssert.assertAll();
	 }
	
	//verify the Orgin Dropdown Data
	@Test(priority=20)
	public void test_8d003_VerifyOrginDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		orginList = objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_01Origin");
		
		if(orginList.size()>0){
				
		}else{
			strMessage=strMessage+" In Destination QC Post-Disposal Tool under search chevron \"Origin\" Drop Down doesn't have any Value";
		}
			
		strMessage= strMessage + objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogCountryList, orginList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.003"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//verify Origin port Drop Down Value
	@Test(priority=25)
	public void test_8d004_VerifyOrginPortDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		String strMessage ="";
		originPortList=objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_02OriginPort");
	
		if(originPortList.size()>0){
		}else{
			strMessage=strMessage+" In Destination QC Post-Disposal Tool under search chevron \"Orgin Port\" Drop Down doesn't have any Value";
		}		
		//verify the customer dropdown list into catalog values
			
		strMessage= strMessage + objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogLoCodeList, originPortList);	
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.004"+ strMessage +"*****");
			
		objSoftAssert.assertAll();
			
	}
		
	//verify Mode Drop Down Values
	@Test(priority=30)
	public void test_8d005_VerifyModeDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		String strMessage ="";
		
		modeList=objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_03Mode");

		if(modeList.size()>0){
		}else{
			strMessage=strMessage+" In Destination QC Post-Disposal Tool under search chevron \"Mode\" Drop Down doesn't have any Value";
		}
		//verify the customer dropdown list into catalog values
		strMessage= strMessage + objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogModeList, modeList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.005"+ strMessage +"*****");
		objSoftAssert.assertAll();		
	}
		

	//verify Vessel Drop Down Values
	@Test(priority=35)
	public void test_8d006_VerifyVesselDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		String strMessage ="";
		vesselList=objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_04Vessel");

		if(vesselList.size()>0){
		}else{
			strMessage=strMessage+" In Destination QC Post-Disposal Tool under search chevron \"Vessel\" Drop Down doesn't have any Value";
		}
		//verify the customer dropdown list into catalog values
		strMessage= strMessage + objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogVesselList, vesselList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.006"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//verify Status drop Down Values
	@Test(priority=40)
	public void test_8d007_VerifystatusDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		String strMessage ="";
		statusList=objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_13Status");

		if(statusList.size()==4){
		}else{
			strMessage=strMessage+" In Destination QC Post-Disposal Tool under search chevron \"Status\" Drop Down doesn't have any Value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.007"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//verify the vendor drop down values
	@Test(priority=45)
	public void test_8d008_VerifyVendorDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		String strMessage ="";
		vendorList=objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_05Vendor");

		if(vendorList.size()>0){
		}else{
			strMessage=strMessage+" In Destination QC Post-Disposal Tool under search chevron \"Vendor\" Drop Down doesn't have any Value";
		}
			
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogSupplierList, vendorList);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.008"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//clear the field 
	@Test(priority=50)
	public void clearFields(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_01Origin"), true, "*****  In Destination QC Post-Disposal Tool \"Orgin\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_02OriginPort"), true, "*****  In Destination QC Post-Disposal Tool \"Orgin port\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_03Mode"), true, "*****  In Destination QC Post-Disposal Tool \"mode\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_04Vessel"), true, "***** In Destination QC Post-Disposal Tool \"vessel\" Field is not cleared *****");	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_11ETAFrom"), true, "***** In Destination QC Post-Disposal Tool \"ETA From\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_12ETATo"), true, "***** In Destination QC Post-Disposal Tool \"ETA TO\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_13Status"), true, "***** In Destination QC Post-Disposal Tool \"Status\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_06Container"), true, "***** In Destination QC Post-Disposal Tool \"Container\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_09HouseBill"), true, "***** In Destination QC Post-Disposal Tool \"HouseBill\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_05Vendor"), true, "***** In Destination QC Post-Disposal Tool \"Vendor\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_14CustomerReference"), true, "***** In Destination QC Post-Disposal Tool \"Customer Reference\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_07PO"), true, "***** In Destination QC Post-Disposal Tool \"WFL Reference\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_08Product"), true, "***** In Destination QC Post-Disposal Tool \"Product\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_19URN"), true, "***** In Destination QC Post-Disposal Tool \"UCR/URN\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearInputField(strDestinationQCPostDisposalFormName, "PARAM_21Customer"), true, "***** In Destination QC Post-Disposal Tool \"Customer\" Field is not cleared *****");
			
		objSoftAssert.assertAll();
	}
	
	//Set Order Number Value and Inspect DropDown
	@Test(priority=55)	
	public void test_8d020_SetValueofOrderNumberDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 8.020 - In Destination QC Post-Disposal Tool \"Select\" Chevron is not clicked *****");
		String strMessage ="";
		clearFields();
											
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.020 - In Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered as Any *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
											
		if(!isNullOrBlank(strOrderNumberValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strOrderNumberValue, strDestinationQCPostDisposalFormName,"PARAM_14CustomerReference"), true,"***** Test ID : 8.020 - In Destination QC Post-Disposal Tool under Select chevron \"Order Number\" DropDown value is not Entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
											
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.020 - In Destination QC Post-Disposal Tool under Select chevron \"RefineSearch\" button is not clicked *****");	
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getLnkWFLReference()), true ," Test ID : 8.020 - In Destination QC Post-Disposal Tool under Select chevron \"WflReference\" link is not Clicked *****");
		wait(5000);
		String window= objAdjunoWFLDestinationQCPostDisposalPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()), true , "***** Test ID : 8.020 - In Destination QC Post-Disposal Tool under purchase order page \"Apply\" button is not clicked *****");

		wait(5000);						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getLnkProductCard()), true ," Test ID : 8.020 - In Destination QC Post-Disposal Tool under Purchase Order Page \"ProductCard\" link is not Clicked *****");
		wait(5000);							
		String orderNumber=objAdjunoWFLDestinationQCPostDisposalPOM.getGridCellValue(strDestinationQCPostDisposalWFLRefFormName, "GRID_ProductCard", 0, "CustomerReference");
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(strOrderNumberValue, orderNumber), true , " Test ID : 8.020 - In Destination QC Post-Disposal Tool \"Order Number\" value which was entered under Select chevron is not Matching with the \"Order Number\" value which is found in Purchase Order Page ***** ");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.020"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//set Value to OrginPort DropDown
	@Test(priority=60)
	public void test_8d011_SetOrginPortDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.011 - In Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
									
		if(!isNullOrBlank(strOrginPortValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strOrginPortValue, strDestinationQCPostDisposalFormName,"PARAM_02OriginPort"), true,"***** Test ID : 8.011 - In Destination QC Post-Disposal Tool under Select chevron \"OrginPort\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"OrginPort\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.011 - In Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
								
		List<WebElement> lstWFLReference=objAdjunoWFLDestinationQCPostDisposalPOM.getLstWFLRef();

		for(int i=0;i<lstWFLReference.size();i++){
			lstWFLReference.get(i).click();
			String window= objAdjunoWFLDestinationQCPostDisposalPOM.getWindowIds();
			String [] pageWindow=window.split(";");
			driver.switchTo().window(pageWindow[1]);
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()), true , "***** Test ID : 8.011 - In Destination QC Post-Disposal Tool under Purchase Order Page \"Apply\" button is not clicked *****");
			wait(5000);
			
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(strOrginPortValue, objAdjunoWFLDestinationQCPostDisposalPOM.getFieldValue(strDestinationQCPostDisposalWFLRefFormName, "OriginPort")), true , " Test ID : 8.011 - In Destination QC Post-Disposal Tool \"Origin Port\" value which was entered under Select chevron is not Matching with the \"Origin Port\" value which is found in Purchase Order Page *****");
			driver.close();
			driver.switchTo().window(pageWindow[0]);
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.011"+ strMessage +"*****");
		objSoftAssert.assertAll();
									
	}
	
	//Set the Customer DropDown Values and inspect the result(Logged in Admin)
	@Test(priority=65)
	public void test_8d024_SetCustomerDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();					
		if(!isNullOrBlank(strCustomerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strCustomerValue, strDestinationQCPostDisposalFormName,"PARAM_21Customer"), true,"***** Test ID : 8.024 - In Destination QC Post-Disposal Tool under Select chevron \"Customer\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Customer\" Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.024 - In Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.024 - In Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
								
		List<WebElement> lstWFLReference=objAdjunoWFLDestinationQCPostDisposalPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
		lstWFLReference.get(i).click();
		String window= objAdjunoWFLDestinationQCPostDisposalPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getCatalogbtnApply()), true , "***** Test ID : 8.024 - In Destination QC Post-Disposal Tool under Purchase Order page \"Apply\" button is not clicked *****");
				
		wait(5000);
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(strCustomerValue, objAdjunoWFLDestinationQCPostDisposalPOM.getFieldValue(strDestinationQCPostDisposalWFLRefFormName, "Customer")), true , " Test ID : 8.024 - In Destination QC Post-Disposal Tool \"Customer\" value which was entered under Select chevron is not Matching with the \"Customer\" value which is found in Purchase Order Page *****");
		wait(5000);
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.024"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	
	//login as a customer
	@Test(priority=75)
	public void LoginCustomer(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickLogout(objAdjunoWFLDestinationQCPostDisposalPOM.getLnkLogout()), true , "***** In Destination QC Post-Disposal Tool \"Logout\" button is not Clicked *****");
		objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
		objAdjunoLIMALoginPOM.setUserName(strCustomerUserName);
		objAdjunoLIMALoginPOM.setPassword(strCustomerPassword);
		objAdjunoLIMALoginPOM.clickLogon();
			
	}
	
	//logged as a customer and verify the customer dropdown
	@Test(priority=80)
	public void test_8d009_VerifyCustCustomerDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		String strMessage ="";
		String strTitle= objAdjunoWFLDestinationQCPostDisposalPOM.callMouseHover(strDestinationQCPostDisposalFormName, objAdjunoWFLDestinationQCPostDisposalPOM.getLnkTools(), objAdjunoWFLDestinationQCPostDisposalPOM.getLnkDestinationQCPostDisposal());
		boolean bFlag = true;
				
		if (isNullOrBlank(strTitle))
			bFlag = false;
				
		if (!(isNullOrBlank(strTitle)))
		{
			if (strTitle.equalsIgnoreCase(strDestinationQCPostDisposalPageTitle))
				bFlag = true;
			else	
				bFlag = false;
		}       		   
		 
		objSoftAssert.assertEquals(bFlag, true, "Page Title of the Destination QC Post-Disposal Tool is wrong");
				
		customerList = objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_21Customer");
		if(customerList.size()==1){
					
		}else{
			strMessage = strMessage +" In Destination QC Post-Disposal Tool \"customer\" Drop down doesn't have value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.009"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//verify the vendor drop down values
	@Test(priority=82)
	public void test_6d001_VerifyCustVendorDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPostDisposalPOM=new AdjunoWFLDestinationQCPostDisposalPOM(driver);
		custvendorList=objAdjunoWFLDestinationQCPostDisposalPOM.getDropdownValues(strDestinationQCPostDisposalFormName, "PARAM_05Vendor");
		System.out.println(custvendorList.size());
		if(custvendorList.size()>0){
		}else{
			strMessage=strMessage+"Destination QC Post-Disposal Tool \"Vendor\" Drop Down doesn't have any Value";
		}
							
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifyCatalogData(catalogCustSupplierList, custvendorList);
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.001"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//Set the value To Mode dropdown box and inspect the values
	@Test(priority=85)
	public void test_8d012_SetValueModeDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.012 - Destination QC Post-Disposal Tool under Search chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 8.012 - Destination QC Post-Disposal Tool \"Select\" Chevron is not clicked *****");
		
		clearFields();
		if(!isNullOrBlank(strModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strModeValue, strDestinationQCPostDisposalFormName,"PARAM_03Mode"), true,"***** Test ID : 8.012 - Destination QC Post-Disposal Tool under Select chevron \"Mode\" Field value is not entered as Sea *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strAnyStatusValue)){	
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.012 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.012 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		wait(5000);

		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strModeValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstMode());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.012"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//Set the value in Vessel dropdown  and inspect the values
	@Test(priority=90)
	public void test_8d013_SetValueVesselDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strVesselValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strVesselValue, strDestinationQCPostDisposalFormName,"PARAM_04Vessel"), true,"***** Test ID : 8.013 - Destination QC Post-Disposal Tool under Select chevron \"Vessel\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		 }
					
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.013 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.013 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
			
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strVesselValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstVessel());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.013"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority=92)
	public void test_8d014_SetValueETAFromCalender() throws ParseException{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.014 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLETAFromDate)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLETAFromDate, strDestinationQCPostDisposalFormName,"PARAM_11ETAFrom"), true,"***** Test ID : 8.014 - Destination QC Post-Disposal Tool under Select chevron \"ETAFrom\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFL Reference\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.014 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostDisposalPOM.compareFromDates(strWFLETAFromDate, objAdjunoWFLDestinationQCPostDisposalPOM.getLstETAFromDate());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.014"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority=94)
	public void test_8d015_SetValueETAToCalender() throws ParseException{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.015 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLETAToDate)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLETAToDate, strDestinationQCPostDisposalFormName,"PARAM_12ETATo"), true,"***** Test ID : 8.015 - Destination QC Post-Disposal Tool under Select chevron \"ETATo\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFL Reference\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.015 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostDisposalPOM.compareToDates(strWFLETAToDate, objAdjunoWFLDestinationQCPostDisposalPOM.getLstETAToDate());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.015"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
		
	//Set the value in status dropdown  and inspect the values
	@Test(priority=95)
	public void test_8d016_SetValueStatusDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		
		wait(5000);
		clearFields();	
		if(!isNullOrBlank(strCompleteStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strCompleteStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.016 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.016 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
	
		wait(5000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strCompleteStatusValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstStatus());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.016"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	//Set the value in Container dropdown  and inspect the values
	@Test(priority=100)
	public void test_8d017_SetValueContainerDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strContainerValue, strDestinationQCPostDisposalFormName,"PARAM_06Container"), true,"***** Test ID : 8.017 - Destination QC Post-Disposal Tool under Select chevron \"Container\" Field value is not entered *****");
		 }else{
		 	strMessage=strMessage+"\"Container\" Value is Empty in XML";
		 }
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.017 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.017 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
	
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strContainerValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstContainer());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.017"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
		
	//Set the value in HouseBill dropdown  and inspect the values
	@Test(priority=105)
	public void test_8d018_SetValueHouseBillDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strHouseBillValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strHouseBillValue, strDestinationQCPostDisposalFormName,"PARAM_09HouseBill"), true,"***** Test ID : 8.018 - Destination QC Post-Disposal Tool under Select chevron \"House Bill\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Housebill\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.018 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.018 - Destination QC Post-Disposal Tool under Select chevron \"RefineSearch\" button is not clicked *****");
	
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strHouseBillValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstHouseBill());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.018"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
		
	//Set the value in Vendor dropdown  and inspect the values
	@Test(priority=110)
	public void test_8d019_SetValueVendorDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strVendorValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strVendorValue, strDestinationQCPostDisposalFormName,"PARAM_05Vendor"), true,"***** Test ID : 8.019 - Destination QC Post-Disposal Tool under Select chevron \"Vendor\" Field value is not entered*****");
		}else{
			strMessage=strMessage+"\"Vendor\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.019 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.019 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
	
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strVendorValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstVendor());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.019"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
		
	//Set the value in product dropdown  and inspect the values
	@Test(priority=115)
	public void test_8d022_SetValueProductDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strProductValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strProductValue, strDestinationQCPostDisposalFormName,"PARAM_08Product"), true,"***** Test ID : 8.022 - Destination QC Post-Disposal Tool under Select chevron \"Product\" Field value is not entered*****");
		}else{
			strMessage=strMessage+"\"Product\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.022 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.022 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strProductValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstProduct());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.022"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
		
	//Set the value in WFLRef dropdown  and inspect the values
	@Test(priority=120)
	public void test_8d021_SetValueWFLRefDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLRefValue, strDestinationQCPostDisposalFormName,"PARAM_07PO"), true,"***** Test ID : 8.021 - Destination QC Post-Disposal Tool under Select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
							
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.021 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
							
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.021 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
	
		strMessage = objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strWFLRefValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstWFLRef());
							
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.021"+ strMessage +"*****");
		objSoftAssert.assertAll();
							
	}
	
	//Check the duplicate value is present or not
	@Test(priority=125)
	public void test_8d025_CheckDuplicateValuePresent(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.025 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strModeAirValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strModeAirValue, strDestinationQCPostDisposalFormName,"PARAM_03Mode"), true,"***** Test ID : 8.025 - Destination QC Post-Disposal Tool under Select chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.025 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostDisposalPOM.duplicateValue(objAdjunoWFLDestinationQCPostDisposalPOM.getLstContainer());
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostDisposalPOM.duplicateValue(objAdjunoWFLDestinationQCPostDisposalPOM.getLstMode());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.025"+ strMessage +"*****");
			
		objSoftAssert.assertAll();
			
	}
		
	//Search for pre inspection Status value
	@Test(priority=130)
	public void test_8d026_VerifyinspectionRequiredField(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strCompleteStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strCompleteStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.026 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
									
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.026 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		
		strMessage =strMessage +objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strCompleteStatusValue, objAdjunoWFLDestinationQCPostDisposalPOM.getLstStatus());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.026"+ strMessage +"*****");
			
		objSoftAssert.assertAll();
			
	}
	
	//Search the product for No Inspection Required
	@Test(priority=135)
	public void test_8d027_NoInspectionRequiredValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		String strValidationMsg="";
		clearFields();
						
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.027 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strNoInspectionRequiredValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strNoInspectionRequiredValue, strDestinationQCPostDisposalFormName,"PARAM_09HouseBill"), true,"***** Test ID : 8.027 - Destination QC Post-Disposal Tool under Select chevron \"HouseBill\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLProductDespatched\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.027 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
						
		strValidationMsg=strValidationMsg + objAdjunoWFLDestinationQCPostDisposalPOM.getStrValidationMsg().getText();
		objSoftAssert.assertEquals(strValidationMsg,StrValidationMessage, " ***** Test ID : 8.027 - Destination QC Post-Disposal Tool under Select chevron \"No Inspection Required\" Product is Displaying *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.027"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
	
	//Set the new value of Corresponding field and inspect the Refine Search values
	@Test(priority=140)
	public void test_8d028_VerifyRefineSearchResult(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
	//	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 7.028 - Select Chevron is not clicked *****");

		clearFields();
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.028 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strModeAirValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strModeAirValue, strDestinationQCPostDisposalFormName,"PARAM_03Mode"), true,"***** Test ID : 8.028 - Destination QC Post-Disposal Tool under Select chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strContainerValue, strDestinationQCPostDisposalFormName,"PARAM_06Container"), true,"***** Test ID : 8.028 - Destination QC Post-Disposal Tool under Select chevron \"Container\" Field value is not entered*****");
		 }else{
		 	strMessage=strMessage+"\"Container\" Value is Empty in XML";
		 }
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.028 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");
		wait(5000);
			
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strModeValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstMode());
		
		strMessage=strMessage+objAdjunoWFLDestinationQCPostDisposalPOM.verifySearchValue(strContainerValue,objAdjunoWFLDestinationQCPostDisposalPOM.getLstContainer());
	
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.028"+ strMessage +"*****");			
		objSoftAssert.assertAll();
				
	}
	
	//Inspect form Values in Post Disposal Stage
	@Test(priority=145)
	public void test_8d029_InspectFormValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		boolean bFlag;
		wait(4000);
//		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 7.028 - Select Chevron is not clicked *****");

		clearFields();
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.029 - Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLRefValue, strDestinationQCPostDisposalFormName,"PARAM_07PO"), true,"***** Test ID : 8.029 - Destination QC Post-Disposal Tool under Select chevron \"WFL RefGrid\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFL Reference\" Value is Empty in XML";
		}
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.029 - Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostDisposal>();
		
		if ( objAdjunoWFLDestinationQCPostDisposalPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostDisposalPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvPostDisposalChecks()),true,"***** Test ID : 8.029 - Destination QC Post-Disposal Tool \"PostDisposal\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.029 - Destination QC Post-Disposal Tool under Select chevron \"No Records are found\" *****");
		}
		wait(5000);
		if (objAdjunoWFLDestinationQCPostDisposalPOM.verifyPageIsLoaded(strDestinationQCPostDisposalFormName)) 
		{
		//	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.checkDoesElementExist(strDestinationQCPostDisposalGridFormName,"Grid1"),true,"***** Test ID : 8.029 - HBL Grid is not dispalyed on the PostDisposal Page page*****");
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 8.029 - Destination QC Post-Disposal Tool \"PostDisposal\" page is not loaded *****");
		}
		wait(5000);
		if(bSearchResultsProductsFound)
		{
			String strReturnMessage = objAdjunoWFLDestinationQCPostDisposalPOM.verifyProductsDataOnGrid(strDestinationQCPostDisposalGridFormName,"Grid1",lstSearchResults);
				
			objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.029 - Destination QC Post-Disposal Tool \"Products\" which was displayed under select chevron is not Matching with \"Products\" which is present under Post-Disposal Grid *****");
							  
		}else{
			
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.029 - Destination QC Post-Disposal Tool under Select chevron \"No Products are found\" *****");
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.029"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Select the value from Disposal Date and Time Date Picker
	@Test(priority=150)
	public void test_8d030_SelectDisposalDateTime(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 8.030 - In Destination QC Post-Disposal \"Select\" Chevron is not clicked *****");
		
		clearFields();
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLRefValue, strDestinationQCPostDisposalFormName,"PARAM_07PO"), true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Select chevron \"WFL Reference\' Grid Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFL Reference\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostDisposal>();
		
		if ( objAdjunoWFLDestinationQCPostDisposalPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostDisposalPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvPostDisposalChecks()),true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"PostDisposal\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Select chevron \"No Products are found\" *****");
		}
		
		if (objAdjunoWFLDestinationQCPostDisposalPOM.verifyPageIsLoaded(strDestinationQCPostDisposalFormName)) 
		{
		//	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.checkDoesElementExist(strDestinationQCPostDisposalGridFormName,"Grid1"),true,"***** Test ID : 8.030 - Post Disposal Grid is not dispalyed *****");
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"PostDisposal\" Chevron page is not loaded*****");
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getClkDisposalDateTime()), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" Field is not clicked *****");
		
		//Set the Past Value 
		strPastDate = objAdjunoWFLDestinationQCPostDisposalPOM.getDate(-3,"dd MMM yyyy HH:MM");
		System.out.println("strPastDate "+strPastDate);
		wait(5000);
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalDate", strPastDate), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" Field value is not entered *****");
		wait(5000);
		
		strWFLDisposalCertNumber= objAdjunoWFLDestinationQCPostDisposalPOM.randomIntegerNo();
		System.out.println(strWFLDisposalCertNumber);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalCertNo", strWFLDisposalCertNumber), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Cert No\" Field value is not entered any Value *****");
//		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalCertNo", strWFLDisposalCertNumber), true , "***** Test ID : 8.030 - Disposal Date/Time Date Picked Field is not entered any Value *****");
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvComplete()), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"Complete\" Chevron is not clicked *****");
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSearchAgain()), true , "***** Test ID : 8.030 -In Destination QC Post-Disposal Tool under Complet chevron \"Search Again\" Button is not clicked *****");
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"Select\" Chevron is not clicked *****");
		
		lstSearchResults = new ArrayList<DestinationQCPostDisposal>();
		
		if ( objAdjunoWFLDestinationQCPostDisposalPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostDisposalPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvPostDisposalChecks()),true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"PostDisposal\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Select chevron \"No Products are found\" *****");
		}
		
		if (objAdjunoWFLDestinationQCPostDisposalPOM.verifyPageIsLoaded(strDestinationQCPostDisposalFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.checkDoesElementExist(strDestinationQCPostDisposalGridFormName,"Grid1"),true,"***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Post Disposal Grid\" is not dispalyed *****");
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"PostDisposal\" Chevron page is not loaded *****");
		}
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(strPastDate, objAdjunoWFLDestinationQCPostDisposalPOM.getGridCellValue(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalDate")), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"Disposal Date/Time\" field value which was entered under \"Post-Disposal\" chevron is not Saved *****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getClkDisposalDateTime()), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time Date\" Field is not clicked *****");
		
		//Set the future date
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getClkDisposalDateTime()), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\"Field is not clicked *****");
		strFutureDate = objAdjunoWFLDestinationQCPostDisposalPOM.getDate(1, "dd MMM yyyy HH:MM");
		System.out.println("strFutureDate "+strFutureDate);
		wait(5000);
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalDate", strFutureDate), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" Field value is not entered *****");
		wait(5000);
		strWFLDisposalCertNumber =objAdjunoWFLDestinationQCPostDisposalPOM.randomIntegerNo();
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalCertNo", strWFLDisposalCertNumber), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Cert No\" Field value is not entered *****");
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvComplete()), true , "***** Test ID : 8.030 - In Destination QC Post-Disposal Tool \"Complete\" Chevron is not clicked *****");
		wait(4000);
		gridValidationMessage =objAdjunoWFLDestinationQCPostDisposalPOM.getValidationMessageOtherthenField(objAdjunoWFLDestinationQCPostDisposalPOM.getStrGridValidationMsg());
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, gridValidationMessage), true , "*****  Test ID : 8.0030 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" field is accepted the future Date also *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.030"+ strMessage +"*****");
		objSoftAssert.assertAll();
	
	}
	
	//Check the Disposal CertificateNumber Field is Mandatory
	@Test(priority=155)
	public void test_8d031_CheckMandatoryField(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		boolean bFlag;
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()),true , "*****  Test ID : 8.031 - In Destination QC Post-Disposal Tool \"Select\" Chevron is not clicked *****");
		
		clearFields();
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Select Chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strWFLRefDisposalMandatoryField)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLRefDisposalMandatoryField, strDestinationQCPostDisposalFormName,"PARAM_07PO"), true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Select Chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFL Reference\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Select Chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostDisposal>();
		
		if ( objAdjunoWFLDestinationQCPostDisposalPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostDisposalPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvPostDisposalChecks()),true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool \"PostDisposal\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Select Chevron \"No Products are found\" *****");
		}
		
		if (objAdjunoWFLDestinationQCPostDisposalPOM.verifyPageIsLoaded(strDestinationQCPostDisposalFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.checkDoesElementExist(strDestinationQCPostDisposalGridFormName,"Grid1"),true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Select Chevron \"Post Disposal\" Grid is not dispalyed *****");
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 8.031 - In Destination QC Post-Disposal Tool \"PostDisposal\" Chevron page is not loaded*****");
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getDisposalCertNo()), true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Cert No\" field is not clicked *****");
		wait(3000);
//		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clearGridInputField(objAdjunoWFLDestinationQCPostDisposalPOM.getDisposalCertNo()), true,"***** Test ID : 8.031 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Cert No\" field is not cleared *****");
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvComplete()), true , "***** Test ID : 8.031 - In Destination QC Post-Disposal Tool \"Complete\" Chevron is not clicked *****");

		gridValidationMessage =objAdjunoWFLDestinationQCPostDisposalPOM.getValidationMessageOtherthenField(objAdjunoWFLDestinationQCPostDisposalPOM.getStrGridValidationMsg());
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, gridValidationMessage), true , "*****  Test ID : 8.031 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Cert No\" field manditory message is not matching *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.031"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//inspect Search Again Button Present or not
	@Test(priority=160)
	public void test_8d032_InspectSearchAgainButton(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true,"***** Test ID : 8.032 - In Destination QC Post-Disposal Tool \"Select\" Chevron is not clicked *****");
		clearFields();
							
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strWFLRefDisposalCertNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLRefDisposalCertNumber, strDestinationQCPostDisposalFormName,"PARAM_07PO"), true,"***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFL Reference\" Value is Empty in XML";
		}
							
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Select chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostDisposal>();
						        
		if ( objAdjunoWFLDestinationQCPostDisposalPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostDisposalPOM.selectMulitpleProducts(1);
								   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvPostDisposalChecks()),true,"***** Test ID : 8.032 - In Destination QC Post-Disposal Tool \"PostDisposal\" chevron is not clicked *****");
									             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Select chevron \"No Products are found\" *****");
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getClkDisposalDateTime()), true , "***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" field is not clicked *****");
		strPastDate = objAdjunoWFLDestinationQCPostDisposalPOM.getDate(-1," dd MMM yyyy HH:MM");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalDate", strPastDate), true , "***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" Field value is not entered *****");
		wait(5000);
		objAdjunoWFLDestinationQCPostDisposalPOM.randomIntegerNo();
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalCertNo", strWFLDisposalCertNumber), true , "***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Post-Disposal chevron \"Disposal Date/Time\" Field value is not entered *****");
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvComplete()), true , "***** Test ID : 8.032 - In Destination QC Post-Disposal Tool \"Complete\" Chevron is not clicked *****");
		wait(5000);
		if(objAdjunoWFLDestinationQCPostDisposalPOM.checkDoesChevronExist(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSearchAgain())){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSearchAgain()), true, "***** Test ID : 8.032 - In Destination QC Post-Disposal Tool under Complete chevron \"Search Again\" button is not clicked *****");

		}else{
			strMessage = strMessage+" In Destination QC Post-Disposal Tool under Complete chevron \"Search Again\" button is not present ";
		}
				
		if (objAdjunoWFLDestinationQCPostDisposalPOM.verifyPageIsLoaded(strDestinationQCPostDisposalFormName)) {
			
		//	objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.checkDoesElementExist(strDestinationQCPostDisposalFormName,"Panel1"),true,"***** Test ID : 8.032 - Search Page is not loaded*****");
			         
		}else{
			boolean	bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, " In Destination QC Post-Disposal Tool \"Search page\" is not loaded ");
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.032"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//click Complete from a fully completed form
	@Test(priority=165)
	public void test_8d033_InspectCompletionStage(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvSelect()), true , "***** Test Id: 8.033 - In Destination QC Post-Disposal Tool \"Select\" chevron is not clicked *****");
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strAnyStatusValue, strDestinationQCPostDisposalFormName,"PARAM_13Status"), true,"***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strWFLRefDisposalCertNumber)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValue(strWFLRefDisposalCertNumber, strDestinationQCPostDisposalFormName,"PARAM_07PO"), true,"***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under select chevron \"WFL Ref\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"WFLRef\" Value is Empty in XML";
		}
								
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getChvRefineSearch()), true,"***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under select chevron \"Refine Search\" button is not clicked *****");

		lstSearchResults = new ArrayList<DestinationQCPostDisposal>();
						        
		if ( objAdjunoWFLDestinationQCPostDisposalPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPostDisposalPOM.selectMulitpleProducts(1);
								   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvPostDisposalChecks()),true,"***** Test ID : 8.033 - In Destination QC Post-Disposal Tool \"PostInspection\" chevron is not clicked *****");
									             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under select chevron \"No Products are found\" *****");
		}

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPostDisposalPOM.getClkDisposalDateTime()), true , "***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under PostDisposal chevron \"Disposal Date/Time\" Field is not clicked *****");
		
		strPresentDate = objAdjunoWFLDestinationQCPostDisposalPOM.getDate(0," dd MMM yyyy HH:MM");
		System.out.println("strPresentDate "+strPresentDate);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalDate", strPresentDate), true , "***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under PostDisposal chevron \"Disposal Date/Time\" Field value is not entered *****");
		strWFLDisposalCertNumber =objAdjunoWFLDestinationQCPostDisposalPOM.randomIntegerNo();
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.setFieldValueForGridCell(strDestinationQCPostDisposalGridFormName, "Grid1", 0, "DisposalCertNo", strWFLDisposalCertNumber), true , "***** Test ID : 8.033 - In Destination QC Post-Disposal Tool under PostDisposal chevron \"Disposal Certificate\" field value is not entered *****");
		wait(1200);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.clickChevron(objAdjunoWFLDestinationQCPostDisposalPOM.getChvComplete()), true , "***** Test ID : 8.033 - In Destination QC Post-Disposal Tool \"Complete\" Chevron is not clicked *****");
		wait(1200);
		
		completeValidationMessage =objAdjunoWFLDestinationQCPostDisposalPOM.getElemetValue(strDestinationQCPostDisposalCompleteValidationMsgFormName, "UpdateMessage");
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPostDisposalPOM.compareTwoStringValuesToSame(completeValidationMessage, strWFLGridCompleteValidationMessageValue), true ,"***** Test ID : 8.033 - In Destination QC Post-Disposal Tool \"Completion message\" which is displayed under Complete chevron is not Verified *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 8.033"+ strMessage +"*****");
		objSoftAssert.assertAll();
	 
	}
		
	@AfterTest
	public void closeBrwser(){
		driver.close();
	}

}