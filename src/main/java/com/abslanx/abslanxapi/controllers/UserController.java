package com.abslanx.abslanxapi.controllers;

import com.abslanx.abslanxapi.models.User;
import com.abslanx.abslanxapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/save")
    public User saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws BadCredentialsException {
        return userService.createAuthenticationToken(user);
    }

}
