package com.muayadsalah.optube.service.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

@Validated
public class UserDetails {

    @NotBlank(message = "Username is mandatory and cannot be empty")
    private String userName;

    @NotNull
    private LocalDate birthDate;

    @NotBlank(message = "Profile Pic URL is mandatory and cannot be empty")
    private String profilePictureUrl;

    public UserDetails() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetails that = (UserDetails) o;
        return Objects.equals(userName, that.userName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(profilePictureUrl, that.profilePictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, birthDate, profilePictureUrl);
    }

    @Override
    public String toString() {
        return "AddNewUserRequest{" +
            "userName='" + userName + '\'' +
            ", birthDate=" + birthDate +
            ", profilePictureUrl='" + profilePictureUrl + '\'' +
            '}';
    }
}
