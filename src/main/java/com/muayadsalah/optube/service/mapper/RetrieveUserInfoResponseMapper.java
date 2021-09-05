package com.muayadsalah.optube.service.mapper;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.service.dto.RetrieveUserInfoRespond;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class RetrieveUserInfoResponseMapper {
    public RetrieveUserInfoResponseMapper() {
    }

    public RetrieveUserInfoRespond userToUserInfoResponse(User user) {
        if (user == null) {
            return null;
        } else {
            RetrieveUserInfoRespond retrieveUserInfoRespond = new RetrieveUserInfoRespond();

            retrieveUserInfoRespond.setUserId(user.getId());
            retrieveUserInfoRespond.setUserName(user.getUsername());
            retrieveUserInfoRespond.setBirthDate(LocalDate.ofInstant(user.getBirthDate(), ZoneId.systemDefault()));
            retrieveUserInfoRespond.setProfilePictureUrl(user.getImageUrl());
            retrieveUserInfoRespond.setTotalLikes(user.getTotalLikes());

            return retrieveUserInfoRespond;
        }
    }
}
