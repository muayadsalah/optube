package com.muayadsalah.optube.service;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.repository.UserRepository;
import com.muayadsalah.optube.service.dto.AddNewUserRequest;
import com.muayadsalah.optube.service.dto.RetrieveUserInfoRespond;
import com.muayadsalah.optube.service.dto.RetrieveUsersInfoByIdsResponse;
import com.muayadsalah.optube.service.dto.UpdateUserRequest;
import com.muayadsalah.optube.service.mapper.AddNewUserRequestMapper;
import com.muayadsalah.optube.service.mapper.RetrieveUserInfoResponseMapper;
import com.muayadsalah.optube.service.mapper.RetrieveUsersInfoByIdsResponseMapper;
import com.muayadsalah.optube.service.mapper.UpdateUserRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service class for managing users.
 */
@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final AddNewUserRequestMapper addNewUserRequestMapper;
    private final UpdateUserRequestMapper updateUserRequestMapper;
    private final RetrieveUserInfoResponseMapper retrieveUserInfoResponseMapper;
    private final RetrieveUsersInfoByIdsResponseMapper retrieveUsersInfoByIdsResponseMapper;

    public UserService(UserRepository userRepository, AddNewUserRequestMapper addNewUserRequestMapper,
                       UpdateUserRequestMapper updateUserRequestMapper, RetrieveUserInfoResponseMapper retrieveUserInfoResponseMapper,
                       RetrieveUsersInfoByIdsResponseMapper retrieveUsersInfoByIdsResponseMapper) {
        this.userRepository = userRepository;
        this.addNewUserRequestMapper = addNewUserRequestMapper;
        this.updateUserRequestMapper = updateUserRequestMapper;
        this.retrieveUserInfoResponseMapper = retrieveUserInfoResponseMapper;
        this.retrieveUsersInfoByIdsResponseMapper = retrieveUsersInfoByIdsResponseMapper;
    }

    public User createUser(AddNewUserRequest addNewUserRequest) {
        userRepository.findOneByUsername(addNewUserRequest.getUserName().toLowerCase()).ifPresent(existingUser -> {
            throw new UsernameAlreadyUsedException();
        });
        User newUser = addNewUserRequestMapper.userDTOToUser(addNewUserRequest);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    public User updateUser(UpdateUserRequest updateUserRequest) {
        String userId = userRepository.findOneByUsername(updateUserRequest.getUserName().toLowerCase())
            .map(User::getId)
            .orElseThrow(() -> new IllegalArgumentException("User Not Found: " + updateUserRequest.getUserName()));

        User updatedUser = updateUserRequestMapper.userDTOToUser(userId, updateUserRequest);
        updatedUser = userRepository.save(updatedUser);
        log.debug("Created Information for User: {}", updatedUser);
        return updatedUser;
    }

    public void deleteUser(String login) {
        userRepository.findOneByUsername(login).ifPresent(user -> {
            userRepository.delete(user);
            log.debug("Deleted User: {}", user);
        });
    }

    public RetrieveUsersInfoByIdsResponse getUsersByIds(Set<String> userIds) {
        List<User> users = userRepository.findAllByIdIn(userIds);
        RetrieveUsersInfoByIdsResponse retrieveUsersInfoByIdsResponse = retrieveUsersInfoByIdsResponseMapper.userToUserInfoResponse(users);
        log.debug("Retrieved {} public users info", users.size());
        return retrieveUsersInfoByIdsResponse;
    }

    public Optional<RetrieveUserInfoRespond> getUserByUsername(String username) {
        return userRepository.findOneByUsername(username)
            .map(retrieveUserInfoResponseMapper::userToUserInfoResponse);
    }
}
