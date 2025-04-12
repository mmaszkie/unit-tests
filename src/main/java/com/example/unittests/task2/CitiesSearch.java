package com.example.unittests.task2;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
        if ("*".equals(inputText)) {
            return AVAILABLE_CITIES;
        }

        if (inputText == null || inputText.length() < 2) {
            return Set.of();
        }

        String regex = ".*" + Pattern.quote(inputText) + ".*";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        return AVAILABLE_CITIES.stream()
                .filter(city -> pattern.matcher(city).matches())
                .collect(Collectors.toSet());
    }

}
