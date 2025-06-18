package app.driver;

import app.config.DriverConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Printer;

import java.time.Duration;

@Component
public class DriverFactory {

    private final DriverConfig driverConfig;
    private AppiumDriverLocalService service;
    private final Printer log = new Printer(DriverFactory.class);

    @Autowired
    public DriverFactory(DriverConfig driverConfig) {
        this.driverConfig = driverConfig;
    }

    public void startService() {
        log.info("Starting Appium service...");
        service = new AppiumServiceBuilder()
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(60))
                .build();
        service.start();
    }

    public AppiumDriver createDriver() {

        DesiredCapabilities capabilities = driverConfig.getDesiredCapabilities();
        System.out.println("Capabilities:");
        capabilities.asMap().forEach((k, v) -> System.out.println(k + " = " + v));
        try {
            return new AppiumDriver(service.getUrl(), capabilities);
        } catch (Exception e) {
            throw new RuntimeException("Failed to start Appium Driver", e);
        }
    }

    public void stopService() {
        log.info("Stopping the Appium service...");
        if (service != null) {
            service.stop();
        }
    }
}
