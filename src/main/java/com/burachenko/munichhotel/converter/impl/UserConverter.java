package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserConverter implements EntityDtoConverter<UserEntity, UserDto> {

    @Override
    public UserDto convertToDto(final UserEntity user) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public UserEntity convertToEntity(final UserDto userDto) {
        final UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user);
        return user;
    }
}
