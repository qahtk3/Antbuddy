package Login;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Sheet;
import jxl.Workbook;

public class Login{
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	File file = new File("D:\\test.xls");//replace by path of file excel



	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver","D:\\chromedriver\\chromedriver.exe");//replace by path of chrome driver
		driver = new ChromeDriver();
		baseUrl = "https://ant.chat";
	}
	//login email + pass
	public void log_email(String lm,String lm2) throws Exception{
		driver.findElement(By.id("js-email")).clear();
		driver.findElement(By.id("js-password")).clear();
		driver.findElement(By.id("js-email")).sendKeys(lm);

		driver.findElement(By.id("js-password")).sendKeys(lm2);

		driver.findElement(By.id("js-btn-submit")).click();

	}


	@Test
	public void testUntitled2() throws Exception {

		try {
			File file = new File("D:\\test.xls");//replace by path of file excel
			Workbook wb = Workbook.getWorkbook(file);
			Sheet sheet = wb.getSheet(0);
			int rows = sheet.getRows();
			driver.manage().window().maximize();
			for(int row=1;row <rows;row++){
				driver.get(baseUrl + "/");
				driver.findElement(By.linkText("LOG IN")).click();
				Thread.sleep(2000);
				String domain=sheet.getCell(1,row).getContents();
				log_email(sheet.getCell(2,row).getContents(), sheet.getCell(3,row).getContents());
				System.out.print("\nemail:"+sheet.getCell(2,row).getContents()+" , ");
				System.out.print("pass:"+sheet.getCell(3,row).getContents());
				System.out.print("->"+domain);
				Thread.sleep(3500);
				//show errors
				if( isElementPresent(By.xpath("//form[@id='js-login-form']/div/p"))==true){
					System.out.print("->");
					System.out.print(driver.findElement(By.xpath("//form[@id='js-login-form']/div/p")).getText());
					System.out.print(driver.findElement(By.xpath("//form[@id='js-login-form']/div[2]/p")).getText());
					System.out.print(driver.findElement(By.id("js-message-response")).getText());
				}
				else{
					if (isElementPresent(By.linkText(domain))){
						driver.findElement(By.linkText(domain)).click();
						Thread.sleep(3500);
						driver.findElement(By.id("tour-username")).click();
						driver.findElement(By.linkText("exit_to_app Sign out")).click();
						System.out.print("-->logout");
					}
					else{
						System.out.print("-> cannot find domain "+domain);
						driver.findElement(By.xpath("//div[@id='login-form']/div[2]/div/button")).click();
						driver.findElement(By.linkText("Sign Out")).click();
					}
				}
			}


		}
		catch (Exception ex) {
			Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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

