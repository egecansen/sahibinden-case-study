package app.api.models;

import lombok.Data;

@Data
public class CurrentConditionsResponseModel {

    String LocalObservationDateTime;
    int EpochTime;
    String WeatherText;
    int WeatherIcon;
    boolean HasPrecipitation;
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
