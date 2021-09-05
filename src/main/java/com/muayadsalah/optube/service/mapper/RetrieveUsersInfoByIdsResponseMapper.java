package com.muayadsalah.optube.service.mapper;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.service.dto.RetrieveUsersInfoByIdsResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RetrieveUsersInfoByIdsResponseMapper {

    private final PublicUserInfoMapper publicUserInfoMapper;

    public RetrieveUsersInfoByIdsResponseMapper(PublicUserInfoMapper publicUserInfoMapper) {
        this.publicUserInfoMapper = publicUserInfoMapper;
    }

    public RetrieveUsersInfoByIdsResponse userToUserInfoResponse(List<User> users) {
        RetrieveUsersInfoByIdsResponse retrieveUsersInfoByIdsResponse = new RetrieveUsersInfoByIdsResponse();
        if (!CollectionUtils.isEmpty(users)) {
            retrieveUsersInfoByIdsResponse.setUsersDetails(users.stream().map(publicUserInfoMapper::userToUserInfoResponse).collect(Collectors.toList()));
        }
        return retrieveUsersInfoByIdsResponse;
    }
}
