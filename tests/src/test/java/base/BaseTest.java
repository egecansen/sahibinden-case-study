package base;

import app.SahibindenCaseStudyApplication;
import app.common.TestStatus;
import app.common.Utils;
import app.driver.Driver;
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

    private final static Printer log = new Printer(BaseTest.class);
    @Autowired
    protected Driver driverService;
    @Autowired
    private ApplicationContext applicationContext;
    protected AppiumDriver driver;


    @BeforeAll
    public static void setup() {
        ContextStore.loadProperties("test.properties");
        log.info("Properties loaded");
    }
    @BeforeEach
    public void before(TestInfo testInfo) {
        driverService = applicationContext.getBean(Driver.class);
        driverService.initialize();
        driver = Driver.getDriver();
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
