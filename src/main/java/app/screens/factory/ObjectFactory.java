package app.screens.factory;


import app.screens.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectFactory {
    private final HomeScreen homeScreen;
    private final GetStartedScreen getStartedScreen;
    private final WeatherInfoScreen weatherInfoScreen;
    private final NotificationsScreen notificationsScreen;
    private final PersonalizedAdsScreen personalizedAdsScreen;
    private final TermsAndPrivacyScreen termsAndPrivacyScreen;
    private final CurrentConditionsScreen currentConditionsScreen;
    private final LocationsDropdownScreen locationsDropdownScreen;
    private final LocationPermissionsScreen locationPermissionsScreen;

    @Autowired
    public ObjectFactory(
            HomeScreen homeScreen,
            GetStartedScreen getStartedScreen,
            WeatherInfoScreen weatherInfoScreen, NotificationsScreen notificationsScreen,
            PersonalizedAdsScreen personalizedAdsScreen,
            TermsAndPrivacyScreen termsAndPrivacyScreen,
            CurrentConditionsScreen currentConditionsScreen, LocationsDropdownScreen locationsDropdownScreen,
            LocationPermissionsScreen locationPermissionsScreen
    ) {
        this.homeScreen = homeScreen;
        this.getStartedScreen = getStartedScreen;
        this.weatherInfoScreen = weatherInfoScreen;
        this.notificationsScreen = notificationsScreen;
        this.personalizedAdsScreen = personalizedAdsScreen;
        this.termsAndPrivacyScreen = termsAndPrivacyScreen;
        this.currentConditionsScreen = currentConditionsScreen;
        this.locationsDropdownScreen = locationsDropdownScreen;
        this.locationPermissionsScreen = locationPermissionsScreen;
    }

    public HomeScreen homeScreen() { return homeScreen; }
    public GetStartedScreen getStartedScreen() { return getStartedScreen; }
    public WeatherInfoScreen weatherInfoScreen() { return weatherInfoScreen; }
    public NotificationsScreen notificationsScreen() { return notificationsScreen; }
    public TermsAndPrivacyScreen termsAndPrivacyScreen() { return termsAndPrivacyScreen; }
    public PersonalizedAdsScreen personalizedAdsScreen() { return personalizedAdsScreen; }
    public CurrentConditionsScreen currentConditionsScreen() { return currentConditionsScreen; }
    public LocationsDropdownScreen locationsDropdownScreen() { return locationsDropdownScreen; }
    public LocationPermissionsScreen locationPermissionsScreen() { return locationPermissionsScreen; }


}
