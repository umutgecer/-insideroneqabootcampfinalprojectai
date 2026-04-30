package tests;

import base.BaseTest;
import page.CareerPage;
import page.HomePage;
import org.testng.annotations.*;

public class insideroneCase extends BaseTest {
    @BeforeMethod
    public void initializePages() {
        homePage = new HomePage(driver);
        careerPage = new CareerPage(driver);
    }

    @Test
    public void checkingTheDetailsOfJobPostingsOnInsider() {
        homePage.checkHomePageIsOpened();
        homePage.checkHomePagePage();
        careerPage.goCareerPage();
        careerPage.seeAllPositionsAndClickQA();
        careerPage.clickQADepartment();
        careerPage.checkJobList();
        careerPage.locationFilterSelect();
        careerPage.checkJobListLocations();
        careerPage.checkJobListTitle();
        careerPage.clickApplyButtonThenGoFormPage();
    }
}