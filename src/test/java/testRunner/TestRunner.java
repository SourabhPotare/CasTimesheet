 package testRunner;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;                       

@RunWith(Cucumber.class)
@CucumberOptions(
	features={"Feature"}, 
	glue="StepDefination",
	plugin = {
			
			"pretty","html:target/cucumber-reports/TimesheetCucumber.html"}
	)
public class TestRunner {         


	
}
