package com.Datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.Xls_Reader;

public class Datadriven {

	public static void main(String[] args) {
		// Excel Data Reader from utility File(EXCEl READER)
		Xls_Reader Excel = new Xls_Reader(
				"D:\\Automation\\WorkSpace\\DataDrivenExcel\\src\\com\\TestExcelData\\ExcelTestData.xlsx");
		String FirstName = Excel.getCellData("GoogleAccountCreation", "FirstName", 2);
		System.out.println(FirstName);
		String LastName = Excel.getCellData("GoogleAccountCreation", "LastName", 2);
		System.out.println(LastName);
		String UserName = Excel.getCellData("GoogleAccountCreation", "UserName", 2);
		System.out.println(UserName);
		String Password = Excel.getCellData("GoogleAccountCreation", "Password", 2);
		System.out.println(Password);
		String comfirm = Excel.getCellData("GoogleAccountCreation", "comfirm", 2);
		System.out.println(comfirm);

		// WebDriver Code for Registering Account
		System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");

		driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(FirstName);
		driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(LastName);
		driver.findElement(By.xpath("//input[@id='username']")).sendKeys(UserName);
		driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(Password);
		driver.findElement(By.xpath("//input[@name='ConfirmPasswd']")).sendKeys(Password);
		WebElement Login=driver.findElement(By.xpath("//*[@class='RveJvd snByac']"));
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", Login);
		System.out.print("Account Created");
		driver.quit();

	}

}
