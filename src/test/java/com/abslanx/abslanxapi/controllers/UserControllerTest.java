package com.abslanx.abslanxapi.controllers;

import com.abslanx.abslanxapi.models.AuthenticationResponse;
import com.abslanx.abslanxapi.models.User;
import com.abslanx.abslanxapi.models.UserResponseDTO;
import com.abslanx.abslanxapi.security.JwtUtil;
import com.abslanx.abslanxapi.services.CustomUserDetailService;
import com.abslanx.abslanxapi.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class})
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailService customUserDetailService;

    @Test
    void shouldSaveAUser() throws Exception {
        User newUser = new User();
        newUser.setEmail("email1@email.com");
        newUser.setUsername("username1");
        newUser.setFirstName("firstName");
        newUser.setLastName("lastName");
        newUser.setPassword("password");

        UserResponseDTO userResponse = new UserResponseDTO();
        userResponse.setEmail(newUser.getEmail());
        userResponse.setUsername(newUser.getUsername());
        userResponse.setFirstName(newUser.getFirstName());
        userResponse.setLastName(newUser.getLastName());

        String newUserJson = new ObjectMapper().writeValueAsString(newUser);

        when(userService.saveUser(any(User.class))).thenReturn(userResponse);

        mockMvc.perform(post("/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(userResponse.getUsername()))
                .andExpect(jsonPath("$.firstName").value(userResponse.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(userResponse.getLastName()))
                .andExpect(jsonPath("$.email").value(userResponse.getEmail()));


        verify(userService).saveUser(any(User.class));

    }

    @Test
    void shouldHandleSaveUserError() throws Exception {
        User newUser = new User();
        newUser.setEmail("email2@email.com");
        newUser.setUsername("username2");
        newUser.setFirstName("firstName2");
        newUser.setLastName("lastName2");
        newUser.setPassword("password2");

        when(userService.saveUser(any(User.class))).thenThrow(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save user"));

        String newUserJson = new ObjectMapper().writeValueAsString(newUser);

        mockMvc.perform(post("/users/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isInternalServerError());
    }
    @Test
    void shouldCreateAuthenticationToken() throws Exception {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        String token = "mockToken";
        ResponseEntity responseEntity = ResponseEntity.ok(new AuthenticationResponse(token));

        when(userService.createAuthenticationToken(any(User.class))).thenReturn(responseEntity);

        String userJson = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string(new ObjectMapper().writeValueAsString(new AuthenticationResponse(token))));

    }

}
