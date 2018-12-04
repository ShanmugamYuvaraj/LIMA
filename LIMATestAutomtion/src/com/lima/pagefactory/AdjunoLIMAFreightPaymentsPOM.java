package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.lima.dto.BookingConfirmation;
import com.lima.dto.FreightPayments;
import com.lima.dto.ProductionCheck;
import com.lima.dto.ShipAuthority;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAFreightPaymentsPOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Freight Payment") WebElement lnkFreightPayments;
	@FindBy(linkText="Logoff") WebElement lnkLogOff;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Lighthouse") WebElement lnkLightHouse;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]/a") WebElement lnkConfiguration;
	
	@FindBy(xpath=".//*[@class='Stage' and text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[text()='Update']") WebElement chevUpdate;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Update']") WebElement chevUpdate1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnApply;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_TabPanel_Configuration__id_extCatalog_LIMA_Configuration_TabItem_TariffModule']") WebElement btnFinance;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_TbPanel_Configuration__id_extCatalog_LIMA_Configuration_Tab_FreightPayment']") WebElement btnFreightPayment;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_Grid_ChargesByCarrier_bbar_btn_AddRow']") WebElement btnAddRowChargeByCarrier;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_Grid_ChargesByCarrier_bbar_btn_DeleteRows']") WebElement btnDeleteRowChargeByCarrier;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_Grid_InvoicesDue_bbar_btn_AddRow']") WebElement btnAddRowInvoiceDue;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_Grid_InvoicesDue_bbar_btn_DeleteRows']") WebElement btnDeleteRowInvoiceDue;
	@FindBy(xpath=".//*[text()='Are you sure you want to delete the selected row?']") WebElement confirmationMsg;
	@FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	@FindBy(xpath=".//*[text()='No']") WebElement btnNo;
		
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[1]/input") List<WebElement> lstCheckBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[2]") List<WebElement> lstContainerNumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[7]") List<WebElement> lstStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> lstCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> lstCarrierType;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[3]") List<WebElement> lstEquipment;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[4]") List<WebElement> lstLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[5]") List<WebElement> lstHBL;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[6]") List<WebElement> lstOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[7]") List<WebElement> lstDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[8]") List<WebElement> lstETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[9]") List<WebElement> lstETA;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement trContainerNo;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[3]") WebElement trEquipment;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[4]") WebElement trLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[5]") WebElement trHBL;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[6]") WebElement trOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[7]") WebElement trDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[8]") WebElement trETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[9]") WebElement trETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]") WebElement trStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement trCheckBox;
	
	@FindBy(xpath=".//*[@id='ext-gen66']/div/table/tbody/tr/td[9]/div/a") WebElement hyperLnkTariff;
	@FindBy(xpath=".//*[text()='Details']") WebElement hyperLnkRateCard;
	@FindBy(xpath=".//*[text()='Rate Card Details']") WebElement RateCardDetailsPopUp;
	
	@FindBy(xpath=".//*[@id='extId_LIMA_FreightPayment_INPUT_UPDATE_Grid_HiddenRateCard_bbar_btn_AddRow']") WebElement btnRateCardAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_FreightPayment_INPUT_UPDATE_Grid_HiddenRateCard_bbar_btn_DeleteRows']") WebElement btnRateCardDeleteRow;
	
	
	
	public WebElement getLnkLogOff() {
		return lnkLogOff;
	}

	public List<WebElement> getLstCheckBox() {
		return lstCheckBox;
	}

	public List<WebElement> getLstEquipment() {
		return lstEquipment;
	}

	public List<WebElement> getLstLoading() {
		return lstLoading;
	}

	public List<WebElement> getLstHBL() {
		return lstHBL;
	}

	public List<WebElement> getLstOriginPort() {
		return lstOriginPort;
	}

	public List<WebElement> getLstDestinationPort() {
		return lstDestinationPort;
	}

	public List<WebElement> getLstETD() {
		return lstETD;
	}

	public List<WebElement> getLstETA() {
		return lstETA;
	}

	public WebElement getTrStatus() {
		return trStatus;
	}

	public WebElement getChevComplete1() {
		return chevComplete1;
	}

	public WebElement getBtnRateCardAddRow() {
		return btnRateCardAddRow;
	}

	public WebElement getBtnRateCardDeleteRow() {
		return btnRateCardDeleteRow;
	}

	public WebElement getRateCardDetailsPopUp() {
		return RateCardDetailsPopUp;
	}

	public WebElement getHyperLnkRateCard() {
		return hyperLnkRateCard;
	}

	public WebElement getHyperLnkTariff() {
		return hyperLnkTariff;
	}

	public WebElement getChevSearch() {
		return chevSearch;
	}

	public WebElement getChevSelect() {
		return chevSelect;
	}

	public WebElement getChevUpdate() {
		return chevUpdate;
	}

	public WebElement getChevComplete() {
		return chevComplete;
	}

	public WebElement getTrContainerNo() {
		return trContainerNo;
	}

	public WebElement getTrEquipment() {
		return trEquipment;
	}

	public WebElement getTrLoading() {
		return trLoading;
	}

	public WebElement getTrHBL() {
		return trHBL;
	}

	public WebElement getTrOriginPort() {
		return trOriginPort;
	}

	public WebElement getTrDestinationPort() {
		return trDestinationPort;
	}

	public WebElement getTrETD() {
		return trETD;
	}

	public WebElement getTrETA() {
		return trETA;
	}

	public WebElement getChevUpdate1() {
		return chevUpdate1;
	}

	public WebElement getTrCheckBox() {
		return trCheckBox;
	}

	public List<WebElement> getLstCarrier() {
		return lstCarrier;
	}

	public List<WebElement> getLstCarrierType() {
		return lstCarrierType;
	}

	public WebElement gettrContainerNo() {
		return trContainerNo;
	}

	public List<WebElement> getLstStatus() {
		return lstStatus;
	}

	public List<WebElement> getLstContainerNumber() {
		return lstContainerNumber;
	}

	public WebElement getChevSelect1() {
		return chevSelect1;
	}

	public WebElement getLnkFreightPayments() {
		return lnkFreightPayments;
	}

	public WebElement getBtnAddRowInvoiceDue() {
		return btnAddRowInvoiceDue;
	}

	public WebElement getBtnDeleteRowInvoiceDue() {
		return btnDeleteRowInvoiceDue;
	}

	public WebElement getBtnNo() {
		return btnNo;
	}

	public WebElement getBtnYes() {
		return btnYes;
	}

	public WebElement getConfirmationMsg() {
		return confirmationMsg;
	}

	public WebElement getBtnDeleteRowChargeByCarrier() {
		return btnDeleteRowChargeByCarrier;
	}
	
	public WebElement getBtnAddRowChargeByCarrier() {
		return btnAddRowChargeByCarrier;
	}

	public WebElement getBtnFreightPayment() {
		return btnFreightPayment;
	}

	public WebElement getBtnFinance() {
		return btnFinance;
	}

	public WebElement getBtnApply() {
		return btnApply;
	}
	
	public WebElement getLnkConfiguration() {
		return lnkConfiguration;
	}

	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}

	public AdjunoLIMAFreightPaymentsPOM(WebDriver driver)
	{
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public void wait(int ms)
    {
    	try 
    	{
			Thread.sleep(ms);
		} 
    	catch (InterruptedException e) 
    	{
			e.printStackTrace();
		}

    }
	
	private boolean isNullOrBlank(String s)
	{
	      return (s==null || s.trim().equals(""));
	}
	
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink)
	{
	       Actions action = new Actions(driver);

	       action.moveToElement(mainLink).perform();
	      
	       action.moveToElement(mainLink).perform();

	       action.moveToElement(subLink);
	       
	       action.click();
	       
	       action.perform();
	       
	       objAdjunoUILibrary = new AdjunoUILibrary(driver);
	       String strTitle = "";
	         if (objAdjunoUILibrary.isPageLoaded(strFormName))
	         {
	             strTitle = driver.getTitle();
	         }
	         return strTitle;
	 }
	
	public boolean verifyPageIsLoaded(String strFormName)
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try
		{
		objAdjunoUILibrary.isPageLoaded(strFormName);
		bFlag = true;
		}
		catch(Exception e)
		{
			bFlag = false;
		}
		return bFlag;
	}
	
	public boolean clickLinkUsingWebElement(WebElement we) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
                
        try
       {
            bFlag = we.isDisplayed();
            we.click();
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;
        
    }
	
	public boolean clickButtonUsingWebElement(WebElement we) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
                
        try
       {
            bFlag = we.isDisplayed();
            we.click();
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;
        
    }
	
	public boolean clickButton(String strFormName, String strControlName) 
	{
		Boolean bFlag;	
		objAdjunoUILibrary = new AdjunoUILibrary(driver);	
		try {
			WebElement button = objAdjunoUILibrary.getElement(strFormName, strControlName);
			button.click();
			wait(2000);
			bFlag = true;
		} catch (NoSuchElementException e) {
			
			bFlag = false;
		}		
		return bFlag;
		
	}
	
	public boolean checkDoesFieldsExist(String strFormName, String strControlName) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
           bFlag = we.isDisplayed();
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;
	}
	
	public boolean checkFieldIsReadOnlyInGrid(String strFormName, String strControlName, int nRow, String strColumnName,String strPreiousValue) {
        
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        WebElement we;
        try
       {
            we = objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, nRow, strColumnName);     
            we.click();
            wait(5000);
            
            we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strControlName, nRow, strColumnName);     
            bFlag = we.isDisplayed();
            we.sendKeys("");
            wait(1000);
            we.sendKeys(strPreiousValue);
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(Exception e)
       {
           bFlag = false;
       }
        
        return bFlag;    
    }
	
	public long getNoOfRowsGrid(String strFormName, String strGridControlName)
	{
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        long Rows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
        
        return Rows;
        
	}
	
	public String getGridCellElementValue(String strformName, String strControlName, int nRow, String strColonmName) {
		String tempVal;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getGridCellElementValue(strformName, strControlName,nRow,strColonmName);
			if(!isNullOrBlank(tempVal)){
				
				
			}
			else{
				tempVal ="";
			}
			
       return tempVal;				
	}
	
	public boolean setFieldValueForGridCell(String strFormName, String strGridControlName, int nRow, String strColumnName, String strValue) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           objAdjunoUILibrary.setGridCellValue(strFormName, strGridControlName, nRow, strColumnName, strValue);    
           WebElement we;
           we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strGridControlName, nRow, strColumnName);
           we.sendKeys(Keys.TAB);
           
           wait(2000);
 
           bFlag= true;
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;        
    } 
	
	public String getWebElementCellValue(WebElement we)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strWeCellValue="";
		strWeCellValue = we.getText();
		if(!isNullOrBlank(strWeCellValue))
		{
			
		}
		else
		{
			strWeCellValue = "The text of the WebElement is not available";
		}
		return strWeCellValue;
	}
	
	public boolean isElementPresent(WebElement we) 
	{
		boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        try
        {
        bFlag = objAdjunoUILibrary.isElementPresent(we);
        }
        catch (NoSuchElementException e)
        {
            bFlag = false;
        }

		return bFlag;	
	}
	
	public boolean clearInputField(String strFormName, String strControlName) 
	{	
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
           bFlag = we.isDisplayed();          
           we.clear();
           wait(1000);
           we.sendKeys(Keys.TAB);
          
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
        return bFlag;
	}
	
	public boolean setFieldValue(String strValue,String strFormName, String strControlName) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
           bFlag = we.isDisplayed();
           we.clear();
           we.sendKeys(strValue);
           we.sendKeys(Keys.TAB);
           
           
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(NullPointerException e)
       {
           bFlag = false;
       }
			
		return bFlag;
	}
	
	public boolean clickChevron(WebElement we)
	{
		Boolean bFlag;		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);	
		try {
			bFlag = objAdjunoUILibrary.isElementPresent(we);
			we.click();
			wait(2000);
		} catch (NoSuchElementException e) {
			
			bFlag = false;
		}		
		return bFlag;
	}
	
	public List<FreightPayments> getStatus() 
	{
		List<FreightPayments> list = new ArrayList<FreightPayments>();
	    
		for (int i = 0; i <= getLstContainerNumber().size()-1; i++) {
			FreightPayments po = new FreightPayments(getLstStatus().get(i).getText(),getLstContainerNumber().get(i).getText(),getLstCarrier().get(i).getText(),getLstCarrierType().get(i).getText()); 
			//scroll down 
			//JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			list.add(po);
			
			//jse.executeScript("window.scrollBy(0,100)", "");
		}
		return list;
	}
	
	public String verifyStaus(String strStatusAwaiting,List<FreightPayments> list) 
	{
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			
			if(list.get(i).getStrStatus().equalsIgnoreCase(strStatusAwaiting))
			{
				
			}
			else{
				
				strMessage = strMessage + "For this Container NO:"+ list.get(i).getStrStatus()+ "Status is not matching";
			}
		}
		
		return strMessage;
	}
	
	public boolean clickCheckBox(WebElement checkbox) 
	{
		boolean bFlag;
		try
        {
			bFlag = checkbox.isDisplayed();
			checkbox.click();
        }
        catch (NoSuchElementException e)
        {
        	bFlag = false;
        }
    	catch(NullPointerException e)
        {
    		bFlag = false;
        }
		return bFlag;
		
	}
	
	public boolean clickCheckBoxs(List <WebElement> checkbox) 
	{
		boolean bFlag = false;
		try
        {
			for(int i = 0;i<=checkbox.size()-1;i++)
			{
			bFlag = checkbox.get(i).isDisplayed();
			checkbox.get(i+1).click();
			break;
			}
        }
        catch (NoSuchElementException e)
        {
        	bFlag = false;
        }
    	catch(NullPointerException e)
        {
    		bFlag = false;
        }
		return bFlag;
		
	}
	
	public int getNoOfRowsResulted() {
	       
        return getLstContainerNumber().size();
    }
	
	public ArrayList<FreightPayments> selectMulitpleProducts(int nMaxProductsToSelect)
    {
        int nCounterLen = getNoOfRowsResulted();
        ArrayList<FreightPayments> lstSearchResults = new ArrayList<FreightPayments>();
       
        if (nCounterLen < nMaxProductsToSelect){
        	
        }
       else 
            nCounterLen = nMaxProductsToSelect;
            
        
        for(int i = 0; i <= nCounterLen - 1; i++)
        {          
            FreightPayments payment = new FreightPayments(getLstContainerNumber().get(i).getText(), getLstEquipment().get(i).getText(),getLstLoading().get(i).getText(),getLstHBL().get(i).getText(),getLstOriginPort().get(i).getText(),getLstDestinationPort().get(i).getText(),getLstETD().get(i).getText(),getLstETA().get(i).getText());
            lstCheckBox.get(i).click();
            lstSearchResults.add(payment);	
        }
		return lstSearchResults;
    }
	public boolean compareTwoStringValuesToSame(String strValue1, String strValue2)
    {
        boolean bFlag = true;
         if ((!(isNullOrBlank(strValue1))) &&  (!(isNullOrBlank(strValue2))))
         {                
             if (strValue1.equalsIgnoreCase(strValue2)){}
             else
             {
                 bFlag = false;
             }
         }
         else
         {
             bFlag = false;
         }
         
         return bFlag;
    }
	
	public String parseDate(String d, String pattern, String pattern1) {
	    String result = "";
	    
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
	    try {
	    	Date dateObject = dateFormatter.parse(d);
	        dateFormatter.applyPattern(pattern1);
	        result = dateFormatter.format(dateObject);

	    } catch (ParseException e) {
	        
	    }

	    return result;

	}
	
	public String verifyProductsDataOnGrid(String strFormName, String strControlName,ArrayList<FreightPayments> lstSearchResults) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	int n1 = lstSearchResults.size();
    	
    	long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
    	
    	
    	String strContainerNo = "";
    	String strEquipment = "";
    	String strLoading = "";
    	String strHBL = "";
    	String strOriginPort = "";
    	String strDestinationPort = "";
    	String strETD = "";
    	String strETA = "";
    	String strReturnMessage = "";
    	 
    	boolean bProductIDFound = false;
    	if(n1==n2)
    	{
        	for (int i=0; i <= n1-1;i++)
        	{	
        		  	
        		strContainerNo =lstSearchResults.get(i).getStrContainerNo();       		
    	    	strEquipment = lstSearchResults.get(i).getStrEquipment(); 
    	    	strLoading = lstSearchResults.get(i).getStrLoading();
    	    	strHBL = lstSearchResults.get(i).getStrHBL();
    	    	strOriginPort = lstSearchResults.get(i).getStrOriginPort();
    	    	strDestinationPort = lstSearchResults.get(i).getStrDestinationPort();
    	    	strETD = lstSearchResults.get(i).getStrETD();
    	    	strETA = lstSearchResults.get(i).getStrETA();
            	for (int j=0; j <= n2-1;j++)
            	{
            		if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "ContainerNumber").getText().equalsIgnoreCase(strContainerNo))
						  
            		{
            			bProductIDFound = true;
            			
            			
            			if(!(compareTwoStringValuesToSame(strContainerNo, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "ContainerNumber").getText())))
            			    strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', Container Number on the grid has issues;";

						if(!(compareTwoStringValuesToSame(strEquipment, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "Charges_Equipment").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', Equipment on the grid has issues;";
											
						if(!(compareTwoStringValuesToSame(strLoading, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Charges_Loading").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', Loading on the grid has issues;"; 
						
						if(!(compareTwoStringValuesToSame(strHBL, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"Charges_HBL").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', HBL on the grid has issues;";
							
						if(!(compareTwoStringValuesToSame(strOriginPort, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"Charges_OriginPort").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', Origin Port on the grid has issues;";
						
						if(!(compareTwoStringValuesToSame(strDestinationPort, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"Charges_DestinationPort").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', Destination Port on the grid has issues;";
					
						if(!(compareTwoStringValuesToSame(strETD, parseDate(objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"Charges_ETD").getText(), "dd MMM yyyy", "dd/MM/yyyy"))))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', ETD on the grid has issues;";
						
						if(!(compareTwoStringValuesToSame(strETA, parseDate(objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"Charges_ETA").getText(), "dd MMM yyyy", "dd/MM/yyyy"))))
							strReturnMessage = strReturnMessage + "For Container '" + strContainerNo + "', ETA on the grid has issues;";
					      	
						break;
            		}

					else 
					{
						if(j==n2-1)
						{
							strReturnMessage = strReturnMessage + "Container No '"+ strContainerNo + "' not found on the grid;";
						}
						
					}
            	}	
        	}
    	}
    	else
    	{
    		strReturnMessage = "The number of products selected in the search results under \"Select page\" do not match with the number of products in the \"Conatiner Charges grid\".";
    	}
    	
		return strReturnMessage;
	}
	
	public ArrayList<String> getCaptionsList(String strFormName, String strControlName) {
        objAdjunoUILibrary = new AdjunoUILibrary(driver); 
        long nGridColumns = objAdjunoUILibrary.getNoOfColumnsGrid(strFormName,strControlName);    
        String strColumnName = "";
        
        ArrayList<String> captionList = new ArrayList<String>();
        
        for (int i=0; i<= nGridColumns - 1; i++)
        {
            strColumnName = objAdjunoUILibrary.getColumnName(strFormName,strControlName, i);
            captionList.add(objAdjunoUILibrary.getGridColumnCaption(strFormName,strControlName,strColumnName,0));
        }
        
        return captionList;
    }
	
	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, int nCaptions) {
        String strMessage = "";
        Element e1;
        if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
        {
            e1 = (Element) nodeList.item(0);
            for (int j = 0; j <= nCaptions-1; j++) {
                String tempVal = e1.getElementsByTagName("Caption").item(j).getTextContent();
               for (int i = 0; i <= list.size()-1; i++) {
            	   String listVal = list.get(i);
            	   
                    if(tempVal.equalsIgnoreCase(listVal)){
                        break;
                    }else{
                        if(i==list.size()-1){
                            strMessage = strMessage+ tempVal+ "from Xml is not found in Grid";
                        }                                
                    }
               }
            
            }
        }    
        return strMessage;
    }
	
	public String getValidationMessageGridElement(String strFormName,String strGridControlName, int nRow, String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	
		String strValidationMsg = objAdjunoUILibrary.getValidationMessageGridElement(strFormName, strGridControlName, nRow, strColumnName);
		if(!isNullOrBlank(strValidationMsg))
		{
			
		}
		else
		{
			strValidationMsg ="";
		}
		return strValidationMsg;
	}
	
	public String getRandomString() 
	{
        String strNumber = "1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 4)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * strNumber.length());
            sb.append(strNumber.charAt(index));
        }
        String saltStr = sb.toString();
        
        return saltStr;

    }
	
	public boolean isHyperlinkPresent(WebElement we){
		boolean bFlag ;

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		bFlag = objAdjunoUILibrary.isElementPresent(we);

		we.click();
		return bFlag;
      	
	}
	
	public boolean isGridElementPresent(String strFormName, String strGridControlName,int nRow, String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag; 
		WebElement we;
		
		we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		if(we.isDisplayed())
		{
			bFlag = true;
		}
		else
		{
			bFlag = false;
		}
		return bFlag;
	}
	
	public void closeWindow1()
	{
		
		  String parentHandle = driver.getWindowHandle();
	       

	        // click

	        getHyperLnkTariff().click();

	        for (String winHandle : driver.getWindowHandles()) {
	           
	            driver.switchTo().window(winHandle);
	           
	        }

	        driver.close();
	        driver.switchTo().window(parentHandle);
		
	}
	
	

	
	
	
	
	
	
    
	
}
