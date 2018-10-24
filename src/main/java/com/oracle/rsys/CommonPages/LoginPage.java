package com.oracle.rsys.CommonPages;

import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.oracle.rsys.Base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class LoginPage extends BasePage  {


	//ExtentReports extent = ExtentManager.getInstance();    If defined this way, we can log in this class as we are invoking explicitly the instance. If we put this line in base test class, logging will work only if BaseTest class is extended. 
	//ReportGeneration = extent.startTest("Login Page");  // This wont work in this class as this class does not extent BaseTest class



	WebDriver driver;

	@FindBy(xpath="//*[@id='txtUserName']")
	public WebElement username;

	@FindBy(xpath="//*[@id='txtPassword']")
	public WebElement password;

	@FindBy(xpath="//button[@id='signIn'and @class='mobile buttonOn']")
	public WebElement signInButton1;

	@FindBy(xpath="//button[@id='signIn'and @onclick='LoginModule.onClickSignIn();")
	public WebElement signInButton2;

	@FindBy(xpath="(//button[@id='signIn'])[3]")
	public WebElement signInButton3;

	@FindBy(xpath="//a[contains(@href,'/interact/account/SubAccountSAFunctions')][text()='Manage Accounts']")
	public WebElement AdministrationDropDown;

	ExtentTest ReportGeneration;

	public LoginPage(WebDriver dr, ExtentTest reportGeneration) {
		/* I am getting an error "Implicit super constructor BasePage() is undefined. Must explicitly invoke another constructor" . Refer 
		 * https://stackoverflow.com/questions/1197634/java-error-implicit-super-constructor-is-undefined-for-default-constructor*
		 * https://www.programcreek.com/2013/04/what-are-the-frequently-asked-questions-about-constructors-in-java/
		 * ------------The fix was to add a no-arg basepage constructor in basepage class and use super(dr) to call baspage with-arg constructor  
		 */	
		super(dr,reportGeneration);
		this.driver = dr;  
		this.ReportGeneration=reportGeneration;
		/* no need to use this keyword here as we have not defined driver variable in this class.
		 *Since driver variable is public to all classes, in super(dr), it gets initialized
		 */
		BasePage.ReportGeneration=reportGeneration;
		//ReportGeneration.log(LogStatus.INFO, "--- Accessing Login Page --- ");
		//System.out.println("- Accessing Login Page - ");
	}



	public HomePage fnLoginToQA1(String un, String pwd) throws NullPointerException {    //- Return type Object because we are returning HomePage if login success else we are returning Login page 
		//public HomePage fnLoginToENG1() {  - Return type HomePage if you are not returning multiple pages
		//driver.manage().window().maximize() ;
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		ReportGeneration.log(LogStatus.INFO, "Invoking QA1 URL");
		System.out.println("Invoking QA1 URL"); 
		//driver.get("https://interact.eng1.responsys.net/authentication/login/LoginPage");
		driver.get("https://interact.qa1.responsys.net/interact/login/LoginPage");
		//driver.get(config.getProperty("testApplicationURL")); Page Obj Model with Page factory is somehow not recognizing config.getProperty
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//username.sendKeys(config.getProperty("Admin_UN"));
		//username.sendKeys("SysAdmin");
		username.sendKeys(un);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		password.sendKeys(pwd);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		ReportGeneration.log(LogStatus.INFO, "--- Entered credentials, now clicking on signin --- ");
		//password.sendKeys(Keys.ENTER);
		signInButton3.click();



		//input[@name="username"] | //input[@id="wm_login-username"]

		//driver.findElement(By.xpath("//button[@id='signIn'and @class='mobile buttonOn'] | //button[@id='signIn'and @class='buttonOn']"));

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		String test1 = driver.getTitle();	
		System.out.println("Title of the qa1 webpage is " + test1);

		/*boolean LoginSuccess=false;

		try {
	        if (AdministrationDropDown.isDisplayed())
	            LoginSuccess = true;
	    } catch (NullPointerException | NoSuchElementException e) {
	        System.err.println("Unable to locate 'AdministrationDropDown'. It does not exist" + e.getStackTrace());
		}*/

		/* Dont use if conditions on webelements, better get title or text of webelement and use it in if. In the above try block, isDisplayed on 
		webelement AdministrationDropDown created unexpected errors. Hence, using the get title value as below   */


		if(!test1.equalsIgnoreCase("Oracle Sign in"))
		{
			ReportGeneration.log(LogStatus.PASS, "Login to QA1 Success");
			System.out.println("Login to QA1 is successful");
			fnTakeScreenshot();
			ReportGeneration.log(LogStatus.INFO, "Screen shot after successful login to QA1 taken");
			//HomePage hp = new HomePage(driver, ReportGeneration);
			//PageFactory.initElements(driver, hp);
			//return hp;
			return null;
		}
		else
		{
			ReportGeneration.log(LogStatus.FAIL, "Login to QA1 Failed");
			fnTakeScreenshot();
			ReportGeneration.log(LogStatus.INFO, "Screen shot after failed login to QA1 taken");
			//return PageFactory.initElements(driver, LoginPage.class);
			return null;
		}


	}



	public Object fnLoginToENG1() {
		System.out.println("----------------- Logging into ENG1 -----------------");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		ReportGeneration.log(LogStatus.INFO, "Invoking ENG1 URL");
		driver.get("https://interact.eng1.responsys.net/interact/login/LoginPage");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		username.sendKeys("SysAdmin");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		password.sendKeys("SysAdmin");
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		//password.sendKeys(Keys.ENTER);
		signInButton3.click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

		String test1 = driver.getTitle();	
		System.out.println("Title of the eng1 webpage is " + test1);




		if(!test1.equalsIgnoreCase("Oracle Sign in"))
		{
			ReportGeneration.log(LogStatus.PASS, "Login Success");
			fnTakeScreenshot();
			ReportGeneration.log(LogStatus.INFO, "Screen shot after successful login taken");
			HomePage hp = new HomePage(driver, ReportGeneration);
			PageFactory.initElements(driver, hp);
			return hp;
		}
		else
		{
			ReportGeneration.log(LogStatus.FAIL, "Login Failed");
			fnTakeScreenshot();
			ReportGeneration.log(LogStatus.INFO, "Screen shot after failed login taken");
			return PageFactory.initElements(driver, LoginPage.class);
		}


	}
	
	
	


}
