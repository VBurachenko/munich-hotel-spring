package com.burachenko.munichhotel.converter.impl;

import com.burachenko.munichhotel.converter.DtoDboConverter;
import com.burachenko.munichhotel.dbo.Booking;
import com.burachenko.munichhotel.dbo.User;
import com.burachenko.munichhotel.dto.BookingDto;
import com.burachenko.munichhotel.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserConverter implements DtoDboConverter<User, UserDto> {

    private final BookingConverter bookingConverter;

    @Autowired
    public UserConverter(BookingConverter bookingConverter) {
        this.bookingConverter = bookingConverter;
    }

    @Override
    public UserDto convertToDto(final User user) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDto, user, "bookingSet");
        setBookingSetToDto(user, userDto);
        return userDto;
    }

    @Override
    public User convertToDbo(UserDto userDto) {
        final User user = new User();
        BeanUtils.copyProperties(userDto, user, "bookingSet");
        setBookingSetToDbo(userDto, user);
        return user;
    }

    private void setBookingSetToDbo(final UserDto dto, final User dbo){
        final Set<BookingDto> dtoBookingSet = dto.getBookingSet();
        if (dtoBookingSet != null){
            for (BookingDto bookingDto : dtoBookingSet) {
                bookingDto.setUser(null);
            }
        }
        final Set<Booking> bookingSet = bookingConverter.convertToDbo(dtoBookingSet);
        dbo.getBookingSet().addAll(bookingSet);
    }

    private void setBookingSetToDto(final User dbo, final UserDto dto){
        final Set<Booking> bookingSet = dbo.getBookingSet();
        if (bookingSet != null){
            for (Booking booking : bookingSet) {
                booking.setUser(null);
            }
        }
        final Set<BookingDto> dtoBookingSet = bookingConverter.convertToDto(bookingSet);
        dto.getBookingSet().addAll(dtoBookingSet);
    }
}
