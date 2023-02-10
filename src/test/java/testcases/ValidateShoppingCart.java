package testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;

public class ValidateShoppingCart extends BaseTest{

	@Test
	public void checkEmptyCart() {
		String cartItem = driver.findElement(By.xpath("//span[@class = 'cart-qty']")).getText();
		System.out.println(cartItem);
		if(!cartItem.equals("0")) {
			click("cartBtn_XPATH");
			click("removeFromCart_XPATH");
			click("cartEdit_XPATH");
			driver.findElement(By.xpath(OR.getProperty("qtyField_XPATH"))).clear();
			click("addToCart_XPATH");
			driver.navigate().refresh();
		}else
		{
			click("cartBtn_XPATH");
		}
	}


	@Test
	public void selectingProduct() {

		moveToElement("computers_XPATH");
		click("desktop_XPATH");
		click("desktop_Pic_XPATH");


	}

}
