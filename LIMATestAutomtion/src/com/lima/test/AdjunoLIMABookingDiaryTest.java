package com.lima.test;

import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
import com.lima.pagefactory.AdjunoLIMABookingDiaryPOM;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMABookingDiaryTest {


	WebDriver driver;
	boolean searchResult;
    long nPage_Timeout = 0;
    String strTestURL;
    String strTrackN0;
    
    String strUserName;
    String strPassword;
    
    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoUILibrary objAdjunoUILibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;
    AdjunoLIMABookingDiaryPOM objAdjunoLIMABookingDiaryPOM;
	
    String  strFormNameBookingDiary;
	String strBookingDiaryTitle;
	String strOrderType;
	String strDomesticOrderType;
    
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
            
            Node testLIMAURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testLIMAURL.getTextContent();
  	            
  	        Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
  	        strUserName = limaUserName.getTextContent();

  	        Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
  	        strPassword = limaPassword.getTextContent();
  	        
  	        Node formNameBookingDiary = (Node) xPath.evaluate("/config/LIMA/Form_Name_BookingDiary", dDoc, XPathConstants.NODE);
  	        strFormNameBookingDiary = formNameBookingDiary.getTextContent();
  	        
  	        Node titleBookingDiary = (Node) xPath.evaluate("/config/LIMA/Booking_Diary_Title", dDoc, XPathConstants.NODE);
	        strBookingDiaryTitle = titleBookingDiary.getTextContent();
  	      
  	        
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
           /* DocumentBuilder builder = domFactory1.newDocumentBuilder();
            Document dDoc1 = builder.parse(objAdjunoLIMALibrary.getBookingDairyXMLDataFileName());

            XPath xPath1 = XPathFactory.newInstance().newXPath();*/
            
           /* Node status = (Node) xPath1.evaluate("/Forwarder_Reference/Search_Param/Status_Awaiting", dDoc1, XPathConstants.NODE);
            strStatus = status.getTextContent();
           */
        }catch(Exception ex){
           ex.printStackTrace();
        }
   }
    
    public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
    
    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
    
    @Test(priority=1)
    public void test_1_AccessBookingDairy(){
    	SoftAssert objSoftAssert = new SoftAssert();
	    objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
	        
	    String strTitle = objAdjunoLIMABookingDiaryPOM.callMouseHover(strFormNameBookingDiary,objAdjunoLIMABookingDiaryPOM.getLnkTools(),objAdjunoLIMABookingDiaryPOM.getLnkBookingDiary());
	    	
	    boolean bFlag = true;
	    	
	    if (!(isNullOrBlank(strTitle))){
	    	if (strTitle.equalsIgnoreCase(strBookingDiaryTitle))
	    		bFlag = true;
	    	else
	    		bFlag = false;
	    }else{
	    	bFlag = false;
	    }	       
	   	objSoftAssert.assertEquals(bFlag, true, " Test Id:1- Title of the page is wrong ");
	   	objSoftAssert.assertAll();
    
    }
    
    public void clear_fields(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_OrderType"), true," can't clear container field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_Destination"), true,"can't clear destination field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_BookingRef"), true,"can't clear booking Reference field");

	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_ContainerMAWBTrailer"), true," can't clear Container field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_Vendor"), true,"can't clear vendor field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_Mode"), true,"can't clear mode field");
	    
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_Vessel"), true," can't clear vessel field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_PONumber"), true,"can't clear po number field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_BookingStatus"), true,"can't clear booking status field");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clearInputField(strFormNameBookingDiary,"PARAM_WorkflowStatus"), true,"can't clear work flow status field");
    	objSoftAssert.assertAll();
    }
    
    @Test(priority=10)
    public void test_2_Existance_Of_Fiels(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
	    
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_OrderType"), true,"container field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_Destination"), true,"destination field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_BookingRef"), true,"booking Reference field doesn't found");

	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_ContainerMAWBTrailer"), true,"Container field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_Vendor"), true,"vendor field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_Mode"), true,"mode field doesn't found");
	    
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_Vessel"), true," vessel field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_PONumber"), true,"po number field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_BookingStatus"), true,"booking status field doesn't found");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"PARAM_WorkflowStatus"), true,"work flow status field doesn't found");

	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"BookingsSearch"), true,"Search button doesn't found");

	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkDoesElementExist(strFormNameBookingDiary,"BookingDiaryUpload"), true,"Browse button doesn't found");
	    objSoftAssert.assertAll();
    }
    
    @Test(priority=15)
    public void test_3_check_For_FrozonFields(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
    	String strMessage = "";
    	
    	clear_fields();
    	
    	if(!isNullOrBlank("Domestic")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("Domestic", strFormNameBookingDiary,"PARAM_OrderType"), true,"***** Test Id :9 - status field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " Domestic value is empty in Xml ";
 	    }
	    
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkFieldIsReadOnly(strFormNameBookingDiary,"PARAM_Mode","test"), false," Mode field is not in frozon mode");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkFieldIsReadOnly(strFormNameBookingDiary,"PARAM_Vessel","test"), false,"vessel field is not in frozon mode");
	    objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.checkFieldIsReadOnly(strFormNameBookingDiary,"PARAM_ContainerMAWBTrailer","test"), false,"Container field is not in frozon mode");

	    objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,""+strMessage);
	    objSoftAssert.assertAll();	
    }
    
    @Test(priority=20)
    public void test_4_check_For_MandatoryField(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
    	clear_fields();
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clickButton(strFormNameBookingDiary,"BookingsSearch"), true,"Search button not clicked");
    	wait(4000);
    	
    	String strErrorOrdertype = objAdjunoLIMABookingDiaryPOM.getValidationMessageFieldElement(strFormNameBookingDiary,"PARAM_OrderType");
    	System.out.println("sds"+strErrorOrdertype);
    	if(!strErrorOrdertype.equals("")){
 	   		if(strErrorOrdertype.contains("&#39;")){
 	   		strErrorOrdertype = strErrorOrdertype.replaceAll("&#39;", "'");
 		   	}
 	   		boolean bFlag = strErrorOrdertype.equalsIgnoreCase("> 'Order Type' is a required field");
 	   		objSoftAssert.assertEquals(bFlag, true,"**** Test Id:4 - The Mandatory field error message mismatch *****");	   			
 	   	}else{
 	   		strMessage = strMessage + "OrderType field error message is empty ";
 	   	}
 	   	
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,""+strMessage);
 	   	
    	objSoftAssert.assertAll();
    }
    
   
    
    @Test(priority=30)
    public void test_5_check_For_InvalidPoNumber(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
    	clear_fields();

    	if(!isNullOrBlank("Domestic")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("Domestic", strFormNameBookingDiary,"PARAM_OrderType"), true,"***** Test Id :5 - order type field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " Domestic value is empty in Xml ";
 	    }
    	
    	
    	if(!isNullOrBlank("3753")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("3753", strFormNameBookingDiary,"PARAM_PONumber"), true,"***** Test Id :5 - Po number field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " Po number value is empty in Xml ";
 	    }
	    
    	if(!isNullOrBlank("Pending")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("Pending", strFormNameBookingDiary,"PARAM_BookingStatus"), true,"***** Test Id :5 - Po number field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " status value is empty in Xml ";
 	    }

    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clickButton(strFormNameBookingDiary,"BookingsSearch"), true,"Search button not clicked");
    	wait(5000);
    	
    	long nRow = objAdjunoLIMABookingDiaryPOM.getNoOfRowsInGrid(strFormNameBookingDiary,"Grid_Bookings");
    	System.out.println("row:"+nRow);
    	if(nRow ==0){
    		
    	}else{
    		strMessage =strMessage + " Products are found ";
    	}
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,""+strMessage);
 	   	
 	   	
    	objSoftAssert.assertAll();
    }
    
    
    @Test(priority=35)
    public void test_6_Search_For_Pending_Status(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	boolean searchResult;
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
    	clear_fields();

    	if(!isNullOrBlank("International")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("International", strFormNameBookingDiary,"PARAM_OrderType"), true,"***** Test Id :5 - order type field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " International value is empty in Xml ";
 	    }
    	
    	
    	if(!isNullOrBlank("Pending")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("Pending", strFormNameBookingDiary,"PARAM_BookingStatus"), true,"***** Test Id :5 - Po number field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " status value is empty in Xml ";
 	    }
	    
    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clickButton(strFormNameBookingDiary,"BookingsSearch"), true,"Search button not clicked");
    	wait(5000);
    	
    	long nRow = objAdjunoLIMABookingDiaryPOM.getNoOfRowsInGrid(strFormNameBookingDiary,"Grid_Bookings");
    	System.out.println("row:"+nRow);
    	if(nRow ==0){
    		searchResult = false;
    	}else{
    		searchResult = true;
   
    	}
    	
    	if(searchResult==true){
    		
	    	strMessage = strMessage + objAdjunoLIMABookingDiaryPOM.verifyValue("Pending",nRow, strFormNameBookingDiary,"Grid_Bookings","Status");
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
    	
    	
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,""+strMessage);
 	   	
 	   	
    	objSoftAssert.assertAll();
    }
    
   @Test(priority=40)
    public void test_7_Search_For_Pending_Status(){
    	SoftAssert objSoftAssert = new SoftAssert();
    	String strMessage = "";
    	boolean searchResult;
    	
    	
    	objAdjunoLIMABookingDiaryPOM = new AdjunoLIMABookingDiaryPOM(driver);
    	
    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clickButtonUsingWebElement(objAdjunoLIMABookingDiaryPOM.getExpandSearch()),true,"expand search not clicked");
    	clear_fields();

    	if(!isNullOrBlank("Domestic")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("Domestic", strFormNameBookingDiary,"PARAM_OrderType"), true,"***** Test Id :5 - order type field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " Domestic value is empty in Xml ";
 	    }
    	
    	
    	if(!isNullOrBlank("Pending")){
 	    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.setFieldValue("Pending", strFormNameBookingDiary,"PARAM_BookingStatus"), true,"***** Test Id :5 - Po number field value is not set *****");
 	    }else{
 	    	strMessage = strMessage + " status value is empty in Xml ";
 	    }
	    
    	objSoftAssert.assertEquals(objAdjunoLIMABookingDiaryPOM.clickButton(strFormNameBookingDiary,"BookingsSearch"), true,"Search button not clicked");
    	wait(5000);
    	
    	long nRow = objAdjunoLIMABookingDiaryPOM.getNoOfRowsInGrid(strFormNameBookingDiary,"Grid_Bookings");
    	System.out.println("row:"+nRow);
    	
    	if(nRow ==0){
    		searchResult = false;
    	}else{
    		searchResult = true;
   
    	}
    	
    	if(searchResult==true){
    		
	    	strMessage = strMessage + objAdjunoLIMABookingDiaryPOM.verifyValue("Pending",nRow, strFormNameBookingDiary,"Grid_Bookings","Status");
		    
	    }else{
	    	strMessage = strMessage + "NO search Result for this criteria ";
	    }
    	
    	
 	   	objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,""+strMessage);
 	   	
 	   	
    	objSoftAssert.assertAll();
    }
   
   @Test(priority=45)
   public void test_8_DragAndDrop(){
	   SoftAssert objSoftAssert = new SoftAssert();
   	   String strMessage = "";
   	   boolean searchResult;
   	  
   	   Actions builder = new Actions(driver);

   	/*   Action dragAndDrop = builder.clickAndHold().moveToElement().release().build();

   	   dragAndDrop.perform();*/
   	
   	
   	   objSoftAssert.assertEquals(strMessage.equalsIgnoreCase(""), true,""+strMessage);
	   	
	   	
   	   objSoftAssert.assertAll();
   }
   
   
}
