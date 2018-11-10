package stepDefinitions;

import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;
import pages.HomePageFacebook;
import pages.PageGenerator;

public class StepsFacebook {

	public PageGenerator page;

	// Constructor
	public StepsFacebook() {
		page = ServiceHooks.page;
	}

	@Given("^The user is on login page$")
	public void theUserIsOnLoginPage() throws Throwable {
		page.GetInstance(HomePageFacebook.class).goToLoginPage();
	}

	@When("^The user logs in using the following credentials, and get into their profile$")
	public void theUserLogsinAndGetInTheirProfile(DataTable data) throws Throwable {
		List<Map<String, String>> dataMap = data.asMaps(String.class, String.class);
		dataMap.forEach(row -> {
			String email = row.get("email");
			String pass = row.get("pass");
			page.GetInstance(HomePageFacebook.class).login(email, pass);
		});
	}

	@Then("^Post an entry in the wall of your profile$")
	public void postAnEntryInTheWallOfYourProfile() throws Throwable {
		page.GetInstance(HomePageFacebook.class).post("post prueba");
	}

}
