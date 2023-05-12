package testdeclaration;

import static org.testng.Assert.assertEquals;

import java.security.SecureRandom;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import dev.failsafe.internal.util.Assert;
import objects.CatalogItemPage;
import objects.OrderPage;
import objects.ShippingPage;
import objects.PaymentPage;

public class testdeclaration {
	private WebDriver driver;
	private final static By loginBy= By.xpath(".//a[@href='/account/login']");	
	private final static By createAccountLink= By.xpath(".//a[contains(text(),'Create an account')]");
	private final static String textboxBy= ".//input[@name='%s']";
	private final static By signUpButtonBy = By.xpath(".//button/span[contains(text(),'SIGN UP')]"); 

	static final  String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom rnd = new SecureRandom();
	
	
	String telephone ="456456";
	String address ="Lost 123";
	String city ="Lima";
	String postCode="5554";
	String country="United States";
	String province="Alabama";
	
	String paymentMethod="Credit Card";
	String cardNumber="4242424242424242";
	String expDate="0424";
	String cvc="242";
	
	
	String item1="Continental 80 shoes";
	String item2="Alphaedge 4d reflective shoes R";
	String item3="Swift run x shoes";
	
	//generate random values for name, email and password
	String firstName =randomString(7);
	String lastName =randomString(7);;
	String FullNameClient = firstName+" "+lastName;
	String passwordClient = randomString(7);
	String emailClient =firstName+"."+lastName+"@mail.com";
	
	public void mainFlow() {

		
		try {
			Thread.sleep(1000);
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(loginBy));
			
			driver.findElement(loginBy).click();
			//create new user
			driver.findElement(createAccountLink).click();

			//fill out full name, email and password textbox
			setTextBox("full_name", FullNameClient);
			setTextBox("email", emailClient);
			setTextBox("password", passwordClient);
			
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOfElementLocated(signUpButtonBy));
			driver.findElement(signUpButtonBy).click();
			
			//add productos in the cart
			CatalogItemPage cip= new CatalogItemPage();

			
			cip.addElementToTheCart(driver,"kids",item1,"L","White");
			cip.addElementToTheCart(driver,"women",item2,"M","Black");
			cip.addElementToTheCart(driver,"men",item3,"S","Pink");
			cip.checkout(driver);

			
			//address
			ShippingPage sp = new ShippingPage();
			sp.addressPageTextBox(driver, "Full name", FullNameClient);
			sp.addressPageTextBox(driver, "Telephone", telephone);
			sp.addressPageTextBox(driver, "Address", address);
			sp.addressPageTextBox(driver, "City", city);
			sp.selectElement(driver,"Country",country);
			sp.selectElement(driver,"Province",province);
			sp.addressPageTextBox(driver, "Postcode", postCode);
			sp.radioButtonStandarDelivery(driver);
			sp.clickContinuePaymentButton(driver);
			Thread.sleep(1000);
			String AddressValidation =FullNameClient+" "+postCode+", "+city+" "+province+", "+country;
			//payment
			
			PaymentPage pp = new PaymentPage();
			Thread.sleep(1000);
			pp.selectPaymentMethodVisa(driver);
			Thread.sleep(1000);
			pp.setCreditCardInformation(driver, "cardnumber", cardNumber);
			Thread.sleep(1000);
			pp.setCreditCardInformation(driver, "exp-date", expDate);
			Thread.sleep(1000);
			pp.setCreditCardInformation(driver, "cvc", cvc);
			Thread.sleep(2000);
			pp.clickPlaceOrderButton(driver);
			
			
			//get order Information from OrderPage
			Thread.sleep(4500);
			OrderPage op = new OrderPage();
			String emailOrder=op.getContactInformation(driver);
			String paymentMethodOrder=op.getPaymentMethod(driver);
			String shippingAddressOrder=op.getShippingAddress(driver, "Shipping Address");
			String billingAddressOrder=op.getShippingAddress(driver, "Billing Address");
			
			//validate order Information from OrderPage
			assertEquals(emailClient, emailOrder);
			assertEquals(paymentMethod, paymentMethodOrder);
			assertEquals(AddressValidation, shippingAddressOrder);
			assertEquals(AddressValidation, billingAddressOrder);

			assertEquals(true, op.validateItem(driver, item1));
			assertEquals(true, op.validateItem(driver, item2));
			assertEquals(true, op.validateItem(driver, item3));
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	@BeforeTest
	public void createClient() {
			
		System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.evershop.io/");
		
		
	}
	
	
	@AfterTest
	public void closeBrowser() {
		
		driver.close();
		driver.quit();
	
		
	}
	
	public String randomString(int len){
		   StringBuilder sb = new StringBuilder(len);
		   for(int i = 0; i < len; i++)
		      sb.append(AB.charAt(rnd.nextInt(AB.length())));
		   String value=sb.toString();
		   return value;
		}
	
	
	public void setTextBox(String nameAttribute, String value) {


		String xpathformat = String.format(textboxBy, nameAttribute);
		By element = By.xpath(xpathformat);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
		driver.findElement(element).clear();
		driver.findElement(element).sendKeys(value);

	}
	
		
}
