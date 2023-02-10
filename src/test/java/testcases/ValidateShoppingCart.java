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
			//click
			driver.findElement(By.xpath("//input[@name = 'removefromcart']")).click();//a[contains(text(), 'Edit')]
			driver.findElement(By.xpath("//a[contains(text(), 'Edit')]")).click();
			driver.findElement(By.xpath("//div[@class = 'add-to-cart-panel']/input[@type = 'text']")).clear();
			click("addToCart_XPATH");
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
