package objects;

import java.security.SecureRandom;
import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ShippingPage {
	
	private WebDriver driver;
	private final static String addressTextboxBy= ".//input[@placeholder='%s']";
	private final static String selectElementBy = ".//select[@placeholder='%s']";
	private final static By postCodeBy = By.xpath(".//input[@placeholder='Postcode']");
	private final static By radioButtonDeliveryBy = By.xpath("//span[contains(text(),'Standard Delivery')]/preceding-sibling::span");
	private final static By continueToPaymentButtonBy = By.xpath(".//button/span[contains(text(),'Continue to payment')]");
		
		
	
		public void addressPageTextBox(WebDriver driver,String name, String value) {

			String xpathformat = String.format(addressTextboxBy, name);
			By element = By.xpath(xpathformat);
			driver.findElement(element).clear();
			driver.findElement(element).sendKeys(value);
		
		}
		
		public void selectElement(WebDriver driver,String dropdown, String option) {

			
			String xpathformat = String.format(selectElementBy, dropdown);
			By element = By.xpath(xpathformat);
			
			
			Select select = new Select(driver.findElement(element));
			select.selectByVisibleText(option);
						
			
		}
		
		public void radioButtonStandarDelivery(WebDriver driver) {


			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(radioButtonDeliveryBy));
			driver.findElement(radioButtonDeliveryBy).click();
			driver.findElement(continueToPaymentButtonBy).click();
			
			
		}
		
		public void clickContinuePaymentButton(WebDriver driver) {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(radioButtonDeliveryBy));
			driver.findElement(continueToPaymentButtonBy).click();
			
			
		}
}
