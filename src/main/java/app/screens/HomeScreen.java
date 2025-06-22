package app.screens;

import app.common.Utils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import utils.Printer;


public class HomeScreen {

    public Printer log = new Printer(HomeScreen.class);
    Utils utils;

    public HomeScreen(AppiumDriver driver) {
        this.utils = new Utils(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
    @AndroidFindBy(xpath = "//android.view.View[@content-desc='Local Affiliate logo']")
    public WebElement mainNavigationLogo;


    public void verifyLandedOnHomeScreen() {
        utils.waitUntilDisplayed(mainNavigationLogo);
        log.success("mainNavigationLogo is displayed on the HomeScreen");
    }


    public void clickOnLocationsFromMainNavigation() {
        utils.clickElementUntil(mainNavigationLogo);
        log.info("Clicked mainNavigationLogo on the HomeScreen");
    }


}
