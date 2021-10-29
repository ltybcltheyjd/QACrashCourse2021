import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import static com.sun.deploy.cache.Cache.copyFile;

public class Test1 {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.edge.driver", "browserDrivers/msedgedriver.exe");
    }

    @BeforeMethod
    public void openBrowser(){
        driver = new EdgeDriver();
        //driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");

    }

    @Test
    public void testSearchAndFirstResult(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        String fieldSearchXpath = "//input[@name='search']";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(fieldSearchXpath)));
        WebElement searchElement = driver.findElement(By.xpath(fieldSearchXpath));
        searchElement.clear();
        searchElement.sendKeys("Монітор");

        String buttonFoundCSS = ".button.search-form__submit";
        WebElement searchButton = driver.findElement(By.cssSelector(buttonFoundCSS));
        searchButton.click();

        String firstElement =  "//li[@rzgridtilelayout][1]//span[@class='goods-tile__title']";
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(firstElement)));
        WebElement firstProductTitle = driver.findElement(By.xpath(firstElement));
        firstProductTitle.click();

        String buttonBuyCSS = "button.buy-button.button_with_icon";
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(buttonBuyCSS)));
        WebElement buttonBuy = driver.findElement(By.cssSelector(buttonBuyCSS));
        buttonBuy.click();

        String buttonSubmitCss = "a.cart-receipt__submit";
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(buttonSubmitCss)));
        WebElement buttonSubmit = driver.findElement(By.cssSelector(buttonSubmitCss));
        buttonSubmit.click();


    }

    @AfterMethod
    public void takeScreenshot(ITestResult result) {
        if (!result.isSuccess())
            try {
                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                copyFile(scrFile, new File(result.getName() + "[" + LocalDate.now()
                        + "][" + System.currentTimeMillis() + "].png"));
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

    }
    @AfterClass
    public void qBrowser(){
        driver.quit();
    }

}
