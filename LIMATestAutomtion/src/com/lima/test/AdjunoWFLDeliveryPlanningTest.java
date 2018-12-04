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

import com.lima.dto.DeliveryPlanning;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;
import com.lima.pagefactory.AdjunoWFLDeliveryPlanningPOM;

public class AdjunoWFLDeliveryPlanningTest {
	WebDriver driver;
				    
	long nPage_Timeout = 0;
	String strTestURL;   
	String strLIMAUserName;
	String strLIMAPassword;
	String strCustomerUserName;
	String strCustomerPassword;
			
	boolean bSearchResultsProductsFound = true;
	AdjunoWFLDeliveryPlanningPOM objAdjunoWFLDeliveryPlanningPOM;
	ArrayList<DeliveryPlanning> lstSearchResults;
			
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
			 
	NodeList searchParams;
	NodeList nlSearchParamsDeliveryPlanning;
	NodeList nlSearchParamsCatalog;
	NodeList nlLegsGridDeliveryPlanning;
		 
	String strSupplier ="";
	String strCustomer ="";
	String strLoCode ="";
	String strDestination ="";
	String strConsignee ="";
		 
	String strFieldProductSKUValue ="";
	String strFieldLineTypeValue ="";
	String strFieldConsigneeValue ="";
	String strFieldDeliveryDateValue ="";
	String strFieldDestinationvalue ="";
	String strSecondSkuValue ="";
	String strSecondLineTypeValue ="";
	String strDeliverydateValue ="";
	String StrLineTypeFieldValue ="";
	String strSecondConsigneeValue ="";
		 
	String strCatalogFormName;
	String strCatalogPageTitle;
	String strDeliveryPlanningFormName;
	String strDeliveryPlanningPageTitle;
	String strWFLDeliveryPlanningRefLinkFormName;
	String strDeliveryPlanningCompletePageFormName;
	String strDeliveryPlanningStageFormName;
	String StrDestinationPortValue;
	String strWFLDeliveryPlanningCompletePageFormName;
			 
	ArrayList<String> catalogSupplierList;
	ArrayList<String> catalogConsigneeList;
	ArrayList<String> catalogCustomerList;
	ArrayList<String> catalogLoCodeList;
	ArrayList<String> catalogDestinationList;
	ArrayList<String> catalogCustSupplierList;
	ArrayList<String> catalogDestinationConsigneeList;
			 
	ArrayList<String> custSupplierList;
	ArrayList<String> custConsigneeList;
	ArrayList<String> custSKUValue;
	ArrayList<String> custProductSKUList;
	ArrayList<String> custCustomerList;
	ArrayList<String> custDestinationPortList;
			 
	ArrayList<String> customerList;
	ArrayList<String> workFlowStatusList;
	ArrayList<String> nextStageList;
	ArrayList<String> supplierList;
	ArrayList<String> lineTypeList;
	ArrayList<String> lineTypeListSplitGrid;
	ArrayList<String> DestinationDropDownList;
	ArrayList<String> DestinationDropDownSplitGridList;
	ArrayList<String> consigneeDropDownSplitGridList;
	ArrayList<String> consigneeDropDownList;
	ArrayList<String> destinationListSKUGrid;
	ArrayList<String> destinationListSplitGrid;

	String strWFLRefCustomerValue ="";
	String strWFLDestinationPortValue ="";
	String strSupplierValue ="";
	String strConsigneeValue ="";
	String strNextStageShipmentBookingValue ="";
	String strNextStageVendorValue ="";
	String strNextStageCargoReceiptValue ="";
	String strNextStageConfornArrivalValue ="";
	String strWFLRefValue ="";
	String strProductValue ="";
	String strProductSKUValue ="";
	String strLineTypeWFLRefValue ="";
	String strVendorBookingWFLRefValue ="";
	String strConformArrivalWFLRefValue ="";
	String strSplitDeliveryWFLRefValue ="";
	String strLineTypeValue ="";
	String strNegativeFieldvalue ="";
	String differenceValue ="";
	String strWFLGridValidationMessageValue ="";
	String strColumnCaptionWFLRef ="";
	String strColumnCaptionVendor ="";
	String secondSKUGridvalue ="";
	String secondSKUGridConsigneevalue ="";
	String secondLineTypeSKUGridvalue ="";
	String secondDeliveryDateSKUGridvalue ="";
	String strSplitDeliveryYValue ="";
	String strSplitDeliveryNValue ="";
	String strLineTypeDirectOrderValue ="";
	String strLineTypeStockOrderValue ="";
	String strBookingRefValue ="";
	String strDeliveryDatePastValue ="";
	String strDeliveryDateFutureValue ="";
	String strDeliveryDateETAValue ="";
	String strDeliveryTimeValue ="";
	String strCompleteStageWFLRefValue ="";
	String strCompleteStageSKUValue ="";
	String strCompleteConsigneeValue ="";
	String strCompleteDestinationValue ="";
	String strCompletionMessageValue ="";
	String strRevisedQuantityValue ="";
	String strRevisedQuantityLesserValue ="";
	String strBalanceCtnsFieldvalue ="";
	String strBalanceCtnsSplitGridFieldvalue ="";
	String strConsigneeSelectPageValue ="";
	String strConformArrivalSKUValue ="";
	String strConformArrivalConsigneeValue ="";
	String strDeliveryDatePast = "";
	String strFutureDeliveryDate = "";
	String strPresentDeliveryDate = "";
	String strFutureDeliveryDateComplete = "";
	 
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
			            
	    Node DeliveryPlanningCustFormName = (Node) xPath.evaluate("/config/WFL/WFL_DeliveryPlanning_FormName", dDoc, XPathConstants.NODE);
	    strDeliveryPlanningFormName = DeliveryPlanningCustFormName.getTextContent();
			            
	    Node DeliveryPlanningPageTitle=(Node) xPath.evaluate("/config/WFL/WFL_DeliveryPlanning_PageTitle", dDoc, XPathConstants.NODE);
	    strDeliveryPlanningPageTitle=DeliveryPlanningPageTitle.getTextContent();
			          
	    Node CatalogPageTitle=(Node) xPath.evaluate("/config/WFL/WFL_Page_Title_Catalog", dDoc, XPathConstants.NODE);
	    strCatalogPageTitle=CatalogPageTitle.getTextContent();
			            
	    Node CatalogFormName=(Node) xPath.evaluate("/config/WFL/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	    strCatalogFormName=CatalogFormName.getTextContent();
			        
	    Node DeliveryPlanningStageFormName = (Node) xPath.evaluate("/config/WFL/WFL_DeliveryPlanning_DeliveryPlanningStage_FormName", dDoc,XPathConstants.NODE);
	    strDeliveryPlanningStageFormName = DeliveryPlanningStageFormName .getTextContent();
				       
	    Node DeliveryPlanningWFLRefFormName = (Node) xPath.evaluate("/config/WFL/WFL_DeliveryPlanning_WFLRefFormName", dDoc,XPathConstants.NODE);
	    strWFLDeliveryPlanningRefLinkFormName = DeliveryPlanningWFLRefFormName .getTextContent();
			        
	    Node DeliveryPlanningCompletePageFormName = (Node) xPath.evaluate("/config/WFL/WFL_Complete_FieldValidation_FormName", dDoc,XPathConstants.NODE);
	    strDeliveryPlanningCompletePageFormName = DeliveryPlanningCompletePageFormName .getTextContent();
	    
	   	       
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
		Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getStrWFLDeliveryPlanning());
		XPath xPath1 = XPathFactory.newInstance().newXPath();
			            
		nlSearchParamsCatalog = (NodeList) xPath1.evaluate("/Catalog/SearchParams", dDoc1, XPathConstants.NODESET);
			           
		nlSearchParamsDeliveryPlanning=(NodeList) xPath1.evaluate("/Catalog/DeliveryPlanning/SearchParams", dDoc1, XPathConstants.NODESET);
			            
		nlLegsGridDeliveryPlanning = (NodeList) xPath1.evaluate("/Catalog/GridDropDown_DeliveryPlanning", dDoc1, XPathConstants.NODESET);

			           
		Element el;
		for(int i=0; i <= nlSearchParamsCatalog.getLength() -1; i++)
		{
			if (nlSearchParamsCatalog.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				el = (Element) nlSearchParamsCatalog.item(i);
				strSupplier = el.getElementsByTagName("Type_Supplier").item(0).getTextContent(); 
			                     
				strCustomer = el.getElementsByTagName("Type_Customer").item(0).getTextContent(); 
			                     
				strConsignee = el.getElementsByTagName("Type_Consignee").item(0).getTextContent();  
			                     	                     	                     
				strDestination = el.getElementsByTagName("Type_Destination").item(0).getTextContent(); 
			                     
				strLoCode = el.getElementsByTagName("Type_LoCode").item(0).getTextContent(); 

			                     
			            
			}
			}
			            
		Element ele;
		for(int i=0; i <= nlSearchParamsDeliveryPlanning.getLength() -1; i++)
		{
			if (nlSearchParamsDeliveryPlanning.item(i).getNodeType() == Node.ELEMENT_NODE)
			{
				ele = (Element) nlSearchParamsDeliveryPlanning.item(i);
				strSupplierValue = ele.getElementsByTagName("Supplier").item(0).getTextContent(); 
			                     
				strConsigneeValue = ele.getElementsByTagName("Consignee").item(0).getTextContent(); 
			                     
				strNextStageShipmentBookingValue = ele.getElementsByTagName("Next_Stage").item(0).getTextContent();
			                     
				strNextStageVendorValue = ele.getElementsByTagName("Next_Stage_VendorBooking").item(0).getTextContent();
			                     
				strNextStageCargoReceiptValue = ele.getElementsByTagName("Next_Stage_CargoReceipt").item(0).getTextContent();
			                     
				strNextStageConfornArrivalValue = ele.getElementsByTagName("Next_Stage_ConfirmArrival").item(0).getTextContent();
			                     
				strWFLRefValue = ele.getElementsByTagName("WFL_Ref").item(0).getTextContent(); 
			                     
				strWFLRefCustomerValue=ele.getElementsByTagName("Customer").item(0).getTextContent();
			                     
				strWFLDestinationPortValue =ele.getElementsByTagName("Destination_Port").item(0).getTextContent();
			                     
				strProductValue = ele.getElementsByTagName("Product").item(0).getTextContent(); 
			                     
				strProductSKUValue = ele.getElementsByTagName("Product_SKU").item(0).getTextContent(); 

				strLineTypeWFLRefValue = ele.getElementsByTagName("LineType_WFLRef").item(0).getTextContent();
			                    
				strVendorBookingWFLRefValue = ele.getElementsByTagName("Vendor_Booking_WFLRef").item(0).getTextContent();
			                     
				strConformArrivalWFLRefValue = ele.getElementsByTagName("ConformArrival_WFLRef").item(0).getTextContent();
			                     
				strSplitDeliveryWFLRefValue = ele.getElementsByTagName("Split_Delivery_WFLRef").item(0).getTextContent(); 

				strLineTypeValue = ele.getElementsByTagName("SetLine_Type_Value").item(0).getTextContent(); 
			                     
				strLineTypeStockOrderValue = ele.getElementsByTagName("SetLine_Type_Value_StockOrder").item(0).getTextContent();
			                     
				strLineTypeDirectOrderValue = ele.getElementsByTagName("SetLine_Type_Value_DirectOrder").item(0).getTextContent();

				strWFLGridValidationMessageValue =ele.getElementsByTagName("WFL_Grid_ValidationMsg").item(0).getTextContent();
				
				strColumnCaptionWFLRef = ele.getElementsByTagName("Column_Caption_WFLRef").item(0).getTextContent(); 
			      			                    
				strColumnCaptionVendor = ele.getElementsByTagName("Column_Caption_Vendor").item(0).getTextContent();
			                     
				strSplitDeliveryYValue = ele.getElementsByTagName("Split_Delivery_Y").item(0).getTextContent();

				strSplitDeliveryNValue = ele.getElementsByTagName("Split_Delivery_N").item(0).getTextContent();
			                     
				strBookingRefValue = ele.getElementsByTagName("BookingRef_value").item(0).getTextContent();
			                    
				strDeliveryDatePastValue = ele.getElementsByTagName("Delivery_Date_Past").item(0).getTextContent();
			                     
				strDeliveryDateFutureValue = ele.getElementsByTagName("Delivery_Date_Future").item(0).getTextContent();

				strDeliveryDateETAValue = ele.getElementsByTagName("Delivery_Date_ETA").item(0).getTextContent();
			                     
				strDeliveryTimeValue = ele.getElementsByTagName("DeliveryTime").item(0).getTextContent();
			                     
				strCompleteStageWFLRefValue = ele.getElementsByTagName("CompleteStage_WFLRef").item(0).getTextContent();

				strCompleteStageSKUValue = ele.getElementsByTagName("CompleteStage_Product_SKU").item(0).getTextContent();
			                     
				strCompleteConsigneeValue = ele.getElementsByTagName("CompleteStage_Consignee").item(0).getTextContent();

				strCompleteDestinationValue = ele.getElementsByTagName("CompleteStage_Destination").item(0).getTextContent();

				strCompletionMessageValue = ele.getElementsByTagName("WFL_Grid_CompleteValidationMsg").item(0).getTextContent();
				
				strRevisedQuantityValue = ele.getElementsByTagName("RevisedQuantity_GreaterValue").item(0).getTextContent();
			                     
				strRevisedQuantityLesserValue = ele.getElementsByTagName("RevisedQuantity_LesserValue").item(0).getTextContent();

				strConsigneeSelectPageValue = ele.getElementsByTagName("SetConsignee_Value").item(0).getTextContent();
			                     
				strConformArrivalSKUValue = ele.getElementsByTagName("ConformArrival_SKU").item(0).getTextContent();
			                     
				strConformArrivalConsigneeValue = ele.getElementsByTagName("ConformArrival_Consignee").item(0).getTextContent();
				
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
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strTitlecatalog=objAdjunoWFLDeliveryPlanningPOM.callMouseHover(strCatalogFormName, objAdjunoWFLDeliveryPlanningPOM.getLnkTools(), objAdjunoWFLDeliveryPlanningPOM.getLnkCatalog());
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
		objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 0 - Page title of Catalog Tool is wrong *****");
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getLnklighthouse()),true,"***** In catalog Tool \"LightHouse\" button is not clicked *****");
		wait(3000);
		objSoftAssert.assertAll();
	}
			
	private boolean isNullOrBlank(String s)	{
				
		return (s==null || s.trim().equals(""));
				
	}
			
		  	
	//clear CatalogField
	public void clearCatalogField(){
				
		SoftAssert objSoftAssert = new SoftAssert();
	//	wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strCatalogFormName,"Param_Name"), true,"***** In catalog Tool under Light house page\"Name\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strCatalogFormName,"Param_Type"), true,"***** In catalog Tool under Light house page\"Type\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strCatalogFormName,"Param_Description"), true,"***** In catalog Tool under Light house page\"Description\" field is not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strCatalogFormName,"Param_AssociatedItem"), true,"***** In catalog Tool under Light house page\"Associated item\" field is not cleared *****");
				
		objSoftAssert.assertAll();
	}
			
	// get the catalog values on different field 
	@Test(priority=5)
	public void getCatalogValues()
	{
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		
		clearCatalogField(); 
		if(!isNullOrBlank(strCustomer)){
			 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCustomer, strCatalogFormName, "Param_Type"),true,"***** Test ID : 2.004 - In catalog Tool under Light house page\"Type\" field value is not set *****");
		 }else{
			 strMessage=strMessage+"Customer Value is Empty in Xml";
		 }
		wait(3000);
	     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.004 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
	     catalogCustomerList=objAdjunoWFLDeliveryPlanningPOM.getCatalogData();
	     System.out.println("Customer drop down 12 "+ catalogCustomerList.size());
	     if(catalogCustomerList.size()>0){
				
		 }else{
			 strMessage = strMessage +"***** Test ID : 2.004 - In catalog Tool under Light house page\"Customer\" field doesn't have data *****";
		 }
		     
	     //set the Suppliers value in textbox
	     if(!isNullOrBlank(strSupplier)){
					        	
	    	 clearCatalogField();
	//    	 wait(5000);
	    	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 2.001 - In catalog Tool under Light house page\"Type\" field value is not set *****");
		     		
	    	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.001 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
	    	 wait(3000);
	    	 catalogCustSupplierList = objAdjunoWFLDeliveryPlanningPOM.getCatalogTableData(objAdjunoWFLDeliveryPlanningPOM.getLstSupplierNameDescription(), objAdjunoWFLDeliveryPlanningPOM.getLstSupplierDescriptionField());
		     		
	     }else{
				        	
	    	 strMessage = strMessage + "***** Test ID : 2.001- In catalog Tool under Light house page\"strSupplier\" value is empty in xml *****";
	     }
	     System.out.println("catalogCustSupplierList is 3 " + catalogCustSupplierList.size());
	     if(catalogCustSupplierList.size()>0){
			        	
	     }else{
	    	 strMessage = strMessage +"***** Test ID : 2.001 - In catalog Tool under Light house page\"Supplier\" field doesn't have data *****";
	     }
			     	
	     if(!isNullOrBlank(strConsignee)){
			       
	    	 clearCatalogField();
	 	     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strConsignee, strCatalogFormName,"Param_Type"), true,"***** Test ID : 2.002 - Type Field value is not entered as Supliers*****");
			 	     		
	 	     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.002 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
	 	     wait(3000);		
	 	     catalogConsigneeList = objAdjunoWFLDeliveryPlanningPOM.getCatalogTableData(objAdjunoWFLDeliveryPlanningPOM.getLstNameDescription(), objAdjunoWFLDeliveryPlanningPOM.getLstConsigneeDescriptionField());
		 	     		
	     }else{
		 			        	
	    	 strMessage = strMessage + "***** Test ID : 2.002 - In catalog Tool under Light house page\"strConsignee\" value is empty in xml *****";
	     }
	     System.out.println("catalogConsigneeList is 4 " + catalogConsigneeList.size());
	     if(catalogConsigneeList.size()>0){
		 		        	
	     }else{
	    	 strMessage = strMessage +"***** Test ID : 2.002 - In catalog Tool under Light house page\"Consignee\" field doesn't have data *****";
	     }
			 	     
	    //Setting the field value LODOCE setFieldValue
	     if(!isNullOrBlank(strLoCode))
	     {
	    	 clearCatalogField();
	    	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strLoCode, strCatalogFormName,"Param_Type"), true,"***** Test ID : 2.011 - In catalog Tool under Light house page\"Type\" field value is not set *****"); 
			    	
	    	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.011 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
	    	 wait(5000);
				                                
	    	 int nCount4 = objAdjunoWFLDeliveryPlanningPOM.valCount();
	    	 catalogLoCodeList = objAdjunoWFLDeliveryPlanningPOM.getCatalogTableData(nCount4,objAdjunoWFLDeliveryPlanningPOM.getLOCODEIsCheckboxClicked(),objAdjunoWFLDeliveryPlanningPOM.getLstDescriptionField());
	    	 wait(2000);
					       
	     }else{
				        	
	    	 strMessage = strMessage + "***** Test ID : 2.011- In catalog Tool under Light house page\"strLoCode\" value is empty in xml *****";
	     }
	     System.out.println("catalogLoCodeList is 57 " + catalogLoCodeList.size());

	     if(catalogLoCodeList.size()>0){
				        	
	     }else{
	     	strMessage = strMessage +"***** Test ID : 2.011 - In catalog Tool under Light house page\"LOCODE\" field doesn't have data *****";
	     }
		     	
	     //set the Suppliers value in textbox
	     if(!isNullOrBlank(strSupplier)){
						        	
	    	 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 2.012 - In catalog Tool under Light house page\"Previous\" button is not clicked *****");
	    	 wait(3000);
	    	 clearCatalogField();
		     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSupplier, strCatalogFormName,"Param_Type"), true,"***** Test ID : 2.012 - In catalog Tool under Light house page\"Type\" field value is not set *****");
			     
		     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.012 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
		     wait(3000);		
		     catalogSupplierList = objAdjunoWFLDeliveryPlanningPOM.getCatalogSupplierTableData();
		     		
	     }else{
						        	
	    	 strMessage = strMessage + "***** Test ID : 2.012 - In catalog Tool under Light house page\"strSupplier\" value is empty in xml *****";
	     }
	     System.out.println("catalogSupplierList is 34 " + catalogSupplierList.size());

	     if(catalogSupplierList.size()>0){
						        	
	     }else{
	    	 strMessage = strMessage +"***** Test ID : 2.012 - In catalog Tool under Light house page\"Supplier\" field doesn't have data *****";
	     } 
				 
		 //set the Destination value in textbox
		 if(!isNullOrBlank(strDestination)){
			 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 2.024 - In catalog Tool under Light house page\"Previous\" button is not clicked *****");
			 wait(3000);
			 clearCatalogField();
		     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strDestination, strCatalogFormName,"Param_Type"), true,"***** Test ID : 2.024 - In catalog Tool under Light house page\"Type\" field value is not set *****");
		     wait(3000);
		     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.024 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
		     int nCount4 = objAdjunoWFLDeliveryPlanningPOM.valCount();
	    	 catalogDestinationList = objAdjunoWFLDeliveryPlanningPOM.getCatalogTableData(nCount4,objAdjunoWFLDeliveryPlanningPOM.getIsDestinationCheckBoxClicked(),objAdjunoWFLDeliveryPlanningPOM.getLstDestinationDescription());
	    	 wait(2000);
				       
	     }else{
				        	
	    	 strMessage = strMessage + "***** Test ID : 2.024 - In catalog Tool under Light house page\"strDestination\" value is empty in xml *****";
	     }
	     System.out.println("catalogDestinationList is 11 " + catalogDestinationList.size());

	     if(catalogDestinationList.size()>0){
					        	
	     }else{
	     	strMessage = strMessage +"***** Test ID : 2.024 - In catalog Tool under Light house page\"Destination\" field doesn't have data *****";
	     }
			 
		 //set the Destination value in textbox
		 if(!isNullOrBlank(strDestination)){
			 objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnPagePrev()),true,"***** Test ID : 2.035 - In catalog Tool under Light house page\"Previous\" button is not clicked *****");
			 wait(3000);
			 clearCatalogField();
		     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strDestination, strCatalogFormName,"Param_Type"), true,"***** Test ID : 2.035 - In catalog Tool under Light house page\"Type\" field value is not set *****");
		     wait(3000);
		     objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()),true,"***** Test ID : 2.035 - In catalog Tool under Light house page\"Apply\" button is not clicked *****");
		     
		     int nCount4 = objAdjunoWFLDeliveryPlanningPOM.valCount();
	    	 catalogDestinationConsigneeList = objAdjunoWFLDeliveryPlanningPOM.getCatalogTableDataDestinationType(nCount4, objAdjunoWFLDeliveryPlanningPOM.getLstDestinationDescription(), objAdjunoWFLDeliveryPlanningPOM.getLstAssociationDescription(), strConsigneeSelectPageValue);
	    	 
	    	 			 
			}else{
			        	
		  	 	strMessage = strMessage + "***** Test ID : 2.035 - In catalog Tool under Light house page\"strDestination\" value is empty in xml *****";
		 }
				

		 System.out.println("catalogDestinationConsigneeList is 2 " + catalogDestinationConsigneeList.size());

	     if(catalogDestinationConsigneeList.size()>0){
					        	
	     }else{
	     	strMessage = strMessage +"***** Test ID : 2.035 - In catalog Tool under Light house page\"Destination\" field doesn't have data *****";
	     }
			     
	     objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,  strMessage);
	     objSoftAssert.assertAll();
			     
	 }	 
			
	//Select DelevertPlanning From ToolMenu 
	@Test(priority=10)
	public void test_2d008_VerifySearchPageLoaded(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		wait(3000);		
		String strTitle= objAdjunoWFLDeliveryPlanningPOM.callMouseHover(strDeliveryPlanningFormName, objAdjunoWFLDeliveryPlanningPOM.getLnkTools(), objAdjunoWFLDeliveryPlanningPOM.getLnkDeliveryPlanning());
		boolean bFlag = true;
				
		if (isNullOrBlank(strTitle))
			bFlag = false;
			
		if (!(isNullOrBlank(strTitle)))
		{
			if (strTitle.equalsIgnoreCase(strDeliveryPlanningPageTitle))
				bFlag = true;
			else	
				bFlag = false;
		}       
		wait(2000);
		objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 8 - Page title of Devlvery Planning tool is wrong  *****");
		objSoftAssert.assertAll();
			
	}
		
	//Inspect customer DropDown
	@Test(priority=15)
	public void test_2d004_VerifyCustomerDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
			
		customerList=objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_Customer");
	//	System.out.println("customerList size is "+customerList.size());
		if(customerList.size()>0){
		}else{
			strMessage=strMessage+"In Delivery Planning Tool under Search chevron \"Customer\" dropdown doesn't have data ";
		}
			
		strMessage =strMessage+ objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogSupplierList, customerList);
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.004"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
		
	//set the WorkflowStatus drop down value
	@Test(priority=20)
	public void test_2d006_VerifyWorkflowStatusValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
			
		workFlowStatusList=objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_WorkflowStatus");
	//	System.out.println("workFlowStatusList size is "+workFlowStatusList.size());
		if(workFlowStatusList.size()==3){
		}else{
			strMessage=strMessage+"In Delivery Planning Tool under Search chevron \"WorkFlowStatus\" dropdown doesn't have data ";
		}
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.006"+ strMessage +"*****");
		objSoftAssert.assertAll();
						
	}
			
	//set the NextStage drop down value
	@Test(priority=25)
	public void test_2d007_VerifyNextStageValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		
		nextStageList=objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_NextStage");
	//	System.out.println("nextStageList size is "+nextStageList.size());
		if(nextStageList.size()==8){
		}else{
			strMessage=strMessage+"In Delivery Planning Tool under Search chevron \"NextStage\" dropdown doesn't have data ";
		}
							
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.007"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	//set the Supplier drop down value
	@Test(priority=30)
	public void test_2d012_VerifySupplierdropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
			
		supplierList=objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_Supplier");
//		System.out.println("supplierList size is "+supplierList.size());
		if(nextStageList.size()>0){
		}else{
			strMessage=strMessage+"In Delivery Planning Tool under Search chevron \"Supplier\" dropdown doesn't have data ";
		}
			
		strMessage =strMessage+ objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogSupplierList, supplierList);
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.012"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
		
	//set the destination port drop down value
	@Test(priority=35)
	public void test_2d005_SetDestinationDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()), true , "***** Test ID : 2.005 - In Delivery Planning Tool \"Select \" chevron is not clicked *****");
		System.out.println(strWFLDestinationPortValue +"is xml value");
			
		if(!isNullOrBlank(strWFLDestinationPortValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strWFLDestinationPortValue, strDeliveryPlanningFormName, "Param_DestinationPort"),true,"***** Test ID : 2.005 - In Delivery Planning Tool under Select chevron\"DestinationPort\" field value is not set  *****");
		}else{
			strMessage=strMessage+" DestinationPort Value is Empty in Xml";
		}
			
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.005 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked *****");
			
		List<WebElement> lstWFLReference=objAdjunoWFLDeliveryPlanningPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
			lstWFLReference.get(i).click();
			String window= objAdjunoWFLDeliveryPlanningPOM.getWindowIds();
			String [] pageWindow=window.split(";");
			driver.switchTo().window(pageWindow[1]);
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()), true , "***** Test ID : 2.005 - In Delivery Planning Tool under WFLRef Page\"Apply\" button is not clicked *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLDestinationPortValue, objAdjunoWFLDeliveryPlanningPOM.getFieldValue(strWFLDeliveryPlanningRefLinkFormName, "DestinationPort")), true , " ***** Test ID : 2.005 - In Delivery Planning Tool under Select Chevron the Destination Port Value which was entered in \"DestinationPort\" field is : "+strWFLDestinationPortValue +" but the \"DestinationPort\" value found under \"WFLRef Link\" page is : "+ objAdjunoWFLDeliveryPlanningPOM.getFieldValue(strWFLDeliveryPlanningRefLinkFormName, "DestinationPort")+" is not Matched ***** ");	
			driver.close();
			driver.switchTo().window(pageWindow[0]);
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.005"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
			
	//Set the customer DropDown value
	@Test(priority=40)
	public void test_2d010_SetCustCustomerValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
		if(!isNullOrBlank(strWFLRefCustomerValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strWFLRefCustomerValue, strDeliveryPlanningFormName,"PARAM_Customer"), true,"***** Test ID : 2.010 - In Delivery Planning Tool under Select chevron\"Customer\" field value is not set  *****");
		}else{
			strMessage=strMessage+" Customer Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strWFLDestinationPortValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strWFLDestinationPortValue, strDeliveryPlanningFormName, "Param_DestinationPort"),true,"***** Test ID : 2.010 - In Delivery Planning Tool under Select chevron\"DestinationPort\" field value is not set  *****");
		}else{
			strMessage=strMessage+"DestinationPort Value is Empty in Xml";
		}
		wait(3000);	
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.010 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked  *****");
		List<WebElement> lstWFLReference=objAdjunoWFLDeliveryPlanningPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
		lstWFLReference.get(i).click();
		String window= objAdjunoWFLDeliveryPlanningPOM.getWindowIds();
		String [] pageWindow=window.split(";");
		driver.switchTo().window(pageWindow[1]);
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()), true , "***** Test ID : 2.010 - In Delivery Planning Tool under WFLRef Page\"Apply\" button is not clicked *****");
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLRefCustomerValue, objAdjunoWFLDeliveryPlanningPOM.getFieldValue(strWFLDeliveryPlanningRefLinkFormName, "Customer")), true , "***** Test ID : 2.010 - In Delivery Planning Tool under Select chevron the Customer Value which was entered in \"Customer\" field is : "+ strWFLRefCustomerValue +" but the \"Customer\" value found under \"WFLRef Link\" page is : "+ objAdjunoWFLDeliveryPlanningPOM.getFieldValue(strWFLDeliveryPlanningRefLinkFormName, "Customer")+" is not Matched ***** ");
		System.out.println(objAdjunoWFLDeliveryPlanningPOM.getFieldValue(strWFLDeliveryPlanningRefLinkFormName, "Customer"));
		driver.close();
		driver.switchTo().window(pageWindow[0]);
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.010"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
				
	//set the Supplier drop down value
	@Test(priority=45)
	public void test_2d013_SetSupplierDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
			
		if(!isNullOrBlank(strSupplierValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSupplierValue, strDeliveryPlanningFormName,"PARAM_Supplier"), true,"***** Test ID : 2.013 - In Delivery Planning Tool under Select chevron\"Supplier\" field value is not set *****");
		}else{
			strMessage=strMessage+" Supplier Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.013 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked *****");
		wait(3000);
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifySearchValue(strSupplierValue,objAdjunoWFLDeliveryPlanningPOM.getLstSetSupplierValue());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.013"+ strMessage +"*****");
		objSoftAssert.assertAll();
	
	}
				
	//set the Consignee drop down value
	@Test(priority=50)
	public void test_2d014_SetConsigneeDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
				
		if(!isNullOrBlank(strConsigneeValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strConsigneeValue, strDeliveryPlanningFormName,"PARAM_Consignee"), true,"***** Test ID : 2.014 - In Delivery Planning Tool under Select chevron\"Consignee\" field value is not set  *****");
		}else{
			strMessage=strMessage+" Consignee Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.014 - In Delivery Planning Tool under Select chevron\"RefineSearch\"button is not clicked *****");
		wait(3000);	
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifySearchValue(strConsigneeValue,objAdjunoWFLDeliveryPlanningPOM.getLstSetConsigneeValue());
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.014"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
			
	//set the NextStage drop down value
	@Test(priority=55)
	public void test_2d016_SetNextStageDropDownValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
				
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.016 - In Delivery Planning Tool under Select chevron\"NextStage\" field value is not set  *****");
		}else{
			strMessage=strMessage+" NextStage Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.013 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked *****");
		wait(3000);	
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifySearchValue(strNextStageCargoReceiptValue,objAdjunoWFLDeliveryPlanningPOM.getLstSetNextStageValue());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.016"+ strMessage +"*****");
		objSoftAssert.assertAll();			
	}
				
	//set the WFLRef value
	@Test(priority=60)
	public void test_2d017_SetWFLRefValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
					
		if(!isNullOrBlank(strWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.017 - In Delivery Planning Tool under Select chevron\"WFLRef\" field value is not set  *****");
		}else{
			strMessage=strMessage+" WFLRef Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.017 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked  *****");
		wait(3000);
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifySearchValue(strWFLRefValue,objAdjunoWFLDeliveryPlanningPOM.getLstSetWFLRefValue());
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.017"+ strMessage +"*****");
		objSoftAssert.assertAll();		
			
	}
				
	//set the Product value
	@Test(priority=65)
	public void test_2d018_SetProductValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		wait(3000);
		clearFields();
			
		if(!isNullOrBlank(strProductValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strProductValue, strDeliveryPlanningFormName,"PARAM_Product"), true,"***** Test ID : 2.018 - In Delivery Planning Tool under Select chevron\"Product\" field value is not set  *****");
		}else{
			strMessage=strMessage+" Product Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.018 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked *****");
		wait(3000);
		List<WebElement> lstWFLReference=objAdjunoWFLDeliveryPlanningPOM.getLstWFLRef();
		for(int i=0;i<lstWFLReference.size();i++){
			lstWFLReference.get(i).click();
			String window= objAdjunoWFLDeliveryPlanningPOM.getWindowIds();
			String [] pageWindow=window.split(";");
			driver.switchTo().window(pageWindow[1]);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getCatalogbtnApply()), true , "***** Test ID : 2.018 - In Delivery Planning Tool under WFLRef Page\"Apply\" button is not clicked *****");
			wait(3000);
			String strProductValueWFLRefLink = objAdjunoWFLDeliveryPlanningPOM.getStrProductValue().getText();
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strProductValue, strProductValueWFLRefLink), true , " ***** Test ID : 2.018 - In Delivery Planning Tool under Select chevron the Product Value which was entered in \"Product\" field is : "+ strProductValue +" but the \"Product\" value found under \"WFLRef Link\" page is : "+ strProductValueWFLRefLink +" is not Matched ***** ");
			System.out.println("Product value is "+strProductValue);
			driver.close();
			driver.switchTo().window(pageWindow[0]);
			}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.018"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
				
	//set the ProductSKU value
	@Test(priority=70)
	public void test_2d019_SetProductSKUValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		clearFields();
				
		if(!isNullOrBlank(strProductSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strProductSKUValue, strDeliveryPlanningFormName,"PARAM_SKU"), true,"***** Test ID : 2.019 - In Delivery Planning Tool under Select chevron\"ProductSKU\" field value is not set  *****");
		}else{
			strMessage=strMessage+" ProductSKU Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.019 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked  *****");
		wait(3000);		
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifySearchValue(strProductSKUValue ,objAdjunoWFLDeliveryPlanningPOM.getLstProductSKUValue());
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.019"+ strMessage +"*****");
		objSoftAssert.assertAll();	
	}
				
	//Check Delivery Planning Page is Loaded
	@Test(priority=75)
	public void test_2d021_CheckDeliveryPlanningPageLoad(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
								
		lstSearchResults = new ArrayList<DeliveryPlanning>();
	        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.021 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked  *****");
			wait(3000);           
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.021 - In Delivery Planning Tool under Select chevron\"No items were Displayed\" message is displayed *****");
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName), true ,"***** Test ID : 21 - In Delivery Planning Tool Page title of Delivery Planning chevron is not loaded *****");
		objSoftAssert.assertAll();
								
	}
			   
				
	@Test(priority=80)
	public void test_2d022_CheckLineTypeMandatoryfield(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.022 - In Delivery Planning Tool \"Select\" chevron is not clicked  *****");
		wait(3000);
		clearFields();
		if(!isNullOrBlank(strLineTypeWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strLineTypeWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.022 - In Delivery Planning Tool under Select chevron\"OrderNumber\" field value is not set  *****");
		}else{
			strMessage=strMessage+"\" WFLRef\" Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.022 - In Delivery Planning Tool under Select chevron\"Refine Search\" button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
	         
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
	//		objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 2.022 - In Delivery Planning Tool under Select chevron No Rows are selected *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.022 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked *****");
			wait(3000);             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.022 - In Delivery Planning Tool under Select chevron\"No Items were found\" message is displayed *****");
		}
				
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeValue), true ,"***** Test ID : 2.022 - In Delivery Planning Tool under Delivery Planning chevron\"LineType\" Grid Drop down value is not set *****" );
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearGridInputField(strDeliveryPlanningStageFormName, "GridSKU", 0, "ConsigneeDesc"), true,"***** Test ID : 2.022 - In Delivery Planning Tool Under Delivery Planning chevron \"Consignee\" Field is not cleared *****");
			wait(5000);
				
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true , "***** Test ID : 2.022 - In Delivery Planning Tool \"Complete\" chevron is not clicked  *****");
			wait(5000);	
					
			String gridValidationMessage =objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg());
	//		System.out.println(gridValidationMessage);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, gridValidationMessage), true , "***** Test ID : 2.022 - In Delivery Planning Tool under Complete chevron \"Expected value\" is :"+ strWFLGridValidationMessageValue + " but found : "+ gridValidationMessage +" *****");
		}else{
					
			objSoftAssert.assertEquals(false, true, "***** Test ID : 2.020 - In Delivery Planning Tool\"Delivery Planning\" Chevron page is not loaded *****");
		}
				
			objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.022"+ strMessage +"*****");
			objSoftAssert.assertAll();
					
	}
				
	@Test(priority=85)
	public void test_2d023_InspectTopGridValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.023 - In Delivery Planning Tool \"Select\" chevron is not clicked  *****");
		wait(3000);
		clearFields();
		if(!isNullOrBlank(strLineTypeWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strLineTypeWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.023 - In Delivery Planning Tool under Select chevron\"OrderNumber\" field value is not set  *****");
		}else{
			strMessage=strMessage+"\" WFLRef\" Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.023 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked *****");
		wait(3000);	
		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		         
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.023 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked *****");
		wait(3000);	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.023 - In Delivery Planning Tool under Select chevron\"No items were found.\" message is displayed ****");
		}
		
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strColumnCaptionWFLRef, objAdjunoWFLDeliveryPlanningPOM.getColumnCaptionValue(strDeliveryPlanningStageFormName, "GridOrder", "OrderLink", 0)), true , "***** Test ID : 2.023 - In Delivery Planning Tool under Delivery Planning chevron\"OrderNumber\" Column is not displayed  *****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strColumnCaptionVendor, objAdjunoWFLDeliveryPlanningPOM.getColumnCaptionValue(strDeliveryPlanningStageFormName, "GridOrder", "Vendor", 0)), true , "***** Test ID : 2.023 - In Delivery Planning Tool under Delivery Planning chevron\"Vendor\" Column is not displayed *****");
		}else{
					
			objSoftAssert.assertEquals(false, true, "***** Test ID : 2.020 - In Delivery Planning Tool\"Delivery Planning\" Chevron page is not loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.023"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	} 
			
	//Inspect Generic Search Term Results
	@Test(priority=95)
	public void test_2d020_InspectGenericSearchTermResults(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.020 - In Delivery Planning Tool \"Select\" chevron is not clicked *****");
		wait(3000);
		clearFields();
		if(!isNullOrBlank(strLineTypeWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strLineTypeWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.020 - In Delivery Planning Tool under Select chevron\"OrderNumber\" field value is not set *****");
		}else{
			strMessage=strMessage+"\" WFLRef\" Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.020 - In Delivery Planning Tool under Select chevron\"RefineSearch\"button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			         
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.020 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked *****");
			wait(3000);            
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.020 - In Delivery Planning Tool under Select chevron\"No items were found.\" message is displated *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(bSearchResultsProductsFound)
			{
				String strReturnMessage = objAdjunoWFLDeliveryPlanningPOM.verifyProductsDataOnGrid(strDeliveryPlanningStageFormName,"GridSKU",lstSearchResults);
					
				objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.020 - The data which was selected under Selected chevron is not matching with the data which is present under Delivery Planning chevron *****"	);
							  
			}else{
				objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.020 - In Delivery Planning Tool under Select chevron \"No items were found\" message is displayed *****");
			}  
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.020 - In Delivery Planning Tool\"Delivery Planning\" Chevron page is not loaded *****");
		}
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.020"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
			
	//Inspect Product Destination Drop Down
	@Test(priority=100)
	public void test_2d024_InspectProductDestinationDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.024 - In Delivery planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);	
		clearFields();
		if(!isNullOrBlank(strVendorBookingWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strVendorBookingWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.024 - In Delivery planning tool under Select chevron \"Order number\" field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFLRef\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.024 - In Delivery planning tool under Select chevron \"NextStage\" field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.024 - In Delivery planning tool under Select chevron \"Refine Search \" button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			         
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
			   
		//	objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 2.024 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.024 - In Delivery planning tool \"Delivery Planning\" chevron value is not clicked *****");
			wait(3000);             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.024 - In Delivery Planning tool under delivery Planning chevron \"No Search products is displayed \" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Split Delivery arrow\" is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Split Delivery\" field value is not entered as \"N\" *****" );
			wait(3000);
			
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
							
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeValue), true ,  "***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Line Type\" field is not entered *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDestinationReadMode()), true ,"***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Product Destination \" Drop Down is not clicked *****");
				wait(3000);
				destinationListSKUGrid= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstDestinationDropDown());
				wait(3000);
				if(destinationListSKUGrid.size()>0){
					
				}else{
					strMessage = strMessage +" In Delivery Planning tool under delivery Planning chevron \"Product Destination\" dropdown doesn't have value *****";
				}
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Split Delivery\" arrow is not clicked *****");
				wait(2000);
					
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \"Split Delivery\" field value is not entered as \"Y\" *****");
				wait(3000);
				if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDestinationDropDownSplitGrid()), true ,"***** Test ID : 2.024 -  In Delivery Planning tool under delivery Planning chevron \" Grid Product Destination\" drop down field is not clicked *****");
					wait(3000);
								
					destinationListSplitGrid= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getlstDestinationDropDownSplitGrid());
					if(destinationListSplitGrid.size()>0){
					//	strMessage = strMessage + objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogDestinationList, destinationListSplitGrid);						

					}else{
						strMessage = strMessage + " In Delivery planning tool under delivery chevron \"Grid Product Destination\"drop down doesn't have value ";
					}
							
				}else{
					strMessage =strMessage +" In Delivery planning tool under delivery planning chevron after entered the \"Split Delivery\" field value as \"Y\",the \"Split Grid\" is not populated";
				}
			}
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.024 - In Delivery Planning Tool \"Delivery planning Stage\" is not Loaded *****");
		}
		
							
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.024"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	
	//Enter Split Delivery as a "N"
	@Test(priority=105)
	public void test_2d025_EnterSplitDeliveryValueN(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.025 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
			
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.025 - In Delivery Planning tool under Select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFLRef\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.025 - In Delivery Planning tool under Select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.025 - In Delivery Planning tool under Select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);
		StrLineTypeFieldValue=objAdjunoWFLDeliveryPlanningPOM.getLstLineTypevalue().getText();
			
		lstSearchResults = new ArrayList<DeliveryPlanning>();
	      
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
			   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.025 - In Delivery Planning tool \" Delivery planning chevron \" is not clicked *****");
			             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.025 - In Delivery Planning tool under Select chevron \"Search Products\" is not displayed *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" Field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" Field value is not entered as \"Y\" *****" );
				wait(2000);
					
			}

				
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" Field is not clicked *****");
				wait(4000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" arrow is not clicked *****");
				wait(4000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" Field value is not entered as \"N\" *****" );
				wait(4000);
					
				if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkLineTypeReadeMode()), true ,"***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron \"LineType\" Field is not clicked *****");
					wait(3000);
					strLineTypeValue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc");
					objSoftAssert.assertEquals(strLineTypeValue.equalsIgnoreCase(StrLineTypeFieldValue), true , "***** Test ID : 2.025 - In Delivery Planning tool under Delivery planning chevron, \"LineType\" value which is present in \"SKU Grid\" is not matching with the \"LineType\" value which was displayed in Split Grid *****");
						
				}else{
					strMessage =strMessage +" In Delivery Planning tool under delivery Planning chevron after entered the Split Delivery field value as \"N\", The Split Grid is still Populating";
				}		
			}
					
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.025 - In Delivery planning tool \"Delivery Planning page\" is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.025"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
				
	@Test(priority=110)
	public void test_2d026_InspectLineTypeDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.026 - In Delivery Planning Tool \"Select\" chevron is not clicked *****");
					
		clearFields();
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.026 - In Delivery Planning Tool under Select chevron\"OrderNumber\" field value is not set  *****");
		}else{
			strMessage=strMessage+" \"WFLRef\" Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.026 - In Delivery Planning Tool under Select chevron\"RefineSearch\" button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		         
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.026 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked *****");
		wait(3000);           
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.026 - In Delivery Planning Tool under Select chevron\"No Search Results \" found *****");
		}
		wait(3000);
		if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.026 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" Field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.026 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.026 - In Delivery Planning tool under Delivery planning chevron \"Split Delivery\" Field value is not entered as \"N\" *****" );
			wait(2000);
		}
		if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkLineTypeReadeMode()), true , "***** Test ID : 2.026 - In Delivery Planning Tool under Delivery Planning chevron\"LineType\" Drop Down is not clicked*****");
					
			wait(3000);
			lineTypeList= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstLineTypeValue());
			System.out.println("lineTypeList size is "+lineTypeList.size());

		//	strMessage = objAdjunoWFLDeliveryPlanningPOM.verifyCaptionsONGrid(lineTypeList,nlLegsGridDeliveryPlanning,3);

				        	
		}else{
			strMessage =strMessage +"***** Test ID : 2.026 - In Delivery Planning tool under Delivery planning chevron, after entered the \"Split Delivery\" Field as \"N\" the split Grid is still populated *****";
		}
				
	
		wait(5000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.026 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
		wait(2000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.026 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" arrow is not clicked *****");
		wait(2000);
						
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.037 - In Delivery Planning Tool under Delivery Planning chevron\"SplitDelivery\" Grid Drop down value is not set as \"Y\"*****" );
		wait(1200);
		if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkLineTypeReadmodeSplitgrid()), true , "***** Test ID : 2.037 - In Delivery Planning Tool under Delivery Planning chevron\"LineType\" Grid Drop down is not clicked *****");

		wait(3000);
											
		wait(3000);
		lineTypeListSplitGrid= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstDestinationDropDown());
		System.out.println("lineTypeListSplitGrid size is "+lineTypeListSplitGrid.size());

	//	strMessage = objAdjunoWFLDeliveryPlanningPOM.verifyCaptionsONGrid(lineTypeListSplitGrid,nlLegsGridDeliveryPlanning,3);
				
					
		}else {
			strMessage=strMessage + "In Delivery Planning Tool under delivery planning chevron,after entered the \"Split Delivery\" field value as \"Y\" , the \"Split Grid\" is not populated ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.026"+ strMessage +"*****");
		objSoftAssert.assertAll();
							
	}
			
	//Inspect SKU Grid Value
	@Test(priority=115)
	public void test_2d027_InspectSKUGridValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		wait(3000);	
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.027 - In Delivery Planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);	
		clearFields();
		if(!isNullOrBlank(strProductSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strProductSKUValue, strDeliveryPlanningFormName,"PARAM_SKU"), true,"***** Test ID : 2.027 - In Delivery Planning tool under delivery planning chevron \"ProductSKU\" field value is Not entered *****");
		}else{
			strMessage=strMessage+" \"ProductSKU\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.027 -  In Delivery Planning tool under delivery planning chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		         
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.027 -  In Delivery Planning tool \"delivery planning chevron\" is Not clicked *****");
		wait(3000);           
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.027 -  In Delivery Planning tool under select chevron \"No products found\" under Search Results.*****");
		}
		
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			String strReturnMessage = objAdjunoWFLDeliveryPlanningPOM.verifyProductsDataOnGrid(strDeliveryPlanningStageFormName,"GridSKU",lstSearchResults);
						
			objSoftAssert.assertEquals(strReturnMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.027 - In Delivery Planning Tool the Products which was present under select chevron is not matching with the products which is displaying under Delivery Planning chevron *****"	);
									  		
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.027 - In Delivery planning tool \"Delivery Planning Stage\" is not Loaded *****");
		}
							
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.027"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
			
	//Select 2nd row in Order grid
	@Test(priority=120)
	public void test_2d028_SelectSecondRowInOrderGrid(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.028 - In Delivery planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
		if(!isNullOrBlank(strSupplierValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSupplierValue, strDeliveryPlanningFormName,"PARAM_Supplier"), true,"***** Test ID : 2.028 - In Delivery Planning Tool Under select chevron \"Supplier\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" Supplier Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strNextStageVendorValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageVendorValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.028 - In Delivery Planning Tool Under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" NextStage Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.028 - In Delivery Planning Tool Under select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);
		strSecondSkuValue= objAdjunoWFLDeliveryPlanningPOM.getSecondSKUValue().getText();	
			
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.028 - In delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked *****");
			wait(3000);             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.028 - In Delivery Planning Tool Under Select chevron No Products are found *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSelectSecondRowOrderGrid()), true , "***** Test ID : 2.028 - In Delivery Planning Tool under Delivery planning chevron second row of the \"Order Grid\" is not clicked *****");
			wait(3000);
			if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSKU")>0){
				secondSKUGridvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "SKU");
				objSoftAssert.assertEquals(strSecondSkuValue.equalsIgnoreCase(secondSKUGridvalue) , true, "***** Test ID : 2.028 - In Delivery Plaiining Tool under delivery Chevron, the Order Grid SKU value is not matching with the SKU value found under SKU Grid *****");

			}else{
				bFlag = false;
				objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.028 - in Delivery Planning tool Under Delivery Planning Chevron \"No Products are Displayed \" in SKU Grid *****");
					
			}
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.028 - In Delivery Planning Tool \"Delivery planning Stage\" is not Loaded *****");
		}
		
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.028"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
			
	//Select 2nd row in Split grid
	@Test(priority=125)
	public void test_2d029_SelectSecondRowInSKUGrid(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.029 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
		if(!isNullOrBlank(strVendorBookingWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strVendorBookingWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.029 - In Delivery Planning Tool under select chevron \"Order Number\" field value is Not entered *****");
		}else{
			strMessage=strMessage+" WFLRef Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.029 - In Delivery Planning Tool under select chevron \"NextStage\" field value is Not entered *****");
		}else{
			strMessage=strMessage+" NextStage Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.029 - In Delivery Planning Tool under select chevron \"Refine Search\" button is Not clicked *****");
		wait(3000);
		strSecondLineTypeValue= objAdjunoWFLDeliveryPlanningPOM.getSecondLineType().getText();	
		strSecondConsigneeValue= objAdjunoWFLDeliveryPlanningPOM.getSecondConsigneeValue().getText();	
					
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.029 - In Delivery Planning Tool \"Delivery Planning Chevron\" is Not clicked *****");
			wait(3000);             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.029 - In Delivery Planning Tool under select chevron \"No Products\" are displayed *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 1, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field value is not Entered As \"Y\" *****" );
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field value is not Entered As \"Y\" *****" );
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSelectSecondRowSKUGrid()), true , "***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron \"Second row of SKU Grid\" is not clicked *****");
			wait(3000);
					
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){
					secondSKUGridConsigneevalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "SplitConsigneeDesc");
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strSecondConsigneeValue, secondSKUGridConsigneevalue), true, "***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron, the consignee value is present in SKU Grid is not matching with consignee value displaying in Split Grid *****");

					secondLineTypeSKUGridvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "SplitLineTypeDesc");
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strSecondLineTypeValue, secondLineTypeSKUGridvalue), true, "***** Test ID :2.029 - In Delivery Planning Tool under delivery planning chevron, the LineType value is present in SKU Grid is not matching with LineType value displaying in Split Grid *****");

				}else{
					bFlag = false;
					objSoftAssert.assertEquals(bFlag, true, " In Delivery Planning Tool Under Delivery Planning chevron in SplitGrid \"No Rows are populated\"");
							
				}
						
			}else{
				strMessage = strMessage +"In Delivery Planning Tool under Delivery Planning chevron \"Split grid\" is not populated";
			}

		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.029 - In Delivery Planning tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.029"+ strMessage +"*****");
		objSoftAssert.assertAll();
							
	}
			
	@Test(priority=130)
	public void test_2d030_InspectSKULineWithSplitDeliveryY(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.030 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Select chevron \"WFL Ref\" field value is not entered *****");
		}else{
			strMessage=strMessage+" WFLRef Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Select chevron \"NextStage\" field value is not entered *****");
		}else{
			strMessage=strMessage+" NextStage Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.030 - In Delivery Planning Tool under Select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);
				
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.030 - In Delivery Planning Tool \"Delivery Planning Chevron\" is not clicked *****");
			wait(3000);            
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.030 - In Delivery Planning Tool under Select chevron \"No Products\" are Displayed *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning chevron \"Split Delivery \" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning chevron \"Split Delivery \" arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning chevron \"Split Delivery \" field value is not emtered as \"N\" *****" );
				wait(3000);
			}
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				strSecondLineTypeValue =objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc");
				wait(3000);
				strSecondConsigneeValue	=objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "ConsigneeDesc");
				wait(3000);
				strDeliverydateValue =objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate");
					
			}else{
				strMessage=strMessage +"In Delivery Planning Tool under Delivery Planning chevron, After entered the split delivery value as \"N\", Split grid is still displaying ";
			}
			
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning chevron \"Split Delivery \" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning chevron \"Split Delivery \" arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning chevron \"Split Delivery \" field value is not entered as \"Y\" *****" );
			wait(3000);
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){
					secondSKUGridConsigneevalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "SplitConsigneeDesc");
					objSoftAssert.assertEquals(strSecondConsigneeValue.equalsIgnoreCase(secondSKUGridConsigneevalue), true, "***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning, the consignee field value which was found under SKU Grid is not matching the Split Grid consignee field value *****");
			//		System.out.println(strSecondConsigneeValue +"is matching "+strSecondConsigneeValue);

					secondLineTypeSKUGridvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "SplitLineTypeDesc");
					objSoftAssert.assertEquals(strSecondLineTypeValue.equalsIgnoreCase(secondLineTypeSKUGridvalue), true, "***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning, the LineType field value which was found under SKU Grid is not matching the Split Grid LineType field value *****");
			//		System.out.println(strSecondLineTypeValue +"is matching "+secondLineTypeSKUGridvalue);
					
					secondDeliveryDateSKUGridvalue=	objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "SplitDeliveryDate");
					objSoftAssert.assertEquals(strDeliverydateValue.contains(secondDeliveryDateSKUGridvalue), true,"***** Test ID : 2.030 - In Delivery Planning Tool under Delivery Planning, the DeliveryDate field value which was found under SKU Grid is not matching the Split Grid SplitDeliveryDate field value *****");
			//	    System.out.println(strDeliverydateValue +"is matching "+secondDeliveryDateSKUGridvalue);
							
											
				}else{
					bFlag = false;
					objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.030 -in Delivery Planning Tool under delivery Planning chevron No Rows Are Populated in SplitGrid  *****");
				}
			}else{
				strMessage = strMessage+" In Delivery Planning Tool under delivery Planning Chevron, After entered the split delivery value as \"Y\" the split grid is not populated";
			}

		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.030 - In Delivery Planning Tool \"Delivery planning\" Stage in not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.030"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
		
	@Test(priority=135)
	public void test_2d031_EnterSplitDeliveryValueY(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.031 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.031 - In Delivery Planning Tool under Select chevron \"WFLRef\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" WFLRef Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.031 - In Delivery Planning Tool under Select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" NextStage Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.031 - In Delivery Planning Tool under Select chevron \"Refine Search \" button is not clicked *****");
		wait(3000);
				
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.031 - In Delivery Planning Tool \"DeliveryPlanning\" Chevron is not clicked *****");
			wait(3000);            
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.031 - In Delivery Planning tool Under Select Chevron No Products are displayed *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field value is not entered as \"N\" *****" );
				wait(4000);
			}else{
				strMessage=strMessage+" In Delivery Planning Tool under Delivery Planning Chevron, After enter the split delivery value as\"N\" Split grid still populating ";
			}
			
			StrLineTypeFieldValue =objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc");
			wait(4000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field value is not entered as \"Y\" *****" );
			wait(4000);
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				 
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){			
					wait(3000);
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkLineTypeReadmodeSplitgrid()), true ,"***** Test ID : 2.031 - In Delivery Planning Tool Under Delivery Planning Chevron \"LineType\" field is not clicked *****");
					wait(3000);
					strLineTypeValue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "SplitLineTypeDesc");

					objSoftAssert.assertEquals(strLineTypeValue.equalsIgnoreCase(StrLineTypeFieldValue) , true , "*****  Test ID : 2.031 - In Delivery Planning Tool under Delivery chevron LineType Value which was present under SKU Grid is not Matching with LineType Value which is present under Split Grid *****");

				}else{
					objSoftAssert.assertEquals(false, true ,"***** Test ID : 2.031 - In Delivery Planning Tool under delivery planning chevron No rows are displaying *****" );
				}
						
			}else{
				strMessage = strMessage +" In Delivery Planning Tool under Delivery Planning chevron, after entered the Split Delivery value as \"Y\", the Split Grid is not Populated";
			}

		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.031 - In Delivery Planning Tool \"Delivery planning Stage\" is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.031"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
			
	//Enter Revised Ctn Qty Field Value Greatedthen PO ctns
	@Test(priority=140)
	public void test_2d042_EnterRevisedCtnQtyGreaterValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.042 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.042 - In Delivery Planning Tool under Select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
			
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.042 - In Delivery Planning Tool under Select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.042 - In Delivery Planning tool Unsed select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.042 - In Delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked sss*****");
		wait(3000);           
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.042 - In Delivery Planning Tool No Products are found under Select Chevron *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.042 - In Delivery Planning tool under Delivery Planning Chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.042 - In Delivery Planning tool under Delivery Planning Chevron \"Split Delivery\" field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.042 - In Delivery Planning tool under Delivery Planning Chevron \"Split Delivery\" field value is not entered as \"Y\" *****" );
			wait(1000);
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){
					
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSplit", 0, "RevisedQuantity", strRevisedQuantityValue), true , "***** Test ID : 2.042 - In Delivery Planning Tool under Delivery Planning chevron \"Revised Quality\" field Value is not Entered *****" );
					wait(1000);
							
					strNegativeFieldvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "OpenProgressQuantity");
					wait(3000);
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.checkNegativeNumber(strNegativeFieldvalue), true, "***** Test ID : 2.042 - In Delivery Planning Tool Under Delivery Planning Chevron \"Balance Qtns\" field does not have Negative Value *****");
				

				}else{
					strMessage=strMessage+"In Delivery Planning Tool under Delivery Planning chevron, \"No rows are populated\" in Split Grid ";
				}
						
			}else{
				strMessage=strMessage+" In Delivery Planning Tool under Delivery Planning chevron, After entered the split delivery field value as \"Y\", the split grid is not populated";
			}
			
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.042 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.042"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	//Enter Revised Ctn Qty Field Value Lessthen PO ctns
	@Test(priority=145)
	public void test_2d043_EnterRevisedCtnQtyLowerValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.043 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
					
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.043 - In Delivery Planning Tool Under Select Chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.043 - In Delivery Planning Tool Under Select Chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.043 - In Delivery Planning Tool Under Select Chevron \"Refine Search\" button is not clicked *****");
		wait(3000);		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.043 - In Delivery Planning Tool \"Delivery Planning Chevron\" is not clicked *****");
		wait(3000);	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.043 - In Delivery Planning Tool Under Select Chevron \"No Products are found.\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.043 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" Field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.043 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" Field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.043 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" Field value is not entered as \"Y\" *****" );
			wait(1000);
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSplit", 0, "RevisedQuantity", strRevisedQuantityLesserValue), true , "***** Test ID : 2.043 - In Delivery Planning Tool Under Delivery Planning Chevron \"Ship Quantity\" value is not entered *****" );
					wait(1000);
								
					differenceValue= objAdjunoWFLDeliveryPlanningPOM.differenceValue(strRevisedQuantityLesserValue, objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "TotalPOQuantity"));
					wait(1200);
							
					objSoftAssert.assertEquals(differenceValue.contains(objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "OpenProgressQuantity")),true, "***** Test ID : 2.043 - In Delivery Planning Tool under Delivery Planning Chevron \"Difference Value\" is not Correctly Displayed under \"Balance Qty\" field *****");
					wait(3000);
					System.out.println(differenceValue +" is matching "+objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "OpenProgressQuantity"));
							

				}else{
					strMessage=strMessage+"In Delivery Planning Tool under Delivery Planning Chevron \"No rows are found\" under Split Grid ";
				}
						
			}else{
				strMessage=strMessage+"In Delivery Planning Tool under Delivery Planning Chevron, After entered the split delivery value as \"Y\", the split grid is not populated";
			}
			
			
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.043 - In Delivery Planning tool \"Delivery planning Stage\" is not Loaded *****");
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.043 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
						
	@Test(priority=155)
	public void test_2d032_SetLineTypeFieldValue(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.032 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.032 - In Delivery Planning Tool under Select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.032 - In Delivery Planning Tool under Select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
						   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.032 - In Delivery Planning Tool \"DeliveryPlanning\"Chevron is not clicked *****");
						             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.032 - In Delivery Planning Tool under Select chevron \"No Products are found.\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Split Delivery\" field value is not entered as \"N\" *****" );
				wait(1000);
			}else{
				strMessage=strMessage+"In Delivery Planning tool Under Delivery Planning Chevron, After entered the split delivery value as \"N\", Split grid is still populating ";
			}
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeDirectOrderValue), true ,  "***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"LineType\" field value is not entered *****");
							
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDestinationReadMode()), true ,"***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Product Destination\" field is not clicked *****" );
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearGridInputField(strDeliveryPlanningStageFormName, "GridSKU", 0, "DestinationDesc"), true ,"***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Product Destination\" field is not cleared *****" );
								
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkConsigneeReadMode()), true ,"***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Consignee\" field is not clicked *****" );
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearGridInputField(strDeliveryPlanningStageFormName, "GridSKU", 0, "ConsigneeDesc"), true ,"***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Consignee\" field is not cleared *****" );
							
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true , "***** Test ID : 2.032 - In Delivery Planning Tool \"Complete\" Chevron is not clicked *****");				
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg())), true , "***** Test ID : 2.032 - In Delivery Planning Tool Under Delivery Planning Chevron \"Product Destination field and Consignee field \" is not mandatory *****");
					
			}
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.032 - In Delivery Planning Tool \"Delivery planning Stage\" is not Loaded *****");
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.032 - "+ strMessage +"*****");
		objSoftAssert.assertAll();
	}

	@Test(priority=160)
	public void test_2d033_SetLineTypeFieldValueStockOrder(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.033 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
			
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.033 - In Delivery Planning Tool under select Chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.033 - In Delivery Planning Tool under Select chevron \"Refine Search Button\" is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
	        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.033 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked *****");
		wait(3000);            
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.033 - In Delivery Planning Tool Under Select Chevron \"No Productsare found.\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.033 - In Delivery Planning Tool Under Delivery Planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.033 - In Delivery Planning Tool Under Delivery Planning chevron \"Split Delivery\" field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.033 - In Delivery Planning Tool Under Delivery Planning chevron \"Split Delivery\" field value is not entered as \"N\" ***** " );
				wait(1000);
			}else{
				strMessage=strMessage+" In Delivery Planning Tool Under Delivery Planning chevron, After entered the split delivery value as \"N\", Split grid is still populated";
			}
						
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeStockOrderValue), true ,  "***** Test ID : 2.033 - In Delivery Planning Tool under delivery planning chevron \"Line Type\" field Value is not Entered *****");
				wait(3000);
				
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDestinationReadMode()), true ,"***** Test ID : 2.033 - In Delivery Planning Tool under delivery planning chevron \"Destination\" Field is not Clicked *****" );
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearGridInputField(strDeliveryPlanningStageFormName, "GridSKU", 0, "DestinationDesc"), true ,"***** Test ID : 2.033 - In Delivery Planning Tool under delivery planning chevron \"Destination\" field is not Cleared *****" );
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.checkFieldIsReadOnlyInGrid(strDeliveryPlanningStageFormName, "GridSKU", 0, "ConsigneeDesc"), false,"***** Test ID : 2.033 -  In Delivery Planning Tool under delivery planning chevron \"Consignee\" field is not read only *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true , "***** Test ID : 2.033 - In Delivery Planning Tool under delivery planning chevron \"Complete\" Chevron is not Clicked *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg())), true , "*****  Test ID : 2.033 - In Delivery Planning Tool under delivery planning chevron \"Destination\" Field is Not a Mandatory Field *****");					
					
			}
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.033 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
					
	
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.033"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	//Inspect Line Consignee  dropDown
 	@Test(priority=165)
	public void test_2d034_LineConsigneeDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.034 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
					
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID :2.034 - In Delivery Planning Tool under Select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.034 - In Delivery Planning Tool under Select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
						   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.034 - In Delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked  *****");
						             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.034 - In Delivery planning tool under select chevron \"No products are dispalyed\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){	
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"N\" *****" );
				wait(4000);
			}
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkConsigneeReadMode()), true ,"***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Consignee \" dropdown field is not clicked *****");
			wait(3000);
			consigneeDropDownList= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstConsigneeDropdownvalue());
					
			strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogConsigneeList, consigneeDropDownList);
			System.out.println("consigneeDropDownList size is "+consigneeDropDownList.size());
			wait(3000);	
		
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"Y\" *****" );
			wait(4000);
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){	
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkConsigneeSplitGridDropdown()), true ,"***** Test ID : 2.034 - In Delivery Planning tool under delivery planning chevron \"Split consignee\" dropdown field is not clicked *****");
				wait(3000);
				consigneeDropDownSplitGridList= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstConsigneeDropdownvalue());
				strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogConsigneeList, consigneeDropDownList);		
					
				System.out.println("consigneeDropDownSplitGridList size is "+consigneeDropDownSplitGridList.size());
							
			}else{
				strMessage = strMessage+" In Delivery Planning Tool under Delivery Planning chevron, after entered \"split delivery\" field value as \"Y\", Split Grid is not populated";
			}
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.034 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.034"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
 	}
			
	@Test(priority=170)
	public void test_2d035_ProductDestinationDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.035 - In Delivery Palnning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID :2.035 - In Delivery Planning Tool under Select chevron \"WFL Ref\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.035 - In Delivery Planning Tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
		      
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
						   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.035 - In Delivery Planning Tool \"Delivery Planning\" chevron is not clicked *****");
		wait(3000);		             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.035 - In Delivery Planning Too Under select chevron \"No Records are found\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"split delivery\" Field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"split delivery\" Field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true ,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"split delivery\" Field value is not entered as \"N\" *****");
			wait(2000);
			
				
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeDirectOrderValue), true ,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"LineType\" Field value is not entered *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDestinationReadMode()), true ,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"Product Destination\" Field is not clicked *****");
				wait(3000);
				DestinationDropDownList= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstDestinationDropDown());
				System.out.println(catalogDestinationConsigneeList+"=====DestinationDropDownList size is ==="+DestinationDropDownList.size());
		
			//	strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogDestinationConsigneeList, DestinationDropDownList);
					
				
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"split delivery\" Field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"split delivery\" Field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true ,"***** Test ID :2.035 - In Delivery Planning Tool under Delivery Planning chevron \"split delivery\" Field value is not entered as \"Y\" *****");	
				wait(3000);
 				if ( objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()) {
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDestinationReadMode()), true ,"***** Test ID :2.035 - LineType drop down is Not clicked *****");
					wait(3000);
					DestinationDropDownSplitGridList= objAdjunoWFLDeliveryPlanningPOM.getGridDropdownvalues(objAdjunoWFLDeliveryPlanningPOM.getLstDestinationDropDown());
					System.out.println("DestinationDropDownSplitGridList size is "+DestinationDropDownSplitGridList.size());				
				//	strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogConsigneeList, consigneeDropDownList);		
					
									
 				}else{
 					strMessage = strMessage+ "In Delivery Planning Tool under Delivery Planning chevron ,after entered the \"split delivery\" field value as \"Y\", split grid is not populated ";
 				}
										
			}
					
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.035 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.035"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
						
	@Test(priority=180)
	public void test_2d036_InspectBookingRefDropDownReadOnly(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.036 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID :2.036 - In Delivery Planning Tool under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" NextStage Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.036 - In Delivery Planning tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
						   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.036 - In Delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked *****");
						             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.036 - In Delivery Planning Tool under Select chevron \"No products are populated\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.036 - In Delivery Planning Tool under Delivery planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.036 - In Delivery Planning Tool under Delivery planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.036 - In Delivery Planning Tool under Delivery planning chevron \"Split Delivery\" field value is not entered as \"N\" *****" );
				wait(2000);
			}
			wait(2000);
			if(!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.checkFieldIsReadOnlyInGrid(strDeliveryPlanningStageFormName, "GridSKU", 0, "BookingRef"), false,"***** Test ID :2.036 - In Delivery Planning Tool Under Delivery Planning chevron \"Booking Ref\" Field is Not Read only *****");
	
			}
	
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.036 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.036"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
			
	@Test(priority=185)
	public void test_2d037_CheckBookingRefDropDownisUpdated(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.037 - In Delivery Planning Tool \"Select Chevron\" is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strConformArrivalWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strConformArrivalWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.037 - In Delivery Planning Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}	
				
		if(!isNullOrBlank(strNextStageConfornArrivalValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageConfornArrivalValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.037 - In Delivery Planning Tool under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
			
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.037 - In Delivery Planning tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
						   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.037 - In Delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked *****");
			wait(3000);		             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.037 - In Delivery Planning Tool under select chevron \"No products are found\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.037 - In Delivery Planning tool under Delivery Planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.037 - In Delivery Planning tool under Delivery Planning chevron \"Split Delivery\" field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true , "***** Test ID : 2.037 - In Delivery Planning tool under Delivery Planning chevron \"Split Delivery\" field value is not entered as \"N\" *****" );
				wait(2000);
			}
			wait(3000);
			strBookingRefValue =objAdjunoWFLDeliveryPlanningPOM.randomIntegerNo();
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "BookingRef", strBookingRefValue), true ,"***** Test ID :2.037 - In Delivery Planning Tool under Delivery Planning chevron \"Booking Ref\" field value is not entered *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDeliveryTimeFieldReadMode1()), true , "***** Test ID :2.037 - In Delivery Planning Tool under Delivery Planning chevron \"Delivery Time\" field is not clicked *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkdeliveryTimearrow()), true , "***** Test ID :2.037 - In Delivery Planning Tool under Delivery Planning chevron \"Delivery Time\" field arrow is not clicked *****");
			wait(3000);
			List<WebElement> lstTime=objAdjunoWFLDeliveryPlanningPOM.getLstDeliveryTime();
			if(lstTime.size()>0){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkdeliveryTimearrow()), true , "***** Test ID :2.037 - In Delivery Planning Tool under Delivery Planning chevron \"Delivery Time\" field arrow is not clicked *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryTime", strDeliveryTimeValue), true ,"***** Test ID :2.037 - In Delivery Planning Tool under Delivery Planning chevron \"Delivery Time\" field value is not entered *****");
				wait(5000);
				strFutureDeliveryDate = objAdjunoWFLDeliveryPlanningPOM.getDate(2, "dd MMM yyyy");
				System.out.println("Future date is: "+strFutureDeliveryDate);

				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDate), true ,"***** Test ID :2.037 - In Delivery Planning Tool under Delivery Planning chevron \"Delivery Date\" field value is not entered *****");
				wait(3000);
		
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.037 - In Delivery Planning Tool \"Complete chevron\" is not clicked *****" );
				wait(3000);
				
				if(objAdjunoWFLDeliveryPlanningPOM.checkDoesElementExist(strDeliveryPlanningCompletePageFormName, "FORK_DeliveryPlanning_SearchAgain")){
					wait(3000);
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.037 - In Delivery Planning Tool under Complete chevron \"Search Again\" Button is Not Clicked *****");
					wait(3000);
				}else{
					strMessage =strMessage+" In Delivery Planning Tool under delivery planning chevron \"Booking Ref\" field cannot be updated";
				}
			}else{
				strMessage=strMessage+" In Delivery planning tool under delivery planning chevron \"Delivery Time\" dropdown doesn't have data ";
			}
			
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.037 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.037"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	@Test(priority=218)
	public void test_2d038_CheckDeliveryDateField(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.038 - In Delivery Planning Tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
			
		if(!isNullOrBlank(strVendorBookingWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strVendorBookingWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.038 - In Delivery Planning Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}	
				
		if(!isNullOrBlank(strNextStageVendorValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageVendorValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID :2.038 - In Delivery Planning Tool under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.038 - In Delivery Planning Tool under Select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);
		lstSearchResults = new ArrayList<DeliveryPlanning>();
				      
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
						   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.038 - In Delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked *****");
		wait(3000);	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.038 - In Delivery Planning Tool under select chevron \"No Products are populated \" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(3000);
			strDeliveryDatePast = objAdjunoWFLDeliveryPlanningPOM.getDate(-2, "dd MMM yyyy");
			System.out.println("Past date is: "+strDeliveryDatePast);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strDeliveryDatePast), true ,"***** Test ID :2.038 - In Delivery Planning Tool under delivery planning chevron \"Delivery Date\" Field Value is Not Entered *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.038 - In Delivery planning tool \"Complete\" Chevron is Not Clicked *****" );
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg())), true , "*****  Test ID : 2.038 - In Delivery Planning tool under delivery planning chevron \"Delivery Date\" field Accept Pastdate Value *****");
			wait(5000);
								
			String strPresentDeliveryDate1 = objAdjunoWFLDeliveryPlanningPOM.getDate(0, "dd MMM yyyy");
			System.out.println("present date is: "+strPresentDeliveryDate1);

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strPresentDeliveryDate1), true ,"***** Test ID :2.038 - In Delivery planning tool under delivery planning chevron \"Delivery Date\" Field Value is Not Entered *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.038 - In Delivery planning tool \"Complete\" Chevron is Not Clicked *****" );
			wait(3000);
			if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.038 - In Delivery planning tool under complete chevron \"Search Again\" Button is Not Clicked *****");
				wait(3000);
					
				if(!isNullOrBlank(strNextStageVendorValue)){
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageVendorValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID :2.038 - In Delievry planning tool under search chevron \"NextStage\" Field value is not entered *****");
				}else{
					strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
				}
					
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.038 - In Delivery planning tool \"Select\" Chevron is Not Clicked *****");
				lstSearchResults = new ArrayList<DeliveryPlanning>();
				      
				if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
				{
				lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
									   
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.038 -In delivery planning tool \"DeliveryPlanning\" chevron is not clicked *****");
				wait(3000);	             
				}else{
					bSearchResultsProductsFound = false;
					objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.038 - In Delivery planning tool under select chevron \"No Products are found\" *****");
				}
			}else{
					strMessage=strMessage+"In Delivery Planning Tool under delivery planning chevron \"Delivery Date\" field is not accept \"Present date\" ";
			}
				
			strFutureDeliveryDate = objAdjunoWFLDeliveryPlanningPOM.getDate(2, "dd MMM yyyy");
			System.out.println("future date is: "+strFutureDeliveryDate);

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDate), true ,"***** Test ID :2.038 - In delivery planning tool under delivery planning chevron \"Delivery Date\" Field Value is Not Entered *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.038 - In Delivery planning tool \"Complete\" Chevron is Not Clicked *****" );
			wait(3000);
			if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.038 - In Delivery planning tool under complete chevron \"Search Again\" button is Not Clicked *****");
				wait(3000);
			}else{
				strMessage=strMessage+"In Delivery planning tool under delivery planning chevron \"Delivery Date\" field is not accept \"Future date\" ";
			}
					
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.038 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.038"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	@Test(priority=195)
	public void test_2d039_CheckDeliverydateFieldAcceptBeforeETA(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		wait(3000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.039 - In delivery planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
				
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.039 - In Delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
					
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.039 - In Delivery planning tool under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.039 - In Delivery planning tool under select chevron \"Refine Search\" Button is not clicked *****");
					
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
				   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.039 - In Delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
			wait(3000);	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.039 - In Delivery planning tool under select chevron \"No products are found\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
		
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.039 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.039 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true ,"***** Test ID : 2.039 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"N\" *****");	
			wait(2000);
			 
			if (!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()) {
				strDeliveryDatePast = objAdjunoWFLDeliveryPlanningPOM.getDate(-2, "dd MMM yyyy");
				System.out.println("Past date is :"+strDeliveryDatePast);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strDeliveryDatePast), true ,"***** Test ID : 2.039 - In Delivery Planning Tool under delivery planning chevron \"Delivery Date \" field value is not entered *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.039 - In Delivery Planning tool \"Complete\" Chevron is Not Clicked *****" );
				wait(3000);
						
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg())), true , "*****  Test ID : 2.039 - In Delivery Planning tool under delivery planning chevron \"Delivery Date\" Accept Pastdate Value *****");
				wait(3000);
				
				System.out.println("before eta value :" +strDeliveryDateETAValue);	
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strDeliveryDateETAValue), true ,"***** Test ID : 2.039 - In Delivery Planning Tool under delivery planning chevron \"Delivery Date \" field value is not entered *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.039 - In Delivery Planning tool \"Complete\" Chevron is Not Clicked *****" );
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg())), true , "*****  Test ID : 2.039 - In Delivery Planning tool under delivery planning chevron \"Delivery Date\" Accept before \"Arrival Date\"*****");
					
			}else{
				objSoftAssert.assertEquals(false, true, "***** Test ID :2.039 - In Delivery Planning Tool Under Delivery Planning chevron after entered \"Split delivery\" field value as \"N\",still \"SplitGrid\" is Displaying *****");
			}
							
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.039 - In Delivery Planning tool\"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.039"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}	
			
	@Test(priority=200)
	public void test_2d040_InspectDeliveryTimeDropDownReadOnly(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.040 - In Delivery Planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
					
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID :2.040 - In Delivery Planning Tool under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.040 - In Delivery Planning Tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
		lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
						   
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.040 - In Delivery Planning tool \"Delivery Planning\" chevron is not clicked *****");
							             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.040 - In Delivery Planning Tool under select chevron \"No Products are populated\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if (objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()) {
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.040 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.040 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true ,"***** Test ID : 2.040 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"N\" *****");	
				wait(2000);
			}			
			if (!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()) {
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.checkFieldIsReadOnlyInGrid(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryTime"), false,"***** Test ID :2.040 - In Delivery Planning Tool under Delivery planning chevron \"delivery time\" field is not read only *****");
				
			}else{
				strMessage = strMessage +" In Delivery planning tool under delivery planning chevron after entered the split delivery value as \"N\", split grid is still displayed ";
			}
			
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.040 - In Delivery Planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.040"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
	@Test(priority=205)
	public void test_2d041_CheckDeliveryTimeDropDownisUpdated(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.041 - In Delivery Planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
		
		if(!isNullOrBlank(strConformArrivalWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strConformArrivalWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.041 - In Delivery Planning Tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strNextStageConfornArrivalValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageConfornArrivalValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.041 - In Delivery Planning Tool under select chevron \"Nextstage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.041 - In Delivery Planning Tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
				       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
						   
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.041 - In Delivery Planning Tool \"DeliveryPlanning\" chevron is not clicked *****");
						             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.041 - In Delivery Planning Tool under select chevron \"No productsare populated\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			if (objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()) {
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.041 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.041 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
				wait(2000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryNValue), true ,"***** Test ID : 2.041 - In Delivery Planning Tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"N\" *****");	
				wait(2000);
			}	
			
			if (!objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()) {
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "BookingRef", strBookingRefValue), true ,"***** Test ID :2.041 - In Delivery Planning tool under delivery planning chevron \"Booking Ref\" field value is not entered *****");
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDeliveryTimeFieldReadMode()), true , "***** Test ID :2.041 - In Delivery Planning tool under delivery planning chevron \"DeliveryTime\" Field is not Clicked *****");
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearGridInputField(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryTime"), true , "***** Test ID :2.041 - In Delivery Planning tool under delivery planning chevron \"Delivery Time\" Field is not Cleared *****");
						
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.041 - In Delivery Planning Tool \"Complete\" Chevron is Not Clicked *****" );
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strWFLGridValidationMessageValue, objAdjunoWFLDeliveryPlanningPOM.getValidationMessageOtherthenField(objAdjunoWFLDeliveryPlanningPOM.getStrGridValidationMsg())), true , "*****  Test ID : 2.041 -  In Delivery Planning tool under delivery planning chevron \"Delivery Time\" field is not Mandatory *****");
				wait(3000);
				
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDeliveryTimeFieldReadMode1()), true , "***** Test ID :2.041 -  In Delivery Planning tool under delivery planning chevron \"Delivery Time\" Field is not Clicked *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkdeliveryTimearrow()), true , "***** Test ID :2.041 -  In Delivery Planning tool under delivery planning chevron \"Delivery Time\" Field Arrow is not Clicked *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryTime", strDeliveryTimeValue), true ,"***** Test ID :2.041 -  In Delivery Planning tool under delivery planning chevron \"Delivery Time\" field Cannot be updated *****");
				wait(5000);
				
				strFutureDeliveryDate = objAdjunoWFLDeliveryPlanningPOM.getDate(2, "dd MMM yyyy");
				System.out.println("present date is: "+strPresentDeliveryDate);

				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDate), true ,"***** Test ID :2.038 -  In Delivery Planning tool under delivery planning chevron \"Delivery Date\" Field Value is Not Entered *****");
				wait(3000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.041 - In Delivery planning Tool \"Complete\" Chevron is Not Clicked *****" );
							
				wait(3000);
			//	if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				if(objAdjunoWFLDeliveryPlanningPOM.checkDoesElementExist(strDeliveryPlanningCompletePageFormName, "FORK_DeliveryPlanning_SearchAgain")){
					wait(3000);
					objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.041 -  In Delivery Planning tool under delivery planning chevron \"Search Again\" Button is Not Clicked *****");
					wait(5000);
				}else{
					strMessage= strMessage+" In Delivery Planning tool under delivery planning chevron \"Delivery Time\" field value is not updated ";
				}
			}else{
				strMessage = strMessage +" In Delivery planning tool under delivery planning chevron after entered the split delivery value as \"N\", \"split grid\" still populating ";
			}
		
			
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.041 - In Delivery planning Tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.041"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
		
	@Test(priority=206)
	public void test_2d044_AddSplitgridrow(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.044 - In Delivery planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
						
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.044 - In Delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
							
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.044 - In Delivery planning tool under select chevron \"Next stage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.044 - In Delivery planning tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);	
		lstSearchResults = new ArrayList<DeliveryPlanning>();
					        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					   
		//	objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 2.044 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.044 - In Delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
			wait(3000);	             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.044 - In Delivery planning tool under select chevron \"No product are populated\" *****");
		}
					
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.044 - In Delivery planning tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.044 - In Delivery planning tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.044 - In Delivery planning tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"Y\" *****" );
			wait(2000);
			
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				strBalanceCtnsFieldvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "OpenProgressQuantity");
				System.out.println(strBalanceCtnsFieldvalue);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkAddRow()), true ,"***** Test ID : 2.044 - In delivery planning tool under delivery planning chevron \"Add Row\" Button is not Clicked *****");
				wait(3000);						
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){
					
					wait(3000);
					strBalanceCtnsSplitGridFieldvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "RevisedQuantity");
					if(strBalanceCtnsFieldvalue.equals(strBalanceCtnsSplitGridFieldvalue)){
					System.out.println(strBalanceCtnsFieldvalue +"is  Matching "+strBalanceCtnsSplitGridFieldvalue);
					}
				}else{
					bFlag = false;
					objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.044 - In delivery planning tool under delivery planning chevron \"No Rows are Populated\" in Split Grid *****");
				}
			}else{
				bFlag = false;
				objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.044 - In Delivery Planning Tool under delivery planning chevron after entered the split delivery value as \"Y\", \"Split Grid\" is not displaying *****");
			}		
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.044 - in delivery planning tool \"Delivery planning\" Stage is not Loaded *****");
		}
					
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.044"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
	@Test(priority=207)
	public void test_2d045_DeleteSplitgridrow(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID : 2.045 - In delivery planning tool \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
						
		if(!isNullOrBlank(strSplitDeliveryWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strSplitDeliveryWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.045 - In delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
						
		if(!isNullOrBlank(strNextStageCargoReceiptValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageCargoReceiptValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.045 - In delivery planning tool under select chevron \"NextStage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID : 2.045 - In delivery planning tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
				        
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
						   
		//	objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID : 2.045 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID : 2.045 - In delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
			wait(3000);		             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID : 2.045 - In delivery planning tool under select chevron \"No Products are displayed \" *****");
		}
				
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryreadOnly()), true,"***** Test ID : 2.045 - In delivery planning tool under delivery planning chevron \"Split Delivery\" field is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getSplitDeliveryArrow()), true,"***** Test ID : 2.045 - In delivery planning tool under delivery planning chevron \"Split Delivery\" field arrow is not clicked *****");
			wait(2000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "SplitDelivery", strSplitDeliveryYValue), true , "***** Test ID : 2.045 - In delivery planning tool under delivery planning chevron \"Split Delivery\" field value is not entered as \"Y\" *****" );
			wait(2000);
			
			if(objAdjunoWFLDeliveryPlanningPOM.getIsDisplayed().isDisplayed()){
				strBalanceCtnsFieldvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSKU", 0, "OpenProgressQuantity");
				System.out.println(strBalanceCtnsFieldvalue);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkAddRow()), true ,"***** Test ID : 2.045 - In delivery planning tool under delivery planning chevron \"Add row\" button is not clicked *****");
				wait(3000);
						
				if(objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsinGrid(strDeliveryPlanningStageFormName, "GridSplit")>0){
					wait(3000);
					strBalanceCtnsSplitGridFieldvalue = objAdjunoWFLDeliveryPlanningPOM.getGridCellValue(strDeliveryPlanningStageFormName, "GridSplit", 0, "RevisedQuantity");
					if(strBalanceCtnsFieldvalue.equals(strBalanceCtnsSplitGridFieldvalue)){
					System.out.println(strBalanceCtnsFieldvalue +"is  Matching "+strBalanceCtnsSplitGridFieldvalue);
					}
				}else{
					bFlag = false;
					objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.045 - In delivery planning tool under delivery planning chevron \"No Rows are Populated\" in Split Grid *****");
				}
			}
			
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkDeleteRow()), true,"***** Test ID : 2.045 - In delivery planning tool under delivery planning chevron \"Delete row\" button is not clicked *****");
			wait(5000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkYesButton()), true,"***** Test ID : 2.045 -  In delivery planning tool under delivery planning chevron \"Yes\" button is not clicked *****");

			
			
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID : 2.045 - In delivery planning tool \"Delivery planning\" Stage is not Loaded *****");
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.045"+ strMessage +"*****");
		objSoftAssert.assertAll();
		
	}
	
	@Test(priority=210)
	public void test_2d046_CheckSearchAgainButtonPresent(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.046 - In delivery planning chevron \"Select\" Chevron is Not Clicked *****");
		wait(3000);
		clearFields();
					
		if(!isNullOrBlank(strCompleteStageWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.046 - In delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strNextStageShipmentBookingValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageShipmentBookingValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.046 - In delivery planning tool under select chevron \"Next Stage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strCompleteStageSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageSKUValue, strDeliveryPlanningFormName,"PARAM_SKU"), true,"***** Test ID : 2.046 - In delivery planning tool under select chevron \"Product SKU\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"ProductSKU\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.046 - In delivery planning tool under select chevron \"Refine Search\" Button is not clicked *****");
		wait(3000);		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
				       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
							   
		//	objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID :2.046 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.046 - In delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
							             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.046 -  In delivery planning tool under select chevron \"No products are populated\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(5000);	
			/*strBookingRefValue = objAdjunoWFLDeliveryPlanningPOM.randomIntegerNo();
			System.out.println(strBookingRefValue);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "BookingRef", strBookingRefValue), true ,"***** Test ID :2.046 - In delivery planning tool under delivery planning chevron \"Booking Ref\" Field value is not entered *****");
			wait(5000);	
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryTime", strDeliveryTimeValue), true ,"***** Test ID :2.046 - In delivery planning tool under delivery planning chevron \"Delivery Time\" Field value is not entered *****");
			wait(5000);	*/
			
			strFutureDeliveryDate = objAdjunoWFLDeliveryPlanningPOM.getDate(2, "dd MMM yyyy");
			System.out.println("future date is: "+strFutureDeliveryDate);

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDate), true ,"***** Test ID :2.046 - In delivery planning tool under delivery planning chevron \"delivery Date\" Field value is not entered *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.046 - In delivery planning tool \"Complete\" Chevron is not Clicked *****" );
				
			wait(5000);
			if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.046 - In delivery planning tool under delivery planning chevron \"Search Again\" button is not Clicked *****");
				wait(5000);	
			}else{
				strMessage=strMessage+"In delivery planning tool under complete chevron \"Search Again\" button is not present";
			}
					
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.046 - In delivery planning tool \"Delivery planning\" Stage in not Loaded *****");
		}
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.046"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
		
	@Test(priority=212,dependsOnMethods="test_2d046_CheckSearchAgainButtonPresent")
	public void test_2d047_CheckSavedDetails(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		wait(6000);
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.047 - In delivery planning tool \"Select\" Chevron is not Clicked *****");
		wait(6000);
		clearFields();
					
		if(!isNullOrBlank(strCompleteStageWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.047 - In delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strNextStageShipmentBookingValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageShipmentBookingValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.047 - In delivery planning tool under select chevron \"Next Stage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strCompleteStageSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageSKUValue, strDeliveryPlanningFormName,"PARAM_SKU"), true,"***** Test ID : 2.047 - In delivery planning tool under select chevron \"Product SKU\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"ProductSKU\" Value is Empty in XML";
		}
						
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.047 - In delivery planning tool under select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
				       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts1(1);
							   
	//		objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID :2.047 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.047 - In delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
							             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.047 - In delivery planning tool under select chevron \"No products are populated\" *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			wait(5000);	
			/*strBookingRefValue = objAdjunoWFLDeliveryPlanningPOM.randomIntegerNo();
			System.out.println(strBookingRefValue);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "BookingRef", strBookingRefValue), true ,"***** Test ID :2.047 - In delivery planning tool under delivery planning chevron \"Booking Ref\" field value is not entered *****");
			wait(5000);	
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryTime", strDeliveryTimeValue), true ,"***** Test ID :2.047 - In delivery planning tool under delivery planning chevron \"Delivery Time\" field value is not entered *****");
			wait(5000);	*/
			strFutureDeliveryDate = objAdjunoWFLDeliveryPlanningPOM.getDate(3, "dd MMM yyyy");
			System.out.println("future date is: "+strFutureDeliveryDate);

			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDate), true ,"***** Test ID :2.047 - In delivery planning tool under delivery planning chevron \"Delivery Date\" field value is not entered *****");
			wait(3000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.047 - In delivery planning tool \"Complete\" Chevron is not Clicked *****" );
				
			wait(5000);
			if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.047 - In delivery planning tool under delivery planning chevron \"Search Again\" button is not Clicked *****");
				wait(5000);	
			}else{
				strMessage=strMessage+" In delivery planning tool under complete chevron \"Search Again\" button is not present";
			}
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.047 - In delivery planning tool \"Delivery planning\" Stage in not loaded *****");
		}
			
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.047"+ strMessage +"*****");
		objSoftAssert.assertAll();
				
	}
		
			
	@Test(priority=215)
	public void test_2d048_InspectCompletionMessage(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.048 - In delivery planning tool \"Select\" Chevron is not clicked *****");
		wait(3000);
		clearFields();
					
		if(!isNullOrBlank(strCompleteStageWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.048 - In delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strNextStageShipmentBookingValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageShipmentBookingValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.048 - In delivery planning tool under select chevron \"Next Stage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strCompleteStageSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageSKUValue, strDeliveryPlanningFormName,"PARAM_SKU"), true,"***** Test ID : 2.048 - In delivery planning tool under select chevron \"Product SKU\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"ProductSKU\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.048 - In delivery planning tool inder select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
			
			
			
		//	objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID :2.048 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.048 - In delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
					             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.048 - In delivery planning tool under delivery planning chevron \"No products are populated\' *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeDirectOrderValue), true ,"***** Test ID :2.048 - In delivery planning tool under delivery planning chevron \"LineType\" field Value is not entered *****");
			wait(1000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "ConsigneeDesc", strCompleteConsigneeValue), true ,"***** Test ID :2.048 - In delivery planning tool under delivery planning chevron \"consignee\" field Value is not entered *****");
			wait(1000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DestinationDesc", strCompleteDestinationValue), true ,"***** Test ID :2.048 - In delivery planning tool under delivery planning chevron \"product Destination\" field Value is not entered *****");
			wait(1000);
			strFutureDeliveryDateComplete =objAdjunoWFLDeliveryPlanningPOM.getDate(3, "dd MMM yyyy");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDateComplete), true ,"***** Test ID :2.048 - In delivery planning tool under delivery planning chevron \"Delivery date\" field Value is not entered *****");
				
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.048 - In delivery planning tool \"Complete\" Chevron is not clicked *****" );
			wait(5000);
			if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				System.out.println(objAdjunoWFLDeliveryPlanningPOM.getElementValue(strDeliveryPlanningCompletePageFormName, "UpdateMessage"));
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strCompletionMessageValue, objAdjunoWFLDeliveryPlanningPOM.getElementValue(strDeliveryPlanningCompletePageFormName, "UpdateMessage")), true, "***** Test ID : 2.048 - In delivery planning tool under complete chevron \"validation message is not verified\"  *****");
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.048 - In delivery planning tool under complete chevron \"Search Again\" button is not clicked *****");
				wait(3000);		
			}else{
				strMessage=strMessage+" In delivery planning tool under complete chevron \"Search Again\" button is not present";
				
			}
						
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.048 - In delivery planning tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.048 "+ strMessage +"*****");
		objSoftAssert.assertAll();

	}
	
	@Test(priority = 216)
	public void test_2d049_CheckDeliveryTrack(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		boolean bFlag;
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvSelect()),true, "***** Test ID :2.049 - In delivery planning tool \"Select\" Chevron is not clicked *****");
		wait(3000);
		clearFields();
					
		if(!isNullOrBlank(strCompleteStageWFLRefValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageWFLRefValue, strDeliveryPlanningFormName,"PARAM_OrderNumber"), true,"***** Test ID : 2.049 - In delivery planning tool under select chevron \"WFL Reference\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"WFL Reference\" Value is Empty in XML";
		}
		
		if(!isNullOrBlank(strNextStageShipmentBookingValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strNextStageShipmentBookingValue, strDeliveryPlanningFormName,"PARAM_NextStage"), true,"***** Test ID : 2.049 - In delivery planning tool under select chevron \"Next Stage\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"NextStage\" Value is Empty in XML";
		}
				
		if(!isNullOrBlank(strCompleteStageSKUValue)){
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValue(strCompleteStageSKUValue, strDeliveryPlanningFormName,"PARAM_SKU"), true,"***** Test ID : 2.049 - In delivery planning tool under select chevron \"Product SKU\" Field value is not entered *****");
		}else{
			strMessage=strMessage+" \"ProductSKU\" Value is Empty in XML";
		}
					
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getClkRefineSearch()), true , "***** Test ID :2.049 - In delivery planning tool inder select chevron \"Refine Search\" button is not clicked *****");
		wait(3000);		
		lstSearchResults = new ArrayList<DeliveryPlanning>();
			       
		if ( objAdjunoWFLDeliveryPlanningPOM.getNoOfRowsResulted() > 0)
		{
			lstSearchResults =  objAdjunoWFLDeliveryPlanningPOM.selectMulitpleProducts(1);
					
		//	objSoftAssert.assertEquals(bSearchResultsProductsFound, true,"***** Test ID :2.049 - No Rows are Selected*****");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvDeliveryPlanning()),true, "***** Test ID :2.049 - In delivery planning tool \"Delivery Planning\" chevron is not clicked *****");
					             
		}else{
			bSearchResultsProductsFound = false;
			objSoftAssert.assertEquals(bSearchResultsProductsFound, true, "***** Test ID :2.049 - In delivery planning tool under delivery planning chevron \"No products are populated\' *****");
		}
		if (objAdjunoWFLDeliveryPlanningPOM.verifyPageIsLoaded(strDeliveryPlanningStageFormName)) 
		{
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "LineTypeDesc", strLineTypeStockOrderValue), true ,"***** Test ID :2.049 - In delivery planning tool under delivery planning chevron \"LineType\" field Value is not entered *****");
			wait(1000);
	//		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "ConsigneeDesc", strCompleteConsigneeValue), true ,"***** Test ID :2.049 - In delivery planning tool under delivery planning chevron \"consignee\" field Value is not entered *****");
			wait(1000);
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DestinationDesc", strCompleteDestinationValue), true ,"***** Test ID :2.049 - In delivery planning tool under delivery planning chevron \"product Destination\" field Value is not entered *****");
			wait(1000);
			strFutureDeliveryDateComplete =objAdjunoWFLDeliveryPlanningPOM.getDate(5, "dd MMM yyyy");
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.setFieldValueForGridCell(strDeliveryPlanningStageFormName, "GridSKU", 0, "DeliveryDate", strFutureDeliveryDateComplete), true ,"***** Test ID :2.049 - In delivery planning tool under delivery planning chevron \"Delivery date\" field Value is not entered *****");
				
			objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickChevron(objAdjunoWFLDeliveryPlanningPOM.getChvcomplete()), true ,"***** Test ID :2.049 - In delivery planning tool \"Complete\" Chevron is not clicked *****" );
			wait(5000);
			if(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain().isDisplayed()){
				System.out.println(objAdjunoWFLDeliveryPlanningPOM.getElementValue(strDeliveryPlanningCompletePageFormName, "UpdateMessage"));
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.compareTwoStringValuesToSame(strCompletionMessageValue, objAdjunoWFLDeliveryPlanningPOM.getElementValue(strDeliveryPlanningCompletePageFormName, "UpdateMessage")), true, "***** Test ID : 2.049 - In delivery planning tool under complete chevron \"validation message is not verified\"  *****");
				wait(5000);
				objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getChkSearchAgain()), true, "***** Test ID :2.049 - In delivery planning tool under complete chevron \"Search Again\" button is not clicked *****");
				wait(3000);		
			}else{
				strMessage=strMessage+" In delivery planning tool under complete chevron \"Search Again\" button is not present";
				
			}
						
		}else{
			bFlag = false;
			objSoftAssert.assertEquals(bFlag, true, "***** Test ID :2.049 - In delivery planning tool \"Delivery planning\" Stage is not Loaded *****");
		}
				
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :2.049 "+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
		
		
			
	public void LoginCustomer(){
		SoftAssert objSoftAssert = new SoftAssert();
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clickButtonUsingWebElement(objAdjunoWFLDeliveryPlanningPOM.getLnkLogout()), true , "***** In Delivery Planning Tool \"Logout\" button is not Clicked *****");
		wait(3000);
		objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
		objAdjunoLIMALoginPOM.setUserName(strCustomerUserName);
		objAdjunoLIMALoginPOM.setPassword(strCustomerPassword);
		objAdjunoLIMALoginPOM.clickLogon();
				
	}
			
	@Test(priority=220)
	public void test_2d001_VerifyCustSupplierDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		LoginCustomer();
		String strTitle= objAdjunoWFLDeliveryPlanningPOM.callMouseHover(strDeliveryPlanningFormName, objAdjunoWFLDeliveryPlanningPOM.getLnkTools(), objAdjunoWFLDeliveryPlanningPOM.getLnkDeliveryPlanning());
		boolean bFlag = true;
				
		if (isNullOrBlank(strTitle))
			bFlag = false;
				
		if (!(isNullOrBlank(strTitle)))
		{
			if (strTitle.equalsIgnoreCase(strDeliveryPlanningPageTitle))
				bFlag = true;
			else	
				bFlag = false;
		}       		   
		objSoftAssert.assertEquals(bFlag, true, "Page Title of the Delivery Planning Tool is wrong");
		
		custSupplierList=objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_Supplier");
		System.out.println("custSupplierList size is "+custSupplierList.size());
		if(custSupplierList.size()>0){
		}else{
			strMessage=strMessage+" In Delivery Planning Tool under Search chevron \"Supplier\" Drop Down doesn't have any Value";
		}
			
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogCustSupplierList, custSupplierList);
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.001"+ strMessage +"*****");
		objSoftAssert.assertAll();
			
	}
			
	@Test(priority=225)
	public void test_2d002_VerifyCustConsigneeDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		custConsigneeList=objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_Consignee");
		System.out.println("custConsigneeList size is "+custConsigneeList.size());
		if(custConsigneeList.size()>0){
		}else{
			strMessage=strMessage+"In Delivery Planning Tool under Search chevron \"Consignee\" Drop Down doesn't have any Value";
		}
				
		strMessage=strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogConsigneeList, custConsigneeList);
					
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.002"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
			
	@Test(priority=230)
	public void test_2d009_VerifyCustCustomerDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		wait(5000);
		custCustomerList = objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "PARAM_Customer");
		System.out.println("customerList : "+customerList.size());       
		if(customerList.size()>0){
						
		}else{
			strMessage = strMessage +" In Delivery Planning Tool under Search chevron \"customer\" Drop drown doesn't have value";
		}
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.009"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
			
			
	public void clearFields(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "Param_DestinationPort"), true,"***** - In Delivery Planning Tool \"Destination Port\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_Supplier"), true,"***** - In Delivery Planning Tool \"Supplier\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_Consignee"), true,"***** - In Delivery Planning Tool \"Consignee\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_WorkflowStatus"), true,"***** - In Delivery Planning Tool \"Workflow Status\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_NextStage"), true,"***** - In Delivery Planning Tool \"NextStage\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_OrderNumber"), true,"***** - In Delivery Planning Tool \"WFL Reference\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_Product"), true,"***** - In Delivery Planning Tool \"Product\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_SKU"), true,"***** - In Delivery Planning Tool \"Product SKU\" Field element not cleared *****");
		objSoftAssert.assertEquals(objAdjunoWFLDeliveryPlanningPOM.clearInputField(strDeliveryPlanningFormName, "PARAM_Customer"), true,"***** - In Delivery Planning Tool \"Customer\" Field element not cleared *****");
		
		objSoftAssert.assertAll();
	}
				
	@Test(priority=235)
	public void test_2d011_VerifyCustDestinationPortDropDown(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoWFLDeliveryPlanningPOM=new AdjunoWFLDeliveryPlanningPOM(driver);
		String strMessage ="";
		custDestinationPortList =objAdjunoWFLDeliveryPlanningPOM.getDropdownValues(strDeliveryPlanningFormName, "Param_DestinationPort");
		System.out.println("custDestinationPortList size is "+custDestinationPortList.size());
			
		if(custDestinationPortList.size()>0){
		}else{
		strMessage=strMessage+" In Delivery Planning Tool Under Search chevron \"DestinationPort\" Drop Down doesn't have any Value";
		}
						
		strMessage =strMessage+objAdjunoWFLDeliveryPlanningPOM.verifyCatalogData(catalogLoCodeList, custDestinationPortList);
						
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 2.011"+ strMessage +"*****");
		objSoftAssert.assertAll();
					
	}
			
	@AfterTest 
	public void terminateBrowser() 
	{ 
		driver.close(); 
	}

	}
				
			



		






