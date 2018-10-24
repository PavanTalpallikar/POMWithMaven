package com.oracle.rsys.Programs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.oracle.rsys.Base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Program_PublishPage extends BasePage {
	
	//WebDriver driver;
	//ExtentTest ReportGeneration;
	public Program_PublishPage(WebDriver dr, ExtentTest reportGeneration) {
		super(dr, reportGeneration);
		//this.driver = dr;
		BasePage.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Inside ProgramPublish Page --- ");
		System.out.println("- Inside ProgramPublish Page - ");
	}

	
	public Program_AnalyzePage fnPublishProgram() {
		System.out.println("-- Publishing the program -- ");
		ReportGeneration.log(LogStatus.INFO, "--- Publishing the program --- ");
		//return new Program_AnalyzePage(driver);
		Program_AnalyzePage pap = new Program_AnalyzePage(driver, ReportGeneration);
		PageFactory.initElements(driver, pap);
		return pap;
	}

		
	
}
