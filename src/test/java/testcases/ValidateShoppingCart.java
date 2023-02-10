package testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.BaseTest;

public class ValidateShoppingCart extends BaseTest{

	@Test
	public void clearingCart() {
		click("cartBtn_XPATH");
	}

	@Test
	public void selectingProduct() {

		moveToElement("computers_XPATH");
		click("desktop_XPATH");
		click("desktop_Pic_XPATH");


	}

}
