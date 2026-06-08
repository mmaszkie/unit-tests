package com.example.unittests.task2;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

class CitiesSearch {

    private static final int MINIMUM_QUERY_LENGTH = 2;
    private static final int FUZZY_SEARCH_MINIMUM_QUERY_LENGTH = 4;
    private static final int MAXIMUM_FUZZY_DISTANCE = 1;
    private static final int MAXIMUM_RESULTS = 3;
    private static final int HIGH_RELEVANCE_SCORE = 50;
    private static final int LOW_RELEVANCE_SCORE = 10;

    private final Set<String> availableCities;
    private final LevenshteinDistance levenshteinDistance;

    CitiesSearch(Set<String> availableCities) {
        this.availableCities = availableCities;
        this.levenshteinDistance = LevenshteinDistance.getDefaultInstance();
    }

    List<String> search(String query) {
        if (!isValidQuery(query)) {
            return List.of();
        }

        String normalizedQuery = normalizeText(query);
        List<String> exactMatches = performExactSearch(normalizedQuery);

        if (exactMatches.isEmpty() && shouldAttemptFuzzySearch(query)) {
            return performFuzzySearch(normalizedQuery);
        }

        return exactMatches;
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

    private boolean shouldAttemptFuzzySearch(String query) {
        return query.trim().length() >= FUZZY_SEARCH_MINIMUM_QUERY_LENGTH;
    }

    private List<String> performFuzzySearch(String normalizedQuery) {
        return availableCities.stream()
                .filter(city -> isFuzzyMatch(normalizeText(city), normalizedQuery))
                .limit(MAXIMUM_RESULTS)
                .toList();
    }

    private boolean isFuzzyMatch(String normalizedCity, String normalizedQuery) {
        return levenshteinDistance.apply(normalizedCity, normalizedQuery) <= MAXIMUM_FUZZY_DISTANCE;
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