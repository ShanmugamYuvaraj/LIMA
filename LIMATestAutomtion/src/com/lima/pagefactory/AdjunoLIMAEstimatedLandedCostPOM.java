package com.lima.pagefactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
//import CurrencyConversion.CurrencyConversionResponse;
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
import com.google.gson.Gson;
import com.lima.dto.CurrencyConversionResponsedto;
import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMAEstimatedLandedCostPOM {
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Estimated Landed Cost") WebElement lnkEstimatedLandedCost;
		
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableOriginNames']") List<WebElement> lstAvailableOriginColumn;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableDestinationNames']") List<WebElement> lstAvailableDestinationColumn;	
	
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableOriginNames'])[1]")WebElement selectFirstValue;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableOriginNames'])[2]")WebElement selectSecondValue;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_SelectedOriginNames'])[1]")WebElement selectedOriginfirstvalue;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_SelectedOriginNames'])[2]")WebElement selectedOriginSecondValue;
	
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableOriginNames']")List<WebElement> availableOrigins;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_SelectedOriginNames']")List<WebElement> selectedOrigins;
	
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableDestinationNames'])[1]")WebElement selectFirstDestinationValue;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableDestinationNames'])[2]")WebElement selectSecondDestinationValue;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_SelectedDestinationNames'])[1]")WebElement selectedDestinationfirstValue;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_SelectedDestinationNames'])[2]")WebElement selectedDestinationSecondValue;
	
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_AvailableDestinationNames']")List<WebElement> availableDestination;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_SelectedDestinationNames']")List<WebElement> selectedDestination;

	@FindBy(xpath = "(.//*[@class='x-form-text x-form-field input_tool'])[2]/following-sibling::img[@class='x-form-trigger x-form-arrow-trigger']")WebElement originCountryArrow;
	@FindBy(xpath ="(.//*[@class='x-form-text x-form-field input_tool'])[3]/following-sibling::img[@class='x-form-trigger x-form-arrow-trigger']")WebElement destinationCountryArrow;
	
	@FindBy(xpath = ".//*[@id='LIMA_Finance_EstimatedLandedCost_Tool_Textbox_OriginCountry']")WebElement originReadOnly;
	@FindBy(xpath = ".//*[@id='LIMA_Finance_EstimatedLandedCost_Tool_Textbox_DestinationCountry']")WebElement destinationReadOnly;
	
	@FindBy(xpath = ".//a[@id='LIMA_Finance_EstimatedLandedCost_Tool_Hyperlink_Tariff']")WebElement hyperlink;
	@FindBy(xpath = ".//*[@class='x-window-header-text']/preceding-sibling::div[@class='x-tool x-tool-close']") WebElement closebtn;
	
	@FindBy(xpath=".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_Equipment']")List<WebElement> lstEquipmentGrid;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-FCLFreightCosts_OriginCountry']")List<WebElement> lstOriginCountry;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-FCLFreightCosts_DestinationCountry']")List<WebElement> lstDestinationCountry;

	@FindBy(xpath = ".//*[@class='x-form-text x-form-field input_tool x-form-focus']/following-sibling::img")WebElement destinationArrow;
	@FindBy(xpath = "(.//*[@class='x-grid3-cell-inner x-grid3-col-AssociatedDescription'])[1]")WebElement associationDescription;
	
	@FindBy(linkText="Catalog") WebElement lnkCatalog;
	@FindBy(linkText="Lighthouse") WebElement lnklighthouse;
	@FindBy(xpath=".//*[@id='OptionsBarSubmitButton']") WebElement catalogbtnApply;
	@FindBy(xpath=".//span[.='FCL Freight Costs' and @class='x-tab-strip-text']")WebElement FCLFreightCosts;
	@FindBy(xpath=".//span[.='FCL Landside Costs' and @class='x-tab-strip-inner']")WebElement FCLLandsideCosts;
	@FindBy(xpath=".//span[@class='x-tab-strip-inner' and  .='FCL Haulage Costs']")WebElement FCLHaulageCosts;
	
	@FindBy(xpath= ".//*[@id='extId_LIMA_Finance_EstimatedLandedCost_Tool_Grid_FCLFreightCosts']/div[1]/span")WebElement strFCLFreightCosts;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Finance_EstimatedLandedCost_Tool_Grid_FCLLandsideCosts']/div[1]/span")WebElement strFCLLandSideCosts;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Finance_EstimatedLandedCost_Tool_Grid_FCLHaulageCosts']/div[1]/span")WebElement strFCLHaulageCosts;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[2]/a")List<WebElement> lstCatalogEquipment;
	@FindBy(xpath = ".//*[@id='CatalogEquipment_OptimumFill']") WebElement optimumfillValue;
	@FindBy(xpath = ".//*[@id='CatalogEquipment_CatalogItemDescription']") WebElement descriptionCatalog;
	
	@FindBy(xpath = ".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstCatalogDescription;
	
	@FindBy(xpath=".//*[@id='InnerContent']/p[2]") WebElement txtCount;
	@FindBy(xpath=".//*[@name='.PageNext']") WebElement catalogbtnPageNext;
	@FindBy(xpath=".//*[@id='InnerContent']/div[3]/table/tbody/tr/td[4]")List<WebElement> lstDescription;

	@FindBy(xpath =".//*[@id='ext-gen284']/div/table/tbody/tr/td[2]/div")List<WebElement> lstOriginCountrydropdown;
	@FindBy(xpath = ".//*[@id='ext-gen284']/div/table/tbody/tr/td[6]/div")List<WebElement> lstDestinationCountryDropDown;
	
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-ImportanceRank']/div[2]") List<WebElement> lstGridRankValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_Margin']")List<WebElement> lstGridMarginValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_ProductLandedCost']")List<WebElement> lstGridProductLandedCostValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_ProductShippingCost']")List<WebElement> lstGridProductShippingCostValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_ProductCost']")List<WebElement> lstGridProductCostValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_TransitTime']")List<WebElement> lstGridTransitTimeValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-TextboxEstimatedDeliveryDate']")List<WebElement> lstGridEstDeliveryDateValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_OriginPort']")List<WebElement> lstGridOriginPortValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_Mode']")List<WebElement> lstGridModeValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_DestinationPort']")List<WebElement> lstGridDestinationPortValue;
	@FindBy(xpath = ".//*[@class='x-grid3-cell-inner x-grid3-col-Textbox_Equipment']")List<WebElement> lstGridEquipmentValue;


	
	public WebElement getSelectFirstValue() {
		return selectFirstValue;
	}

	
	public WebElement getStrFCLFreightCosts() {
		return strFCLFreightCosts;
	}


	public WebElement getStrFCLLandSideCosts() {
		return strFCLLandSideCosts;
	}


	public WebElement getStrFCLHaulageCosts() {
		return strFCLHaulageCosts;
	}


	public WebElement getOptimumfillValue() {
		return optimumfillValue;
	}

	public WebElement getDestinationArrow() {
		return destinationArrow;
	}

	public WebElement getDescriptionCatalog() {
		return descriptionCatalog;
	}

	public List<WebElement> getLstCatalogEquipment() {
		return lstCatalogEquipment;
	}

	public WebElement getFCLFreightCosts() {
		return FCLFreightCosts;
	}

	public WebElement getFCLLandsideCosts() {
		return FCLLandsideCosts;
	}

	public WebElement getFCLHaulageCosts() {
		return FCLHaulageCosts;
	}
	

	public List<WebElement> getLstDescription() {
		return lstDescription;
	}

	public List<WebElement> getLstOriginCountrydropdown() {
		return lstOriginCountrydropdown;
	}

	public List<WebElement> getLstDestinationCountryDropDown() {
		return lstDestinationCountryDropDown;
	}

	public List<WebElement> getLstGridRankValue() {
		return lstGridRankValue;
	}

	public List<WebElement> getLstGridMarginValue() {
		return lstGridMarginValue;
	}

	public List<WebElement> getLstGridProductLandedCostValue() {
		return lstGridProductLandedCostValue;
	}

	public List<WebElement> getLstGridProductShippingCostValue() {
		return lstGridProductShippingCostValue;
	}

	public List<WebElement> getLstGridProductCostValue() {
		return lstGridProductCostValue;
	}

	public List<WebElement> getLstGridTransitTimeValue() {
		return lstGridTransitTimeValue;
	}

	public List<WebElement> getLstGridEstDeliveryDateValue() {
		return lstGridEstDeliveryDateValue;
	}

	public List<WebElement> getLstGridOriginPortValue() {
		return lstGridOriginPortValue;
	}

	public List<WebElement> getLstGridModeValue() {
		return lstGridModeValue;
	}

	public List<WebElement> getLstGridDestinationPortValue() {
		return lstGridDestinationPortValue;
	}

	public List<WebElement> getLstGridEquipmentValue() {
		return lstGridEquipmentValue;
	}

	public List<WebElement> getLstCatalogDescription() {
		return lstCatalogDescription;
	}

	public List<WebElement> getLstOriginCountry() {
		return lstOriginCountry;
	}

	public List<WebElement> getLstDestinationCountry() {
		return lstDestinationCountry;
	}

	public WebElement getClosebtn() {
		return closebtn;
	}
	public WebElement getLnkCatalog() {
		return lnkCatalog;
	}
	public WebElement getLnklighthouse() {
		return lnklighthouse;
	}
	
	public WebElement getCatalogbtnApply() {
		return catalogbtnApply;
	}
	
	public List<WebElement> getLstEquipmentGrid() {
		return lstEquipmentGrid;
	}

	public WebElement getOriginReadOnly() {
		return originReadOnly;
	}

	public WebElement getOriginCountryArrow() {
		return originCountryArrow;
	}

	public WebElement getDestinationCountryArrow() {
		return destinationCountryArrow;
	}

	public WebElement getSelectSecondValue() {
		return selectSecondValue;
	}

	public WebElement getHyperlink() {
		return hyperlink;
	}

	public WebElement getLnkTools() {
		return lnkTools;
	}

	public WebElement getDestinationReadOnly() {
		return destinationReadOnly;
	}

	public WebElement getLnkEstimatedLandedCost() {
		return lnkEstimatedLandedCost;
	}
	
	public List<WebElement> getLstAvailableDestinationColumn() {
		return lstAvailableDestinationColumn;
	}

	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}

	public WebElement getCatalogbtnPageNext() {
		return catalogbtnPageNext;
	}

	public WebElement getSelectedOriginfirstvalue() {
		return selectedOriginfirstvalue;
	}

	public WebElement getSelectedOriginSecondValue() {
		return selectedOriginSecondValue;
	}

	public List<WebElement> getAvailableOrigins() {
		return availableOrigins;
	}

	public List<WebElement> getSelectedOrigins() {
		return selectedOrigins;
	}

	public WebElement getSelectedDestinationfirstValue() {
		return selectedDestinationfirstValue;
	}

	public WebElement getSelectedDestinationSecondValue() {
		return selectedDestinationSecondValue;
	}

	public List<WebElement> getAvailableDestination() {
		return availableDestination;
	}
	
	public WebElement getTxtCount() {
		return txtCount;
	}

	public List<WebElement> getSelectedDestination() {
		return selectedDestination;
	}

	public List<WebElement> getLstAvailableOriginColumn() {
		return lstAvailableOriginColumn;
	}
	
	public WebElement getSelectFirstDestinationValue() {
		return selectFirstDestinationValue;
	}

	public WebElement getSelectSecondDestinationValue() {
		return selectSecondDestinationValue;
	}

	public AdjunoLIMAEstimatedLandedCostPOM(WebDriver driver) {
		this.driver = driver;

		// This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	public String callMouseHover(String strFormName, WebElement mainLink,WebElement subLink) {

		Actions action = new Actions(driver);

		action.moveToElement(mainLink).perform();

		action.moveToElement(subLink);

		action.click();

		action.perform();

		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strTitle = "";
		if (objAdjunoUILibrary.isPageLoaded(strFormName)) {
			strTitle = driver.getTitle();
		}
		return strTitle;
	}
	
		
	public ArrayList<String> getDropdownValues(String strFormName,String strControlName) {
		ArrayList<String> dropDownList= new ArrayList<String>();
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		
		dropDownList = objAdjunoUILibrary.getComboboxValues(strFormName, strControlName);
		return dropDownList;
	}

	public boolean setFieldValue(String strFieldValue, String formName,String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);

		try {
			bFlag = we.isDisplayed();
			we.sendKeys(strFieldValue);
			we.sendKeys(Keys.TAB);
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}

	// check grid value is read only
	public boolean checkFieldIsReadOnlyInGrid(String strFormName,String strControlName, int nRow, String strColumnName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		try {
			we = objAdjunoUILibrary.getGridCellElement(strFormName,strControlName, nRow, strColumnName);
			we.click();
			wait(5000);

			we = objAdjunoUILibrary.getGridCellElementEditor(strFormName,strControlName, nRow, strColumnName);
			bFlag = we.isDisplayed();
			we.sendKeys("");
			wait(1000);

		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (Exception e) {
			bFlag = false;
		}
		return bFlag;
	}
		
	public boolean clickButtonUsingWebElement(WebElement we) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			bFlag = we.isDisplayed();
			we.click();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;

	}
	
	public void validateStringValues(String string1,List<String> string2){
		String strMessage = "";
		for(int i=0;i<string2.size();i++){
			if(string1.equalsIgnoreCase(string2.get(i))){
			System.out.println(string1 +"," + string2.get(i));
			}else{
				strMessage = strMessage +" In Estimated Landed Cost Tool the Selected value is not displayed in dropdown field under shipments panel ";

			}
		}
		
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
		
	public boolean verifyPageIsLoaded(String strFormName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		try {
			objAdjunoUILibrary.isPageLoaded(strFormName);
			bFlag = true;
		} catch (Exception e) {
			bFlag = false;
		}
		return bFlag;
	}
		
	public void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private boolean isNullOrBlank(String s) {
		return (s == null || s.trim().equals(""));
	}
		
	public long getNoOfRowsinGrid(String strFormName, String strGridControlName) {

		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		long row = objAdjunoUILibrary.getNoOfRowsGrid(strFormName,strGridControlName);

		return row;
	}

	public boolean checkDoesElementExist(String formName, String controlName) {
		boolean bFlag;
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(formName, controlName);
		try {
			bFlag = we.isDisplayed();
		} catch (NoSuchElementException e) {
			bFlag = false;
		} catch (NullPointerException e) {
			bFlag = false;
		}
		return bFlag;
	}
	
	public double getShippingCostValuae(String equipment,double totalTariffValue,String totalCBM,String totalPieces){
		String strMessage="";
		double shippingCost = 0;
		int pieces=Integer.parseInt(totalPieces);
		double totalcbm=Double.parseDouble(totalCBM);
		if(equipment.equalsIgnoreCase("20FT")){
			double shipCost=(totalTariffValue/28)*totalcbm;
			shippingCost=shipCost/pieces;
		}else if(equipment.equalsIgnoreCase("40FT")){
			double shipCost=(totalTariffValue/34)*totalcbm;
			shippingCost=shipCost/pieces;
		}else if(equipment.equalsIgnoreCase("45FT")){
			double shipCost=(totalTariffValue/68)*totalcbm;
			shippingCost=shipCost/pieces;
		}else if(equipment.equalsIgnoreCase("40HC")){
			double shipCost=(totalTariffValue/67)*totalcbm;
			shippingCost=shipCost/pieces;
		}else{
			strMessage = strMessage+"In Estimated Landed CostTool Under Estimated Landed Cost Grid \"Equipment\" value is not matching";
		}
		return shippingCost;
	}
		
	public String getFieldValue(String strformName, String strControlName) {
		String tempVal = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		tempVal = objAdjunoUILibrary.getElemetValue2(strformName,strControlName);
		if (!isNullOrBlank(tempVal)) {

		} else {
			tempVal = "";
		}

		return tempVal;
	}
	
	public String getReadonlyFieldValue(String strID){
		String tempVal = "";
		objAdjunoUILibrary = new AdjunoUILibrary(driver);

		tempVal = objAdjunoUILibrary.getElementValueInReadMode(strID);
		if (!isNullOrBlank(tempVal)) {

		} else {
			tempVal = "";
		}

		return tempVal;
		
	}
	
	
	 public int valCount()
	 {
		 String strCount = getTxtCount().getText();
		 String[] vals = strCount.split("of ");
		 int nCount = Integer.parseInt(vals[1]);
	    
		 nCount= nCount/100;
		 if(nCount%100 == 0) {
		 }else {
			 nCount++;
		 }
	    	  
		 return nCount;  
	 }
	 
	 public ArrayList<String> getCatalogTableData(int nCount,String value,String strID2) {
		 ArrayList<String> list =new ArrayList<String>();
	     for(int j=0;j<=nCount-1;j++)
	     {
	     	for (int i = 0; i < lstCatalogEquipment.size(); i++) 
	     	{
	     		lstCatalogEquipment.get(i).click();
	     		wait(500);
     	//		System.out.println(associationDescription.getText());

	     		if(associationDescription.getText().equals(value)){
	     			System.out.println(associationDescription.getText()+"======"+value);
	     			System.out.println(getReadonlyFieldValue(strID2));
	     			list.add(getReadonlyFieldValue(strID2));
	     			driver.navigate().back();
	     		}else{
	     			driver.navigate().back();
	     		}
	     		
	     	}
	        	
	     	getCatalogbtnPageNext().click();
	        
	     }
	     return list;
	}
	 
	 public double currencyConversion(String fromCurrencyCode, String toCurrencyCode,String ContainerValue){
		 double str = 0;
		if(fromCurrencyCode.equalsIgnoreCase(toCurrencyCode)){
			double modContainerValue=Double.parseDouble(ContainerValue);
			str=modContainerValue;
		}else{
			double conversionRate=convert(fromCurrencyCode, toCurrencyCode);
			double modContainerValue=Double.parseDouble(ContainerValue);
			 str=conversionRate*modContainerValue;
		}
		 return str;
	 }
	
	 public static double convert(String fromCurrencyCode, String toCurrencyCode) {
		 final String API_PROVDIER = "http://api.fixer.io/";

		 if ((fromCurrencyCode != null && !fromCurrencyCode.isEmpty())&& (toCurrencyCode != null && !toCurrencyCode.isEmpty())) {

			 CurrencyConversionResponsedto response = getResponse(API_PROVDIER+"/latest?base="+fromCurrencyCode);
				
			 if(response != null) {
					
				 String rate = response.getRates().get(toCurrencyCode);
				 double conversionRate = Double.valueOf((rate != null)?rate:"0.0");
					System.out.println("Conversion Rate-"+conversionRate);
				 return conversionRate;
			 }
				
		 }

		 return 0.0;
	 }

	 private static CurrencyConversionResponsedto getResponse(String strUrl) {

		 CurrencyConversionResponsedto response = null;
		
		 Gson gson = new Gson();
		 StringBuffer sb = new StringBuffer();
			
		 if(strUrl == null || strUrl.isEmpty()) {
				
			 System.out.println("The Application URL is Null or Empty ");
			 return null;
		 }

		 URL url;
		 try {
			 url = new URL(strUrl);

			 HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			 InputStream stream = connection.getInputStream();

			 int data = stream.read();
			 while (data != -1) {
				 sb.append((char) data);
				 data = stream.read();
			 }
			 stream.close();
			 response = gson.fromJson(sb.toString(), CurrencyConversionResponsedto.class);
		 } catch (MalformedURLException e) {
			 
			 System.out.println(e.getMessage());
			 e.printStackTrace();
				
		 } catch (IOException e) {
			 System.out.println(e.getMessage());
			 e.printStackTrace();
		 }
		 return response;
	 }
	 
	 public ArrayList<String> getCatalogData(List<WebElement> wb,String strID1,String strID2)
	 {
		 ArrayList<String> supplierList =new ArrayList<String>();
	     for (int i=0; i < wb.size(); i++)
	     	{
	     		wb.get(i).click();
	     		
	     		String str=getReadonlyFieldValue(strID1);
	 			System.out.println("1. "+str);
	 			if(!isNullOrBlank(str)){
	 				wait(500);
	 				System.out.println(getReadonlyFieldValue(strID2));
	 				supplierList.add(getReadonlyFieldValue(strID2));   
	 				driver.navigate().back();
	 			}else{
	 				driver.navigate().back();
	 			}
	 
	     	}
	        return supplierList;
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
	
	public ArrayList<String> getListGridCellValue(String strFormName, String strGridControlName, long size,String strColumnName){
		ArrayList<String> arrList=new ArrayList<String>();
		objAdjunoUILibrary=new AdjunoUILibrary(driver);
		for(int i=0;i<size;i++){
			String value=objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, i, strColumnName);
			arrList.add(value);
		}
		
		return arrList;
				
	}
	
	public boolean verifyElementIsInReadOnlyMode(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		
		if(!we.getAttribute("readonly").equals("false"))
		{
			bFlag = true;
		}else{
			bFlag = false;
		}
		return bFlag;
	}
	
	public String verifySearchValue(String strValue, List<WebElement> wbList) {
		String strMessage = "";
		for (int i = 0; i <= wbList.size()-1; i++) {
			
			if(!wbList.get(i).getText().isEmpty() && wbList.get(i).getText().equalsIgnoreCase(strValue)){
				System.out.println(strValue);	
				System.out.println(wbList.get(i).getText());
			}else{
				if(wbList.size()==0){
					strMessage = strMessage + wbList.get(i).getText()+ "value is not matched For"+ strValue;
				}
			}
		}
		return strMessage;
	}
	
	public boolean verifyButtonisDisable(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		
		if(!we.getAttribute("disabled").equals("false"))
		{
			bFlag = true;
		}else{
			bFlag = false;
		}
		return bFlag;
	}
	
	
	public String verifyDropDownValues(ArrayList<String> catalogList,List<WebElement> dropDownListList) {
        String strMessage = "";
        for (int i = 0; i <= dropDownListList.size()-1; i++) {
            
        	for (int j = 0; j <= catalogList.size()-1; j++) {
            	
        		if(dropDownListList.get(i).getText().equalsIgnoreCase(catalogList.get(j))){
        			System.out.println(dropDownListList.get(i).getText() +" is matching "+catalogList.get(j));
        			break;
        		}else{
        			if(j==catalogList.size()-1){
        				strMessage = strMessage + dropDownListList.get(i)+" value is not found in catalog ";    
        			}
        		}
        	}
        }
        
        return strMessage;
	}
	
	public String verifyCatalogData(ArrayList<String> catalogList,ArrayList<String> dropDownListList) {
		String strMessage = "";
        for (int i = 0; i <= dropDownListList.size()-1; i++) {
            
        	for (int j = 0; j <= catalogList.size()-1; j++) {
        		System.out.println(dropDownListList.get(i)+"*****"+catalogList.get(j));
        		if(dropDownListList.get(i).equals("All")||dropDownListList.get(i).equalsIgnoreCase(catalogList.get(j))){
        			System.out.println(dropDownListList.get(i)+"======"+catalogList.get(j));
        			break;
        		}else{
        			if(j==catalogList.size()-1){
        				strMessage = strMessage + dropDownListList.get(i)+" value is not matching with"+" : "+ catalogList.get(j)+", ";    
        			}
        		}
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
	
	public String verifyTotalCtnsValue(String s1,String s2){
		System.out.println("String1 "+s1);
		System.out.println("String2 "+s2);

		int value1= Integer.parseInt(s1);
		int value2= Integer.parseInt(s2);
		int difference =value1/value2;
		String str= new Integer(difference).toString();
		System.out.println("difference "+str);
		return str;
	}
	public String getDifference(String s1,String s2){
		System.out.println("String1 "+s1);
		System.out.println("String2 "+s2);
		
		double value1= Double.parseDouble(s1);
		double value2= Double.parseDouble(s2);
		double difference =value1-value2;
		String str= new Double(difference).toString();
		System.out.println("Difference : "+str);
		return str;
	}
	
	public double getAddDoubleValues(double s1,double s2,double s3){
		System.out.println("String1 "+s1);
		System.out.println("String2 "+s2);
		System.out.println("String3 "+s3);

		double difference =s1+s2+s3;;
		
		return difference;
	}
	
	public String verifyRankValue(String firstmarginvalue,ArrayList<String> marginValues,ArrayList<String> rankValues){
		String value="";
		for(int i=0;i<marginValues.size();i++){
			if(marginValues.get(i).equals(firstmarginvalue)){
				 value = rankValues.get(i);
			}
		}
		return value;
		
	}
	
	public String verifyTotalCBMValue(String s1,String s2){
		System.out.println("String1 "+s1);
		System.out.println("String2 "+s2);

		int value1= Integer.parseInt(s1);
		double value2= Double.parseDouble(s2);
		System.out.println(value1+","+value2);
		double difference =value1*value2;
		String str= new Double(difference).toString();
		System.out.println("Product1 : "+str);
		return str;
	}
	
	public String verifyCBMperCarton(String s1,String s2,String s3){
		
		double value1= Double.parseDouble(s1);
	//	double Pvalue1=value1/100;
		
		double value2= Double.parseDouble(s2);
	//	double Pvalue2=value2/100;
		
		double value3= Double.parseDouble(s3);
	//	double Pvalue3=value3/100;
	//	System.out.println(Pvalue1+","+Pvalue2+","+Pvalue3);
	//	double productvalue = (Pvalue1*Pvalue2)*Pvalue3;
		double productvalue = (value1*value2)*value3;
		double productvalue1 =productvalue/1000000;
		System.out.println(productvalue1 +"is the product value");
		String str= new Double(productvalue1).toString();
		System.out.println(" Product2 : "+str);
		return str;
	
	}
	
	public String getDecimalValues(String s1,String s2){
		double value1= Double.parseDouble(s1);
		DecimalFormat df2 = new DecimalFormat(s2);
		System.out.println("double : " + df2.format(value1));
		
		String str= new Double(df2.format(value1)).toString();
		
		return str;
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
	
	//get the Grid cell value
	public String getGridCellValue(String strFormName, String strGridControlName, int nRow , String strColumnName){
		
		objAdjunoUILibrary=new AdjunoUILibrary(driver);
				
		String value=objAdjunoUILibrary.getGridCellElementValue(strFormName, strGridControlName, nRow, strColumnName);

		return value;
				
	}
	
	public String verifyCaptionsONGrid(ArrayList<String> list,NodeList nodeList, int nCaptions) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nCaptions - 1; j++) {
				String tempVal = e1.getElementsByTagName("Caption").item(j).getTextContent();
				for (int i = 1; i <= list.size() - 1; i++) {
					if (tempVal.equalsIgnoreCase(list.get(i))) {
						System.out.println("++++++tempVal "+tempVal +"is matching "+list.get(i) );

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
	
	public String verifyCaptionsONGrid1(ArrayList<String> list,NodeList nodeList, int nCaptions) {
		String strMessage = "";
		Element e1;
		if (nodeList.item(0).getNodeType() == Node.ELEMENT_NODE) {
			e1 = (Element) nodeList.item(0);
			for (int j = 0; j <= nCaptions - 1; j++) {
				String tempVal = e1.getElementsByTagName("Caption").item(j).getTextContent();
				for (int i = 0; i <= list.size() - 1; i++) {
					if (tempVal.equalsIgnoreCase(list.get(i))) {
						System.out.println("++++++tempVal "+tempVal +"is matching "+list.get(i) );

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
	
	
	public ArrayList<String> getListofValidationMessageFieldElement(String strFormName,String strControlName) 
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
			            
		ArrayList<String> strValidationMsg = objAdjunoUILibrary.getListofValidationMessageElement(strFormName, strControlName);
		        
		return strValidationMsg;
	}


	
}
