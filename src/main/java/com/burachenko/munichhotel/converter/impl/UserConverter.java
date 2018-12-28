package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.EntityDtoConverter;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.UserDto;
import com.burachenko.munichhotel.entity.BookingEntity;
import com.burachenko.munichhotel.entity.UserEntity;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class UserConverter implements EntityDtoConverter<UserEntity, UserDto> {

    private final BookingConverter bookingConverter;

    @Override
    public UserDto convertToDto(final UserEntity user) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto, "bookingSet");
        setBookingSetToDto(user, userDto);
        return userDto;
    }

    @Override
    public UserEntity convertToEntity(final UserDto userDto) {
        final UserEntity user = new UserEntity();
        BeanUtils.copyProperties(userDto, user, "bookingSet");
        setBookingSetToDbo(userDto, user);
        return user;
    }

    private void setBookingSetToDbo(final UserDto dto, final UserEntity dbo){
        final Set<BookingDto> dtoBookingSet = dto.getBookingSet();
        if (dtoBookingSet != null){
            for (final BookingDto bookingDto : dtoBookingSet) {
                bookingDto.setUser(null);
            }
        }
        final Set<BookingEntity> bookingSet = bookingConverter.convertToEntity(dtoBookingSet);
        dbo.getBookingSet().addAll(bookingSet);
    }

    private void setBookingSetToDto(final UserEntity dbo, final UserDto dto){
        final Set<BookingEntity> bookingSet = dbo.getBookingSet();
        if (bookingSet != null){
            for (final BookingEntity booking : bookingSet) {
                booking.setUser(null);
            }
        }
        final Set<BookingDto> dtoBookingSet = bookingConverter.convertToDto(bookingSet);
        dto.getBookingSet().addAll(dtoBookingSet);
    }
}
