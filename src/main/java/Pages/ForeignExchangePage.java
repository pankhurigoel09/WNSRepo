package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ForeignExchangePage {
    private WebDriver driver;
    WebDriverWait wait;
    public ForeignExchangePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//h1[text()='Foreign exchange solutions for your business']")
    private WebElement Fx_header;
    @FindBy(xpath = "//input[@placeholder='Search']")
    private List<WebElement> search_btn;
    @FindBy(xpath = "(//input[@type='submit'])[2]")
    private WebElement searchSubmit_btn;

    public void verifyFXHeader() {

       wait.until(ExpectedConditions.visibilityOf(Fx_header));
        Assert.assertEquals(true,Fx_header.isDisplayed());

    }
    public void setSearch(String text) {
               search_btn.get(1).sendKeys(text);
        searchSubmit_btn.click();

    }
}
