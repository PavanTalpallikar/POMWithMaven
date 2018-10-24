package com.oracle.rsys.TestCases;

import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.rsys.BaseTest.BaseTestClass;
import com.oracle.rsys.CommonPages.LoginPage;
import com.oracle.rsys.Utils.DataProviderUtil;
import com.relevantcodes.extentreports.LogStatus;

public class LoginToQA1Test extends BaseTestClass{

	/*ExtentTest ReportGeneration;
	WebDriver driver;*/
	//String sheetName = "Data";
	String testCaseName="LoginToQA1Test";
	

	public LoginToQA1Test() {

	}
/*
	public LoginToQA1Test(WebDriver dr, ExtentTest reportGeneration) {
		//super(dr, reportGeneration);
		
		//this.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Accessing LoginToQA1Test --- ");
		System.out.println("- Accessing LoginToQA1Test - ");
	}*/


	@DataProvider
	public Object[][] getData(){
		return DataProviderUtil.getData(xls, testCaseName);
	}


	@Test(dataProvider="getData")
	public void fnLoginTest2(Hashtable <String,String> data){

		ReportGeneration = extent.startTest("Starting LoginToQA1Test ", " This is a test to login to QA1 using credentials of excel ");



		if(!DataProviderUtil.isTestCaseExecutable(xls, testCaseName) ) {
			ReportGeneration.log(LogStatus.SKIP, "Skipping LoginToQA1Test executable is false");
			throw new SkipException("Skipping LoginToQA1Test executable is false ");
		}	
		else if (data.get("RunMode").equals("N")) {
			ReportGeneration.log(LogStatus.SKIP, "Skipping LoginToQA1Test data run mode is N");
			throw new SkipException("Skipping LoginToQA1Test data run mode is N");
		}

		fnInit();
		//LoginPage lp = PageFactory.initElements(driver, LoginPage.class); We cant pass the report generation object with this kind of initialization, Hence we will use below way
		LoginPage lp = new LoginPage(driver, ReportGeneration);  // Step 1
		PageFactory.initElements(driver, lp); // Step 2    This is how we pass ExtentTest object to Page classes

		ReportGeneration.log(LogStatus.INFO, "Invoking LoginToQA1Test  and logging in to QA1");
		lp.fnLoginToQA1(data.get("Username"), data.get("Password")); //Here we have used Object variable as login can be a success or a failure


	}
		

	//fnQuit();
	
	@AfterMethod
	public void quit() {
		
		if(extent!= null){
			extent.endTest(ReportGeneration);
			extent.flush();
			
		}
		driver.quit();
		
	}
}



