package com.example.makerest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {


    private String name;
    private City capital;

    private String region;
    private String language;

    public Country(String name, City capital, String region, String language) {
        this.name = name;
        this.capital = capital;
        this.region = region;
        this.language = language;
    }

}
