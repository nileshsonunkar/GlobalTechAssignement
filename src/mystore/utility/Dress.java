package mystore.utility;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import mystore.base.BaseClass;

	

public class Dress extends BaseClass {
	
	
 	
		@BeforeMethod
		public void setup() {
			launchApp();
		}
    
	@Test(dataProvider = "testingData", dataProviderClass = ReadXLSXData.class)
	public void Add(String dress, String dressSize, String dressColor, WebDriver driver)
	//public static void Add(String dress, String dressColor, String dressSize)
			 {

		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement search = driver.findElement(By.id("search_query_top"));
		search.sendKeys(dress);
		driver.findElement(By.xpath("//button[@name='submit_search']")).click();
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.className("compare-form")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,450)");
		WebElement dressFrame = driver.findElement(By.xpath("//li[contains(@class,'first-item')]"));
		Actions action = new Actions(driver);
		action.moveToElement(dressFrame).build().perform();
		driver.findElement(By.xpath("//span[text()='More']")).click();
   
		js.executeScript("window.scroll(0,350)");
		WebElement sizeDropdown = driver.findElement(By.id("group_1"));
		Select dropdown = new Select(sizeDropdown);
		dropdown.selectByVisibleText(dressSize);
		driver.findElement(By.xpath("//a[@title='" + dressColor + "']")).click();
		driver.findElement(By.xpath("//span[text()='Add to cart']")).click();
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Proceed to checkout']")));

		String successMessage = driver.findElement(By.tagName("h2")).getText();
		System.out.println(successMessage);
		driver.findElement(By.xpath("//a[@title='Proceed to checkout']")).click();
		
	   // driver.findElement(By.xpath("//img[@alt='My Store']")).click();

	}

}
