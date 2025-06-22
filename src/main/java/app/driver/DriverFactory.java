package app.driver;

import app.common.DevicePool;
import app.config.DeviceConfig;
import app.config.CommonCapabilities;
import app.config.DynamicCapabilities;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.Printer;

import java.net.URL;
import java.time.Duration;

@Component
public class DriverFactory {

    private final CommonCapabilities commonCapabilities;
    private AppiumDriverLocalService service;
    private final Printer log = new Printer(DriverFactory.class);

    @Autowired
    public DriverFactory(CommonCapabilities driverConfig) {
        this.commonCapabilities = driverConfig;
    }

    public void startService(DeviceConfig deviceConfig) {
        log.info("Starting Appium service on port: " + deviceConfig.getAppiumPort());
        service = new AppiumServiceBuilder()
                .withArgument(GeneralServerFlag.LOG_LEVEL, ContextStore.get("appiumLogLevel"))
                .withIPAddress("127.0.0.1")
                .usingPort(deviceConfig.getAppiumPort())
                .withTimeout(Duration.ofSeconds(60))
                .build();
        service.start();
    }

    public AppiumDriver createDriver(DeviceConfig deviceConfig) {
        DynamicCapabilities dynamicCapabilities = new DynamicCapabilities();
        DesiredCapabilities capabilities = dynamicCapabilities.build(
                deviceConfig.getDeviceName(),
                deviceConfig.getUdid(),
                commonCapabilities.getDesiredCapabilities()
        );
        log.info("Capabilities: ");
        capabilities.asMap().forEach((k, v) -> log.info(k + " = " + v));
        try {
            return new AppiumDriver(new URL("http://127.0.0.1:" + deviceConfig.getAppiumPort() + "/"), capabilities);
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
