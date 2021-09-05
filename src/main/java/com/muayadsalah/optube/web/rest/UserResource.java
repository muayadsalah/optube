package com.muayadsalah.optube.web.rest;

import com.muayadsalah.optube.domain.User;
import com.muayadsalah.optube.repository.UserRepository;
import com.muayadsalah.optube.service.UserService;
import com.muayadsalah.optube.service.dto.*;
import com.muayadsalah.optube.web.rest.errors.UsernameAlreadyUsedException;
import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing users.
 * <p>
 * This class accesses the {@link User} entity, and needs to fetch its collection of authorities.
 * <p>
 * For a normal use-case, it would be better to have an eager relationship between User and Authority,
 * and send everything to the client side: there would be no View Model and DTO, a lot less code, and an outer-join
 * which would be good for performance.
 * <p>
 * We use a View Model and a DTO for 3 reasons:
 * <ul>
 * <li>We want to keep a lazy association between the user and the authorities, because people will
 * quite often do relationships with the user, and we don't want them to get the authorities all
 * the time for nothing (for performance reasons). This is the #1 goal: we should not impact our users'
 * application because of this use-case.</li>
 * <li> Not having an outer join causes n+1 requests to the database. This is not a real issue as
 * we have by default a second-level cache. This means on the first HTTP call we do the n+1 requests,
 * but then all authorities come from the cache, so in fact it's much better than doing an outer join
 * (which will get lots of data from the database, for each HTTP call).</li>
 * <li> As this manages users, for security reasons, we'd rather have a DTO layer.</li>
 * </ul>
 * <p>
 * Another option would be to have a specific JPA entity graph to handle this case.
 */
@RestController
@RequestMapping("user/v1")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final UserService userService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    public UserResource(UserService userService, UserRepository userRepository) {
        this.userService = userService;
    }

    /**
     * {@code POST  /addNewUser} : register the user.
     *
     * @param addNewUserRequest the managed user View Model.
     * @throws UsernameAlreadyUsedException {@code 400 (Bad Request)} if the username is already used.
     */
    @PostMapping("addNewUser")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> addNewUser(@Validated @RequestBody AddNewUserRequest addNewUserRequest) {
        log.debug("REST request to add new user : {}", addNewUserRequest);
        userService.createUser(addNewUserRequest);
        log.info("New user {} added successfully", addNewUserRequest.getUserName());
        return ResponseEntity.ok().build();
    }

    /**
     * {@code PUT /updateUser} : Updates an existing User.
     *
     * @param updateUserRequest the user to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated user.
     * @throws UsernameAlreadyUsedException {@code 400 (Bad Request)} if the login is already in use.
     */
    @PutMapping("updateUser")
    public ResponseEntity<Void> updateUser(@Validated @RequestBody UpdateUserRequest updateUserRequest) {
        log.debug("REST request to update User : {}", updateUserRequest);
        userService.updateUser(updateUserRequest);
        log.info("User {} updated successfully", updateUserRequest.getUserName());
        return ResponseEntity.ok().build();
    }

    /**
     * {@code GET /users} : get all users.
     *
     * @param retrieveUsersInfoByIdsRequest the userIds information wrapper.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body all users.
     */
    @GetMapping("retrieveUsersInfoByIds")
    public ResponseEntity<RetrieveUsersInfoByIdsResponse> getUsersByIds(@Validated @RequestBody RetrieveUsersInfoByIdsRequest retrieveUsersInfoByIdsRequest) {
        log.debug("REST request to get all Users by ids : {}", retrieveUsersInfoByIdsRequest.getUsersIds());
        final RetrieveUsersInfoByIdsResponse retrieveUsersInfoByIdsResponse = userService.getUsersByIds(retrieveUsersInfoByIdsRequest.getUsersIds());
        return ResponseEntity.ok(retrieveUsersInfoByIdsResponse);
    }

    /**
     * {@code GET getUserDetails/:username} : get the "username" user.
     *
     * @param username the username of the user to find.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the "username" user, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("getUserDetails/{username}")
    public ResponseEntity<RetrieveUserInfoRespond> getUser(@PathVariable String username) {
        log.debug("REST request to get User : {}", username);
        return ResponseUtil.wrapOrNotFound(userService.getUserByUsername(username));
    }

    /**
     * {@code DELETE /users/:login} : delete the "login" User.
     *
     * @param username the login of the user to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("deleteUser/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        log.debug("REST request to delete User: {}", username);
        userService.deleteUser(username);
        return ResponseEntity.noContent().headers(HeaderUtil.createAlert(applicationName, "A user is deleted with identifier " + username, username)).build();
    }
}
