package app.screens.factory;

import app.driver.Driver;
import app.screens.*;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactory {

    public HomeScreen homeScreen() {
        return new HomeScreen(Driver.getDriver());
    }
    public GetStartedScreen getStartedScreen() {
        return new GetStartedScreen(Driver.getDriver());
    }
    public WeatherInfoScreen weatherInfoScreen() {
        return new WeatherInfoScreen(Driver.getDriver());
    }
    public NotificationsScreen notificationsScreen() {
        return new NotificationsScreen(Driver.getDriver());
    }
    public TermsAndPrivacyScreen termsAndPrivacyScreen() {
        return new TermsAndPrivacyScreen(Driver.getDriver());
    }
    public PersonalizedAdsScreen personalizedAdsScreen() {
        return new PersonalizedAdsScreen(Driver.getDriver());
    }
    public CurrentConditionsScreen currentConditionsScreen() {
        return new CurrentConditionsScreen(Driver.getDriver());
    }
    public LocationsDropdownScreen locationsDropdownScreen() {
        return new LocationsDropdownScreen(Driver.getDriver());
    }
    public LocationPermissionsScreen locationPermissionsScreen() {
        return new LocationPermissionsScreen(Driver.getDriver());
    }
}
