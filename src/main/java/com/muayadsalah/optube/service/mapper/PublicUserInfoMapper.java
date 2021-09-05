package com.muayadsalah.optube.service.mapper;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.service.dto.PublicUserInfo;
import org.springframework.stereotype.Service;

@Service
public class PublicUserInfoMapper {
    public PublicUserInfoMapper() {
    }

    public PublicUserInfo userToUserInfoResponse(User user) {
        if (user == null) {
            return null;
        } else {
            PublicUserInfo publicUserInfo = new PublicUserInfo();

            publicUserInfo.setUserId(user.getId());
            publicUserInfo.setUserName(user.getUsername());
            publicUserInfo.setProfilePictureUrl(user.getImageUrl());

            return publicUserInfo;
        }
    }
}
