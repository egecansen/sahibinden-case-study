package app.screens;

import app.common.Utils;
import app.common.enums.Direction;
import context.ContextStore;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.Printer;

import java.util.Arrays;
import java.util.List;


public class WeatherInfoScreen {

    public Printer log = new Printer(WeatherInfoScreen.class);
    Utils utils;

    public WeatherInfoScreen(AppiumDriver driver) {
        utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='minutecast_bar']")
    public WebElement minuteCastBar;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='CURRENT CONDITIONS']")
    public WebElement currentConditionsLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Temperature']/following-sibling::android.widget.TextView[1]")
    public WebElement temperatureValue;

    @AndroidFindBy(xpath = "//android.view.View[@content-desc='See More']")
    public WebElement seeMoreButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='10-DAY WEATHER FORECAST']")
    public WebElement weatherForecastBox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='10-DAY WEATHER FORECAST']/following-sibling::android.view.View[@content-desc]")
    public List<WebElement> weatherForecastItems;

    public void swipeUntilCurrentConditionsSeeMoreButtonDisplayed() {
        utils.waitUntilDisplayed(minuteCastBar);
        utils.swipeUntilFound(seeMoreButton, Direction.up);
        log.success("Current Conditions seeMoreButton element is displayed on the WeatherInfoScreen");
    }

    public void clickOnSeeMoreButton() {
        utils.waitUntilDisplayed(seeMoreButton);
        utils.clickElementUntil(seeMoreButton);
        log.info("Clicked on seeMoreButton on the WeatherInfoScreen");
    }

    public void swipeUntilWeatherForecastBoxDisplayed() {
        utils.waitUntilDisplayed(minuteCastBar);
        utils.swipeUntilFound(weatherForecastBox, Direction.up);
        log.success("weatherForecastBox element is displayed on the WeatherInfoScreen");
    }

    public void verifyTheForecastData() {
        log.info("Verifying the UI forecast data between UI screen and API response");
        int count = 0;

        for (WebElement item : weatherForecastItems) {
            if (count < 5) {
                String[] items = item.getDomAttribute("content-desc").split("\\s");

                String date = Arrays.stream(items).toList().get(1);
                String maxTemperate = Arrays.stream(items).toList().get(2).replace("°", "");
                String minTemperature = Arrays.stream(items).toList().get(3).replace("°", "");

                temperatureDataVerification(date, maxTemperate, minTemperature);
                count++;
            }
        }
    }

    public void temperatureDataVerification(String date, String maxTemp, String minTemp) {
        String expectedMaxTemp = ContextStore.get(date + " maxTemperatureAPI").toString();
        String expectedMinTemp = ContextStore.get(date + " minTemperatureAPI").toString();

        Assert.assertEquals("Maximum temperature value on the screen does not match with the API response!", expectedMaxTemp, maxTemp);
        log.success("Date " + date + " maximum temperature value verified as -> " + expectedMaxTemp);

        Assert.assertEquals("Minimum temperature value on the screen does not match with the API response!", expectedMinTemp, minTemp);
        log.success("Date " + date + " minimum temperature value verified as -> " + expectedMinTemp);
    }

}
