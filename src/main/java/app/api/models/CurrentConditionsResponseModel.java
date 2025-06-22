package app.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.annotation.Nullable;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentConditionsResponseModel {

    String LocalObservationDateTime;
    int EpochTime;
    String WeatherText;
    int WeatherIcon;
    boolean HasPrecipitation;
    @Nullable
    String PrecipitationType;
    boolean IsDayTime;
    Temperature Temperature;
    String MobileLink;
    String Link;

    @Data
    public class Temperature {

        Metric Metric;
        Imperial Imperial;

        @Data
       public class Metric {
            int Value;
            String Unit;
            int UnitType;
        }

        @Data
       public class Imperial{
            int Value;
            String Unit;
            int UnitType;

        }

    }

}
