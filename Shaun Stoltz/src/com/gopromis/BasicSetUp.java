package com.gopromis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite; 

public class BasicSetUp {
	
	protected WebElement webElement;
	//The driver.
	protected static WebDriver driver;	
	protected static WebDriverBackedSelenium selenium;
	
	protected WebDriverWait wait;
	
	// A String which stores a random number in string format.
	protected static String randoms = random();
	
	/** Object which contains Strings of Error.
	 * */
	protected StringBuffer verificationErrors = new StringBuffer();
	
	
	
	/** Function which sets up browser and defines base url on starting of each test.
	 * @throws Exception 
	 */
	
	@BeforeSuite (alwaysRun = true)
	public void setUp() throws Exception { 
		
		driver= new FirefoxDriver();			
		selenium =new WebDriverBackedSelenium(driver, "");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		startTest();
	}
	
	/**
	 * WriteText is a function to write logs.
	 * 
	 *
	 * @param String
	 */
	public static void writeText(String s){
		FileWriter fWriter = null;
		BufferedWriter writer = null;		
			try {
					fWriter = new FileWriter("TestLogs-"+randoms+".txt", true);
					writer = new BufferedWriter(fWriter);
					writer.append(s);
					writer.newLine();
					writer.close();
					
			} catch (Exception e) {
			}
		}
	
	/**
	 * Generates implicit wait to poll the DOM for a certain amount
	 * of time when driver when trying to find an element or elements if they 
	 * are not immediately available.
	 *
	 * @param Time in seconds.
	 */
	public static void waitBetween(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 
	 * @return Current date and time
	 */
	public static String getDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		   //get current date time with Date()
		   Date date = new Date();
		    return dateFormat.format(date);
	}
	
	/**
	 
	 * @return String of random integer.
	 */
	public static String random(){
		Integer d = Integer.valueOf((int) (Math.random() *10000));
		return Integer.toString( d);
	}
	
	
	/**
	 * Capture screen while error has occurred.
	 *
	 * @param  File name of Image
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void captureScreen(String err) throws IOException {
		WebDriver augmentedDriver = new Augmenter().augment(driver);
      File scrFile = ((TakesScreenshot)augmentedDriver).
                          getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(scrFile, new File("c:\\tmp\\"+err+".png"));
	}
	
	/**
	 * Initial of logs in starting of each test.
	 */
	public static void startTest(){
		writeText("==========================================");
		writeText("Test Started @ " + getDate());
		
		System.out.println("==========================================");
		System.out.println("Test Started @ " + getDate());
		
	}
	
	
	 
	

	/**
	 * Checks if is element present.
	 *
	 * @param By object containing identifier for a element.
	 * @return true, if element is present
	 * 			false, if element is not present
	 */
	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} 
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * Tear down at the end.
	 *
	 * @throws Exception 
	 */
	@AfterSuite
	public void tearDown() throws Exception {
		System.out.println("Test completed @ "+getDate());
		System.out.println("==========================================");
		writeText("Test completed @ "+getDate());
		writeText("==========================================");
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}
	
	
	
	public void sleep(long time) throws InterruptedException{
		Thread.sleep(time*1000);
	}
	
	
	public void verifyElementPresent(By by, String id){
		try{
			Assert.assertTrue(isElementPresent(by));  
		}
		catch(Exception e){
			verificationErrors.append(e.toString());
			writeText(id + "NOT FOUND!");
			System.out.println(id + "NOT FOUND!");
		}
	}
	
	public void verifyTrue(boolean condition, String errorMessage) throws Exception{
		boolean result = condition;
		if(!result){
			captureScreen("verification_error"+randoms);
			writeText(errorMessage+"....@ "+ getDate());
			System.out.println(errorMessage+"....@ "+ getDate());
		}
	}
	
	
	public void isTextPresent(String text){  
		 boolean result = selenium.isTextPresent(text);  
		if(result == true){ 
			writeText(text + " FOUND!");
			System.out.println(text + " FOUND!");
		}
		else{
			writeText(text + " NOT FOUND!");
			System.out.println(text + " NOT FOUND!");
		}
	}
	
	public  String[] readText(String fileName) { 
		 ArrayList<String> list = new ArrayList<String>();
		 String[] rolesList = null;
		 try{ 
			 FileInputStream fstream = new FileInputStream(fileName);
			 DataInputStream in = new DataInputStream(fstream);
			 BufferedReader br = new BufferedReader(new InputStreamReader(in));
			 String strLine; 
			while ((strLine = br.readLine()) != null)   {  
				list.add(strLine);
				}  
			rolesList= list.get(0).split(",");
			
			in.close();
			writeText(fileName+ " read successfully.... @" + getDate());
			System.out.println(fileName+ " read successfully.... @" + getDate());
			return rolesList;
			}
		 catch (Exception e){ 
				writeText("Error while reading "+fileName+".... @"+ getDate());
				System.out.println("Error while reading "+fileName+".... @"+ getDate());	
				System.err.println("Error: " + e.getMessage());
				return rolesList;
			} 	 
	 }
	
	
	public EntityContactPage gotoEntityandContact() throws Exception{
		try{
			writeText("Navigating to Entity and Contact Page.... @ "+ getDate());
			System.out.println("Navigating to Entity and Contact Page.... @ "+ getDate());
			 
			new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Entity and Contact")));
			driver.findElement(By.linkText("Entity and Contact")).click();
			
			return new EntityContactPage(driver);
		}
		catch(Exception e){
			captureScreen("gotoServiceNodesPage_error"+randoms);
			System.out.println("Error: During navigating to Entity and Contact Page @ "+ getDate());
			writeText("Error: During navigating to Entity and Contact  Page @ "+ getDate());
			return new EntityContactPage(driver);
		}
	}
}