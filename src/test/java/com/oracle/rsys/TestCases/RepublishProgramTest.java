package com.oracle.rsys.TestCases;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oracle.rsys.BaseTest.BaseTestClass;
import com.relevantcodes.extentreports.ExtentTest;

public class RepublishProgramTest extends BaseTestClass{
	
	public RepublishProgramTest(WebDriver dr, ExtentTest reportGeneration) {
		super(dr, reportGeneration);
		// TODO Auto-generated constructor stub
	}



	@Test(dataProvider="getData")
	//public void republishProgram(String RunMode, String FolderName, String ProgramName) {  //this is too many parameters. Hence do not use 2 dimensional
																						   //array to return. Instead use hashtable or hashmap 
	
	public void republishProgram(Hashtable <String,String> data) {
		System.out.println(data.get("RunMode"));
	
	}

	
	
	@DataProvider
	public void getData(){
	
/*		Xls_Reader xls = new Xls_Reader("D:\\Pavan\\Learn\\Selenium\\MySeleniumWorkspace\\com.oracle.rsys.POM_PageFactory_RI\\src\\main\\java\\com\\oracle\\responsys\\Utils\\RsysData.xlsx");
		String sheetName = "Data";
		String testCaseName="RepublishProgram";
		
		
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
		
*/		
}
}

