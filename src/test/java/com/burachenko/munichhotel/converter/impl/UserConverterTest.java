package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.tool.MockData;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserConverterTest {

    private final UserConverter userConverter = new UserConverter(
                                                        new BookingConverter(
                                                                    new InvoiceConverter(), new RoomConverter()));

    @Test
    public void convertToDto(){
        final UserEntity userEntity = MockData.userEntity();
        final UserDto userDto = userConverter.convertToDto(userEntity);
        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getName(), userDto.getName());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getSurname(), userDto.getSurname());
        assertEquals(userEntity.getPassword(), userDto.getPassword());
        assertEquals(userEntity.getTelNum(), userDto.getTelNum());
        assertEquals(userEntity.getGenderMale(), userDto.getGenderMale());
        assertEquals(userEntity.getDiscount(), userDto.getDiscount());
        assertEquals(userEntity.getBlocking(), userDto.getBlocking());
        assertEquals(userEntity.getRole(), userDto.getRole());
        assertEquals(userEntity.getBookingSet(), userDto.getBookingSet());
    }

    @Test
    public void convertToEntity(){
        final UserDto userDto = MockData.userDto();
        final UserEntity userEntity = userConverter.convertToEntity(userDto);
        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getName(), userDto.getName());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getSurname(), userDto.getSurname());
        assertEquals(userEntity.getPassword(), userDto.getPassword());
        assertEquals(userEntity.getTelNum(), userDto.getTelNum());
        assertEquals(userEntity.getGenderMale(), userDto.getGenderMale());
        assertEquals(userEntity.getDiscount(), userDto.getDiscount());
        assertEquals(userEntity.getBlocking(), userDto.getBlocking());
        assertEquals(userEntity.getRole(), userDto.getRole());
        assertEquals(userEntity.getBookingSet(), userDto.getBookingSet());
    }

}