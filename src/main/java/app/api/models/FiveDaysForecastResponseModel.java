package app.api.models;

import lombok.Getter;

import java.util.List;

@Getter
public class FiveDaysForecastResponseModel {

    Headline Headline;
    List<DailyForecast> DailyForecasts;

    @Getter
    public static class Headline {
        String EffectiveDate;
        long EffectiveEpochDate;
        int Severity;
        String Text;
        String Category;
        String EndDate;
        long EndEpochDate;
        String MobileLink;
        String Link;

    }

    @Getter
    public static class DailyForecast {
        String Date;
        long EpochDate;
        Temperature Temperature;
        Day Day;
        Night Night;
        List<Object> Sources;
        String MobileLink;
        String Link;

        @Getter
        public static class Temperature {

            Minimum Minimum;
            Maximum Maximum;

            @Getter
            public static class Minimum {
                int Value;
                String Unit;
                int UnitType;
            }

            @Getter
            public static class Maximum {
                int Value;
                String Unit;
                int UnitType;
            }
        }

        @Getter
        public static class Day {
            int Icon;
            String IconPhrase;
            boolean HasPrecipitation;
        }

        @Getter
        public static class Night {
            int Icon;
            String IconPhrase;
            boolean HasPrecipitation;
        }

    }
}
