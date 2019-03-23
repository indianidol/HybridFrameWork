package page.object.model.commons;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserManger extends CommonVariables {

	public BrowserManger() {
		this.browser = configProp.getProperty("browser").toUpperCase();

		if (driver != null) {
			getDriver();
		}

		navigate();
	}

	public BrowserManger(String browser, String url) {
		// this.browser = browser;
		getDriver();
		navigate(url);
	}

	private WebDriver getDriver() {
		switch (this.browser) {

		case "CHROME":

			String path = System.getProperty("user.dir") + configProp.getProperty("chromedriver");
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10000l, TimeUnit.SECONDS);
			// registerListner();
			wait = new WebDriverWait(driver, 30);
			return driver;

		case "FIREFOX":

			// registerListner();
			System.out.println("Firefox");
			return driver;
		case "IE":

			System.out.println("Firefox");
			return driver;
		}
		return null;
	}

	private void registerListner() {
		eventHandler = new EventFiringWebDriver(driver);
		eCapture = new MyListener();
		eventHandler.register(eCapture);
		driver = eventHandler;
	}

	private void navigate() {
		driver.get(configProp.getProperty("url"));
	}

	private void navigate(String url) {
		driver.get(url);
	}
}
