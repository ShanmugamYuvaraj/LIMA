package com.lima.pagefactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lima.dto.DocumentReceived;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMADocumentReceivedPOM {

		
		WebDriver driver;
		AdjunoUILibrary objAdjunoUILibrary;
		AdjunoLIMALibrary objAdjunoLIMALibrary;
		
		
		@FindBy(linkText="Tools") WebElement lnkTools;
		@FindBy(linkText="Track") WebElement lnkTrack;
		@FindBy(linkText="Home") WebElement lnkHome;
		@FindBy(linkText="Documents Received") WebElement lnkDocumentReceived;
		@FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
		
		@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
		@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chvSearch1;
		@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
		@FindBy(xpath=".//*[text()='Update']") WebElement chvUpdate;
		@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[2]/div[5]") WebElement chvUpdate1;
		@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
		@FindBy(xpath=".//*[@id='SUT_StageBar_Update']") WebElement chvComplete1;

		@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement updateHeader;
		
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement hBL;
		@FindBy(xpath=".//*[@class='x-tool x-tool-toggle']") WebElement downArrow;
		@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[7]") List<WebElement> lstStatus;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[2]") List<WebElement> lstHBL;
		
		@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]/input") List<WebElement> lstCheckBok;

		@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]/input")  List <WebElement> teSelect_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[2]")  List <WebElement> teHBL_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[3]")  List <WebElement> teVendor_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[4]")  List <WebElement> teCourier_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[5]")  List <WebElement> teCourierAWB_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[6]")  List <WebElement> teStatus_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[7]")  List <WebElement> teDialog_Multi;
		@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[23]/font")  List <WebElement> thShipment_Status;
		
		@FindBy(xpath=".//*[@class='x-combo-list-inner']/div") List<WebElement> lstUpdateCourier;
		@FindBy(xpath=".//*[@id='ext-gen35']/div/table/tbody/tr/td[1]/div") WebElement UpdateCourier;
		@FindBy(xpath=".//*[@id='ext-gen35']/div/table/tbody/tr/td[2]/div") WebElement UpdateCourierAWB;
		@FindBy(xpath=".//*[@id='ext-gen35']/div/table/tbody/tr/td[4]/div") WebElement UpdateDateReceived;
		@FindBy(xpath=".//*[@id='ext-gen35']/div/table/tbody/tr/td[5]/div") List<WebElement> lstUpdateReceipted;
		@FindBy(xpath=".//*[@id='ext-gen35']/div/table/tbody/tr/td[5]/div") WebElement UpdateReceipted;
		@FindBy(xpath=".//*[@class='x-form-text x-form-field x-form-focus']/following-sibling::img") WebElement imgCourier;
		@FindBy(xpath=".//a[@id='LIMA_DocumentsReceived_SUT_INPUT_Hyperlink_Track']") WebElement trackAWB;
		
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement shipmentOriginPort;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement shipmentDestination;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]") WebElement shipmentMode;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]") WebElement shipmentVessel;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]") WebElement shipmentVoyge;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]") WebElement shipmentCTD;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[8]") WebElement shipmentETA;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement shipmentHBL;
		@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[3]") WebElement shipmentVendor;
		
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[1]") WebElement SDOriginPort;
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[2]") WebElement SDDestination;
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[3]") WebElement SDMode;
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[4]") WebElement SDVessel;
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[5]") WebElement SDVoyge;
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[6]") WebElement SDCTD;
		@FindBy(xpath=".//*[@id='ext-gen19']/div/table/tbody/tr/td[7]") WebElement SDETD;
		@FindBy(xpath=".//*[@id='ext-gen122']/div/table/tbody/tr/td[1]") WebElement SDVendor;
		@FindBy(xpath=".//*[@id='ext-gen122']/div/table/tbody/tr/td[2]") WebElement SDHBL;
		@FindBy(xpath=".//*[@id='ext-gen122']/div/table/tbody/tr/td[2]/div/a") WebElement lnkSDHBL;
		@FindBy(xpath=".//a[@id='LIMA_DocumentsReceived_SUT_INPUT_Dialog_HBL']") WebElement lnkSDDailog;
		
		@FindBy(xpath=".//*[@id='DialogPopupPanel_Message']") WebElement messageBox;
		@FindBy(xpath=".//*[@id='DialogPopupPanel_AddDialogButton']") WebElement btnAddDialog;
		@FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr/td") List<WebElement> lstDialogMessage;
		

		@FindBy(xpath=".//*[text()='Add Document']") WebElement btnAddDocument;
	    @FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	    @FindBy(xpath=".//*[text()='No']") WebElement btnNo;
	    @FindBy(xpath=".//*[text()='Delete Document']") WebElement btnDeleteDocument;
	    @FindBy(xpath=".//label[@id='LIMA_DocumentsReceived_SUT_INPUT_Label2']") WebElement errormsg;
	    
	    
	    @FindBy(xpath=".//*[@id='ext-gen185']/div[1]/table/tbody/tr/td[4]/div/a") WebElement FileHyperlink;
	    @FindBy(xpath=".//*[text()='Add']") WebElement btnAdd;
	    @FindBy(xpath=".//*[text()='Clear']") WebElement btnClear;
	    @FindBy(xpath=".//*[text()='Delete']") WebElement btnDelete;
	    @FindBy(xpath="//div[@id='div_HTML5UploaderForm_HTML5FileUpload']/div/input/following-sibling::input") WebElement btnBrowser;
	    @FindBy(xpath=".//*[text()='Clear all']") WebElement btnClearAll;
	    @FindBy(xpath=".//*[text()='Remove']") WebElement btnRemove;
	    @FindBy(xpath=".//*[text()='Upload']") WebElement btnUpload;
	    @FindBy(xpath=".//*[@class='x-window-header x-window-header-noborder x-unselectable x-window-draggable']/div") WebElement btnCancel;
	    @FindBy(xpath=".//div[@class='x-grid3-cell-inner x-grid3-col-GridFileLink']/a") WebElement UploadFileName;
	    @FindBy(xpath=".//div[@class='x-grid3-cell-inner x-grid3-col-GridFileSize']") WebElement UploadSize;
	    @FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-GridFileLink']") List<WebElement> UploadFileName2;
	    @FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-GridFileSize']") List<WebElement> UploadSize2;
	    @FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_DocumentStatus']") List<WebElement> attachmentDocStatus;
		@FindBy(xpath=".//*[@id='LIMA_DocumentsDespatch_SUT_INPUT_FileStore1']") List<WebElement> Attachments;
		@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[3]") WebElement updateValidationError;
	    //@FindBy(xpath=".//*[text()='Browse...']") WebElement btnBrowser;
	    
		

		public WebElement getBtnOK1() {
			return btnOK1;
		}
		public WebElement getUpdateValidationError() {
			return updateValidationError;
		}

		public WebElement getUpdateHeader() {
			return updateHeader;
		}

	    public List<WebElement> getAttachments() {
			return Attachments;
		}

	    public List<WebElement> getAttachmentDocStatus() {
			return attachmentDocStatus;
		}

	    public List<WebElement> getUploadFileName2() {
			return UploadFileName2;
		}

		public List<WebElement> getUploadSize2() {
			return UploadSize2;
		}

		public WebElement getUploadSize() {
			return UploadSize;
		}
	    
	    public WebElement getUploadFileName() {
			return UploadFileName;
		}
	    
	    public WebElement getBtnCancel() {
			return btnCancel;
		}
	    
	    public WebElement getFileHyperlink() {
			return FileHyperlink;
		}

		public WebElement getBtnAdd() {
			return btnAdd;
		}
		
		public WebElement getBtnClear() {
			return btnClear;
		}
	
		
		public WebElement getBtnDelete() {
			return btnDelete;
		}

		public WebElement getBtnBrowser() {
			return btnBrowser;
		}

		public WebElement getBtnClearAll() {
			return btnClearAll;
		}

		public WebElement getBtnRemove() {
			return btnRemove;
		}

		public WebElement getBtnUpload() {
			return btnUpload;
		}

		public WebElement getErrormsg() {
			return errormsg;
		}
	    
	    public WebElement getBtnAddDocument() {
			return btnAddDocument;
		}

		public WebElement getBtnYes() {
			return btnYes;
		}

		public WebElement getBtnNo() {
			return btnNo;
		}

		public WebElement getBtnDeleteDocument() {
			return btnDeleteDocument;
		}

		public List<WebElement> getLstDialogMessage() {
			return lstDialogMessage;
		}

		public WebElement getBtnAddDialog() {
			return btnAddDialog;
		}

		public WebElement getMessageBox() {
			return messageBox;
		}
		
		public WebElement getLnkSDDailog() {
			return lnkSDDailog;
		}
		
		public WebElement getLnkSDHBL() {
			return lnkSDHBL;
		}
		
		public WebElement getShipmentHBL() {
			return shipmentHBL;
		}

		public WebElement getShipmentVendor() {
			return shipmentVendor;
		}

		public WebElement getSDVendor() {
			return SDVendor;
		}

		public WebElement getSDHBL() {
			return SDHBL;
		}

		public WebElement getSDOriginPort() {
			return SDOriginPort;
		}

		public WebElement getSDDestination() {
			return SDDestination;
		}

		public WebElement getSDMode() {
			return SDMode;
		}

		public WebElement getSDVessel() {
			return SDVessel;
		}

		public WebElement getSDVoyge() {
			return SDVoyge;
		}

		public WebElement getSDCTD() {
			return SDCTD;
		}

		public WebElement getSDETD() {
			return SDETD;
		}

		public WebElement getShipmentOriginPort() {
			return shipmentOriginPort;
		}

		public WebElement getShipmentDestination() {
			return shipmentDestination;
		}

		public WebElement getShipmentMode() {
			return shipmentMode;
		}

		public WebElement getShipmentVessel() {
			return shipmentVessel;
		}

		public WebElement getShipmentVoyge() {
			return shipmentVoyge;
		}

		public WebElement getShipmentCTD() {
			return shipmentCTD;
		}

		public WebElement getShipmentETA() {
			return shipmentETA;
		}

		public WebElement getTrackAWB() {
			return trackAWB;
		}

		public WebElement getUpdateReceipted() {
			return UpdateReceipted;
		}

		public WebElement getImgCourier() {
			return imgCourier;
		}

		public WebElement getUpdateCourier() {
			return UpdateCourier;
		}

		public List<WebElement> getLstUpdateCourier() {
			return lstUpdateCourier;
		}

		public WebElement getUpdateCourierAWB() {
			return UpdateCourierAWB;
		}

		public WebElement getUpdateDateReceived() {
			return UpdateDateReceived;
		}

		public List<WebElement> getLstUpdateReceipted() {
			return lstUpdateReceipted;
		}

		public WebElement getChvSearch1() {
			return chvSearch1;
		}

		public WebElement getChvUpdate1() {
			return chvUpdate1;
		}

		public List<WebElement> getTeSelect_Multi() {
			return teSelect_Multi;
		}

		public List<WebElement> getTeHBL_Multi() {
			return teHBL_Multi;
		}

		public List<WebElement> getTeVendor_Multi() {
			return teVendor_Multi;
		}

		public List<WebElement> getTeCourier_Multi() {
			return teCourier_Multi;
		}

		public List<WebElement> getTeCourierAWB_Multi() {
			return teCourierAWB_Multi;
		}

		public List<WebElement> getTeStatus_Multi() {
			return teStatus_Multi;
		}

		public List<WebElement> getTeDialog_Multi() {
			return teDialog_Multi;
		}

		public List<WebElement> getThShipment_Status() {
			return thShipment_Status;
		}

		public List<WebElement> getLstCheckBok() {
			return lstCheckBok;
		}

		public List<WebElement> getLstHBL() {
			return lstHBL;
		}

		public List<WebElement> getLstStatus() {
			return lstStatus;
		}

		public WebElement getDownArrow() {
			return downArrow;
		}
		
		public WebElement gethBL() {
			return hBL;
		}
		
		public WebElement getChvSearch() {
			return chvSearch;
		}
		public WebElement getChvSelect() {
			return chvSelect;
		}
		public WebElement getChvUpdate() {
			return chvUpdate;
		}
		public WebElement getChvComplete() {
			return chvComplete;
		}

		public WebElement getChvComplete1() {
			return chvComplete1;
		}

		public WebElement getLnkTools() {
			return lnkTools;
		}
		public WebElement getLnkTrack() {
			return lnkTrack;
		}
		public WebElement getLnkHome() {
			return lnkHome;
		}
		public WebElement getLnkDocumentReceived() {
			return lnkDocumentReceived;
		}
		
		
		
		public AdjunoLIMADocumentReceivedPOM(WebDriver driver)
		{
			this.driver = driver;
			//This initElements method will create all WebElements
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

	    
	    
	    private boolean isNullOrBlank(String s)
	    {
	      return (s==null || s.trim().equals(""));
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
	
	
	public boolean checkDoesChevronExist(WebElement chevron) {
        Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        bFlag = objAdjunoUILibrary.isElementPresent(chevron);
        
        return bFlag;
    }
	
	public boolean validationMessageExist(WebElement Message) {
        Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        bFlag = objAdjunoUILibrary.isElementPresent(Message);
        
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

	
	public boolean setFieldValue(String strFieldValue, String formName,String controlName) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try {
			WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
			bFlag = we.isDisplayed();
			we.clear();
			we.sendKeys(strFieldValue);
			wait(2000);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}
	
	
	public long getNoOfGridRows(String strFormName, String strControlName)
	   {
	       objAdjunoUILibrary = new AdjunoUILibrary(driver);
	       long GridRows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strControlName);
	       
	       if(GridRows>0){
	           
	       }
	       else{
	           GridRows = 0;
	       }
	       
	       return GridRows;
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
    
	int i=0;
	public boolean setFieldValueForListWebElement(List<WebElement> we, String strFieldValue, int i) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
    
        ArrayList<String> kwe=new ArrayList<String>(); 
        try
       {
        	
            bFlag = we.get(i).isDisplayed();
            we.clear();
            we.get(i).sendKeys(strFieldValue);    
            we.get(i).sendKeys(Keys.TAB);
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
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try {
			bFlag = we.isDisplayed();
			we.clear();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}

	

	

	public boolean clickChevron(WebElement we) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = objAdjunoUILibrary.isElementPresent(we);
			we.click();
			wait(4000);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}

	public List<String> getStaus() {
		List<String> list = new ArrayList<String>();
		//System.out.println("Size of Status" + getLstStatus().size());
		for (int i = 0; i <= getLstStatus().size() - 1; i++) {
			
			list.add(getLstStatus().get(i).getText());
		//	System.out.println("For" + i + "status" + po.getStatus());
		}

		return list;
	}
	
	
	
	public String compareGridData(List<String> list, String strDocumentStatus) {

		String strMessage = "";

	
		for (int i = 0; i <= list.size() - 1; i++) {
			String status = list.get(i);

			if (status.equalsIgnoreCase(strDocumentStatus) || status.equalsIgnoreCase("Part Pending")) {
				
			} else {
					
				strMessage = strMessage + getLstStatus().get(i).getText()+ " in this line " + i + " Not found ";
					
					
			}

		}
		
		return strMessage;
	}
	
	
	
	
	public List<DocumentReceived> getStaus2() {
	
		List<DocumentReceived> list = new ArrayList<DocumentReceived>();
		System.out.println("Size of HBL"+ getLstHBL().size());
		for (int i = 0; i <= getLstHBL().size()-1; i++) {
				DocumentReceived po = new DocumentReceived(getLstHBL().get(i).getText(), getLstStatus().get(i).getText());
				
				list.add(po);
			//	System.out.println("For" + i + " HBL:" + po.getHBL()+ "Status:" + po.getStatus());
				
		}

			return list;
	}	
	
	public String compareGridData2(String strHBL,String strStatus, List<DocumentReceived> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size()-1; i++) {

			if (list.get(i).getHBL().equalsIgnoreCase(strHBL)) {

			} else {

				strMessage = strMessage + "For this HBL NO:"+ list.get(i).getHBL() + "HBL is not matching";
			}

			if (list.get(i).getStatus().equalsIgnoreCase(strStatus)) {

			} else {

				strMessage = strMessage + "For this Status:"+ list.get(i).getStatus() + "Status is not matching";
			}

		}
	//	System.out.println("error message:" + strMessage);
		return strMessage;
	}

		

	public int getNoOrRowsResulted() {
		// TODO Auto-generated method stub
		return teHBL_Multi.size();
	}

	
	public ArrayList<DocumentReceived> selectMulitpleProducts(int nMaxProductsToSelect) {
		int nCounterLen = 0;
		ArrayList<DocumentReceived> lstSearchResults = new ArrayList<DocumentReceived>();

		// TODO:
		// ArrayList<String> lstStatus = new ArrayList<String>();

		if (teHBL_Multi.size() < nMaxProductsToSelect)
			nCounterLen = teHBL_Multi.size();
		else
			nCounterLen = nMaxProductsToSelect;

		for (int i = 0; i <= nCounterLen - 1; i++) {

			DocumentReceived order = new DocumentReceived(( teHBL_Multi).get(i).getText(), teVendor_Multi.get(i).getText(),teCourier_Multi.get(i).getText(), teCourierAWB_Multi.get(i).getText(), teStatus_Multi.get(i).getText(),teDialog_Multi.get(i).getText());
			teSelect_Multi.get(i).click();
			lstSearchResults.add(order);

			if (i == (nMaxProductsToSelect - 1))
				break;
		}

		return lstSearchResults;
	}

	
	public boolean checkGridCellUserEditable(String strFormName, String strGridControlName, int nRow, String strColumnName) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we;
           we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
            bFlag= we.isDisplayed();
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


	public boolean doesDropDownExist(List<WebElement> we){
		
		boolean bFlag = true;
		int count=getLstUpdateCourier().size();
		
	//	System.out.println("Count of DropDown :" +count);
		getImgCourier().click();
		wait(1000);
		String strMessage="";
		
		if(count>=1){
		for(int i=0;i<=getLstUpdateCourier().size()-1;i++)
		{
			String DDvalue=getLstUpdateCourier().get(i).getText();
			
		//	System.out.println("Value of DropDown :" +DDvalue);
			bFlag=true;
	     }
		}else{
			bFlag=false;
			strMessage=strMessage + "DropDown not present";
		}
		return bFlag;
	}
	
	
	public boolean isHyperlinkPresent(WebElement we){
		boolean bFlag ;

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		bFlag = objAdjunoUILibrary.isElementPresent(we);

		we.click();
		return bFlag;
      	
	}
	
	public boolean isURLMatched()
	{
	
		
		String CurrentUrl=driver.getCurrentUrl();
		
		boolean bFlag=true;
		System.out.println("Get url : "+CurrentUrl);
		if(CurrentUrl.equalsIgnoreCase("http://www.track-trace.com/"))
		{
			System.out.println("URL is matched");
		}else{
			System.out.println("URL didn't match");
			}
		return bFlag;
		
	}	
	
	public void switchWindow()
	{
		String winHandleBefore = driver.getWindowHandle();

	    // Switch to new window opened

	    
	        driver.switchTo().window(winHandleBefore);
	    
	     wait(2000);
	}
	
	
	public void closeWindow(){
		
		String winHandleBefore = driver.getWindowHandle();

	    // Switch to new window opened
		
	   
	       driver.switchTo().window(winHandleBefore);
	   
		 driver.close();
	   
       wait(1000);
	}
	
	
	public void closeWindow1()
	{
		
		  String parentHandle = driver.getWindowHandle();
	        System.out.println(parentHandle);

	        // click

	        getTrackAWB().click();

	        for (String winHandle : driver.getWindowHandles()) {
	            System.out.println(winHandle);
	            driver.switchTo().window(winHandle);
	           
	        }

	        driver.close();
	        driver.switchTo().window(parentHandle);
		
	}
	
	
	public String getWebElementValue(String strFormName, String strControlName,int nRow,String strColumnName){
        WebElement we;
        String strTemp;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
       
        
        we = objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, nRow, strColumnName);
        strTemp = we.getText(); 
        System.out.println("Shipment Value :"+strTemp);
        return strTemp;    
        
        
    }
	
	
	public DocumentReceived updateTableValue(){
		
		DocumentReceived dr=new DocumentReceived(getShipmentOriginPort().getText(),getShipmentDestination().getText(),getShipmentMode().getText(),getShipmentVessel().getText(),getShipmentVoyge().getText(),getShipmentCTD().getText(),getShipmentETA().getText());
		return dr; 
		
	}
	
    public DocumentReceived shipmentTableValue(){
		
		DocumentReceived dr=new DocumentReceived(getSDOriginPort().getText(),getSDDestination().getText(),getSDMode().getText(),getSDVessel().getText(),getSDVoyge().getText(),getSDCTD().getText(),getSDETD().getText());
		return dr; 
		
	
		
	}
	
    public DocumentReceived dataTableValue(){
	
    	DocumentReceived dr=new DocumentReceived(getShipmentVendor().getText(),getShipmentHBL().getText(),"");
	    return dr;
    }
    

    public DocumentReceived ShipmentdataTableValue(){
	
    	DocumentReceived dr=new DocumentReceived(getSDVendor().getText(),getSDHBL().getText(),"");
	    return dr;
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
  
    
   
    
    
	public boolean getNoOrRowsInDialog(int indval) {
		// TODO Auto-generated method stub
		boolean bFlag;
		
		
		int count= lstDialogMessage.size();
	
		System.out.println("Dialog Message :"+count);
		
		if(indval==count){
		bFlag =true;
		}else{
			bFlag=false;
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
    
	
	public boolean isElementPresent(WebElement we) {
	      Boolean bFlag;
	      objAdjunoUILibrary = new AdjunoUILibrary(driver);
	      bFlag = objAdjunoUILibrary.isElementPresent(we);
	     
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
	
	/*public boolean clearFieldUsingWebElement(List<WebElement> we)
	{
		boolean bFlag;
		try{
			we.isDisplayed();
			we.clear();
			bFlag = true;
		}
		catch(NoSuchElementException e)
		{
			bFlag = false;
		}
		
		return bFlag;
		
	}
	*/
	
/*	public String verifyProductsDataOnGrid(String strFormName, String strControlName,ArrayList<DocumentReceived> lstSearchResults) {
        
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
       int n1 = lstSearchResults.size();
       System.out.println("n1 "+ n1);
       long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
       System.out.println("n2 "+n2);
       
       String strPONumber = "";
       String strVendor = "";
       String strBuyingAgent = "";
       String strFactory = "";
       String strReturnMessage ="";
       boolean bProductIDFound = false;
               
       if(n1==n2)
       {
           for (int i=0; i <= n1-1;i++)
           {
               strPONumber =lstSearchResults.get(i).getPoNumber();
               System.out.println("strPONumber"+strPONumber);
               strVendor = lstSearchResults.get(i).getVendor();
               System.out.println("strVendor"+strVendor);
               strBuyingAgent = lstSearchResults.get(i).getBuyingAgent();
               strFactory = lstSearchResults.get(i).getfactory();
                     
               for (int j=0; j <= n2-1;j++)
               {
                    if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "PONumber").getText().equalsIgnoreCase(strPONumber))
                          
                   {
                       bProductIDFound = true;
                       
                       
                       if(!(compareTwoStringValuesToSame(strPONumber, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "PONumber").getText())))
                           strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', PO Number on the grid has issues;";
                          System.out.println("GridPoNumber "+objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "PONumber").getText());
                       
                        if(!(compareTwoStringValuesToSame(strVendor, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "Vendor").getText())))
                            strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', Vendor on the grid has issues;";
                        System.out.println("GridVendorNumber "+objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Vendor").getText());
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
           strReturnMessage = "The number of products selected in the search results do not match with the number of products on the order grid.";
       }
       System.out.println("print:"+strReturnMessage);
        return strReturnMessage;
    }
*/    
}
