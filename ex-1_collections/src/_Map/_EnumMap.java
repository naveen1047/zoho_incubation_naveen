package _Map;

import java.util.EnumMap;
import java.util.Map;

enum Country {
    ENG,
    AUS,
    ESP,
    IND,
    US,
};

public class _EnumMap {

    public static void main(String[] args) {
        EnumMap<Country, String> map = new EnumMap<Country, String>(Country.class);

        map.put(Country.ENG, "England");
        map.put(Country.AUS, "Australia");
        map.put(Country.IND, "India");
        map.put(Country.US, "United States");

        System.out.println(map);


    }
}
