package com.example.makerest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CityRepository {

    private Map<String, City> cities = new HashMap<>();

    public void save(City city) {
        cities.put(city.getName().toLowerCase(), city);
    }

    public City getCity(String name) {
        return cities.get(name.toLowerCase());
    }

    public void remove(City city) {
        cities.remove(city.getName().toLowerCase());
    }

    public Collection<City> getCities() {
        return cities.values();
    }
}
