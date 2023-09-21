package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }

    @Test
    void whenPairWithComment() {
        String path = "./data/with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThatThrownBy(() -> config.value("key")).isInstanceOf(UnsupportedOperationException.class)
                .hasMessage("Don't impl this method yet!");
    }

    @Test
    void whenIllegalArgumentException() {
        String path = "./data/pair.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(" Line contains an invalid template =keys");
    }

    @Test
    void whenNotValue() {
        String path = "./data/value.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class)
                .hasMessage(" Line contains an invalid template values=");
    }
}