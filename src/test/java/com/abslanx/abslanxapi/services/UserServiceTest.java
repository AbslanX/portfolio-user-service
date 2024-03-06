package com.abslanx.abslanxapi.services;

import com.abslanx.abslanxapi.models.AuthenticationResponse;
import com.abslanx.abslanxapi.models.User;
import com.abslanx.abslanxapi.models.UserResponseDTO;
import com.abslanx.abslanxapi.repositories.UserRepository;
import com.abslanx.abslanxapi.security.JwtUtil;
import com.abslanx.abslanxapi.utilities.CustomPasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    CustomPasswordEncoder passwordEncoder;

    @Mock
    CustomUserDetailService userDetailsService;

    @Mock
    JwtUtil jwtUtil;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setPassword("plaintextPassword");
        user.setUsername("username");
        User savedUser = new User();

        when(userRepository.save(user)).thenReturn(user);
        when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());

        UserResponseDTO result = userService.saveUser(user);

        verify(passwordEncoder).encode(user.getPassword());
        verify(userRepository).save(any(User.class));

        assertNotNull(result);
        assertEquals(user.getUsername(), result.getUsername());
    }

    @Test
    public void testCreateAuthenticationToken() {
        User user = new User();
        user.setPassword("password");
        user.setUsername("username");

        CustomUserDetails userDetails = new CustomUserDetails(user);
        when(userDetailsService.loadUserByUsername(user.getUsername())).thenReturn(userDetails);
        when(passwordEncoder.matches(user.getPassword(), userDetails.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(userDetails)).thenReturn("token");

        ResponseEntity<?> result = userService.createAuthenticationToken(user);

        verify(userDetailsService, times(1)).loadUserByUsername(user.getUsername());
        verify(passwordEncoder, times(1)).matches(user.getPassword(), userDetails.getPassword());
        verify(jwtUtil, times(1)).generateToken(userDetails);

        assertEquals("token", ((AuthenticationResponse) result.getBody()).getJwt());
    }
}
