package mystore.actiondriver;

import java.time.Duration;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import mystore.base.BaseClass;

public class Action extends BaseClass {

	public Action(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public static boolean findElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Element is found");
			} else {
				System.out.println("Element is missing");
			}
		}
		return flag;
	}

	public void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);

	}
	
	public static boolean selectByVisibleText (String dressSize){
		boolean flag = false;
		try {
			
			WebElement sizeDropdown = driver.findElement(By.id("group_1"));
			Select dropdown = new Select(sizeDropdown);
			dropdown.selectByVisibleText(dressSize);
			flag = true;
		} catch (Exception e) {
			System.out.println("Element is not found to enter text");
			flag = false;
		} finally {
			if(flag) {
				System.out.println(dressSize+" is entered successfully");
			}else {
				System.out.println("Unable to send value "+dressSize);
			}				
						
		}
		return flag;
		
	}
	

	public void pageLoadTimeout(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeOut));
	}
    
	public static boolean type(WebElement ele, String text){
		boolean flag = false;
		try {
			flag = ele.isDisplayed();
			ele.clear();
			ele.sendKeys(text);
			flag = true;
		} catch (Exception e) {
			System.out.println("Element is not found to enter text");
			flag = false;
		} finally {
			if(flag) {
				System.out.println(text+" is entered successfully");
			}else {
				System.out.println("Unable to send value "+text);
			}				
						
		}
		return flag;
		
	}
	
	public static boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			actions.moveToElement(ele).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void click(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.moveToElement(ele).click().build().perform();
     
	}
	
	public static boolean isDisplayed(WebDriver driver, WebElement ele) {
		boolean flag = false;
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				System.out.println("The element is Displayed");
			} else {
				System.out.println("The element is not Displayed");
			}
		} else {
			System.out.println("Not displayed ");
		}
		return flag;
	}
	
	public static void explicitWait(WebDriver driver, WebElement element, Duration i ) {
		WebDriverWait wait = new WebDriverWait(driver,i);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
}