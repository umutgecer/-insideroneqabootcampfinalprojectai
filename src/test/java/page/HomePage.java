package page;

import base.BaseStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseStep {

    public static final By COOKIE_ACCEPT_BUTTON = By.id("wt-cli-accept-all-btn");
    public static final By HEADER_MENU = By.xpath("//div[@class='header-menu']");
    public static final By FIRST_CONTENT = By.xpath("//div[@class='homepage-hero-content']//h1");
    public static final By PLATFORM_BUTTON = By.xpath("(//div[@class='header-menu-action']//a)[2]");
    public static final By TOP_DEMO_BUTTON = By.xpath("(//div[@class='header-menu-action']//a)[3]");
    public static final By FIRST_INPUT = By.id("email");
    public static final By FIRST_INPUT_BUTTON = By.xpath("(//button[@class='redirect-button'])[1]");
    public static final By ONE_PLATFORM_AREA = By.xpath("//div[@class='homepage-capabilities-main']");
    public static final By ONE_PLATFORM_AREA_BUTTONS = By.xpath("//div[@class='homepage-capabilities-body-buttons']");
    public static final By CHANNELS_AREA = By.xpath("//div[@class='homepage-channels-main']");
    public static final By CASE_STUDY_AREA = By.xpath("//div[@class='homepage-case-study-main']");
    public static final By RESOURCES_WRAPPER = By.xpath("//div[@class='homepage-resources-wrapper']");
    public static final By CALL_TO_ACTION_AREA = By.xpath("//div[@class='homepage-call-to-action-content']");
    public static final By FOOTER = By.xpath("//div[@class='footer-main']");

    public static final String URL = "https://insiderone.com/";
    public static final String PAGE_TITLE = "Insider One | #1 Platform for AI-Powered Customer Engagement";

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void checkHomePageIsOpened() {
        checkPageUrl(URL);
        checkPageTitle(PAGE_TITLE);
        clickElement(COOKIE_ACCEPT_BUTTON);
    }

    public void checkHomePagePage() {
        checkElementVisibility(HEADER_MENU);
        checkElementVisibility(FIRST_CONTENT);
        checkElementVisibility(PLATFORM_BUTTON);
        checkElementVisibility(TOP_DEMO_BUTTON);
        checkElementVisibility(FIRST_INPUT);
        checkElementVisibility(FIRST_INPUT_BUTTON);
        checkElementVisibility(ONE_PLATFORM_AREA);
        checkElementVisibility(ONE_PLATFORM_AREA_BUTTONS);
        checkElementVisibility(CHANNELS_AREA);
        checkElementVisibility(CASE_STUDY_AREA);
        checkElementVisibility(RESOURCES_WRAPPER);
        checkElementVisibility(CALL_TO_ACTION_AREA);
        checkElementVisibility(FOOTER);
    }
}