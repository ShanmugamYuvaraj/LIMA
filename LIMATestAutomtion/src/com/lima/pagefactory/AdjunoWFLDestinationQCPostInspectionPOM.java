package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lima.dto.DestinationQCPostInspection;
import com.lima.library.AdjunoUILibrary;

public class AdjunoWFLDestinationQCPostInspectionPOM {
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	String strCount;
	String strCountPage;
	ArrayList<String> comboBoxValue;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Destination QC (Post-Inspection)") WebElement lnkDestinationQCPostInspection;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Lighthouse") WebElement lnklighthouse;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[@id='SUT_StageBar_PostInspection']") WebElement chvPostInspectionChecks;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete;
	@FindBy(xpath=".//input[@value='Refine Search']") WebElement chvRefineSearch;
	@FindBy(xpath=".//input[@value='Search Again']") WebElement chvSearchAgain;
	
	@FindBy(xpath=".//*[text()='Select']") WebElement chvSelect1;
	
	@FindBy(xpath="//a[text()='Sign Out']") WebElement lnkLogout;
	
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement catalogbtnPagePrev;
	
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]") List<WebElement> description;
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;

	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[4]") List<WebElement> lstMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[1]") List<WebElement> lstVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td/font") List<WebElement> lstStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr/td") List<WebElement> lstContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[2]") List<WebElement> lstHouseBill;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[3]") List<WebElement> lstVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[5]")  List<WebElement> lstProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div[2]/table/tbody/tr/td[4]") List<WebElement> lstWFLRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div[2]/table/tbody/tr/td[9]") List<WebElement> lstCertificateType;
	
	@FindBy(xpath=".//*[@id='ext-gen18']") WebElement pageCustomerValue;
	
	@FindBy(xpath="//p[text()='No items were found.']") WebElement strValidationMsg;
	@FindBy(xpath=".//*[@class=' Warnings ValidationFailures']/p") WebElement strGridValidationMsg;
	@FindBy(xpath=".//*[@name='ProgressForm_UpdateMessage']") WebElement strGridCompleteValidationMsg;
	
	@FindBy(xpath =".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[1]/th[1]/input") List<WebElement> lstCheckBox;
	
	@FindBy(xpath=".//*[@name='LH_PurchaseOrder_OriginPort']") WebElement lstOriginPortValue;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr/td[4]/a") WebElement lnkWFLReference;
	@FindBy(xpath="//span[text()='Product Card']") WebElement lnkProductCard;
	@FindBy(xpath=".//*[@id='extId_LH_PurchaseOrder_CustomerReference']") WebElement orderNumberValue;
	
	@FindBy(xpath="//div[@class='x-grid3-cell-inner x-grid3-col-Outcome']") WebElement clkInspectionOutcomeComboBox;
	@FindBy(xpath="//div[@class='x-grid3-cell-inner x-grid3-col-Disposal']") WebElement clkDisposalComboBox;
	@FindBy(xpath="//div[@class='x-grid3-cell-inner x-grid3-col-DisposalAgent']") WebElement clkDisposalAgentComboBox;
	
	@FindBy(xpath="(.//*[@class='x-combo-list-inner'])[1]/div") List<WebElement> lstInspectionOutcomeDropDownValues;
	@FindBy(xpath="(.//*[@class='x-combo-list-inner'])[2]/div") List<WebElement> lstDisposalDropDownValues;
	@FindBy(xpath=".//*[@onload='AutoFocus();']/div[15]/div[1]/div")List<WebElement> lstDisposalAgentDropDownValues;
		
	@FindBy(xpath=".//*[@id='CatalogLOCODE_IsOrigin']") WebElement isCheckboxClicked;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List<WebElement> lstItemDescription;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_CatalogItemDescription']") WebElement lstDescriptionField;
	@FindBy(xpath=".//*[@id='ext-gen60']/div/table/tbody/tr/td[1]/div") List<WebElement> lstTypeDescription;
	@FindBy(xpath=".//*[@id='CatalogSupplierContact_CatalogItemDescription']") WebElement lstSupplierDescriptionField;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr/td[2]/a") List<WebElement> lstDescriptionSupplier;
	@FindBy(xpath =".//*[@id='ext-gen60']/div/table/tbody/tr/td/div/a")List<WebElement> lstNameDescription;
	
	@FindBy(xpath = ".//*[@id='extId_LIMA_Shipment_SUT_DestinationPostInspection_UPDATE_Outcome']/following-sibling::img")WebElement clkInspectionOutcomeArrow;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Shipment_SUT_DestinationPostInspection_UPDATE_Disposal']/following-sibling::img")WebElement clkDisposalArrow;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Shipment_SUT_DestinationPostInspection_UPDATE_DisposalAgent']/following-sibling::img")WebElement clkDisposalAgentArrow;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> lstETAFromDate;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]")List<WebElement> lstETAToDate;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]")WebElement strMode;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td")WebElement strContainer;
	
	public List<WebElement> getLstETAFromDate() {
		return lstETAFromDate;
	}

	public List<WebElement> getLstETAToDate() {
		return lstETAToDate;
	}

	
	public WebElement getClkDisposalArrow() {
		return clkDisposalArrow;
	}

	public WebElement getClkDisposalAgentArrow() {
		return clkDisposalAgentArrow;
	}

	public WebElement getClkInspectionOutcomeArrow() {
		return clkInspectionOutcomeArrow;
	}

	public List<WebElement> getLstNameDescription() {
		return lstNameDescription;
	}

	public List<WebElement> getLstDescriptionSupplier() {
		return lstDescriptionSupplier;
	}

	public WebElement getLstSupplierDescriptionField() {
		return lstSupplierDescriptionField;
	}

	public WebElement getStrMode() {
		return strMode;
	}

	public WebElement getStrContainer() {
		return strContainer;
	}

	public List<WebElement> getLstTypeDescription() {
		return lstTypeDescription;
	}
	
	public WebElement getcatalogbtnPageNext(){
		return catalogbtnPageNext;
	}
	
	public WebElement getLstDescriptionField() {
		return lstDescriptionField;
	}

	public List<WebElement> getLstItemDescription() {
		return lstItemDescription;
	}

	public WebElement getIsCheckboxClicked() {
		return isCheckboxClicked;
	}
	
	public List<WebElement> getLstCheckBox() {
		return lstCheckBox;
	}
			
	public WebElement getStrGridCompleteValidationMsg() {
		return strGridCompleteValidationMsg;
	}

	public WebElement getStrGridValidationMsg() {
		return strGridValidationMsg;
	}

	public List<WebElement> getLstDisposalAgentDropDownValues() {
		return lstDisposalAgentDropDownValues;
	}

	public WebElement getClkInspectionOutcomeComboBox() {
		return clkInspectionOutcomeComboBox;
	}

	public WebElement getClkDisposalComboBox() {
		return clkDisposalComboBox;
	}

	public WebElement getClkDisposalAgentComboBox() {
		return clkDisposalAgentComboBox;
	}

	public WebElement getOrderNumberValue() {
		return orderNumberValue;
	}
		
	public List<WebElement> getLstDisposalDropDownValues() {
		return lstDisposalDropDownValues;
	}

	public List<WebElement> getInspectionOutcomeDropDownValues() {
		return lstInspectionOutcomeDropDownValues;
	}

	public WebElement getLnkProductCard() {
		return lnkProductCard;
	}
	
	public WebElement getLnkWFLReference() {
		return lnkWFLReference;
	}

	public WebElement getStrValidationMsg() {
		return strValidationMsg;
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}
	
	public WebElement getLnkTools() {
		return lnkTools;
	}
	
	public WebElement getLnkDestinationQCPostInspection() {
		return lnkDestinationQCPostInspection;
	}
	
	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}
	
	public WebElement getLnklighthouse() {
		return lnklighthouse;
	}
	
	public WebElement getChvSearch() {
		return chvSearch;
	}
	
	public WebElement getChvSelect() {
		return chvSelect;
	}
	
	public WebElement getChvPostInspectionChecks() {
		return chvPostInspectionChecks;
	}
	
	public WebElement getChvComplete() {
		return chvComplete;
	}
	
	public WebElement getChvRefineSearch() {
		return chvRefineSearch;
	}
	
	public WebElement getChvSearchAgain() {
		return chvSearchAgain;
	}
	
	public WebElement getChvSelect1() {
		return chvSelect1;
	}
	
	public WebElement getLnkLogout() {
		return lnkLogout;
	}
	
	public WebElement getCatalogbtnApply() {
		return catalogbtnApply;
	}
	
	public WebElement getCatalogbtnPageNext() {
		return catalogbtnPageNext;
	}
	
	public WebElement getCatalogbtnPagePrev() {
		return catalogbtnPagePrev;
	}
	
	public List<WebElement> getDescription() {
		return description;
	}
	
	public WebElement getTxtCount() {
		return txtCount;
	}
	public List<WebElement> getLstDescription() {
		return lstDescription;
	}
	
	public List<WebElement> getLstMode() {
		return lstMode;
	}
	
	public List<WebElement> getLstVessel() {
		return lstVessel;
	}
	
	public List<WebElement> getLstStatus() {
		return lstStatus;
	}
	
	public List<WebElement> getLstContainer() {
		return lstContainer;
	}
	
	public List<WebElement> getLstHouseBill() {
		return lstHouseBill;
	}
	
	public List<WebElement> getLstVendor() {
		return lstVendor;
	}
	
	public List<WebElement> getLstProduct() {
		return lstProduct;
	}
	
	public List<WebElement> getLstWFLRef() {
		return lstWFLRef;
	}
	
	public List<WebElement> getLstCertificateType() {
		return lstCertificateType;
	}
	
	public WebElement getLstOriginPortValue() {
		return lstOriginPortValue;
	}
	
	public WebElement getPageCustomerValue() {
		return pageCustomerValue;
	}
	
	public AdjunoWFLDestinationQCPostInspectionPOM(WebDriver driver) {
		this.driver = driver;

		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}	
	
	/* clear the data which is present in textbox */

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


	/* move the mouse cursor to expected element */
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
	

	/* set the value to text field */
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
	
	
	/* get combobox value */
	public ArrayList<String> getDropdownValues(String strFormName,String strControlName){

	    objAdjunoUILibrary = new AdjunoUILibrary(driver);
		ArrayList<String> dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;			
	}
	
	
	/*get the catalog data in table */
	public ArrayList<String> getCatalogData() 
	{
		ArrayList<String> supplierList =new ArrayList<String>();
		for (int i = 0; i < description.size(); i++) 
		{
			supplierList.add(description.get(i).getText());
	
		}
		
		return supplierList;
	}
	
	
	/*compare the catalog values */
	public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
        String strMessage = "";
        for (int i = 0; i < dropDownListList.size()-1; i++) {
            
        	for (int j = 0; j < catalogList.size()-1; j++) {
            	
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
	
	/*compare the catalog and List of dropdown values */
	public String verifyCatalogAndGridDropdownData(ArrayList<String> catalogList,List<String> dropDownListList) {
        String strMessage = "";
        for (int i = 1; i < dropDownListList.size(); i++) {
            
        	for (int j = 0; j < catalogList.size()-1; j++) {
            	
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
	
	/* count the number of pages available in catalog */
	 public int valCount()
	 {
		 strCount = getTxtCount().getText();
		 String[] vals = strCount.split("of ");
		 int nCount = Integer.parseInt(vals[1]);
	    
		 nCount= nCount/50;
		 if(nCount%50 == 0) {
		 }else {
			 nCount++;
		 }
	    	  
		 return nCount;  
	 }
	 
	   
	 public ArrayList<String> getCatalogTableData(int nCount) {
		 ArrayList<String> supplierList =new ArrayList<String>();
	     for(int j=0;j<=nCount-1;j++)
	     {
	     	for (int i = 0; i < lstDescription.size(); i++) 
	     	{
	     		supplierList.add(lstDescription.get(i).getText());
	       		
	     	}
	        	
	     	getCatalogbtnPageNext().click();
	        
	     }
	     return supplierList;
	 }
	 
	 /* click button using webelement */
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
	 
	 
	 /* get the data in search list table */
	 public String verifySearchValue(String strValue, List<WebElement> wbList) {
		 String strMessage = "";
		 for (int i = 0; i <= wbList.size()-1; i++) {
			
			 if(!wbList.get(i).getText().isEmpty() && wbList.get(i).getText().equalsIgnoreCase(strValue)){
				System.out.println(wbList.get(i).getText() +" is matching with "+strValue);
			 }else{
				 if(wbList.size()==0){
					 strMessage = strMessage + wbList.get(i).getText()+ "value is not matched For"+ strValue;
				 }
			 }
		 }
		 return strMessage;
	 }
	
	 /* Verify the Page is Loaded or not */
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
	 
	 public void wait(int ms)
	 {
	  	try 
	   	{
	   		Thread.sleep(ms);
	   	} catch (InterruptedException e) {	
	   		e.printStackTrace();
	   	}
	 }
	 
	 private boolean isNullOrBlank(String s){
		 return (s==null || s.trim().equals(""));
	 }
	 
	 /* count no of rows in grid */
	 public int getNoOfRowsResulted() {
		       
		 return getLstCheckBox().size();
	 }
	 
		
		
	 public ArrayList<DestinationQCPostInspection> selectMulitpleProducts(int nMaxProductsToSelect)
	 {
		 	int nCounterLen = getNoOfRowsResulted();
	        ArrayList<DestinationQCPostInspection> lstSearchResults = new ArrayList<DestinationQCPostInspection>();
	       
	        if (nCounterLen < nMaxProductsToSelect){
	        	
	        }
	        else 
	        	nCounterLen = nMaxProductsToSelect;
	            
	        for(int i = 0; i <= nCounterLen - 1; i++)
	        {          
	        	DestinationQCPostInspection dqc = new DestinationQCPostInspection(getLstHouseBill().get(i).getText(), getLstCertificateType().get(i).getText(), getLstContainer().get(i).getText());
	            wait(2000);
	        	getLstCheckBox().get(i).click();
	        	lstSearchResults.add(dqc);	
	        }
	        return lstSearchResults;
	 }
	 
	 public boolean clickChevron(WebElement we){
			boolean bFlag;		
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
	 
	//logout 
	public boolean clickLogout(WebElement we){
		boolean bFlag;		
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
	
	//get the window
	public String getWindowIds(){
		String winIds = null;
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		if(set.size()==2){
			String parentWinID = it.next();
			String childWinID =it.next();
					
			winIds =parentWinID +";"+ childWinID;
		}
		if(set.size()==3){
			String parentWinID = it.next();
			String childWinID =it.next();
			String childWinID2 =it.next();
					
			winIds =parentWinID +";" +childWinID +";"+ childWinID2;
		}
		return winIds;
					
	}
	
	public String getFieldValue(String strformName, String strControlName) {
		String tempVal = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getElemetValue2(strformName, strControlName);
		if(!isNullOrBlank(tempVal)){
				
		}else{
			tempVal ="";
		}
			
		return tempVal;				
	}
	
	 public String compareFromDates(String datevalue1,List<WebElement> datevalue2) throws ParseException{
		 String strMessage = "";
		 SimpleDateFormat simpledateformat = new SimpleDateFormat("dd MMM yyyy");

		 for (int i = 0; i <= datevalue2.size() - 1; i++) {
			String date1 = datevalue2.get(i).getText();
			if(simpledateformat.parse(date1).equals(simpledateformat.parse(datevalue1))||simpledateformat.parse(date1).after(simpledateformat.parse(datevalue1))){
			
			}else {
				strMessage = strMessage+"   In Destination QC Post-Inspection Tool ETA from Date is :"+ datevalue1+" this And expected results should be from this date, But Actually value"+date1;
				
			}
			
		 }
		return strMessage;
		 
	 }
	 
	 public String compareToDates(String datevalue1,List<WebElement> datevalue2) throws ParseException{
		 String strMessage = "";
		 SimpleDateFormat simpledateformat = new SimpleDateFormat("dd MMM yyyy");

		 for (int i = 0; i <= datevalue2.size() - 1; i++) {
			String date1 = datevalue2.get(i).getText();
			if(simpledateformat.parse(date1).equals(simpledateformat.parse(datevalue1))||simpledateformat.parse(date1).before(simpledateformat.parse(datevalue1))){
			//	System.out.println(date1 +"is Comming Before "+ datevalue1);

			}else {
				strMessage = strMessage+ "  In Destination QC Post-Inspection Tool ETA To Date is :"+ datevalue1 +" this And expected results should be before this date, But Actually value"+date1;
			}
		 
		 }
		return strMessage;
		 
	 }
	
	public boolean compareTwoStringValuesToSame(String strValue1, String strValue2){
		boolean bFlag;
		
		if ((!(isNullOrBlank(strValue1))) &&  (!(isNullOrBlank(strValue2)))){                
			if (strValue1.equalsIgnoreCase(strValue2)){
				bFlag = true;
			}else{
				bFlag = false;
			}
		}else{
			bFlag = false;
		}
     
		return bFlag;	
	}
	
	//get the Grid cell value
	public String getGridCellValue(String strFormName, String strGridControlName, int nRow , String strColumnName){
	
		objAdjunoUILibrary=new AdjunoUILibrary(driver);
			
		String value=objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, nRow, strColumnName);

		return value;
			
	}
		
	//get the grid drop down value
	public ArrayList<String>  getGridDropdownvalues(List<WebElement> we) {
		 
		ArrayList<String> dropdownList = new ArrayList<String>();
		for (int i = 0; i <= we.size() - 1; i++) {
			//System.out.println(we.size());
			dropdownList.add(we.get(i).getText());

		}
		return dropdownList;
	        
	}
	 
	 //
	 public void getGridCellElement(String strFormName, String strGridControlName, int nRow, String strColumnName){
		 
		 objAdjunoUILibrary=new AdjunoUILibrary(driver);
		 objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		 
	 }
	 
	//set the Grid cell value
	 public boolean setFieldValueForGridCell(String strFormName,String strGridControlName, int nRow, String strColumnName,String strValue) {
			boolean bFlag;
			objAdjunoUILibrary = new AdjunoUILibrary(driver);

			try {
				objAdjunoUILibrary.setGridCellValue(strFormName,strGridControlName, nRow, strColumnName, strValue);
				WebElement we;
				
			we = objAdjunoUILibrary.getGridCellElementEditor(strFormName,strGridControlName, nRow, strColumnName);
				
				we.sendKeys(Keys.TAB);
				
				bFlag = true;
			} catch (NoSuchElementException e) {
				bFlag = false;
			} catch (NullPointerException e) {
				bFlag = false;
			}
			return bFlag;
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
	
	//check the element present or not
	public boolean checkDoesChevronExist(WebElement chevron) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(chevron);
          
		return bFlag;
	}
	
	public boolean checkDoesElementExist(String formName,String controlName) {        
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
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
	
	//get the validation message other then Grid
	public String getValidationMessageOtherthenField(WebElement we){
	
		String validationMs =we.getText();	
		
		return validationMs;
		
	}
	
	// get getElementValueInReadMode
	public String getElementValueInReadMode(String strID){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	    
	     String strValidationMsg = objAdjunoUILibrary.getElementValueInReadMode(strID);
	     if(!isNullOrBlank(strValidationMsg))
	     {
	            
	     }
	     else
	     {
	    	 strValidationMsg ="";
	     }
	    
		
		return strValidationMsg;
		
	}
	
	public String getElemetValue(String strFormName, String strControlName){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	    
	     String strValidationMsg = objAdjunoUILibrary.getElemetValue(strFormName, strControlName);
	     if(!isNullOrBlank(strValidationMsg))
	     {
	            
	     }
	     else
	     {
	    	 strValidationMsg ="";
	     }
	    
		
		return strValidationMsg;
		
	}
	
	//Compare the values and find the duplicate values
	public String duplicateValue(List<WebElement> wbList){
		String strMessage ="";
	   	 for(int i=0;i<wbList.size();i++){
	   	 	for(int j=i+1;j<wbList.size();j++){
	   	 		if(!wbList.get(i).getText().isEmpty() && wbList.get(i).getText().equalsIgnoreCase(wbList.get(j).getText())){
	   				 
	   	 		}else{
	   	 			if(wbList.size()==0){
	   	 				strMessage= strMessage + wbList.get(i).getText()+ "value is not matched For"+ wbList.get(j).getText();
	   	 			}
	   	 		}
	   			 
	   	 	}
	   	 }
	   	 return strMessage;
	}
	
	//check grid value is read only
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
	
	
	 public ArrayList<String> getCatalogTableData1(int nCount) //5
	    {
		 boolean bFlag;
	     ArrayList<String> supplierList =new ArrayList<String>();
	     for(int j=0;j<=nCount-1;j++)
	     {
	     	for (int i=0; i < lstItemDescription.size(); i++){
	     		lstItemDescription.get(i).click();
	     		bFlag = isCheckboxClicked.isSelected();
	     	
	     		if(bFlag){
	     			supplierList.add(lstDescriptionField.getText());     				
	     			driver.navigate().back();	
	     		}else{
	     			driver.navigate().back();	
	     		}
	     			
	     	}
	   	        	
	     	getcatalogbtnPageNext().click();
	        
	     }

	        return supplierList;
	    }
	 
	 
	 public ArrayList<String> getCatalogSupplierTableData()
	    {
	
	     ArrayList<String> supplierList =new ArrayList<String>();
	     for (int i=0; i < lstItemDescription.size(); i++)
	     	{
	     		lstItemDescription.get(i).click();
	 
	     		for (int k=0; k< lstTypeDescription.size(); k++ ){
	     			
	     			if(lstTypeDescription.get(k).getText().equalsIgnoreCase("Customer")){
	     				supplierList.add(lstSupplierDescriptionField.getText());   
	     				break;
	     			}else{
	     				break;
	     			}
	     			
	     		}
  			  				
  			driver.navigate().back();	
	     	}
	        return supplierList;
	    }
	 
	 public ArrayList<String> getCatalogTableData(List<WebElement> lstNameDescription,WebElement lstSupplierDescriptionField) {

			ArrayList<String> supplierList = new ArrayList<String>();
			for (int i = 0; i < lstDescriptionSupplier.size(); i++) {
				lstDescriptionSupplier.get(i).click();

				for (int k = 0; k < lstNameDescription.size(); k++) {

					if (lstNameDescription.get(k).getText().equalsIgnoreCase("MVPCust02")) {
						supplierList.add(lstSupplierDescriptionField.getText());
						break;
					}
				}

				driver.navigate().back();
			}
			return supplierList;
		}
	 
	

}
