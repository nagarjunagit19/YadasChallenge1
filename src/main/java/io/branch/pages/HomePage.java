package io.branch.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import io.branch.utilities.*;

public class HomePage {

	WebDriver driver = null;
	DriverActions act = new DriverActions();

	@FindBy(id = "CybotCookiebotDialogBodyButtonAccept")
	public WebElement buttonCookieAlert;

	@FindBy(xpath = "//footer//ul[@class='bds-accordian-list']/li[2]/a[@data-element-tag='team']")
	public WebElement linkTeam;

	@FindBy(xpath = ".//div[@class='bds-show-at-lg']/a[@data-element-tag='demo-header']")
	public WebElement buttonRequestForDemo;

	/******************************************************************************
	 * Constructor Name: HomePage
	 * 
	 * @Description: Initializing Web driver
	 * @author Nagarjuna
	 * @throws Exception
	 *******************************************************************************/
	public HomePage(WebDriver driver) throws IOException {
		this.driver = driver;
		Properties prop = new Properties();
		InputStream input = Global.loadProperties();
		prop.load(input);

	}

	/******************************************************************************
	 * @Description: This method clicks on request for demo button in header section
	 * @param driver - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/
	public void clickOnRequestForDemoButton(WebDriver driver) {
		// act.waitForElementVisible(driver, buttonRequestForDemo);
		act.clickOnElement(driver, buttonRequestForDemo);
	}

	/******************************************************************************
	 * @Description: This method clicks on Team Link present in footer section
	 * @param driver - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/
	public void clickFooterTeamLink(WebDriver driver) {
		act.waitForElementVisible(driver, linkTeam);
		act.clickOnElement(driver, linkTeam);
	}
	
	/******************************************************************************
	 * @Description: This method clicks on cookie accept button of branch website
	 * @param driver - Type of WebDriver
	 * @author Nagarjuna
	 *******************************************************************************/
	public void acceptCookieAlert(WebDriver driver) {
		act.clickOnElement(driver, buttonCookieAlert);
	}

}
