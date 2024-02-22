Feature: Run the project

	Scenario: testing the BeCognizant
		Given start the browser
		When one cognizant is visible
		Then get the user details
		Then click the OneCognizant
		When get the title
	 	Then enter in the search bar as Timesheet
	 	When get the the all timesheet
	 	When get the current date timesheet
	 	When get the all status timesheet
	 	Then close the driver
	 	