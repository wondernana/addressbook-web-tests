package wondernana.adressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import wondernana.adressbook.data.GroupData;

import java.util.List;

public class GroupModificationTest extends TestBase {
	@Test
	public void testGroupModification(){
		app.goToGroupPage();
		if (!app.isGroupPresent()) {
			app.createGroup(new GroupData("test", null, null, null));
		}
		List<GroupData> before = app.getGroupList();
		app.selectGroup(0);
		app.click(By.name("edit"));
		app.fillInGroupForm(new GroupData("test1", "test2", "test3", "test1"));
		app.submitGroupModification();
		app.returnToGroupPage();
		List<GroupData> after = app.getGroupList();
		Assert.assertEquals(after.size(), before.size());
		//Assert.assertEquals(before, after);
	}
}
