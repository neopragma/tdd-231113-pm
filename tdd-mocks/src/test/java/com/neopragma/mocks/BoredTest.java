package com.neopragma.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BoredTest {

    @Mock
    BufferedReader responseData;

    @Test
    public void it_gets_a_suggested_activity() throws IOException {
        when(responseData.readLine())
                .thenReturn("Something to do")
                .thenReturn(null);
        Bored bored = new Bored();
        assertEquals("Something to do", bored.whatShouldIDo(responseData));
        verify(responseData, times(2)).readLine();
    }

    @Test
    public void it_reads_the_response_data_using_the_default_method() throws IOException {

    }

}
