import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class GoogleSearch {

    private final String googleUrl = "https://www.google.com/";
    private final String query = "softserve it academy";
    private final String softserveUrl = "https://career.softserveinc.com/uk-ua/learning-and-certification";
    private final By searchGoogle = By.name("q");
    private final By searchLink = By.xpath("//*[@id=\"rso\"]/div[1]/div/div[1]/div/div/div/div[1]/a/h3");
    private final By searchElement = By.xpath("//*[@id=\"app-client\"]/header/div[1]/div/div[1]/a");

    static WebDriver driver;

    @Test (description = "Verify Search")
    public void verifySearch() {
        System.setProperty("webdriver.edge.driver", "browserDrivers/msedgedriver.exe");
        driver = new EdgeDriver();
        WebDriverWait wait = new WebDriverWait(driver,5);

        //Option Edge
        driver.manage().window().maximize();

        //Google search
        driver.get(googleUrl);
        driver.findElement(searchGoogle).sendKeys(query);
        driver.findElement(searchGoogle).sendKeys(Keys.ENTER);

        //Equals URL
        driver.findElements(searchLink).get(0).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(searchElement));
        String CurrentURL = driver.getCurrentUrl();
        Assert.assertTrue(CurrentURL.equals(softserveUrl));
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
