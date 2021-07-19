package wondernana.adressbook.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;
import wondernana.adressbook.data.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactDeletionTest extends TestBase{
	@Test
	public void testContactDeletion(){
		if (!app.isContactPresent()) {
			app.createContact(new ContactData("John", "Doe", "911", null), false);
		}
		int before = app.getElementsCount();
		app.selectContact(0);
		//acceptNextAlert = true;
		app.driver.findElement(By.xpath("//input[@value='Delete']")).click();
		app.driver.switchTo().alert().accept();
		app.click(By.linkText("home"));
		int after = app.getElementsCount();
		Assert.assertEquals(after, before - 1);
	}
//	private String closeAlertAndGetItsText() {
	//	try {
		//	Alert alert = app.driver.switchTo().alert();
		//	String alertText = alert.getText();
	//		return alertText;
	//	} catch (NoAlertPresentException e) {
		//	return String.valueOf(e);
	//	}
	//}
}
