package app.api.accuweather;

import app.api.models.*;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

import static app.api.accuweather.AccuWeatherAPI.*;

public interface AccuWeatherServices {

    String BASE_URL = AccuWeatherAPI.BASE_URL;

    @GET(CURRENTCONDITIONS_SUFFIX + V1_SUFFIX + LOCATIONKEY_PREFIX)
    Call<List<CurrentConditionsResponseModel>> getLocationWeather(
            @Path("locationKey") String locationKey,
            @Query("apikey") String apikey
    );

    @GET(FORECAST_SUFFIX + V1_SUFFIX + DAILY_SUFFIX + FIVEDAY_SUFFIX + LOCATIONKEY_PREFIX)
    Call<FiveDaysForecastResponseModel> getFiveDayForecast(
            @Path("locationKey") String locationKey,
            @Query("apikey") String apikey
    );

}
