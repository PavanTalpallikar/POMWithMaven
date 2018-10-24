package com.oracle.rsys.TestCases;

import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.rsys.Base.BasePage;
import com.oracle.rsys.BaseTest.BaseTestClass;
import com.oracle.rsys.Constants.FrameworkConstants;
import com.oracle.rsys.Utils.Xls_Reader;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTestClass {
	
	
	//ExtentReports extent = ExtentManager.getInstance();    If defined this way, we can log in this class as we are invoking explicitly the instance. If we put this line in base test class, logging will work only if BaseTest class is extended. 
		//ReportGeneration = extent.startTest("Login Page");  

		
		//WebDriver driver;

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
		
		public LoginTest() {

		}
		
		public LoginTest(WebDriver dr, ExtentTest reportGeneration) {
			/* I am getting an error "Implicit super constructor BasePage() is undefined. Must explicitly invoke another constructor" . Refer 
			 * https://stackoverflow.com/questions/1197634/java-error-implicit-super-constructor-is-undefined-for-default-constructor*
			 * https://www.programcreek.com/2013/04/what-are-the-frequently-asked-questions-about-constructors-in-java/
			 * ------------The fix was to add a no-arg basepage constructor in basepage class and use super(dr) to call baspage with-arg constructor  
			 */	
			super(dr, reportGeneration);
			//this.driver = dr;   
			/* no need to use this keyword here as we have not defined driver variable in this class.
			 *Since driver variable is public to all classes, in super(dr), it gets initialized
			 */
			this.ReportGeneration=reportGeneration;
			ReportGeneration.log(LogStatus.INFO, "--- Accessing Login Test case --- ");
			System.out.println("- Accessing Login Test case - ");
		}


		
		
		@DataProvider
		public Object[][] getData(){
		
			xls = new Xls_Reader(FrameworkConstants.DATA_XLSX_PATH);
			String sheetName = "Data";
			String testCaseName="LoginTest";
			
			
			int testStartRowNum = 1;
			while(!xls.getCellData(sheetName, 0, testStartRowNum).equalsIgnoreCase(testCaseName)) {
				testStartRowNum++;
			}
			
			System.out.println("Test starts at row :"+ testStartRowNum);
			int colStartRowNum = testStartRowNum+1;   // Columns start after the testStartRowNum
			int dataStartRowNum = testStartRowNum+2;  // Data starts after the column names, hence its testStartRowNum + 2
			
			int rows=0;
			//calculate the total rows of data
			//Have a while loop to get data from dataStartRowNum until there is a blank value
			while(!xls.getCellData(sheetName, 0, dataStartRowNum+rows).equals("")) {    //columname here is zero bcoz our test case name resides in first column only
				rows++;	
			}
			
			System.out.println("Total number of rows this test case has is : " + rows);
			
			
			//calculate the total columns of data
			int columns=0;
			while(!xls.getCellData(sheetName, columns, colStartRowNum).equals("")) {
				columns++;	
			}
			
			System.out.println("Total number of columns this test case has is : " + columns);
			
			
			Object[][] data = new Object[rows][1];
			int dataRow=0;
			//read the data
			Hashtable<String,String> table = null; 
			for(int rNum = dataStartRowNum;rNum<dataStartRowNum+rows;rNum++) {
				table = new Hashtable<String,String>();
				for(int cNum=0;cNum<columns;cNum++) {
					//data[dataRow][cNum] = xls.getCellData(sheetName, cNum, rNum);	
					String key = xls.getCellData(sheetName, cNum, colStartRowNum);	
					String value = xls.getCellData(sheetName, cNum, rNum);	
					table.put(key, value);
					
				}
				data[dataRow][0] = table;  //column is zero bcoz hashtable has only 1 column
				dataRow++;
			}
			//System.out.println(data); //you can print array like this.
			return data;  // dont return 2 d array as number of arguments can vary and also its cumbersome to define all arguments in receiving function like 
						  // republishProgram function in this case.
		}
			
			
		
		
		
		@Test(dataProvider="getData")  
		public void fnLoginToENG1(Hashtable <String,String> data) throws NullPointerException {
			
			 
			ReportGeneration = extent.startTest("Login Test case starting");//- Return type Object because we are returning HomePage if login success else we are returning Login page 
			//public HomePage fnLoginToENG1() {  - Return type HomePage if you are not returning multiple pages
			//driver.manage().window().maximize() ;
			if(!data.get("RunMode").equals("Y")) {
				//ReportGeneration.log(LogStatus.SKIP, "Skipping the test as run mode is N");
				throw new SkipException("Skipping login test as run mode is N ");
			} 

			
			fnInit();
			LoginTest lt = new LoginTest(driver, ReportGeneration);  // Step 1
			PageFactory.initElements(driver, lt); // Step 2    This is how we pass ExtentTest object to Page classes
			
			
			
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			//ReportGeneration.log(LogStatus.INFO, "Invoking URL");
			driver.get("https://interact.eng1.responsys.net/authentication/login/LoginPage");
			//driver.get(config.getProperty("testApplicationURL")); Page Obj Model with Page factory is somehow not recognizing config.getProperty
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			//username.sendKeys(config.getProperty("Admin_UN"));
			//username.sendKeys("SysAdmin");
			System.out.println("Before User name------");
			username.sendKeys(data.get("Username"));
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			password.sendKeys(data.get("Password"));
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			//password.sendKeys(Keys.ENTER);
			signInButton3.click();
			
			
			
			//input[@name="username"] | //input[@id="wm_login-username"]

			//driver.findElement(By.xpath("//button[@id='signIn'and @class='mobile buttonOn'] | //button[@id='signIn'and @class='buttonOn']"));

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			String test1 = driver.getTitle();	
			System.out.println("Title of the webpage is " + test1);

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
				//ReportGeneration.log(LogStatus.PASS, "Login Success");
				BasePage.fnTakeScreenshot();
				//ReportGeneration.log(LogStatus.INFO, "Screen shot after successful login taken");
				//HomePage hp = new HomePage(driver, ReportGeneration);
				//PageFactory.initElements(driver, hp);
				//return hp;
			}
			else
			{
				ReportGeneration.log(LogStatus.FAIL, "Login Failed");
				BasePage.fnTakeScreenshot();
				ReportGeneration.log(LogStatus.INFO, "Screen shot after failed login taken");
				//return PageFactory.initElements(driver, LoginPage.class);
			}
			
			
		}
		

	}

	



	

