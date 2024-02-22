package objectModel;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class Timesheet {
	
	WebDriver driver;
	public Timesheet(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[1]")
	static List<WebElement> dates;
	@FindBy(xpath="//div[@id='win0div$ICField44$0']/div/div[2]/div[2]/div[2]")
	static List<WebElement> status;
	@FindBy(xpath="//div/select[@id='CTS_TS_LAND_WRK_CTS_TS_SEARCH']") WebElement drp_status;
	@FindBy(xpath="//a[@id='CTS_TS_LAND_WRK_DATE$prompt']") WebElement click_calender;
	@FindBy(xpath="//a[@id='curdate']") WebElement btn_crntdate;
	@FindBy(xpath="//select[@id='CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS']") WebElement drp_sheet;
	@FindBy(xpath="//a[@id='CTS_TS_LAND_WRK_SEARCH']") WebElement btn_search;
	@FindBy(xpath="//a[@id='#ICOK']") WebElement btn_notFound;
	
	public void driverSwitch1() {
	
		ArrayList<String> sh = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(sh.get(2));
	}
	
	public void getAllSheetDetails() {
		
		for(int i=0;i<dates.size();i++) {
			System.out.println(dates.get(i).getText()+" "+ status.get(i).getText());
		}
	}
	
	public void getCurrentSheetDetails(){
		Select sl = new Select(drp_status);
		sl.selectByVisibleText("Date");
		click_calender.click();
		btn_crntdate.click();
		btn_search.click();
		
	}
	
	public void getApprovedSheetDetails() throws InterruptedException{
		Select sl = new Select(drp_status);
		sl.selectByVisibleText("Status");
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Approved");
		btn_search.click();
		
	}	
	
	public void getOverdueSheetDetails() throws InterruptedException {
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Overdue");
		btn_search.click();
		btn_notFound.click();
	}
	
	public void getPartiallyApprovedSheetDetails() throws InterruptedException {
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Partially Approved");
		btn_search.click();
		btn_notFound.click();
	}
	
	public void getSavedSheetDetails() throws InterruptedException {
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Saved");
		btn_search.click();
		btn_notFound.click();
	}
	
	public void getSubmittedSheetDetails() throws InterruptedException {
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Submitted for Approval");
		btn_search.click();
		//btn_notFound.click();
	}
	
	public void getPendingSheetDetails() throws InterruptedException {
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Pending");
		btn_search.click();
	}
	
	public void getSentBackSheetDetails() throws InterruptedException {
		Select sl1 = new Select(drp_sheet);
		sl1.selectByVisibleText("Sent Back for Revision");
		btn_search.click();
		btn_notFound.click();
	}
	
	public static List<WebElement> getDate() {
		return dates;	
	}
	
	public static List<WebElement> getStatus() {
		return status;	
	}
}
