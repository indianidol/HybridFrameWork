package page.object.model.tests;

import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import page.object.model.chewy.pages.landingPage;
import page.object.model.chewy.pages.loggedInHomePage;
import page.object.model.commons.CommonFunctions;

public class testCases extends TestBase {


//	 @Test
	public void verifyLandingpagefeatures(Method ere) {
		 
		 System.out.println(ere);

	//	landingPage lp = new landingPage(driver, wait);

		//Assert.assertTrue(				lp.gettitle().equals("Pet Food, Products, Supplies at Low Prices - Free Shipping | Chewy.com"));

//		org.testng.Assert.assertEquals(10, lp.getdefaultSugesstionCount());
	}

//	@Test(dataProvider = "getDatafromexcel")
	public void verifyLoginfunctionality(String runMode, String username, String password, String user) {

		if (runMode.equals("Y")) {
			
			landingPage lp = new landingPage(driver, wait);
			loggedInHomePage lhp = new loggedInHomePage(driver, wait);

			// lp.login("Cignitiracqa@gmail.com", "India@123");

			lp.login(username, password);
			Assert.assertEquals("Welcome back, " + user + "!", lhp.getWelcomeText());
			
	//		BrowserManger("FF", "www.gmail.com");
			
			
		} else {
		
			throw new SkipException(currentTestCase + "Skipped with username  " + username);
		}
	}
//	@Test
	public static void testss() {
		String st = "this is \"a world";
		
		System.out.println(st);
	}

	
	@Test(dataProvider = "getData")
	public void VerifyReport(LinkedHashMap<String, String> dataset) throws Exception {

		test.log(Status.INFO, "Verify report test is Starting");
			System.out.println(dataset);
	/*		landingPage lp = new landingPage(driver, wait);
			loggedInHomePage lhp = new loggedInHomePage(driver, wait);

			lp.login(dataset.get("Username"), dataset.get("Password"));
			*/
			// lp.login("Cignitiracqa@gmail.com", "India@123");

		//	lp.login(username, password);
		
		/*	//test.log(Status.PASS, "Verify report test has Passed");
			try {
//				Assert.assertEquals("Welcome back, " + dataset.get("Username") + "!", lhp.getWelcomeText());
				 lhp.getWelcomeText();
				throw new Exception(); 
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			
				
				
				test.log(Status.FAIL, "Verify report test has Failed");
				
				CommonFunctions.takeSnapShot(screenshotFolderPath+"\\"+currentTestCase+CommonFunctions.getdate()+".png", lhp.welcome_back);
				e.printStackTrace();
			}*/
			
			
		/*} else {
		
			throw new SkipException(currentTestCase + "Skipped with username  " + username);
		}*/
	}
}
