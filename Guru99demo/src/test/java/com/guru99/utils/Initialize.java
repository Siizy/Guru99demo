package com.guru99.utils;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Initialize {

/** @author Chandan Gupta
 * This class holds all the methods related to initialization of the future tests
 */
	
	public static WebDriver driver;
	public static Logger log = Logger.getLogger("selenium");
	public static Properties Config = null;
	public static ExtentReports report;
	public static ExtentTest logger;
	public static String TestName ;
	
@BeforeSuite
public void beforeSuite(){
	report = new ExtentReports("C:\\Users\\Mamta\\Documents\\CG\\Letsjava\\Guru99demo\\src\\test\\resources\\Reports\\MyExtentReport.html", true);
	log.info(this.getClass().getSimpleName() + "is invoked");
	readfile();		
}
	
 @BeforeMethod
 public void beforeMethod(Method method) {
	 logger = report.startTest(this.getClass().getSimpleName()+" :: " + method.getName());
	 launchBrowser(Config.getProperty("Browser").toLowerCase());
	 logger.assignAuthor("Chandan Gupta");	 
	 logger.assignCategory("smoke-test_2019.01-UAT");
 }
 
 @AfterMethod
 public void afterMethod(Method method) {
	 driver.close();
	 logger.log(LogStatus.INFO, "Test"+method.getName()+" Ended");
 }
 
 @AfterSuite
 public void afterSuite(){
	 report.flush();	
 	log.info("Test Executions ended");	
 }
 
 private static void launchBrowser(String Browser) {
	 
	 switch(Browser) {
		
		 case "chrome":
			 System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Drivers\\chromedriver.exe");
			 driver = new ChromeDriver();
			 logger.log(LogStatus.INFO, "Chrome is launched");
			 log.info("");
			 break;
		 case "firefox":
			 System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Drivers\\geckodriver.exe");
			 driver = new FirefoxDriver();
			 break;
		 case "ie":
			 System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\Drivers\\IEDriverServer.exe");
			 driver = new InternetExplorerDriver();
			 break;
		 
	 }	 
 }
 
 public static void setImplicitWait() {
	 
 }
 
 public static void readfile() {
	 Config = new Properties();
		try {
			FileInputStream fs = new FileInputStream("src\\main\\resources\\properties\\config.properties");
			Config.load(fs);
			log.info("config file was successfully loaded");
		} catch (Exception e) {
			log.error(e);
			logger.log(LogStatus.INFO , e);
		} 
 }
 
}
