package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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

public class AdjunoLIMAManifestPOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	
	@FindBy(linkText="Lighthouse") WebElement lnkLightHouse;
	
	@FindBy(linkText="Manifest") WebElement lnkManifest;
	
	@FindBy(linkText="Shipment Manifest") WebElement lnkManifest2;
	
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]") List<WebElement> description;
	
	@FindBy(xpath=".//*[@id='div_LIMA_Shipment_SUT_Manifest_Param_PARAM_Customer']/div/div/input[2]/img") WebElement drpDownImag;
	
	@FindBy(xpath=".//*[@class='x-combo-list-inner']") List<WebElement> dropdown;
	
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	
	@FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	
	@FindBy(xpath=".//*[@class='FindDetail']/table/tbody/tr/td[2]") List<WebElement> colVendor ;
	
	@FindBy(xpath=".//*[@class='FindDetail']/table/tbody/tr/td[4]/a") List<WebElement> colCustomer;
	
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	
	@FindBy(xpath=".//*[@name='Manifest']") WebElement chvManifest;
	
	@FindBy(xpath=".//*[@value='Equipment Refrigeration']") WebElement equipmentRef;
	
	@FindBy(xpath=".//*[@value='Bkd Ctns']") WebElement bkdCtns;
	
	@FindBy(xpath=".//*[@value='Ctns']") WebElement ctns;
	
	@FindBy(xpath=".//*[@class='FindDetail']/table/tbody/tr[2]/td[22]") WebElement refrigeration;
	
	@FindBy(xpath=".//*[@class='List']/tbody/tr[2]/td[5]") List<WebElement> weEquipment;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[9]") List<WebElement> weEquipment2;
	
	@FindBy(xpath=".//*[@class='List']/tbody/tr[2]/td[20]/font") List<WebElement> weManifest;
	
	@FindBy(xpath=".//*[@class='List']/tbody/tr/td[18]") List<WebElement> weDc;
	
	@FindBy(xpath=".//*[@class='List']/tbody/tr[2]/td[22]") List<WebElement> weRefrigeration;
	
	@FindBy(xpath=".//*[@class='List']/tbody/tr[2]/td[17]") List<WebElement> weRefrigeration2;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[1]/input") List<WebElement> selectBox;
		
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[10]") List<WebElement> weEquipmentRef;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[14]") List<WebElement> weCertificate;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[2]/div[5]/span") WebElement chvManifest2;
	
	@FindBy(xpath=".//*[text()='Review']") WebElement chvReview;
	
	@FindBy(xpath=".//*[@value='Review']") WebElement chvReview2;
	
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	
	@FindBy(xpath=".//*[@value='Complete']") WebElement chvComplete2;
	
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement updateMessage;
	
	@FindBy(xpath=".//*[text()='Processed 100%']") WebElement process;
	
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement trackReference;

	@FindBy(xpath=".//*[text()='No items were found.']") WebElement noItem;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[4]") WebElement poNum;
	
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[3]") WebElement warningMessage;
	
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[1]") WebElement thOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[2]") WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[3]") WebElement thETDDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[4]") WebElement thETADate;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[5]") WebElement thCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[6]") WebElement thLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[7]") WebElement thLoadRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[8]") WebElement thContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[1]/th[9]") WebElement thEquipment;
		
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[2]") WebElement thVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[3]") WebElement thHBL;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[4]") WebElement thPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[5]") WebElement thProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[6]") WebElement thIdentifier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[7]") WebElement thCustomerRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[8]") WebElement thBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[9]") WebElement thQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[10]") WebElement thCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[11]") WebElement thCBM;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[12]") WebElement thPackType;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[13]") WebElement thFreightTerms;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[14]") WebElement thDC;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[15]") WebElement thmanifestStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[1]/th[16]") WebElement thDailog;
	
	
	
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> trOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> trVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[3]") List<WebElement> trETDDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[4]") List<WebElement> trETADate;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[5]") List<WebElement> trCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[6]") List<WebElement> trLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[7]") List<WebElement> trLoadRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[8]") List<WebElement> trContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[9]") List<WebElement> trEquipment;	
	
	
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[2]") List<WebElement> trVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[3]") List<WebElement> trHBL;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[4]") List<WebElement> trPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[5]") List<WebElement> trProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[6]") List<WebElement> trIdentifier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[7]") List<WebElement> trCustomerRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[8]") List<WebElement> trBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[9]") List<WebElement> trQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[10]") List<WebElement> trCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[11]") List<WebElement> trCBM;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[12]") List<WebElement> trPackType;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[13]") List<WebElement> trFreightTerms;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[14]") List<WebElement> trDC;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[15]") List<WebElement> trmanifestStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[16]") List<WebElement> trDailog;
	@FindBy(xpath=".//*[@id='LIMA_Shipment_SUT_Manifest_Param_PARAM_Customer']/following-sibling::img") WebElement arrowCustomerDD;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[4]/a") WebElement lnkProduct;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr[2]/td[15]/font") List<WebElement> manifestStatus;
	
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement cancelTitle;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement dailogTitle;
	
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[contains(@class,'col-SKU')]/a") WebElement productLink;
	@FindBy(xpath=".//div[contains(@class, 'x-combo-list-inner') and contains(@style, 'width: 226px; margin-bottom: 8px; height: 221px;')]//*[contains(@class, 'x-combo-list-item')]") List<WebElement> custCustomerDD;

	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[16]") List<WebElement> lstDialog;
	
	
	public List<WebElement> getLstDialog() {
		return lstDialog;
	}

	public AdjunoLIMAManifestPOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);

    }

	public WebElement getBtnOK1() {
		return btnOK1;
	}
	
	public WebElement getProductLink() {
		return productLink;
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
	public List<WebElement> getCustCustomerDD() {
		return custCustomerDD;
	}
	
	public List<WebElement> getManifestStatus() {
		return manifestStatus;
	}
	
	public WebElement getProcess() {
		return process;
	}
	public WebElement getArrowCustomerDD() {
		return arrowCustomerDD;
	}
	
	public WebElement getCancelTitle() {
		return cancelTitle;
	}
	
	public WebElement getDailogTitle() {
		return dailogTitle;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}
	
	public WebElement getBtnViewDetail() {
        return btnViewDetail;
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
	
	public WebElement getTrackReference() {
        return trackReference;
    }

	public List<WebElement> getTrOriginPort() {
		return trOriginPort;
	}

	public List<WebElement> getTrVessel() {
		return trVessel;
	}

	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}

	public List<WebElement> getTrETDDate() {
		return trETDDate;
	}
	
	public WebElement getUpdateMessage() {
		
		return updateMessage;
	}

	public List<WebElement> getTrETADate() {
		return trETADate;
	}

	public List<WebElement> getTrCarrier() {
		return trCarrier;
	}

	public List<WebElement> getTrLoading() {
		return trLoading;
	}

	public WebElement getBtnRun() {
		return btnRun;
	}

	public List<WebElement> getTrLoadRef() {
		return trLoadRef;
	}



	public List<WebElement> getTrContainer() {
		return trContainer;
	}



	public List<WebElement> getTrEquipment() {
		return trEquipment;
	}



	public List<WebElement> getTrVendor() {
		return trVendor;
	}



	public List<WebElement> getTrHBL() {
		return trHBL;
	}



	public List<WebElement> getTrPONumber() {
		return trPONumber;
	}



	public List<WebElement> getTrProduct() {
		return trProduct;
	}



	public List<WebElement> getTrIdentifier() {
		return trIdentifier;
	}



	public List<WebElement> getTrCustomerRef() {
		return trCustomerRef;
	}



	public List<WebElement> getTrBkdQty() {
		return trBkdQty;
	}



	public List<WebElement> getTrQty() {
		return trQty;
	}



	public List<WebElement> getTrCtns() {
		return trCtns;
	}



	public List<WebElement> getTrCBM() {
		return trCBM;
	}



	public List<WebElement> getTrPackType() {
		return trPackType;
	}



	public List<WebElement> getTrFreightTerms() {
		return trFreightTerms;
	}



	public List<WebElement> getTrDC() {
		return trDC;
	}



	public List<WebElement> getTrmanifestStatus() {
		return trmanifestStatus;
	}



	public List<WebElement> getTrDailog() {
		return trDailog;
	}



	public WebElement getWarningMessage() {
		return warningMessage;
	}

	
	public WebElement getLnkProduct() {
		return lnkProduct;
	}
	public WebElement getThOriginPort() {
		return thOriginPort;
	}

	public WebElement getThVessel() {
		return thVessel;
	}

	public WebElement getThETDDate() {
		return thETDDate;
	}

	public WebElement getThETADate() {
		return thETADate;
	}

	public WebElement getThCarrier() {
		return thCarrier;
	}

	public WebElement getThLoading() {
		return thLoading;
	}

	public WebElement getThLoadRef() {
		return thLoadRef;
	}

	public WebElement getThContainer() {
		return thContainer;
	}

	public WebElement getThEquipment() {
		return thEquipment;
	}

	public WebElement getThVendor() {
		return thVendor;
	}

	public WebElement getThHBL() {
		return thHBL;
	}

	public WebElement getThPONumber() {
		return thPONumber;
	}

	public WebElement getThProduct() {
		return thProduct;
	}

	public WebElement getThIdentifier() {
		return thIdentifier;
	}

	public WebElement getThCustomerRef() {
		return thCustomerRef;
	}

	public WebElement getThBkdQty() {
		return thBkdQty;
	}

	public WebElement getThQty() {
		return thQty;
	}

	public WebElement getThCtns() {
		return thCtns;
	}

	public WebElement getThCBM() {
		return thCBM;
	}

	public WebElement getThPackType() {
		return thPackType;
	}

	public WebElement getThFreightTerms() {
		return thFreightTerms;
	}

	public WebElement getThDC() {
		return thDC;
	}

	public WebElement getThmanifestStatus() {
		return thmanifestStatus;
	}

	public WebElement getThDailog() {
		return thDailog;
	}

	public WebElement getPoNum() {
		return poNum;
	}
	
	public WebElement getNoItem() {
		return noItem;
	}
	
	public List<WebElement> getWeRefrigeration() {
		return weRefrigeration;
	}
	
	public WebElement getChvSearch() {
		return chvSearch;
	}

	public WebElement getChvManifest2() {
		return chvManifest2;
	}

	public WebElement getChvReview() {
		return chvReview;
	}
	
	public WebElement getChvReview2() {
		return chvReview2;
	}

	public WebElement getChvComplete() {
		return chvComplete;
	}
	
	public WebElement getChvComplete2() {
		return chvComplete2;
	}

	public List<WebElement> getWeEquipmentRef() {
		return weEquipmentRef;
	}
	
	public List<WebElement> getWeCertificate() {
		return weCertificate ;
	}
	
	public List<WebElement> getWeRefrigeration2() {
		return weRefrigeration2;
	}
	
	public List<WebElement> getWeDc() {
		return weDc;
	}
	
	public WebElement getChvManifest() {
		return chvManifest;
	}

	public List<WebElement> getWeManifest() {
		return weManifest;
	}

	public List<WebElement> getSelectBox() {
		return selectBox;
	}

	public List<WebElement> getWeEquipment() {
		return weEquipment;
	}
	
	public List<WebElement> getWeEquipment2() {
		return weEquipment2;
	}
	
	public WebElement getBkdCtns() {
		return bkdCtns;
	}
	
	public WebElement getRefrigeration() {
		return refrigeration;
	}

	public WebElement getCtns() {
		return ctns;
	}
	
	public WebElement getEquipmentRef() {
		return equipmentRef;
	}
	
	public WebElement getChvSelect() {
		return chvSelect;
	}
	
	public WebElement getDrpDownImag() {
		return drpDownImag;
	}

	public List<WebElement> getDropdown() {
		return dropdown;
	}

	public WebElement getLnkManifest() {
		return lnkManifest;
	}
	
	public WebElement getLnkManifest2() {
		return lnkManifest2;
	}

	public WebElement getBtnApply() {
		return btnApply;
	}

	public List<WebElement> getColVendor() {
		return colVendor;
	}

	public List<WebElement> getColCustomer() {
		return colCustomer;
	}

	public List<WebElement> getDescription() {
		return description;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}

	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
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
	
	public boolean isElementPresent(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(we);
          
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

	public ArrayList<String> getCatalogData() 
	{
		ArrayList<String> supplierList =new ArrayList<String>();
		for (int i = 0; i < description.size(); i++) 
		{
			supplierList.add(description.get(i).getText());
		//	System.out.println(description.get(i).getText());
		}
		
		return supplierList;
	}
	
	/*public ArrayList<String> getDropDownList2(String strFormName, String strControlName)
	{
		ArrayList<String> dropDownList =new ArrayList<String>();
		objAdjunoUILibrary = new AdjunoUILibrary(driver);		
		List<WebElement> weDropDownList = getDropdown();
		System.out.println("dr size:"+weDropDownList.size());
		for (int i = 0; i <= weDropDownList.size()-1; i++) {			
			ArrayList<String> tempList =getDropDownList(strFormName,strControlName);
			System.out.println(weDropDownList.get(i).getText());
			for (int j = 0; j <= tempList.size()-1; j++) {
				
				if(weDropDownList.get(i).getText().contains(tempList.get(j))){
					dropDownList.add(weDropDownList.get(i).getText());
					System.out.println("list data:"+weDropDownList.get(i).getText());
				}
			}
			
		}
		return dropDownList;
			
	}*/
	
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

	public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
		String strMessage = "";
		for (int i = 0; i <= dropDownListList.size()-1; i++) {
			
			for (int j = 0; j <= catalogList.size()-1; j++) {
				if(dropDownListList.get(i).equalsIgnoreCase(catalogList.get(j))){
				//	System.out.println("catolog:"+catalogList.get(j)+"-----"+"dr val:"+dropDownListList.get(i));
					break;
				}else{
					if(j==catalogList.size()-1){
						strMessage = strMessage + dropDownListList.get(i)+" value is not found in catalog ";	
					}
				}
			}
		}
		
		return strMessage;
	}
	
	public String verifyCatalogData2(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
		String strMessage = "";
		for (int i = 0; i <= dropDownListList.size()-1; i++) {
			
			for (int j = 0; j <= catalogList.size()-1; j++) {
				if(i==0){
					continue;
				}else{
					if(dropDownListList.get(i).contains(catalogList.get(j))){
						//	System.out.println("catolog:"+catalogList.get(j)+"-----"+"dr val:"+dropDownListList.get(i));
							break;
						}else{
							if(j==catalogList.size()-1){
								strMessage = strMessage + dropDownListList.get(i)+" value is not found in catalog ";	
							}
						}
				}
				
			}
		}
		
		return strMessage;
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
	
	public String verifyVendorSearchData(String strVendor) 
	{
		String strMessage = "";
		List<WebElement> weVendorList = getColVendor();
		if(weVendorList.size()>0){
			for (int i = 0; i <=weVendorList.size()-1; i++) {
				
				if (!weVendorList.get(i).getText().isEmpty()) {
					if (weVendorList.get(i).getText().equalsIgnoreCase(strVendor)) {
						
					} else {
						strMessage = strMessage + weVendorList.get(i).getText()+ " does not match ";
					}
				}
			}
		}else{
			strMessage = strMessage +" NO search Data found ";
		}	
			
		return strMessage;
	}
	
	public String verifyCustomerSearchData(String strCustomer) 
	{
		String strMessage = "";
		List<WebElement> weCustomerList = getColCustomer();
		System.out.println("clo count:"+weCustomerList.size());
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		if(weCustomerList.size()>0){
			for (int i = 0; i <= weCustomerList.size()-1; i++) {
				
				weCustomerList.get(i).click();
				wait(3000);
				getBtnApply().click();
				wait(3000);
				
				String strPoCustomer = objAdjunoUILibrary.getDropDownElemetValue("LH_PurchaseOrder", "Customer");
				if(strPoCustomer.equalsIgnoreCase(strCustomer)){
					
				}else{
					strMessage = strMessage + strPoCustomer + " does not match "+strCustomer;
				}
				driver.navigate().back();
				wait(3000);
				driver.navigate().back();
				wait(3000);
			}
		}else{
			strMessage = strMessage +" result data is null ";
		}
		return strMessage;
	}

	public String clickProductofDifferentRefrigiration() {
		String strMessage = "";
		List<WebElement> weList = getWeRefrigeration();
		List<WebElement> wemanifest = getWeManifest();
		int nCount = 0;
		System.out.println("we list:"+weList.size()+"manifest list:"+wemanifest.size());
		for (int i = 0; i <= weList.size()-1; i++) {
			if(wemanifest.get(i).getText().equalsIgnoreCase("Manifested")){
				
				if(nCount==2){
					break;
				}
				
				selectBox.get(i).click();
				nCount++;
			}else{
				if(i==weList.size()-1){
					strMessage = "not clicked ";
				}
				
			}
		}		
		return strMessage;
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
	
	private boolean isNullOrBlank(String s)
	{
	      return (s==null || s.trim().equals(""));
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
	public String checkDCField() {
		String strMessage ="";
		List<WebElement> weList = getWeDc();
		for (int i = 0; i <=weList.size()-1; i++) {
			if(!weList.get(i).getText().isEmpty()){
				
			}else{
				strMessage = strMessage +i+" Dc value is Empty ";
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
			if(!isNullOrBlank(tempVal)){
				
			}
			else{
				tempVal ="";
			}
			
       return tempVal;				
	}
	
	
	public WebElement getGridCellElement(String strFormName, String strControlName, int nRow, String strColonmName) {
		WebElement we;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		we = objAdjunoUILibrary.getGridCellElement(strFormName, strControlName,nRow,strColonmName);				
			
       return we;				
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
	
	public boolean checkDoesChevronExist(WebElement chevron) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(chevron);
          
		return bFlag;
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
							strMessage = strMessage+ tempVal+ " from Xml is not found in Grid ";
						}								
					}
    			}
			
			}
     	}	
		return strMessage;
	}
	
	/*public String getWebElementValue(String strFormName, String strControlName){
		WebElement we;
		String strTemp;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		we = objAdjunoUILibrary.getElement(strFormName,strControlName);	
		strTemp = we.getText();
		return strTemp;	
	}*/

	public String getWebElementValue(String strFormName, String strControlName){
		
		String strTemp;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		strTemp = objAdjunoUILibrary.getElemetValue(strFormName,strControlName);	
		
       return strTemp;	
	}
	public int StringToInt(String strVal){
		if(strVal.contains(".")){

			return (int)Double.parseDouble(strVal);
		}else{

			return Integer.parseInt(strVal);
		}
	}
	
	
	public String IntToString(int intVal){
		return String.valueOf(intVal);
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
	  
	  public long getNoOrRowsinGrid(String strFormName, String strControlName) {
		  objAdjunoUILibrary = new AdjunoUILibrary(driver);   
		  long nRow = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
		  return nRow; 
	   }

	public ArrayList<String> getCustdropdownValue () 
	{
		int count = getCustCustomerDD().size();
		System.out.println("xpath count:"+count);
		ArrayList<String> customerList = new ArrayList<String>();

		for (int i = 0; i <= count - 1; i++) {
			customerList.add(getCustCustomerDD().get(i).getText());
		}

		return customerList;
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
			if(list.get(j).getEvent().equalsIgnoreCase("Shipment Manifest")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
				String strAchievedDate = list.get(j).getAcheived();
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
				Date today = new Date();
				
				Date achievedDate = formatter.parse(strAchievedDate);
			
				min = getDifference(today,achievedDate);
				System.out.println("difference is:"+min);
			}
		}
		return min;
	}

	public long getDifference(Date today, Date date) {
		
		if( date == null || today == null ) return 0;

	    return ((today.getTime()/60000) - (date.getTime()/60000));
		
	}
	
	
	    
 }
