package com.schoolapp.core.services.user;

import com.schoolapp.core.exceptions.EmailExistsException;
import com.schoolapp.core.exceptions.UserNotFoundException;
import com.schoolapp.model.user.UserModel;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Service for user that performs user login and register.
 *
 * @author Piotr Krzyminski
 */
public interface UserService extends UserDetailsService {

    /**
     * Saves new user to database. User can be registered if email passed by him not exists in database.
     *
     * @param user user to save.
     * @throws EmailExistsException selected email already exists in database and registration cannot be performed.
     */
    void registerUser(UserModel user) throws EmailExistsException;

    /**
     * Finds user in database by its unique email value.
     *
     * @param email user's email
     * @return User with specified email.
     * @throws UserNotFoundException user with selected email not exists in database.
     */
    UserModel getUserByEmail(String email) throws UserNotFoundException;
}
