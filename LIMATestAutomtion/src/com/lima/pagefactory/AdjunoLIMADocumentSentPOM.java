package com.lima.pagefactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lima.dto.DocumentSent;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMADocumentSentPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;

	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Home") WebElement lnkHome;
	@FindBy(linkText="Documents Sent") WebElement lnkDocumentSent;
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chvSearch1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[text()='Update']") WebElement chvUpdate;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Update']") WebElement chvUpdate1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete1;
	@FindBy(xpath=".//*[text()='Add Document']") WebElement btnAddDocument;
	@FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	@FindBy(xpath=".//*[text()='No']") WebElement btnNo;
	@FindBy(xpath=".//*[text()='Delete Document']") WebElement btnDeleteDocument;
	@FindBy(xpath=".//*[@id='LIMA_DocumentsDespatch_SUT_INPUT_Label2']/font") WebElement errormsg;
	@FindBy(xpath=".//*[@id='ext-gen65']/div/table/tbody/tr/td[1]/div") WebElement txtCourier;
	@FindBy(xpath=".//*[@id='ext-gen65']/div/table/tbody/tr/td[2]/div") WebElement txtCourierAWB;
    @FindBy(xpath=".//*[@id='ext-gen65']/div/table/tbody/tr/td[3]/div") WebElement txtDateSent;
	@FindBy(xpath=".//*[@id='ext-gen65']/div/table/tbody/tr/td[4]/div") WebElement txtRecepient;
	@FindBy(xpath=".//*[@id='LIMA_DocumentsDespatch_SUT_INPUT_FileStore1']") WebElement FileHyperlink;
	@FindBy(xpath=".//*[@id='ext-gen217']") WebElement btnAdd;
	@FindBy(xpath=".//*[@id='ext-gen219']") WebElement btnDelete;
	@FindBy(xpath=".//*[@id='ext-comp-1045-file']") WebElement btnBrowser;
	@FindBy(xpath=".//*[text()='Clear all']") WebElement btnClearAll;
	@FindBy(xpath=".//*[text()='Remove']") WebElement btnRemove;
	@FindBy(xpath=".//*[text()='Upload']") WebElement btnUpload;
	@FindBy(xpath=".//*[@id='ext-gen166']/div/table/tbody/tr/td[2]/div/a") WebElement lnkHBLHyperlink;
	@FindBy(xpath=".//*[@id='ext-gen212']/div[1]/table/tbody/tr/td[1]/div/a/span/b/font") WebElement clkDocumentSent;
	@FindBy(xpath=".//*[@class='x-tool x-tool-toggle']") WebElement SearchDropDown;
	
	    
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[1]")  WebElement thOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[2]")  WebElement thDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[3]")  WebElement thMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[4]")  WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[5]")  WebElement thVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[6]")  WebElement thCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[7]")  WebElement thCTD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/th[8]")  WebElement thETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]/input")  WebElement thHBL;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]/input")  WebElement thVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]/input")  WebElement thCourier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[5]/input")  WebElement thCourierAWB;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[6]/input")  WebElement thStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[7]/input")  WebElement thDialog;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]/input")  List <WebElement> teSelect_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[2]")  List <WebElement> teHBL_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[3]")  List <WebElement> teVendor_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[4]")  List <WebElement> teCourier_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[5]")  List <WebElement> teCourierAWB_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[6]")  List <WebElement> teStatus_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr/td[7]")  List <WebElement> teDialog_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[23]/font")  List <WebElement> thShipment_Status;
	
	@FindBy(xpath=".//*[@id='ext-gen69']/div/table/thead/tr/td[1]/div") WebElement ghCourier;
	@FindBy(xpath=".//*[@id='ext-gen69']/div/table/thead/tr/td[2]/div") WebElement ghCourierAWB;
	@FindBy(xpath=".//*[@id='ext-gen69']/div/table/thead/tr/td[3]/div") WebElement ghDateSent;
	@FindBy(xpath=".//*[@id='ext-gen69']/div/table/thead/tr/td[4]/div") WebElement ghRecepient;
	
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[1]/div") WebElement ghOriginPort;
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[2]/div") WebElement ghDestination;
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[3]/div") WebElement ghMode;
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[4]/div") WebElement ghVessel;
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[5]/div") WebElement ghVoyage;
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[6]/div") WebElement ghCTD;
	@FindBy(xpath=".//*[@id='ext-gen83']/div/table/thead/tr/td[7]/div") WebElement ghETA;
	
	@FindBy(xpath=".//*[@id='ext-gen170']/div/table/thead/tr/td[2]/div") WebElement ghHBL_Doc;
	@FindBy(xpath=".//*[@id='ext-gen170']/div/table/thead/tr/td[1]/div") WebElement ghVendor_Doc;
	@FindBy(xpath=".//*[@id='ext-gen170']/div/table/thead/tr/td[3]/div") WebElement ghDialog_Doc;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr/td[6]")List <WebElement>  teStatus_Multi1;
	@FindBy(xpath=".//*[@id='ext-gen166']/div/table/tbody/tr/td[2]/div/a") WebElement lnkHBL  ;

	@FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-Filename']") WebElement UploadFileName;
	@FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-Size']") WebElement UploadSize;
	
	@FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-GridFileLink']") List <WebElement> UploadFileName2;
	@FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-GridFileSize']") List <WebElement> UploadSize2;
	
	@FindBy(xpath=".//*[@id='LIMA_DocumentsDespatch_SUT_INPUT_FileStore1']") List <WebElement> Attachments;
	@FindBy(xpath=".//*[@class='x-window-header x-window-header-noborder x-unselectable x-window-draggable']/div") WebElement closeaddpage;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/table/tbody/tr/td[2]") List <WebElement> lstHBLS;
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
	
	public List<WebElement> getLstHBLS() {
		return lstHBLS;
	}
	
	public WebElement getCloseaddpage() {
		return closeaddpage;
	}
	public List<WebElement> getAttachments() {
		return Attachments;
	}
	public List<WebElement> getUploadFileName2() {
		return UploadFileName2;
	}
	public List<WebElement> getUploadSize2() {
		return UploadSize2;
	}
	public WebElement getUploadFileName() {
		return UploadFileName;
	}
	public void setUploadSize(WebElement uploadSize) {
		UploadSize = uploadSize;
	}
	public WebElement getSearchDropDown() {
		return SearchDropDown;
	}
	
	public WebElement getUploadSize() {
		return UploadSize;
	}
	
	public WebElement getLnkHBL() {
		return lnkHBL;
	}
	public WebElement getBtnYes() {
		return btnYes;
	}
	public WebElement getBtnNo() {
		return btnNo;
	}
	public WebElement getBtnAddDocument() {
		return btnAddDocument;
	}
	public WebElement getBtnDeleteDocument() {
		return btnDeleteDocument;
	}
	public List<WebElement> getTeStatus_Multi1() {
		return teStatus_Multi1;
	}
	public WebElement getClkDocumentSent() {
		return clkDocumentSent;
	}
   public WebElement getLnkHome() {
		return lnkHome;
	}

   public List<WebElement> getThShipment_Status() {
	return thShipment_Status;
  }
	public WebElement getLnkHBLHyperlink() {
		return lnkHBLHyperlink;
	}
	public WebElement getBtnOK1() {
		return btnOK1;
	}
	

	public WebElement getBtnAdd() {
		return btnAdd;
	}
	public WebElement getBtnDelete() {
		return btnDelete;
	}
	
	public WebElement getFileHyperlink() {
		return FileHyperlink;
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
	public WebElement getGhHBL_Doc() {
		return ghHBL_Doc;
	}
	public WebElement getGhVendor_Doc() {
		return ghVendor_Doc;
	}
	public WebElement getGhDialog_Doc() {
		return ghDialog_Doc;
	}
	public WebElement getGhOriginPort() {
		return ghOriginPort;
	}
	public WebElement getGhDestination() {
		return ghDestination;
	}
	public WebElement getGhMode() {
		return ghMode;
	}
	public WebElement getGhVessel() {
		return ghVessel;
	}
	public WebElement getGhVoyage() {
		return ghVoyage;
	}
	public WebElement getGhCTD() {
		return ghCTD;
	}
	public WebElement getGhETA() {
		return ghETA;
	}
	public WebElement getGhCourier() {
		return ghCourier;
	}
	public WebElement getGhCourierAWB() {
		return ghCourierAWB;
	}
	public WebElement getGhDateSent() {
		return ghDateSent;
	}
	public WebElement getGhRecepient() {
		return ghRecepient;
	}
	public List<WebElement> getTeSelect_Multi() {
		return teSelect_Multi;
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
	public List<WebElement> getTeDialog_Multi() {
		return teDialog_Multi;
	}
	public List<WebElement> getTeHBL_Multi() {
		return teHBL_Multi;
	}
	public List<WebElement> getTeStatus_Multi() {
		return teStatus_Multi;
	}
	public WebElement getThOriginPort() {
		return thOriginPort;
	}
	public WebElement getThDestination() {
		return thDestination;
	}
	public WebElement getThMode() {
		return thMode;
	}
	public WebElement getThVessel() {
		return thVessel;
	}
	public WebElement getThVoyage() {
		return thVoyage;
	}
	public WebElement getThCarrier() {
		return thCarrier;
	}
	public WebElement getThCTD() {
		return thCTD;
	}
	public WebElement getThETA() {
		return thETA;
	}
	public WebElement getThHBL() {
		return thHBL;
	}
	public WebElement getThVendor() {
		return thVendor;
	}
	public WebElement getThCourier() {
		return thCourier;
	}
	public WebElement getThCourierAWB() {
		return thCourierAWB;
	}
	public WebElement getThStatus() {
		return thStatus;
	}
	public WebElement getThDialog() {
		return thDialog;
	}
	public WebElement getLnkTools() {
		return lnkTools;
	}
	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	public WebElement getLnkDocumentSent() {
		return lnkDocumentSent;
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
	public WebElement getChvUpdate() {
		return chvUpdate;
	}
	public WebElement getChvUpdate1() {
		return chvUpdate1;
	}
	public WebElement getChvComplete() {
		return chvComplete;
	}
	
	public WebElement getChvComplete1() {
		return chvComplete1;
	}
	public AdjunoLIMADocumentSentPOM(WebDriver driver)
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
	
	public String getPageTitle()
	{
    	objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	String strTitle = "";
		 if (objAdjunoUILibrary.isPageLoaded("LIMA_Order_POConfirmation_SUT"))
		 {
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
	
	public boolean setFieldValue(String strFieldValue, String formName,String controlName) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);

		try {
			bFlag = we.isDisplayed();
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

	public boolean clickChevron(WebElement we) {
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = objAdjunoUILibrary.isElementPresent(we);
			System.out.println("^^^^^^^^^^^"+bFlag);
			we.click();
			wait(4000);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	

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
	


	public boolean isElementPresent(WebElement we) {
        Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        bFlag = objAdjunoUILibrary.isElementPresent(we);
        
        return bFlag;
    }
	
	public boolean compareTwoStringValuesToSame(String strValue1,
			String strValue2) {
		boolean bFlag = true;
		if ((!(isNullOrBlank(strValue1))) && (!(isNullOrBlank(strValue2)))) {
			if (strValue1.equalsIgnoreCase(strValue2)) {
			} else {
				bFlag = false;
			}
		} else {
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
	
	 public boolean verifyNoelement() {
         try
         {
            if(chvSearch.isDisplayed())
            {
            return false;
            }
            return false;
         }
         catch(Exception e)
         {
              System.out.println("No element displayed");
             return true;
         }


     }
	 
	 public List<DocumentSent> getStaus() {
			List<DocumentSent> list = new ArrayList<DocumentSent>();
			System.out.println("Size of HBL"+ getTeHBL_Multi().size());
			for (int i = 0; i <= getTeHBL_Multi().size() -2; i++) {
				DocumentSent po = new DocumentSent(getTeHBL_Multi().get(i).getText(), getTeStatus_Multi().get(i).getText());
				// scroll down
				// JavascriptExecutor jse = (JavascriptExecutor)driver;

				list.add(po);
				System.out.println("For" + i + " HBL:" + po.getHBL()+ "Status:" + po.getStatus());
				// jse.executeScript("window.scrollBy(0,100)", "");
			}

			return list;
		}
	 
	public String compareshipmentgriddata(String strHBL,String strDocumentStatus) {
		
		String strMessage = "";

		int statusLength = teStatus_Multi1.size();

		for (int i = 0; i <= statusLength - 1; i++) {
			String status = teStatus_Multi1.get(i).getText();

			
			if (status.equalsIgnoreCase(strDocumentStatus) && getLstHBLS().get(i).getText().equalsIgnoreCase(strHBL)) {
				
			} else {
				strMessage = strMessage + " Line " + i+ teStatus_Multi1.get(i).getText()+ " is not matching to pending";
			}

		}
		

		
		return strMessage;
	}
	
	public ArrayList<DocumentSent> selectMulitpleProducts(int nMaxProductsToSelect) {
		int nCounterLen = 0;
		ArrayList<DocumentSent> lstSearchResults = new ArrayList<DocumentSent>();

		// TODO:
		// ArrayList<String> lstStatus = new ArrayList<String>();

		if (teHBL_Multi.size() < nMaxProductsToSelect)
			nCounterLen = teHBL_Multi.size();
		else
			nCounterLen = nMaxProductsToSelect;

		for (int i = 0; i <= nCounterLen - 1; i++) {

			DocumentSent order = new DocumentSent(( teHBL_Multi).get(i).getText(), teVendor_Multi.get(i).getText(),teCourier_Multi.get(i).getText(), teCourierAWB_Multi.get(i).getText(), teStatus_Multi.get(i).getText(),teDialog_Multi.get(i).getText());
			teSelect_Multi.get(i).click();
			lstSearchResults.add(order);

			if (i == (nMaxProductsToSelect - 1))
				break;
		}

		return lstSearchResults;
	}

	public int getNoOrRowsResulted() {
		// TODO Auto-generated method stub
		return teHBL_Multi.size();
	}
	
	 public boolean isPageLoaded(String strFormName)
		{
			boolean bFlag = false;
	    	JavascriptExecutor js = (JavascriptExecutor)driver;
	    	String PageLoadState = "Loading";

	    	String strJS = "return window." + strFormName + "_PageLoadState.value";

	    	int i = 0;
	    	
	        while ((!(PageLoadState.equalsIgnoreCase("Ready"))) && (i < 20))
	    	{
	        	try
	    		{
	        		PageLoadState = (String) js.executeScript(strJS);
	    		}
	    		catch (Exception e)
	    		{
	    		}
	        	wait(1000);
	        	i++;
	    	}

	        if (PageLoadState.equalsIgnoreCase("Ready"))
	        	bFlag = true;
	        else
	        	bFlag = false;
			
	    	return bFlag;
		}
	 
	 public String getDate(int days,String datePattern){
	       
	       Calendar cal = Calendar.getInstance();
	       cal.add(Calendar.DATE, days);
	       
	        SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
	        dateFormat.format( cal.getTime());
	       
	       
	       return dateFormat.format(cal.getTime());            
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

	public void clickdocuments( String strFormName,String strcontrolName)
	{
		 objAdjunoUILibrary = new AdjunoUILibrary(driver);
		 
		 long nCount = objAdjunoUILibrary.getTabCount(strFormName, strcontrolName);
		 
		 for (int i = 1; i <=nCount  ; i++) {
			 long nCurrentIndex =objAdjunoUILibrary.getCurrentIndex(strFormName, strcontrolName);
			 if(nCurrentIndex==5){
				 break;
			 }
			 objAdjunoUILibrary.clickNextTab(strFormName,strcontrolName);
		 }
	}
	
	
	
	public String compareshipmentgriddata1(String strDocumentStatus) {

		String strMessage = "";

		int statusLength = teStatus_Multi1.size();

		for (int i = 0; i <= statusLength - 1; i++) {
			String status = teStatus_Multi1.get(i).getText();

			
			if (status.equalsIgnoreCase(strDocumentStatus)) {
				
			} else {
				strMessage = strMessage + " Line " + i+ teStatus_Multi1.get(i).getText()+ " is not matching to pending";
			}

		}
		return strMessage;
	}
	
	 public List<DocumentSent> getStaus2() {
			List<DocumentSent> list = new ArrayList<DocumentSent>();
			System.out.println("Size of HBL"+ getThShipment_Status().size());
			for (int i = 0; i <= getThShipment_Status().size() -2; i++) {
				DocumentSent po = new DocumentSent(getTeHBL_Multi().get(i).getText(), getTeStatus_Multi().get(i).getText());
				// scroll down
				// JavascriptExecutor jse = (JavascriptExecutor)driver;

				list.add(po);
				System.out.println("For" + i + " HBL:" + po.getHBL()+ "Status:" + po.getStatus());
				// jse.executeScript("window.scrollBy(0,100)", "");
			}

			return list;
		}
	
	public String compareshipmentgriddata2(String strHBL,String strStatus, List<DocumentSent> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size() - 1; i++) {

			if (list.get(i).getHBL().equalsIgnoreCase(strHBL)) {

			} else {

				strMessage = strMessage + "For this HBL NO:"+ list.get(i).getHBL() + "HBL is not matching";
			}

			if (list.get(i).getStatus().equalsIgnoreCase(strStatus)) {

			} else {

				strMessage = strMessage + "For this Status:"+ list.get(i).getStatus() + "Status is not matching";
			}

		}
		System.out.println("error message:" + strMessage);
		return strMessage;
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
	
	  public boolean isHyperlinkPresent(WebElement we){
	        boolean bFlag ;

	        objAdjunoUILibrary = new AdjunoUILibrary(driver);

	        bFlag = objAdjunoUILibrary.isElementPresent(we);

	        we.click();
	        return bFlag;
	         
	    }
	  
	 /* public boolean doesDropDownExist(List<WebElement> we){
	        
	        boolean bFlag = true;
	        int count=getLstUpdateCourier().size();
	        
	        System.out.println("Count of DropDown :" +count);
	        getImgCourier().click();
	        wait(1000);
	        String strMessage="";
	        
	        if(count>=1){
	        for(int i=0;i<=getLstUpdateCourier().size()-1;i++)
	        {
	            String DDvalue=getLstUpdateCourier().get(i).getText();
	            
	            System.out.println("Value of DropDown :" +DDvalue);
	            bFlag=true;
	         }
	        }else{
	            bFlag=false;
	            strMessage=strMessage + "DropDown not present";
	        }
	        return bFlag;
	    }
	    */
	  
	  
	  public void workFlowAttachments() throws IOException
	  {
		  int linkcount=Attachments.size();
		  for(int i=0;i<=linkcount-1;i++)
		  {
			  getAttachments().get(i).click(); 
			  attachmentsTab();		  
		  }
		  
	  }
	  
	  
	  public void attachmentsTab() throws IOException{
		  
		  getBtnAdd().click();
		  
		  wait(1000);
		  
		  getBtnBrowser().click();
		  wait(1000);
		  Runtime.getRuntime().exec("C:\\Users\\user\\Desktop\\Autoit\\FileUpload1.exe");
		  wait(1000);
		  getBtnUpload().click();
		  
		  if(isElementPresent(getBtnYes())){
			  getBtnYes().click();
		  }		  
/*		  Actions action =new Actions(driver);
		  action.sendKeys(Keys.ESCAPE).build().perform();*/
		  getCloseaddpage().click();
	  }
	  
	  public long getNoOrRowsinGrid(String strFormName, String strControlName) {
          objAdjunoUILibrary = new AdjunoUILibrary(driver);   
          long nRow = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
          return nRow; 
       }
	  
	  
	public WebElement clickGridCellLink(String strFormName,String strControlName, int i, String strColoumnName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		
		we= objAdjunoUILibrary.getGridCellElement(strFormName,strColoumnName,i,strColoumnName);
		
		return we;
	}
	
	 public List<DocumentSent> getStaus3() {
			List<DocumentSent> list = new ArrayList<DocumentSent>();
			System.out.println("Size of HBL"+ getTeHBL_Multi().size());
			for (int i = 0; i <= getTeHBL_Multi().size() -2; i++) {
				DocumentSent po = new DocumentSent(getTeHBL_Multi().get(i).getText(), getTeStatus_Multi().get(i).getText());
				// scroll down
				// JavascriptExecutor jse = (JavascriptExecutor)driver;

				list.add(po);
				System.out.println("For" + i + " HBL:" + po.getHBL()+ "Status:" + po.getStatus());
				// jse.executeScript("window.scrollBy(0,100)", "");
			}

			return list;
		}
	 
	public String compareshipmentgriddata3(String strHBL1,String strDocumentStatus1, List<DocumentSent> list) {

		String strMessage = "";
		for (int i = 0; i <= list.size() - 1; i++) {

			if (list.get(i).getHBL().equalsIgnoreCase(strHBL1)) {

			} else {

				strMessage = strMessage + "For this HBL1 NO:"+ list.get(i).getHBL() + "HBL1 is not matching";
			}

			if (list.get(i).getStatus().equalsIgnoreCase(strDocumentStatus1)) {

			} else {

				strMessage = strMessage + "For this Status1:"+ list.get(i).getStatus() + "Status1 is not matching";
			}

		}
		System.out.println("error message:" + strMessage);
		return strMessage;
	}
	
}