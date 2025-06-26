package app.screens;

import app.common.ScreenObject;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class CurrentConditionsScreen extends ScreenObject {

    public CurrentConditionsScreen(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View/following-sibling::android.widget.TextView[1]")
    public WebElement weatherText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='RealFeel']/following-sibling::android.widget.TextView[1]")
    public WebElement realFeelTemperatureValue;


    public void verifyWeatherText() {
        String expectedWeatherText = ContextStore.get("weatherText").toString();
        Assert.assertEquals("Temperature value on the app does not match with the fahrenheitValue!", expectedWeatherText, weatherText.getText());
        log.success("Weather text verified as -> " + ContextStore.get("weatherText").toString());
    }

    public void verifyTemperatureValue() {
        String actualTemperatureNumber = realFeelTemperatureValue.getText().replace("°", "");
        String expectedTemperatureNumber = ContextStore.get("fahrenheitValue").toString();
        Assert.assertEquals("Temperature value on the UI does not match with the API response!", expectedTemperatureNumber, actualTemperatureNumber);
        log.success("Temperature value verified as -> " + ContextStore.get("fahrenheitValue").toString());
    }

}
