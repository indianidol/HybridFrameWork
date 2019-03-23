package page.object.model.chewy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class landingPage {
	WebDriver driver;
	WebDriverWait wait;

	public landingPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		
		//with page factory
		PageFactory.initElements(driver, this);
	}
	
	By header_Search = By.xpath("//input[@id='query search-autocomplete']");

	@FindBy(xpath = "//input[@id='query search-autocomplete']")
	public WebElement header_Search_TextBox;

	@FindBy(xpath = "//ul[@role='listbox']")
	public WebElement suggesstion_list;

	@FindBy(xpath = "//span[@class='top-nav-account']")
	public WebElement yourAccount_link;

	@FindBy(xpath = "//*[@name='username']")
	public WebElement username_text;

	@FindBy(xpath = "//*[@name='password']")
	public WebElement password_text;

	@FindBy(xpath = "//*[@type='submit']")
	public WebElement submit_button;

	public String gettitle() {

		return driver.getTitle();

	}

	public int getdefaultSugesstionCount() {

		
		//without page factory
		//driver.findElement(header_Search).click();
		
		header_Search_TextBox.click();
		wait.until(ExpectedConditions.visibilityOf(suggesstion_list));

		return suggesstion_list.findElements(By.tagName("li")).size();
	}

	public void login(String uname, String password) {

		yourAccount_link.click();
		username_text.sendKeys(uname);
		password_text.sendKeys(password);
		submit_button.click();

	}

}
