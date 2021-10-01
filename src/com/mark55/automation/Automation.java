package com.mark55.automation;

import org.bouncycastle.operator.DefaultAlgorithmNameFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Automation {
	WebDriver driver;
	
	public Automation(String browser) {
		switch(browser) {
			case "firefox":WebDriverManager.firefoxdriver().setup();
							driver =new FirefoxDriver();
							break;
			case "edge":WebDriverManager.edgedriver().setup();
						driver =new EdgeDriver();
						break;
			case "opera":WebDriverManager.operadriver().setup();
						driver =new OperaDriver();
						break;
			case "safari":WebDriverManager.safaridriver().setup();
						driver =new SafariDriver();
						break;
			default:WebDriverManager.chromedriver().setup();
					driver =new ChromeDriver();
					break;
		}
		
	}
	
	public void activate(String mailStr, String pwdStr) {
		driver.manage().window().maximize();
		driver.get("https://elearning.dei.unipd.it/mod/page/view.php?id=1673");
		WebElement img= driver.findElement(By.cssSelector("img[alt=\"Logo SSO Unipd\"]"));
		img.click();
		
		try { //FireFox is slow, so it s better to wait a second
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement mail= driver.findElement(By.id("j_username_js"));
		WebElement password= driver.findElement(By.id("password")); 
		WebElement radio= driver.findElement(By.id("radio2")); 	
		WebElement button= driver.findElement(By.id("login_button_js")); 		
		mail.sendKeys(mailStr);
     	password.sendKeys(pwdStr);
     	radio.click();
     	button.click();
     	
     	//driver.quit();
	}
	
}
