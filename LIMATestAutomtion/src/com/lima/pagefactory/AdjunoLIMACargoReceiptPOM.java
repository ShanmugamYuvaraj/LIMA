package com.lima.pagefactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.lima.dto.ProductOrderData;
import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMACargoReceiptPOM {
     WebDriver driver;
	 AdjunoUILibrary objAdjunoUILibrary;
	 
	 @FindBy(linkText="Tools") WebElement lnkTools;
	 @FindBy(linkText="Track") WebElement lnkTrack;
	 @FindBy(linkText="Cargo Receipt") WebElement lnkCargoReceipt;
	 @FindBy(linkText="Edit") WebElement lnkTrackEdit;
	 @FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	 
     @FindBy(linkText="Shipment Booking") WebElement lnkShipmentBooking;
	

	 @FindBy(xpath=".//*[text()='Search']") WebElement chvSearch;
	 @FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chvSelect;
	 @FindBy(xpath=".//*[text()='Receipt']") WebElement chvReceipt;
	 @FindBy(xpath=".//*[@id='SUT_StageBar_Input']") WebElement clickReceipt;
	 @FindBy(xpath=".//*[text()='Complete']") WebElement chvComplete;
	 @FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement clickComplete;
	 

	 @FindBy(xpath=".//*[@id='LIMA_Shipment_SUTCargoReceipt_PARAM_FORK_CargoReceipt_RefineSearch']") WebElement btnRefineSearch;
	 
	 @FindBy(xpath=".//*[@class='FindDetail']/table/tbody/tr/td[15]/font") List<WebElement> lstStatus;
	 @FindBy(xpath=".//*[@class='FindDetail']/table/tbody/tr/td[4]")List<WebElement> lstPoNumber;
	 
	 @FindBy(xpath=".//*[@class='x-combo-list-item']") List<WebElement> originportdropdown;
	 

     @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[4]/a") WebElement lstOrderNumber;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement lstOriginPort;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement lstVessel;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]") WebElement lstETD;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]") WebElement lstETA;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]") WebElement lstLoading;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[6]") WebElement lstReceivedDate;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement lstVendor;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[3]") WebElement lstVendorRef;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[5]") WebElement lstProduct;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[6]") WebElement lstSKU;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[7]") WebElement lstIdentifier;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[8]") WebElement lstPackType;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[9]") WebElement lstBkdPcs;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[10]") WebElement lstBkdCartons;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[11]") WebElement lstBkdCbm;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[12]") WebElement lstDC;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[13]") WebElement lstQC;
	 
	 
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement lstOriginPortSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]") WebElement lstVesselSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[11]") WebElement lstETDSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[12]") WebElement lstETASB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement lstLoadingSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[2]/a") WebElement lstPoNumberSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]") WebElement lstVendorSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[3]") WebElement lstProductSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[14]") WebElement lstPackTypeSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[10]") WebElement lstBkdPcsSB;
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[12]") WebElement lstBkdCartonsSB;
	 @FindBy(xpath="//*[@id='InnerContent']/div[3]/div[2]/div[2]/table/tbody/tr[2]/td[13]") WebElement lstBkdCbmSB;
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement checkBox;
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/p") WebElement NoItemsFound;
	
	 @FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;

	 @FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement updateMessage;

	 @FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement trackReference;
	
	 @FindBy(xpath=".//*[@id='extId_ProgressForm_ViewDetail']/div[1]/div") WebElement BtnViewDetail; 
	 
	 @FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	 @FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	
	 @FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	 @FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	 @FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	
	 @FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr[5]/td[2]/a") WebElement lnkCargoReceiptevent;
	 
	 @FindBy(linkText="Find") WebElement lnkFind;
	 
	 @FindBy(linkText="PO Status") WebElement lnkPOStatus;
	 
	 @FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	 
	 @FindBy(xpath=".//*[@id='WorkAreaWide']") WebElement PoStatusReport;
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]") WebElement poStatus_Status;
	 
	 @FindBy(linkText="Awaiting Authorisation") WebElement awaitingAutho;
	 
	 
	 @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[6]") WebElement bkdQtuPo;


	 @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[7]") WebElement bkdCtnsPo;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[8]") WebElement bkdCbmsPo;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[9]") WebElement packTypePo;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[14]/a") WebElement bkdHistoryPo;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[9]") WebElement statusPoBooked;
	 @FindBy(xpath=".//*[text()='OK']") WebElement btnOK;
	 
	 public WebElement getBtnOK() {
			return btnOK;
		}
	 
	 public WebElement getBkdQtuPo() {
		return bkdQtuPo;
	}

	public WebElement getBkdCtnsPo() {
		return bkdCtnsPo;
	}

	public WebElement getBkdCbmsPo() {
		return bkdCbmsPo;
	}

	public WebElement getPackTypePo() {
		return packTypePo;
	}

	public WebElement getBkdHistoryPo() {
		return bkdHistoryPo;
	}

	public WebElement getStatusPoBooked() {
		return statusPoBooked;
	}
	
	 
	 
	public WebElement getAwaitingAutho() {
			return awaitingAutho;
	}
	 
	 public WebElement getPoStatus_Status() {
		return poStatus_Status;
	}

	public WebElement getPoStatusReport() {
		return PoStatusReport;
	}

	public WebElement getBtnRun() {
		return btnRun;
	}

	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}

	public WebElement getClickReceipt() {
		return clickReceipt;
	}

	public WebElement getTrackReference() {
		return trackReference;
	}

	public WebElement getBtnTrackApply() {
		return btnTrackApply;
	}

	public WebElement getLnkFind() {
		return lnkFind;
	}

	public WebElement getLnkCargoReceiptevent() {
		return lnkCargoReceiptevent;
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
	 
	 
	 public WebElement getBtnViewDetail() {
         return BtnViewDetail;
     }
	
	 public WebElement getFldRefNo() {
			return fldRefNo;
		}

	 public WebElement gettrackReference() {
         return trackReference;
     }
	
	 
	 public WebElement getupdateMessage() {
         return updateMessage;
     }
	   		
		
	 public List<WebElement> getOriginportdropdown() {
		return originportdropdown;
	}

	 
	 public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
	}


	 public WebElement getcheckBox() {
         return checkBox;
     }
	 
	 public WebElement getBtnApply() {
	         return btnApply;
	     }
		 

	
	 
	 
	public WebElement getLstOrderNumber() {
		return lstOrderNumber;
	}

	public WebElement getNoItemsFound() {
		return NoItemsFound;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkCargoReceipt() {
		return lnkCargoReceipt;
	}
	
	 public WebElement getLnkTrack() {
			return lnkTrack;
	}


	public WebElement getLnkTrackEdit() {
			return lnkTrackEdit;
	}


	public WebElement getLnkShipmentBooking() {
		return lnkShipmentBooking;
	}

	 
	public WebElement getChvSelect() {
		return chvSelect;
	}

	public WebElement getChvReceipt() {
		return chvReceipt;
	}
	
	public WebElement getclickReceipt() {
		return clickReceipt;
	}

	public WebElement getChvComplete() {
		return chvComplete;
	}

	public WebElement getChvSearch() {
		return chvSearch;
	}

	 public WebElement getClickComplete() {
		return clickComplete;
	}

	public WebElement getBtnRefineSearch() {
		return btnRefineSearch;
	}
	
	public List<WebElement> getLstPoNumber() {
		return lstPoNumber;
	}

	public List<WebElement> getLstStatus() {
		return lstStatus;
	}
	
	

	public WebElement getLstOriginPort() {
		return lstOriginPort;
	}

	public WebElement getLstVessel() {
		return lstVessel;
	}

	public WebElement getLstETD() {
		return lstETD;
	}

	public WebElement getLstETA() {
		return lstETA;
	}

	public WebElement getLstLoading() {
		return lstLoading;
	}

	public WebElement getLstReceivedDate() {
		return lstReceivedDate;
	}

	public WebElement getLstVendor() {
		return lstVendor;
	}

	public WebElement getLstVendorRef() {
		return lstVendorRef;
	}

	public WebElement getLstProduct() {
		return lstProduct;
	}

	public WebElement getLstSKU() {
		return lstSKU;
	}

	public WebElement getLstIdentifier() {
		return lstIdentifier;
	}

	public WebElement getLstPackType() {
		return lstPackType;
	}

	public WebElement getLstBkdPcs() {
		return lstBkdPcs;
	}

	public WebElement getLstBkdCartons() {
		return lstBkdCartons;
	}

	public WebElement getLstBkdCbm() {
		return lstBkdCbm;
	}

	public WebElement getLstDC() {
		return lstDC;
	}

	public WebElement getLstQC() {
		return lstQC;
	}

	
	public WebElement getLstOriginPortSB() {
		return lstOriginPortSB;
	}

	public WebElement getLstVesselSB() {
		return lstVesselSB;
	}

	public WebElement getLstETDSB() {
		return lstETDSB;
	}

	public WebElement getLstETASB() {
		return lstETASB;
	}

	public WebElement getLstLoadingSB() {
		return lstLoadingSB;
	}

	public WebElement getLstPoNumberSB() {
		return lstPoNumberSB;
	}

	public WebElement getLstVendorSB() {
		return lstVendorSB;
	}

	public WebElement getLstProductSB() {
		return lstProductSB;
	}

	public WebElement getLstPackTypeSB() {
		return lstPackTypeSB;
	}

	public WebElement getLstBkdPcsSB() {
		return lstBkdPcsSB;
	}

	public WebElement getLstBkdCartonsSB() {
		return lstBkdCartonsSB;
	}

	public WebElement getLstBkdCbmSB() {
		return lstBkdCbmSB;
	}

	public AdjunoLIMACargoReceiptPOM(WebDriver driver) {
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
		// TODO Auto-generated method stub
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		bFlag = objAdjunoUILibrary.isElementPresent(chevron);
          
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

	public List<ProductOrderData> getStaus() {
		List<ProductOrderData> list = new ArrayList<ProductOrderData>();
	    System.out.println("Size of PO numbers"+getLstPoNumber().size());
		for (int i = 0; i <= getLstPoNumber().size()-1; i++) {
			ProductOrderData po = new ProductOrderData(getLstPoNumber().get(i).getText(),getLstStatus().get(i).getText()); 
			//scroll down 
			//JavascriptExecutor jse = (JavascriptExecutor)driver;
			
			list.add(po);
			System.out.println("For"+i + " po number:"+po.getPoNumber() +"status"+po.getStatus());
			//jse.executeScript("window.scrollBy(0,100)", "");
		}
		
		return list;
	}
	
	public ProductOrderData getProductOrder() {
	
	
			ProductOrderData po = new ProductOrderData(getLstOrderNumber().getText(),"",getLstOriginPort().getText(),getLstVessel().getText(),getLstETD().getText(),getLstETA().getText(),getLstLoading().getText(),getLstVendor().getText(),getLstProduct().getText(),getLstPackType().getText(),getLstBkdPcs().getText(),getLstBkdCartons().getText(),getLstBkdCbm().getText(),getLstIdentifier().getText(),getLstSKU().getText()); 
			
		
	
			return po;
		
	}
	
	public ProductOrderData getShipmentData() {
		
		
		ProductOrderData po = new ProductOrderData(getLstPoNumberSB().getText(),"",getLstOriginPortSB().getText(),getLstVesselSB().getText(),getLstETDSB().getText(),getLstETASB().getText(),getLstLoadingSB().getText(),getLstVendorSB().getText(),getLstProductSB().getText(),getLstPackTypeSB().getText(),getLstBkdPcsSB().getText(),getLstBkdCartonsSB().getText(),getLstBkdCbmSB().getText(),"",""); 
      	
		return po;
      	
    
	}

	public String verifyStaus(String strStatusAwaiting,List<ProductOrderData> list) {
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			System.out.println("order number :"+i);
			if(list.get(i).getStatus().equalsIgnoreCase(strStatusAwaiting)){
				System.out.println("test--"+strStatusAwaiting);
			}else{
				
				strMessage = strMessage + "For this PO NO:"+ list.get(i).getPoNumber()+ "Status is not matching";
			}
		}
		System.out.println("error message:"+strMessage);
		return strMessage;
	}
	
		
	
	public boolean isElementPresent(WebElement we) {
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        bFlag = objAdjunoUILibrary.isElementPresent(we);
         
        return bFlag;
    }

	public boolean isHyperlinkPresent(WebElement we){
		boolean bFlag ;

		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		bFlag = objAdjunoUILibrary.isElementPresent(we);

		return bFlag;
      	
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
	
	private boolean isNullOrBlank(String s)
	{
	      return (s==null || s.trim().equals(""));
	}
	
	public String getWebElementValue(WebElement we){
	
		String temp;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		temp=we.getText();
		
		if(!isNullOrBlank(temp)){
			
			
		}
		else{
			temp ="";
		}
		
		return temp;
	}
	
	public ArrayList<String> getCaptionsList(String strFormName, String strControlName) {
        objAdjunoUILibrary = new AdjunoUILibrary(driver); 
        long nGridColumns = objAdjunoUILibrary.getNoOfColumnsGrid(strFormName,strControlName);    
        String strColumnName = "";
        
        ArrayList<String> captionList = new ArrayList<String>();
        
        for (int a=0; a<= nGridColumns - 1; a++)
        {
            strColumnName = objAdjunoUILibrary.getColumnName(strFormName,strControlName, a);
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
                
                    if(tempVal.equalsIgnoreCase(list.get(i))){
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
	
    public String getDate(int days,String datePattern){
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, days);
         
         SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
         dateFormat.format( cal.getTime());
        
        
        return dateFormat.format(cal.getTime());            
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
           wait(1000);
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
               if(list.get(j).getEvent().equalsIgnoreCase("Cargo Receipt")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                   String strAchievedDate = list.get(j).getAcheived();
                   
                   SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                   Date today = new Date();
                   
                   Date achievedDate = formatter.parse(strAchievedDate);
               
                   min = getDifference(today,achievedDate);
                   System.out.println("difference is:"+min);
               }
           }
           return min;
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
    
    
    public ProductOrderData getPoStatus() {
    	
    	
		ProductOrderData po = new ProductOrderData(getBkdQtuPo().getText(),getBkdCtnsPo().getText(),getBkdCbmsPo().getText(),getPackTypePo().getText()); 
		
	

		return po;
	
}
    
    
public boolean checkFieldIsReadOnlyInGrid(String strFormName, String strControlName, int nRow, String strColumnName,String strPreiousValue) {
        
        boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        WebElement we;
        try
       {
            we = objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, nRow, strColumnName);     
            we.click();
            wait(5000);
            
            we = objAdjunoUILibrary.getGridCellElementEditor(strFormName, strControlName, nRow, strColumnName);     
            bFlag = we.isDisplayed();
            we.sendKeys("");
            wait(1000);
            we.sendKeys(strPreiousValue);
       }
       catch (NoSuchElementException e)
       {
           bFlag = false;
       }
       catch(Exception e)
       {
           bFlag = false;
       }
        
        return bFlag;    
    }

}
