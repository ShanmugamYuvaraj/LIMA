package com.lima.test;

import java.util.concurrent.TimeUnit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.lima.library.AdjunoLIMALibrary;
import com.lima.pagefactory.AdjunoLIMALoginPOM;

public class AdjunoLIMALoginTest {
	

    WebDriver driver;

    long nPage_Timeout = 0;
    String strTestURL;
    String strDriver;
    
    String strLIMAUserName;
    String strLIMAPassword;

    AdjunoLIMALibrary objAdjunoLIMALibrary;
    AdjunoLIMALoginPOM objAdjunoLIMALoginPOM;

    @BeforeTest
    public void setup()
    {
    	objAdjunoLIMALibrary = new AdjunoLIMALibrary();
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse(objAdjunoLIMALibrary.getConfigXMLDataFileName());

            XPath xPath = XPathFactory.newInstance().newXPath();

            Node pageTimeout = (Node) xPath.evaluate("/config/Generic/Page_Timeout", dDoc, XPathConstants.NODE);
            nPage_Timeout = Long.parseLong(pageTimeout.getTextContent());

            Node testURL = (Node) xPath.evaluate("/config/LIMA/LIMA_TestURL", dDoc, XPathConstants.NODE);
            strTestURL = testURL.getTextContent();
            
            
            Node limaUserName = (Node) xPath.evaluate("/config/LIMA/LIMA_UserName", dDoc, XPathConstants.NODE);
            strLIMAUserName = limaUserName.getTextContent();

            Node limaPassword = (Node) xPath.evaluate("/config/LIMA/LIMA_Password", dDoc, XPathConstants.NODE);
            strLIMAPassword = limaPassword.getTextContent(); 

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void wait(int ms)
    {
    	try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

    }

    @Test(priority=1)
    @Parameters("browser")
    public void launch(String browser)
    {
    	if(browser.equalsIgnoreCase("firefox")){
    		 driver = new FirefoxDriver();
    	     driver.manage().window().maximize();
    	}else if(browser.equalsIgnoreCase("chrome"))
    	{

			System.setProperty("webdriver.chrome.driver", "F:\\chromedriver.exe");
			// create chrome instance
			driver = new ChromeDriver();
    	}
       

        driver.manage().timeouts().implicitlyWait(nPage_Timeout, TimeUnit.SECONDS);

        driver.get(strTestURL);
    }

    @Test(priority=2)
    public void logIn()
    {
    	objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
    	
    	objAdjunoLIMALoginPOM.setUserName(strLIMAUserName);
    	objAdjunoLIMALoginPOM.setPassword(strLIMAPassword);
    	objAdjunoLIMALoginPOM.clickLogon();
    }

   /* @Test(priority=3)
    public void acx()
    {
    	wait(5000);
    	objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
    	
    	Boolean val = objAdjunoLIMALoginPOM.elementpresent("Dashboard_QuickSearch_Parameters_7","_01Group");
    	
    	if(val==true){
    		System.out.println("found");
    	}else{
    		System.out.println("not found");
    	}
    }*/
    
    
   @Test(priority=5)
    public void signOut()
    {
    	objAdjunoLIMALoginPOM = new AdjunoLIMALoginPOM(driver);
    	
    	objAdjunoLIMALoginPOM.clickLinkSignOut();
    }

   /* @AfterTest
    public void terminateBrowser()
    {
  	  wait(5000);
      driver.close();
    }*/
}
