package com.abslanx.abslanxapi.services;

import com.abslanx.abslanxapi.models.AuthenticationResponse;
import com.abslanx.abslanxapi.models.User;
import com.abslanx.abslanxapi.models.UserResponseDTO;
import com.abslanx.abslanxapi.repositories.UserRepository;
import com.abslanx.abslanxapi.security.JwtUtil;
import com.abslanx.abslanxapi.utilities.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserResponseDTO saveUser(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setCreatedAt(LocalDate.now());
        user.setUpdatedAt(LocalDate.now());
        User savedUser = userRepository.save(user);
        return mapUserToUserResponseDTO(savedUser);
    }

    private UserResponseDTO mapUserToUserResponseDTO(User savedUser) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setEmail(savedUser.getEmail());
        dto.setId(savedUser.getId());
        dto.setCity(savedUser.getCity());
        dto.setCountry(savedUser.getCountry());
        dto.setBiography(savedUser.getBiography());
        dto.setOccupation(savedUser.getOccupation());
        dto.setFirstName(savedUser.getFirstName());
        dto.setLastName(savedUser.getLastName());
        dto.setInstagramUrl(savedUser.getInstagramUrl());
        dto.setState(savedUser.getState());
        dto.setUsername(savedUser.getUsername());
        dto.setDateOfBirth(savedUser.getDateOfBirth());
        dto.setOtherUrl(savedUser.getOtherUrl());
        dto.setXUrl(savedUser.getXUrl());
        dto.setTiktokUrl(savedUser.getTiktokUrl());
        dto.setTumblrUrl(savedUser.getTumblrUrl());
        dto.setProfilePictureURL(savedUser.getProfilePictureURL());
        return dto;
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(User user) {

        final CustomUserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        if (!passwordEncoder.matches(user.getPassword(), userDetails.getPassword())){
            throw new BadCredentialsException("Incorrect password");
        }

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
