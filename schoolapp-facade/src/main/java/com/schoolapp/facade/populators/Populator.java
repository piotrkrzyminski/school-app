package com.schoolapp.facade.populators;

import com.schoolapp.facade.exceptions.ConversionException;

/**
 * Interface for populators.
 * Populator sets values in a target instance based on values from source instance.
 *
 * @author Piotr Krzyminski
 */
public interface Populator<SOURCE, TARGET> {

    /**
     * Populate the target instance with values from source instance.
     *
     * @param source the source object.
     * @param target the target object.
     */
    void populate(SOURCE source, TARGET target) throws ConversionException;
}
