package app.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    public static void setDriver(AppiumDriver driver) {
        driverThread.set(driver);
    }

    public static AppiumDriver getDriver() {
        return driverThread.get();
    }

    public static void removeDriver() {
        driverThread.remove();
    }

}