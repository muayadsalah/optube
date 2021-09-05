package com.muayadsalah.optube.service.mapper;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.service.dto.UpdateUserRequest;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class UpdateUserRequestMapper {
    public UpdateUserRequestMapper() {
    }

    public User userDTOToUser(String userId, UpdateUserRequest updateUserRequest) {
        if (updateUserRequest == null) {
            return null;
        } else {
            User user = new User(userId);

            user.setUsername(updateUserRequest.getUserName());
            user.setBirthDate(updateUserRequest.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            user.setImageUrl(updateUserRequest.getProfilePictureUrl());

            return user;
        }
    }
}
