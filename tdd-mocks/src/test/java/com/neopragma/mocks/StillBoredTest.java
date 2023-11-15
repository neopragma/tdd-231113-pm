package com.neopragma.mocks;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StillBoredTest {

    @Spy
    StillBored bored;

    @Mock
    BufferedReader responseData;

    @Test
    public void it_gets_a_suggested_activity() throws IOException {
        when(responseData.readLine())
                .thenReturn("Something to do")
                .thenReturn(null);
        doReturn(responseData).when(bored).getBufferedReader();
        StillBored bored = new StillBored();
        assertEquals("Something to do", bored.whatShouldIDo());
    }

}
