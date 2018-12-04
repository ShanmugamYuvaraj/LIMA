package com.lima.pagefactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.lima.library.AdjunoUILibrary;

public class AdjunoWFLCertificateComfirmationPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;

	
   	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Certificate Confirmation") WebElement lnkCertificateConfirmation;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Lighthouse") WebElement lnklighthouse;
	@FindBy(linkText="Logoff") WebElement lnkLogoff;

	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]") List<WebElement> lstDescription;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[text()='Certificates']") WebElement chvCertificates;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Input']") WebElement chvCertificates1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chvComplete1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement btnPageNext;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement btnPagePrev;

	@FindBy(xpath=".//*[@class='x-combo-list-item']") List<WebElement> lstDropDownValues;
	
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[2]/span/input[4]") WebElement PageNo;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]") List<WebElement> lstCheckBox;
	
	@FindBy(xpath=".//*[@id='ProgressForm_FORK_CertConfSearch']") WebElement btnSearchAgain;

	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]") List<WebElement> supplier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[5]") List<WebElement> product;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[14]") List<WebElement> certified;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[4]/a") List<WebElement> wflref;
	
	@FindBy(xpath=".//div[contains(@class, 'x-combo-list-inner') and contains(@style, 'width: 286px; margin-bottom: 8px; height: 34px;')]//*[contains(@class, 'x-combo-list-item')] ") List<WebElement> custCustomerDD;
	@FindBy(xpath=".//*[@id='LIMA_Shipment_SUTPOCertification_PARAM_PARAM_Customer']/following-sibling::img") WebElement arrowCustomerDD;
	

	public WebElement getBtnPagePrev() {
		return btnPagePrev;
	}

	public WebElement getArrowCustomerDD() {
		return arrowCustomerDD;
	}
	
	public List<WebElement> getCustCustomerDD() {
		return custCustomerDD;
	}

	public WebElement getLnkLogoff() {
		return lnkLogoff;
	}
	
	public WebElement getBtnSearchAgain() {
		return btnSearchAgain;
	}

	public List<WebElement> getLstCheckBox() {
		return lstCheckBox;
	}
	
	public List<WebElement> getLstSupplier() {
		return supplier;
	}

	public List<WebElement> getLstProduct() {
		return product;
	}

	public List<WebElement> getLstCertified() {
		return certified;
	}

	public List<WebElement> getLstWflref() {
		return wflref;
	}

	String strCount;

	public WebElement getPageNo() {
		return PageNo;
	}

	public WebElement getBtnPageNext() {
		return btnPageNext;
	}

	public WebElement getTxtCount() {
		return txtCount;
	}

	public List<WebElement> getLstDropDownValues() {
		return lstDropDownValues;
	}

	public List<WebElement> getLstDescription() {
		return lstDescription;
	}

	public WebElement getBtnApply() {
		return btnApply;
	}
		
	public WebElement getLnklighthouse() {
		return lnklighthouse;
	}

	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}

    public WebElement getChvSearch() {
		return chvSearch;
	}

	public WebElement getChvSelect() {
		return chvSelect;
	}

	public WebElement getChvCertificates() {
		return chvCertificates;
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

	public WebElement getLnkCertificateConfirmation() {
		return lnkCertificateConfirmation;
	}


	
	public AdjunoWFLCertificateComfirmationPOM(WebDriver driver) {
			this.driver = driver;

			//This initElements method will create all WebElements
			PageFactory.initElements(driver, this);
	}

	
	public WebElement getChvCertificates1() {
		return chvCertificates1;
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

	
	public boolean checkDoesChevronExist(WebElement chevron) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(chevron);
          
		return bFlag;
	}
	
	
	public boolean clickChevorn(WebElement chevron) {
		boolean bFlag;
		try
        {
			bFlag = chevron.isDisplayed();
			chevron.click();
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
	
	
	
	public boolean clearInputField(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try
        {
			bFlag = we.isDisplayed();
			we.clear();
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
	
	
	public boolean setFieldValue(String strFieldValue, String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
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

	
	public boolean clickElement(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
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
	
	
	public List<WebElement> dropdownValue()
	{
		WebElement drop_down =driver.findElement(By.id("cityID"));
		Select se = new Select(drop_down);
		List<WebElement> options = se.getOptions();
		return options;
	}
	
	

	public boolean CustdropdownValue()
	{
		int count = getCustCustomerDD().size();
		String strMessage="";
		boolean bFlag = true;
		//System.out.println("Dropdown Size :" +getCustCustomerDD().size());
		
		if(count<=2)
		{
		 	
		}
		else
		{
		   strMessage =strMessage + "Drop Down has extra value or Null";
		   bFlag = false;
		}
		/*for(int i=0;i<=count-1;i++)
		{
			String strtValue = getCustCustomerDD().get(i).getText();
			System.out.println("DD value :"+strtValue);
		}
	   */ return bFlag;
		
	}
	
	
	public ArrayList<String> getSupplierData(int nCount) 
    {
        ArrayList<String> supplierList =new ArrayList<String>();
        for(int j=0;j<=nCount-1;j++)
        {
        
     
        	for (int i = 0; i < lstDescription.size(); i++) 
        	{
        		supplierList.add(lstDescription.get(i).getText());
        		
        	}
        	
        	getBtnPageNext().click();
        
        }
        
        
        return supplierList;
    }
    
	
    public ArrayList<String> getDropDownList(String strFormName, String strControlName)
    {
        ArrayList<String> supplierList =new ArrayList<String>();
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        long nCount = objAdjunoUILibrary.getDropDownValueCount(strFormName,strControlName);
        System.out.println("dropdown :"+nCount);
        for (int i = 0; i < nCount-1; i++) 
        {
        	
            supplierList.add(objAdjunoUILibrary.getDropDownValue(strFormName,strControlName,i));
            System.out.println("dropdown :"+objAdjunoUILibrary.getDropDownValue(strFormName,strControlName,i));
        }
        
        return supplierList;    
    }
    
    
  /*  public String verifySupplierData(ArrayList<String> supplierList,ArrayList<String> vendorList) {
        String strMessage = "";
        for (int i = 0; i < vendorList.size()-1; i++) {
            
            for (int j = 0; j < supplierList.size()-1; j++) {
                if(vendorList.get(i).equalsIgnoreCase(supplierList.get(j))){
                    break;
                }else{
                    if(j==supplierList.size()){
                        strMessage = strMessage + vendorList.get(i)+" value is not found in catalog ";    
                    }
                }
            }
        }
        
        return strMessage;
    }
    */
    
    public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
        String strMessage = "";
        for (int i = 0; i < dropDownListList.size()-1; i++) {
            
            for (int j = 0; j < catalogList.size()-1; j++) {
            	System.out.println("downdata :"+dropDownListList.get(i)+"-----"+"catalogdata :"+catalogList.get(j));
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
    
    
   public int valCount()
	{		
    strCount = getTxtCount().getText();
    String[] vals = strCount.split("of ");
    int nCount = Integer.parseInt(vals[1]);
    
    System.out.println("Count: "+nCount);
    
      nCount= nCount/50;
      if(nCount%50 == 0)
      {
      }else
      {
    	  nCount++;
      }
    	  
      return nCount;  
    	     
	}
   
   
   public void wait(int ms)
   {
       try{
           Thread.sleep(ms);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }
   
   
  
   public boolean clickButton(String formName,String controlName){
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
   
   
   private boolean isNullOrBlank(String s) {
       // TODO Auto-generated method stub
       return (s==null || s.trim().equals(""));
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

	
	
	public String verifyStatusForAllProduct(String strStatus,String strVendor) {
	   String strMessage = "";
	   
	   int statusLength=certified.size();
	 	       
	   if(!isNullOrBlank(strStatus)){
	   for (int i = 0; i < statusLength-1; i++) {
	       String status = certified.get(i).getText();
	       if (strStatus.equals(status))
	       {          
	          if (status.equalsIgnoreCase("Pending")){
	                   
	          System.out.println("status :"+status);
	          }else{        
	          strMessage =strMessage+status+ "in this line"+ i + "Status is Not found";
	          }
	              
	         } 
	       }   
	   }
	
	   return strMessage;
	 }

	
	public String verifyGridValue(String strStatus) {
	       String strMessage = "";
	   
	       int statusLength=certified.size();
	       
	       for (int i = 0; i < statusLength-1; i++) {
	           String status = certified.get(i).getText();
	           if (strStatus.equals("Awaiting"))
	           {
	               if (status.equalsIgnoreCase("Pending")){
	             	System.out.println("status :"+status);
	               }
	               else{        
	                   strMessage =strMessage+status+ "in this line"+ i + "Status is Not found";
	               }
	               
	           } 
	           
	       }
	       return strMessage;
	   }
      
	
	
	public String verifyVendorSearchData(String strVendor,List<WebElement> weList) 
    {
        String strMessage = "";
         if(weList.size()>0){
            for (int i = 0; i <=weList.size()-1; i++) {
                
                if (!weList.get(i).getText().isEmpty()) {
                    if (weList.get(i).getText().equalsIgnoreCase(strVendor)) {
                        
                    	System.out.println("Vendor : "+weList.get(i).getText());
                    } else {
                        strMessage = strMessage + weList.get(i).getText()+ " does not match ";
                    }
                }
            }
        }else{
            strMessage = strMessage +" result data is null ";
        }    
            
        return strMessage;
    }
	
	
	
    /*
    public String verifyCustomerSearchData(String strCustomer) 
    {
        String strMessage = "";
        List<WebElement> weCustomerList = getLstC();
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
	
*/	
	
	public int getNoOrRowsResulted() {
        
	       return lstCheckBox.size();
	   }

	public void selectMulitpleCheckBox(int nMaxProductsToSelect)
	   {
	       int nCounterLen = getNoOrRowsResulted();
	      
	       if (nCounterLen < nMaxProductsToSelect){
	           
	       }
	      else
	           nCounterLen = nMaxProductsToSelect;
	           
	       
	       for(int i = 0; i <= nCounterLen - 1; i++)
	       {          
	           lstCheckBox.get(i).click();
	       }
	      
	   }
	
	
	
	public boolean selectCertificateGridValue(String strFormName,String strGridControlName,int nRow,String strColumnName)
	{
		boolean bFlag;
		objAdjunoUILibrary=new AdjunoUILibrary(driver);
		try{
			WebElement weGrid=objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
			weGrid.isDisplayed();
			bFlag=true;
			weGrid.click();
		}catch (NoSuchElementException e)
        {
        	bFlag = false;
        }
    	catch(NullPointerException e)
        {
    		bFlag = false;
        }
		return bFlag;
		
	
	}
	
	
	public boolean clickCheckBox(String strFormName,String strControlName) {
		boolean bFlag;
		objAdjunoUILibrary=new AdjunoUILibrary(driver);
		try
        {
			WebElement tickBox=objAdjunoUILibrary.getElement(strFormName, strControlName);
			tickBox.click();
			bFlag=true;
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
	
	
	public String getSaltString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = "HC"+salt.toString();
        System.out.println("random:"+"HC"+salt.toString());
        return saltStr;

    }

	
	public String getRandomString() {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 17) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = "HSCODE,"+salt.toString();
        System.out.println("random:"+"HSCODE"+salt.toString());
        return saltStr;

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
	
	public String validElementExist(String formName,String controlName) {		
	
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		
		String elementText=we.getText();
	
     	return elementText;
	}
	
	
	public String getGridCellValue(String strFormName,String strGridControlName,int nRow,String strColumnName)
	{
		
		objAdjunoUILibrary =new AdjunoUILibrary(driver);
		
		String strValueGrid=objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, nRow, strColumnName);
		System.out.println("Status :"+strValueGrid);
		return strValueGrid;
	}
}