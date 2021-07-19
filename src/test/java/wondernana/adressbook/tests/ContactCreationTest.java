package wondernana.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import wondernana.adressbook.data.ContactData;


public class ContactCreationTest extends TestBase{
	@Test
	public void testContactCreation() {
		int before = app.getElementsCount();
		app.createContact(new ContactData("John", "Doe", "911", "test1"), true);
		int after = app.getElementsCount();
		Assert.assertEquals(after, before + 1);
	}


}
