package project;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class methods {
WebDriver driver;

@BeforeClass
//get Webdriver
public void getWebDriver() {
	driver = driverSetup.getWebdriver();
	driver.manage().window().maximize();
}


@Test
//get url
public void getUrl() {
	try {
		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		Thread.sleep(15000);
		driver.findElement(By.xpath("//input[@id='idSIButton9']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@type='submit']")).click();
		
	}catch(Exception e) {
	driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
	System.out.println("Enter the url successfully......!!!");
	}
}

@Test(dependsOnMethods = {"getUrl"})
//validate the userDetails
public void getUserDetails() throws InterruptedException {
	
	try{
		Thread.sleep(2000);
		WebElement user = driver.findElement(By.xpath("//div[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']"));
		user.click();
		List<WebElement> userDetails = driver.findElements(By.xpath("//div[@class='mectrl_accountDetails']/div"));
	
		for(int i=0;i<2;i++) {
		System.out.println(userDetails.get(i).getText());
		}
	}catch(Exception e) {
		WebElement user = driver.findElement(By.xpath("//div[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']"));
		user.click();
		List<WebElement> userDetails = driver.findElements(By.xpath("//div[@class='mectrl_accountDetails']/div"));
	
		for(int i=0;i<2;i++) {
		System.out.println(userDetails.get(i).getText());
		}
	}
}

public List<WebElement> userDetails() {
	List<WebElement> userDetails = driver.findElements(By.xpath("//div[@class='mectrl_accountDetails']/div"));
	return userDetails;
}

@Test(dependsOnMethods = {"getUserDetails"})
//check one cognizant is visible or not
public void checkOneCognizant() {
	WebElement oneCognizant = driver.findElement(By.xpath("//div[@title='OneCognizant']"));
	System.out.println("One cognizant is visile: "+ oneCognizant.isDisplayed());
	if(oneCognizant.isDisplayed()) {
		oneCognizant.click();
		System.out.println("Clicked successfully......!!!");
	}
}


//@Parameters({1})
@Test(dependsOnMethods = {"checkOneCognizant"})
//switch driver to new window
public void switchDriver() throws InterruptedException {
	Thread.sleep(2000);
	System.out.println(driver.getTitle());
	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(1));
	System.out.println(driver.getTitle());
	System.out.println("driver switch successfully.....!!!!");
}


@Test(dependsOnMethods = {"switchDriver"})
//set search
public void setSearch() throws InterruptedException, IOException {
	try{
		Thread.sleep(3000);
		driver.findElement(By.xpath("//li[@class='searchTopBar']")).click();
		driver.findElement(By.id("oneCSearchTop")).sendKeys(ExcelData.Input());
		Thread.sleep(3000);
		List<WebElement> searchItems = driver.findElements(By.xpath("//div[@id='newSearchQALST']/div"));
		
		for(int i=0;i<searchItems.size();i++) {
		System.out.println(searchItems.get(i).getText());
		}
		for(int i=0;i<searchItems.size();i++) {
		  if(searchItems.get(i).getText().equalsIgnoreCase("Submit Timesheet")) {
			  searchItems.get(i).click(); 
			  break; 
		  }
	}
	}catch(Exception e) {
		Thread.sleep(2000);
		driver.findElement(By.id("oneCSearchTop")).sendKeys(ExcelData.Input());
		Thread.sleep(2000);
		List<WebElement> searchItems = driver.findElements(By.xpath("//div[@id='newSearchQALST']/div"));
		
		for(int i=0;i<searchItems.size();i++) {
			System.out.println(searchItems.get(i).getText());
		}
		for(int i=0;i<searchItems.size();i++) {
			  if(searchItems.get(i).getText().equalsIgnoreCase("Submit Timesheet")) {
				  searchItems.get(i).click(); 
				  break; 
			  }

		}
	}
}


@Test(dependsOnMethods = {"setSearch"})
public void switchDriver1() throws InterruptedException {
	Thread.sleep(2000);
	System.out.println(driver.getTitle());
	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	driver.switchTo().window(tabs.get(2));
	System.out.println(driver.getTitle());
	System.out.println("driver switch successfully.....!!!!");
	
}

@Test(dependsOnMethods= {"switchDriver1"})
public void datchk() throws ParseException, InterruptedException {
	Thread.sleep(2000);
	List<WebElement> dates= driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]"));
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); 
	for(int i=0;i<3;i++){
		String date = dates.get(i).getText();
		String [] dateparts = date.split(" To ");
		Date startdate = sdf.parse(dateparts[0]);
		System.out.println("Start dates: "+sdf.format(startdate));
		Date enddate = sdf.parse(dateparts[1]);
		System.out.println("end dates: "+sdf.format(enddate));
		Calendar s = Calendar.getInstance();
		s.setTime(startdate);
		Calendar e = Calendar.getInstance();
		e.setTime(enddate);
		if(s.get(Calendar.DAY_OF_WEEK)== Calendar.SATURDAY) {
			if(e.get(Calendar.DAY_OF_WEEK)== Calendar.FRIDAY) {
				System.out.println("Timesheet "+(i+1)+" is valid");
			}
		}
	}
}

@Test(dependsOnMethods = {"switchDriver1"})
//get time sheet details
public void getTimeSheet() throws InterruptedException {
	try{
		Thread.sleep(2000);
		driver.getTitle();
		List<WebElement> sheetDate = driver.findElements(By.xpath("//div[@class='ps_box-scrollarea scroll_area_card']/div//span/a"));
		List<WebElement> sheetStatus = driver.findElements(By.xpath("//*[contains(text(),'Submitted for Approval') or contains(text(),'Approved') or contains(text(),'Pending')]"));
		for(int i=0;i<3;i++) {
			System.out.println(sheetDate.get(i).getText()+" "+sheetStatus.get(i).getText());
		}
		System.out.println("Data written successfully.....!!!");
	}catch(Exception e) {
		driver.getTitle();
		List<WebElement> sheetDate = driver.findElements(By.xpath("//div[@class='ps_box-scrollarea scroll_area_card']/div//span/a"));
		List<WebElement> sheetStatus = driver.findElements(By.xpath("//*[contains(text(),'Submitted for Approval') or contains(text(),'Approved') or contains(text(),'Pending')]"));
		for(int i=0;i<3;i++) {
			System.out.println(sheetDate.get(i).getText()+" "+sheetStatus.get(i).getText());
		}
		System.out.println("Data written successfully.....!!!");
	}
}

public static List<WebElement> TimeSheetDate(WebDriver driver){
	List<WebElement> sheetDate = driver.findElements(By.xpath("//div[@class='ps_box-scrollarea scroll_area_card']/div//span/a"));
	return sheetDate;
}

public static List<WebElement> TimeSheetStatus(WebDriver driver){
	List<WebElement> sheetStatus = driver.findElements(By.xpath("//*[contains(text(),'Submitted for Approval') or contains(text(),'Approved') or contains(text(),'Pending')]"));
	return sheetStatus;
}


@Test(dependsOnMethods = {"getTimeSheet"})
//select from dropdown
public void getdropdown() {
	Select sl = new Select(driver.findElement(By.xpath("//div/select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")));
	sl.selectByVisibleText("Date");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_DATE$prompt']")).click();
	driver.findElement(By.xpath("//a[@id='curdate']")).click();
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
}

@Test(dependsOnMethods = {"getdropdown"})
public void getcurrentDate() throws InterruptedException {
	Thread.sleep(2000);
	List<WebElement> curtDate = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]"));
	List<WebElement> curtStatus = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[2]"));
	for(int i=0;i<curtDate.size();i++) {
		System.out.println(curtDate.get(i).getText()+" "+curtStatus.get(i).getText());
	}
}


@Test(dependsOnMethods = {"getcurrentDate"})
//select status
public void status() throws InterruptedException {
	Thread.sleep(2000);
	Select sl = new Select(driver.findElement(By.xpath("//div/select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']")));
	sl.selectByVisibleText("Status");
	List<WebElement> statusDropdown = driver.findElements(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']/option"));
	for(int i=0;i<statusDropdown.size();i++) {
		System.out.println(statusDropdown.get(i).getText());
	}
	
	
}

@Test(dependsOnMethods = {"status"})
//getDropdownDetails
public void getApprovedDetails() throws InterruptedException {
	Select sl = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	
	//click on approved
	sl.selectByVisibleText("Approved");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	
	List<WebElement> approvedDate = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]"));
	List<WebElement> approvedSts = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[2]"));
	for(int i=0;i<approvedDate.size();i++) {
		System.out.println(approvedDate.get(i).getText());
		if(approvedSts.get(i).getText().matches("Approved")){
			System.out.println("yes it is approved");
		}else {
			System.out.println("its not approved");
		}
		
	}
}

@Test(dependsOnMethods = {"getApprovedDetails"})
public void getOverdueDetails() throws InterruptedException{
	//click on overdue
	Select sl1 = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	sl1.selectByVisibleText("Overdue");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	WebElement overdue = driver.findElement(By.xpath("//div[@id='shortmsg']"));
	System.out.println(overdue.getText());
	if(overdue.getText().equals("No results found.")) {
		System.out.println("No overdue sheet is present");
	}else {
		System.out.println("overdue sheet is present");
	}
	Thread.sleep(1000);
	driver.findElement(By.xpath("//a[@id='#ICOK']")).click();
	System.out.println("Clicked......!!!!");
	
	}	
	
@Test(dependsOnMethods = {"getOverdueDetails"})
	public void getPartiallyApprovedDetails() throws InterruptedException{	
	//Partially Approved
	Select sl2 = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	sl2.selectByVisibleText("Partially Approved");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	WebElement partiallyApp = driver.findElement(By.xpath("//div[@id='shortmsg']"));
	System.out.println(partiallyApp.getText());
	if(partiallyApp.getText().equals("No results found.")) {
		System.out.println("No Partially Approved sheet is present");
	}else {
		System.out.println("Partially Approved sheet is present");
	}
	Thread.sleep(3000);
	driver.findElement(By.xpath("//a[@id='#ICOK']")).click();
	System.out.println("Clicked......!!!!");
	}	
	
@Test(dependsOnMethods = {"getPartiallyApprovedDetails"})
	public void getSavedDetails() throws InterruptedException{	
	//Saved
	Select sl3 = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	sl3.selectByVisibleText("Saved");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	WebElement select = driver.findElement(By.xpath("//div[@id='shortmsg']"));
	System.out.println(select.getText());
	if(select.getText().equals("No results found.")) {
		System.out.println("No Saved sheet is present");
	}else {
		System.out.println("Saved Approved sheet is present");
	}
	Thread.sleep(3000);
	driver.findElement(By.xpath("//a[@id='#ICOK']")).click();
	System.out.println("Clicked......!!!!");
	}
	
@Test(dependsOnMethods = {"getSavedDetails"})
	public void getPendingDetails() throws InterruptedException{	
	//pending
	Select sl4 = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	sl4.selectByVisibleText("Pending");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	
	List<WebElement> pendingDate = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]"));
	List<WebElement> pendingSts = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[2]"));
	for(int i=0;i<pendingDate.size();i++) {
		System.out.println(pendingDate.get(i).getText());
		if(pendingSts.get(i).getText().matches("Pending")){
			System.out.println("yes it is pending");
		}else {
			System.out.println("its not pending");
		}
	}
	Thread.sleep(3000);
	System.out.println("clicked......!!!!!");
	}
	
@Test(dependsOnMethods = {"getPendingDetails"})
	public void getSubmittedDetails() throws InterruptedException{
	//Submitted for Approval
	Select sl5 = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	sl5.selectByVisibleText("Submitted for Approval");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	
	List<WebElement> SADate = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]"));
	List<WebElement> SASts = driver.findElements(By.xpath("//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[2]"));
	for(int i=0;i<SADate.size();i++) {
		System.out.println(SADate.get(i).getText());
		if(SASts.get(i).getText().matches("Submitted for Approval")){
			System.out.println("yet to be approved");
		}else {
			System.out.println("its already approved");
		}
	}
	Thread.sleep(3000);
	System.out.println("clicked......!!!!!");
	
	}	

@Test(dependsOnMethods = {"getSubmittedDetails"})
	public void getSentBackForRevisionDetails() throws InterruptedException{
	//Sent Back for Revision
	Select sl6 = new Select(driver.findElement(By.xpath("//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']")));
	sl6.selectByVisibleText("Sent Back for Revision");
	driver.findElement(By.xpath("//a[@id='CTS_TS_LAND_WRK_SEARCH']")).click();
	Thread.sleep(2000);
	WebElement sentBack = driver.findElement(By.xpath("//div[@id='shortmsg']"));
	System.out.println(sentBack.getText());
	if(sentBack.getText().equals("No results found.")) {
		System.out.println("No Sent Back for Revision sheet is present");
	}else {
		System.out.println("Sent Back for Revision sheet is present");
	}
	Thread.sleep(3000);
	driver.findElement(By.xpath("//a[@id='#ICOK']")).click();
	System.out.println("Clicked......!!!!");
	
}

@AfterClass
//driver close
public void driverClose() {
	driver.quit();
	System.out.println("Programme run successfully...!!!");
}

}
