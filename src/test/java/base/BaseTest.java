package base;

import app.driver.Driver;
import io.appium.java_client.AppiumDriver;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import app.SahibindenCaseStudyApplication;

@SpringBootTest(classes = SahibindenCaseStudyApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class BaseTest {

    @Autowired
    protected Driver driverService;

    protected AppiumDriver driver;

    @BeforeAll
    public void setup() {
        System.out.println(">>> BaseTest: setup() called");
        driverService.initialize();
        driver = driverService.getDriver();
    }

    @AfterAll
    public void teardown() {
        System.out.println(">>> BaseTest: teardown() called");
        driverService.terminate();
    }
}
