package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;


public class BaseTest {

	public static WebDriver driver;
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static ExcelReader excel = new ExcelReader("./src/test/resources/excel/testData.xlsx");
	public static WebDriverWait wait;
	public static WebElement dropdown;
	public static Actions action;


	public static boolean isElementPresent(String key) {

		try {
			if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key)));
			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key)));
			} else if (key.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(key)));
			}
			return true;
		}catch(Throwable t) {
			return false;
		}
	}


	public static void click(String key) {

		try {
			if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key))).click();
			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key))).click();
			} else if (key.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(key))).click();
			}
		}catch(Throwable t) {
			Assert.fail(t.getMessage());
		}
	}
	public static void select(String key, String value) {

		try {
			if (key.endsWith("_XPATH")) {
				dropdown = driver.findElement(By.xpath(OR.getProperty(key)));
			} else if (key.endsWith("_CSS")) {
				dropdown = driver.findElement(By.cssSelector(OR.getProperty(key)));
			} else if (key.endsWith("_ID")) {
				dropdown = driver.findElement(By.id(OR.getProperty(key)));
			}

			Select select = new Select(dropdown);
			select.selectByVisibleText(value);
			
		}catch(Throwable t) {

			Assert.fail(t.getMessage());

		}
	}

	public static void moveToElement(String key) {

		action = new Actions(driver);

		try {
			if (key.endsWith("_XPATH")) {
				action.moveToElement(driver.findElement(By.xpath(OR.getProperty(key)))).perform();
			} else if (key.endsWith("_CSS")) {
				action.moveToElement(driver.findElement(By.cssSelector(OR.getProperty(key)))).perform();
			} else if (key.endsWith("_ID")) {
				action.moveToElement(driver.findElement(By.id(OR.getProperty(key)))).perform();
			}
			
		}catch(Throwable t) {

			Assert.fail(t.getMessage());
		}
	}

	public static void type(String key, String value) {

		try {
			if (key.endsWith("_XPATH")) {
				driver.findElement(By.xpath(OR.getProperty(key))).sendKeys(value);
			} else if (key.endsWith("_CSS")) {
				driver.findElement(By.cssSelector(OR.getProperty(key))).sendKeys(value);
			} else if (key.endsWith("_ID")) {
				driver.findElement(By.id(OR.getProperty(key))).sendKeys(value);
			}
		}catch(Throwable t) {

			Assert.fail(t.getMessage());
		}
	}

	@BeforeSuite
	public void setUp() {

		if (driver == null) {
			try {
				fis = new FileInputStream("./src/test/resources/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
			
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			try {
				fis = new FileInputStream("./src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}

			if (Config.getProperty("browser").equals("chrome")) {

				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				
			} else if (Config.getProperty("browser").equals("firefox")) {

				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				
			}

			driver.get(Config.getProperty("testsiteurl"));
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));

		}

	}

	@AfterSuite
	public void tearDown() {

		driver.quit();
		
	}

}
