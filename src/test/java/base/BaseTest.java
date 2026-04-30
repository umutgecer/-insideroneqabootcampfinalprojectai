package base;

import org.openqa.selenium.chrome.ChromeOptions;
import page.CareerPage;
import page.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import reporting.TestReportListener;

@Listeners(TestReportListener.class)
public class BaseTest {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected HomePage homePage;
    protected CareerPage careerPage;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("https://useinsider.com");

        logger.info("...STARTING TEST...");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("...TEST FINISHED...");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}