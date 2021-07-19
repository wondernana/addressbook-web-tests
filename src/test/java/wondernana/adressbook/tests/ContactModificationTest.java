package wondernana.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import wondernana.adressbook.data.ContactData;

public class ContactModificationTest extends TestBase{
	@Test
	public void testContactModification(){
		if (!app.isContactPresent()) {
			app.createContact(new ContactData("John", "Doe", "911", null), false);
		}
		int before = app.getElementsCount();
		app.selectContact(0);
		app.fillInContactForm(new ContactData("John", "Doe", "911", null), false);
		app.click(By.xpath("(//input[@name='update'])"));
		app.returnToHomePage();
		int after = app.getElementsCount();
		Assert.assertEquals(after, before - 1); // баг: после модификации эл-т удаляется
	}

}
