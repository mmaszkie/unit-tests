package com.example.unittests.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CitiesSearchTests {

    private static final Set<String> AVAILABLE_CITIES = Set.of(
            "Warszawa",
            "Wrocław",
            "Kraków",
            "Gdynia",
            "Nowy Jork",
            "Los Angeles",
            "San Francisco",
            "Zakopane",
            "Ankara",
            "Santiago"
    );

    private final CitiesSearch citiesSearch = new CitiesSearch(AVAILABLE_CITIES);

    @Test
    public void shouldPerformSubstringMatch() {
        // given
        String query = "Fran";

        // when
        List<String> citiesFound = citiesSearch.search(query);

        // then
        assertEquals(List.of("San Francisco"), citiesFound);
    }

    @Test
    public void shouldSupportCaseInsensitivity() {
        // given
        String query = "wro";

        // when
        List<String> citiesFound = citiesSearch.search(query);

        // then
        assertEquals(List.of("Wrocław"), citiesFound);
    }

    @Test
    public void shouldValidateQueryLength() {
        // given
        String query = "a";

        // when
        List<String> citiesFound = citiesSearch.search(query);

        // then
        assertTrue(citiesFound.isEmpty());
    }

    @Test
    public void shouldApplyAlphabeticalSorting() {
        // given
        String query = "ar";

        // when
        List<String> citiesFound = citiesSearch.search(query);

        // then
        assertEquals(List.of("Ankara", "Warszawa"), citiesFound);
    }

    @Test
    public void shouldEnforceLimitingResults() {
        // given
        String query = "an";

        // when
        List<String> citiesFound = citiesSearch.search(query);

        // then
        assertEquals(3, citiesFound.size());
    }

}
