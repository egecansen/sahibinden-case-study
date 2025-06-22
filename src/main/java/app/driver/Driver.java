package app.driver;

import app.common.DevicePool;
import app.config.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.Printer;

@Component
@Scope("prototype")
public class Driver {

    private final Printer log = new Printer(Driver.class);
    private final DriverFactory driverFactory;
    private AppiumDriver driver;

    @Autowired
    public Driver(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    public void initialize(DeviceConfig deviceConfig) {
        log.info("Initializing the driver...");
        driverFactory.startService(deviceConfig);
        driver = driverFactory.createDriver(deviceConfig);
        DriverManager.setDriver(driver);
    }

    public void terminate() {
        log.info("Terminating the driver...");
        try {
            if (driver != null) {
                driver.quit();
                DriverManager.removeDriver();
            }
        } catch (Exception e) {
            log.warning("Error during driver.quit: " + e.getMessage());
        }
        try {
            driverFactory.stopService();
        } catch (Exception e) {
            log.warning("Error during stopService: " + e.getMessage());
        }
    }

    public AppiumDriver getDriver() {
        return driver;
    }
}
