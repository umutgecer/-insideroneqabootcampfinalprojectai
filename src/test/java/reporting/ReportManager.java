package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public final class ReportManager {
    private static ExtentReports extentReports;

    private ReportManager() {
    }

    public static synchronized ExtentReports getInstance() {
        if (extentReports == null) {
            Locale.setDefault(Locale.ENGLISH);
            Path reportPath = Paths.get("test-output", "custom-report", "test-report.html");
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath.toString());
            sparkReporter.config().setReportName("TestNG Automation Report");
            sparkReporter.config().setDocumentTitle("InsiderOne Test Results");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);
            extentReports.setSystemInfo("Framework", "TestNG + Selenium");
            extentReports.setSystemInfo("OS", System.getProperty("os.name"));
            extentReports.setSystemInfo("Java", System.getProperty("java.version"));
        }
        return extentReports;
    }
}
