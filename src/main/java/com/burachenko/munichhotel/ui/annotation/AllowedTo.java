package com.burachenko.munichhotel.ui.annotation;

import com.burachenko.munichhotel.enumeration.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface AllowedTo {

    UserRole[] value() default {UserRole.ADMIN, UserRole.MODER, UserRole.CUSTOMER};
}
