package com.oracle.rsys.Programs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.oracle.rsys.Base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Program_CreateProgramPage extends BasePage  {
	
	//WebDriver driver;
	
	//ExtentTest ReportGeneration;

	public Program_CreateProgramPage(WebDriver dr, ExtentTest reportGeneration) {
		super(dr, reportGeneration);
		//this.driver = dr;
		BasePage.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Inside CreateProgram Page --- ");
		System.out.println("- Inside CreateProgram Page - ");
	}
	

	public Program_ProgramDesignerPage fnEnterDetailsforNewProgram() {
			System.out.println("-- Enter Program Name \n " + "  Select Folder for program \n "+  "  Select List \n " + "  Click on 'Create' button --\n");
			//return new Program_ProgramDesignerPage(driver);
			ReportGeneration.log(LogStatus.INFO, "--- Created a new program, now going to designer page --- ");
			Program_ProgramDesignerPage pdp = new Program_ProgramDesignerPage(driver, ReportGeneration);
			PageFactory.initElements(driver, pdp);
			return pdp;
			
	}
	

	
}
