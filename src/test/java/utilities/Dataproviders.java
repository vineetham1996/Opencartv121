package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataproviders {
	
	//DataProvider 1
	
	@DataProvider(name="LoginData")
	public String [][] getdata() throws IOException
	{
		String path = ".\\testData\\Logindetails.xlsx";  //taking xl file from tets data and storing in path
		
		ExcelUtility xlutil = new ExcelUtility(path);    //creating object for XLutility-path will initiate
		
		int totalrows=xlutil.getRowCount("Sheet1");   //no of rows 
		int totalcols=xlutil.getCellCount("Sheet1",1); //no of columns (sheetname ,rownumebr)
		
		String logindata[][]= new String[totalrows][totalcols];    //2d array which will store the data
		
		for(int i=1;i<=totalrows;i++)   //row start from 1 because 0 is header
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j);    //passing row and column getting data and storing the data
				                                                           //array index start from 0 -- i-1 =0   (0,1)
			}
		}
		
		return logindata;    //returning two d array
	}
	
	//dataprovider 2
	
	//data provider 3

}
