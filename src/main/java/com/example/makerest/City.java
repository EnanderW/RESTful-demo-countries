package com.example.makerest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {

    private String name;
    private int population;
    private int size;

    public City(String name, int population, int size) {
        this.name = name;
        this.population = population;
        this.size = size;
    }
}
