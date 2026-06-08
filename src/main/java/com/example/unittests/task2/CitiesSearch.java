package com.example.unittests.task2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class CitiesSearch {

    private final Set<String> availableCities;

    CitiesSearch(Set<String> availableCities) {
        this.availableCities = availableCities;
    }

    List<String> search(String query) {
        if (query == null || query.trim().length() < 2) {
            return List.of();
        }

        String[] queryTokens = query.toLowerCase().split("\\s+");

        return availableCities.stream()
                .filter(city -> Arrays.stream(queryTokens).allMatch(city.toLowerCase()::contains))
                .sorted(String::compareToIgnoreCase)
                .limit(3)
                .toList();
    }

}