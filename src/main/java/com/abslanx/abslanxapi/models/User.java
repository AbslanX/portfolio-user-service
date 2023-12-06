package com.abslanx.abslanxapi.models;

import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
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
    
    // Location Information
    @Pattern(regexp = "^[a-zA-Z\\s,.'-]{2,}$", message = "Invalid city name")
    private String city;

    @Pattern(regexp = "^[a-zA-Z\\s,.'-]{2,}$", message = "Invalid state name")
    private String state;

    @Pattern(regexp = "^[a-zA-Z\\s,.'-]{2,}$", message = "Invalid country name")
    private String country;


    // Professional Information
    @Size(max = 100, message = "Occupation can't be longer than 100 characters")
    private String occupation;


    // Additional Information
    @Size(max = 500, message = "Biography can't be longer than 500 characters")
    private String biography;

    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "Invalid URL")
    private String instagramUrl;

    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "Invalid URL")
    private String xUrl;

    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "Invalid URL")
    private String tumblrUrl;

    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "Invalid URL")
    private String tiktokUrl;

    @Pattern(regexp = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$", message = "Invalid URL")
    private String otherUrl;

    private String profilePictureURL;

    // Role and Permissions
    private Set<String> roles;

    // Audit Fields
    @PastOrPresent(message = "Created date can't be in the future")
    private LocalDate createdAt;

    @PastOrPresent(message = "Updated date can't be in the future")
    private LocalDate updatedAt;
}
