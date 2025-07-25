package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccount extends BasePage {
	
	public MyAccount(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath = "//h2[normalize-space()='My Account']" )
	WebElement lnkacc;
	
	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnklogout;

	//to validate the my acount exist
	//add in try catch if exist reyrn true-- if false return false
	
	public boolean isMyAccountPageExist()

	{
	      try {
	    	  return(lnkacc.isDisplayed());
	      }
	      catch(Exception e)
	      {
	    	  return false;
	      }
	}
	
	public void clickLogout()
	{
		lnklogout.click();
	}
}
