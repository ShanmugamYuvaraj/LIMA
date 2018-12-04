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

public class AdjunoLIMAPoManagerPOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
		
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="PO Manager") WebElement lnkPoManager;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(linkText="Priority") WebElement lnkPriority;
	
	@FindBy(linkText="PO Confirmation") WebElement lnkConfirmation;
	
	@FindBy(linkText="Purchase Order") WebElement lnkPurchaseOrder;
	@FindBy(linkText="Allocation") WebElement lnkAllocation;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Edit']") WebElement chvEdit;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete2;
	@FindBy(xpath=".//*[@type='button' and text()='Yes']") WebElement yesButton;
	@FindBy(xpath=".//*[@id='ext-gen178']/div[1]/table/tbody/tr/td[9]/div") WebElement fldQuantity;
	@FindBy(xpath=".//*[@id='extId_LH_Order_SUTPOManagerTool_Edit_ContractShipDate']") WebElement fldShipDate;
	@FindBy(xpath=".//*[@class='Title']/p") WebElement txtEdit;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement txtUpdateMessage;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(xpath=".//*[@class='Title']/p") WebElement txtPurchaseOrder;
		
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement trPONumber_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[12]/font") WebElement trStatus;
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
	
	public WebElement getTrPONumber_Multi() {
		return trPONumber_Multi;
	}
		
	public WebElement getLnkPriority() {
		return lnkPriority;
	}

	public WebElement getTrStatus() {
		return trStatus;
	}

	public WebElement getLnkConfirmation() {
		return lnkConfirmation;
	}

	public WebElement getLnkPurchaseOrder() {
		return lnkPurchaseOrder;
	}

	public WebElement getLnkAllocation() {
		return lnkAllocation;
	}

	public List<WebElement> getLstTrEvent() {
		return lstTrEvent;
	}

	public List<WebElement> getLstTrAchivedDate() {
		return lstTrAchivedDate;
	}

	public List<WebElement> getLstTrUserName() {
		return lstTrUserName;
	}

	public WebElement getTxtPurchaseOrder() {
		return txtPurchaseOrder;
	}

	public WebElement getBtnApply() {
		return btnApply;
	}
	
	public WebElement getFldRefNo() {
		return fldRefNo;
	}

	public WebElement getTxtUpdateMessage() {
		return txtUpdateMessage;
	}
	
	public WebElement getLnkTool() {
		return lnkTools;
	}
	
	public WebElement getLnkPoManager() {
		return lnkPoManager;
	}
	
	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	
	public WebElement getLnkEdit() {
		return lnkEdit;
	}

	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}
	
	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	
	public WebElement getChvSearch() {
		return chvSearch;
	}
	
	public WebElement getTxtEdit() {
		return txtEdit;
	}
	
	public WebElement getYesButton() {
		return yesButton;
	}
	
	public WebElement getFldQuantity() {
		return fldQuantity;
	}
	
	public WebElement getfldShipDate() {
		return fldShipDate;
	}
	
	public WebElement getChvEdit() {
		return chvEdit;
	}
	
	public WebElement getChvComplete1() {
		return chvComplete1;
	}
	
	public WebElement getChvComplete2() {
		return chvComplete2;
	}

	public WebElement getBtnOK1() {
		return btnOK1;
	}
	
	public AdjunoLIMAPoManagerPOM(WebDriver driver)
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

	public boolean checkDoesChevronExist(WebElement chevron) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(chevron);
          
		return bFlag;
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

	public boolean clearInputField(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try
        {
			bFlag = we.isDisplayed();
			we.clear();
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
	
	public boolean checkGridCellUserEditable(String strFormName, String strGridControlName, int nRow, String strColumnName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try
        {
        	WebElement we;
        	we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strGridControlName, nRow, strColumnName);
        	wait(1000);
			bFlag= we.isDisplayed();
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

	

	public boolean clickButton(String formName,String controlName) {
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

	public boolean clickOnNextTab(String strEditFormNamePOManager, String strPanelName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver); 
		bFlag = objAdjunoUILibrary.clickNextTab(strEditFormNamePOManager,strPanelName);
		return bFlag;
	}

	public boolean clickOnPreviousTab(String strEditFormNamePOManager, String strPanelName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver); 
		bFlag = objAdjunoUILibrary.clickPreviousTab(strEditFormNamePOManager,strPanelName);
		return bFlag;
	}
	
	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, int nCaptions) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
     	{
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nCaptions-1; j++) {
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
	
	//
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
	
	
	public String getFieldValue2(String strformName, String strControlName) {
		String tempVal = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getElemetValue2(strformName, strControlName);
			if(!isNullOrBlank(tempVal)){
				
			}
			else{
				tempVal ="";
			}
			
       return tempVal;				
	}
	public String getDropDownFieldValue(String strformName, String strControlName) {
		String tempVal = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getDropDownElemetValue(strformName, strControlName);
			if(!isNullOrBlank(tempVal)){
				
			}
			else{
				tempVal ="";
			}
			
       return tempVal;				
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
	
	
	private boolean isNullOrBlank(String s)
	{
	      return (s==null || s.trim().equals(""));
	}

	public Long getFieldValueForLong(String strformName,String strControlName) {
		Long tempVal;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		 tempVal = objAdjunoUILibrary.getElemetValueForLong(strformName, strControlName);
			if(tempVal>0){
				
			}
			else{
				tempVal = 0l;
			}
			
       return tempVal;		
	}
	
	public String getDate(int days,String pattern){
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		 //dd MMM yyyy HH:mm
		 SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		 dateFormat.format( cal.getTime());
		
		
		return dateFormat.format( cal.getTime());			
	}
	
	
	public boolean setDate(String strFormName,String strControlName)
	{
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd MMM YYYY");
		String dtTodayDate = dateformatter.format(new Date());
		WebElement we = objAdjunoUILibrary.getElement(strFormName,strControlName);
		try {
			we.isDisplayed();
			we.clear();
			we.sendKeys(dtTodayDate);
			bFlag = true;
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}

	public long getTrackValue(String userName) throws ParseException {
		int length = getLstTrEvent().size();
		long min = 0 ;
    	List<Tracksdata> list= new ArrayList<Tracksdata>();
    	for (int i = 0; i < length-1; i++) {
			Tracksdata track = new Tracksdata(getLstTrEvent().get(i).getText(), getLstTrAchivedDate().get(i).getText(),getLstTrUserName().get(i).getText());
			list.add(track);
    	}
    	
    	for (int j = 0; j < list.size()-1; j++) {
			if(list.get(j).getEvent().equalsIgnoreCase("Purchase Order")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
				String strAchievedDate = list.get(j).getAcheived();
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
				Date today = new Date();
				
				Date achievedDate = formatter.parse(strAchievedDate);
			
				min = getDifference(today,achievedDate);
				System.out.println("difference is:"+min);
			}
			else{
				min = 0;
			}
		}
		return min;
	}

	public long getDifference(Date today, Date date) {
		
		if( date == null || today == null ) return 0;

	    return ((today.getTime()/60000) - (date.getTime()/60000));
		
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
	

	public boolean verifyPoManagerPage(String strFormName,String strPageTitle, WebElement mainLink, WebElement subLink) {
		
		String strTitle = callMouseHover(strFormName,mainLink,subLink);
    	
    	boolean bFlag;
    	
    	if (!(isNullOrBlank(strTitle)))
    	{
    		if (strTitle.equalsIgnoreCase(strPageTitle))
    			bFlag = true;
    		else
    			bFlag = false;
    	}else{
    		bFlag = false;
    	}	       
    	
		return bFlag;
	}
	
	
	public String generateRandomPO(){
		
		
		Random rand = new Random();
		String randomPo = "";
		int rand_int = rand.nextInt(100);
		int rand_int2 = rand.nextInt(1000);

		randomPo = "LIMA"+rand_int+"XPF"+rand_int2;
		System.out.println("PO Number is:"+randomPo);
	    return randomPo;
	}
	
	public String generateRandomPO2(){
		
		
		Random rand = new Random();
		String randomPo = "";
		int rand_int = rand.nextInt(100);
		int rand_int2 = rand.nextInt(1000);

		randomPo = "WFLTEST"+rand_int+"XPF"+rand_int2;
		System.out.println("PO Number is:"+randomPo);
	    return randomPo;
	}
	
}
