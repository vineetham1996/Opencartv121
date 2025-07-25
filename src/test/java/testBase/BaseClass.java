package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties p;

    @BeforeClass(groups = {"Sanity", "Regression", "Master"})
    @Parameters({"os", "browser"})
    public void setup(String os, String br) throws IOException {

        // Load config.properties
        FileReader file = new FileReader("./src//test//resources//config.properties");
        p = new Properties();
        p.load(file);

        logger = LogManager.getLogger(this.getClass());

        
        
        String executionEnv = p.getProperty("execution_env", "local").toLowerCase().trim();

        if (executionEnv.equals("remote")) {
            // Grid setup
            try {
                switch (br.toLowerCase()) {
                    case "chrome": {
                        ChromeOptions options = new ChromeOptions();
                        if (os.equalsIgnoreCase("windows")) options.setPlatformName("windows");
                        else if (os.equalsIgnoreCase("mac")) options.setPlatformName("mac");
                        else if (os.equalsIgnoreCase("linux")) options.setPlatformName("linux");
                        else throw new RuntimeException("Unsupported OS: " + os);

                        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), options);
                        break;
                    }
                    case "firefox": {
                        FirefoxOptions options = new FirefoxOptions();
                        if (os.equalsIgnoreCase("windows")) options.setPlatformName("windows");
                        else if (os.equalsIgnoreCase("mac")) options.setPlatformName("mac");
                        else if (os.equalsIgnoreCase("linux")) options.setPlatformName("linux");
                        else throw new RuntimeException("Unsupported OS: " + os);

                        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), options);
                        break;
                    }
                    case "edge": {
                        EdgeOptions options = new EdgeOptions();
                        if (os.equalsIgnoreCase("windows")) options.setPlatformName("windows");
                        else if (os.equalsIgnoreCase("mac")) options.setPlatformName("mac");
                        else if (os.equalsIgnoreCase("linux")) options.setPlatformName("linux");
                        else throw new RuntimeException("Unsupported OS: " + os);

                        driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), options);
                        break;
                    }
                    default:
                        throw new RuntimeException("Unsupported browser for remote: " + br);
                }

                logger.info("Remote WebDriver initialized: " + br + " on " + os);

            } catch (Exception e) {
                logger.error("Failed to initialize remote WebDriver", e);
                throw new RuntimeException(e);
            }

        } else if (executionEnv.equals("local")) {
            // Local setup
            switch (br.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    driver = new EdgeDriver();
                    break;
                default:
                    throw new RuntimeException("Unsupported browser for local: " + br);
            }

            logger.info("Local WebDriver initialized: " + br);

        } else {
            throw new RuntimeException("Invalid execution_env in config: " + executionEnv);
        }

        if (driver == null) {
            throw new RuntimeException("WebDriver is null. Setup failed.");
        }
        
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(p.getProperty("appURL"));
        logger.info("Navigated to: " + p.getProperty("appURL"));
        
        try {
            Thread.sleep(20000); // 👈 Wait for 20 seconds so session is visible in Grid UI
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

       

    @AfterClass(groups = {"Sanity", "Regression", "Master"})
    public void teardown() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver quit successfully");
        }
    }

    public String captureScreen(String tname) throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddhhss").format(new Date());
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String targetFilePath = System.getProperty("user.dir") + "\\screenshot\\" + tname + "_" + timeStamp + ".png";
        File targetFile = new File(targetFilePath);
        sourceFile.renameTo(targetFile);
        return targetFilePath;
    }
}
