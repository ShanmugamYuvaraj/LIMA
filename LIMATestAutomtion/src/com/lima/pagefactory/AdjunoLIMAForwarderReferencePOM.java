package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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

import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAForwarderReferencePOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	
	@FindBy(linkText="Forwarder Reference") WebElement lnkForwarderReference;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	
	@FindBy(xpath=".//*[text()='Reference']") WebElement chvReference;
	
	@FindBy(xpath=".//*[@value='Reference']") WebElement chvReference2;
	
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	
	@FindBy(xpath=".//*[@value='Complete']") WebElement chvComplete2;
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement noItem;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr[2]/td[1]/input") List<WebElement> selectBox;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td[7]/font") List<WebElement> trStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td[3]") List<WebElement> trVendor;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td[6]") List<WebElement> trShipCtns;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td[4]") List<WebElement> trHouseBill;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td[5]") List<WebElement> trShipQty;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr/td[2]") List<WebElement> trContainer;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> trVessel;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> trOriginPort;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]") List<WebElement> trDestinationPort;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[4]") List<WebElement> trETA;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr/td[1]/a") List<WebElement> lstPONumber;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr/td[21]") List<WebElement> lstStatus;
	
    @FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td") List<WebElement> forwarderRefValue;
    
    @FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]") List<WebElement> PONumberValue;
    
    @FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[7]/a")List<WebElement> poContainerValue;
    
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement referenceTitle;
	
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	
	@FindBy(xpath=".//*[@class=' Warnings ValidationFailures']/p") WebElement validationMsg;

	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(linkText = "Track") WebElement lnkTrack;
	@FindBy(linkText = "Edit") WebElement lnkEdit;
	@FindBy(linkText = "Find") WebElement lnkFind;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	@FindBy(xpath="(.//*[text()='Forwarder Reference'])[last()]") WebElement tracklnkForwarderReference;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[1]/div") List<WebElement> TrackForwardReference;
	@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[4]/div") List<WebElement> TrackHouseBill;
	@FindBy(xpath=".//*[@id='LH_ForwarderReference800__Mode']") WebElement trackShipmentMethod;
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement runButton;
	
	@FindBy(xpath = "(.//*[contains(@class,'x-grid3-row-last')])[3]/table/tbody/tr/td[7]/div")WebElement sumValueOfShipQty;
	@FindBy(xpath = "(.//*[contains(@class,'x-grid3-row-last')])[3]/table/tbody/tr/td[8]/div")WebElement sumValueOfShipCtns;
	@FindBy(xpath = ".//*[@class='x-tool x-tool-close']")WebElement closePODetails;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]")WebElement strVessel;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[4]")WebElement strHouseBillValue;
	
	@FindBy (xpath=".//*[@id='OptionsBarSubmitButton']") WebElement clickApplyButton;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]/a/b") WebElement statusOfPOStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[i]/td[1]/a") WebElement PONumberOfPOStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[1]/a") WebElement PONumberHyperLink;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[4]/a") WebElement ContainerHyperlink;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[7]/a") WebElement ShipmentStatusPONumber;
	@FindBy(xpath =".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[8]") WebElement shipmentStatusContainerValue;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[25]/a") WebElement ShipmentStatusReference; 
    @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[26]/font") WebElement ShipmentStatus_Status;
    @FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[2]") WebElement ContainerValue;
    @FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
    
   

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getShipmentStatusContainerValue() {
		return shipmentStatusContainerValue;
	}

	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}

	public AdjunoLIMAForwarderReferencePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public WebElement getBtnOK1() {
		return btnOK1;
	}

	public WebElement getStrVessel() {
		return strVessel;
	}

	public List<WebElement> getPONumberValue() {
		return PONumberValue;
	}

	public List<WebElement> getPoContainerValue() {
		return poContainerValue;
	}

	public WebElement getClosePODetails() {
		return closePODetails;
	}

	public List<WebElement> getForwarderRefValue() {
		return forwarderRefValue;
	}

	public WebElement getSumValueOfShipQty() {
		return sumValueOfShipQty;
	}

	public WebElement getSumValueOfShipCtns() {
		return sumValueOfShipCtns;
	}

	public List<WebElement> getLstStatus() {
		return lstStatus;
	}

	public List<WebElement> getLstPONumber() {
		return lstPONumber;
	}

	public WebElement getShipmentStatus_Status() {
		return ShipmentStatus_Status;
	}

	public final WebElement getStrHouseBillValue() {
		return strHouseBillValue;
	}

	public WebElement getShipmentStatusPONumber() {
		return ShipmentStatusPONumber;
	}

	public WebElement getShipmentStatusReference() {
		return ShipmentStatusReference;
	}

	public WebElement getPONumberHyperLink() {
		return PONumberHyperLink;
	}

	public WebElement getContainerHyperlink() {
		return ContainerHyperlink;
	}

	public WebElement getPONumberOfPOStatus() {
		return PONumberOfPOStatus;
	}

	public WebElement getStatusOfPOStatus() {
		return statusOfPOStatus;
	}
	

	public WebElement getClickApplyButton() {
		return clickApplyButton;
	}

	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
	}

	public WebElement getRunButton() {
		return runButton;
	}

	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}

	public WebElement getContainerValue() {
		return ContainerValue;
	}

	public WebElement getTracklnkForwarderReference() {
		return tracklnkForwarderReference;
	}

	public List<WebElement> getTrackForwardReference() {
		return TrackForwardReference;
	}
	
	public List<WebElement> getTrackHouseBill() {
		return TrackHouseBill;
	}

	public WebElement getTrackShipmentMethod() {
		return trackShipmentMethod;
	}

	public List<WebElement> getLstTrackEvent() {
		return lstTrackEvent;
	}

	public List<WebElement> getLstTrAchivedDate() {
		return lstTrAchivedDate;
	}

	public List<WebElement> getLstTrUserName() {
		return lstTrUserName;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}
	
	public WebElement getValidationMsg() {
		return validationMsg;
	}
	
	public WebElement getReferenceTitle() {
		return referenceTitle;
	}
	
	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}
	
	public List<WebElement> getTrShipCtns() {
		return trShipCtns;
	}
	
	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	
	public WebElement getLnkEdit() {
		return lnkEdit;
	}
	
	public WebElement getFldRefNo() {
		return fldRefNo;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}
	
	public List<WebElement> getTrHouseBill() {
		return trHouseBill;
	}	
	
	public List<WebElement> getTrStatus() {
		return trStatus;
	}
	
	public List<WebElement> getTrVendor() {
		return trVendor;
	}
	
	public List<WebElement> getTrShipQty() {
		return trShipQty;
	}
	
	public List<WebElement> getTrContainer() {
		return trContainer;
	}
	
	public List<WebElement> getTrVessel() {
		return trVessel;
	}
	
	public List<WebElement> getTrOriginPort() {
		return trOriginPort;
	}
	
	public List<WebElement> getTrDestinationPort() {
		return trDestinationPort;
	}
	
	public List<WebElement> getTrETA() {
		return trETA;
	}

	public WebElement getLnkForwarderReference() {
		return lnkForwarderReference;
	}
	

	public WebElement getChvSearch() {
		return chvSearch;
	}

	public WebElement getChvReference() {
		return chvReference;
	}
	
	public WebElement getChvReference2() {
		return chvReference2;
	}

	public WebElement getChvSelect() {
		return chvSelect;
	}
	
	public List<WebElement> getSelectBox() {
		return selectBox;
	}
	
	public WebElement getChvComplete() {
		return chvComplete;
	}
	
	public WebElement getChvComplete2() {
		return chvComplete2;
	}
	
	public WebElement getNoItem() {
		return noItem;
	}
	
	public WebElement getBtnTrackApply() {
		return btnTrackApply;
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
 
	
	public ArrayList<String> getDropDownList(String strFormName, String strControlName)
	{
		ArrayList<String> dropDownList =new ArrayList<String>();
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		long nCount = objAdjunoUILibrary.getDropDownValueCount(strFormName,strControlName);
		for (int i = 0; i <= nCount-1; i++) 
		{
			dropDownList.add(objAdjunoUILibrary.getDropDownValue(strFormName,strControlName,i));
		}
		
		return dropDownList;	
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
	public boolean clickCheckBox(WebElement checkbox) {
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
	    	we.sendKeys("test");
	    	wait(1000);
	    	we.clear();
	    	bFlag = true;
	    				
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
	public boolean checkFieldIsReadOnly(String strFormName, String strControlName,String strValue) {
		
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		try
        {
			we = objAdjunoUILibrary.getElement(strFormName, strControlName);	 
			we.isDisplayed();
			we.sendKeys(strValue);
			wait(3000);		
			
			we.clear();
			bFlag = true;
			
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
	
	
	public String getDate(int days,String pattern){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		 //dd MMM yyyy HH:mm
		 SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		 dateFormat.format( cal.getTime());
		
		
		return dateFormat.format( cal.getTime());			
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
	
	public boolean checkDoesChevronExist(WebElement chevron) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(chevron);
          
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
	
	public boolean isElementPresent(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(we);
          
		return bFlag;
	}

	public boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}
	
	public String verifyValue(String strValue,List<WebElement> weList) {
		String strMessage = "";
		
		for (int i = 0; i <=weList.size()-1; i++) {
			if(!isNullOrBlank(weList.get(i).getText()) && weList.get(i).getText().equalsIgnoreCase(strValue)){
				
			}else{
				if(i==weList.size()-1){
					strMessage = strMessage +  weList.get(i).getText() +" Value does not match with "+ strValue ;
				}
				
			}
		}
		
		return strMessage;
	}

	public String verifyStatusAndVessel(String strValue, String strValue2) {
		String strMessage = "";
	//	System.out.println("size:"+getTrVessel().size());
		for (int i = 0; i <=getTrVessel().size()-1; i++) {
			if(!isNullOrBlank(getTrStatus().get(i).getText()) && getTrStatus().get(i).getText().equalsIgnoreCase(strValue) && getTrVessel().get(i).getText().equalsIgnoreCase(strValue2)){
				
			}else{
				if(i==getTrStatus().size()-1){
					strMessage = "In Forwarder Reference Tool Under Select Chevron the Vessel Value" +  getTrStatus().get(i).getText() +" does not match with "+ strValue ;
				}
				
			}
		}
		return strMessage;
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
	
	public WebElement getGridCellElement(String strFormName, String strControlName, int nRow, String strColonmName) {
		WebElement we;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		we = objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, nRow, strColonmName);

		return we;			
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
	
	public String isHyperlinkPresent(String strFormName,String strGridControlName,int nRow,String strColumnName)
    {
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        String strMessage = "";
        WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
        String strhref=we.getAttribute("onclick");
        if(!isNullOrBlank(strhref))
        {
           
        }
        else
        {
            strMessage = strMessage + "In Forwarder Reference Tool Under Reference Chevron \"PO Details\" is not a hyperlink";
        }
        return strMessage;
    }

	public boolean clickHyperLink(String strFormName, String strGridControlName,int nRow, String strColumnName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
        boolean bFlag ;
        WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
       
		try{
			we.isDisplayed();
			we.click();
			wait(3000);
			bFlag = true;
		}catch (NoSuchElementException e)
		{
		    bFlag = false;
		}
		catch(NullPointerException e)
		{
			bFlag = false;
		}
		
		
		return bFlag;
		      
	}  
	public boolean clickHyperLinkUsingWebElement(WebElement we)
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		try{
		bFlag = objAdjunoUILibrary.isElementPresent(we);
		we.click();
		wait(3000);
		bFlag = true;
	}catch (NoSuchElementException e)
	{
	    bFlag = false;
	}
	catch(NullPointerException e)
	{
		bFlag = false;
	}
	
	
	return bFlag;
		
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
	
	
	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, int nCaptions) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
     	{
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nCaptions-1; j++) {
				String tempVal = e1.getElementsByTagName("Caption").item(j).getTextContent();
    			for (int i = 0; i <= list.size()-1; i++) {
				
					if(tempVal.equalsIgnoreCase(list.get(i))){
						break;
					}else{
						if(i==list.size()-1){
							strMessage = strMessage+ tempVal+ " Value from Xml is not found in Grid ";
						}								
					}
    			}
			
			}
     	}	
		return strMessage;
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
			
			wait(5000);
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
	
	public String getSplitValueOfString(WebElement wb){
		String str = wb.getText();//Sum: 2000
	//	System.out.println("str value is "+str);
		String[] value = str.split(": ");
	//	System.out.println("value is "+value[1]);
		String finalValue = value[1];
	//	System.out.println("finalValue is "+finalValue);
		if(!isNullOrBlank(finalValue)){
			
		}else{
			finalValue = finalValue + "In Forwarder Reference Tool Under Reference Chevron in PO Details Page "+ str +"\"Sum : \"Value is Empty";
		}
		
		return finalValue;
		
	}

	public String verifyCompleteMessage(String strFormName, String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		String strCompletMessage = objAdjunoUILibrary.getElemetValue(strFormName,strControlName);
		return strCompletMessage;
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
	public long getDifference(Date today, Date date) 
	{
        
        if( date == null || today == null ) return 0;

        long time = (today.getTime()/60000) - (date.getTime()/60000);
     //   System.out.println("Date:"+ time);
        
        return ((today.getTime()/60000) - (date.getTime()/60000));
        
    }
	
	public long getTrackValue(String userName) throws ParseException 
	{
        int length = getLstTrackEvent().size();
        long min = 0 ;
       List<Tracksdata> list= new ArrayList<Tracksdata>();
       for (int i = 0; i < length-1; i++) {
            Tracksdata track = new Tracksdata(getLstTrackEvent().get(i).getText(), getLstTrAchivedDate().get(i).getText(),getLstTrUserName().get(i).getText());
            list.add(track);
       }
       
       for (int j = 0; j < list.size()-1; j++) {
            if(list.get(j).getEvent().equalsIgnoreCase("Forwarder Reference")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                String strAchievedDate = list.get(j).getAcheived();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                Date today = new Date();
                
                Date achievedDate = formatter.parse(strAchievedDate);
            
                min = getDifference(today,achievedDate);
               // System.out.println("difference is:"+min);
            }
        }
        return min;
    }
	
	public String getFieldValue(String strformName, String strControlName) {
		String tempVal = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getElemetValue(strformName, strControlName);
			if(!isNullOrBlank(tempVal)){
				
			}
			else{
				tempVal ="";
			}
			
       return tempVal;				
	}
	
	
	public String generateRandomString(){
		
		String randomRefValue = "";
						
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 7; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		randomRefValue = sb.toString();
	//	System.out.println(randomRefValue);		
				
		return randomRefValue;
	}

	public boolean expandViewDetails(String strFormName, String strControlName) {
		boolean bFlag = true;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		try{
			objAdjunoUILibrary.clickViewDetail(strFormName, strControlName);
			
		}catch(Exception ex){
			bFlag= false;
		}
		return bFlag;
	}
}	
	
	
