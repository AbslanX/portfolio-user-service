package com.abslanx.abslanxapi.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.abslanx.abslanxapi.Validation.MinAge;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class User {

    @Id
    private String id;

    // Basic Info
    @NotNull(message = "Username cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_.]{2,19}$", message = "Invalid username")
    private String username;
    @NotNull(message = "Password cannot be null")   
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Invalid password")
    private String password; // hashed
    @NotNull(message = "Email cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$", message = "Invalid email")
    private String email;

    // Personal Details
    @NotNull(message = "First name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Invalid first name")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Pattern(regexp = "^[a-zA-Z]{2,}$", message = "Invalid last name")
    private String lastName;
    
    @NotNull(message = "Date of birth cannot be null")
    @MinAge(value = 15, message = "You must be 15 years or older")
    private LocalDate dateOfBirth;
    
    private String gender;

    // Location Information
    private String city;
    private String state;
    private String country;

    // Professional Information
    private String occupation;


    // Additional Information
    private String biography;
    private String instagramUrl;
    private String xUrl;
    private String tumblrUrl;
    private String tiktokUrl;
    private String otherUrl;
    private String profilePictureURL;

    // Preferences and Settings
    private String language;
    private String timeZone;
    private Set<String> accountPrivacySettings;

    // Role and Permissions
    private Set<String> roles;

    // Audit Fields
    private LocalDate createdAt;
    private LocalDate updatedAt;
}
