package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoDboConverter<User, UserDto> {

    @Override
    public UserDto convertToDto(final User user) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        return userDto;
    }

    @Override
    public User convertToDbo(UserDto userdto) {
        final User user = new User();
        BeanUtils.copyProperties(userdto, user);
        return null;
    }
}
