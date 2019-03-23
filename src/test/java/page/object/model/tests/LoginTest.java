package page.object.model.tests;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import page.object.model.commons.DataUtil;
import page.object.model.commons.Constants;
public class LoginTest extends TestBase{

	
//	@Test(dataProvider="getData")
	public void VerifyReport(LinkedHashMap<String, String> data) throws Exception {
		
		test.log(Status.INFO, "Starting "+ currentTestCase);

		if(DataUtil.isSkip(currentTestCase, xls) ||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
		test.log(Status.SKIP, "Runmode is set to NO");
		throw new SkipException("Runmode is set to NO");
		}		
	    ds.executeKeywords(currentTestCase, xls, data);
	    
	    
	}

@Test (dataProvider = "getData")
	public void VerifyReport1(LinkedHashMap<String, String> data) throws Exception {

	}

	//@Test(dataProvider = "getData")
	public void VerifyReport2(LinkedHashMap<String, String> data) throws Exception {

	}

//	@Test(dataProvider = "getData")
	public void VerifyReport3(LinkedHashMap<String, String> data) throws Exception {

	}
	
}
	