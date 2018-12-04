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

import com.lima.dto.DestinationQCPreInspection;
import com.lima.library.AdjunoUILibrary;

public class AdjunoWFLDestinationQCPreInspectionPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	String strCount;
	String strCountPage;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Destination QC (Pre-Inspection)") WebElement lnkDestinationQCPreInspection;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Lighthouse") WebElement lnklighthouse;
	
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='ProgressForm_FORK_PreInspSearch']") WebElement chvSearchAgain;
	@FindBy(xpath=".//*[@id='LIMA_Shipment_SUT_DestinationPreInspection_PARAM_FORK_PreInspection_RefineSearch']") WebElement clkRefineSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvcomplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_PreInspection']") WebElement chvPreInspection1;
	@FindBy(xpath="//span[text()='Product Card']") WebElement lnkProductCard;
	
	@FindBy(xpath="//a[text()='Sign Out']") WebElement lnkLogout;
		
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement catalogbtnPagePrev;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[4]/a") WebElement lnkWFLReference;
	@FindBy(xpath=".//*[@id='ext-gen43']/div[1]/table/tbody/tr/td[7]/div") WebElement orderNumberValue;
	
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	@FindBy(xpath =".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[1]/th[1]/input") List<WebElement> lstCheckBox;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[4]") List<WebElement> lstMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[1]") List<WebElement> lstVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td/font") List<WebElement> lstStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr/td") List<WebElement> lstContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[2]") List<WebElement> lstHouseBill;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[3]") List<WebElement> lstVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr[2]/td[5]")  List<WebElement> lstProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div[2]/table/tbody/tr/td[4]") List<WebElement> lstWFLRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[9]") List<WebElement> lstCertificateType;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/div[2]/table/tbody/tr/td[10]/font") List<WebElement> lstInspectionRequired;
	@FindBy(xpath=".//*[@id='LH_PurchaseOrder_View100_OriginPortDesc']") WebElement lstOriginPortValue;
	@FindBy(xpath=".//*[@id='ext-gen18']") WebElement pageCustomerValue;
	
	@FindBy(xpath=".//*[@id='CatalogLOCODE_IsOrigin']") WebElement isCheckboxClicked;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List<WebElement> lstItemDescription;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_CatalogItemDescription']") WebElement lstDescriptionField;
	@FindBy(xpath=".//*[@id='ext-gen60']/div/table/tbody/tr/td[1]/div") List<WebElement> lstTypeDescription;
	@FindBy(xpath=".//*[@id='CatalogSupplierContact_CatalogItemDescription']") WebElement lstSupplierDescriptionField;
	
	@FindBy(xpath="//p[text()='No items were found.']") WebElement strValidationMsg;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr/td[2]/a") List<WebElement> lstDescriptionSupplier;
	@FindBy(xpath =".//*[@id='ext-gen60']/div/table/tbody/tr/td/div/a")List<WebElement> lstNameDescription;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> lstETAFromDate;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]")List<WebElement> lstETAToDate;
	
	
	public List<WebElement> getLstETAFromDate() {
		return lstETAFromDate;
	}

	public List<WebElement> getLstETAToDate() {
		return lstETAToDate;
	}

	
	public WebElement getLstSupplierDescriptionField() {
		return lstSupplierDescriptionField;
	}
	
	public List<WebElement> getLstNameDescription() {
		return lstNameDescription;
	}
	
	public List<WebElement> getLstTypeDescription() {
		return lstTypeDescription;
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

	public WebElement getLstOriginPortValue() {
		return lstOriginPortValue;
	}

	public WebElement getOrderNumberValue() {
		return orderNumberValue;
	}

	public WebElement getLnkWFLReference() {
		return lnkWFLReference;
	}

	public WebElement getLnkProductCard() {
		return lnkProductCard;
	}

	public WebElement getPageCustomerValue() {
		return pageCustomerValue;
	}

	public WebElement getStrValidationMsg() {
		return strValidationMsg;
	}

	public List<WebElement> getLstInspectionRequired() {
		return lstInspectionRequired;
	}

	public WebElement getChvPreInspection1() {
		return chvPreInspection1;
	}

	public List<WebElement> getLstCertificateType() {
		return lstCertificateType;
	}
	
	public List<WebElement> getLstCheckBox() {
		return lstCheckBox;
	}
	
	public WebElement getChvSearchAgain() {
		return chvSearchAgain;
	}

	public WebElement getLnkLogout() {
		return lnkLogout;
	}

	public List<WebElement> getLstWFLRef() {
		return lstWFLRef;
	}
	
	public List<WebElement> getLstProduct() {
		return lstProduct;
	}

	public List<WebElement> getLstStatus() {
		return lstStatus;
	}
	
	public List<WebElement> getLstVendor() {
		return lstVendor;
	}


	public List<WebElement> getLstContainer() {
		return lstContainer;
	}

	public List<WebElement> getLstHouseBill() {
		return lstHouseBill;
	}
	
	public List<WebElement> getLstVessel() {
		return lstVessel;
	}

	
	public List<WebElement> getLstMode() {
		return lstMode;
	}

	
	public WebElement getlnkTools(){
		return lnkTools;
	}
	
  
	public WebElement getlnkDestinationQCPreInspection(){
	return lnkDestinationQCPreInspection;
	}
	
	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}
	

	public WebElement getLnklighthouse() {
		return lnklighthouse;
	}
	
	
	public WebElement getClkRefineSearch() {
		return clkRefineSearch;
	}

	
	public WebElement getchvSearch(){
		return chvSearch;
	}
	
	public WebElement getchvSelect(){
		return chvSelect;
	}
		
	public WebElement getchvcomplete(){
		return chvcomplete;
	}
	
	public WebElement getcatalogbtnApply(){
		return catalogbtnApply;
	}
	
	public WebElement getcatalogbtnPageNext(){
		return catalogbtnPageNext;
	}
	
	public WebElement getcatalogbtnPagePrev(){
		return catalogbtnPagePrev;
	}
		
	public WebElement getTxtCount() {
		return txtCount;
	}
	
	public List<WebElement> getLstDescription() {
		return lstDescription;
	}
	
	public AdjunoWFLDestinationQCPreInspectionPOM(WebDriver driver) {
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
	
	/* get combobox value */
	public ArrayList<String> getDropdownValues(String strFormName,String strControlName){

	    objAdjunoUILibrary = new AdjunoUILibrary(driver);
		ArrayList<String> dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;			
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
	

	/*get the catalog data in table */
	public ArrayList<String> getCatalogData() 
	{
		ArrayList<String> supplierList =new ArrayList<String>();
		for (int i = 0; i < lstDescription.size(); i++) 
		{
			supplierList.add(lstDescription.get(i).getText());
	
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
	 
	   
	 public ArrayList<String> getCatalogTableData(int nCount) //5
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
	 
	 
	 
	 /* get the data in search list table */
	 public String verifySearchValue(String strValue, List<WebElement> wbList) {
		 String strMessage = "";
		 for (int i = 0; i <= wbList.size()-1; i++) {
			
			 if(!wbList.get(i).getText().isEmpty() && wbList.get(i).getText().equalsIgnoreCase(strValue)){
			 }
			 else
			 {
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
	
	
	/* count no of rows in grid */
	public int getNoOfRowsResulted() {
	       
        return getLstCheckBox().size();
    }
	
	
	public ArrayList<DestinationQCPreInspection> selectMulitpleProducts(int nMaxProductsToSelect)
    {
		wait(1000);
        int nCounterLen = getNoOfRowsResulted();
        ArrayList<DestinationQCPreInspection> lstSearchResults = new ArrayList<DestinationQCPreInspection>();
       
        if (nCounterLen < nMaxProductsToSelect){
        	
        }
        else 
        	nCounterLen = nMaxProductsToSelect;
            
        for(int i = 0; i <= nCounterLen - 1; i++)
        {          
        	DestinationQCPreInspection dqc = new DestinationQCPreInspection(getLstHouseBill().get(i).getText(), getLstCertificateType().get(i).getText(), getLstContainer().get(i).getText());
            wait(2000);
        	getLstCheckBox().get(i).click();
        	lstSearchResults.add(dqc);	
        }
        return lstSearchResults;
    }
	
	 public String compareFromDates(String datevalue1,List<WebElement> datevalue2) throws ParseException{
		 String strMessage = "";
		 SimpleDateFormat simpledateformat = new SimpleDateFormat("dd MMM yyyy");

		 for (int i = 0; i <= datevalue2.size() - 1; i++) {
			String date1 = datevalue2.get(i).getText();
			if(simpledateformat.parse(date1).equals(simpledateformat.parse(datevalue1))||simpledateformat.parse(date1).after(simpledateformat.parse(datevalue1))){
			
			}else {
				strMessage = strMessage+"   In Destination QC Pre-Inspection Tool ETA from Date is :"+ datevalue1+" this And expected results should be from this date, But Actually value"+date1;
				
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
				strMessage = strMessage+ "  In Destination QC Pre-Inspection Tool ETA To Date is :"+ datevalue1 +" this And expected results should be before this date, But Actually value"+date1;
			}
		 
		 }
		return strMessage;
		 
	 }
	
	public String verifyProductsDataOnGrid(String strFormName, String strControlName,ArrayList<DestinationQCPreInspection> lstSearchResults) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
    	int n1 = lstSearchResults.size();
    	
    	long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
    	
    	String strHBL = "";
    	String strCertificateType ="";
    	String strContainerNo="";
    	String strReturnMessage ="";
    	boolean bProductIDFound = false;
 	
    	if(n1==n2)
    	{
        	for (int i=0; i <= n1-1;i++)
        	{
        		strHBL =lstSearchResults.get(i).getHbl(); 
        		//System.out.println(strHBL);
        		strCertificateType = lstSearchResults.get(i).getCertificateType();  
        		//System.out.println(strCertificateType);
        		strContainerNo=lstSearchResults.get(i).getContainerNo();
        		//System.out.println(strContainerNo);
	
            	for (int j=0; j <= n2-1;j++)
            	{
            		if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "HouseBillLink").getText().equalsIgnoreCase(strHBL))	  
            		{
            			bProductIDFound = true;
            		
            			if(!(compareTwoStringValuesToSame(strHBL, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "HouseBillLink").getText())))
            				strReturnMessage = strReturnMessage + "For HBL Number '" + strHBL + "',is issue on the Grid;";
            			//System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "HouseBillLink").getText());
            			if(!(compareTwoStringValuesToSame(strCertificateType, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "CertificateType").getText())))
							strReturnMessage = strReturnMessage + "For CertificateType '" + strCertificateType + "',is issue on the Grid;";
            				//System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "CertificateType").getText());
            			if(!(compareTwoStringValuesToSame(strContainerNo, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "Container").getText())))
            				strReturnMessage = strReturnMessage + "For ContainerNo '" + strContainerNo + "', is issue on the Grid;";
            				//System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "Container").getText());
						break;
            		}
					else 
					{
						if(j==n2-1)
						{
							strReturnMessage = strReturnMessage + "HBR number '"+ strHBL + "' not found on the grid;";
						}
						
					}
            	}	
        	}
    	}
    	else
    	{
    		strReturnMessage = "The number of Records selected in the search results do not match with the number of Records on the grid.";
    	}
    	
		return strReturnMessage;
	}


	public boolean compareTwoStringValuesToSame(String strValue1, String strValue2)
	{
		boolean bFlag = true;
		if ((!(isNullOrBlank(strValue1))) &&  (!(isNullOrBlank(strValue2))))
		{                
			if (strValue1.equalsIgnoreCase(strValue2)){
				
			
			}
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
	
	public void wait(int ms)
    {
    	try 
    	{
    		Thread.sleep(ms);
    	} catch (InterruptedException e) {	
    		e.printStackTrace();
    	}
    }
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
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
	
	//logout 
	public boolean clickLogout(WebElement we)
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
	
	public boolean checkDoesElementExistWebElement(WebElement wb){
		 boolean bFlag;
	     objAdjunoUILibrary = new AdjunoUILibrary(driver);
	     try
	        {    
	        	bFlag = wb.isDisplayed();
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
	
	//click the check box in grid
	public boolean clickcheckbox(String strFormName,String strGridControlName,int nRow, String strColumnName) {
		boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
        
        try
       {
            bFlag = we.isDisplayed();
            we.click();
           // we.sendKeys(Keys.TAB);
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
	
	//check the grid check box is checked or not
	public boolean isCheckboxOnGridChecked(String strFormName, String strGridControlName, int nRow, String strColumnName){
		boolean bFlag = false;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isCheckboxOnGridChecked(strFormName, strGridControlName, nRow, strColumnName);
	        
	     return bFlag;	
		
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
	
	//get the Grid cell value
	public String getGridCellValue(String strFormName, String strGridControlName, int nRow , String strColumnName){
		boolean bFlag;
		objAdjunoUILibrary=new AdjunoUILibrary(driver);
		
		String value=objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, nRow, strColumnName);

		return value;
		
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

	
	/* set the value to text field */
	public String getElementValue(String formName, String controlName) {
		String strElementValue ="";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
		if (objAdjunoUILibrary.isPageLoaded(formName))
		{
			strElementValue = we.getText();
		}
		return strElementValue;		
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
	
	
	public boolean isCheckBoxClicked(WebElement we){
		boolean bFlag;
		try{
			bFlag=we.isSelected();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
		
	}
	
	 public ArrayList<String> getCatalogLOCODETableData(int nCount) //5
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
	
}


     
	

		
		
	
		

	
	
		

