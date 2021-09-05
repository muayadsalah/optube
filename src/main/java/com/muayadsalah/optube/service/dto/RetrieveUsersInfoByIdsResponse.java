package com.muayadsalah.optube.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Validated
public class RetrieveUsersInfoByIdsResponse {

    @JsonProperty("usersDetails")
    private List<PublicUserInfo> usersDetails = new ArrayList<>();

    public RetrieveUsersInfoByIdsResponse() {
    }

    public List<PublicUserInfo> getUsersDetails() {
        return usersDetails;
    }

    public void setUsersDetails(List<PublicUserInfo> usersDetails) {
        this.usersDetails = usersDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveUsersInfoByIdsResponse that = (RetrieveUsersInfoByIdsResponse) o;
        return Objects.equals(usersDetails, that.usersDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersDetails);
    }

    @Override
    public String toString() {
        return "RetrieveUsersInfoByIdsResponse{" +
            "usersDetails=" + usersDetails +
            '}';
    }
}
