package app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "appium")
public class Properties {
    private String platformName;
    private String deviceName;
    private String automationName;
    private String appWaitActivity;
    private int adbExecTimeout;
    private int appWaitDuration;
    private int androidInstallTimeout;
    private int uiautomator2ServerLaunchTimeout;
    private int uiautomator2ServerInstallTimeout;
    private String apk;
    private String udid;
    private int appiumPort;
}
