package com.Datadriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.Xls_Reader;

public class paramerizedTest {

	public static void main(String[] args) {

		Xls_Reader Excel = new Xls_Reader(
				"D:\\Automation\\WorkSpace\\DataDrivenExcel\\src\\com\\TestExcelData\\ExcelTestData.xlsx");
		int rowcount = Excel.getRowCount("GoogleAccountCreation");

		System.setProperty("webdriver.chrome.driver", "D:\\Automation\\Selenium\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://accounts.google.com/signup/v2/webcreateaccount?flowName=GlifWebSignIn&flowEntry=SignUp");

		// for loop for reading multiple rows
		for (int rownum = 2; rownum <= rowcount; rownum++) {
			System.out.println("********");
			String FirstName = Excel.getCellData("GoogleAccountCreation", "FirstName", rownum);
			System.out.println(FirstName);
			String LastName = Excel.getCellData("GoogleAccountCreation", "LastName", rownum);
			System.out.println(LastName);
			String UserName = Excel.getCellData("GoogleAccountCreation", "UserName", rownum);
			System.out.println(UserName);
			String Password = Excel.getCellData("GoogleAccountCreation", "Password", rownum);
			System.out.println(Password);
			String comfirm = Excel.getCellData("GoogleAccountCreation", "comfirm", rownum);
			System.out.println(comfirm);

			driver.findElement(By.xpath("//input[@id='firstName']")).clear(); // clear Textbox data before using sendkeys
			driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(FirstName);

			driver.findElement(By.xpath("//input[@id='lastName']")).clear();
			driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(LastName);

			driver.findElement(By.xpath("//input[@id='username']")).clear();
			driver.findElement(By.xpath("//input[@id='username']")).sendKeys(UserName);

			driver.findElement(By.xpath("//input[@name='Passwd']")).clear();
			driver.findElement(By.xpath("//input[@name='Passwd']")).sendKeys(Password);

			driver.findElement(By.xpath("//input[@name='ConfirmPasswd']")).clear();
			driver.findElement(By.xpath("//input[@name='ConfirmPasswd']")).sendKeys(Password);

			WebElement Login = driver.findElement(By.xpath("//*[@class='RveJvd snByac']"));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", Login);
			System.out.println("Account Created");
		}
		driver.quit();

	}

}
