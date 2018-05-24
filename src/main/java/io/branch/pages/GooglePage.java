package io.branch.pages;

import java.io.InputStream;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.branch.utilities.DriverActions;
import io.branch.utilities.Global;

public class GooglePage {

	WebDriver driver = null;
	DriverActions act = new DriverActions();
	Properties prop = new Properties();

	@FindBy(name = "q")
	public WebElement searchTextBox;

	@FindBy(xpath = ".//*[@id='sbtc']//span/input[@value='Google Search']")
	public WebElement googleSearchButton;

	@FindBy(xpath = "//*[@id='rso']//h3/a[text()='Branch.io']")
	public WebElement websiteLink;

	/******************************************************************************
	 * Constructor Name: GooglePage
	 * 
	 * @Description: Initializing Web driver
	 * @author Nagarjuna
	 * @throws Exception
	 *******************************************************************************/
	public GooglePage(WebDriver driver) throws Exception {
		this.driver = driver;
		InputStream input = Global.loadProperties();
		prop.load(input);
		String url = prop.getProperty("loginUrl");
		if (url == null || url.isEmpty()) {
			throw new Exception("URL must not be null nor empty");
		} else {
			this.driver.get(prop.getProperty("loginUrl"));
			driver.manage().window().maximize();
		}

	}

	/******************************************************************************
	 * @Description: This method enters text in google search text box
	 * @param: searchString
	 * @author Nagarjuna
	 * @throws Exception
	 *******************************************************************************/
	public void enterTextToSearchInGoogle(String searchString) {
		act.waitForElementVisible(driver, searchTextBox);
		act.clickOnElement(driver, searchTextBox);
		act.enterText(driver, searchTextBox, searchString);

	}

	/******************************************************************************
	 * @Description: This method clicks on google search button
	 * @param: driver
	 * @author Nagarjuna
	 *******************************************************************************/
	public void clickGoogleSearchButton(WebDriver driver) {
		act.waitForElementVisible(driver, googleSearchButton);
		act.clickOnElement(driver, googleSearchButton);
	}

	/******************************************************************************
	 * @Description: This method navigates to desired Website from google results
	 *               page
	 * @author Nagarjuna
	 *******************************************************************************/

	public void navigateToBranchWebsite() {
		String search = prop.getProperty("searchString");
		enterTextToSearchInGoogle(search);
		clickGoogleSearchButton(driver);
		act.waitForElementVisible(driver, websiteLink);
		act.clickOnElement(driver, websiteLink);
	}

	/******************************************************************************
	 * @Description: This method navigates to desired testpage from test application
	 * @author Nagarjuna
	 *******************************************************************************/
	public void navigateToTeamPage() {
		act.scrollToBottomOfPage(driver);

	}

}
