package com.burachenko.munichhotel.tool;

import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.enumeration.UserRole;

import java.time.LocalDate;

public class MockData {

    public static UserEntity userEntity(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(10L);
        userEntity.setEmail("email");
        userEntity.setPassword("11qq22ww33ee");
        userEntity.setName("Name");
        userEntity.setName("Surname");
        userEntity.setTelNum("+1111");
        userEntity.setBirthday(LocalDate.of(2000, 12, 12));
        userEntity.setDiscount(0);
        userEntity.setBlocking(0);
        userEntity.setRole(UserRole.CUSTOMER);
        userEntity.setGenderMale(true);
        return userEntity;
    }
}
