package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

//created base page under page object which have only constructor we can reuse

//parent of all the page object class and we can take the constructor initialisationn in all the classes using super(this)  
//no need to write pagefactory things again
//achieved reusability

public class BasePage {
	
	public WebDriver driver;
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;                         //Makes the driver available to all methods in that class for performing actions like click, sendKeys, findElement, etc
		PageFactory.initElements(driver, this);      //key part of the Page Object Model (POM) in Selenium and Java. It is used to initialize WebElements that are annotated with @FindBy, @FindAll, or @FindsBy in your page class.
	}
}
