package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.lima.dto.BookingConfirmation;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMABookingConfirmationPOM {
	
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Booking Confirmation") WebElement lnkBookingConfirmation;
	@FindBy(linkText="Booking Diary") WebElement lnkBookingDiary;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[text()='Update']") WebElement chevUpdate;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Update']") WebElement chevUpdate1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement txtErrMsg;
	@FindBy(xpath=".//*[@id='LH_Delivery_DeliveryBookingConfirm_PARAM_SUT_FORK_DeliveryBookingConfirm_SUT_RefineSearch']") WebElement btnRefineSearch;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[5]") List <WebElement> lstPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[8]") List<WebElement> lstStatus;
	@FindBy(xpath=".//*[@id='ArenaPopupForm_Anchor']/div/table/tbody/tr[2]/td[1]") WebElement popUpPoNumber;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement trDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]") WebElement trBookingRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]") WebElement trBookingDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]") WebElement trBookingTime;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]") WebElement trDeliveryMethod;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]") WebElement trOrderType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[4]/div") WebElement trContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[6]") WebElement trDuration;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[5]") WebElement trPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement trCheckBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[3]") WebElement trMode;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thMultiDrop;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thBookingRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") WebElement thBookingDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]") WebElement thBookingTime;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]") WebElement thDuration;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]") WebElement thDeliveryMethod;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[8]") WebElement thHaulier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[9]") WebElement thOrderType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]") WebElement thClentRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement thMode;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement thConatiner;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[5]") WebElement thPoNumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[6]") WebElement thPriority;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[7]") WebElement thPackType;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[8]") WebElement thConfirmationStatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[9]") WebElement thDailogs;
	
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtTrackDetails;
	
	@FindBy(xpath=".//*[@class='Warnings NoSelections']") WebElement txtNoSelectionsErrMsg;
	@FindBy(xpath=".//*[Contains(text(),'Dialogs - Re:')]") WebElement dailogPopUp;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_Subject']") WebElement txtsubject;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_Message']") WebElement txtmessage;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_AddressList']") WebElement txtcopyByEmailTo;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_Watch']") WebElement watch;
	@FindBy(xpath=".//*[@id='div_DialogPopupPanel_RestrictTo']") WebElement restrictTo;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_AddDialogButton']") WebElement btnAddDialog;
	@FindBy(xpath=".//*[text()='Error adding dialog']") WebElement ErrPopUp;
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOK;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[1]/h2/span[2]") WebElement txtDailogNew;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[1]/h2/span[6]") WebElement dialogSubject;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[2]") WebElement dialogMessage;
	@FindBy(xpath=".//*[@id='DialogPopupPanel_DialogAnchor']/div/div/table/tbody/tr[2]/td/div/div[1]/h2/span[7]") WebElement dialogMailID;
	@FindBy(xpath=".//*[@class='x-tool x-tool-close']") WebElement closeBtn;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(xpath=".//*[text()='Processed 100%']") WebElement txtProcessedMsg;
	@FindBy(xpath=".//*[@id='LH_PurchaseOrder_View100_LblTrackRef']") WebElement trackNo;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	@FindBy(xpath=".//*[@class='Title']/p") WebElement TitleEditPage;
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement PoStatusReport;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]/a") WebElement poStatus_Status;
	
	@FindBy(xpath="(.//*[text()='Delivery Confirmation'])[last()]") WebElement lnkTrackDeliveryConfirmation;
	@FindBy(xpath=".//*[Contains(@name,'_BookingConfirmed')]") WebElement trackBookingConfirmedStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[10]") List<WebElement> lstPOStatusSku;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[21]") List<WebElement> lstPOStatus;
	
	

	public List<WebElement> getLstPOStatusSku() {
		return lstPOStatusSku;
	}

	public List<WebElement> getLstPOStatus() {
		return lstPOStatus;
	}

	public WebElement getPopUpPoNumber() {
		return popUpPoNumber;
	}
	
	public WebElement getTrackBookingConfirmedStatus() {
		return trackBookingConfirmedStatus;
	}

	public WebElement getLnkTrackDeliveryConfirmation() {
		return lnkTrackDeliveryConfirmation;
	}

	public WebElement getTxtTrackDetails() {
		return txtTrackDetails;
	}

	public WebElement getTrMode() {
		return trMode;
	}

	public WebElement getLnkBookingDiary() {
		return lnkBookingDiary;
	}

	public List<WebElement> getLstStatus() {
		return lstStatus;
	}
	
	public List<WebElement> getLstPONumber() {
		return lstPONumber;
	}
	
	public WebElement getPoStatus_Status() {
		return poStatus_Status;
	}

	public WebElement getPoStatusReport() {
		return PoStatusReport;
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
	
	public WebElement getTitleEditPage() {
		return TitleEditPage;
	}

	public WebElement getBtnTrackApply() {
		return btnTrackApply;
	}
	
	public WebElement getFldRefNo() {
		return fldRefNo;
	}

	public WebElement getLnkEdit() {
		return lnkEdit;
	}
	
	public WebElement getLnkTrack() {
		return lnkTrack;
	}
	
	public WebElement getTxtsubject() {
		return txtsubject;
	}

	public WebElement getTxtmessage() {
		return txtmessage;
	}

	public WebElement getTxtcopyByEmailTo() {
		return txtcopyByEmailTo;
	}

	public WebElement getTrackNo() {
		return trackNo;
	}

	public WebElement getTxtProcessedMsg() {
		return txtProcessedMsg;
	}
	
	public WebElement getBtnApply() {
		return btnApply;
	}
	
	public WebElement getCloseBtn() {
		return closeBtn;
	}
	
	public WebElement getTxtDailogNew() {
		return txtDailogNew;
	}

	public WebElement getDialogSubject() {
		return dialogSubject;
	}

	public WebElement getDialogMessage() {
		return dialogMessage;
	}

	public WebElement getDialogMailID() {
		return dialogMailID;
	}

	public WebElement getBtnOK() {
		return btnOK;
	}

	public WebElement getErrPopUp() {
		return ErrPopUp;
	}

	public WebElement getBtnAddDialog() {
		return btnAddDialog;
	}

	public WebElement getTxtSubject() {
		return txtsubject;
	}

	public WebElement getTxtMessage() {
		return txtmessage;
	}

	public WebElement getTxtCopyByEmailTo() {
		return txtcopyByEmailTo;
	}

	public WebElement getWatch() {
		return watch;
	}

	public WebElement getRestrictTo() {
		return restrictTo;
	}

	public WebElement getDailogPopUp() {
		return dailogPopUp;
	}

	public WebElement getChevComplete1() {
		return chevComplete1;
	}

	public WebElement getChevUpdate1() {
		return chevUpdate1;
	}

	public WebElement getTrDuration() {
		return trDuration;
	}
	
	public WebElement getTrDestination() {
		return trDestination;
	}

	public WebElement getTrBookingRef() {
		return trBookingRef;
	}

	public WebElement getTrBookingDate() {
		return trBookingDate;
	}

	public WebElement getTrBookingTime() {
		return trBookingTime;
	}

	public WebElement getTrDeliveryMethod() {
		return trDeliveryMethod;
	}

	public WebElement getTrOrderType() {
		return trOrderType;
	}

	public WebElement getTrContainer() {
		return trContainer;
	}

	public WebElement getTrCheckBox() {
		return trCheckBox;
	}

	public WebElement getTxtNoSelectionsErrMsg() {
		return txtNoSelectionsErrMsg;
	}

	public WebElement getThMultiDrop() {
		return thMultiDrop;
	}

	public WebElement getThDestination() {
		return thDestination;
	}

	public WebElement getThBookingRef() {
		return thBookingRef;
	}

	public WebElement getThBookingDate() {
		return thBookingDate;
	}

	public WebElement getThBookingTime() {
		return thBookingTime;
	}

	public WebElement getThDuration() {
		return thDuration;
	}

	public WebElement getThDeliveryMethod() {
		return thDeliveryMethod;
	}

	public WebElement getThHaulier() {
		return thHaulier;
	}

	public WebElement getThOrderType() {
		return thOrderType;
	}

	public WebElement getThClentRef() {
		return thClentRef;
	}

	public WebElement getThMode() {
		return thMode;
	}

	public WebElement getThConatiner() {
		return thConatiner;
	}

	public WebElement getThPoNumber() {
		return thPoNumber;
	}

	public WebElement getThPriority() {
		return thPriority;
	}

	public WebElement getThPackType() {
		return thPackType;
	}

	public WebElement getThConfirmationStatus() {
		return thConfirmationStatus;
	}

	public WebElement getThDailogs() {
		return thDailogs;
	}

	public WebElement getBtnRefineSearch() {
		return btnRefineSearch;
	}

	public WebElement getTrPONumber() {
		return trPONumber;
	}

	public WebElement getTxtErrMsg() {
		return txtErrMsg;
	}

	public WebElement getChevSelect() {
		return chevSelect;
	}

	public WebElement getChevUpdate() {
		return chevUpdate;
	}

	public WebElement getChevComplete() {
		return chevComplete;
	}

	public WebElement getChevSearch() {
		return chevSearch;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkBookingConfirmation() {
		return lnkBookingConfirmation;
	}
	
	public AdjunoLIMABookingConfirmationPOM(WebDriver driver)
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
	
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink)
	{
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
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
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
	
	public boolean clearInputField(String strFormName,String strControlName)
	{
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		try
		{
			WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
			we.clear();
			wait(1000);
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
	
	public BookingConfirmation getBookingConfirmation()
	{
		BookingConfirmation SearchResults = new BookingConfirmation(getTrDestination().getText(),getTrBookingRef().getText(),getTrBookingDate().getText(),getTrBookingTime().getText(),getTrDuration().getText(),getTrDeliveryMethod().getText(),getTrContainer().getText(),getTrOrderType().getText());
		
		return SearchResults;
	}
	
	public String verifyProductsDataOnGrid(String strFormName, String strControlName, BookingConfirmation SearchResults ) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
        
    	
    	long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
    	
    	
    	String strDestination = "";
    	String strBookingRef = "";
    	String strBookingDate = "";
    	String strBookingTime = "";
    	String strDuration = "";
    	String strDeliveryMethod = "";
    	String strContainer = "";
    	String strOrderType = "";
    	String strReturnMessage ="";
    	
    	boolean bProductIDFound = false;
    	    	 	
        		strDestination =SearchResults.getDestination();       		
        		strBookingRef = SearchResults.getBookingRef(); 
        		strBookingDate = SearchResults.getBookingDate();
        		strBookingTime = SearchResults.getBookingTime();
        		strDuration = SearchResults.getDuration();
        		strDeliveryMethod = SearchResults.getDeliveryMethod();
        		strContainer = SearchResults.getContainer();
        		strOrderType = SearchResults.getOrderType();
        		
//        		strBuyingAgent = lstSearchResults.get(i).getBuyingAgent();
//        		strFactory = lstSearchResults.get(i).getfactory();
        		  	
            	for (int j=0; j <= n2-1;j++)
            	{
					if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Container_HAWB_Trailer_FCR").getText().equalsIgnoreCase(strContainer))
						  
            		{
            			bProductIDFound = true;
            			
            			
            			if(!(compareTwoStringValuesToSame(strContainer, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "Container_HAWB_Trailer_FCR").getText())))
            			    strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Container Number on the grid has issues;";

						if(!(compareTwoStringValuesToSame(strBookingRef, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "BookingReference").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Booking Ref on the grid has issues;";
											
						if(!(compareTwoStringValuesToSame(strBookingDate, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "BookingDate1").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Booking Date on the grid has issues;"; 
						
						if(!(compareTwoStringValuesToSame(strBookingTime, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"BookingDate2").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Booking Time on the grid has issues;";
							
						if(!(compareTwoStringValuesToSame(strDuration, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"BookingDuration").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Duration on the grid has issues;";
						
						if(!(compareTwoStringValuesToSame(strDeliveryMethod, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"DeliveryMethod").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Delivery Method on the grid has issues;";
						
						if(!(compareTwoStringValuesToSame(strOrderType, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"OrderType").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Order Type on the grid has issues;";
						
						if(!(compareTwoStringValuesToSame(strDestination, objAdjunoUILibrary.getGridCellElement(strFormName,strControlName,j,"Destination").getText())))
							strReturnMessage = strReturnMessage + "For Container '" + strContainer + "', Destination on the grid has issues;";
					      	
						break;
            		}

					else 
					{
						if(j==n2-1)
						{
							strReturnMessage = strReturnMessage + "Container No '"+ strContainer + "' not found on the grid;";
						}
						
					}
            	}	  	
    	
		return strReturnMessage;
	}

	public ArrayList<String> getCaptionsList(String strFormName, String strControlName) {
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
            	   
            	   if(listVal.contains("<br/>")){
            		   listVal = listVal.replace("<br/>", " ");
            		   
            	   }
               
                    if(tempVal.equalsIgnoreCase(listVal)){
                        break;
                    }
                    else{
                        if(i==list.size()-1)
                        {
                            strMessage = strMessage+ tempVal+ "from Xml is not found in Grid";
                        }                                
                    }
               }
            
            }
        }    
        return strMessage;
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
			 strGridCellValue="The text of the cell is not available in Update page in Booking Confirmation";
		 }
		 return strGridCellValue;
		
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
	
	public boolean clickAllCheckBox(String strFormName,String strGridControlName, String strColumnName) {
        Boolean bFlag = true;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        Long nRows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
        WebElement we;
              
        		for (int i = 0; i < nRows; i++) {
            	we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, i, strColumnName);
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
                 
            }
                
        return bFlag;               
    }
	
	public String isHyperlinkPresent(String strFormName,String strGridControlName,int nRow,String strColumnName, String value)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strLink = "";
		WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		strLink=we.getAttribute(value);
		if(!isNullOrBlank(strLink))
		{
			we.click();
		}
		else
		{
			strLink = strLink + "It is not a hyperlink";
		}
		return strLink;
	}
	
	public boolean setFieldValueForWebElement(WebElement we, String strFieldValue) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
    
        try
       {
            bFlag = we.isDisplayed();
            we.clear();
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
	
	public List<BookingConfirmation> getStaus() 
	{
		List<BookingConfirmation> list = new ArrayList<BookingConfirmation>();
	    
		for (int i = 0; i <= getLstPONumber().size()-1; i++) {
			BookingConfirmation bc = new BookingConfirmation(getLstPONumber().get(i).getText(),getLstStatus().get(i).getText()); 
			
			list.add(bc);
	}
		return list;
	}

	public String verifyStaus(String strStatusAwaiting,List<BookingConfirmation> list) 
	{
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			
			if(list.get(i).getStatus().equalsIgnoreCase(strStatusAwaiting))
			{
				
			}
			else{
				
				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPoNumber()+ "Status is not matching";
			}
		}
		
		return strMessage;
	}
	
	public String parseDate(String d, String pattern, String pattern1) {
	    String result = "";
	    
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
	    try {
	    	Date dateObject = dateFormatter.parse(d);
	        dateFormatter.applyPattern(pattern1);
	        result = dateFormatter.format(dateObject);

	    } catch (ParseException e) {
	        
	    }

	    return result;

	}
	
	public boolean clickLinkUsingWebElement(WebElement we) {
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
	
	public WebElement getGridCellElement(String strFormName, String strGridControlName,int nRow, String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		
		we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		
		return we;
	}
	
	public boolean verfyPOStatus(String strStatus)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag = false;
		for(int i=0; i<=getLstPOStatusSku().size()-1;i++)
		{
			String strPOStatus = lstPOStatus.get(i).getText();
			
			if(compareTwoStringValuesToSame(strPOStatus,strStatus))
			{
				bFlag = true;
				break;
			}
			else
			{
				
			}
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
	


	

}
