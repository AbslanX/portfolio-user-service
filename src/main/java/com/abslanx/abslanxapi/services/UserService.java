package com.abslanx.abslanxapi.services;

import com.abslanx.abslanxapi.models.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User saveUser(User user);

    ResponseEntity<?> createAuthenticationToken(User user);
}
