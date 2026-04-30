package page;

import base.BaseStep;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CareerPage extends BaseStep {

    public static final By SEE_ALL_TEAMS = By.xpath("//a[@class='inso-btn see-more']");
    public static final By QA_ITEM = By.xpath("//div[@data-department='Quality Assurance']");
    public static final By QA_ITEM_BUTTON = By.xpath("//div[@data-department='Quality Assurance']//a");
    public static final By JOB_LIST_AREA = By.xpath("//div[@class='postings-group']");
    public static final By LOCATION_FILTER = By.xpath("(//div[@role='button'])[2]");
    public static final By LOCATION_ISTANBUL_ITEM = By.xpath("//a[normalize-space()='Istanbul']");
    public static final By JOB_TITLE = By.xpath("//div[@class='posting']//h5");
    public static final By JOB_LOCATION = By.xpath("//span[@class='sort-by-location posting-category small-category-label location']");

    public static final By APPLY_BUTTON = By.xpath("//div[@class='posting-apply']");
    public static final By APPLY_FOR_THIS = By.xpath("//div[@class='postings-btn-wrapper']//a");
    public static final By PAGE_JOB_TITLE = By.xpath("(//form[@id='application-form']//h4)[1]");
    public static final By SUBMIT_APPLICATION = By.xpath("//button[@id='btn-submit']");

    public static final String URL = "https://insiderone.com/careers/#open-roles";
    public static final String JOB_TITLE_TEXT = "Quality Assurance";
    public static final String JOB_TITLE_SORT_TEXT = "QA";
    public static final String JOB_LOCATION_TEXT = "ISTANBUL";
    public static final String FORM_HEADER = "SUBMIT YOUR APPLICATION";

    public CareerPage(WebDriver driver) {
        super(driver);
    }

    public void goCareerPage() {
        driver.get("https://insiderone.com/careers/#open-roles");
        switchTab();
        checkPageUrl(URL);
    }

    public void seeAllPositionsAndClickQA() {
        clickElement(SEE_ALL_TEAMS);
        checkElementVisibility(QA_ITEM);
    }

    public void clickQADepartment() {
        scrollToElementByJs(QA_ITEM_BUTTON);
        dynamicTextControl(QA_ITEM_BUTTON);
        clickElement(QA_ITEM_BUTTON);
    }

    public void checkJobList() {
        checkElementVisibility(JOB_LIST_AREA);
    }

    public void locationFilterSelect() {
        findAndClickItem(LOCATION_FILTER, LOCATION_ISTANBUL_ITEM, 3);
    }

    public void checkJobListTitle() {
        checkMultipleElementsText(JOB_TITLE, JOB_TITLE_TEXT, JOB_TITLE_SORT_TEXT);
    }

    public void checkJobListLocations() {
        checkMultipleElementsText(JOB_LOCATION, JOB_LOCATION_TEXT);
    }

    public void clickApplyButtonThenGoFormPage() {
        clickElement(APPLY_BUTTON);
        clickElement(APPLY_FOR_THIS);
        checkMultipleElementsText(PAGE_JOB_TITLE, FORM_HEADER);
        checkElementVisibility(SUBMIT_APPLICATION);
    }
}