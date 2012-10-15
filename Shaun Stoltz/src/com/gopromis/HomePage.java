package com.gopromis;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class HomePage extends BasicSetUp{
	public WebDriver driver;
	public String baseurl;
	
	public HomePage(WebDriver driver, String baseurl) throws Exception{
		this.driver=driver;
		this.baseurl=baseurl;
		driver.get(baseurl);
		sleep(5);
	}
	
	public void login(String username, String password) throws Exception{
		writeText("Logging in....@ "+getDate());
		System.out.println("Logging in....@ "+getDate());
		try{
			
//			driver.findElement(By.linkText("Login")).click();
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.id("LoginForm_username")));
			driver.findElement(By.id("LoginForm_username")).clear();
			driver.findElement(By.id("LoginForm_username")).sendKeys(username);
			driver.findElement(By.id("LoginForm_password")).clear();
			driver.findElement(By.id("LoginForm_password")).sendKeys(password);
			driver.findElement(By.name("yt0")).click();
		}
		catch(Exception e){
			writeText("Error: While Logging in....@ "+getDate());
			System.out.println("Error: While Logging in....@ "+getDate());
		}
	}
	
	
	
}
