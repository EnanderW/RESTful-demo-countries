package com.example.makerest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CityController {

    private CityService service = CityService.INSTANCE;

    @PutMapping("/city/create")
    public CityPayload createCity(@RequestBody City city) {
        city = service.saveCity(city);
        return new CityPayload(city.getName(), city.getPopulation(), city.getSize());
    }

    @GetMapping("/city/all")
    public List<CityPayload> getAllCities() {
        return service.getCities()
                .stream()
                .map(city -> new CityPayload(city.getName(), city.getPopulation(), city.getSize()))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/city/delete/{cityName}")
    public String deleteCity(@PathVariable("cityName") String cityName, HttpServletResponse response) {
        if (!service.deleteCity(cityName)) {
            response.setStatus(404);
            return "There is no city named '" + cityName + "'";
        }

        return "You have deleted '" + cityName + "'";
    }

    @GetMapping("/city/info/{cityName}")
    public CityPayload getInfo(@PathVariable("cityName") String cityName, HttpServletResponse response) {
        City city = service.getCity(cityName);
        if (city == null) {
            response.setStatus(404);
            return null;
        }

        return new CityPayload(city.getName(), city.getPopulation(), city.getSize());
    }

    @PutMapping("/city/update/{cityName}")
    public CityPayload updateCity(@PathVariable("cityName") String cityName, @RequestBody CityUpdate cityUpdate, HttpServletResponse response) {
        if (!service.updateCity(cityName, cityUpdate)) {
            response.setStatus(404);
            return null;
        }

        City city = service.getCity(cityName);
        return new CityPayload(city.getName(), city.getPopulation(), city.getSize());
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
