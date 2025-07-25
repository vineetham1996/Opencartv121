package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
		
		public ExtentSparkReporter sparkReporter;      //UI of the report
		
		public ExtentReports extent;                   //Common information (name of executer,project name,os name ,env details etc...
		
		public ExtentTest test;                        //Responsible for updating the status,attaching screenshot etc

		String repName;
		
		public void onStart(ITestContext testContext) {
			
			/*SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt = new Date();
			String currentdatetimestamp = df.format(dt);   //wiil genearte time stamp */
			
			String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//wiil genearte time stamp in java
			repName ="Test-Report" + timeStamp + ".html" ;   //report name will generate with timestamp
			
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);  //locatoin
			
			sparkReporter.config().setDocumentTitle("Opencart Automation Report");  //tittle od report
			sparkReporter.config().setReportName("Opencart Functional testing");   //name of report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);   //to connect the classes eath other
			extent.setSystemInfo("Application", "Opencart");  //project specified details
			extent.setSystemInfo("Module", "Admin");
			extent.setSystemInfo("Submodule", "windows10");
			extent.setSystemInfo("Browser", "Customers");
			extent.setSystemInfo("User Name",System.getProperty("user.name"));   //will return the current user of system
			extent.setSystemInfo("Environment", "QA");
			
			String os = testContext.getCurrentXmlTest().getParameter("os");     //capturing OS details dynamically from xml file
			extent.setSystemInfo("Operating System", os);
			
			String browser = testContext.getCurrentXmlTest().getParameter("browser"); //capturing browser details dynamically from xml file
			extent.setSystemInfo("Browser", browser);
			
			List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();  //populating included groups information from xml file
			if(!includedGroups.isEmpty()) {           //if group name is available
				extent.setSystemInfo("Groups", includedGroups.toString());  //add group names in report
			}
			
			
		}
		public void onTestSuccess(ITestResult result) {
		    // not implemented
		 
		
		test= extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());      //to display groups in report test case wise
		test.log(Status.PASS, result.getName() + "got successfully executed" );           //this will update the status p/f/s
		 
		 
		 
		  }
		
		public void onTestFailure(ITestResult result) {
			
		    // not implemented
		 test= extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, result.getName() + "got failed" );     //get name will get the test name
		 test.log(Status.INFO,result.getThrowable().getMessage());  //get throwable will generate error message
		 
		 try {
			                                                                    //object of baseclass created 
			                                                                    //executed code from base class and return the location of image
			 String imgPath = new BaseClass().captureScreen(result.getName());  
			 test.addScreenCaptureFromPath(imgPath);                          //attaching the screenshot of failure
		 }
		 catch(IOException e1)
		 {
			 e1.printStackTrace();
		 }
		  }
		public void onTestSkipped(ITestResult result) {
		    // not implemented
		 test= extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, result.getName() + "got failed" );     //get name will get the test name
		 test.log(Status.INFO,result.getThrowable().getMessage()); 
		}
		
		public void onFinish(ITestContext context) {
		    // not implemented
		 
		 extent.flush();    //write all test information - this is mandatory
		 
		 //to opern report automaticaly after test i can use the below code--not mandatory 
		 
		 String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport = new File(pathOfExtentReport);
		 
		 try {
			 Desktop.getDesktop().browse(extentReport.toURI());
		     }
		 catch(IOException e)
		      {
			   e.printStackTrace();
		      }
		 
		 
		 //to send mail automatically after generating repor
		 //add java email dependencey
		/* 
		 try {
			 URL url = new URL("file:///" + System.getProperty("user.dir")+"\\reports\\" + repName);
			 
			 ImageHtmlEmail email = new ImageHtmlEmail();
			 
			 email.setDataSourceResolver(new DataSourceUrlResolver(url));
			 email.setHostName("smtp.googlemail.com");
			 email.setSmtpPort(465);
			 email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com" , "password"));
			 email.setSSLOnConnect(true);
			 email.setFrom("email id " ); //emailid of sender
			 email.setSubject("Test Result");
			 email.setMsg("Please find attached report");
			 email.addTo("email id "); //emailid of reciever can be multiple -use distribution list
			 email.attach(url,"extent_report","please check report...");
			 email.send();
			 
			 }catch(Exception e)
		 {
				 e.printStackTrace();
		 } */

		  }
}
