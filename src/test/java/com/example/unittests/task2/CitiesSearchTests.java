package com.example.unittests.task2;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static java.util.Collections.EMPTY_SET;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CitiesSearchTests {

    private final CitiesSearch citiesSearch = new CitiesSearch();

    @Test
    public void shouldNotFindAnyCityWhenInputTextIsNull() {
        // given
        String inputText = null;

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(EMPTY_SET, foundCities);
    }

    @Test
    public void shouldNotFindAnyCityWhenInputTextIsTooShort() {
        // given
        String inputText = "B";

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(EMPTY_SET, foundCities);
    }

    @Test
    public void shouldFindCitiesThatStartWithInputText() {
        // given
        String inputText = "Va";

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(Set.of("Vancouver", "Valencia"), foundCities);
    }

    @Test
    public void shouldFindCitiesThatContainInputText() {
        // given
        String inputText = "ape";

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(Set.of("Budapest"), foundCities);
    }

    @Test
    public void shouldFindCitiesThatEndWithInputText() {
        // given
        String inputText = "rdam";

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(Set.of("Rotterdam", "Amsterdam"), foundCities);
    }

    @Test
    public void shouldFindCitiesIgnoringCaseDifferences() {
        // given
        String inputText = "vA";

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(Set.of("Vancouver", "Valencia"), foundCities);
    }

    @Test
    public void shouldFindAllCitiesWhenInputTextIsAsterisk() {
        // given
        String inputText = "*";
        Set<String> expectedCities = Set.of(
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

        // when
        Set<String> foundCities = citiesSearch.search(inputText);

        // then
        assertEquals(expectedCities, foundCities);
    }

}
