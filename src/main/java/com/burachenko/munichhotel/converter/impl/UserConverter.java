package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserConverter implements EntityDtoConverter<UserEntity, UserDto> {

    @Override
    public UserDto convertToDto(final UserEntity userEntity) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto, "blocking");
        userDto.setBlocking(UserBlocking.values()[userEntity.getBlocking()]);
        return userDto;
    }

    @Override
    public UserEntity convertToEntity(final UserDto userDto) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity, "blocking");
        userEntity.setBlocking(userDto.getBlocking().ordinal());
        return userEntity;
    }
}
