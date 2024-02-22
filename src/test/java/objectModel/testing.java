package objectModel;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import project.ExcelData;
import project.driverSetup;
import project.screenShot;


@Listeners(Listener.ExtendReport.class)
public class testing {
	
	WebDriver driver;
	BeCognizant be;
	OneCognizant oc;
	Timesheet tc;
	
	

	
	  @BeforeClass 
	  void getUrl() { 
		  driver = driverSetup.getWebdriver();
		  driver.manage().window().maximize();
		  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	  
		  driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx"); 
		  System.out.println("Enter the url successfully......!!!");
	  
	  }
	 
	 
	
	@Test (priority=1) 
	 void OneCognizant() throws Exception { 
	
		 be = new BeCognizant(driver);
		 Assert.assertEquals(be.checkOneConizant(), true);
		 
	 }
	
	
	@Test (priority=2)
	 void getUserDetails() throws InterruptedException, IOException {
	
		try {
			screenShot.takeSnapShot(driver, "BeCognizant");
			be.clickUserDetails();
			be.getUserDetails();
			Thread.sleep(2000);
			List<WebElement> userDetails = be.getUserDetails();                    		
			ExcelData.output(userDetails);
		
			for(int i=0;i<2;i++) {
				if(be.getUserDetails().get(i).getText().equals("Potare, Sourabh (Cognizant)")) {
					System.out.println("Passed");;
				}
				else if(be.getUserDetails().get(i).getText().equals("2303887@cognizant.com")) {
					System.out.println("Passed");
				}
				else {
					System.out.println("Failed");
				}
				
			}
			
		}catch(Exception e) {
			Thread.sleep(3000);
			 be.clickUserDetails();
			 be.getUserDetails();
			 Thread.sleep(2000);
			 List<WebElement> userDetails = be.getUserDetails();                    		
			 ExcelData.output(userDetails);
			 
			 for(int i=0;i<2;i++) {
				 if(be.getUserDetails().get(i).getText().equals("Kabra, Himanshu (Cognizant)")) {
					 System.out.println("Passed");;
				 }
				 else if(be.getUserDetails().get(i).getText().equals("2303787@cognizant.com")) {
					 System.out.println("Passed");
				 }
				 else {
					 System.out.println("Failed");
				 
				 }
				
			 }
		}
		
	 }
	
	@Test (priority =3)
	void clickOnecognizant() throws Exception {
		
		be.clickOneCognizant();
		oc = new OneCognizant(driver);
		oc.driverSwitch();
		Assert.assertEquals(driver.getTitle(),"OneCognizant");
		screenShot.takeSnapShot(driver, "OneCognizant");
	}
	

	
	@Test (priority =4)
	void setSearch() throws Exception {
		
		oc.enterTheSearch();
		Thread.sleep(3000);
		oc.searchResult();
		screenShot.takeSnapShot(driver, "SearchResult");
		Thread.sleep(2000);
		tc = new Timesheet(driver);
		tc.driverSwitch1();
		System.out.println(driver.getTitle());
	}
	
	@Test (priority =5)
	void getAllSheet() throws Exception {
		
		tc.getAllSheetDetails();
		screenShot.takeSnapShot(driver, "AllSheet");
		ExcelData.output1("All Sheet");
		Thread.sleep(3000);
		tc.getApprovedSheetDetails();
		screenShot.takeSnapShot(driver, "ApprovedSheet");
		ExcelData.output1("Approved Sheet");
		Thread.sleep(3000);
		tc.getOverdueSheetDetails();
		screenShot.takeSnapShot(driver, "OverDue");
		ExcelData.output2("Overdue Sheet");
		Thread.sleep(3000); 
		tc.getPendingSheetDetails();
		screenShot.takeSnapShot(driver, "Pending");
		ExcelData.output1("Pending Sheet");
		Thread.sleep(3000);
		tc.getSavedSheetDetails();
		screenShot.takeSnapShot(driver, "Saved");
		ExcelData.output2("Saved Sheet");
		Thread.sleep(3000);
		tc.getPartiallyApprovedSheetDetails();
		screenShot.takeSnapShot(driver, "PartiallyApp");
		ExcelData.output2("Partially App. Sheet");
		Thread.sleep(3000);
		tc.getSubmittedSheetDetails();
		screenShot.takeSnapShot(driver, "Submitted");
		ExcelData.output2("Submitted Sheet");
		Thread.sleep(3000);
		tc.getSentBackSheetDetails();
		screenShot.takeSnapShot(driver, "SentBack");
		ExcelData.output2("Sentback Sheet");
		Thread.sleep(3000);
		tc.getCurrentSheetDetails();
		screenShot.takeSnapShot(driver, "CurrentDate");
		Assert.assertEquals(driver.getTitle(),"Timesheet Landing Component");
	}
	
	
	
	  @AfterClass 
	  void close() { 
		  driver.quit(); 
	  }
	 
}