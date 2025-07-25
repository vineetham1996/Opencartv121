package testBase;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;





public class Baseclassref {
	
	//base class contain reusable methods from test case 
	//we can use across multiple test case 
	//test cases will lokk simpler
	//we can just extend the test cases
	
	//every class is consider as test cases
	
		//creating method
		
		 public static WebDriver driver ;              //common variable acrross multiple object
		 
		 
		                                         // adding log4j in base class
		 public Logger logger;                    //creaate the logger and import this is to get logs
		
		 public Properties p; 
		@SuppressWarnings("deprecation")
		@BeforeClass(groups= {"Sanity","Regression","Master"})      //all the setups 
		
		@Parameters({"os" , "browser"})      //this is to parallel test, desired browser ,os.....
		                                      //pass parameter for OS and browser
		public void setup(String os, String br) throws IOException
		{
			
			//loading config file for common things like url username password so we dont need to mention
			
			FileReader file = new FileReader("./src//test//resources//config.properties");        //open the file path of the file
			
			p= new Properties();
			p.load(file);             //created object and loaded file now we can use it in driver . methods
			
			logger = LogManager.getLogger(this.getClass());   //this.getclass will look for perticular class and get logs then stored in logger 
					                                          //using logger we can get logs
			
			//-----------------------------------------------------//
			//Grid Concept
			
			//i am writing here a condition based on that test case will decide to execute locally or remote
			//added new parameter in property file
			//remote condition we use grid setup to decide the browser
			
			//if executing environment is remote
		
		
			
			
			//normal scenario
			switch(br.toLowerCase())
			{
			case  "chrome" : driver = new ChromeDriver();break;
			case  "firefox" :driver = new FirefoxDriver();break;
			}
			
			
			
	
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get(p.getProperty("appURL"));       //reading url from property file ... using p.getpropety() we can add everything we mentioned in property file
			driver.manage().window().maximize();
			logger.info("Driver installed successfully ");
		
		
		}
		
		@AfterClass(groups= {"Sanity","Regression","Master",})
		public void teardown() throws InterruptedException
		{
			

			driver.quit();
		}
		
		
		//to capture the screenshot
		//whenever the test failed then only we need to take screensho
		//if on testfailure executed my test is failed 
		//On Testfailure we will call the capture
		
		
		
		public String captureScreen(String tname)throws IOException {
			
			
			
			String timeStamp=new SimpleDateFormat("yyyyMMddhhss").format(new Date());
			
			TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
			
			File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String targetFilePath=System.getProperty("user.dir")+"\\screenshot\\"+tname+"_"+timeStamp+".png";
			File targetFile=new File(targetFilePath);
			sourceFile.renameTo(targetFile);
			
			return targetFilePath;
		}

}