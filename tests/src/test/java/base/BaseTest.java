package base;

import app.SahibindenCaseStudyApplication;
import app.common.TestStatus;
import app.common.Utils;
import app.driver.Driver;
import app.driver.DriverManager;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import utils.Printer;

@SpringBootTest(classes = SahibindenCaseStudyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@ExtendWith(StatusWatcher.class)
public class BaseTest {

    Printer log = new Printer(BaseTest.class);
    @Autowired
    Driver driverService;
    @Autowired
    ApplicationContext applicationContext;
    AppiumDriver driver;

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
            if (TestStatus.isFailed())
                Utils.captureScreen(driver, testInfo.getTags().stream().findFirst().orElse("NoTag"));
            driverService.terminate();
        }
    }

}
