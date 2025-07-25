package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	




	
	@FindBy(xpath ="//input[@id='input-email']" )
	WebElement lnkemail;
	
	@FindBy(xpath ="//input[@id='input-password']" )
	WebElement lnkpwd;
	
	@FindBy(xpath ="//input[@value='Login']" )
	WebElement lnklgn;
	
	public void addemail(String email)
	{
		lnkemail.sendKeys(email);
	}
	
	public void addpwd(String password)
	{
		lnkpwd.sendKeys(password);
	}
	public void addclick()
	{
		lnklgn.click();
	}

}
