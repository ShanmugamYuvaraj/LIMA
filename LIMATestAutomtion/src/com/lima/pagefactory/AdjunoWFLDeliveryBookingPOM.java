package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lima.library.AdjunoUILibrary;

public class AdjunoWFLDeliveryBookingPOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
		
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Delivery Booking") WebElement lnkDeliveryBooking;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Lighthouse") WebElement lnkLightHouse;
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]") List<WebElement> description;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement catalogbtnPagePrev;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_IsDestination']") WebElement isCheckboxClicked;
    @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List<WebElement> lstItemDescription;
    @FindBy(xpath=".//*[@id='CatalogLOCODE_CatalogItemDescription']") WebElement lstDescriptionField;
    @FindBy(xpath="//a[text()='Sign Out']") WebElement lnkLogout;
    @FindBy(linkText="Configuration") WebElement lnkConfiguration;
    @FindBy(linkText="ReasonCodes") WebElement lnkReasonCode;
	@FindBy(xpath=".//*[@value='Complete']") WebElement chvComplete2;
	@FindBy(xpath=".//*[@id='extId_ReasonCodeCatalog_Tabs__id_extReasonCodeCatalog_DeliveryBooking_Tab']") WebElement deliveryTab2;
	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_TabPanel_Configuration__id_extCatalog_LIMA_Configuration_TabItem_Delivery']") WebElement deliveryTab;

	@FindBy(xpath = ".//*[@class='x-grid3-col x-grid3-cell x-grid3-td-DeliveryMethod ']/div")WebElement deliveryMethod;
	@FindBy(xpath = ".//*[@class='x-grid3-col x-grid3-cell x-grid3-td-OrderHaulier ']/div")WebElement haulier;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-OrderLineTypeName']")WebElement linetype;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-OrderConsigneeName']")WebElement consignee;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-OrderWarehouseName']")WebElement warehouse;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-OrderBookingReason']")WebElement bookingReasion;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-SplitDelivery']")WebElement splitDelivery;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-SplitDeliveryMethod']")WebElement splitDeliveryMethod;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-SplitHaulier']")WebElement splitHaulier;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-SplitLineTypeName']")WebElement splitLineType;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-SplitConsigneeName']")WebElement splitConsignee;

	@FindBy(xpath=".//*[@id='extId_Catalog_LIMA_Configuration_TabPanel_Delivery_Settings__id_extCatalog_LIMA_Configuration_TabItem_DeliveryBooking']/a[2]") WebElement deliveryBookingTab;
    @FindBy(xpath=".//*[text()='No items were found.']") WebElement noItem;
    @FindBy(xpath=".//label[contains(@id,'Catalog_LIMA_Configuration_LABEL_DeliveryBooking_BookBy')]") List<WebElement> lstLevel;
    @FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr/td[7]/a") WebElement clkWflRef;
    @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[6]") List<WebElement> lstDestnationPort; 
    @FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[4]") List<WebElement> lstDC;
    @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[3]") List<WebElement> lstContainer;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[7]/a") List<WebElement> lstWflRef;
    @FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[7]")List<WebElement> lstWflRef2;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[8]") List<WebElement> lstIdentifier;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[9]") List<WebElement> lstProduct;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[10]") List<WebElement> lstSku;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[11]") List<WebElement> lstSplitId;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[17]") List<WebElement> lstStatus;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr/td[4]/font") List<WebElement> lstWarehouse;
    @FindBy(xpath="//*[@type='CHECKBOX']") List<WebElement> lstSelect;
    @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[1]") List<WebElement> lstModeValue;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[2]") List<WebElement> lstBookingRef;
    @FindBy(xpath="//div[@id='extId_ReasonCodeCatalog_GRID_DeliveryBookingReasons']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div/table/tbody/tr/td[2]/div") List<WebElement> lstReasonCode;
    @FindBy(xpath="(.//*[@class='x-combo-list-inner'])[4]//div[@class='x-combo-list-item']") List<WebElement> lstBookingReason;
    @FindBy(xpath="//div[contains(@class, 'col-OpenProgressQuantity')]") WebElement balanceCtn;    
    @FindBy(xpath="(.//*[@class='x-combo-list-inner'])/div[@class='x-combo-list-item']") List<WebElement> lstLineType;
    @FindBy(xpath="(.//*[@class='x-combo-list-inner'])/div[@class='x-combo-list-item']") List<WebElement> lstConsignee;
    
    public List<WebElement> getLstLineType() {
		return lstLineType;
	}
    
    public WebElement getChvComplete2() {
		return chvComplete2;
	}
  
	public WebElement getSplitLineType() {
		return splitLineType;
	}

	public WebElement getSplitDeliveryMethod() {
		return splitDeliveryMethod;
	}

	public List<WebElement> getLstDescription() {
		return lstDescription;
	}
 
	public WebElement getSplitHaulier() {
		return splitHaulier;
	}

	public WebElement getSplitDelivery() {
		return splitDelivery;
	}

	public WebElement getDeliveryMethod() {
		return deliveryMethod;
	}


	public WebElement getLinetype() {
		return linetype;
	}
	
	public WebElement getSplitConsignee() {
		return splitConsignee;
	}

	public WebElement getConsignee() {
		return consignee;
	}

	public WebElement getWarehouse() {
		return warehouse;
	}

	public WebElement getBookingReasion() {
		return bookingReasion;
	}
	public WebElement getHaulier() {
		return haulier;
	}

	public List<WebElement> getLstConsignee() {
		return lstConsignee;
	}

	public WebElement getBalanceCtn() {
		return balanceCtn;
	}
	
	public List<WebElement> getLstBookingReason() {
		return lstBookingReason;
	}

	public List<WebElement> getLstReasonCode() {
		return lstReasonCode;
	}

	public List<WebElement> getLstBookingRef() {
		return lstBookingRef;
	}
        
    public List<WebElement> getLstStatus() {
		return lstStatus;
	}
    
    public List<WebElement> getLstWarehouse() {
		return lstWarehouse;
	}
    
    public List<WebElement> getLstSelect() {
		return lstSelect;
	}
    
    public List<WebElement> getLstContainer() {
		return lstContainer;
	}
    
    public List<WebElement> getLstProduct() {
		return lstProduct;
	}
    
    public List<WebElement> getLstWflRef() {
		return lstWflRef;
	}
    
    public List<WebElement> getLstWflRef2() {
		return lstWflRef2;
	}
    
    public List<WebElement> getLstIdentifier() {
		return lstIdentifier;
	}
    
    public List<WebElement> getLstSku() {
		return lstSku;
	}
    
    public List<WebElement> getLstSplitId() {
		return lstSplitId;
	}
    
    public List<WebElement> getLstDC() {
		return lstDC;
	}
    
    public List<WebElement> getLstDestnationPort() {
		return lstDestnationPort;
	}
    
    public WebElement getChvSelect() {
		return chvSelect;
	}
    
    public WebElement getNoItem() {
		return noItem;
	}
    
    public List<WebElement> getLstLevel() {
		return lstLevel;
	}

	public WebElement getDeliveryTab() {
		return deliveryTab;
	}
	
	public WebElement getDeliveryTab2() {
		return deliveryTab2;
	}

	public WebElement getDeliveryBookingTab() {
		return deliveryBookingTab;
	}

	public WebElement getLnkReasonCode() {
		return lnkReasonCode;
	}
	
	public WebElement getLnkConfiguration() {
		return lnkConfiguration;
	}

	public WebElement getLnkLogout() {
		return lnkLogout;
	}

	public WebElement getCatalogbtnPageNext() {
		return catalogbtnPageNext;
	}

	public WebElement getCatalogbtnPagePrev() {
		return catalogbtnPagePrev;
	}

	public WebElement getIsCheckboxClicked() {
		return isCheckboxClicked;
	}

	public List<WebElement> getLstItemDescription() {
		return lstItemDescription;
	}

	public WebElement getLstDescriptionField() {
		return lstDescriptionField;
	}

	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
	}
	
	public WebElement getcatalogbtnPagePrev(){
		return catalogbtnPagePrev;
	}
	
	public WebElement getcatalogbtnApply(){
		return catalogbtnApply;
	}
	
	public WebElement getcatalogbtnPageNext(){
		return catalogbtnPageNext;
	}
	
	public WebElement getTxtCount() {
		return txtCount;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkDeliveryBooking() {
		return lnkDeliveryBooking;
	}
	
	public WebElement getClkWflRef() {
		return clkWflRef;
	}
	
	public List<WebElement> getLstModeValue() {
		return lstModeValue;
	}	

	public AdjunoWFLDeliveryBookingPOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

    }
	
    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    public boolean clearInputField(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try
        {
			bFlag = we.isDisplayed();
			we.clear();
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
    
    public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink) {
		Actions action = new Actions(driver);

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
    
    
    public boolean checkForIsPageLoaded(String strFormName){
    	
    	objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	
		return objAdjunoUILibrary.isPageLoaded(strFormName);
		 
    }
    
    public boolean clickButtonUsingWebElement(WebElement we) 
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		try
        {
			bFlag = we.isDisplayed();
			wait(1000);
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
    
    public boolean setFieldValue(String strFieldValue, String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
		try
        {
			bFlag = we.isDisplayed();
			we.sendKeys(strFieldValue);	
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
    
    public ArrayList<String> getCatalogData() 
	{
		ArrayList<String> list =new ArrayList<String>();
		for (int i = 0; i < description.size(); i++) 
		{
			list.add(description.get(i).getText());
	
		}
		
		return list;
	}
    
    public int valCount()
	{
		 String strCount = getTxtCount().getText();
		 String[] vals = strCount.split("of ");
		 int nCount = Integer.parseInt(vals[1]);
	    
		 nCount= nCount/50;
		 if(nCount%50 == 0) {
		 }else {
			 nCount++;
		 }
	    	  
		 return nCount;  
	}
    
    public ArrayList<String> getCatalogTableData(int nCount) 
    {
     	ArrayList<String> supplierList =new ArrayList<String>();
     	for(int j=0;j<=nCount-1;j++)
     	{
     		for (int i = 0; i < lstDescription.size(); i++) 
     		{
     			supplierList.add(lstDescription.get(i).getText());
        		
     		}
        	
     		getcatalogbtnPageNext().click();
        
     	}
        
        return supplierList;
    }
    
    public String getWindowIds() {
		String winIds = null;
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();
		if (set.size() == 2) {
			String parentWinID = it.next();
			String childWinID = it.next();

			winIds = parentWinID + ";" + childWinID;
		}
		if (set.size() == 3) {
			String parentWinID = it.next();
			String childWinID = it.next();
			String childWinID2 = it.next();

			winIds = parentWinID + ";" + childWinID + ";" + childWinID2;
		}
		return winIds;

	}

    
    public ArrayList<String> getDropdownValues(String strFormName,String strControlName){

	    objAdjunoUILibrary = new AdjunoUILibrary(driver);
	    ArrayList<String> dropDownList = new ArrayList<String>();
		dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;			
	}
	
	public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
        String strMessage = "";
        for (int i = 0; i <= dropDownListList.size()-1; i++) {
            
        	for (int j = 0; j <= catalogList.size()-1; j++) {
            	
        		if(dropDownListList.get(i).equalsIgnoreCase(catalogList.get(j))){
        			break;
        		}else{
        			if(j==catalogList.size()){
        				strMessage = strMessage + dropDownListList.get(i)+" value is not found in catalog ";    
        			}
        		}
        	}
        }
        
        return strMessage;
	}
	
	public ArrayList<String> getCatalogTableData1(int nCount) {
		boolean bFlag;
		ArrayList<String> supplierList = new ArrayList<String>();
		for (int j = 0; j <= nCount - 1; j++) {
			for (int i = 0; i <= lstItemDescription.size()-1; i++) {
				lstItemDescription.get(i).click();
				bFlag = isCheckboxClicked.isSelected();
				if (bFlag) {
					supplierList.add(lstDescriptionField.getText());
					driver.navigate().back();
				} else {
					driver.navigate().back();
				}

			}

			getcatalogbtnPageNext().click();

		}

		return supplierList;
	}

	public ArrayList<String> getDeliveryBookingLevels() {
		ArrayList<String> levelList = new ArrayList<String>();
		for (int i = 0; i <= getLstLevel().size()-1; i++) {
			levelList.add(getLstLevel().get(i).getText());
		}
		System.out.println("size:"+levelList.size());
		return levelList;
	}
	
	public boolean isElementPresent(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(we);
          
		return bFlag;
	}
	
	public boolean clickChevorn(WebElement we) {
		boolean bFlag;
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
	
	public boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}
	
	public boolean clickButton(String formName,String controlName) 
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
		try
        {
			bFlag = we.isDisplayed();
			we.click();
			wait(3000);
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
	
	public String verifyValue(String strValue,List<WebElement> weList) {
		String strMessage = "";
		
		for (int i = 0; i <=weList.size()-1; i++) {
			if(!isNullOrBlank(weList.get(i).getText()) && weList.get(i).getText().equalsIgnoreCase(strValue)){
				System.out.println(weList.get(i).getText() +" is matching "+ strValue);
			}else{
				if(i==weList.size()-1){
					strMessage = strMessage +  weList.get(i).getText() +" does not match with "+ strValue ;
				}
				
			}
		}
		
		return strMessage;
	}
	
	
	public String verifyValue2(String strValue,String strColumnName,int nRow,boolean bFlag ) {
		String strMessage = "";
		
		if(bFlag==true){

			if(!isNullOrBlank(strValue)){
				
			}else{
				strMessage = strMessage+ strColumnName +" at this row "+ nRow +" is empty " ;
			}
		
		}else{

			if(isNullOrBlank(strValue)){
				
			}else{
				strMessage = strMessage +strColumnName +" at this row " +" is not empty " ;
			}
		
		}
				
		return strMessage;
	}
	
	public String getDate(int days,String pattern){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		 //dd MMM yyyy HH:mm
		 SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		 dateFormat.format( cal.getTime());
				
		return dateFormat.format( cal.getTime());			
	}
	
	public int getDifference(Date today, Date date) {
		
		if( date == null || today == null ) return 0;

	    return (int) ((today.getTime()/60000) - (date.getTime()/60000));
		
	}
	
	public String compareDate(Date ETA) throws ParseException{
		SimpleDateFormat simpledateformat = new SimpleDateFormat("dd MMM yyyy");
		String strBookingDate="";
		if(ETA.before(new Date()) ){
		//enter tomorrows date	
			strBookingDate = getDate(1, "dd MMM yyyy");
			System.out.println("tomorrowdate "+strBookingDate);
		}else if(ETA.after(new Date())){
			//enter next day of ETA
			int difference = getDifference(new Date(),ETA);
			strBookingDate =getDate(difference+2, "dd MMM yyyy");
			System.out.println("next to eta date "+strBookingDate);

		}else{
			//enter tomorrows date	
			strBookingDate =getDate(1, "dd MMM yyyy");
			System.out.println("tomorrowdate "+strBookingDate);

		}
		return strBookingDate;
	
		
		
		
	}
	
	
	public String getValidationMessageFieldElement(String strFormName,String strControlName) 
	{
		 objAdjunoUILibrary = new AdjunoUILibrary(driver);
		    
	     String strValidationMsg = objAdjunoUILibrary.getValidationMessageElement(strFormName, strControlName);
	     if(!isNullOrBlank(strValidationMsg))
	     {
	            
	     }
	     else
	     {
	    	 strValidationMsg ="";
	     }
	    
	     return strValidationMsg;
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
        	wait(1000);
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
	
	public boolean clearGridInputField(String strFormName,String strGridControlName, int nRow, String strColumnName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		try {
			WebElement we;

			we = objAdjunoUILibrary.getGridCellElementEditor(strFormName,strGridControlName, nRow, strColumnName);

			bFlag = we.isDisplayed();
			we.clear();
			we.sendKeys(Keys.TAB);

		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}
	
	public boolean clickGridField(String strFormName, String strGridControlName, int nRow, String strColumnName){
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		try
        {
        	we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strGridControlName, nRow, strColumnName);
        	we.click();
        	wait(1000);
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
	
	public ArrayList<String> getListOfValidationMessageGridElement(String strFormName,String strGridControlName, int nRow, String strColumnName)
    {
		ArrayList<String> lstErrorMessages = new ArrayList<String>();
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
    
        lstErrorMessages = objAdjunoUILibrary.getListOfValidationMessageGridElement(strFormName, strGridControlName, nRow, strColumnName);
        
        return lstErrorMessages;
    }

	public void selectProduct(List<WebElement> weList) {
		 
		for (int i = 0; i <= weList.size()-1; i++) {
			if(i==0){
				weList.get(i).click();
			}
		}
		
	}

	public String verifyWareHouse(List<WebElement> weList) {
		String strMessage ="";
		for (int i = 0; i <=weList.size()-1; i++) {
			if(!weList.get(i).getText().isEmpty()){
				String strTempColor=weList.get(i).getAttribute("color");
				if(strTempColor.equalsIgnoreCase("Gray")){
					
				}else{
					strMessage = strMessage + "This Line "+i+ " is not in Gray Color";
				}
			}
		}
		return strMessage;
	}
	
	public boolean checkDoesElementExist(String strFormName,String strControlName) {		
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		try
        {	
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
	
	
	
	public long getDifferenceDays(Date d1, Date d2) {
	    long diff = d2.getTime() - d1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}
	
	
	public String getGridCellElementValue(String strformName, String strControlName, int nRow, String strColonmName) {
		String tempVal;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getGridCellElementValue(strformName, strControlName,nRow,strColonmName);
		if (!isNullOrBlank(tempVal)) {

		} else {
			tempVal = "";
		}

       return tempVal;				
	}
	
	public String getWebElementValue(String strFormName, String strControlName){
		String strTemp;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		strTemp = objAdjunoUILibrary.getElemetValue(strFormName, strControlName);
		return strTemp;
	}
	
	public boolean checkFieldIsReadOnlyInGrid(String strFormName, String strControlName, int nRow, String strColumnName) {
		
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
	
	 public String getFieldValue(String strformName, String strControlName) {
			String tempVal = "";
			objAdjunoUILibrary = new AdjunoUILibrary(driver);

			tempVal = objAdjunoUILibrary.getElemetValue2(strformName,strControlName);
			if (!isNullOrBlank(tempVal)) {

			} else {
				tempVal = "";
			}

			return tempVal;
		}

	 public boolean compareTwoStringValuesToSame(String strValue1,String strValue2) {
			boolean bFlag = true;
			if ((!(isNullOrBlank(strValue1))) && (!(isNullOrBlank(strValue2)))) {
				if (strValue1.equalsIgnoreCase(strValue2)) {
					System.out.println(strValue1 + "is matching to " + strValue2);

				} else {
					bFlag = false;
				}
			} else {
				bFlag = false;
			}

			return bFlag;
		}
	 
	 
	 public String getGridCellValue(String strFormName,String strGridControlName, int nRow, String strColumnName) {

			objAdjunoUILibrary = new AdjunoUILibrary(driver);

			String value = objAdjunoUILibrary.getGridCellElementValue(strFormName,strGridControlName, nRow, strColumnName);

			return value;

		}


	public ArrayList<String> getReasonCode() {
		ArrayList<String> lstReasonCode = new ArrayList<String>();
		
		for (int i = 0; i <= getLstReasonCode().size()-1; i++) {
			lstReasonCode.add(getLstReasonCode().get(i).getText());
		}
		return lstReasonCode;
	}


	public ArrayList<String> getReasonCodeFromDropDown() {
		ArrayList<String> lstBookingReason =  new ArrayList<String>();
		for (int i = 0; i < getLstBookingReason().size(); i++) {
			lstBookingReason.add(getLstBookingReason().get(i).getText());
		} 
		return lstBookingReason;
	}

	public ArrayList<String> getListValue(List<WebElement> weLst) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i <= weLst.size()-1; i++) {
			list.add(weLst.get(i).getText());
		}
		return list;
	}
	
	public boolean getIsCheckBoxClcked(String strID) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		return objAdjunoUILibrary.getIsCheckBoxClcked(strID);
	}
}
