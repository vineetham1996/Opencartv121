package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage                     //inherited from parent class base page
{
	//make sure everything is public then only we can access
	
	//whenever we created page object class have 3 parts --- constructor , Locator , Action method
	
	
  
	WebDriver driver;                   //i have created driver class variable and passed in constructor
	
	//step 1 creating constructor
	
	public HomePage(WebDriver driver)
	{
		super(driver);                  //this menthod will take the page factory method and initiate the 
	}
	
	//step 2 locators
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")    //easiest way to find the locator
	WebElement lnkMyaccount;                                      //stroing into web element
	
	
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement lnkLogin;
	

	//Step 3 Action method
	
	 public void clickMyaccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
	
}
