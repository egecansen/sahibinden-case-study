package base;

import app.SahibindenCaseStudyApplication;
import app.common.DevicePool;
import app.common.Utils;
import app.config.DeviceConfig;
import app.driver.Driver;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = SahibindenCaseStudyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    @Autowired
    protected Driver driverService;
    @Autowired
    private ApplicationContext applicationContext;
    protected AppiumDriver driver;
    private DeviceConfig deviceConfig;
    protected DevicePool devicePool = DevicePool.getInstance();

    @BeforeEach
    public void setup() {
        driverService = applicationContext.getBean(Driver.class);
        deviceConfig = devicePool.acquireDevice();
        driverService.initialize(deviceConfig);
        driver = driverService.getDriver();
    }

    @AfterEach
    public void teardown(TestInfo context) {
        if (driver != null) {
            Utils.captureScreen(driver, context.getDisplayName());
            driverService.terminate();
        }
        if (deviceConfig != null) {
            devicePool.releaseDevice(deviceConfig);
        }
    }
}
