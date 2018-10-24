package com.oracle.rsys.Programs;

import org.openqa.selenium.WebDriver;

import com.oracle.rsys.Base.BasePage;
import com.relevantcodes.extentreports.LogStatus;

public class Program_ManageProgramsPage extends BasePage  {
	
	//WebDriver driver;
	
	//ExtentTest ReportGeneration;

	public Program_ManageProgramsPage(WebDriver dr) {
		
		/* I am getting an error "Implicit super constructor BasePage() is undefined. Must explicitly invoke another constructor" . Refer 
		 * https://stackoverflow.com/questions/1197634/java-error-implicit-super-constructor-is-undefined-for-default-constructor*
		 * https://www.programcreek.com/2013/04/what-are-the-frequently-asked-questions-about-constructors-in-java/
		 * ------------The fix was to add a no-arg basepage constructor in basepage class and use super(dr) to call baspage with-arg constructor  
		 */	
			
		
		//super(dr, ReportGeneration);
		//this.driver = dr;
		//this.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Inside ManagePrograms Page --- ");
		System.out.println("- Inside ManagePrograms Page - ");
	}

}
