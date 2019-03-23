package page.object.model.commons;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;




public class CommonVariables {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties configProp;
	public static String currentUrl;
	public static String browser;
	public Readprop rp;

	public BrowserManger bM;
	public static String xlspath;
	public static Xls_Reader xls;	
	public static String testDataSheet;
	
	public static String currentTestCase;
	public static DriverScript ds;
	
	
	EventFiringWebDriver eventHandler;
	MyListener eCapture;
	
	public static String reportpath;
	public static String screenshotFolderPath;
	
	 public static ExtentReports extent;
	public ExtentTest test;
	 public static LinkedHashMap<String, String> data;
	

	public  EventFiringWebDriver edriver;
	public Properties envProp;
	//ublic Properties prop;
	
	public String proceedOnFail;
	public String page;


	public SoftAssert softAssert = new SoftAssert();
}
