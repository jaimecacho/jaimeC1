package objects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderPage  {
	private WebDriver driver;
	
	private final static By contactInformationBy =By.xpath(".//div[contains(text(),'Contact information')]/following-sibling::div");
	private final static String textBoxPaymentMethodrBy =".//input[@name='%s']";
	private final static By placeHolderButtonBy = By.xpath(".//button/span[contains(text(),'Place Order')]");
	private final static By paymentMethodBy =By.xpath(".//div[contains(text(),'Payment Method')]/following-sibling::div");
	private final static By fullnameBy =By.xpath(".//div[contains(text(),'Shipping Address')]/following-sibling::div//div/div[@class='full-name']");
	private final static String zipCodeCityBy =".//div[contains(text(),'%s')]/following-sibling::div//div/div/div[1]";
	private final static String stateCountryBy =".//div[contains(text(),'%s')]/following-sibling::div//div/div/div[2]";
	private final static String item =".//span[@class='font-semibold'][.='%s']";
	
	
	public void setCreditCardInformation(WebDriver driver,String nameAttribute,String value) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame(0);
		String xpathformat = String.format(textBoxPaymentMethodrBy, nameAttribute);
		By element = By.xpath(xpathformat);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value);

	}
	
	public void clickPlaceOrderButton(WebDriver driver) {
		driver.switchTo().defaultContent();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(placeHolderButtonBy));	
		driver.findElement(placeHolderButtonBy).click();
			
	}
	
	public String getContactInformation(WebDriver driver) {

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOfElementLocated(contactInformationBy));
		String value=driver.findElement(contactInformationBy).getText().trim();
		
		return value;
	}
	
	public String getPaymentMethod(WebDriver driver) {

		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(paymentMethodBy));
		String value=driver.findElement(paymentMethodBy).getText().trim();
		
		return value;
	}
	
	public String getShippingAddress(WebDriver driver, String typeAddress) {

		
		String xpathformat = String.format(zipCodeCityBy, typeAddress);
		By element = By.xpath(xpathformat);
		String value1=driver.findElement(element).getText().trim();
		
		xpathformat = String.format(stateCountryBy, typeAddress);
		element = By.xpath(xpathformat);
		String value2=driver.findElement(element).getText().trim();
		String value3=driver.findElement(fullnameBy).getText().trim();
		String address=value3+" "+value1+" "+value2;
		
		return address;
	}

	public boolean validateItem(WebDriver driver, String name) {

		
		String xpathformat = String.format(item, name);
		By element = By.xpath(xpathformat);

		return driver.findElement(element).isDisplayed();
	}	
}
