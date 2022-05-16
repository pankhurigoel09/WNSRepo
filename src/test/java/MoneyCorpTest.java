import Pages.ForeignExchangePage;
import Pages.HomePage;
import Pages.ResultPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.netty.util.internal.ObjectUtil;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import java.io.File;
import java.time.Duration;


public class MoneyCorpTest {
    static WebDriver driver;
    static HomePage homePage1;
    static ForeignExchangePage fxpage;
    static ResultPage resultPage;

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

        homePage1.setAcceptCookie();
        homePage1.setLanguage_drpdwn();

    }
    @org.testng.annotations.Test(priority=1)
    public void verifyFXSolution(){
        homePage1.selectFXFindMore();
        fxpage.verifyFXHeader();
    }
    @org.testng.annotations.Test(priority=2)
    public void verifySearch(){

        fxpage.setSearch("international payments");
       resultPage.verifyResultHeader();
       resultPage.verifyLinkText("https://www.moneycorp.com/en-us/");
    }


@AfterTest
public static void closure()
{
    driver.quit();
}


}
