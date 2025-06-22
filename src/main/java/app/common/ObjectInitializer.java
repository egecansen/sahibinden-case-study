package app.common;

import app.screens.*;
import io.appium.java_client.AppiumDriver;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ObjectInitializer {
    @Autowired
    HomeScreen homeScreen;
    @Autowired
    GetStartedScreen getStartedScreen;
    @Autowired
    WeatherInfoScreen weatherInfoScreen;
    @Autowired
    NotificationsScreen notificationsScreen;
    @Autowired
    TermsAndPrivacyScreen termsAndPrivacyScreen;
    @Autowired
    PersonalizedAdsScreen personalizedAdsScreen;
    @Autowired
    CurrentConditionsScreen currentConditionsScreen;
    @Autowired
    LocationsDropdownScreen locationsDropdownScreen;
    @Autowired
    LocationPermissionsScreen locationPermissionsScreen;

    public void initializeScreens(AppiumDriver driver) {
        homeScreen.init(driver);
        getStartedScreen.init(driver);
        weatherInfoScreen.init(driver);
        notificationsScreen.init(driver);
        termsAndPrivacyScreen.init(driver);
        personalizedAdsScreen.init(driver);
        currentConditionsScreen.init(driver);
        locationsDropdownScreen.init(driver);
        locationPermissionsScreen.init(driver);
    }

}
