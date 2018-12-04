package com.lima.pagefactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.hamcrest.Factory;
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

import com.lima.library.AdjunoUILibrary;

public class AdjunoLIMATariffPOM {
	
	WebDriver driver;
	AdjunoUILibrary objAdjunoUILibrary;
	
	@FindBy(linkText="Tools") WebElement lnkTools;
	@FindBy(linkText="Tariff") WebElement lnkTariff;
	
	@FindBy(xpath=".//*[@class = 'xtb-text' and contains(text(),'Displaying rows')]") WebElement txtNoRows;
	@FindBy(xpath=".//*[@id='extId_LIMA_TariffTool_Grid_SeaFreight']/div[2]/div[2]/div/table/tbody/tr/td/table/tbody/tr/td[8]/table/tbody/tr[2]/td[2]/em/button") WebElement btnNext;
	@FindBy(xpath=".//*[@id='extId_LIMA_TariffTool_Grid_SeaFreight']/div[2]/div[2]/div/table/tbody/tr/td/table/tbody/tr/td[2]/table/tbody/tr[2]/td[2]/em/button") WebElement btnPrevious;
	@FindBy(xpath=".//*[@id='extId_LIMA_TariffTool_Grid_SeaFreight']/div[2]/div[2]/div/table/tbody/tr/td[1]/table/tbody/tr/td[5]/input") WebElement txtFieldPages;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLFreightCosts_bbar_btn_AddRow']") WebElement btnFCLFreightCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLFreightCosts_bbar_btn_DeleteRows']") WebElement btnFCLFreightCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_GOHCharges_bbar_btn_AddRow']") WebElement btnGOHAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_GOHCharges_bbar_btn_DeleteRows']") WebElement btnGOHDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLAdditionalFreightCharges_bbar_btn_AddRow']") WebElement btnAdditionalFreightCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLAdditionalFreightCharges_bbar_btn_DeleteRows']") WebElement btnAdditionalFreightCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLLandsideCosts_bbar_btn_AddRow']") WebElement btnFCLLandsideCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLLandsideCosts_bbar_btn_DeleteRows']") WebElement btnFCLLandsideCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLOriginLandsideCosts_bbar_btn_AddRow']") WebElement btnFCLOriginLandsideCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLOriginLandsideCosts_bbar_btn_DeleteRows']") WebElement btnFCLOriginLandsideCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLHaulageCosts_bbar_btn_AddRow']") WebElement btnFCLHaulageCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLHaulageCosts_bbar_btn_DeleteRows']") WebElement btnFCLHaulageCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLAdditionalHaulageCosts_bbar_btn_AddRow']") WebElement btnFCLAdditionalHaulageCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLHaulageCosts_bbar_btn_DeleteRows']") WebElement btnFCLAdditionalHaulageCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_RentDemurrage_bbar_btn_AddRow']") WebElement btnFCLDemurrageAndStorageCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_RentDemurrage_bbar_btn_DeleteRows']") WebElement btnFCLDemurrageAndStorageDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLFreightCosts_bbar_btn_AddRow']") WebElement btnLCLFreightCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLFreightCosts_bbar_btn_DeleteRows']") WebElement btnLCLFreightCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLAdditionalLCLFreightCharges_bbar_btn_AddRow']") WebElement btnLCLAdditionalFreightChargesAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLAdditionalLCLFreightCharges_bbar_btn_DeleteRows']") WebElement btnLCLAdditionalFreightChargesDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLLandsideCosts_bbar_btn_AddRow']") WebElement btnLCLLandsideCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLLandsideCosts_bbar_btn_DeleteRows']") WebElement btnLCLLandsideCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLHaulageCosts_bbar_btn_AddRow']") WebElement btnLCLHaulageCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLHaulageCosts_bbar_btn_DeleteRows']") WebElement btnLCLHaulageCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLStorageChanges_bbar_btn_AddRow']") WebElement btnLCLStorageChangesAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLStorageChanges_bbar_btn_DeleteRows']") WebElement btnLCLStorageChangesDeleteRow;
	@FindBy(xpath=".//*[@name='Apply']") WebElement btnApply;
	@FindBy(xpath=".//*[text()='Validation Error']") WebElement ValidationErrPopUp;
	@FindBy(xpath=".//*[text()='Validation']") WebElement ValidationErrPopUpAirFreight;
	@FindBy(xpath=".//*[contains(text(),'Validation error in ')]") WebElement ValidationErrMsg;
	@FindBy(xpath=".//*[contains(text(),'Validation error in LIMA_')]") WebElement ValidationErrMsgAirFreight;
	
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOK;
	@FindBy(xpath=".//*[@class = 'x-window-header-text' and text()='Delete rows']") WebElement deleteRowPopUp;
	@FindBy(xpath=".//*[text()='Yes']") WebElement btnYes;
	
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_AirRates_bbar_btn_AddRow']") WebElement btnAirRatesAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_AirRates_bbar_btn_DeleteRows']") WebElement btnAirRatesDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_ExpressService_bbar_btn_AddRow']") WebElement btnExpressServiceAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_ExpressService_bbar_btn_DeleteRows']") WebElement btnExpressServiceDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_LandsideCharges_bbar_btn_AddRow']") WebElement btnAirLandsideCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_LandsideCharges_bbar_btn_DeleteRows']") WebElement btnAirLandsideCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_HaulageCosts_bbar_btn_AddRow']") WebElement btnAirHaulageCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_HaulageCosts_bbar_btn_DeleteRows']") WebElement btnAirHaulageCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_StorageCharges_bbar_btn_AddRow']") WebElement btnAirStorageCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_StorageCharges_bbar_btn_DeleteRows']") WebElement btnAirStorageCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_AmendmentLog_bbar_btn_AddRow']") WebElement btnAirAmendmentLogAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Airfreight_Grid_AmendmentLog_bbar_btn_DeleteRows']") WebElement btnAirAmendmentLogDeleteRow;
	@FindBy(xpath=".//*[@class='x-form-trigger x-form-arrow-trigger']") WebElement btnComboBoxType;
	
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_FullTrailerCharges_bbar_btn_AddRow']") WebElement btnFullTrailerCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_FullTrailerCharges_bbar_btn_DeleteRows']") WebElement btnFullTrailerCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_FullTrailerGOH_bbar_btn_AddRow']") WebElement btnFullTrailerGOHCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_FullTrailerGOH_bbar_btn_DeleteRows']") WebElement btnFullTrailerGOHCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_LandsideCharges_bbar_btn_AddRow']") WebElement btnFullTrailerLandsideCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_LandsideCharges_bbar_btn_DeleteRows']") WebElement btnFullTrailerLandsideCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_DeliveryCharges_bbar_btn_AddRow']") WebElement btnFullTrailerDeliveryCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_DeliveryCharges_bbar_btn_DeleteRows']") WebElement btnFullTrailerDeliveryCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageTrailerCharges_bbar_btn_AddRow']") WebElement btnGroupageTrailerCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageTrailerCharges_bbar_btn_DeleteRows']") WebElement btnGroupageTrailerCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageGOH_bbar_btn_AddRow']") WebElement btnGroupageGOHCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageGOH_bbar_btn_DeleteRows']") WebElement btnGroupageGOHCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageLandsideCharges_bbar_btn_AddRow']") WebElement btnGroupageLandsideAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageLandsideCharges_bbar_btn_DeleteRows']") WebElement btnGroupageLandsideDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageHaulageCharges_bbar_btn_AddRow']") WebElement btnGroupageHaulageCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_GroupageHaulageCharges_bbar_btn_DeleteRows']") WebElement btnGroupageHaulageCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_AmendmentLog_bbar_btn_AddRow']") WebElement btnRoadAmendmentLogAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Roadfreight_Grid_AmendmentLog_bbar_btn_DeleteRows']") WebElement btnRoadAmendmentLogDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLAdditionalHaulageCosts_bbar_btn_AddRow']") WebElement btnAdditionalHaulageCostAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_FCLAdditionalHaulageCosts_bbar_btn_DeleteRows']") WebElement btnAdditionalHaulageCostDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_RentDemurrage_bbar_btn_AddRow']") WebElement btnDemurrangeAndStorageCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_RentDemurrage_bbar_btn_DeleteRows']") WebElement btnDemurrangeAndStorageCostsDeleteRow;
	@FindBy(xpath=".//*[text()='OK']") WebElement btnOk;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLAdditionalLCLFreightCharges_bbar_btn_AddRow']") WebElement btnAdditionalLCLFreightCostsAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLAdditionalLCLFreightCharges_bbar_btn_DeleteRows']") WebElement btnAddtionalLCLFreightCostsDeleteRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_AmendmentLog_bbar_btn_AddRow']") WebElement btnSeaAmendmentLogAddRow;
	@FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_AmendmentLog_bbar_btn_DeleteRows']") WebElement btnSeaAmendmentDeleteRow;
	@FindBy(xpath = ".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLFreightCosts_bbar_btn_AddRow']//button[.='Add row']")WebElement btnAddRowLCLFreightCosts;
    @FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLLandsideCosts_bbar_btn_AddRow']//button[.='Add row']")WebElement btnAddRowLCLLanadsideCosts;
    @FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLHaulageCosts_bbar_btn_AddRow']//button[.='Add row']")WebElement btnAddRowLCLHaulageCosts;

    @FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLFreightCosts_bbar_btn_DeleteRows']/tbody/tr[2]/td[2]/em/button[.='Delete rows']")WebElement btnDeleteRowLCLFreightCosts;
    @FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLLandsideCosts_bbar_btn_DeleteRows']/tbody/tr[2]/td[2]/em/button[.='Delete rows']")WebElement btnDeleteRowLCLLandsideCosts;
    @FindBy(xpath=".//*[@id='extId_LIMA_Tariff_Seafreight_Grid_LCLHaulageCosts_bbar_btn_DeleteRows']/tbody/tr[2]/td[2]/em/button[.='Delete rows']")WebElement btnDeleteRowLCLHaulageCosts;
	
	public WebElement getLnkTools() {
		return lnkTools;
	}
	public WebElement getLnkTariff() {
		return lnkTariff;
	}
	
	public WebElement getTxtNoRows() {
		return txtNoRows;
	}
	
	public WebElement getBtnNext() {
		return btnNext;
	}
	
	public WebElement getBtnPrevious() {
		return btnPrevious;
	}
	
	public WebElement getBtnFCLFreightCostsAddRow() {
		return btnFCLFreightCostsAddRow;
	}
	
	public WebElement getBtnFCLFreightCostsDeleteRow() {
		return btnFCLFreightCostsDeleteRow;
	}
	public WebElement getTxtFieldPages() {
		return txtFieldPages;
	}
	
	public WebElement getBtnGOHAddRow() {
		return btnGOHAddRow;
	}
	public WebElement getBtnGOHDeleteRow() {
		return btnGOHDeleteRow;
	}
	
	public WebElement getBtnAdditionalFreightCostAddRow() {
		return btnAdditionalFreightCostAddRow;
	}
	public WebElement getBtnAdditionalFreightCostDeleteRow() {
		return btnAdditionalFreightCostDeleteRow;
	}
	
	public WebElement getBtnFCLLandsideCostsAddRow() {
		return btnFCLLandsideCostsAddRow;
	}
	public WebElement getBtnFCLLandsideCostsDeleteRow() {
		return btnFCLLandsideCostsDeleteRow;
	}
	public WebElement getBtnFCLOriginLandsideCostsAddRow() {
		return btnFCLOriginLandsideCostsAddRow;
	}
	public WebElement getBtnFCLOriginLandsideCostsDeleteRow() {
		return btnFCLOriginLandsideCostsDeleteRow;
	}
	public WebElement getBtnFCLHaulageCostsAddRow() {
		return btnFCLHaulageCostsAddRow;
	}
	public WebElement getBtnFCLHaulageCostsDeleteRow() {
		return btnFCLHaulageCostsDeleteRow;
	}
	
	public WebElement getBtnFCLAdditionalHaulageCostAddRow() {
		return btnFCLAdditionalHaulageCostAddRow;
	}
	public WebElement getBtnFCLAdditionalHaulageCostDeleteRow() {
		return btnFCLAdditionalHaulageCostDeleteRow;
	}
	public WebElement getBtnFCLDemurrageAndStorageCostAddRow() {
		return btnFCLDemurrageAndStorageCostAddRow;
	}
	public WebElement getBtnFCLDemurrageAndStorageDeleteRow() {
		return btnFCLDemurrageAndStorageDeleteRow;
	}
	
	public WebElement getBtnLCLFreightCostsAddRow() {
		return btnLCLFreightCostsAddRow;
	}
	public WebElement getBtnLCLFreightCostsDeleteRow() {
		return btnLCLFreightCostsDeleteRow;
	}
	public WebElement getBtnLCLAdditionalFreightChargesAddRow() {
		return btnLCLAdditionalFreightChargesAddRow;
	}
	public WebElement getBtnLCLAdditionalFreightChargesDeleteRow() {
		return btnLCLAdditionalFreightChargesDeleteRow;
	}
	public WebElement getBtnLCLLandsideCostsAddRow() {
		return btnLCLLandsideCostsAddRow;
	}
	public WebElement getBtnLCLLandsideCostsDeleteRow() {
		return btnLCLLandsideCostsDeleteRow;
	}
	public WebElement getBtnLCLHaulageCostAddRow() {
		return btnLCLHaulageCostAddRow;
	}
	public WebElement getBtnLCLHaulageCostDeleteRow() {
		return btnLCLHaulageCostDeleteRow;
	}
	public WebElement getBtnLCLStorageChangesAddRow() {
		return btnLCLStorageChangesAddRow;
	}
	public WebElement getBtnLCLStorageChangesDeleteRow() {
		return btnLCLStorageChangesDeleteRow;
	}
	public WebElement getBtnApply() {
		return btnApply;
	}
	public WebElement getValidationErrPopUp() {
		return ValidationErrPopUp;
	}
	public WebElement getValidationErrMsg() {
		return ValidationErrMsg;
	}
	public WebElement getBtnOK() {
		return btnOK;
	}
	public WebElement getBtnYes() {
		return btnYes;
	}
	public WebElement getDeleteRowPopUp() {
		return deleteRowPopUp;
	}
	public WebElement getBtnAirRatesAddRow() {
		return btnAirRatesAddRow;
	}
	public WebElement getBtnAirRatesDeleteRow() {
		return btnAirRatesDeleteRow;
	}
	public WebElement getBtnExpressServiceAddRow() {
		return btnExpressServiceAddRow;
	}
	public WebElement getBtnExpressServiceDeleteRow() {
		return btnExpressServiceDeleteRow;
	}
	public WebElement getBtnAirLandsideCostsAddRow() {
		return btnAirLandsideCostsAddRow;
	}
	public WebElement getBtnAirLandsideCostsDeleteRow() {
		return btnAirLandsideCostsDeleteRow;
	}
	public WebElement getBtnAirHaulageCostsAddRow() {
		return btnAirHaulageCostsAddRow;
	}
	public WebElement getBtnAirHaulageCostsDeleteRow() {
		return btnAirHaulageCostsDeleteRow;
	}
	public WebElement getBtnAirStorageCostsAddRow() {
		return btnAirStorageCostsAddRow;
	}
	public WebElement getBtnAirStorageCostsDeleteRow() {
		return btnAirStorageCostsDeleteRow;
	}
	public WebElement getBtnAirAmendmentLogAddRow() {
		return btnAirAmendmentLogAddRow;
	}
	public WebElement getBtnAirAmendmentLogDeleteRow() {
		return btnAirAmendmentLogDeleteRow;
	}
	public WebElement getBtnComboBoxType() {
		return btnComboBoxType;
	}
	public WebElement getValidationErrPopUpAirFreight() {
		return ValidationErrPopUpAirFreight;
	}
	
	public WebElement getValidationErrMsgAirFreight() {
		return ValidationErrMsgAirFreight;
	}
	
	public WebElement getBtnFullTrailerCostsAddRow() {
		return btnFullTrailerCostsAddRow;
	}
	public WebElement getBtnFullTrailerCostsDeleteRow() {
		return btnFullTrailerCostsDeleteRow;
	}
	public WebElement getBtnFullTrailerGOHCostsAddRow() {
		return btnFullTrailerGOHCostsAddRow;
	}
	public WebElement getBtnFullTrailerGOHCostDeleteRow() {
		return btnFullTrailerGOHCostDeleteRow;
	}
	public WebElement getBtnFullTrailerLandsideCostsAddRow() {
		return btnFullTrailerLandsideCostsAddRow;
	}
	public WebElement getBtnFullTrailerLandsideCostsDeleteRow() {
		return btnFullTrailerLandsideCostsDeleteRow;
	}
	public WebElement getBtnFullTrailerDeliveryCostAddRow() {
		return btnFullTrailerDeliveryCostAddRow;
	}
	public WebElement getBtnFullTrailerDeliveryCostDeleteRow() {
		return btnFullTrailerDeliveryCostDeleteRow;
	}
	public WebElement getBtnGroupageTrailerCostsAddRow() {
		return btnGroupageTrailerCostsAddRow;
	}
	public WebElement getBtnGroupageTrailerCostDeleteRow() {
		return btnGroupageTrailerCostDeleteRow;
	}
	public WebElement getBtnGroupageGOHCostsAddRow() {
		return btnGroupageGOHCostsAddRow;
	}
	public WebElement getBtnGroupageGOHCostsDeleteRow() {
		return btnGroupageGOHCostsDeleteRow;
	}
	public WebElement getBtnGroupageLandsideAddRow() {
		return btnGroupageLandsideAddRow;
	}
	public WebElement getBtnGroupageLandsideDeleteRow() {
		return btnGroupageLandsideDeleteRow;
	}
	public WebElement getBtnGroupageHaulageCostAddRow() {
		return btnGroupageHaulageCostAddRow;
	}
	public WebElement getBtnGroupageHaulageCostDeleteRow() {
		return btnGroupageHaulageCostDeleteRow;
	}
	public WebElement getBtnRoadAmendmentLogAddRow() {
		return btnRoadAmendmentLogAddRow;
	}
	public WebElement getBtnRoadAmendmentLogDeleteRow() {
		return btnRoadAmendmentLogDeleteRow;
	}
	public WebElement getBtnAdditionalHaulageCostAddRow() {
		return btnAdditionalHaulageCostAddRow;
	}
	public WebElement getBtnAdditionalHaulageCostDeleteRow() {
		return btnAdditionalHaulageCostDeleteRow;
	}
	public WebElement getBtnDemurrangeAndStorageCostsAddRow() {
		return btnDemurrangeAndStorageCostsAddRow;
	}
	public WebElement getBtnDemurrangeAndStorageCostsDeleteRow() {
		return btnDemurrangeAndStorageCostsDeleteRow;
	}
	public WebElement getBtnOk() {
        return btnOk;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public AdjunoUILibrary getObjAdjunoUILibrary() {
		return objAdjunoUILibrary;
	}
	public WebElement getBtnAdditionalLCLFreightCostsAddRow() {
		return btnAdditionalLCLFreightCostsAddRow;
	}
	public WebElement getBtnAddtionalLCLFreightCostsDeleteRow() {
		return btnAddtionalLCLFreightCostsDeleteRow;
	}
	public WebElement getBtnSeaAmendmentLogAddRow() {
		return btnSeaAmendmentLogAddRow;
	}
	public WebElement getBtnSeaAmendmentDeleteRow() {
		return btnSeaAmendmentDeleteRow;
	}
	
	public WebElement getBtnAddRowLCLFreightCosts() {
        return btnAddRowLCLFreightCosts;
    }
	public WebElement getBtnAddRowLCLHaulageCosts() {
        return btnAddRowLCLHaulageCosts;
    }
    public WebElement getBtnDeleteRowLCLHaulageCosts() {
        return btnDeleteRowLCLHaulageCosts;
    }
    public WebElement getBtnDeleteRowLCLLandsideCosts() {
        return btnDeleteRowLCLLandsideCosts;
    }
    public WebElement getBtnAddRowLCLLanadsideCosts() {
        return btnAddRowLCLLanadsideCosts;
    }
    public WebElement getBtnDeleteRowLCLFreightCosts() {
        return btnDeleteRowLCLFreightCosts;
    }
	public AdjunoLIMATariffPOM(WebDriver driver)
	{
		this.driver = driver;
		//This initElements method will create all WebElements
		PageFactory.initElements(driver, this);
	}
	
	private boolean isNullOrBlank(String s)
    {
      return (s==null || s.trim().equals(""));
    }
    
	public String callMouseHover(String strFormName,WebElement mainLink, WebElement subLink)
	{
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
	
	public long getDropDownValueCount(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		long count;
		count = objAdjunoUILibrary.getDropDownValueCount(strFormName, strControlName);
		return count;
	}
	
	public String getDropDownValue(String strFormName, String strControlName, int nRow)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strValue = "";
		strValue = objAdjunoUILibrary.getDropDownValue(strFormName, strControlName, nRow);
		if(!isNullOrBlank(strValue))
		{
			
		}
		else
		{
			strValue =  "Drop Box value is empty";
		}
		return strValue;
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
	
	public boolean checkDoesElementExist(String formName,String controlName) 
	{	
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
	
	public ArrayList<String> getCaptionsList(String strFormName, String strControlName)
	{
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
                            strMessage = strMessage+ tempVal+ " from Xml is not found in Grid ";
                        }                                
                    }
               }
            
            }
        }    
        return strMessage;
    }
	
	public long getNoOfRowsGrid(String strFormName, String strGridControlName)
	{
        objAdjunoUILibrary = new AdjunoUILibrary(driver);
        long Rows = objAdjunoUILibrary.getNoOfRowsGrid(strFormName, strGridControlName);
        
        return Rows;
        
	}
	
	public boolean isGridElementPresent(String strFormName, String strGridControlName,int nRow, String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag; 
		WebElement we;
		
		we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		if(we.isDisplayed())
		{
			bFlag = true;
		}
		else
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
	
	public void wait(int ms)
    {
    	try 
    	{
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }
	
	public String gridHyperlinkPresent(String strFormName,String strGridControlName,int nRow,String strColumnName)
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
	
	public String isHyperlinkPresent(String strFormName,String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		String strMessage = "";
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
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
	
	public boolean checkElementIsDisabled(WebElement we)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		we.isDisplayed();
		if(we.getAttribute("class").contains("disabled"))
		{
			bFlag = true;
		}
		else
		{
			bFlag = false;
		}
		
		return bFlag;
	}
	
	public WebElement getElement(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		try{
			we.isDisplayed();
		}
		catch(NoSuchElementException e)
		{
			
		}
		return we;
	}
	
	public boolean verifyElementIsInReadOnlyMode(String strFormName, String strControlName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		WebElement we = objAdjunoUILibrary.getElement(strFormName, strControlName);
		if(we.getAttribute("readonly").equals("true"))
		{
			bFlag = true;
		}
		else
		{
			bFlag = false;
		}
		return bFlag;
	}
	
	public boolean clickTab(String strFormName, String strTabPanelName, String strTabItem)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		boolean bFlag;
		try
		{
		objAdjunoUILibrary.clickTabToMakeItActive(strFormName, strTabPanelName, strTabItem);
		bFlag = true;
		}
		catch(NoSuchElementException e)
		{
			bFlag = false;
		}
		catch(NullPointerException e)
	    {
	           bFlag = false;
	    }
		return bFlag;
	}
	
	public WebElement getGridCellElement(String strFormName, String strGridControlName,int nRow, String strColumnName)
	{
		objAdjunoUILibrary = new AdjunoUILibrary(driver);
		WebElement we;
		
		we = objAdjunoUILibrary.getGridCellElement(strFormName, strGridControlName, nRow, strColumnName);
		
		return we;
	}
	
	public boolean isFileDownloaded_Ext(String dirPath, String ext){
		boolean flag=false;
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        flag = false;
	    }
	    
	    for (int i = 1; i < files.length; i++) {
	    	if(files[i].getName().contains(ext)) {
	    		flag=true;
	    	}
	    }
	    return flag;
	}
	
	public File getLatestFilefromDir(String dirPath)
	{
	    File dir = new File(dirPath);
	    File[] files = dir.listFiles();
	    if (files == null || files.length == 0) {
	        return null;
	    }
	
	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile;
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
			 strGridCellValue="The text of the cell is not available";
		 }
		 return strGridCellValue;
		
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
	
	public String setPresentDate(String strPattern)
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
		calendar.add(Calendar.DATE, 30);
		dt = calendar.getTime();
		String FutureDate = new SimpleDateFormat(strPattern).format(dt);
		FutureDate.equals(dt);
	
		return FutureDate;
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
	
	public String getFieldValue(String strformName, String strControlName) 
	{
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
	
	
	
	
	
	
	
	
	
	

}
