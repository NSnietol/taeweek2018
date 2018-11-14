package stepDefinitions;

import cucumber.api.java.en.Given;
import managers.PageGenerator;

public class CommonSteps {

	public PageGenerator page;

	// Constructor
	public CommonSteps() {
		page = ServiceHooks.page;
	}

	@Given("^the user is in the application$")
	public void isLoaded() {
		page.homePage().get();
	}

}
