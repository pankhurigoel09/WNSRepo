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

public class ResultPage {
    private WebDriver driver;
    WebDriverWait wait;
    public ResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @FindBy(xpath = "//h2[text()='Search Moneycorp']")
    private WebElement Search_header;
    @FindBy(xpath = "//div[@class='results']//a[@class='title u-m-b2']")
    private  List<WebElement> result_link;

    public void verifyResultHeader() {

        wait.until(ExpectedConditions.visibilityOf(Search_header));
        Assert.assertEquals(true,Search_header.isDisplayed());

    }

    public void verifyLinkText(String text) {
       try{
        for (int i = 0; i < result_link.size(); i++) {
            Assert.assertEquals(true, result_link.get(i).getAttribute("href").startsWith(text),"Matches");
        }}
       catch (Exception e)
       {
           e.printStackTrace();
       }

    }
}
