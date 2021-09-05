package com.muayadsalah.optube.service.dto;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class PublicUserInfo {

    private String userId = null;
    private String userName;
    private String profilePictureUrl;

    public PublicUserInfo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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
        PublicUserInfo that = (PublicUserInfo) o;
        return Objects.equals(userId, that.userId) && Objects.equals(userName, that.userName) && Objects.equals(profilePictureUrl, that.profilePictureUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, profilePictureUrl);
    }

    @Override
    public String toString() {
        return "PublicUserInfo{" +
            "userId='" + userId + '\'' +
            ", userName='" + userName + '\'' +
            ", profilePictureUrl='" + profilePictureUrl + '\'' +
            '}';
    }
}
