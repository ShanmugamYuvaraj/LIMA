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

import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMADeliveryBookingPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;

	
	@FindBy(linkText = "Tools") WebElement lnkTools;
	@FindBy(linkText = "Delivery Booking") WebElement lnkDeliveryBooing;
	@FindBy(linkText = "Track") WebElement lnkTrack; 
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOk;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List<WebElement> lstItemDescription;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_IsOrigin']") WebElement isCheckboxClicked;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_CatalogItemDescription']") WebElement lstDescriptionField;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(linkText="Lighthouse") WebElement lnkLightHouse;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement btnFirst;
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chvSearch1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[text()='Booking']") WebElement chvBooking;
	@FindBy(xpath=".//*[@id='SUT_StageBar_DeliveryBooking']") WebElement chvBooking2;	
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete2;
	@FindBy(xpath=".//p[contains(text(),'No items were found.')]") WebElement txtNoResult;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> lstContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[4]") List<WebElement> lstDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[5]") List<WebElement> lstHaulier;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thEquipment;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") WebElement thMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]") WebElement thDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]") WebElement thETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]") WebElement thDialog;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]/input") WebElement thBookingRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]/input") WebElement thBookingDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]/input") WebElement thDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[5]/input") WebElement thHaulier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[6]/input") WebElement thQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[7]/input") WebElement thCartons;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[8]/input") WebElement thCBM;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[9]/input") WebElement thBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[10]/input") WebElement thBkdCartons;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[11]/input") WebElement thBookingStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[5]") List<WebElement> lstDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]") List<WebElement> lstLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[4]") List<WebElement> lstMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[6]") List<WebElement> lstETAFrom;
	
	public List<WebElement> getLstDestinationPort() {
		return lstDestinationPort;
	}
	
	public List<WebElement> getLstLoading() {
		return lstLoading;
	}
	
	public List<WebElement> getLstMode() {
		return lstMode;
	}
	
	public WebElement getThContainer() {
		return thContainer;
	}

	public WebElement getThEquipment() {
		return thEquipment;
	}

	public WebElement getThLoading() {
		return thLoading;
	}

	public WebElement getThMode() {
		return thMode;
	}

	public WebElement getThDestinationPort() {
		return thDestinationPort;
	}

	public WebElement getThETA() {
		return thETA;
	}

	public WebElement getThDialog() {
		return thDialog;
	}

	public WebElement getThBookingRef() {
		return thBookingRef;
	}

	public WebElement getThBookingDate() {
		return thBookingDate;
	}

	public WebElement getThDestination() {
		return thDestination;
	}

	public WebElement getThHaulier() {
		return thHaulier;
	}

	public WebElement getThQty() {
		return thQty;
	}

	public WebElement getThCartons() {
		return thCartons;
	}

	public WebElement getThCBM() {
		return thCBM;
	}

	public WebElement getThBkdQty() {
		return thBkdQty;
	}

	public WebElement getThBkdCartons() {
		return thBkdCartons;
	}

	public WebElement getThBookingStatus() {
		return thBookingStatus;
	}

	public List<WebElement> getLstContainer() {
		return lstContainer;
	}
	
	public List<WebElement> getLstDestination() {
		return lstDestination;
	}
	
	public List<WebElement> getLstHaulier() {
		return lstHaulier;
	}

	public WebElement getChvSearch() {
		return chvSearch;
	}

	public WebElement getChvSearch1() {
		return chvSearch1;
	}

	public WebElement getChvSelect() {
		return chvSelect;
	}

	public WebElement getChvBooking() {
		return chvBooking;
	}

	public WebElement getChvBooking2() {
		return chvBooking2;
	}

	public WebElement getChvComplete1() {
		return chvComplete1;
	}

	public WebElement getChvComplete2() {
		return chvComplete2;
	}

	public WebElement getBtnFirst() {
		return btnFirst;
	}
	
	public WebElement getCatalogbtnApply() {
		return catalogbtnApply;
	}
	
	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
	}
	
	public List<WebElement> getLstETAFrom() {
		return lstETAFrom;
	}
	
	
	public WebElement getTxtCount() {
		return txtCount;
	}
	
	public List<WebElement> getLstItemDescription() {
		return lstItemDescription;
	}

	public WebElement getIsCheckboxClicked() {
		return isCheckboxClicked;
	}

	public WebElement getLstDescriptionField() {
		return lstDescriptionField;
	}

	public WebElement getCatalogbtnPageNext() {
		return catalogbtnPageNext;
	}

	public List<WebElement> getLstDescription() {
		return lstDescription;
	}

	public WebElement getBtnOk() {
		return btnOk;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkDeliveryBooing() {
		return lnkDeliveryBooing;
	}

	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	
	public WebElement getTxtNoResult() {
		return txtNoResult;
	}

	public AdjunoLIMADeliveryBookingPOM(WebDriver driver) {
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

	public String callMouseHover(String strFormName, WebElement mainLink,WebElement subLink) {
		Actions action = new Actions(driver);

		action.moveToElement(mainLink).perform();

		action.moveToElement(subLink);

		action.click();

		action.perform();
		wait(3000);
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strTitle = "";
		if (objAdjunoUILibrary.isPageLoaded(strFormName)) {
			strTitle = driver.getTitle();
		}

		return strTitle;
	}

	public boolean clickButtonUsingWebElement(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = we.isDisplayed();
			we.click();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}

		return bFlag;

	}
	
	
	public List<String> getDestionationPortM() {
		List<String> list = new ArrayList<String>();

		for (int i = 0; i <= getLstDestinationPort().size() - 1; i++) {

			list.add(getLstDestinationPort().get(i).getText());
			// System.out.println("For"+i +
			// " Destination port :"+po.getDestinationPort());
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
		System.out.println("error message:"+strMessage);
		return strMessage;
	}
	
	public boolean clearInputField(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try {
			bFlag = we.isDisplayed();
			we.clear();
			wait(1000);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}
	
	public int valCount() {
		String strCount = getTxtCount().getText();
		String[] vals = strCount.split("of ");
		int nCount = Integer.parseInt(vals[1]);

		if (nCount % 100 == 0) {
			nCount = nCount / 100;
		} else {
			nCount = nCount / 100;

			nCount++;
		}
		System.out.println(nCount);
		return nCount;
	}
	
	 public ArrayList<String> getCatalogTableData(int nCount) 
	 {
		boolean bFlag;
		ArrayList<String> list = new ArrayList<String>();
		for (int j = 0; j <= nCount - 1; j++) {
			for (int i = 0; i < lstItemDescription.size(); i++) {
				lstItemDescription.get(i).click();
				bFlag = isCheckboxClicked.isSelected();

				if (bFlag) {
					// wait(500);
					list.add(lstDescriptionField.getText());
					driver.navigate().back();
				} else {
					driver.navigate().back();
				}

			}

			getCatalogbtnPageNext().click();
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
	     		
	     	getCatalogbtnPageNext().click();
	        
	     }
	     return supplierList;
	}
	 
	 public boolean setFieldValueForWebElement(WebElement we, String strFieldValue) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = we.isDisplayed();
			we.sendKeys(strFieldValue);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	 
	 public boolean setFieldValue(String strFieldValue, String strFormName, String strControlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(strFormName,strControlName);

		try {
			bFlag = we.isDisplayed();
			we.sendKeys(strFieldValue);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;	
	}

	public ArrayList<String> getCatalogData() {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < lstDescription.size(); i++) {
			wait(500);
			list.add(lstDescription.get(i).getText());

		}

		return list;
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
	
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
	
	public String getValidationMessageElement(String strFormName,String strGridControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	
		String strValidationMsg = objAdjunoUILibrary.getValidationMessageElement(strFormName, strGridControlName);
		if(!isNullOrBlank(strValidationMsg))
		{
			
		}
		else
		{
			strValidationMsg ="";
		}
		
		return strValidationMsg;
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
	
	
	public String getDate(int days, String pattern) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		// dd MMM yyyy HH:mm
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.format(cal.getTime());

		return dateFormat.format(cal.getTime());
	}
	
	
	public List<Date> getArrivedDate() throws ParseException {
		List<Date> list = new ArrayList<Date>();
	  
		for (int i = 0; i <= getLstETAFrom().size()-1; i++) {
			
			Date arrivalDate = new SimpleDateFormat("dd/mm/yyyy").parse(getLstETAFrom().get(i).getText());
			list.add(arrivalDate);
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
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
	
}
