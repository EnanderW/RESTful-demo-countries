package com.example.makerest;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CountryRepository {

    private Map<String, Country> countries = new HashMap<>();

    public void save(Country country) {
        countries.put(country.getName().toLowerCase(), country);
    }

    public Country getCountry(String name) {
        return countries.get(name.toLowerCase());
    }

    public void remove(Country country) {
        countries.remove(country.getName().toLowerCase());
    }

    public Collection<Country> getCountries() {
        return countries.values();
    }
}
