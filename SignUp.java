import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SignUp {
	private ChromeDriver driver;
 	WebElement element;
 	private String baseUrl;
	
@Before
public void setUp() throws Exception {
	//MAC 
	System.setProperty("webdriver.chrome.driver", "/Users/hoavu/Documents/server/chromedriver");
	
//Windows	
//	System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	baseUrl = "https://ant.chat";
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
}

//TC001: SignUp failed 
@Test 
	public void TC01SignUp (){
	driver.get(baseUrl + "/");
    driver.findElement(By.linkText("TRY FOR FREE")).click();
    driver.findElement(By.id("js-name")).clear();
    driver.findElement(By.id("js-name")).sendKeys("qahtktr9");
    driver.findElement(By.id("js-email")).clear();
    driver.findElement(By.id("js-email")).sendKeys("qahtktr9@gmail.com");
    driver.findElement(By.id("js-password")).clear();
    driver.findElement(By.id("js-password")).sendKeys("123456");
    driver.findElement(By.id("js-organization")).clear();
    driver.findElement(By.id("js-organization")).sendKeys("123456");
    driver.findElement(By.id("js-organization")).clear();
    driver.findElement(By.id("js-organization")).sendKeys("");
    driver.findElement(By.id("js-domain")).clear();
    driver.findElement(By.id("js-domain")).sendKeys("");
    driver.findElement(By.id("js-organization")).clear();
    driver.findElement(By.id("js-organization")).sendKeys("qahtktr9");
    driver.findElement(By.id("js-btn-submit")).click();
    driver.findElement(By.id("js-btn-submit")).click();
}
 
    public static void verifyEqual(String Actual, String Expected){
		
		if(Actual.equals(Expected)){
			System.out.println("Actual : " + Actual + " equals " + " Expected : " + Expected );
		}else{
			System.out.println("Actual : " + Actual + " Not equals " + " Expected : " + Expected );
		}
		
		Assert.assertEquals(Expected, Actual);
}

	
}
