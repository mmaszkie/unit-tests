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

}
