package com.oracle.rsys.Base;



import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.oracle.rsys.CommonPages.RsysGlobalSearch;
import com.oracle.rsys.Constants.FrameworkConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {
	public static Properties config;
	public static Properties OR;
	public static WebDriver driver;
	public RsysGlobalSearch globalSearch;
	public static ExtentTest ReportGeneration;

	
	public BasePage() {
		
	}
	


	public BasePage(WebDriver dr, ExtentTest reportGeneration) {
		BasePage.driver= dr;
		BasePage.ReportGeneration=reportGeneration;
		System.out.println("In Base Page");
		globalSearch = PageFactory.initElements(dr, RsysGlobalSearch.class);
	}

	//Click function
	public void fnClick(String xpathkey)
	{
		try {
			driver.findElement(By.xpath(OR.getProperty(xpathkey))).click();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}


	//fnInputText
	public void fnInputText(String xpathkey, String text) {
		try {
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			driver.findElement(By.xpath(OR.getProperty(xpathkey))).sendKeys(text);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}


	//fnExplicitlyWaitUntil visibility of element 
	/*public void fnExplicitlyWaitUntil(String xpathkey) {
		Explicit wait for xpathkey to be visible 
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(OR.getProperty(xpathkey))));   
	}*/


	//fnVerifyTitle - Get the title of the webpage where driver is pointing and return the same
	public String fnVerifyTitle()
	{
		String ActualTitle = driver.getTitle();	
		System.out.println("Title of the webpage is " + ActualTitle);
		return ActualTitle;
	}


	//fnSelectOptionsFromAdministrationDropDown
	public void fnSelectOptionsFromAdministrationDropDown(String xpathkey)
	{
		driver.switchTo().frame("header2");
		driver.findElement(By.xpath("//*[@id='sa']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main2");
		driver.findElement(By.xpath(OR.getProperty(xpathkey))).click();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
	}


	//fnSelectAccountManagementRadioButton
	public void fnSelectAccountManagementRadioButton(String xpathkey) {
		try 
		{
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main2");
			Thread.sleep(3000);
			driver.findElement(By.xpath(OR.getProperty(xpathkey))).click();
			//driver.switchTo().defaultContent();
			//Click on 'Next' button
			driver.findElement(By.xpath("//input[@class='btnSpacing' and @name='Next']")).click();
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		}catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	//fnClickOnHamburgerMenu
	public void fnClickOnHamburgerMenu() {

		try {
			WebElement element = driver.findElement(By.xpath("//span[@id='uifresponsyshamburgerbutton-1597-btnEl' and @data-ref='btnEl']"));
			if (element.isEnabled() && element.isDisplayed()) {
				System.out.println("Trying to click on hamburger menu");
				JavascriptExecutor executor = (JavascriptExecutor)driver;
				executor.executeScript("arguments[0].click();", element);
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document "+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element was not found in DOM "+ e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to click on element "+ e.getStackTrace());
		}
	}
	
	
	
	//Global Search Function 
	public RsysGlobalSearch fnGlobalSearch() {
		System.out.println("Inside fnGlobalSearch in BasePage ");
		return globalSearch;
		
	}
	
	public static void fnTakeScreenshot() {
		
		Date d=new Date();
		String ScreenshotFileName=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String ScreenshotFilePath = FrameworkConstants.SCREENSHOT_PATH + ScreenshotFileName;
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		try
		{
			FileUtils.copyFile(srcFile, new File(ScreenshotFilePath));
			//FileUtils.copyFile(srcFile, new File(".//Screenshots//"+ScreenshotFileName));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		ReportGeneration.log(LogStatus.INFO, ReportGeneration.addScreenCapture(ScreenshotFilePath));
	}
	
}




