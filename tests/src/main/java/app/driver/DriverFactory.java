package app.driver;

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
import utils.SystemUtilities;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.time.Duration;

import static utils.StringUtilities.Color.PURPLE;
import static utils.StringUtilities.Color.RESET;
import static utils.StringUtilities.highlighted;

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
        if (Boolean.parseBoolean(ContextStore.get("use-remote-appium", "false"))) {
            log.warning("Remote Appium mode enabled, NOT starting local Appium service");
        } else {
            log.info("Starting Appium service on port: " + deviceConfig.getAppiumPort());
            service = new AppiumServiceBuilder()
                    .withArgument(GeneralServerFlag.LOG_LEVEL, ContextStore.get("appiumLogLevel"))
                    .withIPAddress(ContextStore.get("default-address").toString())
                    .usingPort(getPort(deviceConfig))
                    .withTimeout(Duration.ofSeconds(60))
                    .build();
            service.start();
        }
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
            String url;
            if (Boolean.parseBoolean(ContextStore.get("use-remote-appium", "false"))) {
                url = "http://appium:" + getPort(deviceConfig) + "/";
            } else {
                url = "http://" + ContextStore.get("default-address").toString() + ":" + getPort(deviceConfig) + "/";
            }
            log.warning("Connecting to Appium at " + url);
            return new AppiumDriver(new URL(url), capabilities);
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

    public int getPort(DeviceConfig deviceConfig) {
        int port;
        if (Boolean.parseBoolean(ContextStore.get("use-default-device"))) {
            port = Integer.parseInt(ContextStore.get("default-port"));
        }
        else {
            port = deviceConfig.getAppiumPort();
        }
        return port;
    }

}
