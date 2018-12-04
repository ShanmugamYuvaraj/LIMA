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

import com.lima.dto.Despatch;
import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMADespatchPOM {
	
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Despatch") WebElement lnkDespatch;
	@FindBy(linkText="Manifest") WebElement lnkManifest;
	
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[@class='Stage' and text()='Despatch']") WebElement chevDespatch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Despatch']") WebElement chevDespatch1;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevComplete1;
	
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement txtErrMsg;
	@FindBy(xpath=".//*[text()='You must correct all validation failures before progressing.']") WebElement txtErrMsgDespatch;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement OriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement FeederVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]") WebElement FeederVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]") WebElement FeederETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]") WebElement FeederETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[6]") WebElement DestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[7]") WebElement Carrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[8]") WebElement MotherVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]") WebElement MotherVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[10]") WebElement MotherETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[11]") WebElement MotherETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement Container;
	               
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement Seal;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement Equipment;
	@FindBy(xpath=".//*[@value='Status']") WebElement Status;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[1]") List<WebElement> teCheckBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> teOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teFeederVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teFeederVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[4]") List<WebElement> teFeederETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teFeederETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teDestinationPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teMotherVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teMotherVoyage;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teMotherETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> teMotherETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[3]") List<WebElement> teSeal;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[4]]") List<WebElement> teEquipment;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[2]") List<WebElement> teContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr[2]/td[5]") List<WebElement> teStatus;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]") WebElement thManifestETD;
	
	@FindBy(xpath=".//*[text()='Add Leg']") WebElement btnAddLeg;
	@FindBy(xpath=".//*[text()='Delete Leg']") WebElement btnDeleteLeg;
	@FindBy(xpath=".//*[text()='Are you sure you want to delete the selected row?']")WebElement txtDeleteLeg;
	@FindBy(xpath=".//*[text()='No']") WebElement btnNO;
	@FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	
	@FindBy(xpath=".//*[@id='ProgressForm_UpdateMessage']") WebElement txtDespatchedMsg;
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
	
	@FindBy(linkText="PO Status") WebElement lnkPOStatus;
	@FindBy(xpath=".//*[@value='Run']") WebElement btnRun;
	@FindBy(xpath=".//*[@id='WorkAreaWide']") WebElement PoStatusReport;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[21]/a") WebElement poStatus_Status;
	@FindBy(xpath=".//*[text()='Shipped']") WebElement txtShipped;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]/a") WebElement poStatus_PONumber;
	@FindBy(xpath=".//*[text()='Purchase Order']") WebElement txtPurchaseOrder;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[1]") WebElement manifestOriginPort;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[3]") WebElement manifestETD;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[4]") WebElement manifestETA;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[5]") WebElement manifestCarrier;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[2]") WebElement manifestVessel;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[8]") WebElement manifestContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[2]/td[9]") WebElement manifestEquipment;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[5]") WebElement manifestProductNumber;
	

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getLnkDespatch() {
		return lnkDespatch;
	}
	
	public WebElement getLnkManifest() {
		return lnkManifest;
	}
	
	public WebElement getChevSearch() {
		return chevSearch;
	}
	
	public WebElement getChevSelect() {
		return chevSelect;
	}
	
	public WebElement getChevDespatch() {
		return chevDespatch;
	}
	
	public WebElement getChevDespatch1() {
		return chevDespatch1;
	}

	public WebElement getChevComplete() {
		return chevComplete;
	}
	
	public WebElement getChevComplete1() {
		return chevComplete1;
	}

	public WebElement getTxtErrMsg() {
		return txtErrMsg;
	}
	
	public WebElement getOriginPort() {
		return OriginPort;
	}

	public WebElement getFeederVessel() {
		return FeederVessel;
	}

	public WebElement getFeederVoyage() {
		return FeederVoyage;
	}

	public WebElement getFeederETD() {
		return FeederETD;
	}

	public WebElement getFeederETA() {
		return FeederETA;
	}

	public WebElement getDestinationPort() {
		return DestinationPort;
	}

	public WebElement getCarrier() {
		return Carrier;
	}

	public WebElement getMotherVessel() {
		return MotherVessel;
	}

	public WebElement getMotherVoyage() {
		return MotherVoyage;
	}

	public WebElement getMotherETD() {
		return MotherETD;
	}

	public WebElement getMotherETA() {
		return MotherETA;
	}

	public WebElement getContainer() {
		return Container;
	}

	public WebElement getSeal() {
		return Seal;
	}

	public WebElement getEquipment() {
		return Equipment;
	}

	public WebElement getStatus() {
		return Status;
	}

	public WebElement getTxtErrMsgDespatch() {
		return txtErrMsgDespatch;
	}
	
	public WebElement getThManifestETD(){
		return thManifestETD;
	}
	
	public WebElement getBtnAddLeg()
	{
		return btnAddLeg;
	}
	
	public WebElement getBtnDeleteLeg()
	{
		return btnDeleteLeg;
	}
	
	public WebElement getTxtDeleteLeg()
	{
		return txtDeleteLeg;
	}

	public WebElement getBtnNO() {
		return btnNO;
	}

	public WebElement getBtnYes() {
		return btnYes;
	}
	
	public WebElement getTxtDespatchedMsg() {
		return txtDespatchedMsg;
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
	
	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
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
	
	public WebElement getLnkFind() {
		return lnkFind;
	}
	
	public WebElement getLnkPOStatus() {
		return lnkPOStatus;
	}
	
	public WebElement getPoStatusReport() {
		return PoStatusReport;
	}

	public WebElement getBtnRun() {
		return btnRun;
	}
	
	public WebElement getPoStatus_Status() {
		return poStatus_Status;
	}
	
	public WebElement getTxtShipped() {
		return txtShipped;
	}
	
	public WebElement getPoStatus_PONumber() {
		return poStatus_PONumber;
	}
	
	public WebElement getTxtPurchaseOrder() {
		return txtPurchaseOrder;
	}
	
	public WebElement getManifestOriginPort() {
		return manifestOriginPort;
	}

	public WebElement getManifestETD() {
		return manifestETD;
	}

	public WebElement getManifestETA() {
		return manifestETA;
	}

	public WebElement getManifestCarrier() {
		return manifestCarrier;
	}

	public WebElement getManifestVessel() {
		return manifestVessel;
	}

	public WebElement getManifestContainer() {
		return manifestContainer;
	}

	public WebElement getManifestEquipment() {
		return manifestEquipment;
	}

	public List<WebElement> getTeCheckBox() {
		return teCheckBox;
	}

	public List<WebElement> getTeOriginPort() {
		return teOriginPort;
	}

	public List<WebElement> getTeFeederVessel() {
		return teFeederVessel;
	}

	public List<WebElement> getTeFeederVoyage() {
		return teFeederVoyage;
	}

	public List<WebElement> getTeFeederETD() {
		return teFeederETD;
	}

	public List<WebElement> getTeFeederETA() {
		return teFeederETA;
	}

	public List<WebElement> getTeDestinationPort() {
		return teDestinationPort;
	}

	public List<WebElement> getTeCarrier() {
		return teCarrier;
	}

	public List<WebElement> getTeMotherVessel() {
		return teMotherVessel;
	}

	public List<WebElement> getTeMotherVoyage() {
		return teMotherVoyage;
	}

	public List<WebElement> getTeMotherETD() {
		return teMotherETD;
	}

	public List<WebElement> getTeMotherETA() {
		return teMotherETA;
	}

	public List<WebElement> getTeSeal() {
		return teSeal;
	}

	public List<WebElement> getTeEquipment() {
		return teEquipment;
	}

	public List<WebElement> getTeContainer() {
		return teContainer;
	}

	public List<WebElement> getTeStatus() {
		return teStatus;
	}

	public WebElement getManifestProductNumber() {
		return manifestProductNumber;
	}

	public AdjunoLIMADespatchPOM(WebDriver driver)
	{
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	private boolean isNullOrBlank(String s)
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
           wait(1000);
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
	
	public boolean isElementPresent(WebElement we) 
	{
		Boolean bFlag;
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        
        bFlag = objAdjunoUILibrary.isElementPresent(we);

		return bFlag;	
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
	
//	public String verifyStaus(String strStatusAwaiting,List<Despatch> list) {
//        String strMessage= "";
//        for (int i = 0; i <= list.size()-1; i++) {
//            System.out.println("order number :"+i);
//            if(list.get(i).getStatus().equalsIgnoreCase(strStatusAwaiting)){
//                System.out.println("test--"+strStatusAwaiting);
//            }else{
//                
//                strMessage = strMessage + "For this MAWB:"+ list.get(i).getStrMAWB()+ "Status is not maching";
//            }
//        }
//        System.out.println("error message:"+strMessage);
//        return strMessage;
//    }
	
	public String verifySearchCriteria(String strFormName,String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strMsg = "";
		wait(3000);
		String inputField = objAdjunoUILibrary.getElement(strFormName, strControlName).getAttribute("value");
		
		int containerLength = teContainer.size();
		
		for(int i=0;i<=containerLength-1;i++)
		{
			String gridData =  teContainer.get(i).getText();
		
		
			if(inputField.equalsIgnoreCase(gridData))
			{
			
			}
			else{
				strMsg = strMsg +" "+ gridData +" The Search criteria do not match in the Resultant grid in Despatch Select page";	    
			}
		}
		
		return strMsg;
	}
	
	public String verifyStatusForAllProduct(String strStatus) {
        String strMessage = "";
    
        int statusLength=teStatus.size();
        
        for (int i = 0; i < statusLength-1; i++) {
            String status = teStatus.get(i).getText();
            if (strStatus.equals("Awaiting")) 
            {
                
                if (status.equalsIgnoreCase("Awaiting")){
                    
                }
                else{        
                    strMessage =strMessage+" " + status+ " in this line "+ i + " Status is Not found";
                }
                
            } else if(strStatus.equals("Despatched")){
                
                if (status.equalsIgnoreCase("Despatched")) {                        
                }
                else{                        
                    strMessage =strMessage+" " + status+ "in this line "+ i + "Status is not found";                    
                }
                
             } else if(strStatus.equals("Awaiting Transhipment")){
                
                if (status.equalsIgnoreCase("Awaiting Transhipment")) {        
                }
                else
                {
                    strMessage =strMessage+" " + status+ "in this line "+ i + "Status is not found";
                }   
            }
             
            else{
                strMessage = strMessage + "Status is not displayed in Despatch Select page";
                
            }
            
        }
        return strMessage;
    }
	
	public int getNoOrRowsResulted() {
	       
        return teContainer.size();
    }
	
	public ArrayList<Despatch> selectMulitpleProducts(int nMaxProductsToSelect)
    {
        int nCounterLen = getNoOrRowsResulted();
        ArrayList<Despatch> lstSearchResults = new ArrayList<Despatch>();
       
        if (nCounterLen < nMaxProductsToSelect){
        	
        }
       else 
            nCounterLen = nMaxProductsToSelect;
            
        
        for(int i = 0; i <= nCounterLen - 1; i++)
        {          
            Despatch despatch = new Despatch(teContainer.get(i).getText(), teStatus.get(i).getText(), teOriginPort.get(i).getText(), teFeederVessel.get(i).getText(), teFeederVoyage.get(i).getText(), teFeederETD.get(i).getText(), teFeederETA.get(i).getText(), teDestinationPort.get(i).getText(), teCarrier.get(i).getText(), teMotherVessel.get(i).getText(), teMotherVoyage.get(i).getText(), teMotherETD.get(i).getText(), teMotherETA.get(i).getText());
            teCheckBox.get(i).click();
            lstSearchResults.add(despatch);	
        }
		return lstSearchResults;
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
			 strGridCellValue="The text of the cell is not available in Despatch Chevron";
		 }
		 return strGridCellValue;
		
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
	
	public String setFutureDate( ) {
		
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
	
		Date dt = new Date();
//		Date dt=new SimpleDateFormat("dd MMM YYYY").parse(strGridDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dt);
		calendar.add(Calendar.DATE, 1);
		dt = calendar.getTime();
		String FutureDate = new SimpleDateFormat("dd MMM YYYY").format(dt);
		FutureDate.equals(dt);
		
		return FutureDate;
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
            if(list.get(j).getEvent().equalsIgnoreCase("Advice of Despatch")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                String strAchievedDate = list.get(j).getAcheived();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                Date today = new Date();
                
                Date achievedDate = formatter.parse(strAchievedDate);
            
                min = getDifference(today,achievedDate);
                
            }
        }
        return min;
    }
	
	public Despatch getManifestData()
	{
		Despatch dp = new Despatch(getManifestOriginPort().getText(), getManifestCarrier().getText(), getManifestVessel().getText(), getManifestETD().getText(),getManifestETA().getText(),getManifestContainer().getText(),getManifestEquipment().getText());
		
		return dp;
	}
	
	public Despatch getDespatchData()
	{
		Despatch dp = new Despatch(getContainer().getText(), getOriginPort().getText(), getCarrier().getText(), getMotherETD().getText(), getMotherETA().getText(), getEquipment().getText(), getMotherVessel().getText(),"");
		
		return dp;
	}
	
	public long getNoOfRowsGrid(String strFormName, String strGridControlName)
	{
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        long Rows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
        
        return Rows;
        
	}
	
	public String getFieldValue(String strFormName, String strControlName)
	{
		 objAdjunoUILibrary = new AdjunoUILibrary(driver);
		 String strGridCellValue="";
		 strGridCellValue = objAdjunoUILibrary.getElemetValue(strFormName, strControlName);
		 if(!isNullOrBlank(strGridCellValue))
		 {
			 
		 }
		 else
		 {
			 strGridCellValue="The text of the cell is not available";
		 }
		 return strGridCellValue;
		
	}
	
	
	
	


}
