package readtestdata.kvp.org;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Utilities {
	
	
	public static void capturescreen(WebDriver driver)
	{
		try {
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);           

			 // now copy the  screenshot to desired location using copyFile method
			  
			 FileUtils.copyFile(src, new File("D:/Screenshots"+System.currentTimeMillis()+".png"));                        
			 
			 System.out.println("Screen Shot captured with error message:");
		} catch (Exception e) {
			
			System.out.println("Exception while getting screenshot:"+e.getMessage());
		
	}
	}
}


