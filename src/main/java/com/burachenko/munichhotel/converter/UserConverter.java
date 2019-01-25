package com.burachenko.munichhotel.converter;

import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.UserEntity;
import com.burachenko.munichhotel.enumeration.UserBlocking;
import com.burachenko.munichhotel.enumeration.UserGender;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserConverter extends AbstractConverter<UserEntity, UserDto> {

    @Override
    public UserDto convertToDto(final UserEntity userEntity) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userEntity, userDto, "blocking", "genderMale");
        userDto.setGenderMale(convertGender(userEntity.getGenderMale()));
        userDto.setBlocking(UserBlocking.values()[userEntity.getBlocking()]);
        return userDto;
    }

    @Override
    public UserEntity convertToEntity(final UserDto userDto) {
        final UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity, "blocking", "genderMale");
        userEntity.setGenderMale(convertGender(userDto.getGenderMale()));
        userEntity.setBlocking(userDto.getBlocking().ordinal());
        return userEntity;
    }

    private boolean convertGender(UserGender gender){
        return gender.equals(UserGender.M);
    }

    private UserGender convertGender(Boolean genderMale){
        if (genderMale){
            return UserGender.M;
        }
        return UserGender.F;
    }
}
