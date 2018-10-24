package com.oracle.rsys.Programs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.oracle.rsys.Base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Program_ProgramDesignerPage extends BasePage {
	
	//WebDriver driver;
	
	//ExtentTest ReportGeneration;
	public Program_ProgramDesignerPage(WebDriver dr, ExtentTest reportGeneration) {
		super(dr, reportGeneration);
		//this.driver = dr;
		BasePage.ReportGeneration=reportGeneration;
		ReportGeneration.log(LogStatus.INFO, "--- Inside ProgramDesigner Page --- ");
		System.out.println("- Inside ProgramDesigner Page - ");
	}

	
	public Program_PublishPage fnDesignAProgram() {
		System.out.println("-- Desigining a program \n   Validating the Program \n   go to publish tab  -- ");
		ReportGeneration.log(LogStatus.INFO, "--- Designing a program and validating --- ");
		//return new Program_PublishPage(driver);
		Program_PublishPage ppp = new Program_PublishPage(driver, ReportGeneration);
		PageFactory.initElements(driver, ppp);
		return ppp;
		
	}
	
	
	
	
}
