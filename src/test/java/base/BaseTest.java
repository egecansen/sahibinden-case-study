package base;

import app.SahibindenCaseStudyApplication;
import app.common.DevicePool;
import app.common.TestStatus;
import app.common.Utils;
import app.config.DeviceConfig;
import app.driver.Driver;
import app.driver.DriverManager;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMultipart;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import utils.Printer;
import utils.email.EmailUtilities;

import java.io.File;
import java.io.IOException;

@SpringBootTest(classes = SahibindenCaseStudyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(StatusWatcher.class)
public class BaseTest {

    private final Printer log = new Printer(BaseTest.class);
    @Autowired
    protected Driver driverService;
    @Autowired
    private ApplicationContext applicationContext;
    protected AppiumDriver driver;


    @BeforeEach
    public void setup(TestInfo testInfo) {
        ContextStore.loadProperties("test.properties");
        driverService = applicationContext.getBean(Driver.class);
        driverService.initialize();
        driver = DriverManager.getDriver();
        log.important("EXECUTING: " + testInfo.getDisplayName());
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
        if (driver != null) {
            if (TestStatus.isFailed()) {
                Utils.captureScreen(driver, testInfo.getTags().stream().findFirst().orElse("NoTag"));
                if (Boolean.parseBoolean(ContextStore.get("send-report-email", "false"))) {
                    log.important("Sending the report email...");
                    Utils.sendReportEmail();
                }

            }
            driverService.terminate();
        }


    }
}
