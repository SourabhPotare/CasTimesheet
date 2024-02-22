package project;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class driverSetup {
public static WebDriver driver;
public static WebDriver getWebDriver;

public static WebDriver getWebdriver() {
	try {
		driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}catch(Exception e){
		System.out.println("Chrome is not found....../n opening Edge browser.....!!");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	return driver;
}
}
