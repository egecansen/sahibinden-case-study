package app.api.steps;

import app.api.accuweather.AccuWeather;
import app.api.models.FiveDaysForecastResponseModel;
import app.common.enums.Location;
import app.api.models.CurrentConditionsResponseModel;
import context.ContextStore;
import org.springframework.stereotype.Component;
import utils.DateUtilities;
import utils.Printer;

import java.util.List;

@Component
public class AccuWeatherSteps {

    AccuWeather accuWeather = new AccuWeather();

    private Printer log = new Printer(AccuWeatherSteps.class);

    public AccuWeatherSteps() {
        ContextStore.loadProperties("test.properties");
    }


    public void saveTargetLocationWeatherInfoToContext() {
        Location targetLocation = Location.valueOf(ContextStore.get("location"));
        List<CurrentConditionsResponseModel> currentConditionsResponse = accuWeather.getCurrentConditionsForLocation(targetLocation.getLocationKey());
        ContextStore.put("weatherText", currentConditionsResponse.get(0).getWeatherText());
        log.info("weatherText saved to context for " + targetLocation);
        ContextStore.put("celsiusValue", currentConditionsResponse.get(0).getTemperature().getMetric().getValue());
        log.info("Celsius value saved to context for " + targetLocation);
        ContextStore.put("fahrenheitValue", currentConditionsResponse.get(0).getTemperature().getImperial().getValue());
        log.info("Fahrenheit value saved to context for " + targetLocation);
    }


    public void saveFiveDayForecastDataToContext() {
        Location targetLocation = Location.valueOf(ContextStore.get("location"));
        FiveDaysForecastResponseModel fiveDaysForecastResponse = accuWeather.getFiveDayForecast(targetLocation.getLocationKey());

        for (FiveDaysForecastResponseModel.DailyForecast dailyForecast : fiveDaysForecastResponse.getDailyForecasts()){
            String date = DateUtilities.reformatDateString(dailyForecast.getDate(), "uuuu-MM-dd'T'HH:mm:ssXXX", "M/d");
            ContextStore.put(date + " maxTemperatureAPI", dailyForecast.getTemperature().getMaximum().getValue());
            ContextStore.put(date + " minTemperatureAPI", dailyForecast.getTemperature().getMinimum().getValue());
        }

    }

}
