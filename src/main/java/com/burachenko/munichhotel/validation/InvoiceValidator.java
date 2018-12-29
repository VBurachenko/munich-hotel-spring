package com.burachenko.munichhotel.validation;

import com.burachenko.munichhotel.dto.InvoiceDto;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class InvoiceValidator implements Validator {
    @Override
    public boolean supports(final Class<?> clazz) {
        return InvoiceDto.class.equals(clazz);
    }

    @Override
    public void validate(final Object target, final Errors errors) {

    }
}
