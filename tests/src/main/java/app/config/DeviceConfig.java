package app.config;

import lombok.Getter;

@Getter
public class DeviceConfig {

    public final String deviceName;
    public final String udid;
    public final int appiumPort;

    public DeviceConfig(String deviceName, String udid, int appiumPort) {
        this.deviceName = deviceName;
        this.udid = udid;
        this.appiumPort = appiumPort;
    }


}
