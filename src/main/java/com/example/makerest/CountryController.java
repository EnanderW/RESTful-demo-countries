package com.example.makerest;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CountryController {

    private CountryService service = new CountryService();

    @PutMapping("/country/create")
    public CountryPayload createCountry(@RequestBody CountryCreate countryCreate, HttpServletResponse response) {
        Country country = service.createCountry(countryCreate);
        if (country == null) {
            response.setStatus(404);
            return null;
        }

        return new CountryPayload(country);
    }

    @GetMapping("/country/all")
    public List<CountryPayload> getAllCountries() {
        return service.getCountries()
                .stream()
                .map(CountryPayload::new)
                .collect(Collectors.toList());
    }

    @Getter
    @Setter
    public static final class CountryPayload {
        private String name;
        private String region;
        private String language;
        private String capital;

        public CountryPayload(Country country) {
            this.name = country.getName();
            this.region = country.getRegion();
            this.language = country.getLanguage();
            this.capital = "/city/info/" + country.getCapital().getName();
        }
    }

    @Getter
    @Setter
    public static final class CountryCreate {
        private String name;
        private String region;
        private String language;
        private String capital;
    }

}
