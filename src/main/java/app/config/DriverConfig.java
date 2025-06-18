package app.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import utils.FileUtilities;

import java.io.File;

@Configuration
public class DriverConfig {

    @Autowired
    private Properties properties;

    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // W3C-compliant capability format
        capabilities.setCapability("platformName", properties.getPlatformName());
        capabilities.setCapability("appium:deviceName", properties.getDeviceName());
        capabilities.setCapability("appium:automationName", properties.getAutomationName());
        capabilities.setCapability("appium:appWaitActivity", properties.getAppWaitActivity());
        capabilities.setCapability("appium:adbExecTimeout", properties.getAdbExecTimeout());
        capabilities.setCapability("appium:appWaitDuration", properties.getAppWaitDuration());
        capabilities.setCapability("appium:androidInstallTimeout", properties.getAndroidInstallTimeout());
        capabilities.setCapability("appium:uiautomator2ServerLaunchTimeout", properties.getUiautomator2ServerLaunchTimeout());
        capabilities.setCapability("appium:uiautomator2ServerInstallTimeout", properties.getUiautomator2ServerInstallTimeout());

        String apkFile = FileUtilities.getAbsolutePath(properties.getApk());
        if (apkFile.isEmpty()) {
            throw new RuntimeException("APK file is not found at: " + properties.getApk());
        }

        capabilities.setCapability("appium:app", apkFile);

        return capabilities;
    }
}
