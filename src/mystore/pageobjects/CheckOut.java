package mystore.pageobjects;


import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import mystore.actiondriver.Action;
import mystore.base.BaseClass;

public class CheckOut extends BaseClass{
	
	@FindBy(xpath="//a[@title='Check out']")
	WebElement checkOutMyCart;
	
	@FindBy(xpath="//a[@title='View my shopping cart']")
	WebElement myShoppingCart;
	
	
	
	public CheckOut() {
		PageFactory.initElements(driver, this);
	}
	
	public void myShoppingCart() {
		Action.explicitWait(driver, myShoppingCart, Duration.ofSeconds(5));
		Action.click(driver, myShoppingCart);
		return;
	}
	
	public OrderPage clickOnCheckOut() {
		
		
		Action.click(driver, checkOutMyCart);
		driver.navigate().refresh();
		return new OrderPage();
		
	}
	
	
}
