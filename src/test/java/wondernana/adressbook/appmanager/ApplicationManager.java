package wondernana.adressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.ui.Select;
import wondernana.adressbook.data.ContactData;
import wondernana.adressbook.data.GroupData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.By.*;
import static org.testng.Assert.fail;

public class ApplicationManager {
	private final String browser;
	public WebDriver driver;
	public String baseUrl;
	public boolean acceptNextAlert = true;
	public StringBuffer verificationErrors = new StringBuffer();

	public ApplicationManager(String browser) {
		this.browser = browser;
	}

	public void init() {

		baseUrl = "https://www.google.com/";
		if (browser.equals(BrowserType.FIREFOX)) {
			System.setProperty("webdriver.gecko.driver", "c://users//akazakova/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals(BrowserType.CHROME)) {
			System.setProperty("webdriver.chrome.driver", "c://users//akazakova/chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equals(BrowserType.OPERA)) {
			System.setProperty("webdriver.opera.driver", "c://users//akazakova/operadriver.exe");
			driver = new OperaDriver();
		}
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.get("http://localhost/addressbook/");
		login("admin", "secret");

	}

	public void login(String username, String password) {
		type(By.name("user"), username);
		type(By.name("pass"), password);
		click(By.xpath("//input[@value='Login']"));
	}

	public void returnToGroupPage() {
		click(By.linkText("group page"));
	}

	public void submitGroupForm() {
		click(By.name("submit"));
	}
	public void submitGroupModification() {
		click(By.name("update"));
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	public void fillInGroupForm(GroupData groupData) {
		type(By.name("group_name"), groupData.getName());
		type(By.name("group_header"), groupData.getHeader());
		type(By.name("group_footer"), groupData.getFooter());
		try  {
			if (groupData.getParentGroup() != null) {
				if (isElementPresent(By.name("group_parent_id"))) {
					new Select(driver.findElement(By.name("group_parent_id"))).selectByVisibleText(groupData.getParentGroup());
				}
			}
		}
		catch (Exception e){
			return;
		}

	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
			return true;
		}
			catch (NoSuchElementException e) {
			return false;
			}
	}

	public void type(By locator, String text) {
		if (text != null) {
			String existingText = driver.findElement(locator).getAttribute("value");
			if (!existingText.equals(text)) {
				click(locator);
				driver.findElement(locator).clear();
				driver.findElement(locator).sendKeys(text);
			}
		}
	}

	public void initGroupCreation() {
		click(By.name("new"));
	}

	public void goToGroupPage() {
		click(By.linkText("groups"));
	}

	public void stop() {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
		  fail(verificationErrorString);
		}
	}

	public void deleteSelectedGroup() {
		click(By.name("delete"));
	}

	public void selectGroup(int index) {
		driver.findElements(name("selected[]")).get(index).click();
	}
	public void returnToHomePage() {
		click(linkText("home page"));
	}

	public void submitContactForm() {
		click(xpath("(//input[@name='submit'])[2]"));
	}

	public void fillInContactForm(ContactData contactData, boolean creation) {
		type(name("firstname"), contactData.getFirstname());
		type(name("lastname"), contactData.getLastname());
		type(name("mobile"), contactData.getMobile());
		if (creation) {
			if (isElementPresent(By.name("new_group"))) {
				new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
			}
		}
	}

	public void initContactCreation() {
		click(linkText("add new"));
	}

	public boolean isGroupPresent() {
		return isElementPresent(className("group"));
	}

	public void createGroup(GroupData group) {
		initGroupCreation();
		fillInGroupForm(group);
		submitGroupForm();
		returnToGroupPage();
	}

	public boolean isContactPresent() {
		return isElementPresent(name("selected[]"));
	}

	public void createContact(ContactData contactData, boolean creation) {
		initContactCreation();
		fillInContactForm(contactData, creation);
		submitContactForm();
		returnToHomePage();
	}

	public int getElementsCount() {
		return driver.findElements(name("selected[]")).size();
	}

	public void selectContact(int index) {
		driver.findElements(name("selected[]")).get(index).click();
	}

	public List<GroupData> getGroupList() {
		List<GroupData> groups = new ArrayList<GroupData>();
		List<WebElement> elements = driver.findElements(By.cssSelector("span.group"));
		for (WebElement element : elements){
			String name = element.getText();
			GroupData group = new GroupData(name, null, null, null);
			groups.add(group);
		}
		return groups;
	}
}
