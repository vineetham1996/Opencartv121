
package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistration extends BaseClass {
	
	//moved to base class and extends
	//only test case class and method will be ther 
/*	//every class is consider as test cases
	//creating method
	 WebDriver driver ;
	@BeforeClass        //all the setups 
	void setup()
	{
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}
	
	@AfterClass
	void teardown()
	{
	
		//driver.quit();
	}
	*/
	//actual test 
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		//adding logger method to generate the logs 
		//logger already created in base class
		//severeal type of logs are available 
		//We can pass commands so we will be easly undrstand
		
		logger.info("*****Starting test case *****");
		
		
		//from the home page object class we need to access the methods first
		//to access methods from different class i need to create object of that class
		
		//put everything in try catch -- if any exception in future it will handle
		
		try {
		
		HomePage hp = new HomePage(driver);         //if we didnt pass driver inside the bracket -- constructor will revoke i dont want that i need other method
		
		hp.clickMyaccount();
		logger.info("*****clicked on my account *****");
		
		hp.clickRegister();
		logger.info("*****clicked on Register *****");
		//now we need to go accoubt registration page and access 
		
		AccountRegistrationPage ap= new AccountRegistrationPage(driver);
		
		ap.clickfirstname(randomeString().toUpperCase());
		ap.clicklastname(randomeString().toUpperCase());
		ap.clickemail(randomeString() + "@gmail.com");  //randomly we will create the email in runtime for that we need to write a method
		                                               //created method rendomstring and called
		                                               //char + gamil.com
		
		
		ap.clicktelphone(randomeint());
		
		//For password we need to create and store in variable and then pass 
		//using random generate different every time so password want match
		
		String password = randomealphanumeric();
		
		ap.clickpassword(password);
		ap.clickconfirm(password);
		
		ap.prvcychkbox();
		Thread.sleep(3000);
		ap.clickcontinue();
		logger.info("*****clicked on continue *****");
		ap.confMsg();
		
		//validation part 
		
		//i am using conditional statement because i need to check debud and fatal log
		
		String confmsg = ap.confMsg();
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			Assert.assertTrue(true);
		}
		
		else
		{
			logger.error("Test failed");         //exception error it will show
			logger.debug("Debug logs");          //detailedlogs 
			//logger.fatal("blocked test ");       //if blocked the test case
			
		}
		
		// Assert.assertEquals(confmsg, "Your Account Has Been Created thank!");
		
		
		
		
		
		}
		catch(Exception e)
		{
			
			
			//if catch executed means my test is failed so adding assertion
			
			logger.error("Test failed due to exception: " + e.getMessage(), e);  // 👈 added logging
            Assert.fail("Test failed due to exception: " + e.getMessage());      // 👈 make failure meaningful
		}
		
		logger.info("****Finished test case**********");
		}
		
		
		
	   
	
	//randomly we will create the email in runtime for that we need to write a method
	//also we can use for first name last name alsi -- call the method and convert.touppercase
	
	@SuppressWarnings("deprecation")
	public String randomeString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5); //this method is from common-lan3 library we added    
		 return  generatedstring;                                       //predefined class mention the character number you want 
	}
	
	//for random number we can use for telephone
	
	public String randomeint()
	{
		@SuppressWarnings("deprecation")
		String generatednum = RandomStringUtils.randomNumeric(10); //this method is from common-lan3 library we added    
		 return  generatednum;                                     //predefined class mention the  number you want 
	}
	
	//for random alphanumeric
	@SuppressWarnings("deprecation")
	public String randomealphanumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednum = RandomStringUtils.randomNumeric(3); //this method is from common-lan3 library we added    
		 return  (generatedstring +"@"+ generatednum);                                     //predefined class mention the  number you want 
	}
	
	//
}
