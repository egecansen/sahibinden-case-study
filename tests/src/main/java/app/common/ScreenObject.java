package app.common;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.Printer;

public class ScreenObject {

    public Printer log = new Printer(this.getClass());
    public Utils utils;

    public ScreenObject(AppiumDriver driver) {
        this.utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
