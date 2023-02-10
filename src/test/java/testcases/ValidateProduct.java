package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;

public class ValidateProduct extends BaseTest{

	@Parameters("qty")
	@Test(priority = 0)
	public void enterQuantity(String qty) {
		type("qty_XPATH", qty);
	}

	@Test(priority = 1)
	public void addToCart() {
		click("addToCart_XPATH");
	}
	@Test(priority = 2)
	public void validatingMessage(){
		Assert.assertEquals(isElementPresent("addToCartMsg_XPATH"), true);	
	}
	@Test(priority = 3)
	public void clickingAndValidatingCartTotal() throws InterruptedException {
		Thread.sleep(5000);
		click("shoppingCart_Xpath");
	}

	@Test(priority = 4)
	public void getProductInfo() {

		String unitPrice = driver.findElement(By.xpath(OR.getProperty("unitPrice_XPATH"))).getText();
		String quantity =  driver.findElement(By.xpath(OR.getProperty("quantity_XPATH"))).getAttribute("value");
		Float expectedTotal = Float.parseFloat(unitPrice) * Float.parseFloat(quantity);
		String actualTotal = driver.findElement(By.xpath(OR.getProperty("actualTotal_XPATH"))).getText();
		Assert.assertEquals(Float.parseFloat(actualTotal), expectedTotal);
	}

	@Test(priority = 5)
	public void CheckingCheckBoxAndCheckOut()  {
		click("checkBox_XPATH");
		click("checkOut_XPATH");
	}
}
