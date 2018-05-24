package io.branch.utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.branch.utilities.Log;

public class DriverActions {


	/**
	 * This method is to perform a click operation on the web element
	 * @param driver
	 * @param element
	 */
	public void clickOnElement(WebDriver driver, WebElement element){
		WebDriverWait myWait = new WebDriverWait(driver, 20);
		myWait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
		Log.info("Performed a click operation on the webelement"+element);
	}

	/**
	 * This method is to perform a check on element display and return true/false
	 * @param driver
	 * @param element
	 * @return flag
	 */
	public boolean isElementDisplayed(WebDriver driver, WebElement element){
		try{
			element.isDisplayed();
			Log.info("Performed an element display check and element is displayed for"+element);
			return true;
		} catch (Exception e){
			Log.info("Performed an element display check and element is NOT displayed for"+element);
			return false;
		}
	}

	/**
	 * This method is to perform a check on element enabled and return true/false
	 * @param driver
	 * @param element
	 * @return flag
	 */
	public boolean isElementEnabled(WebDriver driver, WebElement element){
		try{
			element.isEnabled();
			Log.info("Performed an element displayed and enabled check and element is enabled");
			return true;
		} catch (Exception e){
			Log.info("Performed an element displayed and enabled check and element is NOT enabled");
			return false;
		}
	}

	/**
	 * This method is to perform an explicit wait for an element to be visible
	 * @param driver
	 * @param element
	 */
	public void waitForElementVisible(WebDriver driver, WebElement element){
		WebDriverWait myWait = new WebDriverWait(driver, 45);
		myWait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method is to perform - get a text value of an element
	 * @param driver
	 * @param element
	 * @throws Exception 
	 */
	public String getText(WebDriver driver, WebElement element) throws Exception{
		try {
			WebDriverWait myWait = new WebDriverWait(driver, 20);
			myWait.until(ExpectedConditions.visibilityOf(element));
			Log.info("Performing an action get a text value of an element");
			String text = element.getText();
			Log.info("Performed an action getting a text value of an element and text value is returning as " + text);
			return text;
		} catch (Exception e) {
			Log.error("Problem getting field text value");
			Log.error(e.getMessage());
			throw e;
		}
	}

	public void enterText(WebDriver driver, WebElement element, String text) {
		try {
			WebDriverWait myWait = new WebDriverWait(driver, 30);
			myWait.until(ExpectedConditions.visibilityOf(element));
			element.sendKeys(text);
			//element.sendKeys(Keys.ENTER);
		} catch(Exception e)
		{
			Log.error("Problem entering  text value");
			Log.error(e.getMessage());
			throw e;
		}
	}
		
		public void scrollToBottomOfPage(WebDriver driver){ 
			 JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("window.scrollBy(0,document.body.scrollHeight)");

	}
		
		public void scrollDownthePage(WebDriver driver)
		{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,600)", "");
		}
		
		public int countVisibleElementsInWebElementList(WebDriver driver, List <WebElement> element)
		{
			int count=0;
			
			for(WebElement w : element)
			{
				
				if(w.isDisplayed())
				{
					count++;
				}
			}
			return count;
		}
		
		   public void WaitForAjax(WebDriver driver) throws InterruptedException
		    {

		        while (true)
		        {

		            Boolean ajaxIsComplete = (Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0");
		            if (ajaxIsComplete){
		                break;
		            }
		            
		        }
		    }
		   
		   public List<String> textAttributeList(WebDriver driver, List<WebElement> elements)
		   {
			   List<String> textList=new ArrayList<String>();
			   for(WebElement w : elements)
			   {
				   if(w.isDisplayed())
				   {
					   textList.add(w.getText());
				   }
			   }
			   
			   return textList;
			   
		   }
		   
		   

}
