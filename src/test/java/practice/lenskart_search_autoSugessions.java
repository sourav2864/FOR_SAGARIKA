package practice;



import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class lenskart_search_autoSugessions {
	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.lenskart.com/");
		
		driver.findElement(By.name("q")).click();
		
		List<WebElement> autoSugessinsElements = driver.findElements(By.className("trending"));
		
		for(WebElement wb:autoSugessinsElements ) {
			
			System.out.println(wb.getText());
		}
	}
}
