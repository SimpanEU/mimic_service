package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MimicService {

	public WebDriver webDriver;

	public MimicService() {
		System.setProperty("webdriver.chrome.driver", "bin/chromedriver.exe");	
		webDriver = new ChromeDriver();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	private void getRequestAndSleep() {

		String request = "localhost:8080/request";
		webDriver.get(request);
		sleep(500);	
	}

	public void mimicservice() {
		webDriver.get("http://localhost:8080/UnlearnAllResponses");
		webDriver.get("localhost:8080/request");
		webDriver.findElement(By.xpath("//textarea[@name='text']")).sendKeys("res1");
		sleep(500);
		webDriver.findElement(By.id("learn")).click();
		sleep(500);
		webDriver.get("localhost:8080/LearnNextResponse?text=res2");
		sleep(500);
		getRequestAndSleep();
		webDriver.get("localhost:8080/LearnNextResponse?text=res3");
		sleep(500);
		getRequestAndSleep();
		webDriver.get("localhost:8080/LearnNextResponse?text=res4");
		sleep(500);
		getRequestAndSleep();
		webDriver.get("localhost:8080/LearnNextResponse?text=res5");
		sleep(500);

		for(int i = 0; i<4; i++) {

			getRequestAndSleep();	
		}

		webDriver.get("http://localhost:8080/UnlearnResponse");
		sleep(500);
		webDriver.get("localhost:8080/resetState");
		sleep(500);

	}

	public void sleep(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void quit() {
		webDriver.quit();
	}
}
