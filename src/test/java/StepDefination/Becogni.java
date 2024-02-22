package StepDefination;

import java.io.IOException;
import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import objectModel.BeCognizant;
import objectModel.OneCognizant;
import objectModel.Timesheet;
import project.driverSetup;

public class Becogni {
	WebDriver driver;
	BeCognizant be;
	OneCognizant oc;
	Timesheet tc;
	
	@Given("start the browser")
	public void start_the_browser() {
		driver = driverSetup.getWebdriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://cognizantonline.sharepoint.com/sites/Be.Cognizant/SitePages/Home.aspx");
		
	}

	@When("one cognizant is visible")
	public void one_cognizant_is_visible() throws InterruptedException {
		be = new BeCognizant(driver);
		be.checkOneConizant();
		System.out.println(driver.getTitle());
	   //Assert.assertEquals(driver.getTitle(), "BeCognizant");
	}

	@Then("get the user details")
	public void get_the_user_details() {
		
	  try {
		  be.clickUserDetails();
		  be.getUserDetails();
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
	  }catch(Exception e) {
		  be.clickUserDetails();
		  be.getUserDetails();
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

	@Then("click the OneCognizant")
	public void click_the_one_cognizant() {
	    be.clickOneCognizant();
	   
	}
	
	@When("get the title")
	public void get_the_title() {
	    oc = new OneCognizant(driver);
	    oc.driverSwitch();
	    Assert.assertEquals(driver.getTitle(), "OneCognizant");
	}

	@Then("enter in the search bar as Timesheet")
	public void enter_in_the_search_bar_as_timesheet() throws IOException, InterruptedException {
		oc.enterTheSearch();
		Thread.sleep(2000);
		oc.searchResult();
		//oc.pressBtn_Timesheet();
	}
	
	@When("get the the all timesheet")
	public void get_the_the_all_timesheet() throws InterruptedException {
		Thread.sleep(2000);
	    tc = new Timesheet(driver);
	    tc.driverSwitch1();
	    tc.getAllSheetDetails();
	}

	@When("get the current date timesheet")
	public void get_the_current_date_timesheet() throws InterruptedException {
		Thread.sleep(2000);
	    tc.getCurrentSheetDetails();
	}

	@When("get the all status timesheet")
	public void get_the_all_status_timesheet() throws InterruptedException {
		Thread.sleep(2000);
	   tc.getApprovedSheetDetails();
	   Thread.sleep(2000);
	   tc.getOverdueSheetDetails();
	   Thread.sleep(2000);
	   tc.getPartiallyApprovedSheetDetails();
	   Thread.sleep(2000);
	   tc.getPendingSheetDetails();
	   Thread.sleep(2000);
	   tc.getSavedSheetDetails();
	   Thread.sleep(2000);
	   tc.getSentBackSheetDetails();
	   Thread.sleep(2000);
	   tc.getSubmittedSheetDetails();
	   Assert.assertEquals(driver.getTitle(),"Timesheet Landing Component");
	}

	@Then("close the driver")
	public void close_the_driver() {
		driver.quit();
		
	}

}
