package com.abslanx.abslanxapi.services;

import com.abslanx.abslanxapi.models.User;
import com.abslanx.abslanxapi.models.UserResponseDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    UserResponseDTO saveUser(User user);

    ResponseEntity<?> createAuthenticationToken(User user);
}
