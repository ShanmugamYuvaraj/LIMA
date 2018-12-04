package com.lima.library;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class AdjunoUILibrary
{
	WebDriver driver;
    Node node;

	public AdjunoUILibrary(WebDriver driver)
	{
		this.driver = driver;
	}
	
    private void wait(int ms)
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

    public boolean isPageLoaded(String strFormName)
	{
		boolean bFlag = false;
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	String PageLoadState = "Loading";

    	String strJS = "return window." + strFormName + "_PageLoadState.value";

    	int i = 0;
    	
        while ((!(PageLoadState.equalsIgnoreCase("Ready"))) && (i < 20))
    	{
        	try
    		{
        		PageLoadState = (String) js.executeScript(strJS);
    		}
    		catch (Exception e)
    		{
    		}
        	wait(1000);
        	i++;
    	}

        if (PageLoadState.equalsIgnoreCase("Ready"))
        	bFlag = true;
        else
        	bFlag = false;
		
    	return bFlag;
	}

	public WebElement getElement(String strFormName, String strControlName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getElement();";

    	return (WebElement) js.executeScript(strJS);
	}

	public String getLabel(String strFormName, String strLabelName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window." + strFormName + "_" + strLabelName + ".innerHTML";

    	return (String) js.executeScript(strJS);
	}

	public ArrayList getComboboxValues(String strFormName, String strControlName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

		String strJS =
                "function() { \n" +
                "var arr = []; \n" +
                "for (var i = 0; i <= window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getItemCount() - 1; i++) \n" +
                "{ \n" +
                "arr.push(window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getItem(i,true).desc); \n" +
                "} \n" +
                "return arr;" + 
                "} ();\n";

		ArrayList<String> lst = new ArrayList<String>();
		
		return (ArrayList) js.executeScript("return " + strJS+ "");
	}

	public WebElement getComboboxButton(String strFormName, String strControlName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getElement('trigger');";

    	return (WebElement) js.executeScript(strJS);
	}

	public WebElement getGridCellElement(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: " + nRow + ", column: '" + strColumnName + "'})";

    	return (WebElement) js.executeScript(strJS);
	}

public ArrayList<String> getListofValidationMessageElement(String strFormName, String strControlName)
	{
		ArrayList<String> lst = new ArrayList<String>();
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getValidationMessages();";

    	lst = (ArrayList<String>) js.executeScript(strJS);
 	
    	return lst;
	}
	public WebElement getGridCellElementEditor(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('editor', {row: " + nRow + ", column: '" + strColumnName + "'})";

    	return (WebElement) js.executeScript(strJS);
	}

	public void setGridCellValue(String strFormName, String strGridControlName, int nRow, String strColumnName, String strValue)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	
    	String strJS = "window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: " + nRow + ", column: '" + strColumnName + "'}).click();";

    	js.executeScript(strJS);
    	
    	wait(2000);
    	
    	strJS = "window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('editor', {row: " + nRow + ", column: '" + strColumnName + "'}).value = '" + strValue + "';";

    	js.executeScript(strJS);
	}
	
	public long getNoOfRowsGrid(String strFormName, String strGridControlName)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getRowCount(true);";
		
		return (long) js.executeScript(strJS);
		
	}

	public long getNoOfColumnsGrid(String strFormName, String strGridControlName)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
		
		String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getColumnCount(true);";
		
		return (long) js.executeScript(strJS);
		
	}

	public String getGridColumnCaption(String strFormName, String strGridControlName, String strColumnName, int nLevel)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getColumnCaption('" + strColumnName + "'," + nLevel + ");";

    	return (String) js.executeScript(strJS);
	}

	public String validaleComboRunRESTAPI(ArrayList <String>lst, String strCombo, String strURL) throws ClientProtocolException, IOException
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request= new HttpGet(strURL);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));

		String strXml = "";
		String strReturnXml = "";
        NodeList nlNodeList = null;
        String strReturnMessage = "Passed";
        String strValue = "";

		while ((strXml = rd.readLine()) != null)
		{
			strReturnXml = strReturnXml + strXml;
		}
		
    	strReturnXml = strReturnXml.replace("'", "apos");
    	DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document dDoc = builder.parse( new InputSource( new StringReader( strReturnXml ) ) );

            XPath xPath = XPathFactory.newInstance().newXPath();

	    	for (int i=0; i <= (lst.size() - 1); i++)
	    	{
	    		if(!(isNullOrBlank(lst.get(i))))
	    		{
	    			strValue = lst.get(i).replace("'","apos");
	    			nlNodeList = (NodeList) xPath.evaluate("//Description[text() = '" + strValue + "']", dDoc, XPathConstants.NODESET);
	    	        if (nlNodeList.getLength() >= 1){}
	    	        else
	    	        {
	    	            strReturnMessage = strReturnMessage + "The value '" + lst.get(i) + "' should not be present in the " + strCombo + " combo;";
	    	        }
	
	    		}
	    	}
        
	        boolean bFlagFound = false;
			nlNodeList = (NodeList) xPath.evaluate("/data/" + strCombo, dDoc, XPathConstants.NODESET);
	        Element el;
			for (int j=0; j <= nlNodeList.getLength() - 1; j++)
			{
				bFlagFound = false;
	        	if (nlNodeList.item(j).getNodeType() == Node.ELEMENT_NODE)
				{
	        		el = (Element) nlNodeList.item(j);
	    	    	for (int k=0; k <= (lst.size() - 1); k++)
	        		{
	    	    		if(!(isNullOrBlank(lst.get(k))))
	    	    		{
	    	    			strValue = lst.get(k).replace("'","apos");
							if (el.getElementsByTagName("Description").item(0).getTextContent().equalsIgnoreCase(strValue))
					    		bFlagFound = true;
	    	    		}
	        		}
					if (!(bFlagFound))
					{
	    	            strReturnMessage = strReturnMessage + "The value '" + el.getElementsByTagName("Description").item(0).getTextContent() + "' is missing and should be present in the " + strCombo + " combo;";
					}
				}
			}
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
        return strReturnMessage;
	}

	public String runRESTAPI(String strURL) throws ClientProtocolException, IOException
	{
		HttpClient client = new DefaultHttpClient();
		HttpGet request= new HttpGet(strURL);
		HttpResponse response = client.execute(request);
		BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));

		String strXml = "";
		String strReturnXml = "";

		while ((strXml = rd.readLine()) != null)
		{
			strReturnXml = strReturnXml + strXml;
		}
		
		return strReturnXml;
	}
	
	public String getFormName()
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return Object.keys(window.ArenaForms)[0];";

    	return (String) js.executeScript(strJS);
	}

	public String getValidationMessageGridElement(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
		ArrayList<String> lst = new ArrayList<String>();
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getValidationMessages(" + nRow + ", '" + strColumnName + "');";

    	lst = (ArrayList) js.executeScript(strJS);

    	String strReturn = "";

    	if (lst.size() >= 1)
    		strReturn = lst.get(0);

    	return strReturn;
	}
	
	/*public ArrayList<String> getListOfValidationMessageGridElement(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
		ArrayList<String> lst = new ArrayList<String>();
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getValidationMessages(" + nRow + ", '" + strColumnName + "');";

    	lst = (ArrayList<String>) js.executeScript(strJS);
    	
    	return lst;
	}*/

	public String getValidationMessageElement(String strFormName, String strControlName)
	{
		ArrayList<String> lst = new ArrayList<String>();
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getValidationMessages();";

    	lst = (ArrayList) js.executeScript(strJS);

    	String strReturn = "";
    	
    	if (lst.size() >= 1)
    		strReturn = lst.get(0);
    	
    	return strReturn;
	}

	public ArrayList getFormControls(String strFormName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

		String strJS = "return Object.keys(window.ArenaForms." + strFormName + ".Controls);";

		ArrayList<String> lst = new ArrayList<String>();
		
		return (ArrayList) js.executeScript(strJS);
	}

	public boolean isCheckboxOnGridEnabled(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: " + nRow + ", column: '" + strColumnName + "'}).parentNode.parentNode.className.indexOf('ArenaReadOnlyGrid') == -1";

    	return (boolean) js.executeScript(strJS);
	}

	public boolean isCheckboxOnGridChecked(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getCellValue(" + nRow + ", '" + strColumnName + "');";

    	return (boolean) js.executeScript(strJS);
	}
	
	public boolean isButtonOnGridEnabled(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
		String strJSReturn = "";
		boolean bFlag = true;
		
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: " + nRow + ", column: '" + strColumnName + "'}).getAttribute('disabled');";
    	
    	strJSReturn = (String) js.executeScript(strJS);
    	
    	if (isNullOrBlank(strJSReturn))
    	{
    		bFlag = true;
    	}
    	else
    	{
    		if (strJSReturn.equalsIgnoreCase("disabled"))
    		bFlag = false;
    	}
    	return bFlag;
	}

	public String getColumnName(String strFormName, String strGridControlName, int nColumn)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	
    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getColumnName(" + nColumn + ");";

    	return (String) js.executeScript(strJS);
	}

	public String getGridCellElementValue(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: " + nRow + ", column: '" + strColumnName + "'}).textContent";

    	return (String) js.executeScript(strJS);
	}

	public void clickTabToMakeItActive(String strFormName, String strTabPanelName, String strTabItem)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "window.ArenaForms['" + strFormName + "'].Controls." + strTabPanelName + ".setActiveTab('" + strTabItem + "');";
    	
    	js.executeScript(strJS);
	}

	public String getMilestoneColor(String strFormName, String strGridControlName, String strMilstoneCaption, int nRow) 
	{
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	   
	    String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: "+nRow +", column: '"+strMilstoneCaption+"'}).style.color;";
	   
	    return (String) js.executeScript(strJS);
	}

	public String getMilestoneClassName(String strFormName, String strGridControlName, String strMilstoneCaption, int nRow)
	{
	    JavascriptExecutor js = (JavascriptExecutor)driver;
	   
	    String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getElement('cell', {row: "+ nRow +", column: '"+ strMilstoneCaption +"'}).className;";

	    return (String) js.executeScript(strJS);
	}
	
	
	public boolean isElementPresent(WebElement we) 
	{
		  try
          {
              return  we.isDisplayed();
          }
          catch (NoSuchElementException e)
          {
              return false;
          }
		
	}
	
	public boolean doesElementExist(String strFormName, String strControlName) 
	{
		WebElement we;
		boolean bElementExists = true;
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getElement();";
    	we = (WebElement) js.executeScript(strJS);
    	try
        {
             bElementExists = we.isDisplayed();
        }
        catch (NoSuchElementException e)
        {
             bElementExists = false;
        }
    	catch(NullPointerException e)
        {
             bElementExists = false;
        }

        return bElementExists;
	}
	
		
	public boolean clickNextTab(String strFormName, String strTabPanelName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	
    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strTabPanelName + ".selectNextTab();";
    	
    	return (boolean)js.executeScript(strJS);
	}
	
	public boolean clickPreviousTab(String strFormName, String strTabPanelName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	
    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strTabPanelName + ".selectPreviousTab();";
    	
    	return (boolean)js.executeScript(strJS);
	}
	
	public String getElemetValue(String strFormName, String strControlName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	String strVal;
    	long lVal;
    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getValue();";
    	  	
    	try {
			strVal=(String) js.executeScript(strJS);
		} catch (Exception e) {
			lVal= (long) js.executeScript(strJS);
			strVal = String.valueOf(lVal);		
		}
        	
    	return strVal;
	}
	
	
	public String getElemetValue2(String strFormName, String strControlName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	String strVal;
    	long lVal;
    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getElement().value;";
    	  	
    	try {
			strVal=(String) js.executeScript(strJS);
		} catch (Exception e) {
			lVal= (long) js.executeScript(strJS);
			strVal = String.valueOf(lVal);		
		}
        	
    	return strVal;
	}
	
public ArrayList<String> getListOfValidationMessageGridElement(String strFormName, String strGridControlName, int nRow, String strColumnName)
	{
		ArrayList<String> lst = new ArrayList<String>();
    	JavascriptExecutor js = (JavascriptExecutor)driver;

    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getValidationMessages(" + nRow + ", '" + strColumnName + "');";

    	lst = (ArrayList<String>) js.executeScript(strJS);
    	
    	return lst;
	}

	public String getDropDownElemetValue(String strFormName, String strControlName)
	{
    	JavascriptExecutor js = (JavascriptExecutor)driver;
    	String strVal;
    	long lVal;
    	String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getText();";
    	  	
    	try {
			strVal=(String) js.executeScript(strJS);
		} catch (Exception e) {
			lVal= (long) js.executeScript(strJS);
			strVal = String.valueOf(lVal);		
		}
        	
    	return strVal;
	}

	public long getElemetValueForLong(String strFormName, String strControlName)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
	
		String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getValue();";
	
		return (long) js.executeScript(strJS);
	}
	
	
	public void clickOnCheckBox(String strFormName, String strControlName, int nRow, String strColumnName)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
	
		String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getElement('cell', {row: " + nRow + ", column: '" + strColumnName + "'});";
		WebElement we = (WebElement) js.executeScript(strJS);
		we.click();
	}
	
	public long getDropDownValueCount(String strFormName, String strControlName)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
	
		String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getItemCount();";
		
		return (long) js.executeScript(strJS);
	}
	
	public String getDropDownValue(String strFormName, String strControlName,int nRow)
	{
		JavascriptExecutor js = (JavascriptExecutor)driver;
	
		String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getItem(" + nRow + ");";
		
		return (String) js.executeScript(strJS);
	}

	public long getTabCount(String strFormName, String strGridControlName)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strGridControlName + ".getTabCount();";
        
        return (long) js.executeScript(strJS);
        
    }
	
	public String getElementValueInReadMode(String strID)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        String strJS = "return document.getElementById('" +strID+ "').value ";
        
        return (String) js.executeScript(strJS);
        
    }
	
	public long getCurrentIndex(String strFormName, String strControlName) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".getActiveTabIndex();";
        
        return (long) js.executeScript(strJS);
    }
	
	public boolean getIsCheckBoxClcked(String strID)
    {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        String strJS = "return document.getElementById('" +strID+ "').checked ";
        
        return (boolean) js.executeScript(strJS);
        
    }
	
	
	public void clickViewDetail(String strFormName, String strControlName) {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        
        String strJS = "return window.ArenaForms['" + strFormName + "'].Controls." + strControlName + ".expand();";
        
         js.executeScript(strJS);
        
    }
	
}
