package com.lima.pagefactory;

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

import com.lima.dto.ProductionCheck;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAProductionCheckPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Production Check") WebElement lnkProductionCheck;
	@FindBy(linkText="PO Manager") WebElement lnkPoManager;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[text()='Progress']") WebElement chevProgress;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Input']") WebElement chevProgress1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@value='Complete']") WebElement chevCompleteProgress;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Edit']") WebElement chevEdit;
	//path for complete message
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement txtCompleteMessage;
	
	//path for viewDetails
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
    @FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement ErrorMsg;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement trPONumber;
	//@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement trReferenceNumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]/input") WebElement trCheckBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]/input") WebElement thCheckBox_ProductionCheck;
	//@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thReferenceNumber_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thPONumber_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thVendor_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") WebElement thVendorReference_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]") WebElement thFactory_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]") WebElement thBuyingAgent_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]") WebElement thShipDate_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[8]") WebElement thIntendedShipDate_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[9]") WebElement thPOQuantity_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[10]") WebElement thPriority_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[11]") WebElement thProductionStatus_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[12]") WebElement thOrderDetails_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[13]") WebElement thDialogs_ProductionCheck;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[1]") List <WebElement> teCheckBox_ProductionCheck;
	//@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]") List <WebElement> teReferenceNumber_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]") List <WebElement> tePONumber_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[3]") List <WebElement> teVendor_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]") List <WebElement> teVendorReference_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[5]") List <WebElement> teFactory_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[6]") List <WebElement> teBuyingAgent_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[7]") List <WebElement> teShipDate_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[8]") List <WebElement> teIntendedShipDate_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[9]") List <WebElement> tePOQuantity_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[10]") List <WebElement> tePriority_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[11]") List <WebElement> teProductionStatus_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[12]") List <WebElement> teOrderDetails_ProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[13]") List <WebElement> teDialogs_ProductionCheck;
	
	@FindBy(xpath=".//*[text()='You must correct all validation failures before progressing.']") WebElement errMsgProgress;
	
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement txtEditPurchaseOrder;
	
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	
	@FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	@FindBy(xpath=".//*[@id='WorkAreaWide']") WebElement PoStatusReport;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]/font") WebElement poStatus_Status;
	@FindBy(xpath="(.//*[text()='Progress Check'])[last()]") WebElement LnktrackProductionCheck;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[10]") List<WebElement> lstPOStatusSku;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr/td[21]") List<WebElement> lstPOStatus;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevCompletePOManager;
	public WebElement getLnktrackProductionCheck() {
		return LnktrackProductionCheck;
	}

	public void setLnktrackProductionCheck(WebElement lnktrackProductionCheck) {
		LnktrackProductionCheck = lnktrackProductionCheck;
	}

	public List<WebElement> getTeCheckBox_ProductionCheck() {
		return teCheckBox_ProductionCheck;
	}

	public WebElement getTrCheckBox() {
		return trCheckBox;
	}

	public WebElement getChevEdit() {
		return chevEdit;
	}

	public WebElement getLnkPoManager() {
		return lnkPoManager;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}
	
	public WebElement getLnkProductionCheck() {
		return lnkProductionCheck;
	}
	
	public WebElement getChevSearch() {
		return chevSearch;
	}
	
	public WebElement getTxtCompleteMessage() {
		return txtCompleteMessage;
	}

	public WebElement getChevSelect() {
		return chevSelect;
	}

	public WebElement getChevProgress() {
		return chevProgress;
	}
	
	public WebElement getChevProgress1(){
		return chevProgress1;
	}
	
	public WebElement getChevComplete() {
		return chevComplete;
	}
	public WebElement getChevCompleteProgress() {
		return chevCompleteProgress;
	}
	
	
	public WebElement getErrorMsg() {
		return ErrorMsg;
	}
	
	public WebElement getThCheckBox_ProductionCheck() {
		return thCheckBox_ProductionCheck;
	}

	public WebElement getThPONumber_ProductionCheck() {
		return thPONumber_ProductionCheck;
	}
	
//	public WebElement getThReferenceNumber_ProductionCheck() {
//		return thReferenceNumber_ProductionCheck;
//	}

	public WebElement getThOrderDetails_ProductionCheck() {
		return thOrderDetails_ProductionCheck;
	}

	public WebElement getThVendor_ProductionCheck() {
		return thVendor_ProductionCheck;
	}

	public WebElement getThVendorReference_ProductionCheck() {
		return thVendorReference_ProductionCheck;
	}

	public WebElement getThFactory_ProductionCheck() {
		return thFactory_ProductionCheck;
	}

	public WebElement getThBuyingAgent_ProductionCheck() {
		return thBuyingAgent_ProductionCheck;
	}

	public WebElement getThShipDate_ProductionCheck() {
		return thShipDate_ProductionCheck;
	}

	public WebElement getThIntendedShipDate_ProductionCheck() {
		return thIntendedShipDate_ProductionCheck;
	}

	public WebElement getThPOQuantity_ProductionCheck() {
		return thPOQuantity_ProductionCheck;
	}

	public WebElement getThPriority_ProductionCheck() {
		return thPriority_ProductionCheck;
	}

	public WebElement getThProductionStatus_ProductionCheck() {
		return thProductionStatus_ProductionCheck;
	}

	public WebElement getThDialogs_ProductionCheck() {
		return thDialogs_ProductionCheck;
	}
	
	public WebElement getErrMsgProgress() {
		return errMsgProgress;
	}
	
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
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
	
	public WebElement getBtnTrackApply() {
		return btnTrackApply;
	}
	
	public WebElement getTxtEditPurchaseOrder() {
		return txtEditPurchaseOrder;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}

	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}
	
	public WebElement getTrPONumber() {
		return trPONumber;
	}
	
//	public WebElement getTrReferenceNumber() {
//		return trReferenceNumber;
//	}
	
	public WebElement getBtnRun() {
		return btnRun;
	}
	
	public WebElement getPoStatusReport() {
		return PoStatusReport;
	}

	public WebElement getPoStatus_Status() {
		return poStatus_Status;
	}
	
	public List<WebElement> getLstPOStatusSku() {
		return lstPOStatusSku;
	}
		
	public WebElement getChevCompletePOManager() {
		return chevCompletePOManager;
	}

	public AdjunoLIMAProductionCheckPOM(WebDriver driver)
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
	        Boolean bFlag;
	        objAdjunoUILibrary = new AdjunoUILibrary(driver);
	        
	        try
	       {
	            bFlag = objAdjunoUILibrary.isElementPresent(chevron);
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

	public boolean clearInputField(String strFormName, String strControlName) 
	{	
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
           WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
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
	
	public boolean isElementPresent(WebElement we) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        bFlag = objAdjunoUILibrary.isElementPresent(we);

		return bFlag;	
	}
	
	public String verifyStatusForAllProduct(String strProductionStatus) {
        String strMessage = "";
    
        int statusLength=teProductionStatus_ProductionCheck.size();
        
        for (int i = 0; i < statusLength-1; i++) {
            String status = teProductionStatus_ProductionCheck.get(i).getText();
            if (strProductionStatus.equals("{Any}")) 
            {
                
                if (status.equalsIgnoreCase("Progressed")||status.equalsIgnoreCase("Pending")||status.equalsIgnoreCase("Part Progressed")) {
                    
                }
                else{        
                    strMessage =strMessage+" " + status+ " in this line"+ i + " Status is Not found";
                }
                
            } else if(strProductionStatus.equals("Progressed")){
                
                if (status.equalsIgnoreCase("Progressed")) {                        
                }
                else{                        
                    strMessage =strMessage+" " + status+ " in this line"+ i + " Status is not found";                    
                }
                
             } else if(strProductionStatus.equals("Pending")){
                
                if (status.equalsIgnoreCase("Pending")) {        
                }
                else
                {
                    strMessage =strMessage+" " + status+ " n this line"+ i + " Status is not found";
                }   
            }
             else if(strProductionStatus.equals("Part Progressed"))
             {
            	 if(status.equalsIgnoreCase("Part Progressed")){
            	 }
            	 else
            	 {
            		 strMessage =strMessage+" " + status+ " in this line"+ i + " Status is not found";
            	 }
             }
            else{
                System.out.println("Confirmation status is not known");
                
            }
            
        }
        return strMessage;
    }
	
	public int getNoOfRowsResulted() {
       
        return tePONumber_ProductionCheck.size();
    }

	
	public ArrayList<ProductionCheck> selectMulitpleProducts(int nMaxProductsToSelect)
    {
        int nCounterLen = getNoOfRowsResulted();
        ArrayList<ProductionCheck> lstSearchResults = new ArrayList<ProductionCheck>();
       
        if (nCounterLen < nMaxProductsToSelect){
        	
        }
       else 
            nCounterLen = nMaxProductsToSelect;
            
        
        for(int i = 0; i <= nCounterLen - 1; i++)
        {          
            ProductionCheck check = new ProductionCheck(tePONumber_ProductionCheck.get(i).getText(), teVendor_ProductionCheck.get(i).getText(),teVendorReference_ProductionCheck.get(i).getText(),teFactory_ProductionCheck.get(i).getText(),teBuyingAgent_ProductionCheck.get(i).getText(),teShipDate_ProductionCheck.get(i).getText(),teIntendedShipDate_ProductionCheck.get(i).getText(),tePOQuantity_ProductionCheck.get(i).getText(),tePriority_ProductionCheck.get(i).getText(),teProductionStatus_ProductionCheck.get(i).getText(),teDialogs_ProductionCheck.get(i).getText());
            teCheckBox_ProductionCheck.get(i).click();
            lstSearchResults.add(check);	
        }
		return lstSearchResults;
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
            	   if(listVal.contains("<br>")){
            		   listVal = listVal.replace("<br>", " ");
            	   }
               
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

	public String verifyProductsDataOnGrid(String strFormName, String strControlName,ArrayList<ProductionCheck> lstSearchResults) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	int n1 = lstSearchResults.size();
    	
    	long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
    	
    	
    	String strPONumber = "";
    	String strVendor = "";
//    	String strBuyingAgent = "";
//    	String strFactory = "";
    	String strReturnMessage ="";
    	boolean bProductIDFound = false;
    	    	
    	if(n1==n2)
    	{
        	for (int i=0; i <= n1-1;i++)
        	{
        		strPONumber =lstSearchResults.get(i).getPoNumber();       		
        		strVendor = lstSearchResults.get(i).getVendor();        		
//        		strBuyingAgent = lstSearchResults.get(i).getBuyingAgent();
//        		strFactory = lstSearchResults.get(i).getfactory();
        		  	
            	for (int j=0; j <= n2-1;j++)
            	{
					if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "PONumber").getText().equalsIgnoreCase(strPONumber))
						  
            		{
            			bProductIDFound = true;
            			
            			
            			if(!(compareTwoStringValuesToSame(strPONumber, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j, "PONumber").getText())))
            			    strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', PO Number on the grid has issues;";
            			    
            			
						if(!(compareTwoStringValuesToSame(strVendor, objAdjunoUILibrary.getGridCellElement(strFormName, strControlName , j, "Vendor").getText())))
							strReturnMessage = strReturnMessage + "For PONumber '" + strPONumber + "', Vendor on the grid has issues;";
						
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

	public String verifyProductionStatusMandatory(String strFormName, String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);	
			WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
			       
			we.sendKeys(Keys.TAB);
			wait(2000);

        return we.getAttribute("class");
	}

	public boolean clickButton(String strFormName, String strControlName) {
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
	
	
	public boolean setDate(String strFormName,String strControlName)
	{
		Boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		SimpleDateFormat dateformatter = new SimpleDateFormat("dd MMM YYYY");
		String dtTodayDate = dateformatter.format(new Date());
		WebElement we = objAdjunoUILibrary.getElement(strFormName,strControlName);
		try {
			we.isDisplayed();
			we.clear();
			we.sendKeys(dtTodayDate);
			bFlag = true;
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
		
	public boolean verifyProductProgress(String strFormName,String strGridControlName, String strColumnName) {
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
	
	public boolean clickLink(WebElement we) {
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
	
	public String getWebElementCellValue(WebElement we)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strWeCellValue="";
		strWeCellValue = we.getText();
		if(!isNullOrBlank("WeCellValue:"+strWeCellValue))
		{
			
		}
		else
		{
			strWeCellValue = "The text of the WebElement is not available";
		}
		return strWeCellValue;
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
	
	public boolean clickCheckBox(List<WebElement> checkbox) {
		boolean bFlag = false;
		try
        {
			
			for(int i=0;i<=checkbox.size()-1;i++)
			{
			//bFlag =getTeCheckBox_ProductionCheck().get(i).isDisplayed();
			//checkbox.get(checkbox.size()-1).click();
			checkbox.get(i).click();
			
			bFlag = true;
			break;
			}
			
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
	
	public String getWebElementValue(String strFormName, String strControlName){
		WebElement we;
		String strTemp;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		we = objAdjunoUILibrary.getElement(strFormName,strControlName);	
		strTemp = we.getText();
		return strTemp;
	}
	
}

	

	
	

