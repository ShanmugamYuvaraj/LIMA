package com.lima.pagefactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

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

import com.lima.dto.DeliveryPlanning;
import com.lima.dto.DestinationQCPreInspection;
import com.lima.library.AdjunoUILibrary;

public class AdjunoWFLDeliveryPlanningPOM {

	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;

	@FindBy(linkText = "Tools")WebElement lnkTools;
	@FindBy(linkText = "Delivery Planning")WebElement lnkDeliveryPlanning;
	@FindBy(linkText = "Catalog")WebElement lnkCatalog;
	@FindBy(linkText = "Lighthouse")WebElement lnklighthouse;
	@FindBy(xpath = ".//*[@id='OptionsBarSubmitButton']")WebElement catalogbtnApply;
	@FindBy(xpath = ".//*[@name='.PageNext']")WebElement catalogbtnPageNext;
	@FindBy(xpath = ".//*[@name='.PageFirst']")WebElement catalogbtnPagePrev;

	@FindBy(xpath = ".//*[text()='Search']")WebElement chvSearch;
	@FindBy(xpath = ".//*[@id='ProgressForm_FORK_DeliveryPlanning_SearchAgain']")WebElement chkSearchAgain;
	@FindBy(xpath = ".//*[@id='LIMA_Order_DeliveryPlanning_PARAM_FORK_DeliveryPlanning_RefineSearch']")WebElement clkRefineSearch;
	@FindBy(xpath = ".//*[@id='SUT_StageBar_Input']")WebElement chvDeliveryPlanning;
	@FindBy(xpath = ".//*[@id='SUT_StageBar_Select']")WebElement chvSelect;
	@FindBy(xpath = ".//*[@id='SUT_StageBar_Complete']")WebElement chvcomplete;
	@FindBy(xpath = "//a[text()='Sign Out']")WebElement lnkLogout;

	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[3]")WebElement lstProductSKUFieldValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[10]")WebElement lstDeliveryDateValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[7]")WebElement lstDestinationvalue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[5]")WebElement lstLineTypevalue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[6]")WebElement lstConsigneeValue;

	@FindBy(xpath = "//span[text()='Product Card']")WebElement lnkProductCard;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[4]/a")WebElement lnkWFLReference;
	@FindBy(xpath = ".//*[@id='ext-gen43']/div[1]/table/tbody/tr/td[7]/div")WebElement orderNumberValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/p[2]")WebElement txtCount;
	@FindBy(xpath = ".//*[@name='LIMA_Order_DeliveryPlanning_INPUT_chkDebug']")WebElement clkcheckboxToEnabledGrid;

	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[1]/th[1]/input")List<WebElement> lstCheckBox;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div/table/tbody/tr[2]/td[1]/input")List<WebElement> lstCheckBox1;

	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[3]")List<WebElement> lstSKUValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]/a")List<WebElement> lstWFLRef;
	
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-SplitDelivery'])[1]")WebElement clkLineTypeReadeMode;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-SplitLineTypeDesc'])[1]")WebElement clkLineTypeReadmodeSplitgrid;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-ConsigneeDesc']")WebElement clkConsigneeReadMode;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-DestinationDesc']")WebElement clkDestinationReadMode;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-DeliveryDate']")WebElement clkDeliveryDateReadMode;
	@FindBy(xpath = ".//*[@id='ext-gen48']/div/table/tbody/tr/td[18]/div")WebElement clkSplitDeliveryReadMode;
	@FindBy(xpath = ".//div[@class='x-grid3-cell-inner x-grid3-col-DeliveryTime']")WebElement clkDeliveryTimeFieldReadMode;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-SplitDestinationDesc'])[1]") WebElement clkDestinationDropDownSplitGrid;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-DeliveryTime'])[1]")WebElement clkDeliveryTimeFieldReadMode1;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Order_DeliveryPlanning_INPUT_DeliveryTime']/following-sibling::img")WebElement clkdeliveryTimearrow;
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div") List<WebElement> lstDeliveryTime;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[6]")WebElement consigneeValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[5]")WebElement lineTypeValue;
	
	@FindBy(xpath = ".//*[@id='extId_LIMA_Order_DeliveryPlanning_INPUT_LineTypeDesc']/following-sibling::img") WebElement lineTypearrow;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Order_DeliveryPlanning_INPUT_SplitLineTypeDesc']/following-sibling::img") WebElement lineTypeSplitGridarrow;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Order_DeliveryPlanning_INPUT_SplitDestinationDesc']/following-sibling::img")WebElement destinationDropDownSplitGridArrow;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Order_DeliveryPlanning_INPUT_DestinationDesc']/following-sibling::img")WebElement destinationDropDownSKUGridArrow;
	
	@FindBy(xpath = ".//*[@id='CatalogLOCODE_IsDestination']")WebElement isLOCODECheckboxClicked;
	@FindBy(xpath = ".//*[@id='CatalogDestination_StockDC']")WebElement isDestinationCheckBoxClicked;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a")List<WebElement> lstItemDescription;
	@FindBy(xpath = ".//*[@id='CatalogLOCODE_CatalogItemDescription']")WebElement lstDescriptionField;
	@FindBy(xpath = ".//*[@id='CatalogDestination_CatalogItemDescription']")WebElement lstDestinationDescription;
	@FindBy(xpath = ".//*[@id='ext-gen53']/div/table/tbody/tr/td[3]/div/a")List<WebElement> lstNameDescription;
	@FindBy(xpath = ".//*[@id='ext-gen60']/div/table/tbody/tr/td[3]/div/a")List<WebElement> lstSupplierNameDescription;
	@FindBy(xpath = ".//*[@id='CatalogSupplierContact_CatalogItemDescription']")WebElement lstSupplierDescriptionField;
	@FindBy(xpath = ".//*[@id='ext-gen60']/div/table/tbody/tr/td[1]/div")List<WebElement> lstTypeDescription;
	@FindBy(xpath = ".//*[@id='CatalogConsigneeContact_CatalogItemDescription']")WebElement lstConsigneeDescriptionField;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]")List<WebElement> lstSetSupplierValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]")List<WebElement> lstSetNextStageValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]/a")List<WebElement> lstSetWFLRefValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]/a")List<WebElement> lstWFLRefValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[3]")List<WebElement> lstProductSKUValue;
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div")List<WebElement> lstDestinationFieldValue;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[8]")List<WebElement> lstQuantity;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[10]")List<WebElement> lstDeliveryDate;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[7]")List<WebElement> lstDestination;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[5]")List<WebElement> lstLineType;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/div/table/tbody/tr/td[6]")List<WebElement> lstSetConsigneeValue;

	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[3]/td[3]")WebElement secondSKUValue;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[3]/td[5]") WebElement secondLineType;
	@FindBy(xpath = ".//*[@id='ext-gen24']/div[2]/table/tbody/tr/td[4]/div")WebElement selectSecondRowOrderGrid;
	@FindBy(xpath=".//*[@id='ext-gen48']/div[2]/table/tbody/tr/td[9]/div") WebElement selectSecondRowSKUGrid;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[3]/td[6]") WebElement secondConsigneeValue;
	/*
	@FindBy(xpath = "(.//*[@class='x-combo-list-inner'])[3]/div[contains(@class,'x-combo-list-item')]")List<WebElement> lstLineTypeValue;
	@FindBy(xpath = "(.//*[@class='x-combo-list-inner'])[2]/div[contains(@class,'x-combo-list-item')]")List<WebElement> lstLineTypeSplitGridValue;
	@FindBy(xpath="(.//*[@class='x-combo-list-inner'])[1]/div[contains(@class,'x-combo-list-item')]") List<WebElement> lstDestinationSKUGridDropDown;
*/
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div[contains(@class,'x-combo-list-item')]")List<WebElement> lstLineTypeValue;
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div[contains(@class,'x-combo-list-item')]")List<WebElement> lstLineTypeSplitGridValue;
	@FindBy(xpath="(.//*[@class='x-combo-list-inner'])[2]/div[contains(@class,'x-combo-list-item')]") List<WebElement> lstDestinationSKUGridDropDown;
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div[contains(@class,'x-combo-list-item')]") List<WebElement> lstDestinationDropDownSplitGrid;
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div[contains(@class,'x-combo-list-item')]") List<WebElement> lstConsigneeDropdownvalue;
	@FindBy(xpath = ".//*[@class='x-combo-list-inner']/div[contains(@class,'x-combo-list-item')]")WebElement clkConsigneeSplitGridDropdown;
	
	@FindBy(xpath="(.//*[@class='x-combo-list-inner'])/div[@class='x-combo-list-item']")List<WebElement> dropDownValues;
	
	@FindBy(xpath = "(.//*[@class='x-combo-list-inner'])[1]/div")List<WebElement> lstConsigneeHiddenValue;
	@FindBy(xpath = ".//*[@id='ext-gen170']/div/table/tbody/tr/td[4]/div") List<WebElement> lstAssociationDescription;
	@FindBy(xpath = "//p[text()='No items were found.']")WebElement strValidationMsg;
	@FindBy(xpath = ".//p[text()='You must correct all validation failures before progressing.']")WebElement strGridValidationMsg;

	@FindBy(xpath = ".//*[@id='ext-gen154']/div[1]/table/tbody/tr/td[1]/div")WebElement strProductValue;
	@FindBy(xpath = ".//button[text()='Add row']")WebElement clkAddRow;
	@FindBy(xpath = ".//button[text()='Delete rows']") WebElement clkDeleteRow;
	@FindBy(xpath=".//button[text()='Yes']") WebElement clkYesButton;
	
	@FindBy(xpath="(.//*[@class='x-grid3-cell-inner x-grid3-col-SplitDelivery'])[1]")WebElement splitDeliveryreadOnly;
	@FindBy(xpath= ".//*[@id='extId_LIMA_Order_DeliveryPlanning_INPUT_SplitDelivery']/following-sibling::img")WebElement splitDeliveryArrow;
	
	@FindBy(xpath = ".//*[@class='x-grid3-hd-inner x-grid3-hd-SplitID']")WebElement isDisplayed;
	@FindBy(xpath = ".//*[@class='x-tool x-tool-toggle']")WebElement viewdetailsbtn;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	

	
	public WebElement getDestinationDropDownSKUGridArrow() {
		return destinationDropDownSKUGridArrow;
	}
	
	public WebElement getSplitDeliveryreadOnly() {
		return splitDeliveryreadOnly;
	}

	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}

	public WebElement getSplitDeliveryArrow() {
		return splitDeliveryArrow;
	}

	

	public WebElement getViewdetailsbtn() {
		return viewdetailsbtn;
	}

	public WebElement getDestinationDropDownSplitGridArrow() {
		return destinationDropDownSplitGridArrow;
	}

	public List<WebElement> getLstLineTypeSplitGridValue() {
		return lstLineTypeSplitGridValue;
	}

	public WebElement getLineTypeSplitGridarrow() {
		return lineTypeSplitGridarrow;
	}

	public WebElement getLineTypearrow() {
		return lineTypearrow;
	}

	public List<WebElement> getlstConsigneeHiddenValue() {
		return lstConsigneeHiddenValue;
	}
	
	public List<WebElement> getLstConsigneeDropdownvalue() {
		return lstConsigneeDropdownvalue;
	}

	public WebElement getClkConsigneeSplitGridDropdown() {
		return clkConsigneeSplitGridDropdown;
	}

	public List<WebElement> getlstDestinationDropDownSplitGrid() {
		return lstDestinationDropDownSplitGrid;
	}

	
	public WebElement getClkDestinationDropDownSplitGrid() {
		return clkDestinationDropDownSplitGrid;
	}

	public List<WebElement> getLstDestinationDropDown() {
		return lstDestinationSKUGridDropDown;
	}
	
	public List<WebElement> getDropDownValues() {
		return dropDownValues;
	}

	public WebElement getIsDisplayed() {
		return isDisplayed;
	}

	public List<WebElement> getLstAssociationDescription() {
		return lstAssociationDescription;
	}

	public WebElement getLineTypeValue() {
		return lineTypeValue;
	}

	public WebElement getConsigneeValue() {
		return consigneeValue;
	}

	public WebElement getSecondConsigneeValue() {
		return secondConsigneeValue;
	}

	public WebElement getSelectSecondRowSKUGrid() {
		return selectSecondRowSKUGrid;
	}

	public WebElement getSecondLineType() {
		return secondLineType;
	}

	public WebElement getClkYesButton() {
		return clkYesButton;
	}

	public WebElement getClkDeleteRow() {
		return clkDeleteRow;
	}

	public WebElement getClkAddRow() {
		return clkAddRow;
	}
	
	

	public WebElement getClkDeliveryTimeFieldReadMode1() {
		return clkDeliveryTimeFieldReadMode1;
	}

	public WebElement getClkdeliveryTimearrow() {
		return clkdeliveryTimearrow;
	}

	public List<WebElement> getLstDeliveryTime() {
		return lstDeliveryTime;
	}

	public WebElement getClkDeliveryTimeFieldReadMode() {
		return clkDeliveryTimeFieldReadMode;
	}

	public WebElement getChkSearchAgain() {
		return chkSearchAgain;
	}


	public WebElement getClkLineTypeReadmodeSplitgrid() {
		return clkLineTypeReadmodeSplitgrid;
	}

	public WebElement getClkSplitDeliveryReadMode() {
		return clkSplitDeliveryReadMode;
	}

	public WebElement getSelectSecondRowOrderGrid() {
		return selectSecondRowOrderGrid;
	}

	public WebElement getSecondSKUValue() {
		return secondSKUValue;
	}

	public WebElement getLstProductSKUFieldValue() {
		return lstProductSKUFieldValue;
	}

	public List<WebElement> getLstDestinationFieldValue() {
		return lstDestinationFieldValue;
	}

	public WebElement getLstDestinationDescription() {
		return lstDestinationDescription;
	}

	public WebElement getIsDestinationCheckBoxClicked() {
		return isDestinationCheckBoxClicked;
	}

	public WebElement getClkConsigneeReadMode() {
		return clkConsigneeReadMode;
	}

	public WebElement getClkDestinationReadMode() {
		return clkDestinationReadMode;
	}

	public WebElement getClkDeliveryDateReadMode() {
		return clkDeliveryDateReadMode;
	}

	public List<WebElement> getLstDestination() {
		return lstDestination;
	}

	public List<WebElement> getLstDeliveryDate() {
		return lstDeliveryDate;
	}

	public List<WebElement> getLstQuantity() {
		return lstQuantity;
	}

	public WebElement getClkcheckboxToEnabledGrid() {
		return clkcheckboxToEnabledGrid;
	}

	public List<WebElement> getLstLineTypeValue() {
		return lstLineTypeValue;
	}

	public WebElement getClkLineTypeReadeMode() {
		return clkLineTypeReadeMode;
	}

	public WebElement getStrGridValidationMsg() {
		return strGridValidationMsg;
	}

	public List<WebElement> getLstLineType() {
		return lstLineType;
	}

	public List<WebElement> getLstProductSKUValue() {
		return lstProductSKUValue;
	}

	public WebElement getStrProductValue() {
		return strProductValue;
	}

	public List<WebElement> getLstWFLRefValue() {
		return lstWFLRefValue;
	}

	public List<WebElement> getLstSetWFLRefValue() {
		return lstSetWFLRefValue;
	}

	public List<WebElement> getLstSetNextStageValue() {
		return lstSetNextStageValue;
	}

	public List<WebElement> getLstSetConsigneeValue() {
		return lstSetConsigneeValue;
	}

	public List<WebElement> getLstSetSupplierValue() {
		return lstSetSupplierValue;
	}

	public List<WebElement> getLstWFLRef() {
		return lstWFLRef;
	}

	public List<WebElement> getLstSKUValue() {
		return lstSKUValue;
	}

	public List<WebElement> getLstSupplierNameDescription() {
		return lstSupplierNameDescription;
	}

	public WebElement getLstConsigneeDescriptionField() {
		return lstConsigneeDescriptionField;
	}

	public List<WebElement> getLstTypeDescription() {
		return lstTypeDescription;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkDeliveryPlanning() {
		return lnkDeliveryPlanning;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}

	public WebElement getLnklighthouse() {
		return lnklighthouse;
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

	public WebElement getChvSearch() {
		return chvSearch;
	}

	public WebElement getClkRefineSearch() {
		return clkRefineSearch;
	}

	public WebElement getChvDeliveryPlanning() {
		return chvDeliveryPlanning;
	}

	public WebElement getChvSelect() {
		return chvSelect;
	}

	public WebElement getChvcomplete() {
		return chvcomplete;
	}

	public WebElement getLnkLogout() {
		return lnkLogout;
	}

	public WebElement getLnkProductCard() {
		return lnkProductCard;
	}

	public WebElement getLnkWFLReference() {
		return lnkWFLReference;
	}

	public WebElement getOrderNumberValue() {
		return orderNumberValue;
	}

	public WebElement getTxtCount() {
		return txtCount;
	}

	public List<WebElement> getLstDescription() {
		return lstDescription;
	}

	public List<WebElement> getLstCheckBox() {
		return lstCheckBox;
	}
	public List<WebElement> getLstCheckBox1() {
		return lstCheckBox1;
	}

	public WebElement getLOCODEIsCheckboxClicked() {
		return isLOCODECheckboxClicked;
	}

	public List<WebElement> getLstItemDescription() {
		return lstItemDescription;
	}

	public WebElement getLstDescriptionField() {
		return lstDescriptionField;
	}

	public List<WebElement> getLstNameDescription() {
		return lstNameDescription;
	}

	public WebElement getLstSupplierDescriptionField() {
		return lstSupplierDescriptionField;
	}

	public WebElement getStrValidationMsg() {
		return strValidationMsg;
	}

	public WebElement getcatalogbtnPageNext() {
		return catalogbtnPageNext;
	}

	public WebElement getLstDeliveryDateValue() {
		return lstDeliveryDateValue;
	}

	public WebElement getLstDestinationvalue() {
		return lstDestinationvalue;
	}

	public WebElement getLstLineTypevalue() {
		return lstLineTypevalue;
	}

	public WebElement getLstConsigneeValue() {
		return lstConsigneeValue;
	}

	public AdjunoWFLDeliveryPlanningPOM(WebDriver driver) {
		this.driver = driver;

		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}

	public boolean clearInputField(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try {
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

	public ArrayList<String> getDropdownValues(String strFormName,String strControlName) {

		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		ArrayList<String> dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;
	}

	public boolean setFieldValue(String strFieldValue, String formName,String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);

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
		ArrayList<String> supplierList = new ArrayList<String>();
		for (int i = 0; i < lstDescription.size(); i++) {
			supplierList.add(lstDescription.get(i).getText());

		}

		return supplierList;
	}

	public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
		String strMessage = "";
		for (int i = 0; i <=catalogList.size() - 1; i++) {

			for (int j = 0; j <=dropDownListList.size() - 1; j++) {
				if(!isNullOrBlank(catalogList.get(j))){
					if (dropDownListList.get(i).equalsIgnoreCase(catalogList.get(j))) {
						System.out.println(dropDownListList.get(i) + " value is matching "+ catalogList.get(j));
						break;
					} else {
						
						if (j <= catalogList.size()) {
							strMessage = strMessage + " In Delivery planning tool \""+ catalogList.get(i)+ "\" value is not found in dropdown ";
						}
					}
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
                        /*if(i==list.size()-1){
                            strMessage = strMessage + tempVal + "from Xml is not found in Grid";
                        } */                               
                    }
               }
            
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

	// check grid value is read only
	public boolean checkFieldIsReadOnlyInGrid(String strFormName,String strControlName, int nRow, String strColumnName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		try {
			we = objAdjunoUILibrary.getGridCellElement(strFormName,strControlName, nRow, strColumnName);
			we.click();
			wait(5000);

			we = objAdjunoUILibrary.getGridCellElementEditor(strFormName,strControlName, nRow, strColumnName);
			bFlag = we.isDisplayed();
			we.sendKeys("");
			wait(1000);

		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (Exception e) {
			bFlag = false;
		}
		return bFlag;
	}

	public int valCount() {
		String strCount = getTxtCount().getText();
		String[] vals = strCount.split("of ");
		int nCount = Integer.parseInt(vals[1]);

		nCount = nCount / 50;
		if (nCount % 50 == 0) {
			nCount = 1;
		} else {
			nCount++;
		}

		return nCount;
	}

	public ArrayList<String> getCatalogTableData(int nCount) // 5
	{
		ArrayList<String> supplierList = new ArrayList<String>();
		for (int j = 0; j <= nCount - 1; j++) {
			for (int i = 0; i < lstDescription.size(); i++) {
				supplierList.add(lstDescription.get(i).getText());

			}

			getcatalogbtnPageNext().click();

		}

		return supplierList;
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

	public String verifySearchValue(String strValue, List<WebElement> wbList) {
		String strMessage = "";
		for (int i = 0; i <= wbList.size() - 1; i++) {

			if (!wbList.get(i).getText().isEmpty()&& wbList.get(i).getText().equalsIgnoreCase(strValue)) {
				System.out.println(strValue + " is matching "+ wbList.get(i).getText());
			} else {
				if (wbList.size() == 0) {
					strMessage = strMessage + "In Delivery planning tool \""+ wbList.get(i).getText()+ "\" value is not matched with \" " + strValue +"\"";
				}
			}
		}
		return strMessage;
	}

	public boolean verifySearchValue1(String strValue, List<WebElement> wbList) {
		boolean bFlag = false;
		for (int i = 0; i <= wbList.size() - 1; i++) {
			try{
				if (!wbList.get(i).getText().isEmpty()&& wbList.get(i).getText().contains(strValue)) {
					System.out.println(strValue + "is matching"+ wbList.get(i).getText());
					bFlag=true;
				}
			} catch (Exception e) {
				bFlag = false;
			}
	
			
		}
		return bFlag;
	}

	public boolean verifyPageIsLoaded(String strFormName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			objAdjunoUILibrary.isPageLoaded(strFormName);
			bFlag = true;
		} catch (Exception e) {
			bFlag = false;
		}
		return bFlag;
	}

	public int getNoOfRowsResulted() {

		return getLstCheckBox().size();
	}

	public ArrayList<DeliveryPlanning> selectMulitpleProducts(int nMaxProductsToSelect) {
		wait(1000);
		int nCounterLen = getNoOfRowsResulted();
		ArrayList<DeliveryPlanning> lstSearchResults = new ArrayList<DeliveryPlanning>();

		if (nCounterLen < nMaxProductsToSelect) {

		} else
			nCounterLen = nMaxProductsToSelect;

		for (int i = 0; i <= nCounterLen - 1; i++) {
			DeliveryPlanning dqc = new DeliveryPlanning(getLstWFLRefValue().get(i).getText(),getLstProductSKUValue().get(i).getText(), getLstLineType().get(i).getText(), getLstSetConsigneeValue().get(i).getText(), getLstQuantity().get(i).getText(),getLstDeliveryDate().get(i).getText());
			wait(2000);
			getLstCheckBox().get(i).click();
			lstSearchResults.add(dqc);
		}
		return lstSearchResults;
	}
	
	public ArrayList<DeliveryPlanning> selectMulitpleProducts1(int nMaxProductsToSelect) {
		wait(1000);
		int nCounterLen = getNoOfRowsResulted();
		ArrayList<DeliveryPlanning> lstSearchResults = new ArrayList<DeliveryPlanning>();

		if (nCounterLen < nMaxProductsToSelect) {

		} else
			nCounterLen = nMaxProductsToSelect;

		for (int i = 0; i <= nCounterLen - 1; i++) {
			DeliveryPlanning dqc = new DeliveryPlanning(getLstWFLRefValue().get(i).getText(),getLstProductSKUValue().get(i).getText(), getLstLineType().get(i).getText(), getLstSetConsigneeValue().get(i).getText(), getLstQuantity().get(i).getText(),getLstDeliveryDate().get(i).getText());
			wait(2000);
			getLstCheckBox1().get(i).click();
			lstSearchResults.add(dqc);
		}
		return lstSearchResults;
	}

	public String verifyProductsDataOnGrid(String strFormName,String strControlName, ArrayList<DeliveryPlanning> lstSearchResults) {
		String strReturnMessage = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		int n1 = lstSearchResults.size();

		long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
		String strProductSKUValue = "";
		String strLineTypeValue = "";
		String strconsigneeValue = "";
		String strquantityValue = "";
		String strdeliveryDateValue = "";
		boolean bProductIDFound = false;

		if (n1 == n2) {
			for (int i = 0; i <= n1 - 1; i++) {
				strLineTypeValue = lstSearchResults.get(i).getLineType();
				System.out.println(strLineTypeValue);
				strconsigneeValue = lstSearchResults.get(i).getConsignee();
				System.out.println(strconsigneeValue);
				strquantityValue = lstSearchResults.get(i).getQuantity();
				System.out.println(strquantityValue);
				strdeliveryDateValue = lstSearchResults.get(i).getDeliveryDate();
				System.out.println(strdeliveryDateValue);

				for (int j = 0; j <= n2 - 1; j++) {
					if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"LineTypeDesc").getText().equalsIgnoreCase(strLineTypeValue)) {
						bProductIDFound = true;
						
						if (!(compareTwoStringValuesToSame(strLineTypeValue,objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"LineTypeDesc").getText())))
							strReturnMessage = strReturnMessage+ "For LineType '" + strLineTypeValue+ "', is issue on the Grid;";
				 		System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName,strControlName, j, "LineTypeDesc").getText());

						if (!(compareTwoStringValuesToSame(strconsigneeValue,objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"ConsigneeDesc").getText())))
							strReturnMessage = strReturnMessage+ "For Consignee '" + strconsigneeValue+ "', is issue on the Grid;";
				 		System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName,strControlName, j, "ConsigneeDesc").getText());

						if (!(compareTwoStringValuesToSame(strquantityValue,objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"TotalPOQuantity").getText())))
							strReturnMessage = strReturnMessage+ "For Quantity '" + strquantityValue+ "', is issue on the Grid;";
				 		System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName,strControlName, j, "TotalPOQuantity").getText());

						if (!(compareTwoStringDatesAreEqual(strdeliveryDateValue,objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"DeliveryDate").getText())))
							strReturnMessage = strReturnMessage+ "For Deliverydate '"+ strdeliveryDateValue+ "', is issue on the Grid;";
				 		System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName,strControlName, j, "DeliveryDate").getText());
						break;
					} else {
						if (j == n2 - 1) {
							strReturnMessage = strReturnMessage+ "In delivery planning tool Product SKU" + strProductSKUValue+ "not found in the grid";
						}

					}
				}
			}
		} else {
			strReturnMessage = " In delivery planning tool \"The number of Records selected in the search results do not match with the number of Records on the grid.\" ";
		}

		return strReturnMessage;
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

	public void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}

	public boolean clickChevron(WebElement we) {
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

	public long getNoOfRowsinGrid(String strFormName, String strGridControlName) {

		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		long row = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strGridControlName);

		return row;
	}

	public boolean clickcheckbox(String strFormName, String strGridControlName,
			int nRow, String strColumnName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName,	strGridControlName, nRow, strColumnName);

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

	public boolean isCheckboxOnGridChecked(String strFormName,String strGridControlName, int nRow, String strColumnName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		boolean we = objAdjunoUILibrary.isCheckboxOnGridChecked(strFormName,strGridControlName, nRow, strColumnName);

		return we;

	}

	public boolean setFieldValueForGridCell(String strFormName,String strGridControlName, int nRow, String strColumnName,String strValue) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			objAdjunoUILibrary.setGridCellValue(strFormName,strGridControlName, nRow, strColumnName, strValue);
			WebElement we;

			we = objAdjunoUILibrary.getGridCellElementEditor(strFormName,	strGridControlName, nRow, strColumnName);
			wait(1200);
			we.sendKeys(Keys.TAB);
			
			bFlag = true;
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
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

	public String getGridCellValue(String strFormName,String strGridControlName, int nRow, String strColumnName) {

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		String value = objAdjunoUILibrary.getGridCellElementValue(strFormName,strGridControlName, nRow, strColumnName);

		return value;

	}

	public String duplicateValue(List<WebElement> wbList) {
		String strMessage = "";
		for (int i = 0; i < wbList.size(); i++) {
			for (int j = i + 1; j < wbList.size(); j++) {
				if (!wbList.get(i).getText().isEmpty()&& wbList.get(i).getText().equalsIgnoreCase(wbList.get(j).getText())) {

				} else {
					if (wbList.size() == 0) {
						strMessage = strMessage + "In Delivery planning tool \" "+ wbList.get(i).getText()+ "\"value is not matched with \""+ wbList.get(j).getText()+"\"";
					}
				}

			}
		}
		return strMessage;
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

	public String getElementValue(String formName, String controlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strElementValue = objAdjunoUILibrary.getElemetValue(formName, controlName);

		/*if (objAdjunoUILibrary.isPageLoaded(formName)) {
			strElementValue = we.getText();
		}*/
		return strElementValue;
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

	public boolean isCheckBoxClicked(WebElement we) {
		boolean bFlag;
		try {
			bFlag = we.isSelected();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}

	public boolean checkNegativeNumber(String str){
		boolean bFlag;
		char ch=str.charAt(0);
		char negative='-';
		if(ch==negative){
			bFlag=true;
		}else{
			bFlag=false;
		}
		return bFlag;
		
	}
	public String differenceValue(String s1,String s2){
		int value1= Integer.parseInt(s1);
		int value2= Integer.parseInt(s2);
		int difference =value1-value2;
		String str= new Integer(difference).toString();
		System.out.println("difference "+str);
		return str;
	}
	
	public boolean clickGridField(String strFormName, String strGridControlName, int nRow, String strColumnName){
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		try
		{
			we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
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

	public String getElemetValue(String strFormName, String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		String strValidationMsg = objAdjunoUILibrary.getElemetValue(strFormName, strControlName);
		if (!isNullOrBlank(strValidationMsg)) {

		} else {
			strValidationMsg = "";
		}

		return strValidationMsg;

	}

	public ArrayList<String> getCatalogTableData(int nCount,WebElement isCheckboxClicked, WebElement lstDescriptionField) // 5
	{
		boolean bFlag;
		ArrayList<String> supplierList = new ArrayList<String>();
		for (int j = 0; j <= nCount - 1; j++) {
			for (int i = 0; i < lstItemDescription.size(); i++) {
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

	// Compare two Dates are Equal
	public boolean compareTwoStringDatesAreEqual(String strValue1,String strValue2) {
		boolean bFlag = true;
		if ((!(isNullOrBlank(strValue1))) && (!(isNullOrBlank(strValue2)))) {
			if ((strValue1.toLowerCase()).contains(strValue2.toLowerCase())) {
			//	System.out.println(strValue1 + "is matching to " + strValue2);

			} else {
				bFlag = false;
			}
		} else {
			bFlag = false;
		}

		return bFlag;
	}
	
	public ArrayList<String> getCatalogTableDataDestinationType(int nCount, WebElement lstDescriptionField ,List<WebElement> wbList,String strValue) // 5
	{
		boolean bFlag;
		ArrayList<String> supplierList = new ArrayList<String>();
		for (int j = 0; j <= nCount - 1; j++) {
			for (int i = 0; i < lstItemDescription.size(); i++) {
				lstItemDescription.get(i).click();
				
				if (verifySearchValue1(strValue, wbList)) {
					wait(1000);
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


	public ArrayList<String> getCatalogTableData(List<WebElement> lstNameDescription,WebElement lstConsigneeDescriptionField) {

		ArrayList<String> supplierList = new ArrayList<String>();
		for (int i = 0; i < lstItemDescription.size(); i++) {
			lstItemDescription.get(i).click();

			for (int k = 0; k < lstNameDescription.size(); k++) {

				if (lstNameDescription.get(k).getText().equalsIgnoreCase("MVPCust02")) {
					supplierList.add(lstConsigneeDescriptionField.getText());
					break;
				}
			}

			driver.navigate().back();
		}
		return supplierList;
	}

	public ArrayList<String> getCatalogSupplierTableData() {

		ArrayList<String> supplierList = new ArrayList<String>();
		for (int i = 0; i < lstItemDescription.size(); i++) {
			lstItemDescription.get(i).click();

			for (int k = 0; k < lstTypeDescription.size(); k++) {

				if (lstTypeDescription.get(k).getText().equalsIgnoreCase("Customer")) {
					supplierList.add(lstSupplierDescriptionField.getText());
					break;
				}
			}

			driver.navigate().back();
		}
		return supplierList;
	}

	// get the validation message other then Grid
	public String getValidationMessageOtherthenField(WebElement we) {

		String validationMs = we.getText();

		return validationMs;

	}
	
	public String randomIntegerNo(){
			
		Random rand = new Random();
		String randomPo = "";
		int rand_int = rand.nextInt(100);

		randomPo = "BR- "+rand_int;
		return randomPo;
	}

	
	public String getColumnCaptionValue(String strFormName,String strGridControlName, String strColumnName, int nLevel) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String captionName = objAdjunoUILibrary.getGridColumnCaption(strFormName, strGridControlName, strColumnName, nLevel);

		return captionName;
	}

	
	// get the grid drop down value
		public ArrayList<String> getGridDropdownvalues(List<WebElement> we) {
			ArrayList<String> dropdownList = new ArrayList<String>();
			System.out.println(we.size());
			for (int i = 0; i <= we.size() - 1; i++) {
				System.out.println(we.get(i).getText());
				dropdownList.add(we.get(i).getText());

			}
			return dropdownList;

		}

}
