package io.branch.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import io.branch.utilities.DriverActions;
import io.branch.utilities.Global;

public class RequestForDemoPage {

	WebDriver driver = null;
	DriverActions act = new DriverActions();
	Global global = new Global();

	@FindBy(id = "FirstName")
	public WebElement textBoxFirstName;

	@FindBy(id = "LastName")
	public WebElement textBoxLastName;

	@FindBy(id = "Email")
	public WebElement textBoxWorkEmail;

	@FindBy(id = "Company")
	public WebElement textBoxCompany;

	@FindBy(id = "Phone")
	public WebElement textBoxPhoneNumber;

	@FindBy(id = "Title")
	public WebElement textBoxJobTitle;

	@FindBy(id = "Country")
	public WebElement dropDownListCountry;

	@FindBy(id = "MAU_Estimate__c")
	public WebElement dropDownMonthyUserEstimate;

	@FindBy(xpath = ".//div[@class='bds-show-at-lg']/a[@data-element-tag='demo-header']")
	public WebElement buttonRequestForDemo;

	@FindBy(xpath = ".//*[@id='mktoForm_1488']//label[text()='Journeys Web to App Optimization ']")
	public WebElement checkBoxJourneysWeb;

	@FindBy(xpath = ".//button[@type='submit']")
	public WebElement buttonSubmit;

	@FindBy(xpath = ".//*[@id='Bannerheading']/h1[text()='Thanks for submitting a demo request.']")
	public WebElement headerDemoRequestSuccess;

	/******************************************************************************
	 * Constructor Name: HomePage
	 * 
	 * @Description: Initializing Web driver
	 * @author Nagarjuna
	 * @throws Exception
	 *******************************************************************************/
	public RequestForDemoPage(WebDriver driver) throws IOException {
		this.driver = driver;
		Properties prop = new Properties();
		InputStream input = Global.loadProperties();
		prop.load(input);

	}

	/******************************************************************************
	 * @Description: This method sets the text field of First Name
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/

	public void setTextBoxFirstName(WebDriver driver) {
		act.enterText(driver, textBoxFirstName, global.randomString());
	}

	/******************************************************************************
	 * @Description: This method sets the text field of last name
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/
	public void setTextBoxLastName(WebDriver driver) {
		act.enterText(driver, textBoxLastName, global.randomString());
	}

	/******************************************************************************
	 * @Description: This method sets the text field of work email
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/
	public void setTextBoxWorkEmail(WebDriver driver, String domain) {

		act.enterText(driver, textBoxWorkEmail, global.randomEmailGenerator(domain));
	}

	/******************************************************************************
	 * @Description: This method sets the text field of company
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/

	public void setTextBoxCompany(WebDriver driver) {
		act.enterText(driver, textBoxCompany, global.randomString());
	}

	/******************************************************************************
	 * @Description: This method sets the text field of phone number
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/

	public void setTextPhoneNumber(WebDriver driver) {
		act.enterText(driver, textBoxPhoneNumber, global.randomPhoneNumber());
	}

	/******************************************************************************
	 * @Description: This method sets the text field of Job title
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/

	public void setTextBoxJobTitle(WebDriver driver) {
		act.enterText(driver, textBoxJobTitle, "QA");
	}

	/******************************************************************************
	 * @Description: This method selects the random value from country drop down
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/

	public void setCountryDropDown(WebDriver driver) {
		act.waitForElementVisible(driver, dropDownListCountry);
		Select selectCountries = new Select(dropDownListCountry);
		List<WebElement> countriesCount = selectCountries.getOptions();
		selectCountries.selectByIndex(global.randomIntegerValue(1, countriesCount.size()));
	}

	/******************************************************************************
	 * @Description: This method selects random value from Monthly user drop down
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/

	public void setMonthlyUsersDropDown(WebDriver driver) {
		act.waitForElementVisible(driver, dropDownMonthyUserEstimate);
		Select selectMonthlyUsers = new Select(dropDownMonthyUserEstimate);
		List<WebElement> monthlyUsersCount = selectMonthlyUsers.getOptions();
		selectMonthlyUsers.selectByIndex(global.randomIntegerValue(1, monthlyUsersCount.size()));
	}

	/**********************************************************************************
	 * @Description: This method enters all the required fields to submit a demo
	 *               request
	 * @param driver
	 *            - Type of WebDriver
	 * @author Nagarjuna
	 **********************************************************************************/

	public void enterNecessaryDetailsForDemoRequest(WebDriver driver, String emailDomain) {
		setTextBoxFirstName(driver);
		setTextBoxLastName(driver);
		setTextBoxWorkEmail(driver, emailDomain);
		setTextBoxCompany(driver);
		setTextPhoneNumber(driver);
		setTextBoxJobTitle(driver);
		setCountryDropDown(driver);
		setMonthlyUsersDropDown(driver);
		act.clickOnElement(driver, checkBoxJourneysWeb); // For time being, I have selected one checkbox, but this can
															// be customized.
	}

}
