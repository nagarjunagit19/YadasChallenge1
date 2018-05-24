package io.branch.pages;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.branch.utilities.DriverActions;
import io.branch.utilities.Global;

public class TeamPage {

	WebDriver driver = null;
	DriverActions act = new DriverActions();
	Global util = new Global();

	@FindBy(xpath = ".//ul[@class='team-categories']/li/a")
	public List<WebElement> departmentList;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[1]/a[text()='All']")
	public WebElement tabAll;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[2]/a[text()='Data']")
	public WebElement tabData;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[3]/a[text()='Engineering']")
	public WebElement tabEngineering;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[4]/a[text()='Marketing']")
	public WebElement tabMarketing;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[5]/a[text()='Operations']")
	public WebElement tabOperations;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[6]/a[text()='Partner Growth']")
	public WebElement tabPartnerGrowth;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[7]/a[text()='Product']")
	public WebElement tabProduct;

	@FindBy(xpath = ".//section[2]/div/div[1]/ul/li[8]/a[text()='Recruiting']")
	public WebElement tabRecruiting;

	@FindBy(xpath = ".//section[2]//div[2]/div/div[@class='info-block']")
	public List<WebElement> employeeInfoBlock;

	@FindBy(xpath = ".//section[2]/div//div[@class='info-block']/h2")
	public List<WebElement> employeeNamesWebElement;

	@FindBy(xpath = ".//section[2]/div//div[@class='info-block']/h4")
	public List<WebElement> employeeDepartmentsWebElement;

	@FindBy(tagName = "a")
	public List<WebElement> anchorTags;

	@FindBy(tagName = "img")
	public List<WebElement> imageTags;

	@FindBy(xpath = ".//div[@class='info-block']/h2")
	public List<WebElement> employeeNames;

	@FindBy(xpath = ".//div[@class='image-block']")
	public List<WebElement> employeeImages;

	/******************************************************************************
	 * Constructor Name: TeamPage
	 * 
	 * @Description: Initializing Web driver
	 * @author Nagarjuna
	 * @throws Exception
	 *******************************************************************************/
	public TeamPage(WebDriver driver) throws IOException {
		this.driver = driver;
		Properties prop = new Properties();
		InputStream input = Global.loadProperties();
		prop.load(input);

	}

	/******************************************************************************
	 * @Description: This method adds all departments into a single list
	 * @author Nagarjuna
	 * @return List of All departments
	 *******************************************************************************/
	public List<String> getDepartmentList() {
		List<String> departments = new ArrayList<String>();

		for (WebElement w : departmentList) {
			if (w.isDisplayed()) {
				String deptName = w.getText();
				departments.add(deptName);
			}
		}

		return departments;
	}

	/********************************************************************************
	 * @Description: This method provides list of employee names for given
	 *               department
	 * @author Nagarjuna
	 * @param dept
	 * @return a List of employee Names under given department
	 ********************************************************************************/
	public Map<String, String> getDepartmentEmployees(WebElement dept) {

		act.clickOnElement(driver, dept);
		Map<String, String> empNameandDept = new HashMap<String, String>();

		for (WebElement w : employeeInfoBlock) {
			if (w.isDisplayed()) {
				String nameDept = w.getText();
				empNameandDept.put(nameDept.split("\n")[0], nameDept.split("\n")[1]);
			}
		}

		return empNameandDept;

	}

	/********************************************************************************
	 * @Description: This method adds all the webelement departments into single
	 *               list
	 * @author Nagarjuna
	 * @return a List of all weblements of department
	 ********************************************************************************/
	public List<WebElement> getAllDepartmentsWebElements() {

		List<WebElement> l = new ArrayList<WebElement>();
		l.add(tabAll);
		l.add(tabData);
		l.add(tabEngineering);
		l.add(tabMarketing);
		l.add(tabOperations);
		l.add(tabPartnerGrowth);
		l.add(tabProduct);
		l.add(tabRecruiting);

		return l;
	}

	/***************************************************************************************
	 * @Description: This method captures all the href links for image and anchor
	 *               attributes
	 * @author Nagarjuna
	 * @return a List of all href attributes
	 **************************************************************************************/
	public List<String> allLinkOnTeamPage() {
		List<WebElement> anchorandImageTag = new ArrayList<>();
		List<String> linksTeamPage = new ArrayList<String>();
		anchorandImageTag.addAll(anchorTags);
		anchorandImageTag.addAll(imageTags);
		for (WebElement element : anchorandImageTag) {
			if (element.getAttribute("href") != null && element.getAttribute("href").startsWith("http"))

			{
				linksTeamPage.add(element.getAttribute("href"));

			}

		}

		return linksTeamPage;
	}

	/***********************************************************************************
	 * @Description: This method checks presence of broken links
	 * @author Nagarjuna
	 * @return a HashMap of all broken link and corresponding HTTP response on team
	 *         page
	 ***********************************************************************************/
	public HashMap<String, String> getBrokenLinksForTeamPage() {
		return util.checkForBrokenLinks(allLinkOnTeamPage());
	}

	public Map<String, String> getEmployeeNameAndImage(WebElement dept) {
		act.clickOnElement(driver, dept);
		List<String> empNames = new ArrayList<String>();
		List<String> empImages = new ArrayList<String>();
		Map<String, String> nameImage = new HashMap<String, String>();
		for (WebElement name : employeeNames) {
			if (name.isDisplayed()) {
				empNames.add(name.getText());
			}
		}

		for (WebElement image : employeeImages) {
			if (image.isDisplayed()) {
				empImages.add(image.getText());
			}
		}

		for (int i = 0; i < empNames.size(); i++) {
			String name = empNames.get(i);
			if (i >= empImages.size()) {
				nameImage.put(name, null);
			} else {
				nameImage.put(name, empImages.get(i));
			}
		}

		return nameImage;

	}

}
