package loginTestCase;


import org.testng.annotations.Test;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Login {
	
	String [][]data =null;
		
	WebDriver driver;
	
	
	@DataProvider(name="LoginData")
	public String[][] Dataprovider() throws BiffException, IOException
	{
		data =getExcel();
		return data;
	}
	
	public String[][] getExcel() throws BiffException, IOException
	{
		FileInputStream excel  = new FileInputStream("C:\\Users\\manib\\eclipse-workspace2\\DemoProject\\excel\\loginData.xls");
		 Workbook wbook = Workbook.getWorkbook(excel);
		 Sheet sht = wbook.getSheet(0);
		 int rows = sht.getRows();
		 int columns = sht.getColumns();
		 String excelData [][] = new String [rows-1][columns];
		 for(int i=1;i<rows;i++)
		 {
			 for(int j=0;j<columns;j++)
			 {
				 excelData [i-1][j]=sht.getCell(j,i).getContents();
			 }
		 }
		 return excelData;
	}
	@BeforeTest
	public void Beforetest() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\manib\\eclipse-workspace2\\DemoProject\\Driver\\chromedriver.exe");
		 driver = new ChromeDriver();
	}
	
	@AfterTest
	public void Aftertest()
	{
		driver.quit();
	}
	@Test(dataProvider = "LoginData")
	public void LoginWithCorrectValues(String Uname, String Pwd) throws InterruptedException

	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\manib\\eclipse-workspace2\\DemoProject\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		Thread.sleep(3000);
		WebElement Username = driver.findElement(By.xpath("//input[@name='username']"));
		Username.sendKeys(Uname);
		Thread.sleep(3000);
		WebElement Password = driver.findElement(By.name("password"));
		Password.sendKeys(Pwd);
		Thread.sleep(3000);
		WebElement Login = driver.findElement(By.xpath("//button[@type='submit']"));
		Login.click();
			
	}

}
