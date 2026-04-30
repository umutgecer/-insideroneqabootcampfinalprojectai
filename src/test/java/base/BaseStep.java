package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BaseStep {
    protected WebDriver driver;
    private static final Logger logger = LogManager.getLogger(BaseStep.class);
    private WebDriverWait wait;

    public BaseStep(WebDriver webDriver) {
        this.driver = webDriver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void switchTab() {
        driver.switchTo();
    }

    public void checkPageUrl(String url) {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, url, "Page Url Did Not Match!");
        logger.info("Page URL Verified: " + currentUrl);
    }

    public void checkPageTitle(String title) {
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, title, "Page Title Did Not Match!");
        logger.info("Page Title Verified: " + actualTitle);
    }

    private WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void scrollToElement(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void clickElement(By by) {
        checkElementVisibility(by);
        WebElement element = waitForElement(by);
        element.click();
        logger.info("Element Clicked: " + by);
    }

    public void checkElementVisibility(By by) {
        try {
            scrollToElement(by);
            waitForElement(by);
            logger.info("Element is visible: " + by);
        } catch (Exception e) {
            throw new AssertionError("Element is not visible: " + by, e);
        }
    }

    public void checkMultipleElementsText(By by, String... expectedTexts) {
        checkElementVisibility(by);
        List<WebElement> elements = driver.findElements(by);
        Assert.assertTrue(elements.size() > 0, "No elements found with selector: " + by);

        for (WebElement element : elements) {
            String actualText = element.getText();
            boolean isTextMatch = false;

            for (String expectedText : expectedTexts) {
                if (actualText.contains(expectedText)) {
                    isTextMatch = true;
                    break;
                }
            }
            Assert.assertTrue(isTextMatch, "Text did not match for element: " + element + " | Found: " + actualText);
            logger.info("Element text verified: " + actualText);
        }
    }

    public void findAndClickItem(By by, By item, int maxTry) {
        scrollToElement(by);
        for (int i = 0; i < maxTry; i++) {
            WebElement element = waitForElement(by);
            element.click();
            logger.info("Filter Clicked: " + by);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            List<WebElement> targets = driver.findElements(item);
            if (!targets.isEmpty()) {
                targets.get(0).click();
                logger.info("Item Clicked " + item);
                return;
            }
            logger.warn("Item Not Found, Retrying......");
        }
        Assert.fail("Item Not Found, (" + item + "), Maximum Number Of Attempts Reached");
    }

    public void dynamicTextControl(By by) {
        wait.until(driver ->
                !driver.findElement(by).getText().trim().startsWith("0")
        );
    }

    public void scrollToElementByJs(By by) {

        WebElement element = driver.findElement(by);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

    }
}