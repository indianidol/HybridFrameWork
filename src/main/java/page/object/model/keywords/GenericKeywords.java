package page.object.model.keywords;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import page.object.model.commons.BrowserManger;
import page.object.model.commons.ExtentManager;
import page.object.model.commons.MyListener;


public class GenericKeywords extends page.object.model.commons.MyListener	{

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	
	
	/*********************Setter functions***************************/
	public String getProceedOnFail() {
		return proceedOnFail;
	}

	public void setProceedOnFail(String proceedOnFail) {
		this.proceedOnFail = proceedOnFail;
	}


	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}
	
	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	public void setData(LinkedHashMap<String, String> data) {
		this.data = data;
	}
    /*****************************************/
	

	
	

	public void openBrowser(){
		bM = new BrowserManger();
	}
	


	public void click(WebElement we, String Comment){
		try {
		test.log(Status.INFO,"Clicking "+Comment);
		we.click();
		}catch (Exception e ){
			reportFailure("Could not Click "+ Comment);		
		}
	}
	
	
	
	
	public void type(WebElement we, String Comment, String data){
	we.sendKeys(data);
	}
	
	
	
	public void selectByVisibleText(WebElement we, String data){	
		if(isElementInList(we,data))
			reportFailure("Option not found in list "+ data);		
		new Select(we).selectByVisibleText(data);
	}
	

	public void clear(WebElement we, String Comment) {
		test.log(Status.INFO,"Clearing "+Comment);
		we.clear();
	}
	
	public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		
		while(i!=10){
		String state = (String)js.executeScript("return document.readyState;");
		System.out.println(state);

		if(state.equals("complete"))
			break;
		else
			wait(2);

		i++;
		}
		// check for jquery status
		i=0;
		while(i!=10){
	
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue() == 0 )
			 	break;
			else
				 wait(2);
			 i++;
				
			}
		
		}
	
	public void acceptAlert(){
		test.log(Status.INFO, "Switching to alert");
		
		try{
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			test.log(Status.INFO, "Alert accepted successfully");
		}catch(Exception e){
			
				reportFailure("Alert not found when mandatory");
			
		}
		
	}
	
	
	public void wait(int timeSec){
		try {
			Thread.sleep(timeSec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void validateTitle(String expectedTitle ){
		test.log(Status.INFO,"Validating title - "+expectedTitle );
		
		String actualTitle=driver.getTitle();
		if(!expectedTitle.equals(actualTitle)){
			// report failure
			reportFailure("Titles did not match. Got title as "+ actualTitle);
		}
	}
	
	
	
	public void waitTillSelectionToBe(	WebElement e ,String objectkey , String expected) {
		int i=0;
		String actual="";
		while(i!=10){		 
			Select s = new Select(e);
		    actual = s.getFirstSelectedOption().getText();
			if(actual.equals(expected))
				return;
			else
				wait(1);			
				i++;	
		}
		// reach here
		reportFailure("Values Dont match. Got value as "+actual);
		
		
	}
	
	public void quit(){
		if(driver!=null)
			driver.quit();
	}
	/*********************Utitlity Functions
	 * @throws ClassNotFoundException ************************/
	



	public WebElement isElementPresentAndClickable(String xpath){
		WebElement e=null;
	try {
		WebDriverWait wait = new WebDriverWait(driver,20);
		// visibility of Object
		wait.until(ExpectedConditions.visibilityOf(e));
		// state of the object-  clickable
		wait.until(ExpectedConditions.elementToBeClickable(e));
		
		e=driver.findElement(By.xpath(xpath));
			 
	}catch(Exception ex){
		// failure -  report that failure
		reportFailure("Object Not Found " + xpath);
	}
	return e;
				
	}
	
	public boolean isElementInList(WebElement select,String option){
		// validate whether value is present in dropdown
				List<WebElement> options = new Select(select).getOptions();
				for(int i=0;i<options.size();i++){
					if(options.get(i).getText().equals(option))
						return true;
				}
				
				return false;
	}
	
	/*******Reporting function********/
	public void reportFailure(String failureMsg){
		// fail the test
		test.log(Status.FAIL, failureMsg);
		
		// take the screenshot, embed screenshot in reports
		takeSceenShot();
		// fail in testng
		//Assert.fail(failureMsg);// stop on this line
		if(proceedOnFail.equals("Y")){// soft assertion
			softAssert.fail(failureMsg);
		}else{
			softAssert.fail(failureMsg);
			softAssert.assertAll();
		}
	}
	
	public void takeSceenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";	
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotFolderPath+screenshotFile));
			//put screenshot file in reports
			test.log(Status.FAIL,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void assertAll(){
		softAssert.assertAll();
	}
}
