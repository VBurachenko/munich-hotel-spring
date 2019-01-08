package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.UserAccountDto;
import com.burachenko.munichhotel.entity.UserAccountEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserAccountConverter implements EntityDtoConverter<UserAccountEntity, UserAccountDto> {

    @Override
    public UserAccountDto convertToDto(final UserAccountEntity entity) {
        final UserAccountDto userAccountDto = new UserAccountDto();
        BeanUtils.copyProperties(entity, userAccountDto);
        return userAccountDto;
    }

    @Override
    public UserAccountEntity convertToEntity(final UserAccountDto dto) {
        final UserAccountEntity userAccountEntity = new UserAccountEntity();
        BeanUtils.copyProperties(dto, userAccountEntity);
        return userAccountEntity;
    }
}
