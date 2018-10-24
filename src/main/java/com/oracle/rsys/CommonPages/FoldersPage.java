package com.oracle.rsys.CommonPages;

import org.openqa.selenium.WebDriver;

import com.oracle.rsys.Base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FoldersPage extends BasePage  {
	
	//WebDriver driver;
	
	
	//ExtentTest ReportGeneration;
	
	public FoldersPage(WebDriver dr, ExtentTest reportGeneration) {
/* I am getting an error "Implicit super constructor BasePage() is undefined. Must explicitly invoke another constructor" . Refer 
 * https://stackoverflow.com/questions/1197634/java-error-implicit-super-constructor-is-undefined-for-default-constructor*
 * https://www.programcreek.com/2013/04/what-are-the-frequently-asked-questions-about-constructors-in-java/
 */	
		super(dr, reportGeneration);
		BasePage.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Inside Folders Page ---");
		System.out.println("- Inside Folders Page - ");
	}
	
	
	public void fnClickOnPrograms() {
		ReportGeneration.log(LogStatus.INFO, "--- Clicking on program ---");
		System.out.println("- Clicking on program - ");
	}

}
