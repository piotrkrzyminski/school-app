package com.schoolapp.facade.converters.user;

import com.schoolapp.facade.converters.AbstractConverter;
import com.schoolapp.facade.data.user.LoginData;
import com.schoolapp.facade.exceptions.ConversionException;
import com.schoolapp.model.user.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component("loginConverter")
public class LoginConverter extends AbstractConverter<LoginData, UserModel> {


    @Override
    public void populate(LoginData source, UserModel target) throws ConversionException {

        Assert.notNull(source, "Parameter source cannot be null");
        Assert.notNull(target, "Parameter target cannot be null");

        UserModel model = new UserModel();

        model.setEmail(source.getEmail());
        model.setPassword(source.getPassword());
        model.setEnabled(true);
    }
}
