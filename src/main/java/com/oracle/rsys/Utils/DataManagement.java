package com.oracle.rsys.Utils;

import com.oracle.rsys.Constants.FrameworkConstants;

public class DataManagement {
	
	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader(FrameworkConstants.DATA_XLSX_PATH); // Declare this in base test so that it is available for all test cases.
		String sheetName = FrameworkConstants.TEST_DATA_SHEET;
		String testCaseName="UnpublishProgram";
		
		
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
		
		int dataRow=0;
		//read the data
		Object[][] data = new Object[rows][columns];
		for(int rNum = dataStartRowNum;rNum<dataStartRowNum+rows;rNum++) {
			for(int cNum=0;cNum<columns;cNum++) {
				data[dataRow][cNum] = xls.getCellData(sheetName, cNum, rNum);
				
			}
			
		}
		dataRow++;
		//System.out.println(data);  //you can print array like this.
	}
		
		
}