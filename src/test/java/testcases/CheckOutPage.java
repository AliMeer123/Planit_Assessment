package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;

public class CheckOutPage extends BaseTest{


	@Parameters("value")
	@Test(priority = 0)
	public void selectingBillingAddress(String value) {
		select("billingAdd_XPATH", value);
	}

	@Parameters({"country","city","address_1","zipCode","phoneNum"})
	@Test(priority = 1)
	public void enteringBillingAddressDetails(String country, String city, String address_1, String zipCode, String phoneNum) {

		select("country_XPATH", country);
		type("city_ID", city);
		type("address_ID", address_1);
		type("zipCode_ID", zipCode);
		type("phoneNum_ID", phoneNum);
		click("continue_Btn_XPATH");

	}

	@Parameters("shippingAddress")
	@Test(priority = 2)
	public void selectingShippingAdd(String value) {

		select("shippingAddDropDown_ID", value);
		click("continueBtn_1_XPATH");
	}

	@Test(priority = 3)
	public void shippingMethod() {

		click("shippingMethod_XPATH");
		click("shippingMethodBtn_XPATH");

	}
	@Test(priority = 4)
	public void paymentMethod() {
		click("paymentMethod_XPATH");
		click("paymentMethodBtn_XPATH");
	}
	@Test(priority = 5)
	public void validatePaymentMsg() {

		Assert.assertEquals(isElementPresent("paymentMsg_XPATH"), true);
		click("paymentInfoBtn_XPATH");
	}
	
	@Test(priority = 6)
	public void confirmOrder() {

		click("confirmBtn_XPATH");
		Assert.assertEquals(isElementPresent("orderPlacedMsg_XPATH"), true);
		String s = driver.findElement(By.xpath(OR.getProperty("orderNum_XPATH"))).getText();
		System.out.println(s);
		click("continueBtn_XPATH");
	}

	@Test(priority = 7)
	public void doLogOut() {

		click("logout_XPATH");
	}
}
