package wondernana.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import wondernana.adressbook.data.GroupData;

import java.util.List;

public class GroupDeletionTest extends TestBase {
	@Test
	public void testGroupDeletion() {
		app.goToGroupPage();
		if (!app.isGroupPresent()) {
			app.createGroup(new GroupData("test", null, null, null));
		}
		List<GroupData> before = app.getGroupList();
		app.selectGroup(before.size()-1);
		app.deleteSelectedGroup();
		app.returnToGroupPage();
		List<GroupData> after = app.getGroupList();
		Assert.assertEquals(after.size(), before.size()-1);
		before.remove(before.size()-1);
		Assert.assertEquals(before, after);
	}
}
