package com.muayadsalah.optube.service.mapper;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.service.dto.AddNewUserRequest;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
public class AddNewUserRequestMapper {
    public AddNewUserRequestMapper() {
    }

    public User userDTOToUser(AddNewUserRequest addNewUserRequest) {
        if (addNewUserRequest == null) {
            return null;
        } else {
            User user = new User();

            user.setUsername(addNewUserRequest.getUserName());
            user.setBirthDate(addNewUserRequest.getBirthDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
            user.setImageUrl(addNewUserRequest.getProfilePictureUrl());

            return user;
        }
    }
}
