package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import utilities.TestUtil;

public class ValidateLogin extends BaseTest{

	@Test
	public void clickOnLogin() {

		click("login_btn_XPATH");
	}

	@Parameters({"username","password"})
	@Test
	//@Test(dataProviderClass = TestUtil.class, dataProvider = "getData")
	public void doLogin(String username, String password) {

		type("username_XPATH", username);
		type("password_XPATH", password);
		click("login_Btn_XPATH");

	}
	@Parameters("username")
	@Test
	public void  validateUsername(String username) {
		String expected_uname = driver.findElement(By.xpath(OR.getProperty("userID_XPATH"))).getText();
		Assert.assertEquals(username, expected_uname);
	}
}
