package com.example.unittests.task2;

import java.util.List;
import java.util.Set;

class CitiesSearch {

    private final Set<String> availableCities;

    CitiesSearch(Set<String> availableCities) {
        this.availableCities = availableCities;
    }

    List<String> search(String query) {
        return availableCities.stream()
                .filter(city -> city.toLowerCase().contains(query.toLowerCase()))
                .toList();
    }

}