package ru.job4j.ex;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FactTest  {
    @Test
    public void whenN1IsCalc120() {
        int n = 5;
        int expected = 120;
        int result = new Fact().calc(n);
        assertThat(result).isEqualTo(expected);
    }

    @Test
    public void whenException() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Fact().calc(-5);
                });
        assertThat(exception.getMessage()).isEqualTo("N could not be less then 0.");
    }

}