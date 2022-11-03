package mystore.pageobjects;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.locators.RelativeLocator;

import mystore.actiondriver.Action;
import mystore.base.BaseClass;

public class OrderPage extends BaseClass {
	
	@FindBy(xpath="//td[@class='cart_unit']/span/span")
	WebElement unitPrice;
	
	@FindBy(id="total_price")
	WebElement totalPrice;
	
	@FindBy(xpath="(//i[@class='icon-trash'])[2]")
	WebElement trashIcon;
	
	@FindBy(xpath="(//i[@class='icon-plus'])[1]")
	WebElement clickOnAdd;
	
	@FindBy(xpath="//input[@value='2'][2]")
	WebElement verifySize;
	
	@FindBy(xpath="(//span[@class='price'])[4]")
	WebElement fadedDressSinglePrice;
	
	@FindBy(xpath="(//td[@class='cart_total'])[1]")
	WebElement fadedDressSTotalPrice;
	
	@FindBy(xpath="(//span[@class='price'])[7]")
	WebElement printedSummerDressSinglePrice;
	
	@FindBy(xpath="(//td[@class='cart_total'])[2]")
	WebElement printedSummerDressSTotalPrice;
	
	@FindBy(xpath="//td[@id='total_shipping']")
	WebElement shippingPrice;
	
	public OrderPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	public double getTotalPrice() {
		
		String totalPrice1 = totalPrice.getText();
		System.out.println("Total Price is "+totalPrice1);
		String totalPrice = totalPrice1.replace("$", "");
		double finalTotalPrice= Double.parseDouble(totalPrice);
		return finalTotalPrice;
	}

	public void deleteEveningDress() {
		String cartSummary = driver.findElement(By.xpath("//h1[@id='cart_title']")).getText();
		System.out.println("Your "+cartSummary+" is displayed.");	
		Action.click(driver, trashIcon);
		driver.navigate().refresh();
		
	}
	
	public void addOneMoreDress() {
		Action.click(driver, clickOnAdd);
		Action.explicitWait(driver, fadedDressSinglePrice, Duration.ofSeconds(10));
	}
	
	public double singleFadedDressPrice() {
		String siglePrice = fadedDressSinglePrice.getText();
		System.out.println("Price of single Faded Short Sleeve T Shirt is "+siglePrice);
		String siglePrice1 = siglePrice.replace("$", "");
		double singleFadedDress= Double.parseDouble(siglePrice1);		
		return singleFadedDress;	
	}
	
	public double twoFadedDressPrice() {
	
	String twoPrice1 = fadedDressSTotalPrice.getText();
	System.out.println("Total price of the selected Faded Short Sleeve T Shirt is "+twoPrice1);
	String twoPrice2 = twoPrice1.replace("$", "");
	double twoFadedDress= Double.parseDouble(twoPrice2);
	return twoFadedDress;
	}
	
	public double singlePrintedDressPrice() {
		String siglePrintedPrice = printedSummerDressSinglePrice.getText();
		System.out.println("Price of single Printed Summer Dress is "+siglePrintedPrice);
		String siglePrintedDress1 = siglePrintedPrice.replace("$", "");
		double singleFadedDress= Double.parseDouble(siglePrintedDress1);		
		return singleFadedDress;	
	}
	
	public double twoPrintedDressPrice() {
	
	String twoPrintedPrice = printedSummerDressSTotalPrice.getText();
	System.out.println("Total Price of single Printed Summer Dress is "+twoPrintedPrice);
	String totalPrintedDress = twoPrintedPrice.replace("$", "");
	double fianlPrintedDress= Double.parseDouble(totalPrintedDress);
	return fianlPrintedDress;
	}
	
	public double shippingCharge() {
		String shippingC = shippingPrice.getText();
		System.out.println("Shipping Price is "+shippingC);
		String removeSpecial = shippingC.replace("$", "");
		double fianlShippingCharge= Double.parseDouble(removeSpecial);		
		return fianlShippingCharge;	
	}
	
}
