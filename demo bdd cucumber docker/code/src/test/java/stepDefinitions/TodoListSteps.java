package stepDefinitions;

import org.testng.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import managers.PageGenerator;

public class TodoListSteps {

	public PageGenerator page;

	// Constructor
	public TodoListSteps() {
		page = ServiceHooks.page;
	}

	@When("^the user create new \"([^\"]*)\"$")
	public void userWritesTask(String nameTask) throws Throwable {
		page.homePage().createNewTask(nameTask);
	}

	@Then("^validate if task \"([^\"]*)\", was created\\.$")
	public void validateIfTaskWasCreated(String createdTask) throws Throwable {
		Assert.assertTrue(page.homePage().validateTaskExists(createdTask));
	}

	@When("^the user delete \"([^\"]*)\" completed$")
	public void userDeleteTask(String nameTask) throws Throwable {
		page.homePage().deleteTask(nameTask);
	}

	@Then("^validate if task \"([^\"]*)\", was deleted\\.$")
	public void validateIfTaskWasDeleted(String deletedTask) throws Throwable {
		Assert.assertFalse(page.homePage().validateTaskExists(deletedTask));
	}

}
