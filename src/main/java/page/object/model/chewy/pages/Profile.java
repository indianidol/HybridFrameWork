package page.object.model.chewy.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Profile {
	

	@FindBy(xpath="(//span[text()='Edit'])[1]")
	public WebElement name_edit_link ;
	
	@FindBy(xpath="(//span[text()='Edit'])[2]")
	public WebElement name_email_link ;
	
	
	@FindBy(xpath="(//span[text()='Edit'])[2]")
	public WebElement name_password_link ;
	
	
	public boolean verifchangeEmail(String username, String passsword) throws Exception{
		boolean teststatus= false;
		try{
		//Steps for changing the email
			teststatus =true;
		}catch(Exception e){
			//report in extentreports
			//throw e;  << depends on the test
		}	
		
		return false;
		
	}
	

}
