package com.oracle.rsys.TestCases;

import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import com.oracle.rsys.BaseTest.BaseTestClass;
import com.oracle.rsys.CommonPages.HomePage;
import com.oracle.rsys.CommonPages.LoginPage;
import com.oracle.rsys.Programs.Program_AnalyzePage;
import com.oracle.rsys.Programs.Program_CreateProgramPage;
import com.oracle.rsys.Programs.Program_ProgramDesignerPage;
import com.oracle.rsys.Programs.Program_PublishPage;
import com.oracle.rsys.Utils.DataProviderUtil;
import com.relevantcodes.extentreports.LogStatus;


public class CreateAProgramAndPublishTest extends BaseTestClass{


	//String sheetName = "Data";
	String testCaseName="CreateAProgramAndPublishTest";


	public CreateAProgramAndPublishTest() {

	}

	/*public CreateAProgramAndPublishTest(WebDriver dr, ExtentTest reportGeneration) {
		//super(dr, reportGeneration);
		//this.driver2=dr;
		//this.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "Invoking from CreateAProgramAndPublish test  and logging in");
		// TODO Auto-generated constructor stub
	}*/

	@Test
	public void fnSampleProgramPublishTest(){

		/*ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "D:\\Pavan\\Learn\\Selenium\\Selenium Installables\\chromedriver\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();*/
		//ExtentReports extent = ExtentManager.getInstance();   // Initializing the extent reports so that we can see the report when this test runs.
		//ExtentTest test = extent.startTest("CreateAProgramAndPublish", "This is a test to create a new program and publish");
		ReportGeneration = extent.startTest("CreateAProgramAndPublishTest", "This is a test to create a new program and publish");

		if(!DataProviderUtil.isTestCaseExecutable(xls, testCaseName)) {
			ReportGeneration.log(LogStatus.SKIP, "Skipping CreateAProgramAndPublishTest as executable is false");
			throw new SkipException("Skipping CreateAProgramAndPublishTest as executable is false");
		}	



		fnInit();
		//LoginPage lp = PageFactory.initElements(driver, LoginPage.class); We cant pass the report generation object with this kind of initialization, Hence we will use below way
		LoginPage lp2 = new LoginPage(driver, ReportGeneration);  // Step 1
		PageFactory.initElements(driver, lp2); // Step 2    This is how we pass ExtentTest object to Page classes



		Object page = lp2.fnLoginToENG1(); //Here we have used Object variable as login can be a success or a failure


		if (page instanceof LoginPage)
		{
			AssertJUnit.fail("Login Failed because password is not correct");
			System.out.println("Login Failed and hence printing");
		}

		HomePage hp = (HomePage) page;

		hp = new HomePage(driver, ReportGeneration); 
		PageFactory.initElements(driver, hp);


		Program_CreateProgramPage cpp = hp.fnClickOnCreateProgram();


		//Program_CreateProgramPage cpp = PageFactory.initElements(driver, Program_CreateProgramPage.class);
		Program_ProgramDesignerPage pdp =  cpp.fnEnterDetailsforNewProgram();

		//Program_ProgramDesignerPage pdp =  PageFactory.initElements(driver, Program_ProgramDesignerPage.class);
		Program_PublishPage ppp = pdp.fnDesignAProgram();
		ppp.globalSearch.fnSearchForProgramsWithText("Test");  //Avoid this way of coding
		ppp.fnGlobalSearch().fnSearchForProgramsWithText("P_Sanity_SimpleSF");  // This is a standard way of coding
		//Program_PublishPage ppp = PageFactory.initElements(driver, Program_PublishPage.class);
		Program_AnalyzePage pap = ppp.fnPublishProgram();
		pap.fnVerifyProgramAnalyzeTabStats();

		System.out.println("Back to CreateAProgramAndPublishTest class--");

		
		
		
	}	

	
	@AfterMethod
	public void quit() {
		
		if(extent!= null){
			extent.endTest(ReportGeneration);
			extent.flush();
			
		}
		driver.quit();
		
	}
	//fnQuit();



}
