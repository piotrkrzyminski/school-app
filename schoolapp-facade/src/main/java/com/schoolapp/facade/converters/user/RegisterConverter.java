package com.schoolapp.facade.converters.user;

import com.schoolapp.facade.converters.AbstractConverter;
import com.schoolapp.facade.data.user.RegisterData;
import com.schoolapp.facade.exceptions.ConversionException;
import com.schoolapp.model.user.UserModel;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Converts {@link RegisterData} to {@link UserModel}
 *
 * @author Piotr Krzyminski
 */
@Component("registerConverter")
public class RegisterConverter extends AbstractConverter<RegisterData, UserModel> {

    @Override
    public void populate(RegisterData source, UserModel target) throws ConversionException {
        Assert.notNull(source, "Parameter source cannot be null");
        Assert.notNull(target, "Parameter target cannot be null");

        UserModel model = new UserModel();

        model.setFirstName(source.getFirstName());
        model.setLastName(source.getLastName());
        model.setEmail(source.getEmail());
        model.setPassword(source.getPassword());
        model.setEnabled(true);
    }
}
