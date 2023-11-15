package com.neopragma.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CatFactsTest {

    @Mock
    CatFacts facts;

    @Test
    public void play() throws Exception {
        when(facts.justTheFactsMaam())
                .thenReturn("\"Here\u0027s a cat fact.\"");
        FormatCatFact sut = new FormatCatFact();
        verifyNoInteractions(facts);
        assertEquals("\"\\\"Here\\u0027s a cat fact.\\\"\"", sut.formattedCatFact(facts));
        verify(facts).justTheFactsMaam();
        verify(facts, atLeast(1)).justTheFactsMaam();
        verify(facts, times(1)).justTheFactsMaam();
    }
}
