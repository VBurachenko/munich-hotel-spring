package com.burachenko.munichhotel.tool;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.enumeration.UserRole;

import java.time.LocalDate;
import java.util.HashSet;

public class MockData {

    public static UserEntity userEntity(){
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(10L);
        userEntity.setEmail("email");
        userEntity.setPassword("11qq22ww33ee");
        userEntity.setName("Name");
        userEntity.setSurname("Surname");
        userEntity.setTelNum("+1111");
        userEntity.setBirthday(LocalDate.of(2000, 12, 12));
        userEntity.setDiscount(0);
        userEntity.setBlocking(0);
        userEntity.setRole(UserRole.CUSTOMER);
        userEntity.setGenderMale(true);
        userEntity.setBookingSet(new HashSet<>());
        return userEntity;
    }

    public static UserDto userDto(){
        final UserDto userDto = new UserDto();
        userDto.setId(10L);
        userDto.setEmail("email");
        userDto.setPassword("11qq22ww33ee");
        userDto.setName("Name");
        userDto.setSurname("Surname");
        userDto.setTelNum("+1111");
        userDto.setBirthday(LocalDate.of(2000, 12, 12));
        userDto.setDiscount(0);
        userDto.setBlocking(0);
        userDto.setRole(UserRole.CUSTOMER);
        userDto.setGenderMale(true);
        userDto.setBookingSet(new HashSet<>());
        return userDto;
    }
}
