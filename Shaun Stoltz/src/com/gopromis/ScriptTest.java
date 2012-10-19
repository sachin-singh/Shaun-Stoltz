package com.gopromis;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class ScriptTest extends BasicSetUp {
	
	HomePage homePage;
	EntityContactPage entityContactPage;
	Properties prop;
	public String baseurl;
	public String username;
	public String password;
	public String adminusername;
	public String adminpassword;
	
	
	@Test(description="Read Config")
	public ScriptTest(){
		prop = new Properties();
		try{
			prop.load(new FileInputStream("config.properties"));
			baseurl= prop.getProperty("baseurl");
			username= prop.getProperty("username");
			password= prop.getProperty("password");
			adminusername= prop.getProperty("admin_username");
			adminpassword=prop.getProperty("admin_password");
			writeText("Reading Config");
			System.out.println("Reading Config");
			
		}
		catch(Exception e){
			writeText("Error: Couldn't Read Config");
			System.out.println("Error: Couldn't Read Config");
		}
	}
	
	@Test(description="log in")
	public void login() throws Exception{
		homePage = new HomePage(driver, baseurl);
		homePage.login(adminusername, adminpassword);
	}
	
	@Test(description="Create New Entity")
	public void createEntityTest() throws Exception{
		login();
		entityContactPage = homePage.gotoEntityandContact();
		entityContactPage.createEntity(prop);
		
	}
	
	@Test(description = "Search Entity")
	public void searchEntityTest() throws Exception{
		String legalFormName = prop.getProperty("search_legalFormName");
		login();
		entityContactPage = homePage.gotoEntityandContact();
		
		entityContactPage.searchEntityByLegalFormName(legalFormName);
		sleep(10);
		isTextPresent(By.xpath("//div[@id='yw0']/table/tbody/tr/td[2]"),legalFormName);
	}
	
	
}
