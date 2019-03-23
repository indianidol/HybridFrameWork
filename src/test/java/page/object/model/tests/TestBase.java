package page.object.model.tests;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import page.object.model.commons.BrowserManger;
import page.object.model.commons.CommonVariables;
import page.object.model.commons.DriverScript;
import page.object.model.commons.ExtentManager;
import page.object.model.commons.Readprop;
import page.object.model.commons.Xls_Reader;



public class TestBase extends CommonVariables {

	@BeforeSuite
	public void initSuite() {
		rp = new Readprop();
		rp.getproperties();
		xls = new Xls_Reader(xlspath);
		extent = ExtentManager.getInstance(reportpath);
	}
	
	

	@BeforeMethod
	public void initTest(Method method) {		
	//	bM = new BrowserManger();
		currentTestCase= method.getName();
		
		test = extent.createTest(currentTestCase);
		 ds=new DriverScript();
		
	}

	@AfterMethod
	public void quit() throws IOException {
		if(driver!=null)
		driver.quit();
		Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
		
		if(extent!=null)
			extent.flush();
	
	}

	
	
	
	@DataProvider(name = "getData")	
	public static Object[][] getDatafrom1(Method m){
	return	getTestData(m.getName(),xls);
		
	}
	public static Object[][] getTestData(String testName, Xls_Reader xls){
		// find the row Number of the testcase
		int testStartRowNum=1;
		System.out.println(xls.getCellData("Data", 0, testStartRowNum));
		while(!xls.getCellData("Data", 0, testStartRowNum).equalsIgnoreCase(testName)){
			testStartRowNum++;
		}
		System.out.println("Row Number of test is "+testStartRowNum );
		// find total cols in testcase
		int colStartRowNum = testStartRowNum+1;
		int totalCols=0;
		while(!xls.getCellData("Data", totalCols, colStartRowNum).equals("")){
			totalCols++;
		}
		System.out.println("Total Cols - "+totalCols );
		// find total rows in testcase
		int dataStartRowNumber=testStartRowNum+2;
		int totalRows=0;
		while(!xls.getCellData("Data", 0, dataStartRowNumber).equals("")){
			totalRows++;
			dataStartRowNumber++;
		}
		System.out.println("Total Rows "+ totalRows);
		
		// read the data
		dataStartRowNumber=testStartRowNum+2;
		LinkedHashMap<String,String> linkedHmap=null;
		int finalRows = dataStartRowNumber+totalRows;
		Object[][] myData = new Object[totalRows][1];
		int i=0;
		for(int rNum=dataStartRowNumber;rNum<finalRows;rNum++){
			linkedHmap = new LinkedHashMap<String,String>();
			for(int cNum=0;cNum<totalCols;cNum++){// put data in hashtable
				String data = xls.getCellData("Data", cNum, rNum);
				String key = xls.getCellData("Data", cNum, colStartRowNum);
				System.out.println("Key " +key+" --- "+"  Data "+data);
				linkedHmap.put(key, data);
			}
			System.out.println(linkedHmap);
			myData[i][0]=linkedHmap;
			i++;
			System.out.println("----------------");
		}
		
	return myData;
	
	}
	public static boolean isSkip(String testName, Xls_Reader xls){
		int rows = xls.getRowCount("TestCases");
		for(int rNum=2;rNum<=rows;rNum++){
			String tcid = xls.getCellData("TestCases", "TCID", rNum);
			if(tcid.equalsIgnoreCase(testName)){// test is found
				String runmode = xls.getCellData("TestCases", "Runmode", rNum);
				if(runmode.equals("N"))
					return true;
				else
					return false;
			}
		}
		
		return true;
		
	}
}
