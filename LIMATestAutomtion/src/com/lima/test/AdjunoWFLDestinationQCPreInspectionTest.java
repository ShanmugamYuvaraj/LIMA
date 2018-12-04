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

import com.lima.dto.DestinationQCPreInspection;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoWFLDestinationQCPreInspectionPOM;

public class AdjunoWFLDestinationQCPreInspectionTest {
	 WebDriver driver;
	    
	 long nPage_Timeout = 0;
	 String strTestURL;
	    
	 String strLIMAUserName;
	 String strLIMAPassword;
	 String strCustomerUserName;
	 String strCustomerPassword;
	 String strDestinationWFLRefLinkQCFormName;
	 
	 boolean bSearchResultsProductsFound = true;
	 ArrayList<DestinationQCPreInspection> lstSearchResults;
	 
	 AdjunoLIMALibrary objAdjunoLIMALibrary;
	 AdjunoUILibrary objAdjunoUILibrary;
	 AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
	 
	 AdjunoWFLDestinationQCPreInspectionPOM objAdjunoWFLDestinationQCPreInspectionPOM;
	 
	 String strPreInspectionFormName;
	 String strDestinationQcpreinspectionTitle;
	 String strCatalogFormName;
	 String strCatalogPageTitle;
	 String strDestinationQCFormName;
	 
	 String strSupplier ="";
	 String strCustomer ="";
	 String strCountry ="";
	 String strLoCode ="";
	 String strMode ="";
	 String strVessel ="";
	 
	 String strModeValue ="";
	 String strVesselValue ="";
	 String strStatusValue ="";
	 String strContainerValue ="";
	 String strHouseBillValue ="";
	 String strVendorValue ="";
	 String strProductValue ="";
	 String strWFLRefValue ="";
	 String strCompleteStatusValue ="";
	 String strAnyStatusValue ="";
	 String strInspectionRequiredStatusValue ="";
	 String strWFLRefGridValue ="";
	 String strWFLRefGridCertAValue ="";
	 String strWFLFieldValueY ="";
	 String strWFLFieldValueN ="";
	 String strWFLProductDespatched ="";
	 String strValidationMessage ="";
	 String strWFLRefCustomerValue ="";
	 String strWFLOrderNumberValue ="";
	 String strWFLOrginPortValue =""; 
	 String strWFLETAFromDate ="";
	 String strWFLETAToDate ="";
	 String strDespatchedContainer = "";
	 
	 ArrayList<String> catalogSupplierList;
	 ArrayList<String> catalogCountryList;
	 ArrayList<String> catalogCustomerList;
	 ArrayList<String> catalogLoCodeList;
	 ArrayList<String> catalogModeList;
	 ArrayList<String> catalogVesselList;
	 ArrayList<String> catalogCustSupplierList;
	 
	 NodeList searchParams;
	 NodeList nlSearchParamsDestinationQCPreInspection;
	 NodeList nlSearchParamsCatalog;

	 ArrayList<String> customerList;
	 ArrayList<String> orginList;
	 ArrayList<String> originPortList;
	 ArrayList<String> modeList;
	 ArrayList<String> vesselList;
	 ArrayList<String> statusList;
	 ArrayList<String> vendorList;
	 ArrayList<String> custvendorList;
	
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
	            
	        Node DestinationQcpreinspectionFormName = (Node) xPath.evaluate("/config/WFL/Form_Name_Destination_QC_PreInspection", dDoc, XPathConstants.NODE);
	        strPreInspectionFormName = DestinationQcpreinspectionFormName.getTextContent();
	            
	        Node DestinationQcpreinspectionTitle=(Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Destination_QC_PreInspection", dDoc, XPathConstants.NODE);
	        strDestinationQcpreinspectionTitle=DestinationQcpreinspectionTitle.getTextContent();
	            
	        Node CatalogPageTitle=(Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
	        strCatalogPageTitle=CatalogPageTitle.getTextContent();
	            
	        Node CatalogFormName=(Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	        strCatalogFormName=CatalogFormName.getTextContent();
	        
	        Node DestinationQCFormName = (Node) xPath.evaluate("/config/WFL/WFL_DestinationQC_FormName", dDoc,XPathConstants.NODE);
	        strDestinationQCFormName = DestinationQCFormName .getTextContent();
	        
	        Node DestinationQCWFLRefFormName = (Node) xPath.evaluate("/config/WFL/WFL_Destination_QC_PreInspection_WFLRef_FormName", dDoc,XPathConstants.NODE);
	        strDestinationWFLRefLinkQCFormName = DestinationQCWFLRefFormName .getTextContent();
	            
	        
	         
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
	            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getstrWFLDestinationQCPreInspection());
	            
	            XPath xPath1 = XPathFactory.newInstance().newXPath();
	            
	            nlSearchParamsCatalog = (NodeList) xPath1.evaluate("/Catalog/SearchParams", dDoc1, XPathConstants.NODESET);
	            
	            nlSearchParamsDestinationQCPreInspection=(NodeList) xPath1.evaluate("/Catalog/Destination_QC_PreInspection/SearchParams", dDoc1, XPathConstants.NODESET);
	            
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
	            for(int i=0; i <= nlSearchParamsDestinationQCPreInspection.getLength() -1; i++)
	             {
	                 if (nlSearchParamsDestinationQCPreInspection.item(i).getNodeType() == Node.ELEMENT_NODE)
	                 {
	                     ele = (Element) nlSearchParamsDestinationQCPreInspection.item(i);
	                     strModeValue = ele.getElementsByTagName("Mode").item(0).getTextContent(); 
	                     
	                     strVesselValue = ele.getElementsByTagName("Vessel").item(0).getTextContent(); 
	                     
	                     strStatusValue = ele.getElementsByTagName("Status_Awaiting").item(0).getTextContent(); 
	                     
	                     strContainerValue = ele.getElementsByTagName("Container").item(0).getTextContent(); 
	                     
	                     strHouseBillValue = ele.getElementsByTagName("House_Bill").item(0).getTextContent(); 
	                     
	                     strVendorValue = ele.getElementsByTagName("Vendor").item(0).getTextContent(); 
	                     
	                     strProductValue = ele.getElementsByTagName("Product").item(0).getTextContent(); 
	                     
	                     strWFLRefValue = ele.getElementsByTagName("WFL_Ref").item(0).getTextContent(); 
	                     
	                     strCompleteStatusValue = ele.getElementsByTagName("Status_Complete").item(0).getTextContent(); 
	                     
	                     strAnyStatusValue = ele.getElementsByTagName("Status_Any").item(0).getTextContent(); 
	                     
	                     strInspectionRequiredStatusValue = ele.getElementsByTagName("Status_InspectionRequired").item(0).getTextContent(); 
	                     
	                     strWFLRefGridValue = ele.getElementsByTagName("WFL_RefGrid_S").item(0).getTextContent(); 
	                     
	                     strWFLRefGridCertAValue = ele.getElementsByTagName("WFL_RefGrid_A").item(0).getTextContent(); 
	                     	                     	                     
	                     strWFLFieldValueY = ele.getElementsByTagName("WFL_Field_Value_Y").item(0).getTextContent(); 
	                     
	                     strWFLFieldValueN = ele.getElementsByTagName("WFL_Field_Value_N").item(0).getTextContent(); 
	                     	                     
	                     strWFLProductDespatched = ele.getElementsByTagName("WFL_Product_Despatched").item(0).getTextContent(); 
	                     
	                     strValidationMessage = ele.getElementsByTagName("WFL_ValidationMsg").item(0).getTextContent();
	                     
	                     strWFLRefCustomerValue=ele.getElementsByTagName("Customer").item(0).getTextContent();
	                     
	                     strWFLOrderNumberValue=ele.getElementsByTagName("Order_Number").item(0).getTextContent();
	                     
	                     strWFLOrginPortValue=ele.getElementsByTagName("Origin_Port").item(0).getTextContent();
	                     
	                     strWFLETAFromDate=ele.getElementsByTagName("ETD_From").item(0).getTextContent();
           
	                     strWFLETAToDate=ele.getElementsByTagName("ETD_To").item(0).getTextContent();
	                     
	                     strDespatchedContainer=ele.getElementsByTagName("Despatched_container").item(0).getTextContent();

	                     
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
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strTitlecatalog=objAdjunoWFLDestinationQCPreInspectionPOM.callMouseHover(strCatalogFormName, objAdjunoWFLDestinationQCPreInspectionPOM.getlnkTools(), objAdjunoWFLDestinationQCPreInspectionPOM.getLnkCatalog());
		boolean bFlag= true;
	        
		if (isNullOrBlank(strTitlecatalog))
			bFlag = false;
	        
		if (!(isNullOrBlank(strTitlecatalog)))
		{
			if (strTitlecatalog.equalsIgnoreCase(strCatalogPageTitle))
				bFlag = true;
			else
				bFlag = false;
		}    
		objSoftAssert.assertEquals(bFlag, true, "***** Page title of Catalog Tool is wrong  *****");
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getLnklighthouse()),true,"***** LightHouse button is not clicked *****");

		objSoftAssert.assertAll();
	}
	
	//clear CatalogField
	@Test(priority=5)
	public void clearCatalogField(){
		SoftAssert objSoftAssert = new SoftAssert();
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** In Catalog Tool Under lighthouse page \"Name\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** In Catalog Tool Under lighthouse page \"Name\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** In Catalog Tool Under lighthouse page \"Description\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** In Catalog Tool Under lighthouse page \"Associated Item\" field is not cleared *****");
		
		objSoftAssert.assertAll();
	}
	
	 // get the catalog values on different field 
	 @Test(priority=10)
	 public void getCatalogValues()
	 {
		 SoftAssert objSoftAssert = new SoftAssert();
		 String strMessage ="";
		
		 clearCatalogField(); 
		 if(!isNullOrBlank(strCustomer)){
			 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strCustomer, strCatalogFormName, "Param_Type"),true,"*****  Test ID : 6.002 - In Catalog Tool Under lighthouse page \"Type\" field value is not entered as Customer *****");
		 }else{
			 strMessage=strMessage+"\"Customer\" Value is Empty in Xml";
		 }
	     objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"*****  Test ID : 6.002 - In Catalog Tool Under lighthouse page \"Apply\" button is not clicked *****");
	     wait(2000);
	     catalogCustomerList=objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogData();

	     if(catalogCustomerList.size()>0){
			
		 }else{
			 strMessage = strMessage +" *****  Test ID : 6.002 - In Catalog Tool Under lighthouse page \"Customer Type\" won't have data";
		 } 
	
	     //set field value as country
	     clearCatalogField();
	     if(!isNullOrBlank(strCountry))
	     {
	    	 wait(3000);
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strCountry, strCatalogFormName,"Param_Type"), true,"*****  Test ID : 6.003 - In Catalog Tool Under lighthouse page \"Type\" field value is not entered as Country *****");
	    	
	     }else{
	    	 strMessage = strMessage + "***** Test ID : 6.003 -\"Country\" Type value is Empty in xml *****";
	     }
		       
	     objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"*****  Test ID : 6.003 - In Catalog Tool Under lighthouse page \"Apply\" button is not clicked *****");
	     wait(5000);
		        
	     int nCount3 = objAdjunoWFLDestinationQCPreInspectionPOM.valCount();
	     catalogCountryList = new ArrayList<String>();
	     catalogCountryList = objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogTableData(nCount3);
	     wait(2000);
		     
	     if(catalogCountryList.size()>0){
	     }else{
	    	 strMessage = strMessage +" *****  Test ID : 6.003 - In Catalog Tool Under lighthouse page \"Origin Type\" won't have data";
	     }
    
	     //Setting the field value LODOCE setFieldValue
	     if(!isNullOrBlank(strLoCode))
	     {
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnPagePrev()),true,"***** Test ID : 6.004 - In Catalog Tool under lighthouse page \"Previous Button\" is not clicked *****");
	    	 wait(4000);
	    	 clearCatalogField();
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strLoCode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 6.004 - In Catalog Tool under lighthouse page \"Type Field\" value is not entered as LOCODE *****"); 
	    	
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"***** Test ID : 6.004 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
	    	 wait(5000);
		                                
	    	 int nCount4 = objAdjunoWFLDestinationQCPreInspectionPOM.valCount();
	    	 catalogLoCodeList = new ArrayList<String>();
	    	 catalogLoCodeList = objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogLOCODETableData(nCount4);
	    	 wait(2000);
		       
		   	}else{
		        	
		   		strMessage = strMessage + "***** Test ID : 6.004 - \"LOCODE\" value is Empty in xml *****";
		   	}
	   
	     if(catalogLoCodeList.size()>0){
		        	
	     }else{
	    	 strMessage = strMessage +" *****  Test ID : 6.004 - In Catalog Tool Under lighthouse page \"LOCODE Type\" won't have data";
	     }
	    
	     //set the mode value in textbox
	     if(!isNullOrBlank(strMode)){
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnPagePrev()),true,"***** Test ID : 6.005 - In Catalog Tool under lighthouse page \"Previous\" Button is not clicked *****");
	    	 wait(3000);
	    	 clearCatalogField();
	    	 wait(3000);
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strMode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 6.005 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as mode *****"); 
	    	
	    	 wait(5000);
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"***** Test ID : 6.005 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
	    	 wait(5000);
	    	 catalogModeList = objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogData();
		      
	     }else{
	    	 strMessage = strMessage + "***** Test ID : 6.005 - \"Mode\" Type Field is Empty in xml*****";
	     }       
	     if(catalogModeList.size()>0){
		        	
	     }else{
	    	 strMessage = strMessage +" *****  Test ID : 6.005 - In Catalog Tool Under lighthouse page \"Mode Type\" won't have data";
	     }
		        
	     //set the Vessel value in textbox
	     if(!isNullOrBlank(strVessel)){
		        
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnPagePrev()),true,"***** Test ID : 6.006 - In Catalog Tool under lighthouse page \"Previous\" Button is not clicked *****");
	    	 wait(3000);
	    	 clearCatalogField();
	    	 wait(5000);
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strVessel, strCatalogFormName,"Param_Type"), true,"***** Test ID : 6.006 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as Vessel*****");
	    	
	    	 objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"***** Test ID : 6.006 - Apply button is not clicked *****");
	    	 wait(5000);
		             
	    	 int nCount5 = objAdjunoWFLDestinationQCPreInspectionPOM.valCount();
	    	 catalogVesselList = new ArrayList<String>();
	    	 catalogVesselList = objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogTableData(nCount5);
	    	 wait(2000);         
		 	}else{    	
		 		strMessage = strMessage + "***** Test ID : 6.006 - \"Vessel\" Type is Empty in xml *****";
		 	}
	     if(catalogVesselList.size()>0){
		        	
	     }else{
	    	 strMessage = strMessage +" *****  Test ID : 6.006 - In Catalog Tool Under lighthouse page \"Vessel Type\" won't have data";
	     }
			     
	     //set the Suppliers value in textbox
	     if(!isNullOrBlank(strSupplier)){
			        	
	     		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnPagePrev()),true,"***** Test ID : 6.008 - In Catalog Tool under lighthouse page \"Previous\" Button is not clicked *****");
	     		wait(3000);
	     		clearCatalogField();
	     		wait(5000);
	     		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 6.001 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as supplier*****");
	     		
	     		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"***** Test ID : 6.001 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
	     		wait(4000);
	     		catalogSupplierList = objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogSupplierTableData();
	     		
	     	}else{
			        	
	     		strMessage = strMessage + "***** Test ID : 6.001 - \"Supplier\" Type value is Empty in xml *****";
	     	}
	     	if(catalogSupplierList.size()>0){
			        	
	     	}else{
	     		strMessage = strMessage +" *****  Test ID : 6.001 - In Catalog Tool Under lighthouse page \"Supplier Type\" won't have data";
	     }
		 
		 //set the Suppliers value in textbox
		 	if(!isNullOrBlank(strSupplier)){
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnPagePrev()),true,"***** Test ID : 6.001 - In Catalog Tool under lighthouse page \"Previous\" Button is not clicked *****");
		 		wait(4000);
		 		clearCatalogField();
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 6.001 - In Catalog Tool under lighthouse page \"Type\" Field value is not entered as Supliers*****");
		 		
		 		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()),true,"***** Test ID : 6.001 - In Catalog Tool under lighthouse page \"Apply\" button is not clicked *****");
		 		wait(4000);	
		 		catalogCustSupplierList = objAdjunoWFLDestinationQCPreInspectionPOM.getCatalogTableData(objAdjunoWFLDestinationQCPreInspectionPOM.getLstNameDescription(), objAdjunoWFLDestinationQCPreInspectionPOM.getLstSupplierDescriptionField());
		 	}else{
		 		strMessage = strMessage + "***** Test ID : 6.001 - \"Supplier\" Type value is Empty in xml*****";
		 	}
		 	System.out.println(catalogCustSupplierList.size());
		 	if(catalogCustSupplierList.size()>0){
				        	
		 	}else{
		 		strMessage = strMessage +" *****  Test ID : 6.001 - In Catalog Tool Under lighthouse page \"Supplier Type\" won't have data";
		 	}
		 	
	     	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  strMessage);
	       	objSoftAssert.assertAll();
	 }
	
	  
	// verify customer dropdown values 
	@Test(priority=15)
	public void test_6d002_VerifyCustomerValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		String strTitle= objAdjunoWFLDestinationQCPreInspectionPOM.callMouseHover(strPreInspectionFormName, objAdjunoWFLDestinationQCPreInspectionPOM.getlnkTools(), objAdjunoWFLDestinationQCPreInspectionPOM.getlnkDestinationQCPreInspection());
		boolean bFlag = true;
		
		if (isNullOrBlank(strTitle))
			bFlag = false;
		
		if (!(isNullOrBlank(strTitle)))
		{
			if (strTitle.equalsIgnoreCase(strDestinationQcpreinspectionTitle))
				bFlag = true;
			else	
				bFlag = false;
		}       		   
   
		objSoftAssert.assertEquals(bFlag, true, "Page title of Destination QC Pre-Inspection tool is wrong ");
       
		customerList = objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_21Customer");
		wait(4000);
		if(customerList.size()>0){
			
		}else{
			strMessage = strMessage +" In Destination QC Pre-Inspection Tool under Search chevron \"customer\" Drop drown doesn't have value ";
		}
		wait(2000);	
		strMessage= strMessage + objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogCustomerList, customerList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  "***** Test ID : 6.002"+ strMessage +"*****" );
		objSoftAssert.assertAll();
	 }
	
	//verify the Orgin Dropdown Data
	@Test(priority=20)
	public void test_6d003_VerifyOrginDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		orginList = objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_01Origin");
		
		if(orginList.size()>0){
			
		}else{
			strMessage=strMessage+"In Destination QC Pre-Inspection Tool under Search chevron \"Origin\" Drop Down doesn't have Value";
		}
		wait(2000);	
		strMessage= strMessage + objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogCountryList, orginList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.003"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//verify Origin port Drop Down Value
	@Test(priority=25)
	public void test_6d004_VerifyOrginPortDropDownValues(){	
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		originPortList=objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_02OriginPort");
	
		if(originPortList.size()>0){
		}else{
			strMessage=strMessage+"In Destination QC Pre-Inspection Tool under Search chevron \"Orgin Port\" Drop Down doesn't have Value";
		}		
		wait(2000);
		strMessage= strMessage + objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogLoCodeList, originPortList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.004"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//verify Mode Drop Down Values
	@Test(priority=30)
	public void test_6d005_VerifyModeDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 6.005 - Mode Field element not cleared *****");
		wait(4000);
		modeList=objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_03Mode");
		
		if(modeList.size()>0){
		}else{
			strMessage=strMessage+"In Destination QC Pre-Inspection Tool under Search chevron \"Mode\" Drop Down doesn't have Value";
		}
		wait(2000);
		strMessage= strMessage + objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogModeList, modeList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.005"+ strMessage +"*****");
		objSoftAssert.assertAll();		
	}
	
	//verify Vessel Drop Down Values
	@Test(priority=35)
	public void test_6d006_VerifyVesselDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		vesselList=objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_04Vessel");
		
		if(vesselList.size()>0){
		}else{
			strMessage=strMessage+"In Destination QC Pre-Inspection Tool under Search chevron \"Vessel\" Drop Down doesn't have Value";
		}
		wait(2000);
		strMessage= strMessage + objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogVesselList, vesselList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.006"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//verify Status drop Down Values
	@Test(priority=40)
	public void test_6d007_VerifystatusDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		statusList=objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_13Status");
		wait(2000);
		if(statusList.size()==6){
		}else{
			strMessage=strMessage+"In Destination QC Pre-Inspection Tool under Search chevron \"Status\" Drop Down doesn't have Value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.007"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	 
	//verify the vendor drop down values
	@Test(priority=45)
	public void test_6d008_VerifyVendorDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		String strMessage ="";
		vendorList=objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_05Vendor");
		
		if(vendorList.size()>0){
		}else{
			strMessage=strMessage+"In Destination QC Pre-Inspection Tool under Search chevron \"Vender\" Drop Down doesn't have Value";
		}
		wait(2000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogSupplierList, vendorList);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.008"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	

	//clear the field 
	@Test(priority=50)
	public void clearFields(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_01Origin"), true,"***** - In Destination QC Pre-Inspection tool \"Orgin\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_02OriginPort"), true,"***** - In Destination QC Pre-Inspection tool \"Orgin Port\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_03Mode"), true,"***** - In Destination QC Pre-Inspection tool \"Mode\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_04Vessel"), true,"***** - In Destination QC Pre-Inspection tool \"Vessel\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_11ETAFrom"), true,"***** - In Destination QC Pre-Inspection tool \"ETA From\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_12ETATo"), true,"***** - In Destination QC Pre-Inspection tool \"ETA To\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_13Status"), true,"***** - In Destination QC Pre-Inspection tool \"Status\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_06Container"), true,"***** - In Destination QC Pre-Inspection tool \"Container\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_09HouseBill"), true,"***** - In Destination QC Pre-Inspection tool \"HouseBill\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_05Vendor"), true,"***** - In Destination QC Pre-Inspection tool \"Vendor\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_14CustomerReference"), true,"***** - In Destination QC Pre-Inspection tool \"Customer Reference\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_07PO"), true,"***** - In Destination QC Pre-Inspection tool \"WFL Reference\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_08Product"), true,"***** - In Destination QC Pre-Inspection tool \"Product\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_19URN"), true,"***** - In Destination QC Pre-Inspection tool \"UCR/URN\" Field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clearInputField(strPreInspectionFormName, "PARAM_21Customer"), true,"***** - In Destination QC Pre-Inspection tool \"Customer\" Field is not cleared *****");
		
		objSoftAssert.assertAll();
	}
	
	
	//Set the Customer DropDown Values and inspect the result
	@Test(priority=55)
	public void test_6d024_SetCustomerDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.024 - In Destination QC Pre-Inspection tool \"Select\" button not clicked *****");
		clearFields();					
		
		if(!isNullOrBlank(strWFLRefCustomerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefCustomerValue, strPreInspectionFormName,"PARAM_21Customer"), true,"***** Test ID : 6.024 - In Destination QC Pre-Inspection tool under select chevron\"Customer\" field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Customer\" Value is Empty in XML";
		}
							
		if(!isNullOrBlank(strCompleteStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strCompleteStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.024 - In Destination QC Pre-Inspection tool under select chevron\"Status\" field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		wait(2000);	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.024 - In Destination QC Pre-Inspection tool under select chevron\"Refine Search\" button is not clicked *****");
		wait(4000);					
		List<WebElement> lstWFLReference=objAdjunoWFLDestinationQCPreInspectionPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
		lstWFLReference.get(i).click();
		String window= objAdjunoWFLDestinationQCPreInspectionPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()), true , "***** Test ID : 6.024 - In Destination QC Pre-Inspection tool under Purchase Order page \"Apply\" button is not clicked *****");
			
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.compareTwoStringValuesToSame(strWFLRefCustomerValue, objAdjunoWFLDestinationQCPreInspectionPOM.getFieldValue(strDestinationWFLRefLinkQCFormName, "Customer")), true , " Test ID : 6.024 - The Customer value which was entered under select chevron is not matching with the customer value  found in Purchase order page ***** ");
		wait(1000);
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.024"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set Order Number Value and Inspect DropDown
	@Test(priority=58)	
	public void test_6d020_SetValueofOrderNumberDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
								
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.020 - In Destination QC Pre-Inspection tool under select chevron\"Status\" field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strWFLOrderNumberValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLOrderNumberValue, strPreInspectionFormName,"PARAM_14CustomerReference"), true,"***** Test ID : 6.020 - In Destination QC Pre-Inspection tool under select chevron\"Order Number\" field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Order Number\" Value is Empty in XML";
		}
		wait(2000);						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.020 - In Destination QC Pre-Inspection tool under select chevron\"Refine Search\" button is not clicked *****");	
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getLnkWFLReference()), true ,"***** Test ID : 6.020 - In Destination QC Pre-Inspection tool under select chevron\"WFL Ref\" is not clicked *****");
		wait(2000);
		String window= objAdjunoWFLDestinationQCPreInspectionPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()), true , "***** Test ID : 6.020 - In Destination QC Pre-Inspection tool under Purchase order page\"Apply\" button is not clicked *****");

		wait(1000);					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getLnkProductCard()), true ,"***** Test ID : 6.020 - In Destination QC Pre-Inspection tool under purchase order page \"Product link\" button is not clicked *****");
		wait(1000);							
		String orderNumber=objAdjunoWFLDestinationQCPreInspectionPOM.getGridCellValue(strDestinationWFLRefLinkQCFormName, "GRID_ProductCard", 0, "CustomerReference");					
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.compareTwoStringValuesToSame(strWFLOrderNumberValue, orderNumber), true , " Test ID : 6.020 - The Order Number value which was entered under select chevron is not matching with the value found under Purchase page ***** ");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.020"+ strMessage +"*****");
		objSoftAssert.assertAll();
								
	}
				
	//Set the value in product dropdown  and inspect the values
	@Test(priority=60)
	public void test_6d026_InspectPreInspectionField(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strModeValue, strPreInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 6.026 - In Destination QC Pre-Inspection tool under select chevron\"Mode\" field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Mode\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strInspectionRequiredStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strInspectionRequiredStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.026 - In Destination QC Pre-Inspection tool under select chevron\"status\" field value is not entered*****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
		wait(2000);	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.026 - In Destination QC Pre-Inspection tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(4000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strInspectionRequiredStatusValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstInspectionRequired());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.026"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
	
	//set Value to OrginPort DropDown
	@Test(priority=65)
	public void test_6d011_SetOrginPortDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";

		clearFields();
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.011 - In Destination QC Pre-Inspection tool under select chevron\"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"Status\" Value is Empty in XML";
		}
							
		if(!isNullOrBlank(strWFLOrginPortValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLOrginPortValue, strPreInspectionFormName,"PARAM_02OriginPort"), true,"***** Test ID : 6.011 - In Destination QC Pre-Inspection tool under select chevron\"OrginPort\" Field value is not entered *****");
		}else{
			strMessage=strMessage+"\"OrginPort\" Value is Empty in XML";
		}
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.011 - In Destination QC Pre-Inspection tool under select chevron\"Refine Search\" button is not clicked *****");
		wait(4000);				
		List<WebElement> lstWFLReference=objAdjunoWFLDestinationQCPreInspectionPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
			lstWFLReference.get(i).click();
	
			String window= objAdjunoWFLDestinationQCPreInspectionPOM.getWindowIds();
			String [] pageWindow=window.split(";");
			driver.switchTo().window(pageWindow[1]);
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getcatalogbtnApply()), true , "***** Test ID : 6.011 - In Destination QC Pre-Inspection tool under Purchase Order Page \"Apply\" button is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.compareTwoStringValuesToSame(strWFLOrginPortValue, objAdjunoWFLDestinationQCPreInspectionPOM.getFieldValue(strDestinationWFLRefLinkQCFormName, "OriginPort")), true , " Test ID : 6.011 -  In Destination QC Pre-Inspection tool The \"OriginPort\" value which was entered under select chevron is not matching with the \"Origin Port\" value which is present under \"Purchase Order\" page ***** ");
			driver.close();
			driver.switchTo().window(pageWindow[0]);
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.011"+ strMessage +"*****");
		objSoftAssert.assertAll();
							
	}
		
	//login as a customer
	@Test(priority=75)
	public void LoginCustomer(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickLogout(objAdjunoWFLDestinationQCPreInspectionPOM.getLnkLogout()), true , "***** In Destination QC Pre-Inspection Tool \"Logout\" button is not Clicked *****");
		objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
		objAdjunoLIMALoginPOM.setUserName(strCustomerUserName);
		objAdjunoLIMALoginPOM.setPassword(strCustomerPassword);
		objAdjunoLIMALoginPOM.clickLogon();
		
	}
	
	//logged as a customer and verify the customer dropdown
	@Test(priority=80)
	public void test_6d009_VerifyCustCustomerDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		wait(4000);
		String strTitle= objAdjunoWFLDestinationQCPreInspectionPOM.callMouseHover(strPreInspectionFormName, objAdjunoWFLDestinationQCPreInspectionPOM.getlnkTools(), objAdjunoWFLDestinationQCPreInspectionPOM.getlnkDestinationQCPreInspection());
		boolean bFlag = true;
		
		if (isNullOrBlank(strTitle))
			bFlag = false;
		
		if (!(isNullOrBlank(strTitle)))
		{
			if (strTitle.equalsIgnoreCase(strDestinationQcpreinspectionTitle))
				bFlag = true;
			else	
				bFlag = false;
		}       		   
   
		objSoftAssert.assertEquals(bFlag, true, "Page Title of the Destination QC Pre-Inspection Tool is wrong");
		
		customerList = objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_21Customer");
	       
		if(customerList.size()==1){
			
		}else{
			strMessage = strMessage +" In Destination QC Pre-Inspection Tool under search chevron \"customer\" Drop drown doesn't have value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.009"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	private boolean isNullOrBlank(String s)
	{
		return (s==null || s.trim().equals(""));
	}
	
	//verify the vendor drop down values
	@Test(priority=82)
	public void test_6d001_VerifyCustVendorDropDownValues(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDestinationQCPreInspectionPOM=new AdjunoWFLDestinationQCPreInspectionPOM(driver);
		custvendorList=objAdjunoWFLDestinationQCPreInspectionPOM.getDropdownValues(strPreInspectionFormName, "PARAM_05Vendor");
		System.out.println(custvendorList.size());
		if(custvendorList.size()>0){
		}else{
			strMessage=strMessage+" In Destination QC Pre-Inspection Tool under search chevron \"Vendor\" Drop Down Won't have any Value";
		}
		wait(2000);				
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifyCatalogData(catalogCustSupplierList, custvendorList);
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.001"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set the value To Mode dropdown box and inspect the values
	@Test(priority=85)
	public void test_6d012_SetValueModeDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.012 - In Destination QC Pre-Inspection Tool under search chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.012 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not clicked *****");
		wait(4000);
		clearFields();
		if(!isNullOrBlank(strModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strModeValue, strPreInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 6.012 - In Destination QC Pre-Inspection Tool under select chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Mode\" Value is Empty in XML";
		}
		if(!isNullOrBlank(strAnyStatusValue)){
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.012 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage = strMessage +" \"Status\" value is Empty in xml";
		}
		
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.012 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
 		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strModeValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstMode());
		
 		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.012"+ strMessage +"*****");
	 	objSoftAssert.assertAll();
	}
	
	//Set the value in Vessel dropdown  and inspect the values
	@Test(priority=90)
	public void test_6d013_SetValueVesselDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
	
		if(!isNullOrBlank(strVesselValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strVesselValue, strPreInspectionFormName,"PARAM_04Vessel"), true,"***** Test ID : 6.013 - In Destination QC Pre-Inspection Tool under select chevron \"Vessel\" Field value is not entered*****");
	 	}else{
	 		strMessage = strMessage+" \"Mode\" Value is Empty in XML";
	 	}
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.013 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.013 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);	
		strMessage= strMessage+ objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strVesselValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstVessel());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.013 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
	}	
	

	@Test(priority=92)
	public void test_6d014_SetValueETAFromCalender() throws ParseException{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.014 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLETAFromDate)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLETAFromDate, strPreInspectionFormName,"PARAM_11ETAFrom"), true,"***** Test ID : 6.014 - In Destination QC Pre-Inspection Tool under select chevron \"ETAFrom\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.014 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage =strMessage +objAdjunoWFLDestinationQCPreInspectionPOM.compareFromDates(strWFLETAFromDate, objAdjunoWFLDestinationQCPreInspectionPOM.getLstETAFromDate());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.014 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority=94)
	public void test_6d015_SetValueETAToCalender() throws ParseException{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.015 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLETAToDate)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLETAToDate, strPreInspectionFormName,"PARAM_12ETATo"), true,"***** Test ID : 6.015 - In Destination QC Pre-Inspection Tool under select chevron \"ETATo\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.015 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage =strMessage +objAdjunoWFLDestinationQCPreInspectionPOM.compareToDates(strWFLETAToDate, objAdjunoWFLDestinationQCPreInspectionPOM.getLstETAToDate());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.015"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//Set the value in status dropdown  and inspect the values
	@Test(priority=95)
	public void test_6d016_SetValueStatusDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();	
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.016 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.016 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
			
		wait(2000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strStatusValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstStatus());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.016"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set the value in Container dropdown  and inspect the values
	@Test(priority=100)
	public void test_6d017_SetValueContainerDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strContainerValue, strPreInspectionFormName,"PARAM_06Container"), true,"***** Test ID : 6.017 - In Destination QC Pre-Inspection Tool under select chevron \"Container\" Field value is not entered*****");
	 	}else{
	 		strMessage=strMessage+" \"Container\" Value is Empty in XML";
	 	}
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.017 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.017 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strContainerValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstContainer());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.017"+ strMessage +"*****");
		objSoftAssert.assertAll();
	
	}
	
	//Set the value in HouseBill dropdown  and inspect the values
	@Test(priority=105)
	public void test_6d018_SetValueHouseBillDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strHouseBillValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strHouseBillValue, strPreInspectionFormName,"PARAM_09HouseBill"), true,"***** Test ID : 6.018 - In Destination QC Pre-Inspection Tool under select chevron \"House Bill\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Housebill\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.018 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.018 - In Destination QC Pre-Inspection Tool under select chevron \"RefineSearch\" button is not clicked *****");
		wait(2000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strHouseBillValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstHouseBill());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.018"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//Set the value in Vendor dropdown  and inspect the values
	@Test(priority=110)
	public void test_6d019_SetValueVendorDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strVendorValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strVendorValue, strPreInspectionFormName,"PARAM_05Vendor"), true,"***** Test ID : 6.019 - In Destination QC Pre-Inspection Tool under select chevron \"Vendor\" Field value is not entered*****");
		}else{
			strMessage=strMessage+" \"Vendor\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.019 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.019 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strVendorValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstVendor());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.019"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	//Set the value in product dropdown  and inspect the values
	@Test(priority=115)
	public void test_6d022_SetValueProductDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
	
		if(!isNullOrBlank(strProductValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strProductValue, strPreInspectionFormName,"PARAM_08Product"), true,"***** Test ID : 6.022 - In Destination QC Pre-Inspection Tool under select chevron \"Product\" Field value is not entered*****");
		}else{
			strMessage=strMessage+" \"Product\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.022 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.022 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage=strMessage+objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strProductValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstProduct());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.022"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//Set the value in WFLRef dropdown  and inspect the values
	@Test(priority=120)
	public void test_6d021_SetValueWFLRefDropdown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
	
		if(!isNullOrBlank(strWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.021 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.021 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.021 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage = objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strWFLRefValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstWFLRef());
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.021"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
		
	//check for the duplicate records 
	@Test(priority=130)
	public void test_6d025_DuplicateValuePresent(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
		
		if(!isNullOrBlank(strModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strModeValue, strPreInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 6.025 - In Destination QC Pre-Inspection Tool under select chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Mode\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.025 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.025 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage =strMessage +objAdjunoWFLDestinationQCPreInspectionPOM.duplicateValue(objAdjunoWFLDestinationQCPreInspectionPOM.getLstContainer());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.025"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//Search the product certificate type N has been Despatched
	@Test(priority=135)
	public void test_6d027_SearchProductNDespatched(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		clearFields();
					
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.027 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strDespatchedContainer)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strDespatchedContainer, strPreInspectionFormName,"PARAM_06Container"), true,"***** Test ID : 6.027 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"DespatchedContainer\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.027 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);			
		if(objAdjunoWFLDestinationQCPreInspectionPOM.checkDoesElementExistWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getStrValidationMsg())){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.getStrValidationMsg().getText().equalsIgnoreCase("No items were found."), true,"***** Test ID : 6.027 - In Destination QC Pre-Inspection Tool the Expected Value is :\" No items were found.\" but Found : "+objAdjunoWFLDestinationQCPreInspectionPOM.getStrValidationMsg().getText() +"*****");

		}else{
			strMessage = strMessage + "In Destination QC Pre-inspection Tool Under Select Chevron \"Despatched Product\" is displaying ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.027 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
	
	//Set the new value of Corresponding field and inspect the Refine Search values
	@Test(priority=140)
	public void test_6d028_VerifyRefineSearchResult(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		wait(4000);
		clearFields();
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.028 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strModeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strModeValue, strPreInspectionFormName,"PARAM_03Mode"), true,"***** Test ID : 6.028 - In Destination QC Pre-Inspection Tool under select chevron \"Mode\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Mode\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.028 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		strMessage=objAdjunoWFLDestinationQCPreInspectionPOM.verifySearchValue(strModeValue,objAdjunoWFLDestinationQCPreInspectionPOM.getLstMode());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.028"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	// Tick the notify APHA 
	@Test(priority=145)
	public void test_6d030_TickNotifyAPHA()	{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";

		clearFields();
		wait(4000);
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strWFLRefGridValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);	
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
		          
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
	   
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"No Rows are Selected\" *****");
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool \"PreInspection\" chevron is not clicked *****");
			wait(4000);       
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		if(!objAdjunoWFLDestinationQCPreInspectionPOM.isCheckboxOnGridChecked(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA")){	
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA"), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under PreInspection chevron \"Notify APHA\" Checkbox is not clicked *****");

		}
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "DocsDelivered"), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under PreInspection chevron \"DocsDelivered\" Checkbox is not clicked *****");

		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvcomplete()), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under complete chevron \"Search Again\" Button is not Clicked *****" );
		wait(2000);	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not Clicked *****" );
		wait(2000);		
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
		   
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"No Rows are Selected\" *****");
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool \"PreInspection\" chevron is not clicked *****");
			wait(2000);  
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.isCheckboxOnGridChecked(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA"), true , "***** Test ID : 6.030 - In Destination QC Pre-Inspection Tool under PreInspection chevron \"Notify APHA\" Check box is not clicked in the Grid*****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.030"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	
	//Inspect Certificate type is A 
	@Test(priority=150)
	public void test_6d031_InspectGridValueCertTypeA(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not clicked *****");
		clearFields();
		wait(4000);
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strWFLRefGridCertAValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridCertAValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
			          
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
			   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool \"PreInspection\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		if (objAdjunoWFLDestinationQCPreInspectionPOM.verifyPageIsLoaded(strDestinationQCFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.checkDoesElementExist(strDestinationQCFormName,"Grid1"),true,"***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under PreInspection chevron \"HBL Grid\" field is not dispalyed *****");
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool \"PreInspection\" page is not loaded*****");
		}
		if(bSearchResultsProductsFound)
		{
			String strReturnMessage = objAdjunoWFLDestinationQCPreInspectionPOM.verifyProductsDataOnGrid(strDestinationQCFormName,"Grid1",lstSearchResults);
				
			objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under select chevron the \"Products\" which are present is not matching with the \"Products\" present under Pre-Inspection chevron *****");
						  
		}
		else
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.031 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.031"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Inspect Certificatr Type S 
	@Test(priority=155)
	public void test_6d029_InspectGridValueCertTypeS(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.029 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not clicked *****");
		wait(4000);
		clearFields();
		
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.029 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strWFLRefGridValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.029 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.029 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(4000);
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
		
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
	  
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.029 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");   
			wait(2000);
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.029- In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
			
		if (objAdjunoWFLDestinationQCPreInspectionPOM.verifyPageIsLoaded(strDestinationQCFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.checkDoesElementExist(strDestinationQCFormName,"Grid1"),true,"***** Test ID : 6.029-  In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Grid\" is not populating *****");
	         
		}else{
			bFlag = false;	 
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 6.029- In Destination QC Pre-Inspection Tool \"Pre-Inspection\" page is not loaded*****");
		}
		if(bSearchResultsProductsFound)
		{
			String strReturnMessage = objAdjunoWFLDestinationQCPreInspectionPOM.verifyProductsDataOnGrid(strDestinationQCFormName,"Grid1",lstSearchResults);
				
			objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.029 - In Destination QC Pre-Inspection Tool under select chevron the \"Products\" which are present is not matching with the \"Products\" present under Pre-Inspection chevron *****");
				  
		}else{
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.029- In Destination QC Pre-Inspection Tool under select chevron \"No Products\" are displaying *****");
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.029"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
	
	//Set the corresponding field and do necessary Action,Check the result it taking to Search Page
	@Test(priority=160)
	public void test_6d035_InspectCompletionStage(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool \"select\" chevron is not clicked *****");
		wait(4000);
		clearFields();
		boolean bFlag;
		
		if(!isNullOrBlank(strWFLRefGridValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
								
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered as Any *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);		
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
				          
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.0035 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");
			wait(2000);	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are populating\" *****");
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA"), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Notify APHA\" Checkbox is not clicked *****");
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "DocsDelivered"), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"DocsDelivered\" Checkbox is not clicked *****");
						
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvcomplete()), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool under complete chevron \"Search Again\" Button is not Clicked *****" );
		wait(2000);				
		if (objAdjunoWFLDestinationQCPreInspectionPOM.verifyPageIsLoaded(strPreInspectionFormName)) {
			
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.checkDoesElementExist(strPreInspectionFormName,"Panel1"),true,"***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool \"Search Page\" is not displaying *****");
			         
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 6.035 - In Destination QC Pre-Inspection Tool \"Search page\" is not loaded *****");
		}	
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.035"+ strMessage +"*****");
		objSoftAssert.assertAll();
						
	}
		
	//set the Inspection Required is "Y"
	@Test(priority=165)
	public void test_6d032_SetInspectionReqY(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool \"Select\" chevrin is not clicked *****");
		wait(4000);
		clearFields();
		if(!isNullOrBlank(strWFLRefGridValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
	          
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
	   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");
	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are populating\" *****");
		}
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA"), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Notify APHA\" Checkbox is not clicked *****");
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValueForGridCell(strDestinationQCFormName, "Grid1", 0, "InspectionRequired", strWFLFieldValueY), true , " Test ID : 6.032 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Inspection Required\" Field Value is not Set *****");
		wait(4000);	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvcomplete()), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under complete chevron \"Search Again\" Button is not Clicked *****" );
		wait(2000);	
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not Clicked *****" );
			
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
	   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.032 - Not able to click on PreInspection Checks chevron*****");
	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		wait(2000);	
		String wflFieldValueY=objAdjunoWFLDestinationQCPreInspectionPOM.getGridCellValue(strDestinationQCFormName, "Grid1", 0, "InspectionRequired");
		
		objSoftAssert.assertEquals(wflFieldValueY, strWFLFieldValueY , "***** Test ID : 6.032 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Inspection Required\" field value is not saved *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.032"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
	
	//Check the DocsDelivered"
	@Test(priority=170)
	public void test_6d034_TickDocsDelivered(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool \"select\" chevron is not clicked *****");
		wait(4000);
		clearFields();
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strWFLRefGridValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
		wait(2000);			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under select chevron\"Refine Search\" button is not clicked *****");
					
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
			          
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
			   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		
		wait(3000);
		
		if(!objAdjunoWFLDestinationQCPreInspectionPOM.isCheckboxOnGridChecked(strDestinationQCFormName, "Grid1", 0, "DocsDelivered")){	
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "DocsDelivered"), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"DocsDelivered\" Checkbox is not clicked *****");

		}
		
		wait(3000);				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA"), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron\"Notify APHA\" Checkbox is not clicked *****");
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvcomplete()), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under complete chevron \"Search Again\" Button is not Clicked *****" );
		wait(2000);			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not Clicked *****" );
					
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0){
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		wait(4000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.isCheckboxOnGridChecked(strDestinationQCFormName, "Grid1", 0, "DocsDelivered"), true , "***** Test ID : 6.034 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"DocsDelivered\" Check box was not clicked *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.034 "+ strMessage +"*****");
		objSoftAssert.assertAll();		
	}
	
	//set the Inspection Required is "N"
	@Test(priority=175)
	public void test_6d033_SetInspectionReqN()
	{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool \"Select\" chevron is not clicked *****");
		wait(4000);
		clearFields();
		if(!isNullOrBlank(strWFLRefGridValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strWFLRefGridValue, strPreInspectionFormName,"PARAM_07PO"), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strAnyStatusValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValue(strAnyStatusValue, strPreInspectionFormName,"PARAM_13Status"), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under select chevron \"Status\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"Status\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getClkRefineSearch()), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(2000);		
		lstSearchResults = new ArrayList<DestinationQCPreInspection>();
		          
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
		  
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");
		             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.033 -In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickcheckbox(strDestinationQCFormName, "Grid1", 0, "NotifyAPHA"), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron\"Notify APHA\" Checkbox is not clicked *****");
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.setFieldValueForGridCell(strDestinationQCFormName, "Grid1", 0, "InspectionRequired", strWFLFieldValueN), true , " Test ID : 6.033 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Inspection Required\" field value is not entered *****");
		wait(1000);
			
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvcomplete()), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool \"Complete\" chevron is not Clicked *****" );
		wait(1000);
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickButtonUsingWebElement(objAdjunoWFLDestinationQCPreInspectionPOM.getChvSearchAgain()), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under complete chevron \"Search Again\" Button is not Clicked *****" );
		wait(2000);		
		objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getchvSelect()), true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool \"select chevron\" is not Clicked *****" );
			
		if ( objAdjunoWFLDestinationQCPreInspectionPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDestinationQCPreInspectionPOM.selectMulitpleProducts(1);
		   
			objSoftAssert.assertEquals(objAdjunoWFLDestinationQCPreInspectionPOM.clickChevron(objAdjunoWFLDestinationQCPreInspectionPOM.getChvPreInspection1()),true,"***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool \"Pre-Inspection\" chevron is not clicked *****");
			wait(2000);        
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under select chevron \"No Products are displaying\" *****");
		}
		wait(2000);
		String wflFieldValueN=objAdjunoWFLDestinationQCPreInspectionPOM.getGridCellValue(strDestinationQCFormName, "Grid1", 0, "InspectionRequired");
		
		objSoftAssert.assertEquals(wflFieldValueN, strWFLFieldValueN , "***** Test ID : 6.033 - In Destination QC Pre-Inspection Tool under Pre-Inspection chevron \"Inspection Required\" field value was not saved *****");
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6.033"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
	
	@AfterTest
    public void closeBrowser()
	{
		driver.close();
	}
		
}
