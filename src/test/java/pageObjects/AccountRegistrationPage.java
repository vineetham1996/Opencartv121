package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

//make sure everything is public

public class AccountRegistrationPage extends BasePage {
 
	
	public WebDriver driver;
	
	
	//constructor
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	//locator
	

	
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement lnkfirstname;
	
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement lnklastname;
	
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement lnkemail;
	
	@FindBy(xpath ="//input[@id='input-telephone']")
	WebElement lnktelephone;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement lnkpassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']" )
	WebElement lnkconfirm;
	
	@FindBy(xpath = "//input[@value='0']")
	WebElement lnkchkbox;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement lnkprvcy;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement lnkcontinue;
	
	@FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement lnkconf;
	
	
	
	//Action method   
	//parse the parameter we will create property file to data
	
	public void clickfirstname(String fname)
	{
		lnkfirstname.sendKeys(fname);
	}
	public void clicklastname(String lstname)
	{
		lnklastname.sendKeys(lstname);
	}
	public void clickemail(String email)
	{
		lnkemail.sendKeys(email);
		
	}
	
	public void clicktelphone(String tel)
	{
		lnktelephone.sendKeys(tel);
	}
	public void clickpassword(String pwd)
	{
		lnkpassword.sendKeys(pwd);
	}
	public void clickconfirm(String pwd)
	{
		lnkconfirm.sendKeys(pwd);
	}
	
	public void clickcheckbox()
	{
		lnkchkbox.click();
	}
	
	public void prvcychkbox()
	{
		lnkprvcy.click();
	}
	public void clickcontinue()
	{
		lnkcontinue.click();
		
		//if click is not working use other options
		
		//lnkcontinue.submit();
		
		//Actions act = new Actions(driver);
		//act.moveToElement(lnkcontinue).click().perform();
		
		//lnkcontinue.sendkeys(keys.return);
		
		//Explicitwait
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofseconds(10));
		//mywait.until(Expectedconditions.elementtobeclickabe(lnkcontinue)).click();
		
		
		
	}
	 public String confMsg()
	{
		 try {
		return(lnkconf.getText());            //ill get the text and return the value .. we wil use in test cases 
		 }                                     //using try catch -- because there is a chance of exception so i dont want to block remaining
		 catch(Exception e)
		 {
			 return(e.getMessage());
		 }
	
	}
	 
	 
	
}
