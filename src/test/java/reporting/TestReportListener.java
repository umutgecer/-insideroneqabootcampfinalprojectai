package reporting;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class TestReportListener implements ITestListener {
    private static final ThreadLocal<ExtentTest> TEST_NODE = new ThreadLocal<>();
    private static final ExtentReports REPORT = ReportManager.getInstance();

    @Override
    public void onStart(ITestContext context) {
        ensureDirectory(Paths.get("test-output", "custom-report", "evidence"));
    }

    @Override
    public void onFinish(ITestContext context) {
        try {
            REPORT.flush();
        } catch (Exception e) {
            // Reporter hatasi test sonucunu etkilemesin.
            System.err.println("Rapor flush hatasi: " + e.getMessage());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        String className = result.getTestClass().getName();
        String methodName = result.getMethod().getMethodName();
        ExtentTest test = REPORT.createTest(methodName)
                .assignCategory(className);
        TEST_NODE.set(test);
        test.info("Test basladi: " + className + "." + methodName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        finalizeTest(result, Status.PASS, "Test basarili.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        finalizeTest(result, Status.FAIL, "Test basarisiz.");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        finalizeTest(result, Status.SKIP, "Test atlandi.");
    }

    private void finalizeTest(ITestResult result, Status status, String message) {
        ExtentTest test = TEST_NODE.get();
        if (test == null) {
            return;
        }
        test.log(status, message + " Sure: " + getDurationMs(result) + " ms");

        if (result.getThrowable() != null) {
            if (status == Status.FAIL) {
                test.fail(result.getThrowable());
            } else if (status == Status.SKIP) {
                test.skip(result.getThrowable());
            }
        }

        attachEvidence(test, result, status.name());
        TEST_NODE.remove();
    }

    private long getDurationMs(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }

    private void attachEvidence(ExtentTest test, ITestResult result, String status) {
        WebDriver driver = resolveDriver(result);
        if (driver == null) {
            test.warning("Driver bulunamadi, ekran goruntusu/page source eklenemedi.");
            return;
        }

        String baseName = result.getMethod().getMethodName() + "_" + System.currentTimeMillis();
        Path evidenceDir = Paths.get("test-output", "custom-report", "evidence");

        try {
            Path screenshotPath = takeScreenshot(driver, evidenceDir, baseName + ".png");
            Path htmlPath = savePageSource(driver, evidenceDir, baseName + ".html");

            test.info("Durum: " + status);
            test.info("Ekran goruntusu: " + screenshotPath.toString());
            test.info("HTML kaniti: " + htmlPath.toString());
            test.info(MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath.toString()).build());
        } catch (Exception e) {
            test.warning("Kanit eklenemedi: " + e.getMessage());
        }
    }

    private WebDriver resolveDriver(ITestResult result) {
        Object instance = result.getInstance();
        if (instance instanceof BaseTest baseTest) {
            return baseTest.getDriver();
        }
        return null;
    }

    private Path takeScreenshot(WebDriver driver, Path evidenceDir, String fileName) throws IOException {
        Path destination = evidenceDir.resolve(fileName);
        Path source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
        Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
        return destination;
    }

    private Path savePageSource(WebDriver driver, Path evidenceDir, String fileName) throws IOException {
        Path destination = evidenceDir.resolve(fileName);
        Files.writeString(destination, driver.getPageSource());
        return destination;
    }

    private void ensureDirectory(Path path) {
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
            throw new RuntimeException("Rapor klasoru olusturulamadi: " + path, e);
        }
    }
}
