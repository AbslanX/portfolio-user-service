package com.abslanx.abslanxapi.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
