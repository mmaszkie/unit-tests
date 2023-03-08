package com.example.unittests.task0;

import java.util.List;

class StringUtils {

    String concatenate(List<String> strings) {
        return strings.stream().reduce("", (x, y) -> x + y);
    }

}
