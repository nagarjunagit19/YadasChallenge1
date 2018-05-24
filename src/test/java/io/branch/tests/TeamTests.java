package io.branch.tests;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.branch.pages.GooglePage;
import io.branch.pages.HomePage;
import io.branch.pages.TeamPage;
import io.branch.pages.RequestForDemoPage;
import io.branch.utilities.DriverActions;
import io.branch.utilities.DriverCheck;
import io.branch.utilities.Log;
import io.branch.utilities.Global;

public class TeamTests {

	InputStream input = null;
	WebDriver driver = null;
	GooglePage googlePage = null;
	HomePage homePage = null;
	TeamPage teamPage = null;
	RequestForDemoPage requestForDemoPage=null;
	
	DriverActions act = new DriverActions();
	Properties prop = new Properties();
	String testCaseName = null;
	

	@BeforeMethod
	public void setup() {
		try {
			input = Global.loadProperties();
			prop.load(input);
			driver = DriverCheck.getDriver();

			googlePage = PageFactory.initElements(driver, GooglePage.class);
			Log.info("Google Page has been initiated");

			homePage = PageFactory.initElements(driver, HomePage.class);
			Log.info("Home Page has been initiated");

			teamPage = PageFactory.initElements(driver, TeamPage.class);
			Log.info("Team Page has been initiated");
			
			requestForDemoPage = PageFactory.initElements(driver, RequestForDemoPage.class);
			Log.info("Request For demo Page has been initiated");
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test(description = "Test Method to verify if department count matches",enabled=false)
	public void validateDeptCount() {
		testCaseName = "validateDeptCount";
		try {
			Log.startTestCase(testCaseName);
			googlePage.navigateToBranchWebsite();
			act.scrollToBottomOfPage(driver);
			homePage.clickFooterTeamLink(driver);
			act.scrollDownthePage(driver);
			Log.info("Current URL =  " +driver.getCurrentUrl());
			Log.info("Current Title of the page -  " + driver.getTitle());
			List<String> m = teamPage.getDepartmentList();
			Log.endTestCase(testCaseName);
			Assert.assertEquals(m.size(), prop.getProperty("expectedTotalNumberOfDepartments"),
					"There is mis match between current and expected number of departments");
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test(description = "Test Method to verify the count of Employees match between All tab and rest of the departments Tab",enabled=false)
	public void validateEmployeeCount() {
		testCaseName = "validateEmployeeCount";
		try {
			Log.startTestCase(testCaseName);
			int countOfEmployeesUnderAllTab, countOfTotalEmployessUnderAllDept = 0;
			googlePage.navigateToBranchWebsite();
			act.scrollToBottomOfPage(driver);
			homePage.clickFooterTeamLink(driver);
			act.scrollDownthePage(driver);
			Log.info("Current URL =  " +driver.getCurrentUrl());
			Log.info("Current Title of the page -  " + driver.getTitle());
			List<WebElement> deptWebElements = teamPage.getAllDepartmentsWebElements();
			Map<String,String> allEmployees = teamPage.getDepartmentEmployees(deptWebElements.get(0));
			countOfEmployeesUnderAllTab = allEmployees.size();
			for (int i = 1; i < deptWebElements.size(); i++) {
				countOfTotalEmployessUnderAllDept = countOfTotalEmployessUnderAllDept
						+ teamPage.getDepartmentEmployees(deptWebElements.get(i)).size();
			}
			Log.endTestCase(testCaseName);
			Assert.assertEquals(countOfTotalEmployessUnderAllDept, countOfEmployeesUnderAllTab,
					"Failing due to employee count mismatch between All Tab and rest of all departments");
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}

	@Test(enabled=false)
	public void validateEmployeeNames() {
		testCaseName = "validateEmployeeNames";
		try {
			Log.startTestCase(testCaseName);
			googlePage.navigateToBranchWebsite();
			act.scrollToBottomOfPage(driver);
			homePage.clickFooterTeamLink(driver);
			act.scrollDownthePage(driver);
			Log.info("Current URL =  " +driver.getCurrentUrl());
			Log.info("Current Title of the page -  " + driver.getTitle());
			List<WebElement> deptWebElements = teamPage.getAllDepartmentsWebElements(); // All with dept tab..
			Map<String, String> allTabInfo=teamPage.getDepartmentEmployees(deptWebElements.get(0));
			for (int i = 1; i < deptWebElements.size(); i++) {
				Map<String,String> deptEmployees = teamPage.getDepartmentEmployees(deptWebElements.get(i));
				for (String name : deptEmployees.keySet() )
				{
					Assert.assertTrue(allTabInfo.containsKey(name));
					Assert.assertEquals(deptEmployees.get(name), allTabInfo.get(name));
				}
			}
			Log.endTestCase(testCaseName);
			
		} catch (Exception e) {
			Log.error(e.toString());
		}

	}
	
	@Test
	public void validateEmployeeImages() {
		testCaseName = "validateEmployeeNames";
		try {
			Log.startTestCase(testCaseName);
			googlePage.navigateToBranchWebsite();
			act.scrollToBottomOfPage(driver);
			homePage.clickFooterTeamLink(driver);
			act.scrollDownthePage(driver);
			Log.info("Current URL =  " +driver.getCurrentUrl());
			Log.info("Current Title of the page -  " + driver.getTitle());
			List<WebElement> deptWebElements = teamPage.getAllDepartmentsWebElements(); // All with dept tab..
			Map<String, String> allTabImages=teamPage.getEmployeeNameAndImage(deptWebElements.get(0));
			for (int i = 1; i < deptWebElements.size(); i++) {
				Map<String,String> deptImages = teamPage.getEmployeeNameAndImage(deptWebElements.get(i));
				for (String name : deptImages.keySet() )
				{
					Assert.assertTrue(allTabImages.containsKey(name));
					Assert.assertEquals(deptImages.get(name), allTabImages.get(name));
				}
			}
			Log.endTestCase(testCaseName);
			
		} catch (Exception e) {
			Log.error(e.toString());
		}

	}

	@Test(enabled=false)
	public void validateMissingEmployeeNamesInDeptTabs() throws InterruptedException {
		testCaseName = "validateEmployeeNames1";
		Set<String> allNamesInAllDept = new HashSet<String>();
		try {
			boolean flag = false;
			Log.startTestCase(testCaseName);
			googlePage.navigateToBranchWebsite();
			act.scrollToBottomOfPage(driver);
			homePage.clickFooterTeamLink(driver);
			act.scrollDownthePage(driver);
			Log.info("Current URL =  " +driver.getCurrentUrl());
			Log.info("Current Title of the page -  " + driver.getTitle());
			List<WebElement> deptWebElements = teamPage.getAllDepartmentsWebElements(); // all tab depts
			Map<String, String> allTabNames=teamPage.getDepartmentEmployees(deptWebElements.get(0)); //All tab pairs
			for (int i = 1; i < deptWebElements.size(); i++) {
				WebElement currentWebElement=deptWebElements.get(i);
				Map<String, String> deptEmployees=teamPage.getDepartmentEmployees(currentWebElement);
				//allNamesInAllDept.addAll(teamPage.getDepartmentEmployees(deptWebElements.get(i)).keySet());
				allNamesInAllDept.addAll(deptEmployees.keySet());
			}
			
			for (String s : allTabNames.keySet()) {
				if (!allNamesInAllDept.contains(s)) {
					Log.info("This Employee" + s + " is present in All Tab list but not present in any department");
					flag = true;
					System.out.println(s);
				}
			}
			Log.endTestCase(testCaseName);
			Assert.assertEquals(flag, true,
					"There are people who are present in All Tab but does not belong any department.Details are mentioned in log");

		} catch (Exception e) {
			Log.error(e.toString());
		}

	}

	@Test(description = "Test Method to verify for presence of any broken links on Team page ",enabled=false)
	public void validateBrokenLinksOnTeamPage() {
		testCaseName = "validateBrokenLinksOnTeamPage";
		Map<String, String> brokenLinksWithReponseMessage = new HashMap<String, String>();
		try {
			Log.startTestCase(testCaseName);
			googlePage.navigateToBranchWebsite();
			act.scrollToBottomOfPage(driver);
			homePage.clickFooterTeamLink(driver);
			act.scrollDownthePage(driver);
			Log.info("Current URL =  " +driver.getCurrentUrl());
			Log.info("Current Title of the page -  " + driver.getTitle());
			brokenLinksWithReponseMessage = teamPage.getBrokenLinksForTeamPage();
			Log.info("Broken Link details" + brokenLinksWithReponseMessage);
			Log.endTestCase(testCaseName);
			Assert.assertEquals("0", brokenLinksWithReponseMessage.size(),
					"There are broken links detected on team page, details are mentioned in log");
		} catch (Exception e) {
			Log.error(e.toString());
		}
	}
	
	@Test(description="Test method to complete sign up process for branch demo request",enabled=false)
	public void validateSignUpForDemoRequest() {
	testCaseName="validateSignUpForDemoRequest";
	try {
		
		Log.startTestCase(testCaseName);
		googlePage.navigateToBranchWebsite();
		act.scrollToBottomOfPage(driver);
		homePage.acceptCookieAlert(driver);
		act.clickOnElement(driver, homePage.buttonRequestForDemo);
		Log.info("Current URL =  " +driver.getCurrentUrl());
		Log.info("Current Title of the page -  " + driver.getTitle());	
		requestForDemoPage.enterNecessaryDetailsForDemoRequest(driver,".com");
		act.clickOnElement(driver, requestForDemoPage.buttonSubmit);
		Log.info("URL of current page after submitting the demo request" +driver.getCurrentUrl());
		Assert.assertEquals("Thanks for submitting a demo request.",requestForDemoPage.headerDemoRequestSuccess.getText(),
				"There is some issue in submitting the demo request process");
		
		
	} catch (Exception e)
	{
		Log.error(e.toString());
	}
	}
	
	@AfterMethod
	public void tearDown() {
		try {
			driver.close();
		} catch (Exception e) {
			Log.error(e.toString());
		}
}
}