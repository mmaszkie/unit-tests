package com.example.unittests.task2;

import java.util.Set;

import static java.util.stream.Collectors.toSet;

class CitiesSearch {

    private static final Set<String> AVAILABLE_CITIES = Set.of(
            "Paris",
            "Budapest",
            "Skopje",
            "Rotterdam",
            "Valencia",
            "Vancouver",
            "Amsterdam",
            "Vienna",
            "Sydney",
            "New York City",
            "London",
            "Bangkok",
            "Hong Kong",
            "Dubai",
            "Rome",
            "Istanbul"
    );

    Set<String> search(String inputText) {
        if (inputText == null || inputText.length() < 2) {
            return Set.of();
        }

        return AVAILABLE_CITIES.stream()
                .filter(city -> city.toLowerCase().contains(inputText.toLowerCase()))
                .collect(toSet());
    }

}
