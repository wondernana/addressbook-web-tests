package wondernana.adressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import wondernana.adressbook.appmanager.ApplicationManager;

public class TestBase {

	protected final ApplicationManager app = new ApplicationManager(BrowserType.FIREFOX);

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {
		app.init();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		app.stop();
	}


}
