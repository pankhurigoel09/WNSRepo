import Pages.ForeignExchangePage;
import Pages.HomePage;
import Pages.ResultPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.util.internal.ObjectUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;
import utils.ExtentTestManager;

import java.io.File;
import java.time.Duration;


public class MoneyCorpTest {
    static WebDriver driver;
    static HomePage homePage1;
    static ForeignExchangePage fxpage;
    static ResultPage resultPage;
 public ExtentReports extent;
    @BeforeSuite
    public void beforeSuite() {
        extent = ExtentManager.getReporter("MoneyCorp.html");
        extent.addSystemInfo("Browser","Chrome");
    }

@BeforeTest
    public static void setup() {
    WebDriverManager.chromedriver().setup();
    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/chromedriver.exe");
      driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https:\\www.moneycorp.com\\en-gb\\");
    driver.manage().window().maximize();
    homePage1 = new HomePage(driver);
    fxpage=new ForeignExchangePage(driver);
    resultPage=new ResultPage(driver);
    }

@org.testng.annotations.Test(priority=-1)
    public void verifyLangChange(){
    ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        homePage1.setAcceptCookie();
        homePage1.setLanguage_drpdwn();
        test.setDescription("Language changed");
    ExtentTestManager.endTest();
    }
    @org.testng.annotations.Test(priority=1)
    public void verifyFXSolution(){
        ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        homePage1.selectFXFindMore();
        fxpage.verifyFXHeader();
        ExtentTestManager.endTest();
    }
    @org.testng.annotations.Test(priority=2)
    public void verifySearch(){
        ExtentTest test = ExtentTestManager.startTest(Thread.currentThread().getStackTrace()[1].getMethodName());
        fxpage.setSearch("international payments");
       resultPage.verifyResultHeader();
       resultPage.verifyLinkText("https://www.moneycorp.com/en-us/");
        ExtentTestManager.endTest();
    }

    @AfterMethod
    public void Teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName());//to add name in extent report
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable());//to add error/exception in extent report
        } else if (result.getStatus() == ITestResult.SKIP) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

        }


        ExtentManager.getReporter().endTest(ExtentTestManager.getTest());
        ExtentManager.getReporter().flush();
    }
@AfterTest
public static void closure()
{
    driver.quit();
}


}
