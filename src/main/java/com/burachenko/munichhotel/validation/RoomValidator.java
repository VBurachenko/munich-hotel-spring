package com.burachenko.munichhotel.validation;

import com.burachenko.munichhotel.dto.RoomDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class RoomValidator implements Validator {
    @Override
    public boolean supports(final Class<?> clazz) {
        return RoomDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {

    }
}
