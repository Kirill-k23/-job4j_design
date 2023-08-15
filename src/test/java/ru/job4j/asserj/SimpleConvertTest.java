package ru.job4j.asserj;

import org.assertj.core.data.Index;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class SimpleConvertTest {
    @Test
    void checkArray() {
        SimpleConvert simpleConvert = new SimpleConvert();
        String[] array = simpleConvert.toArray("first", "second", "three", "four", "five");
        assertThat(array).hasSize(5)
                .contains("second")
                .contains("first", Index.atIndex(0))
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("first", Index.atIndex(1));
    }

    @Test
    void checkList() {
        SimpleConvert simpleConvert = new SimpleConvert();
        List<String> list = simpleConvert.toList("first", "second", "three", "four", "five");
        assertThat(list).hasSize(5)
                .contains("second")
                .contains("second", Index.atIndex(1))
                .containsAnyOf("zero", "first", "second")
                .doesNotContain("three", Index.atIndex(3));
    }

    @Test
    void checkSet() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Set<String> set = simpleConvert.toSet("first", "second", "three", "four", "five", "second", "four");
        assertThat(set).hasSize(5)
                .contains("five")
                .containsOnly("first", "second", "three", "four", "five", "second")
                .containsAnyOf("zero", "second", "six")
                .doesNotContain("six", "zero", "seven");
    }

    @Test
    void checkMap() {
        SimpleConvert simpleConvert = new SimpleConvert();
        Map<String, Integer> map = simpleConvert.toMap("first", "second", "three", "four", "five", "second");
        assertThat(map).hasSize(5)
                .containsKeys("second", "first")
                .doesNotContainKeys("six", "zero", "seven")
                .doesNotContainValue(11)
                .containsEntry("second", 1);
    }
}