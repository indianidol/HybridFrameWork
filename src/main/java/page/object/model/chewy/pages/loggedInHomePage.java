package page.object.model.chewy.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loggedInHomePage {
	
	WebDriver driver;
	WebDriverWait wait;

	public loggedInHomePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath = "//h1[contains(text(),'Welcome back')]")
	public WebElement welcome_back;
	
	public String getWelcomeText(){
		
		return welcome_back.getText();
		
	}
	
	

}
