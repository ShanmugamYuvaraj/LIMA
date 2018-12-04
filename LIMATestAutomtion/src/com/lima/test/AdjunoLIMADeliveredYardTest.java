package com.lima.test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMADeliveredYardPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMADeliveredYardTest {
	WebDriver driver;
	long nPage_Timeout = 0;
    String strTestURL;
    
    String strUserName;
    String strPassword;
    
    String strDeleiveredYardFormName;
    String strPageTitleDeleiveredYard;
    String strUpdatechevFormName;
    

    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMADeliveredYardPOM objAdjunoLIMADeliveredYardPOM;
    
	String strCatalogPageTitle;
	String strCatalogFormName;
	NodeList status;
	ArrayList<String> catalogVesselList;
	ArrayList<String> catalogDestinationList;
	ArrayList<String> catalogOffDockList;
	String strInvalidContainer;
	String strStatusAwaiting;
	String strCatalogLocode;
	String strCatalogVessel;
	String strDestinationPort;
	String strStatusDepated;
	String strStatusArrived;
	String strReason;
	String strComments;
	String strVesselValue;
	boolean searchResult;
	ArrayList<String> containerList;
	String strValidContainer;
	int position;
	NodeList bookingUploadedCaptions;
	String strNextDay;
	String strETAFrom;
	String strETATo;
	String strArrivalDate;
	String strDestination;
	String strContainerValue;
	String strDeleiveredYardchevFormName;
	String strNextDay2;
	String strTrackN0;
	String strDeliveredYardLinkFormName;
	String strDestination2;
	private String strForwarderRef;
    
	public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}

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
              
            Node testLIMAURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
	        
  	        Node pageTitleDeleiveredYard = (Node) xPath.evaluate("/config/LIMA/Page_Title_Deleivered_Yard", dDoc, XPathConstants.NODE);
  	        strPageTitleDeleiveredYard = pageTitleDeleiveredYard.getTextContent();
  	        

	        Node catalogPageTitle = (Node) xPath.evaluate("/config/LIMA/Catalog_Page_Title", dDoc, XPathConstants.NODE);
	        strCatalogPageTitle = catalogPageTitle.getTextContent();
  	        
  	        Node formNameDeleiveredYard = (Node) xPath.evaluate("/config/LIMA/Search_Form_Name_Deleivered_Yard", dDoc, XPathConstants.NODE);
  	        strDeleiveredYardFormName = formNameDeleiveredYard.getTextContent();
          
  	        Node updatechevFormName = (Node) xPath.evaluate("/config/LIMA/DeliveredYard_Page_Form_Name", dDoc, XPathConstants.NODE);
	        strUpdatechevFormName = updatechevFormName.getTextContent();
	        
	        Node formNameDeleiveredYardchev = (Node) xPath.evaluate("/config/LIMA/DeliveryYardFormName", dDoc, XPathConstants.NODE);
	        strDeleiveredYardchevFormName = formNameDeleiveredYardchev.getTextContent();
         
	        Node catalogFormName = (Node) xPath.evaluate("/config/LIMA/Form_Name_Catalog", dDoc, XPathConstants.NODE);
	        strCatalogFormName = catalogFormName.getTextContent();
	        
	        Node deliveredYardLinkFormName = (Node) xPath.evaluate("/config/LIMA/DeliveredYardLinkFormName", dDoc, XPathConstants.NODE);
	        strDeliveredYardLinkFormName = deliveredYardLinkFormName.getTextContent();
	        
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
            DocumentBuilder builder1 = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder1.parse(objAdjunoLIMALibrary.getStrLIMADeliveredYard());

            XPath xPath1 = XPathFactory.newInstance().newXPath();
            
            Node invalidContainer = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Invalid_Container", dDoc1, XPathConstants.NODE);
            strInvalidContainer = invalidContainer.getTextContent();
            
            Node statusAwiting = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strStatusAwaiting = statusAwiting.getTextContent();
            
            Node statusDeparted = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Status_Departed", dDoc1, XPathConstants.NODE);
            strStatusDepated = statusDeparted.getTextContent();
            
            Node statusArrived = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Status_Arrived", dDoc1, XPathConstants.NODE);
            strStatusArrived = statusArrived.getTextContent();
                     
            Node locode = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Catalog_Locode", dDoc1, XPathConstants.NODE);
            strCatalogLocode = locode.getTextContent();
            
            Node vessel = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Catalog_Vessel", dDoc1, XPathConstants.NODE);
            strCatalogVessel = vessel.getTextContent();
            
            Node destiPort = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Destination_port", dDoc1, XPathConstants.NODE);
            strDestinationPort = destiPort.getTextContent();
            
            Node destination = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Destination", dDoc1, XPathConstants.NODE);
            strDestination = destination.getTextContent();
            
            Node destination2 = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Destination2", dDoc1, XPathConstants.NODE);
            strDestination2 = destination2.getTextContent();
            
            Node forwarderRef = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/ForwarderRef", dDoc1, XPathConstants.NODE);
            strForwarderRef = forwarderRef.getTextContent();
            
            Node vesselValue = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Vessel_value", dDoc1, XPathConstants.NODE);
            strVesselValue = vesselValue.getTextContent();
            
            Node reason = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Reason", dDoc1, XPathConstants.NODE);
            strReason = reason.getTextContent();
            
            Node comments = (Node) xPath1.evaluate("/DeliveredYard/Search_Param/Comments", dDoc1, XPathConstants.NODE);
            strComments = comments.getTextContent();
           
            bookingUploadedCaptions = (NodeList) xPath1.evaluate("/DeliveredYard/Grid_Caption", dDoc1, XPathConstants.NODESET);
            status = (NodeList) xPath1.evaluate("/DeliveredYard/Status", dDoc1, XPathConstants.NODESET);
	       
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    

    
    @Test(priority=1)
    public void test_accessCatalog()
    {
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	String strTitle = objAdjunoLIMADeliveredYardPOM.callMouseHover(strCatalogFormName,objAdjunoLIMADeliveredYardPOM.getLnkTools(), objAdjunoLIMADeliveredYardPOM.getLnkCatalog());    
         boolean bFlag = true;
    
         if (isNullOrBlank(strTitle))
             bFlag = false;
         
         if (!(isNullOrBlank(strTitle)))
         {
             if (strTitle.equalsIgnoreCase(strCatalogPageTitle))
                 bFlag = true;
             else
                 bFlag = false;
         }          
        
         objSoftAssert.assertEquals(bFlag, true, "***** Title of the Catelog page is wrong *****");
         objSoftAssert.assertAll();
    }
    
    
   /* @Test(priority=2)
    public void test_CatalogData()
    {
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	String strMessage = "";
    	catalogDestinationList = new ArrayList<String>();
    	catalogVesselList = new ArrayList<String>();
    	catalogOffDockList = new ArrayList<String>();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getLnkLightHouse()), true,"***** Lighthouse Link is not clicked in Catalog page *****");
    	wait(3000);
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strCatalogLocode)){
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strCatalogLocode, strCatalogFormName, "Param_Type"),true,"***** Type Field value is not entered as LOCODE*****");
		 
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getCatalogbtnApply()),true,"***** Apply Button is not Clicked *****");
			 wait(5000);
			 
			 int nCount = objAdjunoLIMADeliveredYardPOM.valCount();
			 catalogDestinationList = objAdjunoLIMADeliveredYardPOM.getCatalogTableData(nCount);
			 System.out.println("catalogDestinationList size"+catalogDestinationList.size());
			 wait(5000);
    	
    	}else{
			 strMessage=strMessage+"Locode Value is Empty in Xml";
		}
    	
    	clearCatalogFields();
    	
    	if(!isNullOrBlank(strCatalogVessel)){
    		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strCatalogVessel, strCatalogFormName,"Param_Type"), true,"***** Type Field value is not entered as Vessel*****");
			
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getCatalogbtnApply()),true,"*****  Apply button is not clicked *****");
			 wait(5000);
			             
			 int nCount2 = objAdjunoLIMADeliveredYardPOM.valCount();
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnFirst()),true,"*****  Apply button is not clicked *****");
			 wait(3000);
			 catalogVesselList = objAdjunoLIMADeliveredYardPOM.getCatalogTableData1(nCount2);  
			 System.out.println("vessel size"+catalogVesselList.size());
    	}else{
			 strMessage=strMessage+"vessel Value is Empty in Xml";
		}
    	
    	objSoftAssert.assertAll();
    }
   */
    
   @Test (priority=3)
    public void test_1_accessDeliveredYardLink(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
  
    	String strTitle = objAdjunoLIMADeliveredYardPOM.callMouseHover(strDeleiveredYardFormName,objAdjunoLIMADeliveredYardPOM.getLnkTools(), objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard());
    	
    	strTitle = driver.getTitle();
    	
    	boolean bFlag = true;
    	
    	if (isNullOrBlank(strTitle))
    		bFlag = false;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitleDeleiveredYard))
    		{
    			
    			bFlag = true;
    		}
    		else
    			bFlag = false;
    	}	       
    	
    	objSoftAssert.assertEquals(bFlag, true, "*****  Test ID : 1 - Title of the Delivered Yard page is Wrong *****");
    	objSoftAssert.assertAll();
    	
    }
    
    @Test(priority=4)
    public void test_2_checkChevorons(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesChevronExist(objAdjunoLIMADeliveredYardPOM.getChvSearch()), true,"***** search Chevorn in the Delivered Yard page is not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesChevronExist(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"***** select Chevorn in the Delivered Yard page is not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesChevronExist(objAdjunoLIMADeliveredYardPOM.getChvDeliveredYard()), true,"***** Delivered Yard Chevorn in the Delivered Yard page is not found ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesChevronExist(objAdjunoLIMADeliveredYardPOM.getChvComplete()), true,"***** complete Chevorn in the Delivered Yard page is not found ***** ");
        
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=5)
    public void test_3_CheckForExistanceOfFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
        objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
     	        
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_DestinationPort"), true," *****  Test ID : 3 - DestinationPort field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_ETAFrom"), true," *****  Test ID : 3 - ETAFrom field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_ETATo"), true," *****  Test ID : 3 - ETATo field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_WFStatus"), true," *****  Test ID : 3 - WFStatus field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_Status"), true," *****  Test ID : 3 - Status field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_Vessel"), true," *****  Test ID : 3 - Vessel field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_Container"), true," *****  Test ID : 3 - Container field not found in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName,"PARAM_ForwarderReference"), true," *****  Test ID : 3 - PARAM_ForwarderReference field not found in Search page of Delivered Yard tool ***** ");
        
        objSoftAssert.assertAll();
    }
   
   
    public void clearFields()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
        
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_DestinationPort"), true," *****  Test ID : 3 - DestinationPort field not cleared in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_ETAFrom"), true,"*****  Test ID : 3 - ETAFrom field not cleared in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_ETATo"), true,"*****  Test ID : 3 - ETATo field not cleared in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_WFStatus"),true,"*****  Test ID : 3 - WFStatus field not cleared in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_Status"), true,"*****  Test ID : 3 - Status field not cleared in Search page of Delivered Yard tool ***** ");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_Vessel"),true," *****  Test ID : 3 -can't clear Vessel field in Search page of Delivered Yard tool *****");
        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_Container"), true,"*****  Test ID : 3 - Container field not cleared in Search page of Delivered Yard tool *****");     
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strDeleiveredYardFormName,"PARAM_ForwarderReference"),true," *****  Test ID : 3 - ForwarderReference  field not cleared in Search page of Delivered Yard tool *****");
	     
	    objSoftAssert.assertAll();
    }
   
    public void clearCatalogFields(){
    	SoftAssert objSoftAssert = new  SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strCatalogFormName, "Param_Name"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strCatalogFormName, "Param_Type"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strCatalogFormName, "Param_Description"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strCatalogFormName, "Param_AssociatedItem"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clearInputField(strCatalogFormName, "Param_AssociatedType"),true,"***** Could not clear  field value in Lighthouse Page *****");
    	objSoftAssert.assertAll();
    }	

    
    @Test(priority=9)
    public void test_5_VerifyStatusDropDownValues(){
    
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> statusList = new ArrayList<String>();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		statusList = objAdjunoLIMADeliveredYardPOM.getDropDownList(strDeleiveredYardFormName, "PARAM_Status");
		
		if(statusList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveredYardPOM.verifyStatusDropDown(status, statusList,3);
				
		}else{
			strMessage=strMessage+"status Drop Down Won't have any Value";
		}
		
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 5"+ strMessage +"*****");
		objSoftAssert.assertAll();
    }
    
   /* @Test(priority=10)
    public void test_4_VerifyDestinationDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> destinationList = new ArrayList<String>();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		destinationList = objAdjunoLIMADeliveredYardPOM.getDropDownList(strDeleiveredYardFormName, "PARAM_DestinationPort");
		
		if(destinationList.size()>0 && catalogDestinationList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveredYardPOM.verifyCatalogData(catalogDestinationList, destinationList);
		}else{
			strMessage=strMessage+"Destination Drop Down Won't have any Value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 4"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
    @Test(priority=12)
    public void test_6_VerifyVesselDropDownValues()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		ArrayList<String> vesselList = new ArrayList<String>();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		vesselList = objAdjunoLIMADeliveredYardPOM.getDropDownList(strDeleiveredYardFormName, "PARAM_Vessel");
		
		if(vesselList.size()>0 && catalogVesselList.size()>0){
			strMessage= strMessage + objAdjunoLIMADeliveredYardPOM.verifyCatalogData(catalogVesselList, vesselList);
		}else{
			strMessage=strMessage+"Vessel Drop Down Won't have any Value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 6"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    */
    
   @Test(priority=15)
    public void getValidContainerList()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
        String strMessage = "";
    	clearFields();
    	containerList =  new ArrayList<String>();	
    	if(!isNullOrBlank(strStatusAwaiting)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusAwaiting, strDeleiveredYardFormName, "PARAM_Status"),true,"***** *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
		}
    	
    	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID : 7 - can't click on Select chev in Search page of Delivered Yard tool *****");
 
    	wait(3000);
    	
    	searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
    	if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMADeliveredYardPOM.getLstContainer().size()-1; i++) {
				
				containerList.add(objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText());
			}
				
			    
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 7-"+ strMessage +"*****");
		
        objSoftAssert.assertAll();
        
    }
    
    
    @Test(priority=18)
    public void test_13_VerifyInvalidContainer()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		clearFields();
		wait(3000);
		
		if(!isNullOrBlank(strInvalidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strInvalidContainer, strDeleiveredYardFormName, "PARAM_Container"),true,"***** Test id:13- Could not set invallid container value  *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Invalid container Value is Empty in Xml";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"***** Test id:13 - Select Chevron is not clicked in Delivered Yard tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
		if(searchResult==true){
			
		} else {
			strMessage = strMessage + "No items were found. text is not shown for invalid container value";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 13-"+ strMessage +"*****");
		objSoftAssert.assertAll();
	}
    
    
        
    
    @Test(priority=22)
    public void test_8_verifyDestinationPort()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strDestinationPort))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strDestinationPort,strDeleiveredYardFormName,"PARAM_DestinationPort"),true," *****  Test ID : 8 - Destination Port is not set Value in Select page of Delivered Yard tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 8 - Destination Port is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID : 8 - can't click on Select chev in Search page of Delivered Yard tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Delivered Yard tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMADeliveredYardPOM.getDestionationPortM();
	    strMessage = objAdjunoLIMADeliveredYardPOM.verifyDestinationPort(strDestinationPort,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:8- " + strMessage +" ***** ");
	    objSoftAssert.assertAll();
        
    }
    
    @Test(priority=23) 
    public void test_9_verifyEtaFrom() throws ParseException
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	
    	String strMessage ="";
    	
    	strETAFrom = objAdjunoLIMADeliveredYardPOM.getDate(-60,"dd MMM yyyy");
	    Date etaFromDate = new SimpleDateFormat("dd MMM yyyy").parse(strETAFrom);
	       
        clearFields();
		
	    if(!isNullOrBlank(strETAFrom))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strETAFrom,strDeleiveredYardFormName,"PARAM_ETAFrom"),true," *****  Test ID : 9 -  ETA from date in Select page of Delivered Yard tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 9 - ETA From Value is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID : 9 - can't click on Select chev in Search page of Delivered Yard tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Delivered Yard tool ***** ");
	    wait(5000);
	     
	    List<Date> list = new ArrayList<Date>();  
	    list = objAdjunoLIMADeliveredYardPOM.getArrivedDate();
	    strMessage = objAdjunoLIMADeliveredYardPOM.verifyArrivedDate(etaFromDate,list);
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:9- " + strMessage +" ***** ");
	
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=24) 
    public void test_10_verifyEtaTo() throws ParseException
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	
    	String strMessage ="";
    	
    	strETATo= objAdjunoLIMADeliveredYardPOM.getDate(0,"dd MMM yyyy");
	    Date etaToDate = new SimpleDateFormat("dd MMM yyyy").parse(strETATo);
	       
        clearFields();
		
    	//Setting the field value Invalid Container:setFieldValue
	    if(!isNullOrBlank(strETATo))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strETATo,strDeleiveredYardFormName,"PARAM_ETATo"),true," *****  Test ID : 10 -  ETA From date in Select page of Delivered Yard tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 10 - ETA From Value is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID :10 - can't click on Select chev in Search page of Delivered Yard tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Delivered Yard tool ***** ");
	    wait(5000);
	     
	    List<Date> list = new ArrayList<Date>();  
	    list = objAdjunoLIMADeliveredYardPOM.getArrivedDate();
	    strMessage = objAdjunoLIMADeliveredYardPOM.verifyArrivedDate2(etaToDate,list);
	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:10- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
    
    
    
    
    
    @Test(priority=25)
    public void test_11_verifyStatus()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strStatusAwaiting))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusAwaiting,strDeleiveredYardFormName,"PARAM_Status"),true," *****  Test ID : 11 - Status is not set Value in Select page of Delivered Yard tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 11 - Status is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID : 11 - can't click on Select chev in Search page of Delivered Yard tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Delivered Yard tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMADeliveredYardPOM.getStatusAwait();
	    strMessage = objAdjunoLIMADeliveredYardPOM.verifyStatusAwaiting(strStatusAwaiting,list);
    	
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:11- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=28)
    public void test_12_verifyVessel()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

	    if(!isNullOrBlank(strVesselValue))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strVesselValue,strDeleiveredYardFormName,"PARAM_Vessel"),true," *****  Test ID : 12 - Vessel Value is not set in Select page of Delivered Yard tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 12 - vessel is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID : 12 - can't click on Select chev in Search page of Delivered Yard tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Delivered Yard tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMADeliveredYardPOM.getVesselValues();
	    strMessage = objAdjunoLIMADeliveredYardPOM.verifyVesselvalue(strVesselValue,list);
    	
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:12- " + strMessage +" ***** ");
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=30)
    public void test_16_verifyForwardRef()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	
    	String strMessage ="";
	       
        clearFields();

    	//Setting the field value Destination Port:setFieldValue
	    if(!isNullOrBlank(strForwarderRef))
	    {
	    	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strForwarderRef,strDeleiveredYardFormName,"PARAM_ForwarderReference"),true," *****  Test ID : 16 - ForwarderReference Value is not set in Select page of Delivered Yard tool ***** ");    	   
	    }else{
	    	  strMessage = strMessage + " *****  Test ID : 16 - ForwarderRef is Empty ***** ";
	    }
	   
	    objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickChevorn(objAdjunoLIMADeliveredYardPOM.getChvSelect()),true,"***** Test ID : 16 - can't click on Select chev in Search page of Delivered Yard tool *****");
	    //objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"),true," ***** Test ID : 8 - Not able to click Refine Search button in Select page of Delivered Yard tool ***** ");
	    wait(5000);
	     
	    List<String> list = new ArrayList<String>();  
	    list = objAdjunoLIMADeliveredYardPOM.getForwardRefValues();
	    strMessage = objAdjunoLIMADeliveredYardPOM.verifyForwardRefvalue(strForwarderRef,list);
	    
	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,"***** Test ID:16- " + strMessage +" ***** ");
    	
    	objSoftAssert.assertAll();
    }
    
    
    
    @Test(priority=32)
    public void test_14_VerifyValidContainerSearch()
    {
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage ="";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
		
		clearFields();
		wait(3000);
		if(!isNullOrBlank(strStatusAwaiting)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusAwaiting, strDeleiveredYardFormName, "PARAM_Status"),true,"***** Test id:14- Could not set status value *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
		}
	
		
		for (int i = 0; i <=containerList.size()-1; i++) {
			if(!containerList.get(i).isEmpty()){
				strValidContainer = containerList.get(i);
				position = i;
				break;
			}
		}
		
		if(!isNullOrBlank(strValidContainer)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strValidContainer, strDeleiveredYardFormName, "PARAM_Container"),true,"*****Test id:14- Could not set vallid container value *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty in List";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"*****Test id:14- Select Chevron is not clicked in Delivered Yard  tool *****");
		wait(4000);
		
		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
	
		if(searchResult==false){
		
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		strMessage = strMessage + objAdjunoLIMADeliveredYardPOM.verifyContainerAndStaus(strValidContainer,strStatusAwaiting);
		
	//  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getTxtNoResult().getText(), "No items were found.","Results are not found for valid container*****");
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:14- "+ strMessage +"*****");
		objSoftAssert.assertAll();		   
    }

        
   
   
  @Test(priority=33)
    public void test_7_verifyFieldsInSelectChevron(){
	   SoftAssert objSoftAssert = new SoftAssert();
		
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
		
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName, "FORK_DeliveredYard_RefineSearch"), true,"***** TestId:7- Refine Search button is not shown in Select chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName, "UploadDeliveredYardTemplate"), true,"***** TestId:7- Upload button is not shown in Select chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardFormName, "OpenReport"), true,"***** TestId:7- Download Delivered Yard Template Link is not shown in Select chevron in Delivered Yard  tool *****");
		
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThVessel()), true,"***** Test ID:7- Vessel Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThDestinationPOrt()), true,"***** Test ID:7- Destination Port Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThArrivedDate()), true,"***** Test ID:7- Arrival Date Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThContainer()), true,"***** Test ID:7- Container Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThYardArrivedDate()), true,"***** Test ID:7-  Yard Arrived Date in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThDestination()), true,"***** Test ID:7- Destination Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThForwarderRef()), true,"***** Test ID:7- Forwarder Ref Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getThStatus()), true,"***** Test ID:7- Status Colunm in not shown in Select Chevron in Delivered Yard  tool *****");
		
	   objSoftAssert.assertAll();	
		
   }

    
    
  @Test(priority=35)
  public void test_17_downloadYardtemplate() throws IOException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
	   
	   File downloadedFile = new File("C:\\Users\\Anand\\Downloads\\DeliveredYardTemplate.xlsx");
	   if(downloadedFile.exists()){
		   downloadedFile.delete();
		   System.out.println("deleted");
	   }
		
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "OpenReport"), true,"***** Test ID:17- Download Delivered yard Template Link is not cliked in select chevron in Delivered yard Tool *****");
	   wait(5000);
		
	   Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\DownloadDeliveredYardTemplate.exe");
	   wait(6000);
		
	   boolean exists = downloadedFile.exists();
	   wait(6000);
	   objSoftAssert.assertEquals(exists, true,"***** Test ID:17- Delivered Yard Template File is not downloaded *****");
		
	   objSoftAssert.assertAll();
  }
    
  @Test(priority=40)
  public void test_19_verifyUpdateChevron(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage = ""; 
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
	   
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardFormName, "UploadDeliveredYardTemplate"),true,"*****Test ID:19- Upload Button is not clicked in \"Select Chevorn\" in Delivered Yard tool");
	   wait(4000);
	   if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strUpdatechevFormName)){
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "YardArrivalDate1"), true,"***** Test ID:19- Yard Arrived Date field is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "YardDepartureDate1"), true,"***** Test ID:19- yard Departed Date field is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "Destination1"), true,"***** Test ID:19- Destination  field is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "ReasonCode1"), true,"***** Test ID:19- Reason field is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "Comments1"), true,"***** Test ID:19- Comments field is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "DeliveredYardUpload"), true,"***** Test ID:19- Browse button is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "BUTTON_Update"), true,"***** Test ID:19- Update Button is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strUpdatechevFormName, "UploadDeliveredYardTemplate"), true,"***** Test ID:19- Upload Button is not found in \"Update Chevorn\" in Delivered Yard tool *****");
		   
	   }else{
		 strMessage = strMessage +" Update Chevron Page is not loaded";  
	   } 
	      
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 19"+ strMessage +"*****");
	   
	   objSoftAssert.assertAll();
	   
  }  
  
  @Test(priority=45)
  public void test_18_uploadYardTemplate() throws IOException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   String strMessage ="";
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
	   
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strUpdatechevFormName, "DeliveredYardUpload"), true,"***** Test ID:18- Browse button is not clicked in \"Update Chevorn\" in Delivered yard Tool *****");
	   wait(3000);
	   long nRows = objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strUpdatechevFormName,"Grid_Containers");
	   
	   Runtime.getRuntime().exec("C:\\LIMATestAutomationProject\\AutoIt\\UploadDeliveredYard.exe");
	   wait(5000);
	   
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnUpload()), true,"***** Test ID:18- upload button is not clicked in \"Update Chevorn\" in Delivered yard Tool *****");
	   wait(4000);
	  
	   long nRows2 = objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strUpdatechevFormName,"Grid_Containers");
	   
	   if(nRows2>nRows){
		   
	   }else{
		   strMessage = strMessage + " Upload Delivered Yard funtion in working";
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 18-"+ strMessage +"*****");
	   
	   
	   objSoftAssert.assertAll();
  }
  
  @Test(priority=50)
  public void test_verifyField(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
	   String strMessage="";
	   	
	   ArrayList<String> list = new ArrayList<String>();
	    	
	   list = objAdjunoLIMADeliveredYardPOM.getCaptionsList(strUpdatechevFormName,"Grid_Containers");
	   //int count = (int) objAdjunoLIMADeliveredYardPOM.getNoOfColumnsGrid(strUpdatechevFormName,"Grid_Containers");
	   strMessage = objAdjunoLIMADeliveredYardPOM.verifyCaptionsONGrid(list,bookingUploadedCaptions,7);
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** "+ strMessage +"*****");
	   objSoftAssert.assertAll();
  }
  
  
  @Test(priority=55,dependsOnMethods="test_18_uploadYardTemplate")
  public void test_20_VerifyMandatory(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
	   String strMessage="";
	  
	   ArrayList<String> listError = objAdjunoLIMADeliveredYardPOM.getListOfValidationMessageGridElement(strUpdatechevFormName,"Grid_Containers",0,"YardArrivalDate");
	   
	   for (int i = 0; i <= listError.size() - 1; i++) {
		  
		   if (listError.get(i).equalsIgnoreCase("> &#39;Yard Arrival Date&#39; is a required field")) {
			
		   } else {
			   if (i == listError.size() - 1) {
					strMessage = strMessage	+ " yard Arrived Date Mandatory message is not displayed ";
				}
		   }
	   }
		
	  String strErrorOffDockLocation = objAdjunoLIMADeliveredYardPOM.getValidationMessageGridElement(strUpdatechevFormName,"Grid_Containers",0,"Destination");
	   if(!strErrorOffDockLocation.equals("")){
	   		boolean bFlag = strErrorOffDockLocation.equalsIgnoreCase("> &#39;Destination&#39; is a required field");
	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:20 - In Delivered Yard Tool Under Update Chevron \"Destination \" Field is not Mandatory *****");	   			
	   }else{
	   		strMessage = strMessage +"In Delivered Yard Tool Under Update Chevron \" Destination\" Field Mandatory message is not displayed";
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 20"+ strMessage +"*****");
	   objSoftAssert.assertAll();
  }
  
  @Test(priority=60)
  public void test_21_VerifyGridValue() throws ParseException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
	   String strMessage="";
	   long nRows2 = objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strUpdatechevFormName,"Grid_Containers");
	  
  	   
	  	//   strArrivalDate = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName, "Grid_Containers", 0, "ArrivedDate");
	  	   
	  strNextDay = objAdjunoLIMADeliveredYardPOM.getDate(-1, "dd MMM yyyy");
	   
	   if(!isNullOrBlank(strNextDay)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay, strUpdatechevFormName, "YardArrivalDate1"),true,"***** *****");
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay, strUpdatechevFormName, "YardDepartureDate1"),true,"***** *****");
	   }else{
	   		strMessage=strMessage+"yard Arried Date/yard Departure Date Value is Empty";
	   }
	   
	   if(!isNullOrBlank(strDestination)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strDestination, strUpdatechevFormName, "Destination1"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Destination Value is Empty";
	   }
	   
	   if(!isNullOrBlank(strReason)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strReason, strUpdatechevFormName, "ReasonCode1"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Reason Value is Empty  in XML";
	   }
	   
	   if(!isNullOrBlank(strComments)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strComments, strUpdatechevFormName, "Comments1"),true,"***** *****");	
	   }else{
	   		strMessage=strMessage+"Comments Value is Empty in XML";
	   }
	   
	   wait(4000);
	   
	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getSelectBox()), true,"***** Test ID:21- Select CheckBox is not clicked in the Update Chevron *****");
	  wait(2000);
	  
	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strUpdatechevFormName, "BUTTON_Update"),true,"***** Test ID:21- Update buttoin not clicked in Update Chevron *****");
	  wait(3000);
	  
	  //verify entered data
	  for (int i = 0; i <=nRows2-1; i++) {
		objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strUpdatechevFormName,"Grid_Containers",i,"YardArrivalDate")), true,"***** Test ID:21-  yard Arrival Date is not matching With Entered value *****");
	  
	  	objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strUpdatechevFormName,"Grid_Containers",i,"YardDepartureDate")), true,"***** Test ID:21-  Yard Departure Date is not matching With Entered value *****");
	  
	  	objSoftAssert.assertEquals(strDestination.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strUpdatechevFormName,"Grid_Containers",i,"Destination")), true,"***** Test ID:21-  Destination is not matching With Entered value *****");
	  
	  	objSoftAssert.assertEquals(strReason.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strUpdatechevFormName,"Grid_Containers",i,"ReasonCode")), true,"***** Test ID:21- Reason is not matching With Entered value *****");
	  
	  	objSoftAssert.assertEquals(strComments.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strUpdatechevFormName,"Grid_Containers",i,"Comments")), true,"***** Test ID:21- Comments is not matching With Entered value *****");
	  }
	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 21"+ strMessage +"*****");
	  objSoftAssert.assertAll();
  }
  
  
  
  @Test(priority=65)
  public void test_22_deleteRow(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
	   String strMessage="";
	   
	   long nRow1 = objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strUpdatechevFormName, "Grid_Containers");
	   
	   if(nRow1>0){
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnDelete()), true,"***** Test ID;26- Delete Rows Button is not cliked in Update Chevron *****");
		   wait(5000);
		   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnYes()), true,"***** Test ID;26- Yes Button is not cliked in Update Chevron *****");
		   wait(2000);
		   
	   }else{
		   strMessage = strMessage + " No rows are uploaded to deleted";
	   }
	   
	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 22"+ strMessage +"*****");
	   objSoftAssert.assertAll();
	  
  }

  
  @Test(priority=70,dependsOnMethods="test_22_deleteRow")
  public void test_upload() throws IOException, ParseException{
	   SoftAssert objSoftAssert = new SoftAssert();
	   test_18_uploadYardTemplate();
	   wait(4000);
	   test_21_VerifyGridValue();
	   wait(4000);
	   objSoftAssert.assertAll();
  }
  
  
  @Test(priority=75)
  public void test_23_complete(){
	   SoftAssert objSoftAssert = new SoftAssert();
	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvComplete2()), true,"***** Test ID:23- Complete Chevron is not clicked  *****");

	 //  String poDetails = objAdjunoLIMADeliveredYardPOM.getTrackMessage().getText();
	 //  String[] vals = poDetails.split("Off dock location with reference");
	   
	 //  System.out.println("complete message:"+poDetails);
	   wait(5000);
	   objSoftAssert.assertAll();
  }
  
    
    @Test (priority=78)
    public void test_25_accessDeliveredYardLink(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	wait(2000);
    	String strTitle = objAdjunoLIMADeliveredYardPOM.callMouseHover(strDeleiveredYardFormName,objAdjunoLIMADeliveredYardPOM.getLnkTools(), objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard());
    	strTitle = driver.getTitle();
    	
    	boolean bFlag = true;
    	
    	if (isNullOrBlank(strTitle))
    		bFlag = false;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitleDeleiveredYard))
    		{
    			
    			bFlag = true;
    		}
    		else
    			bFlag = false;
    	}	       
    	
    	objSoftAssert.assertEquals(bFlag, true, "*****  Test ID : 25 - Title of the Delivered Yard page is Wrong *****");
    	objSoftAssert.assertAll();
    	
    }
    
    
    @Test(priority=80)
    public void test_26_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		//containerList.remove(position);
 
 		String strMessage ="";
 		
 	
 		wait(4000);
 		clearFields();
 		
 		if(!isNullOrBlank(strStatusAwaiting)){
 			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusAwaiting, strDeleiveredYardFormName, "PARAM_Status"),true,"*****Test ID:26- Awaiting status value is not set in Search chevron*****");
 	 	}else{
 	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
 		}
 		
 		
 		
 		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"*****Test ID:26-  Select Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		 
 		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
		if(searchResult==false){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvDevlieredYard1()), true,"*****Test ID:26-  Delivered yard Chevron is not clicked in Delivered Yard Tool *****");
	 		wait(4000);
	 		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getErrMessage().isDisplayed(), true,"***** Test ID:26-Validation message is not displayed *****");
	 			
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:26 "+ strMessage +" *****");
		objSoftAssert.assertAll();
    	
    }
   
    @Test(priority=85)
    public void test_27_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		//containerList.remove(position);
 		String strMessage ="";
 	
 		wait(4000);
 		clearFields();
 		
 		if(!isNullOrBlank(strStatusAwaiting)){
 			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusAwaiting, strDeleiveredYardFormName, "PARAM_Status"),true,"*****Test ID:27- Awaiting status value is not set in Search chevron*****");
 	 	}else{
 	   		strMessage=strMessage+"Awaiting status Value is Empty in Xml";
 		}
 		
 		
 		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"*****Test ID:27-  Select Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		 
 		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMADeliveredYardPOM.getLstContainer().size()-1; i++) {
				
				if(!objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText().isEmpty()){
					objAdjunoLIMADeliveredYardPOM.getLstSelect().get(i).click();
					strContainerValue = objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText();
					break;
				}
			}
				
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvDevlieredYard1()), true,"***** Test ID:27- Delivered Yard Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		
 		 if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strDeleiveredYardchevFormName)){
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "YardArrivalDate1"), true,"***** Test ID:27- yard Arrived Date field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "YardDepartureDate1"), true,"***** Test ID:27-   Yard Departed Date field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "Destination1"), true,"***** Test ID:27- Destination field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "ReasonCode1"), true,"***** Test ID:27- Reason field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "Comments1"), true,"***** Test ID:27- Comments field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "BUTTON_Update"), true,"***** Test ID:27- Update Button is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   
 			   
 		   }else{
 			 strMessage = strMessage +" Delivered yard Chevron Page is not loaded";  
 		   }
 		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:27 "+ strMessage +" *****");
		objSoftAssert.assertAll();
    	
    }
    
    @Test(priority=90)
    public void test_28_VerifyMandatory(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
 	   String strMessage="";
 	   //OffDockLocation
 	   ArrayList<String> listError = objAdjunoLIMADeliveredYardPOM.getListOfValidationMessageGridElement(strDeleiveredYardchevFormName,"Grid_Containers",0,"YardArrivalDate");
 	   
 	   for (int i = 0; i <= listError.size() - 1; i++) {
 		  
 		   if (listError.get(i).equalsIgnoreCase("> &#39;Yard Arrival Date&#39; is a required field")) {
 			
 		   } else {
 			   if (i == listError.size() - 1) {
 					strMessage = strMessage	+ " Yard Arrived Date Mandatory message is not displayed ";
 				}
 		   }
 	   }
 		
 	  String strErrorOffDockLocation = objAdjunoLIMADeliveredYardPOM.getValidationMessageGridElement(strDeleiveredYardchevFormName,"Grid_Containers",0,"Destination");
 	   if(!strErrorOffDockLocation.equals("")){
 	   		boolean bFlag = strErrorOffDockLocation.equalsIgnoreCase("> &#39;Destination&#39; is a required field");
 	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:28 - In Delivered Yard Tool Under Delivered yard Chevron \"Destination \" Field is not Mandatory *****");	   			
 	   }else{
 	   		strMessage = strMessage +"In Delivered Yard Tool Under Delivered yard Chevron \" Destination\" Field Mandatory message is not displayed";
 	   }
 	   
 	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 28"+ strMessage +"*****");
 	   objSoftAssert.assertAll();
   }

    @Test(priority=95)
    public void test_29_VerifyGridValue() throws ParseException{
  	   SoftAssert objSoftAssert = new SoftAssert();
  	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
  	   String strMessage="";
  	   
  	   int nRows = (int) objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strDeleiveredYardchevFormName, "Grid_Containers");
  	   
  	//   strArrivalDate = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName, "Grid_Containers", 0, "ArrivedDate");
  	   
  	   strNextDay = objAdjunoLIMADeliveredYardPOM.getDate(-1, "dd MMM yyyy");
  	   
  	   strNextDay2 = objAdjunoLIMADeliveredYardPOM.getDate(0, "dd MMM yyyy");
  	   
  	   if(!isNullOrBlank(strNextDay)){
  			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay, strDeleiveredYardchevFormName, "YardArrivalDate1"),true,"***** *****");
  			
  	   }else{
  	   		strMessage=strMessage+" yard Arried Date Value is Empty";
  	   }
  	   
  	  if(!isNullOrBlank(strNextDay2)){
 			
 			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay2, strDeleiveredYardchevFormName, "YardDepartureDate1"),true,"***** *****");
 	   }else{
 	   		strMessage=strMessage+" Yard Departure Date Value is Empty";
 	   }
  	   
  	   if(!isNullOrBlank(strDestination)){
  			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strDestination, strDeleiveredYardchevFormName, "Destination1"),true,"***** *****");	
  	   }else{
  	   		strMessage=strMessage+"Destination Value is Empty";
  	   }
  	   
  	   if(!isNullOrBlank(strReason)){
  			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strReason, strDeleiveredYardchevFormName, "ReasonCode1"),true,"***** *****");	
  	   }else{
  	   		strMessage=strMessage+"Reason Value is Empty  in XML";
  	   }
  	   
  	   if(!isNullOrBlank(strComments)){
  			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strComments, strDeleiveredYardchevFormName, "Comments1"),true,"***** *****");	
  	   }else{
  	   		strMessage=strMessage+"Comments Value is Empty in XML";
  	   }
  	   
  	   wait(4000);
  	  
  	  for (int i = 0; i <= nRows-1; i++) {
  		  	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getGridCellElement(strDeleiveredYardchevFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:29- Select CheckBox is not clicked in the Delivered yard Chevron *****");
  		  	
  	  }
  	  wait(2000);
  	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardchevFormName, "BUTTON_Update"),true,"***** Test ID:29- Update buttoin not clicked in  Chevron *****");
  	  wait(3000);
  	  
  	  //verify entered data
  	  for (int i = 0; i <= nRows-1; i++) {
 	
  		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"YardArrivalDate")), true,"***** Test ID:29-  Yard Arrival Date is not matching With Entered value *****");
  	 	  
  	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"YardDepartureDate")), true,"***** Test ID:29-  Yard Departure Date is not matching With Entered value *****");
  	 	  
  	 	  objSoftAssert.assertEquals(strDestination.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"Destination")), true,"***** Test ID:29-  Destination is not matching With Entered value *****");
  	 	  
  	 	  objSoftAssert.assertEquals(strReason.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"ReasonCode")), true,"***** Test ID:29- Reason is not matching With Entered value *****");
  	 	  
  	 	  objSoftAssert.assertEquals(strComments.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"Comments")), true,"***** Test ID:29- Comments is not matching With Entered value *****");
  	 	 
  	  }
  	  
  	   
  	  //strTrackN0 = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"TrackReference");
  	  wait(4000);
  	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 29"+ strMessage +"*****");
  	  objSoftAssert.assertAll();
     }
    
    
    @Test(priority=100)
    public void test_30_complete(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvComplete2()), true,"***** Test ID:34- Complete Chevron is not clicked  *****");
 	   wait(2000);
 	   String poDetails = objAdjunoLIMADeliveredYardPOM.getTrackMessage().getText();
 	   String[] a= poDetails.split("achieved on track '", 2);
 	   a = a[1].split("'");
 	   strTrackN0 = a[0];
 	   System.out.println(strTrackN0);
 	   wait(5000);
 	   objSoftAssert.assertAll();
    }
    
    
    @Test(priority=105)
    public void test_32_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   
 	  objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
 	    	
 	  objAdjunoLIMADeliveredYardPOM.callMouseHover(strCatalogFormName,objAdjunoLIMADeliveredYardPOM.getLnkTrack(),objAdjunoLIMADeliveredYardPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:32- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=110)
	public void test_33_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getFldRefNo()),true," ***** Test ID:33-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValueForWebElement(objAdjunoLIMADeliveredYardPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:33-Reference field value is not set");   
			    	  
			 objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:33-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=115)
    public void test_34_clickYardLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard2()), true,"***** Test ID:34- Delivered Yard link is not clicked *****");
		wait(5000);
		
		if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strDeliveredYardLinkFormName)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdContainer()), true,"***** Test ID : 34 - In Delivered Yard page \"Container\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdSeal()), true,"***** Test ID : 34 - In Delivered Yard page \"Seal\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdArrivalDate()), true,"***** Test ID :34 - In Delivered Yard page \"Arrived Date\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdDepartureDate()), true,"***** Test ID : 34 - In Delivered Yard page \"Departure Date\" is not found *****");
	        

			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdDestination()), true,"***** Test ID : 34 - In Delivered Yard page \"OffDockLocation\"  is not found *****");
	       
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdReasonCode()), true,"***** Test ID :34 - In Delivered Yard page \"ReasonCode\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdComments()), true,"***** Test ID : 34 - In Delivered Yard page \"Comments\" is not found *****");
			
	        
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColContainer().getText().equalsIgnoreCase(strContainerValue), true," Test ID : 34 - In Delivered Yard page Container value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColArrivedDate().getText().contains(strNextDay), true," Test ID : 34 - In Delivered Yard page Arrived Date value is not matching *****");
	      
	        
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColDepartedDate().getText().contains(strNextDay2), true," Test ID : 34 - In Delivered Yard page Departure Date value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColDestination().getText().equalsIgnoreCase(strDestination), true," Test ID : 34 - In Delivered Yard page Delivered Yard  value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColReasonCode().getText().equalsIgnoreCase(strReason), true," Test ID : 34 - In Delivered Yard page Reason value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColComments().getText().equalsIgnoreCase(strComments), true," Test ID : 34 - In Delivered Yard page Comments value is not matching *****");
	        

		}else{
			strMessage = strMessage + "  Delivered Yard Page is not loaded ";
		}
				
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:34-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test (priority=120)
    public void test_35_accessDeliveredYardLink(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	wait(2000);
    	String strTitle = objAdjunoLIMADeliveredYardPOM.callMouseHover(strDeleiveredYardFormName,objAdjunoLIMADeliveredYardPOM.getLnkTools(), objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard());
    	strTitle = driver.getTitle();
    	boolean bFlag = true;
    	
    	if (isNullOrBlank(strTitle))
    		bFlag = false;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitleDeleiveredYard))
    		{
    			
    			bFlag = true;
    		}
    		else
    			bFlag = false;
    	}	       
    	
    	objSoftAssert.assertEquals(bFlag, true, "*****  Test ID : 35 - Title of the Delivered Yard page is Wrong *****");
    	objSoftAssert.assertAll();
    	
    }
       
    @Test(priority=125)
    public void test_36_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		//containerList.remove(position);
 		String strMessage ="";
 	
 		wait(4000);
 		clearFields();
 		
 		if(!isNullOrBlank(strStatusDepated)){
 			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusDepated, strDeleiveredYardFormName, "PARAM_Status"),true,"*****Test ID:36- Departed status value is not set in Search chevron*****");
 	 	}else{
 	   		strMessage=strMessage+"Depated status Value is Empty in Xml";
 		}
 		
 		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strContainerValue, strDeleiveredYardFormName, "PARAM_Container"),true,"*****Test ID:36- Container value is not in Search Chevron In Delivered yard tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
		
 		
 		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"*****Test ID:36-  Select Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		 
 		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMADeliveredYardPOM.getLstContainer().size()-1; i++) {
				
				if(!objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText().isEmpty()){
					objAdjunoLIMADeliveredYardPOM.getLstSelect().get(i).click();
				//	strContainerValue = objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText();
					break;
				}
			}
				
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvDevlieredYard1()), true,"***** Test ID:36- Delivered Yard Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		
 		 if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strDeleiveredYardchevFormName)){
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "YardArrivalDate1"), true,"***** Test ID:36- yard Arrived Date field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "YardDepartureDate1"), true,"***** Test ID:36-   Yard Departed Date field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "Destination1"), true,"***** Test ID:36- Destination field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "ReasonCode1"), true,"***** Test ID:36- Reason field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "Comments1"), true,"***** Test ID:36- Comments field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "BUTTON_Update"), true,"***** Test ID:36- Update Button is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   
 			  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"YardArrivalDate")), true,"***** Test ID:36-  Yard ArrivalDate is not matching With Entered value *****");
 			   objSoftAssert.assertEquals(strDestination.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"Destination")), true,"***** Test ID:36-  Destination is not matching With Entered value *****");
 		   }else{
 			 strMessage = strMessage +" Delivered yard Chevron Page is not loaded";  
 		   }
 		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:36 "+ strMessage +" *****");
		objSoftAssert.assertAll();
    	
    }
    
    @Test(priority=130)
    public void test_37_VerifyGridValue() throws ParseException{
   	   SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
   	   String strMessage="";
   	   
   	   int nRows = (int) objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strDeleiveredYardchevFormName, "Grid_Containers");
   	   
   	  //   strArrivalDate = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName, "Grid_Containers", 0, "ArrivedDate");
   	   
   	   strNextDay = objAdjunoLIMADeliveredYardPOM.getDate(0, "dd MMM yyyy");
   	   
   	  //  strNextDay2 = objAdjunoLIMADeliveredYardPOM.getDate(1, "dd MMM yyyy");
   	   
   	   if(!isNullOrBlank(strNextDay)){
   			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay, strDeleiveredYardchevFormName, "YardArrivalDate1"),true,"***** *****");
   			
   	   }else{
   	   		strMessage=strMessage+" yard Arrival Date Value is Empty";
   	   }
   	   
   	   
   	   if(!isNullOrBlank(strDestination2)){
   			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strDestination2, strDeleiveredYardchevFormName, "Destination1"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Destination Value is Empty";
   	   }
   	   
   	   
   	   wait(4000);
   	  
   	  for (int i = 0; i <= nRows-1; i++) {
   		  	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getGridCellElement(strDeleiveredYardchevFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:37- Select CheckBox is not clicked in the Delivered Yard Chevron *****");   		  	
   	  }
   	  
   	  wait(2000);
   	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardchevFormName, "BUTTON_Update"),true,"***** Test ID:37- Update buttoin not clicked in Delivered yard Chevron *****");
   	  wait(3000);
   	  
   	  //verify entered data
   	  for (int i = 0; i <= nRows-1; i++) {
  	
   		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"YardArrivalDate")), true,"***** Test ID:37-  Yard Arrival Date is not matching With Entered value *****");
   	 	  objSoftAssert.assertEquals(strDestination2.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"Destination")), true,"***** Test ID:37-  Destination is not matching With Entered value *****");
   	 	 
   	  }
   	  
   	   
   	  //strTrackN0 = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"TrackReference");
   	  wait(4000);
   	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID : 37"+ strMessage +"*****");
   	  objSoftAssert.assertAll();
      }
     
    
    @Test(priority=135)
    public void test_38_complete(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvComplete2()), true,"***** Test ID:34- Complete Chevron is not clicked  *****");
 	   wait(2000);
 	   String poDetails = objAdjunoLIMADeliveredYardPOM.getTrackMessage().getText();
 	   String[] a= poDetails.split("achieved on track '", 2);
 	   a = a[1].split("'");
 	   strTrackN0 = a[0];
 	   System.out.println(strTrackN0);
 	   wait(5000);
 	   objSoftAssert.assertAll();
    }
    
    
    @Test(priority=140)
    public void test_40_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   
 	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
 	    	
 	  objAdjunoLIMADeliveredYardPOM.callMouseHover(strCatalogFormName,objAdjunoLIMADeliveredYardPOM.getLnkTrack(),objAdjunoLIMADeliveredYardPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:40- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    
    @Test(priority=145)
	public void test_41_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getFldRefNo()),true," ***** Test ID:41-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValueForWebElement(objAdjunoLIMADeliveredYardPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:41-Reference field value is not set");   
			    	  
			 objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:41-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    @Test(priority=150)
    public void test_42_clickYardLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard2()), true,"***** Test ID:42- Delivered Yard link is not clicked *****");
		wait(5000);
		
		if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strDeliveredYardLinkFormName)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdContainer()), true,"***** Test ID : 42 - In Delivered Yard page \"Container\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdSeal()), true,"***** Test ID : 42 - In Delivered Yard page \"Seal\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdArrivalDate()), true,"***** Test ID :42 - In Delivered Yard page \"Arrived Date\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdDepartureDate()), true,"***** Test ID : 42 - In Delivered Yard page \"Departure Date\" is not found *****");
	        

			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdDestination()), true,"***** Test ID : 42 - In Delivered Yard page \"OffDockLocation\"  is not found *****");
	       
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdReasonCode()), true,"***** Test ID :42 - In Delivered Yard page \"ReasonCode\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdComments()), true,"***** Test ID : 42 - In Delivered Yard page \"Comments\" is not found *****");
			
	        
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColContainer().getText().equalsIgnoreCase(strContainerValue), true," Test ID : 42 - In Delivered Yard page Container value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColArrivedDate().getText().contains(strNextDay), true," Test ID : 42 - In Delivered Yard page Arrived Date value is not matching *****");
	        
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColDestination().getText().equalsIgnoreCase(strDestination2), true," Test ID : 42 - In Delivered Yard page Destination value is not matching *****");
	        

		}else{
			strMessage = strMessage + "  Delivered Yard Page is not loaded ";
		}
				
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:42-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test (priority=155)
    public void test_43_accessDeliveredYardLink(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	wait(2000);
    	String strTitle = objAdjunoLIMADeliveredYardPOM.callMouseHover(strDeleiveredYardFormName,objAdjunoLIMADeliveredYardPOM.getLnkTools(), objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard());
    	strTitle = driver.getTitle();
    	boolean bFlag = true;
    	
    	if (isNullOrBlank(strTitle))
    		bFlag = false;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitleDeleiveredYard))
    		{
    			
    			bFlag = true;
    		}
    		else
    			bFlag = false;
    	}	       
    	
    	objSoftAssert.assertEquals(bFlag, true, "*****  Test ID : 43 - Title of the Delivered Yard page is Wrong *****");
    	objSoftAssert.assertAll();
    	
    }
    

    @Test(priority=160)
    public void test_44_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		//containerList.remove(position);
 		String strMessage ="";
 	
 		wait(4000);
 		clearFields();
 		
 		if(!isNullOrBlank(strStatusDepated)){
 			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusDepated, strDeleiveredYardFormName, "PARAM_Status"),true,"*****Test ID:44- Arrived status value is not set in Search chevron*****");
 	 	}else{
 	   		strMessage=strMessage+"departed status Value is Empty in Xml";
 		}
 		
 		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strContainerValue, strDeleiveredYardFormName, "PARAM_Container"),true,"*****Test ID:44- Container value is not in Search Chevron In Delivered Yard tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
 		
 		
 		
 		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"*****Test ID:44-  Select Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		 
 		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
		if(searchResult==false){
			
			for (int i = 0; i <= objAdjunoLIMADeliveredYardPOM.getLstContainer().size()-1; i++) {
				
				if(!objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText().isEmpty()){
					objAdjunoLIMADeliveredYardPOM.getLstSelect().get(i).click();
				//	strContainerValue = objAdjunoLIMADeliveredYardPOM.getLstContainer().get(i).getText();
					break;
				}
			}
				
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvDevlieredYard1()), true,"***** Test ID:44- Delivered Yard Chevron is not clicked in Delivered Yard Tool *****");
 		wait(4000);
 		
 		 if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strDeleiveredYardchevFormName)){
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "YardArrivalDate1"), true,"***** Test ID:44- yard Arrived Date field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "YardDepartureDate1"), true,"***** Test ID:44-   Yard Departed Date field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "Destination1"), true,"***** Test ID:44- Destination field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "ReasonCode1"), true,"***** Test ID:44- Reason field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "Comments1"), true,"***** Test ID:44- Comments field is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.checkDoesElementExist(strDeleiveredYardchevFormName, "BUTTON_Update"), true,"***** Test ID:44- Update Button is not found in \"Delivered Yard Chevorn\" in Delivered Yard Tool *****");
 			   
 			  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"YardArrivalDate")), true,"***** Test ID:44-  Yard ArrivalDate is not matching With Entered value *****");
 			   objSoftAssert.assertEquals(strDestination2.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"Destination")), true,"***** Test ID:44-  Destination is not matching With Entered value *****");
 		   }else{
 			 strMessage = strMessage +" Delivered yard Chevron Page is not loaded";  
 		   }
 		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:44 "+ strMessage +" *****");
		objSoftAssert.assertAll();
    	
    }
    
    @Test(priority=165)
    public void test_45_VerifyGridValue() throws ParseException{
   	   SoftAssert objSoftAssert = new SoftAssert();
   	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);	
   	   String strMessage="";
   	   
   	   int nRows = (int) objAdjunoLIMADeliveredYardPOM.getNoOfRowsGrid(strDeleiveredYardchevFormName, "Grid_Containers");
   	   
   	  //   strArrivalDate = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName, "Grid_Containers", 0, "ArrivedDate");
   	   
   	   strNextDay = objAdjunoLIMADeliveredYardPOM.getDate(-1, "dd MMM yyyy");
   	   
   	   strNextDay2 = objAdjunoLIMADeliveredYardPOM.getDate(0, "dd MMM yyyy");
   	   
   	   if(!isNullOrBlank(strNextDay)){
   			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay, strDeleiveredYardchevFormName, "YardArrivalDate1"),true,"***** *****");
   			
   	   }else{
   	   		strMessage=strMessage+" yard Arrival Date Value is Empty";
   	   }
	   
	   if(!isNullOrBlank(strNextDay2)){
   			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strNextDay2, strDeleiveredYardchevFormName, "YardDepartureDate1"),true,"***** *****");
   			
   	   }else{
   	   		strMessage=strMessage+" yard Departure Value is Empty";
   	   }
   	   
   	   
   	   if(!isNullOrBlank(strDestination2)){
   			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strDestination2, strDeleiveredYardchevFormName, "Destination1"),true,"***** *****");	
   	   }else{
   	   		strMessage=strMessage+"Destination Value is Empty";
   	   }
   	   
   	   
   	   wait(4000);
   	  
   	  for (int i = 0; i <= nRows-1; i++) {
   		  	objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getGridCellElement(strDeleiveredYardchevFormName,"Grid_Containers", i, "Select")), true,"***** Test ID:45- Select CheckBox is not clicked in the Delivered Yard Chevron *****");
   		  	
   	  }
   	  wait(2000);
   	  objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButton(strDeleiveredYardchevFormName, "BUTTON_Update"),true,"***** Test ID:45- Update buttoin not clicked in Delivered Yard Chevron *****");
   	  wait(3000);
   	  
   	  //verify entered data
   	  for (int i = 0; i <= nRows-1; i++) {
  	
   		  objSoftAssert.assertEquals(strNextDay.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"YardArrivalDate")), true,"***** Test ID:45-  Yard Arrival Date is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strNextDay2.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"YardDepartureDate")), true,"***** Test ID:45-  Yard Departure Date is not matching With Entered value *****");
   	 	  
   	 	  objSoftAssert.assertEquals(strDestination2.equalsIgnoreCase(objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",i,"Destination")), true,"***** Test ID:45-  Destination is not matching With Entered value *****");
   	 	 
   	  }
   	  
   	   
   	  //strTrackN0 = objAdjunoLIMADeliveredYardPOM.getGridCellElementValue(strDeleiveredYardchevFormName,"Grid_Containers",0,"TrackReference");
   	  wait(4000);
   	  objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID :45"+ strMessage +"*****");
   	  objSoftAssert.assertAll();
      }
     
    
    @Test(priority=170)
    public void test_46_complete(){
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvComplete2()), true,"***** Test ID:46- Complete Chevron is not clicked  *****");
 	   wait(2000);
 	   String poDetails = objAdjunoLIMADeliveredYardPOM.getTrackMessage().getText();
 	   String[] a= poDetails.split("achieved on track '", 12);
 	   
 	   a = a[1].split("'");
 	   strTrackN0 = a[0];
 	   System.out.println(strTrackN0);
 	   wait(5000);
 	   objSoftAssert.assertAll();
    }

    @Test(priority=175)
    public void test_48_ClickOnTrackLink(){	    
 	   SoftAssert objSoftAssert = new SoftAssert();
 	   
 	   objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
 	    	
 	  objAdjunoLIMADeliveredYardPOM.callMouseHover(strCatalogFormName,objAdjunoLIMADeliveredYardPOM.getLnkTrack(),objAdjunoLIMADeliveredYardPOM.getLnkEdit());
 	   wait(4000);
 	  
 	   objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getEditTitle().getText(), "Edit or create a track","***** Test ID:48- Header Title is not matching *****");
 	   
 	   objSoftAssert.assertAll();
 	}
    
    @Test(priority=180)
	public void test_49_setTrackNumber() {
		SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getFldRefNo()),true," ***** Test ID:49-Reference field is not exist in Edit Track");
		
		if(!isNullOrBlank(strTrackN0)){	
			 objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValueForWebElement(objAdjunoLIMADeliveredYardPOM.getFldRefNo(),strTrackN0),true,"***** Test ID:49-Reference field value is not set");   
			    	  
			 objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getBtnApply());
			 wait(3000);
		}else{
        	strMessage =  "Track number value is empty";		        	
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:49-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test(priority=190)
    public void test_50_clickYardLink(){
    	SoftAssert objSoftAssert = new SoftAssert();
		String strMessage = "";
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		
		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard2()), true,"***** Test ID:50- Delivered Yard link is not clicked *****");
		wait(5000);
		
		if(objAdjunoLIMADeliveredYardPOM.verifyPageIsLoaded(strDeliveredYardLinkFormName)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdContainer()), true,"***** Test ID : 50 - In Delivered Yard page \"Container\"  is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdSeal()), true,"***** Test ID : 50 - In Delivered Yard page \"Seal\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdArrivalDate()), true,"***** Test ID :50 - In Delivered Yard page \"Arrived Date\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdDepartureDate()), true,"***** Test ID : 50 - In Delivered Yard page \"Departure Date\" is not found *****");
	        

			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdDestination()), true,"***** Test ID : 50 - In Delivered Yard page \"OffDockLocation\"  is not found *****");
	       
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdReasonCode()), true,"***** Test ID :50 - In Delivered Yard page \"ReasonCode\" is not found *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getHdComments()), true,"***** Test ID : 50 - In Delivered Yard page \"Comments\" is not found *****");
			
	        
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColContainer().getText().equalsIgnoreCase(strContainerValue), true," Test ID : 50 - In Delivered Yard page Container value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColArrivedDate().getText().contains(strNextDay), true," Test ID : 50 - In Delivered Yard page Arrived Date value is not matching *****");
	       
	        
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColDepartedDate().getText().contains(strNextDay2), true," Test ID : 50 - In Delivered Yard page Departure Date value is not matching *****");
	        objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.getColDestination().getText().equalsIgnoreCase(strDestination2), true," Test ID : 50 - In Delivered Yard page Delivered Yard  value is not matching *****");
	        

		}else{
			strMessage = strMessage + "  Delivered Yard Page is not loaded ";
		}
				
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:50-"+ strMessage +"*****");
		
		objSoftAssert.assertAll();
		
    }
    
    
    @Test (priority=195)
    public void test_51_accessDeliveredYardLink(){
    	
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
    	wait(2000);
    	String strTitle = objAdjunoLIMADeliveredYardPOM.callMouseHover(strDeleiveredYardFormName,objAdjunoLIMADeliveredYardPOM.getLnkTools(), objAdjunoLIMADeliveredYardPOM.getLnkDeliveredYard());
    	strTitle = driver.getTitle();
    	boolean bFlag = true;
    	
    	if (isNullOrBlank(strTitle))
    		bFlag = false;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitleDeleiveredYard))
    		{
    			
    			bFlag = true;
    		}
    		else
    			bFlag = false;
    	}	       
    	
    	objSoftAssert.assertEquals(bFlag, true, "*****  Test ID : 51 - Title of the Delivered Yard page is Wrong *****");
    	objSoftAssert.assertAll();
    	
    }
    

    @Test(priority=200)
    public void test_51_SearchForData(){
		SoftAssert objSoftAssert = new SoftAssert();
		objAdjunoLIMADeliveredYardPOM = new AdjunoLIMADeliveredYardPOM(driver);
		//containerList.remove(position);
 		String strMessage ="";
 	
 		wait(4700);
 		clearFields();
 		
 		if(!isNullOrBlank(strStatusDepated)){
 			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strStatusDepated, strDeleiveredYardFormName, "PARAM_Status"),true,"*****Test ID:51- Arrived status value is not set in Search chevron*****");
 	 	}else{
 	   		strMessage=strMessage+"Arrived status Value is Empty in Xml";
 		}
 		
 		if(!isNullOrBlank(strContainerValue)){
			objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.setFieldValue(strContainerValue, strDeleiveredYardFormName, "PARAM_Container"),true,"*****Test ID:51- Container value is not in Search Chevron In Delivered Yard tool *****");
	 	
	   	}else{
	   		strMessage=strMessage+"Container Value is Empty";
		}
 		
 		
 		objSoftAssert.assertEquals(objAdjunoLIMADeliveredYardPOM.clickButtonUsingWebElement(objAdjunoLIMADeliveredYardPOM.getChvSelect()), true,"*****Test ID:51-  Select Chevron is not clicked in Delivered Yard Tool *****");
 		wait(5000);
 		 
 		searchResult = objAdjunoLIMADeliveredYardPOM.isElementPresent(objAdjunoLIMADeliveredYardPOM.getTxtNoResult());
		
		if(searchResult==false){
			
				
		} else {
			strMessage = strMessage + "NO search Result for this criteria ";
		}
		
		objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true, "***** Test ID:51 "+ strMessage +" *****");
		objSoftAssert.assertAll();
    	
    }

    
    @AfterClass
    public void terminate(){
    	driver.quit();
    }
}