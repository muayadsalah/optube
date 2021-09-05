package com.muayadsalah.optube.service.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;
import java.util.Set;

public class RetrieveUsersInfoByIdsRequest {

    @NotEmpty
    private Set<String> usersIds;

    public RetrieveUsersInfoByIdsRequest() {
    }

    public Set<String> getUsersIds() {
        return usersIds;
    }

    public void setUsersIds(Set<String> usersIds) {
        this.usersIds = usersIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RetrieveUsersInfoByIdsRequest that = (RetrieveUsersInfoByIdsRequest) o;
        return Objects.equals(usersIds, that.usersIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usersIds);
    }

    @Override
    public String toString() {
        return "RetrieveUsersInfoByIdsRequest{" +
            "usersIds=" + usersIds +
            '}';
    }
}
