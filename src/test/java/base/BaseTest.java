package base;

import app.SahibindenCaseStudyApplication;
import app.common.DevicePool;
import app.common.Utils;
import app.config.DeviceConfig;
import app.driver.Driver;
import app.driver.DriverManager;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = SahibindenCaseStudyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class BaseTest {

    @Autowired
    protected Driver driverService;
    @Autowired
    private ApplicationContext applicationContext;
    protected AppiumDriver driver;


    @BeforeEach
    public void setup() {
        ContextStore.loadProperties("test.properties");
        driverService = applicationContext.getBean(Driver.class);
        driverService.initialize();
        driver = DriverManager.getDriver();
    }

    @AfterEach
    public void teardown(TestInfo context) {
        if (driver != null) {
            Utils.captureScreen(driver, context.getTags().stream().findFirst().orElse("NoTag"));
            driverService.terminate();
        }
    }
}
