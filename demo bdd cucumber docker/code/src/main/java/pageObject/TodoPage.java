package pageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;

import managers.FileReaderManager;
import utility.Log;
import utility.Wait;

public class TodoPage extends LoadableComponent<TodoPage> {

	// *********Page Variables*********
	private String baseURL;
	private WebDriver driver;
	private BasePage basePage;

	@FindBy(css = "input[class='v-textfield v-widget v-has-width']")
	private WebElement newTaskInput;

	@FindBy(css = "div[class='v-button v-widget primary v-button-primary']")
	private WebElement newTaskSubmit;

	@FindBy(css = "div[class='v-button v-widget']")
	WebElement deleteTasksSubmit;

	String nameTasksCreateds = "input[class='v-textfield v-widget borderless v-textfield-borderless v-has-width']";

	@FindBys(value = @FindBy(css = "input[class='v-textfield v-widget borderless v-textfield-borderless v-has-width']"))
	private List<WebElement> tasksBeforeCreateOne;

	// *********Constructor*********
	public TodoPage(WebDriver driver) {
		this.driver = driver;
		this.basePage = new BasePage(this.driver);
		this.baseURL = FileReaderManager.getInstance().getConfig().url();
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void load() {
		this.driver.get(baseURL);
	}

	@Override
	protected void isLoaded() throws Error {
		if (!driver.getCurrentUrl().contains(baseURL)) {
			throw new Error("HomePage is not loaded!");
		}
	}

	public void createNewTask(String taskName) throws Error, InterruptedException {
		basePage.writeText(newTaskInput, taskName);
		basePage.click(newTaskSubmit);
		Wait.getWait(driver)
				.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector(nameTasksCreateds), tasksBeforeCreateOne.size()));
	}

	public boolean validateTaskExists(String createdTask) {
		WebElement result = findTask(createdTask);
		if (null != result) {
			return true;
		}
		return false;
	}

	private WebElement findTask(String createdTask) {
		List<WebElement> tasks = driver.findElements(By.cssSelector(nameTasksCreateds));
		return tasks.stream().filter(element -> element.getAttribute("value").contains(createdTask)).findFirst()
				.orElse(null);
	}

	public void deleteTask(String nameTask) {
		WebElement taskToDelete = findTask(nameTask);
		try {
		basePage.click(
				taskToDelete.findElement(By.xpath("../..")).findElement(By.cssSelector("input[type='checkbox'")));
		} catch (NullPointerException e) {
			Log.warn("the task " + nameTask + " doesnt exists already.");
			return;
		}
		basePage.click(deleteTasksSubmit);
		Log.info(String.valueOf(tasksBeforeCreateOne.size()));
		Wait.getWait(driver)
				.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(nameTasksCreateds), tasksBeforeCreateOne.size()));
	}


}
