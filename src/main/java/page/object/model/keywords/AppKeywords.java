package page.object.model.keywords;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;



public class AppKeywords extends GenericKeywords{

	public void login(){
		openBrowser();
		
	}
	public void validateLogin(){
		
		test.log(Status.INFO, "Starting login");
		
		
	}
	
	public void navigate() {
		
	}


	
	
}
