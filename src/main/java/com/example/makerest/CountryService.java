package com.example.makerest;

import java.util.Collection;

public class CountryService {

    private CityService cityService = CityService.INSTANCE;
    private CountryRepository repository = new CountryRepository();

    public Country createCountry(CountryController.CountryCreate countryCreate) {
        City city = cityService.getCity(countryCreate.getCapital());
        if (city == null)
            return null;

        Country country = new Country(countryCreate.getName(), city, countryCreate.getRegion(), countryCreate.getLanguage());
        repository.save(country);
        return country;
    }

    public Collection<Country> getCountries() {
        return repository.getCountries();
    }
}
