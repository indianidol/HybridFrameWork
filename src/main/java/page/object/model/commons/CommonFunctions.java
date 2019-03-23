package page.object.model.commons;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonFunctions extends CommonVariables
{
	
	public static String  getdate() {
		Date d = new Date();
		System.out.println(d);
		
		String folderName=d.toString().replace(":", "_").replace(" ", "_");
		
		System.out.println(folderName);
		return folderName;
		
	}

	/*public static String getcurrentdateandhour() {
		Calendar cal = Calendar.getInstance();
		DateFormat sdf = new SimpleDateFormat("MMM_dd_yyyy_HH_mm");
		Date date = new Date();
		return sdf.format(cal.getTime());
		}
*/
	
	   public static void takeSnapShot(String fileWithPath,  WebElement elem) throws Exception{
		   highlight(elem);
	        //Convert web driver object to TakeScreenshot

	        TakesScreenshot scrShot =((TakesScreenshot)driver);

	        //Call getScreenshotAs method to create image file

	                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

	            //Move image file to new destination

	                File DestFile=new File(fileWithPath);

	                //Copy file at destination

	                FileUtils.copyFile(SrcFile, DestFile);

	    }

	   public static void highlight( WebElement elem) throws Exception{
	 //   WebElement elem = 
	    // draw a border around the found element
	    if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", elem);
	        
	        // document.getElementById("iskpiFilterAction").type = "button";
	    }
	   }

}
