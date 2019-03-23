package page.object.model.commons;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;

import page.object.model.keywords.AppKeywords;

	

public class DriverScript extends CommonVariables{

	AppKeywords app;
	
	public void executeKeywords		(String testName, Xls_Reader xls, LinkedHashMap<String, String> data2) throws Exception{
		int rows = xls.getRowCount(Constants.KEYWORDS_SHEET);
		System.out.println("Rows "+ rows);
	app = new AppKeywords();
	

		// send prop to keywords class
		app.setEnvProp(envProp);	
		// send the data
		app.setData(data2);
		app.setExtentTest(test);
		for(int rNum=2;rNum<=rows;rNum++){
		String tcid = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rNum);
			if(tcid.equalsIgnoreCase(testName)){
				String keyword = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COL, rNum);
				String objectKey = xls.getCellData(Constants.KEYWORDS_SHEET, Constants.OBJECT_COL, rNum);
				String dataKey= xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COL, rNum);
				String proceedOnFail=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.PROCEED_COL, rNum);
				String page=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.PAGE, rNum);
				String data = data2.get(dataKey);
				//System.out.println(tcid +" --- "+ keyword+" --- "+ prop.getProperty(objectKey)+" --- "+ data);
				//test.log(Status.INFO, tcid +" --- "+ keyword+" --- "+ prop.getProperty(objectKey)+" --- "+ data);
			
				
				app.setProceedOnFail(proceedOnFail);
				app.setPage(page);
				// Reflections Api
				Method method;
				method = app.getClass().getMethod(keyword);
				method.invoke(app);
					
				
			}
		}
		app.assertAll();

		
	}


	public Properties getEnvProp() {
		return envProp;
	}


	public void setEnvProp(Properties envProp) {
		this.envProp = envProp;
	}


	
	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}
	
	public void quit(){
		if(app!=null)
		app.quit();
	}
	
	
	
}
