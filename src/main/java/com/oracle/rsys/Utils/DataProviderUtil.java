package com.oracle.rsys.Utils;

import java.util.Hashtable;

import com.oracle.rsys.Constants.FrameworkConstants;

public class DataProviderUtil {

	public static Object[][] getData(Xls_Reader xls, String testCaseName) {

		xls = new Xls_Reader(FrameworkConstants.DATA_XLSX_PATH);
		String sheetName = "Data";
		//testCaseName="LoginTest";


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
				System.out.println("Key is : " + key);
				String value = xls.getCellData(sheetName, cNum, rNum);	
				System.out.println("Value is : " + value);
				table.put(key, value);
				
			}
			data[dataRow][0] = table;//column is zero bcoz hashtable has only 1 column
			dataRow++;
		}
		//System.out.println(data); //you can print array like this.
		return data; 

	}


	public static boolean isTestCaseExecutable(Xls_Reader xls, String testCaseName) {
		int rows = xls.getRowCount(FrameworkConstants.TESTCASES_SHEET);
		String RunModes;
		for (int i = 2; i<rows; i++) {
			String TCID = xls.getCellData(FrameworkConstants.TESTCASES_SHEET, 0, i);
			if(TCID.equals(testCaseName)) {
				RunModes = xls.getCellData(FrameworkConstants.TESTCASES_SHEET, "RunModes", i); 
				if(RunModes.equals("Y"))
					return true;
				else
					return false;
			}
		}
		return true;
		
				
	}

}
