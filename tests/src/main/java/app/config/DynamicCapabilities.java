package app.config;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class DynamicCapabilities {

    @Autowired
    private Properties properties;

    public DesiredCapabilities build(String deviceName, String udid, DesiredCapabilities mainCapabilities) {
        DesiredCapabilities capabilities = new DesiredCapabilities(mainCapabilities);
        capabilities.setCapability("appium:udid", udid);
        capabilities.setCapability("appium:deviceName", deviceName);
        return capabilities;
    }

}
