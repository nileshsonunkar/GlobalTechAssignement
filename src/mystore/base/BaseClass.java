package mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import static org.openqa.selenium.support.locators.RelativeLocator.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;


public class BaseClass {
	public static Properties properties;
	public static WebDriver driver;

	@BeforeTest
	public void loadConfig() {

		try {
			properties = new Properties();
			FileInputStream input = new FileInputStream(
					System.getProperty("user.dir") + "\\Configurations\\Config.properties");
			properties.load(input);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchApp() {
		
		WebDriverManager.chromedriver().setup();
		String browserName = properties.getProperty("browser");
		
		if (browserName.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("FireFox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} 
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(properties.getProperty("url"));	
		String initialWindowTitle = driver.getTitle();
        System.out.println("Store title is "+initialWindowTitle);
		
	}

}
