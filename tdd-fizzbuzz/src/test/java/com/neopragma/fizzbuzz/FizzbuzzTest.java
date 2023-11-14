package com.neopragma.fizzbuzz;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FizzbuzzTest
{
    private Fizzbuzz fizzbuzz;

    @BeforeEach
    public void beforeEachTestCase() {
        fizzbuzz = new Fizzbuzz();
    }

    @ParameterizedTest
    @MethodSource("valuesForFizzbuzz")
    public void itReturnsTheInputValueWhenItIsntAFizzBuzzNumber(int number, String result) {
        assertEquals(result, fizzbuzz.processNumber(number));
    }

    private static Stream<Arguments> valuesForFizzbuzz() {
        return Stream.of(
                Arguments.of(1, "1"),
                Arguments.of(2, "2"),
                Arguments.of(3, "Fizz"),
                Arguments.of(5, "Buzz"),
                Arguments.of(6, "Fizz"),
                Arguments.of(10, "Buzz"),
                Arguments.of(15, "FizzBuzz"),
                Arguments.of(45, "FizzBuzz")
        );
    }

}
