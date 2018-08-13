package com.schoolapp.core.services.user.impl;

import com.schoolapp.core.exceptions.EmailExistsException;
import com.schoolapp.core.exceptions.UserNotFoundException;
import com.schoolapp.core.repository.user.UserRepository;
import com.schoolapp.core.services.user.UserService;
import com.schoolapp.model.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Default implementation for {@link UserService}
 *
 * @author Piotr Krzyminski
 */
@Service("userService")
public class DefaultUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserService.class);

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOG.debug("Searching for user with email " + email);

        UserModel user = getUserRepository().findByEmail(email);
        if(user == null) {
            LOG.warn("User with email " + email + " not exists");
            throw new UsernameNotFoundException("User not found");
        }

        LOG.debug("Found user with email " + email);

        return new User(user.getEmail(), user.getPassword(), user.isEnabled(),
                true, true, true, Collections.emptyList());
    }

    @Override
    public void registerUser(UserModel user) throws EmailExistsException {

        try {
            getUserByEmail(user.getEmail());

            LOG.debug("Saving new user with email " + user.getEmail());
            getUserRepository().save(user);
        } catch (UserNotFoundException e) {
            throw new EmailExistsException();
        }
    }

    @Override
    public UserModel getUserByEmail(String email) throws UserNotFoundException {

        LOG.debug("Searching for user with email " + email);

        UserModel result = getUserRepository().findByEmail(email);
        if(result == null) {
            LOG.warn("User with email " + email + " not found");
            throw new UserNotFoundException();
        }

        LOG.debug("Found user with email " + email);
        return result;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
