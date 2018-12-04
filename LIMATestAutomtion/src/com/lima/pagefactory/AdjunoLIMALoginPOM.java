package com.lima.pagefactory;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lima.library.AdjunoUILibrary;


public class AdjunoLIMALoginPOM {
	
	WebDriver driver;

	AdjunoUILibrary objAdjunoUILibrary;
	

	@FindBy(xpath="//input[@name='Username']") WebElement txtUsername;
    @FindBy(xpath="//input[@name='Password']") WebElement txtPassword;
    @FindBy(xpath="//input[@name='RememberMe']") WebElement txtRememberMe;
    @FindBy(xpath="//input[@name='Logon']") WebElement txtLogon;
	@FindBy(linkText="Sign Out") WebElement lnkSignOut;
	@FindBy(linkText="Tools") WebElement lnkTools;
	
	//Class constructor
		public AdjunoLIMALoginPOM(WebDriver driver)
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
	    
	    public void setUserName(String strUserName)
		{
			txtUsername.clear();
			txtUsername.sendKeys(strUserName);
			txtUsername.sendKeys(Keys.TAB);
		}

		public void setPassword(String strPassword)
		{
			txtPassword.clear();
			txtPassword.sendKeys(strPassword);
			txtPassword.sendKeys(Keys.TAB);
		}

		public void clickLogon()
		{
			txtLogon.click();
		}

		public void clickLinkSignOut()
		{
			lnkSignOut.click();
		}

}
