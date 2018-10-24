package com.oracle.rsys.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleChromeDriverLaunchcheck {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "D:\\Pavan\\Learn\\Selenium\\Selenium Installables\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.urbandictionary.com/");

	}

}
