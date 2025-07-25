package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;

public class TC002_Login extends BaseClass {
	
	
	@Test(groups={"Sanity", "Master"})
	public void verifyLogin()
	{
		logger.info("*****Starting the test case*********");
		
		try {
HomePage hp = new HomePage(driver);         //if we didnt pass driver inside the bracket -- constructor will revoke i dont want that i need other method
		
		hp.clickMyaccount();
		logger.info("*****clicked on my account *****");
		
		hp.clickLogin();
		logger.info("*****clicked on Register *****");
		 
		LoginPage lp = new LoginPage(driver);
		lp.addemail(p.getProperty("email"));
		lp.addpwd(p.getProperty("password"));
	
		
		
		lp.addclick();
		 
	
		
		MyAccount ma = new MyAccount(driver);
		boolean page = ma.isMyAccountPageExist();   //it will return true or false
		
		Thread.sleep(3000);
		
	
		
		
		Assert.assertEquals(page, true ,"login failed");   //if page value is true it passed else it failed login
		
		ma.clickLogout();
		}
		catch(Exception e)
		{
			logger.error("Test failed due to exception: " + e.getMessage(), e);  // 👈 added logging
            Assert.fail("Test failed due to exception: " + e.getMessage());      // 👈 make failure meaningful
		}
	}

}
