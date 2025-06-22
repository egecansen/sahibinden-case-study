package app.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {

    private static final ThreadLocal<AppiumDriver> driverThread = new ThreadLocal<>();

    public static void setDriver(AppiumDriver driver) {
        driverThread.set(driver);
    }

    public static AppiumDriver getDriver() {
        AppiumDriver driver = driverThread.get();
        if (driver == null)
            throw new IllegalStateException("Appium driver is not initialized!");
        if (driver.getSessionId() == null)
            throw new IllegalStateException("Appium session is already closed!");
        return driver;
    }

    public static void removeDriver() {
        driverThread.remove();
    }


}