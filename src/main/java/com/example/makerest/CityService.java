package com.example.makerest;

import java.util.Collection;

public class CityService {

    public static final CityService INSTANCE = new CityService();

    private CityRepository repository = new CityRepository();




    public City saveCity(City city) {
        repository.save(city);
        return city;
    }

    public boolean deleteCity(String name) {
        City city = getCity(name);
        if (city == null)
            return false;

        repository.remove(city);
        return true;
    }

    public boolean updateCity(String name, CityController.CityUpdate cityUpdate) {
        City city = getCity(name);
        if (city == null)
            return false;

        city.setSize(cityUpdate.getSize());
        city.setPopulation(cityUpdate.getPopulation());
        return true;
    }

    public City getCity(String name) {
        return repository.getCity(name);
    }

    public Collection<City> getCities() {
        return repository.getCities();
    }
}
