package mystore.testscripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import mystore.base.BaseClass;
import mystore.pageobjects.CheckOut;
import mystore.pageobjects.OrderPage;
import mystore.utility.Dress;

public class completeScenario extends BaseClass {
	Dress dress;
	CheckOut checkout;
	OrderPage orderPage;

	@BeforeMethod
	public void setup() {
		launchApp();
	}
	
	 @AfterMethod
	   public void browserQuit() {
		   driver.quit();
	   }
	

	@Test
	public void addDress() {
		dress = new Dress();
		checkout = new CheckOut();
		orderPage = new OrderPage();
		SoftAssert softAssert = new SoftAssert();
		
		dress.Add("Faded Short Sleeve T Shirt", "M", "Blue", driver);
		dress.Add("Evening Dress", "S", "Beige", driver);
		dress.Add("Printed Summer Dress", "M", "Orange", driver);
		orderPage.deleteEveningDress();
		double fadedShortSleevePrice = orderPage.singleFadedDressPrice();
		orderPage.addOneMoreDress();
		double twoFadedShortSleevePrice = fadedShortSleevePrice*2;
		System.out.println("Price of two Faded Short Sleeve t-shirt is "+twoFadedShortSleevePrice);
		double totalFadedShortSleevePrice = orderPage.twoFadedDressPrice();
		softAssert.assertEquals(totalFadedShortSleevePrice, twoFadedShortSleevePrice, "Both prices are different");
		
		double printedSummerDressPrice = orderPage.singlePrintedDressPrice();
		double totalPrintedSummerPrice = orderPage.twoPrintedDressPrice();

		softAssert.assertEquals(printedSummerDressPrice, totalPrintedSummerPrice);
		double shippingPrice = orderPage.shippingCharge();
		double finalPrice = orderPage.getTotalPrice();
		double totalOrderValue = twoFadedShortSleevePrice+totalPrintedSummerPrice+shippingPrice;
		System.out.println("Total order sum is "+totalOrderValue+" and Total is "+finalPrice);
		softAssert.assertEquals(totalOrderValue, finalPrice, "Both prices are different");
		

	}

}
