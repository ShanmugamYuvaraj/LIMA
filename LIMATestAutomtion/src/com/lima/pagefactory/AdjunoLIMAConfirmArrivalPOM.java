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

import com.lima.dto.ConfirmArrival;
import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAConfirmArrivalPOM {

	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Confirm Arrival") WebElement lnkConfirmArrival;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(linkText="Customs Clearance") WebElement lnkCustomClearence;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[text()='Arrive']") WebElement chevArrive;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Arrive']") WebElement chevArrive1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement txtNoProductErr;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]") WebElement thArrived;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement thContainerNo;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement thOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[5]") WebElement thForwardRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[6]") WebElement thStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[3]") WebElement trContainerNo;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement trVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]")WebElement trDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement trArrived;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[4]") WebElement trOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[5]") WebElement trForwardRef;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[6]") WebElement trStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]") WebElement CheckBox;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[3]") List<WebElement> LstContainerNo;
	
	@FindBy(xpath=".//*[text()='You must make selection(s) before progressing']") WebElement txtProgressingErrMsg;
	@FindBy(xpath=".//*[text()='Processed 100%']") WebElement txtProcessedMsg;
	@FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	@FindBy(xpath="(.//*[text()='Confirm Arrival'])[last()]") WebElement LnkTrackConfirmArrival;
	@FindBy(xpath=".//*[text()='Confirm Arrival']") WebElement ConfirmArrivalPage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[9]") WebElement trCustomClearenceStatus;
	
	
	
	public WebElement getTrCustomClearenceStatus() {
		return trCustomClearenceStatus;
	}

	public WebElement getLnkCustomClearence() {
		return lnkCustomClearence;
	}

	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}

	public AdjunoLIMALibrary getObjAdjunoLIMALibrary() {
		return objAdjunoLIMALibrary;
	}

	public WebElement getConfirmArrivalPage() {
		return ConfirmArrivalPage;
	}

	public WebElement getLnkTrackConfirmArrival() {
		return LnkTrackConfirmArrival;
	}

	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
	}
	
	public List<WebElement> getLstTrAchivedDate() {
		return lstTrAchivedDate;
	}
	
	public List<WebElement> getLstTrUserName() {
		return lstTrUserName;
	}
	
	public List<WebElement> getLstTrackEvent() {
		return lstTrackEvent;
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
	
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}
	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	
	public WebElement getTxtProcessedMsg() {
		return txtProcessedMsg;
	}
	
	public WebElement getChevComplete1() {
		return chevComplete1;
	}

	public WebElement getTrVessel() {
		return trVessel;
	}

	public WebElement getTrDestinationPort() {
		return trDestinationPort;
	}

	public WebElement getTrArrived() {
		return trArrived;
	}

	public WebElement getTrOriginPort() {
		return trOriginPort;
	}

	public WebElement getTrForwardRef() {
		return trForwardRef;
	}

	public WebElement getTrStatus() {
		return trStatus;
	}

	public List<WebElement> getLstContainerNo() {
		return LstContainerNo;
	}
	
	public WebElement getChevArrive1() {
		return chevArrive1;
	}

	public WebElement getTxtProgressingErrMsg() {
		return txtProgressingErrMsg;
	}

	public WebElement getCheckBox() {
		return CheckBox;
	}

	public WebElement getTrContainerNo() {
		return trContainerNo;
	}

	public WebElement getThVessel() {
		return thVessel;
	}

	public WebElement getThDestinationPort() {
		return thDestinationPort;
	}

	public WebElement getThArrived() {
		return thArrived;
	}

	public WebElement getThContainerNo() {
		return thContainerNo;
	}

	public WebElement getThOriginPort() {
		return thOriginPort;
	}

	public WebElement getThForwardRef() {
		return thForwardRef;
	}

	public WebElement getThStatus() {
		return thStatus;
	}

	public WebElement getTxtNoProductErr() {
		return txtNoProductErr;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getChevSearch() {
		return chevSearch;
	}

	public WebElement getChevSelect() {
		return chevSelect;
	}

	public WebElement getChevArrive() {
		return chevArrive;
	}

	public WebElement getChevComplete() {
		return chevComplete;
	}
	
	public WebElement getLnkConfirmArrival() {
		return lnkConfirmArrival;
	}

	public AdjunoLIMAConfirmArrivalPOM(WebDriver driver)
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
	
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink) {
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
	    	  strTitle = strTitle + "Confirm Arrival Search page is not loaded";
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
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
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
	
	public int getNoOfRowsResulted() {
	       
        return getLstContainerNo().size();
    }
	
	public ConfirmArrival ContainerDetails()
	{
		ConfirmArrival ca = new ConfirmArrival(getTrVessel().getText(), getTrDestinationPort().getText(), getTrArrived().getText(), getTrContainerNo().getText(), getTrOriginPort().getText(), getTrForwardRef().getText(), getTrStatus().getText());
		return ca;
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
	
	public String setDate(String strPattern)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		SimpleDateFormat dateformatter = new SimpleDateFormat(strPattern);
		String dtTodayDate = dateformatter.format(new Date());
		return dtTodayDate;
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
            if(list.get(j).getEvent().equalsIgnoreCase("Confirm Arrival")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                String strAchievedDate = list.get(j).getAcheived();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                Date today = new Date();
                
                Date achievedDate = formatter.parse(strAchievedDate);
            
                min = getDifference(today,achievedDate);
               
            }
        }
        return min;
    }

	
	
 }
