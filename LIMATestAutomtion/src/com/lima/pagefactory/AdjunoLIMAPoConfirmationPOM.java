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

import com.lima.dto.PurchaseOrders;
import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;


public class AdjunoLIMAPoConfirmationPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;

	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="PO Confirmation") WebElement lnkPOConfirmation;
	@FindBy(linkText="Track") WebElement lnkTrack;
	@FindBy(linkText="Edit") WebElement lnkEdit;
	@FindBy(linkText="Find") WebElement lnkFind;
	@FindBy(linkText="Complete List") WebElement lnkCompleteList;
	@FindBy(linkText ="PO Status")WebElement lnkPOStatusTool;
	@FindBy(xpath=".//*[@id='InnerContent']/table/tbody/tr[40]/td[1]/a") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnRun;
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOK1;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Search']") WebElement chvSearch1;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	@FindBy(xpath=".//*[text()='Confirmation']") WebElement chvConfirmation;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Input']") WebElement chvConfirmation1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement txtViewDetail;
    @FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement btnViewDetail;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[2]/div[7]") WebElement chvComplete1;
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement Updatemsg;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement TitlePurchaseOrder;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;

	
	@FindBy(xpath=".//*[@id='extId_LIMA_Order_POConfirmation_SUT_INPUT_SelectProduct']']") WebElement chkProductDetails;
	@FindBy(xpath=".//input[@name='LIMA_Order_POConfirmation_SUT_PARAM_PARAM_ConfirmationStatus']/following-sibling::input") WebElement txtconfirmationstatus;
	@FindBy(xpath=".//*[@id='ext-gen63']/div[1]/table/tbody/tr/td[24]/div") WebElement txtcomments;
	@FindBy(xpath=".//*[@id='InnerContent']/p") WebElement contentmsg;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[3]/p") WebElement Mandatorymsg;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[1]/input") List <WebElement> teSelect_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List <WebElement> tePONumber_Multi;
	@FindBy(xpath="//*[@id='InnerContent']/div[3]/table/tbody/tr/td[3]") List <WebElement> teVendor_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]") List <WebElement> teVendorref_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[5]") List <WebElement> teFactory_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[6]") List <WebElement> teBuyingAgent_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[7]") List <WebElement> teShipDate_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[8]") List <WebElement> teLatestShipDate_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[9]") List <WebElement> teDeliveryDate_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[10]") List <WebElement> tePOQuantity_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[11]") List <WebElement> tePriority_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[12]") List <WebElement> teConfirmationstatus_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[13]") List <WebElement> teOrderDetails_Multi;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[14]") List <WebElement> teDialogs_Multi;
	

	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thPONumber;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[3]") WebElement thVendor;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") WebElement thVendorref;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[5]") WebElement thFactory;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[6]") WebElement thBuyingAgent;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[7]") WebElement thShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[8]") WebElement thLatestShipDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[9]") WebElement thDeliveryDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[10]") WebElement thPOQuantity;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[11]") WebElement thPriority;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[12]") WebElement thConfirmationstatus;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[13]") WebElement thOrderDetails;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[14]") WebElement thDialogs;
	@FindBy(xpath = ".//*[@id='ext-gen64']/div/table/thead/tr/td[3]/div/span") WebElement clkMultipleCheckBox;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[12]") WebElement teConfirmationstatus;
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]/input")WebElement chkCheckBoxClicked;
	@FindBy(xpath = ".//*[@id='LIMA_Order_POConfirmation_SUT_PARAM_FORK_POConfirmation_SUT_RefineSearch']")WebElement clkRefineSearch;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr[4]/td[21]/a/b/font")WebElement statusColumn;
	
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a")  List <WebElement> trEvent;;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]")  List <WebElement> trAcheived;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]")  List <WebElement> trUserName;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]/a")WebElement strPONumber;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr/td[1]/a") List<WebElement> lstPONumber;
	@FindBy(xpath = ".//*[@id='InnerContent']/div/table/tbody/tr/td[21]") List<WebElement> lstStatus;
	
	
	
	
	public List<WebElement> getTeConfirmationstatus_Multi() {
		return teConfirmationstatus_Multi;
	}
	
	public WebElement getBtnOK1() {
		return btnOK1;
	}
	
	public WebElement getStrPONumber() {
		return strPONumber;
	}

	public WebElement getStatusColumn() {
		return statusColumn;
	}

	public WebElement getClkMultipleCheckBox() {
		return clkMultipleCheckBox;
	}

	public WebElement getLnkPOStatusTool() {
		return lnkPOStatusTool;
	}

	public WebElement getClkRefineSearch() {
		return clkRefineSearch;
	}

	public WebElement getChkCheckBoxClicked() {
		return chkCheckBoxClicked;
	}

	public WebElement getteConfirmationstatus() {
		return teConfirmationstatus;
	}

	public List<WebElement> getTrEvent() {
		return trEvent;
	}

	public List<WebElement> getTrAcheived() {
		return trAcheived;
	}

	public List<WebElement> getTrUserName() {
		return trUserName;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkPOConfirmation() {
		return lnkPOConfirmation;
	}

	public WebElement getLnkTrack() {
		return lnkTrack;
	}

	public WebElement getLnkEdit() {
		return lnkEdit;
	}
	
	
	public List<WebElement> getLstStatus() {
		return lstStatus;
	}

	public List<WebElement> getLstPONumber() {
		return lstPONumber;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}

	public WebElement getLnkCompleteList() {
		return lnkCompleteList;
	}

	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}

	public WebElement getFldRefNo() {
		return fldRefNo;
	}
	
	public WebElement getBtnApply() {
		return btnApply;
	}

	public WebElement getThPriority() {
		return thPriority;
	}

	public WebElement getChvSearch() {
		return chvSearch;
	}

	public WebElement getChvSelect() {
		return chvSelect;
	}

	public WebElement getChvConfirmation() {
		return chvConfirmation;
	}

	public WebElement getChvComplete() {
		return chvComplete;
	}

	public WebElement getContentMsg() {
		return contentmsg;
	}

	
	public WebElement getMandatorymsg() {
		return Mandatorymsg;
	}

	public WebElement getChvConfirmation1() {
		return chvConfirmation1;
	}

	public WebElement getChvComplete1() {
		return chvComplete1;
	}

	public WebElement getThPONumber() {
		return thPONumber;
	}

	public WebElement getThVendor() {
		return thVendor;
	}

	public WebElement getThVendorref() {
		return thVendorref;
	}

	public WebElement getThFactory() {
		return thFactory;
	}

	public WebElement getThBuyingAgent() {
		return thBuyingAgent;
	}

	public WebElement getThShipDate() {
		return thShipDate;
	}

	public WebElement getThLatestShipDate() {
		return thLatestShipDate;
	}

	public WebElement getThDeliveryDate() {
		return thDeliveryDate;
	}

	public WebElement getThPOQuantity() {
		return thPOQuantity;
	}

	public WebElement getThConfirmationstatus() {
		return thConfirmationstatus;
	}

	public WebElement getThOrderDetails() {
		return thOrderDetails;
	}

	public WebElement getThDialogs() {
		return thDialogs;
	}
	
	public WebElement getChvSearch1() {
		return chvSearch1;
	}

	
	public WebElement getUpdatemsg() {
		return Updatemsg;
	}
	
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}

	public WebElement getBtnViewDetail() {
		return btnViewDetail;
	}
	
	public WebElement getTitlePurchaseOrder() {
		return TitlePurchaseOrder;
	}
	
	public WebElement getBtnRun() {
		return btnRun;
	}

	public AdjunoLIMAPoConfirmationPOM(WebDriver driver)
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
		
		public boolean isElementPresent(WebElement we) {
	        Boolean bFlag;
	        objAdjunoUILibrary = new AdjunoUILibrary(driver);
	        bFlag = objAdjunoUILibrary.isElementPresent(we);
	        
	        return bFlag;
	    }
		
		
		
		public String verifyConfirmationStatusMandatory(String strFormName, String strControlName)
	    {
	        new Actions(driver).moveToElement(txtconfirmationstatus).perform();
	       
	        txtconfirmationstatus.clear();
	        txtconfirmationstatus.sendKeys(Keys.TAB);
	        wait(3000);

	        return txtconfirmationstatus.getAttribute("class");
	    }
		
		public boolean clickButton(String formName, String controlName) {
	        Boolean bFlag;
	        objAdjunoUILibrary = new AdjunoUILibrary(driver);
	        WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
	        
	        try
	       {
	            bFlag = we.isDisplayed();
	            we.click();
	            wait(4000);
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
		
	
		public boolean clickChevron(WebElement we) {
			Boolean bFlag;
	        objAdjunoUILibrary = new AdjunoUILibrary(driver);
	      
	        try
	       {
	            bFlag = objAdjunoUILibrary.isElementPresent(we);
	            we.click();
	            wait(4000);
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
	        Boolean bFlag;
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
		
	public boolean clearInputField(String formName, String controlName) {
		Boolean bFlag;
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

	public ArrayList<PurchaseOrders> selectMulitpleProducts(int nMaxProductsToSelect) {
		int nCounterLen = 0;
		ArrayList<PurchaseOrders> lstSearchResults = new ArrayList<PurchaseOrders>();

		// ArrayList<String> lstStatus = new ArrayList<String>();

		if (tePONumber_Multi.size() < nMaxProductsToSelect)
			nCounterLen = tePONumber_Multi.size();
		else
			nCounterLen = nMaxProductsToSelect;
		
		for (int i = 0; i <= nCounterLen - 1; i++) {
			for(int j=0;j<= teConfirmationstatus_Multi.size()-1;j++){
				if(teConfirmationstatus_Multi.get(j).getText().equalsIgnoreCase("Pending")){
					if(teSelect_Multi.get(j).isSelected()){
						
					}else{
					PurchaseOrders order = new PurchaseOrders(tePONumber_Multi.get(j).getText(), teVendor_Multi.get(j).getText(),teVendorref_Multi.get(j).getText(), teFactory_Multi.get(j).getText(), teBuyingAgent_Multi.get(j).getText(),teShipDate_Multi.get(j).getText(), teDeliveryDate_Multi.get(j).getText(), tePOQuantity_Multi.get(i).getText(), tePriority_Multi.get(j).getText(),teConfirmationstatus_Multi.get(j).getText(),teOrderDetails_Multi.get(j).getText(), teDialogs_Multi.get(j).getText());
					teSelect_Multi.get(j).click();
					lstSearchResults.add(order);
					break;
					}
				}

			}
			
		}
			
		return lstSearchResults;
	}


	public int getNoOfRowsResulted() {
			
		return tePONumber_Multi.size();
	}

	public boolean verifyProductConfirmation(String strFormName,String strGridControlName,int nRow, String strColumnName) {
		Boolean bFlag;
	    objAdjunoUILibrary = new AdjunoUILibrary(driver);
	    WebElement we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
	        
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

	public String verifyStatusForAllProduct(String strConfirmationStatus) {
		String strMessage = "";
		
		int statusLength=teConfirmationstatus_Multi.size();
			
		for (int i = 0; i < statusLength-1; i++) {
			String status = teConfirmationstatus_Multi.get(i).getText();
			if (strConfirmationStatus.equals("{Any}")) 
			{
					
				if (status.equalsIgnoreCase("Under Query")||status.equalsIgnoreCase("Pending")||status.equalsIgnoreCase("Confirmed")||status.equalsIgnoreCase("Part Pending")) {
						
				}else{			
					strMessage =strMessage+teConfirmationstatus_Multi.get(i).getText()+ "in this line"+ i + "Not found ";
				}
					
			} else if(strConfirmationStatus.equals("Confirmed")){
					
				if (status.equalsIgnoreCase("Confirmed")) {						
				}else{						
					strMessage =strMessage+teConfirmationstatus_Multi.get(i).getText()+ "in this line"+ i + "Not found ";					
				}
					
			} else if(strConfirmationStatus.equals("Pending")){
					
				if (status.equalsIgnoreCase("Pending")||status.equalsIgnoreCase("Part Pending")) {		
				}else{
					strMessage =strMessage+teConfirmationstatus_Multi.get(i).getText()+ "in this line"+ i + "Not found ";
				}
					
			}else if(strConfirmationStatus.equals("Under Query")){
					
				if (status.equalsIgnoreCase("Under Query")) {
				}else{
					strMessage =strMessage + " " +status+ "in this line  "+ i + " Not found ";		
				}
					
			}else{
				strMessage =strMessage + "In Po Confirmation Tool Under Select Chevron \"Confirmation status\" is not found";		

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
	
	public ArrayList<String> getCaptionsList(String strFormName,String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		long nGridColumns = objAdjunoUILibrary.getNoOfColumnsGrid(strFormName,strControlName);
		String strColumnName = "";

		ArrayList<String> captionList = new ArrayList<String>();

		for (int a = 0; a <= nGridColumns - 1; a++) {
			strColumnName = objAdjunoUILibrary.getColumnName(strFormName,strControlName, a);
			captionList.add(objAdjunoUILibrary.getGridColumnCaption(strFormName, strControlName, strColumnName, 0));
		}

		return captionList;
	}

	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, int nCaptions) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nCaptions - 1; j++) {
				String tempVal = e1.getElementsByTagName("Caption").item(j).getTextContent();
				for (int i = 0; i <= list.size() - 1; i++) {

					if (tempVal.equalsIgnoreCase(list.get(i))) {
					//	System.out.println("tempVal "+tempVal +"is matching "+list.get(i) );
						break;
					} else {
						if (i == list.size() - 1) {
							strMessage = strMessage + tempVal+ "from Xml is not found in Grid";
						}
					}
				}

			}
		}
		return strMessage;
	}
	
	
	public boolean compareTwoStringValuesToSame(String strValue1, String strValue2)
    {
        boolean bFlag = true;
         if ((!(isNullOrBlank(strValue1))) &&  (!(isNullOrBlank(strValue2))))
         {                
             if (strValue1.equalsIgnoreCase(strValue2)){
            	// System.out.println(strValue1 +"is Matching "+ strValue2);
             }
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

	public String verifyProductsDataOnGrid(String strFormName, String strControlName,ArrayList<PurchaseOrders> lstSearchResults) {
        
       objAdjunoUILibrary = new AdjunoUILibrary(driver);
       int n1 = lstSearchResults.size();
       long n2 = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strControlName);
			String strPoNumber ="";
			String strVendor = "";
			
			boolean bProductIDFound = false;
			String strReturnMessage ="";
		if (n1 == n2) {
			for (int i = 0; i <= n1 - 1; i++) {

				strPoNumber = lstSearchResults.get(i).getPoNumber();
				strVendor = lstSearchResults.get(i).getVender();
			
				for (int j = 0; j <= n2 - 1; j++) {
					if (objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"PONumber").getText().equalsIgnoreCase(strPoNumber))
					{
						bProductIDFound = true;

						if (!(compareTwoStringValuesToSame(strPoNumber,objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"PONumber").getText())))
							strReturnMessage = strReturnMessage+ "For PONumber '" + strPoNumber+ "', PO Number on the grid has issues;";
					//	System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"PONumber").getText());

						if (!(compareTwoStringValuesToSame(strVendor,objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"Vendor").getText())))
							strReturnMessage = strReturnMessage+ "For PONumber'" + strPoNumber+ "', Vendor on the grid has issues;";
					//	System.out.println(objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, j,"Vendor").getText());

						
						break;
					} else {
						if(j==n2-1)
						strReturnMessage = strReturnMessage + "PONumber'"+ strPoNumber + "' not found on the grid;";

					}

				}
				

			}
		}
			else {
			strReturnMessage = "The number of products selected in the search results do not match with the number of products on the grid.";
		}

		return strReturnMessage;
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

    public boolean setFieldValueForGridCell(String strFormName, String strGridControlName, int nRow, String strColumnName, String strValue) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        try
       {
            objAdjunoUILibrary.setGridCellValue(strFormName, strGridControlName, nRow, strColumnName, strValue);    
           WebElement we;
          
           we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strGridControlName, nRow, strColumnName);
           
        
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

  public String getGridCellValue(String strFormName,String strGridControlName, int nRow, String strColumnName) {

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		String value = objAdjunoUILibrary.getGridCellElementValue(strFormName,strGridControlName, nRow, strColumnName);

		return value;

	}

  public long getDifference(Date today, Date date) {
      
      if( date == null || today == null ) return 0;

      return ((today.getTime()/60000) - (date.getTime()/60000));
      
  }

  public long getTrackValue(String userName) throws ParseException {
    int length = getTrEvent().size();
    long min = 0 ;
   List<Tracksdata> list= new ArrayList<Tracksdata>();
   for (int i = 0; i < length-1; i++) {
        Tracksdata track = new Tracksdata(getTrEvent().get(i).getText(), getTrAcheived().get(i).getText(),getTrUserName().get(i).getText());
        list.add(track);
   }
   
   for (int j = 0; j < list.size()-1; j++) {
        if(list.get(j).getEvent().equalsIgnoreCase("Order Confirmation")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
            String strAchievedDate = list.get(j).getAcheived();
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
            Date today = new Date();
            
            Date achievedDate = formatter.parse(strAchievedDate);
        
            min = getDifference(today,achievedDate);
          //  System.out.println("difference is:"+min);
        }
    }
    return min;
}
}


