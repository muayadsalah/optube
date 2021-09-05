package com.muayadsalah.optube.service.dto;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
public class RetrieveUserInfoRespond extends UserDetails {

    private String userId = null;
    private Integer totalLikes = null;

    public RetrieveUserInfoRespond() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(Integer totalLikes) {
        this.totalLikes = totalLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RetrieveUserInfoRespond that = (RetrieveUserInfoRespond) o;
        return Objects.equals(userId, that.userId) && Objects.equals(totalLikes, that.totalLikes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, totalLikes);
    }

    @Override
    public String toString() {
        return "RetrieveUserInfoRespond{" +
            "userId='" + userId + '\'' +
            ", totalLikes=" + totalLikes +
            '}';
    }
}
