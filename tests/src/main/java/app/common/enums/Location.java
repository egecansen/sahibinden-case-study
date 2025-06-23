package app.common.enums;

import lombok.Getter;

@Getter
public enum Location {

    newyork("349727"),
    amsterdam("249758"),
    istanbul("318251");

    final String locationKey;
    Location(String locationKey) {
        this.locationKey = locationKey;
    }

}
