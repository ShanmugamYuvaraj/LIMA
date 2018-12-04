package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAOffDockLocationPOM {
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(linkText="Lighthouse") WebElement lnkLightHouse;
	@FindBy(linkText="Off Dock Location") WebElement lnkOffDockLocation;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_IsOrigin']") WebElement isCheckboxClicked;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List<WebElement> lstItemDescription;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_CatalogItemDescription']") WebElement lstDescriptionField;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement btnFirst;
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chvSearch1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[text()='Off Dock Location']") WebElement chvOffDock;
	@FindBy(xpath=".//*[@id='SUT_StageBar_OffDock']") WebElement chvOffDock2;	
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete2;
	@FindBy(xpath=".//p[contains(text(),'No items were found.')]") WebElement txtNoResult;	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]") List<WebElement> lstContainers;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]/input") List<WebElement> lstSelect;
	@FindBy(xpath=".//*[@class='Warnings NoSelections']") WebElement errorMessage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement trCheckbox;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement trContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[6]/font") WebElement trStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thArrivalDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thDestinationPOrt;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]") WebElement thContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement thOffDockArrivalDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement thOffDockLocation;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[5]") WebElement thForwarderRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[6]") WebElement thStatus;
	@FindBy(xpath=".//*[@id='LH_Off_Dock_Storage_Upload_SUT_UploadDeliveryBookingTemplate']") WebElement btnUpload;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]") List<WebElement> lstDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> lstETAFrom;
    @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> lstVessel;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[6]") List<WebElement> lstStatus;
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[5]") List<WebElement> lstForwardref;
	 
	@FindBy(xpath=".//*[@id='LH_Off_Dock_Storage_Upload_SUT_btnDeleteRows']") WebElement btnDelete;
	@FindBy(xpath=".//*[@id='LH_Off_Dock_Storage_Upload_SUT_Confirm_OK']") WebElement btnYes;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement editTitle;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(xpath="(.//*[text()='Off Dock Location'])[last()]")WebElement lnkOffDock;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement trackMessage;
	
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Container')]") WebElement hdContainer;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Seal')]") WebElement hdSeal;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-ArrivedDate')]") WebElement hdArrivedDate;
	@FindBy(xpath=" .//*[contains(@class,'x-grid3-hd-DepartedDate')]") WebElement hdDepartedDate;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-OffDockLocation')]") WebElement hdOffDockLocation;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Operator')]") WebElement hdOperator;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-ReasonCode')]") WebElement hdReasonCode;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Comments')]") WebElement hdComments;
	
	 
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-Container')]") WebElement colContainer;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-ArrivedDate')]") WebElement colArrivedDate;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-DepartedDate')]") WebElement colDepartedDate;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-OffDockLocation')]") WebElement colOffDockLocation;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-ReasonCode')]") WebElement colReasonCode;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-Comments')]") WebElement colComments;
	//@FindBy(linkText="Go To") WebElement lnkGoToOffDock;
	 @FindBy(xpath=".//span[contains(@class,'x-grid3-cc-Select')]")WebElement select;
	 @FindBy(xpath=".//*[@class='Warnings NoSelections']") WebElement errMessage;
	 
	 @FindBy(xpath=".//*[@class='x-grid3-check-col x-grid3-cc-GridSelect']") WebElement selectBox;
	 
	
	public WebElement getSelectBox() {
		return selectBox;
	}

	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}

	public void setObjAdjunoUILibrary(AdjunoUILibrary objAdjunoUILibrary) {
		this.objAdjunoUILibrary = objAdjunoUILibrary;
	}
	
	public WebElement getErrMessage() {
		return errMessage;
	}

	public WebElement getTrContainer() {
		return trContainer;
	}
	
	public WebElement getSelect() {
		return select;
	}
	
	public WebElement getLnkOffDock() {
		return lnkOffDock;
	}
	
	public WebElement getFldRefNo() {
		return fldRefNo;
	}
	
	public WebElement getColContainer() {
		return colContainer;
	}

	public WebElement getColArrivedDate() {
		return colArrivedDate;
	}

	public WebElement getColDepartedDate() {
		return colDepartedDate;
	}

	public WebElement getColOffDockLocation() {
		return colOffDockLocation;
	}

	public WebElement getColReasonCode() {
		return colReasonCode;
	}

	public WebElement getColComments() {
		return colComments;
	}

	
	public WebElement getHdContainer() {
		return hdContainer;
	}

	public WebElement getHdSeal() {
		return hdSeal;
	}

	public WebElement getHdDepartedDate() {
		return hdDepartedDate;
	}

	public WebElement getHdOffDockLocation() {
		return hdOffDockLocation;
	}

	public WebElement getHdOperator() {
		return hdOperator;
	}

	public WebElement getHdReasonCode() {
		return hdReasonCode;
	}

	public WebElement getHdComments() {
		return hdComments;
	}
	
	public WebElement getHdArrivedDate() {
		return hdArrivedDate;
	}
	
	public WebElement getTrackMessage() {
		return trackMessage;
	}
	
	public WebElement getEditTitle() {
		return editTitle;
	}
	
	public WebElement getBtnApply() {
		return btnApply;
	}
	
	public WebElement getBtnUpload() {
		return btnUpload;
	}
	
	public WebElement getBtnYes() {
		return btnYes;
	}

	
	public WebElement getBtnDelete() {
		return btnDelete;
	}
	
	public WebElement getErrorMessage() {
		return errorMessage;
	}
	
	public WebElement getTrCheckbox() {
		return trCheckbox;
	}

	public WebElement getTrStatus() {
		return trStatus;
	}
	
	public List<WebElement> getLstContainers() {
		return lstContainers;
	}
	
	public List<WebElement> getLstSelect() {
		return lstSelect;
	}
	
	public WebElement getChvSelect() {
		return chvSelect;
	}

	public WebElement getTxtNoResult() {
		return txtNoResult;
	}

	public WebElement getChvOffDock() {
		return chvOffDock;
	}
	
	public WebElement getChvOffDock2() {
		return chvOffDock2;
	}


	public WebElement getThVessel() {
		return thVessel;
	}

	public WebElement getThArrivalDate() {
		return thArrivalDate;
	}

	public WebElement getThDestinationPOrt() {
		return thDestinationPOrt;
	}

	public WebElement getThContainer() {
		return thContainer;
	}

	public WebElement getThOffDockArrivalDate() {
		return thOffDockArrivalDate;
	}

	public WebElement getThOffDockLocation() {
		return thOffDockLocation;
	}

	public WebElement getThForwarderRef() {
		return thForwarderRef;
	}
	
	public List<WebElement> getLstStatus() {
 		return lstStatus;
 	}
	
	public WebElement getLnkEdit() {
		return lnkEdit;
	}

	public WebElement getThStatus() {
		return thStatus;
	}

	public WebElement getChvComplete2() {
		return chvComplete2;
	}
	
	public WebElement getChvSearch() {
		return chvSearch;
	}
	
	public WebElement getChvSearch1() {
		return chvSearch1;
	}
	
	public List<WebElement> getLstDestinationPort() {
		return lstDestinationPort;
	}

	public WebElement getChvComplete1() {
		return chvComplete1;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}
	
	public WebElement getBtnFirst() {
		return btnFirst;
	}
	
	public WebElement getLnkTrack() {
		return lnkTrack;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}
	
	public List<WebElement> getLstETAFrom() {
		return lstETAFrom;
	}
	
	public List<WebElement> getLstForwardref() {
		return lstForwardref;
	}

	
	public List<WebElement> getLstDescription() {
		return lstDescription;
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
	
	public WebElement getTxtCount() {
		return txtCount;
	}

	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
	}
	
	public List<WebElement> getLstVessel() {
		return lstVessel;
	}

	public WebElement getLnkOffDockLocation() {
		return lnkOffDockLocation;
	}
	
	public WebElement getcatalogbtnPageNext(){
		return catalogbtnPageNext;
	}

	public WebElement getCatalogbtnApply() {
		return catalogbtnApply;
	}

	public AdjunoLIMAOffDockLocationPOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
    }
	
	
	public long getNoOfRowsGrid(String strFormName, String strGridControlName){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		return objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
	}
	
	public long getNoOfColumnsGrid(String strFormName, String strGridControlName){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		return objAdjunoUILibrary.getNoOfColumnsGrid(strFormName, strGridControlName);
	}
	
	public boolean clearInputField(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try
        {
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
	
	public void wait(int ms) {
		   try 
		   {
				Thread.sleep(ms);
				
		   } 
		   catch (InterruptedException e) 
		   {
				e.printStackTrace();
		   }
		}
	
	public boolean clickButtonUsingWebElement(WebElement we) 
	{
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
	
	public boolean setFieldValue(String strFieldValue, String strFormName, String strControlName) 
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		
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
	
	public boolean isPageLoaded(String strFormName){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	
		return objAdjunoUILibrary.isPageLoaded(strFormName);	
	}
	
	public String callMouseHover(String strFormName, WebElement mainLink,WebElement subLink) {
		Actions action = new Actions(driver);

		action.moveToElement(mainLink).perform();

		action.moveToElement(subLink);

		action.click();

		action.perform();

		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strTitle = "";
		if (objAdjunoUILibrary.isPageLoaded(strFormName)) {
			strTitle = driver.getTitle();
		}

		return strTitle;
	}
	
	public boolean clickButton(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);

		try {
			bFlag = we.isDisplayed();
			we.click();
			wait(3000);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	
	public int valCount()
	{
		 String strCount = getTxtCount().getText();
		 String[] vals = strCount.split("of ");
		 int nCount = Integer.parseInt(vals[1]);
	    
		 if(nCount%100 == 0) {
			 nCount= nCount/100;
		 }else {
			 nCount= nCount/100;
			 
			 nCount++;
		 }
		 return nCount;  
	 }
	
	 public ArrayList<String> getCatalogTableData(int nCount) 
	 {
		 boolean bFlag;
	     ArrayList<String> list =new ArrayList<String>();
	     for(int j=0;j<=nCount-1;j++)
	     {
	     	for (int i=0; i < lstItemDescription.size(); i++){
	     		lstItemDescription.get(i).click();
	     		bFlag = isCheckboxClicked.isSelected();
	     		
	     		if(bFlag){
	     		//	wait(500);
	     			list.add(lstDescriptionField.getText());     				
	     			driver.navigate().back();	
	     		}else{
	     			driver.navigate().back();	
	     		}
	     			
	     	}
	   	        	
	     	getcatalogbtnPageNext().click();
	     }
	     
	        return list;
	 }
	 
	 public ArrayList<String> getCatalogTableData1(int nCount) {
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
	 
	public ArrayList<String> getCatalogData() {
		ArrayList<String> supplierList = new ArrayList<String>();
		for (int i = 0; i < lstDescription.size(); i++) {
			wait(500);
			supplierList.add(lstDescription.get(i).getText());

		}

		return supplierList;
	}
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
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
	
	
	 
	public ArrayList<String> getDropdownValues(String strFormName,String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		ArrayList<String> dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;
	}
	
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
	
	public String verifyStatusDropDown(NodeList nodeList,ArrayList<String> list, int nstatus) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
     	{
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nstatus-1; j++) {
				String tempVal = e1.getElementsByTagName("Status_Value").item(j).getTextContent();
    			for (int i = 0; i <= list.size()-1; i++) {
				
					if(tempVal.equalsIgnoreCase(list.get(i))){
						break;
					}else{
						if(i==list.size()-1){
							strMessage = strMessage+ tempVal+ " from Xml is not found in Drop down ";
						}								
					}
    			}
			
			}
     	}	
		return strMessage;
	}
	
	
	public boolean isElementPresent(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(we);
          
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

	public String verifyContainerAndStaus(String strContainer,	String strStatus) {
		String strMessage = "";
		
		if(getTrContainer().getText().equalsIgnoreCase(strContainer)){
			
		}else{
			strMessage = strContainer + "Container value is mismatch select chevron- Exepected value:"+strContainer +" But Found:"+getTrContainer().getSize();
		}
		
		if(getTrStatus().getText().equalsIgnoreCase(strStatus)){
			
		}else{
			strMessage = strContainer + "status value is mismatch in select chevron- Exepected value:"+strStatus +" But Found:"+getTrStatus().getSize();
		}
		return strMessage;
	}
	
	public ArrayList<String> getCaptionsList(String strFormName, String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver); 
		long nGridColumns = objAdjunoUILibrary.getNoOfColumnsGrid(strFormName,strControlName);	
		String strColumnName = "";
		
		ArrayList<String> captionList = new ArrayList<String>();
		
		for (int a=0; a<= nGridColumns - 1; a++)
		{
			strColumnName = objAdjunoUILibrary.getColumnName(strFormName,strControlName, a);
			captionList.add(objAdjunoUILibrary.getGridColumnCaption(strFormName,strControlName,strColumnName,0));
		}
		
		return captionList;
	}
	
	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, long count) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
     	{
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= count-1; j++) {
				String tempVal = e1.getElementsByTagName("caption").item(j).getTextContent();
    			for (int i = 0; i <= list.size()-1; i++) {
				
					if(tempVal.equalsIgnoreCase(list.get(i))){
						break;
					}else{
						if(i==list.size()-1){
							strMessage = strMessage+ tempVal+ " from Xml is not found in Grid ";
						}								
					}
    			}
			
			}
     	}	
		return strMessage;
	}
	
	public boolean setFieldValueForWebElement(WebElement we, String strFieldValue) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	
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
	
	public String getNextDateFromCertainDate(String strDate,String format,int day) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(strDate));
		c.add(Calendar.DATE, day);
		return sdf.format(c.getTime());
	}
	
	public String getDate(int days, String pattern) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		// dd MMM yyyy HH:mm
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.format(cal.getTime());

		return dateFormat.format(cal.getTime());
	}
	
	public WebElement getGridCellElement(String strFormName, String strControlName, int nRow, String strColumnName) 
	{
	
		return objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, nRow, strColumnName);     
		
	}
	
	
	public List<String> getDestionationPortM() {
		List<String> list = new ArrayList<String>();
	   
		for (int i = 0; i <= getLstDestinationPort().size()-1; i++) {
			
		list.add(getLstDestinationPort().get(i).getText());
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}
	
	public String verifyDestinationPort(String strDestinationPort,List<String> list) {
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
				
			if(list.get(i).equalsIgnoreCase(strDestinationPort)){
				//System.out.println("test--"+strDestinationPort);
			}else{
				
				strMessage = strMessage + "For this Container :"+ list.get(i)+ "Destination Port is not matching";
			}
		}
		
		return strMessage;
	}
	
	
	public List<Date> getArrivedDate() throws ParseException {
		List<Date> list = new ArrayList<Date>();
	  
		for (int i = 0; i <= getLstETAFrom().size()-1; i++) {
			
			Date arrivalDate = new SimpleDateFormat("dd MMM yyyy").parse(getLstETAFrom().get(i).getText());
			list.add(arrivalDate);
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}

	public String verifyArrivedDate(Date etaFromDate, List<Date> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			
			if(list.get(i).after(etaFromDate) || list.get(i).equals(etaFromDate)){
				
			}else{
				strMessage = strMessage + " "+list.get(i)+" value is before "+ etaFromDate;
			}
		}
		
		return strMessage;
	}
	
	
	public String verifyArrivedDate2(Date etaToDate, List<Date> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).before(etaToDate) || list.get(i).equals(etaToDate)){
				
			}else{
				strMessage = strMessage + " "+list.get(i)+" value is after "+ etaToDate;
			}
		}
		
		return strMessage;
	}

	public String verifyVesselvalue(String strVesselValue, List<String> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).equalsIgnoreCase(strVesselValue)){
				
			}else{
				strMessage = strMessage + " " + list.get(i)+" value is not matching "+strVesselValue;
			}
		}
		return strMessage;
	}
	
	public List<String> getStatusAwait() {
		List<String> list = new ArrayList<String>();
	    //System.out.println("Size of Status"+getLstStatusAwaiting().size());
		for (int i = 0; i <= getLstStatus().size()-1; i++) {			
			list.add(getLstStatus().get(i).getText());
			//System.out.println("For"+i + " Status : "+po.getStatus());
		}
		
		return list;
	}
	
	public List<String> getVesselValues() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= getLstVessel().size()-1; i++) {
			
			list.add(getLstVessel().get(i).getText());
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}
	
	public String verifyStatusAwaiting(String strStatusAwaitng,List<String> list) {
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			if(list.get(i).equalsIgnoreCase(strStatusAwaitng)){
				
			}else{
				strMessage = strMessage + "For this Container :"+ list.get(i)+ "Status is not matching";
			}
		}
		
		return strMessage;
	}
	
	
	public List<String> getForwardRefValues() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= getLstForwardref().size()-1; i++) {
			
			list.add(getLstForwardref().get(i).getText());
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}

	public String verifyForwardRefvalue(String strForwarderRef,List<String> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).equalsIgnoreCase(strForwarderRef)){
				
			}else{
				strMessage = strMessage + " " + list.get(i)+" value is not matching "+strForwarderRef;
			}
		}
		return strMessage;
	}
	
	
}
