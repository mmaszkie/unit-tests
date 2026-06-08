package com.example.unittests.task2;

import org.apache.commons.lang3.StringUtils;

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

        String normalizedQuery = normalizeText(query);
        String[] queryTokens = normalizedQuery.split("\\s+");

        return availableCities.stream()
                .filter(city -> Arrays.stream(queryTokens).allMatch(normalizeText(city)::contains))
                .sorted(String::compareToIgnoreCase)
                .limit(3)
                .toList();
    }

    private String normalizeText(String text) {
        return StringUtils.stripAccents(text.trim()).toLowerCase();
    }

}