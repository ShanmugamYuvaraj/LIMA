package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.hamcrest.Factory;
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

import com.lima.dto.ProductionCheck;
import com.lima.dto.ShipAuthority;
import com.lima.dto.Tracksdata;
import com.lima.dto.VendorBooking;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAVendorBookingPOM {
	
WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Vendor Booking") WebElement lnkVendorBooking;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[text()='Booking']") WebElement chevBooking;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Booking']") WebElement chevBooking1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	@FindBy(linkText="PO Manager") WebElement lnkPoManager;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Edit']") WebElement chvEdit;
	@FindBy(linkText="Home") WebElement lnkHome;
	
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement errMsgNoRecords;
	@FindBy(xpath=".//*[@class='x-panel-header x-unselectable']/div") WebElement btnSearchPanelArrowBtn;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]/input") List<WebElement> lstCheckBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]") List<WebElement> lstPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[3]") List<WebElement> lstProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[7]") List<WebElement> lstVendorRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[9]") List<WebElement> lstPOShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[8]") List<WebElement> lstCustomerRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[10]") List<WebElement> lstPOQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[11]") List<WebElement> lstOpenQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[12]") List<WebElement> lstBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[13]") List<WebElement> lstBkdCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[14]") List<WebElement> lstBkdCBM;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[17]") List<WebElement> lstStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement trPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thFactory;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") WebElement thOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]") WebElement thDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]") WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]") WebElement thVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[8]") WebElement thETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[9]") WebElement thETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[10]") WebElement thLoading;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[11]") WebElement thVendorBookingID;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]") WebElement thPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement thProduct;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement thIdentifier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[5]") WebElement thPriority;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[6]") WebElement thPromotion;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[7]") WebElement thVendorRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[8]") WebElement thCustomerRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[9]") WebElement thPOShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[10]") WebElement thPOQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[11]") WebElement thOpenQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[12]") WebElement thBkdQty;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[13]") WebElement thBkdCtns;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[14]") WebElement thBkdCbm;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[15]") WebElement thDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[16]") WebElement thQC;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[17]") WebElement thBookingStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[18]") WebElement thAuthorityStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[19]") WebElement thDailog;
	
	@FindBy(xpath=".//*[text()='You must make selection(s) before progressing']") WebElement txtProgressingErrMsg;
	@FindBy(xpath=".//*[@id='extId_LIMA_Order_SUTVendorBooking_Booking_Containers_bbar_btn_DeleteRows']") WebElement btnDeleteRowCYContainerRequirement;
	@FindBy(xpath=".//*[@id='LIMA_Order_SUTVendorBooking_Booking_Link_VendorDetails']") WebElement hyperLinkVentorDetails;
	@FindBy(xpath=".//*[@class='x-tool x-tool-close']") WebElement btnClose;
	@FindBy(xpath=".//*[@class='x-window-header x-window-header-noborder x-unselectable x-window-draggable']/span") WebElement VendorDetailsPopup;
	@FindBy(xpath=".//*[@id='LIMA_Order_SUTVendorBooking_Booking_Link_ConsigneeDetails']") WebElement hypeLinkConsigneeDetails;
	@FindBy(xpath=".//*[@id='LIMA_Order_SUTVendorBooking_Booking_PANEL_ConsigneeDetails']") WebElement ConsigneeDetailsPopUp;
	@FindBy(xpath=".//*[.='Consignee Details']/preceding-sibling::div[@class='x-tool x-tool-close']") WebElement btnConsigneeDetailsCloseButton;
	@FindBy(xpath=".//*[text()='Changing mode']") WebElement ChangingModePopup;
	@FindBy(xpath=".//*[text()='Are you sure you want to change the mode?']") WebElement ChangeModeValidationMsg1;
	@FindBy(xpath=".//*[text()='You may lose existing data from Marks and Numbers field.']") WebElement ChangeModeValidationMsg2;
	@FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-Product_AdditionalRequirements']/a") WebElement AdditionaliRequirementHyperLink;
	@FindBy(xpath=".//*[contains(text(),'Additional Requirements for')]") WebElement AddtionalRequirementPopUp;
	@FindBy(xpath=".//*[.='Equipment Selector']/preceding-sibling::div[@class='x-tool x-tool-close']") WebElement btnEquipmentSelectorCloseButton;
	@FindBy(xpath=".//*[@id='extId_LIMA_Order_SUTVendorBooking_Booking_Containers_bbar_btn_AddRow']") WebElement btnAddRowCYContainerRequirementPanel;
	@FindBy(xpath=".//*[@id='div_LIMA_Order_SUTVendorBooking_Booking_EquipmentSelector']/a") WebElement hyperLinkEquipmentSelector;
	@FindBy(xpath=".//*[text()='Delete rows']") WebElement DeleteRowPopUp;
	@FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	@FindBy(xpath=".//*[@class='Title']") WebElement PurchaseOrderPage;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	@FindBy(xpath="(.//*[text()='Shipment Request'])[last()]") WebElement lnkTrackShipmentRelated;
	@FindBy(xpath=".//*[@class='Title']/p") WebElement ShipmentRelatedPage;
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement PoStatusReport;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]/a") WebElement poStatus_Status;
	@FindBy(linkText = "Lighthouse") WebElement lnkLightHouse;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(linkText="Configuration") WebElement lnkConfiguration;  
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]/a") WebElement hyperLinkItemCatalog;
	@FindBy(xpath="(.//*[@class='x-grid3-cell-inner x-grid3-col-AssociatedDescription'])[1]") WebElement catalogAssociatedDescription;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]") List<WebElement> lstCatalogItem;
	@FindBy(xpath="(.//*[@title='Open the Vendor Booking tool'])[1]") WebElement lnkDashBoardVendorBooking;
	@FindBy(xpath="(.//*[@class='x-grid3-cell-inner x-grid3-col-LinkExpectedTab4']/a/span)[3]") WebElement lnkVBExpected;
	@FindBy(xpath="(.//*[@class='x-grid3-cell-inner x-grid3-col-LinkDueTab4']/a/span/font)[3]") WebElement lnkVBDue;
	@FindBy(xpath="(.//*[@class='x-grid3-cell-inner x-grid3-col-LinkCriticalTab4']/a/span/font)[3]") WebElement lnkVBCritical;
	@FindBy(xpath=".//*[contains(text(),'Additional Requirements for')]/preceding-sibling::div[@class='x-tool x-tool-close']") WebElement btnAdditionalRequirementClose;
	String strCompleteMsgID = "ProgressForm_UpdateMessage";
	
	
	public String getStrCompleteMsgID() {
		return strCompleteMsgID;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public WebElement getLnkTools() {
		return lnkTools;
	}
	public WebElement getLnkVendorBooking() {
		return lnkVendorBooking;
	}
	
	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}
	public WebElement getChevSearch() {
		return chevSearch;
	}
	public WebElement getChevSelect() {
		return chevSelect;
	}
	public WebElement getChevBooking() {
		return chevBooking;
	}
	public WebElement getChevComplete() {
		return chevComplete;
	}
	public WebElement getErrMsgNoRecords() {
		return errMsgNoRecords;
	}
	public WebElement getBtnSearchPanelArrowBtn() {
		return btnSearchPanelArrowBtn;
	}
	public List<WebElement> getLstPONumber() {
		return lstPONumber;
	}
	public WebElement getTrPONumber() {
		return trPONumber;
	}
	public WebElement getThVendor() {
		return thVendor;
	}
	public WebElement getThFactory() {
		return thFactory;
	}
	public WebElement getThMode() {
		return thMode;
	}
	public WebElement getThOriginPort() {
		return thOriginPort;
	}
	public WebElement getThDestinationPort() {
		return thDestinationPort;
	}
	public WebElement getThVessel() {
		return thVessel;
	}
	public WebElement getThVoyage() {
		return thVoyage;
	}
	public WebElement getThETD() {
		return thETD;
	}
	public WebElement getThETA() {
		return thETA;
	}
	public WebElement getThLoading() {
		return thLoading;
	}
	public WebElement getThVendorBookingID() {
		return thVendorBookingID;
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
	public WebElement getThPriority() {
		return thPriority;
	}
	public WebElement getThPromotion() {
		return thPromotion;
	}
	public WebElement getThVendorRef() {
		return thVendorRef;
	}
	public WebElement getThCustomerRef() {
		return thCustomerRef;
	}
	public WebElement getThPOShipDate() {
		return thPOShipDate;
	}
	public WebElement getThPOQty() {
		return thPOQty;
	}
	public WebElement getThOpenQty() {
		return thOpenQty;
	}
	public WebElement getThBkdQty() {
		return thBkdQty;
	}
	public WebElement getThBkdCtns() {
		return thBkdCtns;
	}
	public WebElement getThBkdCbm() {
		return thBkdCbm;
	}
	public WebElement getThDestination() {
		return thDestination;
	}
	public WebElement getThQC() {
		return thQC;
	}
	public WebElement getThBookingStatus() {
		return thBookingStatus;
	}
	public WebElement getThAuthorityStatus() {
		return thAuthorityStatus;
	}
	public WebElement getThDailog() {
		return thDailog;
	}
	public WebElement getChevBooking1() {
		return chevBooking1;
	}
	public WebElement getTxtProgressingErrMsg() {
		return txtProgressingErrMsg;
	}
	public List<WebElement> getLstProduct() {
		return lstProduct;
	}
	public List<WebElement> getLstVendorRef() {
		return lstVendorRef;
	}
	public List<WebElement> getLstPOShipDate() {
		return lstPOShipDate;
	}
	public List<WebElement> getLstCustomerRef() {
		return lstCustomerRef;
	}
	public List<WebElement> getLstPOQty() {
		return lstPOQty;
	}
	public List<WebElement> getLstOpenQty() {
		return lstOpenQty;
	}
	public List<WebElement> getLstBkdQty() {
		return lstBkdQty;
	}
	public List<WebElement> getLstBkdCtns() {
		return lstBkdCtns;
	}
	public List<WebElement> getLstBkdCBM() {
		return lstBkdCBM;
	}
	public List<WebElement> getLstCheckBox() {
		return lstCheckBox;
	}
	public WebElement getBtnDeleteRowCYContainerRequirement() {
		return btnDeleteRowCYContainerRequirement;
	}
	public WebElement getHyperLinkVentorDetails() {
		return hyperLinkVentorDetails;
	}
	public WebElement getBtnClose() {
		return btnClose;
	}
	public WebElement getVendorDetailsPopup() {
		return VendorDetailsPopup;
	}
	
	public WebElement getHypeLinkConsigneeDetails() {
		return hypeLinkConsigneeDetails;
	}
	
	public WebElement getBtnConsigneeDetailsCloseButton() {
		return btnConsigneeDetailsCloseButton;
	}
	
	public WebElement getConsigneeDetailsPopUp() {
		return ConsigneeDetailsPopUp;
	}
	
	public WebElement getChangingModePopup() {
		return ChangingModePopup;
	}
	
	public WebElement getChangeModeValidationMsg1() {
		return ChangeModeValidationMsg1;
	}
	
	public WebElement getChangeModeValidationMsg2() {
		return ChangeModeValidationMsg2;
	}
	
	public WebElement getAdditionaliRequirementHyperLink() {
		return AdditionaliRequirementHyperLink;
	}
	
	public WebElement getAddtionalRequirementPopUp() {
		return AddtionalRequirementPopUp;
	}
	public WebElement getBtnEquipmentSelectorCloseButton() {
		return btnEquipmentSelectorCloseButton;
	}
	public WebElement getBtnAddRowCYContainerRequirementPanel() {
		return btnAddRowCYContainerRequirementPanel;
	}
	public WebElement getHyperLinkEquipmentSelector() {
		return hyperLinkEquipmentSelector;
	}
	public WebElement getDeleteRowPopUp() {
		return DeleteRowPopUp;
	}
	public WebElement getBtnYes() {
		return btnYes;
	}
	
	public WebElement getChevComplete1() {
		return chevComplete1;
	}
	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
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
	
	public WebElement getBtnTrackApply() {
		return btnTrackApply;
	}
	
	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
	}
	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}
	public WebElement getPurchaseOrderPage() {
		return PurchaseOrderPage;
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
	public WebElement getLnkTrackShipmentRelated() {
		return lnkTrackShipmentRelated;
	}
	
	public WebElement getShipmentRelatedPage() {
		return ShipmentRelatedPage;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}
	
	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}
	
	public WebElement getBtnRun() {
		return btnRun;
	}
	
	public WebElement getPoStatusReport() {
		return PoStatusReport;
	}
	
	public WebElement getPoStatus_Status() {
		return poStatus_Status;
	}

	public List<WebElement> getLstStatus() {
		return lstStatus;
	}
	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
	}
	public WebElement getBtnApply() {
		return btnApply;
	}
	
	public WebElement getLnkConfiguration() {
		return lnkConfiguration;
	}
	
	public WebElement getLnkPoManager() {
		return lnkPoManager;
	}
	public WebElement getChvEdit() {
		return chvEdit;
	}
	
	public WebElement getHyperLinkItemCatalog() {
		return hyperLinkItemCatalog;
	}
	
	public WebElement getCatalogAssociatedDescription() {
		return catalogAssociatedDescription;
	}
	public List<WebElement> getLstCatalogItem() {
		return lstCatalogItem;
	}
	public WebElement getLnkDashBoardVendorBooking() {
		return lnkDashBoardVendorBooking;
	}
	public WebElement getLnkHome() {
		return lnkHome;
	}
	public WebElement getLnkVBExpected() {
		return lnkVBExpected;
	}
	public WebElement getLnkVBDue() {
		return lnkVBDue;
	}
	public WebElement getLnkVBCritical() {
		return lnkVBCritical;
	}
	public WebElement getBtnAdditionalRequirementClose() {
		return btnAdditionalRequirementClose;
	}
	public AdjunoLIMAVendorBookingPOM(WebDriver driver)
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
       wait(2000);
       action.moveToElement(subLink);
	       
       action.doubleClick();
	       
       action.perform();
	       
       objAdjunoUILibrary = new AdjunoUILibrary(driver);
       String strTitle = "";
      if (objAdjunoUILibrary.isPageLoaded(strFormName))
       {
             strTitle = driver.getTitle();
       }
      else
      {
    	  strTitle = strTitle + "Vendor Booking Search page is not loaded";
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
	
	public boolean isElementPresent(WebElement we) 
	{
		Boolean bFlag;
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
	
	public boolean clearInputField(String strFormName,String strControlName)
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try
		{
			WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
			we.clear();
			
			we.sendKeys(Keys.TAB);	
			bFlag = true;
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
	
	public String getValidationMessageElement(String strFormName, String strControlName)
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
	
	public String getDropDownValue(String strFormName, String strControlName, int nRow)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strValue = "";
		strValue = objAdjunoUILibrary.getDropDownValue(strFormName, strControlName, nRow);
		if(!isNullOrBlank(strValue))
		{
			
		}
		else
		{
			strValue =  "Drop Box value is empty";
		}
		return strValue;
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
	
	public int getNoOfRowsResulted() {
	       
        return getLstPONumber().size();
    }
	
	public ArrayList<VendorBooking> selectMulitpleProducts(int nMaxProductsToSelect)
    {
        int nCounterLen = getNoOfRowsResulted();
        ArrayList<VendorBooking> lstSearchResults = new ArrayList<VendorBooking>();
       
        if (nCounterLen < nMaxProductsToSelect){
        	
        }
       else 
            nCounterLen = nMaxProductsToSelect;
            
        
        for(int i = 0; i <= nCounterLen - 1; i++)
        {          
        	VendorBooking check = new VendorBooking(getLstPONumber().get(i).getText(),getLstProduct().get(i).getText(),getLstVendorRef().get(i).getText(),getLstPOShipDate().get(i).getText(),getLstCustomerRef().get(i).getText(),getLstPOQty().get(i).getText(),getLstOpenQty().get(i).getText(),getLstBkdQty().get(i).getText(),getLstBkdCtns().get(i).getText(),getLstBkdCBM().get(i).getText());
            getLstCheckBox().get(i).click();
            lstSearchResults.add(check);	
        }
		return lstSearchResults;
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
	
	public String verifyProductsDataOnGrid(String strFormName, String strControlName,ArrayList<VendorBooking> lstSearchResults) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	int n1 = lstSearchResults.size();
    	
    	long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
    	
    	
    	String strPONumber = "";
    	String strProduct = "";
    	String strVendorRef = "";
    	String strPOShipDate = "";
    	String strCustomRef = "";
    	String strPOQty = "";
    	String strOpenQty = "";
    	String strBkdQty = "";
    	String strBkdCtns = "";
    	String strBkdCBM = "";
    	
    	String strReturnMessage ="";
    	boolean bProductIDFound = false;
    	    	
    	if(n1==n2)
    	{
        	for (int i=0; i <= n1-1;i++)
        	{
        		strPONumber =lstSearchResults.get(i).getStrPONumber();       		
        		strProduct = lstSearchResults.get(i).getStrProduct(); 
        		strVendorRef = lstSearchResults.get(i).getStrVendorRef();
        		System.out.println("strVendorRef:"+strVendorRef);
        		strPOShipDate = lstSearchResults.get(i).getStrPOShipDate();
        		strCustomRef = lstSearchResults.get(i).getStrCustomerRef();
        		System.out.println("strCustomRef:"+strCustomRef);
        		strPOQty = lstSearchResults.get(i).getStrPOQty();
        		strOpenQty = lstSearchResults.get(i).getStrOpenQty();
        		strBkdQty = lstSearchResults.get(i).getStrBkdQty();
        		System.out.println("strBkdQty:"+strBkdQty);
        		strBkdCtns = lstSearchResults.get(i).getStrBkdCtns();
        		strBkdCBM = lstSearchResults.get(i).getStrBkdCBM();
        		  	
            	for (int j=0; j <= n2-1;j++)
            	{
					if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_PONumber").getText().equalsIgnoreCase(strPONumber))
						  
            		{
            			bProductIDFound = true;
            			
            			
            			if(!(compareTwoStringValuesToSame(strPONumber, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_PONumber").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', PO Number field has issues on PO Details Grid in Bookings page";
            			    
            			if(!(compareTwoStringValuesToSame(strProduct, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "LinkProduct").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', Product field has issues on PO Details Grid in Bookings page";
            			
//            			if(!(compareTwoStringValuesToSame(strVendorRef, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_VendorRef").getText())))
//            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', Vendor ref field has issues on PO Details Grid in Bookings page";
//            			System.out.println("strVendorRef1:"+ objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_VendorRef").getText() );
            			
            			if(!(compareTwoStringValuesToSame(strPOShipDate, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_ShipDate").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', POShipDate field has issues on PO Details Grid in Bookings page";
            			
//            			if(!(compareTwoStringValuesToSame(strCustomRef, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_CustomerRef").getText())))
//            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', CustomRef field has issues on PO Details Grid in Bookings page";
//            			System.out.println("strCustomerRef1:"+ objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_CustomerRef").getText() );
//            			
            			if(!(compareTwoStringValuesToSame(strPOQty, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_POQty").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', POQty field has issues on PO Details Grid in Bookings page";
            			
            			if(!(compareTwoStringValuesToSame(strOpenQty, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_OpenQty").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', OpenQty field has issues on PO Details Grid in Bookings page";
            			
            			if(!(compareTwoStringValuesToSame(strBkdQty, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_BkdQty").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', BkdQty field has issues on PO Details Grid in Bookings page";
            			System.out.println("BkdQty1:"+ objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_BkdQty").getText() );
            			
            			if(!(compareTwoStringValuesToSame(strBkdCtns, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_BkdCtns").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', BkdCtns field has issues on PO Details Grid in Bookings page";
            			
            			if(!(compareTwoStringValuesToSame(strBkdCBM, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Product_BkdCBM").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', BkdCBM field has issues on PO Details Grid in Bookings page";
            		
						break;
            		}

					else 
					{
						if(j==n2-1)
						{
							strReturnMessage = strReturnMessage + "PONumber '"+ strPONumber + "' not found on the grid;";
						}
						
					}
            	}	
        	}
    	}
    	else
    	{
    		strReturnMessage = "The number of products selected in the search results in \"Select page\" do not match with the number of products on the \"Order grid\" under Progress Chevron.";
    	}
    	
		return strReturnMessage;
	}

	public ArrayList<String> getCaptionsList(String strFormName, String strControlName)
	{
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
        	   if(listVal.contains("<br>")){
        		   listVal = listVal.replace("<br>", " ");
        	   }
                if(tempVal.equalsIgnoreCase(listVal)){
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
	
	public String getGridCellValue(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
		 objAdjunoUILibrary = new AdjunoUILibrary(driver);
		 String strGridCellValue="";
		 strGridCellValue = objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, nRow, strColumnName);
		 if(!isNullOrBlank(strGridCellValue))
		 {
			 
		 }
		 else
		 {
			 strGridCellValue="The text of the cell is not available in Despatch Chevron";
		 }
		 return strGridCellValue;
		
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
	
	public String getFieldValue(String strFormName, String strControlName)
	{
		 objAdjunoUILibrary = new AdjunoUILibrary(driver);
		 String strGridCellValue="";
		 strGridCellValue = objAdjunoUILibrary.getElemetValue(strFormName, strControlName);
		 if(!isNullOrBlank(strGridCellValue))
		 {
			 
		 }
		 else
		 {
			 strGridCellValue="The text of the cell is not available";
		 }
		 return strGridCellValue;
		
	}
	
	public String setPresentDate(String strPattern)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		SimpleDateFormat dateformatter = new SimpleDateFormat(strPattern);
		String dtTodayDate = dateformatter.format(new Date());
		
		return dtTodayDate;
	}
	
	public int getNoOfRowsGrid(String strFormName, String strGridControlName)
	{
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        int Rows = (int) objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
        
        return Rows;
        
	}
	
	public String getValueFromReadOnlyMode(String strID)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strMessage = "";
		
		strMessage = objAdjunoUILibrary.getElementValueInReadMode(strID);
		
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
	
	public long getDifference(Date today, Date date) 
	{
        if( date == null || today == null ) return 0;
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
            if(list.get(j).getEvent().equalsIgnoreCase("Shipment Authorisation")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
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
	
	public boolean clickLink(WebElement we)
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
	
	public WebElement getElement(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
				
		return we;		
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
	
	public List<VendorBooking> getStaus() 
	{
		List<VendorBooking> list = new ArrayList<VendorBooking>();
	    
		for (int i = 0; i <= getLstPONumber().size()-1; i++) {
			System.out.println("status:"+getLstStatus().get(i).getText());
			VendorBooking po = new VendorBooking(getLstPONumber().get(i).getText(),getLstStatus().get(i).getText()); 
			//scroll down 
			//JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			list.add(po);
			
			//jse.executeScript("window.scrollBy(0,100)", "");
		}
		
		return list;
	}
	
	public String verifyStaus(String strStatus,String strStatus1,String strStatus2,List<VendorBooking> list) 
	{
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			
			if(list.get(i).getStrStatus().contains(strStatus)|| list.get(i).getStrStatus().contains(strStatus1)||list.get(i).getStrStatus().contains(strStatus2))
			{
				
			}
			
			else{
				
				strMessage = strMessage + "For this PO NO:"+ list.get(i).getStrPONumber()+ "Status is not matching";
			}
		}
		
		return strMessage;
	}
	
	public boolean verifyElementIsInEditableMode(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		try{
		if(we.getAttribute("readonly").equals("true"))
		{
			bFlag = false;
		}
		else
		{
			bFlag = true;
		}
		}
		catch(NullPointerException e)
		{
			bFlag = true;
		}
		return bFlag;
	}
	
	public boolean clickTab(String strFormName, String strTabPanelName, String strTabItem)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		try
		{
		objAdjunoUILibrary.clickTabToMakeItActive(strFormName, strTabPanelName, strTabItem);
		bFlag = true;
		}
		catch(NoSuchElementException e)
		{
			bFlag = false;
		}
		catch(NullPointerException e)
	    {
	           bFlag = false;
	    }
		return bFlag;
	}
	
	public boolean verifyElementIsInReadOnlyMode(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		if(we.getAttribute("readonly").equals("true"))
		{
			bFlag = true;
		}
		else
		{
			bFlag = false;
		}
		return bFlag;
	}
	
	public int getDropDownCount(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		int nDropDownListCount = (int) objAdjunoUILibrary.getDropDownValueCount(strFormName, strControlName);
		
		return nDropDownListCount;
				
	}
	
	
	public ArrayList<String> getDropDownList(String strFormName, String strControlName)
	{
    objAdjunoUILibrary = new AdjunoUILibrary(driver); 
    int nDropDownListCount = getDropDownCount(strFormName, strControlName);    
    
    ArrayList<String> dropDownList = new ArrayList<String>();
    
    for (int i=0; i<= nDropDownListCount - 1; i++)
    {
        dropDownList.add(objAdjunoUILibrary.getDropDownValue(strFormName,strControlName,i));
    }
    
    return dropDownList;
	}
	
	
	
	
	
	
	
}
