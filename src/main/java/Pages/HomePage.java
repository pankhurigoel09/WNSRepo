package Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    public WebDriver driver;
    WebDriverWait wait ;
     public HomePage(WebDriver driver) {
         this.driver = driver;
         PageFactory.initElements(driver, this);
         wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }


    //Using FindBy for locating elements
    @FindBy(id = "onetrust-accept-btn-handler")
    private WebElement acceptCookie;
    @FindBy(id = "language-dropdown-flag")
    private WebElement language_drpdwn;
    @FindBy(xpath = "//div[@class='nav-inner']//a[@aria-label='USA English']")
    private WebElement usEnglish;
    @FindBy(xpath = "//a[@href='/en-us/business/foreign-exchange-solutions/']//span")
    private WebElement FXFindMore_Btn;




    public void setAcceptCookie() {
       try {

           wait.until(ExpectedConditions.elementToBeClickable(acceptCookie)).click();
       }catch(Exception e)
       {
           e.printStackTrace();
       }
    }
    public void setLanguage_drpdwn() {
try{
        wait.until(ExpectedConditions.visibilityOf(language_drpdwn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(usEnglish)).click();
    }catch (Exception e)
    {
        e.printStackTrace();
    }}
    public void selectFXFindMore() {
       try {

           JavascriptExecutor js = (JavascriptExecutor) driver;
           js.executeScript("window.scrollBy(0,1050)");
           wait.until(ExpectedConditions.visibilityOf(FXFindMore_Btn)).click();

       }catch (Exception e)
       {
           e.printStackTrace();
       }

    }

}
