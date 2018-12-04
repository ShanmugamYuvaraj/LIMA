package com.lima.pagefactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMABookingDiaryPOM {

	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	
	@FindBy(linkText="Booking Diary") WebElement lnkBookingDiary;
	
	@FindBy(xpath=".//*[@id='extId_LIMA_Booking_Diary_Panel_SearchParameters_Details']/div[1]/div") WebElement expandSearch;
	
	
	public AdjunoLIMABookingDiaryPOM(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
    }
	
	
	public boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
	 public void wait(int ms)
	 {
	    	try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

	 }
	 

	public WebElement getExpandSearch() {
		return expandSearch;
	}


	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkBookingDiary() {
		return lnkBookingDiary;
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
	
	public boolean checkFieldIsReadOnly(String strFormName,String strControlName, String strValue) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		try {
			we = objAdjunoUILibrary.getElement(strFormName, strControlName);
			we.isDisplayed();
			we.sendKeys(strValue);
			wait(3000);

			we.clear();
			bFlag = true;

		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (Exception e) {
			bFlag = false;
		}

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


	public long getNoOfRowsInGrid(String strFormName, String strGridControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		long GridRows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
		
		if(GridRows>0){
			
		}
		else{
			GridRows = 0;
		}
		
		return GridRows;
	}


	public String verifyValue(String strStatus, long nRow, String strFormName, String strGridControlName,String strColumnName ) {
		String strMessage = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		for (int i = 0; i <=nRow-1; i++) {
			String tempStatus =objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, i, strColumnName);
			if(tempStatus.equalsIgnoreCase(strStatus)){
				
			}else{
				strMessage = strMessage + i + "row  " + tempStatus +" does not match with "+ strStatus + " "  ;
			}
		}
		
		return strMessage;
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
}
