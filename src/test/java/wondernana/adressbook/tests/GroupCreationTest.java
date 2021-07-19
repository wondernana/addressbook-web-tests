package wondernana.adressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import wondernana.adressbook.data.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goToGroupPage();
    List<GroupData> before = app.getGroupList();
    //TODO parent group
    app.createGroup(new GroupData("test", null, null, "parent"));
    List<GroupData> after = app.getGroupList();
    Assert.assertEquals(after.size(), before.size()+1);
    before.add(after.get(after.size()-1));
    System.out.println(before);
    System.out.println(after);
    Assert.assertEquals(before, after);
  }

}

