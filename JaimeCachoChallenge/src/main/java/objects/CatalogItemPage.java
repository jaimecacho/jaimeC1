package objects;

import java.time.Clock;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CatalogItemPage  {
	 WebDriver driver;
	 
	 	private final static String typeBy=".//a[@href='/category/%s']";
		private final static String productBy = ".//img[@alt='%s']";
		private final static String sizeBy= ".//a[.='%s']";
		private final static String colorBy= ".//a[contains(text(),'%s')]";
		private final static By addToCartButtonBy = By.xpath(".//button/span[contains(text(),'ADD TO CART')]");
		private final static By closeBy = By.xpath(".//div[contains(text(),'JUST ADDED TO YOUR CART')]/following-sibling::div/a"); 
		private final static By cartBy= By.xpath(".//a[@href='/cart']");
		private final static By checkoutBy= By.xpath(".//a[@href='/checkout']");	


	public void addElementToTheCart(WebDriver driver, String type, String product, String size, String color) {

		String xpathformat = String.format(typeBy, type);
		By element = By.xpath(xpathformat);
		driver.findElement(element).click();
		
		xpathformat = String.format(productBy, product);
		element = By.xpath(xpathformat);
		driver.findElement(element).click();
		
		xpathformat = String.format(sizeBy, size);
		element = By.xpath(xpathformat);
		driver.findElement(element).click();
		
		xpathformat = String.format(colorBy, color);
		element = By.xpath(xpathformat);
		driver.findElement(element).click();

		try {
			Thread.sleep(1000);
			driver.findElement(addToCartButtonBy).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(closeBy));
			driver.findElement(closeBy).click();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void checkout(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(cartBy));
		driver.findElement(cartBy).click();
		driver.findElement(checkoutBy).click();
	}

}
