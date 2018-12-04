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

import com.lima.dto.Tracksdata;
import com.lima.library.AdjunoLIMALibrary;
import com.lima.library.AdjunoUILibrary;
//import com.lima.test.Select;

public class AdjunoLIMAContainerRestitutionPOM {
	WebDriver driver;
	
	AdjunoUILibrary objAdjunoUILibrary;
	AdjunoLIMALibrary objAdjunoLIMALibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Container Restitution") WebElement lnkContainerRestitution;
	@FindBy(linkText="Lighthouse") WebElement lnkLightHouse;
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a") List<WebElement> lstItemDescription;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_CatalogItemDescription']") WebElement lstDescriptionField;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@name='.PageFirst']") WebElement btnFirst;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[3]") List<WebElement> lstForwardref;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[4]") List<WebElement> lstStatusAwaiting;
	@FindBy(xpath=".//*[text()='Processed 100%']") WebElement txtProcessedMsg;
	@FindBy(linkText = "Track") WebElement lnkTrack;
	@FindBy(linkText = "Edit") WebElement lnkEdit;
	@FindBy(linkText = "Find") WebElement lnkFind;
	@FindBy(xpath=".//*[@name='Reference']") WebElement fldRefNo;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnTrackApply;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement btnApply;
	@FindBy(xpath=".//*[@id='LH_Container_Restitution_Upload_SUT_UploadContainerRestitutionTemplate']") WebElement btnUpload;
	@FindBy(xpath=".//*[@id='extId_LH_Off_Dock_Storage_Upload_SUT_DeliveryBooking_bbar_btn_DeleteRows']") WebElement btnDelete;
	@FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	@FindBy(xpath=".//*[@class='Title']") WebElement restitutionpagetitle;
	@FindBy(xpath=".//*[text()='Search']") WebElement chevSearch;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Select']") WebElement chevSelect;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[4]/div[2]/div[1]/span") WebElement chevUpdate;
	@FindBy(xpath=".//*[text()='Restitution']") WebElement chevRestitution;
	@FindBy(xpath=".//*[text()='Complete']") WebElement chevComplete;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Complete']") WebElement chevronComplete;
	@FindBy(xpath=".//*[text()='No items were found.']") WebElement txtNoProductErr;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[1]") WebElement thDestination;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr[1]/th[2]") WebElement thDeliveryDate;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[2]") WebElement thContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[3]") WebElement thForwarderReference;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[1]/th[4]") WebElement thStatus;
	@FindBy(xpath=".//*[@id='CatalogLOCODE_IsOrigin']") WebElement isCheckboxClicked;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[1]/div/div") WebElement RestitutionPageCheckBox;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl']") WebElement txtViewDetail;
	@FindBy(xpath="(.//*[text()='Container Restitution'])[last()]") WebElement tracklnkContainerRestitution;
	@FindBy(xpath=".//*[text()='Shipment']") WebElement txtShipmentTrack;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[2]/a") List<WebElement> lstTrackEvent;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[4]") List<WebElement> lstTrAchivedDate;
	@FindBy(xpath=".//*[@id='InnerContent']/table[2]/tbody/tr/td[5]") List<WebElement> lstTrUserName;
	
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[2]/div") WebElement thContainerHeader;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[4]/div") WebElement thDestinationHeader;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[5]/div") WebElement thDeliveryBookingDate;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[6]/div") WebElement thCarrier;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[7]/div") WebElement thHaulier;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[8]/div") WebElement thCollectionDate;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[9]/div") WebElement thRestitutionDate;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[10]/div") WebElement thRestitutionTime;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[12]/div") WebElement thComments;
	@FindBy(xpath=".//*[@id='ext-gen31']/div/table/thead/tr/td[11]/div") WebElement thReason;
	
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") WebElement trContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[4]/font") WebElement trStatus;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[4]/div") List<WebElement> trDestination;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[5]/div") List<WebElement> trDeliveryBookingDate;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[6]/div") List<WebElement> trCarrier;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[7]/div") List<WebElement> trHaulier;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[8]/div") List<WebElement> trCollectionDate;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[9]/div") List<WebElement> trRestitutionDate;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[10]/div") List<WebElement> trRestitutionPoint;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[11]/div") List<WebElement> trReason;
	@FindBy(xpath=".//*[@id='ext-gen27']/div/table/tbody/tr/td[12]/div") List<WebElement> trComments;
	@FindBy(xpath=".//*[@id='ProgressForm_FeedbackControl_inner']") WebElement trackMessage;
	
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Container')]") WebElement hdContainer;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Seal')]") WebElement hdSeal;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-CollectionDate')]") WebElement hdCollectionDate;
	@FindBy(xpath=" .//*[contains(@class,'x-grid3-hd-RestitutionDate')]") WebElement hdRestitutionDate;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-RestitutionPoint')]") WebElement hdRestitutionPoint;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-ReasonCode')]") WebElement hdReasonCode;
	@FindBy(xpath=".//*[contains(@class,'x-grid3-hd-Comments')]") WebElement hdComments;
	
	 
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-Container')]") WebElement colContainer;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-CollectionDate')]") WebElement colCollectionDate;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-RestitutionDate')]") WebElement colRestitutionDate;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-RestitutionPoint')]") WebElement colRestitutionPoint;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-ReasonCode')]") WebElement colReasonCode;
	 @FindBy(xpath=".//*[contains(@class,'x-grid3-col-Comments')]") WebElement colComments;
	 @FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[2]") List<WebElement> lstETAFrom;
		
	@FindBy(xpath=".//*[@id='LH_ContainerRestitution_SUT_PARAMS_FORK_ContainerRestitution_RefineSearch']") WebElement thRefineSearch;
	@FindBy(xpath=".//*[@id='LH_ContainerRestitution_SUT_PARAMS_UploadContainerRestitutionTemplate']") WebElement thUpload;
	@FindBy(xpath=".//*[@id='LH_ContainerRestitution_SUT_PARAMS_OpenReport']") WebElement lnkDownloadContainerRestitutionTemplate;
	@FindBy(xpath=".//*[@id='SUT_StageBar_Update']") WebElement chevronRestitution;
	@FindBy(xpath=".//*[text()='You must make selection(s) before progressing']") WebElement txtProgressingErrMsg;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[1]/input") WebElement CheckBox;
	//@FindBy(xpath=".//*[@id='InnerContent']/div[3]/div[2]/table/tbody/tr[2]/td[2]") List<WebElement> LstContainerNo;
	@FindBy(xpath=".//*[@id='InnerContent']/div/table/tbody/tr[2]/td[1]") List<WebElement> destinationresultlist;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[4]/font") List<WebElement> statusresultlist;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[4]/font") List<WebElement> haulierresultlist;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]") List<WebElement> lstContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[2]") List<WebElement> containerresultlist;
	//@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[3]") List<WebElement> forwarderrefresultlist;
	@FindBy(xpath=".//*[@id='ext-gen18']/div/table/tbody/tr/td[1]/div") List<WebElement> TrackContainer;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div/div/table/tbody/tr[2]/td[1]/input") List<WebElement> selectBox;
	@FindBy(xpath=".//*[@id='InnerContent']/div/div[2]/table/tbody/tr/td[1]/input") List<WebElement> lstSelect;
	@FindBy(xpath=".//*[@id='OptionsBar']/div[1]/p") WebElement editTitle;
	
	public WebElement getLnkTools() {
		return lnkTools;
	}
	public WebElement getTitleContainer() {
		return restitutionpagetitle;
	}
	public WebElement getLnkLightHouse() {
		return lnkLightHouse;
	}
	
	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}
	
	public WebElement getCatalogbtnApply() {
		return catalogbtnApply;
	}
	
	public WebElement getTxtCount() {
		return txtCount;
	}
	
	public WebElement getcatalogbtnPageNext(){
		return catalogbtnPageNext;
	}
	public List<WebElement> getLstStatusAwaiting() {
 		return lstStatusAwaiting;
 	}
	public WebElement getBtnFirst() {
		return btnFirst;
	}
	public WebElement getTxtViewDetail() {
		return txtViewDetail;
	}
	public WebElement getTxtShipmentTrack() {
		return txtShipmentTrack;
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
	
	public WebElement getBtnUpload() {
		return btnUpload;
	}
	
	public WebElement getBtnYes() {
		return btnYes;
	}
	
	public WebElement getBtnDelete() {
		return btnDelete;
	}
	
	public WebElement getLnkFind() {
		return lnkFind;
	}
	public WebElement getBtnTrackApply() {
		return btnTrackApply;
	}
	public WebElement getBtnApply() {
		return btnApply;
	}
	public WebElement getTrackMessage() {
		return trackMessage;
	}
	
	public WebElement getEditTitle() {
		return editTitle;
	}
	
	public WebElement getColContainer() {
		return colContainer;
	}

	public WebElement getColCollectionDate() {
		return colCollectionDate;
	}

	public WebElement getColRestitutionDate() {
		return colRestitutionDate;
	}

	public WebElement getColRestitutionPoint() {
		return colRestitutionPoint;
	}

	public WebElement getColReasonCode() {
		return colReasonCode;
	}

	public WebElement getColComments() {
		return colComments;
	}

	
	public WebElement getHdContainer() {
		return hdContainer;
	}

	public WebElement getHdSeal() {
		return hdSeal;
	}

	public WebElement getHdRestitutionDate() {
		return hdRestitutionDate;
	}

	public WebElement getHdRestitutionPoint() {
		return hdRestitutionPoint;
	}

	
	public WebElement getHdReasonCode() {
		return hdReasonCode;
	}

	public WebElement getHdComments() {
		return hdComments;
	}
	
	public WebElement getHdCollectionDate() {
		return hdCollectionDate;
	}
	
	
	public List<WebElement> getLstTrackEvent() {
		return lstTrackEvent;
	}
	public List<WebElement> getLstForwardref() {
		return lstForwardref;
	}
	public List<WebElement> getLstContainer() {
		return lstContainer;
	}
	public List<WebElement> getLstTrAchivedDate() {
		return lstTrAchivedDate;
	}
	
	public List<WebElement> getLstTrUserName() {
		return lstTrUserName;
	}
	public WebElement getTracklnkContainerRestitution() {
		return tracklnkContainerRestitution;
	}
	
	public List<WebElement> getSelectBox() {
		return selectBox;
	}
	public List<WebElement> getTrackContainer() {
		return TrackContainer;
	}
	
	public List<WebElement> getDestinationresultlist() {
		return destinationresultlist;
	}
	public ArrayList<String> getDestinationValues() 
	{
		ArrayList<String> list =new ArrayList<String>();
		for (int i = 0; i < destinationresultlist.size(); i++) 
		{
			list.add(destinationresultlist.get(i).getText());
	
		}
		
		return list;
	}
	
	public List<String> getDestionationPortM() {
		List<String> list = new ArrayList<String>();
	    System.out.println("Size of Destination port"+getDestinationresultlist().size());
		for (int i = 0; i <= getDestinationresultlist().size()-1; i++) {
			
		list.add(getDestinationresultlist().get(i).getText());
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}
	public String verifyDestinationPort(String strDestinationPort,List<String> list) {
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
				
			if(list.get(i).equalsIgnoreCase(strDestinationPort)){
				//System.out.println("test--"+strDestinationPort);
			}else{
				
				strMessage = strMessage + "For this  :"+ list.get(i)+ "Destination Port is not matching with "+ strDestinationPort;
			}
		}
		System.out.println("error message:"+strMessage);
		return strMessage;
	}
	
	public List<String> getForwardRefValues() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= getLstForwardref().size()-1; i++) {
			
			list.add(getLstForwardref().get(i).getText());
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}
	public String verifyForwardRefvalue(String strForwarderRef,List<String> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).equalsIgnoreCase(strForwarderRef)){
				
			}else{
				strMessage = strMessage + " " + list.get(i)+" value is not matching "+strForwarderRef;
			}
		}
		return strMessage;
	}
	public String verifyContainervalue(String strContainerNo,List<String> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).equalsIgnoreCase(strContainerNo)){
				
			}else{
				strMessage = strMessage + " " + list.get(i)+" value is not matching "+strContainerNo;
			}
		}
		return strMessage;
	}
	public ArrayList<String> getStatusValues() 
	{
		ArrayList<String> list =new ArrayList<String>();
		for (int i = 0; i < statusresultlist.size(); i++) 
		{
			list.add(statusresultlist.get(i).getText());
	
		}
		
		return list;
	}
	
	public ArrayList<String> getHaulierValues() 
	{
		ArrayList<String> list =new ArrayList<String>();
		for (int i = 0; i < haulierresultlist.size(); i++) 
		{
			list.add(haulierresultlist.get(i).getText());
	
		}
		
		return list;
	}
	
	public List<String> getContainerValues() {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i <= getLstContainer().size()-1; i++) {
			
			list.add(getLstContainer().get(i).getText());
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}
	
	
	public List<WebElement> getLstContainers() {
		return containerresultlist;
	}
	
	public List<WebElement> getLstSelect() {
		return lstSelect;
	}
	
/*	public ArrayList<String> getForwarderRefValues() 
	{
		ArrayList<String> list =new ArrayList<String>();
		for (int i = 0; i < forwarderrefresultlist.size(); i++) 
		{
			list.add(forwarderrefresultlist.get(i).getText());
	
		}
		
		return list;
	}
	*/
	public String verifyCompleteMessage(String strFormName, String strControlName) {
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		String strCompletMessage = objAdjunoUILibrary.getElemetValue(strFormName,strControlName);
		return strCompletMessage;
	}
	
	 public ArrayList<String> getCatalogTableData1(int nCount) {
		 ArrayList<String> supplierList =new ArrayList<String>();
	     for(int j=0;j<=nCount-1;j++)
	     {
	     	for (int i = 0; i < lstDescription.size(); i++) 
	     	{
	     		supplierList.add(lstDescription.get(i).getText());
	       		
	     	}
	     		
	     	getcatalogbtnPageNext().click();
	        
	     }
	     return supplierList;
	}
	
	public boolean verifyResults(String strDestinationValue,ArrayList<String> DestinationResultList) {
        //String strMessage = "";
		boolean bFlag = true;
        for (int i = 0; i <= DestinationResultList.size()-1; i++) {
                    	           	
        		if(!DestinationResultList.get(i).equalsIgnoreCase(strDestinationValue)){
        			bFlag = false;
        			
        		}
        		
        }
        return bFlag;
	}
	public WebElement getTrStatus() {
		return trStatus;
	}
	
	public String verifyContainerAndStaus(String strContainer,	String strStatus) {
		String strMessage = "";
		
		if(getTrContainer().getText().equalsIgnoreCase(strContainer)){
			
		}else{
			strMessage = strContainer + "Container value is mismatch select chevron- Exepected value:"+strContainer +" But Found:"+getTrContainer().getSize();
		}
		
		if(getTrStatus().getText().equalsIgnoreCase(strStatus)){
			
		}else{
			strMessage = strContainer + "status value is mismatch in select chevron- Exepected value:"+strStatus +" But Found:"+getTrStatus().getSize();
		}
		return strMessage;
	}

	public int valCount()
	{
		 String strCount = getTxtCount().getText();
		 String[] vals = strCount.split("of ");
		 int nCount = Integer.parseInt(vals[1]);
	    
		 if(nCount%100 == 0) {
			 nCount= nCount/100;
		 }else {
			 nCount= nCount/100;
			 
			 nCount++;
		 }
		 System.out.println(nCount);
		 return nCount;  
	 }
	
	 public ArrayList<String> getCatalogTableData(int nCount) 
	 {
		 boolean bFlag;
	     ArrayList<String> list =new ArrayList<String>();
	     for(int j=0;j<=nCount-1;j++)
	     {
	     	for (int i=0; i < lstItemDescription.size(); i++){
	     		lstItemDescription.get(i).click();
	     		bFlag = isCheckboxClicked.isSelected();
	     		
	     		if(bFlag){
	     		//	wait(500);
	     			list.add(lstDescriptionField.getText());     				
	     			driver.navigate().back();	
	     		}else{
	     			driver.navigate().back();	
	     		}
	     			
	     	}
	   	        	
	     	getcatalogbtnPageNext().click();
	     }
	     
	        return list;
	 }
	
		
	public WebElement getLnkContainerRestitution() {
		return lnkContainerRestitution;
	}
	public WebElement getChevSearch() {
		return chevSearch;
	}

	public WebElement getChevSelect() {
		return chevSelect;
	}
	
	public WebElement getChevUpdate() {
		return chevUpdate;
	}

	public WebElement getChevRestitution() {
		return chevRestitution;
	}

	public WebElement getChevComplete() {
		return chevComplete;
	}
	public WebElement getChevronComplete() {
		return chevronComplete;
	}
	
	public WebElement getTxtNoProductErr() {
		return txtNoProductErr;
	} 

	public WebElement getThDestination() {
		return thDestination;
	}
	public WebElement getThDeliveryDate() {
		return thDeliveryDate;
	}
	public WebElement getThContainer() {
		return thContainer;
	}
	public WebElement getThForwarderReference() {
		return thForwarderReference;
	}
	public WebElement getThStatus() {
		return thStatus;
	}

	public WebElement getThContainerHeader() {
		return thContainerHeader;
	}
	
	public WebElement getThDestinationHeader() {
		return thDestinationHeader;
	}
	
	public WebElement getThDeliveryBookingDate() {
		return thDeliveryBookingDate;
	}
	
	public WebElement getThCarrier() {
		return thCarrier;
	}
	public WebElement getThHaulier() {
		return thHaulier;
	}
	public WebElement getThCollectionDate() {
		return thCollectionDate;
	}
	public WebElement getThRestitutionDate() {
		return thRestitutionDate;
	}
	public WebElement getThRestitutionTime() {
		return thRestitutionTime;
	}
	public WebElement getThReason() {
		return thReason;
	}
	public WebElement getThComments() {
		return thComments;
	}
	public WebElement getThRefineSearch() {
		return thRefineSearch;
	}
	
	public WebElement getThUpload() {
		return thUpload;
	}
	
	public WebElement getCheckBox() {
		return CheckBox;
	}
	
	public WebElement getRestitutionPageCheckBox() {
		return RestitutionPageCheckBox;
	}
	
	public WebElement getChevronRestitution() {
		return chevronRestitution;
	}
	public WebElement getlnkDownloadContainerRestitutionTemplate() {
		return lnkDownloadContainerRestitutionTemplate;
	}
	public List<WebElement> getLstETAFrom() {
		return lstETAFrom;
	}
		
	public WebElement getTxtProgressingErrMsg() {
		return txtProgressingErrMsg;
	}
	
	public List<WebElement> getLstContainerNo() {
		return containerresultlist;
	}
	public WebElement getTxtProcessedMsg() {
		return txtProcessedMsg;
	}
	
	public AdjunoLIMAContainerRestitutionPOM(WebDriver driver)
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
	public boolean isPageLoaded(String strFormName){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
    	
		return objAdjunoUILibrary.isPageLoaded(strFormName);	
	}
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink)
	{
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
	    	  strTitle = strTitle + "Requested page is not loaded";
	      }
	         return strTitle;
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
	public List<Date> getArrivedDate() throws ParseException {
		List<Date> list = new ArrayList<Date>();
	  
		for (int i = 0; i <= getLstETAFrom().size()-1; i++) {
			
			Date arrivalDate = new SimpleDateFormat("dd MMM yyyy").parse(getLstETAFrom().get(i).getText());
			list.add(arrivalDate);
		//	System.out.println("For"+i + " Destination port :"+po.getDestinationPort());
		}
		
		return list;
	}

	public String verifyArrivedDate(Date etaFromDate, List<Date> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).after(etaFromDate) || list.get(i).equals(etaFromDate)){
				
			}
			else{
			strMessage = strMessage + " "+list.get(i)+" value is before "+ etaFromDate;
			}
		}
		
		
		return strMessage;
	}
	
	public String verifyArrivedDate2(Date etaToDate, List<Date> list) {
		String strMessage = "";
		for (int i = 0; i <=list.size()-1; i++) {
			if(list.get(i).before(etaToDate) || list.get(i).equals(etaToDate)){
				
			}
			else{
				strMessage = strMessage + " "+list.get(i)+" value is after "+ etaToDate;
			}
		}
		
		return strMessage;
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
	public String getDate(int days, String pattern) {

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		// dd MMM yyyy HH:mm
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.format(cal.getTime());

		return dateFormat.format(cal.getTime());
	}
	public long getNoOfRowsGrid(String strFormName, String strGridControlName){
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		return objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
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
	
	public String getGridCellElementValue(String strformName, String strControlName, int nRow, String strColonmName) {
		String tempVal;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
				
		tempVal = objAdjunoUILibrary.getGridCellElementValue(strformName, strControlName,nRow,strColonmName);
		if (!isNullOrBlank(tempVal)) {

		} else {
			tempVal = "";
		}
	
       return tempVal;				
	}
	
	public String getNextDateFromCertainDate(String strDate,String format,int day) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar c = Calendar.getInstance();
		c.setTime(sdf.parse(strDate));
		c.add(Calendar.DATE, day);
		return sdf.format(c.getTime());
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
            if(list.get(j).getEvent().equalsIgnoreCase("Forwarder Reference")&&list.get(j).getUsername().equalsIgnoreCase(userName)){
                String strAchievedDate = list.get(j).getAcheived();
                
                SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.UK);
                Date today = new Date();
                
                Date achievedDate = formatter.parse(strAchievedDate);
            
                min = getDifference(today,achievedDate);
               // System.out.println("difference is:"+min);
            }
        }
        return min;
    }
	public long getDifference(Date today, Date date) 
	{
        
        if( date == null || today == null ) return 0;

        long time = (today.getTime()/60000) - (date.getTime()/60000);
     //   System.out.println("Date:"+ time);
        
        return ((today.getTime()/60000) - (date.getTime()/60000));
        
    }

	
	public boolean clickButtonUsingWebElement(WebElement we) 
	{
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
	public WebElement getGridCellElement(String strFormName, String strControlName, int nRow, String strColumnName) 
	{
	
		return objAdjunoUILibrary.getGridCellElement(strFormName, strControlName, nRow, strColumnName);     
		
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
	
	public String getWebElementValue(WebElement we)
	{
		
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
	public boolean expandViewDetails(String strFormName, String strControlName) {
		boolean bFlag = true;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		try{
			objAdjunoUILibrary.clickViewDetail(strFormName, strControlName);
			
		}catch(Exception ex){
			bFlag= false;
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
	
	public String setDate(String strPattern)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		SimpleDateFormat dateformatter = new SimpleDateFormat(strPattern);
		String dtTodayDate = dateformatter.format(new Date());
		return dtTodayDate;
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
	public ArrayList<String> getDropDownList(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		ArrayList<String> dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;  
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
	public int getNoOfRowsResulted() {
	       
        return getLstContainerNo().size();
    }
	public List<String> getStatusAwait() {
		List<String> list = new ArrayList<String>();
	    //System.out.println("Size of Status"+getLstStatusAwaiting().size());
		for (int i = 0; i <= getLstStatusAwaiting().size()-1; i++) {			
			list.add(getLstStatusAwaiting().get(i).getText());
			//System.out.println("For"+i + " Status : "+po.getStatus());
		}
		
		return list;
	}
	public String verifyStatusAwaiting(String strStatusAwaitng,List<String> list) {
		String strMessage= "";
		for (int i = 0; i <= list.size()-1; i++) {
			if(list.get(i).equalsIgnoreCase(strStatusAwaitng)){
				
			}else{
				strMessage = strMessage + "For this Container :"+ list.get(i)+ "Status is not matching";
			}
		}
		System.out.println("error message:"+strMessage);
		return strMessage;
	}
	public String verifyStatusDropDown(NodeList nodeList,ArrayList<String> list, int nstatus) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE)
     	{
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nstatus-1; j++) {
				String tempVal = e1.getElementsByTagName("Status_Value").item(j).getTextContent();
    			for (int i = 0; i <= list.size()-1; i++) {
				
					if(tempVal.equalsIgnoreCase(list.get(i))){
						break;
					}else{
						if(i==list.size()-1){
							strMessage = strMessage+ tempVal+ " from Xml is not found in Drop down ";
						}								
					}
    			}
			
			}
     	}	
		return strMessage;
	}
	
	public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
        String strMessage = "";
        for (int i = 0; i < dropDownListList.size()-1; i++) {
            
        	for (int j = 0; j < catalogList.size()-1; j++) {
            	
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

	public WebElement getTrContainer() {
		return trContainer;
	}
	
	public List<WebElement> getTrDestination() {
		return trDestination;
	}
	public List<WebElement> getTrDeliveryBookingDate() {
		return trDeliveryBookingDate;
	}
	public List<WebElement> getTrCarrier() {
		return trCarrier;
	}
	public List<WebElement> getTrHaulier() {
		return trHaulier;
	}
	public List<WebElement> getTrCollectionDate() {
		return trCollectionDate;
	}
	public List<WebElement> getTrRestitutionDate() {
		return trRestitutionDate;
	}
	public List<WebElement> getTrRestitutionPoint() {
		return trRestitutionPoint;
	}
	public List<WebElement> getTrReason() {
		return trReason;
	}
	public List<WebElement> getTrComments() {
		return trComments;
	}
	
}
