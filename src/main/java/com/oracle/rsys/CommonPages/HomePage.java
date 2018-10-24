package com.oracle.rsys.CommonPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.oracle.rsys.Base.BasePage;
import com.oracle.rsys.Programs.Program_CreateProgramPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BasePage {
	//WebDriver driver;
	
	//ExtentTest ReportGeneration;
	public HomePage(WebDriver dr, ExtentTest reportGeneration) {
		/* I am getting an error "Implicit super constructor BasePage() is undefined. Must explicitly invoke another constructor" . Refer 
		 * https://stackoverflow.com/questions/1197634/java-error-implicit-super-constructor-is-undefined-for-default-constructor*
		 * https://www.programcreek.com/2013/04/what-are-the-frequently-asked-questions-about-constructors-in-java/
		 * ------------The fix was to add a no-arg basepage constructor in basepage class and use super(dr) to call baspage with-arg constructor  
		 */	
		super(dr, reportGeneration);
		//this.driver = dr;
		BasePage.ReportGeneration=reportGeneration;
		System.out.println("- Inside Home Page - ");
	}	
	
	public FoldersPage fnGoToFolders() {
		ReportGeneration.log(LogStatus.INFO, "--- Clicking on Folders --- " );
		System.out.println("- Clicking on Folders - ");
		FoldersPage fp = new FoldersPage(driver, ReportGeneration);
		PageFactory.initElements(driver, fp);
		return fp;
	}
	
/*	public Program_CreateProgramPage fnClickOnCreateProgram() {
		System.out.println("- Clicking on Programs in Hamburger Menu options - ");
		System.out.println("- Clicking on Create Program link- ");
		return new Program_CreateProgramPage(driver);
	}
*/

	public Program_CreateProgramPage fnClickOnCreateProgram() {
		ReportGeneration.log(LogStatus.INFO, "--- Clicking on Programs in Hamburger Menu options --- " );
		System.out.println("- Clicking on Programs in Hamburger Menu options - ");
		ReportGeneration.log(LogStatus.INFO, "--- Clicking on Create Program link --- " );
		System.out.println("- Clicking on Create Program link - ");
		//return new Program_CreateProgramPage(driver);
		Program_CreateProgramPage cpp = new Program_CreateProgramPage(driver, ReportGeneration);
		PageFactory.initElements(driver, cpp);
		return cpp;
	}
	
	
	
}
