package objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PaymentPage  {
	private WebDriver driver;
	
	private final static By paymentVisaBy =By.xpath("(.//div[@class='py-2']//a[@href='#'])[3]");
	private final static String textBoxPaymentMethodrBy =".//input[@name='%s']";
	private final static By placeHolderButtonBy = By.xpath(".//span[contains(text(),'Place Order')]");
	

	
	public void selectPaymentMethodVisa(WebDriver driver) {

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentVisaBy));
		driver.findElement(paymentVisaBy).click();
		
	}
	
	public void setCreditCardInformation(WebDriver driver,String nameAttribute,String value) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		String xpathformat = String.format(textBoxPaymentMethodrBy, nameAttribute);
		By element = By.xpath(xpathformat);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value+Keys.TAB);

	}
	
	public void clickPlaceOrderButton(WebDriver driver) {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(placeHolderButtonBy));	
		javascriptClick(driver, driver.findElement(placeHolderButtonBy));
		//driver.findElement(placeHolderButtonBy).click();
		
		
	}
	
    public  void javascriptClick(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

    }
}
