package project;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class screenShot {
	
	
	public static void takeSnapShot(WebDriver driver, String name) throws Exception{
		try{
			TakesScreenshot scrShot =(TakesScreenshot)driver;
			File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
			File DestFile = new File(System.getProperty("user.dir")+"\\screenShot\\"+ name +".png");
			FileUtils.copyFile(SrcFile, DestFile);
			System.out.println("ScreenShot take successfully.........!!!!");
		}catch(Exception e) {
			System.out.println("cannot take ss.....!!!!!");
		}
	}

}
