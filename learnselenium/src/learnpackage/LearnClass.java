package learnpackage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LearnClass {

	public static void main(String[] args) throws IOException, InterruptedException {
		try {
			// TODO Auto-generated method stub
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver_win32\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			//Launch Amazon in chrome browser
			driver.get("https://www.amazon.in/");
			driver.manage().window().maximize(); // Maximize window size
			String pageTitle = driver.getTitle();
			
			System.out.println("WebPage Title: " +pageTitle);

			//Sign-in to Amazon Account
			WebElement hello = driver.findElement(By.id("nav-link-accountList"));
			hello.click();

			//Enter Username, Pwd and login to Amazon
			WebElement usrname = driver.findElement(By.id("ap_email"));
			usrname.sendKeys("jeni.jayesh@gmail.com");
			WebElement continuebtn = driver.findElement(By.id("continue"));
			continuebtn.click();
			WebElement pswd = driver.findElement(By.id("ap_password"));
			pswd.sendKeys("Amazon@27");
			WebElement submitbtn = driver.findElement(By.id("signInSubmit"));
			submitbtn.click();
			System.out.println("Amazon User logged in Successful");

			// Take screenshot of user login
			File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("C:\\Users\\Administrator\\Desktop\\Selenium\\java\\screenshot1.png"));
			System.out.println("Took Amazon login screenshot");


			// Search smartwatch product
			WebElement searchtext = driver.findElement(By.id("twotabsearchtextbox"));
			searchtext.click();
			searchtext.sendKeys("smartwatch", Keys.ENTER);
			/*WebElement searchbtn = driver.findElement(By.id("nav-search-submit-button"));
			searchbtn.click();*/


			//to perform Scroll down in product page
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,350)", "");
			System.out.println("Smartwatch search Success");

			String oldwindow = driver.getWindowHandle();

			WebElement prodwatch1 = driver.findElement(By.cssSelector("a[href*='AGG-Verve-Smartwatch']"));
			prodwatch1.click();
			Thread.sleep(5000);

			Set <String> handles = driver.getWindowHandles();
			System.out.println("Windowsize: " +handles.size());
			for (String newwindow : handles) {
				if(!newwindow.equalsIgnoreCase(oldwindow))

					//switch to the new tab
					driver.switchTo().window(newwindow);


			}

			WebElement addcart = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
			addcart.click();
			System.out.println("Smartwatch added to cart");

			//Navigate to Cart page
			WebElement carticon = driver.findElement(By.id("nav-cart-count"));
			carticon.click();

			//Proceed to buy smartwatch
			WebElement proceedtobuy = driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']"));
			proceedtobuy.click();
			System.out.println("Proceed to buy smartwatch");

			System.out.println("Fill in the delivery address");
			WebElement newaddr = driver.findElement(By.cssSelector("a[href*='new-address']"));
			newaddr.click();

			//Select a country from drop down
			Select countrydrpdwn = new Select (driver.findElement(By.xpath("//*[@name='address-ui-widgets-countryCode']")));
			countrydrpdwn.selectByVisibleText("India");

			//Enter a delivery address
			WebElement fullname = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressFullName']"));
			fullname.sendKeys("Jeni");

			WebElement mob= driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPhoneNumber']"));
			mob.sendKeys("9884710024");

			WebElement postalcode = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressPostalCode']"));
			postalcode.sendKeys("600039");

			WebElement flatno = driver.findElement(By.xpath("//input[@id='address-ui-widgets-enterAddressLine1']"));
			flatno.sendKeys("8th Street ");
			//Thread.sleep(8000);
			flatno.sendKeys(Keys.ARROW_DOWN);
			flatno.sendKeys(Keys.ENTER);

			WebElement area = driver.findElement(By.xpath("//*[@id='address-ui-widgets-enterAddressLine2']"));
			area.sendKeys("Area testing 123");

			WebElement landmrk = driver.findElement(By.xpath("//input[@id='address-ui-widgets-landmark']"));
			landmrk.sendKeys("Landmark of area");

			WebElement defaultaddrs = driver.findElement(By.xpath("//input[@id='address-ui-widgets-use-as-my-default']"));
			defaultaddrs.click();

			//to perform Scroll down 

			JavascriptExecutor js1 = (JavascriptExecutor) driver;
			js1.executeScript("window.scrollBy(0,350)", "");

			WebElement use_addrs = driver.findElement(By.xpath("//*[@id=\"address-ui-widgets-form-submit-button\"]/span/input"));
			use_addrs.click();


			//to perform Scroll UP until element is visible
			// Navigate to payment page
			System.out.println("Entered payment page");
			Thread.sleep(8000);
			WebElement choose_paymethod = driver.findElement(By.xpath("//input[@value='SelectableAddCreditCard']"));
			JavascriptExecutor js2 = (JavascriptExecutor) driver;
			js2.executeScript("arguments[0].scrollIntoView();", choose_paymethod); 
			choose_paymethod.click();

			/*


			WebElement add_card = driver.findElement(By.xpath("//*[contains (@id, 'Add a credit or debit card')]"));
			add_card.click();


			WebElement continuebtn2 = driver.findElement(By.xpath("//span[@id='pp-dWBsXP-146-announce']"));
			continuebtn2.click();*/

			driver.switchTo().window(oldwindow);
			driver.close();
			//driver.quit();
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
