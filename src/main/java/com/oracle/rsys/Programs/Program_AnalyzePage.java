package com.oracle.rsys.Programs;

import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.PageFactory;

import com.oracle.rsys.Base.BasePage;
import com.oracle.rsys.CommonPages.HomePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Program_AnalyzePage extends BasePage {
	
	//WebDriver driver;
	
	//ExtentTest ReportGeneration;
	public Program_AnalyzePage(WebDriver dr, ExtentTest reportGeneration) {
		
		/* I am getting an error "Implicit super constructor BasePage() is undefined. Must explicitly invoke another constructor" . Refer 
		 * https://stackoverflow.com/questions/1197634/java-error-implicit-super-constructor-is-undefined-for-default-constructor*
		 * https://www.programcreek.com/2013/04/what-are-the-frequently-asked-questions-about-constructors-in-java/
		 * ------------The fix was to add a no-arg basepage constructor in basepage class and use super(dr) to call baspage with-arg constructor  
		 */	
			
		super(dr, reportGeneration);
		//this.driver = dr;
		BasePage.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Inside ProgramAnalyze Page --- ");
		System.out.println("- Inside ProgramAnalyze Page - ");
	}
	
	public HomePage fnVerifyProgramAnalyzeTabStats() {
		ReportGeneration.log(LogStatus.INFO, "--- Checking Stats of the program --- ");
		System.out.println("- Checking Stats of the program - ");
		return null;
		
		
	}

	/*@AfterClass
	public void quit() {
		driver.quit();
	}*/
	

}
