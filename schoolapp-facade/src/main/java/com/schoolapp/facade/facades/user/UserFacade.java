package com.schoolapp.facade.facades.user;

import com.schoolapp.facade.data.user.LoginData;
import com.schoolapp.facade.data.user.RegisterData;

/**
 * Facade for {@link com.schoolapp.model.user.UserModel}
 *
 * @author Piotr Krzyminski
 */
public interface UserFacade {

    /**
     * Get data from front and perform user registration
     *
     * @param data user's data
     * @return true if registration finished success or false.
     */
    boolean register(RegisterData data);

    /**
     * Get data from login form and perform user log-in.
     *
     * @param data user's data
     * @return true if login finished success or false.
     */
    boolean login(LoginData data);
}
