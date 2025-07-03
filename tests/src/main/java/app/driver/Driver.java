package app.driver;

import app.common.DevicePool;
import app.config.DeviceConfig;
import context.ContextStore;
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
    private static AppiumDriver driver;
    private DeviceConfig deviceConfig;
    protected DevicePool devicePool = DevicePool.getInstance();

    @Autowired
    public Driver(DriverFactory driverFactory) {
        this.driverFactory = driverFactory;
    }

    public void initialize() {
        log.info("Initializing the driver...");
        ContextStore.loadProperties("test.properties");
        if (Boolean.parseBoolean(ContextStore.get("use-default-device"))) {
            log.warning("Default device is selected");
            deviceConfig = devicePool.getDefaultDevice();
        }
        else deviceConfig = devicePool.acquireDevice();
        driverFactory.startService(deviceConfig);
        driver = driverFactory.createDriver(deviceConfig);
    }

    public void terminate() {
        log.info("Terminating the driver...");
        try {
            if (driver != null) {
                driver.quit();
            }
            if (deviceConfig != null) {
                devicePool.releaseDevice(deviceConfig);
            }
        } catch (Exception e) {
            log.warning("Error during quiting the driver: " + e.getMessage());
        }
        try {
            driverFactory.stopService();
        } catch (Exception e) {
            log.warning("Error during stopService: " + e.getMessage());
        }
    }

    public static AppiumDriver getDriver() {
        return driver;
    }
}
