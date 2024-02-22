package objectModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import project.ExcelData;

public class OneCognizant {
	
	WebDriver driver;
	public OneCognizant(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//li[@class='searchTopBar']") WebElement clickSearchIcon;
	@FindBy(id="oneCSearchTop") WebElement searchBar;
	@FindBy(xpath="//div[@id='newSearchQALST']/div") List<WebElement> searchResult;
	@FindBy(xpath="//div[@class='row row1']/div[1]") WebElement btn_Timesheet;
	
	public void driverSwitch() {
		ArrayList<String> sh = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(sh.get(1));
	}
	
	public void enterTheSearch() throws IOException {
		clickSearchIcon.click();
		searchBar.sendKeys(ExcelData.Input());
	}
	
	public void searchResult(){
		for(int i=0;i<searchResult.size();i++) {
			if(searchResult.get(i).getText().equalsIgnoreCase("Submit Timesheet")) {
				searchResult.get(i).click();
			}
		}
	}
	public void pressBtn_Timesheet() {
		btn_Timesheet.click();
	}

}
