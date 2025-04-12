package com.example.unittests.task2;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.EMPTY_SET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.argumentSet;

class CitiesSearchTests {

    private final CitiesSearch citiesSearch = new CitiesSearch();

    @ParameterizedTest
    @MethodSource("citiesSearchTestCases")
    public void shouldPerformCitiesSearch(String inputText, Set<String> expectedCities) {
        // given / when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(expectedCities, foundCities);
    }

    private static Stream<Arguments> citiesSearchTestCases() {
        return Stream.of(
                argumentSet(
                        "should not find any city when input text is null",
                        null,
                        EMPTY_SET
                ),
                argumentSet(
                        "should not find any city when input text is too short",
                        "B",
                        EMPTY_SET
                ),
                argumentSet(
                        "should find cities that start with input text",
                        "Va",
                        Set.of("Vancouver", "Valencia")
                ),
                argumentSet(
                        "should find cities that contain input text",
                        "ape",
                        Set.of("Budapest")
                ),
                argumentSet(
                        "should find cities that end with with input text",
                        "rdam",
                        Set.of("Rotterdam", "Amsterdam")
                ),
                argumentSet(
                        "should find cities ignoring case differences",
                        "vA",
                        Set.of("Vancouver", "Valencia")
                ),
                argumentSet(
                        "should find all cities when input text is asterisk",
                        "*",
                        Set.of(
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
                        )
                )
        );
    }

}
