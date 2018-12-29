package com.burachenko.munichhotel.validation;

import com.burachenko.munichhotel.dto.BookingDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class BookingValidator implements Validator {
    @Override
    public boolean supports(final Class<?> clazz) {
        return BookingDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {

    }
}
