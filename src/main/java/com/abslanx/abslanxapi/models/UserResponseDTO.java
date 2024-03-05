package com.abslanx.abslanxapi.models;

import com.abslanx.abslanxapi.Validation.MinAge;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    @Id
    private String id;

    // Basic Info
    @NotNull(message = "Username cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9][a-zA-Z0-9_.]{2,19}$", message = "Invalid username")
    private String username;

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
}
