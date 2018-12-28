package com.burachenko.munichhotel.validation;

import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(final Class<?> clazz) {
        return UserDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {

    }
}
