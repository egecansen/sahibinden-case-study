package app.screens.factory;

import app.driver.DriverManager;
import app.screens.*;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactory {

    public HomeScreen homeScreen() {
        return new HomeScreen(DriverManager.getDriver());
    }
    public GetStartedScreen getStartedScreen() {
        return new GetStartedScreen(DriverManager.getDriver());
    }
    public WeatherInfoScreen weatherInfoScreen() {
        return new WeatherInfoScreen(DriverManager.getDriver());
    }
    public NotificationsScreen notificationsScreen() {
        return new NotificationsScreen(DriverManager.getDriver());
    }
    public TermsAndPrivacyScreen termsAndPrivacyScreen() {
        return new TermsAndPrivacyScreen(DriverManager.getDriver());
    }
    public PersonalizedAdsScreen personalizedAdsScreen() {
        return new PersonalizedAdsScreen(DriverManager.getDriver());
    }
    public CurrentConditionsScreen currentConditionsScreen() {
        return new CurrentConditionsScreen(DriverManager.getDriver());
    }
    public LocationsDropdownScreen locationsDropdownScreen() {
        return new LocationsDropdownScreen(DriverManager.getDriver());
    }
    public LocationPermissionsScreen locationPermissionsScreen() {
        return new LocationPermissionsScreen(DriverManager.getDriver());
    }
}
