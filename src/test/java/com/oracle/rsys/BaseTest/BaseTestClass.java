package com.oracle.rsys.BaseTest;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import com.oracle.rsys.Constants.FrameworkConstants;
import com.oracle.rsys.ExtentReports.ExtentManager;
import com.oracle.rsys.Utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class BaseTestClass {

	public static Properties config;
	public static Properties OR;
	public WebDriver driver;
	


	public ExtentReports extent = ExtentManager.getInstance();
	
	public static ExtentTest  ReportGeneration;

	public static Xls_Reader xls = new Xls_Reader(FrameworkConstants.DATA_XLSX_PATH);

	public BaseTestClass() {
	}

	public BaseTestClass(WebDriver dr, ExtentTest reportGeneration) {
		this.driver = dr;
		BaseTestClass.ReportGeneration=reportGeneration;
	}

	public void fnInit() {	
		/*ChromeOptions options1 = new ChromeOptions();
			options1.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", "D:\\Pavan\\Learn\\Selenium\\Selenium Installables\\chromedriver\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();*/


		if (driver==null)
		{
			config = new Properties();
			OR = new Properties();
			try 
			{
				System.out.println(System.getProperty("user.dir"));
				FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\oracle\\rsys\\ConfigFiles\\config.properties");
				config.load(fs);

				fs = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\oracle\\rsys\\ConfigFiles\\OR.properties");
				OR.load(fs);

			}catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if(!FrameworkConstants.EXECUTE_ON_GRID) {
			
			// run on local machine
			System.out.println(config.getProperty("browser"));

			if(config.getProperty("browser").equalsIgnoreCase("Chrome")){
				//ReportGeneration = extent.startTest("BaseTestClass", "This is inside base test class");
				ReportGeneration.log(LogStatus.INFO, "Browser is chrome ");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--disable-notifications");
				System.setProperty("webdriver.chrome.driver", FrameworkConstants.CHROME_DRIVER_PATH);
				//System.out.println("Before chrome driver launch");
				driver = new ChromeDriver();
				//System.out.println("After chrome driver launch");
				
			}
			else if (config.getProperty("browser").equals("IE"))
			{
				ReportGeneration.log(LogStatus.INFO, "Browser is IE ");
				System.setProperty("webdriver.ie.driver", FrameworkConstants.IE_DRIVER_PATH);
				driver = new InternetExplorerDriver();				
			}
			else 
			{
				ReportGeneration.log(LogStatus.INFO, "Browser is Mozilla "); 
				System.setProperty("webdriver.gecko.driver",FrameworkConstants.MOZILLA_DRIVER_PATH);
				driver = new FirefoxDriver();
			}
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			}
			
			
			else {
				//run on GRID
				DesiredCapabilities cap=null;
				if(config.getProperty("browser").equalsIgnoreCase("Mozilla")){
					System.out.println("Inside desired capabilities object for mozilla");
					ReportGeneration.log(LogStatus.INFO, "Browser is Mozilla on grid"); 
					cap = DesiredCapabilities.firefox();
					cap.setBrowserName("firefox");
					cap.setJavascriptEnabled(true);
					cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
					
				}else if(config.getProperty("browser").equalsIgnoreCase("Chrome")){
					System.out.println("Inside desired capabilities object for chrome");
					ReportGeneration.log(LogStatus.INFO, "Browser is chrome on grid ");
					 cap = DesiredCapabilities.chrome();
					 cap.setBrowserName("chrome");
					 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}
				else {
					ReportGeneration.log(LogStatus.INFO, "Browser is IE on grid");
					System.out.println("Inside desired capabilities object for IE");
					cap = DesiredCapabilities.internetExplorer();
					 cap.setBrowserName("internet explorer");
					 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}
				
				try {
					driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			}
			
		}

	}
	
	
	/*@AfterClass
	public void quit() {
		
		if(extent!= null){
			extent.endTest(ReportGeneration);
			extent.flush();
			//extent.close();
		}
		driver.quit();
		
	}*/



	
}



