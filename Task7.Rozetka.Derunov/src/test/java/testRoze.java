import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testRoze {
    WebDriver driver;

    @BeforeClass
    public void setUp(){
        System.setProperty("webdriver.edge.driver", "browserDrivers/msedgedriver.exe");
    }

    @BeforeMethod
    public void openBrowser(){
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        driver.get("https://rozetka.com.ua/");
    }

    @Test
    public void testSearchAndFirstResult(){
        String fieldSearchXpath = "//input[@name='search']";
        WebElement searchElement = driver.findElement(By.xpath(fieldSearchXpath));
        searchElement.clear();
        searchElement.sendKeys("Монітор");

        String buttonFoundCSS = ".button.search-form__submit";
        WebElement searchButton = driver.findElement(By.cssSelector(buttonFoundCSS));
        searchButton.click();

        String firstElement =  "//li[@rzgridtilelayout][1]//span[@class='goods-tile__title']";
        WebElement firstProductTitle = driver.findElement(By.xpath(firstElement));
        String firstElementTitle = firstProductTitle.getText();

        Assert.assertTrue(firstElementTitle.contains("Монітор"));
    }

    @AfterMethod
    public void qBrowser(){
        driver.quit();
    }

}
