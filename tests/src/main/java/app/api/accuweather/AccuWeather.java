package app.api.accuweather;

import app.api.models.CurrentConditionsResponseModel;
import api_assured.ApiUtilities;
import api_assured.ServiceGenerator;
import app.api.models.FiveDaysForecastResponseModel;
import context.ContextStore;
import okhttp3.Headers;
import retrofit2.Call;

import java.util.List;

public class AccuWeather extends ApiUtilities {

    AccuWeatherServices accuWeather = new ServiceGenerator(
            new Headers.Builder().add("Accept", "*/*").build()
    ).setRequestLogging(true).generate(AccuWeatherServices.class);

    public List<CurrentConditionsResponseModel> getCurrentConditionsForLocation(String locationKey) {
        log.info("Acquiring the current conditions weather data..." );
        Call<List<CurrentConditionsResponseModel>> getCurrentConfitionsForLocationCall = accuWeather.getLocationWeather(locationKey, ContextStore.get("apikey"));
        return perform(getCurrentConfitionsForLocationCall, false, false);
    }

    public FiveDaysForecastResponseModel getFiveDayForecast(String locationKey) {
        log.info("Acquiring the current conditions weather data..." );
        Call<FiveDaysForecastResponseModel> getFiveDayForecastCall = accuWeather.getFiveDayForecast(locationKey, ContextStore.get("apikey"));
        return perform(getFiveDayForecastCall, false, false);
    }

}
