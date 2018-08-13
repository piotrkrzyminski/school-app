package com.schoolapp.facade.converters;

import com.schoolapp.facade.exceptions.ConversionException;
import com.schoolapp.facade.populators.Populator;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractConverter<SOURCE, TARGET> implements Converter<SOURCE, TARGET>, Populator<SOURCE, TARGET> {

    private Class<TARGET> targetClass;

    @Override
    public TARGET convert(SOURCE source) throws ConversionException {
        final TARGET target = createFromClass();
        populate(source, target);

        return target;
    }

    @Override
    public abstract void populate(SOURCE source, TARGET target) throws ConversionException;

    protected TARGET createFromClass() {
        try {
            return targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public Class<TARGET> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<TARGET> targetClass) {
        this.targetClass = targetClass;

        if(targetClass == null) {
            createFromClass();
        }
    }
}
