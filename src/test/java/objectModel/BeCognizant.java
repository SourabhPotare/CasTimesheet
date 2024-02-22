package objectModel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BeCognizant {
	WebDriver driver;
	public BeCognizant(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[normalize-space()='OneCognizant']//div[@id='QuicklinksItemTitle']") WebElement txt_oneCognizant;
	@FindBy(xpath="//div[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']") WebElement btn_userDetails;
	@FindBy (xpath="//div[@class='mectrl_accountDetails']/div") List<WebElement> lt_userDetails;
	
	
	public boolean checkOneConizant() throws InterruptedException {
		return txt_oneCognizant.isDisplayed();
	}
	
	public void clickUserDetails() {
		btn_userDetails.click();
	}
	
	public List<WebElement> getUserDetails() {
		return lt_userDetails;
		
	}
	
	public void clickOneCognizant() {
		txt_oneCognizant.click();
	}
	
}
