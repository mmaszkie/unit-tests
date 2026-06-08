package com.example.unittests.task2;

import java.util.Set;

class CitiesSearchTests {

    private static final Set<String> AVAILABLE_CITIES = Set.of(
            "Warszawa",
            "Wrocław",
            "Kraków",
            "Gdynia",
            "Nowy Jork",
            "Los Angeles",
            "San Francisco",
            "Zakopane",
            "Ankara",
            "Santiago"
    );

    private final CitiesSearch citiesSearch = new CitiesSearch(AVAILABLE_CITIES);

}
