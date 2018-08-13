package com.schoolapp.facade.converters;

import com.schoolapp.facade.exceptions.ConversionException;

/**
 * Conversion from model object to dto.
 *
 * @author Piotr Krzyminski
 */
public interface Converter<SOURCE, TARGET> {

    TARGET convert(SOURCE source) throws ConversionException;
}
