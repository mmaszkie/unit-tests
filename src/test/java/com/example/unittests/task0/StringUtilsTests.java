package com.example.unittests.task0;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringUtilsTests {

    @Test
    void testConcatenate() {
        assertEquals("abc", new StringUtils().concatenate(List.of("a", "b", "c")));
    }

    @Test
    void shouldCorrectlyConcatenateListOfStrings() {
        // given
        List<String> stringsToConcatenate = List.of("a", "b", "c");

        // when
        String result = new StringUtils().concatenate(stringsToConcatenate);

        // then
        assertEquals("abc", result);
    }

}





