package com.abslanx.abslanxapi.utilities;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class CustomPasswordEncoderTest {

    private final CustomPasswordEncoder customPasswordEncoder = new CustomPasswordEncoder();

    @Test
    void testEncode() {
        String rawPassword = "password";
        String encodedPassword = customPasswordEncoder.encode(rawPassword);

        assertNotNull(encodedPassword);
        assertNotEquals(rawPassword, encodedPassword);
    }

    @Test
    void testMatches(){
        String rawPassword = "password";
        String encodedPassword = customPasswordEncoder.encode(rawPassword);

        assertTrue(customPasswordEncoder.matches(rawPassword, encodedPassword));
    }

}
