package com.crm.Vtiger.GenericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import com.crm.Vtiger.elementRepsitory.HomePage;
import com.crm.Vtiger.elementRepsitory.LoginPage;

public class BaseClass {
	
	public DataBaseUtility dbLib = new DataBaseUtility();
	public JsonFileUtility jsonLib = new JsonFileUtility();
	public WebDriverUtility wLib = new WebDriverUtility();
	public ExcelUtility eLib = new ExcelUtility();
	public JavaUtility jLib = new JavaUtility();
	public WebDriver driver;
	public static WebDriver staticdriver;
	
	@BeforeSuite (groups = { "SmokeSuites", "RegressionSuites"})
	public void connectToDb() {
		//dbLib.getDataFromMySqlDB(null, 0);
		System.out.println("=======make DB connections========");
	}
	//@Parameters("Browser")
	@BeforeClass (groups = { "SmokeSuites", "RegressionSuites"})
	
	//public void launchBrowser(String BROWSER) throws IOException, ParseException {
	public void launchBrowser() throws IOException, ParseException {
		System.out.println("=======launch browser========");
		String BROWSER = jsonLib.readDataFromJson("browser");
		String URL = jsonLib.readDataFromJson("url");
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver= new ChromeDriver();
			}else{
				driver= new FirefoxDriver();
			}
		wLib.maximiseWindow(driver);
		wLib.waitForPageLoad(driver);
		driver.get(URL);
		staticdriver = driver;
	}
	
	@BeforeMethod (groups = { "SmokeSuites", "RegressionSuites"})
	public void loginToApp() throws IOException, ParseException {
		System.out.println("=========login to app============");
		String USERNAME = jsonLib.readDataFromJson("username");
		String PASSWORD = jsonLib.readDataFromJson("password");
		LoginPage lp = new LoginPage(driver);
		lp.logIn(USERNAME, PASSWORD);
	}
	
	@AfterMethod  (groups = { "SmokeSuites", "RegressionSuites"})
	public void logOutOfApp() {
		System.out.println("========log out from app==========");
		HomePage hp = new HomePage(driver);
		hp.signOut(driver);
	}
	
	@AfterClass  (groups = { "SmokeSuites", "RegressionSuites"})
	public void closeBrowser() {
		System.out.println("============clocse the browser============");
		driver.close();
	}
	
	@AfterSuite (groups = { "SmokeSuites", "RegressionSuites"})
	public void closeTheDB() {
		System.out.println("===========clocse the db===========");
		
	}
	
	public String getScreenShot(String name) throws IOException {
		File srcfile = ((TakesScreenshot)staticdriver).getScreenshotAs(OutputType.FILE);
		String destfile = System.getProperty("user.dir")+"/Screenshots/"+name+".png";
		File finaldest = new File(destfile);
		FileUtils.copyFile(srcfile, finaldest);
		
		return destfile;
	}
}
