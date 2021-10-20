package com.example.makerest;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    @PutMapping("/country/create")
    public void createCountry() {

    }

}
