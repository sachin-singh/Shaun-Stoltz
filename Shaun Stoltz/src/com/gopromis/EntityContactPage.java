package com.gopromis;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EntityContactPage extends BasicSetUp {
	
	public WebDriver driver;
	public EntityContactPage(WebDriver driver){
		this.driver=driver;
	}
	
	
	public void createEntity(Properties prop) throws Exception{
		writeText("Creating new Entity....@ "+getDate());
		System.out.println("Creating new Entity....@ "+getDate());
		try{
			
			new WebDriverWait(driver, 100).until(ExpectedConditions.presenceOfElementLocated(By.id("btnCreateEntity")));
			driver.findElement(By.id("btnCreateEntity")).click();
			new Select(driver.findElement(By.id("ddl_legal_form_type_id"))).selectByVisibleText(prop.getProperty("legalFormType"));
			driver.findElement(By.id("txt_legal_form_name")).clear();
			driver.findElement(By.id("txt_legal_form_name")).sendKeys(prop.getProperty("legalFormName"));
			driver.findElement(By.id("txt_tax_payer")).clear();
			driver.findElement(By.id("txt_tax_payer")).sendKeys("30-0563487");
			driver.findElement(By.id("txt_eligible")).clear();
			driver.findElement(By.id("txt_eligible")).sendKeys("Yes");
			driver.findElement(By.id("txt_telephone")).clear();
			driver.findElement(By.id("txt_telephone")).sendKeys("7169999999");
			driver.findElement(By.id("txt_fax")).clear();
			driver.findElement(By.id("txt_fax")).sendKeys("7169991111");
			driver.findElement(By.id("txt_email")).clear();
			driver.findElement(By.id("txt_email")).sendKeys("admin@cattonusinc.com");
			driver.findElement(By.id("txt_address1")).clear();
			driver.findElement(By.id("txt_address1")).sendKeys("123 ABC Lane");
			driver.findElement(By.id("txt_address2")).clear();
			driver.findElement(By.id("txt_address2")).sendKeys("Suit 100");
			driver.findElement(By.id("txt_address3")).clear();
			driver.findElement(By.id("txt_address3")).sendKeys("NA");
			driver.findElement(By.id("txt_zip_postal")).clear();
			driver.findElement(By.id("txt_zip_postal")).sendKeys("14201");
			new Select(driver.findElement(By.id("ddl_entity_country"))).selectByVisibleText("China");
			driver.findElement(By.id("txt_region_state")).clear();
			driver.findElement(By.id("txt_region_state")).sendKeys("NY");
			driver.findElement(By.id("txt_website")).clear();
			driver.findElement(By.id("txt_website")).sendKeys("www.cattonusinc.com");
			new Select(driver.findElement(By.id("ddl_month"))).selectByVisibleText("April");
			new Select(driver.findElement(By.id("ddl_day"))).selectByVisibleText("30");
			new Select(driver.findElement(By.id("ddl_payment_method"))).selectByVisibleText("EFT");
			driver.findElement(By.id("btnEntitySave")).click();
			
			
		}
		catch(Exception e){
			writeText("Error: While Creating new Entity....@ "+getDate());
			System.out.println("Error: While Creating new Entity....@ "+getDate());
		}
	}

}
