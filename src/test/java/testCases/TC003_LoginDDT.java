package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccount;
import testBase.BaseClass;
import utilities.Dataproviders;


/*
 * valid data - success -passed-logout
 * valid data- unsuccess-failed
 * invalid data-success-failed-logouy
 * invaliddata-unsuccess-paassed
 */

public class TC003_LoginDDT extends BaseClass {
	

	@Test(dataProvider="LoginData",dataProviderClass=Dataproviders.class ,groups="DataDrivern")  //dataprovide is in different package adn diff class  so add dataproviders.class)
	                                                                       //if data provider in sam package same clss the extra parameter is not required
	public void verifyLogin(String email,String pwd, String exp) throws InterruptedException   //parameter passed based on excel file 
	{
		logger.info("*****Starting the test case*********");
		
		try 
		{
		
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
		

/*
 * valid data - success -passed-logout
 * valid data- unsuccess-failed
 * invalid data-success-failed-logouy
 * invaliddata-unsuccess-paassed
 */
		
		if(exp.equalsIgnoreCase("valid"))    //exp is from excel file colum data is valid
		{
			if(page= true)             //login success
			{
				ma.clickLogout();
				Assert.assertTrue(true);
			}
				
				
			}
			else
			{
				Assert.assertTrue(false);   //if data is valid and logout failed test failed
			}
		
		
		if(exp.equalsIgnoreCase("invalid"))    //data is invalid
		{
			if(page= true)             //login success
			{
				ma.clickLogout();
				Assert.assertTrue(false);   //case if failed
			}
				
				
			}
			else
			{
				Assert.assertTrue(true);   //passed
			}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		Thread.sleep(3000);

	}

}
