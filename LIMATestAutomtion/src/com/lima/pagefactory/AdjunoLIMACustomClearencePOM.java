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
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMACustomClearencePOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Customs Clearance") WebElement lnkCustomsClearance;
	
	@FindBy(linkText="DC Booking") WebElement lnkDCBooking;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[text()='Clearance']") WebElement chevClearence;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Clearance']") WebElement chevClearence1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td") WebElement trContainerNo;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement trCheckBox;
	

	@FindBy(xpath=".//*[text()='No items were found.']") WebElement NoProductErrMsg;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thArrivalDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th") WebElement thContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[2]") WebElement thHouseBill;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[3]") WebElement thEntryDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[4]") WebElement thEntryNo;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[5]") WebElement thClearenceDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[6]") WebElement thEPU;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[7]") WebElement thUCR;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[8]") WebElement thTaxPoint;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[1]/th[9]") WebElement thStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td/a") WebElement lnkContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[2]/a") WebElement lnkHouseBill;
	
	@FindBy(xpath=".//*[@class='Title']") WebElement PageShipMentStatus;
	@FindBy(xpath=".//*[text()='You must make selection(s) before progressing']") WebElement NoSelectErrMsg;
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement txtUpdateMsg;
	@FindBy(xpath=".//*[text()='Processed 100%']") WebElement txtProcessedMsg;
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	@FindBy(xpath="(.//*[text()='Customs Clearance'])[last()]") WebElement lnkTrackCustomClearance;
	@FindBy(xpath=".//*[@id='ext-gen19']/div[1]/table/tbody/tr/td[2]/div") WebElement trkContainerNo;
	@FindBy(xpath=".//*[@id='ext-gen19']/div[1]/table/tbody/tr/td[4]/div") WebElement trkEntryNo;
	@FindBy(xpath=".//*[@id='ext-gen19']/div[1]/table/tbody/tr/td[5]/div") WebElement trkEntryDate;
	@FindBy(xpath=".//*[@id='ext-gen19']/div[1]/table/tbody/tr/td[6]/div") WebElement trkClearenceDate;
	@FindBy(xpath=".//*[@id='ext-gen19']/div[1]/table/tbody/tr/td[7]/div") WebElement trkEPU;
	@FindBy(xpath=".//*[@id='ext-gen19']/div[1]/table/tbody/tr/td[8]/div") WebElement trkUCR;
	
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement pgeDCBooking;
	@FindBy(xpath=".//*[@id='InnerContent']/table[1]/tbody/tr/td[1]/table/tbody/tr[5]/td[2]/input") WebElement DCBookContainerNoField;
	@FindBy(xpath=".//*[@id='InnerContent']/div[2]/table/tbody/tr[3]/td[2]/a") WebElement DCBookingContainerNo;
	@FindBy(xpath=".//*[@value='Pending']") WebElement DCBookingStatus;
	
	
	

	public WebElement getDCBookingConatinerNo() {
		return DCBookingContainerNo;
	}

	public WebElement getDCBookingStatus() {
		return DCBookingStatus;
	}

	public WebElement getDCBookContainerNo() {
		return DCBookContainerNoField;
	}

	public WebElement getLnkDCBooking() {
		return lnkDCBooking;
	}

	public WebElement getPgeDCBooking() {
		return pgeDCBooking;
	}

	public WebElement getTrkEntryNo() {
		return trkEntryNo;
	}

	public WebElement getTrkEntryDate() {
		return trkEntryDate;
	}

	public WebElement getTrkClearenceDate() {
		return trkClearenceDate;
	}

	public WebElement getTrkEPU() {
		return trkEPU;
	}

	public WebElement getTrkUCR() {
		return trkUCR;
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
	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
	}

	public WebElement getLnkTrackCustomClearance() {
		return lnkTrackCustomClearance;
	}

	public WebElement getTrkContainerNo() {
		return trkContainerNo;
	}

	public WebElement getTxtUpdateMsg() {
		return txtUpdateMsg;
	}

	public WebElement getShipmentTrack() {
		return txtShipmentTrack;
	}

	public WebElement getLnkFind() {
		return lnkFind;
	}
	
	public WebElement getTxtUpdatedMsg() {
		return txtUpdateMsg;
	}

	public WebElement getTxtProcessedMsg() {
		return txtProcessedMsg;
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
	
	public WebElement getChevComplete1() {
		return chevComplete1;
	}

	public WebElement getTrCheckBox() {
		return trCheckBox;
	}
	
	public WebElement getNoSelectErrMsg() {
		return NoSelectErrMsg;
	}

	public WebElement getChevClearence1() {
		return chevClearence1;
	}

	public WebElement getLnkHouseBill() {
		return lnkHouseBill;
	}

	public WebElement getPageShipMentStatus() {
		return PageShipMentStatus;
	}

	public WebElement getLnkContainer() {
		return lnkContainer;
	}

	public WebElement getThVessel() {
		return thVessel;
	}

	public WebElement getThArrivalDate() {
		return thArrivalDate;
	}

	public WebElement getThContainer() {
		return thContainer;
	}

	public WebElement getThHouseBill() {
		return thHouseBill;
	}

	public WebElement getThEntryDate() {
		return thEntryDate;
	}

	public WebElement getThEntryNo() {
		return thEntryNo;
	}

	public WebElement getThClearenceDate() {
		return thClearenceDate;
	}

	public WebElement getThEPU() {
		return thEPU;
	}

	public WebElement getThUCR() {
		return thUCR;
	}

	public WebElement getThTaxPoint() {
		return thTaxPoint;
	}

	public WebElement getThStatus() {
		return thStatus;
	}

	public WebElement getNoProductErrMsg() {
		return NoProductErrMsg;
	}

	public WebElement getTrContainerNo() {
		return trContainerNo;
	}
	
	public WebElement getLnkTools() {
		return lnkTools;
	}
	public WebElement getLnkCustomsClearance() {
		return lnkCustomsClearance;
	}
	public WebElement getChevSearch() {
		return chevSearch;
	}
	public WebElement getChevSelect() {
		return chevSelect;
	}
	public WebElement getChevClearence() {
		return chevClearence;
	}
	public WebElement getChevComplete() {
		return chevComplete;
	}
	
	public AdjunoLIMACustomClearencePOM(WebDriver driver)
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
	
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink) {
	       Actions action = new Actions(driver);

	       action.moveToElement(mainLink).perform();

	       action.moveToElement(subLink);
	       
	       wait(2000);
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
	    	  strTitle = strTitle + "Custom Clearence Search page is not loaded";
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
	
	public String isHyperlinkPresent(String strFormName,String strGridControlName,int nRow,String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strMessage = "";
		WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		String strhref=we.getAttribute("href");
		if(!isNullOrBlank(strhref))
		{
			we.click();
		}
		else
		{
			strMessage = strMessage + "It is not a hyperlink";
		}
		return strMessage;
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
	
	public boolean clickCheckBox(WebElement checkbox)
	{
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
            	   
                    if(tempVal.equalsIgnoreCase(listVal)){
                        break;
                    }else{
                        if(i==list.size()-1){
                            strMessage = strMessage+ tempVal+ "from Xml is not found in Grid";
                        }                                
                    }
               }
            
            }
        }    
        return strMessage;
    }
	
	public String setCurrentDate(String strPattern)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		SimpleDateFormat dateformatter = new SimpleDateFormat(strPattern);
		String dtTodayDate = dateformatter.format(new Date());
		return dtTodayDate;
	}
	
	public String setFutureDate(String strPattern) 
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		Date dt = new Date();
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		dt = calendar.getTime();
		String FutureDate = new SimpleDateFormat(strPattern).format(dt);
		FutureDate.equals(dt);
		
		return FutureDate;
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
			 strGridCellValue="The text of the cell is not available in Custom Clearence \"Clearence\" Chevron";
		 }
		 return strGridCellValue;
		
	}
	
	public String getRandomString() 
	{
        String strNumber = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * strNumber.length());
            salt.append(strNumber.charAt(index));
        }
        String saltStr = salt.toString();
        
        return saltStr;

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
	
	public long getDifference(Date today, Date date) {
        
        if( date == null || today == null ) return 0;

        return ((today.getTime()/60000) - (date.getTime()/60000));
        
    }
	
	public long getTrackValue(String userName) throws ParseException {
        int length = getLstTrackEvent().size();
        long min = 0 ;
       List<Tracksdata> list= new ArrayList<Tracksdata>();
       for (int i = 0; i < length-1; i++) {
            Tracksdata track = new Tracksdata(getLstTrackEvent().get(i).getText(), getLstTrAchivedDate().get(i).getText(),getLstTrUserName().get(i).getText());
            list.add(track);
       }
       
       for (int j = 0; j < list.size()-1; j++) {
            if(list.get(j).getEvent().equalsIgnoreCase("Customs Clearance")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                String strAchievedDate = list.get(j).getAcheived();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                Date today = new Date();
                
                Date achievedDate = formatter.parse(strAchievedDate);
            
                min = getDifference(today,achievedDate);
            }
        }
        return min;
    }
	
	public String callMouseOverUsingWebElement(String strPageTitle,WebElement mainLink, WebElement subLink)
	{
		Actions action = new Actions(driver);

	       action.moveToElement(mainLink).perform();

	       action.moveToElement(subLink);
	       
	       action.click();
	       
	       action.perform(); 
	       objAdjunoUILibrary = new AdjunoUILibrary(driver);
	       String strTitle = "";
	      if (objAdjunoUILibrary.isPageLoaded(strPageTitle))
	       {
	             strTitle = driver.getTitle();
	       }
	      else
	      {
	    	  strTitle = strTitle + "Page is not loaded";
	      }
	         return strTitle;	    
	}
	
	
	
	

}
