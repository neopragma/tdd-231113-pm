package com.neopragma.rpn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RPNTest
{
    private RPN rpn;

    @BeforeEach
    public void beforeEachTestCase() {
        rpn = new RPN();
    }

    @Test
    public void entering_a_number_adds_the_number_to_the_stack() {
        assertEquals("3.75", rpn.enter("3.75"));
    }

    @Test
    public void it_handles_adding_three_values() {
        rpn.enter("5.0");
        rpn.enter("6.0");
        rpn.enter("+");
        rpn.enter("2.0");
        assertEquals("13.0", rpn.enter("+"));
    }

    @Test
    public void it_handles_a_complex_expression() {
        rpn.enter("1.5");
        rpn.enter("2");
        rpn.enter("+");
        rpn.enter("30");
        assertEquals("105.0", rpn.enter("*"));
    }

    @Test
    public void it_throws_when_wrong_number_of_operands_on_stack() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rpn.enter("+");
        });
        assertEquals("Please enter another number", exception.getMessage());
    }

    @Test
    public void it_throws_when_a_bogus_token_is_entered() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rpn.enter("foo");
        });
        assertEquals("Token entered is neither a number nor an operator", exception.getMessage());
    }
    @Test
    public void it_throws_when_a_null_is_entered() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            rpn.enter(null);
        });
        assertEquals("Token cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("provideValuesForRPN")
    public void it_handles_various_operators(
            String value1, String value2, String operator, String expected) {
        rpn.enter(value1);
        rpn.enter(value2);
        assertEquals(expected, rpn.enter(operator));
    }

    private static Stream<Arguments> provideValuesForRPN() {
        return Stream.of(
                Arguments.of("5.0", "6.5", "+", "11.5"),
                Arguments.of("2.0", "5.0", "*", "10.0"),
                Arguments.of("4.0", "12.0", "-", "8.0"),
                Arguments.of("3", "18", "/", "6.0")
        );
    }


}

