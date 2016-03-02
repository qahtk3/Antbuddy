import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Sheet;
import jxl.Workbook;
//import net.sourceforge.htmlunit.corejs.javascript.ast.ThrowStatement;


public class SignUp {
	private ChromeDriver driver;
 	WebElement element;
 	private String baseUrl;
 	private StringBuffer verificationErrors = new StringBuffer();
 	File file = new File("/Users/hoavu/Documents/workspace/AntBuddy/signupaccount.xls");
	
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
//signup new account 

	public void sign_up (String fullname, String username, String email, String pass, String coname, String doname ) throws Exception  {
 //   driver.findElement(By.linkText("TRY FOR FREE")).click();
    driver.findElement(By.id("js-name")).clear();
    driver.findElement(By.id("js-name")).sendKeys(fullname);
    driver.findElement(By.id("js-name")).clear();
    driver.findElement(By.id("js-name")).sendKeys(username);
    driver.findElement(By.id("js-email")).clear();
    driver.findElement(By.id("js-email")).sendKeys(email);
    driver.findElement(By.id("js-password")).clear();
    driver.findElement(By.id("js-password")).sendKeys(pass);
    driver.findElement(By.id("js-organization")).clear();
    driver.findElement(By.id("js-organization")).sendKeys(coname);
    driver.findElement(By.id("js-domain")).clear();
    driver.findElement(By.id("js-domain")).sendKeys(doname);
    driver.findElement(By.id("js-btn-submit")).click();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
 
@Test 
	public void testSignUp() throws Exception {
		try { 
			File file = new File ("/Users/hoavu/Documents/workspace/AntBuddy/signupaccount.xls");
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			int rows = sheet.getRows();
			driver.manage().window().maximize();
			for(int row=1;row<rows;row++){
				driver.get(baseUrl + "/");
				driver.findElement(By.linkText("TRY FOR FREE")).click();
			//	driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
				Thread.sleep(3000);
				
				sign_up(sheet.getCell(1, row).getContents(),
						sheet.getCell(2, row).getContents(),
						sheet.getCell(3, row).getContents(),
						sheet.getCell(4,row).getContents(),
						sheet.getCell(5, row).getContents(),
						sheet.getCell(6,row).getContents());
				System.out.print("fullname:"+sheet.getCell(1,row).getContents()+" , ");
				System.out.print("username:"+sheet.getCell(2,row).getContents()+" , ");
				System.out.print("email:"+sheet.getCell(3,row).getContents()+" , ");
				System.out.print("password:"+sheet.getCell(4,row).getContents()+" , ");
				System.out.print("nameorg:"+sheet.getCell(5,row).getContents()+" , ");
				System.out.print("domainorg:"+sheet.getCell(6,row).getContents()+"-->"+"\n");
				
				//show error 
				if( isElementPresent(By.xpath("//[@id='signup']/div/div[2]/form/div/p"))==true){
					System.out.print("->");
					System.out.print(driver.findElement(By.xpath("//[@id='signup']/div/div[2]/form/div[1]/p")).getText());
					System.out.print(driver.findElement(By.xpath("//[@id='signup']/div/div[2]/form/div[2]/p")).getText());
					System.out.print(driver.findElement(By.xpath("//[@id='signup']/div/div[2]/form/div[3]/p")).getText());
					System.out.print(driver.findElement(By.xpath("//[@id='signup']/div/div[2]/form/div[4]/p")).getText());
					System.out.print(driver.findElement(By.xpath("//[@id='signup']/div/div[2]/form/div[5]/p")).getText());
					System.out.print(driver.findElement(By.xpath("//[@id='signup']/div/div[2]/form/div[6]/p")).getText());
					System.out.print(driver.findElement(By.id("verify")).getText());
			}
		}
	}
				catch (Exception ex) {
			Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

}
		

