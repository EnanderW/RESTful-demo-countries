package com.example.makerest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CityController {

    private Map<String, City> cities = new HashMap<>();

    private City selected;

    @PutMapping("/city/create")
    public CityPayload createCity(@RequestBody City city) {
        save(city);

        return new CityPayload(city.getName(), city.getPopulation(), city.getSize());
    }

    @GetMapping("/city/all")
    public List<CityPayload> getAllCities() {
        return cities.values()
                .stream()
                .map(city -> new CityPayload(city.getName(), city.getPopulation(), city.getSize()))
                .collect(Collectors.toList());
    }

    @PostMapping("/city/select/{cityName}")
    public String selectCity(@PathVariable("cityName") String cityName, HttpServletResponse response) {
        selected = getCity(cityName);
        if (selected == null) {
            response.setStatus(404);
            return "There is no city named '" + cityName + "'";
        }

        return "Selected city '" + cityName + "'";
    }

    @DeleteMapping("/city/delete")
    public String deleteCity(HttpServletResponse response) {
        if (selected == null) {
            response.setStatus(404);
            return "You have not selected a city.";
        }

        remove(selected);
        String name = selected.getName();
        selected = null;
        return "You have deleted '" + name + "'";
    }

    @GetMapping("/city/info")
    public CityPayload getInfo(HttpServletResponse response) {
        if (selected == null) {
            response.setStatus(404);
            return null;
        }

        return new CityPayload(selected.getName(), selected.getPopulation(), selected.getSize());
    }

    @PutMapping("/city/update")
    public CityPayload updateCity(@RequestBody CityUpdate cityUpdate, HttpServletResponse response) {
        if (selected == null) {
            response.setStatus(404);
            return null;
        }

        selected.setPopulation(cityUpdate.getPopulation());
        selected.setSize(cityUpdate.getSize());
        return new CityPayload(selected.getName(), selected.getPopulation(), selected.getSize());
    }

    public void save(City city) {
        cities.put(city.getName().toLowerCase(), city);
    }

    public City getCity(String name) {
        return cities.get(name.toLowerCase());
    }

    public void remove(City city) {
        cities.remove(city.getName().toLowerCase());
    }

    @Getter
    @Setter
    public static final class CityPayload {
        private String name;
        private int population;
        private int size;

        public CityPayload(String name, int population, int size) {
            this.name = name;
            this.population = population;
            this.size = size;
        }
    }

    @Getter
    @Setter
    public static final class CityUpdate {
        private int population;
        private int size;
    }
}
