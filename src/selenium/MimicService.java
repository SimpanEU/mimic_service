package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MimicService {
	
	private WebDriver webDriver;
	
	public void mimicservice() {
		System.setProperty("webdriver.chrome.driver", "bin/chromedriver");		
		webDriver = new ChromeDriver();
		webDriver.get("localhost:8080/request");
		WebElement element = webDriver.findElement(By.xpath("//textarea[@name='text']"));
		element.sendKeys("res1");
		WebElement element2 = webDriver.findElement(By.id("learn"));
		element2.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webDriver.get("localhost:8080/LearnNextResponse?text=res2");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/LearnNextResponse?text=res3");
		webDriver.get("localhost:8080/resquest");
		webDriver.get("localhost:8080/LearnNextResponse?text=test4");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/LearnNextResponse?text=test5");
		webDriver.get("localhost:8080/request");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/unlearn");
		webDriver.get("localhost:8080/resetState");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/request");
		webDriver.get("localhost:8080/request");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
