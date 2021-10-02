package practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonWalaPractice {
	public static void main(String[] args) {
		int price = 30000;
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("phone under "+price);
		driver.findElement(By.id("nav-search-submit-button")).click();
		driver.findElement(By.xpath("//a[@aria-label='See more, Brand']/span")).click();
		driver.findElement(By.xpath("//span[text()='MI']")).click();
		String actualPrice = driver.findElement(By.xpath("//div[@data-component-type='s-search-result'][1]//span[@class='a-price-symbol']/../span[2]")).getText();
		String actualPrice1 =actualPrice.replace(",", "");
		int actualPriceInt = Integer.parseInt(actualPrice1);
		System.out.println(actualPriceInt<price?"actual price is -->"+actualPriceInt+" --> validation passed":"actual price is -->"+actualPriceInt+" --> validation failure");
		driver.close();
	}
}
