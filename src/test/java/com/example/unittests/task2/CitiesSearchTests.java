package com.example.unittests.task2;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
