package com.muayadsalah.optube.repository;

import com.muayadsalah.optube.domain.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Spring Data MongoDB repository for the {@link User} entity.
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    String USERS_BY_USERNAME_CACHE = "usersByUsername";

    @Cacheable(cacheNames = USERS_BY_USERNAME_CACHE)
    Optional<User> findOneByUsername(String username);

    List<User> findAllByIdIn(Set<String> ids);
}
