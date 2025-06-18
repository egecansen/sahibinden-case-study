package app.driver;

import io.appium.java_client.AppiumDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Printer;

@Component
public class Driver {

    private final Printer log = new Printer(Driver.class);
    private final DriverFactory driverFactory;
    private AppiumDriver driver;

    @Autowired
    public Driver(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    public void initialize() {
        log.info("Initializing the driver...");
        driverFactory.startService();
        driver = driverFactory.createDriver();
    }

    public void terminate() {
        log.info("Terminating the driver...");
        if (driver != null) {
            driver.quit();
        }
        driverFactory.stopService();
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
