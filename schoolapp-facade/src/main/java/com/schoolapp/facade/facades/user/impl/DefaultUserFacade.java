package com.schoolapp.facade.facades.user.impl;

import com.schoolapp.core.exceptions.EmailExistsException;
import com.schoolapp.core.exceptions.UserNotFoundException;
import com.schoolapp.core.services.user.UserService;
import com.schoolapp.facade.converters.Converter;
import com.schoolapp.facade.data.user.LoginData;
import com.schoolapp.facade.data.user.RegisterData;
import com.schoolapp.facade.exceptions.ConversionException;
import com.schoolapp.facade.facades.user.UserFacade;
import com.schoolapp.model.user.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component("userFacade")
@ComponentScan(basePackages = {"com.schoolapp"})
public class DefaultUserFacade implements UserFacade {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultUserFacade.class);

    private Converter<RegisterData, UserModel> registerDataConverter;

    private Converter<LoginData, UserModel> loginDataConverter;

    private UserService userService;

    @Override
    public boolean register(RegisterData data) {

        try {
            getUserService().registerUser(getRegisterDataConverter().convert(data));
        } catch (EmailExistsException | ConversionException e) {
            LOG.error(e.getMessage(), e);
            return false;
        }

        return true;
    }

    @Override
    public boolean login(LoginData data) {
        try {
            getUserService().loadUserByUsername(data.getEmail());
        } catch (UsernameNotFoundException e) {
            LOG.warn(e.getMessage(), e);
            return false;
        }

        return true;
    }

    public Converter<RegisterData, UserModel> getRegisterDataConverter() {
        return registerDataConverter;
    }

    @Autowired
    @Qualifier("registerConverter")
    public void setRegisterDataConverter(Converter<RegisterData, UserModel> registerDataConverter) {
        this.registerDataConverter = registerDataConverter;
    }

    public UserService getUserService() {
        return userService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Converter<LoginData, UserModel> getLoginDataConverter() {
        return loginDataConverter;
    }

    @Autowired
    @Qualifier("loginConverter")
    public void setLoginDataConverter(Converter<LoginData, UserModel> loginDataConverter) {
        this.loginDataConverter = loginDataConverter;
    }
}
