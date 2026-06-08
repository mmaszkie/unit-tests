package com.example.unittests.task2;

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

        return availableCities.stream()
                .filter(city -> city.toLowerCase().contains(query.toLowerCase()))
                .sorted(String::compareToIgnoreCase)
                .limit(3)
                .toList();
    }

}