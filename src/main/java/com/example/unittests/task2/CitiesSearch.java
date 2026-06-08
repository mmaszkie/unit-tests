package com.example.unittests.task2;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class CitiesSearch {

    private static final int MINIMUM_QUERY_LENGTH = 2;
    private static final int MAXIMUM_RESULTS = 3;
    private static final int HIGH_RELEVANCE_SCORE = 50;
    private static final int LOW_RELEVANCE_SCORE = 10;

    private final Set<String> availableCities;

    CitiesSearch(Set<String> availableCities) {
        this.availableCities = availableCities;
    }

    List<String> search(String query) {
        if (!isValidQuery(query)) {
            return List.of();
        }

        String normalizedQuery = normalizeText(query);
        return performExactSearch(normalizedQuery);
    }

    private boolean isValidQuery(String query) {
        return query != null && query.trim().length() >= MINIMUM_QUERY_LENGTH;
    }

    private List<String> performExactSearch(String normalizedQuery) {
        return availableCities.stream()
                .filter(city -> containsAllQueryTokens(normalizeText(city), normalizedQuery))
                .sorted((firstCity, secondCity) -> sortByRelevanceAndName(firstCity, secondCity, normalizedQuery))
                .limit(MAXIMUM_RESULTS)
                .toList();
    }

    private boolean containsAllQueryTokens(String normalizedCity, String normalizedQuery) {
        String[] queryTokens = normalizedQuery.split("\\s+");
        return Arrays.stream(queryTokens).allMatch(normalizedCity::contains);
    }

    private int sortByRelevanceAndName(String firstCity, String secondCity, String normalizedQuery) {
        int firstCityScore = calculateRelevanceScore(normalizeText(firstCity), normalizedQuery);
        int secondCityScore = calculateRelevanceScore(normalizeText(secondCity), normalizedQuery);

        if (firstCityScore != secondCityScore) {
            return Integer.compare(secondCityScore, firstCityScore);
        }

        return firstCity.compareToIgnoreCase(secondCity);
    }

    private int calculateRelevanceScore(String normalizedCity, String normalizedQuery) {
        return normalizedCity.startsWith(normalizedQuery)
                ? HIGH_RELEVANCE_SCORE
                : LOW_RELEVANCE_SCORE;
    }

    private String normalizeText(String text) {
        return StringUtils.stripAccents(text.trim()).toLowerCase();
    }

}